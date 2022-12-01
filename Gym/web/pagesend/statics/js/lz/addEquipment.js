function submitEquipment() {
    //获取值
    var equipmentName = $('#equipmentName').val();
    var equipmentLocation = $('#equipmentLocation').val();
    var equipmentStatus = $('#equipmentStatus').val();
    var equipmentMessage = $('#equipmentMessage').val();
    if (equipmentLocation == "") {
        return false;
    }
    if (equipmentLocation == "") {
        return false;
    }
    if (equipmentMessage == "") {
        return false;
    }
    $.ajax({
        url: '../../GymEquipmentAddServlet.do',
        type: 'post',
        dataType: 'text',
        data: {
            //获取的数据
            equipmentName: equipmentName,
            equipmentLocation: equipmentLocation,
            equipmentStatus: equipmentStatus,
            equipmentMessage: equipmentMessage,
        },
        //成功或失败的返回
        success: function (res) {
            layer.alert("添加成功!", {icon: 6}, function () {
                location.reload();
            });
        },
        error: function (res) {
            layer.alert('添加失败,请检查!', {icon: 5})
        }
    })
}