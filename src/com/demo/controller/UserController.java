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
		System.out.println("��ʼ��½");
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
	 * SpringMvc֧��servlet���ö���
	 * @param req
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest req) {
		ArrayList<User> list = userDao.findAll();
		req.setAttribute("userList", list);
		return "UserList"; 
		//ͨ��El���ʽ��jstl��ǩ����ȡ������е�����
	}
	/**
	 * ɾ��
	 * 
	 * @PathVariable:
	 * 				������URL�е�ģ�����ӳ�䵽���ܴ������Ĳ����ϡ�
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
	 * ��ת���޸�ҳ��
	 */
	@RequestMapping(value="/add/{id}")
	public String userUdp(@PathVariable("id") Integer id,Map<String,Object> map){
		System.out.println(id);
		User u = userDao.findById(id);
		map.put("u", u);
		return "udp";
	}
	
	/**
	 * ִ���޸�
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
		System.out.println(id+"��ֵ");
		if(id!=null) {
			map.put("user", userDao.findById(id));
		}
	}
}
