package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.User;

public class AuthorizedInterceptor implements HandlerInterceptor{

	//1.准备一个数组，存放不用来接的请求路径后缀名
	public static final String[] ignore_uri= {"log","login"};
	
	
	/**
	 * 请求完成之后来执行此方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("拦截器完成了请求");
	}
	/**
	 * Controller方法，调用之后执行，方法中可以对ModelAndView进行操作
	 * 也只能在当Interceptor的prehandle方法的返回值为true时执行
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
			System.out.println("拦截器方法调用之后");
	}
	/**
	 * 处理拦截器时使用，该方法将在Controller处理之前进行调用，返回值为true拦截器才会继续往下执行，
	 * 该方法的返回值为FALSE时，整个请求结束
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, 
				HttpServletResponse resp, Object handler) throws Exception {
		System.out.println("拦截");
		boolean flag = false;
		String servletPath = req.getServletPath();//获取请求对象的访问路径
	for (String url : ignore_uri) {
			if(servletPath.contains(url)) {
				flag = true;
				break;
			}
		}
    if (!flag){  /** 拦截请求 */
    	/** 1.获取session中的用户  */
    	String user =  (String) req.getSession().getAttribute("loginUser");
    	/** 2.判断用户是否已经登录 */
    	if(user == null){
    		 /** 如果用户没有登录，跳转到登录页面 */
    		req.setAttribute("message", "请先登录再访问网站!");
    		req.getRequestDispatcher("log").forward(req, resp);
    		return flag;
    	}else{
    		 flag = true;
    	}
    }
    return flag;
	}

}
