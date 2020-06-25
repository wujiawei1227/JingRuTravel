package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.categoryService;
import cn.itcast.travel.service.impl.categoryServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private categoryService service=new categoryServiceImp();
    /*
    *
     * @Description //TODO 查询所有线路分类
     * @Param [request, response]
     * @return void
     **/
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service方法查询
        List<Category> categories = service.listCategory();
        //序列化为json并输出
        writeValue(categories,response);

    }


}
