<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
<title>维修记录</title>
</head>
<body>
	<div class='page'>
		<div class='page__bd'>
			<div class="weui-panel weui-panel_access">
				<div class="weui-panel__hd">维修记录</div>
				<div class="weui-panel__bd"></div>
				<div class="weui-loadmore" id="reading">
					<i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载</span>
				</div>
			</div>
			<div class="weui-loadmore weui-loadmore_line" hidden="hidden">
				<span class="weui-loadmore__tips">已加载全部</span>
			</div>
		</div>
	</div>
</body>
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
<script type="text/javascript">
		$().ready(function(){
			var pageNumber=2;//初始化页数
             $.ajax({
            	 type:'POST',
            	 url:'${path}/api/repairmanRecode.do',
            	 data:{openid:'${openid}'},
            	 success:function(data){
            		 var result=$.parseJSON(data).msg;
            		 var LastPage=$.parseJSON(data).LastPage
            		 $.each(result,function(i,msg){
            			 var date =dateFtt('yyyy-MM-dd hh:mm:ss',new Date(msg.timestamp));
            			 var status="";
            			  if(msg.status==1){
            				  status='未受理'
            			  }else if(msg.status==2){
            				  status='未完工'
            			  }
       	 $(".weui-panel__bd").append("<a href='${path}/wx/repairmanAcceptance.do?wpid="+msg.wpid+"'class='weui-media-box weui-media-box_appmsg'>"+ 
       	           "<div class='weui-cell__hd'>"+
         		 "<img src='${path}/img/repairman.png' style='height:60px;width: 100px;'/></div>"+
         		    "<div class='weui-media-box__bd'>"+
                 "<span class='weui-media-box__title'>报修人:"+msg.studentMsg.sname+"</span>"+
                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>报修描述</span>:"+msg.projectdetail+"</p>"+
                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>报修地点</span>:"+msg.studentMsg.flat.fname+"/"+msg.studentMsg.dormitory+"宿舍</p>"+
                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>报修项目</span>:"+msg.repairProject.repairArea.raname+"/"+msg.repairProject.rpname+"</p>"+
                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>派工备注</span>:"+msg.remark+"</p>"+
                 "<ul class='weui-media-box__info' style='margin-top: 5px;'>"+
                 "<li class='weui-media-box__info__meta'>"+date+"</li>"+
                 "<li class='weui-media-box__info__meta weui-media-box__info__meta_extra'>"+status+"</li>"+ 
                 "</ul></div></a>") 
            		 })
            		 if(LastPage){//判断是否是最后一页
                    	 $(".weui-loadmore").show();
                    	 $(document.body).destroyInfinite();//加载了全部数据,销毁滚动加载
                    	 $("#reading").hide();
                     }
            	 }
             })	
            
 			var loading = false;  //状态标记,由于无法判断是否是加载完成还是正在加载,所以需要状态位进行标记
			$(document.body).infinite().on("infinite", function() {
			  if(loading) return;
			  loading = true;
		    //模拟延迟
				     $.ajax({
				    	 type:'POST',
		            	 url:'${path}/api/repairmanRecode.do',
		            	 data:{openid:'${openid}',
		            		 pageNumber:pageNumber
		            	       },
		            	 success:function(data){
		            		 var result=$.parseJSON(data).msg;
		            		 var LastPage=$.parseJSON(data).LastPage
		            		 $.each(result,function(i,msg){
		            			 var date =dateFtt('yyyy-MM-dd hh:mm:ss',new Date(msg.timestamp));
		            			 var status="";
		            			  if(msg.status==1){
		            				  status='未受理'
		            			  }else if(msg.status==2){
		            				  status='未完工'
		            			  }else if(msg.status==3){
		            				  status='已完工'
		            			  }
		            			  $(".weui-panel__bd").append("<a href='${path}/admin/repairmanAcceptance.do?wpid="+msg.wpid+"'class='weui-media-box weui-media-box_appmsg'>"+ 
		            	       	           "<div class='weui-cell__hd'>"+
		            	         		 "<img src='${path}/img/repairman.png' style='height:60px;width: 100px;'/></div>"+
		            	         		    "<div class='weui-media-box__bd'>"+
		            	                 "<span class='weui-media-box__title'>报修人:"+msg.studentMsg.sname+"</span>"+
		            	                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>报修描述</span>:"+msg.projectdetail+"</p>"+
		            	                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>报修地点</span>:"+msg.studentMsg.flat.fname+"/"+msg.studentMsg.dormitory+"宿舍</p>"+
		            	                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>报修项目</span>:"+msg.repairProject.repairArea.raname+"/"+msg.repairProject.rpname+"</p>"+
		            	                 "<p class='weui-media-box__desc'><span style='color: #FA8072;'>派工备注</span>:"+msg.remark+"</p>"+
		            	                 "<ul class='weui-media-box__info' style='margin-top: 5px;'>"+
		            	                 "<li class='weui-media-box__info__meta'>"+date+"</li>"+
		            	                 "<li class='weui-media-box__info__meta weui-media-box__info__meta_extra'>"+status+"</li>"+ 
		            	                 "</ul></div></a>") 
		            	             })
		            	             pageNumber++;
		            	             loading = false;
		            	             if(LastPage){//判断是否是最后一页
		            	            	 $(".weui-loadmore").show();
		            	            	 $(document.body).destroyInfinite();//加载了全部数据,销毁滚动加载
		            	            	 $("#reading").hide();
		            	             }
		            	           
		            		 }
				     })
			}); 
			}) 
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
			</script>
</html>