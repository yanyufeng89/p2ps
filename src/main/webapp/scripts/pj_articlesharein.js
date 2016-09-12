var ispublic=true;
$(function(){
	//分类
	getAllParentTypeConfigs('articletype');
	
	//输入文章链接  点击确定
	$('.articlepreview').live('click',function(){
		
		var url=$('#searcharticle').val();
		var $child=$(this).nextAll('.error').children();
		if($.trim(url)==''){
			 $(this).nextAll('.error').empty().append($child).append('请输入正确的链接').show();
	    	 return false;
		}
		//判断链接是否合法   
	     if(isURL(url)){
	    	$('.capture-loading').prev().val('');
	 		$('.capture-loading').show();
	    	//后台判断是否能够拼通链接
	        get3WInfo(url);
	     }else{
	    	 $(this).nextAll('.error').empty().append($child).append('链接格式不正确').show();
	    	 return false;
	     }
	})
	
	//确认分享
	$('#sharearticle').live('click',function(){
		//推荐理由必填
		$('textarea[name=recommend]').each(function(){
			if($.trim($(this).val()).length==0){
				$(this).nextAll().css('display','inline-block');
                $(this).focus();
                return false;
			}
		})
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
    /*//标签删除
    $('.zg-inline a[name=remove]').live('click',function(){
   		var $floor=$(this).parents('.zg-inline').next();
   		$floor.find(':first-child').show();
   		$floor.find('input[type=text]').show();
   		$floor.find(':last-child').hide();
   		$(this).parent().remove();
   	});*/
})

//判断输入的链接是否拼通
function get3WInfo(url){
	$.ajax({
		type:"POST",
		url:projectName+"/myCenter/get3WInfo",
		data:{url:url},
		dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			$('.capture-loading').show();
     			$('#upload-initarticle-container').hide();
    	    	$('#upload-article-container').show();
    	    	$('input[name=articleurl]').val(url);
    	    	
    	    	$('.capture-loading').prev().val('确定');
    			$('.capture-loading').hide();
    	    	//文章详情
	            initTitleInfoBySearchArticle(data);
    		}else{//返回失败
    			
    		}
    		
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
//判断是否是合格的URL
function isURL (str_url) {// 验证url  
    var strRegex="^((https|http|rtsp|mms)?://)"  
    + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@  
    + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
    + "|" // 允许IP和DOMAIN（域名）  
    + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.  
    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名  
    + "[a-z]{2,6})" // first level domain- .com or .museum  
    + "(:[0-9]{1,4})?" // 端口- :80  
    + "((/?)|" // a slash isn't required if there is no file name  
    + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
    var re=new RegExp(strRegex); 
    return re.test(str_url); 
} 