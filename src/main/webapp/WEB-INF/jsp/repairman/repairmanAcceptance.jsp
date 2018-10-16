<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<c:set value="${WxOnlineRepairMsg}" var="msg"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<link href="https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css"
	rel="stylesheet" />
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<title>报修单详情</title>
	</head>
	<style type="text/css">
		th{
			text-align: center;
			vertical-align: middle !important;
		}
		td{
			text-align: center;
		}
	</style>
	<body>
		<div class="col-xs-12" style="padding: 0px;">
		<div class="panel panel-primary" style="border: none">
		  <!-- 面包屑导航 -->
				<ol class="breadcrumb">
					<li><a href="${path}/wx/repairmanRecode.do?openid=${openid}">维修记录</a></li>
				  <li class="active">保修单详情</li>
				</ol>
			<table class="table table-hover" style="word-break:break-all; word-wrap:break-all;border: none">
				<tr>
					<th class="col-xs-4">报修时间</th>
					 <td class="col-xs-8">${fn:substring(msg.timestamp, 0, 19)}</td>
				</tr>
				<tr>
					<th>报修地点</th>
					 <td>${msg.studentMsg.flat.fname}/${msg.studentMsg.dormitory}宿舍</td>
				</tr>
				<tr>
					<th>报修项目</th>
					 <td>${msg.repairProject.repairArea.raname}/${msg.repairProject.rpname}</td>
				</tr>
				<tr>
					<th>报修人</th>
					 <td>${msg.studentMsg.sname}</td>
				</tr>
				<tr>
					<th>报修电话</th>
					 <td>${msg.tel}</td>
				</tr>
				<tr>
					<th>报修描述</th>
					 <td>${msg.projectdetail}</td>
				</tr>
				<tr>
					<th>派工备注</th>
					 <td >${msg.remark}</td>
				</tr>
					<tr>
					<th>故障照片</th>
					 <td>
					 	<c:forEach items="${msg.simage}" var="simage">
								<img src="${path}/admin/showImage.do?id=${simage.simagepath}"
									class="img-thumbnail col-md-6" style="height: 100px">
								</c:forEach>
					 </td>
				</tr>
			</table>
		</div>
			<c:choose>
			 <c:when test="${msg.status==1}"><button class="btn btn-primary btn-block" id="Acceptance">点击受理</button></c:when>
			  <c:when test="${msg.status==2}"><a class="btn btn-primary btn-block" id="Complete" href="${path}/wx/CompleteWxOnlineRepair.do?wpid=${msg.wpid}">提交完工</a></c:when>
			 <c:otherwise><button class="btn btn-block disabled" disabled="disabled">订单已完成</button></c:otherwise>
			</c:choose>
		</div>
	</body>
	<script type="text/javascript" src="https://res.wx.qq.com/open/libs/weuijs/1.1.2/weui.min.js"></script>
	<script type="text/javascript">
	$().ready(function(){
		$("#Acceptance").click(function(){//师傅点击受理
			$.ajax({
				type:'get',
				data:{wpid:"${msg.wpid}"},
				url:'${path}/wx/repairAcceptanceWxOnlineMsg.do',
				success:function(){
			$(window).attr('location','/repairManager/static/acceptanceSuccess.html');
				}
			})
		})
	})
	</script>
</html>
