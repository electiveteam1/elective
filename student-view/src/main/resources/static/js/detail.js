import {Tools} from "./util/tools.js";

let tool = new Tools();
$(".header").load("header.html");
let userInfoString = sessionStorage.getItem("user");
let userInfo = JSON.parse(userInfoString);
//从路径获取参数
let courseId = Tools.getUrlParam("courseId");
let tId = Tools.getUrlParam("tid");
let flag = Tools.getUrlParam("flag");
if (courseId === null || courseId === '' || tId === null || tId === '') {
    alert("请选择课程！");
    window.history.back(-1);
}
if (flag !== null || flag !== ''){
    $(".btn").hide();
}

//查询教师详情
tool.get("/student/view/teacher", {username: tId},false)
    .then(res => {
        console.log(res);
        let content = ``;
        if (res.data === null) {

        } else {
            content += `<img src="${res.data.teaPicture == null ? "img/default.jpg" : res.data.teaPicture}">
          <div class="t_detail">
              <div class="item">
                <label for="name">教师名称:</label>
                <span id="name">${res.data.teaName}</span>
              </div>
              <div class="item">
                <label for="phone">手机号:</label>
                <span id="phone">${res.data.teaPhone}</span>
              </div>
              <div class="item">
                <label for="zhicheng">职称:</label>
                <span id="zhicheng">${res.data.teaJobTitle}</span>
              </div>
              <div class="item">
                <label for="jycd">教育程度:</label>
                <span id="jycd">${res.data.teaDegreeEdu}</span>
              </div>
              <div class="item" style="width: 100%;">
                <label for="">教师简介:</label>
                <span>${res.data.remark == null ? "暂无简介" : res.data.remark}</span>
              </div>
            </div>`;
        }
        $(".tacherInfo").append(content);
    }, res => {
        console.log(res);
    });
//查询课程详情
tool.get("/student/view/detail", {electiveCourseId: courseId},false)
    .then(res => {
        console.log(res);
        let content = ``;
        let grade = ``;
        let timeAndPlace = JSON.parse(res.data.timeAndPlace);
        let arr = timeAndPlace.map(item => {
            return item.week + "-" + item.lou + "-" + item.room + "-" + item.times
        });
        $("#group").val(res.data.courseGroupName);
        content = `<div class="introd">
                                <div class="introd1">
                                    <h3>课程简介</h3>
                                </div>
                                <div class="introd2">
                                    <p>${res.data.courseLibraryIntroduction}</p>
                                </div>
                            </div>
                            <div class="introd">
                                <div class="introd1">
                                    <h3>上课详情</h3>
                                </div>

                                <!--<div class="courseDatail">-->
                                <ul class="courseDatail">
                                    <li>任课教师：${res.data.teacherName}</li>
                                    <li>学分：${res.data.credit}</li>
                                    <li>学时：${res.data.classHour}</li>
                                </ul>
                                <h3> 上课时间和地点</h3>
                                <ul class="courseDatail1">
                                    `;
        arr.forEach(item => {
            content += `<li>${item}</li>`;
        });
        content += `</ul>
                            </div>`;
        $(".active").append(content);
        grade += `<div class="introd1">
                                    <h3>考核标准</h3>
                                </div>
                                <div class="cheng">
                                    <table cellspacing="0" cellpadding="10">
                                        <tr>
                                            <th>平时成绩</th>
                                            <th>期中成绩</th>
                                            <th>期末成绩</th>
                                            <th>技能考核</th>
                                        </tr>
                                        <tr>
                                            <td>${res.data.peacetimePerformanceProportion}%</td>
                                            <td>${res.data.midTermPerformanceProportion}%</td>
                                            <td>${res.data.finalPerformanceProportion}%</td>
                                            <td>${res.data.skillAssessmentProportion}%</td>
                                        </tr>
                                    </table>
                                </div>`;
        $("#grade").append(grade);
    }, res => {
        console.log(res);
    });
$(".btn").click(function () {
    let group = $("#group").val();
    console.log(group);
    let myData = {
        token: userInfo.token,
        electiveCourseId: courseId,
        courseGroupName: group
    };
    tool.post("/student/view/getcourse",myData,true)
        .then(res => {
            console.log(res);
            if (res.status === 200) {
                alert("选课成功!")
            } else {
                alert(res.msg)
            }
        },res => {
            console.log(res);
        })
});