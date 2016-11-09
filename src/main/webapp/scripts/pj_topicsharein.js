var ispublic=true;
var oldval,newval;
$(function(){
	/*//窗体加载的时候 焦点定位到文本框
	$('#js-before-ask').focus();*/
	//确定
	$("#upload-inittopic-container").on('click','#btntopicsure',function(){
		 var istrue=true;
		 var askval=$("#js-before-ask").val();/*.replace(/[ ]/g,"");*///去掉空格
		 $(this).addClass('capture-loading').html('');
		 if(askval.length==0){
			 $("#js-before-ask").parent().find('.error').html('请输入话题').show();
			 istrue=false;
		 }else if(askval.length>50){
			 $("#js-before-ask").parent().find('.error').html('已超出'+(askval.length-50)+'字').show();
			 istrue=false;
		 }
		 if(!istrue){
			 $('#js-before-ask').focus();
			 $(this).removeClass('capture-loading').html('确定');
			 return false;
		 }
		 //字数限制在50字以内
		 $('input[name=pj-autocomplete]').val('');
		 $('.capture-loading').show();
		 $("#upload-inittopic-container").hide();
		 $("#upload-topicfiles-container").show();
		 $("#pj-question-suggest-title-content").val(askval).focus();
		 $('.capture-loading').hide();
	})
	//(提问题文本框)光标一直定位在起始位置
	$("#pj-question-suggest-title-content").live('click',function(){
		if($(this).val().trim().length==0)
			$(this).val($(this).val().trim());
	})
	//实例化编辑器
	 var editor=UM.getEditor('summernote',{
			initialFrameHeight:170,//设置编辑器高度  
			initialFrameWidth :563,//设置编辑器宽度563
			scaleEnabled:true
		});
	//默认隐藏工具栏
	$('.edui-container .edui-toolbar .edui-btn-toolbar').css('display','none');
	$('.tr-inline-icon').live('click',function(){
		$('.edui-container .edui-toolbar .edui-btn-toolbar').toggle();
	})
	//分类
	getAllParentTypeConfigs('topicstype');

    //获取财富值
    $('select').live('change',function(){
	  $(this).prev().val($(this).find('option:selected').val());
    });
   
    //简介输入（提示信息隐藏）
   $('.edui-body-container').on('focus',function(){
   	$('#pj-modal-dialog-detail-wrapper').hide();
   });
   
   //UEditor点击全屏按钮事件
   $('.edui-btn-fullscreen').live('click',function(){
   	if($('.edui-body-container').height()=='170'){
   		 $('.edui-body-container').css('height','950px');
   		 $('.zg-noti-number,#ft,#footnote').hide();
   		$('.editlayoutbook dd').eq(1).hide();
   	}else{
   		 $('.edui-body-container').css('height','170px');
   		$('.zg-noti-number,#ft,#footnote').show();
   		$('.editlayoutbook dd').eq(1).show();
   	}
 });
    //窗体缩放
  	$(window).resize(function(){
		$('.edui-container').css('z-index','0');
	})
	//发布
	$("#btn-public-all").on('click',function(){
		
		var ispublic=true;
		//点击发布的时候  首先判断输入的内容是否符合要求  (标题不少于两个字  且 要带有问号)
		var title=$("#pj-question-suggest-title-content").val();
		if(title.length<=2){
			ispublic=false;
		}
		if(title.length>50){
			$("#pj-modal-dialog-warnmsg-wrapper").find('.item-msg-content').html('已超出'+(title.length-50)+'字');
			ispublic=false;
		}
		if(title.substring(title.length-1,title.length)!="?"&&title.substring(title.length-1,title.length)!="？"){
			$("#pj-modal-dialog-warnmsg-wrapper").find('.item-msg-content').html('您还没有给问题添加问号');
			ispublic=false;
		}
		if(title.replace(/[^x00-xFF]/g,'**').length>256){
			$("#pj-modal-dialog-warnmsg-wrapper").find('.item-msg-content').html('标题字数超出最大限制');
			ispublic=false;
		}
		if(!ispublic){
			$("#pj-modal-dialog-warnmsg-wrapper").show();
			$('#pj-question-suggest-title-content').focus();
			return false;
		}
		//问题简介必填
		var detailContent=editor.getContent();
		if(!editor.hasContents()){
			$('#pj-modal-dialog-detail-wrapper span').html('问题简介必填');
			$('#pj-modal-dialog-detail-wrapper').show();
			$('#summernote').focus();
			return false;
		}
		//字数不能大于10000
		var len=detailContent.length+(detailContent.match(/[^\x00-\xff]/g) ||"").length;
    	if(len>10000){
    		$('#pj-modal-dialog-detail-wrapper span').html('请控制在10000 字以下');
    		$('#pj-modal-dialog-detail-wrapper').show();
			$('#summernote').focus();
    		return false;
    	}
		
		//分类
		if($('input[name=topicstype]').val()==''||$('input[name=topicstype]').val().indexOf('undefined')!=-1){
			$('#pj-modal-dialog-classify').show();
			return false;
		}
		
		//问题简介
		var about=$(".edui-body-container").html();
		$("#topicaddForm input[name=content]").val(about);
		
		//匿名
		$("#topicaddForm input[name=ispublic]").val($("input[type=checkbox]").is(':checked')==true?0:1);
		
		//选择话题标签 (必填   只有话题标签是必填的 其他五项不是)
		var tagid="";
		$("#inputtags div a").each(function(){
			tagid+=$(this).attr('id')+":"+$(this).data('name')+",";
		});
		if($.trim(tagid).length==0){
			$('.pj-warmprompt').show();
			return false;
		}
		$("#topicaddForm input[name=topicsclass]").val(tagid.substring(0,tagid.length-1));
		//添加遮罩层 防止在上传的同时做其他操作
		addMaskLayer();
		$("#topicaddForm").submit();
	});

	//input实时从数据库中筛选数据
	$('#js-before-ask,#pj-question-suggest-title-content').bind('input propertychange',function(){
		 var searchtype=$(this).attr('data-searchtype');
		 oldval=$('input[name=pj-autocomplete]').val();
		 newval=$(this).val();
		if(newval !== null &&newval !== undefined&&$.trim(newval).length!=0&&$.trim(oldval).length!=$.trim(newval).length){
			findTitle($(this),searchtype);
		}else if($.trim(newval).length==0){
			if(searchtype=='0'){
				$("#ask-renderer div").remove();
			}else{
				$("#pj-question-suggest-ac-wrap .ac-renderer div").remove();		
			}
			$('#ask-renderer,#pj-question-suggest-ac-wrap').css('border','0');
		}
		$('input[name=pj-autocomplete]').val(newval);
	})
	
})
function sendFile(file,editor,welEditable) {
    data = new FormData();
    data.append("file", file);
    $.ajax({
        data: data,
        type: "POST",
        url: "/topics/album/uploadImage/999",
        cache: false,
        contentType: false,
        processData: false,
        success: function(data) {
            editor.insertImage(welEditable, data.url);
        }
    });
}

