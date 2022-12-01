<%@ page import="com.xhwy.gym.entity.MemberAll" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生选课和退选</title>
</head>
<!--layUi-->
<script src="../statics/layui/layui.js"></script>
<link rel="stylesheet" href="../statics/layui/css/layui.css">
<script src="../statics/js/jquery.js"></script>
<body>
<%
    MemberAll member = (MemberAll) session.getAttribute("members");
    System.out.println(member);
    String memberId = member.getMemberId();
%>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="classId" autocomplete="off" class="layui-input" id="classId"
                                   placeholder="请输入课程编号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">课程名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="className" autocomplete="off" class="layui-input" id="className"
                                   placeholder="请输入课程名称">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">开课时间</label>
                        <div class="layui-input-inline">
                            <input type="text" name="classBegin" autocomplete="off" class="layui-input" id="classBegin"
                                   placeholder="请输入开课时间">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">教练</label>
                        <div class="layui-input-inline">
                            <input type="text" name="coach" autocomplete="off" class="layui-input" id="coach"
                                   placeholder="请输入教练">
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
<script type="text/html" id="barDemo">
    <button class="layui-btn layui-btn-xs" lay-event="choose">选课</button>
    <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">退选</button>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            , even: true //开启隔行背景
            , cellMinWidth: 0
            , id: 'testReload'
            , url: '../../GymClassTableSelectServlet.do' //数据接口
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
                , {field: 'classId', title: '课程编号', sort: true, align: 'center'}
                , {field: 'className', title: '课程名称', sort: true, edit: true, align: 'center'}
                , {field: 'classSrc', title: '课程文件', sort: true, edit: true, align: 'center'}
                , {field: 'classBegin', title: '开课时间', sort: true, edit: true, align: 'center'}
                , {field: 'classTime', title: '课程时长', sort: true, edit: true, align: 'center'}
                , {field: 'coach', title: '教练编号', sort: true, edit: true, align: 'center'}
                , {field: 'right', title: '操作', width: 180, toolbar: '#barDemo', align: 'center'}
            ]]
        });
        //监听行事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //实现选择课程
            if (obj.event === 'choose') {
                layer.confirm('确定选择这个课程吗进行训练吗?', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        type: 'post',
                        url: '../../GymClassTableChoose.do?classId=' + data.classId + "&coach=" + data.coach + "&memberId=" +<%=memberId%>,
                        success: function (res) {
                            layer.alert(res);
                        }
                    })
                    layer.close(index);
                });
                //实现退选课程
            } else if (obj.event === 'del') {
                layer.confirm('确定退选这个课程吗?', {icon: 3, title: '提示'}, function (index) {
                    $.ajax({
                        type: 'post',
                        url: '../../GymClassTableDel.do?classId=' + data.classId + "&coach=" + data.coach + "&memberId=" +<%=memberId%>,
                        success: function (res) {
                            layer.alert(res);
                        }
                    })
                    layer.close(index);
                });
            }
        });
    });

    function select() {
        var classId = $('#classId').val();
        var className = $('#className').val();
        var classBegin = $('#classBegin').val();
        var coach = $('#coach').val();
        var table = layui.table;
        table.reload('testReload', {//二次加载
            url: '../../GymClassTableLikeSelectServlet.do?classId=' + classId + "&className=" + className + "&classBegin=" + classBegin + "&coach=" + coach,
            where: {
                classId: classId,
                className: className,
                classBegin: classBegin,
                coach: coach
            }
        })
    }
</script>
</body>
</html>
