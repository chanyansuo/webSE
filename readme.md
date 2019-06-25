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
修改了代码位置，提取重复JavaScript代码到js文件
## TestActive ##
test.jsp中需要使用JavaScript代码

## SimulationActive ##
simulation.jsp中需要使用JavaScript代码

## SimulationFinishActive ##
simulation_finished.jsp中需要使用JavaScript代码

## ExerciseActive ##
exercise.jsp中需要使用JavaScript代码