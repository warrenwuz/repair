<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
<!--
        	作者：gongting@163.com
        	时间：2018-01-11
        	描述：管理员管理详情
        -->
<%@ include file="/common/css.jsp"%>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<%@ include file="/common/head.jsp"%>
		<!-- 头部区域 -->
		<%@ include file="/common/menu.jsp"%>
		<!-- 菜单区域 -->
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="panel panel-success" style="padding: 15px;">
				<div class="panel-heading">
					<a id="newRepair" href="javascript:void(0);">最新报修</a>
				</div>
				<div class="panel-body">
					<ul class="nav nav-tabs  nav-justified statusMenu">
					
						<li id="queryUnCheckBtn" class="active queryStatus"><a><i class="fa fa-table">待审核</i></a></li>
						<li id="queryUnFinshedBtn" class="queryStatus"><a><i class="fa fa-table">正在派工</i></a></li>
						<li id="queryRepairingBtn" class=" queryStatus"><a><i class="fa fa-table">正在维修</i></a></li>
						<li id="queryFinsedBtn" class="queryStatus"><a><i class="fa fa-table">已完工</i></a></li>
						<li id="queryAcceptedBtn" class="queryStatus"><a><i class="fa fa-table">已验收</i></a></li>
					   <li id="queryAllBtn" class="queryStatus"><a><i class="fa fa-table">全部</i></a></li>
					</ul>
				</div>
				<table id="table"></table>
			</div>
		</div>
			<!--固定底部区域-->
		<%@ include file="/common/foot.jsp"%>
		<script src="${path}/js/app.js"></script>
	</div>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
	
</body>
</html>