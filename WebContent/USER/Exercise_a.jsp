﻿	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.*,java.sql.*,java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>练习模式</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/Button.css" type="text/css" media="all" />
<!--/css-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Petcare Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="../resource/css/hover.css" rel="stylesheet" media="all">
<script src="../resource/js/jquery.min.js"></script>
<script src="../resource/js/modernizr.custom.js"></script>
<script src="../resource/js/responsiveslides.min.js"></script>
<script src="../resource/js/jquery.cxdialog.min.js"></script>
<script>
    $(function () {
	      $("#slider2").responsiveSlides({
	        auto: true,
	        pager: true,
	        speed: 300,
	        namespace: "callbacks",
	      });
	});

	    
	    
	    
  </script>
</head>
<body onkeydown="key();">
<!--header-->
<div class="header">
	<div class="header-info">
		<div class="container">
			<jsp:include page="navigation.jsp"></jsp:include>
		</div>
	</div>
</div>
<!--/header-->
<!--login strip start here-->
<div class="header">
	<div class="header-info">
		<div class="container">
				<div class="menu_test">
					<span class="menu-info"> </span>
						<ul class="cl-effect-test">
<!--							<li><a class="buttom" id="Bcmp" onclick="skip(1,0)">填空题</a></li> -->
							<li><a class="buttom" id="Btf" onclick="skip(2,0)">判断题</a></li>
							<li><a class="buttom" id="Bchoice" onclick="skip(3,0)">单选题</a></li>
							<li><a class="buttom" id="Bmulchoice" onclick="skip(4,0)">多选题</a></li>
<!-- 							<li><a class="buttom" id="Bq" onclick="skip(5,0)">简答题</a></li> -->
						</ul>
				</div>
			<div class="clearfix"> </div>
				<div class="menu_test1">
					<span class="menu-info"> </span>
						<ul class="cl-effect-test" style="margin-top:10%">
<!-- 							<li><a href="Favorite.jsp" class="buttom">收藏夹</a></li> -->
<!-- 							<li><a href="Worry.jsp" class="buttom">错题集</a></li> -->
<!--							<li><a href=# class="buttom" onclick="no()">收藏夹</a></li> -->
<!--							<li><a href=# class="buttom" onclick="no()">错题集</a></li> -->
								<li></li>
								<li></li>
						</ul>
				</div>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="dd2">
	<div>
		<div class="container">
			<div class="img-bg">
			<form>
			<div id="Question" class="Question">


			</div>
			<div class="navigation">
				<div class="nav">
			 			<p id="RD" href="#" class="buttom" onclick="Random()">顺序模式</p>
			 			<!-- 开启随机模式时class=activebuttom 关闭时class=buttom -->
			 		</div>
			 		<div class="nav">
			 			<p class="buttom" onclick="restart()">重新开始</p>
			 		</div>
				 	<div class="nav">
<!-- 				 		<input href="#" type="submit" class="buttom" onclick="Next()" value="下一题"> -->
						<p id="next" class="buttom" onclick="Next()">下一题</p>
				 	</div>
				 	<div class="nav">
				 		<p  class="buttom" onclick="Up()">上一题</p>
					</div>
<!-- 					<div class="nav"> -->
<!-- 				 		<p href="#" class="buttom">加入收藏夹</p> -->
<!-- 					</div> -->
			</div>
			</form>
			<div class="img-bg1">
				<div class="navigation1" id="navigation1">

				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<!--login strip end here-->
</body>
</html>

