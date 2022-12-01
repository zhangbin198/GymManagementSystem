layui.use(['echarts'], function () {
    let echarts = layui.echarts;
    var column2 = echarts.init(document.getElementById('column2'), null, {
        width: 600,
        height: 400
    });

    var data = [1000, 600, 500, 300];
    option = {
        backgroundColor: '#ffffff',
        title: {
            text: '各年龄层男丶女会员百分比情况',
            left: 'left',
            top: 2,
            fontSize: 20
        },
        color: ['#fed46b', '#2194ff',],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        legend: {
            left: 'center',
            bottom: '2%',
            data: ['男', '女',]
        },
        xAxis: [{
            type: 'category',
            data: ['40后', '50后', '60后', '70后', '80后', '90后', '00后'],
            axisTick: {
                alignWithLabel: true
            }
        }],
        yAxis: [{
            type: 'value'
        }],
        barMaxWidth: '30',
        label: {
            show: true,
            position: 'top',
            formatter: function (params) {
                return params.value + '%'
            }
        },
        series: [

            {
                name: '男',
                type: 'bar',
                data: [2, 12, 63, 228, 666, 464, 520]
            },
            {
                name: '女',
                type: 'bar',
                data: [1, 10, 53, 110, 765, 675, 430]
            },
        ]
    };
    column2.setOption(option);

    window.onresize = function () {
        column2.resize();
    }

})
