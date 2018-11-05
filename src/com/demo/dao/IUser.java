package com.demo.dao;

import java.util.ArrayList;

import com.demo.entity.User;

public interface IUser {

	boolean findByLogin(String name, String pwd);

	ArrayList<User> findAll();

	boolean delete(Integer id);

	User findById(Integer id);

	boolean update(User user);

}
