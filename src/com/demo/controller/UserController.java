package com.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.dao.IUser;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.entity.User;

@Controller
public class UserController {
	@Resource
	IUser userDao; 
	@RequestMapping("/log")
	public String login() {
		System.out.println("开始登陆");
		return "Login";
	}
	@RequestMapping("login")
	public String login(@RequestParam("name") String name,
						@RequestParam("pwd") String pwd,
						 HttpSession session) {
		System.out.println(name+","+pwd);
		boolean bool = userDao.findByLogin(name,pwd);
		
		if(bool) {
			session.setAttribute("loginUser", "user");
			return "redirect:list";
		}
		return "Login";
	}
	/**
	 * SpringMvc支持servlet常用对象
	 * @param req
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest req) {
		ArrayList<User> list = userDao.findAll();
		req.setAttribute("userList", list);
		return "UserList"; 
		//通过El表达式，jstl标签库来取出结合中的数据
	}
	/**
	 * 删除
	 * 
	 * @PathVariable:
	 * 				将请求URL中的模板变量映射到功能处理方法的参数上。
	 * 				
	 */
	@RequestMapping(value="/del/{id}")
	public String userDel(@PathVariable("id") Integer id){
		System.out.println(id);
		boolean b = userDao.delete(id);
		
		if(true) {
			return "redirect:/list";
		}
		return "redirect:/del/id";
	}
	/**
	 * 跳转到修改页面
	 */
	@RequestMapping(value="/add/{id}")
	public String userUdp(@PathVariable("id") Integer id,Map<String,Object> map){
		System.out.println(id);
		User u = userDao.findById(id);
		map.put("u", u);
		return "udp";
	}
	
	/**
	 * 执行修改
	 */
	@RequestMapping(value="toUpdate")
	public String toUpdate(User user) {
		System.out.println(user.getId()+","+user.getName()+","+user.getPwd());
		boolean b = userDao.update(user);
		if(b) {
			return "redirect:/list";
		}
		return null;
	}
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id,
						Map<String ,Object> map) {
		System.out.println("-------------");
		System.out.println(id+"的值");
		if(id!=null) {
			map.put("user", userDao.findById(id));
		}
	}
}
