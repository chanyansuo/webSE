package com.sssri.test.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseServlet������Ϊ����Servlet�ĸ���
 */
@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html,charset=UTF-8"); //处理编码
		request.setCharacterEncoding("UTF-8"); //
		
		/*
		 * ��ȡmethod����
		 */
		String methodName=request.getParameter("method");
		Method method=null;
		
		/*
		 * ͨ�����ƻ�ȡmethod����
		 */
		try {
			method=this.getClass().getMethod(methodName,
					HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("��Ҫ���õķ���û�У�");
		} 
		
		/*
		 * ͨ��method������������
		 */
		try {
			String result=(String)method.invoke(this,request, response);
			//System.out.print(result);
			if(result!=null && !result.trim().isEmpty()){
				int index=result.indexOf(":");//��ȡ��һ��ð�ŵ�λ��
				if(index==-1){ //û��ð�ţ�ʹ��ת��
					request.getRequestDispatcher(result).forward(request, response);
				}else{ //����ð�ţ��ض���
					String start=result.substring(0, index);
					String path=result.substring(index+1);
					if(start.equals("f")){ //ת��
						request.getRequestDispatcher(path).forward(request, response);
					}else if(start.equals("r")){ //�ض���
						response.sendRedirect(request.getContextPath()+path);	
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
