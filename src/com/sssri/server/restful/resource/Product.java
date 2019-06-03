package com.sssri.server.restful.resource;

/**
 * REST 接口操作的resource对象
 * 
 * @author huangjw(huangjw@primeton.com)
 *
 */
public class Product {

	/**
	 * 产品名称
	 */
	private String name;
	
	/**
	 * 保质期
	 */
	private int storeTime;
	
	/**
	 * 条形码
	 */
	private String seqNo;

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #storeTime}
	 */
	public int getStoreTime() {
		return storeTime;
	}

	/**
	 * @param storeTime the {@link #storeTime} to set
	 */
	public void setStoreTime(int storeTime) {
		this.storeTime = storeTime;
	}

	/**
	 * @return the {@link #seqNo}
	 */
	public String getSeqNo() {
		return seqNo;
	}

	/**
	 * @param seqNo the {@link #seqNo} to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	
	@Override
	public String toString() {
		return "[产品信息 ]"+this.getName()+":"+this.getSeqNo();
	}
}
