userInfo;
$(function(){
	//发布
	$('a[name=savebook]').live('click',function(){
		insertComment($(this),'1');
	});
	//文章详情  删除推荐语
	$('.js-cancel').live('click',function(){
		cancelCommtent($(this));
	});
   //文章详情 点击评论
	$('._CommentForm_actions_ooEq [name=answeraddnew]').live('click',function(){
		insertComment($(this),'0');
	});
})
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
	 if($.trim(commendcontent).replace(/[^x00-xFF]/g,'**').length>65535){
			obj.prevAll().show();
			return false;
	 }
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
       		   $('.pagetemplate').setTemplateURL('/bookAppendTemplate.html');
           	   $('.pagetemplate').processTemplate(datamodel);
               if($('.loadmore').length>0)
            	   $('.loadmore').prev().append($('.pagetemplate').html());
               else
            	   $('.detail-list').append($('.pagetemplate').html());
           	   $(".pagetemplate").empty();
           	   $this.parent().find('.errortip').remove();
           	   $('.commentcontent').val('');
           	   $('#article-commcount').html('用户推荐&nbsp;('+(Number($('#article-commcount').attr('data-num'))+1)+')');
	           $('#article-commcount').attr('data-num',Number($('#article-commcount').attr('data-num'))+1);
       		}else{
       			
       		}
       	}
	 })
}
//文章加载更多
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