package top.houwing.servlet;

import top.houwing.data.Cart;
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
*@date:2018/9/5
*@description:购物车servlet
**/
public class CartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Objects.equals("/cart/cart.do",req.getServletPath())) {
            String ProductId = req.getParameter("productId");
            if (null != ProductId) {
                Product product = LocalCache.getProduct(Long.valueOf(ProductId));
                LocalCache.addCart(product);
            }
            resp.sendRedirect("/cart/list.do");
        }else if(Objects.equals("/cart/list.do",req.getServletPath())){
            req.setAttribute("carts",LocalCache.getCarts());
            req.getRequestDispatcher("/WEB-INF/views/biz/cart.jsp").forward(req,resp);
        }else if(Objects.equals("/cart/delete.do",req.getServletPath())){
            String productId=req.getParameter("productId");
            if(null!=productId){
                LocalCache.delCart(Long.valueOf(productId));
            }
            resp.sendRedirect("/cart/list.do");
        }else if(Objects.equals("/cart/incr.do",req.getServletPath())){
            String productId=req.getParameter("productId");
            if(null!=productId){
                LocalCache.incrCart(Long.valueOf(productId));
            }
            resp.sendRedirect("/cart/list.do");
        }else if(Objects.equals("/cart/decr.do",req.getServletPath())){
            String productId=req.getParameter("productId");
            if(null!=productId){
                LocalCache.decrCart(Long.valueOf(productId));
            }
            resp.sendRedirect("/cart/list.do");
        }else if(Objects.equals("/cart/settlement.do",req.getServletPath())){
            String[] carts=req.getParameterValues("carts");
            int totalPrice=0;
            if(null!=carts){
                for (int i = 0; i < carts.length; i++) {
                    Cart cart = LocalCache.getCart(Long.valueOf(carts[i]));
                    totalPrice+=cart.getPrice();
                    LocalCache.delCart(cart.getId());
                }
                req.setAttribute("totalPrice",totalPrice);
                req.getRequestDispatcher("/WEB-INF/views/biz/settlement.jsp").forward(req,resp);
            }
        }
    }
}
