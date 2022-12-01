layui.use(['echarts'], function () {
    let echarts = layui.echarts;
    var line2 = echarts.init(document.getElementById('line2'), null, {
        width: 750,
        height: 400
    });

    const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
    option = {
        backgroundColor: '#fff',
        title: {
            text: '客户体脂率分析',
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
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月'],
            axisLine: {
                lineStyle: {
                    color: '#DCE2E8'
                }
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                interval: 0,
                color: '#556677',
                // 默认x轴字体大小
                fontSize: 12,
                // margin:文字到x轴的距离
                margin: 15
            },
            axisPointer: {
                label: {
                    // padding: [11, 5, 7],
                    padding: [0, 0, 10, 0],

                    // 这里的margin和axisLabel的margin要一致!
                    margin: 15,
                    // 移入时的字体大小
                    fontSize: 12,
                    backgroundColor: {
                        type: 'linear',
                        x: 0,
                        y: 0,
                        x2: 0,
                        y2: 1,
                        colorStops: [{
                            offset: 0,
                            color: '#fff' // 0% 处的颜色
                        }, {
                            // offset: 0.9,
                            offset: 0.86,

                            color: '#fff' // 0% 处的颜色
                        }, {
                            offset: 0.86,
                            color: '#33c0cd' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: '#33c0cd' // 100% 处的颜色
                        }],
                        global: false // 缺省为 false
                    }
                }
            },
            boundaryGap: false
        }],
        yAxis: [{
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#DCE2E8'
                }
            },
            axisLabel: {
                color: '#556677',
            },
            splitLine: {
                show: false
            }
        }, {
            type: 'value',
            position: 'right',
            axisTick: {
                show: false
            },
            axisLabel: {
                color: '#556677',
                formatter: '{value}'
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#DCE2E8'
                }
            },
            splitLine: {
                show: false
            }
        }],
        series: [{
            name: '未体测',
            type: 'line',
            data: [533, 688, 723, 432, 338, 273, 120],
            symbolSize: 1,
            symbol: 'circle',
            smooth: true,
            yAxisIndex: 0,
            showSymbol: true,
            lineStyle: {
                width: 5,
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: '#9effff'
                },
                    {
                        offset: 1,
                        color: '#9E87FF'
                    }
                ]),
                shadowColor: 'rgba(158,135,255, 0.3)',
                shadowBlur: 10,
                shadowOffsetY: 20
            },
            itemStyle: {
                color: colorList[0],
                borderColor: colorList[0]
            }
        }, {
            name: '10%以下',
            type: 'line',
            data: [38, 40, 100, 120, 99, 102, 90],
            symbolSize: 1,
            symbol: 'circle',
            smooth: true,
            yAxisIndex: 0,
            showSymbol: true,
            lineStyle: {
                width: 5,
                color: new echarts.graphic.LinearGradient(1, 1, 0, 0, [{
                    offset: 0,
                    color: '#73DD39'
                },
                    {
                        offset: 1,
                        color: '#73DDFF'
                    }
                ]),
                shadowColor: 'rgba(115,221,255, 0.3)',
                shadowBlur: 10,
                shadowOffsetY: 20
            },
            itemStyle: {
                color: colorList[1],
                borderColor: colorList[1]
            }
        },
            {
                name: '10%-20%',
                type: 'line',
                data: [799, 633, 532, 629, 433, 232, 443],
                symbolSize: 1,
                yAxisIndex: 1,
                symbol: 'circle',
                smooth: true,
                showSymbol: true,
                lineStyle: {
                    width: 5,
                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                        offset: 0,
                        color: '#F17373'
                    },
                        {
                            offset: 1,
                            color: '#F8004D'
                        }
                    ]),
                    shadowColor: 'rgba(254,154,139, 0.3)',
                    shadowBlur: 10,
                    shadowOffsetY: 20
                },
                itemStyle: {
                    color: colorList[2],
                    borderColor: colorList[2]
                }
            },
            {
                name: '20%-25%',
                type: 'line',
                data: [600, 433, 655, 433, 233, 102, 70],
                symbolSize: 1,
                yAxisIndex: 1,
                symbol: 'circle',
                smooth: true,
                showSymbol: true,
                lineStyle: {
                    width: 5,
                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                        offset: 0,
                        color: '#F07B94'
                    },
                        {
                            offset: 1,
                            color: '#FACEDB'
                        }
                    ]),
                    shadowColor: 'rgba(254,154,139, 0.3)',
                    shadowBlur: 10,
                    shadowOffsetY: 20
                },
                itemStyle: {
                    color: colorList[3],
                    borderColor: colorList[3]
                }
            },
            {
                name: '25%以上',
                type: 'line',
                data: [411, 199, 299, 422, 100, 50, 102],
                symbolSize: 1,
                yAxisIndex: 1,
                symbol: 'circle',
                smooth: true,
                showSymbol: true,
                lineStyle: {
                    width: 5,
                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                        offset: 0,
                        color: '#FF7E53'
                    },
                        {
                            offset: 1,
                            color: '#FA4E00'
                        }
                    ]),
                    shadowColor: 'rgba(254,154,139, 0.3)',
                    shadowBlur: 10,
                    shadowOffsetY: 20
                },
                itemStyle: {
                    color: colorList[4],
                    borderColor: colorList[4]
                }
            }
        ]
    };

    line2.setOption(option);

    window.onresize = function () {
        line2.resize();
    }

})
