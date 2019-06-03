<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理界面-成绩统计题库</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../resource/css/easyui.css">
<link rel="stylesheet" type="text/css" href="../resource/css/icon.css">
<link rel="stylesheet" type="text/css" href="../resource/css/demo.css">
<link rel="stylesheet" type="text/css" href="../resource/css/default_theme.css">
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
    
    var DATA;
    $(function(){
		$.ajax({
			cache: true,
			type: "GET",
			dataType: "json",
			url: "../rest/Manage/getQuestions/2",
			async: false,
			success: function(data) {
				DATA = data;
			}
		});
	});
  </script>
</head>
<body style="margin:20px 200px;">

	<div style="margin:20px 0;"></div>

<div>

	<div style="margin-bottom: 30px;">
		<div class="easyui-panel" style="width:100%;max-width:100%;padding:30px 60px;border-color:#ae9ae7;background-color:#e8e6f0;">
			<label style="
    		margin-bottom: 0px;
    		font-size: 1.2em;
			">考试批次</label>
			<select class="easyui-combobox" id="PiCi" style="width:30%;margin-bottom: 20px;" data-options="editable:false,selectOnNavigation:false,panelHeight:'auto'">

			</select>
			<p class="easyui-linkbutton" style="width: 50px;height: 30px" onclick="submit1()">确认</p>
		</div>
	</div>

	<div style="margin-bottom: 30px;">
		<div class="easyui-panel" style="width:100%;max-width:100%;padding:30px 60px;border-color:#95B8E7;background-color:#E2EDFD;">
			<label style="
    		margin-bottom: 10px;
    		font-size: 1.2em;
			">目前参考人员</label>
			<input class="easyui-tagbox" id="choiced" value="" style="width:100%;">
			<div style="text-align: center;">
				<p class="easyui-linkbutton" style="width:150px;margin-top: 30px;" onclick="submit2()">确认</p>
			</div>
		</div>
	</div>

	<div style="margin-bottom: 30px;">
		<div class="easyui-panel" id="0" style="width:100%;max-width:100%;padding:30px 60px;">
			<input name="choice" type="radio" value="1" style="
			margin: 0px 10px 10px 0px;
			">
			<label style="
    		margin-bottom: 10px;
    		font-size: 1.2em;
			">全选</label>
		</div>
	</div>
	
	<div style="margin-bottom: 30px;">
		<div class="easyui-panel" id="1" style="width:100%;max-width:100%;padding:30px 60px;">
			<input name="choice" type="radio" value="2" style="
			margin: 0px 10px 10px 0px;
			">
			<label style="
    		margin-bottom: 10px;
    		font-size: 1.2em;
			">根据工号选择</label>
			<p style="
    		margin-bottom: 10px;
    		font-size: 1.2em;
			">
			<input class="easyui-textbox" id="d1" style="width:150px">&nbsp;&nbsp;&nbsp;至&nbsp;
			<input class="easyui-textbox" id="d2" style="width:150px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()">查询</a>
			</p>
			<input class="easyui-tagbox" id="text" value="" style="width:100%;">
		</div>
	</div>
		
	<div style="margin-bottom: 30px;">
		<div class="easyui-panel" id="2" style="width:100%;max-width:100%;padding:30px 60px;">
			<input name="choice" type="radio" value="3" style="
			margin: 0px 10px 10px 0px;
			">
			<label style="
    		margin-bottom: 10px;
    		font-size: 1.2em;
			">根据部门进行选择</label>
			<select class="easyui-combobox" id="combo1" style="width:100%;margin-bottom: 20px;" data-options="editable:false,selectOnNavigation:false,panelHeight:'auto'">
				<option value="">--请选择部门--</option>
				<option value="分所领导">分所领导</option>
				<option value="综合计划">综合计划</option>
				<option value="研究开发">研究开发</option>
				<option value="系统工程">系统工程</option>
				<option value="市场发展">市场发展</option>
				<option value="技术制造">技术制造</option>
				<option value="在读研究生">在读研究生</option>
			</select>
			<div style="padding-top: 20px;">
				<input id="D" class="easyui-tagbox" value="" style="width:100%;">
			</div>	
		</div>
	</div>	
	
	<div style="text-align: center;margin-bottom: 30px;">
		<p id="add" class="easyui-linkbutton" style="width:150px" onclick="add()">添加</p>
	</div>	
		
