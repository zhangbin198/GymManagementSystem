<%@ page import="com.xhwy.gym.entity.MemberAll" %>
<%@ page import="com.xhwy.gym.dao.lyy.impl.ClassTableDaoImpl" %>
<%@ page import="com.xhwy.gym.dao.lyy.ClassTableDao" %>
<%@ page import="com.xhwy.gym.dao.zb.MemberDao" %>
<%@ page import="com.xhwy.gym.dao.zb.impl.MemberDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学员上传作品</title>
</head>
<link rel="stylesheet" href="../statics/layui/css/layui.css"/>
<script src="../statics/layui/layui.js"></script>
<script src="../statics/js/jquery.js"></script>
<body>
<%
    ClassTableDao members = new ClassTableDaoImpl();
    MemberAll member = (MemberAll) session.getAttribute("members");
    String src = member.getSrc();
    String memberId = member.getMemberId();
    String memberId1 = member.getMemberId();
    String memberName = member.getMemberName();
    int classId = member.getClassId();
    String className = members.getTName(classId);
%>
<h1><%=memberId%>会员,乐刻健身欢迎您!</h1>
<br>
<h2>您当前的选课是:<%=className%>
</h2>
<br>
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
<h1><a href="" id="link">预览</a></h1>
<script type="text/javascript">
    layui.use(['upload', 'element', 'layer'], function () {
        var $ = layui.jquery
            , upload = layui.upload
            , element = layui.element
            , layer = layui.layer;
        var myLink = document.getElementById("link");
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
                    myLink.setAttribute("href", "/zb/" + file.name)
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
                    p = res.src2;
                    $.ajax({
                        type: 'get',
                        url: '../../GymSubmitAddServlet.do?src=' + p + "&memberId=" +<%=memberId1%>,
                        dataType: 'text',
                        //返回添加成功或失败的结果
                        success: function (res) {
                            if (res == "success") {
                                layer.alert("上传课程成功!", {icon: 6});
                            } else {
                                layer.alert('上传课程失败，请联系管理员!', {icon: 5});
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
