<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册 - layuiAdmin</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/layui/style/admin.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/layui/style/login.css" media="all">
    <script src="${ctx}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script>
        var ctx = "${ctx}";  //全局js项目路径
    </script>
</head>
<body>


<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>个性化音乐推荐系统</h2>
            <p> 个性化音乐推荐系统--PC端注册页面</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <form id="regForm" action="#" onsubmit="return false;">
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone"
                           for="LAY-user-login-cellphone"></label>
                    <input type="text" name="phone" id="LAY-user-login-cellphone" lay-verify="phone"
                           placeholder="手机"
                           class="layui-input">
                </div>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone"
                           for="LAY-user-login-email"></label>
                    <input type="text" name="email" id="LAY-user-login-email" lay-verify="email" placeholder="邮箱"
                           class="layui-input">
                </div>
                <div class="layui-form-item">
                    <div class="layui-row">
                        <div class="layui-col-xs7">
                            <label class="layadmin-user-login-icon layui-icon layui-icon-user"
                                   for="picurl"></label>
                            <input id="myfile" type="file" name="myfile" style="display: none;"
                                   onchange="uploadFileUtil('myfile','picurl')">
                            <input type="text" name="picurl" id="picurl" lay-verify="required"
                                   placeholder="请选择用户头像" class="layui-input">
                        </div>
                        <div class="layui-col-xs5">
                            <div style="margin-left: 10px;">
                                  <span onclick="uploadFileUtilClick('myfile')" class="layui-btn"><i
                                          class="layui-icon"></i>上传头像&nbsp;&nbsp;</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-row">
                        <div class="layui-col-xs7">
                            <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
                                   for="LAY-user-login-vercode"></label>
                            <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required"
                                   placeholder="验证码" class="layui-input">
                        </div>
                        <div class="layui-col-xs5">
                            <div style="margin-left: 10px;">
                                <img src="${ctx}/pc/code" class="layadmin-user-login-codeimg"
                                     id="LAY-user-get-vercode" onclick="this.src=this.src+'?'">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                           for="LAY-user-login-password"></label>
                    <input type="password" name="password" id="LAY-user-login-password" lay-verify="pass"
                           placeholder="密码"
                           class="layui-input">
                </div>
                <%-- <div class="layui-form-item">
                     <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                            for="LAY-user-login-account"></label>
                     <input type="text" name="account" id="LAY-user-login-account" lay-verify="nickname"
                            placeholder="昵称"
                            class="layui-input">
                 </div>--%>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                           for="LAY-user-login-name"></label>
                    <input type="text" name="name" id="LAY-user-login-name" lay-verify="required" placeholder="姓名"
                           class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit">注 册</button>
                </div>
                <div class="layui-trans layui-form-item layadmin-user-login-other">

                    <a href="${ctx}/pc/login" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
                    <a href="${ctx}/pc/login"
                       class="layadmin-user-jump-change layadmin-link layui-hide-sm layui-show-xs-inline-block">登入</a>
                </div>
            </form>
        </div>
    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <div class="layui-main">
            <h4>©  个性化音乐推荐系统 版权所有</h4>
            <p>
                个性化音乐推荐系统 PC端页面
            </p>
        </div>
    </div>
</div>

<script>
    layui.config({
        base: '' //静态资源所在路径
    }).extend({}).use(['form'], function () {
        var $ = layui.$
            , setter = layui.setter
            , admin = layui.admin
            , form = layui.form
            , router = layui.router()
            , search = router.search;

        form.render();

        //提交
        form.on('submit(LAY-user-reg-submit)', function (obj) {
            //表单数据formData
            var regFrom = obj.field;
            $.post("${ctx}/pc/registerSubmit", regFrom, function (result) {
                if (result.code == 0) {
                    layer.msg('恭喜你，注册成功，到登录页面进行登录', {
                        offset: '15px'
                        , icon: 1
                    }, function () {
                        location.href = "${ctx}/pc/login";
                    });
                } else {
                    layer.alert(result.msg);
                }
            }, "JSON");
        });
    });
</script>
</body>
</html>
