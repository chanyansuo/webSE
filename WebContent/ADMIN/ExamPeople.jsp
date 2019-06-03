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
    var Exam;
    var DetailFlag;  //全选标志
    var ExNum;  //当前选择的考试批次号
    $(function(){
		$.ajax({
			cache: true,
			type: "GET",
			dataType: "json",
			url: "../rest/Manage/getAllUserInfo",
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
			<label style="margin-bottom: 0px;font-size: 1.2em;">考试批次</label>
			<select class="easyui-combobox" id="PiCi" style="width:30%;margin-bottom: 20px;" data-options="editable:false,selectOnNavigation:false,panelHeight:'auto'">
				
			</select>
			<p class="easyui-linkbutton" style="width: 50px;height: 30px" onclick="submit1()">确认</p>
		</div>
	</div>

	<div style="margin-bottom: 5px;">
	<table id="dg" class="easyui-datagrid" title="用户数据库" style="width:100%;height:auto;"
			data-options="
			rownumbers:true,
			toolbar: '#tb',
			singleSelect:true,
			pagination:true,
			method:'get',
			onClickCell: onClickCell,
			onEndEdit: onEndEdit
			">
		<thead>
			<tr>				
				<th data-options="field:'Employee_id',width:100,align:'center'">工号</th>
				
				<th data-options="field:'Name',width:120,align:'center'">姓名</th>				
				
				<th data-options="field:'Department',width:130,align:'center'">部门</th>
				
				<th data-options="field:'User_position',width:150,align:'center'">职位</th>
				
				<th data-options="field:'Now_Exam',width:170,align:'center',
				formatter:function(value,row){
					if (row.Now_Exam == 0)
					{return '暂无考试';}
					else
					{return row.Now_Exam;}
				}">修改考试批次</th>
				
				<th data-options="field:'CK',width:170,align:'center',
				formatter:function(value,row){
					if (row.CK == 1)
					{return '参加本批次';}
					else
					{return '未修改批次';}
				},
				editor:{
					type:'checkbox',
					options:{
						on: '1',
						off:'0',
						required:true
					}
				}
				">是否参加本次考试</th>
								
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="All()">全选</a>	
	</div>
	</div>

	
		
</div>	
	<script type="text/javascript">
	
	var radio = document.getElementsByName("choice");
	var updated;	
	var editIndex = undefined;
	//分页
	(function($){
	function pagerFilter(data){
		if ($.isArray(data)){	// is array
			data = {
				total: data.length,
				rows: data
			}
		}
		var target = this;
		var dg = $(target);
		var state = dg.data('datagrid');
		var opts = dg.datagrid('options');
		if (!state.allRows){
			state.allRows = (data.rows);
		}
		if (!opts.remoteSort && opts.sortName){
			var names = opts.sortName.split(',');
			var orders = opts.sortOrder.split(',');
			state.allRows.sort(function(r1,r2){
				var r = 0;
				for(var i=0; i<names.length; i++){
					var sn = names[i];
					var so = orders[i];
					var col = $(target).datagrid('getColumnOption', sn);
					var sortFunc = col.sorter || function(a,b){
						return a==b ? 0 : (a>b?1:-1);
					};
					r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
					if (r != 0){
						return r;
					}
				}
				return r;
			});
		}
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = state.allRows.slice(start, end);
		return data;
	}

	var loadDataMethod = $.fn.datagrid.methods.loadData;
	var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
	$.extend($.fn.datagrid.methods, {
		clientPaging: function(jq){
			return jq.each(function(){
				var dg = $(this);
 	        	var state = dg.data('datagrid');
				var opts = state.options;
				opts.loadFilter = pagerFilter;
            	var onBeforeLoad = opts.onBeforeLoad;
            	opts.onBeforeLoad = function(param){
                	state.allRows = null;
                	return onBeforeLoad.call(this, param);
            	}
            	var pager = dg.datagrid('getPager');
				pager.pagination({
					onSelectPage:function(pageNum, pageSize){
						opts.pageNumber = pageNum;
						opts.pageSize = pageSize;
						pager.pagination('refresh',{
							pageNumber:pageNum,
							pageSize:pageSize
						});
						dg.datagrid('loadData',state.allRows);
					}
				});
         	   $(this).datagrid('loadData', state.data);
        	    if (opts.url){
        	    	$(this).datagrid('reload');
       	     }
			});
		},
    	loadData: function(jq, data){
        	jq.each(function(){
            	$(this).data('datagrid').allRows = null;
        	});
        	return loadDataMethod.call($.fn.datagrid.methods, jq, data);
    	},
    	deleteRow: function(jq, index){
    		return jq.each(function(){
    			var row = $(this).datagrid('getRows')[index];
    			deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
    			var state = $(this).data('datagrid');
    			if (state.options.loadFilter == pagerFilter){
    				for(var i=0; i<state.allRows.length; i++){
    					if (state.allRows[i] == row){
    						state.allRows.splice(i,1);
    						break;
    					}
    				}
    				$(this).datagrid('loadData', state.allRows);
    			}
    		});
    	},
    	getAllRows: function(jq){
    		return jq.data('datagrid').allRows;
    	}
	})
	})(jQuery);

	$(function(){
		var a = new Object();
		for(var n in DATA)
		{
			a[n] = DATA[n];
		}
		$('#dg').datagrid({data:a}).datagrid('clientPaging');
	});
	//END
	
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				updated = $('#dg').datagrid('getChanges', "updated");
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell(index, field){
			if (ExNum != null){
				if (editIndex != index){
					if (endEditing()){
						$('#dg').datagrid('selectRow', index)
								.datagrid('beginEdit', index);
						var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
						if (ed){
							($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						}
						editIndex = index;
					} else {
						setTimeout(function(){
							$('#dg').datagrid('selectRow', editIndex);
						},0);
					}
				}
			}
			else
			{
				$.messager.alert("提示", "请先选择考试批次号！");
			}
		}
		function onEndEdit(index, row){
			if (DetailFlag = 1){
				a = ExNum;
				row.CK = "1";
				row.Now_Exam = a;
			}
			else{
				var a = row.CK
				if(a == "1")
				{
					a = ExNum;
					row.Now_Exam = a;
				}	
				else
				{
					a = 0;
				}
			}
					
		}
		function All(){
			DetailFlag = 1;
			var detailrow = DATA.total;
			for(i = 0;i < detailrow;i++)
			{
				 onClickCell(i, "CK");
			}
		}
		function accept(){
			if (endEditing()){
				var $dg = $('#dg');
				var rows = $('#dg').datagrid('acceptChanges');
				
				
				 if (rows.length) {
					 var effectRow = new Object();
				
					 effectRow["updated"] = JSON.stringify(updated);
					 
					 
					
				 }
			}
			$.post("../rest/Manage/getUserChange", effectRow, function (rsp) {
				if (rsp) {
					$('#dg').datagrid('acceptChanges');
					
					$.cxDialog.defaults.baseClass = 'ios';
					$.cxDialog({
						title: '提示',
						info: '修改成功！',
						ok: function(){
						
						}
					});
					
				}
			},"JSON").error(function () {
				$.messager.alert("提示", "提交错误了！");
			});
		}
		
		var i,  //计数
			y,  //显示用string
			
			ExamLength;  //考试类型总数
			
		function GetExam(){
			$.ajax({
	   	 		cache: true,
	   		 	type: "GET",
	    		dataType: "json",
	    		url: "../rest/Manage/getAllRecord",   //后台连接代码
	    		async: false,
	    		error: function(request) {
	    	    	alert("失败");
	    		},
	    		success: function(exam) {
	    			Exam = exam;
		    	}
			});
		}
		
		function ShowPici(){
			y = "";
			ExamLength = Exam.rows.length; 
			if(ExamLength == 0){
				alert("请添加考试计划！");
			}
			else{
				for(i=0; i<ExamLength; i++)
				{
					y += "<option value=\"" + Exam.rows[i].Exam_id +"\">考试批次" + Exam.rows[i].Exam_id +"</option>";
				}
			}
			$("#PiCi").html(y);
		}
		
		function submit1(){
			ExNum = document.getElementById("PiCi").value;
		}
		
		GetExam();
		ShowPici();
		
	</script>
</body>
</html>