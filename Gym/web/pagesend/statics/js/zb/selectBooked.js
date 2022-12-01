layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#demo'
        , even: true //开启隔行背景
        , cellMinWidth: 0
        , url: '../../GymBookedSelectServlet.do' //数据接口
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
            , {field: 'billId', title: '账单编号', sort: true, align: 'center'}
            , {field: 'billingAccount', title: '用户账号', sort: true, edit: true, align: 'center'}
            , {field: 'billName', title: '用户姓名', sort: true, edit: true, align: 'center'}
            , {field: 'whetherMembers', title: '是否会员', sort: true, edit: true, align: 'center'}
            , {field: 'billMoney', title: '账单金额', sort: true, edit: true, align: 'center'}
            , {field: 'billNotel', title: '账单备注', sort: true, edit: true, align: 'center'}
            , {field: 'consumerPlace', title: '消费去处', sort: true, edit: true, align: 'center'}
            , {field: 'billTime', title: '入账日期', sort: true, edit: true, align: 'center'}
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
                    url: '../../GymBookedDelServlet.do?billId=' + obj.data.billId,
                    type: 'post',
                    data: {
                        billId: obj.data.billId,
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
                , title: '编入账信息'//标题
                , content: 'bookedUpdate.html'//加载指定页面
                , anim: 4
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let billId = $(layero).find('iframe')[0].contentWindow.billId.value;
                    let billingAccount = $(layero).find('iframe')[0].contentWindow.document.getElementById('billingAccount').value;
                    let billName = $(layero).find('iframe')[0].contentWindow.document.getElementById('billName').value;
                    let whetherMembers = $(layero).find('iframe')[0].contentWindow.document.getElementById('whetherMembers').value;
                    let billMoney = $(layero).find('iframe')[0].contentWindow.document.getElementById('billMoney').value;
                    let billNotel = $(layero).find('iframe')[0].contentWindow.document.getElementById('billNotel').value;
                    let consumerPlace = $(layero).find('iframe')[0].contentWindow.document.getElementById('consumerPlace').value;
                    if (billingAccount == "") {
                        layer.msg("用户账号，岂能为空？", {icon: 5})
                        return false;
                    }
                    if (billName == "") {
                        layer.msg("用户姓名，岂能为空？", {icon: 5})
                        return false;
                    }
                    if (whetherMembers == "") {
                        layer.msg("是否，岂能为空？", {icon: 5})
                        return false;
                    }
                    if (billMoney == "") {
                        layer.msg("账单金额，岂能为空？", {icon: 5})
                        return false;
                    }
                    if (billNotel == "") {
                        layer.msg("账单备注，岂能为空？", {icon: 5})
                        return false;
                    }
                    if (consumerPlace == "") {
                        layer.msg("消费去除，岂能为空？", {icon: 5})
                        return false;
                    }
                    //同步数据表格中的数据
                    obj.update({
                        billId: billId,
                        billingAccount: billingAccount,
                        billName: billName,
                        whetherMembers: whetherMembers,
                        billMoney: billMoney,
                        billNotel: billNotel,
                        consumerPlace: consumerPlace
                    })
                    $.ajax({
                        url: '../../GymBookedEditServlets.do?billId=' + billId + "&billingAccount=" + billingAccount + "&billName=" + billName + "&whetherMembers=" + whetherMembers + "&billMoney=" + billMoney + "&billNotel=" + billNotel + "&consumerPlace=" + consumerPlace,
                        type: 'post',
                        dataType: 'text',
                        data: {
                            billId: billId,
                            billingAccount: billingAccount,
                            billName: billName,
                            whetherMembers: whetherMembers,
                            billMoney: billMoney,
                            billNotel: billNotel,
                            consumerPlace: consumerPlace
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
                    body.find('#billId').val(data.billId);
                    body.find('#billingAccount').val(data.billingAccount);
                    body.find('#billName').val(data.billName);
                    body.find('#whetherMembers').val(data.whetherMembers);
                    body.find('#billMoney').val(data.billMoney);
                    body.find('#billNotel').val(data.billNotel);
                    body.find('#consumerPlace').val(data.consumerPlace);
                }
            });
        }
    });
    //监听单元格编辑(选中某一个字段直接更改对应的值)
    table.on('edit(test)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        layer.msg('[ID: ' + data.billId + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymBookedEditServlets.do?billId=' + data.billId + "&billingAccount=" + data.billingAccount + "&billName=" + data.billName + "&whetherMembers=" + data.whetherMembers + "&billMoney=" + data.billMoney + "&billNotel=" + data.billNotel + "&consumerPlace=" + data.consumerPlace,
            type: 'post',
            dataType: 'text',
            data: {
                billId: data.billId,
                billingAccount: data.billingAccount,
                billName: data.billName,
                whetherMembers: data.whetherMembers,
                billMoney: data.billMoney,
                billNotel: data.billNotel,
                consumerPlace: data.consumerPlace
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
    var billId = $('#billId').val();
    var billingAccount = $('#billingAccount').val();
    var billName = $('#billName').val();
    var billTime = $('#billTime').val();
    var table = layui.table;
    table.reload('demo', {//二次加载
        url: '../../GymBookedLikeSelect.do?billId=' + billId + "&billingAccount=" + billingAccount + "&billName=" + billName + "&billTime=" + billTime,
        where: {
            billId: billId,
            billingAccount: billingAccount,
            billName: billName,
            billTime: billTime
        }
    })
}