layui.use(['echarts'], function () {
    let echarts = layui.echarts;
    var line1 = echarts.init(document.getElementById('line1'), null, {
        width: 750,
        height: 400
    });

    const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
    option = {
        backgroundColor: '#fff',
        title: {
            text: '各时间段健身房人数统计',
            fontSize: 12,
            fontWeight: 400,
            left: 'left',
            top: '5%'
        },
        legend: {
            icon: 'circle',
            top: '5%',
            right: '5%',
            itemWidth: 6,
            itemGap: 20,
            color: '#556677'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                label: {
                    show: true,
                    backgroundColor: '#fff',
                    color: '#556677',
                    borderColor: 'rgba(0,0,0,0)',
                    shadowColor: 'rgba(0,0,0,0)',
                    shadowOffsetY: 0
                },
                lineStyle: {
                    width: 0
                }
            },
            backgroundColor: '#fff',
            color: '#5c6c7c',
            padding: [10, 10],
            extraCssText: 'box-shadow: 1px 0 2px 0 rgba(163,163,163,0.5)'
        },
        grid: {
            top: '15%'
        },
        xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                margin: 10,
                fontSize: 14,
                color: 'rgba(#999)'
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#939ab6',
                    opacity: .15
                }
            },
            data: ['8:00-12:00', '12:00-14:00', '14:00-17:00', '17:00-22:00']
        },],
        yAxis: [{
            type: 'value',
            offset: 15,
            max: 2000,
            min: 0,
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                margin: 10,
                fontSize: 14,
                color: '#999'

            },
            splitLine: {
                show: false
            }

        }],
        series: [{
            name: '男',
            type: 'line',
            z: 3,
            showSymbol: false,
            smoothMonotone: 'x',
            lineStyle: {
                width: 3,
                color: {
                    type: 'linear',
                    x: 0,
                    y: 0,
                    x2: 0,
                    y2: 1,
                    colorStops: [{
                        offset: 0, color: 'rgba(59,102,246)' // 0% 处的颜色
                    }, {
                        offset: 1, color: 'rgba(118,237,252)' // 100% 处的颜色
                    }]
                },
                shadowBlur: 4,
                shadowColor: 'rgba(69,126,247,.2)',
                shadowOffsetY: 4
            },
            areaStyle: {
                color: {
                    type: 'linear',
                    x: 0,
                    y: 0,
                    x2: 0,
                    y2: 1,
                    colorStops: [{
                        offset: 0, color: 'rgba(227,233,250,.9)' // 0% 处的颜色
                    }, {
                        offset: 1, color: 'rgba(248,251,252,.3)' // 100% 处的颜色
                    }]
                }
            },
            smooth: true,
            data: [899, 603, 1050, 1200]
        }, {
            name: '女',
            type: 'line',
            showSymbol: false,
            smoothMonotone: 'x',

            lineStyle: {
                width: 3,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(255,84,108)'
                }, {
                    offset: 1,
                    color: 'rgba(252,140,118)'
                }], false),
                shadowBlur: 4,
                shadowColor: 'rgba(253,121,128,.2)',
                shadowOffsetY: 4
            },
            areaStyle: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(255,84,108,.15)'
                }, {
                    offset: 1,
                    color: 'rgba(252,140,118,0)'
                }], false),
            },
            smooth: true,
            data: [677, 403, 988, 1133]
        }
        ]

    };

    line1.setOption(option);

    window.onresize = function () {
        line1.resize();
    }

})
