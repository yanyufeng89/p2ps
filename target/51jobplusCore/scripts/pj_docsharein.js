var ispublic=true;
var num=0;//上传数量
var arrFiles=[];//需要上传的文件数组
var filetypes =[".doc",".docx",".ppt",".pptx",".xls",".xlsx",".pot",".pps",
                ".rtf",".wps",".pdf",".txt",]; 
$(function(){
	//上传文档
	$("#docfileField").live('change',function(e){
		arrFiles=[];
		var files=e.target.files;
		var filename="";
		var isnext = true;
	   for(var i=0;i<files.length;i++){
		    var fileend = files[i].name.substring(files[i].name.lastIndexOf("."));
		    files[i].name=files[i].name.substring(0,files[i].name.lastIndexOf("."));
		    if(filetypes.indexOf(fileend.toLowerCase())<0){
		    	isnext=false;
		    }
		    //提示文件类型不符合
		    if(!isnext){
				arrFiles=[];
				$(this).next().show();
				return false;
			}
			else{
				$(this).next().hide();
			}
		    if(files[i].size>20971520){
		    	$(this).next().html('上传的文件大小限制在20M以内').show();
		    	return false;
		    }
		    else{
		    	$(this).next().hide();
		    }
			filename+=files[i].name+",";
			arrFiles.push(files[i]);
		}
		
	    $(".txtdocument").val(filename.substring(0,filename.length-1));
	    //启用确定按钮
	        $("#btndocsure").removeClass("btn-disblue").addClass("btn-blue");
	    if(filename==""){
	    	$("#btndocsure").removeClass("btn-blue").addClass("btn-disblue");
	     }
    });
	$('input[name=title]').live('input propertychange',function(){
		$(this).parents('.edit-table').find('.item-msg-content').html('建议您结合文档正文完善文档标题信息');
		$(this).parents('.edit-table').find('.ic-msg').css('background-position','-15px -145px');
	})
	//继续添加
	$("#btn-add-all input[type=file]").live('change',function(e){
		var addfiles=e.target.files;
		var addfilename="";
		var flag = true;
		var newarrFiles=[];//继续上传的文件数组
	   for(var i=0;i<addfiles.length;i++){
		    var fileend = addfiles[i].name.substring(addfiles[i].name.lastIndexOf("."));
		    if(filetypes.indexOf(fileend.toLowerCase())<0){
		    	flag=false;
		    }
		    //文件类型不符合
		    if(!flag){
				$('#upload-files-container .error').css('display','block');
				return false;
			}
			else{
				$('#upload-files-container .error').css('display','none');
			}
		    newarrFiles.push(addfiles[i]);
		}	
	  
	   $("#upload-list").append(funDisposePreviewHtml(newarrFiles,false));
	   $(this).each(function(){
		   $(this).css('z-index','0');
	    })
	   var inputhtml="<input type='file' name='fileField' class='file' size='28' multiple='multiple' style='z-index:9999'/>";
	   $(this).after(inputhtml);
	});
	
	//界面初始化
	$("input[type=file]").each(function(){
		if($(this).val()==""){
			$(this).parents().find(".txtdocument").val("请选择文档...");
		}
	});
	
	//确定
	$("#btndocsure").live('click',function(){
		$(this).addClass('capture-loading').html('');
		$("#upload-init-container").hide();
		$("#upload-files-container").show();
		$("#upload-list").append(funDisposePreviewHtml(arrFiles,true));
	})	
		   
    //标签删除
    $('.zg-inline a[name=remove]').live('click',function(){
   		var $floor=$(this).parents('.zg-inline').next();
   		$floor.find(':first-child').show();
   		$floor.find('input[type=text]').show();
   		$floor.find(':last-child').hide();
   		$(this).parent().remove();
   		
   	});
    //删除
	$("#upload-files-container .ui-imgBtn-delete").live('click',function(){
		    if($('#btndocsure').hasClass('capture-loading'))
		    $('#btndocsure').removeClass('capture-loading').html('确定');
			var index=$(this).data('index');
			var name=$(this).data('name');
			var size=$(this).data('size');
			$("#upload-item-"+index).remove();
			//根据同一个文件名和大小 在数组中删掉相应的数据
			for(var i=0;i<arrFiles.length;i++){
				if(arrFiles[i].name==name&&arrFiles[i].size==size)
				{
					arrFiles.splice($.inArray(arrFiles[i],arrFiles),1); 
				}
			}
			//当上传的文档全部被删完  调到重新上传文档界面
			if($('.editlayoutbook').length==0){
				arrFiles=[];
				$("#upload-init-container").show();
				$("#upload-files-container").hide();
				$('input[type=file]').each(function(){
					$(this).after($(this).clone());  
					$(this).remove();
				})
				num=0;
				$("#textfield").val(' ').val("请选择文档...");
				$("#btndocsure").removeClass("btn-blue").addClass("btn-disblue");
			}
	})
	//确认上传
	$('#btn-submit-all').live('click',function(){
		var flag=true;
         //标题必填
		$('input[name=title]').each(function(){
			if($(this).val().length==0){
				$(this).parents('.edit-table').find('.item-msg-content').html('').html('标题不能为空');
				$(this).parents('.edit-table').find('.ic-msg').css('background-position','-47px -144px');
				window.location.href='#'+$(this).parents('.editlayoutbook').attr('id');
				return false;
				flag=false;
			}
			if($(this).val().replace(/[^x00-xFF]/g,'**').length>256){
				$(this).parents('.edit-table').find('.item-msg-content').html('').html('标题超出最大限制');
				$(this).parents('.edit-table').find('.ic-msg').css('background-position','-47px -144px');
				window.location.href='#'+$(this).parents('.editlayoutbook').attr('id');
				return false;
				flag=false;
			}
		})
	    //简介限制
		$('.bookcontent').each(function(){
			if($(this).val().replace(/[^x00-xFF]/g,'**').length>512){
				$(this).nextAll().show();
				flag=false;
			}
		})
		
		//分类必选
		if($('input[name=doctype]').length==0){
			$('.docclassify').show();
			flag=false;
		}else{
			$('input[name=doctype]').each(function(){
				if($.trim($(this).val()).length==0||$(this).val().indexOf('undefined')!=-1){
					$(this).parents('ul').next().show();
					window.location.href='#'+$(this).parents('.editlayoutbook').attr('id');
					return false;
					flag=false;
				}
			})
		}

		//获取标签的值
		$('.zg-inline').each(function(){
			var tagid="";
			$(this).find('div a').each(function(){
				tagid+=$(this).attr('id')+":"+$(this).data('name')+",";
			})
			$(this).parents('td').find('input[name=docclass]').val(tagid.substring(0,tagid.length-1));
		})
		if(flag){
			 //添加遮罩层 防止在上传的同时做其他操作
			 addMaskLayer();
			 $('#test11form').submit();
		}
		
	})

   //获取财富值
  $('select').live('change',function(){
	  $(this).prev().val($(this).find('option:selected').val());
  });
   //获取公开 或者私有
   $("input[type=radio]").live('change',function(){
	   $(this).parent().find('input[name=ispublic]').val($(this).val());
   })  
});



