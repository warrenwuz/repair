<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			 <ol class="breadcrumb">
                    <li><a href="${path}/admin/wxOnLineRepairMsgManager.do">维修管理</a></li>
                    <li class="active">报修详情</li>
                </ol>
				<div class="panel-heading">
					报修详情
					<div class="btn-group pull-right" style="margin-top: -8px;">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span class="glyphicon glyphicon-th-list"></span>
						</button>
						<ul class="dropdown-menu" role="menu" style="text-align: center;">
							<li
								<c:if test="${WxOnlineRepairMsg.status!=0}">class="disabled"</c:if>
								style="border-bottom: 1px solid wheat;"><a href="#"
								data-toggle="modal" data-target="#myModal">派工</a></li>
							<li style="border-bottom: 1px solid wheat;"><a href="#">驳回</a></li>
							<li
								<c:if test="${WxOnlineRepairMsg.status!=3}">class="disabled"</c:if>><a
								href="#" data-toggle="modal" data-target="#check">验收</a></li>
						</ul>
					</div>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-md-4">编号</th>
							<th class="col-md-2">故障地点</th>
							<th class="col-md-2">报修类别</th>
							<th class="col-md-1">报修人</th>
							<th class="col-md-2">电话</th>
							<th class="col-md-1">状态</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${WxOnlineRepairMsg.wpid}</td>
							<td>${WxOnlineRepairMsg.studentMsg.flat.fname}/${WxOnlineRepairMsg.studentMsg.dormitory}宿舍</td>
							<td>${WxOnlineRepairMsg.repairProject.rpname}/${WxOnlineRepairMsg.repairProject.repairArea.raname}</td>
							<td>${WxOnlineRepairMsg.studentMsg.sname}</td>
							<td>${WxOnlineRepairMsg.tel}</td>
							<c:choose>
								<c:when test="${WxOnlineRepairMsg.status==0}">
									<td class="col-md-1">待审核</td>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==1}">
									<td class="col-md-1">已派工</td>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==2}">
									<td class="col-md-1">正在维修</td>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==3}">
									<td class="col-md-1">已完工</td>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==4}">
									<td class="col-md-1">已验收</td>
								</c:when>
							</c:choose>
						</tr>
					</tbody>
				</table>
				<div class="panel-body">
					<!-- row -->
					<div class="row">
						<!-- 维修进度 -->
						<div class="progress">
							<c:choose>
								<c:when test="${WxOnlineRepairMsg.status==0}">
									<div class="progress-bar progress-bar-primary"></div>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==1}">
									<div class="progress-bar progress-bar-primary"
										style="width: 25%;">已派工</div>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==2}">
									<div class="progress-bar progress-bar-primary"
										style="width: 25%;">已派工</div>
									<div class="progress-bar progress-bar-success"
										style="width: 25%;">正在维修</div>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==3}">
									<div class="progress-bar progress-bar-primary"
										style="width: 25%;">已派工</div>
									<div class="progress-bar progress-bar-success"
										style="width: 25%;">正在维修</div>
									<div class="progress-bar progress-bar-primary"
										style="width: 25%;">已完工</div>
								</c:when>
								<c:when test="${WxOnlineRepairMsg.status==4}">
									<div class="progress-bar progress-bar-primary"
										style="width: 25%;">已派工</div>
									<div class="progress-bar progress-bar-success"
										style="width: 25%;">正在维修</div>
									<div class="progress-bar progress-bar-primary"
										style="width: 25%;">已完工</div>
									<div class="progress-bar progress-bar-success"
										style="width: 25%;">已验工</div>
								</c:when>
							</c:choose>

						</div>
						<!-- 报修内容 -->
						<ul class="list-group">
							<li class="list-group-item"><span class="text-info">报修时间</span>:${fn:substring(WxOnlineRepairMsg.timestamp, 0, 19)}</li>
							<li class="list-group-item"><span class="text-info">故障描述</span>:${WxOnlineRepairMsg.projectdetail}<span></span></li>
							<li class="list-group-item"><span class="text-info">派工备注</span>:${WxOnlineRepairMsg.remark}</li>
							<li class="list-group-item"><span class="text-info">维修人员</span>:
								<c:choose>
									<c:when test="${empty WxOnlineRepairMsg.repairman}">
										<td class="col-md-1">未指派</td>
									</c:when>
									<c:otherwise>
										<td class="col-md-1">${WxOnlineRepairMsg.repairman.rmname}</td>
									</c:otherwise>
								</c:choose></li>
							<li class="list-group-item"><span class="text-info">完工描述</span>:${WxOnlineRepairMsg.completeremark}</li>
							<li class="list-group-item"><span class="text-info">学生评价</span>:</li>
						</ul>

						<!-- /报修内容 -->
						<!-- 保修图片/完工图片 -->
						<div class="col-md-6">
							<table class="table">
								<tr>
									<td class="col-md-2"><span class="text-info">故障图片</span></td>
									<td class="col-md-10"  id="layer-photos-demo" class="layer-photos-demo">
									<c:forEach
											items="${WxOnlineRepairMsg.simage}" var="simage">
											<c:if test="${simage.imagetype==0}">
												<img
													src="${path}/admin/showImage.do?id=${simage.simagepath}"
													class="img-thumbnail col-md-6" style="height: 100px">
											</c:if>
										</c:forEach></td>
										
								</tr>
								<tr>
									<td class="col-md-2"><span class="text-info">完工图片</span></td>
									<td class="col-md-10"><c:forEach
											items="${WxOnlineRepairMsg.simage}" var="simage">
											<c:if test="${simage.imagetype==1}">
												<img
													src="${path}/admin/showImage.do?id=${simage.simagepath}"
													class="img-thumbnail col-md-6" style="height: 100px">
											</c:if>
										</c:forEach></td>
								</tr>
							</table>
						</div>
						<!-- /保修图片/完工图片 -->
						<!-- /维修进度  -->
						<div class="col-md-1"></div>
						<ul class="layui-timeline col-md-5">
						<c:forEach items="${WxOnlineRepairMsg.repairLog}" var="repairLog">
							<li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis">&#xe63f;</i>
								<div class="layui-timeline-content layui-text">
									<h3 class="layui-timeline-title">${fn:substring(repairLog.logDate,0,19)}</h3>
									<p>
									${repairLog.repairLog} <i class="layui-icon"></i>
									</p>
								</div></li>
						</c:forEach>
						
						</ul>
						<!-- /维修流程 -->
					</div>
					<!-- /row -->
				</div>
				<div class="panel-footer"></div>
			</div>
		</div>
		<!--固定底部区域-->
			<%@ include file="/common/foot.jsp"%>
			<script src="${path}/js/repair_msg_detail.js"></script>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">报修审核</h4>
				</div>
				<div class="modal-body">
					<form role="form" id="dispatchingForm">
						<input value='${WxOnlineRepairMsg.wpid}' type="hidden" name="wpid">
						<div class="form-group" id="DispatchingTypeDiv">
							<label for="name">派工方式</label> <select class="form-control"
								name="dispatchingType" id="DispatchingType"
								title="自动派工会自动为保修单选择维修工人">
								<option value="0">自动派工</option>
								<option value="1">手动派工</option>
							</select>
						</div>
						<div class="form-group" id="repairman" hidden="hidden">
							<label for="name">维修工人</label> <select class="form-control"
								name="rmid" id="rmid">
								<option></option>
							</select>
						</div>
						<div class="form-group">
							<label for="name" id="remarklabel">审核备注</label>
							<textarea class="form-control" rows="1" name="remark" id="remark"
								placeholder="可为空"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="sumbit">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="check" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">验收报修单</h4>
				</div>
				<div class="modal-body">请一定要确认报修同学的报修情况得到解决</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<a type="button" class="btn btn-primary"
						href='${path}/admin/checkandacceptance.do?wpid=${WxOnlineRepairMsg.wpid}'>
						确认 </a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
</body>
</html>