package com.sssri.server.access.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.sssri.server.access.ILoginStateChecker;

/**
 * 用来封装{@link HttpServletRequest}请求. 
 * 移动接入过程中可能需要对请求参数等信息做修改
 * 
 * @author huangjw(mailto:huangjw@primeton.com)
 *
 */
public class MHttpServletRequest extends HttpServletRequestWrapper {

	private String requestData="";
	
	private ILoginStateChecker handler;
	
	public MHttpServletRequest(HttpServletRequest request, ILoginStateChecker loginHandler) {
		super(request);
		this.handler = loginHandler;
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return super.getInputStream();
	}
	
	@Override
	public String getContentType() {
		String contentType = super.getContentType();
		String uri = this.getRequestURI();
//		System.out.println("[ClientAccessFilter][URI=] "+uri+", [Content-Type=]"+contentType);
		if(uri.endsWith(".biz.ext")){
			//逻辑流需要特殊处理
			return "text/json;charset=UTF-8";
		}
		else return contentType;
	}
	
	@Override
	public String getParameter(String name) {
		if(name.equals("access_token")){
			String data="",temp="";
			try {
				BufferedReader sbr = super.getReader();
				while((temp=sbr.readLine())!=null){
					data+=temp;
				}
//				sbr.close();
				this.requestData = data;
				return handler.getAccessToken(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return super.getParameter(name);
	}
	
	public BufferedReader getReader() throws IOException {
		String uri = super.getRequestURI();
		
		if(uri.equals(handler.getLoginURI((HttpServletRequest) this.getRequest()))){
			this.requestData = handler.getLoginParam((HttpServletRequest) this.getRequest());
		} 
		System.out.println("[ClientAccessFilter][URI=] "+uri+", [request-data=]"+this.requestData);
		BufferedReader br = new BufferedReader(new StringReader(this.requestData));
		return br;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	
}
