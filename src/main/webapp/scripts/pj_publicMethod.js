var userInfo;
$(function(){	
	//获取用户信息
	getCurrentUser();
	
	//举报按钮点击
	$('#topicreport').live('click',function(){
		//举报人电话
		var tel=userInfo.mobile;
		//举报人id
		var userid=userInfo.userid;
		//被举报人
		var userreportid=$(this).attr("data-userreportid");
		//被举报的话题
		var topiccommentid=$(this).attr("data-topiccommentid");
		
		//举报类型
		var reporttype=$(this).attr("data-reporttype");
		
		//举报原因
		var reportcause=$('#reportReasons input[type=text]').val();
		//举报选项的id
		var reportcauseid='';
		//1代表是其他理由
		var reportradiotype='';
		$('#reportReasons input[type=radio]').each(function(){
			if($(this).is(':checked')){
				reportcauseid=$(this).attr('id');
				reportradiotype=$(this).attr('data-type');
			}
		})
		$('.zh-pm-warnmsg').html('').hide();
		
		if((reportradiotype==1&&$.trim(reportcause)=='')||($.trim(reportcause)==''&& reportcauseid=='')){
			$('.zh-pm-warnmsg').html('<i class="tips_icon"></i>请填写举报内容').show();
			$('.ReportMenu-actions').css('padding-top','0');
			return false;
		}
		
		$this=$(this);
		// {"tbl_user","tbl_article","tbl_books","tbl_courses","tbl_docs","tbl_topics","tbl_topics_comment"}; REPORTTYPE_INDEX
		// 从0开始数  
		$.ajax({
			type:"POST",
	     	url:projectName+"myCenter/reportReportInfo",
	     	data:{reportuserid:userid,reporttel:tel,reportbyuserid:userreportid,reporttargetid:topiccommentid,
	     		reportcause:reportcause,REPORTTYPE_INDEX:reporttype,reportcauseid:reportcauseid},
	        dataType:"json",
	        success:function(data){
	        	if(data.returnStatus=='000'){//返回成功
	        		closeReport($this);
	            }
	        	else{
	        		
	        	}
	        }
		})
		
	});
	
	//书籍、课程 文章详情  评论 (加载输入框)
	$('.js-toggleCommentBox').live('click',function(){
		if($(this).parents('.media-right').find('.comment-app-holder').length==0){
			var $child=$(this).children();
        	$(this).empty().append($child).append('收起评论');
    		//被评论人id
    	   var recommend=$(this).attr('data-recommend');
    	   //关联主体的id
    	   var relationid=$(this).attr('data-relationid');
    	   //被推荐人的名字
    	   var recommendname=$(this).attr('data-recommendname');
    	   var $children=$(this).children();
    	
    	   //加载模板
    	   var data={
    			   recommend:recommend,
    			   recommendname:recommendname,
    			   relationid:relationid,
    	   }
    	   $('.headiconintotem').setTemplateURL(projectName+'bookCommentTemplate.html');
    	   $('.headiconintotem').processTemplate(data);
    	   $(this).parents('.media-right').append($('.headiconintotem').html());
    	   $(".headiconintotem").empty();

 		   autosize(document.querySelectorAll('._InputBox_blur_3JWV'));

		}else{
			var $child=$(this).children();
        	$(this).empty().append($child).append('评论');
        	$(this).parents('.operate').next().remove();
		}
	  
	});
	//书籍、课程详情--回答里面的评论(评论里面继续评论)
    $('._InputBox_root_1Xwi').live('focus',function(){
    	$(this).removeClass('_InputBox_blur_3JWV').parent().addClass('_CommentForm_expanded_39uD');
    })
    
    //书籍、课程详情--回答里面的评论(评论里面继续评论)--取消
    $('._CommentForm_cancelButton_cy3u').live('click',function(){
    	answerreplyCancel($(this));
    })
	
	//右侧部分始终固定在顶部(话题详情页  书籍详情页)
	 if($('.plus-main-sidebar').length){
		 var t=$('.plus-main-sidebar').offset().top;
		 var mh = $('.plus-main-content').height();
	     var fh = $('.plus-main-sidebar').height();
	     var fw = $('.plus-main-sidebar').offset().left;
	     $(window).scroll(function(e){
	 		var s = $(document).scrollTop();	
	 		if(s > t - 10){
	 			$('.plus-main-sidebar').css('position','fixed');
	 		    $('.plus-main-sidebar').css('left',fw+'px');
	 			$('.plus-main-sidebar').css('top','-10px');			
	 		}else{
	 			$('.plus-main-sidebar').css('position','');
	 		}
	 	})
	 }
	
	//关掉弹出层(举报)
    $('.modal-dialog-title-close').live('click',function(){
    	closeReport($(this));
    });

	$('textarea[name=reportcontent]').live('click',function(){
		if($.trim($(this).val()).length==0){
			$(this).val('').focus();
		}
	});
	//私信加载
    $('.private-message').live('click',function(){
    	var receivedid=$(this).attr('data-receivedid');
    	var name=$(this).attr('data-name');
    	var moduletype=$(this).attr('data-moduletype');
    	var datamodel={
    			receivedid:receivedid,
    			name:name,
    		}
    	$('.pagetemplate').setTemplateURL(projectName+'privateMessage.html');
		$('.pagetemplate').processTemplate(datamodel);
		$('.page').append($('.pagetemplate').html());
		$('.pagetemplate').empty();
		$('.pinwheel_wrap').hide();
		$('.edui-container').css('z-index','0');
		
    });
    //发送私信
    $('#zh-question-pm-send-button').live('click',function(){
    	var receivedid=$(this).attr('data-receivedid');
    	var smscontent=$('.pj-seamless-input-origin-element').val();
    	$('.zh-pm-warnmsg').html('').hide();
    	$this=$(this);
    	if($.trim(smscontent)==''){
    		$('.zh-pm-warnmsg').html('请填写私信内容。').show();
    		return false;
    	}
    	$.ajax({
    		type:"POST",
        	url:projectName+"myCenter/sendSmsPrivate",
        	data:{receivedid:receivedid,smstype:1,smscontent:smscontent},
        	dataType:"json",
            success:function(data){
            	if(data.returnStatus=='000'){//返回成功 
            		closeReport($this);
            	}
            	else{
            		
            	}
            }
    	})
    });
    //取消私信
    $('.zm-command-cancel').live('click',function(){
    	closeReport($(this));
    });
    //私信文本框获得焦点
	 $('.zg-editor-input').live('click',function(){
		 topicFocus($(this));
	 })
	//举报文本框获取焦点
	 $('.dialog-report [name=reportcontent]').live('click',function(){
		 topicFocus($(this));
	 });
    //详情简介展开
	$('.slidedown').live('click',function(){
		$(this).hide().next().show();
		$(this).parents('.brief').removeClass('book-height');
	})
	//书籍详情简介收起
	$('.slideup').live('click',function(){
		$(this).hide().prev().show();
		$(this).parents('.brief').addClass('book-height');
	})
})

