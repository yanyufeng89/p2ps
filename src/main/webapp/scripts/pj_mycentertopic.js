var userInfo,editor,umtor;
$(function(){
	//头像信息
	intoUserInfo();
	//获取用户信息
	getCurrentUser();
	editor=UM.getEditor('uEditorCustom',{
		initialFrameWidth :830,//设置编辑器宽度
		initialFrameHeight:150,//设置编辑器高度
		scaleEnabled:true
	});
	//我的话题 ---已分享单个删除
	 $('.operate span').live('click',function(){
	    	var elem = $(this).closest('.item');    	
	    	var id=$(this).data('id');
	    	var name=$(this).data('name');
	    	//1代表已分享  0代表回复
            var type=$(this).data('type');
	    	$this=$(this);
			$.confirm({
				'title'		: '确认删除话题',
				'message'	: "确认要删除话题\""+name+"\"吗?",
				'buttons'	: {
					'确认'	: {
						'class'	: 'blue',
						'action': function(){
							if(type=='1')
						        deleteTopics(id.toString(),$this);
							else{
								deleteTopicsComments(id.toString(),$this);
							}					
						}
					},
					'取消'	: {
						'class'	: 'gray',
						'action': function(){}
					}
				}
			});
	 })
	    
    //我的话题批量删除(已分享,关注,回复)
    $('.remove').live('click',function(){
    	var $this=$(this);
    	var type=$this.data('type');
    	if($this.hasClass('enabled')){
    		var conditions="";
    		$this.parent().nextAll('.docs-list').find('.select-icon').each(function(){
    			//从界面上移除当前
    			conditions+=$(this).data('topicid')+',';
    		});
    		
    		conditions=conditions.substring(0,conditions.length-1);
			$.confirm({
				'title': '确认删除',
				'message': "确认要删除这些话题吗?",
				'buttons': {
					'确认': {
						'class': 'blue',
						'action': function () {
							if(type=='1')//1代表分享  2是关注  0回复
								deleteTopics(conditions,$this);
							else if(type=='0'){
								deleteTopicsComments(conditions,$this);
							}else{
								deleteAttentions(conditions,$this);
							}
						}
					},
					'取消': {
						'class': 'gray',
						'action': function () {
						}
					}
				}
			});
    	}
    	$(this).removeClass('enabled');
    })
    
    //取消关注
    $('.attention span').live('click',function(){
    	var topicid=$(this).data('topicid');
    	var $btn=$(this).find('input[type=botton]');
    	if($btn.hasClass('topic-disblue')){
    		$btn.removeClass('topic-disblue').addClass('topic-blue').val('关注');
    		deleteAttentions(topicid.toString(),$(this));
    		$(this).attr('data-actiontype','1');
    	}else{
    		$btn.removeClass('topic-blue').addClass('topic-disblue').val('取消关注');
    		topicFollow($(this),1,'1');
    		$(this).attr('data-actiontype','0');
    	}
    	 
    })

    //单独一个点击文本框 选中或者反选
    $('.chktopic').click(function(){
    	if($(this).hasClass('select-icon')){
    		$(this).removeClass('select-icon');
    		$(this).parents('.docs-list').prev().find('.checkbox').removeClass('select-icon');
    		//一个个点击去掉选中的时候   当最后一个去点选中之后  最上面的删除变灰色
    		if($(this).parents('ul').find('.select-icon').length==0)
    			$(this).parents('.docs-list').prevAll('.delete-box').find('.topic-blue').removeClass('topic-blue').addClass('topic-disblue');
    	}
    	else{
    		$(this).addClass('select-icon');
    		//当选中的大于等于2个 就可以批量删除
    		if($(this).parents('ul').find('.select-icon').length>=2)
    			$(this).parents('.docs-list').prevAll('.delete-box').find('.topic-disblue').removeClass('topic-disblue').addClass('topic-blue');
    	}
    });
	//UEditor点击全屏按钮事件
    $('.edui-btn-fullscreen').live('click',function(){
    	if($('.edui-body-container').height()=='150'){
    		 $('.edui-body-container').css('height','950px');
    	}else{
    		 $('.edui-body-container').css('height','150px');
    	}
    });

	 //以下话题详情部分

	 //发布回答文本框获得焦点
	 $('.edui-body-container').live('click',function(){
		 topicFocus($(this));
	 })

     //话题的标题点击修改(模板)
    $('#zh-question-title a[name=edit]').live('click',function(){
		var titletext=$(this).prev().text();
		var tid=$(this).data('id');
		var data={
				id:tid,
				title:titletext,
		}
	  $('#titletemplate').setTemplateURL(projectName+'topicTitleEditTemplate.html',null, {filter_data: false});
	  $('#titletemplate').processTemplate(data);
	  $('#zh-question-title h2').append($('#titletemplate').html());
	  $("#titletemplate").empty();
      $('#zh-question-title h2 .zm-editable-content').css('display','none');
      $('#zh-question-title h2 .pj-editable-editor-wrap').css('display','block');
    });
    //话题标签点击修改
    $('.zm-tag-editor a[name=edit]').live('click',function(){
    	$(this).parent().css('display','none');
    	var topicsclass=$(this).attr('data-topicsclass');
    	var topicsid=$(this).data('topicsid');
        var tb='[';
    	for(var i=0;i<topicsclass.split(',').length;i++){
    		tb+='{"id":'+topicsclass.split(",")[i].split(":")[0]+',"name":"'+topicsclass.split(",")[i].split(":")[1]+'"},';
    	}
    	tb=tb.substring(0,tb.length-1);
    	tb+=']';
    	var data={
    			id:topicsid,
    			table:jQuery.parseJSON(tb),
    			taglen:topicsclass.split(',').length,
    	}
    	$('#tagtemplate').setTemplateURL(projectName+'topicTagEditTemplate.html',null, {filter_data: false});
        $('#tagtemplate').processTemplate(data);
        $('.zm-tag-editor').append($('#tagtemplate').html());
        $("#tagtemplate").empty();
       
    })
    
    //话题的标题点击修改--取消
    $('#zh-question-title a[name=cancel]').live('click',function(){
    	titledisplayorhide();
    });
    //话题的标题点击修改--确定
    $('#zh-question-title a[name=save]').live('click',function(){
    	var titleid=$('input[name=titleid]').val();
    	var titlename=$('#titleedit').val();
    	//是否加问号
    	if(titlename.substring(titlename.length-1,titlename.length)!="?"&&titlename.substring(titlename.length-1,titlename.length)!="？")
    		{
	    		$.confirm({
					'title'		: '提示信息',
					'message'	: "你还没有给问题添加问号",
					'buttons'	: {
						'确认'	: {
							'class'	: 'blue',
							'action': function(){
							}
						},
					}
				});
	    		return false;
    		}
    	
    	$.ajax({
    		type:'POST',
    	    url:projectName+"myCenter/updateTopics",
    	    data:{id:titleid,title:titlename},
    	    dataType:"json",
    	    success:function(data){
    	    	if(data.returnStatus=='000'){//返回成功
    	    		titledisplayorhide();
    	    		$('#zh-question-title .title').text(titlename);
    	    	}
    	    	else{
    	    		
    	    	}
    	    }
    	    
    	})
    	
    });
    //话题详情界面标签删除
    $('#topictagedit a[name=remove]').live('click',function(){
    	var topicindex=$('.zg-inline div').index($(this).parent());
		$(this).parent().remove();
		$('#topictag').parent().show();
		$('#topictag').parent().next().hide();
		addTagToDatabase(topicindex);
	});
	//话题详情标签点击完成
    $('a[name=close]').live('click',function(){
    	$('.zm-tag-editor-labels').show();
    	$('.zm-tag-editor-editor').remove();
    });
    //话题详情内容点击修改(添加富文本编辑器)
    $('#zh-question-detail a[name=edit]').live('click',function(){
    	$('#zh-question-detail .zm-editable-content').hide();
    	$('#zh-question-detail .zm-command').show();
    	if($('#zh-question-detail .edui-container').length){
    		$('#zh-question-detail .edui-container').show()
    	}
    	else
    	umtor=UM.getEditor('topiceditor',{
    		initialFrameWidth :830,//设置编辑器宽度
    		initialFrameHeight:150,//设置编辑器高度
    		scaleEnabled:true
    	});
    })
    //话题详情内容取消
    $('#zh-question-detail a[name=cancel]').live('click',function(){
    	titlecondisplayorhide();
    })
    //话题详情---内容保存
    $('#zh-question-detail a[name=save]').live('click',function(){
    	var titleid=$('input[name=titleid]').val();
    	/*var about=$(".edui-body-container").html();*/
       	var about=umtor.getContent();
        //判断输入的字节大小
    	var leng=about.length+(about.match(/[^\x00-\xff]/g) ||"").length;
    	if(leng>10000){
    		$(this).before('<span class="errortip">请控制在10000 字以下</span>&nbsp;&nbsp;');
    		return false;
    	}
    	if(!UM.getEditor('topiceditor').hasContents()){
    		return false;
    	}
    	$.ajax({
    		type:'POST',
    	    url:projectName+"myCenter/updateTopics",
    	    data:{id:titleid,content:about},
    	    dataType:"json",
    	    success:function(data){
    	    	if(data.returnStatus=='000'){//返回成功
    	    		titlecondisplayorhide();
    	    		about=about.substring(0,about.length-4);
    	    		about+="<a href='javascript:;' class='zu-edit-button' name='edit'><i class='zu-edit-button-icon'></i>修改</a></p>"
    	    		$('#zh-question-detail .zm-editable-content').empty().append(about);
    	    	}
    	    }
    	    
    	})
    })
    //话题详情--回答里面的评论收起或展开
    $('#zh-question-answer-wrap .js-comment').live('click',function(){
       	$(this).toggle(function(){
        	var $child=$(this).children();
        	$(this).empty().append($child).append('收起评论');
        	var answerid=$(this).attr('data-answer');
        	var createperson=$(this).attr('data-createperson');
        	var objectName=$('input[name=titlename]').val();
        	$this=$(this);
        	var $holder=$(this).parent().nextAll('.comment-app-holder');
        	if($holder.length>0){
        		$holder.show();
        	}
        	else{
            	$.ajax({
            		type:'POST',
            		url:projectName+"topics/getPartTopicsComment",
            		data:{parentcommid:answerid,type:2,relationidPg:answerid,objectNamePg:objectName,objCreatepersonPg:createperson},
            	    dataType:"json",
            	    success:function(data){
           	    	if(data.returnStatus=='000'){//返回成功
           	    		$.each(data.obj.list,function(index,item){
            	    			if(item.likedUsers!=undefined){
            	    				if(item.likedUsers.indexOf(',')!=-1){
            	    					if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.likedUsers.split(','))!=-1){
            	    						item.likedUsers=1;
            	    					}else{
            	    						item.likedUsers=0;
            	    					}
            	    				}else{
            	    					if(item.likedUsers==(userInfo==undefined?'':userInfo.userid)){
            	    						item.likedUsers=1;
            	    					}else{
            	    						item.likedUsers=0;
            	    					}
            	    					
            	    				}
    	        	    		}else{
    	        	    			item.likedUsers=0;
    	        	    		}
            	    	})
           	    	 	var datamodel={
           	    				answerid:answerid,
           	    				topicscommentList:data.obj.list,
           	    				topiccount:data.obj.list.length,
           	    				userid:userInfo==undefined?'':userInfo.userid,
           	    				createperson:createperson,
           	    				objectName:objectName,
           	    		}
           	    		//加载模板
           	    	 	$('.anscommtemplate').setTemplateURL(projectName+'topicAskCommentTemplate.html');
           	        	$('.anscommtemplate').processTemplate(datamodel);
           	        	$this.parents('.zm-item-meta').append($('.anscommtemplate').html());
           	        	//textarea高度自适应
           	        	autosize(document.querySelectorAll('._InputBox_blur_3JWV'));
           	        	$('.anscommtemplate').empty();
           	        	$('.zg-link').pinwheel();
           	        	$('.Avatar').pinwheel();
           	    	}
           	    	else{
           	    		
           	    	}
           	    }
            	})
        	} 	
        },function(){
        	var $children=$(this).children();
        	//获得评论条数
        	commcount=$(this).parent().next().find('._CommentItem_root_PQNS').size();
        	$(this).empty().append($children).append(commcount+'条评论');
        	$(this).parent().nextAll('.comment-app-holder').hide();
        })
        $(this).trigger('click');
    });
    
    //话题详情--回答里面的点赞(针对每条回答)
    $('#zh-question-answer-wrap .js-thank').live('click',function(){
    	topicLike($(this),'answer');
    })

    $('.CommentItem_like').live('click',function(){
    	topicLike($(this),'answerlike');
    });
    //话题详情--回答里面的评论(评论里面继续评论)
    $('._InputBox_root_1Xwi').live('focus',function(){
    	$(this).removeClass('_InputBox_blur_3JWV').parent().addClass('_CommentForm_expanded_39uD');
    })
    //话题详情--评论里面
    $('.zm-comment-editable').live('focus',function(){
    	$(this).parent().addClass('expanded');
    })
    //话题详情--评论里面(取消)
    $('span[name=closeform]').live('click',function(){
    	closereply($(this));
    });
    //话题详情  新增的评论进行删除
    $('a[name=delcomment]').live('click',function(){
    	cancelnewreply($(this),'comment');
    });
    //话题详情 --回答  对新增的评论进行删除
    $('._CommentItem_root_PQNS .CommentItem_cancel').live('click',function(){
    	cancelnewreply($(this),'answer');
    });
    //话题详情--评论里面继续添加评论
    $('span[name=addnew]').live('click',function(){
    	var createperson=$('input[name=createperson]').val();
    	var name=$('input[name=titlename]').val();
    	var content=$(this).parent().prev().val();
    	//字数不能大于1000
    	var len=content.length+(content.match(/[^\x00-\xff]/g) ||"").length;
    	if(len>1000){
    		if($(this).parent().find('.errortip').length==0)
    			$(this).before('<span class="errortip">请控制在 1000 字以下</span>&nbsp;&nbsp;');
        		return false;
    	}
    	var topicid=$('#content input[name=titleid]').val();
    	if($.trim(content)==''){
    		return false;
    	}
    	$this=$(this);
    	$.ajax({
    		type:'POST',
    		url:projectName+"topics/insertTopicsComment",
    		data:{topicsid:topicid,type:'3',commcontent:content,useid:userInfo.userid,objCreatepersonPg:createperson,objectNamePg:name,relationidPg:topicid},
    	    dataType:"json",
    	    success:function(data){
    	    	if(data.returnStatus=='000'){//返回成功
    	    		var datamodel={
    	    		   topicsComment:data.topicsComment,
    	    		   userinfo:userInfo,
    	    		}
    	    		
    	    		closereply($this);
    	    		//界面添加一条数据(添加模板)
    	    		$('#appendreply').setTemplateURL(projectName+'appendreply.html');
       	    		$('#appendreply').processTemplate(datamodel);
       	    		//判断是否有分页
       	    		if($('.page-inner').length>0){
    	    			$('.page-inner').before($('#appendreply').html());
    	    		}else{
    	    			$('#appendreply').before($('#appendreply').html());
    	    		}
       	    		$('#appendreply').empty();
       	    		intoUserInfo();
       	    		$this.parent().prev().val('');
       	    		$this.parent().find('.errortip').remove();
    	    	}
    	    	else{
    	    		
    	    	}
    	    }
    	})
    })
    //话题详情---回答里面评论  再添加评论
    $('button[name=answeraddnew]').live('click',function(){
    	var content=$(this).parent().prev().val();
    	//字数不能大于1000
    	var len=content.length+(content.match(/[^\x00-\xff]/g) ||"").length;
    	if(len>1000){
    		if($(this).parent().find('.errortip').length==0)
    			$(this).before('<span class="errortip">请控制在 1000 字以下</span>&nbsp;&nbsp;');
        		return false;
    	}
    	var topicid=$('#content input[name=titleid]').val();
    	var parentcommid=$(this).attr('data-parentcommid');
    	var createperson=$(this).attr('data-createperson');
    	var objectName=$('input[name=titlename]').val();
    	if($.trim(content)=='') return false;
    	$this=$(this);
    	$.ajax({
    		type:'POST',
    		url:projectName+"topics/insertTopicsComment",
    		data:{topicsid:topicid,type:'2',commcontent:content,useid:userInfo.userid,parentcommid:parentcommid,relationidPg:parentcommid,objCreatepersonPg:createperson,objectNamePg:objectName},
    	    dataType:"json",
    	    success:function(data){
    	    	if(data.returnStatus=='000'){//返回成功
    	    		var datamodel={
    	    		   topicsComment:data.topicsComment,
    	    		   userinfo:userInfo,
    	    		   parentcommid:parentcommid,
    	    		}
    	    		answerreplyCancel($this);
    	    		//界面添加一条数据(添加模板)
    	    		$('#appendanswerreplys').setTemplateURL(projectName+'appendAnswerTemplate.html');
       	    		$('#appendanswerreplys').processTemplate(datamodel);
       	    		$this.parents('._CommentForm_root_1-ko').prev().append($('#appendanswerreplys').html());
       	    		$('#appendanswerreplys').empty();
       	    		intoUserInfo();
       	    		$this.parent().find('.errortip').remove();
       	    		$this.parent().prev().val('');
    	    	}
    	    	else{
    	    		
    	    	}
    	    }
    	})
    });
    
    //话题详情--评论里面针对每条评论回复
    $('.zm-comment-submit-comment').live('click',function(){	
    	var content=$(this).parent().prev().find('.pj-replaycontent').val();
    	//字数不能超过一千字
    	var len=content.length+(content.match(/[^\x00-\xff]/g) ||"").length;
    	if(len>1000){
    		if($(this).parent().find('.errortip').length==0)
    		   $(this).before('<span class="errortip">请控制在 1000 字以下</span>&nbsp;&nbsp;');
        	   return false;
    	}
    	var topicid=$('#content input[name=titleid]').val();
    	//被评论人的id 和名字
    	var commentby=$(this).attr('data-commentid');
    	var commentname=$(this).attr('data-name');
    	
    	//当前回复的评论
    	var objectname=$('input[name=titlename]').val();
    	var relationid=$(this).attr('data-relationid');
    	
    	if($.trim(content)=='')return false;
    	$this=$(this);
    	$.ajax({
    		type:'POST',
    		url:projectName+"topics/insertTopicsComment",
    		data:{topicsid:topicid,type:'3',commcontent:content,useid:userInfo.userid,commentby:commentby,objCreatepersonPg:commentby,relationidPg:relationid,objectNamePg:objectname},
    	    dataType:"json",
    	    success:function(data){
    	    	if(data.returnStatus=='000'){//返回成功
    	    		var datamodel={
    	    		   topicsComment:data.topicsComment,
    	    		   userinfo:userInfo,
    	    		   commentby:commentby,
    	    		   commentname:commentname,
    	    		}
    	    		//关闭文本框
    	    		$this.parent().parent().css('display','none');
    	    		$this.parent().prev().children(':first').html('');
    	    		//界面添加一条数据(添加模板)
    	    		$('#appendreply').setTemplateURL(projectName+'appendreply.html');
       	    		$('#appendreply').processTemplate(datamodel);
       	    		if($('.page-inner').length>0){
       	    			$('.page-inner').before($('#appendreply').html());
       	    		}else{
           	    		$this.parents('.zm-comment-list').append($('#appendreply').html());
       	    		}
       	    		$('#appendreply').empty();
       	    		intoUserInfo();
       	    	    //发布成功之后去掉提示信息
            	    $this.parent().find('.errortip').remove();
    	    	}
    	    	else{
    	    		
    	    	}
    	    }
    	})
    })
    
    //话题详细---回答里面针对每条评论进行回复
     $('.zm-comment-submit-answer').live('click',function(){
    	var content=$(this).parent().prev().find('.pj-replaycontent').val();
    	//字数不能超过一千字
    	var len=content.length+(content.match(/[^\x00-\xff]/g) ||"").length;
    	if(len>1000){
    		if($(this).parent().find('.errortip').length==0)
    		   $(this).before('<span class="errortip">请控制在 1000 字以下</span>&nbsp;&nbsp;');
        	   return false;
    	}
     	var topicid=$('#content input[name=titleid]').val();
     	var commentby=$(this).attr('data-commentid');
     	var commentname=$(this).attr('data-name');
     	var parentcommid=$(this).attr('data-parentcommid');
     	var relationid=$(this).attr('data-relationid');
     	var objectName=$('input[name=titlename]').val();
     	if($.trim(content)==''){
     		return false;
     	}
     	$this=$(this);
     	$.ajax({
    		type:'POST',
    		url:projectName+"topics/insertTopicsComment",
    		data:{topicsid:topicid,type:'2',commcontent:content,useid:userInfo.userid,commentby:commentby,parentcommid:parentcommid,objectNamePg:objectName,relationidPg:relationid,objCreatepersonPg:commentby},
    	    dataType:"json",
    	    success:function(data){
    	    	if(data.returnStatus=='000'){//返回成功
    	    		var datamodel={
    	    		   topicsComment:data.topicsComment,
    	    		   userinfo:userInfo,
    	    		   commentby:commentby,
    	    		   commentname:commentname,
    	    		   parentcommid:parentcommid,
    	    		}
    	    		//关闭文本框
    	    		$this.parent().parent().css('display','none');
    	    		$this.parent().prev().children(':first').html('');
    	    		//界面添加一条数据(添加模板)
    	    		$('#appendanswerreplys').setTemplateURL(projectName+'appendAnswerTemplate.html');
       	    		$('#appendanswerreplys').processTemplate(datamodel);
       	    		$this.parents('._CommentBox_list_10_K').append($('#appendanswerreplys').html());
       	    		$('#appendanswerreplys').empty();
       	    		intoUserInfo();
       	    	    //发布成功之后去掉提示信息
            	    $this.parent().find('.errortip').remove();
            	    $this.parent().prev().find('.pj-replaycontent').val('');
    	    	}
    	    	else{
    	    		
    	    	}
    	    }
    	})
     })
    //话题详情--回答里面的评论(评论里面继续评论)--取消
    $('._CommentForm_cancelButton_cy3u').live('click',function(){
    	answerreplyCancel($(this));
    })
    //话题详情评论加载列表
    $('#topiccomment').toggle(function(){
    	var $child=$(this).children();
    	$(this).empty().append($child).append('收起评论');
    	var createperson=$('input[name=createperson]').val();
    	var topicname=$('input[name=titlename]').val();
    	var topicid=$('#content input[name=titleid]').val();
    	$this=$(this);
    	var $container=$(this).parents('.zm-meta-panel').nextAll('.panel-container')
    	//判断是否存在
    	if($container.length>0){
    		$container.show();
    	}
    	else{
        	$.ajax({
        		type:'POST',
        		url:projectName+"topics/getPartTopicsComment",
        		data:{topicsid:topicid,type:'3'},
        	    dataType:"json",
        	    success:function(data){
        	      if(data.returnStatus=='000'){//返回成功
        	    	//判断当前用户是否点赞
        	    	$.each(data.obj.list,function(index,item){
        	    		if(item.likedIds!=undefined){
        	    			if(item.likedIds.indexOf(',')!=-1){
        	    				if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.likedIds.split(','))!=-1){
            						item.likedIds=1;
            					}else{
            						item.likedIds=0;
            					}
        	    			}
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
        	    	if(data.obj.last>1){
        	    		data.obj.last=1;
        	    	}else{
        	    		data.obj.last=0;
        	    	}
        	    	var topicdata={
        	    			topicid:topicid,
       	    				topicscommentList:data.obj.list,
       	    				topiccount:data.obj.count,
       	    				userid:userInfo==undefined?userInfo.userid:'',
       	    				last:data.obj.last,
       	    				createperson:createperson
       	    		}
       	    		$('.pagetemplate').setTemplateURL(projectName+'topicCommentTemplate.html');
        	    	$('.pagetemplate').processTemplate(topicdata);

       	    		$this.parents('.zm-item-meta').append($('.pagetemplate').html());
       	    		$('.pagetemplate').empty();
       	    		autosize(document.querySelectorAll('.pj-replaycontent'));
       	    		$('.zm-item-link-avatar').pinwheel();
       	            $('.zg-link').pinwheel();
       	            //为了分页
       	    		$.getScript('/51jobplusCore/scripts/jquery.simplePagination.js',function(){
       	    			$("#topiccompaging").pagination({
       	    				items:data.obj.count,
       	    				itemsOnPage:data.obj.pageSize,
       	    				cssStyle:'light-theme',
       	    				moduleType:'topiccommentlist'
       	    			})
       	    		});

        	    	}
        	    	else{
        	    		
        	    	}
        	    }
        	})
    	}
    	
    },function(){
    	var commentcount=$(this).parent().nextAll('.panel-container').find('.zm-item-comment').size();
    	var $children=$(this).children();
    	$(this).empty().append($children).append(commentcount+'条评论');
    	$(this).parents('.zm-meta-panel').nextAll('.panel-container').hide();
    })
    //话题详情评论里面的每条评论点击回复
    $('.zm-comment-ft .reply').live('click',function(){
    	appendreply($(this),'comment');
    })
    //话题详情评论里面的每条评论点赞
    $('.zm-comment-ft .like').live('click',function(){
    	topicLike($(this),'comment');
    })
    
    //话题详情评论里面的每条评论点击回复(取消)
    $('.zm-comment-form .zm-comment-close').live('click',function(){
    	$(this).parents('.zm-comment-form').hide();
    });
    //话题详情回答里面点击回复
    $('.CommentItem_reply').live('click',function(){
    	appendreply($(this),'answer');
    })
   //话题详情 回答--举报
    $('#zh-question-answer-wrap .js-report').live('click',function(){
    	topicReport($(this));
    })
    //针对话题的举报
    $('#zh-question-meta-wrap [name=report-question]').live('click',function(){
    	topicReport($(this));
    });
    //话题详情评论里面的每条评论举报
    $('.zm-comment-ft .report').live('click',function(){
    	topicReport($(this));
    });
    //话题回答 评论里面举报
    $('.CommentItem_report').live('click',function(){
    	topicReport($(this));
    });

    //发布回答
    $('.submit-button').live('click',function(){
    	var topicsid=$(this).attr('data-id');
    	//关联的主体名称(话题名称)
    	var topicname=$('input[name=titlename]').val();
    	var objCreateperson=$('input[name=createperson]').val();
        /*var commcontent=$('.publishanswer .edui-body-container').html();*/
    	var commcontent=editor.getContent();
    	//判断输入的字节大小
    	var len=commcontent.length+(commcontent.match(/[^\x00-\xff]/g) ||"").length;
    	if(len>10000){
    		if($(this).parent().find('.errortip').length==0)
    		$(this).before('<span class="errortip">请控制在 10000 字以下</span>&nbsp;&nbsp;');
    		return false;
    	}
    	var $this=$(this);
    	var isPublic=$('input[type=checkbox]').is(':checked')?0:1;
    	if(!UM.getEditor('uEditorCustom').hasContents())return false;
    	$.ajax({
    		type:"POST",
    		url:projectName+"topics/insertTopicsComment",
    		data:{commcontent:commcontent,type:1,topicsid:topicsid,isPublic:isPublic,objCreatepersonPg:objCreateperson,relationidPg:topicsid,objectNamePg:topicname},
    	    dataType:"json",
            success:function(data){
            	if(data.returnStatus=='000'){//返回成功
            		data.topicsComment.showcreatetime=data.topicsComment.showcreatetime.substring(11);
            		//界面加载数据
            		var datamodel={
            			userid:userInfo.userid,
            			username:userInfo.username,
            			headicon:userInfo.headicon,
            			topicsComment:data.topicsComment,
            			commcontent:commcontent,
            			ispublic:isPublic,
            		}
            		$('.anscommtemplate').setTemplateURL(projectName+'answerTemplate.html',null, {filter_data: false});
            		$('.anscommtemplate').processTemplate(datamodel);
            		if($('.loadmore').length>0){
            			$('.loadmore').before($('.anscommtemplate').html());
            		}else{
            			$('#zh-question-answer-wrap').append($('.anscommtemplate').html());
            		}
            		
            		$('.anscommtemplate').empty();
            		$('.publishanswer .edui-body-container').empty();
            		 intoUserInfo();
            		//发布成功之后去掉提示信息
            		$this.parent().find('.errortip').remove();
                }
            	else{
            		
            	}
            }
    	})
    })
    //关注人
    $('.operation .zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })
    //关注问题
    $('#topicfollowanswer').live('click',function(){
    	var createperson=$('input[name=createperson]').val();
    	var titleid=$('input[name=titleid]').val();
    	var titlename=$('input[name=titlename]').val();
    	topicFollow($(this),1,undefined,createperson,titleid,titlename)
    })
    
   //回到顶部
   $("#topicbacktop").mousemove(function(){
    	$("#topicbacktop").css("background-position-x", "-28px");
    }).mouseleave(function(){
    	$("#topicbacktop").css("background-position-x", "0");
    })
    
    /*当界面下拉到一定位置出现向上的箭头 start*/
    $(window).scroll(function(){  
        if ($(window).scrollTop()>100){  
            $("#topicbacktop").fadeIn("fast");  
        }  
        else  
        {  
            $("#topicbacktop").fadeOut("fast");  
        }  
    });
    /*当界面下拉到一定位置出现向上的箭头 end*/
   
    //话题详情加载更多
    $('.loadmore').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	topicLoadMore($(this))
    });
    
    /*--start--*/
    //当子元素设置了绝对定位  父元素设置了相对定位   父元素是无法自动适应高度的  因为脱离了文档流    只能用代码实现
    $('#zh-question-answer-form-wrap .edui-btn-emotion').live('click',function(e){
    	$('#zh-question-answer-form-wrap').css('height','500px');
    });
    $(document).click(function(e){
	  if($(e.target).hasClass('edui-icon-emotion')!=$('.edui-icon-emotion').hasClass('edui-icon-emotion')){
		  $('#zh-question-answer-form-wrap').css('height','308px');
	  }
    })
    //定时刷新界面去掉因上面设置高度
    setInterval("cancelHeight()",1000); 
    /*--end--*/
})
//定时刷新界面去掉因上面设置高度
function cancelHeight() { 
  if($('#zh-question-answer-form-wrap .edui-popup-emotion').is(':hidden')){
	  $('#zh-question-answer-form-wrap').css('height','308px');
  }
} 
//话题详情加载更多
function topicLoadMore(obj){
	var pageNo=obj.attr('data-pageno');
    var sumpage=obj.data('sumpage');
    var sorttype=$('input[name=sorttype]').val();
    var topicid=$('#content input[name=titleid]').val();
    $.ajax({
    	type:"POST",
      	url:projectName+"topics/getSortTopicsCommentsByTopicId",
      	data:{pageNo:Number(pageNo)+1,topicsid:topicid,sortType:sorttype},
    	dataType:"json",
    	success:function(data){
    		if(data.returnStatus=='000'){//返回成功
    			 $.each(data.obj.list,function(index,item){
  	    			if(item.likedIds!=undefined){
  	    				if(item.likedIds.indexOf(',')!=-1){
  	    					if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.likedIds.split(','))!=-1){
  	    						item.likedIds=1;
  	    					}else{
  	    						item.likedIds=0;
  	    					}
  	    				}else{
  	    					if(item.likedIds==(userInfo==undefined?'':userInfo.userid)){
  	    						item.likedIds=1;
  	    					}else{
  	    						item.likedIds=0;
  	    					}
  	    					
  	    				}
      	    		}else{
      	    			item.likedIds=0;
      	    		}
  	    	    })
    			var datamodel={
    			   topiclist:data.obj.list,
    			   userid:userInfo.userid,
    		    }
    		   $('.headiconintotem').setTemplateURL(projectName+'topicLoadMoreTemplate.html',null, {filter_data: false});
          	   $('.headiconintotem').processTemplate(datamodel);
          	   $('.loadmore').before($('.headiconintotem').html());
          	   $(".headiconintotem").empty();
          	   $('.loadmore').attr('data-pageno',Number(pageNo)+1);
          	   obj.removeClass('loading').empty().append('更多');
          	   if(Number(sumpage)==Number(pageNo)+1)
          		 $('.loadmore').hide();
          	   intoUserInfo();
    		}else{
    			
    		}
    	}
    })
}
//关掉评论
function closereply(obj){
	obj.parents('.zm-comment-form').removeClass('expanded');
	obj.parents('.zm-comment-form').children(':first');
}

