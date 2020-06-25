package cn.itcast.travel.dao;

import cn.itcast.travel.domain.*;

import java.util.List;

public interface RouteDao {
    public int findTotalCount(int cid,String rname);
    public List findByPage(int cid,int start,int pageSize,String rname);
    public Route findOne(int rid);

   public List<RouteImg> findImg(int rid);

    public Seller findSell(int sid);
    public Favorite FindByRidAndUid(int Rid, int Uid);
    public int favoriteCount(int rid);
    public void addFavorite(int rid,int uid);
}
