import {Tools} from './util/tools.js';

let tool = new Tools();
$("#tip").hide();
$("#username").focus();
$("button").click(function () {
    $("#tip").css("color","black");
    $("#tip").text("正在登录...");
    let username = $("#username").val();
    let pwd = $("#password").val();
    if (username == null || username === '') {
        $("#username").focus();
        $("#tip").show();
        $("#tip").css("color","red");
        $("#tip").text("用户名不能为空！");
        return;
    }
    if (pwd == null || pwd === '') {
        $("#password").focus();
        $("#tip").show();
        $("#tip").css("color","red");
        $("#tip").text("密码不能为空！");
        return;
    }
    let data = {
        username: username,
        password: pwd
    };
    tool.post("/student/view/login", data)
        .then(res => {
            if (res.status !== 200) {
                $("#tip").show();
                $("#tip").css("color","red");
                $("#tip").text(res.msg);
            } else {
                sessionStorage.setItem("user", JSON.stringify(res.data));
                location.href = "index.html";
            }
        }, (res => {
            alert(res);
        }));
});
//回车键登录
$(document).keydown(function (e) {
    if (e.keyCode === 13) {
        $("button").click();
    }
});

