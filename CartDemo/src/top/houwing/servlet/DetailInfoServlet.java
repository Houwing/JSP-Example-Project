package top.houwing.servlet;

import top.houwing.data.LocalCache;
import top.houwing.data.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
/*
*@author:Houwing
*@date:2018/9/17
*@description: 商品详情servlet
**/

public class DetailInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Objects.equals("/detail/detail.do",request.getServletPath())){
            String productId=request.getParameter("productId");
            if(null!=productId){
                Product product= LocalCache.getProduct(Long.valueOf(productId));
                request.setAttribute("product",product);
                LocalCache.addBrowseLog(product);
            }
        }
        request.getRequestDispatcher("/WEB-INF/views/biz/detail.jsp").forward(request,response);
    }
}
