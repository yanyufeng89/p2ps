$(function(){
	//头像信息
	intoUserInfo();

	//单个删除  文章已分享
    $('.operate .cancelshare').live('click',function(){
	    	var articleid=$(this).data('courseid');
	    	var name=$(this).data('name');
	    	$this=$(this);
			$.confirm({
				'title'		: '确认删除已分享的文章',
				'message'	: "确认要删除已分享的文章&nbsp;\""+name+"\"&nbsp;吗?",
				'buttons'	: {
					'确认'	: {
						'class'	: 'blue',
						'action': function(){
							delSharedArticle(articleid,$this);				
						}
					},
					'取消'	: {
						'class'	: 'gray',
						'action': function(){}
					}
				}
			});
	});
    //单个删除  文章收藏
	$('.operate span').live('click',function(){
		    var articleid=$(this).data('courseid');
	    	var name=$(this).data('name');
	    	$this=$(this);
			$.confirm({
				'title'		: '确认删除收藏的文章',
				'message'	: "确认要删除收藏的文章&nbsp;\""+name+"\"&nbsp;吗?",
				'buttons'	: {
					'确认'	: {
						'class'	: 'blue',
						'action': function(){
							deleteMyCollects(articleid,$this);				
						}
					},
					'取消'	: {
						'class'	: 'gray',
						'action': function(){}
					}
				}
			});
	  });
    //我的文章批量删除(已分享)   批量删除 文章已收藏
    $('.remove').live('click',function(){
    	//0代表已分享  1代表收藏 
    	var $this=$(this);
    	var type=$this.attr('data-type');
    	if($this.hasClass('enabled')){
    		var conditions="";
    		$this.parent().nextAll('.docs-list').find('.select-icon').each(function(){
    			//从界面上移除当前
    			conditions+=$(this).data('courseid')+',';
    			
    		});
    		conditions=conditions.substring(0,conditions.length-1);
			$.confirm({
				'title': '确认删除',
				'message': "确认要删除这些文章吗?",
				'buttons': {
					'确认': {
						'class': 'blue',
						'action': function () {
							if(type==0)
								delSharedArticle(conditions,$this);
							else
								deleteMyCollects(conditions,$this);
						}
					},
					'取消': {
						'class': 'gray',
						'action': function () {
						}
					}
				}
			});
    	}
    	$(this).removeClass('enabled');
    });
    
    //文章详情 点击评论
	$('._CommentForm_actions_ooEq [name=answeraddnew]').live('click',function(){
		insertComment($(this),'0');
	});
	
    /*当界面下拉到一定位置出现向上的箭头 end*/
   //文章详情  删除推荐语
	$('.js-cancel').live('click',function(){
		cancelCommtent($(this));
	});
    //关注人
    $('.zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })
    //发布
	$('.submit-button').live('click',function(){
		insertComment($(this),'1');
	});
	 //文章详情 举报
	$('.js-report,#articlereport').live('click',function(){
		topicReport($(this));
	});
    
    //文章详情 点赞
    $('#articlelike').live('click',function(){
    	articleLike($(this));
    })

    //收藏文章
    $('#articlefollowanswer').live('click',function(){
    	collectArticle($(this));
    });
  //文章详情页加载更多
    $('.loadmore').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	articleLoadMore($(this))
    });
    //打赏
    $('#support-author').live('click',function(){
    	if ($("#currentUserId").val() == '') {
            toLogin();
 		}else{
 			var sumValue=$(this).attr('data-sumvalue');
 	    	//自己不能为自己打赏
 	    	if($(this).attr('data-userid')==$('input[name=articleCreatePerson]').val()){
 	    		ZENG.msgbox.show('您不能为自己打赏!', 5, 3000);
 	    		return false;
 	    	}
 	    	supportAuthor(sumValue);
 		}

    })
    //打赏人数过多时省略
    $('.fa-ellipsis-s').live('click',function(){
    	$(this).parents('.avatar-list').hide();
    	$(this).parents('.avatar-list').next().show();
    })
    //立即支付
    $('#payment').live('click',function(){
    	payMent($(this));
    })
    $('#reward_amount_in_yuan').live('focus',function(){
    	$('.zh-pm-warnmsg').hide();
    })
})
//立即支付
function payMent(obj){
	var userid=obj.attr('data-userid');
	
	var articleid=obj.attr('data-articleid');
	var sumvalue=obj.attr('data-sumValue');
	var re = /^-?\\d+$/;
	var supportValue=$('#reward_amount_in_yuan').val();
	var smsContent=$('#reward_message').val();
	var title=$('input[name=articlename]').val();
	var len=supportValue.length;
	for(var i=0;i<len;i++){
		c=supportValue.charAt(i).charCodeAt(0);
		if(c<48 || c>57){
			$('.zh-pm-warnmsg').html('打赏不能小于1个财富值!').show();
			return false;
		}
	}
	if($.trim(supportValue).length==0){/*||!re.test(parseInt(supportValue))*/  /*||!isNaN(supportValue)*/
		$('.zh-pm-warnmsg').html('打赏不能小于1个财富值!').show();
		return false;
	}else if(parseInt(sumvalue)<parseInt(supportValue)){
		$('.zh-pm-warnmsg').html('财富值不足!').show();
		return false;
	}else{
		$.ajax({
			type:"POST",
	      	url:"/article/reward",
	    	data:{id:articleid,userid:userid,supportValue:parseInt(supportValue),smsContent:smsContent,title:title},
	    	dataType:"json",
	    	success:function(data){
	    		if(data.returnStatus=='000'){//返回成功
	    			ZENG.msgbox.show('打赏成功,感谢您的打赏!', 4, 3000);
	    			closeReport(obj);
	    		}else{
	    			
	    		}
	    	}
		})
	}
	
}
//打赏
function supportAuthor(sumValue){
	var datamodel={
	  userid:$('input[name=articleCreatePerson]').val(),
	  articleid:$('input[name=articleid]').val(),
	  sumValue:sumValue
	}
	//加载模板
	$('.pagetemplate').setTemplateURL(projectName+'supportAuthor.html');
	$('.pagetemplate').processTemplate(datamodel);
	$('body').append($('.pagetemplate').html());
	$('.pagetemplate').empty();
	/*$('.dialog-report [name=reportcontent]').focus();*/
}
//课程加载更多
function articleLoadMore(obj){
    var pageNo=obj.attr('data-pageno');
    var sumpage=obj.data('sumpage');
    var articleid=$('input[name=articleid]').val();
    $.ajax({
    	type:"POST",
      	url:"/article/loadComments",
      	data:{pageNo:Number(pageNo)+1,articleid:articleid},
    	dataType:"json",
    	success:function(data){
    		if(data.returnStatus=='000'){//返回成功
    			var datamodel={
    			   courseslist:data.obj.list,
    			}
    		   $('.headiconintotem').setTemplateURL(projectName+'coursesLoadMoreTemplate.html');
          	   $('.headiconintotem').processTemplate(datamodel);
          	   $('.loadmore').prev().append($('.headiconintotem').html());
          	   $(".headiconintotem").empty();
          	   $('.loadmore').attr('data-pageno',Number(pageNo)+1);
          	   obj.removeClass('loading').empty().append('更多');
          	   if(Number(sumpage)==Number(pageNo)+1){
          		 $('.loadmore').hide(); 
          	   }
          	   intoUserInfo();
    		}else{
    			
    		}
    	}
    })
};
//收藏文章
function collectArticle(obj){
	var actiontype=obj.attr('data-actiontype');
	var articleid=$('input[name=articleid]').val();
	var $this=obj;
	$.ajax({
		type:"POST",
      	url:"/article/collectArticle",
      	data:{actionType:actiontype,objectid:articleid,judgeTodo:actiontype},
      	dataType:"json",
      	success:function(data){
      		if(data.returnStatus=='000'){//返回成功
      			 if(actiontype==0){//取消关注
      				$this.removeClass('zg-btn-green').addClass('zg-btn-white');
      				$this.empty().html('取消收藏');
      				$this.attr('data-actiontype','1');
      				$this.next().find('strong').html(Number($this.next().find('strong').html())+1);
         		}
         		else{
         			$this.removeClass('zg-btn-white').addClass('zg-btn-green');
         			$this.attr('data-actiontype','0');
         			$this.empty().html('收藏文章');
         			$this.next().find('strong').html(Number($this.next().find('strong').html())-1);
         		} 
      		}
      		else{
      			
      		}
      	}
	})
}
//文章点赞
function articleLike(obj){
	var islike=obj.attr('data-islike');
	var likecount=obj.attr('data-likecount');
	var articleid=$('input[name=articleid]').val();
	var objCreatepersonPg=$('input[name=articleCreatePerson]').val();
	var objectNamePg=$('input[name=articlename]').val();
	$.ajax({
		type:"POST",
      	url:"/article/clickLikeOnArticle",
      	data:{likeOperate:islike,id:articleid,relationidPg:articleid,objectNamePg:objectNamePg,objCreatepersonPg:objCreatepersonPg},
      	dataType:"json",
      	success:function(data){
      		if(data.returnStatus=='000'){//返回成功
      			var $children=obj.children();
        		if(islike==0){
        			obj.attr('data-islike','1');
        			obj.attr('data-likecount',Number(likecount)+1);
        			obj.empty().append($children).append('取消赞('+obj.attr('data-likecount')+")");
        			
        		}
        		else{
        			obj.attr('data-islike','0');
        			obj.attr('data-likecount',Number(likecount)-1);
        			obj.empty().append($children).append(obj.attr('data-likecount')+'人赞');
        		}
      		}else{
      			
      		}
      	}
	})
}
//加载用户信息
function intoUserInfo(){
	$('.uname,.zm-item-link-avatar,.zm-list-avatar,.author-link').pinwheel();
}
//删除推荐语
function cancelCommtent(obj){
	var recommend=obj.attr('data-recommend');
	var articleid=$('input[name=articleid]').val();
	$.ajax({
	    type:"POST",
     	url:"/article/delComment",
     	data:{id:recommend,articleid:articleid},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			//同时从界面上移除一条推荐语
     			obj.parents('.item').remove();
     			$('#article-commcount').html('用户推荐('+(Number($('#article-commcount').attr('data-num'))-1)+')');
  	            $('#article-commcount').attr('data-num',Number($('#article-commcount').attr('data-num'))-1);
     		}
     		else{
     			
     		}
     	}
	})
}
//删除分享的文章
function delSharedArticle(conditions,obj){
	   $.ajax({
	    	type:"POST",
	    	url:"/article/delSharedArticle",
	    	data:{condition:conditions},
	    	dataType:"json",
	    	success:function(data){
	    		if(data.returnStatus=='000'){//返回成功
		    	    $('.remove').removeClass('enabled');
		      		if(conditions.toString().indexOf(',')!=-1){
		     			obj.parent().nextAll('.docs-list').find('.select-icon').each(function(){
		         			 //从界面上移除当前
		         			 $(this).parents('li').remove();
		            		});
		       		}else{
			       		    //判断是否是批量删除 但只删除了一条
	         				if(obj.hasClass('remove')){
	         					obj.parent().nextAll('.docs-list').find('.select-icon').parents('li').remove()
	         				}else{
	         					obj.parents('li').remove();
	         				}
		        		}
		        		$('#articleshare').html("分享("+data.operationSum.articlesharesum+")");
		   		}else{//返回失败
		   		}

	   	}
	});
}

