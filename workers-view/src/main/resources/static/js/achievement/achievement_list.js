layui.use('laydate', function () {
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#selTime' //指定元素
    });
});

layui.use(['form', 'layer', 'table'], function () {
    var table = layui.table
        , form = layui.form,layer = layui.layer; //弹层
    let teacherInfo =JSON.parse(sessionStorage.getItem("teacherInfo"));
    if(teacherInfo==null){
        location.href = '/toLogin';
        return false;
    }
    table.render({
        done: function(res, curr, count){
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            // console.log(res.data);
            var that = this.elem.next();
            // console.log(that)
            res.data.forEach(function (item, index) {
                if (item.passFlag==0) {
                    var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("background-color", "#FFA500").css("color","white");
                }
            });
        },
        elem: '#courseLibrary' //指定原始表格元素选择器（推荐id选择器）
        , url: "http://localhost:9009/selectAllPerformance?collegeName=软件学院"
        , id: 'courseReload'
        , even: true    //隔行换色
        , cols: [[{
            field: 'studentId', title: '学号'
        }, {
            field: 'studentName', title: '姓名'
        }, {
            field: 'peacetimePerformance', title: '平时成绩'
        }, {
            field: 'midTermPerformance', title: '期中成绩'
        }, {
            field: 'finalPerformance', title: '期末成绩'
        }, {
            field: 'skillAssessment', title: '技能考核成绩'
        }, {
            field: 'totalScore', title: '总成绩'
        }, {
            field: 'passFlag', title: '是否及格',templet: '#passFlag'
        }]],
        page: true
    });

    $("#searchPerformance").click(function () {
        var table = layui.table;
        //执行重载
        table.reload('courseReload', {
            url: 'http://localhost:9009/selectPerformanceByIdOrName'
            , where: { //设定异步数据接口的额外参数，任意设
                studentId: $('#stuId').val(),
                studentName:$('#StuName').val(),
                collegeName:'软件学院'
            }
            , page: true
        })
    });
})