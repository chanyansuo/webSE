<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>安全知识学习</title>

	<meta name="description" content="Heera HTML5 Template by Jewel Theme" >

	<meta name="author" content="Jewel Theme">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->

	<!-- Bootstrap  -->
	<link href="../assets/css/bootstrap.min2.css" rel="stylesheet">

	<!-- icon fonts font Awesome -->
	<link href="../assets/css/font-awesome.min.css" rel="stylesheet">

	<!-- Import Magnific Pop Up Styles -->
	<link rel="stylesheet" href="../assets/css/magnific-popup.css">

	<!-- Import Custom Styles -->
	<link href="../assets/css/style2.css" rel="stylesheet">

	<!-- Import Animate Styles -->
	<link href="../assets/css/animate.min.css" rel="stylesheet">

	<!-- Import owl.carousel Styles -->
	<link href="../assets/css/owl.carousel.css" rel="stylesheet">

	<!-- Import Custom Responsive Styles -->
	<link href="../assets/css/responsive.css" rel="stylesheet">
	
	<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
	<link rel="stylesheet" href="../resource/css/Exam.css" type="text/css" media="all" />
	<link rel="stylesheet" href="../resource/css/zzsc.css" type="text/css" media="all" />

	
	
  		<!--<script src="assets/js/html5shiv.js"></script>-->
        <!--<script src="assets/js/respond.min.js"></script>-->
  		

  	</head>
  	<body class="header-fixed-top">

  		<div id="page-top" class="page-top"></div><!-- /.page-top -->

  		<section id="site-banner" class="site-banner text-center">
  			<div class="container">
  				<div class="site-logo">
  					<a href="./"><img src="../resource/images/logo.png" alt="Site Logo"></a>
  				</div><!-- /.site-logo -->
  			</div><!-- /.container -->
  		</section><!-- /#site-banner -->



  		<header id="main-menu" class="main-menu">
  			<div class="container">
  				<div class="row">
  					<div class="col-sm-12">
  						<div class="navbar-header">
  							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu">
  								<i class="fa fa-bars"></i>
  							</button>
              </div>
              <nav id="menu" class="menu collapse navbar-collapse">
               <ul id="headernavigation" class="menu-list nav navbar-nav">
                	<li><a id="1" href="Information_Person.jsp"><%= session.getAttribute("login_name")%></a></li>
                	<li><a href="index_Logined.jsp" style="width: 150px;text-align: center;">首&nbsp;&nbsp;页</a></li>
					<li><a href="BaomiStudy.jsp">保密学习与考试</a></li>
					<li class="active"><a href="AnquanStudy.jsp">安全学习与考试</a></li>
					<li><a href="employeebandbook.jsp"  style="width: 150px;text-align: center;">员工手册</a></li>
					<li><a href=# onclick="no()"  style="width: 150px;text-align: center;">项目管理</a></li>
					<li><a href=# onclick="fun()" style="width: 150px;text-align: center;">退&nbsp;&nbsp;出</a></li>
              </ul><!-- /.menu-list -->
            </nav><!-- /.menu-list -->
          </div>
      </div><!-- /.row -->
    </div><!-- /.container -->
  </header><!-- /#main-menu -->



  <section id="main-slider" class="main-slider carousel slide" data-ride="carousel">

   <!-- Wrapper for slides -->
   <div class="carousel-inner" role="listbox">
    <div class="item item-1 active">
      <img src="../assets/images/5.jpg" alt=""/>
<!--       <div class="carousel-caption" style="padding-top: 60px;"> -->
<!--           <h3 class="carousel-title" style="font-size: 90px;font-weight: 800;">保&nbsp;&nbsp;&nbsp;&nbsp;密 <span style="font-size: 90px;">学&nbsp;习&nbsp;&nbsp;与&nbsp;&nbsp;考&nbsp;试</span></h3> -->
<!--      </div> -->
   </div>

   <div class="item item-2">
    <img src="../assets/images/6.jpg" alt=""/>
<!--     <div class="carousel-caption" style="padding-top: 60px;">    -->
<!--           <h3 class="carousel-title" style="font-size: 90px;font-weight: 800;">保&nbsp;&nbsp;&nbsp;&nbsp;密 <span style="font-size: 90px;">学&nbsp;习&nbsp;&nbsp;与&nbsp;&nbsp;考&nbsp;试</span></h3> -->
<!-- 	</div> -->
  </div>
</div>
</section><!-- /#main-slider -->


<section class="services" id="SERVICE">
        <div class="container">
            <div class="row">
            <a href="Study_anquan.jsp">
                <div class="col-md-3 text-center">
                    <div class="single_service wow fadeInUp" data-wow-delay="0.5s">
                        <i class="icon-pencil"></i>
                        <img src="../assets/images/icon2/1.png" alt=""/>
                        <h2>&nbsp;</h2>
                        <h2>知识学习</h2>
                        <p>Knowledge Learning</p>
                    </div>
                </div>
            </a>  
			<a href="Exercise_a.jsp">      
