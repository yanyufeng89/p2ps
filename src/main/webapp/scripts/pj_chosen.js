//doc 文档
//topic 话题
//topictag 话题详情
//skill 个人主页技能
var upOrDownCount=0;
var container_div='';
var container_each='';

(function() {
	var Chosen,acid;
	$.fn.extend({
	    chosen: function(data,obj,type,conds) {
	    	return new Chosen(data,obj,type,conds);
	    },
	  });
	 Chosen=(function(){
		    function Chosen(data,obj,type,conds){
		    	this.data = data;	
				this.id=generate_field_id();
				acid=this.id
				console.log(this.id);
				this.obj=obj;
				this.type=type;
				this.conds=conds;
				this.initDropdown();
		    }
		    
			//初始化下拉列表
		    Chosen.prototype.initDropdown=function(){
		    	container_div='';
		    	container_each='';
		    	if(this.type=='skill'){
		    		    var mark=false;
		    		    this.obj.parent().find('div:last-child').remove();
		    		    container_div+="<div id='"+this.id+"' class='ac-renderer tagdropdown' style='display:block'>"
			            $this=this;
		    		    $.each($this.data.skillList,function(index,item){
							if($.trim(item.skillname)==$.trim($this.conds)){
								mark=true;
							}
						})
						if($this.data.length==0||!mark){
							container_div+="<div class='ac-create ac-tag ac-active createtag' data-name='"+$this.conds+"'>"
						    container_div+="   <span class='zu-autocomplete-row-name textoverflow'>创建&nbsp;'"+$this.conds+"'&nbsp;技能</span>"
						    container_div+="</div>"	
						}
		    		    $.each($this.data.skillList,function(a,b){
							if(a==0&&mark){
								container_div+="<div class='ac-row ac-tag ac-active' id='"+generate_field_id()+"'  data-name='"+b.skillname+"' data-id='"+b.id+"'>"
							}else{
								container_div+="<div class='ac-row ac-tag'  id='"+generate_field_id()+"' data-name='"+b.skillname+"' data-id='"+b.id+"'>"
							}
							container_div+="      <span class='zu-autocomplete-row-name textoverflow'>"+b.skillname+"</span>"
							container_div+="    </div>"
						})
						container_div+="</div>";
						$(".zm-tag-editor-command-buttons-wrap").append(container_div);	
						//计算新添加层的高度
		    		    $('#skill0').removeAttr('style').height($('#skill0').height()+$('#'+$this.id).height());
						$this.container_click();
		    	}else{
		    		    if(this.type=='topic'||this.type=='topictag')
				    	   $(".zm-tag-editor-command-buttons-wrap div:last-child").remove();
				    	if(this.type=='doc'||this.type=='book')
				           this.obj.parent().find('div:last-child').remove();
						var flag=false;
						container_div+="<div id='"+this.id+"' class='ac-renderer tagdropdown' style='display:block'>"
			            $this=this;
						$.each($this.data['tagsList'],function(index,item){
							if($.trim(item.tagname)==$.trim($this.conds)){
								flag=true;
							}
						})
						if($this.data['tagsList'].length==0||!flag){
							container_div+="<div class='ac-create ac-tag ac-active createtag' data-name='"+$this.conds+"'>"
						    container_div+="   <span class='zu-autocomplete-row-name textoverflow'>创建&nbsp;'"+$this.conds+"'&nbsp;标签</span>"
						    container_div+="</div>"	
						}
						$.each($this.data['tagsList'],function(a,b){
							if(a==0&&flag){
								container_div+="<div class='ac-row ac-tag ac-active' id='"+generate_field_id()+"'  data-name='"+b.tagname+"' data-id='"+b.id+"'>"
							}else{
								container_div+="<div class='ac-row ac-tag'  id='"+generate_field_id()+"' data-name='"+b.tagname+"' data-id='"+b.id+"'>"
							}
							container_div+="      <span class='zu-autocomplete-row-name textoverflow'>"+b.tagname+"</span>"
							container_div+="    </div>"
						})
						container_div+="</div>";
						if($this.type=='topic'||$this.type=='topictag')
						   $(".zm-tag-editor-command-buttons-wrap").append(container_div);		
						if($this.type=='doc'||$this.type=='book')
							$this.obj.parent().append(container_div);		
						$this.container_click();	
		    	}
			}
		    
		    Chosen.prototype.container_click=function(){
		    	var name='';
		    	var id='';
		    	if(this.type=='topic'||this.type=='topictag'){
		    		$("#"+$this.id+" .ac-row").bind('click',function(){
			    		 name=$(this).data('name');
			    		 id=$(this).data('id');
			    		 $this.iniTopicIputTags(name,id,$this.type);
					})
					$('#searchTopic').on('click',function(e){
			    		 $("#"+$this.id).show();
			    		 e.stopPropagation();
			    	});
		    		$(document).click(function(){
					    $("#"+$this.id).hide();
					});
		    	}
		    	if(this.type=='skill'){
		    		var skillname='';
		    		var skillid='';
		    		$("#"+$this.id+" .ac-row").bind('click',function(){
		    			skillname=$(this).data('name');
		    			skillid=$(this).data('id');
			    		$this.iniSkillsIputTags(skillname,skillid);
					})
					$(".zu-question-suggest-skill-input").on('click',function(e){
						//技能重新计算高度
						$('.background-skills-container').height($('#skill0').height()+$(this).parent().find('.ac-renderer').height());
						$(this).parent().find('.ac-renderer').show();
						$this.obj=$(this);
			    		e.stopPropagation();
			    	});
		    		$(document).click(function(){
		    			$('.zu-question-suggest-skill-input').parent().find('.ac-renderer').hide();
		    			$('#skill0').removeAttr('style').css('min-height','150px');
					});
		    	}
		    	if(this.type=='doc'||this.type=='book'){
		    		$this.obj.next().next().find('.ac-row').bind('click',function(event){
			    		 name=$(this).data('name');
			    		 id=$(this).data('id');
			    		 $this.iniDocIputTags(name,id);
					})
					$(".zu-question-suggest-topic-input").on('click',function(e){
						$(this).parent().find('.ac-renderer').show();
						$this.obj=$(this);
			    		e.stopPropagation();
			    	});
		    		$(document).click(function(){
		    			$('.zu-question-suggest-topic-input').parent().find('.ac-renderer').hide();
					});
		    	}
		        			    	
				$("#"+$this.id+" .ac-tag").bind('hover',function(){
		    		$(this).addClass('ac-active').siblings().removeClass('ac-active');
				})

		    }
		  
		    //话题标签(新增)
		    Chosen.prototype.iniTopicIputTags=function(name,id,type){
		    	$('.pj-warmprompt').hide();
		    	var html='';
		    	html+="<div class='zm-tag-editor-edit-item'>";
		    	html+=" <span>"+name+"</span>"
		    	html+=" <a id='"+id+"' data-name='"+name+"' class='zm-tag-editor-remove-button' name='remove'></a>"
		    	html+="</div>";	
		    	if(type=='topic'){
		    	    $('#inputtags').append(html);
			    	$("#"+$this.id).remove();
			    	$('#searchTopic').val('').focus();
			    	$('#inputtags a[name=remove]').bind('click',function(){
						$(this).parent().remove();
						$('#searchTopic').show().focus().prev().show();
			    		$('#searchTopic').parent().find('.err-tip').hide();
			    		
			    		if($("#inputtags").children().length==0){
			    			var $child=$('.pj-warmprompt').children();
			    			$('.pj-warmprompt').empty().append($child).append('至少添加一个标签').show();
			    		}
					});
			    	if($("#inputtags>div").length==5){
			    		$('#searchTopic').hide().prev().hide();
			    		$('#searchTopic').parent().find('.err-tip').show();
			    	}
		    	}
		    	else{
		    		$('#topictagedit').append(html);
		    		$('.zm-tag-editor-labels').children(':first').after('<a class="zm-item-tag" href="" data-topicid='+id+'>'+name+'</a>')
		    		$("#"+$this.id).remove();
			    	$('#topictag').val('').focus();
			    	
			    	if($("#topictagedit>div").length==5){
			    		$('#topictag').parent().hide();
			    		$('#topictag').parent().next().show();
			    	}
			    	addTagToDatabase();
		    	}
		    	$this.obj.parent().find('input[name=currenttagval]').val('');
		    }
		    //技能
			Chosen.prototype.iniSkillsIputTags=function(skillname,skillid){
				$('.skill-error').hide();
				if(!isExistSkill(skillname)){
					return false;
				};
				if($('#skillinputtags>div').length>50){
					var $child=$('.skill-error').children();
					$('.skill-error').empty().append($child).append('最多只能添加50个技能').show();
					return false;
				}
				var html='';
		    	html+="<div class='zm-tag-editor-edit-item'>";
		    	html+=" <span>"+skillname+"</span>"
		    	html+=" <a id='"+skillid+"' data-name='"+skillname+"' class='zm-tag-editor-remove-button' name='removeskill'></a>"
		    	html+="</div>";	
		    	$('#skillinputtags').append(html);
			    $("#"+$this.id).remove();
			    $('#searchSkill').val('').focus();
			    
			    $('#skillinputtags a[name=remove]').bind('click',function(){
					$(this).parent().remove();
					$('#searchSkill').show().focus().prev().show();
		    		$('#searchSkill').parent().find('.err-tip').hide();
				});
			    $('#skill0').removeAttr('style').css('min-height','150px');
			}
		    //文档
			Chosen.prototype.iniDocIputTags=function(name,id){
				$('.pj-warmprompt').hide();
				$this.obj.parents('.zm-tag-editor-editor').next().hide();
                var html='';
			    
			    html+="<div class='zm-tag-editor-edit-item'>";
		    	html+=" <span>"+name+"</span>"
		    	html+=" <a id='"+id+"' data-name='"+name+"' class='zm-tag-editor-remove-button' name='remove'></a>"
		    	html+="</div>";
		    	
		    	$this.obj.val('').focus();
		    	$this.obj.parent().find('.ac-renderer').remove();
		    	$this.obj.parent().parent().find('.zg-inline').append(html);
		    	$this.obj.parent().find('.err-tip').hide();
		    	
		    	 //标签删除
		        $('.zg-inline a[name=remove]').bind('click',function(){
		       		var $floor=$(this).parents('.zg-inline').next();
		       		/*$floor.find(':first-child').show();*/
		       		$floor.find('input[type=text]').show();
		       		$floor.find(':last-child').hide();
		       		$(this).parent().remove();
		       	});
		        
		    	if($this.obj.parents('.zm-tag-editor-editor').find('.zg-inline>div').length==5){
		    		$this.obj.hide().prev().hide();
		    		$this.obj.parent().find('.err-tip').show();
		    	}
		    	$this.obj.parent().find('input[name=currenttagval]').val('');
			}
			
			return  Chosen;
		})()
	    //话题  书籍 文章 站点 课程  技能
        $('#searchTopic,.zu-question-suggest-topic-input,#topictag,#searchSkill').live('keydown',function(event){ 
        	upOrDownConvert($(this));
        });
        //创建新的标签
        $('.createtag').live('click',function(){
        	createtag($(this));
        })
	 
}).call(this);