//显示隐藏
function titledisplayorhide(){
	$('#zh-question-title h2 .zm-editable-content').css('display','block');
    $('#zh-question-title h2 .pj-editable-editor-wrap').remove();
}
function titlecondisplayorhide(){
	$('#zh-question-detail .zm-editable-content').show();
	$('#zh-question-detail .edui-container').hide();
	$('#zh-question-detail .zm-command').hide();
}
 //批量删除话题 --分享  
function deleteTopics(conditions,obj){
	/*   var s = JSON.stringify(conditions);
	   console.log(s)*/;
  	   $.ajax({
         	type:"POST",
         	url:projectName+"myCenter/deleteTopics",
         	data:{condition:conditions},
         	dataType:"json",
         	success:function(data){
         		if(data.returnStatus=='000'){//返回成功
            		console.log(data);
            		$('.remove ').removeClass('enabled');
            		if(conditions.toString().indexOf(',')!=-1){
            			obj.parent().nextAll('.docs-list').find('.select-icon').each(function(){
                			//从界面上移除当前
                			$(this).parents('li').remove();
                		});
            		}
            		else{
            			//判断是否是批量删除 但只删除了一条
            			if(obj.hasClass('remove')){
            				obj.parent().nextAll('.docs-list').find('.select-icon').parents('li').remove();
            			}else{
            				obj.parents('li').remove();
            			}
            			
            		}
            		$('#topicshare').html("分享("+data.operationSum.topicssharesum+")");

        		}else{//返回失败

        		}

        	}
     });
  }
