<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:if test="${errormsg!=null}">
    <script>
        layui.alert('${errormsg}');
    </script>
</c:if>
<ul class="layui-nav layui-bg-cyan" lay-filter="" style="margin-bottom: 0px;text-align: center;">
    <li class="layui-nav-item"><a href="${ctx}/pc/index">个性化音乐推荐系统信息展示</a></li>
    <li class="layui-nav-item"><a href="${ctx}/pc/index">首页</a></li>
    <li class="layui-nav-item"><a href="${ctx}/manage/login" target="_blank">后台</a></li>

    <li class="layui-nav-item"><a href="${ctx}/pc/informIndex">公告</a></li>
    <li class="layui-nav-item"><a href="${ctx}/pc/wenjuan">问卷</a></li>
    <li class="layui-nav-item"><a href="${ctx}/pc/bangdan">榜单</a></li>
    <%--    <li class="layui-nav-item"><a href="${ctx}/pc/liuyan">留言</a></li>--%>
    <c:if test="${sessionScope.loginUserinfo==null}">
        <li class="layui-nav-item"><a href="${ctx}/pc/login">登陆</a></li>
        <li class="layui-nav-item"><a href="${ctx}/pc/register">注册</a></li>
    </c:if>
    <c:if test="${sessionScope.loginUserinfo!=null}">
        <li class="layui-nav-item"><a href="${ctx}/pc/tuijian">音乐标签推荐</a></li>
        <li class="layui-nav-item"><a href="${ctx}/pc/algorithmList">协同过滤推荐</a></li>
        <li class="layui-nav-item"><a href="${ctx}/pc/userinfoCenter">个人中心</a></li>
        <li class="layui-nav-item">
            <a href=""><img src="${sessionScope.loginUserinfo.picurl}"
                            class="layui-nav-img">${sessionScope.loginUserinfo.name}</a>
            <dl class="layui-nav-child">
                <dd><a href="${ctx}/pc/userinfoCenter">个人中心</a></dd>
                <dd><a href="${ctx}/pc/loginOut">退出登录</a></dd>
            </dl>
        </li>
    </c:if>
</ul>
