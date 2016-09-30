var userInfo,edit;
$(function(){
	//头像信息
	intoUserInfo();
	
    //我的书籍批量删除(已分享)   批量删除 书籍已收藏
    $('.remove').live('click',function(){
    	//0代表已分享  1代表收藏 
    	var $this=$(this);
    	var type=$this.attr('data-type');
    	if($this.hasClass('enabled')){
    		var conditions="";
    		$this.parent().nextAll('.docs-list').find('.select-icon').each(function(){
    			//从界面上移除当前
    			conditions+=$(this).data('commentid')+":"+$(this).data('bookid')+',';
    			
    		});
    		conditions=conditions.substring(0,conditions.length-1);
			$.confirm({
				'title': '确认删除',
				'message': "确认要删除这些书籍吗?",
				'buttons': {
					'确认': {
						'class': 'blue',
						'action': function () {
							if(type==0)
								delComment(conditions,$this);
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
	
	//单个删除 书籍已分享
     $('.operate .cancelshare').live('click',function(){
	    	var commentid=$(this).data('commentid');
	    	var bookid=$(this).data('bookid');
	    	var name=$(this).data('name');
	    	$this=$(this);
			$.confirm({
				'title'		: '确认删除已分享的书籍评论',
				'message'	: "确认要删除已分享的书籍评论\""+name+"\"吗?",
				'buttons'	: {
					'确认'	: {
						'class'	: 'blue',
						'action': function(){
							delComment(commentid+":"+bookid,$this);				
						}
					},
					'取消'	: {
						'class'	: 'gray',
						'action': function(){}
					}
				}
			});
	    });
	
	 //单个删除  收藏
	 $('.operate span').live('click',function(){
	     	var commentid=$(this).data('commentid');
	    	var bookid=$(this).data('bookid');
	    	var name=$(this).data('name');
	    	$this=$(this);
			$.confirm({
				'title'		: '确认删除收藏的书籍',
				'message'	: "确认要删除收藏的书籍\""+name+"\"吗?",
				'buttons'	: {
					'确认'	: {
						'class'	: 'blue',
						'action': function(){
							deleteMyCollects(commentid+":"+bookid,$this);				
						}
					},
					'取消'	: {
						'class'	: 'gray',
						'action': function(){}
					}
				}
			});
	    });
	 
	//书籍详情点赞   为空说明还没点赞  0代表去点赞   1是取消点赞
	$('.js-thank').live('click',function(){
		var reasonid=$(this).attr('data-reasonid');
		var islike=$(this).attr('data-islike');
		var likecount=$(this).attr('data-likecount');
		var recommend=$(this).attr('data-recommend');
		var bookid=$('input[name=bookid]').val();
		var objectNamePg=$('input[name=bookname]').val();
		var $this=$(this);
		$.ajax({
			type:"POST",
         	url:"/books/clickOnLike",
         	data:{id:reasonid,likeOperate:islike,topObjId:bookid,objectNamePg:objectNamePg,relationidPg:reasonid,objCreatepersonPg:recommend},
         	dataType:"json",
         	success:function(data){
         		if(data.returnStatus=='000'){//返回成功
            		var $children=$this.children();
            		if(islike==0){
            			$this.attr('data-islike','1');
            			$this.attr('data-likecount',Number(likecount)+1);
            			$this.empty().append($children).append('取消赞('+$this.attr('data-likecount')+")");
            		}
            		else{
            			$this.attr('data-islike','0');
     					$this.attr('data-likecount',Number(likecount)-1);
     					$this.empty().append($children).append($this.attr('data-likecount')+'人赞');
            		}
            		
        		}else{//返回失败
        			
        		}
         	}
		})
	});
	

	
	//书籍详情 点击评论
	$('._CommentForm_actions_ooEq [name=answeraddnew]').live('click',function(){
		insertComment($(this),'0');
	});
	
	//书籍详情 举报
	$('.js-report').live('click',function(){
		topicReport($(this));
	});
	//书籍详情  删除推荐语
	$('.js-cancel').live('click',function(){
		cancelCommtent($(this));
	});
	
	//发布
	$('.submit-button').live('click',function(){
		insertComment($(this),'1');
	});
	
	//收藏书籍
    $('#bookfollowanswer').live('click',function(){
    	collectBook($(this));
    });
    
    //关注人
    $('.zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })
    
    //回到顶部
    $("#bookbacktop").mousemove(function(){
    	$("#bookbacktop").css("background-position-x", "-28px");
    }).mouseleave(function(){
    	$("#bookbacktop").css("background-position-x", "0");
    });
    /*当界面下拉到一定位置出现向上的箭头 start*/
    $(window).scroll(function(){  
        if ($(window).scrollTop()>100){  
            $("#bookbacktop").fadeIn("fast");  
        }  
        else  
        {  
            $("#bookbacktop").fadeOut("fast");  
        }  
    });
   /*当界面下拉到一定位置出现向上的箭头 end*/
    
    //书籍详情页加载更多
    $('.loadmore').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	bookLoadMore($(this))
    });
})
//书籍详情页加载更多
function bookLoadMore(obj){
    var pageNo=obj.attr('data-pageno');
    var sumpage=obj.data('sumpage');
    var bookid=$('input[name=bookid]').val();
    $.ajax({
    	type:"POST",
      	url:"/books/loadComments",
      	data:{pageNo:Number(pageNo)+1,bookid:bookid},
    	dataType:"json",
    	success:function(data){
    		if(data.returnStatus=='000'){//返回成功
    			 $.each(data.obj.list,function(index,item){
 	    			if(item.likedIds!=undefined){
 	    				if(item.likedIds.indexOf(',')!=-1){
 	    					if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.likedIds.split(','))!=-1){
 	    						item.likedIds=1;
 	    					}else{
 	    						item.likedIds=0;
 	    					}
 	    				}else{
 	    					if(item.likedIds==(userInfo==undefined?'':userInfo.userid)){
 	    						item.likedIds=1;
 	    					}else{
 	    						item.likedIds=0;
 	    					}
 	    					
 	    				}
     	    		}else{
     	    			item.likedIds=0;
     	    		}
 	    	    })
    			var datamodel={
    			   booklist:data.obj.list,
    			}
    		   //判断是否点赞
    		   $('.headiconintotem').setTemplateURL(projectName+'bookLoadMoreTemplate.html');
          	   $('.headiconintotem').processTemplate(datamodel);
          	   $('.loadmore').before($('.headiconintotem').html());
          	   $(".headiconintotem").empty();
          	   $('.loadmore').attr('data-pageno',Number(pageNo)+1);
          	   obj.removeClass('loading').empty().append('更多');
          	   if(Number(sumpage)==Number(pageNo)+1)
          		 $('.loadmore').hide();
          	   intoUserInfo();
    		}else{
    			
    		}
    	}
    })
};
//收藏书籍
function collectBook(obj){
	var actiontype=obj.attr('data-actiontype');
	var bookid=$('input[name=bookid]').val();
	var $this=obj;
	$.ajax({
		type:"POST",
      	url:"/books/collectBook",
      	data:{actionType:actiontype,objectid:bookid,judgeTodo:actiontype},
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
         			$this.empty().html('收藏书籍');
         			$this.next().find('strong').html(Number($this.next().find('strong').html())-1);
         		} 
      		}
      		else{
      			
      		}
      	}
	})
}
//删除推荐语
function cancelCommtent(obj){
	var recommend=obj.attr('data-recommend');
	var commentby=obj.attr('data-commendby');
	var bookid=$('input[name=bookid]').val();
	var tableName=obj.attr('data-tableName');
	$.ajax({
	    type:"POST",
	    url:"/manage/backstage/delComment",
     	data:{id:recommend,tableName:tableName},//commentby:commentby,bookid:bookid
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			//同时从界面上移除一条推荐语
     			obj.parents('.item').remove();
     		}
     		else{
     			
     		}
     	}
	})
}
//加载用户信息
function intoUserInfo(){
    $('.uname,.zm-item-link-avatar,.zm-list-avatar,.author-link').pinwheel();
}

