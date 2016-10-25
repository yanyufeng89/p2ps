$(function(){
	//头像信息
	intoUserInfo();

	//单个删除 课程已分享
    $('.operate .cancelshare').live('click',function(){
	    	var courseid=$(this).data('courseid');
	    	var name=$(this).data('name');
	    	$this=$(this);
			$.confirm({
				'title'		: '确认删除已分享的课程',
				'message'	: "确认要删除已分享的课程&nbsp;\""+name+"\"&nbsp;吗?",
				'buttons'	: {
					'确认'	: {
						'class'	: 'blue',
						'action': function(){
							delSharedCourses(courseid,$this);				
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
	     	var courseid=$(this).data('courseid');
	    	var name=$(this).data('name');
	    	$this=$(this);
			$.confirm({
				'title'		: '确认删除收藏的课程',
				'message'	: "确认要删除收藏的课程&nbsp;\""+name+"\"&nbsp;吗?",
				'buttons'	: {
					'确认'	: {
						'class'	: 'blue',
						'action': function(){
							deleteMyCollects(courseid,$this);				
						}
					},
					'取消'	: {
						'class'	: 'gray',
						'action': function(){}
					}
				}
			});
	  });
    //我的课程批量删除(已分享)   批量删除 课程已收藏
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
				'message': "确认要删除这些课程吗?",
				'buttons': {
					'确认': {
						'class': 'blue',
						'action': function () {
							if (type == 0)
								delSharedCourses(conditions, $this);
							else
								deleteMyCollects(conditions, $this);
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
     //关注人
    $('.zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })
    //课程详情 举报
	$('.js-report').live('click',function(){
		topicReport($(this));
	});
    //课程详情 点击评论
	$('._CommentForm_actions_ooEq [name=answeraddnew]').live('click',function(){
		insertComment($(this),'0');
	});
	//发布
	$('.submit-button').live('click',function(){
		insertComment($(this),'1');
	});
	//课程详情  删除推荐语
	$('.js-cancel').live('click',function(){
		cancelCommtent($(this));
	});
	//收藏课程
    $('#coursefollowanswer').live('click',function(){
    	collectCourse($(this));
    });
	//课程详情 点赞
    $('#courselike').live('click',function(){
    	courseLike($(this));
    })
    
   //课程详情页加载更多
    $('.loadmore').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	courseLoadMore($(this))
    });
   /* $('#courseurl').live('click',function(){
    	var url=$(this).attr('data-url');
    	url=url.substr(0,7).toLowerCase()=="http://"?url:"http://"+url;
    	window.location.href=url;
    })*/
})
//课程加载更多
function courseLoadMore(obj){
    var pageNo=obj.attr('data-pageno');
    var sumpage=obj.data('sumpage');
    var coursesid=$('input[name=courseid]').val();
    $.ajax({
    	type:"POST",
      	url:"/courses/loadComments",
      	data:{pageNo:Number(pageNo)+1,coursesid:coursesid},
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
//课程点赞
function courseLike(obj){
	var islike=obj.attr('data-islike');
	var likecount=obj.attr('data-likecount');
	var coursesid=$('input[name=courseid]').val();
	var courseCreatePerson=$('input[name=courseCreatePerson]').val();
	var coursesname=$('input[name=coursename]').val();
	$.ajax({
		type:"POST",
      	url:"/courses/clickLikeOnCourse",
      	data:{likeOperate:islike,id:coursesid,objCreatepersonPg:courseCreatePerson,relationidPg:coursesid,objectNamePg:coursesname},
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
//收藏课程
function collectCourse(obj){
	var actiontype=obj.attr('data-actiontype');
	var coursesid=$('input[name=courseid]').val();
	var $this=obj;
	$.ajax({
		type:"POST",
      	url:"/courses/collectCourses",
      	data:{actionType:actiontype,objectid:coursesid,judgeTodo:actiontype},
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
         			$this.empty().html('收藏课程');
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
	var coursesid=$('input[name=courseid]').val();
	$.ajax({
	    type:"POST",
     	url:"/courses/delComment",
     	data:{id:recommend,coursesid:coursesid},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			//同时从界面上移除一条推荐语
     			obj.parents('.item').remove();
     			$('#course-commcount').html('用户推荐('+(Number($('#course-commcount').attr('data-num'))-1)+')');
  	            $('#course-commcount').attr('data-num',Number($('#course-commcount').attr('data-num'))-1);
     		}
     		else{
     			
     		}
     	}
	})
}
//删除分享的课程
function delSharedCourses(conditions,obj){
	   $.ajax({
	    	type:"POST",
	    	url:"/courses/delSharedCourses",
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
		        	$('#courseshare').html("分享("+data.operationSum.coursessharesum+")");
		   		}else{//返回失败
		   		}

	   	}
	});
}
//删除已收藏课程
function deleteMyCollects(conditions,obj){
   $.ajax({
   	type:"POST",
   	url:"/myCenter/deleteMyCollects",
   	data:{condition:conditions,collecttype:"tbl_courses"},
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
    		$('#coursecollect').html("收藏("+data.operationSum.coursescollsum+")");

  		}else{//返回失败
  		}

  	}
  });
}
//加载用户信息
function intoUserInfo(){
	$('.uname,.zm-item-link-avatar,.zm-list-avatar,.author-link').pinwheel();
}
//新增推荐语
function insertComment(obj,type){
	 //type  0 推荐语的评论    1是新发布的评论	  
	 var commendcontent='';
	 var objCreatepersonPg='';
	 var relationid='';
	 var coursesid=$('input[name=courseid]').val();
	 //被评论人
	 var commentby=obj.attr('data-recommend');
	 //被推荐人的名字
	 var recommendname=obj.attr('data-recommendname');
	 var objectName=$('input[name=coursename]').val();
	 //评论内容
	 if(type==0){
	   commendcontent=obj.parent().prev().val();
	   objCreatepersonPg=commentby;
	   relationid=obj.attr('data-relationid');
	 }
	 else{
	   objCreatepersonPg=$('input[name=courseCreatePerson]').val();
       commendcontent=$('.commentcontent').val();
       relationid=coursesid;
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
       	url:"/courses/addComment",
       	data:{coursesid:coursesid,recommend:commendcontent,commentby:commentby,objCreatepersonPg:objCreatepersonPg,relationidPg:relationid,objectNamePg:objectName},
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
               $('#course-commcount').html('用户推荐('+(Number($('#course-commcount').attr('data-num'))+1)+')');
	           $('#course-commcount').attr('data-num',Number($('#course-commcount').attr('data-num'))+1);
       		}else{
       			
       		}
       	}
	 })
}