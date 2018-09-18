package top.houwing.filter;


import javax.servlet.*;
import java.io.IOException;
/*
*@author:Houwing
*@date:2018/9/5
*@description:字符集过滤器
**/
public class CharsetFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding=filterConfig.getInitParameter("encoding");
        System.out.printf("Initing charsetfilter. Get charset = "+encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
