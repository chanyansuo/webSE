package com.sssri.server.access;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 移动端接入调用客户业务登陆接口后需要解析返回数据来判断
 * 用户名、密码是否正确<BR>
 * 用户需要实现改接口并配置到web.xml的<code>ClientAccessFilter</code>中去
 * 
 * @author huangjw(mailto:huangjw@primeton.com)
 *
 */
public interface ILoginStateChecker {
	
	/**
	 * <code>web.xml</code>中用来描述解析登陆返回值的的字段
	 */
	public static final String PARAM_CLASS = "stateChecker";

	/**
	 * 用户登陆验证的URI<br>
	 * 例如: <code>http://localhost:8080/default/org.gocom.auth.login.biz.ext</code>
	 * 的URI填写为 <code>/default/org.gocom.auth.login.biz.ext</code>
	 * @param request
	 * @return
	 */
	public String getLoginURI(HttpServletRequest request);
	
	/**
	 * 成功调用用户业务系统登陆校验后执行
	 * 
	 * @param result 登陆校验后返回的数据
	 * @return <code>true</code>-登陆成功,<code>false</code>-失败
	 */
	public boolean successLogin(String result);
	
	/**
	 * 获取登录请求的报文数据.<BR/>
	 * 为了方便用户根据自己的登录接口名、参数名进行数据处理
	 * 
	 * @return
	 */
	public String getLoginParam(HttpServletRequest requeset);
	
	/**
	 * 获取请求报文中的<code>access_token</code>值.<br/>
	 * 不同请求可能报文格式不同，需要用户自行解析获取<code>access_token</code>字段的值
	 * @param reqestData
	 * @return
	 */
	public String getAccessToken(String reqestData);
}
