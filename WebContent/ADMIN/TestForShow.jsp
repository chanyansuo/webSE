    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>后台管理界面-成绩统计题库</title>
        <!--bootstarp-css-->
        <link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
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
        <th data-options="field:'answer',width:80">Item ID</th>
        <th data-options="field:'productid',width:100">Product</th>
        <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
        <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
        <th data-options="field:'attr1',width:250">Attribute</th>
        <th data-options="field:'status',width:60,align:'center'">Status</th>
        </tr>
        </thead>
        </table>
        <!--login strip end here-->
        </body>
        </html>
        <script language="javascript" type="text/javascript">
        //全局变量
        var DATA,              //全部数据
            DATA1;
        var editIndex = undefined;
        var updated;
        var score;
        var BATCH;

        //连接数据库
        function a(){
            $.ajax({
            cache: true,
            type: "GET",
            dataType: "json",
            url: "../rest/Exam/getExam",   //后台连接代码
            async: false,
            error: function(request) {
            alert("生成考卷失败");
            },
            success: function(data) {
            DATA = data;
            DATA1 = data.choice;
            }
            });
        return DATA1;
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
        $.ajax({
        cache: true,
        type: "GET",
        dataType: "json",
        url:"../rest/Exam/getExam",
        async: true,
        success: function(data) {
        $('#dg').datagrid({data:data}).datagrid('clientPaging');
        }
        });

        });
        //END

        function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#dg').datagrid('validateRow', editIndex)){
        if(score(editIndex)){

        $('#dg').datagrid('endEdit', editIndex);
        updated = $('#dg').datagrid('getChanges', "updated");
        editIndex = undefined;
        return true;
        }
        } else {
        return false;
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
        </script>