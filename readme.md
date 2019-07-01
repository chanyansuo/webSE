#新增接口，rest/SafetyExam  #
里面存放都是安全考试相关接口，修改时把原本的exam改为SafetyExam
# 考试数据库修改 #
## record表 ##
安全考试认为从10001的id开始
</br>
考试类型为2
## 考试题型表 ##
增加Safety为首的表，这样虽然有冗余表，但是可以方便维护，不用再接口上增加字段


#修改正式考试界面前端代码20190625 #
**修改了代码位置，提取重复JavaScript代码到js文件:**</br>
1. TestActive :test.jsp中需要使用JavaScript代码</br>
2. simulation.jsp中需要使用JavaScript代码</br>
3. simulation_finished.jsp中需要使用JavaScript代码</br>
4. exercise.jsp中需要使用JavaScript代码</br>

# 2019.7.1修改 #
1. 完成安全考试代码修改，修改pds生成，安全考试试卷位置为“D:\SafeDoc”
2. 修改联系模式接口，“Practice/GetSafetyPractice”填空题与简答题的题目都返回null
3. 增加模拟训练考试接口：“Practice/GetSafetyExam”
4. 增加接口“/Manage/SafetyEXdownload”安全试卷下载  <font color="#FF0000">前端需要再个人信息页面增加安全信息相关下载</font> 
## <font color="#FF0000">bug需要修改</font> ##
1. 练习模式：有填空题，简答题选项，这两个要去掉
2. 练习模式：选择题，会有出现undefined选项
3. 模拟训练与前面考试系统一样出现考卷不显示内容

# 2019.7.1修改 - MXY #
1. 完成练习模式前端修改
2. 修改模拟训练接口代码 ISafetyExamMapper Exammapper = session.getMapper(ISafetyExamMapper.class);
3. 修改个人信息页面
## <font color="#FF0000">bug需要修改</font> ##
1. 练习模式下，单选题多选题，选项为空时需发送数据，避免出现undefined选项（保密部分有发送空值）
2. user/getUserInfo 接口中需要增加 发送安全考试成绩，与安全考试时间，与保密考试数据分开显示