var userInfo,comIntro;
$(function(){
	intoUserInfo();
	getCurrentUser();	
	//快讯内容超出一定字数隐藏
	cutContet();
	//公司介绍超出一定内容隐藏
	if($('.profile-brief .intro').html()!=undefined){
		if($('.profile-brief .intro').html().replace(/[^x00-xFF]/g,'**').length>1110){
			var intro=$('.profile-brief .intro').html();
		   $('.profile-brief .intro').empty().append(autoAddEllipsis(intro,1000)).after("<span class='showallintro'>展示全部</span>")
		}
	}
	
	//公司介绍展示全部
	$('.showallintro').live('click',function(){
		//获取公司相关信息
		showAllIntro();
		$('.profile-brief .intro').empty().append(comIntro).after("<span class='upintro-content'>收起</span>");
		$('.showallintro').remove();
	})
	
	//判断简介图片大小
	var _URL = window.URL || window.webkitURL;
	$("#brieffile").live('change',function (e) {
      var file, img;
      if ((file = this.files[0])){
    	if(this.files[0].size>2097152){
    		 ZENG.msgbox.show('请上传小于2M的图片!', 5, 3000);
             return false;
    	}
        img = new Image();
        img.onload = function () {
          if(this.width<830 || this.height<220){
         	 ZENG.msgbox.show('请上传宽度大于830px,高度大于220px的图片!', 5, 3000);
             return false;
           }
           
     	   previewImage(file,2);
        };
        img.src = _URL.createObjectURL(file);
      }
	 })
	 //快讯新增链接加载输入框
	 $('#link-button').live('click',function(){
		 $(this).toggle(
			 function(){
				 $('.homepageTemplate').setTemplateURL('/addNewsUrl.html');
		    	 $('.homepageTemplate').processTemplate();
		    	 $(this).after($('.homepageTemplate').html());
		    	 $('.homepageTemplate').empty();
			 },function(){
				 /*if(){}*/
				 $(this).next().remove();
			 }
		 )
		 $(this).trigger('click');
	 });
	 //快讯新增链接输入框输入事件
	 $('#newsurl').live('input propertychange',function(){
		 var newsurl=$(this).val();
		 if(isURL(newsurl)){
			 $('#checkurl').css('background','url(/image/check-icon2.png) no-repeat 0 0');
		 }else{
			 $('#checkurl').css('background','url(/image/check-icon.png) no-repeat 0 0');
		 }
	 })
	 //展示全部快讯内容
	 $('.showallnews').live('click',function(){
		 showAllNews($(this));
	 });
	 //收起快讯内容
	 $('.upnews-content').live('click',function(){
		 upnewsContent($(this));
	 });
	 //收起内容简介
	 $('.upintro-content').live('click',function(){
		 upintroContent($(this));
	 });
	 //添加快讯链接
	 $('#checkurl').live('click',function(){
		 $(this).addClass('disabled');
		 var newsurl=$('#newsurl').val();
		 var $this=$(this);
		 $.ajax({
	    		type:"POST",
	    		url:"/comp/get3WInfo",
	    		data:{url:newsurl},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.returnStatus=='000'){
	    			  if(data.obj.title!=data.obj.url){
		    			  $('input[name=sitetitle]').val(data.obj.title);
	    			  }else{
	    				  data.obj.title='';
	    			  }
	    			  $('input[name=siteurl]').val(data.obj.url);
	    			  var urlHtml='';
	    			  urlHtml+="<div class='url-preview'><a href='"+data.obj.url+"' class='tap-target' target='_blank'>";
	    			  urlHtml+=" <article  class='image-description ember-view'>";
	    			  urlHtml+="   <h2>"+data.obj.title+"</h2>";
	    			  urlHtml+="   <h3>"+data.obj.url+"</h3>";
	    			  urlHtml+=" </article>";
	    			  urlHtml+="</a>"
	    		      urlHtml+="<a href='javascript:void(0)' class='close-newsurl' id='close-newsurl'></a></div>";
	    		      $('#addNewsContent').append(urlHtml);
	    			  $('#link-button').addClass('disabled').trigger('click');
	    			  $this.removeClass('disabled');
	    			}
	    		}
	    	})
	 });
	 //移除发布快讯时上传的图片
	 $('#close-newsimg').live('click',function(){
		 $('#newsImgFiles').removeClass('disabled'); 
		 $('.img-preview').remove();
		 cleanFile();
	 });
	 //移除发布快讯时上传的url
	 $('#close-newsurl').live('click',function(){
		 $('#link-button').removeClass('disabled'); 
		 $('.url-preview').remove();
	 });
	 //快讯上传图片
	 $('#newsImgFiles').live('change',function(e){
		 $(this).addClass('disabled');
		 previewNewsImage(this.files[0]);
	 })
	//编辑界面最新快讯和公司简介切换
	$('#company-tab>span').live('click',function(){
		$(this).addClass('current').siblings().removeClass('current');
		var index=$(this).index()==2?($(this).index()-1):$(this).index();
		$('.middle-card-left').eq(index).show();
		$('.middle-card-left').filter(':not(:eq('+index+'))').hide();
	})
	//预览界面tab切换
	$('#aboutCompany-tab>span').live('click',function(){
		$(this).addClass('current').siblings().removeClass('current');
		var index=($(this).index()+1)/2-1;
		$('.middle-card-left').eq(index).show();
		$('.middle-card-left').filter(':not(:eq('+index+'))').hide();
	})
	//取消修改用户名
	$('#title-container a[name=cancel]').live('click',function(){
		var username=$('input[name=username]').val();
		$('#title-container .title').removeClass('clearfix').html(username);
		hiddenEdit($(this));
	})
	//取消修改公司标语
	$('#slogan-container a[name=cancel]').live('click',function(){
		var slogan=$('input[name=slogan]').val();
		$('#slogan-container .slogan').removeClass('clearfix').html(slogan==""?'企业标语':slogan);
		hiddenEdit($(this));
	})
	//取消修改企业服务
	$('#enterprise-container a[name=cancel]').live('click',function(){
		var enterprise=$('input[name=enterprise]').val();
		$('#enterprise-container .enterprise').removeClass('clearfix').html(enterprise==""?'企业产品(服务)':enterprise);
		hiddenEdit($(this));
	});
	
	//保存修改用户名
	$('#title-container a[name=save]').live('click',function(){
		var username=$('#title-container .listorder').val();
		if($.trim(username).replace(/[^x00-xFF]/g,'**').length<4 || $.trim(username).replace(/[^x00-xFF]/g,'**').length>18 ){
    		$(this).parent().find('b').show();
    		$(this).parent().find('.item-msg-content').html('合法长度为4-18个字符').show();
    		return false;s
    	}
		$('#title-container .title').removeClass('clearfix').html(username);
		$('input[name=username').val(username);
		//同时更改数据库中的字段值
    	updateColumn(username,"username=");
		hiddenEdit($(this));
	})
	//保存修改公司标语
	$('#slogan-container a[name=save]').live('click',function(){
		var slogan=$('#slogan-container .listorder').val();
		if($.trim(slogan).replace(/[^x00-xFF]/g,'**').length>512){
    		$(this).parent().find('b').show();
    		$(this).parent().find('.item-msg-content').html('标语超出最大限制').show();
    		return false;
    	}
		$('#slogan-container .slogan').removeClass('clearfix').html(slogan==""?'企业标语':slogan);
		$('input[name=slogan').val(slogan);
		//同时更改数据库中的字段值
    	updateColumn(slogan,"description=");
		hiddenEdit($(this));
	});
	//保存修改企业服务
	$('#enterprise-container a[name=save]').live('click',function(){
		var enterprise=$('#enterprise-container .listorder').val();
		if($.trim(enterprise).replace(/[^x00-xFF]/g,'**').length>512){
    		$(this).parent().find('b').show();
    		$(this).parent().find('.item-msg-content').html('企业产品超出最大限制').show();
    		return false;
    	}
		$('input[name=enterprise').val(enterprise);
		$('#enterprise-container .enterprise').removeClass('clearfix').html(enterprise==""?'企业产品(服务)':enterprise);
		//同时更改数据库中的字段值
    	updateColumn(enterprise.replace(/(^\s+)|(\s+$)/g," ").replace(/\s/g," "),"specialty=");
		hiddenEdit($(this));
	});
	//修改业务领域
	$('a[name=editsarea]').live('click',function(){
		$(this).hide();
    	var areaitem=$('input[name=busiarea]').val();
    	var data;
    	if(areaitem!=""){
    		var tb='[';
        	for(var i=0;i<areaitem.split(',').length;i++){
        		tb+='{"id":'+areaitem.split(",")[i].split(":")[0]+',"name":"'+areaitem.split(",")[i].split(":")[1]+'"},';
        	}
        	tb=tb.substring(0,tb.length-1);
        	tb+=']';
        	data={
        		areaitem:jQuery.parseJSON(tb),
        	}
    	}
    	
    	$('.homepageTemplate').setTemplateURL('/myareaTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
    	var $child=$('.business-area .area').children('a');
    	$('.business-area .area').empty().append($('.homepageTemplate').html()).append($child);
    	$('.homepageTemplate').empty();
	});
	//取消修改业务领域
	$('#business-area [name=cancel]').live('click',function(){
		cancelArea();
	});
    //修改公司介绍
	$('a[name=editscombrief]').live('click',function(){
		//获取公司相关信息
		showAllIntro();
		$(this).hide();
	    $('.showallintro,.upintro-content').remove();
		var briefcon=comIntro==undefined?'':comIntro.replace(/<br\s*\/?\s*>/ig, '\n');
		$('.homepageTemplate').setTemplateURL('/editComBrief.html');
    	$('.homepageTemplate').processTemplate();
		$('.profile-brief .intro').empty().append($('.homepageTemplate').html());
		$('.homepageTemplate').empty();
		$('textarea[name=intro]').val(briefcon);
	});
	//取消修改公司介绍
	$('#profile-brief button[name=cancel]').live('click',function(){
		cancelBrief();
	});
	//修改公司地址
	$('.com-address .zu-edit-button').live('click',function(){
		$(this).hide();
		var nation=$('input[name=nation]').val();
		var province=$('input[name=province]').val();
		var city=$('input[name=city]').val();
		var address=$('input[name=address]').val();
		var postcode=$('input[name=postcode]').val();
		var data={
				nation:nation,
				province:province,
				city:city,
				postcode:postcode,
				address:address,
		}
		$('.homepageTemplate').setTemplateURL('/comAddressTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
    	$('.com-address form').empty().append($('.homepageTemplate').html())/*.append($child)*/;
    	/*$.getScript('/scripts/pj_country.js',function(){*/
    		setup();
		/*});*/
    	$("#edit-company-country").find("option[text='"+nation+"']").attr("selected",true);
    	$('.homepageTemplate').empty();
	});
	//修改公司网站以及相关信息
	$('.com-websit .zu-edit-button').live('click',function(){
		$(this).hide();
		var url =$('input[name=url]').val();
		var scale=$('input[name=scale]').val();
		var industry=$('input[name=industry]').val();
		var establishtime=$('input[name=establishtime]').val();
		var type=$('input[name=type]').val();
		var data={
				url:url,
				scale:scale,
				industry:industry,
				establishtime:establishtime,
				type:type,
		}
		
		$('.homepageTemplate').setTemplateURL('/websiteTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
    	$('.com-websit form').empty().append($('.homepageTemplate').html());
    	$('.homepageTemplate').empty();
    	//规模
    	$('#edit-company-size option').each(function(){
         	if($(this).attr('value')==scale){
         		$(this).attr("selected",true);
         	}
         })
         //行业
         $('#edit-company-industry option').each(function(){
        	 if($(this).attr('value')==industry){
        		 $(this).attr("selected",true);
        	 }
         })
         //类型
         $('#edit-company-type option').each(function(){
        	 if($(this).attr('value')==type){
        		 $(this).attr("selected",true);
        	 }
         })
	});
	
	//修改企业用户信息
	$('a[name=edittitle],a[name=editsenterprise],a[name=editslogan]').live('click',function(){
		$(this).hide().prev().addClass('clearfix');
		editUserInfo($(this));
	});
	//快讯点赞
	$('.numLikes').live('click',function(){
		//1：取消点赞  0 点赞
		flashLike($(this));
	})
	//企业快讯加载更多
	$('.flash-load-more').live('click',function(){
		$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
		flashLoadMore($(this))
	})
	 //最近访问加载更多
    $('.pjvisitor-load-more').live('click',function(){
    	addClassFun($(this));
    	moreVisitor($(this));
    });
	//业务领域获取焦点
	$('.manage-areas').live('click',function(){
		$('#searchArea').focus();
	});
	//编辑界面评论
	$('.numComments').live('click',function(){
		$(this).toggle(function(){
				var $child=$(this).children();
				var flashId=$(this).data('flashid');
		    	$(this).empty().append($child).append('收起评论');
		    	var islike=$(this).prev().data('likeoperate');
		    	var $this=$(this);
		    	$.ajax({
		    		type:'POST',
		    		url:"/comp/loadNewsComments",
					data:{newsid:flashId},
					dataType:"json",
					success:function(data){
						if(data.returnStatus=='000'){
							$.each(data.obj.list,function(index,item){
								item.createtime=formatDate(item.createtime)
							})
							var datas={
						    		flashcommentList:data.obj.list,
						    		flashId:flashId,
						    		last:data.obj.last,
						    		userInfo:userInfo,
						    		islike:islike
						    	}
						    	$('.homepageTemplate').setTemplateURL('/flashCommentTemplate.html');
						    	$('.homepageTemplate').processTemplate(datas);
						    	$this.parents('.operate-container').append($('.homepageTemplate').html());
						    	$('.homepageTemplate').empty();
						    	intoUserInfo();
						    	//为了分页
		       	    			$("#flashcompaging").pagination({
		       	    				items:data.obj.count,
		       	    				itemsOnPage:data.obj.pageSize,
		       	    				cssStyle:'light-theme',
		       	    				id:flashId,
		       	    				moduleType:'flashcommentlist'
		       	    			})
						}else{
							
						}
					}
		    	})
		},function(){
			var $children=$(this).children();
	    	$(this).empty().append($children).append('评论  '+$(this).attr('data-commsum'));
	    	$(this).parents('.operate-container').find('.panel-container').remove();
		})
		$(this).trigger('click');
	})
	 //选择6大类获取分享的数据
    $('#cmprofile-navbar .pjitem').live('click',function(){
    	addClassFun($(this));
    	loadShareData($(this));
    });
	//查看他人主页  点击个人关注
    $('#company-attention,#cmfans-navbar .pjitem:eq(0)').live('click',function(){
    	addClassFun($(this));
    	otherAtten($(this));
    })
    //界面右侧关注点击
    $('#cmotheratten').live('click',function(){
    	$('#company-attention').trigger('click');
    	$('#cmfans-navbar .pjitem:eq(0)').trigger('click');
    })
    //关注人
    $('.operation .zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })
    //界面右侧粉丝点击
    $('#cmotherfans').live('click',function(){
    	$('#company-attention').trigger('click');
    	$('#cmfans-navbar .pjitem:eq(1)').trigger('click');
    })
    //界面右侧最近访问加载更多
    $('#cmmoreVisitor').live('click',function(){
    	$('#company-attention').trigger('click');
    	$('#cmfans-navbar .pjitem:eq(2)').trigger('click');
    })
    //界面右侧最新分享加载更多
    $('#cmmoreShare').live('click',function(){
    	$('.middle-card-left').hide();
        $('#cmprofile-navbar').show();
    	$('#cmprofile-navbar .pjitem:eq(0)').trigger('click');
    	$('#company-share').addClass('current').siblings().removeClass('current');
    });
    //他的粉丝
    $('#cmfans-navbar .pjitem:eq(1),#cmotherfans').live('click',function(){
    	addClassFun($(this));
    	otherFans($(this));
    })
     //最近访问
    $('#cmfans-navbar .pjitem:eq(2)').live('click',function(){
    	addClassFun($(this));
    	currVisitor($(this));
    })
    //取消保存公司网站以及相关信息
    $('#com-websit button[type=button]').live('click',function(){
    	cancelWebsit($(this));
    });
    //取消保存公司网站以及相关信息
    $('#com-address button[type=button]').live('click',function(){
    	cancelAddress($(this));
    });
    //删除快讯评论
    $('a[name=delcomment]').live('click',function(){
    	deleteFlashComment($(this));
    });
    //粉丝和他的关注加载更多
	$('.pjfans-load-more,.pjatten-load-more').live('click',function(){
		$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
		loadMoreFansAndAttenData($(this));
	});
    //个人主页文档加载更多
    /*tbl_docs  tbl_article   tbl_sites tbl_topics                  title  
    tbl_books  bookname
    tbl_courses  coursesName*/
    $('.pj-load-more').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	loadMoreData($(this));
    })
    //快讯新增评论
    $('.flash-comment').live('click',function(){
    	var flashid=$(this).data('flashid');//快讯id 
    	//评论内容
    	var comment=$(this).parents('.cm-comment-form').find('.pj-replaycontent').val();
    	if($.trim(comment).length==0){
    		return false;
    	}
    	if($.trim(comment).replace(/[^x00-xFF]/g,'**').length>1000){
    		$(this).parents('.cm-command').find('.ic-msg,.item-msg-content').show();
    		return false;
    	}
    	//快讯公司ID
    	var objCreatepersonPg=$('input[name=companyId]').val();
    	//快讯公司名字
    	var objectNamePg=$('input[name=companyName]').val();
        var $this=$(this);
    	$.ajax({
	         	type:"POST",
	         	url:"/comp/addComment",
	         	data:{compid:objCreatepersonPg,newsid:flashid,comments:comment,objCreatepersonPg:objCreatepersonPg,objectNamePg:objectNamePg},
	         	dataType:"json",
	         	success:function(data){
	         		if(data.returnStatus=='000'){//返回成功
	         			$this.parents('.cm-comment-form').find('.pj-replaycontent').val('');
	         			data.obj.createtime=formatDate(data.obj.createtime);
	         			var data={
	         					flashComment:data.obj,
	         					userinfo:userInfo,
	         			}
	         			$('.homepageTemplate').setTemplateURL('/appendFlash.html');
	       	    		$('.homepageTemplate').processTemplate(data);
	       	    		if($('.page-inner').length>0){
	    	    			$('.page-inner').before($('.homepageTemplate').html());
	    	    		}else{
	    	    			$this.parents('.cm-comment-box').find('.zm-comment-list').append($('.homepageTemplate').html());
	    	    		}
	       	    		$('.homepageTemplate').empty(); 
	       	    		intoUserInfo();
	       	    		//评论条数加1
	       	    		var commsum=$this.parents('.operate-container').find('.numComments').attr('data-commsum');
	       	    		$this.parents('.operate-container').find('.numComments').attr('data-commsum',Number(commsum)+1);
	        		}else{//返回失败
	        		}
	        	}
	     });

    })
    
    //快讯评论框获取焦点
    $('.pj-replaycontent').live('focus',function(){
    	$('.item-msg-content,.ic-msg').hide();
    });
    
    //删除企业快讯
    $('.delete-falsh').live('click',function(){
    	var id=$(this).data('newsid');
    	$this=$(this);
    	
    	$.confirm({
    		'title'		: '删除快讯',
    		'message'	: "确认要删除这条快讯吗?",
    		'buttons'	: {
    			'确认'	: {
    				'class'	: 'blue',
    				'action': function(){
    					delCpNews(id)
    				}
    			},
    			'取消'	: {
    				'class'	: 'gray',
    				'action': function(){}
    			}
    		}
    	});
    })
    //置顶企业快讯
    $('.stick-botton').live('click',function(){
    	topCpNews($(this));
    })
    //发布企业快讯
    $('#public-flash').live('click',function(){
    	$(this).addClass('disabled');
    	//判断是否为空
    	if($.trim($('#addNewsContent .mentions-input').val()).length==0&&$('.url-preview').length==0&&$('.img-preview').length==0){
    		return false;
    	}
    	addCpNews($(this));
    })
    //获取请求的url参数
	var strHref = this.location.href;
	//0表示点击用户图像上面的关注   1代表粉丝   2代表分享  3代表最近访问更多   4代表公司快讯
    var requesType = strHref.getQuery("requesType");
    if(requesType!=null){
    	if(requesType==0){
    		$('#cmotheratten').trigger('click');
    	}else if(requesType==1){
    		$('#cmotherfans').trigger('click');
    	}else if(requesType==2){
            $('#company-share').trigger('click');
    	}else if(requesType==4){
            $('#aboutCompany-tab>span:eq(0)').trigger('click');
    	}else{
    		$('#cmmoreVisitor').trigger('click');
    	}
    }
    
})
//点击取消  移除编辑框
function hiddenEdit(obj){
	obj.parents('.hm-command').prev().show();
	obj.parents('.hm-command').remove();
}


