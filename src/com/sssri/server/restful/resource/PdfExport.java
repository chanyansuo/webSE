package com.sssri.server.restful.resource;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sssri.server.db.DatabaseUtils;
import com.sssri.server.db.mapper.IUserMapper;
import com.sssri.server.db.mapper.IgetExamMapper;
import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Examination;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.Question;
import com.sssri.server.db.model.User;
import com.sssri.server.restful.UserExm;
import com.sssri.server.restful.resource.manager.KSInfoManager;

public class PdfExport {
	
	
	/**
	 * @param examInfo考生考卷
	 * @param userMark考生成绩
	 * @return
	 */
	public boolean wordExport(Examination examInfo,Mark userMark){
		
		SqlSession session =null;
		Document doc=new Document();
		try {
			
			File file1 =new File("D:/doc/"+examInfo.getStartTime().replace(":", ""));//2017.8.7增加分考卷存储功能
			if (!file1.exists()) {//判断是否存在当前批次文件夹
				file1.mkdirs();//如果不存在则创建文件夹
			}
			
			PdfWriter.getInstance(doc, new FileOutputStream("D:/doc/"+examInfo.getStartTime().replace(":", "")+"/"+examInfo.getEmployee_id()+".pdf"));//新目录
			doc.open();
			BaseFont baseFont=BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font titleFont=new Font(baseFont,20,Font.BOLD);
			SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = new Date(); 
			Paragraph title=new Paragraph(DateFormat.format(date)+" 保密考试",titleFont);
			title.setAlignment(ElementTags.ALIGN_CENTER);
			doc.add(title);
			doc.add(new Paragraph("\n"));
			//正常字体
			Font normalFont=new Font(baseFont,12,Font.NORMAL);
			//答案字体(用红色显示)
			Font answerFont=new Font(baseFont,12,Font.NORMAL,Color.RED);
			//加下划线的用户答案
			Font nlineFont=new Font(baseFont,12,Font.NORMAL|Font.UNDERLINE);
			
			//设置考生基本信息
			session = DatabaseUtils.getSessionFactory().openSession();//数据库连接
			IUserMapper userMapper = session.getMapper(IUserMapper.class);
			User examUser = userMapper.selectUserByID(examInfo.getEmployee_id());
			String strUser="舰船分所"+"   "+examUser.getDepartment()+"    "+examUser.getName();
			Paragraph userName=new Paragraph(strUser, normalFont);
			userName.setAlignment(ElementTags.ALIGN_RIGHT);
			doc.add(userName);
			doc.add(new Paragraph("\n"));
			
			//获取统计的成绩
			Map<String, Object> gradeMap=new LinkedHashMap<String, Object>();
			gradeMap.put("填空题", userMark.getCompletion_score());
			gradeMap.put("判断题", userMark.getJudgment_score());
			gradeMap.put("单选题", userMark.getChoice_score());
			gradeMap.put("多选题", userMark.getMchoice_score());
			gradeMap.put("简答题", 0);
			
			PdfPTable gradeTable=createGradeTable(gradeMap,normalFont, answerFont);
			doc.add(gradeTable);
			doc.add(new Paragraph("\n"));
			
			
			Font chapterFont=new Font(baseFont, 14, Font.NORMAL|Font.BOLD);
			//1.填空题
			java.util.List<Completion> completionList=examInfo.getCompletion();
			int numComp =0;//空格总数
			for (int i = 0; i < completionList.size(); i++) {
				numComp=numComp+completionList.get(i).getBlank_num();
			}
			double sum =examInfo.getCompletion_Score()*numComp;//单项总分2017.7.31算法有问题
			doc.add(new Paragraph("一、填空题（每空"+examInfo.getCompletion_Score()+"分，共计"+sum+"分）",chapterFont));
			
			
			//得到填空题
			List comList=getCompletionList(completionList, normalFont, nlineFont, answerFont );
			doc.add(comList);
			doc.add(new Paragraph("\n"));
			
			//2.判断题
			java.util.List<Judgment> judgmentList=examInfo.getJudgment();
			sum=examInfo.getJudgment_Score()*judgmentList.size();
			doc.add(new Paragraph("二、判断题（每空"+examInfo.getJudgment_Score()+"分，共计"+sum+"分）",chapterFont));
			
			List judgList=getJudgList(judgmentList, normalFont, answerFont);
			doc.add(judgList);
			doc.add(new Paragraph("\n"));
			
			//3.单选题
			java.util.List<Choice> choiceList=examInfo.getChoice();
			sum=examInfo.getChoice_Score()*choiceList.size();
			doc.add(new Paragraph("三、单选题（每题"+examInfo.getChoice_Score()+"分，共计"+sum+"分）",chapterFont));
			
			List dangxuanList=getChoiceList(choiceList, normalFont, answerFont);
			doc.add(dangxuanList);
			doc.add(new Paragraph("\n"));
			
			//4.多选题
			java.util.List<Choice> multiChoiceList=examInfo.getMulchoice();
			sum=examInfo.getMulChoice_Score()*multiChoiceList.size();
			doc.add(new Paragraph("四、多选题（每题"+examInfo.getMulChoice_Score()+"分，共计"+sum+"分）",chapterFont));
			
			List mulList=getChoiceList(multiChoiceList, normalFont, answerFont);
			doc.add(mulList);
			doc.add(new Paragraph("\n"));
			
			//5.简答题
			java.util.List<Question> juquestionList=examInfo.getQuestion();
			sum=examInfo.getQA_Score()*juquestionList.size();
			if(sum>0)//判断简答题数量是否大于02018.12.28
			{
				doc.add(new Paragraph("四、简答题（每题"+examInfo.getQA_Score()+"分，共计"+sum+"分）",chapterFont));
				
				List questionList=getQuestionList(juquestionList, normalFont);
				doc.add(questionList);
				doc.add(new Paragraph("\n"));
				
				Font testFont=new Font(baseFont,12,Font.NORMAL|Font.UNDERLINE);
				doc.add(new Paragraph("              ",testFont));
			}
			doc.close();
			return true; 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 if (session != null)
				 session.close();
		}
		return false;
	}

