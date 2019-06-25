/**
前端动作函数 
 */

/**
Exercise.html
*/

	//赋值
	function Valuation(){
		Current = 0;
		WorkedNumber = 0;
		currentNum = 0;
		switch (type){
 			case 1:
  				PresentSum = DATA.completion.length;
 				CmpSum =  DATA.completion.length;
 				break;
 			case 2:
 				PresentSum = DATA.judgment.length;
 				TFSum =  DATA.judgment.length;
 				break;
 			case 3:
				PresentSum = DATA.choice.length;
				CSum = DATA.choice.length;
 				break;
 			case 4:
 				PresentSum = DATA.mulchoice.length;
 				MulCSum =  DATA.mulchoice.length;
 				break;
 			case 5:
 				PresentSum = DATA.question.length;
 				QutSum =  DATA.question.length;
 				break;
 		}
		RadioNum = 0;
	}

	//空格替换
	function Replace(value){
		var a = value.replace(/\s/g,"&nbsp;")    //将文本中的空格替换为可被html读取的&nbsp
		return a;
	}

	//题目显示
	function showQuestionType(value){
		Buttonclass(value);     //题目类型设置
		switch (value) {
		case 1:   //填空题输出
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.completion[currentNum].content;     //题目信息获取
			y = "<div class=\"title\"><p id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";    //replace置换空格
			y += "<div id=\"input\"></div>";
			NowCmpN = DATA.completion[currentNum].Blank_num;                    //该题空格数
			$("#Question").html(y);                                           //由于input显示value的原因，输入框在回写代码function中
			showCNumber();
			break;
		case 2:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.judgment[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"1\"/><label>√</label></div>";         //radio
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"0\"/><label>×</label></div>";
			$("#Question").html(y);
			showCNumber();
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
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"radio\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //String.fromCharCode(j+65)转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
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
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"checkbox\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
			break;
		case 5:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.question[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"nav\" style=\"float: left;\"><a class=\"buttom\" onclick=\"Next()\">显示答案</a></div>";
			$("#Question").html(y);
			showCNumber();
			break;
		}
		report();
	}
	
	//回答错误题目显示
	function showQuestionTypeW(value){
		Buttonclass(value);     //题目类型设置
		switch (value) {
		case 1:   //填空题输出
			y = "";
			z = "";
			NowCmpN = DATA.completion[currentNum].Blank_num;                    //该题空格数
			ShowNum = currentNum + 1;
			Question = DATA.completion[currentNum].content;     //题目信息获取
			for(j=1;j<=NowCmpN;j++){
				if(j==1){
					z += DATA.completion[currentNum].Answer_one;
					z += "；";
				}
				if(j==2){
					z += DATA.completion[currentNum].Answer_two;
					z += "；";
				}
				if(j==3){
					z += DATA.completion[currentNum].Answer_three;
					z += "；";
				}
				if(j==4){
					z += DATA.completion[currentNum].Answer_four;
					z += "；";
				}
				if(j==5){
					z += DATA.completion[currentNum].Answer_five;
					z += "；";
				}
				
			}
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";    //replace置换空格
			y += "<div class=\"CorrectA\"><p  id=\"Content\" style=\"padding-left: 30px;\">正确答案：" + z + "</p></div>";      //ajax获得数据
			y += "<div id=\"input\"></div>";
			$("#Question").html(y);                                           //由于input显示value的原因，输入框在回写代码function中
			showCNumber();
			break;
		case 2:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.judgment[currentNum].content;
			y = "<div class=\"title\"><p id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			if(DATA.judgment[currentNum].answer == "1"){
				var A = "√";
			}
			if(DATA.judgment[currentNum].answer == "0"){
				var A = "×";
			}
			y += "<div class=\"CorrectA\"><p id=\"Content\" style=\"padding-left: 30px;\">正确答案：" + A + "</p></div>";
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"1\"/><label>√</label></div>";         //radio
			y += "<div class=\"Answer\"><input id=\"UAnswer\" name=\"choice\" type=\"radio\"  value=\"0\"/><label>×</label></div>";
			$("#Question").html(y);
			showCNumber();
			break;
		case 3:
			RadioNum = 0;   //初始化选项个数
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.choice[currentNum].content;     //题目信息获取
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"CorrectA\"><p  id=\"Content\" style=\"padding-left: 30px;\">正确答案：" + DATA.choice[currentNum].answer + "</p></div>";
			Options[0] = DATA.choice[currentNum].Option_one;
			Options[1] = DATA.choice[currentNum].Option_two;
			Options[2] = DATA.choice[currentNum].Option_three;
			Options[3] = DATA.choice[currentNum].Option_four;
			Options[4] = DATA.choice[currentNum].Option_five;
			Options[5] = DATA.choice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"radio\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //String.fromCharCode(j+65)转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
			break;
		case 4:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.mulchoice[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"CorrectA\"><p  id=\"Content\" style=\"padding-left: 30px;\">正确答案：" + DATA.mulchoice[currentNum].answer + "</p></div>";
			Options[0] =DATA.mulchoice[currentNum].Option_one;
			Options[1] =DATA.mulchoice[currentNum].Option_two;
			Options[2] =DATA.mulchoice[currentNum].Option_three;
			Options[3] =DATA.mulchoice[currentNum].Option_four;
			Options[4] =DATA.mulchoice[currentNum].Option_five;
			Options[5] =DATA.mulchoice[currentNum].Option_six;
			for (j=0;j<6;j++){
				if ( Options[j] != "")
				{
					y += "<div class=\"Answer\"><input name=\"choice\" type=\"checkbox\" value=\""+ String.fromCharCode(j+65) +"\"/><label>" + Options[j] + "</label></div>";   //转码显示英文
					RadioNum++;
				}
			}
			$("#Question").html(y);
			showCNumber();
			break;
		case 5:
			y = "";
			ShowNum = currentNum + 1;
			Question = DATA.question[currentNum].content;
			y = "<div class=\"title\"><p  id=\"Content\">" + ShowNum + "、" + Replace(Question) + "</p></div>";
			y += "<div class=\"Answer\"><textarea id=\"UAnswer\" name=\"Ans\" rows=\"12\" cols=\"20\" readonly=\"readonly\">" + DATA.question[currentNum].answer + "</textarea></div>";
			$("#Question").html(y);
			showCNumber();
			break;
		}
		report();
		isReshow = 0;
	}	
	
	//题目进度显示
	function showCNumber(){
		var z ="";
		var Num;
		var x;
		x = currentNum+1;
		z += "<div class=\"Q_nub\"><p>第<input id=\"input1\" type=\"text\" class=\"skip\" placeholder=\"" + x +"\">题/共";
		z += PresentSum;
		z += "题<button id=\"input2\" class=\"skip1\" onclick=\"Rskip()\">跳转</button>";
		z += "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;正确率：";
		if(WorkedNumber == 0){
			z += "0";                  //正确率值
		}
		else{
			Num = Current/WorkedNumber*100;                  //正确率值
			z += Num.toFixed(1);
		}
		z += "%</p></div>";
		$("#navigation1").html(z);
	}

	//下标跳转
	function Rskip(){
		var Value = document.getElementById("input1").value
		if(Value<=PresentSum && Value>0){
			skip(type,Number(Value)-1);
		}
		else{
			$.cxDialog.defaults.baseClass = 'ios';
			$.cxDialog({
				title: '提示',
				info: '题号超出范围!',
				ok: function(){
				}
			});
		}
	}
	
	
	//题目跳转
	function skip(Stype,SNum){
		currentNum = SNum;
		var C;
		if(Stype == type){
			switch (type){
				case 1:
					C = DATA.completion[currentNum].itemStatus;
					break;
				case 2:
					C = DATA.judgment[currentNum].itemStatus;
					break;
				case 3:
					C = DATA.choice[currentNum].itemStatus;
					break;
				case 4:
					C = DATA.mulchoice[currentNum].itemStatus;
					break;
				case 5:
					C = DATA.question[currentNum].itemStatus;
					break;
			}
			if(C==0){
				showQuestionType(Stype);
			}
			
			if(C==3){
				showQuestionTypeW(Stype);		
			}
		}
		else{
			type = Stype;
			GetData(Stype);
			Valuation(Stype);
			showQuestionType(Stype);
		}
	}

	//答案处理
	function AnswerDispose(){
		answer="";   //初始化answer
		var a;  //单个空格答案
		if (type == 1)
		{
			for(j=0;j<NowCmpN;j++)
			{
				a = document.getElementById("UAnswer"+(j+1)).value;
				a = a.substring(0,50);
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
			answer = "";
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
					if(radio[j].checked)	{
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
		document.getElementById("Bcmp").setAttribute("class","buttom");
		document.getElementById("Btf").setAttribute("class","buttom");
		document.getElementById("Bchoice").setAttribute("class","buttom");
		document.getElementById("Bmulchoice").setAttribute("class","buttom");
		document.getElementById("Bq").setAttribute("class","buttom");
		switch (value){
		case 1:
		document.getElementById("Bcmp").setAttribute("class","activebuttom");
		break;
		case 2:
		document.getElementById("Btf").setAttribute("class","activebuttom");
		break;
		case 3:
		document.getElementById("Bchoice").setAttribute("class","activebuttom");
		break;
		case 4:
		document.getElementById("Bmulchoice").setAttribute("class","activebuttom");
		break;
		case 5:
		document.getElementById("Bq").setAttribute("class","activebuttom");
		break;
		}
	}

	//重新开始按钮
	function restart(){
		GetData(type);
		skip(type,0);
	};

	//下一题按钮
	function Next(){
		var xtype= type;   //临时变量 ，（type替代用）
		var VSum = currentNum;
		switch (type){
			case 1:
				C = DATA.completion[currentNum].itemStatus;
				break;
			case 2:
				C = DATA.judgment[currentNum].itemStatus;
				break;
			case 3:
				C = DATA.choice[currentNum].itemStatus;
				break;
			case 4:
				C = DATA.mulchoice[currentNum].itemStatus;
				break;
			case 5:
				C = DATA.question[currentNum].itemStatus;
				break;
		}
		if(C==0){
			Correct();
			if(isReshow == 0){
				VSum++;	
			}
		}
		else{
			if(VSum+1>PresentSum-1){
				$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '已经是最后一道题了!',
					ok: function(){
					}
				});
			}
			else{
				VSum++;
			}
		}
		skip(xtype,VSum);
	}

	function key(){
    	var e = window.event || event;
    	if(e.keyCode == 13)
    		Next();
    }
	
	//上一题按钮
	function Up(){
		var xtype;   //临时变量 ，（type替代用）
		var VSum = currentNum;
		xtype = type;
		switch (type){
			case 1:
				C = DATA.completion[currentNum].itemStatus;
				break;
			case 2:
				C = DATA.judgment[currentNum].itemStatus;
				break;
			case 3:
				C = DATA.choice[currentNum].itemStatus;
				break;
			case 4:
				C = DATA.mulchoice[currentNum].itemStatus;
				break;
			case 5:
				C = DATA.question[currentNum].itemStatus;
				break;	
		}
		if(C==0){
			Correct();
			if(isReshow == 0){
				VSum--;	
			}
		}
		else{
			if(VSum-1<0){
				$.cxDialog.defaults.baseClass = 'ios';
				$.cxDialog({
					title: '提示',
					info: '已经是第一道题了!',
					ok: function(){
					}
				});
			}
			else{
				VSum--;
			}
		}
		skip(xtype,VSum);
	}

	//随机显示按钮
	function Random(){
		if (isRandom == 0){
			isRandom = 1;
			document.getElementById("RD").setAttribute("class","activebuttom");
			$("#RD").html("随机模式");
			GetData(type);
			skip(type,0);
		}
		else{
			isRandom = 0;
			document.getElementById("RD").setAttribute("class","buttom");
			$("#RD").html("顺序模式");
			GetData(type);
			skip(type,0);
		}
	}


	//答案回写
	function report(){
		var returnAnswer;    //回写答案
		var itemStatus;   //题目状态
		switch (type){
			case 1:
				returnAnswer = DATA.completion[currentNum].userAnswer;
				if(returnAnswer != ""){
					var strs = new Array();
					strs = returnAnswer.split("$%")
					y = "";
					for(j=1;j<=NowCmpN;j++){
						y += "<div class=\"Answer\"><input autoComplete=\"off\" id=\"UAnswer" + j + "\" name=\"CmpAnswer" + j + "\" type=\"text\" value= " + strs[j-1] + "></div>";
					}
					$("#input").html(y);
				}
				else{
					y = "";
					for (j = 1;j <= NowCmpN;j++){
						y += "<div class=\"Answer\"><input autoComplete=\"off\" id=\"UAnswer" + j + "\" name=\"CmpAnswer" + j + "\" type=\"text\"></div>"
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
				}
				break;
		}
	}

	//答案正确性判断，回发isReshow 1 0 
	function Correct(){
		AnswerDispose();
		switch (type){
			case 1:
				if(DATA.completion[currentNum].itemStatus == 0){
					accept(answer);
					var temp = JSON.stringify(DATA.completion[currentNum]);
					$.ajax({
						cache: true,
						type: "POST",
						dataType: "json",
						url: "../rest/Practice/CheckAnswer",   //后台连接代码
						data:{"temp":temp},
						async: false,
						error: function(request) {
							alert("生成考卷失败");
						},
						success: function(data) {
							if (data==false) {//传过来是字符型，但只用用boolen判断
								DATA.completion[currentNum].itemStatus = 3;  //正确为2
								isReshow = 1;
								return false;
							}
							else{
								DATA.completion[currentNum].itemStatus = 2;  //正确为2
								Current++;
								isReshow = 0;
							}
						}
					});
					WorkedNumber++;
				}
			break;
		case 2:
			if(DATA.judgment[currentNum].itemStatus == 0){
				accept(answer);
				if(answer == DATA.judgment[currentNum].answer){
					DATA.judgment[currentNum].itemStatus =2;
					Current++;
					isReshow = 0;
				}else{
					DATA.judgment[currentNum].itemStatus =3;
					isReshow = 1;
				}
				WorkedNumber++;
			}
			break;
		case 3:
			if(DATA.choice[currentNum].itemStatus == 0){
				accept(answer);
				if(answer == DATA.choice[currentNum].answer){
					DATA.choice[currentNum].itemStatus =2;
					Current++;
					isReshow = 0;
				}else{
					DATA.choice[currentNum].itemStatus =3;
					isReshow = 1;
				}
				WorkedNumber++;
			}
			break;
		case 4:
			if(DATA.mulchoice[currentNum].itemStatus == 0){
				accept(answer);
				if(answer == DATA.mulchoice[currentNum].answer){
					DATA.mulchoice[currentNum].itemStatus =2;
					Current++;
					isReshow = 0;
				}else{
					DATA.mulchoice[currentNum].itemStatus =3;
					isReshow = 1;
				}
				WorkedNumber++;
			}
			break;
		case 5:
			if(DATA.question[currentNum].itemStatus == 0){
				DATA.question[currentNum].itemStatus =3;
				isReshow = 1;
			}
			Current++;
			WorkedNumber++;
			break;
		}
	}


	//数据传递
	function accept(value){
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
		}
	}
	
	function no(){
		$.cxDialog.defaults.baseClass = 'ios';
		$.cxDialog({
			title: '提示',
			info: '暂未开启！',
			ok: function(){
			}
		});
	}

	