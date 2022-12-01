<!--登录切换特效-->
//滑动的状态
let flag = true
const mySwith = () => {
    if (flag) {
        //获取到滑动盒子的dom元素并修改它移动的位置
        $(".pre-box").css("transform", "translateX(100%)")
        //获取到滑动盒子的dom元素并更改它的背景颜色
        $(".pre-box").css("background-color", "#2D2D2D")
        //修改图片路径
        $("img").attr("src", "pagesend/statics/images/loginLog.png")
    } else {
        $(".pre-box").css("transform", "translateX(0%)")
        $(".pre-box").css("background-color", "#2D2D2D")
        $("img").attr("src", "pagesend/statics/images/background.jpeg")
    }
    flag = !flag
}

//注册
function zhuce() {
    let username = $("input[name='username']").val();
    let password = $("input[name='password']").val();
    let passwords = $("input[name='passwords']").val();
    if (password != passwords) {
        layer.alert('两次密码不一致,请重新输入', {icon: 5});
    } else {
        $.ajax({
            type: 'post',
            url: 'GymAddLogin.do',
            data: {
                'username': username,
                'password': password
            },
            dataType: 'text',
            success: function (res) {
                if (res == "success") {
                    layer.alert('注册成功,3秒后自动跳转登录', {icon: 6});
                    setTimeout(function () {
                        location.href = 'login.html'
                    }, 3000);
                } else {
                    layer.alert('注册失败,已有此用户', {icon: 5});
                }
            }
        })
    }
}

//泡泡特效
/*const bubleCreate = () => {
    const body = document.body
    const buble = document.createElement('span')
    let r = Math.random() * 5 + 25; //得到的半径25~30
    buble.style.width = r + 'px'
    buble.style.height = r + 'px'
    buble.style.left = Math.random() * innerWidth + 'px'
    body.append(buble)
    setTimeout(() => {
        buble.remove()
    }, 4000)
}
setInterval(() => {
    bubleCreate()
}, 200)*/
