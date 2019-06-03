package com.sssri.server.access.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 因需要处理中间过程的返回数据，封装{@link HttpServletResponse}
 * 
 * @author huangjw(maito:huangjw@primeton.com)
 *
 */
public class MHttpServletResponse extends HttpServletResponseWrapper {

	private StringWriter writer;
	
	public MHttpServletResponse(HttpServletResponse response) {
		super(response);
		writer = new StringWriter();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return super.getOutputStream();
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		PrintWriter pw = new PrintWriter(writer, false);
		return pw;
	}
	
	@Override
	public boolean isCommitted() {
		return super.isCommitted();
	}
	
	public String getData(){
		String result = writer.getBuffer().toString();
		System.out.println("[ClientAccessFilter][响应结果=] "+result);
		return result;
	}
	
}
