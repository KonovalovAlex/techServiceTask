package com.techservicetask.demo.controller;

import com.techservicetask.demo.entity.Role;
import com.techservicetask.demo.entity.User;
import com.techservicetask.demo.jwtAuthentication.JwtAuthenticationResponse;
import com.techservicetask.demo.service.JwtUserService;
import com.techservicetask.demo.service.RoleService;
import com.techservicetask.demo.service.UserService;
import com.techservicetask.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;
    //    @Value("${roleName}")
    private String roleName = "ROLE_USER";
    private User user;
    private Role role;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserService jwtUserService;


    @RequestMapping(value = "/registrate", method = RequestMethod.POST)
    public ResponseEntity<?> registrateUser(@RequestBody User userRequest) {
        if (checkCredentials(userRequest)) {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        User user = userService.registr(initEntity(userRequest));
        String token = jwtTokenUtil.generateToken(jwtUserService.generateJwtUser(user));
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    private User initEntity(User namePassword) {
        role = roleService.findUserRole(roleName);
        user = new User();
        user.setLastPasswordResetDate(getCurrentTime());
        user.setUsername(namePassword.getUsername());
        user.setPassword(namePassword.getPassword());
        user.setEnabled(true);
        user.setRole(role);
        return user;
    }



    private boolean checkCredentials(User user) {
        user = userService.findByUserName(user.getUsername());
        return user != null;
    }

    private Date getCurrentTime() {
        return new Date();
    }

    @Autowired
    public UserRestController(UserService userService, RoleService roleService, JwtTokenUtil jwtTokenUtil, JwtUserService jwtUserService) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserService = jwtUserService;
    }
}






