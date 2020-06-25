package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;


public interface RouteService {
    public PageBean pageQuery(int cid, int currentPage, int pageSize,String rname);
    public Route findOne(int rid);
    public Favorite FindUserByRidAndUid(int rid, int uid);
    public int favoriteCount(int rid);
    public void addFavorite(int rid,int uid);
}