//删除已收藏书籍
function deleteMyCollects(conditions,obj){
   $.ajax({
   	type:"POST",
   	url:"/myCenter/deleteMyCollects",
   	data:{condition:conditions,collecttype:"tbl_books"},
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
    		$('#bookcollect').html("收藏("+data.operationSum.booksharesum+")");

  		}else{//返回失败
  		}

  	}
  });
}
//删除已分享的书籍
function delComment(conditions,obj){
	   $.ajax({
    	type:"POST",
    	url:"/books/delComment",
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
	        		$('#bookshare').html("分享("+data.operationSum.booksharesum+")");
	   		}else{//返回失败
	   		}

   	}
});
}

//新增推荐语
function insertComment(obj,type){
	 //type  0 推荐语的评论    1是新发布的评论
	 var commendcontent='';
	 var relationid="";
	 var commentby='';
	 var objCreatepersonPg='';
	 var bookname=$('input[name=bookname]').val();
	 var bookid=$('input[name=bookid]').val();
	 //被推荐人的名字
	 var recommendname=obj.attr('data-recommendname');
	 //评论内容
	 if(type==0){
	    commendcontent=obj.parent().prev().val();
	    relationid=obj.attr('data-relationid');
	    commentby=obj.attr('data-recommend');
	    objCreatepersonPg=obj.attr('data-recommend');
	 }else{
		 commendcontent=$('.commentcontent').val();
		 relationid=$('input[name=bookid]').val();
		 //被评论人
		 objCreatepersonPg=$('input[name=bookCreatePerson]').val();
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
         	url:"/books/insertComment",
         	data:{bookid:bookid,recommend:commendcontent,commentby:commentby,objectNamePg:bookname,objCreatepersonPg:objCreatepersonPg,relationidPg:relationid},
         	dataType:"json",
         	success:function(data){
         		if(data.returnStatus=='000'){//返回成功
         			answerreplyCancel($this);
         			//界面上同时追加一条推荐语
         			var datamodel={
         				user:userInfo,	
         			    id:data.obj.id,
         			    userShareTime:data.obj.userShareTime,
         			    recommendname:recommendname,
         			    commentby:commentby,
         			    commendcontent:commendcontent,
         			}
         		   $('.headiconintotem').setTemplateURL(projectName+'bookAppendTemplate.html');
             	   $('.headiconintotem').processTemplate(datamodel);
             	   if($('.loadmore').length>0){
             		   $('.loadmore').before($('.headiconintotem').html());
             	   }else{
                 	   $('.detail-list').append($('.headiconintotem').html());
             	   }
             	   $(".headiconintotem").empty();
/*             	   //界面动态添加一条数据
             	   $('.bookrenson-'+data.obj.id).append(commendcontent);*/
             	   intoUserInfo();
             	   $this.parent().find('.errortip').remove();
             	   //推荐语文本框清空
             	   $('.commentcontent').val('');
         		}  
         		else{
         			
         		}
         	}
	 })
}