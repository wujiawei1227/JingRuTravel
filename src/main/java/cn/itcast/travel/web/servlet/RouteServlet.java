package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteSreviceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service=new RouteSreviceImp();
    /*
    *
     * @Description //TODO 分页查询
     * @Param [request, response]
     * @return void
     **/
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cids = request.getParameter("cid");
        String currentPages = request.getParameter("currentPage");
        String pageSizes = request.getParameter("pageSize");
        int cid=0;
        if (cids != null && cids != "") {
            cid= Integer.parseInt(cids);
        }
        int currentPage=1;
        if(currentPages!=null&&currentPages!=""){
            currentPage=Integer.parseInt(currentPages);
        }
        int pageSize=5;
        if(pageSizes!=null&&pageSizes!=""){
            pageSize=Integer.parseInt(pageSizes);
        }

        PageBean<Route> bean = service.pageQuery(cid, currentPage, pageSize);
        writeValue(bean,response);

    }

}
