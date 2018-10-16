$().ready(function() {
	   $.ajax({
		   async : false,
			 type:"Get",
			 url:"/repairManager/admin/repairProjectForJson.do",
			 success:function(data){
					$.each($.parseJSON(data), function(i, val) {// 这个使用parseJSON是因为传过来的data需要转换成json对象
					console.log(val)
						$("#repairProject").append("<option value='"+val.rpid+"'>"+val.rpname+"/"+val.repairArea.raname+"</option>")
					})
			 }
		 })	
	$('#repairProject').multiselect({
		nonSelectedText : "请选择维修项目",
		nSelectedText : "个维修项目",
		allSelectedText : "全部维修项目",
		 enableCollapsibleOptGroups: true,
		 multiple:true
	});
	var adminArray=null;
	$('#table').bootstrapTable({
		url : '/repairManager/admin/queryRepairmaForJson.do',
		pagination : true,
		 cache: false,     // 是否使用缓冲
		queryParamsType:'',
		sidePagination:'client',
		 pageNumber:1,      // 初始化加载第一页，默认第一页
         pageSize: 10,      // 每页的记录行数（*）
         pageList: [10, 25, 50, 100],  // 可供选择的每页的行数（*）
         uniqueId:'rmid',
         clickToSelect: true, 
         detailView:true,//父子表
		columns : [ 
			{
			field : 'rmid',
			title : '工号',
		}, {
			field : 'rmname',
			title : '姓名',
			 editable:{
				 type: 'text',
                 title: '姓名',
                 validate: function (v) {
                     if (!v) return '姓名不能为空';
                 }
			 }
		}, {
			field : 'rmtel',
			title : '电话',
			 editable:true
		}],
            onExpandRow: InitSubTable,
	    onEditableSave: function (field, row, oldValue, $el) {
	    	console.log(row)
	        $.ajax({
	        	type:'POST',
	        	url:'/repairManager/admin/modifyRepairman.do',
	        	contentType:'application/x-www-form-urlencoded',
	        	data:{
	        		field:field,
	        		rmid:row.rmid,
	        		rmname:row.rmname,
	        		rmtel:row.rmtel
	        	}
	        })
	    }
	});
	$("#btn_add").click(function(){
		 $("#myModal").modal('show');
	})
	$("#submit").click(function(){
				var rmname=$("#rmname").val();
				var rmtel=$("#rmtel").val();
				var rpid=$("#repairProject").val();
				console.log(rpid)
			    if(rmname==""||rmtel==""||rpid==""){
			    	alert("输入的信息不能为空")
			    	return;
			    }
		var count=$("#table").bootstrapTable("getData").length;
		$.ajax({
			url:'/repairManager/admin/addRepairman.do',
			traditional:true,
			method:'post',
			contentType:'application/x-www-form-urlencoded',
			data:{
				count:count,
				rmname:rmname,
				rmtel:rmname,
				rpids:rpid
		
			},
			success:function(data){
				 $("#myModal").modal('hide');
				 $("#table").bootstrapTable("insertRow",{
					

					 row:{
						 rmid:data,
						 rmname:rmname,
						 rmtel:rmtel,
					 }
				 })
				 
			}
		})
	})
})
/** ************************************初始化子表格*********************************** */
   function InitSubTable (index, row, $detail) {
        var parentid = row.rmid;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: '/repairManager/admin/queryProjectByRmidForJson.do',
            method: 'get',
            queryParams: {rmid: parentid },
            clickToSelect: true,
            uniqueId: "rpid",
            pageSize: 10,
            pageList: [10, 25],
            columns: [
            	{
                field: 'rpname',
                title: '维修范围',
                formatter:function(value,row,index){
                	return value+"/"+row.repairArea.raname
                }
            }, {
                field: 'rpid',
                title: '操作',
                formatter:function(value,row,index){
    				return "<a href='#'><i class='glyphicon glyphicon-remove'style='color: red;'></i></a>"
                }	
            }],
            onLoadSuccess:function(data){
            	cur_table.append("<caption><button class='btn btn-block btn-primary repairmanProjectAdd' value="+parentid+" >添加</button></caption>")
            }
        });
        $(".repairmandetail").on('click','.repairmanProjectAdd',function(){
        	$("#repairUnSelectProjectSubmit").val($(this).val());
        	$('#repairUnSelectProject').multiselect({
  				nonSelectedText : "请选择维修项目",
  				nSelectedText : "个维修项目",
  				allSelectedText : "全部维修项目",
  				 enableCollapsibleOptGroups: true,
  				 multiple:true
  			});
        	 $.ajax({
        		 url:'/repairManager/admin/queryUnSelectRepairProjectForJson.do',
        		 data:{rmid:$(this).val()},
        		 success:function(data){
        			 //首先清空多选框
        			 $('#repairUnSelectProject').empty();
        			 $.each($.parseJSON(data), function(i, val) {
        				 // 这个使用parseJSON是因为传过来的data需要转换成json对象
     			$("#repairUnSelectProject").append("<option value='"+val.rpid+"'>"+val.rpname+"/"+val.repairArea.raname+"</option>")
        			 }) 
        			 //重新建立多选框
        			 $("#repairUnSelectProject").multiselect("rebuild");
        			 $("#selectModal").modal("show");
        		 }
        	 })
        })
        //添加维修人员维修范围
        $("#repairUnSelectProjectSubmit").click(function(){
        	var rpid=$("#repairUnSelectProject").val();
        	console.log(rpid)
        	if(rpid.length==0){
        		alert("请选择一个维修项目,在点击提交")
        		return;
        	}
        	$.ajax({
        		url:'/repairManager/admin/addRepairProjectForRepairman.do',
        		traditional:true,
        		contentType:'application/x-www-form-urlencoded',
        		method:'post',
        		data:{rpids:rpid,
        		     rmid:$(this).val()	
        		},
        		success:function(){
        			alert("添加成功")
        			$("#selectModal").modal("hide");
        		}
        		
        	})
        })
    };
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
