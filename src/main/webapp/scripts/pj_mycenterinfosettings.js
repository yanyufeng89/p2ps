$(function(){
	//消息设置
	$('input[type=radio]').live('click',function(){
		//消息类型 filteritem;过滤等级  filterlevel
		var filteritem=$(this).attr('data-filteritem');
		var filterlevel=$(this).attr('data-filterlevel');
		var ischecked=$(this).parent().attr('data-ischecked');
		var $this=$(this);
        if(ischecked=='0'){
        	$.ajax({
    			type:"POST",
    			url:"/myCenter/setSmsFilter",
    			data:{filteritem:Number(filteritem),filterlevel:Number(filterlevel)},
    			dataType:"json",
    			success:function(data){
    			  if(data.returnStatus='000'){
    				$this.parent().attr('data-ischecked','1').siblings().attr('data-ischecked','0');
    			  }
    			}
    		})
        }
	});
})