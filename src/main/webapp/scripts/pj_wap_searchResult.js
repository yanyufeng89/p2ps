$(function(){
	var  requestUrl=window.location.href;
	//搜索界面 加载更多
	$(window).scroll(function() {
	 if(requestUrl.indexOf('index')!=-1||requestUrl.indexOf('search')!=-1){
		 var scrollTop = $(this).scrollTop(),scrollHeight = $(document).height(),windowHeight = $(this).height();  
	     var positionValue = (scrollTop + windowHeight) - scrollHeight;  
    	 if (positionValue<=0) {  
	    	  $('.search-loadmore').addClass('loading').empty().append("<span class='capture-loading'></span>加载中...");
	    	  loadMore('search-loadmore');
	     }  
	 }
    });
	//6大类选择
	$('#search-classify li').live('click',function(){
		$(this).addClass('active').siblings().removeClass('active');
		reloadPage();
	});
	
	//搜索内容
	$('#searches-form-submit').live('click',function(){
		reloadPage();
	})
})
//加载更多
function loadMore(types){
	$('.'+types)
	var type=$('.'+types).attr('data-type');
	var pageNo=$('.'+types).attr('data-pageno');
	var sumpage=$('.'+types).data('sumpage');
	var Condition='';
	var protoType ='';
	if(type==0){
		Condition='';
		protoType=0;
	}else{
		 Condition = $("#searchres_input").val();
		 protoType = $("#search-classify li.active").attr("data-objtype");
	}
	var sharedType= 0;
	var tags = "";
	var pages = "";
	var $this=$('.'+types);
	$.ajax({
		type:"POST",
		url:"/search/" + sharedType,
		//搜索页标从0开始
		data: {Condition:Condition, sharedType:sharedType, protoType:protoType, tags:tags, pages:Number(pageNo)+1},
		dataType:"json",
		ansync:false,
		success:function(data){	
			var array = eval(data.result);
			var datamodel={
				result:array,
			}
			//加载模板
			$('.pagetemplate').setTemplateURL('/searchWapTemplate.html');
			$('.pagetemplate').processTemplate(datamodel);
			if(type==0){
			  $('.index-loadmore').prev().append($('.pagetemplate').html());
			  $('.index-loadmore').attr('data-pageno',Number(pageNo)+1);
			}else{
			  $('.search-loadmore').prev().append($('.pagetemplate').html());
			  $('.search-loadmore').attr('data-pageno',Number(pageNo)+1);
			}
			$('.pagetemplate').empty();
			
			$this.removeClass('loading').empty().append('更多');
      	    if(Number(sumpage)==Number(pageNo)+1){
      	    	if(type==0){
      		       $('.index-loadmore').hide(); 
      	    	}else{
      	    		$('.search-loadmore').hide(); 
      	    	}
      	    }
			$('.item-content a').each(function(){
			    $(this).text(autoAddEllipsis($(this).text(),80));
			})
			$this.removeClass('loading').empty().append('更多');
		}
	})
}
function reloadPage() {
    var Condition = $("#searchres_input").val();
    var protoType = $("#search-classify li.active").attr("data-objtype")==undefined?'':$("#search-classify li.active").attr("data-objtype");
    var sharedType = 0;
   
    var uurl = encodeURI("/search/" + sharedType + "?Condition=" + Condition + "&protoType=" + protoType);
    
    window.location.href = uurl;


}