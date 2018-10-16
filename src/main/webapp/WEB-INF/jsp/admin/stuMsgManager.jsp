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
				<div class="panel-heading" style="text-align: center;">
					学生信息管理
					<button class="btn btn-primary pull-right"
						style="margin-top: -8px;" id="addStuMsgBtn">添加</button>
					<button class="btn btn-primary pull-left" style="margin-top: -8px;"
						id="campusMenu">批量导入</button>
				</div>
				<table class="table table-hover table-condensed table-striped">
					<thead>
						<tr>
							<th class="col-md-2">学号</th>
							<th class="col-md-2">姓名</th>
							<th class="col-md-1">公寓</th>
							<th class="col-md-3">宿舍</th>
							<th class="col-md-1">电话</th>
							<th class="col-md-1">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${stuMsgList}" var="stuMsg">
							<tr id="${stuMsg.stuid}" class="stuMsgItem">
								<td class="col-md-2 stuid">${stuMsg.stuid}</td>
								<td class="col-md-2 sname">${stuMsg.sname}</td>
								<td class="col-md-1 fname">${stuMsg.flat.fname}</td>
								<td class="col-md-3 dormitory">${stuMsg.dormitory}</td>
								<td class="col-md-1 tel">${stuMsg.tel}</td>
								<td><a class="modifystuMsg" href="javascript:void(0);">
										<i class="glyphicon glyphicon-pencil" style="color: red;"></i>
								</a> <a href="javascript:void(0);"> <i
										class="glyphicon glyphicon-remove" style="color: red;"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<ul class="pagination pull-right" style="margin-top: -20px;">
				<li><a href="#">&laquo;</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">&raquo;</a></li>
			</ul>
		</div>
		<!--固定底部区域-->
		<%@ include file="/common/foot.jsp"%>
		<script type="text/javascript" src="${path}/js/stuMsgManager.js"></script>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">学生信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="${path}/admin/addStuMsg.do"
						class="form-horizontal" id="addStuMsgForm" method="POST">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">学号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="stuid" value=""
									name="stuid" placeholder="请输入学号">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">名字</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="sname" value=""
									name="sname" placeholder="请输入姓名">
							</div>
						</div>
						<div class="form-group">
							<label for="flat" class="col-sm-2 control-label">公寓</label>
							<div class="col-sm-10">
								<select class="form-control" name="fid" id="flat">
									<option value="">请选择学生公寓</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="dormitory" class="col-sm-2 control-label">宿舍</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="dormitory" value=""
									name="dormitory" placeholder="请输入宿舍号">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="tel" value=""
									name="tel" placeholder="请输入电话号码" required="required">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="submit">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- /.modal -->
	
</body>
</html>