//批量删除话题 --已关注  
function deleteAttentions(conditions,obj){
	 /*var s = JSON.stringify(conditions);
	   console.log(s);*/
	   $.ajax({
       	type:"POST",
       	url:projectName+"myCenter/deleteAttentions",
       	data:{condition:conditions},
       	dataType:"json",
       	success:function(data){
       		if(data.returnStatus=='000'){//返回成功
          		console.log(data);
          		$('.remove ').removeClass('enabled');
          		if(conditions.toString().indexOf(',')!=-1){
          			obj.parent().nextAll('.docs-list').find('.select-icon').each(function(){
            			//从界面上移除当前
            			$(this).parents('li').remove();
            		});
          		}
          		else{
          			/*obj.parents('li').remove();*/
          			if(obj.hasClass('remove')){
        				obj.parent().nextAll('.docs-list').find('.select-icon').parents('li').remove();
        			}
          		}

             $('#topicattention').html("关注("+data.operationSum.topicsattesum+")");
      		}else{//返回失败

      		}

      	}
   });
}

//批量删除话题 --回复
function deleteTopicsComments(conditions,obj){
	   $.ajax({
       	type:"POST",
       	url:projectName+"myCenter/deleteTopicsComments",
       	data:{condition:conditions},
       	dataType:"json",
       	success:function(data){
       		if(data.returnStatus=='000'){//返回成功
          		
          		$('.remove ').removeClass('enabled');
          		if(conditions.indexOf(',')!=-1){
          			obj.parent().nextAll('.docs-list').find('.select-icon').each(function(){
            			//从界面上移除当前
            			$(this).parents('li').remove();
            		});
          		}
          		else{
          		//判断是否是批量删除 但只删除了一条
        			if(obj.hasClass('remove')){
        				obj.parent().nextAll('.docs-list').find('.select-icon').parents('li').remove();
        			}else{
        				obj.parents('li').remove();
        			}
          		}
          		$('#topicreplay').html("回复("+data.operationSum.topicscomsum+")");
          		
      		}else{//返回失败
      			
      		}
      		
      	}
   });
}

