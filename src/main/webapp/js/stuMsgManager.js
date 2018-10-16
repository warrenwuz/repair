$().ready(function() {
			// 通过异步请求加载需要选择的公寓
					$.ajax({
						type : "post",
						url : "/repairManager/admin/queryAllFlatJson.do",
						success : function(data) {
							$.each($.parseJSON(data), function(i, val) {// 这个使用parseJSON是因为传过来的data需要转换成json对象
								$("#flat").append("<option value="+val.fid+">"+val.fname+"</option>")
							})
						}
					})
					//修改学生信息
					$(".modifystuMsg").click(function() {
					    //首先找到点击按钮的父辈tbody 再查询其下的子辈元素类名为stuid的td 获取他的文本值
						var tbody=$(this).parents(".stuMsgItem");
						var stuid=tbody.children(".stuid").html();//学号
						var sname=tbody.children(".sname").html();//姓名
						var fname=tbody.children(".fname").html();//公寓名
						var dormitory=tbody.children(".dormitory").html();//宿舍号
						var tel=tbody.children(".tel").html();//电话号码
						$("#stuid").val(stuid).attr("disabled","disabled");// 因为学生的学号和姓名不能更改
					     $("#sname").val(sname).attr("disabled","disabled");
                        $("#flat").find("option").each(function() {
                        	 if ($(this).html() == fname) { // 判断option的html值和td中的值
                        		 $(this).attr("selected","selected"); // 相等，则设置为默认选择
                        	 }
					    });
                        $("#dormitory").val(dormitory);
                        $("#tel").val(tel);
                        $("#submit").val("modify");// 设置按钮的value为修改。因为添加和修改公用一个模态框，通过value值区分
					    $("#myModal").modal('show');// 完成后显示模态框
							});
					//添加学生信息
					$("#addStuMsgBtn").click(function() {
								$("#flat").find("option:selected").removeAttr(
										"selected");// 移除默认选择框
								// 将所有的input设置为空字符串
							     $("#stuid").val("").attr("disabled",null);
							     $("#sname").val("").attr("disabled",null);
								 $("#dormitory").val("");
                                 $("#tel").val("");
								$("#submit").val("");
								$("#myModal").modal('show');
							});
					//提交/修改学生信息
					   $("#submit").click(function() {
						                var stuid=$("#stuid").val();//学生学号
						                var sname=$("#sname").val();
										var fid = $("#flat").val();// 公寓ID
										var fname = $("#flat").find("option:selected").html();
										var dormitory = $("#dormitory").val();// 宿舍号
										var tel = $("#tel").val();// 电话号
										var btnValue = $(this).val();// 提交按钮的值
										if(stuid==null||stuid==""||sname==null||sname==""||fid==""||fid==null||dormitory==""||dormitory==null||tel==""||tel==null){
									           alert("有必填项未填")
										}else{
											if (btnValue == "modify") {// 如果为修改则触发修改按钮
												var stuid = $("#stuid").val();
												var data = {
													stuid : stuid,
													fid : fid,
													dormitory : dormitory,
													tel : tel
												}
												$.ajax({
													async : true,
													type : "POST",
													data : data,
													url : "/repairManager/admin/modifyStuMsg.do",
													success : function() {
													alert("修改数据成功")
												   //将所有数据封装在一个数组里面进行赋值
													 var param = [fname,dormitory,tel ];
													 //寻找到那个修改的tr 通过stuid
														$("#"+stuid).find("td").each(function(index) {
																	if(index>=2&&index<5){
																		$(this).html(param[index-2]);
																	}
																});
														$("#myModal").modal('hide');
													      },
														error : function() {
																alert("修改数据失败")
														}
												});
											} else {
												$("#addStuMsgForm").submit();
											}	
										}
									})
				})
