//添加入账信息
function submitBooked() {
    //获取值
    var billingAccount = $('#billingAccount').val();
    var billName = $('#billName').val();
    var whetherMembers = $('#whetherMembers').val();
    var billMoney = $('#billMoney').val();
    var billNotel = $('#billNotel').val();
    var consumerPlace = $('#consumerPlace').val();
    if (billingAccount == '') {
        return false;
    }
    if (billName == '') {
        return false;
    }
    if (whetherMembers == '') {
        return false;
    }
    if (billMoney == '') {
        return false;
    }
    if (billNotel == '') {
        return false;
    }
    if (consumerPlace == '') {
        return false;
    }
    //异步获取数据
    $.ajax({
        url: '../../GymBookedAddServlet.do',
        type: 'post',
        dataType: 'text',
        data: {
            //获取的数据
            billingAccount: billingAccount,
            billName: billName,
            whetherMembers: whetherMembers,
            billMoney: billMoney,
            billNotel: billNotel,
            consumerPlace: consumerPlace,
        },
        //正确错误返回结果
        success: function (res) {
            layer.alert("添加成功!", {icon: 6}, function () {
                location.reload();
            });
        },
        error: function (res) {
            layer.alert('添加失败，请检查!', {icon: 5})
        }
    })
}