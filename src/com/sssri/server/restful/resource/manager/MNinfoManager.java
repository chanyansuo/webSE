package com.sssri.server.restful.resource.manager;

import java.util.HashMap;
import java.util.Map;

import com.sssri.server.db.model.Examination;
import com.sssri.server.restful.resource.IKSInfo;

public class MNinfoManager {
	private Map<String,IKSInfo> infoMap = null;
	
	private static MNinfoManager instance = null;
	
	protected MNinfoManager() {
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
	
	public static MNinfoManager getManager(){
		if(instance == null){
			instance = new MNinfoManager();
		}
		return instance;
	}
}