//创建标签HTML
var funDisposePreviewHtml=function(files,flag){
	var html = "";
	$.each(files,function(k,v){
		var exist=true;
		//判断是否为已经上传的文件
		if(!flag){
			$.each(arrFiles,function(n,m){
				if(v.name==m.name&&v.size==m.size){
					exist=false;
				}
			})
		}
	    var data={
	    		index:num+1,
	    		num:num,
	    		size:v.size,
	    		name:v.name.substring(0,v.name.lastIndexOf('.')),
	    		namesuffix:v.name,
	    		pid:'public'+num,
	    		doctype:parentConfigList[0].typeid+":"+parentConfigList[0].typename+","+childConfigList[0].typeid+":"+childConfigList[0].typename,
	    		parentConfigList:parentConfigList,
	    		childConfigList:childConfigList,
	    }
	  $("#addtemp").empty();
	  
	  if(exist){
		  if(v.size!=0){
			  for(var i=0;i<arrFiles.length;i++){
				/*if(arrFiles[i].name!=v.name&&arrFiles[i].size!=v.size)
				{
					arrFiles.push(v); 
				}*/
				if($.inArray(v, arrFiles)==-1){
					arrFiles.push(v); 
				}
			  }
			  $("#addtemp").setTemplateURL("/doctTemplate.html");
		  }  
		  else{
			  //删除已经保存到数组中的数据
			  for(var i=0;i<arrFiles.length;i++){
				if(arrFiles[i].name==v.name&&arrFiles[i].size==v.size)
				{
					arrFiles.splice($.inArray(arrFiles[i],arrFiles),1); 
				}
			  }
			  data.tips='上传附件失败!';
			  $("#addtemp").setTemplateURL("/docreTemplate.html");
		  }
	  }
	  //重复上传
	  else{
		     data.tips='您刚刚已上传了该文档，不需要重复上传!';
		     $("#addtemp").setTemplateURL("/docreTemplate.html");
		}
	    $("#addtemp").processTemplate(data);
	    $('#upload-list').append($("#addtemp").html());
	    $("#addtemp").empty();
		num++;
	})
	return html;
}