//根据条件查询(标签) url:"tags/findClass/"+condition
function getTagsByCondition(obj){
	var conds=$(obj).val();
	//过滤掉空格 "" null 等
	if (conds !== null || conds !== undefined || conds !== '') { 
		//过滤掉正在输入汉字的情况
		if($.trim(conds).length>0){
		 $.ajax({
	         	type:"POST",
	         	url:projectName+"tags/findClass/"+conds,
	         	//data:{condition:100},
	         	dataType:"json",
	         	success:function(data){
	         		if(data.returnStatus=='000'){//返回成功
	            		console.log(data);
	         			/*initTagBySearchTopic(data);	*/
	            		$("#topictag").chosen(data,'','topictag',conds);
	        		}else{//返回失败
	        			
	        		}
	        		
	        	}
	     });
		} 
	   else{
			//当input为空时 隐藏搜索的下拉列表
		   $(".zm-tag-editor-command-buttons-wrap div:last-child").remove();
		}
	}else{//输入值 为   空格 "" null 等
		
	}   		
}
//话题编辑
function updateTopics(){
	   $.ajax({
     	type:"POST",
     	url:projectName+"/myCenter/updateTopics",
     	data:{id:2,topicsclass:"测试话题标签",content:"<p><img src='http://192.168.0.39:8199/2016/07/01/1fe0d531d23540d99e94e6001bd46d9f.png' _src='http://192.168.0.39:8199/2016/07/01/1fe0d531d23540d99e94e6001bd46d9f.png'/></p>",topicsclass:"100:语文"},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			
        		console.log(data);
        		alert("success");
        		
    		}else{//返回失败
    			alet("failed");
    		}
    		
    	}
 });
} 

