$(function(){
	editor=UM.getEditor('uEditorAbout',{
		initialFrameWidth :860,//设置编辑器宽度
		initialFrameHeight:188,//设置编辑器高度
		scaleEnabled:true
	});
	//UEditor点击全屏按钮事件
    $('.edui-btn-fullscreen').live('click',function(){
    	if($('.edui-body-container').height()=='188'){
    		 $('.edui-body-container').css('height','950px');
    		 $('.zg-noti-number,#ft,#footnote').hide();
    	}else{
    		 $('.edui-body-container').css('height','188px');
    		 $('.zg-noti-number,#ft,#footnote').show();
    	}
    });
})


function sugSubmit(){
	var sugMobile = $.trim($("#sugMobile").val());
	var sugEmail =  $.trim($("#sugEmail").val());
	var sugContent = editor.getContent();
	if(!UM.getEditor('uEditorAbout').hasContents()){
		ZENG.msgbox.show('请输入反馈内容!', 5, 3000);return false;;return false;
	}
	if(sugEmail=="" && sugMobile==""){
		ZENG.msgbox.show('手机或者邮箱不能为空!', 5, 3000);return false;
	}
	
	var myreg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var reg= /^[1][34578]\d{9}$/; 
	
	if(!myreg.test(sugEmail) && !reg.test(sugMobile)){
		ZENG.msgbox.show('请输入正确的手机或邮箱!', 5, 3000);return false;
	}
	$.ajax({
	type:"POST",
  	url:"/51jobplusCore/suggestion/add",
  	data:{sugtel:sugMobile,sugemail:sugEmail,sugcontent:sugContent},
	dataType:"json",
	success:function(data){
		if(data.returnStatus=='000'){
			ZENG.msgbox.show('提交成功!',4, 3000);
			//清空文本框
			$('#sugEmail').val('');
			$('#sugMobile').val('');
			editor.setContent('');
		}else{
			
		}
	}
})}
