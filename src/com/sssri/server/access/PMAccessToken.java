package com.sssri.server.access;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 接口调用票据模型. 获取token接口必须进行登录认证校验需要注意：
 * <ol><li>原登录接口必须支持JSON格式报文
 * <li>GET请求URL格式为：<code>http://[HOST]:[PORT]/[Application]/pmtoken?userid=USERID&pmsecret=PASSWORD
 * <li>正常返回数据：{"access_token":"ACCESS_TOKEN","expires_in":7200}
 *</ol>
 * <br>
 * @author huangjw(mailto:huangjw@primeton.com)
 * 
 */
public class PMAccessToken {

	/**
	 * 参数KEY: userid
	 */
	public static final String KEY_USERID = "userid";
	
	/**
	 * 参数KEY: pmsecret
	 */
	public static final String KEY_SECRET = "secret";
	
	/**
	 * 获取token的servlet_path
	 */
	public static final String TOKEN_REQUEST = "/access_token";
	
	private String accessToken;
	
	private String userid;
	
	/**
	 * 用户登陆密码, 需要加密
	 */
	private String userSecret;
	
	/**
	 * 创建时间
	 */
	private long createTime;
	
	/**
	 * 有效时长, 单位秒
	 */
	private long lastTime=7200;
	
	protected PMAccessToken() {
	}

	protected PMAccessToken(String userid, String secret, long expireTime) {
		this.createTime = System.currentTimeMillis();
		this.userid = userid;
		this.lastTime = expireTime;
		this.setUserSecret(secret);
		
		this.generateToken();
	}
	
	/**
	 * @return the {@link #token}
	 */
	public String getToken() {
		return accessToken;
	}

	protected void generateToken() {
		//ATTENTION 生成token的算法
		String temp = "pmsecret="+this.userSecret+"&userid="+userid+"&createtime="+this.createTime;
		try {
			this.accessToken = DigestUtils.sha1Hex(temp.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the {@link #userid}
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the {@link #userid} to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * @return the {@link #userSecret}
	 */
	public String getUserSecret() {
		return userSecret;
	}

	/**
	 * 将用户登陆密码加密存储
	 * 
	 * @param userid the {@link #userSecret} to set
	 */
	protected void setUserSecret(String userSecret) {
		//ATTENTION 需要对用户密码进行加密
		this.userSecret = userSecret;
	}

	/**
	 * @return the {@link #createTime}
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the {@link #createTime} to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the {@link #lastTime}
	 */
	public long getLastTime() {
		return lastTime;
	}

	/**
	 * @param lastTime the {@link #lastTime} to set
	 */
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	/**
	 * 获取当前access_token是失效
	 * @return <code>true</code> 失效，<code>false</code> 有效
	 */
	public boolean isExpired(){
		if(this.createTime == 0)
			return true;
		
		// 已经花费的时间（单位：毫秒）
		long differ = System.currentTimeMillis() - this.createTime;
		long seconds = differ/1000;
		
		// 预留10分钟的意外时间
		seconds = seconds + 10*60;
		
		return (seconds >= this.lastTime);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PMAccessToken){
			return this.accessToken.equals(((PMAccessToken)obj).getToken());
		}
		else return false;
	}
	
}
