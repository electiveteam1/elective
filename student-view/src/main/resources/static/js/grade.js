import {Tools} from "./util/tools.js";
let tool = new Tools();
$(".header").load("header.html");
let userInfoString = sessionStorage.getItem("user");
let userInfo = JSON.parse(userInfoString);
let data = {
    token: userInfo.token,
    term: 'all'
};
show(data).then(res => {
    console.log(res);
    let content = ``;
    if (res.status === 200) {
        res.data.forEach(item => {
            content += `<tr>
                        <td ${item.passFlag === 0 ? "style='background: #ff6559;color: white;'" : ""}>${item.courseLibraryName}</td>
                        <td>${item.peacetimePerformance}(${item.peacetimePerformanceProportion}%)</td>
                        <td>${item.midTermPerformance}(${item.midTermPerformanceProportion}%)</td>
                        <td>${item.finalPerformance}(${item.finalPerformanceProportion}%)</td>
                        <td>${item.skillAssessment}(${item.skillAssessmentProportion}%)</td>
                        <td>${item.totalScore}</td>
                        <td>${item.credit}</td>
                        <td>${item.teacherName}</td>
                    </tr>`;
        });
        content += `</table>`;
        $("#mygrade").append(content);
    } else {
        alert(res.msg);
        location.href = "index.html";
    }
},res => {
    console.log(res);
});
$("#mybtn").click(function () {
    // console.log($("#filterTerm").val());
    $("#mygrade tr:not(:first)").html("");
    let myData = {
        token: userInfo.token,
        term: $("#filterTerm").val()
    };
    show(myData).then(res => {
        console.log(res);
        let content = ``;
        if (res.status === 200) {
            res.data.forEach(item => {
                content += `<tr>
                        <td ${item.passFlag === 0 ? "style='background: #ff6559;color: white;'" : ""}>${item.courseLibraryName}</td>
                        <td>${item.peacetimePerformance}(${item.peacetimePerformanceProportion}%)</td>
                        <td>${item.midTermPerformance}(${item.midTermPerformanceProportion}%)</td>
                        <td>${item.finalPerformance}(${item.finalPerformanceProportion}%)</td>
                        <td>${item.skillAssessment}(${item.skillAssessmentProportion}%)</td>
                        <td>${item.totalScore}</td>
                        <td>${item.credit}</td>
                        <td>${item.teacherName}</td>
                    </tr>`;
            });
            content += `</table>`;
            $("#mygrade").append(content);
        } else {
            alert(res.msg);
            location.href = "index.html";
        }
    },res => {
        console.log(res);
    });
});
function show(data) {
    return tool.get("/student/view/grade",data, false);
}