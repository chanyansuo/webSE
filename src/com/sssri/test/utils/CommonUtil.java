package com.sssri.test.utils;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class CommonUtil {
	
	/*
	 * ����һ�����ظ���16���ַ���
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/*
	 * ��Map����ת��Ϊָ���������
	 */
	public static<T> T toBean(Map map, Class<T> clazz){
		//1.ͨ������clazz����ʵ��
		try {
			T bean=clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			//2.ʹ��BeanUtils.populate��map�����ݷ�װ��bean��
			BeanUtils.populate(bean, map);
			return bean;	
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		} 
	}
	
}
