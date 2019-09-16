package com.techservicetask.demo.service;


import com.techservicetask.demo.entity.Role;

public interface RoleService {

    Role findRole(String roleName);
    Role addRole(Role role);

}
