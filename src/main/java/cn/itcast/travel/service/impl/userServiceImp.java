package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.userDaoImp;
import cn.itcast.travel.dao.userDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.userService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-20 09:59
 **/

public class userServiceImp implements userService {
    private     userDao dao= new userDaoImp();


    /*
    *
     * @Description //TODO 验证并激活用户邮箱
     * @Param [code]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean active(String code) {

            //根据code去查询用户
            User user=dao.findByCode(code);
            if (user!=null){
                //设置参数 验证邮箱成功
               dao.updateStatus(user);
               return true;
            }
        return false;
    }

    /*
    *
     * @Description //TODO 注册用户
     * @Param [user]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean regiest(User user) {


        User byName = dao.findByName(user.getUsername());
        if (byName != null) {
            //注册失败
            return false;
        }else {
            //设置用户唯一注册id
            user.setCode(UuidUtil.getUuid());
            //设置激活状态
            user.setStatus("N");
            dao.save(user);

            //发送验证邮件
            String email = user.getEmail();
            String href="<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【静茹旅游网】</a>";
            MailUtils.sendMail(email,href,"邮箱验证");

            return true;

        }
    }
}
