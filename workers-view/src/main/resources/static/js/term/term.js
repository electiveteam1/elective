layui.use('table', function () {
    var table = layui.table
        , form = layui.form;
    let teacherInfo =JSON.parse(sessionStorage.getItem("teacherInfo"));
    if(teacherInfo==null){
        location.href = '/toLogin';
        return false;
    };
    table.render({
        done: function (res, curr, count) {
            var that = this.elem.next();

            for(let i=0;i<res.data.length;i++){
                let item = res.data[i];
                if(item.termStatus==4){
                    $($("[data-field='6']")[i+1]).find("div")[0].innerHTML="暂无操作"
                }
            }
            if (res.data[0].openTime != null) {
                $(".allocation").addClass(" layui-btn-disabled")
            }
            if(teacherInfo.teaPower!="校选"){
                $($("[data-field='6']")[1]).find("div")[0].innerHTML="暂无操作"
            }
        },
        elem: '#term' //指定原始表格元素选择器（推荐id选择器）
        , url: 'http://localhost:9009/selectAllTerm'
        , id: 'termReload'
        ,cellMinWidth: 80
        , even: true    //隔行换色
        , cols: [[{
            field: 'termId', title: '学期编号'
        }, {
            field: 'termName', title: '学期名称'
        }, {
            field: 'openTime', title: '选课开启时间'
        }, {
            field: 'closeTime', title: '选课关闭时间'
        }, {
            field: 'semesterEnds', title: '学期结束时间'
        }, {
            field: 'termStatus', title: '所处状态', templet: '#status'
        }, {
            fixed: 'right', align: 'center', title: '操作', toolbar: '#barDemo'
        }]],
        page: true
    });
    //监听行工具事件
    table.on('tool(term)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            if ($(this).hasClass('layui-btn-disabled')) {
                e.stopPropagation();
                layer.msg("您已配置了规则，不能重复配置哦");
                return false;
            }
            console.log(data.termId);
            layer.open({
                type: 2,
                area: ['600px', '550px'],
                fixed: false, //不固定
                maxmin: true,
                offset: 'auto',
                title: '添加规则',
                content: 'http://localhost:9009/termRuleAdd?termId=' + data.termId
            });
        } else if (obj.event === 'del') {
            let dataResult = JSON.stringify(data);
            layer.open({
                type: 2,
                area: ['600px', '550px'],
                fixed: false, //不固定
                maxmin: true,
                offset: 'auto',
                title: '修改规则',
                content: 'http://localhost:9009/termRule_edit?data=' + encodeURIComponent(dataResult)
            });
        }
    });
    $("#search").click(function () {
        var table = layui.table;
        ////console.log(table);
        //执行重载
        table.reload('termReload', {
            url: '/Back/Goods/selectTimeOrState.do'
            , where: { //设定异步数据接口的额外参数，任意设
                gTime: $('#selTime').val(),
                gStatus: $('#gStatus').val()
            }
            , page: true
        })
    });
})