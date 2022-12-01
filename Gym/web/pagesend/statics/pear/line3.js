layui.use(['echarts'], function () {
    let echarts = layui.echarts;

    var line3 = echarts.init(document.getElementById('line3'), null, {
        width: 750,
        height: 400
    });

    const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']

    option = {
        backgroundColor: '#fff',
        title: {
            text: "总收入预览",
            left: "18px",
            top: "0",
            color: "#999",
            fontSize: 12,
            fontWeight: '400'
        },
        color: ['#73A0FA', '#73DEB3', '#FFB761', '#FB4D4D', '#FFBBBB', '#0F00FF'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                },
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        grid: {
            left: '25',
            right: '25',
            bottom: '24',
            top: '75',
            containLabel: true
        },
        legend: {
            data: ['会员卡收入', '私教课收入', '团课收入', '训练营收入', '商品收入', '定金收入'],
            orient: 'horizontal',
            icon: "rect",
            show: true,
            left: 20,
            top: 25,
        },
        xAxis: {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月'],
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                color: '#999',
                fontSize: 12
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#F3F4F4'
                }
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
        },
        series: [{
            name: '会员卡收入',
            type: 'line',
            smooth: true,
            data: [43022.05, 23000.01, 43231.23, 29991.99, 62301.33, 82301.32, 73244.33]
        },
            {
                name: '私教课收入',
                type: 'line',
                smooth: true,
                data: [103232.32, 83131.33, 67372.44, 72333.44, 99913.33, 44232.11, 111211.55]
            },
            {
                name: '团课收入',
                type: 'line',
                smooth: true,
                data: [4222.00, 5200.11, 2500, 3200, 1999, 4500, 6600]
            },
            {
                name: '训练营收入',
                type: 'line',
                smooth: true,
                data: [7000.11, 6500, 10200, 23001, 9000, 19012, 42321.33]
            },
            {
                name: '商品收入',
                type: 'line',
                smooth: true,
                data: [23000.11, 12313.33, 8999.33, 9999.11, 7733, 12232.11, 14032]
            },
            {
                name: '定金收入',
                type: 'line',
                smooth: true,
                data: [3000, 5400, 3600, 5100, 10200, 9050, 5002]
            }
        ]
    };

    line3.setOption(option);

    window.onresize = function () {
        line3.resize();
    }

})
