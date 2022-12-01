function submitCoachScore() {
    //获取值
    var studentName = $('#studentName').val();
    var studentSex = $('#studentSex').val();
    var studentClass = $('#studentClass').val();
    var studentScore = $('#studentScore').val();
    if (studentName == '') {
        return false;
    }
    if (studentSex == '') {
        return false;
    }
    if (studentClass == '') {
        return false;
    }
    if (studentScore == '') {
        return false;
    }
    //ajax异步添加
    $.ajax({
        url: '../../GymCoachScoreAdd.do',
        type: 'post',
        dataType: 'text',
        data: {
            //获取添加的数据
            studentName: studentName,
            studentSex: studentSex,
            studentClass: studentClass,
            studentScore: studentScore,
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