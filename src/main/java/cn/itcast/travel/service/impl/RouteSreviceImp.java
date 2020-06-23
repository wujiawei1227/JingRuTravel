package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImp;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-23 15:59
 **/

public class RouteSreviceImp implements RouteService {
private RouteDao dao=new RouteDaoImp();
    /*
    *
     * @Description //TODO 按指定分页信息查询
     * @Param [cid, currentPage, pageSize]
     * @return cn.itcast.travel.domain.PageBean
     **/
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        PageBean<Route> bean=new PageBean();
        //设置当前页码数
        bean.setCurrentPage(currentPage);
        //设置页码大小
        bean.setPageSize(pageSize);
        //设置总记录数
       int totalCount=dao.findTotalCount(cid);
       bean.setTotalCount(totalCount);

       //设置总页码数
       int totalPage=totalCount%pageSize==0?(totalCount/pageSize):totalCount/pageSize+1;
       bean.setTotalPage(totalPage);
       //设置当前页码数据集合
       int start=(currentPage-1)*pageSize;
        List<Route> list = dao.findByPage(cid, start, pageSize);
        bean.setList(list);

        return bean;
    }
}
