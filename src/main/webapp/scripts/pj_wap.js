var load_flag=false;
var userInfo;
$(function(){
	 if (typeof WeixinJSBridge == "object" && typeof WeixinJSBridge.invoke == "function") {
		 handleFontSize();
	 }else{
			 if (document.addEventListener){
				 document.addEventListener("WeixinJSBridgeReady", handleFontSize, false);
			 }else if(document.attachEvent){
				 document.attachEvent("WeixinJSBridgeReady", handleFontSize);

				 document.attachEvent("onWeixinJSBridgeReady", handleFontSize);
			 }
	 }
	getCurrentUser();
	/*$(window).scroll(function() {
        	var  requestUrl=window.location.href;
            //当页面拉到底的时候 加载更多
            if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
            	if($('.loadmore').length>0){
            		$('.loadmore').addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
            		if(requestUrl.indexOf('article')!=-1){
                    	articleLoadMore($('.loadmore'));
                	}else if(requestUrl.indexOf('sites')!=-1){
                		
                	}
            	}
            }
      });*/
	
	 $('.loadmore').live('click',function(){
		 var  requestUrl=window.location.href;
		 if($('.loadmore').length>0){
     		$('.loadmore').addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
     		if(requestUrl.indexOf('article')!=-1){
             	articleLoadMore($('.loadmore'));
         	}else if(requestUrl.indexOf('books')!=-1){
         		bookLoadMore($('.loadmore'));
         	}else if(requestUrl.indexOf('sites')!=-1){
         		siteLoadMore($('.loadmore'));
         	}else if(requestUrl.indexOf('courses')!=-1){
         		courseLoadMore($('.loadmore'));
         	}else if(requestUrl.indexOf('topics')!=-1){
         		topicLoadMore($('.loadmore'));
         	}
     		
     	}
	 })
	 //关掉弹出层(举报)
    $('.modal-dialog-title-close').live('click',function(){
    	closeReport($(this));
    });

	 //书籍、课程 文章详情  评论 (加载输入框)
	$('.js-toggleCommentBox').live('click',function(){
		if($(this).parents('.media-right').find('.comment-app-holder').length==0){
			var $child=$(this).children();
        	$(this).empty().append($child).append('收起评论');
    		//被评论人id
    	   var recommend=$(this).attr('data-recommend');
    	   //关联主体的id
    	   var relationid=$(this).attr('data-relationid');
    	   //被推荐人的名字
    	   var recommendname=$(this).attr('data-recommendname');
    	   var $children=$(this).children();
    	   //加载模板
    	   var data={
    			   recommend:recommend,
    			   recommendname:recommendname,
    			   relationid:relationid,
    			   iswap:1
    	   }
    	   $('.pagetemplate').setTemplateURL('/bookCommentTemplate.html');
    	   $('.pagetemplate').processTemplate(data);
    	   $(this).parents('.media-right').append($('.pagetemplate').html());
    	   $(".pagetemplate").empty();
		}else{
			var $child=$(this).children();
        	$(this).empty().append($child).append('评论');
        	$(this).parents('.operate').next().remove();
		}
	  
	});
	 //详情界面点击搜索图标  显示搜索框
	 $('#search-icon').live('click',function(){
		 var $search=$(this).parents('body').find('.search-box')
		 if($search.is(':visible')){
			 $search.hide();
		 }else{
			 $search.show();
		 }
	 });
	 //书籍、课程详情--回答里面的评论(评论里面继续评论)
	 $('._InputBox_root_1Xwi').live('focus',function(){
	    	$(this).removeClass('_InputBox_blur_3JWV').parent().addClass('_CommentForm_expanded_39uD');
	 })
	    
	 //书籍、课程详情--回答里面的评论(评论里面继续评论)--取消
	 $('._CommentForm_cancelButton_cy3u').live('click',function(){
	    	answerreplyCancel($(this));
	 })
	 /*//登录名处理
	 if(userInfo!=undefined){
		 if(userInfo.username.replace(/[^x00-xFF]/g,'**').length>9){
			 $('.username a').empty().html(autoAddEllipsis(userInfo.username,9));
		 }
	 }*/
     $('#username').live('click',function(){
    	 $(this).toggle(function(){
    		 $(this).find('i').addClass('z-icon-selfup').removeClass('z-icon-selfdown');
    		 $('#top-nav-profile-dropdown').show();
    		 $('#searches-form-submit').hide();
    	 },function(){
    		 $(this).find('i').removeClass('z-icon-selfup').addClass('z-icon-selfdown');
    		 $('#top-nav-profile-dropdown').hide();
    		 $('#searches-form-submit').show();
    	 })
    	 $(this).trigger('click');
     });
	 $('#pj-fewer').live('click',function(){
		 $('#top-nav-profile-dropdown').hide();
		 $('#username').trigger('click');
	 });
	
	 
})
//书籍里面继续评论  点击取消
function answerreplyCancel(obj){
	var $closecomment=obj.parents('.comment-app-holder').prev().find('.js-toggleCommentBox');
	var $child=$closecomment.children();
	$closecomment.empty().append($child).append('评论');
	obj.parents('._CommentForm_root_1-ko').removeClass('_CommentForm_expanded_39uD');
	obj.parents('._CommentForm_root_1-ko').children(':first').empty().addClass('_InputBox_blur_3JWV')
	obj.parents('.comment-app-holder').remove();
}
//关掉举报弹出层
function closeReport(obj){
	obj.parents('.modal-wrapper').prev().remove();
	obj.parents('.modal-wrapper').remove();
}
//是否为微信浏览器打开
function isWeiXin(){ 
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
	return true; 
	}else{ 
	return false; 
	} 
	} 
