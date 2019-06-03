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
	
	<table id="dg" class="easyui-datagrid" title="成绩统计" style="width:100%;height:auto;"
			data-options="
			rownumbers:true,
			toolbar: '#tb',
			singleSelect:true,
			pagination:true,
			method:'get',
			onClickCell: onClickCell
			">
		<thead>
			<tr>
				<th data-options="field:'Employee_id',width:80">员工工号</th>
				
				<th data-options="field:'Name',width:80">员工姓名</th>
				
				<th data-options="field:'Completion_score',width:100">填空题</th>
				
				<th data-options="field:'Judgment_score',width:100">判断题</th>

				<th data-options="field:'Choice_score',width:100">单选题</th>
				
				<th data-options="field:'Mchoice_score',width:100">多选题</th>

				<th data-options="field:'Question_score',width:100,editor:'numberbox'">简答题</th>
				
				<th data-options="field:'Score_sum',width:100,editor:'numberbox'">总分</th>
				
				<th data-options="field:'Float_score',width:100,editor:'numberbox'">调整分数</th>
				
				<th id="NS" data-options="field:'Final_score',width:100,editor:'numberbox',styler:cellStyler">最终总分</th>	
				
				<th data-options="field:'Remark',width:500,editor:'textbox'">备注</th>			
							
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		考试日期：<input class="easyui-datebox" id="d1" style="width:150px">
		至：<input class="easyui-datebox" id="d2" style="width:150px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()">查询</a>
		<input id="batch" class="easyui-combobox" name="batch" style="width:200px;" data-options="valueField: 'id',textField: 'text',labelPosition: 'top',editable:false,panelHeight:'auto'">
		<a href="#" class="easyui-linkbutton" onclick="showSearch()">确认</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存修改</a>
		<button id="bo" class="easyui-linkbutton" type="button" onclick="ExporterExcel();">下载为Excel</button>
	</div>
	
	<script type="text/javascript">
	var editIndex = undefined;
	var updated;
	var score;
	var SCORE;
	var BATCH;
	var y;   //string储存空间
	var a = new Object();
	var bo = true;
	//成绩判定
	function cellStyler(value,row,index){
		if (value < 80){
			return 'background-color:rgba(228, 238, 254, 1);color:red;';
		}
	}
	//END
		
	function turn(){
		if (editIndex != undefined){
			score(index);
		}
		if (bo == false){
			return false;
		}
	}
	
	
	//分页显示
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
// 			自改
			editIndex = undefined;
// 			end
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
		    getAllRows: function(jq){
		    	return jq.data('datagrid').allRows;
		    }
		})
		})(jQuery);

	$(function(){
		
	});
	
//END
	function onEndEdit(index, row){
		
	}	
	function endEditing(){
		if (editIndex == undefined){return true}
		score(editIndex);
		if(bo == true){
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				updated = $('#dg').datagrid('getChanges', "updated");
				editIndex = undefined;
				return true;
			}
			else {
				return false;
			}
		}	
	}
	function onClickCell(index, field){
		if (editIndex != index){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
				if (ed){
					($(ed.target).data('numbertbox') ? $(ed.target).numberbox('numberbox') : $(ed.target)).focus();
				}
				editIndex = index;
			} else {
				setTimeout(function(){
					$('#dg').datagrid('selectRow', editIndex);
				},0);
			}
		}
	}


	//浮动分数增加
	function score(index){
		
		var Score = $('#dg').datagrid('getSelected').Score_sum;
		
		var Float = $('#dg').datagrid('getEditor',{index:index,field:'Float_score'}).target.val();	

		var scoreEditor = $('#dg').datagrid('getEditor',{index:index,field:'Final_score'});
		
		var scoresumEditor = $('#dg').datagrid('getEditor',{index:index,field:'Score_sum'});
		
		var Completion_score = $('#dg').datagrid('getSelected').Completion_score;
		
		var Judgment_score = $('#dg').datagrid('getSelected').Judgment_score;

		var Choice_score = $('#dg').datagrid('getSelected').Choice_score;
		
		var Mchoice_score = $('#dg').datagrid('getSelected').Mchoice_score;

		var Question_score = $('#dg').datagrid('getEditor',{index:index,field:'Question_score'}).target.val();
		
		Score = Completion_score + Judgment_score + Choice_score + Mchoice_score + Question_score*1;

		var NScore = Float*1 + Completion_score + Judgment_score + Choice_score + Mchoice_score + Question_score*1;

		if (NScore > 100 || Score > 100){
			$.cxDialog.defaults.baseClass = 'ios';
			$.cxDialog({
				title: '提示',
				info: '请确认总分！目前总分超过100！',
				ok: function(){
				
				}
			});
			bo = false;
		}else if (NScore < 0 || Score < 0){
			$.cxDialog.defaults.baseClass = 'ios';
			$.cxDialog({
				title: '提示',
				info: '请确认总分！目前总分小于0！',
				ok: function(){
				
				}
			});
			bo = false;
		}else{
			$(scoreEditor.target).numberbox('setValue',NScore);
			$(scoresumEditor.target).numberbox('setValue',Score);
			bo = true;
		}
		
// 		endEditing();
	}

	//浮动分数增加END

	//查找数据传并传送的combobox
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
	function showSearch(){
		$('#dg').datagrid('reload');
		$('#dg').datagrid('loadData',{total:0,rows:[]});
		var batchChoiced = $("#batch").combobox('getText'); 
		if(batchChoiced == ""){
			alert("请先选择考试批次！")
		}
		else{
			$.ajax({                                 //发送考试批次数据
				cache: true,
				type: "POST",
				dataType: "json",
				url: "../rest/Manage/getEmployeeMark",
				data:{"ExamId":batchChoiced},
				async: true,
				error: function(errCode, errMsg) {
					alert("Connection error: "+errCode);
				},
				success: function(data) {
					SCORE = data;
					for(var n in SCORE)
					{
						a[n] = SCORE[n];
					}
					$('#dg').datagrid({data:a}).datagrid('clientPaging');
				}			
			});
		}	
	}

	//保存数据修改
	function accept(){
		if (endEditing()){
			var $dg = $('#dg');
			var rows = $('#dg').datagrid('acceptChanges');
			 if (rows.length) {
				 var effectRow = new Object();
					effectRow["updated"] = JSON.stringify(updated);
			 }
		}
		$.post("../rest/Manage/setFinalScore", effectRow, function (rsp) {
			if (rsp) {
				$dg.datagrid('acceptChanges');
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
	//保存数据修改END

	//导出Excel
		function ExporterExcel() {
            //获取Datagride的列
            var rows = SCORE.rows;
					//$('#dg').datagrid('getData').rows;
            var columns = $("#dg").datagrid("options").columns[0];
            var oXL = new ActiveXObject("Excel.Application"); //创建AX对象excel
            var oWB = oXL.Workbooks.Add(); //获取workbook对象
            var oSheet = oWB.ActiveSheet; //激活当前sheet
            //设置工作薄名称
            oSheet.name = "保密考试简答题库";
            //设置表头
            for (var i = 0; i < columns.length; i++) {
                oSheet.Cells(1, i+1).value = columns[i].title;
            }
            //设置内容部分
            for (var i = 0; i < rows.length; i++) {
                //动态获取每一行每一列的数据值
                for (var j = 0; j < columns.length; j++) {
                    oSheet.Cells(i + 2, j+1).value = rows[i][columns[j].field];
                }
            }
            oXL.Visible = true; //设置excel可见属性
            
        }		
	
	</script>
</body>
</html>