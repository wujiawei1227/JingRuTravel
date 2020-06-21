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
import java.sql.ResultSet;
import java.util.Map;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
