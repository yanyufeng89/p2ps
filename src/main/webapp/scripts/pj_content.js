$(function () {
    /*右侧书籍推荐 滚动*/
    if($("#hot-recommend ul li").length!=0){
    	$("#hot-recommend").jCarouselLite({
    		vertical: true,
    		hoverPause:true,
    		visible:7,
    		auto:955,
    		speed:2000
    	});
    }
	
    //左侧导航栏最后一个去掉底线
    $('.wk-all-cate >dl:last').css('border-bottom','0');
    /*首页中间图片滚动start*/  
	var $slider = $("#slide-bner");
    var c = $slider.find(".nav-btn a.pre");
    var u = $slider.find(".nav-btn a.next");
    var len = $(".play-nav > li").length;
    var index = 0;
    $slider.on("mouseenter",
       function () {
           c.css("display", "block"),
           u.css("display", "block")
       }),
    $slider.on("mouseleave",
       function () {
           c.css("display", "none"),
           u.css("display", "none")
       })
    c.on("click", function () {
        index--;
        if (index == -1) { index = len-1; }
        showImg(index);
    }),
    u.on("click", function () {
        index++;
        if (index == len) { index = 0; }
        showImg(index);
    })
    var adTimer;
    $(".play-nav > li").mousemove(function () {
        index = $(".play-nav > li").index(this);
        showImg(index);
    }).eq(0).mousemove();
    $("#slide-bner").hover(function () {
        clearInterval(adTimer);
    }, function () {
        adTimer = setInterval(function () { showImg(index); index++; if (index == len) { index = 0;} }, 3000)
    }
    ).trigger("mouseleave");
    /*首页中间图片滚动end*/  
    
    /*首页右边六个知识库类型自动播放start*/
    var rotateTimer;
    var length=$("#TANGRAM__PSP_7__form .block-cnt>a").length;
    var ex=0;
    $("#TANGRAM__PSP_7__form .block-cnt").hover(function(){
    	clearInterval(rotateTimer);
    },function(){
    	rotateTimer = setInterval(function () { addBorder(ex); ex++; if (ex == length) { ex = 0;} },3000)
    }).trigger("mouseleave");
    /*首页右边六个知识库类型自动播放end*/
    
    /*首页热门话题下面的每一个框框 鼠标移动到每个tab  显示不同的内容 start*/
    $("#SI_Order_U span").mousemove(function(){
    	$(this).addClass("selected").siblings().removeClass("selected");
    	var index=$(this).index();        	
    	$("#SI_Order_U .uni-blk-b > div").eq(index).show().siblings().hide();
    });
    
    $("#SI_Order_S span").mousemove(function(){
    	$(this).addClass("selected").siblings().removeClass("selected");
    	var index=$(this).index();        	
    	$("#SI_Order_S .uni-blk-b > div").eq(index).show().siblings().hide();
    });
    /*首页热门话题下面的每一个框框 鼠标移动到每个tab  显示不同的内容 end*/
   
    //首页界面左侧鼠标移上去加载详情
    $('#wk-all-cate > dl').each(function(a){
    	var explorer =navigator.userAgent;
    	$(this).hover(function(){
    		/*$(this).css('height',$(this).height()-3+'px');
    		$(this).css('width',$(this).width()-1+'px');*/
    		$(this).nextAll('dl').removeClass("current");
    		$(this).nextAll('.menu_sub').addClass("dn");
    		$(this).prev().prev().css('border-bottom','0');
    		switch (a) {
    		 case 0:
    			 $(this).css('border-top','1px solid #d6d6d6');
                 $(this).addClass("current").next().css({
                     top: -1
                 }).removeClass("dn");
                 break;
    		 case 1:
                 $(this).addClass("current").next().css({
                     top:0
                 }).removeClass("dn");
                 break;
             case 2:
                 $(this).addClass("current").next().css({
                     top: $(this).prevAll('dl').height()+23
                 }).removeClass("dn");
                 break;
             case 3:
                 $(this).addClass("current").next().css({
                     top: ($(this).prevAll('dl').height()+23)*2
                 }).removeClass("dn");
                 break;
             case 4:
                 $(this).addClass("current").next().css({
                     top: ($(this).prevAll('dl').height()+23)*3
                 }).removeClass("dn");
                 break;
             case 5:
                 $(this).addClass("current").next().css({
                     top:($(this).prevAll('dl').height()+23)*4
                 }).removeClass("dn");
                 break;
             case 6:
                 $(this).addClass("current").next().css({
                     top: ($(this).prevAll('dl').height()+23)*5
                 }).removeClass("dn");
                 break;
             case 7:
                 $(this).addClass("current").next().css({
                     top: ($(this).prevAll('dl').height()+23)*6
                 }).removeClass("dn");
                 break;
             default:
            	 $('#wk-all-cate').css('border-bottom','0');
                 $(this).addClass("current").next().css({
                		top: ($(this).prevAll('dl').height()+23)*6+13
                 }).removeClass("dn")
    		}
        },function(){
        	if(a==0){
        		 $(this).css('border-top','0');
        	}
        	if(a==8){
        		$('#wk-all-cate').css('border-bottom','1px solid #d6d6d6');
        	}
        	$(this).removeClass("current").next().addClass("dn");
        	$(this).prev().prev().css('border-bottom','1px solid #e9e9e9');
    		/*$(this).css('height',$(this).height()+3+'px');
    		$(this).css('width',$(this).width()-1+'px');*/
        })
       })
       //鼠标移动到分节点  对应的父节点显示
       $('.menu_sub').each(function(a){
    	   $(this).hover(function(){
    		   $(this).removeClass("dn").prev().addClass('current-b');
    		   if(a==0){
    			   $(this).prev().css('border-top','1px solid #d6d6d6');
    			   $(this).removeClass("dn").prev().addClass('current');
    		   }else if(a==8){
    			   $('#wk-all-cate').css('border-bottom','0'); 
    		   }
    		  
    	   },function(){
    		   $(this).addClass("dn").prev().removeClass('current-b');
    		   if(a==0){
    			   $(this).prev().css('border-top','0');
    			   $(this).addClass("dn").prev().removeClass('current');
    		   }else if(a==8){
    			   $('#wk-all-cate').css('border-bottom','1px solid #d6d6d6'); 
    		   }
    		   
    	   });
       })
    /*首页当界面下拉到一定位置出现向上的箭头 start*/
    $(window).scroll(function(){  
        if ($(window).scrollTop()>200){  
            $("#backtop").fadeIn("fast");  
        }  
        else  
        {  
            $("#backtop").fadeOut("fast");  
        }  
    });
   /*首页当界面下拉到一定位置出现向上的箭头 end*/
   
   
   
   //登录
    $("#userlogin").live('click',function(){
		$.ajax({
			type:'POST',
    		url:"/userinfo.txt",
    		contentType: "text/html;charset=UTF-8", 
    		dataType: 'text',	
    		error: function(xhr) { alert("error: " + xhr.responseText); },
    		success:function(data){
    			$("#screen-hd .user-info").empty().html(data);
    		}
    	})
		return false;
	})
	/*首页点击分享按钮界面更新end*/

	//热搜
    $(".hotSearch a").click(function(){
        $("input[name='Condition']").val($.trim($(this).text()));
       $("#searchForm").submit();
    });
    // 采用了防抖动
    /*window.addEventListener('scroll',debounce(realFunc,500))*/; 
    /*window.addEventListener('scroll',realFunc);*/
})
/*function debounce(func, wait, immediate) {
    // 定时器变量
    var timeout;
    return function() {
        // 每次触发 scroll handler 时先清除定时器
        clearTimeout(timeout);
        // 指定 xx ms 后触发真正想进行的操作 handler
        timeout = setTimeout(func, wait);
    };
};
 
// 实际想绑定在 scroll 事件上的 handler
function realFunc(){
    console.log("Success");
}
 */
     
function showImg(index) {
    var lists = $("#slide-bner .img-list").children();           
    for (var i = 0; i < lists.length; i++)
    {
        lists[i].setAttribute("index", i);
    }
    $("#slide-bner .img-list>li").each(function ()
    {
        if (this.getAttribute("index") == index) {
            $(this).css("style", "").css("opacity", "1").css("z-index","9");
        }
        else {
            $(this).css("style", "").css("opacity", "0").css("z-index", "1");
        }
    })
    $(".play-nav > li").removeClass("cur").eq(index).addClass("cur");
}
function addBorder(ex)
{
	var lists = $("#TANGRAM__PSP_7__form .block-cnt").children();
	for (var i = 0; i < lists.length; i++)
    {
        lists[i].setAttribute("index", i);
    }
	
	$("#TANGRAM__PSP_7__form .block-cnt>a").each(function(){
		if(this.getAttribute("index")==ex)
		{
			$(this).addClass("tb-selected");
		}
		else{
			$(this).removeClass("tb-selected");
		}
	})
}