</div>	
	<script type="text/javascript">
	
	var radio = document.getElementsByName("choice");
	var Choiced; //已选中的参考人员工号
	var Choice;
	var People;
	var Form;
	var checked = false;
	var i;  //计数器
	var j;  //计数器
	
	radio[0].onclick = function(){
		for(j=0;j<radio.length;j++){
			if(radio[j].checked){
				$("#"+j).attr("style","width:100%;max-width:100%;padding:30px 60px;border-color:#b9e7e4;background-color:#f7fdfb;");
	   		}
	   		else{
	   			$("#"+j).attr("style","width:100%;max-width:100%;padding:30px 60px;border-color:#c5c5c5;");
	   		}
		}
   	}
	radio[1].onclick = function(){
		for(j=0;j<radio.length;j++){
			if(radio[j].checked){
				$("#"+j).attr("style","width:100%;max-width:100%;padding:30px 60px;border-color:#b9e7e4;background-color:#f7fdfb;");
			}
	   		else{
	   			$("#"+j).attr("style","width:100%;max-width:100%;padding:30px 60px;border-color:#c5c5c5;");
	   		}
		}
   	}
	radio[2].onclick = function(){
		for(j=0;j<radio.length;j++){
			if(radio[j].checked){
				$("#"+j).attr("style","width:100%;max-width:100%;padding:30px 60px;border-color:#b9e7e4;background-color:#f7fdfb;");
			}
	   		else{
	   			$("#"+j).attr("style","width:100%;max-width:100%;padding:30px 60px;border-color:#c5c5c5;");
	   		}
		}
   	}
	
	$("#combo1").combobox({
		onChange:function(){
			var selectobj = document.getElementById("combo1").value;
			alert(selectobj);
// 			$.ajax({                                 //发送时间数据
//				cache: true,
//				type: "POST",
//				dataType: "json",
//				url: "../rest/Manage/searchExamId",
//				data:{"startTime":date1, "overTime":date2},
//				async: false,
//				error: function(errCode, errMsg) {
//					alert("Connection error: "+errCode);
//				},
//					success: function(data) {
//					BATCH = data;
//				}
//			});
			$("#D").tagbox({value:"1111 王XX,1112 徐XX,Y3123 李XX"});
		}
	});
	
	function doSearch(){
		var date1 = document.getElementById("d1").value;
		var date2 = document.getElementById("d2").value;
		if(date1 == "" || date2 == "" || date1>date2){
			alert("工号输入错误！")
		}
		else{
// 			$.ajax({                                 //发送时间数据
// 				cache: true,
// 				type: "POST",
// 				dataType: "json",
// 				url: "../rest/Manage/searchExamId",
// 				data:{"startTime":date1, "overTime":date2},
// 				async: false,
// 				error: function(errCode, errMsg) {
// 					alert("Connection error: "+errCode);
// 				},
// 					success: function(data) {
// 					BATCH = data;
// 				}
// 			});
			$("#text").tagbox({value:"1111 王XX,1112 徐XX"});    //向tag框输入文字
		}
	}
	
	function Dispose(){
		for(j=0;j<radio.length;j++)
		{
			checked = checked||radio[j].checked;
		}
		if (!checked)
		{
			$.cxDialog.defaults.baseClass = 'ios';
			$.cxDialog({
				title: '提示',
				info: '没有选择考试人员',
				ok: function(){	
				}
			});
			return false;
		}
		else{
			for(j=0;j<radio.length;j++)
			{
				if(radio[j].checked){
					Choice = radio[j].value;
				}
			}
		}
		Choice = parseInt(Choice);
		switch (Choice)                           //被选中框
		{
			case 1:
				People = "ALL";
				return true;
				break;
			case 2:
				People = document.getElementById("text").value;
				return true;
				break;
			case 3:
				People = document.getElementById("D").value;
				return true;
				break;
		}
	}
	
	function add(){
		if(Dispose()){
			if(People == "ALL"){
				$("#choiced").tagbox({value:"ALL"});
			}
			else{
				var istrue;
				var s = People.split(',');
				var y = document.getElementById("choiced").value;
				var c = y.split(',');
				for(j=0;j<s.length;j++)
				{
					istrue = true;
					if(s[j].substring(0,1) != "Y"){
						s[j] = s[j].substring(0,4);
						
					}
					else{
						s[j] = s[j].substring(0,5);
					}
					for(i=0;i<c.length;i++)
					{
						if(s[j] == c[i]){
							istrue = false
						}
					}
					if(istrue){
						if(c[0].length > 1){	
							y += ",";
						}
						y += s[j];
						y += ",";
					}
				}
			}
			y = y.substring(0,y.length-1);
			$("#choiced").tagbox({value:y});
		}
	}

	function submit1(){
		var Pici = document.getElementById("PiCi").value;
		alert(Pici);
	}

	function submit2(){
		var Choiced = document.getElementById("choiced").value;
		alert(Choiced);
	}
	</script>
</body>
</html>