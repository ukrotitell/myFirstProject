package com.gonchcharov.ru.EPAMFinalProject.service;


import com.gonchcharov.ru.EPAMFinalProject.dao.*;
import com.gonchcharov.ru.EPAMFinalProject.entity.*;
import com.gonchcharov.ru.EPAMFinalProject.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    // need to inject user dao
    @Autowired
    private UserDao userDao;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private StudentsDAO studentsDao;

    @Autowired
    private RoleDao roleDao;



    @Override
    @Transactional
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userDao.findByUserName(userName);
    }

    @Override
    @Transactional
    public User findById(int id) {
        return findById(id);
    }


    @Override
    @Transactional
    public void save(CrmUser crmUser) {
        User user = new User();
        Student student = new Student();
        Teacher teacher = new Teacher();
        // assign user details to the user object
        user.setUserName(crmUser.getUserName());
        user.setPassword(crmUser.getPassword());
        user.setFirstName(crmUser.getFirstName());
        user.setLastName(crmUser.getLastName());
        user.setEmail(crmUser.getEmail());

        if (crmUser.getRole().equals("ROLE_TEACHER")) {
            user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_TEACHER")));
        } else {
            user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_STUDENT")));
        }

        userDao.save(user);

        if (crmUser.getRole().equals("ROLE_TEACHER")) {
            teacher.setUser(user);
            teacherDAO.save(teacher);
        }
        if (crmUser.getRole().equals("ROLE_STUDENT")) {
            student.setUser(user);
            studentsDao.save(student);
        }

    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
