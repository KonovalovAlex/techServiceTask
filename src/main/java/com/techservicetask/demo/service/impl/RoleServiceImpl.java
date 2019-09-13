package com.techservicetask.demo.service.impl;

import com.techservicetask.demo.entity.Role;
import com.techservicetask.demo.repository.RoleRepository;
import com.techservicetask.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findUserRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }
    public Role addRole(Role role){

        return roleRepository.save(role);
    }

}
