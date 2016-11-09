$(function(){
	 //站点详情简介
	if($('input[name=sitescontent]').length>0){
		var content=$('input[name=sitescontent]').val();
		if(content.replace(/[^x00-xFF]/g,'**').length>150){
			$('.slidedown').show();
			
		}
   }
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
    		   $('.pagetemplate').setTemplateURL('/coursesLoadMoreTemplate.html');
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