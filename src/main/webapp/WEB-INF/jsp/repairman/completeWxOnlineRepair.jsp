<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css"
	rel="stylesheet" />
<title>提交完工</title>
<style type="text/css">
input {
	padding-left: 10%;
}

.weui-cell_access {
	color: #A9A9A9;
	padding-left: 10%;
}

.page__hd {
	background-image: url(${path}/img/logobg.png);
	height: 50px;
	width: 100%;
}

#text_title {
	text-align: center;
	color: white;
}

.page__desc {
	color: #888;
	font-size: 18px
}

p {
	word-wrap: break-word;
	word-break: break-all;
	overflow: hidden;
}

.tone-text {
	color: #0088CC;
}
</style>
</head>

<body bgcolor="#f8f8f8">
	<div class="page">
		<div class="page__hd">
			<div class="page__title">
				<h3 id="text_title" style="padding-top: 8px;">太原师范学院服务中心完工单</h3>
			</div>
		</div>
		<div class="page__bd">
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
								<textarea class="weui-textarea" placeholder="请填写完工备注" rows="3" name="completeremark" id="completeremark"></textarea>
							 	<div class="weui-textarea-counter"><span>0</span>/200</div>
							</div>
						</div>
				<div class="weui-cell">
					<div class="weui-cell__bd">
						<div class="weui-uploader">
							<div class="weui-uploader__hd">
								<p class="weui-uploader__title">图片上传</p>
								<div class="weui-uploader__info">
									<span id="uploadCount">0</span>/5
								</div>
							</div>
							<div class="weui-uploader__bd">
								<ul class="weui-uploader__files" id="uploaderFiles"></ul>
								<div class="weui-uploader__input-box">
									<input id="uploaderInput" class="weui-uploader__input"
										type="file" accept="image/*" capture="camera" multiple="" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
             	<button  class="weui-btn weui-btn_primary form-submit" id='completeSubmit'>提交评价 </button>
		</div>
		<div class="page__ft" style="padding-top: 30px;">
			<div class="weui-footer">
				<p class="weui-footer__text">Copyright &copy; 2017-2018
					太原师范学院维修服务平台</p>
			</div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://res.wx.qq.com/open/libs/weuijs/1.1.2/weui.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
						//上传插件
						var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>', $gallery = $("#gallery"), $galleryImg = $("#galleryImg"), $uploaderInput = $("#uploaderInput"), $uploaderFiles = $("#uploaderFiles"), $gallery__del = $(".weui-gallery__del");
						var uploadCount = 0;
						var uploadImages = new Array();
						//weui.js 上传图片js
						weui.uploader('#uploader', {
							url : '${path}/upload/uploadImage.do',
							auto : true,
							type : 'file',
							fileVal : 'image',//上传到服务器端的文件标识
							compress : {//压缩配置
								width : 1600,
								height : 1600,
								quality : .8
							},
							onBeforeQueued : function(files) {
								if ([ "image/jpg", "image/jpeg", "image/png",
										"image/gif" ].indexOf(this.type) < 0) {
									weui.alert('请上传图片');
									return false; // 阻止文件添加
								}
								if (this.size > 10 * 1024 * 1024) {
									weui.alert('请上传不超过10M的图片');
									return false;
								}
								if (files.length > 5) { // 防止一下子选择过多文件
									weui.alert('最多只能上传5张图片，请重新选择');
									return false;
								}
								if (uploadCount + 1 > 5) {
									weui.alert('最多只能上传5张图片');
									return false;
								}
								++uploadCount;
							},
							onSuccess : function(ret) {
								uploadImages.push(ret);
								console.log(uploadImages)
							},
							onError : function(err) {
								console.log(this, err);
								// return true; // 阻止默认行为，不使用默认的失败态
							}
						})
						$uploaderFile = null;
						//这样写是为了绑定未来元素 使用gallery预览图片
						$uploaderFiles.on("click", "li", function() {
							$uploaderFile = $(this)
							$galleryImg.attr("style", this
									.getAttribute("style"));
							$gallery.fadeIn(100);//方法使用淡入效果来显示被选元素，假如该元素是隐藏的
						});
						//隐藏图片
						$gallery.on("click", function() {
							$gallery.fadeOut(100);
						});
						$gallery__del.on("click", function(even) {//事件会向上冒泡
							weui.confirm('确认删除照片', function() {
								console.log($uploaderInput.files())
								$uploaderFile.remove()
							}, function() {
								console.log('no')
							});
						})
						$("#completeSubmit").click(function(){
							var completeremark=$("#completeremark").val();
				              if(completeremark==""){
				              	 	weui.alert("请填写完工备注")
				              }else{
				            	  $.ajax({
				            		  type:'POST',
				            		  url:'${path}/wx/CompleteWxOnlineRepair.do',
				            		  data:{
				            			  wpid:"${wpid}",
				            			  completeremark:completeremark,
				            			  uploadImages:uploadImages.toString()
				            		  },
				            		  success:function(data){
				            			  weui.toast('提交成功', {
				            				    duration: 3000,
				            				    className: 'custom-classname',
				            				    callback: function(){
				            	 	 $(window).attr('location','${path}/wx/repairmanRecode.do?openid=${openid}');
				            				    }
				            				});
				            		
				            		  }
				            		  
				            	  })
				              }
						})
					})
</script>
</html>