function submitClassTable() {
    //获取值
    var className = $('#className').val();
    var classBegin = $('#classBegin').val();
    var classTime = $('#classTime').val();
    var coach = $('#coach').val();
    if (className == "") {
        return false;
    }
    if (classBegin == "") {
        return false;
    }
    if (classTime == "") {
        return false;
    }
    if (coach == "") {
        return false;
    }
    $.ajax({
        url: '../../GymClassTableAddServlet.do',
        type: 'post',
        dataType: 'text',
        data: {
            className: className,
            classBegin: classBegin,
            classTime: classTime,
            coach: coach
        },
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
        elem: '#classBegin' //指定元素
        , type: 'datetime'
        , calendar: true
        , theme: '#393D49'
    });
});