//删除已收藏文章
function deleteMyCollects(conditions,obj){
   $.ajax({
   	type:"POST",
   	url:"/myCenter/deleteMyCollects",
   	data:{condition:conditions,collecttype:"tbl_article"},
   	dataType:"json",
   	success:function(data){
   		if(data.returnStatus=='000'){//返回成功
      		console.log(data);
      		$('.remove ').removeClass('enabled');
    		if(conditions.toString().indexOf(',')!=-1){
    			obj.parent().nextAll('.docs-list').find('.select-icon').each(function(){
        			//从界面上移除当前
        			$(this).parents('li').remove();
        		});
    		}else{
    			//判断是否是批量删除 但只删除了一条
 				if(obj.hasClass('remove')){
 					obj.parent().nextAll('.docs-list').find('.select-icon').parents('li').remove()
 				}else{
 					obj.parents('li').remove();
 				}
    		}
    		$('#articlecollect').html("收藏("+data.operationSum.articlecollsum+")");

  		}else{//返回失败
  		}

  	}
  });
}

//新增推荐语
function insertComment(obj,type){
	 //type  0 推荐语的评论    1是新发布的评论	  
	 var commendcontent='';
	 var objCreatepersonPg='';
	 var relationid='';
	 var articleid=$('input[name=articleid]').val();
	 //被评论人
	 var commentby=obj.attr('data-recommend');
	 //被推荐人的名字
	 var recommendname=obj.attr('data-recommendname');
	 var objectName=$('input[name=articlename]').val();
	 //评论内容
	 if(type==0){
	   commendcontent=obj.parent().prev().val();
	   objCreatepersonPg=commentby;
	   relationid=obj.attr('data-relationid');
	 }
	 else{
       commendcontent=$('.commentcontent').val();
       objCreatepersonPg=$('input[name=articleCreatePerson]').val();
       relationid=articleid;
	 }
	 if($.trim(commendcontent).length==0){
		return false; 
	 }
	 var len=commendcontent.length+(commendcontent.match(/[^\x00-\xff]/g) ||"").length;
	 if(len>1000){
	 		if(obj.parent().find('span').length==0)
	 			obj.before('<span class="errortip">请控制在 1000 字以下</span>&nbsp;&nbsp;');
	     		return false;
	 }
	 if(commendcontent.length==0)return false;
	 var $this=obj;
	 $.ajax({
		type:"POST",
       	url:"/article/addComment",
       	data:{articleid:articleid,recommend:commendcontent,commentby:commentby,objCreatepersonPg:objCreatepersonPg,relationidPg:relationid,objectNamePg:objectName},
       	dataType:"json",
       	success:function(data){
       		if(data.returnStatus=='000'){//返回成功
       			answerreplyCancel($this);
       			//界面上同时追加一条推荐语
       			var datamodel={
       				user:userInfo,	
       			    id:data.obj.id,
       			    userShareTime:data.obj.userCommentTime,
       			    recommendname:recommendname,
       			    commentby:commentby,
       			    commendcontent:commendcontent
       			}
       		   $('.headiconintotem').setTemplateURL(projectName+'bookAppendTemplate.html');
           	   $('.headiconintotem').processTemplate(datamodel);
               if($('.loadmore').length>0)
            	   $('.loadmore').prev().append($('.headiconintotem').html());
               else
            	   $('.detail-list').append($('.headiconintotem').html());
           	   $(".headiconintotem").empty();
           	   $this.parent().find('.errortip').remove();
           	   intoUserInfo();
           	   $('.commentcontent').val('');
           	   $('#article-commcount').html('用户推荐('+(Number($('#article-commcount').attr('data-num'))+1)+')');
	           $('#article-commcount').attr('data-num',Number($('#article-commcount').attr('data-num'))+1);
       		}else{
       			
       		}
       	}
	 })
}