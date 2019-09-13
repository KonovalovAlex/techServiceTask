package com.techservicetask.demo.service;


import com.techservicetask.demo.entity.Role;

public interface RoleService {

    Role findUserRole(String roleName);
    Role addRole(Role role);

}
