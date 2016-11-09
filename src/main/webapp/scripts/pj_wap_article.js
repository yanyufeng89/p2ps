$(function(){
	
})
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