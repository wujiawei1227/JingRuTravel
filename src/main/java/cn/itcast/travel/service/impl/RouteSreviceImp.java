package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImp;
import cn.itcast.travel.domain.*;
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
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> bean=new PageBean();
        //设置当前页码数
        bean.setCurrentPage(currentPage);
        //设置页码大小
        bean.setPageSize(pageSize);
        //设置总记录数
       int totalCount=dao.findTotalCount(cid,rname);
       bean.setTotalCount(totalCount);

       //设置总页码数
       int totalPage=totalCount%pageSize==0?(totalCount/pageSize):totalCount/pageSize+1;
       bean.setTotalPage(totalPage);
       //设置当前页码数据集合
       int start=(currentPage-1)*pageSize;
        List<Route> list = dao.findByPage(cid, start, pageSize,rname);
        bean.setList(list);

        return bean;
    }
/*
*
 * @Description //TODO 查询路线的详情信息
 * @Param [rid]
 * @return cn.itcast.travel.domain.Route
 **/
    @Override
    public Route findOne(int rid) {
        Route one = dao.findOne(rid);
        List<RouteImg> list=dao.findImg(rid);
      one.setRouteImgList(list);
        int sid = one.getSid();
        Seller seller=dao.findSell(sid);
        one.setSeller(seller);
        return one;
    }
/*
*
 * @Description //TODO 根据线路id和用户id查询该线路是否被该用户收藏
 * @Param [rid, uid]
 * @return cn.itcast.travel.domain.Favorite
 **/
    @Override
    public Favorite FindUserByRidAndUid(int rid, int uid) {
        return dao.FindByRidAndUid(rid,uid);
    }
/*
*
 * @Description //TODO 查询该线路被收藏次数
 * @Param [rid]
 * @return int
 **/
    @Override
    public int favoriteCount(int rid) {
        return dao.favoriteCount(rid);
    }
    /*
    *
     * @Description //TODO 在此用户背景下将该线路收藏
     * @Param [rid, uid]
     * @return void
     **/
    public void addFavorite(int rid,int uid){
        dao.addFavorite(rid,uid);
    }
}
