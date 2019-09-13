package com.techservicetask.demo.service;

import com.techservicetask.demo.entity.User;
import com.techservicetask.demo.service.exception.ServiceException;

public interface UserService {

    User saveOrUpdate(User entity);
    User registr(User user) throws ServiceException;
    User findByUserName(String name);
}