//时间日期转换
function formatDate(str){
	var now=new Date(str);
	var year=now.getFullYear();
	var month=now.getMonth();
	var date=now.getDate();
    var hours=now.getHours();
    var minute=now.getMinutes();
    var second=now.getSeconds();
    // year+"年"+fixZero(month,2)+"月"+fixZero(date,2)+"日    "+fixZero(hour,2)+":"+fixZero(minute,2)+":"+fixZero(second,2)
    return  year+"-"+fixZero(month,2)+"-"+fixZero(date,2); 
}
function fixZero(num,length){     
	var str=""+num;      
	var len=str.length;     
	var s="";      
	for(var i=length;i-->len;){
		s+="0";     
	}      
	return s+str; 
}

//关掉举报弹出层
function closeReport(obj){
	obj.parents('.modal-wrapper').prev().remove();
	obj.parents('.modal-wrapper').remove();
	$('.edui-container').css('z-index','999');
}
//文本框获取焦点
function topicFocus(obj){
	if($.trim(obj.val())==''){
		obj.val('');
		obj.focus();
	 }
}

//书籍里面继续评论  点击取消
function answerreplyCancel(obj){
	var $closecomment=obj.parents('.comment-app-holder').prev().find('.js-toggleCommentBox');
	var $child=$closecomment.children();
	$closecomment.empty().append($child).append('评论');
	obj.parents('._CommentForm_root_1-ko').removeClass('_CommentForm_expanded_39uD');
	obj.parents('._CommentForm_root_1-ko').children(':first').empty().addClass('_InputBox_blur_3JWV')
	obj.parents('.comment-app-holder').remove();
}

