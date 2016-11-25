$(function(){
	//书籍详情简介 
	if($('input[name=bookcontent]').length>0){
		 var cont=$('input[name=bookcontent]').val();
		 if(cont.replace(/[^x00-xFF]/g,'**').length>150){
			 $('.brief-content').html(autoAddEllipsis(cont,150)).after("<span class='showall' style='font-size:2.6rem'>展示全部</span>");
		 }
	}
	//详情简介展开
	$('.showall').live('click',function(){
		   $(this).hide().next().show();
		   $('.brief-content').html($('input[name=bookcontent]').val());
		   $('#bookbrief').removeClass('tool-height');
	})
	//详情简介收起
	$('.slideup').live('click',function(){
		  $(this).hide().prev().show();
		  $('.brief-content').html(autoAddEllipsis($('input[name=bookcontent]').val(),150));
		  $('#bookbrief').addClass('tool-height');
	})
	//书籍详情  删除推荐语
	$('.js-cancel').live('click',function(){
		cancelCommtent($(this));
	});
	//发布
	$('.submit-button').live('click',function(){
		insertComment($(this),'1');
	});
	//书籍详情 点击评论
	$('._CommentForm_actions_ooEq [name=answeraddnew]').live('click',function(){
		insertComment($(this),'0');
	});
})
//删除推荐语
function cancelCommtent(obj){
	var recommend=obj.attr('data-recommend');
	var commentby=obj.attr('data-commendby');
	var bookid=$('input[name=bookid]').val();
	$.ajax({
	    type:"POST",
     	url:"/books/delComment",
     	data:{id:recommend,commentby:commentby,bookid:bookid},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			//同时从界面上移除一条推荐语
     			obj.parents('.item').remove();
     			$('#book-commcount').html('用户推荐('+(Number($('#book-commcount').attr('data-num'))-1)+')');
		        $('#book-commcount').attr('data-num',Number($('#book-commcount').attr('data-num'))-1);
     		}
     		else{
     			
     		}
     	}
	})
}
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
    				item.userCommentTime=item.userShareTime
    			})
    			var datamodel={
    				 courseslist:data.obj.list,
    			}
    		   //判断是否点赞
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
         		   $('.pagetemplate').setTemplateURL(projectName+'bookAppendTemplate.html');
             	   $('.pagetemplate').processTemplate(datamodel);
             	   if($('.loadmore').length>0){
             		   $('.loadmore').prev().append($('.pagetemplate').html());
             	   }else{
             		   $('.uncomment').remove();
                 	   $('.detail-list').append($('.pagetemplate').html());
             	   }
             	   $(".pagetemplate").empty();
             	   $this.parent().find('.errortip').remove();
             	   //推荐语文本框清空
             	   $('.commentcontent').val('');
             	   $('#book-commcount').html('用户推荐('+(Number($('#book-commcount').attr('data-num'))+1)+')');
		           $('#book-commcount').attr('data-num',Number($('#book-commcount').attr('data-num'))+1);
         		}else{
         			
         		}
         	}
	 })
}