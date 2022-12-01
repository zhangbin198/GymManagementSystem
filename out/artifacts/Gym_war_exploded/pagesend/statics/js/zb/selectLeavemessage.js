layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#demo'
        , even: true //开启隔行背景
        , cellMinWidth: 0
        , url: '../../GymLeaveMessageSelectServlet.do' //数据接口
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
            , {field: 'leavemessageId', title: '留言编号', sort: true, align: 'center'}
            , {field: 'leavemessageUser', title: '留言用户姓名', sort: true, edit: true, align: 'center'}
            , {field: 'leavemessageContent', title: '留言内容', sort: true, edit: true, align: 'center'}
            , {field: 'leavemessagePhone', title: '留言手机号码', sort: true, edit: true, align: 'center'}
            , {field: 'leavemessageTime', title: '留言时间', sort: true, edit: true, align: 'center'}
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
                    url: '../../GymLeaveMessageDelServlet.do?leavemessageId=' + obj.data.leavemessageId,
                    type: 'post',
                    data: {
                        leavemessageId: obj.data.leavemessageId,
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
                , title: '编辑留言信息'//标题
                , content: 'leaveMessageUpdate.html'//加载指定页面
                , anim: 4
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let leavemessageId = $(layero).find('iframe')[0].contentWindow.leavemessageId.value;
                    let leavemessageUser = $(layero).find('iframe')[0].contentWindow.document.getElementById('leavemessageUser').value;
                    let leavemessageContent = $(layero).find('iframe')[0].contentWindow.document.getElementById('leavemessageContent').value;
                    let leavemessagePhone = $(layero).find('iframe')[0].contentWindow.document.getElementById('leavemessagePhone').value;
                    if (leavemessageUser == "") {
                        layer.msg('留言姓名，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (leavemessageContent == "") {
                        layer.msg('留言内容，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (leavemessagePhone == "") {
                        layer.msg('留言电话，岂能为空？', {icon: 5})
                        return false;
                    }
                    //同步数据表格中的数据
                    obj.update({
                        leavemessageId: leavemessageId,
                        leavemessageUser: leavemessageUser,
                        leavemessageContent: leavemessageContent,
                        leavemessagePhone: leavemessagePhone,
                    })
                    $.ajax({
                        url: '../../GymLeaveMessageEditServlets.do?leavemessageId=' + leavemessageId + "&leavemessageUser=" + leavemessageUser + +"&leavemessageContent=" + leavemessageContent + "&leavemessagePhone=" + leavemessagePhone,
                        type: 'post',
                        dataType: 'text',
                        data: {
                            leavemessageId: leavemessageId,
                            leavemessageUser: leavemessageUser,
                            leavemessageContent: leavemessageContent,
                            leavemessagePhone: leavemessagePhone,
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
                    body.find('#leavemessageId').val(data.leavemessageId);
                    body.find('#leavemessageUser').val(data.leavemessageUser);
                    body.find('#leavemessageContent').val(data.leavemessageContent);
                    body.find('#leavemessagePhone').val(data.leavemessagePhone);
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
        layer.msg('[ID: ' + data.leavemessageId + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymLeaveMessageEditServlets.do?leavemessageId=' + data.leavemessageId + "&leavemessageUser=" + data.leavemessageUser + "&leavemessageContent=" + data.leavemessageContent + "&leavemessagePhone=" + data.leavemessagePhone,
            type: 'post',
            dataType: 'text',
            data: {
                leavemessageId: data.leavemessageId,
                leavemessageUser: data.leavemessageUser,
                leavemessageContent: data.leavemessageContent,
                leavemessagePhone: data.leavemessagePhone,
            },
            success: function (res) {
                layer.alert('编辑成功!', {icon: 6});
            },
            error: function (res) {
                layer.alert('编辑失败,请检查!', {icon: 5})
            }
        })
    });
});

function select() {
    var leavemessageId = $('#leavemessageId').val();
    var leavemessageUser = $('#leavemessageUser').val();
    var leavemessagePhone = $('#leavemessagePhone').val();
    var leavemessageTime = $('#leavemessageTime').val();
    var table = layui.table;
    table.reload('demo', {//二次加载
        url: '../../GymLeaveMessageLikeSelect.do?leavemessageId=' + leavemessageId + "&leavemessageUser=" + leavemessageUser + "&leavemessagePhone=" + leavemessagePhone + "&leavemessageTime=" + leavemessageTime,
        where: {
            leavemessageId: leavemessageId,
            leavemessageUser: leavemessageUser,
            leavemessagePhone: leavemessagePhone,
            leavemessageTime: leavemessageTime
        }
    })
}