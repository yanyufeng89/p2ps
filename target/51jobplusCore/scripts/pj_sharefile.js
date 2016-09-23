$(function(){
	
    /*文件上传页（searchuploadFile.html）根据选中的不同知识库类型调到对应的页面start*/
    $("#uploadFile a").click(function(){
    	var href="";
    	$("#upload-init-container .block-cnt a").each(function(){
    		if($(this).hasClass("tb-selected")){
    			   href=$(this).attr("data-target");
    			}
    	})
    	$(this).attr("href",href);
    })
   /*文件上传页（searchuploadFile.html）根据选中的不同知识库类型调到对应的页面end*/
   $("#upload-init-container .block-cnt a").click(function(){
    	if($(this).hasClass("tb-selected"))
            $(this).removeClass("tb-selected").children('i').remove();
    	else if($(this).children('i').length==0)
    		$(this).append("<i></i>").addClass("tb-selected").siblings().removeClass("tb-selected").children('i').remove();
    	var sharename=$(this).attr('data-sharename');
    	$('#loveshare').text(sharename);
    });
        
})