<!--            <a href=# onclick="ExChoice()">    -->
                <div class="col-md-3 text-center">
                    <div class="single_service wow fadeInUp" data-wow-delay="0.5s">
                        <i class="icon-gears"></i>
                        <img src="../assets/images/icon2/2.png" alt=""/>
                        <h2>&nbsp;</h2>
                        <h2>练习模式</h2>
                        <p>Exercise & Pattern</p>
                    </div>
                </div>
            </a>
            <a href="Simulation_a.jsp">    
                <div class="col-md-3 text-center">
                    <div class="single_service wow fadeInUp" data-wow-delay="0.5s">
                        <i class="icon-camera"></i>
                        <img src="../assets/images/icon2/3.png" alt=""/>
                        <h2>&nbsp;</h2>
                        <h2>模拟训练</h2>
                        <p>Simulated & Training</p>
                    </div>
                </div>
            </a>
            <a href=# onclick="fun1()">    
                <div class="col-md-3 text-center">
                    <div class="single_service wow fadeInUp" data-wow-delay="0.5s">
                        <i class="icon-magnifying-glass"></i>
                        <img src="../assets/images/icon2/4.png" alt=""/>
                        <h2>&nbsp;</h2>
                        <h2>水平测试</h2>
                        <p>Horizontal & Checkout</p>
                    </div>
                </div>
            </a> 
           				 <div id="ExamClass">
							<div id="loginModal" class="modal show">
								<div class="modal-dialog" style="margin-top: 250px;">
								    <div class="modal-content">
								    	<div class="modal-header">
									        <button type="button" class="close" onclick="document.getElementById('ExamClass').style.display='none'">×</button>
									        <h1 class="text-center text-primary">考试须知</h1>
									    </div>
									    <div class="modal-body">
									    	<form action="" class="form col-md-12 center-block" onsubmit="return false">
									    	<div class="form-group">
									        	<iframe src="warning.jsp" style="height: 300px;">
							
									    		</iframe>
									   		</div>
									        <div class="form-group">
									           	<p>我已阅读并承诺<input id="loginChoice" type="checkbox" style="width: 30px;"/></p>
									        </div>
									        <div class="form-group">
									            <button id="submit" class="btn btn-primary btn-lg btn-block" onclick="test()">确认</button>
									        </div>
									        </form>
									    </div>
									    <div class="modal-footer">
									        
									    </div>
									</div>
								</div>
							</div>
						</div>  
<!--
						<div id="ExerciseClass">
							<div id="loginModal" class="modal show">
								<div class="modal-dialog" style="margin-top: 350px;">
								    <div class="modal-content">
								    	<div class="modal-header">
									        <button type="button" class="close" onclick="document.getElementById('ExerciseClass').style.display='none'">×</button>
									        <h1 class="text-center text-primary">练习类型选择</h1>
									    </div>
									    <div class="modal-body" style="height: 130px;">
									    	<form action="" class="form col-md-12 center-block" onsubmit="return false">
									        <div class="form-group" style="height: 120px;">
									           	<p style="color: #5A6569;font-size: 1.2em;line-height: 25px;">题目模式一<input style="width: 80px;" id="ExChoice" name="ExChoice" type="radio" onclick="check(this)" value="1"/></p>
									           	<p style="color: #5A6569;font-size: 1.2em;line-height: 25px;">题目模式二<input style="width: 80px;" id="ExChoice" name="ExChoice" type="radio" onclick="check(this)" value="2"/></p>
									           	<p style="color: #5A6569;font-size: 1.2em;line-height: 25px;">题目模式三<input style="width: 80px;" id="ExChoice" name="ExChoice" type="radio" onclick="check(this)" value="3"/></p>
									        </div>
									        <div class="form-group">
									            <button id="submit2" class="btn btn-primary btn-lg btn-block" onclick="fun2()">确认</button>
									        </div>
									        </form>
									    </div>
									    <div class="modal-footer">
									        
									    </div>
									</div>
								</div>
							</div>
						</div>
-->						 
            </div>            
        </div>
    </section>






<!-- Include modernizr-2.8.3.min.js -->
<!--<script src="assets/js/modernizr-2.8.3.min.js"></script>-->

<!-- Include jquery.min.js plugin -->
<script src="../assets/js/jquery-2.1.0.min.js"></script>

<!-- Include magnific-popup.min.js -->
<script src="../assets/js/jquery.magnific-popup.min.js"></script>

    <script src="../assets/js/jquery.nicescroll.js"></script>
    <script src="../assets/js/owl.carousel.js"></script>
    <script src="../assets/js/wow.js"></script>
    <script src="../assets/js/script.js"></script>

