package com.techservicetask.demo.service.impl;


import com.techservicetask.demo.entity.Role;
import com.techservicetask.demo.entity.User;
import com.techservicetask.demo.jwtAuthentication.JwtUser;
import com.techservicetask.demo.service.JwtUserService;
import com.techservicetask.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserServiceImpl implements JwtUserService {

    private final UserService userService;

    @Autowired
    public JwtUserServiceImpl(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", name));
        } else {
            return generateJwtUser(user);
        }
    }

    @Override
    public JwtUser generateJwtUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        List<GrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority apply = apply(role);
        list.add(apply);
        return list;
    }

    private SimpleGrantedAuthority apply(Role role) {
        return new SimpleGrantedAuthority(role.getRole());
    }
}
