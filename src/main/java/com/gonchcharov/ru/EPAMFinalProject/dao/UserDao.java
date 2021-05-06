package com.gonchcharov.ru.EPAMFinalProject.dao;


import com.gonchcharov.ru.EPAMFinalProject.entity.Student;
import com.gonchcharov.ru.EPAMFinalProject.entity.User;

import java.util.List;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);

    public User findById(int id);

}
