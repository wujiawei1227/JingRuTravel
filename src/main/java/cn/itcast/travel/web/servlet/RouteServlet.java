package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
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
        //获取线路分类编号
        String cids = request.getParameter("cid");
        //获取页码
        String currentPages = request.getParameter("currentPage");
        //获取页面大小
        String pageSizes = request.getParameter("pageSize");
        //获取线路名称
        String rname = request.getParameter("rname");
        //此处rname会出现乱码
        if (rname != null && rname != "") {
            //将rname进行重新编码
            rname=new String(rname.getBytes("iso-8859-1"),"utf-8");
        }
        //设置线路编号初始值
        int cid=0;
        if (cids != null && cids != ""&&!"null".equals(cids)) {
            cid= Integer.parseInt(cids);
        }
        //设置页码初始值
        int currentPage=1;
        if(currentPages!=null&&currentPages!=""){
            currentPage=Integer.parseInt(currentPages);
        }
        //设置页码初始大小
        int pageSize=5;
        if(pageSizes!=null&&pageSizes!=""){
            pageSize=Integer.parseInt(pageSizes);
        }

        PageBean<Route> bean = service.pageQuery(cid, currentPage, pageSize,rname);
        writeValue(bean,response);

    }
    /*
    *
     * @Description //TODO 获取线路详情
     * @Param [request, response]
     * @return void
     **/
    public void detil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rids = request.getParameter("rid");
        int rid=0;
        if (rids!=null&&rids!=""){
            rid = Integer.parseInt(rids);
        }

       Route route=service.findOne(rid);
        writeValue(route,response);
    }
    /*
    *
     * @Description //TODO 判断线路是否被当前用户收藏
     * @Param [request, response]
     * @return void
     **/
    public void favourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        int uid = user.getUid();
        String rids = request.getParameter("rid");
        int rid = Integer.parseInt(rids);
        Favorite favorite = service.FindUserByRidAndUid(rid, uid);
        //判断是否查询到收藏信息
       if (favorite!=null){
           // 查询到，返回true
           writeValue(true,response);
       }else {
           //未查询到，返回false
           writeValue(false,response);
       }

    }
    /*
    *
     * @Description //TODO 统计线路被收藏的次数
     * @Param [request, response]
     * @return void
     **/
    public void favouriteCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rids = request.getParameter("rid");
        int rid = Integer.parseInt(rids);
        int i = service.favoriteCount(rid);
        writeValue(i,response);

    }
    /*
    *
     * @Description //TODO 在当前用户信息下将线路收藏
     * @Param [request, response]
     * @return void
     **/
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        //判断用户是否登录
        if (user!=null) {
            //用户已登录 ，获取用户id进行查询
            int uid = user.getUid();
            String rids = request.getParameter("rid");
            int rid = Integer.parseInt(rids);
            service.addFavorite(rid,uid);
            writeValue(true,response);
        }else {
            //用户没有登陆，返回错误信息
            writeValue(false,response);
        }
    }

}