//企业快讯加载更多
function flashLoadMore(obj){
	 var pageNo=obj.attr('data-pageno');
	 var sumpage=obj.data('sumpage');
	 var compid=$('input[name=companyId]').val();
	 var comtype=obj.data('comtype');
	 $.ajax({
		 type:"POST",
		 url:"/comp/loadNews",
         data:{compid:compid,pageNo:Number(pageNo)+1},
         dataType:"json",
			success:function(data){
				if(data.returnStatus=='000'){
		    		
					$.each(data.obj.list,function(index,item){
						item.createtime=formatDate_hhmmss(item.createtime);
						if(item.likedIds!=undefined){
			    			if(item.likedIds.indexOf(',')!=-1){
			    				if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.likedIds.split(','))!=-1){
								item.likedIds=1;
							}else{
								item.likedIds=0;
							}}
			    			else{
			    				if(item.likedIds==(userInfo==undefined?'':userInfo.userid)){
			    					item.likedIds=1;
			    				}
			    				else{
			    					item.likedIds=0;
			    				}
			    			}
			    		}
			    		else{
			    			item.likedIds=0;
			    		}
					})
					var datamodel={
				        cpNewes:data.obj.list,
				        userInfo:userInfo,
				        comtype:comtype
			    	}
					$('.homepageTemplate').setTemplateURL('/flashLoadMoreTemplate.html');
		          	$('.homepageTemplate').processTemplate(datamodel);
		          	$('.flash-load-more').before($('.homepageTemplate').html());
		          	$(".homepageTemplate").empty();
		          	$('.flash-load-more').attr('data-pageno',Number(pageNo)+1);
		          	obj.removeClass('loading').empty().append('更多');
		          	if(Number(sumpage)==Number(pageNo)+1){
		          		 $('.flash-load-more').hide(); 
		          	}
		          	intoUserInfo();
		          	cutContet();
				}else{
					
				}
			}
	 })
}
//企业快讯点赞
function flashLike(obj){
	//动作  1：取消点赞  0 点赞
	var likeOperate=obj.attr('data-likeoperate'); 
	var flashid=obj.attr('data-flashid');//快讯id
	var objCreatepersonPg=$('input[name=companyId]').val();
	var likecount=obj.attr('data-likenum');
	$.ajax({
			type:"POST",
			url:"/comp/likeNews",
			data:{likeOperate:likeOperate,id:flashid,objCreatepersonPg:objCreatepersonPg},
			dataType:"json",
			success:function(data){
	        	if(data.returnStatus=='000'){
	        		if(likeOperate==0){
	        			obj.attr('data-likeoperate','1');
	        			obj.attr('data-likenum',Number(likecount)+1);
	        			obj.empty().append('取消赞  '+"("+obj.attr('data-likenum')+")");
	        		}else{
	        			obj.attr('data-likeoperate','0');
	        			obj.attr('data-likenum',Number(likecount)-1);
	        			obj.empty().append('赞  '+obj.attr('data-likenum'));
	        		}
	        	}else{
	        		
	        	}
			}
	 })
}

