<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>保密知识学习</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
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
<!--header-->
<div class="header">
	<div class="header-info">
		<div class="container">
			<jsp:include page="navigation.jsp"></jsp:include>
		</div>
	</div>
</div>
<!--/header-->
<!--baomi strip start here-->
<div class="dd1">
<div class="dd-width">
	<div class="quartern">
		<div class="container2" style="margin:0px 2em;">
		<div class="KnowledgeLearning">
				<a href="Study_baomi.jsp"><img src="../resource/images/1.png"  alt=""/></a>
		</div>
		</div>
	</div>
	<div class="quartern">
		<div class="container2" style="margin:0px 2em;">
		<div class="Exercise">
				<a href="Exercise.jsp"><img src="../resource/images/1.png"  alt=""/></a>
		</div>
		</div>
	</div>
	<div class="quartern">
		<div class="container2" style="margin:0px 2em;">
		<div class="Simulation">
				<a href="Simulation.jsp"><img src="../resource/images/1.png" alt=""/></a>
		</div>
		</div>
	</div>
	<div class="quartern">
		<div class="container2" style="margin:0px 2em;">
		<div class="Test">
				<a onclick="fun1()"><img src="../resource/images/1.png" alt=""/></a>
				<script language="javascript" type="text/javascript">
				function fun1(){
			    	$.cxDialog.defaults.baseClass = 'ios';
					$.cxDialog({
						title: '信息：',
						info: '考试中途不得离开网页!是否开始考试？',
						ok: function(){
							window.open("Test.jsp",'newwindow','fullscreen=1','menubar=0');
						}
						,
						okText: '开始',
						no: function(){
						}
						,
						noText: '取消'
					});
			    }
				
// 				function fun2(){
// 					$.cxDialog.defaults.baseClass = 'ios';
//     				$.cxDialog({
//     					title: '提示',
//     					info: '考试即将开始！请做好准备！',
//     					ok: function(){
//     						window.open("Test.jsp",'newwindow','fullscreen=1','menubar=0');
//     					}
//     				});
// 				}
				</script>
		</div>
		</div>
	</div>
</div>
</div>
<!--baomi strip end here-->
</body>
</html>
<script language="javascript" type="text/javascript">
function no(){
	$.cxDialog.defaults.baseClass = 'ios';
	$.cxDialog({
		title: '提示',
		info: '暂未开启！',
		ok: function(){
		}
	});
}
</script>