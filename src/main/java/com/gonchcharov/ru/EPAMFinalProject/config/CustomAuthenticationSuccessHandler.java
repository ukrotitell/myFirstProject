package com.gonchcharov.ru.EPAMFinalProject.config;


import com.gonchcharov.ru.EPAMFinalProject.dao.CoursesDAO;
import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import com.gonchcharov.ru.EPAMFinalProject.entity.User;
import com.gonchcharov.ru.EPAMFinalProject.service.CoursesService;
import com.gonchcharov.ru.EPAMFinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");
		String userName = authentication.getName();
		System.out.println("userName=" + userName);
		User theUser = userService.findByUserName(userName);
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_TEACHER")) {
			response.sendRedirect("/teachers");
		}
		if (roles.contains("ROLE_STUDENT")) {
			response.sendRedirect("/students");
		}
	}

}
