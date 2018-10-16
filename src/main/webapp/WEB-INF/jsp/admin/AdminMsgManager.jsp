<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					管理员管理
					<div class="btn-group pull-right" style="margin-top: -8px;">
						<button id="btn_add" type="button" class="btn btn-primary">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
						</button>
						<button id="btn_delete" type="button" class="btn btn-primary">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
						</button>
					</div>
				</div>
				<table id="table"></table>
			</div>
		</div>
		<!--固定底部区域-->
			<%@ include file="/common/foot.jsp"%>
			<script src="${path}/js/adminMsgManager.js"></script>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">管理员信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" class="form-horizontal" id="AdminMsg"
						method="Post">
						<div class="form-group">
							<label for="adminname" class="col-sm-2 control-label">名字</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="adminname" value=""
									name="adminname" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="admintel" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="admintel" value=""
									name="admintel" required="required">
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