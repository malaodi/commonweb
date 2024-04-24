<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>问卷统计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <style>
        #myEcharts02 {
            width: 800px;
            height: 500px;
            /*border: solid 1px red;*/
            margin: 50px auto;

        }
    </style>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="https://cdn.staticfile.org/echarts/4.7.0/echarts.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all"/>
    <style>
        #myEcharts02 {
            width: 800px;
            height: 500px;
            /*border: solid 1px red;*/
            margin: 50px auto;

        }
    </style>
</head>
<body>
<div class="layui-container">
    <br>
    <h1 style="text-align: center;"><a href="${ctx}/manage/wenjuanList" class="layui-btn">返回列表</a></h1>

    <!--为echarts准备一个dom容器-->
    <div id="myEcharts02"></div>
    <script>

        //请求路径
        var url = "${ctx}/pc/selectAction";
        //执行的sql语句
        var sql = "SELECT a${id} as name,count(*) as val FROM wenjuanjieguo GROUP BY a${id} ";
        //初始化echarts实例
        //柱状图
        function getpieChart() {
            var pieData = [];
            var legendData = [];
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                data: {"sql": sql},
                cache: false,
                success: function (data) {
                    var data = data.data;
                    for (let j = 0; j < data.length; j++) {
                        var value = data[j].val;
                        var name = data[j].name;
                        var o = {value: parseInt(value), name: name};
                        pieData.push(o);
                        legendData.push(name);
                    }
                    console.log(pieData);
                    console.log(legendData);
                    //饼图
                    var pieChart = echarts.init(document.getElementById("myEcharts02"));
                    var pieoption = {
                        title: {
                            text: '问卷调查结果统计图',
                            left: 'center'
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                mark: {show: true},
                                dataView: {show: true, readOnly: false},
                                magicType: {
                                    show: true,
                                    type: ['pie', 'funnel'],
                                    option: {
                                        funnel: {
                                            x: '25%',
                                            width: '50%',
                                            funnelAlign: 'left',
                                            max: 1548
                                        }
                                    }
                                },
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        //设置饼状图每个颜色块的颜色
                        legend: {
                            data: legendData,
                            orient: 'vertical',
                            left: '0px',
                        },
                        series: [
                            {
                                name: '结果分类',
                                // radius: ['20%', '80%'], 圆形圈
                                radius: '55%',
                                hoverAnimation: true,
                                avoidLabelOverlap: true,
                                type: 'pie',
                                data: pieData,
                                emphasis: {
                                    itemstyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    pieChart.setOption(pieoption);
                    $(window).resize(pieChart.resize);

                },
                error: function (responseStr) {
                    alert("数据类模板数量查询失败！");
                }
            });


        }


        $(function () {
            //加载饼图
            getpieChart();
        });
    </script>
</div>
</body>
</html>