//删除企业快讯
function deleteFlashComment(obj){
	var id=obj.data('id');
	var newsid=obj.data('flashid');
	$.ajax({
		type:'POST',
		url:'/comp/delComment',
		data:{id:id,newsid:newsid},
		dataType:'json',
		success:function(data){
			if(data.returnStatus=='000'){
				//评论条数减1
   	    		var commsum=obj.parents('.operate-container').find('.numComments').attr('data-commsum');
   	    		obj.parents('.operate-container').find('.numComments').attr('data-commsum',Number(commsum)-1);
				obj.parents('.zm-item-comment').remove();
			}else{
				
			}
		}
	})
}



//加载用户信息
function intoUserInfo(){
	$('.uhead,.zm-item-link-avatar,.author-link,.zm-item-img-avatar,.zg-link').pinwheel();
}

//保存与取消按钮
function saveOrCancelBtn(num,type){
	//type=1表示是个人主页头部内容修改
	var shtm='';
	    if(num==1){
	    	shtm+="<div class='hm-command'>";
	    }else{
	    	shtm+="<div class='cm-command'>";
	    }
	    if(type=='enterprise'){
	    	shtm+='<span class="editor-tags-i">多个企业产品(服务)可以用空格隔开</span>'
		}
	    shtm+='<b class="ic ic-msg" style="background-position: -47px -144px;display:none"></b>';
	    shtm+='<span class="item-msg-content"  style="display:none">合法长度为4-18个字符</span>';
	    shtm+='<a class="zm-command-cancel" name="cancel" href="javascript:;" style="font-size:14px;font-weight:500;">取消</a>'
	    shtm+='<a class="zg-r3px zg-btn-blue" name="save" href="javascript:;">保存</a>';	
	    shtm+="</div>";
	return shtm;
}