<script language="javascript" type="text/javascript">
	//全局变量
	var DATA,              //全部数据
		Current,		//正确题号
		WorkedNumber,		//做题号	
		paperNum,          //存题号
		Url,                //连接地址
		isRandom = 0,          //是否开启随机模式，0 关闭，1开启
		Sum,               //一共有多少题1
		CSum,              //单选题题数1
		MulCSum,           //多选题题数1
		TFSum,             //判断题题数1
		CmpSum,            //填空题题数1
		QutSum,            //简答题题数1
		PresentSum,         //目前题型题数
		currentNum = 0,     //当前该类型下题号
		ShowNum,            //显示题号
		Question,           //题目文本
		CorrectAnswer,      //正确答案
		type = 2,               //目前题目类型 1填空 2判断 3单选 4多选 5简答
		Options = new Array(),     //选项集合
		RadioNum,              //选项个数
		NowCmpN,      //目前填空题空格数
		answer,             //目前题目用户输入的答案
		Nubarray = new Array(),           //题目样式集合
		y,                  //String公用存储空间
		z,                  //String存储空间
		isReshow,        //是否回显当前错题（1回显，0不回显）
		j;              //计数器1（循环用）

	//连接数据库
	function GetData(value){
		Url = value;
		$.ajax({
   	 		cache: true,
//    	 	type: "GET",
			type:"POST",
    		dataType: "json",
    		url: "../rest/Practice/GetSafetyPractice",   //后台连接代码
			data:{"type":value,"isRandom":isRandom},
    		async: false,
    		error: function(request) {
    	    	alert("生成考卷失败");
    		},
    		success: function(data) {
    			DATA = data;
	    	}
		});
	}

			//赋值
	function Valuation(){
		Current = 0;
		WorkedNumber = 0;
		currentNum = 0;
			switch (type){
 			
 			case 2:
 				PresentSum = DATA.judgment.length;
 				TFSum =  DATA.judgment.length;
 				break;
 			case 3:
				PresentSum = DATA.choice.length;
				CSum = DATA.choice.length;
 				break;
 			case 4:
 				PresentSum = DATA.mulchoice.length;
 				MulCSum =  DATA.mulchoice.length;
 				break;
 			
			}
		RadioNum = 0;
	}

	//空格替换
	function Replace(value){
		var a = value.replace(/\s/g,"&nbsp;")    //将文本中的空格替换为可被html读取的&nbsp
		return a;
	}

	//题目显示
	function showQuestionType(value){
		Buttonclass(value);     //题目类型设置
		switch (value) {
		
		case 2:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.judgment[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"1\"/><label>√</label></div>";         //radio
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"0\"/><label>×</label></div>";
			$("#Question").html(y);
			showCNumber();
			break;
		case 3:
			RadioNum = 0;   //初始化选项个数
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.choice[currentNum].content;     //题目信息获取
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			Options[0] = DATA.choice[currentNum].Option_one;
			Options[1] = DATA.choice[currentNum].Option_two;
			Options[2] = DATA.choice[currentNum].Option_three;
			Options[3] = DATA.choice[currentNum].Option_four;
			Options[4] = DATA.choice[currentNum].Option_five;
			Options[5] = DATA.choice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"radio\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //String.fromCharCode(j+65)转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
			break;
		case 4:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.mulchoice[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			Options[0] =DATA.mulchoice[currentNum].Option_one;
			Options[1] =DATA.mulchoice[currentNum].Option_two;
			Options[2] =DATA.mulchoice[currentNum].Option_three;
			Options[3] =DATA.mulchoice[currentNum].Option_four;
			Options[4] =DATA.mulchoice[currentNum].Option_five;
			Options[5] =DATA.mulchoice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"checkbox\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
			break;
		
		}
		report();
	}
	
	//回答错误题目显示
	function showQuestionTypeW(value){
		Buttonclass(value);     //题目类型设置
		switch (value) {
		
		case 2:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.judgment[currentNum].content;
			y = "<div class=\"title\"><p id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			if(DATA.judgment[currentNum].answer == "1"){
				var A = "√";
			}
			if(DATA.judgment[currentNum].answer == "0"){
				var A = "×";
			}
			y += "<div class=\"CorrectA\"><p id=\"Content\" style=\"padding-left: 30px;\">正确答案：" + A + "</p></div>";
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"1\"/><label>√</label></div>";         //radio
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"0\"/><label>×</label></div>";
			$("#Question").html(y);
			showCNumber();
			break;
		case 3:
			RadioNum = 0;   //初始化选项个数
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.choice[currentNum].content;     //题目信息获取
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"CorrectA\"><p  id=\"Content\" style=\"padding-left: 30px;\">正确答案：" + DATA.choice[currentNum].answer + "</p></div>";
			Options[0] = DATA.choice[currentNum].Option_one;
			Options[1] = DATA.choice[currentNum].Option_two;
			Options[2] = DATA.choice[currentNum].Option_three;
			Options[3] = DATA.choice[currentNum].Option_four;
			Options[4] = DATA.choice[currentNum].Option_five;
			Options[5] = DATA.choice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"radio\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //String.fromCharCode(j+65)转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
			break;
		case 4:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.mulchoice[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"CorrectA\"><p  id=\"Content\" style=\"padding-left: 30px;\">正确答案：" + DATA.mulchoice[currentNum].answer + "</p></div>";
			Options[0] =DATA.mulchoice[currentNum].Option_one;
			Options[1] =DATA.mulchoice[currentNum].Option_two;
			Options[2] =DATA.mulchoice[currentNum].Option_three;
			Options[3] =DATA.mulchoice[currentNum].Option_four;
			Options[4] =DATA.mulchoice[currentNum].Option_five;
			Options[5] =DATA.mulchoice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"checkbox\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
			break;
		
		}
		report();
		isReshow = 0;
	}	
	
	//题目进度显示
	function showCNumber(){
		var z ="";
		var Num;
		var x;
		x = currentNum+1;
		z += "<div class=\"Q_nub\"><p>第<input id=\"input1\" type=\"text\" class=\"skip\" placeholder=\"" + x +"\">题/共";
		z += PresentSum;
		z += "题<button id=\"input2\" class=\"skip1\" onclick=\"Rskip()\">跳转</button>";
		z += "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;正确率：";
		if(WorkedNumber == 0){
			z += "0";                  //正确率值
		}
		else{
			Num = Current/WorkedNumber*100;                  //正确率值
			z += Num.toFixed(1);
		}
		z += "%</p></div>";
		$("#navigation1").html(z);
	}

	//下标跳转
	function Rskip(){
		var Value = document.getElementById("input1").value
		if(Value<=PresentSum && Value>0){
			skip(type,Number(Value)-1);
		}
		else{
			$.cxDialog.defaults.baseClass = 'ios';
			$.cxDialog({
				title: '提示',
				info: '题号超出范围!',
				ok: function(){
				}
			});
		}
	}
	
	
	//题目跳转
	function skip(Stype,SNum){
		currentNum = SNum;
		var C;
		if(Stype == type){
			switch (type){
				
				case 2:
					C = DATA.judgment[currentNum].itemStatus;
					break;
				case 3:
					C = DATA.choice[currentNum].itemStatus;
					break;
				case 4:
					C = DATA.mulchoice[currentNum].itemStatus;
					break;
				
			}
			if(C==0){
				showQuestionType(Stype);
			}
			
			if(C==3){
				showQuestionTypeW(Stype);		
			}
		}
		else{
			type = Stype;
			GetData(Stype);
			Valuation(Stype);
			showQuestionType(Stype);
		}
	}

	//答案处理
	function AnswerDispose(){
		answer="";   //初始化answer
		var a;  //单个空格答案
		if (type == 1)
		{
			for(j=0;j<NowCmpN;j++)
			{
				a = document.getElementById("UAnswer"+(j+1)).value;
				a = a.substring(0,50);
				if(a ==""){
					a=" ";
					answer+=a + "$%";
				}
				else{
					answer+=a + "$%";
				}
			}
			return answer;
		}
		else if(type == 5){
			answer = "";
			return answer;
		}
		else if(type == 2 || type == 3)
		{
			var radio;    //目前题目选项信息
			var checked = false;  //是否被选中判定
			radio = document.getElementsByName("choice");
			checked = false;
			for(j=0;j<radio.length;j++)
			{
				checked = checked||radio[j].checked;
			}
			if (!checked)
			{
				return answer;
			}
			else{
				for(j=0;j<radio.length;j++)
				{
					if(radio[j].checked)	{
					answer = radio[j].value;
					}
				}
				return answer;
			}
		}
		else if(type == 4){
			var checkbox;    //目前题目选项信息
			var checked = false;  //是否被选中判定
			checkbox = document.getElementsByName("choice");
			checked = false;
			for(j=0;j<checkbox.length;j++)
			{
				checked = checked||checkbox[j].checked;
			}
			if (!checked)
			{
				return answer;
			}
			else{
				for(j=0;j<checkbox.length;j++)
				{
					if(checkbox[j].checked)	{
					answer += checkbox[j].value;
				}
			}
			return answer;
			}
		}
	}

	//题目类型样式变化
	function Buttonclass(value,ab){
		
		document.getElementById("Btf").setAttribute("class","buttom");
		document.getElementById("Bchoice").setAttribute("class","buttom");
		document.getElementById("Bmulchoice").setAttribute("class","buttom");
		
		switch (value){
		
		case 2:
		document.getElementById("Btf").setAttribute("class","activebuttom");
		break;
		case 3:
		document.getElementById("Bchoice").setAttribute("class","activebuttom");
		break;
		case 4:
		document.getElementById("Bmulchoice").setAttribute("class","activebuttom");
		break;
		
		}
	}

	//重新开始按钮
	function restart(){
		GetData(type);
		skip(type,0);
	};

	//下一题按钮
	function Next(){
		var xtype= type;   //临时变量 ，（type替代用）
		var VSum = currentNum;
		switch (type){
			
			case 2:
				C = DATA.judgment[currentNum].itemStatus;
				break;
			case 3:
				C = DATA.choice[currentNum].itemStatus;
				break;
			case 4:
				C = DATA.mulchoice[currentNum].itemStatus;
				break;
			
		}
		if(C==0){
			Correct();
			if(isReshow == 0){
				VSum++;	
			}
		}
		else{
			if(VSum+1>PresentSum-1){
				$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '已经是最后一道题了!',
					ok: function(){
					}
				});
			}
			else{
				VSum++;
			}
		}
		skip(xtype,VSum);
	}

	function key(){
    	var e = window.event || event;
    	if(e.keyCode == 13)
    		Next();
    }
	
	//上一题按钮
	function Up(){
		var xtype;   //临时变量 ，（type替代用）
		var VSum = currentNum;
		xtype = type;
		switch (type){
			
			case 2:
				C = DATA.judgment[currentNum].itemStatus;
				break;
			case 3:
				C = DATA.choice[currentNum].itemStatus;
				break;
			case 4:
				C = DATA.mulchoice[currentNum].itemStatus;
				break;
			
		}
		if(C==0){
			Correct();
			if(isReshow == 0){
				VSum--;	
			}
		}
		else{
			if(VSum-1<0){
				$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '已经是第一道题了!',
					ok: function(){
					}
				});
			}
			else{
				VSum--;
			}
		}
		skip(xtype,VSum);
	}

	//随机显示按钮
	function Random(){
		if (isRandom == 0){
			isRandom = 1;
			document.getElementById("RD").setAttribute("class","activebuttom");
			$("#RD").html("随机模式");
			GetData(type);
			skip(type,0);
		}
		else{
			isRandom = 0;
			document.getElementById("RD").setAttribute("class","buttom");
			$("#RD").html("顺序模式");
			GetData(type);
			skip(type,0);
		}
	}


	//答案回写
	function report(){
		var returnAnswer;    //回写答案
		var itemStatus;   //题目状态
		switch (type){
			
			case 2:
				returnAnswer = DATA.judgment[currentNum].userAnswer;
				if(returnAnswer != ""){
					if(returnAnswer == 1){
						var choice = document.getElementsByName("choice");
						choice[0].checked = "checked";
					}
					else{
						var choice = document.getElementsByName("choice");
						choice[1].checked = "checked";
					}
				}
				break;
			case 3:
				returnAnswer = DATA.choice[currentNum].userAnswer;
				if(returnAnswer != ""){
					if(returnAnswer.indexOf("A") != -1){
						var choice = document.getElementsByName("choice");
						choice[0].checked = "checked";
					}
					else if(returnAnswer.indexOf("B") != -1){
						var choice = document.getElementsByName("choice");
						choice[1].checked = "checked";
					}
					else if(returnAnswer.indexOf("C") != -1){
						var choice = document.getElementsByName("choice");
						choice[2].checked = "checked";
					}
					else if(returnAnswer.indexOf("D") != -1){
						var choice = document.getElementsByName("choice");
						choice[3].checked = "checked";
					}
				}
				break;
			case 4:
				returnAnswer = DATA.mulchoice[currentNum].userAnswer;
				if(returnAnswer != ""){
					if(returnAnswer.indexOf("A") != -1){
						var choice = document.getElementsByName("choice");
						choice[0].checked = "checked";
					}
					if(returnAnswer.indexOf("B") != -1){
						var choice = document.getElementsByName("choice");
						choice[1].checked = "checked";
					}
					if(returnAnswer.indexOf("C") != -1){
						var choice = document.getElementsByName("choice");
						choice[2].checked = "checked";
					}
					if(returnAnswer.indexOf("D") != -1){
						var choice = document.getElementsByName("choice");
						choice[3].checked = "checked";
					}
				}
				break;
		}
	}

	//答案正确性判断，回发isReshow 1 0 
	function Correct(){
		AnswerDispose();
		switch (type){
			
		case 2:
			if(DATA.judgment[currentNum].itemStatus == 0){
				accept(answer);
				if(answer == DATA.judgment[currentNum].answer){
					DATA.judgment[currentNum].itemStatus =2;
					Current++;
					isReshow = 0;
				}else{
					DATA.judgment[currentNum].itemStatus =3;
					isReshow = 1;
				}
				WorkedNumber++;
			}
			break;
		case 3:
			if(DATA.choice[currentNum].itemStatus == 0){
				accept(answer);
				if(answer == DATA.choice[currentNum].answer){
					DATA.choice[currentNum].itemStatus =2;
					Current++;
					isReshow = 0;
				}else{
					DATA.choice[currentNum].itemStatus =3;
					isReshow = 1;
				}
				WorkedNumber++;
			}
			break;
		case 4:
			if(DATA.mulchoice[currentNum].itemStatus == 0){
				accept(answer);
				if(answer == DATA.mulchoice[currentNum].answer){
					DATA.mulchoice[currentNum].itemStatus =2;
					Current++;
					isReshow = 0;
				}else{
					DATA.mulchoice[currentNum].itemStatus =3;
					isReshow = 1;
				}
				WorkedNumber++;
			}
			break;
		
		}
	}


	//数据传递
	function accept(value){
		switch (type){
			case 1:
				DATA.completion[currentNum].userAnswer = value;
				break;
			case 2:
				DATA.judgment[currentNum].userAnswer = value;
				break;
			case 3:
				DATA.choice[currentNum].userAnswer = value;
				break;
			case 4:
				DATA.mulchoice[currentNum].userAnswer = value;
				break;
		}
	}
	
	function no(){
		$.cxDialog.defaults.baseClass = 'ios';
		$.cxDialog({
			title: '提示',
			info: '暂未开启！',
			ok: function(){
			}
		});
	}

	

	
		GetData(type);
		Valuation(type);
		showQuestionType(type);

</script>