<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="${path}/layui/css/layui.css">
<title>维修进度</title>
<style type="text/css">
input {
	padding-left: 10%;
}

.weui-cell_access {
	color: #A9A9A9;
	padding-left: 10%;
}

.page__hd {
	background-image: url(${path}/img/logobg.png);
	height: 50px;
	width: 100%;
}

#text_title {
	text-align: center;
	color: white;
}

.page__desc {
	color: #888;
	font-size: 18px
}

p {
	word-wrap: break-word;
	word-break: break-all;
	overflow: hidden;
}

.tone-text {
	color: #0088CC;
}
</style>
</head>
<body ontouchstart bgcolor="#f8f8f8">
	<div class="page">
		<div class="page__hd">
			<div class="page__title">
				<h3 id="text_title" style="padding-top: 8px;">
					维修进度
					</h3>
			</div>
		</div>
		<div class="page__bd">
				<!-- /维修进度  -->
				  <div class="weui-cell">
            <ul class="layui-timeline" style="margin-top: 30px;margin-left: 50px">
            	 
						<c:forEach items="${repairLogList}" var="repairLog">
							<li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis">&#xe63f;</i>
								<div class="layui-timeline-content layui-text">
									<h3 class="layui-timeline-title">${fn:substring(repairLog.logDate,0,19)}</h3>
									<p>
									${repairLog.repairLog} <i class="layui-icon"></i>
									</p>
								</div></li>
						</c:forEach>
						
						</ul>
						</div>
            
           
						
		</div>
		<div class="page__ft" style="padding-top: 30px;">
			<div class="weui-footer">
				<p class="weui-footer__text">Copyright &copy; 2017-2018
					太原师范学院维修服务平台</p>
			</div>
		</div>
	</div>
</body>
</html>