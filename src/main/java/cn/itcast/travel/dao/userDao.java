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
   /*
   *
    * @Description //TODO 更新用户邮箱验证状态
    * @Param [user]
    * @return void
    **/
   public  void updateStatus(User user);
   /*
   *
    * @Description //TODO 根据code查询用户
    * @Param [code]
    * @return java.lang.Boolean
    **/
   public  User findByCode(String code);
}