//获取用户信息
function getCurrentUser(){
	$.ajax({
		type:"POST",
		url:"/myCenter/getCurrentUser",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.returnStatus=='000'){
				userInfo=data.user;
			}
			else{
				
			}
		}
	
	})
}
function handleFontSize() {
    // 设置网页字体为默认大小
    WeixinJSBridge.invoke('setFontSizeCallback', {

    'fontSize': 0

    });

    // 重写设置网页字体大小的事件
    WeixinJSBridge.on('menu:setfont', function () {

    WeixinJSBridge.invoke('setFontSizeCallback', {

    'fontSize': 0

	});

	});

}
//时间日期转换
function formatDate(str){
	var now=new Date(str);
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var date=now.getDate();
    var hours=now.getHours();
    var minute=now.getMinutes();
    var second=now.getSeconds();
    // year+"年"+fixZero(month,2)+"月"+fixZero(date,2)+"日    "+fixZero(hour,2)+":"+fixZero(minute,2)+":"+fixZero(second,2)
    return  year+"-"+fixZero(month,2)+"-"+fixZero(date,2); 
}
//时间日期转换
function formatDate_hhmmss(str){
	var now=new Date(str);
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var date=now.getDate();
	var hours=now.getHours();
	var minute=now.getMinutes();
	var second=now.getSeconds();
	// year+"年"+fixZero(month,2)+"月"+fixZero(date,2)+"日    "+fixZero(hour,2)+":"+fixZero(minute,2)+":"+fixZero(second,2)
	return  year+"-"+fixZero(month,2)+"-"+fixZero(date,2)+" "+fixZero(hours,2)+":"+fixZero(minute,2)+":"+fixZero(second,2); 
}
function fixZero(num,length){     
	var str=""+num;      
	var len=str.length;     
	var s="";      
	for(var i=length;i-->len;){
		s+="0";     
	}      
	return s+str; 
}
//内容超出截取字符串
function autoAddEllipsis(pStr, pLen) {  
	  
    var _ret = cutString(pStr, pLen);  
    var _cutFlag = _ret.cutflag;  
    var _cutStringn = _ret.cutstring;  
  
    if ("1" == _cutFlag) {  
        return _cutStringn + "...";  
    } else {  
        return _cutStringn;  
    }  
}  
  
