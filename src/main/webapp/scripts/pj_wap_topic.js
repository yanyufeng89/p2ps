$(function(){
	//发布回答
    $('.submit-button').live('click',function(){
    	insertAnswer($(this))
    })
   /* //针对话题标题的评论下载下拉列表
    $('#topiccomment').live('click',function(){
    	topicComment($(this));
    });*/
    //针对回答评论列表
    $('.topic-com').live('click',function(){
    	topicCom($(this));
    });
    //话题详情评论里面的每条评论点击回复(取消)
    $('.zm-comment-form .zm-comment-close').live('click',function(){
    	$(this).parents('.zm-comment-form').hide();
    });
    //针对回答的评论
    $('button[name=answeraddnew]').live('click',function(){
    	ansWerAddnew($(this));
    });
    //话题详情 --回答  对新增的评论进行删除
    $('._CommentItem_root_PQNS .CommentItem_cancel').live('click',function(){
    	cancelnewreply($(this),'answer');
    });
    //话题详情  新增的评论进行删除
    $('a[name=delcomment]').live('click',function(){
    	cancelnewreply($(this),'comment');
    });
    //话题详情--回答里面的评论(评论里面继续评论)--取消
    $('._CommentForm_cancelButton_cy3u').live('click',function(){
    	answerreplyCancel($(this));
    })
    //话题详情回答里面点击回复
    $('.CommentItem_reply').live('click',function(){
    	appendreply($(this),'answer');
    })
     //话题详细---回答里面针对每条评论进行回复
     $('.zm-comment-submit-answer').live('click',function(){
    	var content=$(this).parent().prev().find('.pj-replaycontent').val();
    	if($.trim(content).replace(/[^x00-xFF]/g,'**').length>65535){
    		$(this).prevAll().show();
    		return false;
    	}
     	var topicid=$('#content input[name=titleid]').val();
     	var commentby=$(this).attr('data-commentid');
     	var commentname=$(this).attr('data-name');
     	var parentcommid=$(this).attr('data-parentcommid');
     	var relationid=$(this).attr('data-relationid');
     	var objectName=$('input[name=titlename]').val();
     	if($.trim(content)==''){
     		return false;
     	}
     	$this=$(this);
     	$.ajax({
    		type:'POST',
    		url:"/topics/insertTopicsComment",
    		data:{topicsid:topicid,type:'2',commcontent:content,useid:userInfo==undefined?'':userInfo.userid,commentby:commentby,parentcommid:parentcommid,objectNamePg:objectName,relationidPg:relationid,objCreatepersonPg:commentby},
    	    dataType:"json",
    	    success:function(data){
    	    	if(data.returnStatus=='000'){//返回成功
    	    		var datamodel={
    	    		   topicsComment:data.topicsComment,
    	    		   userinfo:userInfo,
    	    		   commentby:commentby,
    	    		   commentname:commentname,
    	    		   parentcommid:parentcommid,
    	    		}
    	    		//关闭文本框
    	    		$this.parent().parent().css('display','none');
    	    		$this.parent().prev().children(':first').html('');
    	    		//界面添加一条数据(添加模板)
    	    		$('#appendanswerreplys').setTemplateURL(projectName+'appendAnswerTemplate.html');
       	    		$('#appendanswerreplys').processTemplate(datamodel);
       	    		$this.parents('._CommentBox_list_10_K').append($('#appendanswerreplys').html());
       	    		$('#appendanswerreplys').empty();
       	    	    //发布成功之后去掉提示信息
            	    $this.parent().find('.errortip').remove();
            	    $this.parent().prev().find('.pj-replaycontent').val('');
    	    	}
    	    	else{
    	    		
    	    	}
    	    }
    	})
     })
})
//追加回复
function appendreply(obj,type){
	if(obj.parent().next().next().length>0){
		obj.parent().next().next().show()
	}
	else{
		//针对回答里面的每条评论的回复获取评论的id
		if(type=='answer')
		var parentcommid=obj.attr('data-parentcommid');
		
    	var commentbyid=obj.attr('data-commentbyid');//被回复人的ID
    	var commentid=obj.attr('data-commentid');//当前评论的ID
    	var commentname=obj.attr('data-name');//当前评论人的名字
    	var objectname=$('input[name=titlename]').val();//当前回复的评论
    	var relationid=obj.attr('data-relationid');//当前回复的评论的id
    	var $reply=obj.parent().next();
    	var data={
				commentbyid:commentbyid,
				commentid:commentid,
				commentname:commentname,
				commenttype:type,
				parentcommid:parentcommid,
				objectname:objectname,
				relationid:relationid,
		}
		//加载模板
    	$reply.setTemplateURL(projectName+'replyTemplate.html');
    	$reply.processTemplate(data);
    	if(type=='comment')//话题评论详情里面的回复点击
    	    obj.parents('.zm-comment-content-wrap').append($reply.html());
    	else{
    		obj.parents('._CommentItem_body_3qwB').append($reply.html());
    	}
    	$reply.empty();
	}
}
//回答里面继续评论  点击取消
function answerreplyCancel(obj){
	obj.parents('._CommentForm_root_1-ko').removeClass('_CommentForm_expanded_39uD');
	obj.parents('._CommentForm_root_1-ko').children(':first').empty().addClass('_InputBox_blur_3JWV')
}

