	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.*,java.sql.*,java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>模拟考试</title>
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
<body>
<div class="header">
	<div class="header-info">
		<div class="container">
			<jsp:include page="navigation.jsp"></jsp:include>
		</div>
	</div>
</div>
<!--header-->
<div class="header">
	<div class="header-info">
		<div class="container">
				<div class="menu_test">
				<p id="IdName" class="IdName"></p>
					<span class="menu-info"> </span>
						<ul class="cl-effect-test1">
							<li><a class="buttom" id="Bcmp" onclick="skip(1,0)">填空题</a></li>
							<li><a class="buttom" id="Btf" onclick="skip(2,0)">判断题</a></li>
							<li><a class="buttom" id="Bchoice" onclick="skip(3,0)">单选题</a></li>
							<li><a class="buttom" id="Bmulchoice" onclick="skip(4,0)">多选题</a></li>
							<li><a class="buttom" id="Bq" onclick="skip(5,0)">简答题</a></li>
						</ul>
				</div>
			<div class="clearfix"> </div>
			<div class="menu_test1">
				<div class="now-time">
					<p><i>&nbsp;</i><span id="timepiece">00:00:00</span></p>
				</div>				
				<div class="test-time">
					<p>剩余时间：</p>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!--/header-->
<!--login strip start here-->
<div class="dd2">
	<div>
		<div class="container">
			<div class="img-bg">
			<form>
			<div id="Question" class="Question">


			</div>
			<div class="navigation">
			 	<div class="nav">
			 		<a href="#" class="buttom" onclick="handinAttention()">交&nbsp;&nbsp;&nbsp;&nbsp;卷</a>
			 	</div>
			 	<div class="nav">
			 		<a href="#" class="buttom" onclick="Next()">下一题</a>
			 	</div>
			 	<div class="nav">
			 		<a href="#" class="buttom"  onclick="Up()">上一题</a>
			 	</div>
			</div>
			</form>
			<div class="img-bg1">
			<div class="Number">
				<div id="CmpDIV" class="title2">

				</div>
				<div id="TFDIV" class="title2">

				</div>				
				<div id="choiceDIV" class="title2">

				</div>
				<div id="mulchoiceDIV" class="title2">

				</div>
				<div id="QutDIV" class="title2">

				</div>
			</div>
			</div>
			
			</div>
		</div>
	</div>
</div>
<!--login strip end here-->
</body>
</html>
<script src="../resource/js/SimulationActive.js"></script>
<script language="javascript" type="text/javascript">
	//全局变量
	var DATA,              //全部数据
		User,
		paperNum,          //存题号
		Sum,               //一共有多少题
		CSum,              //单选题题数
		MulCSum,           //多选题题数
		TFSum,             //判断题题数
		CmpSum,            //填空题题数
		QutSum,            //简答题题数
		currentNum = 0,  //当前该类型下题号
		ShowNum,        //显示题号
		Question,          //题目文本
		type,              //目前题目类型 1填空 2判断 3单选 4多选 5简答
		Options = new Array(),     //选项集合
		RadioNum,              //选项个数
		NowCmpN,      //目前填空题空格数
		answer,             //目前题目用户输入的答案
		Nubarray = new Array(),           //题目样式集合
		y,                  //String公用存储空间
		c,               //计时器起始时间，假设为45分钟，此处单位为秒
		j;              //计数器

	//连接数据库
	function GetData(){
		$.ajax({
   	 		cache: true,
   		 	type: "GET",
    		dataType: "json",
    		url: "../rest/Practice/getExam",   //后台连接代码
    		async: false,
    		error: function(request) {
    	    	alert("生成考卷失败");
    		},
    		success: function(data) {
    			if (data==false) {//传过来是字符型，但只用用boolen判断
					$.cxDialog.defaults.baseClass = 'ios';
    				$.cxDialog({
    					title: '提示',
    					info: '考试已结束，请勿重复答题！',
    					ok: function(){
    						window.close();
    					}
    				});
				}
    			else{
    				DATA = data;
    			}
    	
	    	}
		});
	}


	//交卷按钮
	function handin(){
		answer = AnswerDispose();
		if(answer != ""){
			accept(answer);
		}
		$.ajax({
   	 		cache: true,
   		 	type: "POST",
    		dataType: "json",
    		url: "../rest/Practice/endExam",   //后台连接代码
    		async: false,
    		error: function(request) {
    	    	alert("生成考卷失败");
    		},
    		success: function(data) {
    			$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '评卷完成！',
					ok: function(){
						
					}
				});
	    	}
		});
		window.location="Simulation_finished.jsp";
	}

	



		GetData();
		Valuation();
		Number();
		showQuestionType(type);
		timedCount();

</script>
