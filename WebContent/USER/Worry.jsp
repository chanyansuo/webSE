<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错题集</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
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
<!--login strip start here-->
<div class="header">
	<div class="header-info">
		<div class="container">
				<div class="menu_test">
					<span class="menu-info"> </span>
						<ul class="cl-effect-test">
							<li><a href="index.jsp" class="activebuttom">单选题</a></li>
							<li><a href=# class="buttom">多选题</a></li>
							<li><a href=# class="buttom">判断题</a></li>
							<li><a href=# class="buttom">填空题</a></li>
							<li><a href=# class="buttom">简答题</a></li>
						</ul>
				</div>
			<div class="clearfix"> </div>
				<div class="menu_test1">
					<span class="menu-info"> </span>
						<ul class="cl-effect-test">
							<li><a href="Favorite.jsp" class="buttom">收藏夹</a></li>
							<li><a href=# class="activebuttom">错题集</a></li>
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
			<div class="Question1">
				<form>
				<div class="title">
					<p>1、制定《武器装备科研生产单位保密资格审查认证管理办法》的依据是（ &nbsp;&nbsp;&nbsp;&nbsp;）</p>
				</div>
				<div class="Answer">
					<input name="choice" type="radio"  value="A"/><label>A、《中华人民共和国保守国家秘密法》和有关保密规定</label>
				</div>
				<div class="Answer">
					<input name="choice" type="radio"  value="B"/><label>B、《中华人民共和国知识产权法》和有关保密规定</label>
				</div>
				<div class="Answer">
					<input name="choice" type="radio"  value="B"/><label>C、《中华人民共和国国家安全法》和有关保密规定</label>
				</div>
				<div class="Answer">
					<input name="choice" type="radio"  value="B"/><label>D、《武器装备科研生产许可管理条例》和有关保密规定</label>
				</div>			
				</form>
			</div>
			<div class="navigation">
			 		<div class="nav">
			 			<a href="#" class="buttom" onclick="fun1()">暂&nbsp;&nbsp;&nbsp;&nbsp;停</a>
			 		</div>
				 	<div class="nav">
				 		<a href="#" class="buttom">下一题</a>
				 	</div>
				 	<div class="nav">
				 		<a href="#" class="buttom">上一题</a>
					</div>				 	
					<div class="nav">
				 		<a href="#" class="buttom">重新开始</a>
					</div>
			</div>
			<div class="img-bg1">
				<div class="navigation1">
					<div class="Q_nub">
				 		<p>第1题/共160题    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				 		进度：1%
				 		</p>
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