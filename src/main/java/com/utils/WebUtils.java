package com.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author yangyibufeng
 * @date 2023/2/8
 * @deprecate BeanUtils 工具类，它可以一次性的把所有请求的参数注入到 JavaBean 中。
 * BeanUtils 工具类，经常用于把 Map 中的值注入到 JavaBean 中，或者是对象属性值的拷贝操作。
 */
public class WebUtils {
//    public static void copyParamToBean(HttpServletRequest req,Object bean){
//        try {
////            System.out.println("注入之前" + bean);
//
//            BeanUtils.populate(bean,req.getParameterMap());
////            System.out.println("注入之后" + bean);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    优化方向（思路）：
//      1.  首先因为Java代码分为三层 Dao层、Service层和Web层，但是只有Web层有HttpServletRequest类，
//          为了降低代码的耦合度、而且将Map中的键值对注入到JavaBean中是一个常用的需求、而且HttpServletRequest传回的数据本质上也是一个个键值对的集合
//          所以首先将HttpServletRequest 改为 Map
//          同时其他使用该方法时，应该改为 ：
//              User user = new User();
//              WebUtils.copyParamToBean(req.getParameterMap(), user);

//      2.  为了将原来的两行代码优化为一行，原来的两行代码可以改为：
//              User user = (User)WebUtils.copyParamToBean(req.getParameterMap(), new user());
//          所以要将方法的返回值改为object，并且要让方法返回注入后的JavaBean

//      3.  为了让该方法有更大的作用范围，而且不用在进行类型强制转换，在方法中引入泛型
//          原来的代码可以更加精简：
//              User user = WebUtils.copyParamToBean(req.getParameterMap(), new user());


    public static <T> T copyParamToBean(Map value, T bean) {
        try {
//            System.out.println("注入之前" + bean);

            BeanUtils.populate(bean, value);
//            System.out.println("注入之后" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * @param strInt:
     * @param defaultValue:
     * @return int:
     * @author yangyibufeng
     * @description 将字符串转换成为int类型的数据
     * @date 2023/2/9 15:15
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

}