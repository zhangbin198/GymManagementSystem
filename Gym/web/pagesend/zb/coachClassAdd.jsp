<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.xhwy.gym.entity.Coach" %>
<html>
<head>
    <title>教练发布课程</title>
</head>
<link rel="stylesheet" href="../statics/layui/css/layui.css"/>
<script src="../statics/layui/layui.js"></script>
<script src="../statics/js/jquery.js"></script>
<%
    Coach coach = (Coach) session.getAttribute("coachs");
    String coachId = coach.getCoachId();
%>
<body>
<h1 style="font-size: 50px" class="layui-container layui-col-md-offset5">教练发布课程</h1>
<div class="layui-container" style="margin-top:50px">
    <div class="layui-row">
        <div class="layui-col-md6 layui-col-md-offset3">
            <div class="layui-form-item">
                <label class="layui-form-label"><i style="color: red">*</i>&nbsp;&nbsp;登录教练编号</label>
                <div class="layui-input-block">
                    <input type="text" name="coachId" id="coachId" class="layui-input" readonly
                           value="<%=coachId%>"/>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i style="color: red">*</i>&nbsp;&nbsp;课程名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="className" id="className" placeholder="请输入课程名称"
                               class="layui-input" lay-verify="required" lay-reqtext="课程名称，岂能为空？"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i style="color: red">*</i>&nbsp;&nbsp;开课时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="classBegin" id="classBegin" placeholder="请输入开课时间"
                               class="layui-input" lay-verify="required" lay-reqtext="开课时间，岂能为空？"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i style="color: red">*</i>&nbsp;&nbsp;课程时长</label>
                    <div class="layui-input-block">
                        <input type="text" name="classTime" id="classTime" class="layui-input"
                               placeholder="请输入课程时长" lay-verify="required" lay-reqtext="课程时长，岂能为空？"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i style="color: red">*</i>&nbsp;&nbsp;教练编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="coachName" id="coachName" class="layui-input"
                               placeholder="请输入教练" lay-verify="required" lay-reqtext="教练编号，岂能为空？" value="<%=coachId%>"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layui-upload">
    <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>
    <div class="layui-upload-list" style="max-width: 2000px;">
        <table class="layui-table">
            <colgroup>
                <col width="500px">
                <col width="500px">
                <col width="500px">
                <col width="500px">
            </colgroup>
            <thead>
            <tr>
                <th>文件名</th>
                <th>大小</th>
                <th>上传进度</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="demoList"></tbody>
        </table>
    </div>
    <button type="button" class="layui-btn" id="testListAction">开始上传</button>
</div>
<script type="text/javascript">
    layui.use(['upload', 'element', 'layer'], function () {
        var $ = layui.jquery
            , upload = layui.upload
            , element = layui.element
            , layer = layui.layer;
        //演示多文件列表
        var uploadListIns = upload.render({
            elem: '#testList'
            , elemList: $('#demoList') //列表元素对象
            , url: '../../GymCoachUpload.do' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
            , accept: 'file'
            , multiple: true
            , number: 3
            , auto: false
            , bindAction: '#testListAction'
            , choose: function (obj) {
                var that = this;
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td>' + file.name + '</td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td><div class="layui-progress" lay-filter="progress-demo-' + index + '"><div class="layui-progress-bar" lay-percent=""></div></div></td>'
                        , '<td>'
                        , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));
                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    that.elemList.append(tr);
                    element.render('progress'); //渲染新加的进度条组件
                });
            }
            , done: function (res, index, upload) { //成功的回调
                var that = this;
                if (res) { //上传成功
                    var coachId = $('#coachId').val();
                    var classBegin = $('#classBegin').val();
                    var className = $('#className').val();
                    var classTime = $('#classTime').val();
                    var coachName = $('#coachName').val();
                    p = res.src2;
                    $.ajax({
                        url: '../../GymClassTableAddServlet.do?coachId=' + coachId + "&classSrc=" + p + "&classBegin=" + classBegin +
                            "&classTime=" + classTime + "&coachName=" + coachName + "&className=" + className,
                        type: 'get',
                        dataType: 'text',
                        //返回添加成功或失败的结果
                        success: function (res) {
                            if (res == "success") {
                                layer.alert("发布课程成功!", {icon: 6}, function () {
                                    location.href = 'http://localhost:8080/Gym_Web_exploded/pagesend/lyy/classTableSelect2.html';
                                })
                            } else {
                                layer.alert('发布课程失败，请联系管理员!', {icon: 5});
                            }
                        }
                    })
                    var tr = that.elemList.find('tr#upload-' + index)
                        , tds = tr.children();
                    tds.eq(3).html(''); //清空操作
                    delete this.files[index]; //删除文件队列已经上传成功的文件
                    return;
                }
                this.error(index, upload);
            }
            , allDone: function (obj) { //多文件上传完毕后的状态回调
                console.log(obj)
            }
            , error: function (index, upload) { //错误回调
                var that = this;
                var tr = that.elemList.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
            , progress: function (n, elem, e, index) { //注意：index 参数为 layui 2.6.6 新增
                element.progress('progress-demo-' + index, n + '%'); //执行进度条。n 即为返回的进度百分比
            }
        });
    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#classBegin' //指定元素
            , calendar: true
            , type: 'datetime'
            , theme: '#393D49'
        });
    });
</script>
</body>
</html>
