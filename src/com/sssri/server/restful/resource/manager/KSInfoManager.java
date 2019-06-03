package com.sssri.server.restful.resource.manager;

import java.util.HashMap;
import java.util.Map;

import com.sssri.server.db.model.Examination;
import com.sssri.server.restful.resource.IKSInfo;

/**
 * @author sssri
 *
 */
public class KSInfoManager {

	private Map<String,IKSInfo> infoMap = null;
	
	private static KSInfoManager instance = null;
	
	protected KSInfoManager() {
		this.infoMap = new HashMap<String, IKSInfo>();
	}
	
	public IKSInfo getKSInfo(String userid){
		return infoMap.get(userid);
	}
	
	public IKSInfo removeKSInfo(String userid){
		return infoMap.remove(userid);
	}
	
	public void putKSInfo(Examination userData){
		infoMap.put(userData.getEmployee_id(), userData);
	}
	
	public void removeAll(){
		infoMap.clear();
	}
	
	public static KSInfoManager getManager(){
		if(instance == null){
			instance = new KSInfoManager();
		}
		return instance;
	}
}