//举报
function topicReport(obj){
	//被举报人
	var commentbyid=obj.attr("data-commentbyid");
	//被举报的话题
	var bookcommentid=obj.attr("data-topiccommentid");
	//举报类型  为了区分是书籍 还是话题举报  书籍举报是2  话题是6  课程是3  文章是1
	var reporttype=obj.attr("data-reporttype");
	var datamodel='';
	$.ajax({
		type:"POST",
    	url:projectName+"myCenter/getReportInfoConfigList",
    	dataType:"json",
    	async:false, 
        success:function(data){
        	if(data.returnStatus=='000'){//返回成功 
        		datamodel={
        			    commentbyid:commentbyid,
        				topiccommentid:bookcommentid,
        			    reporttype:reporttype,
        			    reportreason:data.baseResponseList,
        			    reportreasonlength:data.baseResponseList.length-1,
        		}
        	}
        	else{
        		
        	}
        }
	})
	//加载模板
	$('.pagetemplate').setTemplateURL(projectName+'report.html');
	$('.pagetemplate').processTemplate(datamodel);
	$('body').append($('.pagetemplate').html());
	$('.pagetemplate').empty();
	$('.dialog-report [name=reportcontent]').focus();
	$('.edui-container').css('z-index','0');
}

//获取用户信息
function getCurrentUser(){
	$.ajax({
		type:"POST",
		url:projectName+"/myCenter/getCurrentUser",
		dataType:"json",
		success:function(data){
			if(data.returnStatus=='000'){
				userInfo=data.user;
			}
			else{
				
			}
		}
	
	})
}


//关注人或话题  function topicFollow(obj,objecttype,type)
function topicFollow(obj,objecttype,type,createperson,titleid,titlename)//objecttype 0:用户  1：话题    actionType 1关注，0取消关注    type代表个人主页话题关注列表
{  
	var objectId,objectNamePg;
	if(objecttype==0){
		objectId=obj.attr('data-userid');	
	}
	else{
		objectId=obj.attr('data-id');
	}
	
	var actiontype=obj.attr('data-actionType');
	$.ajax({
		type:"POST",
		url:projectName+"myCenter/addFollows",
		data:{objectType:objecttype,objectid:objectId,actionType:actiontype,objectNamePg:titlename,objCreatepersonPg:createperson,relationidPg:titleid},
		dataType:"json",
        success:function(data){
        	if(data.returnStatus=='000'){
        	  if(objecttype==1&&type==undefined){
	        		 if(actiontype==1){//取消关注
	          			obj.removeClass('zg-btn-green').addClass('zg-btn-white');
	          			obj.empty().html('取消关注');
	          			obj.attr('data-actiontype','0');
	          			obj.next().find('strong').html(Number(obj.next().find('strong').html())+1);
	          		}
	          		else{
	          			obj.removeClass('zg-btn-white').addClass('zg-btn-green');
	          			obj.attr('data-actiontype','1');
	          			obj.empty().html('关注话题');
	          			obj.next().find('strong').html(Number(obj.next().find('strong').html())-1);
	          		} 
        	  }
        	  if(objecttype==0&&type==undefined){
        		  if(actiontype==1){//取消关注
            			obj.removeClass('zg-btn-follow').addClass('zg-btn-unfollow');
            			obj.empty().html('取消关注');
            			obj.attr('data-actiontype','0');
            		}
            		else{
            			obj.removeClass('zg-btn-unfollow').addClass('zg-btn-follow');
            			obj.empty().html('关注他');
            			obj.attr('data-actiontype','1');
            		} 
        	  }
        	}
        	else{
        		
        	}
        }
	})
}

/*window._bd_share_config = {
	    "common": {
	      "bdSnsKey": {},
	      "bdText": "",
	      "bdMini": "2",
	      "bdPic": "",
	      "bdStyle": "0",
	      "bdSize": "16"
	    },
	    "share": {},
	    "image": {
	      "viewList": ["qzone", "tsina","weixin"],
	      "viewText": "分享到：",
	      "viewSize": "16"
	    },
	    "selectShare": {
	      "bdContainerClass": null,
	      "bdSelectMiniList": ["qzone", "tsina", "weixin"]
	    }
	  };
with(document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~ ( - new Date() / 36e5)];
*/