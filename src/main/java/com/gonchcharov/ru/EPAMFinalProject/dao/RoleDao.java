package com.gonchcharov.ru.EPAMFinalProject.dao;


import com.gonchcharov.ru.EPAMFinalProject.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