	private List getQuestionList(java.util.List<Question> juquesList, Font normalFont) {
		List questionList=new List(true,false);
		for(int i=0;i<juquesList.size();i++){
			String title=juquesList.get(i).getContent();
			String userAnswer=juquesList.get(i).getUserAnswer();
			if(userAnswer==null || userAnswer.trim().isEmpty()){
				userAnswer="";
			}
			ListItem item=new ListItem();
			item.add(new Paragraph(title,normalFont));
			item.add(new Paragraph(userAnswer,normalFont));
			questionList.add(item);
		}
		return questionList;
	}

	//选择题：每一题作为一个ListItem
	private List getChoiceList(java.util.List<Choice> juChoiceList, Font normalFont, Font answerFont) {
		List choiceList=new List(true, false);
		for(int i=0;i<juChoiceList.size();i++ ){
			String[] choices=new String[]{ juChoiceList.get(i).getOption_one(),juChoiceList.get(i).getOption_two(),
					juChoiceList.get(i).getOption_three(), juChoiceList.get(i).getOption_four(),
					juChoiceList.get(i).getOption_five(), juChoiceList.get(i).getOption_six() };
			String title=juChoiceList.get(i).getContent();
			String userAnswer=juChoiceList.get(i).getUserAnswer();
			String corrAnswer=juChoiceList.get(i).getAnswer();
			int status=juChoiceList.get(i).getItemStatus();

			Paragraph titleparag=getTitlePg(title, userAnswer, normalFont);
			if(status!=2){   //如果用户答案不正确，添加红色正确答案
				titleparag.add(new Chunk("(  "+corrAnswer+"  )", answerFont));
			}
			ListItem item=new ListItem();
			item.add(titleparag);
			for(int index=0;index<choices.length;index++){
				if(choices[index]!=null && !choices[index].trim().isEmpty()){
					Paragraph choicePar=new Paragraph(choices[index],normalFont);
					item.add(choicePar);
				}
			}
			choiceList.add(item);
		}
		return choiceList;
	}
	
