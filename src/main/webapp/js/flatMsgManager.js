$().ready(function() {
  $("#Addflat").click(function(){
	  $("#fid").val("");
	  $("#fname").val("");
	  $("#submit").val("");
	  $("#fid").removeAttr("disabled");
	  $("#myModal").modal('show');
	  
  })
  $(".modifyFlat").click(function(){
		//首先找到点击按钮的父辈tbody 再查询其下的子辈元素类名为raname的td 获取他的文本值
		var tbody=$(this).parents(".FlatItem");
		var fid=tbody.children(".fid").html();
		var fname=tbody.children(".fname").html();
	     $("#fid").val(fid).attr("disabled","disabled");
		  $("#fname").val(fname);
		  $("#submit").val("modify");
		  $("#myModal").modal('show');
  })
  $("#submit").click(function(){
	   if($(this).val()=="modify"){
		   $("#fid").removeAttr("disabled");
		   $("#FlatMsg").attr("action","/repairManager/admin/modifyFlat.do");
	         $("#FlatMsg").submit();	   
	   }
	   else{
         $("#FlatMsg").attr("action","/repairManager/admin/addFlat.do");
         $("#FlatMsg").submit();
	   }
	   
  })

})