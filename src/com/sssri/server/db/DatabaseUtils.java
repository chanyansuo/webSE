package com.sssri.server.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 数据库连接工具
 * 
 * @author xumj
 *
 */
public class DatabaseUtils {

	public static final String MYBATIS_CONFIGURATION_FILE = "D:/github/sssri/code/src/test/WebContent/WEB-INF/mysql_datasource.xml";
	
	private static SqlSessionFactory sessionFactory = null;
	
	public static SqlSessionFactory getSessionFactory() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mysql_datasource.xml");
			sessionFactory= new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;	
	}
	
//	public static SqlSessionFactory getSessionFactory() {
//		return getSessionFactory(MYBATIS_CONFIGURATION_FILE);
//	}
//	
	public static SqlSessionFactory getSessionFactory(String configurationFile){
		if(sessionFactory == null){
			if(StringUtils.isBlank(configurationFile)){
				System.out.println("MyBatis配置文件缺失");
				return null;
			}
			
			File dataConfiguratin = new File(configurationFile);
			if (dataConfiguratin.exists() == false) {
				System.out.println("MyBatis配置文件缺失");
				return null;
			}
			
			try {
				InputStream inputStream = new FileInputStream(dataConfiguratin);
				sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
	}
}
