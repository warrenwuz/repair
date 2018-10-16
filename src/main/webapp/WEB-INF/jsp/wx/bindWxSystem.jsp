<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css"
	rel="stylesheet" />
<title>绑定系统</title>
</head>
<body>
	<div class="page">
	<!-- 导航栏 -->
	<div class="weui-tab" id="tab">
		<!-- 身份选择  START-->
			<div class="weui-navbar">
				<div class="weui-navbar__item weui-bar__item_on">学生</div>
				<div class="weui-navbar__item">维修人员</div>
			</div>
	    <!-- 身份选择  END-->
	    <!-- 绑定页面Logo -->
			<div class="page__hd" style="padding-top: 100px">
				<div class="page__title" style="text-align: center;">
					<img src="${path}/img/logowx.png" height="100px" width="100px" />
				</div>
			</div>
	  <!-- /绑定页面Logo -->
	   <!-- TAB Content -->
			<div class="weui-tab__panel">
				<div class="weui-tab__content">
					<div class="weui-cells weui-cells_form">
						<form  id="bindWxStudentForm" method="POST">
							<input type="hidden" name="openid" value="${openid}">
							<div class="weui-cell">
								<div class="weui-cell__hd" style="text-align: center;">
									<label class="weui-label">学号</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" type="text" placeholder="请输入学号"
										name="stuid" id="stuid" style="text-align: center;" />
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd" style="text-align: center;">
									<label class="weui-label">密码</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" type="password" placeholder="请输入密码"
										name="password" id="password" style="text-align: center;" />
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="weui-tab__content">
					<div class="weui-cells weui-cells_form">
						<form  id="bindWxRepairmanForm" method="POST">
							<input type="hidden" name="openid" value="${openid}">
							<div class="weui-cell">
								<div class="weui-cell__hd" style="text-align: center;">
									<label class="weui-label">工号</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" type="text" placeholder="请输入工号"
										name="rmid" id="rmid" style="text-align: center;" />
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd" style="text-align: center;">
									<label class="weui-label">密码</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" type="password" placeholder="请输入密码"
										name="rmpassword" id="rmpassword" style="text-align: center;" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<button class="weui-btn weui-btn_primary" style="margin-top: 50px;" value='0'
				id="submit">点击绑定</button>
			<div class="page__ft footer" style="position: fixed; bottom: 0px; left: 10%">
				<div class="weui-footer">
					<p class="weui-footer__text">Copyright &copy; 2017-2018
						太原师范学院在线保修系统</p>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/libs/weuijs/1.1.2/weui.min.js"></script>
<script type="text/javascript" src="${path}/js/bindWxSystem.js?type=${type}"></script>
</html>