//键盘上下键切换
function upOrDownConvert(obj){
		var $len=obj.nextAll('.tagdropdown').children().length;  
		var key = event.keyCode;  
		if (key == 38) { /*向上按钮*/  
			upOrDownCount--;  
			if (upOrDownCount ==-1) upOrDownCount = $len-1; //到顶了
	    } else if (key == 40) {/*向下按钮*/  
	    	upOrDownCount++;  
	    	if (upOrDownCount ==$len) upOrDownCount =0; //到底了   
	    }else if(key == 13){//回车键
	    	upOrDownCount=0;
	    	//创建标签 
	    	//获取当前有类ac-active 同时又类ac-create 说明是创建一个新的标签
	    	var $children=obj.nextAll('.tagdropdown');
	    	if($children.find('.ac-create')&&$children.find('.ac-create').hasClass('ac-active')){
	    		createtag($children.find('.ac-create'));
	    	}else{
	    		var tagnames=$children.find('.ac-active').attr('data-name');
	    		var tagid=$children.find('.ac-active').attr('data-id');
	    		if($.trim(tagnames).length!=0){
	    			if($this.type=='topic'||$this.type=='topictag'){
		            	$this.iniTopicIputTags(tagnames,tagid,$this.type);
		            }
		            else if($this.type=='doc'||$this.type=='book'){
		            	$this.iniDocIputTags(tagnames,tagid);
		            }else if($this.type=='skill'){
		            	$this.iniSkillsIputTags(tagnames,tagid);
		            }
	    		}
	    	}
	    	ispublic=false;
	    }
	var $div = obj.nextAll('.tagdropdown').children().eq(upOrDownCount);  
	$div.addClass('ac-active').siblings().removeClass('ac-active');     
}
//创建新的标签
function createtag(obj){
	var tagname=obj.attr('data-name');
	//不能添加重复的技能
	if(!isExistSkill(tagname)){
		return false;
	};
	if(tagname.length>10&&$this.type!='skill'){
		if($this.type=='topictag'){
			var $pjchild=$('#pj-warmprompt').children()
			$('#pj-warmprompt').empty().append($pjchild).append('标签长度不能大于10个字').show(); 
		}else{
			var $child=$this.obj.parents('td').find('.pj-warmprompt').children();
			$this.obj.parents('td').find('.pj-warmprompt').empty().append($child).append('标签长度不能大于10个字').show();
		}
		return false;
	 }else if(tagname.length>20&&$this.type=='skill'){
		var $child=$('.skill-error').children();
		$('.skill-error').empty().append($child).append('技能长度不能大于20个字').show();
		return false;
	}
	//技能
	if($this.type=='skill'){
		$.ajax({
			type:"POST",
	     	url:"/skills/insert",
	     	data:{skillname:tagname},
	     	dataType:'json',
	     	success:function(data){
	     		if(data.returnStatus=='000'){
	     			$this.iniSkillsIputTags(tagname,data.obj.id);
	     			$('#skill0').removeAttr('style').css('min-height','150px');
	     		}else if(data.returnStatus=='-999'){
	     			var $child=$('.skill-error').children();
	     			$('.skill-error').empty().append($child).append('敏感词语不能添加').show();
	     			return false;
	     		}
	     	}
		})
	}else{
		$.ajax({
			type:"POST",
	     	url:"/tags/insertTags",
	     	data:{tagname:tagname,tagtype:'9',tier:'1'},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.returnStatus=='000'){
	                if($this.type=='topic'||$this.type=='topictag'){
	                	$this.iniTopicIputTags(tagname,data.obj.id,$this.type);
	                }
	                else if($this.type=='doc'||$this.type=='book'){
	                	$this.iniDocIputTags(tagname,data.obj.id);
	                }
	                		
	     		}else if(data.returnStatus=='-999'){
	     			var $child=$('.pj-warmprompt').children();
	     			$('.pj-warmprompt').empty().append($child).append('敏感词语不能添加').show();
	     			return false;
	     		}
	     	}
		})
	}
	
}
//话题详细界面修改标签
function addTagToDatabase(topicindex){
	var tagclass="";
	var tagid=$('input[name=titleid]').val();
	//同时保存到数据库
	$('#topictagedit div a').each(function(){
		tagclass+=$(this).attr('id')+":"+$(this).data('name')+",";
	})
	tagclass=tagclass.substring(0,tagclass.length-1);
	$.ajax({
		type:'POST',
		url:"/myCenter/updateTopics",
	    data:{id:tagid,topicsclass:tagclass},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		titledisplayorhide();
	    		//判断界面上还有几个标签
	    		if($('#topictagedit div').length==0){
	    			$('#topictag').hide().next().hide();
	    		}
	    		if(topicindex!='')
	    		$('.zm-tag-editor-labels .zm-item-tag').eq(topicindex).remove();
	    		$('.zm-tag-editor-labels').children(':last').attr('data-topicsclass',tagclass);
	    	}
	    }
	    
	})
}
//当前添加的技能不能重复
function isExistSkill(tagname){
	var isexist=true;
	$('#skillinputtags a').each(function(){
		var skillname=$(this).attr('data-name');
		if($.trim(skillname)==$.trim(tagname)){
			var $child=$('.skill-error').children();
			$('.skill-error').empty().append($child).append('技能名称重复').show();
			$("#"+$this.id).remove();
			isexist=false;
		}
	})
	return isexist;
}