//企业快讯置顶
function topCpNews(obj){
	   var id=obj.data('newsid');  
	   $.ajax({
      	type:"POST",
      	url:"/comp/topCpNews",
      	data:{id:id},
      	dataType:"json",
      	success:function(data){
      		if(data.returnStatus=='000'){//返回成功
         		$('#entity-list').prepend(obj.parents('.entity-list-item'));
         		//其他层的置顶图标移除
         		$('.com-top').remove();
         		$('#entity-list-item-'+id+' .feed-s-main-content').prepend('<i class="com-top com-icon"></i>');
         		var top=$('#entity-list').offset().top;
         		window.scrollTo(0,top);
     		}else{//返回失败
     			console.log("topCpNews fail");
     		}
     	}
  });
}
//删除企业快讯
function delCpNews(id){
  	   $.ajax({
         	type:"POST",
         	url:"/comp/delCpNews",
         	data:{id:id},
         	dataType:"json",
         	success:function(data){
         		if(data.returnStatus=='000'){//返回成功
            		//当前块隐藏
            		$("#entity-list-item-"+id).remove();
            		if($('#entity-list li').length==0){
            			$('#entity-list').append('<li class="no-flashlist">暂无最新快讯</li>');
            		}
        		}else{//返回失败
        			
        		}
        	}
     });
} 

