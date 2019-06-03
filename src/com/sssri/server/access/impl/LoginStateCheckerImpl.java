package com.sssri.server.access.impl;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.sssri.server.access.ILoginStateChecker;
import com.sssri.server.access.PMAccessToken;

/**
 * EOS针对移动端登陆接入处理的默认实现
 * 
 * @author huangjw(mailto:huangjw@primeton.com)
 *
 */
public class LoginStateCheckerImpl implements ILoginStateChecker {

	public boolean successLogin(String result) {
		JSONObject json = JSONObject.parseObject(result);
		int retCode = json.getInteger("retCode").intValue();
		return (retCode==1);
	}

	public String getLoginParam(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		json.put("userId", request.getParameter(PMAccessToken.KEY_USERID));
		json.put("password", request.getParameter(PMAccessToken.KEY_SECRET));
		
		return json.toJSONString();
	}

	public String getLoginURI(HttpServletRequest request) {
		return "/default/org.gocom.components.coframe.auth.LoginManager.login.biz.ext";
	}

	public String getAccessToken(String reqestData) {
		JSONObject json = JSONObject.parseObject(reqestData);
		return json.getString("access_token");
	}

}
