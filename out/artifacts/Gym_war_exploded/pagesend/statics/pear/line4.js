layui.use(['echarts'], function () {
    let echarts = layui.echarts;

    var line4 = echarts.init(document.getElementById('line4'), null, {
        width: 750,
        height: 400
    });

    const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
    option = {
        title: {
            text: '客户BMI分析'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['未体测', '偏瘦(18.5以下)', '正常(18.5-23.9)', '偏重(24及以上)']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,//坐标轴两边留白
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月'],
            axisLabel: { //坐标轴刻度标签的相关设置。
                interval: 0,//设置为 1，表示『隔一个标签显示一个标签』
                //	margin:15,

                color: '#1B253A',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12,

                formatter: function (params) {
                    var newParamsName = "";
                    var paramsNameNumber = params.length;
                    var provideNumber = 4;  //一行显示几个字
                    var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                    if (paramsNameNumber > provideNumber) {
                        for (var p = 0; p < rowNumber; p++) {
                            var tempStr = "";
                            var start = p * provideNumber;
                            var end = start + provideNumber;
                            if (p == rowNumber - 1) {
                                tempStr = params.substring(start, paramsNameNumber);
                            } else {
                                tempStr = params.substring(start, end) + "\n";
                            }
                            newParamsName += tempStr;
                        }

                    } else {
                        newParamsName = params;
                    }
                    return newParamsName
                },
                //rotate:50,
            },
            axisTick: {//坐标轴刻度相关设置。
                show: false,
            },
            axisLine: {//坐标轴轴线相关设置
                lineStyle: {
                    color: '#E5E9ED',
                    // opacity:0.2
                }
            },
            splitLine: { //坐标轴在 grid 区域中的分隔线。
                show: true,
                lineStyle: {
                    color: '#E5E9ED',
                    // 	opacity:0.1
                }
            }
        },
        yAxis: [
            {
                type: 'value',
                splitNumber: 5,
                axisLabel: {

                    color: '#a8aab0',
                    fontStyle: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 12

                },
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#E5E9ED',
                        // 	opacity:0.1
                    }
                }

            }
        ],
        series: [
            {
                name: '未体测',
                type: 'line',
                itemStyle: {
                    color: '#3A84FF',
                    lineStyle: {
                        color: "#3A84FF",
                        width: 1
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                            offset: 0,
                            color: 'rgba(58,132,255,0)'
                        }, {
                            offset: 1,
                            color: 'rgba(58,132,255,0.35)'
                        }]),
                    }
                },
                data: [1123, 803, 771, 881, 622, 322, 102]
            },
            {
                name: '偏瘦(18.5以下)',
                type: 'line',
                itemStyle: {
                    color: 'rgba(255,80,124,1)',
                    lineStyle: {
                        color: "rgba(255,80,124,1)",
                        width: 1
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                            offset: 0,
                            color: 'rgba(255,80,124,0)'
                        }, {
                            offset: 1,
                            color: 'rgba(255,80,124,0.35)'
                        }]),
                    }
                },
                data: [722, 691, 503, 391, 633, 321, 300]
            },
            {
                name: '正常(18.5-23.9)',
                type: 'line',
                itemStyle: {
                    color: 'rgba(0,255,255,1)',
                    lineStyle: {
                        color: "rgba(0,200,255,1)",
                        width: 1
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                            offset: 0,
                            color: 'rgba(255,80,124,0)'
                        }, {
                            offset: 1,
                            color: 'rgba(65,105,225,35)'
                        }]),
                    }
                },
                data: [991, 900, 877, 731, 666, 671, 1050]
            },
            {
                name: '偏重(24及以上)',
                type: 'line',
                itemStyle: {
                    color: 'rgba(255,0,255,1)',
                    lineStyle: {
                        color: "rgba(238,130,238,1)",
                        width: 1
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                            offset: 0,
                            color: 'rgba(221,160,221,0)'
                        }, {
                            offset: 1,
                            color: 'rgba(221,160,221,0.35)'
                        }]),
                    }
                },
                data: [403, 371, 320, 243, 190, 320, 179]
            }
        ]
    };


    line4.setOption(option);

    window.onresize = function () {
        line4.resize();
    }

})
