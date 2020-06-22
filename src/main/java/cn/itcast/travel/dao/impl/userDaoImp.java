package cn.itcast.travel.dao.impl;


import cn.itcast.travel.dao.userDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-20 10:01
 **/

public class userDaoImp implements userDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());



    @Override
    public User findByName(String name) {
        User user = null;
        try {
            String sql="select * from tab_user where username=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), name);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public void save(User user) {
        System.out.println(user);
        String sql="insert into tab_user (username,password,name,birthday,sex,telephone,email,status,code) value(?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());

    }

    @Override
    public void updateStatus(User user) {
       String sql="update tab_user set status='Y' where uid=?";
       template.update(sql,user.getUid());

    }

    @Override
    public User findByCode(String code) {
        User user = null;
        String sql="select * from tab_user where code=?";

        try {

            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {

        }

        return user;
    }

    @Override
    public User findByNameAndPassword(User user) {
        User use = null;
        try {
            String sql="select * from tab_user where username=? and password=?";
            use = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(),user.getPassword());
        } catch (Exception e) {

        }

        return use;
    }
}

