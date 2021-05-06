package com.gonchcharov.ru.EPAMFinalProject.service;


import com.gonchcharov.ru.EPAMFinalProject.entity.User;
import com.gonchcharov.ru.EPAMFinalProject.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);
	public User findById(int id);
	public void save(CrmUser crmUser);
}
