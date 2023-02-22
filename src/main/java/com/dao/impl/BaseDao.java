package com.dao.impl;

import com.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 创建BaseDao类，对数据库进行CRUD操作
 *
 * @author yangyibufeng
 * @date 2023/2/3
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @return 如果返回-1表示失败，返回其余表示影响行数
     * @Author yangyibufeng
     * @Description 方法用来执行Insert/Update/Delete语句
     * @Date 2023/2/3 23:04
     */
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param type:         返回的对象类型
     * @param sql:执行的sql语句
     * @param args:sql对应的参数
     * @return T
     * @author yangyibufeng
     * @Description 查询返回一个JavaBean的sql语句
     * @Data 2023/2/3 23:32
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param type:         返回的对象类型
     * @param sql:执行的sql语句
     * @param args:sql对应的参数
     * @return T
     * @author yangyibufeng
     * @Description 查询返回多个JavaBean的sql语句
     * @Data 2023/2/3 23:32
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param sql:  执行的SQL语句
     * @param args: sql对应的参数
     * @return java.lang.Object
     * @author yangyibufeng
     * @description 执行返回一列的sql语句
     * @date 2023/2/3 23:49
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();

        try {
            return queryRunner.query(con, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}