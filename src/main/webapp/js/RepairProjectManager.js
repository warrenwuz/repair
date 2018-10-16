$().ready(function(){
	
	 $.ajax({
		 type:"Get",
		 url:"/repairManager/queryRepairAreaJson.do",
		 success:function(data){
			 console.log($.parseJSON(data).length)
				$.each($.parseJSON(data), function(i, val) {// 这个使用parseJSON是因为传过来的data需要转换成json对象
					$("#repairArea").append("<option value=" + val.raid + ">"+ val.raname + "</option>")
				})
		 }
		 
	 })
	 
	
	//报修区域管理窗口显示
	$("#repairareaMangerShow").click(function(){
		$("#repairareaManger").show();
		$("#repairProjectManager").removeClass("col-md-12").addClass("col-md-9");
	})
	//报修区域管理窗口隐藏
	$("#repairareaMangerHide").click(function(){
		$("#repairareaManger").hide();
		$("#repairProjectManager").removeClass("col-md-9").addClass("col-md-12");
	})
	//报修区域添加
	$("#repairareaMangerAdd").click(function(){
		$("#repairAreaModal").modal("show");
		//初始化所有值
		$("#repairAreaSubmit").val("");
		$("#raname").val("");
		$("#rid").val("")
	})
	//报修区域提交
	$("#repairAreaSubmit").click(function(){
		var btnValue=$(this).val();
		var  raid= parseInt($("#raid").val());
		var raname=$("#raname").val();
		if(raname==null||raname==""){
			alert("报修区域不能为空")
		}else{
			if(btnValue==""){
				   var length=$(".addressTable").children().length;//获取tr的个数便于添加编号
					$.ajax({
						type:"POST",
						url:"/repairManager/admin/repariAreaAdd.do",
						data:{raname:raname},
						success:function(raid){//插入数据返回主键
							$(".addressTable").append("<tr class='repairAreaItem'><td class='raid' hidden='hidden'>"+raid+"</td><td>"+(length+1)+"</td><td>"+raname+"</td><td><a class='repairAreaModify' href='javascript:void(0);'><i class='glyphicon glyphicon-pencil' style='color: red;'></i></a> <a href='javascript:void(0);'><i class='glyphicon glyphicon-remove' style='color: red;'></i></a></td></tr>");
							$("#repairAreaModal").modal("hide");
						}
					})
				}else{
					//这里没有使用异步提交表单 是因为不止一处用到了报修区域的值
					$("#repairAreaForm").submit();
					$("#repairAreaModal").modal("hide");
				}
		}
	});
	 //报修区域修改
	$("#repairProjectItem").on('click',"td a",function(){
		  event.stopPropagation();//阻止事件冒泡
		//首先找到点击按钮的父辈tbody 再查询其下的子辈元素类名为raname的td 获取他的文本值
		var tbody=$(this).parents(".repairAreaItem");
		var raname=tbody.children(".raname").html();
		var raid=tbody.children(".raid").html();
		$("#raname").val(raname);
		$("#raid").val(raid);
		$("#repairAreaSubmit").val("modify");
		$("#repairAreaModal").modal("show");
	})
	//根据报修区域切换报修项目
	$(".repairAreaItem").click(function(){
		var raid=parseInt($(this).children(".raid").html());
		$.ajax({
			type:"Get",
			url:"/repairManager/queryRepairProjectByRaid.do",
			data:{raid:raid},
			success:function(data){
				$(".repairProjectTable").html("");
				$.each($.parseJSON(data), function(i, val) {// 这个使用parseJSON是因为传过来的data需要转换成json对象
						$(".repairProjectTable").append("<tr class='repairProjectItem'><td class='rpid' hidden='hidden'>"+val.rpid+"</td><td>"+(i+1)+"</td><td>"+val.rpname+"</td><td>"+val.repairArea.raname+"</td><td><a class='repairProjectModify' href='javascript:void(0);'><i class='glyphicon glyphicon-pencil' style='color: red;'></i></a> <a href='javascript:void(0);'><i class='glyphicon glyphicon-remove' style='color: red;'></i></a></td></tr>");
				})
			}
		})
		
	})
	//报修项目的添加
	$("#repairProjectAdd").click(function(){
		$("#repairProjectModal").modal("show");
		//初始化所有值
		$("#rpid").val("");
		$("#rpname").val("");
		$("#repairArea").find("option:selected").removeAttr("selected");// 移除默认选择框
		$("#repairProjectSubmit").val("");
	});
	//报修项目提交
	$("#repairProjectSubmit").click(function(){
		var btnValue=$(this).val();
		var  rpid= parseInt($("#rpid").val());
		var rpname=$("#rpname").val();
		var raid=$("#repairArea").val();
		var raname=$("#repairArea").find("option:selected").html();
		if(rpname==null||rpname==""||repairArea==null||repairArea==""){
			alert("项目名称或所属区域不能为空")
		}else{
			if(btnValue==""){
				   var length=$(".repairProjectTable").children().length;//获取tr的个数便于添加编号
					$.ajax({
						type:"POST",
						url:"/repairManager/admin/repariProjectAdd.do",
						data:{rpname:rpname,raid:raid},
						success:function(rpid){//插入数据返回主键
							$(".repairProjectTable").append("<tr class='repairProjectItem'><td class='rpid' hidden='hidden'>"+rpid+"</td><td>"+(length+1)+"</td><td>"+rpname+"</td><td>"+raname+"</td><td><a class='repairAreaModify' href='javascript:void(0);'><i class='glyphicon glyphicon-pencil' style='color: red;'></i></a> <ahref='javascript:void(0);'><i class='glyphicon glyphicon-remove' style='color: red;'></i></a></td></tr>");
							$("#repairProjectModal").modal("hide");
						}
					})
				}else{
					//这里没有使用异步提交表单 是因为不止一处用到了报修区域的值
					$("#repairProjectForm").submit();
					$("#repairProjectModal").modal("hide");
				}
		}
	});
	//报修项目更改
	$(".repairProjectModify").click(function(){
		//首先找到点击按钮的父辈tbody 再查询其下的子辈元素类名为raname的td 获取他的文本值
		var tbody=$(this).parents(".repairProjectItem");
		var raname=tbody.children(".raname").html();
		var rpname=tbody.children(".rpname").html();
		var rpid=tbody.children(".rpid").html();
		$("#rpname").val(rpname);
		$("#rpid").val(rpid);
		$("#repairArea").find("option").each(function(){
			if($(this).html()==raname){
				$(this).attr("selected","selected");
			}
		})
		$("#repairProjectSubmit").val("modify");
		$("#repairProjectModal").modal("show");
	})
})