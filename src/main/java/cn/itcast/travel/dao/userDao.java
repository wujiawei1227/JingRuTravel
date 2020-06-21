package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface userDao {
    /*
    *
     * @Description //TODO 根据name查询是否有该用户
     * @Param [name]
     * @return boolean
     **/
   public User findByName(String name);
   /*
   *
    * @Description //TODO 保存用户信息
    * @Param [user]
    * @return boolean
    **/
   public void save(User user);
}
