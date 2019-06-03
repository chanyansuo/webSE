package com.sssri.test.utils;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class CommonUtil {
	
	/*
	 * 返回一个不重复的16个字符串
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/*
	 * 把Map类型转化为指定的类对象
	 */
	public static<T> T toBean(Map map, Class<T> clazz){
		//1.通过参数clazz创建实例
		try {
			T bean=clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			//2.使用BeanUtils.populate把map的数据封装到bean中
			BeanUtils.populate(bean, map);
			return bean;	
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		} 
	}
	
}
