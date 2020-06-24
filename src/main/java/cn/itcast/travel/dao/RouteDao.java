package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;

public interface RouteDao {
    public int findTotalCount(int cid,String rname);
    public List findByPage(int cid,int start,int pageSize,String rname);
    public Route findOne(int rid);

    List<RouteImg> findImg(int rid);

    Seller findSell(int sid);
}
