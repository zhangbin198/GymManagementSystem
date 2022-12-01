function submitClassOrder() {
    //获取值
    var className = $('#className').val();
    var classPhone = $('#classPhone').val();
    var classTable = $('#classTable').val();
    var classCoach = $('#classCoach').val();
    if (className == "") {
        return false;
    }
    if (classPhone == "") {
        return false;
    }
    if (classTable == "") {
        return false;
    }
    if (classCoach == "") {
        return false;
    }
    //异步获取数据
    $.ajax({
        url: '../../GymClassOrderAddServlet.do',
        type: 'post',
        dataType: 'text',
        data: {
            //获取的参数
            className: className,
            classPhone: classPhone,
            classTable: classTable,
            classCoach: classCoach,
        },
        //成功失败的返回结果
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