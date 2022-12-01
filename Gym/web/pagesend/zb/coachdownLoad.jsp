<%@ page import="com.xhwy.gym.entity.Coach" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教练下载&查看学生作品</title>
</head>
<!--layUi-->
<script src="../statics/layui/layui.js"></script>
<link rel="stylesheet" href="../statics/layui/css/layui.css">
<script src="../statics/js/jquery.js"></script>
<body>
<%
    Coach coach = (Coach) session.getAttribute("coachs");
    //取到教练的编号
    String coachId = coach.getCoachId();
%>
<script>
    layui.use('table', function () {
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            , even: true //开启隔行背景
            , cellMinWidth: 0
            , url: '../../GymgetStuUpload.do?coachId=' +<%=coachId%> //数据接口
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , method: 'post'
            , page: true //开启分页
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'memberId', title: '学员编号', sort: true, align: 'center'}
                , {field: 'studentName', title: '学员姓名', sort: true, edit: true, align: 'center'}
                , {field: 'studentSex', title: '学员性别', sort: true, edit: true, align: 'center'}
                , {field: 'studentName', title: '学员选课', sort: true, edit: true, align: 'center'}
                , {field: 'studentSex', title: '学员作品', sort: true, edit: true, align: 'center'}
                , {field: 'right', title: '操作', width: 180, toolbar: '#barDemo', align: 'center'}
            ]]
        });
        //监听行事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            //实现删除
            if (obj.event === 'coachDownLoad') {
                layer.confirm('确定下载吗?', {icon: 3, title: '提示'}, function (index) {
                    window.location = data.src
                });
                layer.close(index);
            }
        });
    });
</script>
<table id="demo" lay-filter="test"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="coachDownLoad">下载</a>
</script>
</body>
</html>
