layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#demo'
        , id: 'testReload'
        , even: true //开启隔行背景
        , cellMinWidth: 0
        , url: '../../GymEquipmentSelectServlet.do' //数据接口
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
            , {field: 'equipmentId', title: '器材id', sort: true, align: 'center'}
            , {field: 'equipmentName', title: '器材名称', sort: true, edit: true, align: 'center'}
            , {field: 'equipmentLocation', title: '器材位置', sort: true, edit: true, align: 'center'}
            , {field: 'equipmentStatus', title: '器材状态', sort: true, edit: true, align: 'center'}
            , {field: 'equipmentMessage', title: '器材备注信息', sort: true, edit: true, align: 'center'}
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
                    url: '../../GymEquipmentDelServlet.do?equipmentId=' + obj.data.equipmentId,
                    type: 'post',
                    data: {
                        equipmentId: obj.data.equipmentId,
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
                , title: '编辑器材信息'//标题
                , content: 'equipmenUpdate.html'//加载指定页面
                , anim: 4
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let equipmentId = $(layero).find('iframe')[0].contentWindow.equipmentId.value;
                    let equipmentName = $(layero).find('iframe')[0].contentWindow.document.getElementById('equipmentName').value;
                    let equipmentLocation = $(layero).find('iframe')[0].contentWindow.document.getElementById('equipmentLocation').value;
                    let equipmentStatus = $(layero).find('iframe')[0].contentWindow.document.getElementById('equipmentStatus').value;
                    let equipmentMessage = $(layero).find('iframe')[0].contentWindow.document.getElementById('equipmentMessage').value;
                    if (equipmentName == "") {
                        layer.msg('器材名称，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (equipmentLocation == "") {
                        layer.msg('器材位置，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (equipmentStatus == "") {
                        layer.msg('器材状态，岂能为空？', {icon: 5})
                        return false;
                    }
                    //同步数据表格中的数据
                    obj.update({
                        equipmentId: equipmentId,
                        equipmentName: equipmentName,
                        equipmentLocation: equipmentLocation,
                        equipmentStatus: equipmentStatus,
                        equipmentMessage: equipmentMessage,
                    })
                    $.ajax({
                        url: '../../GymEquipmentEditServlets.do?equipmentId=' + equipmentId + "&equipmentName=" + equipmentName + "&equipmentLocation=" + equipmentLocation + "&equipmentStatus=" + equipmentStatus + "&equipmentMessage=" + equipmentMessage,
                        type: 'post',
                        dataType: 'text',
                        data: {
                            equipmentId: equipmentId,
                            equipmentName: equipmentName,
                            equipmentLocation: equipmentLocation,
                            equipmentStatus: equipmentStatus,
                            equipmentMessage: equipmentMessage,
                        },
                        success: function (res) {
                            layer.alert('编辑成功!', {icon: 6});
                        },
                        error: function (res) {
                            layer.msg('编辑失败,请检查!', {icon: 55})
                        }
                    })
                    layer.close(index);
                }, success: function (layero, index) {
                    let div = layero.find('iframe').contents().find('#useradmin');
                    let body = layer.getChildFrame('body', index);
                    let iframeWindow = window['layui-layer-iframe' + index]
                    body.find('#equipmentId').val(data.equipmentId);
                    body.find('#equipmentName').val(data.equipmentName);
                    body.find('#equipmentLocation').val(data.equipmentLocation);
                    body.find('#equipmentStatus').val(data.equipmentStatus);
                    body.find('#equipmentMessage').val(data.equipmentMessage);
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
        layer.msg('[ID: ' + data.equipmentId + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymEquipmentEditServlets.do?equipmentId=' + data.equipmentId + "&equipmentName=" + data.equipmentName + "&equipmentLocation=" + data.equipmentLocation + "&equipmentStatus=" + data.equipmentStatus + "&equipmentMessage=" + data.equipmentMessage,
            type: 'post',
            dataType: 'text',
            data: {
                equipmentId: data.equipmentId,
                equipmentName: data.equipmentName,
                equipmentLocation: data.equipmentLocation,
                equipmentStatus: data.equipmentStatus,
                equipmentMessage: data.equipmentMessage,
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
    var equipmentId = $('#equipmentId').val();
    var equipmentName = $('#equipmentName').val();
    var equipmentLocation = $('#equipmentLocation').val();
    var equipmentStatus = $('#equipmentStatus').val();
    var table = layui.table;
    table.reload('testReload', {//二次加载
        url: '../../GymEquipmentLikeSelectServlet.do?equipmentId=' + equipmentId + "&equipmentName=" + equipmentName + "&equipmentLocation=" + equipmentLocation + "&equipmentStatus=" + equipmentStatus,
        where: {
            equipmentId: equipmentId,
            equipmentName: equipmentName,
            equipmentLocation: equipmentLocation,
            equipmentStatus: equipmentStatus

        }
    })
}