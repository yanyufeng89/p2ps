var ispublic=true;
$(function(){
	//分类
	getAllParentTypeConfigs('sitetype');
	//输入链接点击确定
	$('.sitepreview').live('click',function(){
		var url=$('#searchsite').val();
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
	$('#sharesite').live('click',function(){
		//推荐理由必填
		$('textarea[name=recommend]').each(function(){
			if($.trim($(this).val()).length==0){
				$(this).nextAll().css('display','inline-block');
                $(this).focus();
                return false;
			}
		})
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
		
		$('#siteaddForm').submit();
	})
})

//判断输入的链接是否拼通
function get3WInfo(url){
	$.ajax({
		type:"POST",
		url:projectName+"/myCenter/get3WInfo",
		data:{url:url,tableName:'tbl_sites'},
		dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			$('#upload-initsite-container').hide();
    	    	$('#upload-site-container').show();
    	    	$('input[name=siteurl]').val(url);
    	    	
    	    	//站点详情
	            initTitleInfoBySearchSite(data);
    		}else{//返回失败
    			var $child=$('.sitepreview').nextAll('.error').children();
    			$('.sitepreview').nextAll('.error').empty().append($child).append(data.returnMsg).show();
    		}
     		$('.capture-loading').prev().val('确定');
			$('.capture-loading').hide();
    	}
	})
}
//站点详情
function initTitleInfoBySearchSite(data){
	 $('.add-course-title-form').empty();
	    var container_div='';
		container_div+="<div class='courseinfo'>";
		container_div+="  <input type='hidden' name='title' value='"+data.obj.title+"'>"
		container_div+="  <input type='hidden' name='intro' value='"+data.obj.intro+"'>"
		container_div+="  <p><span>[站点]</span>"+data.obj.title+"</p>"
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
function isURL_old (str_url) {// 验证url  
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

//判断是否是合格的URL
function isURL(str_url){
    return!!str_url.match(/(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g);
}