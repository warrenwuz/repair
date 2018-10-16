$().ready(function() {
	$('#table').bootstrapTable({
		url : '/repairManager/admin/queryWxOnileRepairMsg.do',
		pagination : true,
		 cache: false,     //是否使用缓冲
		queryParamsType:'',
		sidePagination:'server',
		 pageNumber:1,      //初始化加载第一页，默认第一页
         pageSize: 10,      //每页的记录行数（*）
         pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
         uniqueId:'wpid',
		columns : [ 
			{
			field : 'timestamp',
			title : '报修时间',
			width:'15%',
			formatter:function(value,row,index){
				 var date = new Date(value);
		            return dateFtt('yyyy-MM-dd hh:mm:ss',date);
			}
		}, {
			field : 'studentMsg.flat.fname',
			title : '报修地点',
			width:'20%',
			formatter:function(value,row,index){
				return value+'/'+row.studentMsg.dormitory+'宿舍'
			}
		}, {
			field : 'repairProject.repairArea.raname',
			title : '报修类别',
			width:'18%',
			formatter:function(value,row,index){
				return value+'/'+row.repairProject.rpname
			}
		}, {
			field : 'projectdetail',
			title : '描述',
			width:'12%',
			cellStyle:function(value){
				return {title:value};
			},
			class:'projectdetail',
		}, {
			field : 'studentMsg.sname',
			title : '报修人',
			width:'10%',
		}, {
			field : 'tel',
			title : '报修电话',
			width:'10%',
		}, {
			field : 'status',
			title : '状态',
			formatter:statusFtt,
			width:'10%',
		},{
			field:'wpid',
			title:'详情',
			width:'5%',
			formatter:function(value,row,index){
				return "<a href='/repairManager/admin/repairMsgDetail.do?wpid="+value+"'><i class='glyphicon glyphicon-pencil'style='color: #000080;'></i></a>"
			}
		} ],
		onLoadSuccess:function(value){
			/**
			 * 加载数据完成后,对描述的td进行加title属性
			 */
			$("#table").find("td[class='projectdetail']").each(function(i,val){
			          $(this).attr("title",$(this).html())
			})
		}
	});
	/***
	 * 切换状态的菜单按钮
	 */
	$(".queryStatus").click(function(){
		$(".active").removeClass("active");
		var statusText=$(this).children("a").children("i").html();
		var param=null;
		console.log(statusText)
		if(statusText=="待审核"){
			param={status:0}
		}else if(statusText=="正在派工"){
			param={status:1}
		}else if(statusText=="正在维修"){
			param={status:2}
		}else if(statusText=="已完工"){
			param={status:3}
		}else if(statusText=="已验收"){
			param={status:4}
		}else if(statusText=="全部"){
			param={status:-1}
		}
		console.log(param)
		$('#table').bootstrapTable("refreshOptions",{
            queryParams:function(params){
                var query = $.extend(true,params,param);
                return query;
            }
        });
		$(this).addClass("active")	
	});
	$("#newRepair").click(function(){
		$('#table').bootstrapTable("refreshOptions",{
	            queryParams:function(params){
	                var query = $.extend(true,params,{status:-1});
	                return query;
	            }
	        });
	})
})
/**************************************保修状态的判断************************************/
function statusFtt(value,row,index){
	if(value==0){
		return '未审核'
	}else if(value==1){
		return  '正在派工'
	}else if(value==2){
		return '正在维修'	
	}else if(value==3){
		return '已完工' 
	}else if(value==4){
		return '已验收' 
	}
}
/**************************************时间格式化处理************************************/
function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}
