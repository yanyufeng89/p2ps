$(function(){
	//书籍详情简介 
	if($('input[name=bookcontent]').length>0){
		var content=$('input[name=bookcontent]').val();
		if(content.replace(/[^x00-xFF]/g,'**').length>150){
			$('.slidedown').show();
		}
	}
	//详情简介展开
	$('.slidedown').live('click',function(){
		$(this).hide().next().show();
		$(this).parents('.description').removeClass('tool-height');
	})
	//详情简介收起
	$('.slideup').live('click',function(){
		$(this).hide().prev().show();
		$(this).parents('.description').addClass('tool-height');
	})
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
