package top.houwing.servlet;

import top.houwing.data.LocalCache;
import top.houwing.data.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
/*
*@author:Houwing
*@date:2018/9/17
*@description:浏览记录servlet
**/

public class BrowseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Objects.equals("/browse/list.do",request.getServletPath())){
            request.setAttribute("products",LocalCache.getBrowseLogs());
            request.getRequestDispatcher("/WEB-INF/views/biz/browse_list.jsp").forward(request,response);
        }else if(Objects.equals("/browse/delete.do",request.getServletPath())){
            String productId=request.getParameter("productId");
            if(null!=productId){
                LocalCache.deleteBroeseLog(Long.valueOf(productId));
            }
            response.sendRedirect("/browse/list.do");
        }
    }
}