/* 
 * 注：半角长度为1，全角长度为2 
 *  
 * pStr:字符串 
 * pLen:截取长度 
 *  
 * return: 截取后的字符串 
 */  
function cutString(pStr, pLen) {  
  
    // 原字符串长度  
    var _strLen = pStr.length;  
  
    var _tmpCode;  
  
    var _cutString;  
  
    // 默认情况下，返回的字符串是原字符串的一部分  
    var _cutFlag = "1";  
  
    var _lenCount = 0;  
  
    var _ret = false;  
  
    if (_strLen <= pLen/2) {  
        _cutString = pStr;  
        _ret = true;  
    }  
  
    if (!_ret) {  
        for (var i = 0; i < _strLen ; i++ ) {  
            if (isFull(pStr.charAt(i))) {  
                _lenCount += 2;  
            } else {  
                _lenCount += 1;  
            }  
  
            if (_lenCount > pLen) {  
                _cutString = pStr.substring(0, i);  
                _ret = true;  
                break;  
            } else if (_lenCount == pLen) {  
                _cutString = pStr.substring(0, i + 1);  
                _ret = true;  
                break;  
            }  
        }  
    }  
      
    if (!_ret) {  
        _cutString = pStr;  
        _ret = true;  
    }  
  
    if (_cutString.length == _strLen) {  
        _cutFlag = "0";  
    }  
  
    return {"cutstring":_cutString, "cutflag":_cutFlag};  
}  
  
/* 
 * 判断是否为全角 
 *  
 * pChar:长度为1的字符串 
 * return: true:全角 
 *          false:半角 
 */  
function isFull (pChar) {  
    if ((pChar.charCodeAt(0) > 128)) {  
        return true;  
    } else {  
        return false;  
    }  
} 
//关注人与取消关注
function topicFollow(obj,objecttype,type,createperson,titleid,titlename){//objecttype 0:用户  1：话题    actionType 1关注，0取消关注    type代表个人主页话题关注列表  
	var objectId,objectNamePg;
	if(objecttype==0){
		objectId=obj.attr('data-userid');	
	}
	else{
		objectId=obj.attr('data-id');
	}
	
	var actiontype=obj.attr('data-actionType');
	$.ajax({
		type:"POST",
		url:"/myCenter/addFollows",
		data:{objectType:objecttype,objectid:objectId,actionType:actiontype,objectNamePg:titlename,objCreatepersonPg:createperson,relationidPg:titleid},
		dataType:"json",
        success:function(data){
        	if(data.returnStatus=='000'){
        	  if(objecttype==1&&type==undefined){
	        		 if(actiontype==1){//取消关注
	          			obj.removeClass('zg-btn-green').addClass('zg-btn-white');
	          			obj.empty().html('取消关注');
	          			obj.attr('data-actiontype','0');
	          			obj.next().find('strong').html(Number(obj.next().find('strong').html())+1);
	          			//添加对应人的头像信息
	          			showHeadIcon();
	          		}
	          		else{
	          			obj.removeClass('zg-btn-white').addClass('zg-btn-green');
	          			obj.attr('data-actiontype','1');
	          			obj.empty().html('关注话题');
	          			obj.next().find('strong').html(Number(obj.next().find('strong').html())-1);
	          			//移除对应的头像信息
	          			deleteHeadIcon();
	          		} 
        	  }
        	  if(objecttype==0&&type==undefined){
        		  if(actiontype==1){//取消关注
            			obj.removeClass('zg-btn-follow').addClass('zg-btn-unfollow');
            			obj.empty().html('取消关注');
            			obj.attr('data-actiontype','0');
            		}
            		else{
            			obj.removeClass('zg-btn-unfollow').addClass('zg-btn-follow');
            			obj.empty().html('+关注');
            			obj.attr('data-actiontype','1');
            		} 
        	  }
        	}
        	else{
        		
        	}
        }
	})
}