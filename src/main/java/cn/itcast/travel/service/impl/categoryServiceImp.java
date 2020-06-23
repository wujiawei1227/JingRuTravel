package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImp;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.categoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-22 17:30
 **/

public class categoryServiceImp implements categoryService {
    @Override
    public List<Category> listCategory() {
        //从redis中查询
        //获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        /*//使用sortedset排序查询
        Set<String> set =jedis.zrange("category",0,-1);*/
        //上面的方法得不到排序的分数，也就是cid，优化如下
        Set<Tuple> set = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs=null;
        //判断查询的集合是否为空
        if(set==null||set.size()==0){
            //判断为空，从数据库中查询，将查询到的数据存入redis
            CategoryDao dao=new CategoryDaoImp();
            cs=dao.findAll();
            //将集合数据存储到redis中
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }

        }else {
            //如果不为空 将数据存入set
            cs=new ArrayList<Category>();
            for (Tuple name :set) {
                Category category = new Category();
                category.setCname(name.getElement());
                category.setCid((int)name.getScore());
                cs.add(category);

            }

        }

        return cs;
    }
}
