<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理界面-试卷主观题评分</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../resource/css/easyui.css">
<link rel="stylesheet" type="text/css" href="../resource/css/icon.css">
<link rel="stylesheet" type="text/css" href="../resource/css/demo.css">
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
<script type="text/javascript" src="../resource/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resource/js/easyui-lang-zh_CN.js"></script>
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
	<div id="Grade" style="margin-bottom:20px">
		<p id="titil">题目：</p>
		<textarea id="TiMu" class="BATCHbox" readonly="readonly"></textarea>
		<p id="titil1">正确答案：</p>
		<textarea id="Answer" class="BATCHQbox" readonly="readonly"></textarea>
		<p id="people"></p>
		<textarea id="UAnswer" class="BATCHAbox" readonly="readonly"></textarea>
		<p id="people1"></p><input id="4" type="number" class="BATCHinput"></input>
		<input class="BATCHsubmit" type="button" value="确认" onclick="post(j)"></input>
	</div>
<script type="text/javascript">
var paperGrade;
var people;
var people1;
var SCORE;
var j = 0;    //计数器
$.ajax({
	cache: true,
	type: "GET",
	dataType: "json",
	url: "../rest/Manage/getAllQuestionAnswer",
	async: false,
	success: function(data) {
		paperGrade = data;	
	}
});
var length = paperGrade.rows.length;
if(length == 0){
	var y = "<p id=\"titil\">暂无评卷内容！</p>";
	$("#Grade").html(y);	
}
else {
	doshow(j);
}

function doshow(value){
	if(j>=length){
		var y = "<p id=\"titil\">题目已经评阅完毕！</p>";
		$("#Grade").html(y);
	}
	else{
		show(j);
	}
}

function show(value){
	$("#TiMu").val(paperGrade.rows[value].Content);
	$("#Answer").val(paperGrade.rows[value].Answer);
	var UserAnswer = paperGrade.rows[value].User_answer;
	if(UserAnswer != "" &&  typeof(UserAnswer) != "undefined"){
		$("#UAnswer").val(UserAnswer);
	}
	Score = paperGrade.rows[value].QA_Score;
	people ="工号："+paperGrade.rows[value].Employee_id+" "+"答案：";
	people1 ="评分：（本题满分："+ Score +"）";
	document.getElementById("people").innerHTML = people;
	document.getElementById("people1").innerHTML = people1;
	document.getElementById("4").value = "";
}

function post(value){
	var score;
	score=document.getElementById("4").value;
	if(score != "" && Number(score)<=Score && Number(score)>=0)
	{
		paperGrade.rows[value].Statues = 1;
		paperGrade.rows[value].Question_score = Number(score);
		var SC = paperGrade.rows[value];
		SCORE = JSON.stringify(SC);
		$.ajax({
   	 		cache: true,
   		 	type: "POST",
    		dataType: "json",
    		data:{"SCORE":SCORE},
    		url: "../rest/Manage/CheckQanswerScore",   //后台连接代码
    		async: false,
    		error: function(request) {
    			$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '评卷失败！',
					ok: function(){
					}
				});
    		},
    		success: function(data) {
    			$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '评卷成功！',
					ok: function(){
					}
				});
    			j++;
    			doshow(j);
	    	}
		});
	}
	else{
		$.cxDialog.defaults.baseClass = 'ios';
		$.cxDialog({
			title: '提示',
			info: '评分不符合规范，请再次检查！',
			ok: function(){
			}
		});
	}
}
</script>
</body>
</html>