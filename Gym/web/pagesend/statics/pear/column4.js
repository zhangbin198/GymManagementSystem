layui.use(['echarts'], function () {
    let echarts = layui.echarts;
    var column4 = echarts.init(document.getElementById('column4'), null, {
        width: 600,
        height: 400
    });
    option = {
        backgroundColor: '#fff',
        title: {
            text: "2022年会员增长人数",
            top: 10,
            left: 15,
            color: "#35598d",
            fontSize: 16,
            fontWeight: 'normal'
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{b}：{c}',
        },
        grid: {
            left: '5%',
            right: '6%',
            bottom: '3%',
            top: '20%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月'],
            axisLabel: {          //坐标轴字体颜色
                color: '#9eaaba'
            },
            axisLine: {
                lineStyle: {
                    color: "#e5e5e5"
                }
            },
            axisTick: {       //y轴刻度线
                show: false
            },
            splitLine: {    //网格
                show: false,
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {        //坐标轴字体颜色
                color: '#9eaaba'
            },
            axisLine: {
                show: false,
            },
            axisTick: {       //y轴刻度线
                show: false
            },
            splitLine: {    //网格
                show: true,
                lineStyle: {
                    color: '#dadde4',
                    type: "dashed" //坐标网线类型
                }
            }
        },
        series: {
            name: '',
            type: 'bar',
            barWidth: '40%',  //柱子宽度
            itemStyle: {  //柱子颜色
                borderWidth: 2,
                borderColor: 'rgb(79, 116, 223)',
                color: 'rgba(79, 116, 223, .3)',
            },
            data: [700, 890, 954, 1203, 1100, 1050, 1400]
        }
    };

    column4.setOption(option);

    window.onresize = function () {
        column4.resize();
    }

})
