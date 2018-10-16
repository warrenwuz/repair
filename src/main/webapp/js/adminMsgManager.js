$().ready(function() {
	var adminArray=null;
	$('#table').bootstrapTable({
		url : '/repairManager/admin/queryAdminGetJson.do',
		toolbar: '#toolbar', 
		pagination : true,
		 cache: false,     // 是否使用缓冲
		queryParamsType:'',
		sidePagination:'client',
		 pageNumber:1,      // 初始化加载第一页，默认第一页
         pageSize: 10,      // 每页的记录行数（*）
         pageList: [10, 25, 50, 100],  // 可供选择的每页的行数（*）
         uniqueId:'adminid',
         clickToSelect: true, 
		columns : [ 
			{
			field : 'adminid',
			title : '工号',
		}, {
			field : 'adminname',
			title : '姓名',
			 editable:{
				 type: 'text',
                 title: '姓名',
                 validate: function (v) {
                     if (!v) return '姓名不能为空';
                 }
			 }
		}, {
			field : 'admintel',
			title : '电话',
			 editable:true
		}, {
			field : 'authorization',
			title : '身份',
			formatter:authorizationFtt	
		}],
	    onEditableSave: function (field, row, oldValue, $el) {
	        $.ajax({
	        	type:'POST',
	        	url:'/repairManager/admin/modifyAdministrator.do',
	        	contentType:'application/x-www-form-urlencoded',
	        	data:{
	        		field:field,
	        		adminid:row.adminid,
	        		adminname:row.adminname,
	        		admintel:row.admintel
	        	}
	        })
	    }
	});
	$("#btn_add").click(function(){
		 $("#myModal").modal('show');
	})
	$("#submit").click(function(){
				var adminname=$("#adminname").val();
				var admintel=$("#admintel").val();
			    if(adminname==""||admintel==""){
			    	alert("输入的信息不能为空")
			    	return;
			    }
		var count=$("#table").bootstrapTable("getData").length-1;
		$.ajax({
			url:'/repairManager/admin/addAdministrator.do',
			method:'post',
			contentType:'application/x-www-form-urlencoded',
			data:{
				count:count,
				adminname:adminname,
				admintel:admintel
			},
			success:function(data){
				 $("#myModal").modal('hide');
				 $("#table").bootstrapTable("insertRow",{
					 index:count+1,
					 row:{
						 adminid:data,
						 adminname:adminname,
						 admintel:admintel,
						 authorization:1
					 }
				 })
				 
			}
		})
	})
	$("#queryUnCheckBtn").click(function(){
		// 直接使用refresh 如果出现多条分页记录 点下一页的时候会将数据清空
			$('#table').bootstrapTable("refreshOptions",{
	            queryParams:function(params){
	                var query = $.extend(true,params,{status:0});
	                return query;
	            }
	        });
	})
	$("#newRepair").click(function(){
		$('#table').bootstrapTable("refreshOptions",{
	            queryParams:function(params){
	                var query = $.extend(true,params,{status:-1});
	                return query;
	            }
	        });
	})
})
/** ************************************保修状态的判断*********************************** */
function authorizationFtt(value,row,index){
	if(value==0){
		return '超级管理员'
	}else if(value==1){
		return  '管理员'
	}
}
/** ************************************时间格式化处理*********************************** */
function dateFtt(fmt,date)   
{ // author: meizz
  var o = {   
    "M+" : date.getMonth()+1,                 // 月份
    "d+" : date.getDate(),                    // 日
    "h+" : date.getHours(),                   // 小时
    "m+" : date.getMinutes(),                 // 分
    "s+" : date.getSeconds(),                 // 秒
    "q+" : Math.floor((date.getMonth()+3)/3), // 季度
    "S"  : date.getMilliseconds()             // 毫秒
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}
