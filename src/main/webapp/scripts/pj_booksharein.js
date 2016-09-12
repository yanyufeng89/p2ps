var ispublic=true;
$(function(){
	//搜索书籍文本框获得焦点
	$('#searchbook').focus();
	$('.booktitle').pinwheel();
	
	//分类
	getAllParentTypeConfigs('sharetype');
	
	//书籍搜索文本框回车事件
	$('#searchbook').keyup(function(){
	   var conds=$(this).val();
	   if($.trim(conds).length==0){
		   if($('#book-renderer').length>0){
				 $('#book-renderer').empty();
				 $('#book-renderer').removeClass('book-border');
			 }
	   }
	  $(this).nextAll('.error').hide();
	  if(event.keyCode == 13){
	     $('.capture-loading').prev().val('');
		 $('.capture-loading').show();
		 
		 if($.trim(conds).length>0)
		 searchbook($(this),conds);
		 else{
			 $(this).nextAll('.error').show();
			 $(this).focus();
		 }
	   }
	
	})
	//点击书籍搜索文本框后面的按钮
	$('.bookpreview').click(function(){
		var conds=$(this).prev().val();
		if($.trim(conds).length==0){
			$(this).nextAll('.error').show();
			$(this).prev().focus();
			return false;
		}
		$('.capture-loading').prev().val('');
		$('.capture-loading').show();
		searchbook($(this),conds);
	})
	
	//点击书籍搜索下拉框里面的信息  调到详细界面
	$('#upload-initbook-container .ask-row').live('click',function(){
		//关闭书籍详情弹出层
		$('.pinwheel_wrap').hide();
		var bookid=$(this).attr('data-bookid');
		$.ajax({
			 type:"POST",
		   	 url:projectName+"books/getBookSimpleInfo",
		     data:{id:bookid},
		     dataType:"json",
		   	 success:function(data){
		   		 if(data.returnStatus=='000'){//返回成功
		   			$('#upload-initbook-container').hide();
		            $('#upload-book-container').show();
		            $('input[name=bookid]').val(data.obj.id);
		            //书籍详情
		            initTitleInfoBySearchBook(data);
		   		 }
		   		 else{
		   			 
		   		 }
		   	  }
		})
	});
	
	
    
   //确认分享
   $('#sharebook').live('click',function(){
	   //推荐理由必填
	   if($.trim($('textarea[name=recommend]').val()).length==0){
		   $('textarea[name=recommend]').nextAll().show();
		   $('textarea[name=recommend]').focus();
		   return false;
	   }
	   //分类
		if($('input[name=sharetype]').val()==''||$('input[name=sharetype]').val().indexOf('undefined')!=-1){
			$('#pj-modal-dialog-bookclassify').show();
			return false;
		}
	    //选择话题标签
		var tagid="";
		$("#bookinputtags div a").each(function(){
			tagid+=$(this).attr('id')+":"+$(this).data('name')+",";
		});
		$("input[name=shareclass]").val(tagid.substring(0,tagid.length-1));
		
	    $('form').submit();
   })
})
//搜索书籍信息
function searchbook(obj,conds){
     $.ajax({
   	  type:"POST",
   	  url:projectName+"books/searchBooksByName",
   	  data:{bookName:conds},
   	  dataType:"json",
   	  success:function(data){
   		  if(data.returnStatus=='000'){//返回成功
   			  if(data.list.length>0){
   				$('#book-renderer').addClass('book-border');
 			    initTitleBySearchBook(data);
 			    $('.booktitle').pinwheel();
   			  }
   			  else{
   				$('#book-renderer').removeClass('book-border');
   			  }
   			  $('.capture-loading').prev().val('确定');
   		      $('.capture-loading').hide();
   		  }
   		  else{
   			  
   		  }
   	  }
     })    
}
//加载书籍信息
function initTitleBySearchBook(data){
   $("#book-renderer").empty();
   var container_div='';
   $.each(data.list,function(index,item){
	   container_div+="<div class='ask-row' data-bookid='"+item.data_id+"'> ";
	   container_div+="   <a style='color:rgb(34,34,34)' href='#' class='booktitle' data-booktype='1' data-bookid='"+item.data_id+"'>"+item.title+"</a>";
	   container_div+='</div>';
   })
   $('#book-renderer').append(container_div).show();
}

//书籍详细界面加载书籍详情
function initTitleInfoBySearchBook(data){
	$('.add-question-title-form').empty();
	var container_div='';
	
	container_div+="<div class='booksimg'>";
	if(data.obj.bookimg.length==0){
		container_div+="<img class='Avatar-book' src='"+projectName+"/image/thief.jpg"+"'>";
	}
	else{
		container_div+="<img class='Avatar-book' src='"+data.obj.bookimg+"'>";
	}
	container_div+="</div>";
	
	container_div+="<div class='booksinfo'>";
	container_div+="  <p><span>[书籍]</span>"+data.obj.bookname+"</p>";
    container_div+="  <p><span>出版社:</span>"+data.obj.press+"</p>";
    container_div+="  <p><span>作者:</span>"+data.obj.author+"</p>";
	container_div+="</div>";

	$('.add-question-title-form').append(container_div);
}
