 $().ready(function() {
		/*****防止下端的页脚弹出****/
			var h = $(window).height();
			$(window).resize(function() {
			if ($(window).height() < h) {
					$('.footer').hide();
				}
				if ($(window).height() >= h) {
					$('.footer').show();
				}
			});
		  /*****防止下端的页脚弹出****/
		  /*****weui导航栏****/
			weui.tab('#tab',{
			    defaultIndex: 0,
			    onChange: function(index){
			    $("#submit").val(index);
			    }
			});
		   /*****weui导航栏****/
		   /*****学生绑定事件****/
			$("#submit").click(function() {
				var submitValue=$(this).val();
				console.log(submitValue)
				if(submitValue==0){
					if ($("#stuid").val() == ""|| $("#password").val() == "") {
						weui.alert("学号或密码不能为空");
					} else {
					    $.ajax({
							type : "POST",
							url : "/repairManager/wx/bindWxSystem.do",
							data : $("#bindWxStudentForm").serialize(),
							success : function(data) {
					         if (data == "0") {//绑定成功
						        $(window).attr('location','/repairManager/static/success.html');
					          } else {//绑定失败
								    if (data == 1) {//1.绑定的学号和密码错误
									   weui.alert("学号或密码错误");
								      } else {
										      /*****已经在其他微信号上绑定过微信****/	
										    weui.confirm('你已经在其他微信绑定过平台,是否要覆盖原绑定',function() {//点击确认执行的方法
										    	  /*****更新绑定微信号****/	
										    	  $.ajax({
													type:"POST",
													url:"/repairManager/wx/updateWxSystem.do",
													data:$("#bindWxStudentForm").serialize(),
													success:function(data) {//更新成功
													 $(window).attr('location','/repairManager/static/success.html');
													 }
										    	  })
										    	  /*****更新绑定微信号****/	
										    	});
										    /*****已经在其他微信号上绑定过微信****/	
									}
						   }

						 }
							})
						}
				}else{
					if ($("#rmid").val() == ""|| $("#rmpassword").val() == "") {
						weui.alert("工号或密码不能为空");
				    }else{
				    	console.log($("#bindWxRepairmanForm").serialize())
				    	  $.ajax({
								type : "POST",
								url : "/repairManager/wx/bindWxRepairmanSystem.do",
								data : $("#bindWxRepairmanForm").serialize(),
								success : function(data) {
							         if (data == "0") {//绑定成功
								        $(window).attr('location','/repairManager/static/success.html');
							          } else {//绑定失败
										    if (data == 1) {//1.绑定的工号和密码错误
											   weui.alert("工号或密码错误");
										      } else {
												      /*****已经在其他微信号上绑定过微信****/	
											    weui.confirm('你已经在其他微信绑定过平台,是否要覆盖原绑定',function() {//点击确认执行的方法
											    	  /*****更新绑定微信号****/	
										    	  $.ajax({
													type:"POST",
													url:"/repairManager/wx/updateWxRepairmanSystem.do",
													data:$("#bindWxRepairmanForm").serialize(),
													success:function(data) {//更新成功
													 $(window).attr('location','/repairManager/static/success.html');
													 }
										    	  })
											    	  /*****更新绑定微信号****/	
											    	});
												    /*****已经在其他微信号上绑定过微信****/	
											}
								        }
								 }
				    	  })
				    }
			}
					 })
			
		})
