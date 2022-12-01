layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#demo'
        , even: true //开启隔行背景
        , cellMinWidth: 0
        , url: '../../GymClassOrderSelectServlet.do' //数据接口
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
            , {field: 'classOrderId', title: '报名编号', sort: true, align: 'center'}
            , {field: 'className', title: '报名名称', sort: true, edit: true, align: 'center'}
            , {field: 'classPhone', title: '报名电话', sort: true, edit: true, align: 'center'}
            , {field: 'classTable', title: '报名课程', sort: true, edit: true, align: 'center'}
            , {field: 'classCoach', title: '报名教练', sort: true, edit: true, align: 'center'}
            , {field: 'classTime', title: '报名时间', sort: true, edit: true, align: 'center'}
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
                    url: '../../GymClassOrderDelServlet.do?classOrderId=' + obj.data.classOrderId,
                    type: 'post',
                    data: {
                        classOrderId: obj.data.classOrderId,
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
                , title: '编辑报名信息'//标题
                , content: 'classOrderUpdate.html'//加载指定页面
                , anim: 4
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let classOrderId = $(layero).find('iframe')[0].contentWindow.classOrderId.value;
                    let className = $(layero).find('iframe')[0].contentWindow.document.getElementById('className').value;
                    let classPhone = $(layero).find('iframe')[0].contentWindow.document.getElementById('classPhone').value;
                    let classTable = $(layero).find('iframe')[0].contentWindow.document.getElementById('classTable').value;
                    let classCoach = $(layero).find('iframe')[0].contentWindow.document.getElementById('classCoach').value;
                    if (className == "") {
                        layer.msg('报名姓名，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (classPhone == "") {
                        layer.msg('报名电话，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (classTable == "") {
                        layer.msg('报名课程，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (classCoach == "") {
                        layer.msg('报名教练，岂能为空？', {icon: 5})
                        return false;
                    }
                    //同步数据表格中的数据
                    obj.update({
                        classOrderId: classOrderId,
                        className: className,
                        classPhone: classPhone,
                        classTable: classTable,
                        classCoach: classCoach,
                    })
                    $.ajax({
                        url: '../../GymClassOrderEditServlets.do?classOrderId=' + classOrderId + "&className=" + className + "&classPhone=" + classPhone + "&classTable=" + classTable + "&classCoach=" + classCoach,
                        type: 'post',
                        dataType: 'text',
                        data: {
                            classOrderId: classOrderId,
                            className: className,
                            classPhone: classPhone,
                            classTable: classTable,
                            classCoach: classCoach,
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
                    body.find('#classOrderId').val(data.classOrderId);
                    body.find('#className').val(data.className);
                    body.find('#classPhone').val(data.classPhone);
                    body.find('#classTable').val(data.classTable);
                    body.find('#classCoach').val(data.classCoach);
                }
            });
        }
    });
    //监听单元格编辑(选中某一个字段直接更改对应的值)
    table.on('edit(test)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        layer.msg('[ID: ' + data.classOrderId + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymClassOrderEditServlets.do?classOrderId=' + data.classOrderId + "&className=" + data.className + "&classPhone=" + data.classPhone + "&classTable=" + data.classTable + "&classCoach=" + data.classCoach,
            type: 'post',
            dataType: 'text',
            data: {
                className: data.className,
                classPhone: data.classPhone,
                classTable: data.classTable,
                classCoach: data.classCoach,
            },
            success: function (res) {
                layer.alert('编辑成功!', {icon: 6});
            },
            error: function (res) {
                layer.msg('编辑失败，请检查!', {icon: 55})
            }
        })
    });
});

function select() {
    var classOrderId = $('#classOrderId').val();
    var className = $('#className').val();
    var classCoach = $('#classCoach').val();
    var classPhone = $('#classPhone').val();
    var table = layui.table;
    table.reload('demo', {//二次加载
        url: '../../GymClassOrderLikeSelect.do?classOrderId=' + classOrderId + "&className=" + className + "&classCoach=" + classCoach + "&classPhone=" + classPhone,
        where: {
            classOrderId: classOrderId,
            className: className,
            classCoach: classCoach,
            classPhone: classPhone
        }
    })
}