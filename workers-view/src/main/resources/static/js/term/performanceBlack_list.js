layui.use(['form', 'layer', 'table'], function () {
    var table = layui.table
        , form = layui.form,layer = layui.layer; //弹层
    let teacherInfo =JSON.parse(sessionStorage.getItem("teacherInfo"));
    if(teacherInfo==null){
        location.href = '/toLogin';
        return false;
    }
    table.render({
        elem: '#performanceBlack_list' //指定原始表格元素选择器（推荐id选择器）
        , url: "http://localhost:9009/selectElectiveByStatus?collegeName"
        , id: 'electiveCourseReload'
        , even: true    //隔行换色
        , cols: [[{
            field: 'courseLibraryName', title: '课程名称'
        }, {
            field: 'teacherName', title: '教师名称'
        }, {
            field: 'restrictedGrade', title: '限选年级'
        }, {
            field: 'courseLibraryIntroduction', title: '课程简介'
        }, {
            fixed: 'right', align: 'center', title: '操作', toolbar: '#barDemo'
        }]],
        page: true,
    });

    //监听行工具事件
    table.on('tool(performanceBlack_list)', function (obj) {
        var data = obj.data,layer = layui.layer;
        console.log(data);
        if (obj.event === 'edit') {
            //询问框
            layer.confirm('您要打回这门课程的成绩吗？', {
                btn: ['是的','取消'] //按钮
            }, function(index){
                $.ajax({
                    url: 'http://localhost:9009/updateElectiveCourseStatus',
                    type: "post",
                    data: {
                        electiveCourseId:data.electiveCourseId
                    },success: function (res) {
                        if (res.status == 200) {
                            obj.del();
                            layer.close(index);
                            window.location.reload();
                        } else if (res.status == 420) {
                            layer.msg(res.msg);
                        }
                    }, error: function (data) {
                        layer.msg("打回成绩失败");
                    }
                })
            });
        }
    });
})