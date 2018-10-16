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
				<div class="panel-heading" style="text-align: center;">
					公寓管理
					<button class="btn btn-primary pull-right"
						style="margin-top: -8px;" id="Addflat">添加</button>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>公寓名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="flat">
							<tr class="FlatItem">
								<td class="fid">${flat.fid}</td>
								<td class="fname">${flat.fname}</td>
								<td><a class="modifyFlat" href="javascript:void(0);"> <i
										class="glyphicon glyphicon-pencil" style="color: red;"></i>
								</a> <a class="deleteFlat" href="javascript:void(0);"> <i
										class="glyphicon glyphicon-remove" style="color: red;"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--固定底部区域-->
			<%@ include file="/common/foot.jsp"%>
			<script src="${path}/js/flatMsgManager.js"></script>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">公寓信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" class="form-horizontal" id="FlatMsg"
						method="Post">
						<div class="form-group">
							<label for="fid" class="col-sm-2 control-label">编号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="fid" value=""
									name="fid" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="fname" class="col-sm-2 control-label">公寓名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="fname" value=""
									name="fname" required="required">
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
	<script type="text/javascript" src="${path}/js/flatMsgManager.js"></script>
</body>
</html>