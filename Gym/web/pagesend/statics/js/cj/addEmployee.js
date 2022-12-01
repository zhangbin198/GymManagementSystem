function submitEmployee() {
    //获取值
    var employeeName = $('#employeeName').val();
    var employeeGender = $('#employeeGender').val();
    var employeeAge = $('#employeeAge').val();
    var entryTime = $('#entryTime').val();
    var staff = $('#staff').val();
    var employeeMessage = $('#employeeMessage').val();
    if (employeeName == "") {
        return false;
    }
    if (employeeGender == "") {
        return false;
    }
    if (employeeAge == "") {
        return false;
    }
    if (entryTime == "") {
        return false;
    }
    if (staff == "") {
        return false;
    }
    if (employeeMessage == "") {
        return false;
    }
    //ajax异步获取
    $.ajax({
        url: '../../GymEmployeeAddServlet.do',
        type: 'post',
        dataType: 'text',
        data: {
            //获取的数据
            employeeName: employeeName,
            employeeGender: employeeGender,
            employeeAge: employeeAge,
            entryTime: entryTime,
            staff: staff,
            employeeMessage: employeeMessage
        },
        //添加成功或失败的返回
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

layui.use('laydate', function () {
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#entryTime' //指定元素
        , calendar: true
        , type: 'datetime'
        , theme: '#393D49'
    });
});