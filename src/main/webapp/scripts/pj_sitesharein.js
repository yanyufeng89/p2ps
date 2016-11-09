var ispublic=true;
$(function(){
	//分类
	getAllParentTypeConfigs('sitetype');
	//输入链接点击确定
	$('.sitepreview').live('click',function(){
		$('.sitepreview').nextAll('.error').hide();
		var url=$('#searchsite').val();
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
	$('#sharesite').live('click',function(){
		//简介必填
		if($.trim($('textarea[name=intro]').val()).length==0){
			$('textarea[name=intro]').next().next().html('简介必填');
			$('textarea[name=intro]').nextAll().css('display','inline-block');
            $('textarea[name=intro]').focus();
            return false;
		}
		//简介字数限制
		if($.trim($('textarea[name=intro]').val()).replace(/[^x00-xFF]/g,'**').length>65535){
			$('textarea[name=intro]').next().next().html('简介字数超出最大限制');
			$('textarea[name=intro]').nextAll().css('display','inline-block');
            $('textarea[name=intro]').focus();
            return false;
		}
		//推荐理由必填
		if($.trim($('textarea[name=recommend]').val()).length==0){
			$('textarea[name=recommend]').nextAll().css('display','inline-block');
            $('textarea[name=recommend]').focus();
            return false;
		}
		//分类
		if($('input[name=sitetype]').val()==''||$('input[name=sitetype]').val().indexOf('undefined')!=-1){
			$('#pj-modal-dialog-siteclassify').show();
			return false;
		}
		//标签
		var tagid="";
		$("#siteinputtags div a").each(function(){
			tagid+=$(this).attr('id')+":"+$(this).data('name')+",";
		});
		$("input[name=siteclass]").val(tagid.substring(0,tagid.length-1));
		//添加遮罩层 防止在上传的同时做其他操作
		addMaskLayer();
		$('#siteaddForm').submit();
	})
	//确定
	$('#btnsitesure').live('click',function(){
		$(this).addClass('capture-loading').html('');
		if($.trim($('#searchsite').val()).length==0){
			var $child=$('.sitepreview').nextAll('.error').children();
			$('.sitepreview').nextAll('.error').empty().append($child).append('请输入正确的链接').show();
	    	return false;
		}else{
	    	$.ajax({
	    		type:"POST",
	    		url:"/myCenter/get3WInfo",
	    		data:{url:$('#searchsite').val(),tableName:'tbl_sites'},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.returnStatus=='000'){
	    				$('input[name=siteurl]').val(data.obj.url);
	    				//站点详情
	    				initTitleInfoBySearchSite(data);
	    				$('#upload-initsite-container').hide();
	    		    	$('#upload-site-container').show();
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
		data:{url:url,tableName:'tbl_sites'},
		dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
    	    	$("#btnsitesure").removeClass("btn-disblue").addClass("btn-blue");
    		}else{//返回失败
    			var $child=$('.sitepreview').nextAll('.error').children();
    			$('.sitepreview').nextAll('.error').empty().append($child).append(data.returnMsg).show();
    		}
     		    $('.sitepreview').removeClass('capture-loading').addClass('tocapture');
    	}
	})
}
//站点详情
function initTitleInfoBySearchSite(data){
	$('.add-title-form').html(data.obj.title);
	$('input[name=title]').val(data.obj.title);
	$('textarea[name=intro]').html(data.obj.intro);
}
 
