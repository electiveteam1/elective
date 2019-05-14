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
            res.data.forEach(function (item, index) {
                let x = that.find(".layui-table-box tbody tr[data-index='" + index + "']")[0].childNodes[5];
               if ((item.minNumber/2) > item.currentNumber) {
                    var tr = $(x).css("background-color", "#FF5722").css("color","white");
                }
                // else if (item.currentNumber >=((item.minNumber/2)-3) && item.currentNumber <=((item.minNumber/2)+3)) {
                //     var tr = $(x).css("background-color", "#ffff00");
                // }
            });
        },
        elem: '#courseLibrary' //指定原始表格元素选择器（推荐id选择器）
        , url: "http://localhost:9009/selectAllElectiveCourseInfo?collegeName=软件学院"
        , id: 'electiveCourseReload'
        , even: true    //隔行换色
        , cols: [[{
            field: 'courseLibraryName', title: '课程名称'
        }, {
            field: 'teacherName', title: '教师名称'
        }, {
            field: 'restrictedGrade', title: '限选年级'
        }, {
            field: 'maxNumber', title: '最大人数'
        }, {
            field: 'minNumber', title: '最小人数'
        }, {
            field: 'currentNumber', title: '当前人数',align: 'center'
        }, {
            field: 'courseLibraryIntroduction', title: '课程简介'
        }, {
            fixed: 'right', align: 'center', title: '操作', toolbar: '#barDemo'
        }]],
        page: true,
    });

    //监听行工具事件
    table.on('tool(courseLibrary)', function (obj) {
        var data = obj.data,layer = layui.layer;
        if (obj.event === 'edit') {
            let dataResult = JSON.stringify(data);
            layer.open({
                type: 2,
                area: ['1000px', '550px'],
                fixed: false, //不固定
                maxmin: true,
                offset: 'auto',
                content: 'http://localhost:9009/electiveCourseInfo?data=' + encodeURIComponent(dataResult)
            });
        }else if(obj.event === 'del'){
            //询问框
            layer.confirm('您要停止这门课程吗？', {
                btn: ['是的','取消'] //按钮
            }, function(index){
                $.ajax({
                    url: 'http://localhost:9009/deleteCourse',
                    type: "get",
                    data: {electiveCourseId:data.electiveCourseId},
                    success: function (res) {
                        if (res.status == 200) {
                            obj.del();
                            layer.close(index);
                            window.location.reload();
                        } else if (res.status == 420) {
                            layer.msg(res.msg);
                        }
                    }, error: function (data) {
                        layer.msg("课程停止失败");
                    }
                })
            });
        }
    });

    $("#electiveCourseReload").click(function () {
        var table = layui.table;
        // console.log($('#CourseName').val());
        //执行重载
        table.reload('electiveCourseReload', {
            url: 'http://localhost:9009/selectAllElectiveCourseByCourseOrTeaName'
            , where: { //设定异步数据接口的额外参数，任意设
                CourseName: $('#CourseName').val(),
                collegeName:"软件学院",
                TeacherName:$('#teacherName').val()
            }
            , page: true
        })
    });
})