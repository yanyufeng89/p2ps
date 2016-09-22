var userInfo;
$(function(){   
	 /*个人中心左侧start*/
	var Accordion = function(el,multiple) {
		this.el = el || {};
		this.multiple = multiple || false;
		var links = this.el.find('.link');
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}
	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this),
			$next = $this.next();
		$next.slideToggle();
		$this.parent().toggleClass('open');
		if(!$this.parent().hasClass('open'))
		{  
			$this.parent().find('.doc-arrow-btn').html('');
		}
		if (!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
			$el.find('.doc-arrow-btn').html('');
			if($this.parent().hasClass('open'))
			$this.parent().find('.doc-arrow-btn').html('');
		};
	}	
	var accordion = new Accordion($('#accordion'),false);
	/*个人中心左侧end*/
	
	//头像信息
	intoUserInfo();
	
	//我的粉丝里面  粉丝数 与互粉
	$("#fanscontent .fansleft span").click(function(){
		$(this).addClass('current').siblings().removeClass('current');
		$("#fanspeople>div").eq($(this).index()).show().siblings().hide();
	})
	
    
	//我的关注
	$("#share ul li a").on('click',function(){
		if($(this).hasClass("tb-selected"))
            $(this).removeClass("tb-selected").children('i').remove();
    	else if($(this).children('i').length==0)
    		$(this).append("<i></i>").addClass("tb-selected").parents().siblings().find('a').removeClass("tb-selected").children('i').remove();
		$(".content>div").eq($(this).parent().index()+1).show().siblings().hide();
	})
	
    //我的文档  全选或者反选
    $('.select-all').live('click',function(){
    	if($(this).hasClass('select-icon')){
    		$(this).removeClass('select-icon');
        	$(this).parents('.status-box').prev().find('.remove').removeClass('enabled');
        	$(this).parents('.status-box').next().find('.checkbox').each(
            		function(){
            			$(this).removeClass('select-icon');
            		}
            );
    	}else{
    		$(this).addClass('select-icon');
        	//删除加重显示
        	$(this).parents('.status-box').prev().find('.remove').addClass('enabled');
        	$(this).parents('.status-box').next().find('.checkbox').each(
        		function(){
        			$(this).addClass('select-icon');
        		}
        	);
    	}
    });
    
    //单独一个点击文本框 选中或者反选
    $('.chk').live('click',function(){
    	if($(this).hasClass('select-icon')){
    		$(this).removeClass('select-icon');
    		$(this).parents('.docs-list').prev().find('.checkbox').removeClass('select-icon');
    		//一个个点击去掉选中的时候   当最后一个去点选中之后  最上面的删除变灰色
    		if($(this).parents('ul').find('.select-icon').length==0)
    			$(this).parents('.docs-list').prevAll('.delete-box').find('.remove').removeClass('enabled');
    	}
    	else{
    		$(this).addClass('select-icon');
    		//当选中的大于等于2个 就可以批量删除
    		if($(this).parents('ul').find('.select-icon').length>=2)
    			$(this).parents('.docs-list').prevAll('.delete-box').find('.remove').addClass('enabled');
    	}
    });
    //粉丝列表 关注列表 最近访问  点击关注 与取消关注
    $('.maincontent .zg-btn,#zh-profile-current-visit-list .zg-btn').live('click',function(){
    	addFollows($(this));
    })
    //关注人
    $('.operation .zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })

    //最近访问列表   加载更多
    $('.pj-load-more').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	visitorLoadMore($(this));
    	$(this).removeClass('loading').empty().append('更多');
    });
    //个人中心 点击每个模块的我关注的人  相应的下面加载数据
    $('#myselfattenman').live('click',function(){
    	initSelfAttenMan($(this));
    })
    //个人中心 点击每个模块的我粉丝  相应的下面加载数据
    $('#myselffans').live('click',function(){
    	initSelfFans($(this));
    })
}) 
//个人中心 点击每个模块的我关注的人  相应的下面加载数据
function initSelfAttenMan(obj){
	var userid=obj.attr('data-userid');
	$.ajax({
		type:"POST",
		url:projectName+"myCenter/getMyAttenMan",
		data:{userid:userid},
		dataType:"json",
		success:function(data){
			$.each(data.attenManPage.list,function(index,item){
				item.fansIds=1;
			})
			var model={
					 attenManPage:data.attenManPage.list,
			}
			 //加载他关注的人的数据模板
			 $('.pagetemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
			 $('.pagetemplate').processTemplate(model);
			 $('.maincontent').empty().append($('.pagetemplate').html());
			 $('.pagetemplate').empty();
			 if($('.page-inner').length>0){
				 $('.page-inner').remove();
			 }
			 if(data.attenManPage.last>1){
				 //有分页的时候加载分页标签
				 $('.maincontent').after(initPagingHtml('myattenpaging'));
			 }
			 //加载分页
	        $.getScript('/51jobplusCore/scripts/jquery.simplePagination.js',function(){
	    			$("#myattenpaging").pagination({
	    				items:data.attenManPage.count,
	    				itemsOnPage:data.attenManPage.pageSize,
	    				cssStyle:'light-theme',
	    				moduleType:'myattenlist'
	    			})
	    		});
	        $('.maincontent').css('padding','0 16px 0 16px');
	        intoUserInfo();
		}
	})
}
//加载分页标签的html
function initPagingHtml(tag){
	var htm='';
	htm+='<div class="page-inner">'
	htm+='  <div class="ui-pager pager-center">'
	htm+='     <div class="pager">'
	htm+='       <div class="pager-inner">'	
	htm+='           <div id='+tag+' class="page"></div>'
	htm+='       </div>'
	htm+='    </div>'
	htm+=' </div>'
    htm+='</div>'
	return htm
}
//个人中心 点击每个模块的我粉丝  相应的下面加载数据
function initSelfFans(obj){
	var userid=obj.attr('data-userid');
	$.ajax({
		type:"POST",
		url:projectName+"myCenter/getMyFans",
		data:{userid:userid},
		dataType:"json",
		success:function(data){
			$.each(data.myFansPage.list,function(index,item){
    			if(item.fansIds!=undefined){
    				if(item.fansIds.indexOf(',')!=-1){
    					if($.inArray(String(userInfo.userid), item.fansIds.split(','))!=-1){
    						item.fansIds=1;
    					}else{
    						item.fansIds=0;
    					}
    				}else{
    					if(item.fansIds==String(userInfo.userid)){
    						item.fansIds=1;
    					}else{
    						item.fansIds=0;
    					}
    					
    				}
	    		}else{
	    			item.fansIds=0;
	    		}
             }) 
             
			 var model={
					 attenManPage:data.myFansPage.list,
			 }
			 //加载他的粉丝数据模板
			 $('.pagetemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
			 $('.pagetemplate').processTemplate(model);
			 $('.maincontent').empty().append($('.pagetemplate').html());
			 $('.pagetemplate').empty(); 
			 if($('.page-inner').length>0){
				 $('.page-inner').remove();
			 }
			 if(data.myFansPage.last>1){
				 //有分页的时候加载分页标签
				 $('.maincontent').after(initPagingHtml('myfanspaging'));
			 }
			 //加载分页
	        $.getScript('/51jobplusCore/scripts/jquery.simplePagination.js',function(){
	    			$("#myfanspaging").pagination({
	    				items:data.myFansPage.count,
	    				itemsOnPage:data.myFansPage.pageSize,
	    				cssStyle:'light-theme',
	    				moduleType:'myfanslist'
	    			})
	    	});
	        $('.maincontent').css('padding','0 16px 0 16px');
	        intoUserInfo();
		  }
		})
}
//我的最近访问加载更多
function visitorLoadMore(obj){
	    var pageNo=obj.attr('data-pageno');
	    var sumpage=obj.data('sumpage');
	    var userid=obj.data('data-userid');
	    $.ajax({
	    	type:"POST",
	      	url:projectName+"myHome/moreRecentVistors",
	      	data:{pageNo:Number(pageNo)+1,userid:userid},
	    	dataType:"json",
	    	success:function(data){
	    		$.each(data.visitors.list,function(index,item){
	    			if(item.fansIds!=undefined){
 	    			if(item.fansIds.indexOf(',')!=-1){
 	    				if($.inArray(String(userInfo.userid), item.fansIds.split(','))!=-1){
    						item.fansIds=1;
    					}else{
    						item.fansIds=0;
    					}
 	    			}
 	    			else{
 	    				if(item.fansIds==userInfo.userid){
 	    					item.fansIds=1;
 	    				}
 	    				else{
 	    					item.fansIds=0;
 	    				}
 	    			}
 	    		}
 	    		else{
 	    			item.fansIds=0;
 	    		}
	    		})
				var datamodel={
				   concernlist:data.visitors.list,
				}
			   $('.zm-profile-followee-page').removeAttr("style");
			   $('.pagetemplate').setTemplateURL(projectName+'visitLoadMoreTemplate.html');
	      	   $('.pagetemplate').processTemplate(datamodel);
	      	   $('.pj-load-more').prev().append($('.pagetemplate').html());
	      	   $(".pagetemplate").empty();
	      	   $('.pj-load-more').attr('data-pageno',Number(pageNo)+1)
	      	   if(Number(sumpage)==Number(pageNo)+1)
	      		 $('.pj-load-more').hide();
	      	   intoUserInfo(); 
	    	}
	    })
	   
}
 //我的关注列表 
 function addFollows(obj){
	 var userid=obj.attr('data-userid');
	 //actionType 1关注，0取消关注
	 var actiontype=obj.attr('data-actionType');
	 $.ajax({
			type:"POST",
			url:projectName+"myCenter/addFollows",
			data:{objectType:'0',objectid:userid,actionType:actiontype},
			dataType:"json",
	        success:function(data){
	        	if(data.returnStatus=='000'){
		        		 if(actiontype==1){//取消关注
		          			obj.removeClass('zg-btn-follow').addClass('zg-btn-unfollow');
		          			obj.empty().html('取消关注');
		          			obj.attr('data-actiontype','0');
		          		}
		          		else{
		          			obj.removeClass('zg-btn-unfollow').addClass('zg-btn-follow');
		          			obj.attr('data-actiontype','1');
		          			obj.empty().html('关注');
		          		} 
	        	  }
	        }
		})
   }


	//加载用户信息
	function intoUserInfo(){
		$('.author-link,.zm-item-link-avatar').pinwheel();
	}

