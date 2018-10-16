	$().ready(function() {
					$.ajax({
						type : 'post',
						url : '/repairManager/admin/queryRepairmaForJson.do',
						success : function(data) {
							$.each($.parseJSON(data), function(i, val) {// 这个使用parseJSON是因为传过来的data需要转换成json对象
								$("#rmid").append(
										"<option value='"+val.rmid+"'>"
												+ val.rmname + "</option>")
							})
						}
					})
					$("#DispatchingType").change(function() {
						$("#repairman").toggle();//显示审核模态框
					})
					$("#sumbit").click(function() {
						var DispatchingTypeValue = $("#DispatchingType").val();
						var remark = $("#remark").val();
						if (DispatchingTypeValue == 1) {
							if ($("#rmid").val() == '') {
								alert("请选择派工人员")
								return;
							}
						}
						console.log($("#dispatchingForm").serializeJSON())
						$.ajax({
							contentType : "application/x-www-form-urlencoded",
							type : 'POST',
							url : '/repairManager/admin/dispatchingRepairMsg.do',
							data : $("#dispatchingForm").serializeJSON(),
							success : function() {
								layer.msg("提交成功")
								$("#myModal").modal("hide");
							}
						})
					})
					$("#checkResult").change(function() {
						var checkResult = $("#checkResult").val();
						if (checkResult == 1) {
							$("#DispatchingTypeDiv").show();
							$("#remarklabel").html("审核备注");
						}
						if (checkResult == 2) {
							console.log(2)
							$("#DispatchingTypeDiv").hide();
							$("#remarklabel").html("驳回理由");
						}
					})
				})