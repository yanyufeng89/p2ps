$(function(){
	 //消息提示
	 /*
	    * /*消息类型   0系统消息   1私信  2关注通知(成为你的粉丝)                  2
		*11话题新增回答  12话题的回答新增评论     13话题新增评论   14话题评论新增回复      15话题回答点赞     7
		*21书籍推荐新增回复   22书籍点赞										9
		*31课程新增推荐      32课程新增评论 	 33课程点赞							12
		*41文章新增推荐      42文章新增评论 	 43文章点赞							15
		*51站点新增推荐      52站点新增评论 	 53站点点赞							18
		*61文档新增推荐       62文档新增评论	 63文档点赞							21
	 */
	
	
	//私信点击图标
	$("#menu-item-5 .pm-img").live('click',function(){
		priveteSmg($(this));
	})
	//私信点击数字
	$('#menu-item-5 .zg-noti-number').live('click',function(){
		priveteSmg($(this));
	});
	//系统消息点击图标
	$('#menu-item-4 .smg-img').live('click',function(){
		smsInfo($(this));
	})
	//系统消息点击数字
	$('#menu-item-4 .zg-noti-number').live('click',function(){
		smsInfo($(this));
	})
	//回复私信
	$('.zg-link-replypm').live('click',function(e){
		$('#top-nav-live-new1').hide();
		var receivedid=$(this).attr('data-receivedid');
    	var name=$(this).attr('data-name');
    	var datamodel={
    			receivedid:receivedid,
    			name:name,
    		}
    	$('.pagetemplate').setTemplateURL(projectName+'privateMessage.html');
		$('.pagetemplate').processTemplate(datamodel);
		$(this).parents('#top-nav-live-new1').after($('.pagetemplate').html());
		$('.pagetemplate').empty();
		$('.edui-container').css('z-index','0');
		e.stopPropagation();
	})
	
	//私信或消息点击 (颜色变暗)
	$('.zm-noti7-content-item').live('click',function(e){
		var id=$(this).attr('data-id');
		var ispmorsms=$(this).attr('data-ispmorsms');
		var islook=$(this).attr('data-islook');
		var $this=$(this);
		//0是消息   1是私信
		if(ispmorsms=='1'&&islook=='0'){
			$.ajax({
				type:"POST",
				url:projectName+"myCenter/getSmsDetail",
				data:{id:id,islook:0},
				dataType:"json",
				success:function(data){
					if(data.returnStatus=='000'){
					    var num=Number($('#menu-item-5 .zu-top-nav-pm-count').html())-1;
					    $('#menu-item-5 .zu-top-nav-pm-count').html(num);
						localStorage.setItem('smsCount', num);
						$this.css('color','#999');
						$this.attr('data-islook','1');
						$('#top-nav-live-new1').show();
					}else{
						
					}
				}
			})
		}else if(ispmorsms=='0'&&islook=='0'){
			$.ajax({
				type:"POST",
				url:projectName+"myCenter/getSmsDetail",
				data:{id:id,islook:0},
				dataType:"json",
				success:function(data){
					if(data.returnStatus=='000'){
					    var num=Number($('#menu-item-4 .zu-top-nav-smg-count').html())-1;
					    $('#menu-item-4 .zu-top-nav-smg-count').html(num);
						localStorage.setItem('pmCount', num);
						$this.attr('data-islook','1');
						$this.css('color','#999');
						$('#top-nav-live-new2').show();
					}else{
						
					}
				}
			})
		}
	    
		
	})
	
	$('#top-nav-live-new').live('click',function(){
		e.stopPropagation();  
	})
	//点击界面其他区域关掉弹出层
	$(document).click(function(){
	     $("#top-nav-live-new1").remove();
	     $("#top-nav-live-new2").remove();
	})
	$('#top-nav-live-new1').live('click',function(e){
		e.stopPropagation();  
	})
	$('#top-nav-live-new2').live('click',function(e){
		e.stopPropagation();  
	})
	/*setInterval(updateData,10000);   */
})
//定时更新数据
function updateData(){
	try{
		$.ajax({
			type:"POST",
			url:projectName+"getMyHeadTopAndOper",
			dataType:"json",
			success:function(){
			
			}
		})
	}catch(err){
		 clearInterval(updateData);  
	}
}
//私信
function priveteSmg(obj){
	$('#top-nav-live-new2').remove();
	var $this=obj;
	if($('#top-nav-live-new1').length==0){
		$.ajax({
			type:"POST",
			url:projectName+"myCenter/getNewSms",
			data:{smsType:'1'},
			dataType:"json",
			success:function(data){
				if(data.returnStatus=='000'){
					$.each(data.baseResponseList,function(index,item){
						/*if(item.smscontent.length>23){
							item.smscontentstr=item.smscontent.substring(0,23)+'...';
						}*/
						/*else{
							item.smscontentstr=item.smscontent;
						}*/
						/*item.smscontentstr=item.smscontent;*/
					})
					//加载模板
					 var datamodel={
						 baseResponse:data.baseResponseList,
						 pmorsmg:'1'
					 }
					 $('.pagetemplate').setTemplateURL(projectName+'myCenterInfoTip.html');
					 $('.pagetemplate').processTemplate(datamodel);
					 $this.parents('#menu-top-new').after($('.pagetemplate').html());
					 $('.pagetemplate').empty();
					 //更新私信条数
					 if($('#menu-item-5 .zg-noti-number').length==0&&data.obj2!=0){
						 $('#menu-item-5').append('<span class="zg-noti-number zu-top-nav-pm-count" style="visibility:visible" data-count="'+data.obj2+'">'+data.obj2+'</span>');
					 }else{
						 $('#menu-item-5 .zg-noti-number').html(data.obj2);
					 }
					localStorage.setItem('smsCount', data.obj2);
					
				}else{
					
				}
			}
		})
	}
	else{
		$('#top-nav-live-new1').remove();
	}
}
//消息
function smsInfo(obj){
	var $this=obj;
	$('#top-nav-live-new1').remove();
	if($('#top-nav-live-new2').length==0){
		$.ajax({
			type:"POST",
			url:projectName+"myCenter/getNewSms",
			dataType:"json",
			success:function(data){
				if(data.returnStatus=='000'){
					$.each(data.obj,function(index,item){
						if(item.smstype=='11'||item.smstype=='12'||item.smstype=='13'||item.smstype=='14'||item.smstype=='21'||item.smstype=='22'||
						 item.smstype=='31'||item.smstype=='32'||item.smstype=='33'||item.smstype=='41'||item.smstype=='42'||item.smstype=='43'||
						 item.smstype=='51'||item.smstype=='52'||item.smstype=='53'||item.smstype=='61'||item.smstype=='62'||item.smstype=='63'){
							item.smstype='1'//消息
						}
						else if(item.smstype=='0'){
							item.smstype='0'//系统消息
						}
						else{
							item.smstype='2'//关注成为粉丝   3是私信
						}
					})
					//加载模板
					 var datamodel={
						 baseResponse:data.obj,
						 pmorsmg:'0'
					 }
					 $('.pagetemplate').setTemplateURL(projectName+'myCenterInfoTip.html',null, {filter_data: false});
					 $('.pagetemplate').processTemplate(datamodel);
					 $this.parents('#menu-top-new').after($('.pagetemplate').html());
					 $('.pagetemplate').empty();
					 //更新消息条数
					 if($('#menu-item-4 .zg-noti-number').length==0&&data.obj2!=0){
						 $('#menu-item-4').append('<span class="zg-noti-number zu-top-nav-smg-count" style="visibility:visible" data-count="'+data.obj2+'">'+data.obj2+'</span>');
					 }else{
						 $('#menu-item-4 .zg-noti-number').html(data.obj2);
					 }

					localStorage.setItem('pmCount', data.obj2);
				}else{
					
				}
				
			 
			}
		})
	}else{
		$('#top-nav-live-new2').remove();
	}
}
//获取私信条数
function getSmsCount(){
	 $.ajax({
			type:"POST",
			url:projectName+"myCenter/getNewSms",
			data:{smsType:'1'},
			dataType:"json",
			success:function(data){
			   if(data.returnStatus=='000'){
			        //更新私信条数
					 if($('#menu-item-5 .zg-noti-number').length==0&&data.obj2!=0){
						 $('#menu-item-5').append('<span class="zg-noti-number zu-top-nav-pm-count" style="visibility:visible" data-count="'+data.obj2+'">'+data.obj2+'</span>');
					 }else{
						 $('#menu-item-5 .zg-noti-number').html(data.obj2);
					 }
				   localStorage.setItem('smsCount', data.obj2);
			   }else{
			    
			        
			   }
			}
     })
}
//获取消息条数
function getpmCount(){
	$.ajax({
		type:"POST",
		url:projectName+"myCenter/getNewSms",
		dataType:"json",
		success:function(data){
		   if(data.returnStatus=='000'){
		     //更新消息条数
			 if($('#menu-item-4 .zg-noti-number').length==0&&data.obj2!=0){
				 $('#menu-item-4').append('<span class="zg-noti-number zu-top-nav-smg-count" style="visibility:visible" data-count="'+data.obj2+'">'+data.obj2+'</span>');
			 }else{
				 $('#menu-item-4 .zg-noti-number').html(data.obj2);
			 }
			   localStorage.setItem('pmCount', data.obj2);
		   }else{
		   
		   }
		}
})
}