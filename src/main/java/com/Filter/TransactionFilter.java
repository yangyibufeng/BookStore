package com.Filter;

import com.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;

/**
 * 用来批量处理业务的提交与回滚
 *
 * @author yangyibufeng
 * @date 2023/2/19
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);

            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();

            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}