//修改用户信息
function editUserInfo(obj){
	var type=obj.data('type');
	var comName=$('.'+type).html();
	if(type=='enterprise'){
		comName=$('input[name=enterprise]').val();
	}
	if(comName=='企业产品(服务)'||comName=='企业标语'){
		comName="";
	}
	var list_form = '<textarea name="combrief" rows="6" cols="3" class="listorder_'+type+' listorder"></textarea>';
	$('.'+type).html(list_form);
	$('#'+type+'-container').append(saveOrCancelBtn(1,type));
	$('.listorder_'+type).val(comName).focus();
}

//新增评论
// compid 上一级评论id*****暂时不用
// newsid 快讯id 
// comments 评论内容
// objCreatepersonPg 快讯公司id 
// objectNamePg 快讯名称 	
function addComment(obj) {
	var newsid = 1;
	var comments = "";
	var objCreatepersonPg = 31; 
	$.ajax({
			type : "POST",
			url : "/comp/addComment",
			data : {
				newsid : 1,
				comments : "非常好",
				objCreatepersonPg : 30
//				objectNamePg : "快讯名称"
			},
			dataType : "json",
			success : function(data) {
				if (data.returnStatus == '000') {// 返回成功
					console.log(data);
				} else {// 返回失败
				}
			}
		});
}
//发布企业快讯
function addCpNews(obj){
	var formData = new FormData($("#adNewsForm")[0]);
    $.ajax({  
    	url:"/comp/addCpNews",
         type: 'POST',  
         data:formData,  
        /* async: false,  
         cache: false,*/  
         contentType: false,  
         processData: false,  
         success: function (data) {  
         	 $('#link-button,#newsImgFiles').removeClass('disabled');
        	 /*var data=JSON.parse(data);*/
             //添加一条快讯
    		 var cpNewes = new Array();
    		 var mynews=new Object();
    		 mynews.headIcon=userInfo.headicon;
    		 mynews.userName=userInfo.username;
    		 mynews.createtime=formatDate_hhmmss(data.obj.createtime);
    		 mynews.siteurl=data.obj.siteurl;
    		 mynews.sitetitle=$('input[name=sitetitle]').val();
    		 mynews.news=$('#addNewsContent .mentions-input').val();
    		 mynews.imgurl=data.obj.imgurl;
    		 mynews.likedIds=0;
    		 mynews.id=data.obj.id;
    		 mynews.likesum=data.obj.likesum;
    		 mynews.commentsum=data.obj.commentsum;
    		 cpNewes.push(mynews);
             var datamodel={
				        cpNewes:cpNewes,
				        userInfo:userInfo,
				        comtype:1
			    	}
					$('.homepageTemplate').setTemplateURL('/flashLoadMoreTemplate.html');
		          	$('.homepageTemplate').processTemplate(datamodel);
		          	if($('#entity-list').find('.no-flashlist').length==1){
		          		$('#entity-list').empty();
		          	}
		          	$('#entity-list').prepend($('.homepageTemplate').html());
		          	$(".homepageTemplate").empty();
		          	obj.removeClass('disabled');
		          	$('input[name=sitetitle],input[name=siteurl]').val('');
		            //清空文本框内容
		        	$('#addNewsContent .mentions-input').val('');
		        	var $textarea=$('#addNewsContent').children('.mentions-input');
		        	$('#addNewsContent').empty().append($textarea);
		        	//清空file
		        	cleanFile();
		          	if($('#newsurl1').length>0){
			          	$('#link-button').trigger('click');
		          	}
		          	cutContet();
         },  
         error: function (data) {  
             
         }  
    }); 
}
//清空file域
function cleanFile(){
	$('#newsImgFiles').removeClass('disabled');
	var file = $("#newsImgFiles");
	file.after(file.clone().val(""));      
	file.remove();  
}
//快讯截取一部分内容 添加展示全部
function cutContet(){
	 //快讯内容超出 显着展示全部
	 $('.inline-show-more-text').each(function(){
		  var flash=$(this).data('flash');
		  var text=$(this).text();
		  if(text.replace(/[^x00-xFF]/g,'**').length>300){
			  if($(this).find('i').length>0){
				  var $child=$(this).children('i');
			      $(this).empty().append($child).append(autoAddEllipsis(text,300)).append("<span class='showallnews' data-flash='"+flash+"'>展示全部</span>")
			  }else{
			      $(this).text(autoAddEllipsis(text,300)).append("<span class='showallnews' data-flash='"+flash+"'>展示全部</span>")
			  }
		  }
	 });
}
//收起快讯内容
function upnewsContent(obj){
	 var $this=obj.parents('.inline-show-more-text');
	 var text=$this.text();
	 var flash=$this.data('flash'); 
	 if($this.find('i').length>0){
		  var $child=$this.children('i');
		  $this.empty().append($child).append(autoAddEllipsis(text,300)).append("<span class='showallnews' data-flash='"+flash+"'>展示全部</span>")
	  }else{
		  $this.empty().append(autoAddEllipsis(text,300)).append("<span class='showallnews' data-flash='"+flash+"'>展示全部</span>")
	  }
}
//收起内容简介
function upintroContent(){
  var intro=$('.profile-brief .intro').html().replace(/(^\s+)|(\s+$)/g,"&nbsp;").replace(/\s/g,"&nbsp;&nbsp;");
  $('.profile-brief .intro').empty().append(autoAddEllipsis(intro,1000)).append("<span class='showallintro'>展示全部</span>");
  $('.upintro-content').remove();
}
//展示全部快讯内容
function showAllNews(obj){
	var flash=obj.data('flash');
	$.ajax({
		type : "POST",
		url : "/comp/getOneNews ",
		data : {id:flash},
		dataType : "json",
		success : function(data) {
			if (data.returnStatus == '000') {// 返回成功
				var $i;
				if(obj.parents('.inline-show-more-text').find('i')){
					 $i=obj.parents('.inline-show-more-text').children('i');
				}
				obj.parents('.inline-show-more-text').empty().prepend($i).append(data.obj.news).append("<span class='upnews-content'>收起</span>");
			} else {// 返回失败 
				
			}
		}
	})
}
//公司介绍展示全部
function showAllIntro(){
	$.ajax({
		type : "POST",
		url : "/comp/getCompIntro",
		data : {id:userInfo.userid},
		dataType : "json",
		async:false,
		success : function(data) {
			if (data.returnStatus == '000'&&data.obj!=undefined) {// 返回成功
				comIntro=data.obj.intro;
			} else {// 返回失败 
				
			}
		}
	})
}
//保存公司地址
function postAddressData(){
	var zipcode=$('#edit-company-zipcode').val();
	var istrue=false;
	if(!is_postcode(zipcode)&&zipcode!=''){
		 $('#com-address').find('.item-msg-content').html('邮编格式不正确');
		 istrue=true;
	 }
	 if(istrue){
		 $('#com-address').find('.item-msg-content,.ic-msg').show();
		 return false;
	 }

	 $.ajax({  
    	 url:"/comp/updUserInfo",
         type: 'POST',  
         data:$('#com-address').serialize(),  
         async: false,  
         dataType: "json",
         success: function (data) {  
            //界面赋值  
            $('input[name=nation]').val(data.obj.nation);
            $('input[name=province]').val(data.obj.province);
            $('input[name=city]').val(data.obj.city);
            $('input[name=postcode]').val(data.obj.postcode);
            $('input[name=address]').val(data.obj.address);
            var datamodel={
            		nation:data.obj.nation,
            		province:data.obj.province,
            		city:data.obj.city,
            		postcode:data.obj.postcode,
            		address:data.obj.address
        	}
        	$('.homepageTemplate').setTemplateURL('/saveAddressInfo.html');
          	$('.homepageTemplate').processTemplate(datamodel);
          	$('#com-address').empty().append($('.homepageTemplate').html());
          	$(".homepageTemplate").empty();
         }
	 });
	 return false;
}
//保存公司介绍
function postBriefData(){
	$('textarea[name=intro]').val($('textarea[name=intro]').val().replace(/\n/g,'<br/>'));
	$.ajax({  
   	 url:"/comp/updUserInfo",
        type: 'POST',  
        data:$('#profile-brief').serialize(),  
        async: false,  
        dataType: "json",
        success: function (data) {  
           //介绍超出一定字数  展示全部
           if(data.obj.intro.replace(/[^x00-xFF]/g,'**').length>1110){
        	   $('#profile-brief .intro').empty().append(autoAddEllipsis(data.obj.intro,1000)).after("<span class='showallintro'>展示全部</span>")
           }else{
        	   $('#profile-brief .intro').empty().append(data.obj.intro);
           }
           $('#profile-brief a[name=editscombrief]').show();
        }
   });  
   return false;
}
//取消修改公司介绍
function cancelBrief(obj){
	if(comIntro==undefined?'':comIntro.replace(/[^x00-xFF]/g,'**').length>1110){
  	   $('#profile-brief .intro').empty().append(autoAddEllipsis(comIntro,1000)).after("<span class='showallintro'>展示全部</span>")
    }else{
    	$('#profile-brief .intro').empty().append(comIntro);
    }
	$('#profile-brief a[name=editscombrief]').show();
}
//保存业务领域
function postAreaData(){
	var areas='';
	/*var skillIds='';*/
	var areaHtm='';
	$('#skillinputtags a').each(function(){
		areas+=$(this).attr('id')+":"+$(this).data('name')+",";
		areaHtm+='<span class="areazone" data-areaid="'+$(this).attr('id')+'">'+$(this).data('name')+'</span>'
	})
	areas=areas.substring(0,areas.length-1);
	$('input[name=busiarea]').val(areas);
	$.ajax({  
	   	 url:"/comp/updUserInfo",
	        type: 'POST',  
	        data:$('#business-area').serialize(),  
	        async: false,  
	        dataType: "json",
	        success: function (data) {
	        	var $c=$('#business-area .area').children('a');
	        	$('#business-area .area').empty().append(areaHtm).append($c);
	        	$('#business-area a[name=editsarea]').show();
	        }
	   });  
	   return false;
}
//取消修改业务领域
function cancelArea(){
	var busiarea=$('input[name=busiarea]').val();
	var areaHtml='';
	if(busiarea!=''){
		for(var i=0;i<busiarea.split(',').length;i++){
			areaHtml+='<span class="areazone" data-areaid="'+busiarea.split(',')[i].split(':')[0]+'">'+busiarea.split(',')[i].split(':')[1]+'</span>'
		}
	}
	var $c=$('#business-area .area').children('a');
	$('#business-area .area').empty().append(areaHtml).append($c);
	$('#business-area a[name=editsarea]').show();
}
//保存公司网站以及相关信息
function postWebsitData() {
	 var websiturl=$('#edit-company-url').val();
	 var flag=false;
	 if(!isURL(websiturl)&&websiturl!=''){
		 $('#com-websit').find('.item-msg-content').html('网站链接无效');
		 flag=true;
	 }
	 var evalue =$("#establishtime").val()==''?'':$("#establishtime").val(); 
	 var reg=/^(\+|-)?\d+$/;
	 var d = new Date()
	 var vYear = d.getFullYear()
	 
	 if((!reg.test(evalue)||evalue<1000||evalue>Number(vYear))&&evalue!=''){
		 $('#com-websit').find('.item-msg-content').html('创建年份无效');
		 flag=true;
	 }
	if(flag){
		 $('#com-websit').find('.item-msg-content,.ic-msg').show();
		 return false;
	 }
	 $.ajax({  
    	 url:"/comp/updUserInfo",
         type: 'POST',  
         data:$('#com-websit').serialize(),  
         async: false,  
         dataType: "json",
         success: function (data) {  
            //界面赋值  
        	if(data.obj.url==undefined||data.obj.url==''||data.obj.url==null){
        		data.obj.url="";
        	}
        	$('input[name=url]').val(data.obj.url);
            $('input[name=scale]').val(data.obj.scale);
            $('input[name=industry]').val(data.obj.industry);
            if(data.obj.establishtime==undefined||data.obj.establishtime==''||data.obj.establishtime==null){
            	data.obj.establishtime="";
            }else{
                data.obj.establishtime=formatDate(data.obj.establishtime).split('-')[0];
            }
            $('input[name=establishtime]').val(data.obj.establishtime);
            $('input[name=type]').val(data.obj.type);
            var datamodel={
        			url:data.obj.url,
        			scale:data.obj.scale,
        			industry:data.obj.industry,
        			establishtime:data.obj.establishtime,
        			type:data.obj.type
        	}
        	$('.homepageTemplate').setTemplateURL('/saveWebsitInfo.html');
          	$('.homepageTemplate').processTemplate(datamodel);
          	$('#com-websit').empty().append($('.homepageTemplate').html());
          	$(".homepageTemplate").empty();
         } 
    });  
    return false;
}
//取消保存公司网站以及相关信息
function cancelWebsit(obj){
	var url=$('input[name=url]').val();
	var scale=$('input[name=scale]').val();
	var industry=$('input[name=industry]').val();
	var establishtime=$('input[name=establishtime]').val();
	var type=$('input[name=type]').val();
	var datamodel={
			url:url,
			scale:scale,
			industry:industry,
			establishtime:establishtime,
			type:type
	}
	$('.homepageTemplate').setTemplateURL('/saveWebsitInfo.html');
  	$('.homepageTemplate').processTemplate(datamodel);
  	$('#com-websit').empty().append($('.homepageTemplate').html());
  	$(".homepageTemplate").empty();
}

