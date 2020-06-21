package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.userServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServletServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