//回答里面继续评论  点击取消
function answerreplyCancel1(obj){
	obj.parents('._CommentForm_root_1-ko').removeClass('_CommentForm_expanded_39uD');
	obj.parents('._CommentForm_root_1-ko').children(':first').empty().addClass('_InputBox_blur_3JWV')
}

//新增的评论里的回复进行删除
function cancelnewreply(obj,type){
	var operatetype=0;//是话题评论删除  还是回答里面的评论删除
	if(type=='comment'){
		operatetype=3;
	}else{
		operatetype=2;
	}
	var replyid=obj.attr('data-reply');
	var topicsid=$('#content input[name=titleid]').val();
	var parentcommId=obj.attr('data-answer');
	$this=obj;
	$.ajax({
       	type:"POST",
       	url:"/topics/delCommentOnTopDetail",
       	data:{id:replyid,type:operatetype,topicsid:topicsid,parentcommid:parentcommId},
       	dataType:"json",
       	success:function(data){
       		if(data.returnStatus=='000'){
       			if(type=='comment')
       			$this.parents('.zm-item-comment').remove();
       			else
       			$this.parents('._CommentItem_root_PQNS').remove();	
       		}
       		else{
       			
       		}
       	}
	})
}

function ansWerAddnew(obj){
	var content=obj.parent().prev().val();
	if($.trim(content).replace(/[^x00-xFF]/g,'**').length>65535){
		$(this).prevAll().show();
		return false;
	}
	var topicid=$('input[name=topicid]').val();
	var parentcommid=obj.attr('data-parentcommid');
	var createperson=obj.attr('data-createperson');
	var objectName=$('input[name=titlename]').val();
	if($.trim(content)=='') return false;
	$this=obj;
	$.ajax({
		type:'POST',
		url:"/topics/insertTopicsComment",
		data:{topicsid:topicid,type:'2',commcontent:content,useid:userInfo==undefined?'':userInfo.userid,parentcommid:parentcommid,relationidPg:parentcommid,objCreatepersonPg:createperson,objectNamePg:objectName},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		var datamodel={
	    		   topicsComment:data.topicsComment,
	    		   userinfo:userInfo,
	    		   parentcommid:parentcommid,
	    		}
	    		answerreplyCancel1($this);
	    		//界面添加一条数据(添加模板)
	    		$('#appendanswerreplys').setTemplateURL(projectName+'appendAnswerTemplate.html');
   	    		$('#appendanswerreplys').processTemplate(datamodel);
   	    		$this.parents('._CommentForm_root_1-ko').prev().append($('#appendanswerreplys').html());
   	    		$('#appendanswerreplys').empty();
   	    		$this.parent().find('.errortip').remove();
   	    		$this.parent().prev().val('');
	    	}
	    	else{
	    		
	    	}
	    }
	})
}
function topicCom(obj){
	obj.toggle(function(){
    	var $child=obj.children();
    	obj.empty().append($child).append('收起评论');
    	var answerid=obj.attr('data-answer');
    	var createperson=obj.attr('data-createperson');
    	var objectName=$('input[name=titlename]').val();
    	$this=obj;
    	var $holder=$this.parent().find('.comment-app-holder');
    	if($holder.length>0){
    		$holder.show();
    	}
    	else{
        	$.ajax({
        		type:'POST',
        		url:"/topics/getPartTopicsComment",
        		data:{parentcommid:answerid,type:2,relationidPg:answerid,objectNamePg:objectName,objCreatepersonPg:createperson},
        	    dataType:"json",
        	    success:function(data){
       	    	if(data.returnStatus=='000'){//返回成功
       	    	 	var datamodel={
       	    				answerid:answerid,
       	    				topicscommentList:data.obj.list,
       	    				topiccount:data.obj.list.length,
       	    				userid:String(userInfo==undefined?'':userInfo.userid),
       	    				createperson:createperson,
       	    				objectName:objectName,
       	    				iswap:1
       	    		}
       	    		//加载模板
       	    	 	$('.pagetemplate').setTemplateURL(projectName+'topicAskCommentTemplate.html');
       	        	$('.pagetemplate').processTemplate(datamodel);
       	        	if($this.parents('.operate').length==0){
       	        		$this.parents('.zm-meta-panel').append($('.pagetemplate').html());
       	        	}else{
       	        		$this.parents('.operate').append($('.pagetemplate').html());
       	        	}
       	        	
       	        	$('.pagetemplate').empty();
       	    	}
       	    	else{
       	    		
       	    	}
       	    }
        	})
    	} 	
    },function(){
    	var $children=obj.children();
    	//获得评论条数
    	commcount=obj.parent().find('._CommentItem_root_PQNS').size();
    	obj.empty().append($children).append(commcount+'条评论');
    	obj.parent().find('.comment-app-holder').hide();
    })
    obj.trigger('click');
}
//发布回答
function insertAnswer(obj){
    	var topicsid=$('input[name=topicid]').val();
    	var objCreateperson=$('input[name=createperson]').val();
    	//关联的主体名称(话题名称)
    	var topicname=$('input[name=titlename]').val();
    	var commcontent=$('.commentcontent').val();
    	if($.trim(commcontent).length==0){
    		return false;
    	}
		if($.trim(commcontent).replace(/[^x00-xFF]/g,'**').length>65535){
			$(this).prevAll().show();
			return false;
		}
    	var $this=obj;
    	var isPublic=$('input[type=checkbox]').is(':checked')?0:1;
    	$.ajax({
    		type:"POST",
    		url:"/topics/insertTopicsComment",
    		data:{commcontent:commcontent,type:1,topicsid:topicsid,isPublic:isPublic,objCreatepersonPg:objCreateperson,relationidPg:topicsid,objectNamePg:topicname},
    	    dataType:"json",
            success:function(data){
            	if(data.returnStatus=='000'){//返回成功
            		data.topicsComment.showcreatetime=data.topicsComment.showcreatetime.substring(11);
            		//界面加载数据
            		var datamodel={
            			userid:userInfo==undefined?'':userInfo.userid,
            			username:userInfo==undefined?'':userInfo.username,
            			headicon:userInfo==undefined?'':userInfo.headicon,
            			topicsComment:data.topicsComment,
            			commcontent:commcontent,
            			ispublic:isPublic,
            			topicname:topicname,
            			iswap:1
            		}
            		$('.pagetemplate').setTemplateURL('/answerTemplate.html',null, {filter_data: false});
            		$('.pagetemplate').processTemplate(datamodel);
            	    $('.detail-list').append($('.pagetemplate').html());
            		$('.pagetemplate').empty();
            		$('.commentcontent').val('');
            		//发布成功之后去掉提示信息
            		$this.parent().find('.errortip').remove();
            		$('#topic-commcount').html(Number($('#topic-commcount').attr('data-num'))+1+'个回答');
            		$('#topic-commcount').attr('data-num',Number($('#topic-commcount').attr('data-num'))+1);
                }
            	else{
            		
            	}
            }
    	})
}
//话题详情加载更多
function topicLoadMore(obj){
	var pageNo=obj.attr('data-pageno');
    var sumpage=obj.data('sumpage');
    var topicid=$('input[name=topicid]').val();
    $.ajax({
    	type:"POST",
      	url:"/topics/getSortTopicsCommentsByTopicId",
      	data:{pageNo:Number(pageNo)+1,topicsid:topicid,sortType:'1'},
    	dataType:"json",
    	success:function(data){
    		if(data.returnStatus=='000'){//返回成功
    			var datamodel={
    				 courseslist:data.obj.list,
    		    }
    		   $('.pagetemplate').setTemplateURL('/topicLoadMoreWapTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.loadmore').prev().append($('.pagetemplate').html());
          	   $(".pagetemplate").empty();
          	   $('.loadmore').attr('data-pageno',Number(pageNo)+1);
          	   obj.removeClass('loading').empty().append('更多');
          	   if(Number(sumpage)==Number(pageNo)+1){
          		 $('.loadmore').hide();
          	   }
    		}else{
    			
    		}
    	}
    })
}