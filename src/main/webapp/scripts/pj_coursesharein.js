var flag;
var ispublic=true;
$(function(){
	//分类
	getAllParentTypeConfigs('coursestype');
	
	//输入课程链接  点击确定
	$('.coursepreview').live('click',function(){
		var url=$('#searchcourse').val();
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
	//获得焦点
	$('#searchcourse').live('focus',function(){
		$(this).nextAll('.error').hide();
	})
	
	//确认分享
	$('#sharecourse').live('click',function(){
		//推荐理由必填
		$('textarea[name=recommend]').each(function(){
			if($.trim($(this).val()).length==0){
				$(this).nextAll().css('display','inline-block');
                $(this).focus();
                return false;
			}
		})
		
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
})

//根据条件查询(标签) url:"tags/findClass/"+condition
function getTagsByCondition(obj){
	$this=$(obj);
	var conds=$.trim($(obj).val());
	//过滤掉空格 "" null 等
	if (conds !== null || conds !== undefined || conds !== '') { 
		//过滤掉正在输入汉字的情况
		if($.trim(conds).length>0){
		 $.ajax({
	         	type:"POST",
	         	url:projectName+"tags/findClass/"+conds,
	         	//data:{condition:100},
	         	dataType:"json",
	         	success:function(data){
	         		if(data.returnStatus=='000'){//返回成功
	         			$this.chosen(data,$this,'doc',conds);
	        		}else{//返回失败
	        			
	        		}
	        		
	        	}
	     });
		} 
	   else{
			//当input为空时 隐藏搜索的下拉列表
		   $(".zm-tag-editor-command-buttons-wrap div:last-child").remove();
		}
	}else{//输入值 为   空格 "" null 等
		
	}   		
}
//判断输入的链接是否拼通
function get3WInfo(url){
	$.ajax({
		type:"POST",
		url:projectName+"/myCenter/get3WInfo",
		data:{url:url},
		dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			$('#upload-initcourse-container').hide();
    	    	$('#upload-course-container').show();
    	    	$('input[name=coursesurl]').val(url);
    	    	$('.capture-loading').prev().val('确定');
    			$('.capture-loading').hide();
    	    	//课程详情
	            initTitleInfoBySearchCourse(data);
    		}else{//返回失败
    			
    		}
    		
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
//判断是否是合格的URL
function isURL (str_url) {// 验证url  
    var strRegex="^((https|http|ftp|rtsp|mms)?://)"  
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
