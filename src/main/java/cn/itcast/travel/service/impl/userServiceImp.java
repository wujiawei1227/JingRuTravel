package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.userDaoImp;
import cn.itcast.travel.dao.userDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.userService;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-20 09:59
 **/

public class userServiceImp implements userService {
    @Override
    public Boolean regiest(User user) {
        userDao dao=new userDaoImp();

        User byName = dao.findByName(user.getUsername());
        if (byName != null) {
            //注册失败
            return false;
        }else {
            dao.save(user);
            return true;
        }
    }
}
