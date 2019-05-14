import {Common} from "/js/DATA_JSON.js";

let common = new Common();
let collegeName = common.getCollegeName();
for (let results of collegeName) {
    $("#collegeName").append(new Option(results.key, results.value));
}
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
let courseName = [], teacherNames = [];
var option = {};
var colorList = [
    '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
    '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
    '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
    '#FFB7DD', '#660077', '#FFCCCC', '#FFC8B4', '#550088',
    '#FFFFBB', '#FFAA33', '#99FFFF', '#CC00CC', '#FF77FF',
    '#CC00CC', '#C63300', '#F4E001', '#9955FF', '#66FF66',
    '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
    '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
    '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
    '#FFB7DD', '#660077', '#FFCCCC', '#FFC8B4', '#550088',
    '#FFFFBB', '#FFAA33', '#99FFFF', '#CC00CC', '#FF77FF',
    '#CC00CC', '#C63300', '#F4E001', '#9955FF', '#66FF66'
];
let labelOption = {
    normal: {
        show: true,
        position: 'top',
        rotate: 90,
        align: 'left',
        verticalAlign: 'middle',
        distance: 10,
        formatter: '{c}',// {a}
        fontSize: 16,
        rich: {
            name: {
                textBorderColor: '#fff'
            }
        }
    }
};
let series = [], defaultCollegeName;
$("#warp").on('click', "#search", function () {
    if ($("#collegeName").val() != "请选择学院") {
        defaultCollegeName = $("#collegeName").val();
    }
    COLLEGE_COURSE(defaultCollegeName);
});

function COLLEGE_COURSE() {
    $.ajax({
        url: 'http://localhost:9009/selectTeacherSdNumberByTermId',
        type: 'get',
        async: false,
        data: {
            termId: 1, collegeName: defaultCollegeName
        },
        success: function (res) {
            if (res.status == 200 && res.data != null) {
                myChart.clear();
                series = [];
                teacherNames = [];
                collegeName = [];
                let nums = [], teachername = null;
                //所有课程
                courseName = res.data[0].courseLibraryName.split(',');
                // console.log(res.data);
                for (let i = 0; i < res.data.length; i++) {
                    let results = res.data[i];
                    nums = results.teacherId.split(',');
                    teachername = results.teacherName;
                    //教师名称
                    teacherNames.push(results.teacherName);
                    series.push({
                        name: teachername,
                        data: nums,
                        type: 'bar',
                        label: labelOption,
                        barGap: 0
                    });
                }
                // console.log(series);
                option = {
                    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: teacherNames
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {show: false},
                            name: '课程名称',
                            axisLabel: {
                                interval: 0
                            },//
                            data: courseName
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '人数'
                        }
                    ],
                    series: series
                };
                myChart.setOption(option, true);
            }
        }, error: function () {
            layer.msg("请求数据错误");
            myChart.hideLoading();
        }
    })
}