package com.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.demo.dao.IUser;
import com.demo.entity.User;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport
							implements IUser{
	
	
	@Resource(name="sqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public boolean findByLogin(String name, String pwd) {
		User u = new User();
		u.setName(name);
		u.setPwd(pwd);
		u = getSqlSession().selectOne("com.demo.dao.userMapper.findByLogin", u);
		return u!=null?true:false;
	}

	@Override
	public ArrayList<User> findAll() {
		List<User> list = 	getSqlSession().selectList("com.demo.dao.userMapper.findAll");
		return (ArrayList<User>) list;
	}
	@Override
	public boolean delete(Integer id) { //参考Mybatis根据ID删除
		return false;
	}
	@Override
	public User findById(Integer id) {//根据ID 查询询
		return getSqlSession().selectOne("com.demo.dao.userMapper.findById",id);
	}
	@Override
	public boolean update(User user) {//根据对象来更新
		return getSqlSession().update("com.demo.dao.userMapper.update", user)>=1?true:false;
	}
	
}