//取消保存地址
function cancelAddress(obj){
	var nation=$('input[name=nation]').val();
	var province=$('input[name=province]').val();
	var city=$('input[name=city]').val();
	var postcode=$('input[name=postcode]').val();
	var address=$('input[name=address]').val();
	var datamodel={
			nation:nation,
			province:province,
			city:city,
			postcode:postcode,
			address:address
	}
	$('.homepageTemplate').setTemplateURL('/saveAddressInfo.html');
  	$('.homepageTemplate').processTemplate(datamodel);
  	$('#com-address').empty().append($('.homepageTemplate').html());
  	$(".homepageTemplate").empty();
}
//验证邮政编码  
function is_postcode(postcode) {  
    if (postcode == "") {  
        return false;  
    } else {  
        if (! /^[0-9][0-9]{5}$/.test(postcode)) {  
            return false;  
        }  
    }  
    return true;  
}  
//创建业务领域(根据输入的条件查询  有就直接筛选  没有就创建)
function  getAreaByCondition(obj){
	var oldval,newval;
	oldval=$('input[name=currentareaval]').val();
	newval=$(obj).val();
	if(newval !== null &&newval !== undefined&&$.trim(newval).length!=0&&$.trim(oldval).length!=$.trim(newval).length){
		findAreas(newval,$(obj));
	}else if($.trim(newval).length==0){
		   $(obj).parent().find('div:last-child').remove();
		   $('input[name=currentareaval]').val('');
	}
}
function findAreas(conds,obj){
	$.ajax({
		type:'POST',
		url:"/comp/findBusLib/"+conds,
		dataType:"JSON",
     	async:false,
     	success:function(data){
     		if(data.returnStatus=='000'){
     			$('input[name=currentskillval]').val(conds);
     			obj.chosen(data,obj,'area',conds);
     		}else{
     			
     		}
     	}		
	})
}