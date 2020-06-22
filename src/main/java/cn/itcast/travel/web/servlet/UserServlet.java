package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.userServiceImp;
import cn.itcast.travel.service.userService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private ResultInfo info = new ResultInfo();
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode==null||!checkcode.equalsIgnoreCase(check)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        }else {
            //获取参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            //将参数封装进user
            User user=new User();
            try {
                BeanUtils.populate(user,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //调用service层方法完成注册
            userService service=new userServiceImp();
            Boolean bool  = service.regiest(user);

            if (bool){
                //注册成功
                info.setFlag(true);
            }else {
                info.setFlag(false);
                info.setErrorMsg("注册失败");
            }
        }
        //将info序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(info);
        response.setContentType("application/json,charset=utf-8");
        response.getWriter().write(s);

    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        if (checkcode_server.equalsIgnoreCase(check)){
            //验证码正确
            userService service=new userServiceImp();
            User user=new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user = service.login(user);

            if (user==null){
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误");
            }
            if (user!=null&&!"Y".equals(user.getStatus())){
                info.setFlag(false);
                info.setErrorMsg("账号未激活，请登录邮箱激活");

            }
            if (user!=null&&"Y".equals(user.getStatus())){
                info.setFlag(true);
                //将user写入session中
                request.getSession().setAttribute("user",user);
            }

        }else{
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        }
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),info);
    }
    public void finduserbyname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        response.setContentType("application/json,charset=utf-8");
        response.getWriter().write(s);
    }
    public void exist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//销毁session
        request.getSession().invalidate();
        //重定向到登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    public void activeuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String code = request.getParameter("code");
        Boolean bool=false;
        if (code!=""||code!=null){
            //根据激活码去查询用户
            userServiceImp service=new userServiceImp();
            bool = service.active(code);
        }
        String msg=null;
        if (bool){
            //激活成功
            msg="激活成功，请<a href='login.html'>登录</a>";
        }else {
            //激活失败
            msg="验证失败，请联系管理员";
        }
        response.getWriter().write(msg);
    }
    }

