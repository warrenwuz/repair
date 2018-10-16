//WebScoket的开启方法
$().ready(function(){
    	    var index=0;
    		 var confirm=null;//维修通知单提醒
    	    var websocket = null;
    	    var host = window.location.host;//获取主机地址
    	    console.log(host)
    	    //判断当前浏览器是否支持WebSocket
    	    if ('WebSocket' in window) {
    	        websocket = new WebSocket("ws://"+host+"/repairManager/websocket.do");
    	    }
    	    else {
    	        alert('当前浏览器 Not support websocket')
    	    }

    	    //连接发生错误的回调方法
    	    websocket.onerror = function () {
    	    };

    	    //连接成功建立的回调方法
    	    websocket.onopen = function () {
    	    }

    	    //接收到消息的回调方法
    	    websocket.onmessage = function (event) {
    	    	  index=index+1;
    	    	  if(confirm!=null){
    	    		  confirm.close();
    	    	  }
    	    	 $("#notifymusic")[0].play();
    	    	 confirm=layer.open({
                     title: '新的报修订单'
                     ,content: '你有'+index+'条维修通知单等待查看',
                     btn:['查看', '取消'],
                     btnAlign: 'c',
                     offset: 'lt',
                     skin:'layui-layer-lan'
                     ,yes:function(index, layero){
                         $(window).attr("location","/repairManager/admin/wxOnLineRepairMsgManager.do")
                     },
                     btn2:function(index,layero){
                    	 layer.msg("请及时查看")
                         layer.close(index)
                     },
                     anim:6
                 });
    	    }

    	    //连接关闭的回调方法
    	    websocket.onclose = function () {
    	        setMessageInnerHTML("WebSocket连接关闭");
    	    }

    	    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    	    window.onbeforeunload = function () {
    	    	  websocket.onclose;//关闭
    	    }
        $("#test").click(function(){
            $("#notifymusic")[0].play();//播放提示音
        }); 
        layui.use(['element', 'layer'], function () {
            var element = layui.element;
            var layer = layui.layer
            layer.photos({
  			  photos: '#layer-photos-demo'
  			  ,anim: 0 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
  			});
        });
       
     })
