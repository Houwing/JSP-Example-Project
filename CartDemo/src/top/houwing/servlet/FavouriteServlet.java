package top.houwing.servlet;

import top.houwing.data.LocalCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
/*
*@author:Houwing
*@date:2018/9/17
*@description:收藏servlet
**/
public class FavouriteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Objects.equals("/favourite/favourite.do",req.getServletPath())){
            String productId=req.getParameter("productId");
            if(null!=productId){
                LocalCache.addFavourite(LocalCache.getProduct(Long.valueOf(productId)));
                resp.sendRedirect("/favourite/list.do");
            }
        }else if(Objects.equals("/favourite/list.do",req.getServletPath())) {
            req.setAttribute("products",LocalCache.getFavourites());
            req.getRequestDispatcher("/WEB-INF/views/biz/favourite.jsp").forward(req, resp);
        }else if(Objects.equals("/favourite/delete.do",req.getServletPath())){
            String productId=req.getParameter("productId");
            if(null!=productId){
                LocalCache.deleteFavourite(Long.valueOf(productId));
                resp.sendRedirect("/favourite/list.do");
            }
        }
    }
}