<!-- Gmap3.js For Static Maps -->
<script src="../assets/js/gmap3.js"></script>

<!-- Javascript Plugins  -->
<script src="../assets/js/plugins.js"></script>

<!-- Custom Functions  -->
<script src="../assets/js/functions.js"></script>

<script src="../assets/js/wow.min.js"></script>

<script type="text/javascript" src="../assets/js/jquery.ajaxchimp.min.js"></script>

<script src="../resource/js/jquery.cxdialog.min.js"></script>

<script language="javascript" type="text/javascript">

var DATA;
var choiced;

function no(){
	$.cxDialog.defaults.baseClass = 'ios';
	$.cxDialog({
		title: '提示',
		info: '暂未开启！',
		ok: function(){
		}
	});
}

function test(){
// 	$.cxDialog.defaults.baseClass = 'ios';
// 	$.cxDialog({
// 		title: '信息：',
// 		info: '考试中途不得离开网页!是否开始考试？',
// 		ok: function(){
// 			window.open("Test.jsp",'newwindow','fullscreen=1','menubar=0');
// 		}
// 		,
// 		okText: '开始',
// 		no: function(){
// 		}
// 		,
// 		noText: '取消'
// 	});
// 	window.open("Test.jsp",'blank','fullscreen=1','menubar=0');
	if(DATA.result == 1){
		document.getElementById("ExamClass").style.display="none";
		var choice = document.getElementById("loginChoice");
		choice.checked = false;
		$("#submit").attr("disabled","disabled");
		window.open("Test_a.jsp","blank");
	}
	else{
// 		alert(DATA.result);
		$.cxDialog.defaults.baseClass = 'ios';
		$.cxDialog({
			title: '提示',
			info: DATA.result,
			ok: function(){
				document.getElementById("ExamClass").style.display="none";
				var choice = document.getElementById("loginChoice");
				choice.checked = false;
				$("#submit").attr("disabled","disabled");
			}
		});
	}
	
}

$("#submit").attr("disabled","disabled");


function fun(){
	$.cxDialog.defaults.baseClass = 'ios';
	$.cxDialog({
		title: '信息：',
		info: '请问是否退出考试系统？',
		ok: function(){
			$.ajax({
	   	 		cache: true,
	   		 	type: "GET",
	    		dataType: "json",
	    		url: "../rest/user/logout",   //后台连接代码
	    		async: false,
	    		success: function(data) {
	    			
		    	}
			});
			location.href=("Login.jsp");
		}
		,
		okText: '确认退出',
		no: function(){
		}
		,
		noText: '暂不退出'
	});
}	

	var oBox = document.getElementById('loginChoice');
   	oBox.onclick = function(){
   		if(this.checked){
   			$("#submit").removeAttr("disabled");
   		}
   		else{
   			$("#submit").attr("disabled","disabled");
   		}
   	}
	
	function fun1(){
		$.ajax({
			cache: true,
			type: "GET",
			dataType: "json",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			url: "../rest/Exam/checkQA",
			async: false,
			error: function(errCode, errMsg) {
				alert("Connection error: "+errCode);
			},
			success:function(data){
				if(data.isExam == false || data.isExam == "false"){
					$.cxDialog.defaults.baseClass = 'ios';
       				$.cxDialog({
       					title: '提示',
       					info: data.result,
       					ok: function(){
       						document.getElementById("ExamClass").style.display="none";
       						var choice = document.getElementById("loginChoice");
       						choice.checked = false;
       						$("#submit").attr("disabled","disabled");	
       					}
       				});
// 					alert(data.result);
				}
				else{
					document.getElementById("ExamClass").style.display="block";
					DATA = data;
				}
			}
		});
    }		
	
	function ExChoice(){
		document.getElementById("ExerciseClass").style.display="block";
		var radio = document.getElementsByName("ExChoice");
		var checked = false;  //是否被选中判定
		var j;
		for(j=0;j<radio.length;j++)
		{
			checked = checked||radio[j].checked;
		}
		if (!checked)
		{
			$("#submit2").attr("disabled","disabled");	
		}
		else{
			$("#submit2").removeAttr("disabled");
		}
    }	
		
	function check(obj){
		var radio = document.getElementsByName("ExChoice");
		var checked = false;  //是否被选中判定
		var j;
		for(j=0;j<radio.length;j++)
		{
			checked = checked||radio[j].checked;
		}
		if (!checked)
		{
			$("#submit2").attr("disabled","disabled");	
		}
		else{
			for(j=0;j<radio.length;j++)
			{
				if(radio[j].checked){
					choiced = radio[j].value;
				}
			}
			$("#submit2").removeAttr("disabled");
			return choiced;
		}
	}

	
</script>
</body>
</html>