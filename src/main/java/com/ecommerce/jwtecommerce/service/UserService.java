package com.ecommerce.jwtecommerce.service;

import com.ecommerce.jwtecommerce.dao.RoleDao;
import com.ecommerce.jwtecommerce.dao.UserDao;
import com.ecommerce.jwtecommerce.entity.Role;
import com.ecommerce.jwtecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    public User registerNewUser(User user){

        Set<Role> roles = new HashSet();
        Role role = roleDao.findById("User").get();
        roles.add(role);
        user.setRoles(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public void initiateUsersAndRoles(){

        Role adminRole = new Role();
        Role userRole = new Role();

        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        userRole.setRoleName("User");
        userRole.setRoleDescription("User role");

        User newUser1 = new User();
        newUser1.setUserName("anuja93");
        newUser1.setUserFirstName("Anuja");
        newUser1.setUserLastName("Weerakoon");
        newUser1.setUserPassword(getEncodedPassword("1234"));
        Set<Role> roles1 = new HashSet<>();
        roles1.add(adminRole);
        newUser1.setRoles(roles1);

        User newUser2 = new User();
        newUser2.setUserName("dana93");
        newUser2.setUserFirstName("Dana");
        newUser2.setUserLastName("Payagala");
        newUser2.setUserPassword(getEncodedPassword("1234"));
        Set<Role> roles2 = new HashSet<>();
        roles2.add(userRole);
        newUser2.setRoles(roles2);

        roleDao.save(adminRole);
        roleDao.save(userRole);
        userDao.save(newUser1);
        userDao.save(newUser2);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

}
