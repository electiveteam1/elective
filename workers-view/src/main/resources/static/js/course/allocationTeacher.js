import {Common} from "/js/DATA_JSON.js"
layui.use(['form', 'element', 'layer'], function () {
    var form = layui.form
        , layer = layui.layer;
    //调用json数据
    let common=new Common();
    let weeks = common.getWeeks();
    let times = common.getTimes();
    let getRestrictedGrade = common.getRestrictedGrade();
    $(document).ready(function () {
        let teacherInfo =JSON.parse(sessionStorage.getItem("teacherInfo"));
        if(teacherInfo==null){
            location.href = '/toLogin';
            return false;
        }
        let BasicsInfo = JSON.parse($("#dataInfo").val());
        let basics = ` <tr>
                                    <td>课程名称:${BasicsInfo.courseLibraryName}</td>
                                    <td>学时:${BasicsInfo.classHour}</td>
                                    <td>学分:${BasicsInfo.credit}</td>
                                    <td>平时成绩:${BasicsInfo.peacetimePerformanceProportion}</td>
                                    <td>期中成绩:${BasicsInfo.midTermPerformanceProportion}</td>
                                    <td>期末成绩:${BasicsInfo.finalPerformanceProportion}</td>
                                    <td>技能考核:${BasicsInfo.skillAssessmentProportion}</td>
                                </tr>`;
        $("#basics").append(basics);

        for (let results of weeks) {
            $(".week").append(new Option(results.key, results.value));
        }
        for (let results of times) {
            $(".times").append(new Option(results.key, results.value));
        }
        for (let results of getRestrictedGrade) {
            $(".restrictedGrade").append(new Option(results.key, results.value));
        }
        layui.element.render();
        form.render();
        lou();
        updateLou();
        MaxPerson();
        selectTeacherName();
    });
    let buildingNUmber = null, i = "";
    //监听提交
    $("body").on('click', "#sub", function (data) {
        let datas = JSON.parse($("#dataInfo").val());
        console.log(datas)
        let JsonDate = [], time_and_place = [];
        let courseLibraryId = datas.courseLibraryId, courseLibraryName = datas.courseLibraryName,RoomMinNumber=0,
            courseGroupName = datas.courseGroupName;
        let max = $(this).parents("#lists").find(".courseInfo");
        for (let i = 0; i < max.length; i++) {
            if($(max[i]).find(".teacherName option:checked").text()=='请选择教师'){
                layer.msg("教师姓名不能为空哦");
                return false;
            }else if ($(max[i]).find(".restrictedGrade option:checked").val()==""){
                layer.msg("限选年级不能为空哦");
                return false;
            }else if ($(max[i]).find("textarea").val()==""){
                layer.msg("课程简介不能为空哦");
                return false;
            }else if ($(max[i]).find(".minNumber").val()==""){
                layer.msg("最小人数不能为空哦");
                return false;
            }
            let size = $(max[i]).find(".layui-collapse").length;
            for (let j = 0; j < size; j++) {
                let info = $($(max[i]).find(".layui-collapse")[j]);
                if (info.find(".week option:checked").val()==""){
                    layer.msg("星期不能为空哦");
                    return false;
                }else if (info.find(".times option:checked").val()==""){
                    layer.msg("上课节数不能为空哦");
                    return false;
                }else if (info.find(".lou").val()==""){
                    layer.msg("楼号不能为空哦");
                    return false;
                }else if (info.find(".classRoom").val()==""){
                    layer.msg("上课教室不能为空哦");
                    return false;
                }else if (info.find(".maxNumber").val()==""){
                    layer.msg("最大人数不能为空哦");
                    return false;
                }
                let x = {
                    "week": info.find(".week option:checked").val(),
                    "times": info.find(".times option:checked").val(),
                    "lou": info.find(".lou").val(),
                    "classRoom": info.find(".classRoom").val()
                };
                time_and_place.push(x);
                let RoomMaxNumber=info.find(".maxNumber").val();
                if(RoomMinNumber==0){
                    RoomMinNumber=RoomMaxNumber;
                }else if( RoomMinNumber>RoomMaxNumber){
                    RoomMinNumber=info.find(".maxNumber").val();
                }
            }
            // console.log(RoomMinNumber);
            let optionFlag = null;
            if (i == 0) {
                optionFlag = 1;
            } else {
                optionFlag = 0;
            }
            let maxInfo = {
                "teacherId": $(max[i]).find(".teacherName option:checked").val(),
                "teacherName": $(max[i]).find(".teacherName option:checked").text(),
                "courseLibraryId": courseLibraryId,
                "courseLibraryName": courseLibraryName,
                "courseGroupName": courseGroupName,
                "restrictedGrade": $(max[i]).find(".restrictedGrade option:checked").val(),
                "maxNumber": RoomMinNumber,
                "minNumber": $(max[i]).find(".minNumber").val(),
                "timeAndPlace": time_and_place,
                "classHour": datas.classHour, "credit": datas.credit,
                "courseLibraryIntroduction": $(max[i]).find("textarea").val(),
                "collegeName": datas.collegeName,
                "priority": (i + 1),
                "deleteFlag": 0, "electiveCourseStatus": 0, optionFlag: optionFlag, term_id: "",remark:$(".teacherName option:checked")[0].title
            };
            JsonDate.push(maxInfo);
            time_and_place = null;
        }
        $.ajax({
            type: "post",
            url: 'http://localhost:9009/insertCourse',
            data: {
                JsonDate: JSON.stringify(JsonDate)
            },
            async: false,
            success: function (res) {
                // console.log(res);
                if (res.status == 200) {
                    //发异步，把数据提交给php
                    layer.alert("配置成功", {icon: 6}, function (index) {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                } else if (res.status == 420) {
                    layer.msg(res.msg)
                }
            }, error: function () {
                layer.msg("数据格式错误");
            }
        });
    });
    //加载楼号
    function lou() {
        $.ajax({
            url: 'http://localhost:9009/selectAllRoom',
            type: 'get',
            data: {},
            success: function (res) {
                // console.log(res.data);
                $("select[name=lou]").html(" <option value=\"\" selected>请选择楼号</option>");
                for (let results of res.data) {
                    $(".lou").append(new Option(results.crLocation, results.crLocation));
                }
                layui.element.render();
                form.render();
            }, error: function (res) {
            }
        });
    }

    //楼号改变时触发
    function updateLou() {
        form.on("select(business)", function (data) {
            buildingNUmber = data.value;
            $.ajax({
                url: 'http://localhost:9009/selectAllClazzRoomByLou',
                type: 'get',
                data: {
                    crLocation: buildingNUmber
                },
                success: function (res) {
                    // console.log(res.data);
                    let clazz = $($(data.elem).parents(".layui-colla-item").find(".classRoom").prevObject[0]).find(".classRoom");
                    clazz.html(" <option value=\"\" selected>请选择上课教室</option>");
                    for (let results of res.data) {
                        clazz.append(new Option(results.crRoomnum, results.crRoomnum));
                    }
                    layui.element.render();
                    form.render();
                }, error: function (res) {
                }
            });
        });
    };

    //教室改变时加载最大人数
    function MaxPerson() {
        form.on("select(MaxPerson)", function (data) {
            // console.log($($(data.elem).parents(".layui-colla-item").find(".maxNumber").prevObject[0]).find(".maxNumber"));
            $.ajax({
                url: 'http://localhost:9009/selectAllClazzRoomByLouAndRoom',
                type: 'get',
                data: {
                    crLocation: buildingNUmber,
                    crRoomNum: data.value
                },
                success: function (res) {
                    let maxNumber = $($(data.elem).parents(".layui-colla-item").find(".maxNumber").prevObject[0]).find(".maxNumber");
                    maxNumber.val(res.data.crMaxstunum);
                }, error: function (res) {
                }
            });
        });
    };

    //查询教师姓名
    function selectTeacherName() {
        $.ajax({
            url: 'http://localhost:9009/selectAllTeacherName',
            type: 'get',
            data: {},
            success: function (res) {
                $("select[name=teacherName]").html(" <option value=\"\" selected>请选择教师</option>");
                for (let results of res.data) {
                    $(".teacherName").append(`<option value="${results.workNumber}" title="${results.teaPicture}">${results.teaName}</option>`);
                }
                layui.element.render();
                form.render();
            }, error: function () {

            }
        });
    };
    //配置教师
    $(".teacherAdd").on('click', function (e) {
        let size = $(this).parents(".layui-card").find(".layui-collapse").length;
        for (let i = 0; i < size; i++) {
            $(this).parents(".layui-card").find(".layui-collapse").find(".layui-colla-content").removeClass("layui-show");
        }
        i = $(".layui-collapse").length;
        let x = `<div class="layui-collapse courseInfo" lay-filter="test" lay-accordion="">
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">配置教师</h2>
                                    <div class="layui-colla-content layui-show">
                                        <!--选择教师-->
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">
                                                <span class="x-red">*</span>请选择教师
                                            </label>
                                            <div class="layui-input-inline">
                                                <select name="teacherName" class="teacherName" lay-verify="required" lay-search>
                                                    <option value="" selected>选择或输入...</option>
                                                </select>
                                            </div>
                                            <label class="layui-form-label">
                                                <span class="x-red">*</span>限选年级
                                            </label>
                                            <div class="layui-input-inline">
                                                <select name="restrictedGrade" class="restrictedGrade" lay-verify="required" lay-search>
                                                    <option value="" selected>请选择年级</option> 
                                                </select>
                                            </div>
                                            <div class="layui-input-inline add_course_details">
                                                <button class="layui-btn layui-btn-normal">添加上课详情</button>
                                            </div>
                                        </div>
                                        <div class="layui-form-item" style="width: 84%;padding-left: 9px;">
                                                <textarea name="courseLibraryIntroduction" placeholder="请输入课程简介" class="layui-textarea"></textarea>
                                        </div>
                                        <div class="layui-form-item">
                                            <!--配置多课程详情-->
                                            <div class="layui-collapse" lay-filter="test" lay-accordion="">
                                                <div class="layui-colla-item">
                                                    <h2 class="layui-colla-title">配置课程详情</h2>
                                                    <div class="layui-colla-content layui-show">
                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>请选择星期
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="week" class="week" lay-verify="required" lay-search>
                                                                    <option value="" selected>请选择星期</option>
                                                                </select>
                                                            </div>
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>请选择节数
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="times" class="times" lay-verify="" lay-search>
                                                                    <option value="" selected>请选择第几节课</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>请选择楼号
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="lou" class="lou" lay-filter="business">
                                                                    <option value="" selected>请选择上课的楼</option>
                                                                </select>
                                                            </div>

                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>上课教室
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="classRoom" class="classRoom" lay-filter="MaxPerson">
                                                                    <option value="" selected>请选择上课教室</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>最大人数
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <input type="text" name="maxNumber" required lay-verify="required" placeholder="请填写最大人数" autocomplete="off" class="layui-input maxNumber">
                                                            </div>
                                                            <div class="layui-form-mid layui-word-aux" style="font-size:13px">将从所有的配置中选取最小人数作为最大容量哦</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="course_details"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
        $(".add_teacher").append(x);
        layui.element.render();
        form.render();
        //配置教师
        let allocationTeacher = $(this).parents(".layui-card").find(".add_teacher").find(".courseInfo");
        // console.log($(allocationTeacher[allocationTeacher.length-1]).find(".layui-collapse"));
        $.ajax({
            url: 'http://localhost:9009/selectAllRoom',
            type: 'get',
            data: {},
            success: function (res) {
                // console.log(res.data);
                $(allocationTeacher[allocationTeacher.length - 1]).find(".layui-collapse").find(".lou").html(" <option value=\"\" selected>请选择楼号</option>");
                for (let results of res.data) {
                    $(allocationTeacher[allocationTeacher.length - 1]).find(".layui-collapse").find(".lou").append(new Option(results.crLocation, results.crLocation));
                }
                layui.element.render();
                form.render();
            }, error: function (res) {
            }
        });
        updateLou();
        MaxPerson();
        $.ajax({
            url: 'http://localhost:9009/selectAllTeacherName',
            type: 'get',
            data: {},
            success: function (res) {
                $(allocationTeacher[allocationTeacher.length - 1]).find(".teacherName").html(" <option value=\"\" selected>请选择教师</option>");
                for (let results of res.data) {
                    $(allocationTeacher[allocationTeacher.length - 1]).find(".teacherName").append(new Option(results.teaName, results.workNumber));
                }
                layui.element.render();
                form.render();
            }, error: function () {

            }
        });
        for (let results of weeks) {
            $(allocationTeacher[allocationTeacher.length - 1]).find(".layui-collapse").find(".week").append(new Option(results.key, results.value));
        }
        for (let results of times) {
            $(allocationTeacher[allocationTeacher.length - 1]).find(".layui-collapse").find(".times").append(new Option(results.key, results.value));
        }
        for (let results of getRestrictedGrade) {
            $(allocationTeacher[allocationTeacher.length - 1]).find(".restrictedGrade").append(new Option(results.key, results.value));
        }
        layui.element.render();
        form.render();
    });
    //配置课程详情
    $("body").on('click', ".add_course_details", function (e) {
        let size = $(this).parents(".layui-colla-item").find(".layui-colla-item").length;
        for (let i = 0; i < size; i++) {
            $(this).parents(".layui-colla-content").find(".layui-colla-content").removeClass("layui-show");
        }
        let courseDetails = ` <div class="layui-collapse" lay-filter="test" lay-accordion="">
                                                <div class="layui-colla-item">
                                                    <h2 class="layui-colla-title">配置课程详情</h2>
                                                    <div class="layui-colla-content layui-show">
                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>请选择星期
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="week" class="week" lay-verify="required" lay-search>
                                                                    <option value="" selected>请选择星期</option>
                                                                </select>
                                                            </div>
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>请选择节数
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="times" class="times" lay-verify="" lay-search>
                                                                    <option value="" selected>请选择第几节课</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>请选择楼号
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="lou" class="lou" lay-filter="business">
                                                                    <option value="" selected>请选择上课的楼</option>
                                                                </select>
                                                            </div>

                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>上课教室
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <select name="classRoom" class="classRoom" lay-filter="MaxPerson">
                                                                    <option value="" selected>请选择上课教室</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label">
                                                                <span class="x-red">*</span>最大人数
                                                            </label>
                                                            <div class="layui-input-inline">
                                                                <input type="text" name="maxNumber" required lay-verify="required" placeholder="请填写最大人数" autocomplete="off" class="layui-input maxNumber">
                                                            </div>
                                                            <div class="layui-form-mid layui-word-aux" style="font-size:13px">将从所有的配置中选取最小人数作为最大容量哦</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>`;
        $(".course_details").append(courseDetails);
        layui.element.render();
        form.render();
        let allocationLou = $(this).parents(".layui-colla-content").find(".layui-colla-content");
        // console.log($(allocationLou[allocationLou.length-1]));
        $.ajax({
            url: 'http://localhost:9009/selectAllRoom',
            type: 'get',
            data: {},
            success: function (res) {
                $(allocationLou[allocationLou.length - 1]).find(".lou").html(" <option value=\"\" selected>请选择楼号</option>");
                for (let results of res.data) {
                    $(allocationLou[allocationLou.length - 1]).find(".lou").append(new Option(results.crLocation, results.crLocation));
                }
                layui.element.render();
                form.render();
            }, error: function (res) {
            }
        });
        updateLou();
        MaxPerson();
        for (let results of weeks) {
            $(allocationLou[allocationLou.length - 1]).find(".week").append(new Option(results.key, results.value));
        }
        for (let results of times) {
            $(allocationLou[allocationLou.length - 1]).find(".times").append(new Option(results.key, results.value));
        }
        layui.element.render();
        form.render();
    });
});