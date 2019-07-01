	<%@ page language="java" contentType="text/html; charset=utf-8"
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
							<li><a class="buttom" id="Bcmp" onclick="skip(1,0)">填空题</a></li>
							<li><a class="buttom" id="Btf" onclick="skip(2,0)">判断题</a></li>
							<li><a class="buttom" id="Bchoice" onclick="skip(3,0)">单选题</a></li>
							<li><a class="buttom" id="Bmulchoice" onclick="skip(4,0)">多选题</a></li>
							<li><a class="buttom" id="Bq" onclick="skip(5,0)">简答题</a></li>
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
<script src="../resource/js/ExerciseActive.js"></script>
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
		type = 1,               //目前题目类型 1填空 2判断 3单选 4多选 5简答
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


	
		GetData(type);
		Valuation(type);
		showQuestionType(type);

</script>