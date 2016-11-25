$(function(){
	 //站点详情简介
	if($('input[name=sitescontent]').length>0){
		var content=$('input[name=sitescontent]').val();
		if(content.replace(/[^x00-xFF]/g,'**').length>150){
			 $('.brief-content').html(autoAddEllipsis(content,150)).after("<span class='showall' style='font-size:2.6rem'>展示全部</span>");
		 }
   }
    //详情简介展开
	$('.showall').live('click',function(){
		   $(this).hide().next().show();
		   $('.brief-content').html($('input[name=sitescontent]').val());
		   $('#sitesbrief').removeClass('tool-height');
	})
	//详情简介收起
	$('.slideup').live('click',function(){
		  $(this).hide().prev().show();
		  $('.brief-content').html(autoAddEllipsis($('input[name=sitescontent]').val(),150));
		  $('#sitesbrief').addClass('tool-height');
	})
	//发布
	$('a[name=savebook]').live('click',function(){
		insertComment($(this),'1');
	});
	//站点详情 点击评论
	$('._CommentForm_actions_ooEq [name=answeraddnew]').live('click',function(){
		insertComment($(this),'0');
	});
	//站点详情  删除推荐语
	$('.js-cancel').live('click',function(){
		cancelCommtent($(this));
	});
})
//站点加载更多
function siteLoadMore(obj){
    var pageNo=obj.attr('data-pageno');
    var sumpage=obj.data('sumpage');
    var siteid=$('input[name=siteid]').val();
    $.ajax({
    	type:"POST",
      	url:"/sites/loadComments",
      	data:{pageNo:Number(pageNo)+1,siteid:siteid},
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
//删除推荐语
function cancelCommtent(obj){
	var recommend=obj.attr('data-recommend');
	var siteid=$('input[name=siteid]').val();
	$.ajax({
	    type:"POST",
     	url:"/sites/delComment",
     	data:{id:recommend,siteid:siteid},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			//同时从界面上移除一条推荐语
     			obj.parents('.item').remove();
     			$('#site-commcount').html('用户推荐('+(Number($('#site-commcount').attr('data-num'))-1)+')');
 	            $('#site-commcount').attr('data-num',Number($('#site-commcount').attr('data-num'))-1);
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
	 var siteid=$('input[name=siteid]').val();
	 //被评论人
	 var commentby=obj.attr('data-recommend');
	 //被推荐人的名字
	 var recommendname=obj.attr('data-recommendname');
	 var objectName=$('input[name=sitesname]').val();
	 //评论内容
	 if(type==0){
		   commendcontent=obj.parent().prev().val();
		   objCreatepersonPg=commentby;
		   relationid=obj.attr('data-relationid');
	 }
	 else{
	      commendcontent=$('.commentcontent').val();
	      objCreatepersonPg=$('input[name=siteCreatePerson]').val();
	      relationid=siteid;
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
       	url:"/sites/addComment",
       	data:{siteid:siteid,recommend:commendcontent,commentby:commentby,objCreatepersonPg:objCreatepersonPg,relationidPg:relationid,objectNamePg:objectName},
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
           	   $('#site-commcount').html('用户推荐('+(Number($('#site-commcount').attr('data-num'))+1)+')');
	           $('#site-commcount').attr('data-num',Number($('#site-commcount').attr('data-num'))+1);
       		}else{
       			
       		}
       	}
	 })
}