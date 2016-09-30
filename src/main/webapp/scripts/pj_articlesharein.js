var ispublic=true;
$(function(){
	//分类
	getAllParentTypeConfigs('articletype');
	
	//输入文章链接  点击确定
	$('.articlepreview').live('click',function(){
		$('.articlepreview').nextAll('.error').hide();
		var url=$('#searcharticle').val();
		var $child=$(this).nextAll('.error').children();
		if($.trim(url)==''){
			 $(this).nextAll('.error').empty().append($child).append('请输入正确的链接').show();
	    	 return false;
		}
		//判断链接是否合法   
	     if(isURL(url)){
	    	 $(this).addClass('capture-loading').removeClass('tocapture');
	    	 //后台判断是否能够拼通链接
	         get3WInfo(url);
	     }else{
	    	 $(this).nextAll('.error').empty().append($child).append('链接格式不正确').show();
	    	 return false;
	     }
	})
	
	//确认分享
	$('#sharearticle').live('click',function(){
		if($.trim($('textarea[name=recommend]').val()).length==0){
			$('textarea[name=recommend]').nextAll().css('display','inline-block');
            $('textarea[name=recommend]').focus();
            return false;
		}
		//分类
		if($('input[name=articletype]').val()==''||$('input[name=articletype]').val().indexOf('undefined')!=-1){
			$('#pj-modal-dialog-articleclassify').show();
			return false;
		}
		//标签
		var tagid="";
		$("#articleinputtags div a").each(function(){
			tagid+=$(this).attr('id')+":"+$(this).data('name')+",";
		});
		$("input[name=articleclass]").val(tagid.substring(0,tagid.length-1));
		$('#articleaddForm').submit();
	})
	//推荐理由获得焦点
	$('textarea[name=recommend]').live('click',function(){
		$(this).nextAll().css('display','none');
	})
    //确定
	$('#btnarticlesure').live('click',function(){
		$(this).addClass('capture-loading').html('');
		if($.trim($('#searcharticle').val()).length==0){
			var $child=$('.articlepreview').nextAll('.error').children();
			$('.articlepreview').nextAll('.error').empty().append($child).append('请输入正确的链接').show();
	    	return false;
		}else{
	    	$.ajax({
	    		type:"POST",
	    		url:"/myCenter/get3WInfo",
	    		data:{url:$('#searcharticle').val(),tableName:'tbl_article'},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.returnStatus=='000'){
	    				$('input[name=articleurl]').val(data.obj.url);
	    				//文章详情
	    				initTitleInfoBySearchArticle(data);
	    				$('#upload-initarticle-container').hide();
	    		    	$('#upload-article-container').show();
	    			}
	    		}
	    	})
	    	
		}
	})
})

//判断输入的链接是否拼通
function get3WInfo(url){
	$.ajax({
		type:"POST",
		url:"/myCenter/get3WInfo",
		data:{url:url,tableName:'tbl_article'},
		dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
    	    	
    	    	$("#btnarticlesure").removeClass("btn-disblue").addClass("btn-blue");
    		}else{//返回失败
    			var $child=$('.articlepreview').nextAll('.error').children();
    			$('.articlepreview').nextAll('.error').empty().append($child).append(data.returnMsg).show();
    		}   
     		$('.articlepreview').removeClass('capture-loading').addClass('tocapture');
    	}
	})
}
//文章详情
function initTitleInfoBySearchArticle(data){
    $('.add-course-title-form').empty();
    var container_div='';
	container_div+="<div class='courseinfo'>";
	container_div+="  <input type='hidden' name='title' value='"+data.obj.title+"'>"
	container_div+="  <input type='hidden' name='intro' value='"+data.obj.intro+"'>"
	container_div+="  <p><span>[文章]</span>"+data.obj.title+"</p>"
	if(data.obj.intro!=undefined){
		if(data.obj.intro.length>180){
			container_div+="  <p title='"+data.obj.intro+"'><span>简介:</span>"+data.obj.intro.substring(0,180)+"..."+"</p>"
		}
		else
			container_div+="  <p title='"+data.obj.intro+"'><span>简介:</span>"+data.obj.intro+"</p>"
	}
	
	container_div+="</div>";
	
	$('.add-course-title-form').append(container_div);
}
