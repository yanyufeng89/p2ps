$(function(){
	   //课程详情简介
	   if($('input[name=coursecontent]').length>0){
		   var cont=$('input[name=coursecontent]').val();
		   if(cont.replace(/[^x00-xFF]/g,'**').length>80){
				$('.brief-content').html(autoAddEllipsis(cont,75)).after("<span class='showall' style='font-size:2.2rem'>展示全部</span>");
			}
	   }
	   //简介展开全部
	   $('.showall').live('click',function(){
		   $(this).hide().next().show();
		   $('.brief-content').html($('input[name=coursecontent]').val());
		   $('#coursebrief').removeClass('course-height');
	   });
	   //简介收起
	   $('.slideup').live('click',function(){
		   $(this).hide().prev().show();
		   $('.brief-content').html(autoAddEllipsis($('input[name=coursecontent]').val(),75));
		   $('#coursebrief').addClass('course-height');
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
})
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
    		   $('.pagetemplate').setTemplateURL('/coursesLoadMoreWapTemplate.html');
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
};
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
	 if($.trim(commendcontent).replace(/[^x00-xFF]/g,'**').length>65535){
			obj.prevAll().show();
			return false;
     }
	
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
       		   $('.pagetemplate').setTemplateURL('/bookAppendTemplate.html');
           	   $('.pagetemplate').processTemplate(datamodel);
               if($('.loadmore').length>0)
            	   $('.loadmore').prev().append($('.pagetemplate').html());
               else
            	   $('.detail-list').append($('.pagetemplate').html());
           	   $(".pagetemplate").empty();
               $this.parent().find('.errortip').remove();
           	   $('.commentcontent').val('');
               $('#course-commcount').html('用户推荐('+(Number($('#course-commcount').attr('data-num'))+1)+')');
	           $('#course-commcount').attr('data-num',Number($('#course-commcount').attr('data-num'))+1);
       		}else{
       			
       		}
       	}
	 })
}