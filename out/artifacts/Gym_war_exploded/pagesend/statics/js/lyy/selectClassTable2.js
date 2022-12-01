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
        //console.log(obj)
        //实现删除
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    url: '../../GymClassTableDelServlet.do?classId=' + obj.data.classId,
                    type: 'post',
                    data: {
                        classId: obj.data.classId,
                    },
                    success: function (res) {
                        layer.alert('删除成功!', {icon: 6});
                        obj.del();
                    },
                    error: function (res) {
                        layer.msg('删除失败!', {icon: 5})
                    }
                })
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            //实现编辑功能
            layer.open({
                type: 2//当前页面弹出
                , title: '编辑课程信息'//标题
                , content: 'classTableUpdate.html'//加载指定页面
                , anim: 4
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let classId = $(layero).find('iframe')[0].contentWindow.classId.value;
                    let className = $(layero).find('iframe')[0].contentWindow.document.getElementById('className').value;
                    let classBegin = $(layero).find('iframe')[0].contentWindow.document.getElementById('classBegin').value;
                    let classTime = $(layero).find('iframe')[0].contentWindow.document.getElementById('classTime').value;
                    let coach = $(layero).find('iframe')[0].contentWindow.document.getElementById('coach').value;
                    if (className == "") {
                        layer.msg('课程名称，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (classBegin == "") {
                        layer.msg('开课时间，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (classTime == "") {
                        layer.msg('课程时长，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (coach == "") {
                        layer.msg('教练编号，岂能为空？', {icon: 5})
                        return false;
                    }
                    //同步数据表格中的数据
                    obj.update({
                        classId: classId,
                        className: className,
                        classBegin: classBegin,
                        classTime: classTime,
                        coach: coach
                    })
                    $.ajax({
                        url: '../../GymClassTableEditServlets.do?classId=' + classId + "&className=" + className + "&classBegin=" + classBegin + "&classTime=" + classTime + "&coach=" + coach,
                        type: 'post',
                        dataType: 'text',
                        data: {
                            classId: classId,
                            className: className,
                            classBegin: classBegin,
                            classTime: classTime,
                            coach: coach
                        },
                        success: function (res) {
                            layer.alert('编辑成功!', {icon: 6});
                        },
                        error: function (res) {
                            layer.alert('编辑失败,请检查!', {icon: 5})
                        }
                    })
                    layer.close(index);
                }, success: function (layero, index) {
                    let div = layero.find('iframe').contents().find('#useradmin');
                    let body = layer.getChildFrame('body', index);
                    let iframeWindow = window['layui-layer-iframe' + index]
                    body.find('#classId').val(data.classId);
                    body.find('#className').val(data.className);
                    body.find('#classBegin').val(data.classBegin);
                    body.find('#classTime').val(data.classTime);
                    body.find('#coach').val(data.coach);
                }
            });
        }
    });
    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        }
        ;
    });
    //监听单元格编辑(选中某一个字段直接更改对应的值)
    table.on('edit(test)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        layer.msg('[ID: ' + data.classId + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymClassTableEditServlets.do?classId=' + data.classId + "&className=" + data.className + "&classBegin=" + data.classBegin + "&classTime=" + data.classTime + "&coach=" + data.coach,
            type: 'post',
            dataType: 'text',
            data: {
                classId: data.classId,
                className: data.className,
                classBegin: data.classBegin,
                classTime: data.classTime,
                coach: data.coach
            },
            success: function (res) {
                layer.alert('编辑成功!', {icon: 6});
            },
            error: function (res) {
                layer.alert('编辑失败，请检查!', {icon: 5})
            }
        })
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