function submitEquipment() {
    //获取值
    var leavemessageUser = $('#leavemessageUser').val();
    var leavemessageContent = $('#leavemessageContent').val();
    var leavemessagePhone = $('#leavemessagePhone').val();
    if (leavemessageUser == '') {
        return false;
    }
    if (leavemessageContent == '') {
        return false;
    }
    if (leavemessagePhone == '') {
        return false;
    }
    //ajax异步添加
    $.ajax({
        url: '../../GymLeaveMessageAddServlet.do',
        type: 'post',
        dataType: 'text',
        data: {
            //获取添加的数据
            leavemessageUser: leavemessageUser,
            leavemessageContent: leavemessageContent,
            leavemessagePhone: leavemessagePhone,
        },
        //返回添加成功或失败的结果
        success: function (res) {
            layer.alert("添加成功!", {icon: 6}, function () {
                location.reload();
            });
        },
        error: function (res) {
            layer.alert('添加失败，请检查!', {icon: 5});
        }
    })
}