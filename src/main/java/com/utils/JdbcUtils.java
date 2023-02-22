package com.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 3、编写工具类 JdbcUtil
 *
 * @author yangyibufeng
 * @date 2023/2/3
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 如果返回null就是获取连接失败<br />有值就是成功
     * @Author yangyibufeng
     * @Description 获取数据库链接池中的链接
     * @Date 2023/2/3 17:07
     */
    public static Connection getConnection() {
        Connection conn = conns.get();

        if (conn == null) {
            try {
                conn = dataSource.getConnection();//如果没有链接，就从连接池中取
                conns.set(conn);//将获取到的连接保存到ThreadLocal对象中，以便以后的jdbc操作
                conn.setAutoCommit(false);//设置为手动操作
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * @return void:
     * @author yangyibufeng
     * @description 提交事务，关闭并释放连接
     * @date 2023/2/18 19:25
     */
    public static void commitAndClose() {
        Connection connection = conns.get();

        if (connection != null) {//如果连接不为空，证明之前使用过该链接
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //一定要执行remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }

    /**
     * @return void:
     * @author yangyibufeng
     * @description 回滚事务，关闭并释放连接
     * @date 2023/2/18 19:28
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();

        if (connection != null) {//如果连接不为空，证明之前使用过该链接
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //一定要执行remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }

    /**
     * @Author yangyibufeng
     * @Description 关闭连接，放回数据库连接池
     * @Date 2023/2/3 17:08


    public static void close(Connection conn){
    if(conn != null){
    try {
    conn.close();
    } catch (SQLException e) {
    e.printStackTrace();
    }
    }
    }
     */

}