package cn.itcast.travel.dao;

import java.util.List;

public interface RouteDao {
    public int findTotalCount(int cid);
    public List findByPage(int cid,int start,int pageSize);
}
