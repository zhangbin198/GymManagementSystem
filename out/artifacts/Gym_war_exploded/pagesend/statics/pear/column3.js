layui.use(['echarts'], function () {
    let echarts = layui.echarts;
    var column3 = echarts.init(document.getElementById('column3'), null, {
        width: 600,
        height: 400
    });

    const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']

    option = {
        title: {
            text: '会员私教课程统计',
            left: 'left',
            top: 2,
            fontSize: 20
        },
        backgroundColor: '#fff',
        tooltip: {
            trigger: "axis",
            padding: [8, 10],
            backgroundColor: 'rgba(255,255,255,0.5)',
            axisPointer: {
                type: "shadow",
                color: "#fff"
            }
        },
        legend: {
            data: ['新开会员', '私教课程', '关闭会员'],
            align: 'left',
            right: 0,

            color: "#333",
            fontSize: 14,
            fontWeight: 200,

            itemWidth: 14,
            itemHeight: 14,
            itemGap: 35
        },
        grid: {
            left: '0',
            right: '0',
            bottom: '8%',
            top: '15%',
            containLabel: true
        },
        label: {
            show: true,
            position: 'top',
            color: '#333',
            fontSize: 14,
            fontWeight: 700
        },
        xAxis: [{
            type: 'category',
            offset: 10,
            data: ['4月', '5月', '6月', '7月'],
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                show: true,
                color: "#333",
                fontSize: 16,
                fontWeight: 200

            },
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            splitLine: {
                show: false
            }
        }],
        series: [{
            name: '新开会员',
            type: 'bar',
            data: [320, 400, 150, 199, 230],
            barWidth: 22, //柱子宽度
            barGap: 1, //柱子之间间距
            itemStyle: {
                color: '#0071c8',
                opacity: 1,
            }
        }, {
            name: '私教课程',
            type: 'bar',
            data: [120, 244, 199, 103, 320],
            barWidth: 22,
            barGap: 1,
            itemStyle: {
                color: '#fdc508',
                opacity: 1,
            }
        }, {
            name: '关闭会员',
            type: 'bar',
            data: [30, 44, 18, 20, 6],
            barWidth: 22,
            barGap: 1,
            itemStyle: {
                color: '#dfeafc',
                opacity: 1,
            }
        }]
    };

    column3.setOption(option);

    window.onresize = function () {
        column3.resize();
    }

})