//点赞
function topicLike(obj,type){
	var like=obj.attr('data-like');
	var commid=obj.attr('data-topiccommentid');
	var createperson=obj.attr('data-createperson');
	var objectName=$('input[name=titlename]').val();
	var topObjId=$('input[name=titleid]').val();
	var isLiked='';
	$this=obj;
	//为空说明还没点赞  0代表去点赞   1是取消点赞
	if(like=='1'){
		isLiked=0;
	}
	else{
		isLiked=1;
	}
	$.ajax({
		type:"POST",
     	url:projectName+"myCenter/clickOnLike",
     	data:{id:commid,likeOperate:isLiked,objectNamePg:objectName,objCreatepersonPg:createperson,relationidPg:commid,topObjId:topObjId},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){
     			var $children =$this.children();
     			if(type=='answer'){
     				if(isLiked==0){
     					$this.attr('data-like','0');
     					$this.attr('data-likecount',Number($this.attr('data-likecount'))+1);
	     				$this.empty().append($children).append('取消赞('+$this.attr('data-likecount')+")");
	     				
     				}
     				else{
     					$this.attr('data-like','1');
     					$this.attr('data-likecount',Number($this.attr('data-likecount'))-1);
     					$this.empty().append($children).append($this.attr('data-likecount')+'赞');
     				}
     			}
     			else if(type=='comment'){
         			var $like=$this.parents('.zm-comment-ft').find('._CommentItem_likes_2hey').find('span:first');
         			if(isLiked==0){
         				$this.attr('data-like','0');
         				$this.empty().append($children).append('取消赞');
         				//点赞量加1
         				$like.text(Number($like.text())+1)
         			}
         			else{
         				$this.attr('data-like','1');
         				$this.empty().append($children).append('赞');
         				$like.text(Number($like.text())-1)
         			}
     			}
     			else{
     				var $like=$this.parents('._CommentItem_body_3qwB').find('._CommentItem_likes_2hey').find('span:first');
         			if(isLiked==0){
         				$this.attr('data-like','0');
         				$this.empty().append($children).append('取消赞');
         				//点赞量加1
         				$like.text(Number($like.text())+1)
         			}
         			else{
         				$this.attr('data-like','1');
         				$this.empty().append($children).append('赞');
         				$like.text(Number($like.text())-1)
         			}
     			}
     		}
     		else{
     			
     		}
     	}
	})
}

