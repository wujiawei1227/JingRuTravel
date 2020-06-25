package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-23 15:50
 **/

public class RouteDaoImp implements RouteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /*
    *
     * @Description //TODO 查询总记录数
     * @Param [cid]
     * @return int
     **/
    @Override
    public int findTotalCount(int cid,String rname) {
        /*  String sql="select count(*) from tab_route where cid=?";*/
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuilder builder=new StringBuilder(sql);
        List list=new ArrayList();
        if (cid!=0){
            //传入cid不为空
            builder.append("  and cid=  ? ");
            list.add(cid);
        }
        if (rname!=null&&rname.length()>0){

            builder.append(" and rname like  ?");
            list.add("%"+rname+"%");

        }
        sql=builder.toString();
        return template.queryForObject(sql,Integer.class,list.toArray());
    }
/*
*
 * @Description //TODO 查询指定页数据
 * @Param [cid, start, end]
 * @return java.util.List
 **/
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        //String sql="select * from tab_route where cid=? limit ?,?";
        String sql="select * from tab_route where 1=1 ";
        StringBuilder builder=new StringBuilder(sql);
        List list=new ArrayList();
        if (cid!=0){
            //传入cid不为空
            builder.append("  and cid= ?");
            list.add(cid);
        }
        if (rname!=null&&rname.length()>0){

            builder.append(" and rname like ?");
            list.add("%"+rname+"%");

        }
        builder.append(" limit ?,?");
        list.add(start);
        list.add(pageSize);
        sql=builder.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
    }

    /*
    *
     * @Description //TODO 根据线路id查找线路
     * @Param [rid]
     * @return cn.itcast.travel.domain.Route
     **/
    @Override
    public Route findOne(int rid) {
        String sql="select * from tab_route where rid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
/*
*
 * @Description //TODO 根据线路id查找附属图片
 * @Param [rid]
 * @return java.util.List<cn.itcast.travel.domain.RouteImg>
 **/
    @Override
    public List<RouteImg> findImg(int rid) {
        String sql="select * from tab_route_img where rid = ?";

        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);

    }
/*
*
 * @Description //TODO 根据线路id查询附属图片
 * @Param [sid]
 * @return cn.itcast.travel.domain.Seller
 **/
    @Override
    public Seller findSell(int sid) {
        String sql="select * from tab_seller where sid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);

    }
    /*
    *
     * @Description //TODO 查看根据线路id和用户id查看线路是否被收藏
     * @Param [Rid, Uid]
     * @return cn.itcast.travel.domain.Favorite
     **/
    public Favorite FindByRidAndUid(int Rid, int Uid) {
        String sql="select * from tab_favorite where rid = ? and uid =?";
        Favorite user=null;
        try {
            user=template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),Rid,Uid);
        } catch (DataAccessException e) {

        }
        return user;
    }
/*
*
 * @Description //TODO 根据线路id查询该线路被收藏次数
 * @Param [rid]
 * @return int
 **/
    @Override
    public int favoriteCount(int rid) {
        String sql="select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql,Integer.class,rid);
    }
    /*
    *
     * @Description //TODO 根据用户id收藏线路
     * @Param [rid, uid]
     * @return void
     **/
    public void addFavorite(int rid,int uid){
        String sql="insert into tab_favorite (rid,date,uid) value(?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }
}
