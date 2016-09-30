var flag;
var ispublic=true;
$(function(){
	//分类
	getAllParentTypeConfigs('coursestype');
	
	//输入课程链接  点击确定
	$('.coursepreview').live('click',function(){
		$('.coursepreview').nextAll('.error').hide();
		var url=$('#searchcourse').val();
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
	//获得焦点
	$('#searchcourse').live('focus',function(){
		$(this).nextAll('.error').hide();
	})
	
	//确认分享
	$('#sharecourse').live('click',function(){
		//推荐理由必填
		if($.trim($('textarea[name=recommend]').val()).length==0){
			$('textarea[name=recommend]').nextAll().css('display','inline-block');
            $('textarea[name=recommend]').focus();
            return false;
		}
		//分类
		if($('input[name=coursestype]').val()==''||$('input[name=coursestype]').val().indexOf('undefined')!=-1){
			$('#pj-modal-dialog-courseclassify').show();
			return false;
		}
		//标签
		var tagid="";
		$("#courseinputtags div a").each(function(){
			tagid+=$(this).attr('id')+":"+$(this).data('name')+",";
		});
		$("input[name=coursesclass]").val(tagid.substring(0,tagid.length-1));
		
		$('#courseaddForm').submit();
	})
	
	//推荐理由获得焦点
	$('textarea[name=recommend]').live('click',function(){
		$(this).nextAll().css('display','none');
	})
	//确定
	$('#btncoursesure').live('click',function(){
		$(this).addClass('capture-loading').html('');
		if($.trim($('#searchcourse').val()).length==0){
			var $child=$('.coursepreview').nextAll('.error').children();
			$('.coursepreview').nextAll('.error').empty().append($child).append('请输入正确的链接').show();
	    	return false;
		}else{
	    	$.ajax({
	    		type:"POST",
	    		url:"/myCenter/get3WInfo",
	    		data:{url:$('#searchcourse').val(),tableName:'tbl_courses'},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.returnStatus=='000'){
	    				$('input[name=coursesurl]').val(data.obj.url);
	    				//课程详情
	    	            initTitleInfoBySearchCourse(data);
	    	            $('#upload-initcourse-container').hide();
	    		    	$('#upload-course-container').show();
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
		data:{url:url,tableName:'tbl_courses'},
		dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
    	    	
    	    	$("#btncoursesure").removeClass("btn-disblue").addClass("btn-blue");
    		}else{//返回失败
    			var $child=$('.coursepreview').nextAll('.error').children();
    			$('.coursepreview').nextAll('.error').empty().append($child).append(data.returnMsg).show();
    		}
     		$('.coursepreview').removeClass('capture-loading').addClass('tocapture');
    	}
	})
}
//课程详情
function initTitleInfoBySearchCourse(data){
	$('.add-course-title-form').empty();
    var container_div='';
	container_div+="<div class='courseinfo'>";
	container_div+="  <input type='hidden' name='coursesname' value='"+data.obj.title+"'>"
	container_div+="  <input type='hidden' name='intro' value='"+data.obj.intro+"'>"
	container_div+="  <p><span>[课程]</span>"+data.obj.title+"</p>"
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