	//得到插入用户答案的标题
	private Paragraph getTitlePg(String title, String userAnswer, Font normalFont) {
		if(userAnswer==null || userAnswer.trim().isEmpty()){
			userAnswer="";
			
		}
		int index=title.lastIndexOf("）");  //和数据库中保存的字符一致
		String titlepart;
		if(index!=-1){
			Paragraph titlePg=new Paragraph();
			titlepart=title.substring(0,index);
			titlePg.add(new Chunk(titlepart,normalFont));
			titlePg.add(new Chunk("  "+userAnswer,normalFont));
			titlepart=title.substring(index, title.length());
			titlePg.add(new Chunk(titlepart,normalFont));
			return titlePg;
		}else{
			Paragraph titlePg=new Paragraph();
			titlePg.add(new Chunk(title,normalFont));
			titlePg.add(new Chunk("(  "+userAnswer+"  )",normalFont));
			return titlePg;
		}
	}

	//获取判断题列表项
	private List getJudgList(java.util.List<Judgment> judgmentList, Font normalFont, Font answerFont) {
		List judgList=new List(true,false);
		String rightAnswer="√";
		String wrongAnswer="×";
		for(int i=0;i<judgmentList.size();i++){
			String title=judgmentList.get(i).getContent();
			String userAnswer=judgmentList.get(i).getUserAnswer();
			if(userAnswer==null ||userAnswer.trim().isEmpty()){
				userAnswer="";
			}
			int status=judgmentList.get(i).getItemStatus();
			ListItem item=new ListItem();
			item.add(new Chunk(title+"(     ",normalFont));
			if(status==2){//答案正确
				if (userAnswer.equals("1")) {
					item.add(new Chunk(rightAnswer,normalFont));  
				}
				if (userAnswer.equals("0")) {
					item.add(new Chunk(wrongAnswer,normalFont));  
				}
				item.add(new Chunk("     )",normalFont));
			}else{
				if (userAnswer.equals("1")) {
					item.add(new Chunk(rightAnswer,normalFont));
				}
				else if (userAnswer.equals("0")) {
					item.add(new Chunk(wrongAnswer,normalFont));
				}
				else{
					item.add(new Chunk(userAnswer,normalFont));
				}
				item.add(new Chunk("     )",normalFont));
				String curAnswer=judgmentList.get(i).getAnswer();
				if (curAnswer.equals("1")) {
					item.add(new Chunk("(   "+rightAnswer+"   )",answerFont));
				}
				else{
					item.add(new Chunk("(   "+wrongAnswer+"   )",answerFont));
				}
			}
			
			judgList.add(item);
		}
		return judgList;
	}

	//将填空题List转化为一个个ListItem
	private List getCompletionList(java.util.List<Completion> completionList, Font normalFont, Font nlineFont, Font answerFont) {
		List comList=new List(true,false);
		for(int i=0;i<completionList.size();i++){
			String timu=completionList.get(i).getContent();
			int kongNum=completionList.get(i).getBlank_num();
			String userAnswer=completionList.get(i).getUserAnswer();
			//将用户答案转化数组对象
			java.util.List<String> userAnswerList=changeUserAnswer(kongNum,userAnswer);
			//试题的正确答案
			String curAnswer=getAnswerStr(completionList.get(i), kongNum);
			//将试题中的横线换成用户答案
			ListItem timuItem=changeBlankToUserAnswer(timu, curAnswer,
					userAnswerList, normalFont, nlineFont, answerFont ,completionList.get(i).getItemStatus());
			comList.add(timuItem);
		}
		return comList;
	}

