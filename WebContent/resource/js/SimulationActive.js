/**
前端动作函数 
 */

/**
Simulation.html
*/

//赋值
	function Valuation(){
		User = window.sessionStorage.getItem("login_user");
		CSum = DATA.choice.length;
		MulCSum =  DATA.mulchoice.length;
		TFSum =  DATA.judgment.length;
		CmpSum =  DATA.completion.length;
		QutSum =  DATA.question.length;
		Sum = CSum + MulCSum + TFSum + CmpSum + QutSum;
		RadioNum = 0;
		if(CmpSum != 0){
			type = 1;
		}
		else if(TFSum != 0){
			type = 2;
		}
		else if(CSum!= 0){
			type = 3;
		}
		else if(MulCSum!= 0){
			type = 4;
		}
		else{
			type = 5;
		}
		DATA.completion[(type-1)].itemStatus = 4;
		c = 2700;
		$("#timepiece").html(formatSeconds(c));    //将c的值显示为时间形式，考试限时
	}

	//空格替换
	function Replace(value){
		var a = value.replace(/\s/g,"&nbsp;")    //将文本中的空格替换为可被html读取的&nbsp
		return a;
	}

	//题目显示
	function showQuestionType(value){
		Buttonclass(value);     //题目类型设置
		Numberclass(value);     //下框样式设置
		switch (value) {
		case 1:   //填空题输出
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.completion[currentNum].content;     //题目信息获取
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";    //replace置换空格
			y += "<div id=\"input\"></div>";
			NowCmpN = DATA.completion[currentNum].Blank_num;                    //该题空格数
			$("#Question").html(y);                                           //由于input显示value的原因，输入框在回写代码function中
			break;
		case 2:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.judgment[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"1\"/><label>√</label></div>";         //radio
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"0\"/><label>×</label></div>";
			$("#Question").html(y);
			break;
		case 3:
			RadioNum = 0;   //初始化选项个数
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.choice[currentNum].content;     //题目信息获取
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			Options[0] = DATA.choice[currentNum].Option_one;
			Options[1] = DATA.choice[currentNum].Option_two;
			Options[2] = DATA.choice[currentNum].Option_three;
			Options[3] = DATA.choice[currentNum].Option_four;
			Options[4] = DATA.choice[currentNum].Option_five;
			Options[5] = DATA.choice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "" && typeof(Options[j]) != "undefined")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"radio\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //String.fromCharCode(j+65)转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			break;
		case 4:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.mulchoice[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			Options[0] =DATA.mulchoice[currentNum].Option_one;
			Options[1] =DATA.mulchoice[currentNum].Option_two;
			Options[2] =DATA.mulchoice[currentNum].Option_three;
			Options[3] =DATA.mulchoice[currentNum].Option_four;
			Options[4] =DATA.mulchoice[currentNum].Option_five;
			Options[5] =DATA.mulchoice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "" && typeof(Options[j]) != "undefined")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"checkbox\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			break;
		case 5:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.question[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"Answer\"><textarea id=\"UAnswer\" name=\"Ans\" rows=\"12\" cols=\"20\"></textarea></div>";
			$("#Question").html(y);
			break;
		}
		report();
	}

	//下方矩形题号显示
	function Number(){
		var x="";     //字符串空间
		if(CmpSum != 0 && typeof(CmpSum) != "undefined")
		{
			x="<div style=\"margin-left: 48px;\">";    //清空字符串空间
			for(j=1;j<=CmpSum;j++){
				x+="<div id=\"Cmp" + j + "\" class=\"nub\"><a href=# class=\"\" onclick=skip(1,"+(j-1)+")>" + j + "</a></div>";
			}
			x+="</div>"
			$("#CmpDIV").html("<div class=\"choice-title\"><p id=\"Cmp\">填空题</p></div>" + x);
		}
		if(TFSum != 0 && typeof(TFSum) != "undefined")
		{
			x="<div style=\"margin-left: 48px;\">";
			for(j=1;j<=TFSum;j++){
				x+="<div id=\"TF" + j + "\" class=\"nub\"><a href=# class=\"\" onclick=skip(2,"+(j-1)+")>" + j + "</a></div>";
			}
			x+="</div>"
			$("#TFDIV").html("<div class=\"choice-title\"><p id=\"TF\">判断题</p></div>" + x);
		}
		if(CSum != 0 && typeof(CSum) != "undefined")
		{
			x="<div style=\"margin-left: 48px;\">";
			for(j=1;j<=CSum;j++){
				x+="<div id=\"choice" + j + "\" class=\"nub\"><a href=# class=\"\" onclick=skip(3,"+(j-1)+")>" + j + "</a></div>";
			}
			x+="</div>"
			$("#choiceDIV").html("<div class=\"choice-title\"><p id=\"choice\">单选题</p></div>" + x);
		}
		if(MulCSum != 0 && typeof(MulCSum) != "undefined")
		{
			x="<div style=\"margin-left: 48px;\">";
			for(j=1;j<=MulCSum;j++){
				x+="<div id=\"Mulchoice" + j + "\" class=\"nub\"><a href=# class=\"\" onclick=skip(4,"+(j-1)+")>" + j + "</a></div>";
			}
			x+="</div>"
			$("#mulchoiceDIV").html("<div class=\"choice-title\"><p id=\"Mulchoice\">多选题</p></div>" + x);
		}
		if(QutSum != 0 && typeof(QutSum) != "undefined")
		{
			x="<div style=\"margin-left: 48px;\">";
			for(j=1;j<=QutSum;j++){
				x+="<div id=\"Qut" + j + "\" class=\"nub\"><a href=# class=\"\" onclick=skip(5,"+(j-1)+")>" + j + "</a></div>";
			}
			x+="</div>"
			$("#QutDIV").html("<div class=\"choice-title\"><p id=\"Qut\">简答题</p></div>" + x);
		}
	}

	//矩形按钮跳转
	function skip(Stype,SNum){
		answer = AnswerDispose();
		if (answer != ""&&answer != " $%"&&answer != " $% $%"&&answer != " $% $% $%"&&answer != " $% $% $% $%"&&answer != " $% $% $% $% $%")
		{
			accept(answer);
			switch (type)                                //1状态为题目已经完成
			{
			case 1:
				DATA.completion[currentNum].itemStatus = 1;
				break;
			case 2:
				DATA.judgment[currentNum].itemStatus = 1;
				break;
			case 3:
				DATA.choice[currentNum].itemStatus = 1;
				break;
			case 4:
				DATA.mulchoice[currentNum].itemStatus = 1;
				break;
			case 5:
				DATA.question[currentNum].itemStatus = 1;
				break;
			}
		}
		else{
			switch (type)                           //题目未完成
			{
			case 1:
				DATA.completion[currentNum].itemStatus = 0;
				break;
			case 2:
				DATA.judgment[currentNum].itemStatus = 0;
				break;
			case 3:
				DATA.choice[currentNum].itemStatus = 0;
				break;
			case 4:
				DATA.mulchoice[currentNum].itemStatus = 0;
				break;
			case 5:
				DATA.question[currentNum].itemStatus = 0;
				break;
			}
		}
		currentNum = SNum;
		type = Stype;
		switch (Stype)                           //题目被选中
		{
			case 1:
				DATA.completion[SNum].itemStatus = 4;
				break;
			case 2:
				DATA.judgment[SNum].itemStatus = 4;
				break;
			case 3:
				DATA.choice[SNum].itemStatus = 4;
				break;
			case 4:
				DATA.mulchoice[SNum].itemStatus = 4;
				break;
			case 5:
				DATA.question[SNum].itemStatus = 4;
				break;
		}
		showQuestionType(Stype);

	}

	//答案处理
	function AnswerDispose(){
		answer="";   //初始化answer
		var a=" ";  //单个空格答案
		if (type == 1)
		{
			for(j=0;j<NowCmpN;j++)
			{
				a = document.getElementById("UAnswer"+(j+1)).value;
				a = a.substring(0,50);
// 				if(a !="")
// 				{answer+=a + "$%";}
				if(a ==""){
					a=" ";
					answer+=a + "$%";
				}
				else{
					answer+=a + "$%";
				}
			}
			return answer;
		}
		else if(type == 5){
			answer = document.getElementById("UAnswer").value;
			answer = answer.substring(0,1000);
			return answer;
		}
		else if(type == 2 || type == 3)
		{
			var radio;    //目前题目选项信息
			var checked = false;  //是否被选中判定
			radio = document.getElementsByName("choice");
			checked = false;
			for(j=0;j<radio.length;j++)
			{
				checked = checked||radio[j].checked;
			}
			if (!checked)
			{
				return answer;
			}
			else{
				for(j=0;j<radio.length;j++)
				{
					if(radio[j].checked){
						answer = radio[j].value;
					}
				}
				return answer;
			}
		}
		else if(type == 4){
			var checkbox;    //目前题目选项信息
			var checked = false;  //是否被选中判定
			checkbox = document.getElementsByName("choice");
			checked = false;
			for(j=0;j<checkbox.length;j++)
			{
				checked = checked||checkbox[j].checked;
			}
			if (!checked)
			{
				return answer;
			}
			else{
				for(j=0;j<checkbox.length;j++)
				{
					if(checkbox[j].checked)	{
					answer += checkbox[j].value;
				}
			}
			return answer;
			}
		}
	}

	//题目类型样式变化
	function Buttonclass(value){
		document.getElementById("Bcmp").setAttribute("class","tbuttom");
		document.getElementById("Btf").setAttribute("class","tbuttom");
		document.getElementById("Bchoice").setAttribute("class","tbuttom");
		document.getElementById("Bmulchoice").setAttribute("class","tbuttom");
		document.getElementById("Bq").setAttribute("class","tbuttom");
		switch (value){
		case 1:
		document.getElementById("Bcmp").setAttribute("class","tactivebuttom");
		break;
		case 2:
		document.getElementById("Btf").setAttribute("class","tactivebuttom");
		break;
		case 3:
		document.getElementById("Bchoice").setAttribute("class","tactivebuttom");
		break;
		case 4:
		document.getElementById("Bmulchoice").setAttribute("class","tactivebuttom");
		break;
		case 5:
		document.getElementById("Bq").setAttribute("class","tactivebuttom");
		break;
		}
	}

	//题目类型样式变化
	function Numberclass(){
		var a;     //存储用空间
		var i;    //循环变量2
		var x = new Array();
		x[0] = "normal";
		x[1] = "finish";
		x[2] = "correct";
		x[3] = "worry";
		x[4] = "choiced";
		if(CmpSum != 0)
		{
			for(i=0;i<5;i++)
			{
				Nubarray = new Array();
				for(j=0;j<CmpSum;j++)
				{
					Nubarray[j] = DATA.completion[j].itemStatus;
					if(Nubarray[j] == i){
						a = document.getElementById("Cmp"+(j+1)).childNodes;
						a[0].setAttribute("class",x[i]);
					}
				}
			}
		}
		if(TFSum != 0)
		{
			for(i=0;i<5;i++)
			{
				Nubarray = new Array();
				for(j=0;j<TFSum;j++)
				{
					Nubarray[j] = DATA.judgment[j].itemStatus;
					if(Nubarray[j] == i){
						a = document.getElementById("TF"+(j+1)).childNodes;
						a[0].setAttribute("class",x[i]);
					}
				}
			}
		}
		if(CSum != 0)
		{
			for(i=0;i<5;i++)
			{
				Nubarray = new Array();
				for(j=0;j<CSum;j++)
				{
					Nubarray[j] = DATA.choice[j].itemStatus;
					if(Nubarray[j] == i){
						a = document.getElementById("choice"+(j+1)).childNodes;
						a[0].setAttribute("class",x[i]);
					}
				}
			}
		}
		if(MulCSum != 0)
		{
		for(i=0;i<5;i++)
		{
			Nubarray = new Array();
			for(j=0;j<MulCSum;j++)
			{
				Nubarray[j] = DATA.mulchoice[j].itemStatus;
				if(Nubarray[j] == i){
					a = document.getElementById("Mulchoice"+(j+1)).childNodes;
					a[0].setAttribute("class",x[i]);
				}
			}
		}
		}
		if(QutSum != 0)
		{
		for(i=0;i<5;i++)
		{
			Nubarray = new Array();
			for(j=0;j<QutSum;j++)
			{
				Nubarray[j] = DATA.question[j].itemStatus;
				if(Nubarray[j] == i){
					a = document.getElementById("Qut"+(j+1)).childNodes;
					a[0].setAttribute("class",x[i]);
				}
			}
		}
		}
	}

	function handinAttention(){
		var n = 0;
		for(j=0;j<CmpSum;j++){
			if(DATA.completion[j].itemStatus == 0)
			{
				n++;
			}
		}
		for(j=0;j<TFSum;j++){
			if(DATA.judgment[j].itemStatus == 0)
			{
				n++;
			}
		}
		for(j=0;j<CSum;j++){
			if(DATA.choice[j].itemStatus == 0)
			{
				n++;
			}
		}
		for(j=0;j<MulCSum;j++){
			if(DATA.mulchoice[j].itemStatus == 0)
			{
				n++;
			}
		}
		for(j=0;j<QutSum;j++){
			if(DATA.question[j].itemStatus == 0)
			{
				n++;
			}
		}
		$.cxDialog.defaults.baseClass = 'ios';
		$.cxDialog({
			title: '信息：',
			info: '还有' + n + '题未完成！是否交卷！',
			ok: function(){
				handin();
			}
			,
			okText: '确认',
			no: function(){
			}
			,
			noText: '取消'
		});
	}

	//下一题按钮
	function Next(){
		var SumType=new Array();
		var Sum;   //临时变量，（下一题在对应题型中的序号 xxxx[Sum].
		var xtype;   //临时变量 ，（type替代用）
		SumType[1]=CmpSum ;
		SumType[2]=TFSum ;
		SumType[3]=CSum ;
		SumType[4]=MulCSum;
		SumType[5]=QutSum;
		Sum = currentNum;
		xtype = type;
		if(Sum+1>SumType[type]-1){
			if(xtype==5){
				$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '已经是最后一道题了！',
					ok: function(){
					}
				});
			}
			for(var tempType=xtype+1;tempType<=5;tempType++){
				if(SumType[tempType]!=""&&typeof(SumType[tempType])!="undefined"){
					xtype=tempType;
					Sum=0;
					//数据提交未写
					skip(xtype,Sum);
					break;
				}
				if(tempType==5){
					$.cxDialog.defaults.baseClass = 'ios';
    				$.cxDialog({
    					title: '提示',
    					info: '已经是最后一道题了！',
    					ok: function(){
    					}
    				});
				}
			}
		}
		else{
			Sum++;
			skip(xtype,Sum);
		}

	}

	//上一题按钮
	function Up(){
		var SumType=new Array();
		var Sum;   //临时变量，（下一题在对应题型中的序号 xxxx[Sum].
		var xtype;   //临时变量 ，（全局变量type替代用）
		SumType[1]=CmpSum ;
		SumType[2]=TFSum ;
		SumType[3]=CSum ;
		SumType[4]=MulCSum;
		SumType[5]=QutSum;
		Sum = currentNum;
		xtype = type;
		if(currentNum-1<0){
			if(xtype==1){
				$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '现在已在第一道题！',
					ok: function(){
					}
				});
			}
			for(var tempType=xtype-1;tempType>0;tempType--){
				if(SumType[tempType]!=""&&typeof(SumType[tempType])!="undefined"){
					xtype=tempType;
					Sum=SumType[xtype]-1;
					//数据提交未写
					skip(xtype,Sum);
					break;
				}
				if(tempType==1){
					$.cxDialog.defaults.baseClass = 'ios';
					$.cxDialog({
						title: '提示',
						info: '现在已在第一道题！',
						ok: function(){
						}
					});
				}
			}
		}
		else{
			Sum--;
			skip(xtype,Sum);
		}

	}
	
	//答案回写
	function report(){
		var returnAnswer="";    //回写答案
		var itemStatus;   //题目状态
		switch (type){
			case 1:
				returnAnswer = DATA.completion[currentNum].userAnswer;
				if(returnAnswer != ""){
					var strs = new Array();
					strs = returnAnswer.split("$%")
					y = "";
					for(j=1;j<=NowCmpN;j++){
						y += "<div class=\"Answer\"><input autoComplete=\"off\" id=\"UAnswer" + j + "\" name=\"CmpAnswer" + j + "\" type=\"text\" value= " + strs[j-1] + " ></div>";
					}
					$("#input").html(y);
				}
				else{
					y = "";
					for (j = 1;j <= NowCmpN;j++){
						y += "<div class=\"Answer\"><input autoComplete=\"off\" id=\"UAnswer" + j + "\" name=\"CmpAnswer" + j + "\" type=\"text\" /></div>"
					}
					$("#input").html(y);
				}

				break;
			case 2:
				returnAnswer = DATA.judgment[currentNum].userAnswer;
				if(returnAnswer != ""){
					if(returnAnswer == 1){
						var choice = document.getElementsByName("choice");
						choice[0].checked = "checked";
					}
					else{
						var choice = document.getElementsByName("choice");
						choice[1].checked = "checked";
					}
				}
				break;
			case 3:
				returnAnswer = DATA.choice[currentNum].userAnswer;
				if(returnAnswer != ""){
					if(returnAnswer.indexOf("A") != -1){
						var choice = document.getElementsByName("choice");
						choice[0].checked = "checked";
					}
					else if(returnAnswer.indexOf("B") != -1){
						var choice = document.getElementsByName("choice");
						choice[1].checked = "checked";
					}
					else if(returnAnswer.indexOf("C") != -1){
						var choice = document.getElementsByName("choice");
						choice[2].checked = "checked";
					}
					else if(returnAnswer.indexOf("D") != -1){
						var choice = document.getElementsByName("choice");
						choice[3].checked = "checked";
					}
				}
				break;
			case 4:
				returnAnswer = DATA.mulchoice[currentNum].userAnswer;
				if(returnAnswer != ""){
					if(returnAnswer.indexOf("A") != -1){
						var choice = document.getElementsByName("choice");
						choice[0].checked = "checked";
					}
					if(returnAnswer.indexOf("B") != -1){
						var choice = document.getElementsByName("choice");
						choice[1].checked = "checked";
					}
					if(returnAnswer.indexOf("C") != -1){
						var choice = document.getElementsByName("choice");
						choice[2].checked = "checked";
					}
					if(returnAnswer.indexOf("D") != -1){
						var choice = document.getElementsByName("choice");
						choice[3].checked = "checked";
					}
					if(returnAnswer.indexOf("E") != -1){
						var choice = document.getElementsByName("choice");
						choice[4].checked = "checked";
					}
					if(returnAnswer.indexOf("F") != -1){
						var choice = document.getElementsByName("choice");
						choice[5].checked = "checked";
					}
				}
				break;
			case 5:
				returnAnswer = DATA.question[currentNum].userAnswer;
				if(returnAnswer != ""){
					$("#UAnswer").html(returnAnswer);
				}
				break;
		}
	}

	//计时器
	function timedCount() {
		t = setInterval(function(){
			$("#timepiece").html(formatSeconds(c));
			c=c-1;
			if(c == -2){
				handin();
			}
		}, 1000);
	}

	function stopCount() {
		clearInterval(t);
	}

	//时间计算
	function formatSeconds(value) {
		var theTime = parseInt(value);// 秒
		var rvsecond ;//秒
		var theTime1 = 0;// 分
		var rvminute ; //分
		var theTime2 = 0;// 小时
		var  rvhour ; //时
		if(theTime > 60) {
			theTime1 = parseInt(theTime/60);
			theTime = parseInt(theTime%60);
			if(theTime1 > 60) {
				theTime2 = parseInt(theTime1/60);
				theTime1 = parseInt(theTime1%60);
			}
		}
		if(theTime<10){
			//alert("小于9："+parseInt(theTime));
			rvsecond =":0"+parseInt(theTime);
		}else{
			//alert("大于9："+parseInt(theTime));
			rvsecond =":"+parseInt(theTime);
		}
		if(theTime1 > 0) {
			if(theTime1<10){
				rvminute = ":0"+parseInt(theTime1);
			}else{
				rvminute = ":"+parseInt(theTime1);
			}
		}else{
			rvminute =":00";
		}
		if(theTime2 > 0) {
			if(theTime1 <10){
				rvhour = "0"+parseInt(theTime2);
			}else{
				rvhour = parseInt(theTime1);
			}
		}else{
			rvhour ="00";
		}
		var result =rvhour+rvminute+rvsecond;
		return result;
	}






	//数据传递
	function accept(value){

		$.ajax({
			cache: true,
			type: "POST",
			dataType: "json",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			url: "../rest/Practice/getuserAnswer",
			data:{"userid":User, "type":type, "currentNum":currentNum,"userAnswer":value},
			async: false,
			error: function(errCode, errMsg) {
				alert("Connection error: "+errCode);
			}
		});

		//alert(value);
		switch (type){
			case 1:
				DATA.completion[currentNum].userAnswer = value;
				break;
			case 2:
				DATA.judgment[currentNum].userAnswer = value;
				break;
			case 3:
				DATA.choice[currentNum].userAnswer = value;
				break;
			case 4:
				DATA.mulchoice[currentNum].userAnswer = value;
				break;
			case 5:
				DATA.question[currentNum].userAnswer = value;
				break;
		}
	}




