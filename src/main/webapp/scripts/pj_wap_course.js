$(function(){
	   //课程详情简介
	   if($('input[name=coursecontent]').length>0){
		   var cont=$('input[name=coursecontent]').val();
		   if(cont.replace(/[^x00-xFF]/g,'**').length>120){
				$('.brief-content').html(autoAddEllipsis(cont,120)).after("<span class='showall'>展示全部</span>");
			}
	   }
	   //简介展开全部
	   $('.showall').live('click',function(){
		   $(this).hide().next().show();
		   $('.brief-content').html($('input[name=coursecontent]').val());
		   $('#coursebrief').removeClass('course-height').css('font-size','1.6rem');
	   });
	   //简介收起
	   $('.slideup').live('click',function(){
		   $(this).hide().prev().show();
		   $('.brief-content').html(autoAddEllipsis($('input[name=coursecontent]').val(),120));
		   $('#coursebrief').addClass('course-height').css('font-size','2.2rem');
	   });
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