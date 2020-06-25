package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-22 17:24
 **/

public class CategoryDaoImp implements CategoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /*
    *
     * @Description //TODO 查找线路分类
     * @Param []
     * @return java.util.List<cn.itcast.travel.domain.Category>
     **/
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        List<Category> query = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        System.out.println(query.size());
        return query;
    }
}