//追加回复
function appendreply(obj,type){
	if(obj.parent().next().next().length>0){
		obj.parent().next().next().show()
	}
	else{
		//针对回答里面的每条评论的回复获取评论的id
		if(type=='answer')
		var parentcommid=obj.attr('data-parentcommid');
		
    	var commentbyid=obj.attr('data-commentbyid');//被回复人的ID
    	var commentid=obj.attr('data-commentid');//当前评论的ID
    	var commentname=obj.attr('data-name');//当前评论人的名字
    	var objectname=$('input[name=titlename]').val();//当前回复的评论
    	var relationid=obj.attr('data-relationid');//当前回复的评论的id
    	var $reply=obj.parent().next();
    	var data={
				commentbyid:commentbyid,
				commentid:commentid,
				commentname:commentname,
				commenttype:type,
				parentcommid:parentcommid,
				objectname:objectname,
				relationid:relationid,
		}
		//加载模板
    	$reply.setTemplateURL(projectName+'replyTemplate.html');
    	$reply.processTemplate(data);
    	if(type=='comment')//话题评论详情里面的回复点击
    	    obj.parents('.zm-comment-content-wrap').append($reply.html());
    	else{
    		obj.parents('._CommentItem_body_3qwB').append($reply.html());
    	}
    	$reply.empty();
    	autosize(document.querySelectorAll('.pj-replaycontent'));
    	intoUserInfo();
	}
}