	//拼接填空题用户答案
	private String getAnswerStr(Completion completion, int kongNum) {
		StringBuilder sb=new StringBuilder();
		sb.append("( ");
		switch(kongNum){
			case 1:
				sb.append(completion.getAnswer_one());
				break;
			case 2:
				sb.append(completion.getAnswer_one()).append(";")
					.append(completion.getAnswer_two());
				break;
			case 3:
				sb.append(completion.getAnswer_one()).append(";")
					.append(completion.getAnswer_two()).append(";")
					.append(completion.getAnswer_three());
				break;
			case 4:
				sb.append(completion.getAnswer_one()).append(";")
					.append(completion.getAnswer_two()).append(";")
					.append(completion.getAnswer_three()).append(";")
					.append(completion.getAnswer_four());
				break;
			case 5:
				sb.append(completion.getAnswer_one()).append(";")
					.append(completion.getAnswer_two()).append(";")
					.append(completion.getAnswer_three()).append(";")
					.append(completion.getAnswer_four()).append(";")
					.append(completion.getAnswer_five());
				break;
		}
		sb.append(" )");
		return sb.toString();
	}

	//将题目中的空格换成用户答案并添加正确答案
	private ListItem changeBlankToUserAnswer(String timu,String corrAnswer,
			java.util.List<String> userAnswerList,
			Font normalFont, Font nlineFont, Font answerFont,int itemStatus) {
		String[] str=timu.split("_+");
		ListItem item=new ListItem();
		for(int i=0;i<userAnswerList.size();i++){
			item.add(new Chunk(str[i],normalFont));
			if(userAnswerList.get(i)==null ||userAnswerList.get(i).trim().isEmpty())
			{
				item.add(new Chunk("          ", nlineFont));
			}else{
				item.add(new Chunk("   "+userAnswerList.get(i)+"   ", nlineFont));
			}
						
		}
		item.add(new Chunk(str[str.length-1],normalFont));
		if (itemStatus!=2) {
			item.add(new Chunk(corrAnswer,answerFont));
		}
		return item;
	}

	//将用户答案字符串分割和重组
	private java.util.List<String> changeUserAnswer(int kongNum, String userAnswer) {
		java.util.List<String> userAnswerList=new ArrayList<String>();
		if(userAnswer==null || userAnswer.trim().isEmpty()||userAnswer== " $%"||userAnswer == " $% $%"||userAnswer == " $% $% $%"||userAnswer == " $% $% $% $%"||userAnswer == " $% $% $% $% $%"){
			for(int i=0;i<kongNum;i++){
				userAnswerList.add("");  //前后各5个空格
			}	
		}else{
			String[] userStr=userAnswer.split("\\$%");
			/*
			String[] userStr=new String[kongNum];
			while(kongNum>0){
				int index=userAnswer.lastIndexOf("$%");
				String dgAnswer="";
				if(index!=-1){
					dgAnswer=userAnswer.substring(index+1);
					userAnswer=userAnswer.substring(0,index);
				}else{
					dgAnswer=userAnswer;
				}
				kongNum--;
				userStr[kongNum]=dgAnswer;  //前后各3个空格
			}
			*/
			for(int i=0; i<userStr.length; i++){
				userAnswerList.add(userStr[i]);
			}
		}
		return userAnswerList;
	}

	private PdfPTable createGradeTable(Map<String,Object> gradeMap,Font font, Font answerFont) {
		PdfPTable gradeTable=null;
		try {
			gradeTable = new PdfPTable(8);
			gradeTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			//设置题目的类型
			String[] cellTitle=new String[]{"题型","填空题","判断题","单选题","多选题","合计","简答题","总分"};
			PdfPCell cell=null;
			for(int i=0;i<cellTitle.length; i++){
				cell=new PdfPCell(new Paragraph(cellTitle[i],font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				gradeTable.addCell(cell);
			}
			
			float totalGrade=0;
			//统计电脑阅卷总分
			for(String str:gradeMap.keySet()){
				float grade=Float.parseFloat(gradeMap.get(str).toString());
				totalGrade+=grade;
			}
			//分值单元格
			cell=new PdfPCell(new Paragraph("分值",font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			gradeTable.addCell(cell);
			
			String[] cellContent=new String[]{gradeMap.get("填空题").toString(),
					gradeMap.get("判断题").toString(),gradeMap.get("单选题").toString(),
					gradeMap.get("多选题").toString(),String.valueOf(totalGrade),"",""};
			
			for(int i=0;i<cellContent.length; i++){
				cell=new PdfPCell(new Paragraph(cellContent[i], answerFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				gradeTable.addCell(cell);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gradeTable;	
	}
	
	
	
}
