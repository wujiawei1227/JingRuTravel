package cn.itcast.travel.domain;


import java.util.List;

/**
 * @program: travel
 * @description:
 * @author: wudaren
 * @create: 2020-06-23 10:56
 **/

public class PageBean<T>{
    private int totalCount;//总记录数
    private  int totalPage;//总页码数
    private  int currentPage;//当前页码数
    private  int PageSize;//每页显示条数
    private List list;//每页显示的数据集合

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", PageSize=" + PageSize +
                '}';
    }
}
