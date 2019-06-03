package com.sssri.test.Junittest;


import java.awt.Color;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.PathParam;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;
import com.sssri.server.db.DatabaseUtils;
import com.sssri.server.db.mapper.IgetAdminInfo;
import com.sssri.server.db.mapper.IgetExamMapper;

/////11111
public class sqlTest {
	
	@Test
	public void selectMark(){
//		SqlSession session = null;
//		session = DatabaseUtils.getSessionFactory().openSession();
//		IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
//		Date sqtime= new Date();
//		String Stime= "2014-4-13";
//		String Otime= "2017-6-20";
//		System.out.println(sqtime.toGMTString());
//		java.util.List<Integer> test = userDAO.selectExamId(Stime, Otime);
//		java.util.List<Mark> Marktest= userDAO.selectExamScore(test.get(0));
//		java.util.List<Qanswer> qqq =userDAO.selectAllUserAnswer(1);
//		Gson gson= new Gson();
//		String x= gson.toJson(Marktest);
//		JSONArray array = new JSONArray();
//		for (int i = 0; i < test.size(); i++) {
//			 JSONObject map=new JSONObject();
//			 map.put("id", (i+1));
//			 map.put("ExamId", test.get(i));
//		     array.add(map);
//		     System.out.println(map.toJSONString());
//		}
//		    
//		 //生成的JSON字符串为：[["username","age","sex"],{"price":69,"bookname":"CSS3实战"}]
//
//		System.out.println(array.toJSONString());
		String x;
		x=gettpyePractice(1, 1);
		System.out.println(x);
	}
	public String gettpyePractice(int type, int mode){
		List<?> practiceList = null;
		String result = "";
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetExamMapper praticeDAO = session.getMapper(IgetExamMapper.class);
			switch (type) {
			case 1:
				practiceList=praticeDAO.selectallComplletion(1);
//				for(int i=2;i<6;i++){
//					practiceList.addAll( praticeDAO.selectallComplletion(i));
//				}
				break;
			case 2:
				practiceList=praticeDAO.selectallJudgment();
				break;
			case 3:
				practiceList=praticeDAO.selectallchoice(3);
				break;
			case 4:
				practiceList=praticeDAO.selectallchoice(4);
				break;
			case 5:
				practiceList=praticeDAO.selectallQuestion();
				break;
			}
			if (mode==0) {
				result= ChangeToJSON(practiceList, type);
				return result;
			}
			else if(mode==1){
				Random ran = new Random();//随机数
				Set<String> praticeSet= new LinkedHashSet<String>();
				List<Object> practiceResult = new ArrayList<>();
				if(practiceList.size()!=0){
					praticeSet.clear();
					while (true) {
						int a = ran.nextInt(practiceList.size());//在[0,ChoiceNumber)中随机选出ExamChoiceNum个随机数
						praticeSet.add(String.valueOf(a)); 
						if (praticeSet.size() == practiceList.size() ) {
							break;
						}
					}
					for (String s : praticeSet) {
						practiceResult.add(practiceList.get(Integer.parseInt(s)));
					}
				}
				result= ChangeToJSON(practiceResult, type);
				return result;
			}
		} finally {
			if (session != null)
				session.close();
		}
		return null;
		
	}
	
	private String ChangeToJSON(List<?> prList,int type){
		Gson gson = new Gson();
		String result=null;
		result=gson.toJson(prList);
//		switch (type) {
//		case 1:
//			result = gson.toJson(prList, Completion.class);
//			break;
//		case 2:
//			result = gson.toJson(prList, Judgment.class);
//			break;
//		case 3:
//			result = gson.toJson(prList, Choice.class);
//			break;
//		case 4:
//			result = gson.toJson(prList, Choice.class);
//			break;
//		case 5:
//			result = gson.toJson(prList, Question.class);
//			break;
//		}
		return result;
		
	}
	
	@Test
	public void demo(){
		int n= 0;
		Object[] values = new Object[20] ;
		Random randow = new Random();
		HashMap<Object, Object> hashMap= new HashMap<Object,Object>();
		for(int i= 0;i<values.length;i++){
			int number = randow.nextInt(100)+1;
			hashMap.put(number, i);
		}
		values= hashMap.keySet().toArray();
		System.out.println(values);
	}
	
	@Test
	public void text(){
		String title="一份文件为机密级，保密期限是10年，应当标注为（    ）。";
		String timu ="因私出国（境）的重要涉密人员须经______或省级______管理部门审批。";
//		String answer=choice.getAnswer();
//		String userAnswer=choice.getUserAnswer();
//		userAnswer.toLowerCase().equals(answer.toLowerCase())
		String[] str=timu.split("_+");
		int index=title.lastIndexOf("）");  //和数据库中保存的字符一致
		String titlepart;
		titlepart=title.substring(0,index);
		System.out.println(titlepart);
	    titlepart=title.substring(index, title.length());
	    System.out.println(titlepart);
	}
	
	@Test
	public void data1(){
//		Date nowdate=new Date();
		DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String enddate="2017-07-27 12:12:55";
		LocalDateTime nowdate= LocalDateTime.now();
		nowdate=nowdate.plusMinutes(50);
		LocalDateTime endtime = LocalDateTime.parse(enddate,format);
		long p = ChronoUnit.SECONDS.between(nowdate, endtime);
		System.out.println(p/60);
	}
}
