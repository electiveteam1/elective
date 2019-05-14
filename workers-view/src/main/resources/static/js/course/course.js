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
        elem: '#courseLibrary' //指定原始表格元素选择器（推荐id选择器）
        , url: "http://localhost:9009/selectAllCourse?collegeName=软件学院"
        , id: 'courseReload'
        , even: true    //隔行换色
        , cols: [[{
            field: 'courseLibraryName', title: '课程名称'
        }, {
            field: 'classHour', title: '学时'
        }, {
            field: 'credit', title: '学分'
        }, {
            field: 'peacetimePerformanceProportion', title: '平时成绩'
        }, {
            field: 'midTermPerformanceProportion', title: '期中成绩'
        }, {
            field: 'finalPerformanceProportion', title: '期末成绩'
        }, {
            field: 'skillAssessmentProportion', title: '技能考核成绩'
        }, {
            fixed: 'right', align: 'center', title: '操作', toolbar: '#barDemo'
        }]],
        page: true
    });

    //监听行工具事件
    table.on('tool(courseLibrary)', function (obj) {
        var data = obj.data,layer = layui.layer;
        if (obj.event === 'edit') {
            // console.log(data);
            let dataResult = JSON.stringify(data);
            layer.open({
                type: 2,
                area: ['1000px', '550px'],
                fixed: false, //不固定
                maxmin: true,
                offset: 'auto',
                content: 'http://localhost:9009/selectTeacherName?data=' + encodeURIComponent(dataResult)
            });
        }
    });

    $("#searchCourse").click(function () {
        var table = layui.table;
        // console.log($('#CourseName').val());
        //执行重载
        table.reload('courseReload', {
            url: 'http://localhost:9009/selectAllCourse?collegeName=软件学院'
            , where: { //设定异步数据接口的额外参数，任意设
                CourseName: $('#CourseName').val()
            }
            , page: true
        })
    });
})