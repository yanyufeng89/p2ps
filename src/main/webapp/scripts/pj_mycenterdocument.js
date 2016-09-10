var userInfo;
$(function(){
	//加载用户信息
	intoUserInfo();
	//加载文档阅读插件
	$('a.media').media({width:800, height:1200});
	 //我的文档批量删除
    $('.remove').live('click',function(){
    	//1代表上传  0代表下载和收藏
        var type=$(this).data('type');
        //0代表下载 1代表收藏
        var actiontype=$(this).data('actiontype');
        var $this=$(this);
    	if($this.hasClass('enabled')){
    		var conditions="";
    		$this.parent().nextAll('.docs-list').find('.select-icon').each(function(){
    			//从界面上移除当前
    			conditions+=$(this).data('docid')+',';
    		}); 
    		conditions=conditions.substring(0,conditions.length-1);
			$.confirm({
				'title': '确认删除',
				'message': "确认要删除这些文档吗?",
				'buttons': {
					'确认': {
						'class': 'blue',
						'action': function () {
							if(type=="1")
								deleteDocs(conditions,$this);
							else{
								deleteMyCollects(conditions,$this,actiontype);
							}
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
    	$(this).addClass('enabled');
    })
    //我的文档--操作--删除(单个删除)
    $('.operate span').live('click',function(){
    	var elem = $(this).closest('.item');    	
    	var id=$(this).data('id');
    	var name=$(this).data('name');
    	//1代表上传  0代表下载和收藏
    	var type=$(this).data('type');
    	//0代表下载 1代表收藏
        var actiontype=$(this).data('actiontype');
    	$this=$(this);
		$.confirm({
			'title'		: '确认删除文档',
			'message'	: "确认要删除文档\""+name+"\"吗?",
			'buttons'	: {
				'确认'	: {
					'class'	: 'blue',
					'action': function(){
						if(type=='1')
						    deleteDocs(id.toString(),$this);
						else{
							deleteMyCollects(id.toString(),$this,actiontype);
						}
						
					}
				},
				'取消'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
    })
    //判断当前点击的文档格式是否转换 未转换的不能打开
    $('.doctitle').live('click',function(){
    	var isconverter=$(this).attr('data-isconverter');
    	if(isconverter!=1){//未转换或者未转换成功
    		/*if($('p.error').length==0)*/
    		$(this).after('<p class="error" style="display:block"><i class="tips_icon"></i>当前文档转换中...,请稍后再试!</p>');
    		//一段时间之后自动隐藏提示信息
    		setInterval("startrequest()",5000); 
    		return false;
    	}
    });    
   
    //文档详情 点赞
    $('#doclike').live('click',function(){
    	docLike($(this));
    });
   
    //文档详情 收藏
    $('#doccoll').live('click',function(){
    	docCollect($(this));
    });
    //文档详情 举报
    $('#docreport').live('click',function(){
    	docReport($(this));
    });
    //关注人或者取消关注
    $('.zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })
    //文档详情 发布评价
    $('a[name=savedocs]').live('click',function(){
    	commentDocs($(this),'1');
    });
    //文档详情  删除评论
    $('.js-cancel').live('click',function(){
    	cancelComment($(this));
    });
    //文档详情评价 加载更多
    $('.loadmore').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	docLoadMore($(this))
    });
    //文档详情  点击评论
	$('._CommentForm_actions_ooEq [name=answeraddnew]').live('click',function(){
		commentDocs($(this),'0');
	});
	//文档详情 文档下载
	$('#docfollow').live('click',function(){
		docDownLoadInit($(this));
	});
	//点击 立即下载
	$('.btn-diaolog-downdoc').live('click',function(){
		docDownLoad($(this));
	});
	//文档详情  内容分页
	$('.detail-list .goBtn').live('click',function(){
		detailContentPage($(this));
	});
	
})
 //定时刷新界面上边框的颜色
function startrequest() { 
	  $('p.error').hide();
} 
//文档详情  内容分页
function detailContentPage(obj){
	var page=Number(obj.attr('data-currentpage'))+1;
	var pagesize=Number(obj.attr('data-pagesize'))-3;
	var readurl=$('input[name=readurl]').val();
	var url='';
	if(Number(obj.attr('data-pagesize'))<3){
		for(var i=0;i<Number(obj.attr('data-pagesize'));i++){
			url+='<div class="secant_line"></div><a class="media" href="'+readurl+String((Number(page)*3-(i+1)))+'.swf"></a>'
		}	
	}else{
		  url+='<div class="secant_line"></div><a class="media" href="'+readurl+String((Number(page)*3-2))+'.swf"></a><div class="secant_line"></div>'
		  url+='<a class="media" href="'+readurl+String((Number(page)*3-1))+'.swf"></a><div class="secant_line"></div>'
		  url+='<a class="media" href="'+readurl+String((Number(page)*3))+'.swf"></a>'
	}

    $('.detail-list .banner-more-btn').before(url);
    $('a.media').media({width:800, height:1200});
    $('.moreBtn span:first-child').empty().append('还剩'+pagesize+'页未读，');
    if(Number(obj.attr('data-pagesize'))<=3){
    	$('.banner-more-btn').hide();
    	$('.banner-more-btn').next().hide();
    }
    obj.attr('data-pagesize',pagesize);
    obj.attr('data-currentpage',page);
   
}
//点击 立即下载
function docDownLoad(obj){
	//下载所需要的财富值
	var downvalue=obj.attr('data-downvalue');
	//自己所拥有的财富值
	var sumvalue=obj.attr('data-sumvalue');
	var docCreatePerson=obj.attr('data-docCreatePerson');
	//当前自己拥有的财富值大于或等于下载所需的财富值  才能下载
	if(Number(downvalue)>Number(sumvalue)){
		$('.zh-pm-warnmsg').show();
		return false;
	}   
	var docsuffix=$('input[name=docsuffix]').val();
    var docurl=$('input[name=docurl]').val();
    var title=$('input[name=docname]').val();
    docurl=docurl.substring(0,docurl.indexOf('.swf'))+'.'+docsuffix;
	var docid=$('input[name=docid]').val();
	 //关掉下载框
	 $('.modal-dialog-bg').remove();
     $('.modal-wrapper').remove();
	 $.ajax({
	    	type:"POST",
	      	url:projectName+"docs/downloadDocs",
	      	data:{downvalue:downvalue,id:docid,userid:docCreatePerson},
	    	dataType:"json",
	    	 async:false, 
	    	success:function(data){
	    		if(data.returnStatus=='000'){//返回成功
	    			window.location.href=docurl+'?filename='+title+'.'+docsuffix;
	    			$('#docfollow').attr('data-downvalue','0');
	    		}else{
	    			
	    		}
	       }
	 })
}
//文档详情 文档下载
function docDownLoadInit(obj){
	
	var downvalue=obj.attr('data-downvalue');
	var docCreatePerson=$('input[name=docCreatePerson]').val();
	var sumvalue=obj.attr('data-sumvalue');
	var docid=$('input[name=docid]').val();
	var isdown=obj.attr('data-isdown');
	if(String(userInfo.userid)==docCreatePerson||isdown==1){
		downvalue=0;	
	}
	var datamodel={
			downvalue:downvalue,
			sumvalue:sumvalue,
			docid:docid,
			docCreatePerson:docCreatePerson,
	}
	
	//加载模板
	$('.headiconintotem').setTemplateURL(projectName+'docDownLoad.html');
	$('.headiconintotem').processTemplate(datamodel);
	$('body').append($('.headiconintotem').html());
	$('.headiconintotem').empty();
}

//文档详情评价  加载更多
function docLoadMore(obj){
    var pageNo=obj.attr('data-pageno');
    var sumpage=obj.data('sumpage');
    var docid=$('input[name=docid]').val();
    $.ajax({
    	type:"POST",
      	url:projectName+"docs/loadComments",
      	data:{pageNo:Number(pageNo)+1,docid:docid},
    	dataType:"json",
    	success:function(data){
    		if(data.returnStatus=='000'){//返回成功
    			var datamodel={
    			   booklist:data.obj.list,
    			   userid:userInfo==undefined?'':userInfo.userid
    			}
    		   //判断是否点赞
    		  
    		   $('.headiconintotem').setTemplateURL(projectName+'docsLoadMoreTemplate.html');
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
//话题详情  删除评论
function cancelComment(obj){
	var docid=$('input[name=docid]').val();
	var id=obj.attr('data-recommend');
	$.ajax({
			type:"POST",
	     	url:projectName+"docs/delComment",
	     	data:{id:id,docid:docid},
			dataType:"json",
		    success:function(data){
		    	if(data.returnStatus=='000'){//返回成功
		    		obj.parents('.item').remove();
		    	}else{
		    		
		    	}
		    }
     	})
}
//文档详情 发布评价
function commentDocs(obj,type){//type 1代表用户直接发布评价语  0代表回复别人的或者自己的评论
	var commentby=''; 
	var relationid='';
	//被推荐人的名字
	var recommendname=obj.attr('data-recommendname');
	var docid=$('input[name=docid]').val();
	var docCreatePerson=$('input[name=docCreatePerson]').val();
	var comments=$('.commentcontent').val();
	var objectNamePg=$('input[name=docname]').val();
	if(type=='0'){
		commentby=obj.attr('data-recommend');
		comments=obj.parent().prev().val();
		objCreatepersonPg=commentby;
		relationid=obj.attr('data-relationid');
	}
	else{
	     relationid=docid;
	}
	//字数不能超过一千字
	var len=comments.length+(comments.match(/[^\x00-\xff]/g) ||"").length;
	if(len>1000){
		if(obj.parent().find('.errortip').length==0)
			obj.before('<span class="errortip">请控制在 1000 字以下</span>&nbsp;&nbsp;');
    		return false;
	}
	
	$this=obj;
	$.ajax({
		type:"POST",
     	url:projectName+"docs/addComment",
     	data:{docid:docid,commentby:commentby,comments:comments,objCreatepersonPg:docCreatePerson,relationidPg:docid,objectNamePg:objectNamePg},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		$('.commentcontent').val('');
	    		answerreplyCancel($this);
     			//界面上同时追加一条评论语
     			var datamodel={
     				user:userInfo,	
     			    id:data.obj.id,
     			    userShareTime:data.obj.userCommentTime,
     			    recommendname:recommendname,
     			    commentby:commentby,
     			    commendcontent:comments
     			}
     		   $('.headiconintotem').setTemplateURL(projectName+'bookAppendTemplate.html');
         	   $('.headiconintotem').processTemplate(datamodel);
         	   if($('.loadmore').length>0){
         		   $('.loadmore').before($('.headiconintotem').html());
         	   }else{
             	   $('.detailcomment-list').append($('.headiconintotem').html());
         	   }
         	   $(".headiconintotem").empty();
         	   intoUserInfo();
         	   //评价语文本框清空
         	   $('.commentcontent').val('');
         	   //发布成功之后去掉提示信息
       	       obj.parent().find('.errortip').remove();

	    	}else{
	    		
	    	}
	    }
	})
}
//加载用户信息
function intoUserInfo(){
	    $('.uname,.author-link,.zm-item-link-avatar,.zm-list-avatar').pinwheel();
}
//文档详情 举报
function docReport(obj){
	topicReport(obj);
}
//文档详情 收藏
function docCollect(obj){
	var iscollect=obj.attr('data-iscollect');
	var docid=$('input[name=docid]').val();
	var collectcount=obj.attr('data-collectcount');
	$.ajax({
		type:"POST",
     	url:projectName+"docs/collectDocs",
     	data:{judgeTodo:iscollect,objectid:docid},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		var $child=obj.children();
	    		if(iscollect==0){
     				obj.attr('data-iscollect','1');
     				obj.attr('data-collectcount',Number(collectcount)+1);
     				obj.empty().append($child).append('已收藏('+obj.attr('data-collectcount')+")");
     			}else{
     				obj.attr('data-iscollect','0');
     				obj.attr('data-collectcount',Number(collectcount)-1);
     				obj.empty().append($child).append(obj.attr('data-collectcount')+'人收藏');
     			}
	    	}else{
	    		
	    	}
	    }
	})
}
//文档详情 点赞
function docLike(obj){
	var docCreatePerson=$('input[name=docCreatePerson]').val();
	var relationidPg=$('input[name=docid]').val();
	var objectNamePg=$('input[name=docname]').val();
	var islike=obj.attr('data-islike');
	var likecount=obj.attr('data-likecount');
	$.ajax({
		type:"POST",
     	url:projectName+"docs/clickLikeOnDoc",
     	data:{likeOperate:islike,id:relationidPg,objCreatepersonPg:docCreatePerson,relationidPg:relationidPg,objectNamePg:objectNamePg},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			var $child=$('#doclike').children();
     			if(islike==0){
     				obj.attr('data-islike','1');
     				obj.attr('data-likecount',Number(likecount)+1);
     				obj.empty().append($child).append('取消赞('+obj.attr('data-likecount')+")");
     			}else{
     				obj.attr('data-islike','0');
     				obj.attr('data-likecount',Number(likecount)-1);
     				obj.empty().append($child).append(obj.attr('data-likecount')+'人赞');
     			}
     			
     		}else{
     			
     		}
     	}
	})
}
 //批量删除文档 --上传  
function deleteDocs(conditions,obj){
  	   $.ajax({
         	type:"POST",
         	url:projectName+"myCenter/deleteDocs",
         	data:{condition:conditions},
         	dataType:"json",
         	success:function(data){
         		if(data.returnStatus=='000'){//返回成功
         			//批量删除
         			if(conditions.indexOf(',')>0){
         				obj.parent().nextAll('.docs-list').find('.select-icon').each(function(){
    	        			$(this).parents('li').remove();
    	        		});
         			}
         			//单个删除
         			else{
         				//判断是否是批量删除 但只删除了一条
         				if(obj.hasClass('remove')){
         					obj.parent().nextAll('.docs-list').find('.select-icon').parents('li').remove()
         				}else{
         					obj.parents('li').remove();
         				}
         			}
          		   $('.remove ').removeClass('enabled');
          		   //上传
          		   $('#docupload').html('').html('上传('+data.operationSum.docsharesum+")");

        		}else{//返回失败

        		}

        	}
     });
  }


//批量删除文档 --（下载 收藏）
function deleteMyCollects(conditions,obj,actiontype){
	  //actionType 下载0 收藏1
	   $.ajax({
      	type:"POST",
      	url:"/51jobplusCore/myCenter/deleteMyCollects",
      	data:{condition:conditions,actionType:actiontype,collecttype:"tbl_docs"},
      	dataType:"json",
      	success:function(data){
      		if(data.returnStatus=='000'){//返回成功
      			//批量删除
      			if(conditions.indexOf(',')>0){
     				obj.parent().nextAll('.docs-list').find('.select-icon').each(function(){
	        			$(this).parents('li').remove();
	        		});
     			}
      			//单个删除
     			else{
     				//判断是否是批量删除 但只删除了一条
     				if(obj.hasClass('remove')){
     					obj.parent().nextAll('.docs-list').find('.select-icon').parents('li').remove();
     				}else{
     					obj.parents('li').remove();
     			    }
     				
     			}
      			$('#docdownload').html('').html('下载('+data.operationSum.docdownsum+")");
      			$('#doccollect').html('').html('收藏('+data.operationSum.doccollsum+")");
         		$('.remove ').removeClass('enabled');

     		}else{//返回失败

     		}

     	}
  });
}