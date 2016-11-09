var ispublic=true;
$(function(){
	//分类
	getAllParentTypeConfigs('articletype');
	//实例化编辑器
	 var editor=UM.getEditor('arsummernote',{
		    initialFrameWidth :720,//设置编辑器宽度
			initialFrameHeight:603,//设置编辑器高度 
			scaleEnabled:true
		});
   /*$(".edui-body-container").css("width", "98%");*/
	//UEditor点击全屏按钮事件
   $('.edui-btn-fullscreen').live('click',function(){
	   	if($('.edui-body-container').height()=='603'){
	   		 $('.edui-body-container').css('height','950px');
	   		 $('.zg-noti-number,#ft,#footnote').hide();
	   		$('.editlayoutarticle dd').eq(1).hide();
	   	}else{
	   		 $('.edui-body-container').css('height','603px');
	   		 $('.zg-noti-number,#ft,#footnote').show();
	   		$('.editlayoutarticle dd').eq(1).show();
	   	}
   });
	//确认分享
	$('#sharearticle').live('click',function(){
		//标题不为空
		if($.trim($('#pj-article-suggest-title-content').val()).length==0){
			$('#pj-article-warnmsg .item-msg-content').html('请输入文章标题').show();
			$('#pj-article-warnmsg').show();
			$('#pj-article-suggest-title-content').focus();
			return false;
		}
		if($.trim($('#pj-article-suggest-title-content').val()).replace(/[^x00-xFF]/g,'**').length>256){
			$('#pj-article-warnmsg .item-msg-content').html('标题字数超出最大限制').show();
			$('#pj-article-warnmsg').show();
			$('#pj-article-suggest-title-content').focus();
			return false;
		}
		if(!editor.hasContents()){
			$('#pj-article-warnmsg .item-msg-content').html('请输入文章内容');
			$('#pj-article-warnmsg').show();
			return false;
		}
		if(editor.hasContents().replace(/[^x00-xFF]/g,'**').length>16777215){
			$('#pj-article-warnmsg .item-msg-content').html('文章内容字数超出最大限制');
			$('#pj-article-warnmsg').show();
			return false;
		}
		//分类
		if($('input[name=articletype]').val()==''||$('input[name=articletype]').val().indexOf('undefined')!=-1){
			$('#pj-modal-dialog-articleclassify').show();
			return false;
		}
		$('input[name=intro]').val(editor.getContent());
		//标签
		var tagid="";
		$("#articleinputtags div a").each(function(){
			tagid+=$(this).attr('id')+":"+$(this).data('name')+",";
		});
		$("input[name=articleclass]").val(tagid.substring(0,tagid.length-1));
		//添加遮罩层 防止在上传的同时做其他操作
		addMaskLayer();
		$('#articleaddForm').submit();
	})
	//推荐理由获得焦点
	$('textarea[name=recommend]').live('click',function(){
		$(this).nextAll().css('display','none');
	})
    //确定
	$('#btnarticlesure').live('click',function(){
		if($.trim($('#searcharticle').val()).length==0){
			var $child=$('#searcharticle').next().children();
			$('#searcharticle').next().empty().append($child).append('请输入标题').show();
	    	return false;
		}else{
			$('#upload-initarticle-container').hide();
	    	$('#upload-article-container').show();
	    	$('#pj-article-suggest-title-content').val($('#searcharticle').val());
		}
	})
	//窗体缩放
	$(window).resize(function(){
		$('.edui-container').css('z-index','0');
	})
	//获取公开 或 匿名
    $("input[type=radio]").live('change',function(){
	    $('input[name=ispublic]').val($(this).val());
    }) 
})


//文章详情
function initTitleInfoBySearchArticle(data){
    $('.add-course-title-form').empty();
    var container_div='';
	container_div+="<div class='courseinfo'>";
	container_div+="  <input type='hidden' name='title' value='"+data.obj.title+"'>"
	container_div+="  <input type='hidden' name='intro' value='"+data.obj.intro+"'>"
	container_div+="  <p><span>[文章]</span>"+data.obj.title+"</p>"
	if(data.obj.intro!=undefined){
	   container_div+="  <p><span>简介:</span>"+data.obj.intro+"</p>"
	}
	
	container_div+="</div>";
	
	$('.add-course-title-form').append(container_div);
}
