layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#demo'
        , even: true //开启隔行背景
        , cellMinWidth: 0
        , url: '../../GymCoachSelect.do' //数据接口
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
            , {field: 'coachId', title: '教练编号', sort: true, align: 'center'}
            , {field: 'coachName', title: '教练姓名', sort: true, edit: true, align: 'center'}
            , {field: 'coachGender', title: '教练性别', sort: true, edit: true, align: 'center'}
            , {field: 'coachField', title: '发布课程名', sort: true, edit: true, align: 'center'}
            , {field: 'coachSaff', title: '教练阶级', sort: true, edit: true, align: 'center'}
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
                    url: '../../GymCoachDel.do?coachId=' + obj.data.coachId,
                    type: 'post',
                    data: {
                        coachId: obj.data.coachId,
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
                , title: '编辑教练信息'//标题
                , content: 'coachUpdate.html'//加载指定页面
                , anim: 4
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let coachId = $(layero).find('iframe')[0].contentWindow.coachId.value;
                    let coachName = $(layero).find('iframe')[0].contentWindow.document.getElementById('coachName').value;
                    let coachGender = $(layero).find('iframe')[0].contentWindow.document.getElementById('coachGender').value;
                    let coachAge = $(layero).find('iframe')[0].contentWindow.document.getElementById('coachAge').value;
                    let coachField = $(layero).find('iframe')[0].contentWindow.document.getElementById('coachField').value;
                    let coachSaff = $(layero).find('iframe')[0].contentWindow.document.getElementById('coachSaff').value;
                    if (coachName == "") {
                        layer.msg('教练姓名，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (coachGender == "") {
                        layer.msg('教练性别，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (coachAge == "") {
                        layer.msg('教练年龄，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (coachField == "") {
                        layer.msg('教练领域，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (coachSaff == "") {
                        layer.msg('教练阶级，岂能为空？', {icon: 5})
                        return false;
                    }
                    //同步数据表格中的数据
                    obj.update({
                        coachId: coachId,
                        coachName: coachName,
                        coachGender: coachGender,
                        coachAge: coachAge,
                        coachField: coachField,
                        coachSaff: coachSaff
                    })
                    $.ajax({
                        url: '../../GymCoachEdit.do?coachId=' + coachId + "&coachName=" + coachName + "&coachGender=" + coachGender + "&coachAge=" + coachAge + "&coachField=" + coachField + "&coachSaff=" + coachSaff,
                        type: 'post',
                        dataType: 'text',
                        data: {
                            coachId: coachId,
                            coachName: coachName,
                            coachGender: coachGender,
                            coachAge: coachAge,
                            coachField: coachField,
                            coachSaff: coachSaff
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
                    body.find('#coachId').val(data.coachId);
                    body.find('#coachName').val(data.coachName);
                    body.find('#coachGender').val(data.coachGender);
                    body.find('#coachAge').val(data.coachAge);
                    body.find('#coachField').val(data.coachField);
                    body.find('#coachSaff').val(data.coachSaff);
                }
            });
        }
    });
    //监听单元格编辑(选中某一个字段直接更改对应的值)
    table.on('edit(test)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        layer.msg('[ID: ' + data.coachId + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymCoachEdit.do?coachId=' + data.coachId + "&coachName=" + data.coachName + "&coachGender=" + data.coachGender + "&coachAge=" + data.coachAge + "&coachField=" + data.coachField + "&coachSaff=" + data.coachSaff,
            type: 'post',
            dataType: 'text',
            data: {
                coachId: data.coachId,
                coachName: data.coachName,
                coachGender: data.coachGender,
                coachAge: data.coachAge,
                coachField: data.coachField,
                coachSaff: data.coachSaff
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
    var coachId = $('#coachId').val();
    var coachName = $('#coachName').val();
    var coachField = $('#coachField').val();
    var coachSaff = $('#coachSaff').val();
    var table = layui.table;
    table.reload('demo', {//二次加载
        url: '../../GymCoachLikeSelect.do?coachId=' + coachId + "&coachName=" + coachName + "&coachField=" + coachField + "&coachSaff=" + coachSaff,
        where: {
            coachId: coachId,
            coachName: coachName,
            coachField: coachField,
            coachSaff: coachSaff
        }
    })
}