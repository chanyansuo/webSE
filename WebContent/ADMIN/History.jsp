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
<body>

	<div style="margin:20px 0;"></div>
	
	<div style="margin-bottom: 30px;">
		<div class="easyui-panel" style="width:100%;max-width:100%;padding:30px 60px;border-color:#ae9ae7;background-color:#e8e6f0;">
			考试日期：<input class="easyui-datebox" id="d1" style="width:150px">
			至：<input class="easyui-datebox" id="d2" style="width:150px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()">查询</a>
			<input id="batch" class="easyui-combobox" name="batch" style="width:200px;" data-options="valueField: 'id',textField: 'text',labelPosition: 'top',editable:false,panelHeight:'auto'">
			<a href="#" class="easyui-linkbutton" onclick="pull()">确认</a>
			<input id="batch2" class="easyui-combobox" name="batch" style="width:200px;" data-options="valueField: 'id',textField: 'text',labelPosition: 'top',editable:false,panelHeight:'auto'">
			<a href="#" class="easyui-linkbutton" onclick="pull_ID()">确认</a>	
		</div>
	</div>
	
	<script type="text/javascript">
	
	var BATCH;
	
	function doSearch(){
		$('#dg').datagrid('loadData',{total:0,rows:[]});
		var date1 = document.getElementById("d1").value;
		var date2 = document.getElementById("d2").value;
		if(date1 == "" || date2 == "" || date1>date2){
			$.cxDialog.defaults.baseClass = 'ios';
			$.cxDialog({
				title: '提示',
				info: '时间输入错误！',
				ok: function(){
				}
			});
		}
		else{
			$.ajax({                                 //发送时间数据
				cache: true,
				type: "POST",
				dataType: "json",
				url: "../rest/Manage/searchExamId",
				data:{"startTime":date1, "overTime":date2},
				async: false,
				error: function(errCode, errMsg) {
					alert("Connection error: "+errCode);
				},
				success: function(data) {
					BATCH = data;
				}
			});
			if(BATCH == ""){
				$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '没有符合的考试批次！',
					ok: function(){
					}
				});
			}
			else{
				$("#batch").combobox({
					data:BATCH
				});    //替换后台连接
			}
		}
	}
	//查找数据传并传送的combobox	END

	//下拉框选中后表格显示
	function pull(){
		$('#dg').datagrid('reload');
		$('#dg').datagrid('loadData',{total:0,rows:[]});
		var batchChoiced = $("#batch").combobox('getText'); 
		window.sessionStorage.setItem('Batch_Choiced', batchChoiced);
		if(batchChoiced == ""){
			alert("请先选择考试批次！")
		}
		else{
			$.ajax({                                 //发送考试批次数据  返回参与考试人员名单（工号）
				cache: true,
				type: "POST",
				dataType: "json",
				url: "../rest/Manage/searchExamId",
				data:{"startTime":date1, "overTime":date2},
				async: false,
				error: function(errCode, errMsg) {
					alert("Connection error: "+errCode);
				},
				success: function(data) {
					BATCH = data;
				}
			});
			$("#batch2").combobox({
				data:BATCH
			});    //替换后台连接
		}	
	}
	
	function pull_ID(){
		var idChoiced = $("#batch2").combobox('getText');
		window.sessionStorage.setItem('PDF_ID', idChoiced);
	}
	
	</script>
	
</body>
</html>