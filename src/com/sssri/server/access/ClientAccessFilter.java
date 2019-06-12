package com.sssri.server.access;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * 移动端接入filter, 用来控制token的分发校验
 * 
 * @author huangjw(mailto:huangjw@primeton.com)
 *
 */
public class ClientAccessFilter implements Filter {

	private FilterConfig config;
	
	private ILoginStateChecker clientLoginHandler = null;
	
	/**
	 * 需要拦截的URL的通配规则
	 */
	private String[] INCLUDE_PATTERN = null;
	
	/**
	 * 不拦截的URL的通配规则
	 */
	private String[] EXCLUDE_PATTERN = null;
	
	public ClientAccessFilter() {
	}

	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //登陆访问限制
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		
		String encoding = config.getInitParameter("encoding");//防止编码乱码
		if(StringUtils.isNotBlank(encoding)){
			hRequest.setCharacterEncoding(encoding);
			hResponse.setCharacterEncoding(encoding);
		}
		
		String appName = hRequest.getContextPath();
		String servletPath = hRequest.getRequestURI();
		servletPath = servletPath.substring(appName.length());
		
		//chain.doFilter(hRequest, hResponse);
		
		
		boolean isUserLogin = isLogin(hRequest);//判断是否登陆
		if(servletPath.endsWith(".html") || servletPath.endsWith(".htm") || servletPath.endsWith(".jsp")){
			boolean isLoginPage = servletPath.endsWith("/Login.jsp") || servletPath.endsWith("/login.html");
			if(isLoginPage){//登陆页面
				chain.doFilter(hRequest, hResponse);
			} else if (isUserLogin) {//已经登陆
				chain.doFilter(hRequest, hResponse);
			} else {//其他
				hResponse.sendRedirect("/webSE/USER/Login.jsp");
			}//后缀为html，jsp
		} else if(servletPath.startsWith("/resource/")){//资源路径放行
			chain.doFilter(hRequest, hResponse);
		} else if(servletPath.startsWith("/rest")){//后台处理界面放行
			boolean isLoginRequest = servletPath.equalsIgnoreCase("/rest/user/login")||servletPath.equalsIgnoreCase("/rest/user/loginAdmin");
			if(isLoginRequest){
				chain.doFilter(hRequest, hResponse);
			} else if (isUserLogin) {
				chain.doFilter(hRequest, hResponse);
			} else {//未登录跳转
				hResponse.sendRedirect("/webSE/USER/Login.jsp");
			}
		} else {
			chain.doFilter(hRequest, hResponse);
		}
		
	}  
	
	/**
	 * 判断用户是否登陆
	 * @param request
	 * @return
	 */
	protected boolean isLogin(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session==null){
			return false;
		}
		long timeDiff = System.currentTimeMillis() - session.getCreationTime();
		if(timeDiff/1000 > (session.getMaxInactiveInterval() + 60)){//超时
			return false;
		}
		Object userid = session.getAttribute("login_user");//如果用string，没有对象是无法创建此处用object
		if(userid==null || userid.toString().trim().length()<=0){
			return false;
		}
		return true;
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
		String handleClass = config.getInitParameter(ILoginStateChecker.PARAM_CLASS);
		if(StringUtils.isNotBlank(handleClass)){
			try {
				Class<?> clazz = Class.forName(handleClass);
				clientLoginHandler = (ILoginStateChecker) clazz.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		String includeStr = config.getInitParameter("include_url");
		String excludeStr = config.getInitParameter("exclude_url");
		if(StringUtils.isNotBlank(includeStr)){
			INCLUDE_PATTERN = includeStr.split(",");
		}
		if(StringUtils.isNotBlank(excludeStr)){
			EXCLUDE_PATTERN = excludeStr.split(",");
		}
		
	}

	/**
	 * @see #iNCLUDE_PATTERN
	 * @return iNCLUDE_PATTERN
	 */
	public String[] getINCLUDE_PATTERN() {
		return INCLUDE_PATTERN;
	}

	/**
	 * @param iNCLUDE_PATTERN 设置 <code>{@link #iNCLUDE_PATTERN}</code>字段的值
	 * @see #iNCLUDE_PATTERN
	 */
	public void setINCLUDE_PATTERN(String[] iNCLUDE_PATTERN) {
		INCLUDE_PATTERN = iNCLUDE_PATTERN;
	}

	/**
	 * @see #eXCLUDE_PATTERN
	 * @return eXCLUDE_PATTERN
	 */
	public String[] getEXCLUDE_PATTERN() {
		return EXCLUDE_PATTERN;
	}

	/**
	 * @param eXCLUDE_PATTERN 设置 <code>{@link #eXCLUDE_PATTERN}</code>字段的值
	 * @see #eXCLUDE_PATTERN
	 */
	public void setEXCLUDE_PATTERN(String[] eXCLUDE_PATTERN) {
		EXCLUDE_PATTERN = eXCLUDE_PATTERN;
	}

	public ILoginStateChecker getClientLoginHandler() {
		return clientLoginHandler;
	}

}
