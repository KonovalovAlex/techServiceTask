package com.techservicetask.demo.listener;

import com.techservicetask.demo.entity.Product;
import com.techservicetask.demo.entity.Role;
import com.techservicetask.demo.entity.User;
import com.techservicetask.demo.repository.ProductRepository;
import com.techservicetask.demo.repository.RoleRepository;
import com.techservicetask.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class InsertInDb implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private ProductRepository productRepository;

    @Autowired
    public InsertInDb(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role role = roleRepository.findByRole("ROLE_ADMIN");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date());
        user.setRole(role);
        userRepository.save(user);
    }
}
