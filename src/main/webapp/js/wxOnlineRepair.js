			$().ready(function() {
				//js的数组不需要确认长度 
				var repairAreaPickArray=new Array();//维修区域下拉选项
				var repairProjectArray=new Array();;//维修项目下拉选项 
				//填写报修单前提醒
		     weui.alert('为防止同学们错填导致维修进度,报修单中不可更改报修公寓、宿舍号和姓名(为默认登记的个人信息)', { title: '填写报修单提醒' });
		      //异步获取维修区域
			     $.ajax({
					 type:"Get",
					 url:"/repairManager/queryRepairAreaJson.do",
					 success:function(data){
							$.each($.parseJSON(data), function(i, val) {// 这个使用parseJSON是因为传过来的data需要转换成json对象
								repairAreaPickArray[i]={label:val.raname,value:val.raid}
							})
					 }
				 })	
		     //维修区域picker事件
			 $(".repairArea-picker").click(function() {
					    weui.picker(repairAreaPickArray, {
						className: 'picker-classname',
						container: 'body',
						defaultValue: [3],
						onConfirm: function(result) {
							var data = result[0];
							$(".repairArea-picker").html(data.label);
							$(".repairProject-picker").html("请选择报修项目");
							$("#raid").val(data.value);
							$("#rpid").val("");
							var raid=parseInt(data.value);
							//异步获取维修区域
						     $.ajax({
								 type:"Get",
								 url:"/repairManager/queryRepairProjectByRaid.do",
								 data:{raid:raid},
								 success:function(data){
									 repairProjectArray=new Array();
										$.each($.parseJSON(data), function(i, val) {// 这个使用parseJSON是因为传过来的data需要转换成json对象
											repairProjectArray[i]={label:val.rpname,value:val.rpid}
										})
								 }
							 })	
						},
						id: 'repairAreaPicker'
					  })
	     	 });
			 
			 //维修项目picker事件
			 $(".repairProject-picker").click(function() {
				 if(repairProjectArray.length==0){
					 weui.alert("请先选择报修区域")
					 return;
				 }
				    weui.picker(repairProjectArray, {
					className: 'picker-classname',
					container: 'body',
					defaultValue: [3],
					onConfirm: function(result) {
						var data = result[0];
						$(".repairProject-picker").html(data.label);
						$("#rpid").val(data.value);
					},
					id: 'repairProjectPicker'
				  })
  	       });
		//上传插件
		     var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>',
		      $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
		      $uploaderInput = $("#uploaderInput"), $uploaderFiles = $("#uploaderFiles"),
		      $gallery__del=$(".weui-gallery__del") ;
		       var uploadCount = 0;
		       var uploadImages=new Array();
		       //weui.js 上传图片js
		       weui.uploader('#uploader', {
		    	   url: '/repairManager/upload/uploadImage.do',
		    	   auto: true,
		    	   type: 'file',
		    	   fileVal: 'image',//上传到服务器端的文件标识
		    	   compress: {//压缩配置
		    	       width: 1600,
		    	       height: 1600,
		    	       quality: .8
		    	   },
		    	   onBeforeQueued:function(files){
		    		   if(["image/jpg", "image/jpeg", "image/png", "image/gif"].indexOf(this.type) < 0){
		    	           weui.alert('请上传图片');
		    	           return false; // 阻止文件添加
		    	       } if(this.size > 10 * 1024 * 1024){
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
		    	   onSuccess: function (ret) {
		    		   uploadImages.push(ret);
                        console.log(uploadImages)
		    	   },
		    	   onError: function(err){
		    	       console.log(this, err);
		    	       // return true; // 阻止默认行为，不使用默认的失败态
		    	   }
		       })
             $uploaderFile=null;
            //这样写是为了绑定未来元素 使用gallery预览图片
             $uploaderFiles.on("click", "li", function(){
             	$uploaderFile=$(this)
                $galleryImg.attr("style", this.getAttribute("style"));
                $gallery.fadeIn(100);//方法使用淡入效果来显示被选元素，假如该元素是隐藏的
            });
            //隐藏图片
            $gallery.on("click", function(){
               $gallery.fadeOut(100);
           });
           $gallery__del.on("click",function(even){//事件会向上冒泡
           	weui.confirm('确认删除照片',
           	function(){  
           		console.log( $uploaderInput.files())
           		$uploaderFile.remove() 
           	},
           	function()
           	{ console.log('no')
           	});
           })
          //公寓、宿舍号和姓名不能更改提醒
          $(".sname,.flat,.dormitory").click(function() {
					weui.alert("公寓、宿舍号和姓名不能更改")
		  });
           $("#OnlineRepairSubmit").click(function(){
              var tel=$("#tel").val();
              var raid=$("#raid").val();
              var rpid=$("#rpid").val();
              var projectdetail=$("#projectdetail").val();
              if(raid==""||rpid==""||projectdetail==""){
              	 	weui.alert("打*号的内容为必填项,请务必填写完整")
              }else{
            	  $.ajax({
            		  type:'POST',
            		  url:'/repairManager/wx/wxOnlineRepair.do',
            		  data:{
            			  stuid:$("#stuid").val(),
            			  tel:$("#tel").val(),
            			  rpid:$("#rpid").val(),
            			  projectdetail:$("#projectdetail").val(),
            			  uploadImages:uploadImages.toString()
            		  },
            		  success:function(data){
            			 $(window).attr('location','/repairManager/static/submitSuccess.html'); 
            		  }
            		  
            	  })
              }
           })
		 })
