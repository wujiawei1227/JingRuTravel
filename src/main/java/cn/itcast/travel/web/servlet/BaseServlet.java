package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String requestURI = req.getRequestURI();

        // 获取方法名称
        String substring = requestURI.substring(requestURI.lastIndexOf("/") + 1);


        try {
            //通过反射得到方法
            Method method = this.getClass().getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    /*
     *
     * @Description //TODO 将传入对象序列化为json并写出到客户端
     * @Param [obj, response]
     * @return void
     **/
    public void writeValue(Object obj, HttpServletResponse response)throws IOException {
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), obj);
    }

    public String writeValueAsString(Object obj, HttpServletResponse response) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(obj);
        return s;
    }
}
