var userInfo;
(function($){
	var $loading, $wrap,booktype,timers = [], optsArray = [], currentObj, currentOpts = {},
		
		_position = function($ref, $target){
			var scrollTop,
				scrollLeft,
				windowHeight,
				windowWidth,
				refOffset,
				refHeight,
				refWidth,
				targetTop,
				targetLeft,
				targetHeight,
				targetWidth,
				originTop,
				originRight,
				originBottom,
				originLeft,
				arrowPositon,//箭头方向
				arrowSize = 5,
				isPosition = false;
				
			
			scrollTop = $(document).scrollTop();
			scrollLeft = $(document).scrollLeft();
			windowHeight = $(window).height();
			windowWidth = $(window).width();
			refOffset = $ref.offset();
			refHeight = $ref.outerHeight();
			refWidth = $ref.outerWidth();
			targetHeight =160;
			targetWidth =380;
			
			//定位显示的位置
			if(refOffset.top - scrollTop - targetHeight - arrowSize>= 0){//上
				if(windowWidth + scrollLeft - refOffset.left - targetWidth >= 0){//上右
					if(booktype=='1'){
						targetTop = refOffset.top - targetHeight - arrowSize+70;
					}else{
						targetTop = refOffset.top - targetHeight - arrowSize;
					}
					
					targetLeft = refOffset.left-65;
					/*targetLeft = refOffset.left + refWidth + arrowSize;*/				
					isPosition = true;
					arrowPositon = "b";
				}else if(refOffset.left + refWidth -scrollLeft - targetWidth >=0){//上左
					targetTop = refOffset.top - targetHeight - arrowSize;
					targetLeft = refOffset.left + refWidth - targetWidth;
					isPosition = true;
					arrowPositon = "b";
					$wrap.find('.arrow').css({left: 'auto',right: '20px'});
					$wrap.find('.arrow2').css({left: 'auto',right: '20px'});
				}
			}
			
			if(!isPosition){
				if(windowHeight - (refOffset.top + refHeight - scrollTop) - targetHeight - arrowSize >= 0){//下
					if(windowWidth + scrollLeft  - refOffset.left - targetWidth >= 0){//下右
						targetTop = refOffset.top + refHeight + arrowSize;
						targetLeft =refOffset.left-65;
						isPosition = true;
						arrowPositon = "t";
						$('#pj-tooltip').removeClass('top').addClass('bottom');
						$('#pj-booktooltip').removeClass('top').addClass('bottom');
					}else if(refOffset.left + refWidth -scrollLeft - targetWidth >=0){//下左
						targetTop = refOffset.top + refHeight + arrowSize;
						targetLeft = refOffset.left + refWidth - targetWidth;
						isPosition = true;
						arrowPositon = "t";
						$wrap.find('.arrow').css({left: 'auto',right: '20px'});
						$wrap.find('.arrow2').css({left: 'auto',right: '20px'});
						$('#pj-tooltip').removeClass('top').addClass('bottom');
						$('#pj-booktooltip').removeClass('top').addClass('bottom');
					}
				}
			}
			
			
			if(!isPosition){
				if(windowWidth + scrollLeft - refOffset.left - refWidth - targetWidth - arrowSize>= 0){//右
					if(refOffset.top + refHeight - scrollTop - targetHeight >= 0){//右上
						targetTop = refOffset.top + refHeight - targetHeight;
						targetLeft = refOffset.left + refWidth + arrowSize;
						isPosition = true;
						arrowPositon = "l";
						$wrap.find('.arrow').css({top: 'auto', bottom: '20px'});
					}else if(refOffset.top + windowHeight - scrollTop - targetHeight>= 0){//右下
						targetTop = refOffset.top;
						targetLeft = refOffset.left + refWidth + arrowSize;
						isPosition = true;
						arrowPositon = "l";
					}
				}
			}
			
			if(!isPosition){
				if(refOffset.left - scrollLeft - targetWidth - arrowSize>=0){//左
					if(windowHeight - (refOffset.top - scrollTop) - targetHeight >= 0){//左下
						targetTop = refOffset.top;
						targetLeft = refOffset.left - targetWidth -arrowSize;
						isPosition = true;
						arrowPositon = "r";
						
					}else if(refOffset.top + refHeight - scrollTop - targetHeight >= 0){//左上
						targetTop = refOffset.top + refHeight - targetHeight;
						targetLeft = refOffset.left - targetWidth - arrowSize;
						isPosition = true;
						arrowPositon = "r";
						
					} 
				}
			}
			
			if(!isPosition){
				//特殊情况定位(非最大化情况下)
				//计算原点与浏览器视窗边缘的距离
				originTop = refOffset.top - scrollTop + refHeight/2;
				originBottom = windowHeight - originTop;
				originLeft = refOffset.left - scrollLeft + refWidth/2;
				originRight = windowWidth - originLeft;
				
				if(originTop >= originBottom ){
					if(originRight >= originLeft){
						if(originTop < targetHeight && originRight >= targetWidth){//右上
							targetTop = refOffset.top + refHeight - targetHeight;
							targetLeft = refOffset.left + refWidth + arrowSize;	
							arrowPositon = "l";
							$wrap.find('.arrow').css({top: 'auto', bottom: '20px'});
						}else{//上右
							targetTop = refOffset.top - targetHeight - arrowSize;
							targetLeft = refOffset.left;
							arrowPositon = "b";
						}
					}else{
						if(originTop < targetHeight && originLeft >= targetWidth){//左上
							targetTop = refOffset.top + refHeight - targetHeight;
							targetLeft = refOffset.left - targetWidth - arrowSize;
							arrowPositon = "r";
							$wrap.find('.arrow').css({top: 'auto', bottom: '20px'});
							
						}else{//上左
							targetTop = refOffset.top - targetHeight - arrowSize;
							targetLeft = refOffset.left + refWidth - targetWidth;
							arrowPositon = "b";
							$wrap.find('.arrow').css({left: 'auto',right: '20px'});
						}
					}
				}else{
					if(originRight >= originLeft){
						if(originBottom < targetHeight && originRight >= targetWidth){//右下
							targetTop = refOffset.top;
							targetLeft = refOffset.left + refWidth + arrowSize;		
							arrowPositon = "l";
						}else{//下右
							targetTop = refOffset.top + refHeight + arrowSize;
							targetLeft = refOffset.left;
							arrowPositon = "t";
						}
					}else{
						if(originBottom < targetHeight && originLeft >= targetWidth){//左下
							targetTop = refOffset.top;
							targetLeft = refOffset.left - targetWidth - arrowSize;
							arrowPositon = "r";
						}else{//下左
							targetTop = refOffset.top + refHeight + arrowSize;
							targetLeft = refOffset.left + refWidth - targetWidth;
							arrowPositon = "t";
							$wrap.find('.arrow').css({left: 'auto',right: '20px'});
						}
					}
				}
				
				isPosition = true;	
			}
			
			$wrap.find('.arrow').removeClass().addClass('arrow arrow_' + arrowPositon);
			
			if(isPosition){
				$target.css({
					top: targetTop,
					left: targetLeft
				});
			}

		},
		
		_appendContent = function(){
			var type, href, data, content,userid,bookid,ajaxLoader,moduletype;
					if (typeof($wrap) != "undefined"){
						$wrap.html('');
					}else{	
						$('body').append('<div class="pinwheel_wrap"></div>');
						$wrap = $('.pinwheel_wrap');
					}
					
					$wrap.html('<div class="pinwheel_layer"><div class="bg"><div class="effect"><div class="pinwheel_content"></div><div class="arrow"></div></div></div></div>');
					$wrap.find('.pinwheel_content').append($loading);
					userid=$(currentObj).attr('data-userid');
					booktype=$(currentObj).attr('data-booktype');
					moduletype=$(currentObj).attr('data-moduletype');//0代表话题   1代表书籍
					bookid=$(currentObj).attr('data-bookid');
					if(booktype=='1'){//得到书籍的详情
						  ajaxLoader = $.ajax({
								type:'POST',
								url:"/books/getBookSimpleInfo",
								data:{id:bookid},
								dataType:"json",
				        	    success:function(data){
				        	    	if(data.returnStatus=='000'){
				        	    		var bookmodel={
					        	    		    bookSimpleInformation:data.obj,
					        	    		    moduletype:moduletype
					        	    		}
				        	    		$('.headiconintotem').setTemplateURL(projectName+'bookInfoTemplate.html');
				        	    		$('.headiconintotem').processTemplate(bookmodel);
				        	    		$wrap.find('.pinwheel_content').html($('.headiconintotem').html());
				        	    		$('.headiconintotem').empty();
				        	    		_position($(currentObj), $wrap);//定位
				        	    	}
				        	    	else{
				        	    		console.log('ajax error');
				        	    	}
				        	    }
							});
					}
					else{//得到用户信息的详情
					   intoUserInfo();
					   ajaxLoader = $.ajax({
							type:'POST',
							url:"/myCenter/getUserSimpleInformation?userId="+userid,
							dataType:"json",
			        	    success:function(data){
			        	    	if(data.returnStatus=='000'){
			        	    		if(data.user.fansIds!=undefined){
			        	    			if(data.user.fansIds.indexOf(',')!=-1){
			        	    				if($.inArray(String(data.currentUserid), data.user.fansIds.split(','))!=-1){
			        	    					data.user.fansIds=0;
			            					}else{
			            						data.user.fansIds=1;
			            					}
			        	    			}else{
			        	    				if(data.user.fansIds==data.currentUserid){
			        	    					data.user.fansIds=0;
			        	    				}else{
			        	    					data.user.fansIds=1;
			        	    				}
			        	    				
			        	    			}
			        	    		}else{
			        	    			data.user.fansIds=1;
			        	    		}
			        	    		if(data.user.description!=undefined){
			        	    			if(data.user.description.length>22){
				        	    			data.user.description=data.user.description.substring(0,22)+'...'
				        	    		}
			        	    		}
			        	    		
			        	    		var datamodel={
			        	    		    userSimpleInformation:data.user,
			        	    		    userid:userid,
			        	    		    cutuserid:userInfo==undefined?'':userInfo.userid,
			        	    		}
			        	    		//加载模板
			        	    		$('.pagetemplate').setTemplateURL(projectName+'headIconInfoTemplate.html');
			        	    		$('.pagetemplate').processTemplate(datamodel);
			        	    		$wrap.find('.pinwheel_content').html($('.pagetemplate').html());
			        	    		$('.pagetemplate').empty();
			        	    		_position($(currentObj), $wrap);//定位
			        	    	}
			        	    	else{
			        	    		console.log('ajax error');
			        	    	}
			        	    }
						});
					}

		},		
		//清除所有定时器 ps:之前没考虑到会产生多个定时器,差点被搞死...
		_clearTimer = function(){
			for(var i=0; i<timers.length; i++){
				if(timers[i]){
					clearInterval(timers[i]);
				}
			}
			timers = [];
		},
		
		_debug = function($obj){
			if(window.console && window.console.log){
				window.console.log("pinwheel count :" +$obj.size());
			};
		};
		
	$.fn.pinwheel = function(options){
		//_debug(this);
		var opts = $.extend({}, $.fn.pinwheel.defaults, options);
		
		return this.each(function(){	
								  
			optsArray.push({obj:this, opts:opts});
			
			$(this).bind("mouseover",function(e){
				e.stopPropagation();
				_clearTimer();	
				
				
				if(currentObj && currentObj == this){			
					_position($(this), $wrap);//定位
					$wrap.show();
				}else{
					currentObj = this;	
					_appendContent();//为容器添加内容		
					_position($(this), $wrap);//定位
					$wrap.show();

					$wrap.unbind().bind('mouseover', function(e){							  
						e.stopPropagation();
						_clearTimer();
					});

					$(document).bind("mouseout",function(e){
						e.stopPropagation();
						_clearTimer();																	
						var timer = setInterval(function(){
							$wrap.hide();
							_clearTimer();
						},50);	
						timers.push(timer);
					})
					
				}
			});


			
		});
	};
	
	$.fn.pinwheel.defaults = {

	};
})(jQuery);