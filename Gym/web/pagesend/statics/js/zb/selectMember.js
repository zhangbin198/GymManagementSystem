layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#demo'
        , even: true //开启隔行背景
        , page: true //开启分页
        , id: 'testReload'
        , method: 'get'
        , url: '../../GymMemberSelectServlet.do' //数据接口
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            , layEvent: 'LAYTABLE_TIPS'
            , icon: 'layui-icon-tips'
        }]
        , cols: [[ //表头
            {type: 'checkbox'}
            , {field: 'memberAccount', title: '会员账号', sort: true, align: 'center'}
            , {field: 'memberName', title: '会员姓名', sort: true, edit: true, align: 'center'}
            , {field: 'memberGender', title: '会员性别', sort: true, edit: true, align: 'center'}
            , {field: 'memberAge', title: '会员年龄', sort: true, edit: true, align: 'center'}
            , {field: 'memberHeight', title: '会员身高', sort: true, edit: true, align: 'center'}
            , {field: 'memberWeight', title: '会员体重', sort: true, edit: true, align: 'center'}
            , {field: 'memberPhone', title: '会员电话', sort: true, edit: true, align: 'center'}
            , {field: 'cardTime', title: '办卡时间', sort: true, edit: true, align: 'center'}
            , {field: 'cardClass', title: '购买课时', sort: true, edit: true, align: 'center'}
            , {field: 'cardNextClass', title: '剩余课时', sort: true, edit: true}
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
                    url: '../../GymMemberDelServlet.do?memberAccount=' + obj.data.memberAccount,
                    type: 'post',
                    data: {
                        memberAccount: obj.data.memberAccount,
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
                , title: '编辑会员信息'//标题
                , anim: 4
                , content: 'membersUpdate.html'//加载指定页面
                , area: ['100%', '100%']//弹出层尺寸
                , btn: ['提交', '取消']//弹出按钮
                , yes: function (index, layero) {//做的事情
                    //得到回调的数值
                    let memberAccount = $(layero).find('iframe')[0].contentWindow.memberAccount.value;
                    let memberName = $(layero).find('iframe')[0].contentWindow.document.getElementById('memberName').value;
                    let memberGender = $(layero).find('iframe')[0].contentWindow.document.getElementById('memberGender').value;
                    let memberAge = $(layero).find('iframe')[0].contentWindow.document.getElementById('memberAge').value;
                    let memberHeight = $(layero).find('iframe')[0].contentWindow.document.getElementById('memberHeight').value;
                    let memberWeight = $(layero).find('iframe')[0].contentWindow.document.getElementById('memberWeight').value;
                    let memberPhone = $(layero).find('iframe')[0].contentWindow.document.getElementById('memberPhone').value;
                    let cardClass = $(layero).find('iframe')[0].contentWindow.document.getElementById('cardClass').value;
                    let cardNextClass = $(layero).find('iframe')[0].contentWindow.document.getElementById('cardNextClass').value;
                    if (memberName == "") {
                        layer.msg('会员姓名，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (memberGender == "") {
                        layer.msg('会员性别，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (memberAge == "") {
                        layer.msg('会员年龄，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (memberHeight == "") {
                        layer.msg('会员身高，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (memberWeight == "") {
                        layer.msg('会员体重，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (memberPhone == "") {
                        layer.msg('会员电话，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (cardClass == "") {
                        layer.msg('购买课时，岂能为空？', {icon: 5})
                        return false;
                    }
                    if (cardNextClass == "") {
                        layer.msg('剩余课时，岂能为空？', {icon: 5})
                        return false;
                    }

                    //同步数据表格中的数据
                    obj.update({
                        memberAccount: memberAccount,
                        memberName: memberName,
                        memberGender: memberGender,
                        memberAge: memberAge,
                        memberHeight: memberHeight,
                        memberWeight: memberWeight,
                        memberPhone: memberPhone,
                        cardClass: cardClass,
                        cardNextClass: cardNextClass
                    })
                    $.ajax({
                        url: '../../GymMemberEditServlet.do?memberAccount=' + memberAccount + "&memberName=" + memberName + "&memberGender=" + memberGender + "&memberAge=" + memberAge + "&memberHeight=" + memberHeight + "&memberWeight=" + memberWeight + "&memberPhone=" + memberPhone + "&cardClass=" + cardClass + "&cardNextClass=" + cardNextClass,
                        type: 'post',
                        contentType: 'application/json;charset=utf-8',
                        dataType: 'text',
                        data: {
                            memberAccount: memberAccount,
                            memberName: memberName,
                            memberGender: memberGender,
                            memberAge: memberAge,
                            memberHeight: memberHeight,
                            memberWeight: memberWeight,
                            memberPhone: memberPhone,
                            cardClass: cardClass,
                            cardNextClass: cardNextClass
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
                    body.find('#memberAccount').val(data.memberAccount);
                    body.find('#memberName').val(data.memberName);
                    body.find('#memberGender').val(data.memberGender);
                    body.find('#memberAge').val(data.memberAge);
                    body.find('#memberHeight').val(data.memberHeight);
                    body.find('#memberWeight').val(data.memberWeight);
                    body.find('#memberPhone').val(data.memberPhone);
                    body.find('#cardClass').val(data.cardClass);
                    body.find('#cardNextClass').val(data.cardNextClass);
                }
            });
        }
    });
    //监听单元格编辑(选中某一个字段直接更改对应的值)
    table.on('edit(test)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        layer.msg('[ID: ' + data.memberAccount + '] ' + field + ' 字段更改值为：' + value);
        $.ajax({
            url: '../../GymMemberEditServlet.do?memberAccount=' + data.memberAccount + "&memberName=" + data.memberName + "&memberGender=" + data.memberGender + "&memberAge=" + data.memberAge + "&memberHeight=" + data.memberHeight + "&memberWeight=" + data.memberWeight + "&memberPhone=" + data.memberPhone + "&cardClass=" + data.cardClass + "&cardNextClass=" + data.cardNextClass,
            type: 'post',
            contentType: 'application/json;charset=utf-8',
            dataType: 'text',
            data: {
                memberAccount: data.memberAccount,
                memberName: data.memberName,
                memberGender: data.memberGender,
                memberAge: data.memberAge,
                memberHeight: data.memberHeight,
                memberWeight: data.memberWeight,
                memberPhone: data.memberPhone,
                cardClass: data.cardClass,
                cardNextClass: data.cardNextClass
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
    var memberAccount = $('#memberAccount').val();
    var memberName = $('#memberName').val();
    var memberPhone = $('#memberPhone').val();
    var cardTime = $('#cardTime').val();
    var table = layui.table;
    table.reload('testReload', {//二次加载
        url: '../../GymMemberLikeSelectServlet.do?memberAccount=' + memberAccount + "&memberName=" + memberName + "&memberPhone=" + memberPhone + "&cardTime=" + cardTime,
        where: {
            memberAccount: memberAccount,
            memberName: memberName,
            memberPhone: memberPhone,
            cardTime: cardTime
        }
    })
}