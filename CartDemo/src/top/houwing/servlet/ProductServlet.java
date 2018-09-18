package top.houwing.servlet;

import top.houwing.data.LocalCache;
import top.houwing.data.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
*@author:Houwing
*@date:2018/9/5
*@description:商品servlet
**/
public class ProductServlet extends HttpServlet {

    private static final int PRODUCT_NUMBER_PER_PAGE=8;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       req.setAttribute("products",LocalCache.getProductMap());
//       req.getRequestDispatcher("/WEB-INF/views/biz/list.jsp").forward(req,resp);
        String pageStr=req.getParameter("page");
        String name=req.getParameter("title");


        int page=1;
        if(null!=pageStr && !"".equals(pageStr)){
            page=Integer.parseInt(pageStr);
        }


        int totalProduct=LocalCache.getProductCount(name);
        int totalPage=totalProduct%8 > 0 ? totalProduct/8 + 1 : totalProduct/8;
        req.setAttribute("curPage",page);
        req.setAttribute("prePage",page > 1 ? page-1 : 1);
        req.setAttribute("nextPage",totalPage>page ? page+1 : totalPage);
        req.setAttribute("totalPage",totalPage);
        req.setAttribute("title",name);

        req.setAttribute("products",LocalCache.getProducts(page,PRODUCT_NUMBER_PER_PAGE,name));
       req.getRequestDispatcher("/WEB-INF/views/biz/list.jsp").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
