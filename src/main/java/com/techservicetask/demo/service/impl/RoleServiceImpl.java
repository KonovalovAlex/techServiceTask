package com.techservicetask.demo.service.impl;

import com.techservicetask.demo.entity.Role;
import com.techservicetask.demo.repository.RoleRepository;
import com.techservicetask.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Role findRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Role addRole(Role role){
        return roleRepository.save(role);
    }

}
