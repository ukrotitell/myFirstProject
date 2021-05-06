package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.dao.RoleDao;
import com.gonchcharov.ru.EPAMFinalProject.dao.StudentsDAO;
import com.gonchcharov.ru.EPAMFinalProject.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDAO;

    @Override
    @Transactional
    public Role findRoleByName(String theRoleName) {
        return roleDAO.findRoleByName(theRoleName);
    }
}
