function submitMember() {
    //获取值
    var memberName = $('#memberName').val();
    var memberGender = $('#memberGender').val();
    var memberAge = $('#memberAge').val();
    var memberHeight = $('#memberHeight').val();
    var memberWeight = $('#memberWeight').val();
    var memberPhone = $('#memberPhone').val();
    var cardClass = $('#cardClass').val();
    var cardNextClass = $('#cardNextClass').val();
    if (memberName == '') {
        return false;
    }
    if (memberGender == '') {
        return false;
    }
    if (memberAge == '') {
        return false;
    }
    if (memberHeight == '') {
        return false;
    }
    if (memberWeight == '') {
        return false;
    }
    if (memberPhone == '') {
        return false;
    }
    if (cardClass == '') {
        return false;
    }
    if (cardNextClass == '') {
        return false;
    }
    $.ajax({
        url: '../../GymMemberAddServlet.do',
        type: 'post',
        dataType: 'text',
        data: {
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
            if (res == 'success') {
                layer.alert("添加成功!", {icon: 6}, function () {
                    location.reload();
                });
            }
        },
        error: function (res) {
            layer.alert("添加失败，请检查!", {icon: 5});
        }
    })
}