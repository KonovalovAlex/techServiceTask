package com.techservicetask.demo.service;
import com.techservicetask.demo.entity.User;
import com.techservicetask.demo.jwtAuthentication.JwtUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface JwtUserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username);
    JwtUser generateJwtUser(User user);
}
