<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css" rel="stylesheet" />
		<title>在线报修</title>
		<style type="text/css">
			input {
				padding-left: 10%;
			}
			.weui-cell_access {
				
				padding-left: 10%;
			}
			.page__hd{
			  background-image: url(${path}/img/logobg.png);
				height: 50px;
				width: 100%;
			}
			#text_title{
				text-align: center;
				color: white;
			}
		   .tone-text{
		   	color: #0088CC;
		   }
		   .weui-label{
		    	color: #0088CC;
		   }
		   .picker-classname{
		     font-size: 20px;
		   }
		</style>
	</head>
	<body  bgcolor="#f8f8f8">
		<div class="page">
			<div class="page__hd" >
				<div class="page__title" >
					<h3 id="text_title" style="padding-top: 8px;">太原师范学院服务中心报修单</h3>
				</div>
				
			</div>
				<div class="page__bd">
					<div class="weui-cells weui-cells_form" style="padding: 0px;margin: 0px;">
						<form>
						<div class="weui-cell">
						    <input name="stuid" id="stuid" value="${studentMsg.stuid}" type="hidden">
							<div class="weui-cell__hd"><label class="weui-label">报修公寓*</label></div>
							<div class="weui-cell__bd flat"> 
								<input class="weui-input" type="text"  disabled="disabled" value="${studentMsg.flat.fname}">
								<input class="weui-input" type="hidden"  disabled="disabled" value="${studentMsg.flat.fid }" name="fid" id="fid"/>
							</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__hd"><label class="weui-label">宿舍号*</label></div>
							<div class="weui-cell__bd dormitory"> 
								<input class="weui-input" type="text"  disabled="disabled" value="${studentMsg.dormitory}" name="dormitory"/>
							</div>
						</div>
						<div class="weui-cell" >
							<div class="weui-cell__hd"><label class="weui-label">姓名*</label></div>
							<div class="weui-cell__bd sname">
								<input class="weui-input" type="text"  disabled="disabled" value="${studentMsg.sname}" name="sname" />
							</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__hd"><label class="weui-label">电话*</label></div>
							<div class="weui-cell__bd">
								<input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入电话号码" value="${studentMsg.tel}" name="tel" id="tel"/>
							</div>
						</div>
						<div class="weui-cell weui-cell_select weui-cell_select-after">
							<div class="weui-cell__hd"><label class="weui-label" for="">报修区域*</label></div>
							<div class="weui-cell__bd">
								<div class="weui-cell weui-cell_access repairArea-picker">请选择报修区域</div>
								<input type="hidden" name="raid" value="" id="raid"/> 
							</div>
						</div>
						<div class="weui-cell weui-cell_select weui-cell_select-after">
							<div class="weui-cell__hd"><label class="weui-label" for="">报修项目*</label></div>
							<div class="weui-cell__bd">
								<div class="weui-cell weui-cell_access repairProject-picker">请选择报修项目</div>
								<input type="hidden" name="rpid" value="" id="rpid" /> 
							</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<textarea class="weui-textarea" placeholder="请输入报修项目详情*" rows="2" name="projectdetail" id="projectdetail"></textarea>
							<!-- 	<div class="weui-textarea-counter"><span>0</span>/200</div> -->
							</div>
						</div>
						<!--
                        	作者：wuzhe1159@163.com
                        	时间：2018-01-25
                        	描述：微信上传插件  配合gallery使用
                        -->
                        <div class="weui-gallery" id="gallery">
				            <span class="weui-gallery__img" id="galleryImg"></span>
				            <div class="weui-gallery__opr">
				                <a href="javascript:" class="weui-gallery__del">
				                    <i class="weui-icon-delete weui-icon_gallery-delete"></i>
				                </a>
				            </div>
                        </div>
                        <div class="weui-cells weui-cells_form" id="uploader">
                        <div class="weui-cell">
                        	 <div class="weui-cell__bd">
                        	 	<!--weui-uploader Start-->
                        	 	 <div class="weui-uploader">
                        	 	 	<!--weui-uploader__hd Start-->
                        	 	 	 <div class="weui-uploader__hd">
				                            <p class="weui-uploader__title">图片上传</p>
				                            <div class="weui-uploader__info">请尽量提供故障图片</div>
                                      </div>
                                      <!--weui--uploader__bd Start-->
                        	 	 	   <div class="weui-uploader__bd">
					                            <ul class="weui-uploader__files" id="uploaderFiles">
					                            </ul>
			                            <div class="weui-uploader__input-box">
			                                <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*" capture=camera multiple="multiple"  name="file"/>
			                            </div>
                      				  </div>
                        	 	 </div>
                        	 </div>
                        </div>
						</div>
						</form>
				<button  class="weui-btn weui-btn_primary form-submit" id='OnlineRepairSubmit'> 提交 </button>
						
					</div>
					
				</div>
				<div class="page__ft" style="padding-top: 30px;">
					<div class="weui-footer">
						<p class="weui-footer__text">Copyright &copy; 2017-2018 太原师范学院维修服务平台</p>
					</div>
				</div>
			</div>

	</body>
       <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" src="https://res.wx.qq.com/open/libs/weuijs/1.1.2/weui.min.js"></script>
		<script type="text/javascript" src="${path}/js/wxOnlineRepair.js" ></script>
</html>