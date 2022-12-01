layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#demo'
        , even: true //开启隔行背景
        , cellMinWidth: 0
        , url: '../../GymEmployeeSelectServlet.do' //数据接口
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
            , {field: 'employeeAccount', title: '员工账号', sort: true, align: 'center'}
            , {field: 'employeeName', title: '员工姓名', sort: true, edit: true, align: 'center'}
            , {field: 'employeeGender', title: '员工性别', sort: true, edit: true, align: 'center'}
            , {field: 'employeeAge', title: '员工年龄', sort: true, edit: true, align: 'center'}
            , {field: 'entryTime', title: '入职时间', sort: true, edit: true, align: 'center'}
            , {field: 'staff', title: '职务', sort: true, edit: true, align: 'center'}
            , {field: 'employeeMessage', title: '备注信息', sort: true, edit: true, align: 'center'}
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
                    url: '../../GymEmployeeDelServlet.do?employeeAccount=' + obj.data.employeeAccount,
                    type: 'post',
                    data: {
                        employeeAccount: obj.data.employeeAccount,
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
                , title: '编辑员工信息'//标题
                , content: 'employeeUpdate.html'//加载指定页面
                , anim: 4
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let employeeAccount = $(layero).find('iframe')[0].contentWindow.employeeAccount.value;
                    let employeeName = $(layero).find('iframe')[0].contentWindow.document.getElementById('employeeName').value;
                    let employeeGender = $(layero).find('iframe')[0].contentWindow.document.getElementById('employeeGender').value;
                    let employeeAge = $(layero).find('iframe')[0].contentWindow.document.getElementById('employeeAge').value;
                    let entryTime = $(layero).find('iframe')[0].contentWindow.document.getElementById('entryTime').value;
                    let staff = $(layero).find('iframe')[0].contentWindow.document.getElementById('staff').value;
                    let employeeMessage = $(layero).find('iframe')[0].contentWindow.document.getElementById('employeeMessage').value;
                    if (employeeName == "") {
                        layer.msg('员工姓名，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (employeeGender == "") {
                        layer.msg('员工性别，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (employeeAge == "") {
                        layer.msg('员工年龄，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (entryTime == "") {
                        layer.msg('员工入职时间，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (staff == "") {
                        layer.msg('员工职务，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (employeeMessage == "") {
                        layer.msg('员工备注信息，岂能为空？', {icon: 5})
                        return false;
                    }
                    //同步数据表格中的数据
                    obj.update({
                        employeeAccount: employeeAccount,
                        employeeName: employeeName,
                        employeeGender: employeeGender,
                        employeeAge: employeeAge,
                        entryTime: entryTime,
                        staff: staff,
                        employeeMessage: employeeMessage
                    })
                    $.ajax({
                        url: '../../GymEmployeeEditServlets.do?employeeAccount=' + employeeAccount + "&employeeName=" + employeeName + "&employeeGender=" + employeeGender + "&employeeAge=" + employeeAge + "&entryTime=" + entryTime + "&staff=" + staff + "&employeeMessage=" + employeeMessage,
                        type: 'post',
                        dataType: 'text',
                        data: {
                            employeeAccount: employeeAccount,
                            employeeName: employeeName,
                            employeeGender: employeeGender,
                            employeeAge: employeeAge,
                            entryTime: entryTime,
                            staff: staff,
                            employeeMessage: employeeMessage
                        },
                        success: function (res) {
                            layer.alert('编辑成功!', {icon: 6});
                        },
                        error: function (res) {
                            layer.alert('编辑失败，请检查!', {icon: 5})
                        }
                    })
                    layer.close(index);
                }, success: function (layero, index) {
                    let div = layero.find('iframe').contents().find('#useradmin');
                    let body = layer.getChildFrame('body', index);
                    let iframeWindow = window['layui-layer-iframe' + index]
                    body.find('#employeeAccount').val(data.employeeAccount);
                    body.find('#employeeName').val(data.employeeName);
                    body.find('#employeeGender').val(data.employeeGender);
                    body.find('#employeeAge').val(data.employeeAge);
                    body.find('#entryTime').val(data.entryTime);
                    body.find('#staff').val(data.staff);
                    body.find('#employeeMessage').val(data.employeeMessage);

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
        layer.msg('[ID: ' + data.employeeAccount + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymEmployeeEditServlets.do?employeeAccount=' + data.employeeAccount + "&employeeName=" + data.employeeName + "&employeeGender=" + data.employeeGender + "&employeeAge=" + data.employeeAge + "&entryTime=" + data.entryTime + "&staff=" + data.staff + "&employeeMessage=" + data.employeeMessage,
            type: 'post',
            dataType: 'text',
            data: {
                employeeAccount: data.employeeAccount,
                employeeName: data.employeeName,
                employeeGender: data.employeeGender,
                employeeAge: data.employeeAge,
                entryTime: data.entryTime,
                staff: data.staff,
                employeeMessage: data.employeeMessage
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
    var employeeAccount = $('#employeeAccount').val();
    var employeeName = $('#employeeName').val();
    var entryTime = $('#entryTime').val();
    var staff = $('#staff').val();
    var table = layui.table;
    table.reload('demo', {//二次加载
        url: '../../GymEmployeeLikeSelectServlet.do?employeeAccount=' + employeeAccount + "&employeeName=" + employeeName + "&entryTime=" + entryTime + "&staff=" + staff,
        where: {
            employeeAccount: employeeAccount,
            employeeName: employeeName,
            entryTime: entryTime,
            staff: staff
        }
    })
}