//新增的评论里的回复进行删除
function cancelnewreply(obj,type){
	var operatetype=0;//是话题评论删除  还是回答里面的评论删除
	if(type=='comment'){
		operatetype=3;
	}else{
		operatetype=2;
	}
	var replyid=obj.attr('data-reply');
	var topicsid=$('#content input[name=titleid]').val();
	var parentcommId=obj.attr('data-answer');
	$this=obj;
	$.ajax({
       	type:"POST",
       	url:projectName+"topics/delCommentOnTopDetail",
       	data:{id:replyid,type:operatetype,topicsid:topicsid,parentcommid:parentcommId},
       	dataType:"json",
       	success:function(data){
       		if(data.returnStatus=='000'){
       			if(type=='comment')
       			$this.parents('.zm-item-comment').remove();
       			else
       			$this.parents('._CommentItem_root_PQNS').remove();	
       		}
       		else{
       			
       		}
       	}
	})
}
//回答里面继续评论  点击取消
function answerreplyCancel(obj){
	obj.parents('._CommentForm_root_1-ko').removeClass('_CommentForm_expanded_39uD');
	obj.parents('._CommentForm_root_1-ko').children(':first').empty().addClass('_InputBox_blur_3JWV')
}

//加载用户信息
function intoUserInfo(){
	$('.zm-list-avatar,.author-link,.zm-item-link-avatar,.zg-link,.zm-item-img-avatar,.Avatar-s').pinwheel();
}