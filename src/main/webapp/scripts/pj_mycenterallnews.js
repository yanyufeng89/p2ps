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
				url:projectName+"myCenter/getSmsDetail",
				data:{id:id,islook:0},
				dataType:"json",
				success:function(data){
					if(data.returnStatus=='000'){
						$this.css('color','#999');
						$this.attr('data-islook','1');
						//同时条数减1
						$('.unread_count').html(Number($('.unread_count').html())-1);
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
			url:projectName+"myCenter/delSms",
			data:{id:id},
			dataType:"json",
			success:function(data){
				if(data.returnStatus=='000'){
					if(type=='0'){
						$('.unread_count').html(Number($('.unread_count').html())-1);
					}else{
						$('.read_count').html(Number($('.read_count').html())-1);
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
		var $this=$(this);
		var type=$('input[name=currenttype]').val();
		$.ajax({
			type:"POST",
			url:projectName+"myCenter/makeSmsRead",
			dataType:"json",
			success:function(data){
				if(data.returnStatus=='000'){
					$this.addClass('current').siblings().removeClass('current');
					if(type=='0'){
						$('.myAllMessage ul').empty();
						//当全部标记为已读   未读消息的条数就是0
						$('.unread_count').html('0');
					}
				}else{
					
				}
			}
		})
	});

	//清空所有通知
	$('.empty-allnews').live('click',function(){
		var $this=$(this);
		$.ajax({
			type:"POST",
			url:projectName+"myCenter/delSms",
			dataType:"json",
			success:function(data){
				if(data.returnStatus=='000'){
					$this.addClass('current').siblings().removeClass('current');
					$('.myAllMessage ul').empty();
					//当清空所有消息  未读消息的条数变成0  全部消息变成0
					$('.unread_count').html('0');
					$('.read_count').html('0');
				}else{
					
				}
			}
		})
	});
})