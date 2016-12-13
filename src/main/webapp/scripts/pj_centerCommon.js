
$(function(){
	 //关注与取消关注人
    $('.detail-list .zg-btn,.profile-func .zg-btn,#cm-profile-follows-list .zg-btn,#zh-profile-follows-list .zg-btn').live('click',function(){
    	addFollows($(this));
    	var actiontype=$(this).attr('data-actiontype');
    	var userid=$(this).attr('data-userid');
    	//当前区域同一用户变成相同状态
    	$('.zg-btn').each(function(){
    		if($(this).attr('data-userid')==userid){
    			if(actiontype==1){
    				$(this).removeClass('zg-btn-follow').addClass('zg-btn-unfollow');
    				$(this).empty().html('取消关注');
    				$(this).attr('data-actiontype','0');
    			}else{
    				$(this).removeClass('zg-btn-unfollow').addClass('zg-btn-follow');
    				$(this).empty().html('+&nbsp;关注');
    				$(this).attr('data-actiontype','1');
    			}
    		}
    	})
    })
    //删除技能或技能
    $('a[name=removeskill]').live('click',function(){
    	$(this).parent().remove();
    })
    
})

//更新数据库中对应的字段
function updateColumn(val,column){
	$.ajax({
		type:'POST',
	    url:"/myHome/updUserInfo",
	    data:column+val,
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		
	    	}
	    	else{
	    		
	    	}
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
			url:"/myCenter/addFollows",
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
		          			obj.empty().html('+&nbsp;关注');
		          		} 
	        	  }
	        }
		})
}
//最近访问加载更多
function moreVisitor(obj){
	var userid=obj.attr('data-userid');
	var pageNo=obj.attr('data-pageno');
	var sumpage=obj.data('sumpage');
    $.ajax({
    	type:"POST",
      	url:"/myHome/moreRecentVistors",
      	data:{pageNo:Number(pageNo)+1,userid:userid},
    	dataType:"json",
    	success:function(data){
    		$.each(data.visitors.list,function(index,item){
    			if(item.fansIds!=undefined){
	    			if(item.fansIds.indexOf(',')!=-1){
	    				if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.fansIds.split(','))!=-1){
						item.fansIds=1;
					}else{
						item.fansIds=0;
					}
	    			}
	    			else{
	    				if(item.fansIds==(userInfo==undefined?'':userInfo.userid)){
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
    		$('.pagetemplate').setTemplateURL(projectName+'visitLoadMoreTemplate.html');
	      	$('.pagetemplate').processTemplate(datamodel);
	      	if($('#zh-profile-follows-list .zh-general-list').length>0){
	      		$('#zh-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
	      	}else{
	      		$('#cm-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
	      	}
    		
			$('.pagetemplate').empty();				
   	        $('.pjvisitor-load-more').attr('data-pageno',Number(pageNo)+1);
     	       obj.removeClass('loading').empty().append('更多');
     	     if(Number(sumpage)==Number(pageNo)+1)
     		   $('.pjvisitor-load-more').hide();
     	    intoUserInfo();
    	}
      	
    })
}
//他的粉丝和他的关注加载更多
function loadMoreFansAndAttenData(obj){
	var isfans=obj.attr('data-isfans');
	var userid=obj.attr('data-userid');
	var pageNo=obj.attr('data-pageno');
	var sumpage=obj.data('sumpage');
	if(isfans=='1'){
		$.ajax({
			type:"POST",
			url:"/myCenter/getMyFans",
			data:{userid:userid,pageNo:Number(pageNo)+1},
			dataType:"json",
			ansyc:false,
	        success:function(data){
	        	$.each(data.myFansPage.list,function(index,item){
	    			if(item.fansIds!=undefined){
	    				if(item.fansIds.indexOf(',')!=-1){
	                        if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.fansIds.split(','))!=-1){
	    						item.fansIds=1;
	    					}else{
	    						item.fansIds=0;
	    					}
	    				}else{
	    					if(item.fansIds==(userInfo==undefined?'':userInfo.userid)){
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
				 if($('#zh-profile-follows-list .zh-general-list').length>0){
					 $('#zh-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
				 }else{
					 $('#cm-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
				 }

				 $('.pagetemplate').empty();				
        	     $('.pjfans-load-more').attr('data-pageno',Number(pageNo)+1);
          	       obj.removeClass('loading').empty().append('更多');
          	     if(Number(sumpage)==Number(pageNo)+1)
          		   $('.pjfans-load-more').hide();
          	     intoUserInfo();
	        }
		})
	}else{
		$.ajax({
			type:"POST",
			url:"/myCenter/getMyAttenMan",
			data:{userid:userid,pageNo:Number(pageNo)+1},
			dataType:"json",
			ansyc:false,
	        success:function(data){
	        	$.each(data.attenManPage.list,function(index,item){
	    			if(item.fansIds!=undefined){
	    				if(item.fansIds.indexOf(',')!=-1){
	    					if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.fansIds.split(','))!=-1){
	    						item.fansIds=1;
	    					}else{
	    						item.fansIds=0;
	    					}
	    				}else{
	    					if(item.fansIds==(userInfo==undefined?'':userInfo.userid)){
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
						 attenManPage:data.attenManPage.list,
				 }
				 //加载他关注的人的数据模板
				 $('.pagetemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
				 $('.pagetemplate').processTemplate(model);
				 if($('#zh-profile-follows-list .zh-general-list').length>0){
				    $('#zh-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
				 }else{
					$('#cm-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
				 }
				 $('.pagetemplate').empty();
	        	 $('.pjatten-load-more').attr('data-pageno',Number(pageNo)+1);
	          	   obj.removeClass('loading').empty().append('更多');
	          	   if(Number(sumpage)==Number(pageNo)+1)
	          		 $('.pjatten-load-more').hide();
	          	 intoUserInfo();
	        }
		})
	}
}
//加载更多分享的6大类的数据
function loadMoreData(obj){
	var tablename=obj.attr('data-tablename');
	var tablecolumn=obj.attr('data-tablecolumn');
	var tablecolumn2=obj.attr('data-tablecolumn2');
    var pageNo=obj.attr('data-pageno');
	var userid=obj.attr('data-userid');
	var sumpage=obj.data('sumpage');
	$.ajax({
		type:"POST",
		url:"/myHome/getOneShares",
		data:{userid:userid,tableName:tablename,tableColumn:tablecolumn,pageNo:Number(pageNo)+1,tableColumn2:tablecolumn2},
		dataType:"json",
        success:function(data){
        	   //当点击他的分享的时候  个人信息  
        	   $('#zh-profile-answers-inner-list ul').append(loadMoreHtml(data.shares.list,convertCh(tablename),tablename));
        	   $('.pj-load-more').attr('data-pageno',Number(pageNo)+1);
          	   obj.removeClass('loading').empty().append('更多');
          	   if(Number(sumpage)==Number(pageNo)+1)
          		 $('.pj-load-more').hide();
        }
	})
}
//他的粉丝
function otherFans(obj){
	var userid=obj.attr('data-userid');
	$.ajax({
		type:"POST",
		url:"/myCenter/getMyFans",
		data:{userid:userid},
		dataType:"json",
		success:function(data){
			if(data.myFansPage.list.length==0){
				if($('#cm-profile-follows-list .zh-general-list').length>0){
					$('#cm-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无信息</span>").show();
				}else{
					$('#zh-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无信息</span>").show();
				}
				$('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
			}else{
				$.each(data.myFansPage.list,function(index,item){
	    			if(item.fansIds!=undefined){
	    				if(item.fansIds.indexOf(',')!=-1){
	    					if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.fansIds.split(','))!=-1){
	    						item.fansIds=1;
	    					}else{
	    						item.fansIds=0;
	    					}
	    				}else{
	    					if(item.fansIds==(userInfo==undefined?'':userInfo.userid)){
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
				 $('.homepageTemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
				 $('.homepageTemplate').processTemplate(model);
				 if($('#cm-profile-follows-list .zh-general-list').length>0){
					 $('#cm-profile-follows-list .zh-general-list').empty().append($('.homepageTemplate').html());
				 }else{
					 $('#zh-profile-follows-list .zh-general-list').empty().append($('.homepageTemplate').html());
				 }
				
				 $('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
				 $('.homepageTemplate').empty();
				 
				
				 //判断当前是否有加载更多
				 if(data.myFansPage.last>1){
					 var mhtml='<a href="javascript:void(0);" data-pageno="1" data-isfans="1" data-userid='+data.user.userid+' data-sumpage='+data.myFansPage.last+' class="zg-btn-white zg-r3px zu-button-more pjfans-load-more">更多</a>'  
				 }
				 if($('#cm-profile-follows-list').length>0){
					 $('#cm-profile-follows-list').show();
					 $('#cm-profile-follows-list').append(mhtml);
				 }else{
					 $('#zh-profile-follows-list').show();
					 $('#zh-profile-follows-list').append(mhtml);
				 }

				 intoUserInfo();
			}
			 //显示当前层 隐藏其他层
			 //隐藏个人信息
			 $('.section-container,#contact-info-section,.zm-profile-section-wrap,#profile-navbar').hide();
			 $('#fans-navbar').show();
			 $('#personal-attention').addClass('current').siblings().removeClass('current');
			 $('#fans-navbar .pjitem:eq(1)').addClass('current').siblings().removeClass('current');
		}
	})
}
//他关注的人
function otherAtten(obj){
	var userid=obj.attr('data-userid');
	$.ajax({
		type:"POST",
		url:"/myCenter/getMyAttenMan",
		data:{userid:userid},
		dataType:"json",
		success:function(data){
			if(data.attenManPage.list.length==0){
				 if($('#cm-profile-follows-list .zh-general-list').length>0){
					 $('#cm-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无信息</span>").show();
				 }else{
					 $('#zh-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无信息</span>").show();
				 }
				 $('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
			}else{
				$.each(data.attenManPage.list,function(index,item){
	    			if(item.fansIds!=undefined){
	    				if(item.fansIds.indexOf(',')!=-1){
	    					if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.fansIds.split(','))!=-1){
	    						item.fansIds=1;
	    					}else{
	    						item.fansIds=0;
	    					}
	    				}else{
	    					if(item.fansIds==(userInfo==undefined?'':userInfo.userid)){
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
						 attenManPage:data.attenManPage.list,
				 }
				 //加载他关注的人的数据模板
				 $('.homepageTemplate').setTemplateURL('/otherAttenAndFansTemplate.html');
				 $('.homepageTemplate').processTemplate(model);
				 if($('#cm-profile-follows-list .zh-general-list').length>0){
					 $('#cm-profile-follows-list .zh-general-list').empty().append($('.homepageTemplate').html());
				 }else{
					 $('#zh-profile-follows-list .zh-general-list').empty().append($('.homepageTemplate').html());
				 }
				
				 $('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
				 $('.homepageTemplate').empty();
				 
				 //判断当前是否有加载更多
				 if(data.attenManPage.last>1){
					 var mhtml='<a href="javascript:;" data-pageno="1"  data-isfans="0" data-userid='+data.user.userid+' data-sumpage='+data.attenManPage.last+' class="zg-btn-white zg-r3px zu-button-more pjatten-load-more">更多</a>'  
				 }
				 if($('#cm-profile-follows-list').length>0){
					 $('#cm-profile-follows-list').append(mhtml);
					 $('#cm-profile-follows-list').show(); 
				 }else{
					 $('#zh-profile-follows-list').append(mhtml);
					 $('#zh-profile-follows-list').show();
				 }

				 intoUserInfo();
			}
			 //显示当前层 隐藏其他层
			 //隐藏个人信息
			 $('.section-container,#contact-info-section,.zm-profile-section-wrap,#profile-navbar').hide();
			 $('#fans-navbar').show();
			 $('#personal-attention').addClass('current').siblings().removeClass('current');
			 $('#fans-navbar .pjitem:eq(0)').addClass('current').siblings().removeClass('current');
		}
		
	})
}
//最近访问
function currVisitor(obj){
	var userid=obj.attr('data-userid');
	$.ajax({
		type:"POST",
		url:"/myHome/moreRecentVistors",
		data:{userid:userid},
		dataType:"json",
		success:function(data){
			if(data.visitors.list.length==0){
				 if($('#cm-profile-follows-list .zh-general-list').length>0){
					 $('#cm-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无访问信息</span>").show();
				 }else{
					 $('#zh-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无访问信息</span>").show();
				 }
				 $('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
			}else{
				$.each(data.visitors.list,function(index,item){
	    			if(item.fansIds!=undefined){
		    			if(item.fansIds.indexOf(',')!=-1){
		    				if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.fansIds.split(','))!=-1){
							item.fansIds=1;
						}else{
							item.fansIds=0;
						}
		    			}
		    			else{
		    				if(item.fansIds==(userInfo==undefined?'':userInfo.userid)){
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
				$('.homepageTemplate').setTemplateURL('/visitLoadMoreTemplate.html');
		        $('.homepageTemplate').processTemplate(datamodel);
		        if($('#cm-profile-follows-list .zh-general-list').length>0){
		        	$('#cm-profile-follows-list .zh-general-list').empty().append($('.homepageTemplate').html());
		        }else{
		        	$('#zh-profile-follows-list .zh-general-list').empty().append($('.homepageTemplate').html());
		        }
		        
				$('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
				$('.homepageTemplate').empty();
				 
				 //判断当前是否有加载更多
				 if(data.visitors.last>1){
					 var mhtml='<a href="javascript:;" data-pageno="1"   data-userid='+userid+' data-sumpage='+data.visitors.last+' class="zg-btn-white zg-r3px zu-button-more pjvisitor-load-more">更多</a>'  
				 }
				 if($('#cm-profile-follows-list').length>0){
					 $('#cm-profile-follows-list').append(mhtml);
				 }else{
					 $('#zh-profile-follows-list').append(mhtml);
					 $('#zh-profile-follows-list').show();
				 }
				 
				 intoUserInfo();
			}
			
		 }
		})
}
function addClassFun(obj){
	obj.addClass('current').siblings().removeClass('current');
}

//选择6大类获取分享的数据
function loadShareData(obj){
	var userid=obj.attr('data-userid');
	var tablename=obj.attr('data-tablename');
	var tablecolumn=obj.attr('data-tablecolumn');
	var tablecolumn2=obj.attr('data-tablecolumn2');
	$.ajax({
		type:"POST",
		url:"/myHome/getOneShares",
		data:{userid:userid,tableName:tablename,tableColumn:tablecolumn,tableColumn2:tablecolumn2},
		dataType:"json",
        success:function(data){
        	   var name=convertCh(tablename);
        	   $('#zh-profile-answers-inner-list ul').empty().append(loadMoreHtml(data.shares.list,name,tablename));
        	   if($('.pj-load-more').length>0)
        	   $('.pj-load-more').remove();
        	   if(Number(data.shares.last)>1){
        		   if(tablename==''){
        			   var htm='<a href="javascript:void(0)"  data-pageno="1" data-tablecolumn2=""  data-tablename=""  data-tablecolumn="" data-userid='+userid+' data-sumpage='+data.shares.last+' class="zg-btn-white zg-r3px zu-button-more pj-load-more">更多</a>'
        		   }else{
        			   var htm='<a href="javascript:void(0)"  data-pageno="1" data-tablecolumn2='+tablecolumn2+' data-tablename='+tablename+'  data-tablecolumn='+tablecolumn+' data-userid='+userid+' data-sumpage='+data.shares.last+' class="zg-btn-white zg-r3px zu-button-more pj-load-more">更多</a>'
        		   }
        		  
        		   $('#zh-profile-answers-inner-list').after(htm);
        	   }
        }
	})
	//显示当前层  隐藏其他层
	$('#zh-profile-follows-list,.section-container,#contact-info-section').hide();
	$('.zm-profile-section-wrap').show();
	/*$('.contact').html('<b class="t-down"></b>个人简介')*/
}
//他的分享6大类切换--加载html   新增tablename add byJerry
function loadMoreHtml(shares,name,tablename){
	//判断是否访问自己的个人主页
	var userid=$('input[name=userid]').val();
	var shtml='';
	if(shares.length==0){
		var type = 0;
	    if (name=='话题')
	        type = 1;
	    else if (name=='书籍')
	        type = 2;
	    else if (name=='课程')
	        type = 3;
	    else if (name=='文章')
	        type = 4;
	    else if (name=='站点')
	        type = 5;
		if(userid==userInfo.userid){
			shtml='<span class="nosharelist">这没有分享，开始<a href="/sharein/searchuploadFile?type=' +type+'" target="_blank">分享'+name+'</a></span>';
		}else{
			shtml='<span class="nosharelist">这家伙很懒，还没有分享'+name+'</span>';
		}
		
	}else if(tablename!=''){
		$.each(shares,function(index,item){
			shtml+='<li>'
		    shtml+='  <div class="allnewscontent textoverflow">'
		    shtml+='     <a href='+getLinkUrl(tablename,item.id)+' target="_blank" title="'+item.title+'"><span class="allnewsname control-display">'+item.title+'</span></a>'
		    shtml+='     <span class="smsdate zg-right">'+formatDate(item.createtime)+'</span>'
		    shtml+=' </div>'	
		    shtml+='</li>'	
		})
	}else{
		$.each(shares,function(index,item){
			shtml+='<li>'
		    shtml+='  <div class="allnewscontent textoverflow">'
		    shtml+='     ['+convertCh(item.type)+']<a href='+item.objurl+' target="_blank" title="'+item.title+'"><span class="allnewsname control-display">'+item.title+'</span></a>'
		    shtml+='     <span class="smsdate zg-right">'+formatDate(item.createtime)+'</span>'
		    shtml+=' </div>'	
		    shtml+='</li>'	
		})
	}
	
	return shtml;
}