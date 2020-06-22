package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface userService {
    public Boolean active(String code);
    public Boolean regiest(User user);
    public User login(User user);
}