//模糊查询标题  	
function findTitle(obj,flag){
	    $("#js-before-ask").parent().find('.error').hide();
	    $("#pj-modal-dialog-warnmsg-wrapper").hide();
     	var conds=$(obj).val();
	  	//过滤掉空格 "" null 等
   		if($.trim(conds).length>0){
   		 $.ajax({
	         	type:"POST",
	         	url:"/topics/findTitle/"+conds,
	         	//data:{condition:100},
	         	dataType:"json",
	         	async:false,
	         	success:function(data){
	         		if(data.returnStatus=='000'){//返回成功
	         		    $('#ask-renderer').css('border','1px solid #ccc').css('border-top','0');
	         			if(flag=='0')
	         			initTitleBySearchTopic(data,conds);
	         			else
	         		    initTitleContentBySearchTopic(data,conds);
	         		  
	            		
	        		}else{//返回失败
	        			
	        		}
	        	}
	     });
   		}
   		else{
   			$("#ask-renderer").hide();
   			$("#pj-question-suggest-ac-wrap").hide();
   		}
	   	  		
	  }

function initTitleBySearchTopic(data,conds){
	$("#ask-renderer div").remove();
	var html='';
	var divhtml="<div class='ask-row ask-first'><b>你想问的是不是:</b></div>";
	$.each(data['list'],function(a,b){
		if(b.replysum==undefined)
			b.replysum=0;
		html+="<div class='ask-row' data-askid='"+b.data_id+"' data-answer-count"+b.replysum+"> ";
		html+="   <a style='color:rgb(34,34,34)' target='_blank' href='/topics/getTopicsDetail/"+b.data_id+"'>"+b.title+"</a>"
	    html+="   <span class='ask-gray'>"+b.replySum+"个人回答问题</span>"
		html+="</div>";
	})
	if(html!="")
	    $("#ask-renderer").append(divhtml+html).show();
	else
		$('#ask-renderer').css('border','0');
}

function initTitleContentBySearchTopic(data,conds){
	$("#pj-question-suggest-ac-wrap .ac-renderer div").remove();
	var container_div="";
	var container_html='';
	var html='';
	var divhtml="<div class='ac-head zg-gray' style='-webkit-user-select: none;'><b>你的问题可能已经有答案</b></div>";
	$.each(data['list'],function(a,b){
		if(b.replysum==undefined)
			b.replysum=0;
		html+="<div class='ask-row goog-zippy-header goog-zippy-collapsed' data-answer_id="+b.data_id+" data-answer_count='"+b.replysum+"'>";
	    html+="<a style='color: rgb(34, 34, 34);' target='_blank' href='/topics/getTopicsDetail/"+b.data_id+"'>"+b.title+"</a>"
	    html+="   <span class='zm-ac-gray'>"+b.replySum+"个人回答问题</span>"
		html+="</div>";
	 })
	 if(html!=""){
		 $("#pj-question-suggest-ac-wrap .ac-renderer").append(divhtml+html);
	 }
	 $("#pj-question-suggest-ac-wrap").show();
}
function initClass(){
	$('.topic-sort ol').html(leftsort());
	$('.edit-class-sec ol').html(rightsort(''));
}
