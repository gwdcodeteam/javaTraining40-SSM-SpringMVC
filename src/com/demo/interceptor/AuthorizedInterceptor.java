package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.User;

public class AuthorizedInterceptor implements HandlerInterceptor{

	//1.׼��һ�����飬��Ų������ӵ�����·����׺��
	public static final String[] ignore_uri= {"log","login"};
	
	
	/**
	 * �������֮����ִ�д˷���
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("���������������");
	}
	/**
	 * Controller����������֮��ִ�У������п��Զ�ModelAndView���в���
	 * Ҳֻ���ڵ�Interceptor��prehandle�����ķ���ֵΪtrueʱִ��
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
			System.out.println("��������������֮��");
	}
	/**
	 * ����������ʱʹ�ã��÷�������Controller����֮ǰ���е��ã�����ֵΪtrue�������Ż��������ִ�У�
	 * �÷����ķ���ֵΪFALSEʱ�������������
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, 
				HttpServletResponse resp, Object handler) throws Exception {
		System.out.println("����");
		boolean flag = false;
		String servletPath = req.getServletPath();//��ȡ�������ķ���·��
	for (String url : ignore_uri) {
			if(servletPath.contains(url)) {
				flag = true;
				break;
			}
		}
    if (!flag){  /** �������� */
    	/** 1.��ȡsession�е��û�  */
    	String user =  (String) req.getSession().getAttribute("loginUser");
    	/** 2.�ж��û��Ƿ��Ѿ���¼ */
    	if(user == null){
    		 /** ����û�û�е�¼����ת����¼ҳ�� */
    		req.setAttribute("message", "���ȵ�¼�ٷ�����վ!");
    		req.getRequestDispatcher("log").forward(req, resp);
    		return flag;
    	}else{
    		 flag = true;
    	}
    }
    return flag;
	}

}
