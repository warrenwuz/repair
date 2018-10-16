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
			<div class="row" style="padding: 15px;">
				<!--
                	作者：gongting@163.com
                	时间：2018-01-11
                	描述：报修区域管理
                -->
				<div class="col-md-3 " style="padding: 0px;" hidden="hidden"
					id="repairareaManger">
					<div class="panel panel-default">
						<div class="panel-heading" style="text-align: center;">
							报修区域管理
							<button class="btn btn-primary pull-left"
								style="margin-top: -8px;" id="repairareaMangerHide">
								<i class="glyphicon glyphicon-chevron-left"></i>
							</button>
							<button class="btn btn-primary pull-right"
								style="margin-top: -8px;" id="repairareaMangerAdd">添加</button>
						</div>
						<table class="table">
							<thead>
								<tr>
									<th>编号</th>
									<th>维修区域</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="addressTable">
								<c:forEach items="${repairAreaList}" var="Area"
									varStatus="Status">
									<tr class="repairAreaItem" id="${Area.raid}">
										<td class="raid" hidden="hidden">${Area.raid}</td>
										<td>${Status.index+1}</td>
										<td class="raname">${Area.raname}</td>
										<td><a class="repairAreaModify"
											href="javascript:void(0);"><i
												class="glyphicon glyphicon-pencil" style="color: red;"></i></a>
											<a href="javascript:void(0);"><i
												class="glyphicon glyphicon-remove" style="color: red;"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!--
            	作者 gongting@163.com
            	时间：2018-01-11
            	描述：报修项目管理
                 -->
				<div class="col-md-12" style="padding: 0px;"
					id="repairProjectManager">
					<div class="panel panel-default">
						<div class="panel-heading" style="text-align: center;">
							报修项目管理
							<button class="btn btn-primary pull-right"
								style="margin-top: -8px;" id="repairProjectAdd">添加</button>
							<button class="btn btn-primary pull-left"
								style="margin-top: -8px;" id="repairareaMangerShow">
								报修区域管理&nbsp;<i class="glyphicon glyphicon-list"></i>
							</button>
						</div>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>报修项目</th>
									<th>区域</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="repairProjectTable">
								<c:forEach items="${repairProjectList}" var="Project"
									varStatus="status">
									<tr class="repairProjectItem">
										<td class="rpid" hidden="hidden">${Project.rpid}</td>
										<td>${status.index+1}</td>
										<td class="rpname">${Project.rpname}</td>
										<td class="raname">${Project.repairArea.raname}</td>
										<td><a class="repairProjectModify"
											href="javascript:void(0);"> <i
												class="glyphicon glyphicon-pencil" style="color: red;"></i>
										</a> <a href="javascript:void(0);"> <i
												class="glyphicon glyphicon-remove" style="color: red;"></i>
										</a></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!--公寓管理END-->
			</div>
		</div>
		<!--固定底部区域-->
		<%@ include file="/common/foot.jsp"%>
		<script type="text/javascript" src="${path}/js/RepairProjectManager.js"></script>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="repairAreaModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">报修区域信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" class="form-horizontal" id="repairAreaForm"
						method="Post" action="${path}/admin/repariAreaModify.do">
						<input type="hidden" id="raid" value="" name="raid">
						<div class="form-group">
							<label for="raname" class="col-sm-2 control-label">维修区域</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="raname" value=""
									name="raname" required="required">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="repairAreaSubmit">提交</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="repairProjectModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">报修项目信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" class="form-horizontal" id="repairProjectForm"
						method="Post" action="${path}/admin/repairProjectModify.do">
						<input type="hidden" name="rpid" id="rpid" value="">
						<div class="form-group">
							<label for="repairArea" class="col-sm-2 control-label">维修区域</label>
							<div class="col-sm-10">
								<select class="form-control" name="repairArea" id="repairArea">
									<option value="">请选择维区域</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="rpname" class="col-sm-2 control-label">报修项目</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="rpname" value=""
									name="rpname" required="required">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="repairProjectSubmit" value="">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>