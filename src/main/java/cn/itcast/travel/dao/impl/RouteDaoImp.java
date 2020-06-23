package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
    public int findTotalCount(int cid) {
        String sql="select count(*) from tab_route where cid=?";
        return template.queryForObject(sql,Integer.class,cid);
    }
/*
*
 * @Description //TODO 查询指定页数据
 * @Param [cid, start, end]
 * @return java.util.List
 **/
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql="select * from tab_route where cid=? limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
    }
}
