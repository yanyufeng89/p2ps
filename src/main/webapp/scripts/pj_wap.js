var load_flag=false;
$(function(){
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
         	}
     	}
	 })
	
  
  
    
	//展示全部
	$('.showall').live('click',function(){
		
	});
})



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