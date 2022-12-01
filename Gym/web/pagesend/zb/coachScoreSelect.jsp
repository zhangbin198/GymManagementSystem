<%@ page import="com.xhwy.gym.entity.Coach" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>给学员打分</title>
</head>
<!--layUi-->
<script src="../statics/layui/layui.js"></script>
<link rel="stylesheet" href="../statics/layui/css/layui.css">
<script src="../statics/js/jquery.js"></script>
<body>
<%
    Coach coach = (Coach) session.getAttribute("coachs");
    String coachId = coach.getCoachId();
%>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学员编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="memberId" autocomplete="off" class="layui-input" id="memberId"
                                   placeholder="请输入学员编号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学员姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentName" autocomplete="off" class="layui-input"
                                   id="studentName"
                                   placeholder="请输入学员姓名">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">教练编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="coach" autocomplete="off" class="layui-input" id="coach"
                                   placeholder="请输入选择课程编号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学员成绩</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentScore" autocomplete="off" class="layui-input"
                                   id="studentScore"
                                   placeholder="请输入学员成绩">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                lay-filter="data-search-btn" onclick="select()"><i class="layui-icon"></i> 搜 索
                        </button>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
</div>
<table id="demo" lay-filter="test"></table>
<!--查询员工信息js-->
<script>
    layui.use('table', function () {
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            , even: true //开启隔行背景
            , cellMinWidth: 0
            , url: '../../GymCoachScoreSelect.do?coachId=' +<%=coachId%> //数据接口
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , method: 'get'
            , page: true //开启分页
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'memberId', title: '学员编号', sort: true, align: 'center'}
                , {field: 'studentName', title: '学员姓名', sort: true, edit: true, align: 'center'}
                , {field: 'studentSex', title: '学员性别', sort: true, edit: true, align: 'center'}
                , {field: 'coach', title: '学员教练编号', sort: true, edit: true, align: 'center'}
                , {field: 'studentScore', title: '学员成绩', sort: true, edit: true, align: 'center'}
            ]]
        });
        //监听单元格编辑(选中某一个字段直接更改对应的值)
        table.on('edit(test)', function (obj) {
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field; //得到字段
            layer.msg('[ID: ' + data.coach + '] ' + field + ' 字段更改值为：' + value);
            $.ajax({
                url: '../../GymCoachScoreEdit.do?memberId=' + data.memberId + "&studentName=" + data.studentName + "&studentSex=" + data.studentSex + "&coach=" + data.coach + "&studentScore=" + data.studentScore,
                type: 'post',
                dataType: 'text',
                data: {
                    memberId: data.memberId,
                    studentName: data.studentName,
                    studentSex: data.studentSex,
                    coach: data.coach,
                    studentScore: data.studentScore,
                },
                success: function (res) {
                    layer.alert('打分成功!', {icon: 6});
                },
                error: function (res) {
                    layer.alert('打分失败,请检查!', {icon: 5})
                }
            })
        });
    });

    function select() {
        var memberId = $('#memberId').val();
        var studentName = $('#studentName').val();
        var coach = $('#coach').val();
        var studentScore = $('#studentScore').val();
        var table = layui.table;
        table.reload('demo', {//二次加载
            url: '../../GymCoachScoreLikeSelect.do?memberId=' + memberId + "&studentName=" + studentName + "&coach=" + <%=coachId%> +"&studentScore=" + studentScore,
            where: {
                memberId: memberId,
                studentName: studentName,
                coach: <%=coachId%>,
                studentScore: studentScore
            }
        })
    }
</script>
</body>
</html>
