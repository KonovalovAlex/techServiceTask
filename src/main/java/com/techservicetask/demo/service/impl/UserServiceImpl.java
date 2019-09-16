package com.techservicetask.demo.service.impl;

import com.techservicetask.demo.entity.User;
import com.techservicetask.demo.repository.UserRepository;
import com.techservicetask.demo.repository.exception.PersistException;
import com.techservicetask.demo.service.UserService;
import com.techservicetask.demo.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    protected final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public User registr(User user) throws ServiceException {
        this.user = user;
        user = userRepository.findByUsername(user.getUsername());
        if (user != null) {
            throw new PersistException("user is already registered");
        } else {
            encodePassword(getUser().getPassword());
            return saveOrUpdate(this.user);
        }
    }

    private void encodePassword(String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}