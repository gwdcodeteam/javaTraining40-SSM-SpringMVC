package com.demo.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.dao.impl.UserDaoImpl;
import com.demo.entity.User;

public class TestUser {
	public static void main(String[] args) {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDaoImpl udp = (UserDaoImpl)ac.getBean("userDaoImpl");
		User u = new User();
		u.setId(3);
		u.setName("33");
		System.out.println(udp.update(u));
	}
}	
