//全部消息
$(function(){
	//点击每条消息 颜色变暗
	$('.myAllMessage ul li').live('click',function(){
		var id=$(this).attr('data-id');
		var islook=$(this).attr('data-islook');
	    var $this=$(this);
	    if(islook=='0'){
	    	$.ajax({
				type:"POST",
				url:"/myCenter/getSmsDetail",
				data:{id:id,islook:0},
				dataType:"json",
				success:function(data){
					if(data.returnStatus=='000'){
						$this.css('color','#999');
						$this.attr('data-islook','1');
						//同时条数减1
						$('#unread label').html(Number($('#unread label').html())-1);
					}else{
						
					}
				}
			})
	    }
	})
	
	//删除消息(未读通知)
	$('.zg-cancelnews').live('click',function(){
		var id=$(this).attr('data-id');
		var type=$(this).attr('data-type');
		var $this=$(this);
		$.ajax({
			type:"POST",
			url:"/myCenter/delSms",
			data:{id:id},
			dataType:"json",
			success:function(data){
				if(data.returnStatus=='000'){
					if(type=='0'&&$this.parents('li').attr('data-islook')!='1'){
						$('#unread label').html(Number($('#unread label').html())-1);
					}else{
						$('#markread label').html(Number($('#markread label').html())-1);
					}
					$this.parents('li').remove();
				}else{
					
				}
			}
		})
	});
	//私信回复
	$('.zg-link-replypm').live('click',function(e){
		var receivedid=$(this).attr('data-receivedid');
    	var name=$(this).attr('data-name');
    	var datamodel={
    			receivedid:receivedid,
    			name:name,
    		}
    	$('.pagetemplate').setTemplateURL(projectName+'privateMessage.html');
		$('.pagetemplate').processTemplate(datamodel);
		$(this).parents('ul').after($('.pagetemplate').html());
		$('.pagetemplate').empty();
		e.stopPropagation();
	});
	//全部标记为已读
	$('.mark_read').live('click',function(){
		$(this).addClass('current').siblings().removeClass('current');
		var $this=$(this);
		var type=$('input[name=currenttype]').val();
		$.confirm({
			'title'		: '确认全部标记为已读',
			'message'	: "确认全部标记为已读吗?",
			'buttons'	: {
				'确认'	: {
					'class'	: 'blue',
					'action': function(){
						makeSmsRead($this,type);
					}
				},
				'取消'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
		
	});

	//清空所有通知
	$('.empty-allnews').live('click',function(){
		$(this).addClass('current').siblings().removeClass('current');
		var type=$('input[name=currenttype]').val();
		var $this=$(this);
		$.confirm({
			'title'		: '确认清空所有通知',
			'message'	: "确认清空所有通知吗?",
			'buttons'	: {
				'确认'	: {
					'class'	: 'blue',
					'action': function(){
						delSms($this,type);
					}
				},
				'取消'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
	});

})

function makeSmsRead($this,type) {
	$.ajax({
		type : "POST",
		url : projectName + "myCenter/makeSmsRead",
		dataType : "json",
		success : function(data) {
			if (data.returnStatus == '000') {
				$this.addClass('current').siblings().removeClass('current');
				if (type == '0') {
					$('.myAllMessage ul').empty();
					$('.page-inner').empty();
					// 当全部标记为已读 未读消息的条数就是0
					$('#unread label').html('0');
				}
			} else {

			}
		}
	})
}
function delSms($this,type){
	$.ajax({
		type:"POST",
		url:"/myCenter/delSms",
		data:{islook:type},
		dataType:"json",
		success:function(data){
			if(data.returnStatus=='000'){
				$this.addClass('current').siblings().removeClass('current');
				$('.myAllMessage ul').empty();
				$('.page-inner').empty();
				//当清空所有消息  未读消息的条数变成0  全部消息变成0
				if(type==0){
					$('#unread label').html('0');
				}else{
					$('#markread label').html('0');
				}
				
			}else{
				
			}
		}
	})
}