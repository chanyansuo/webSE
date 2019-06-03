package com.sssri.server.access;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link PMAccessToken} 管理
 * 
 * @author huangjw(mailto:huangjw@primeton.com)
 *
 */
public class PMTokenManager {
	
	/**
	 * key:票据串码
	 */
	private static Map<String, PMAccessToken> manager = new HashMap<String, PMAccessToken>();
	
	/**
	 * 获取{@link PMAccessToken}对象
	 * @param token 票据
	 * @return
	 */
	public static PMAccessToken getAccessToken(String token){
		return manager.get(token);
	}
	
	/**
	 * 生成{@link PMAccessToken}
	 * @param userid
	 * @param pmsecret
	 * @return
	 */
	protected static PMAccessToken createAccessToken(String userid, String pmsecret){
		PMAccessToken at = new PMAccessToken(userid, pmsecret, 7200);
		manager.put(at.getToken(), at);
		return at;
	}
}
