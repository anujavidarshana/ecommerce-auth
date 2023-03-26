package com.ecommerce.jwtecommerce.controller;

import com.ecommerce.jwtecommerce.entity.Role;
import com.ecommerce.jwtecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }

    @GetMapping({"/onlyAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String adminResponse(){
        return "Success";
    }

    @GetMapping({"/onlyUser"})
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public String userResponse(){
        return "Success";
    }
}
