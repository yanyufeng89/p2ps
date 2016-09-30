var ispublic=true;
$(function(){
	/*//搜索书籍文本框获得焦点
	$('#searchbook').focus();*/
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
		   $("#btnbooksure").addClass("btn-disblue").removeClass("btn-blue");
	   }
	  $(this).nextAll('.error').hide();
	  if(event.keyCode == 13){
		 $(this).next().addClass('capture-loading').removeClass('tocapture');
		 if($.trim(conds).length>0){
			 searchbook($(this),conds);
			 $(this).next().removeClass('capture-loading').addClass('tocapture');
		 }else{
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
		$(this).addClass('capture-loading').removeClass('tocapture');
		searchbook($(this),conds);
		$(this).removeClass('capture-loading').addClass('tocapture');
	})
	
	//点击书籍搜索下拉框里面的信息  调到详细界面
	$('#upload-initbook-container .ask-row').live('click',function(){
		var bookid=$(this).attr('data-bookid');
		var bookname=$(this).attr('data-bookname');
		$('#searchbook').val(bookname);
		$('input[name=bookid]').val(bookid);
		$('#book-renderer').empty().removeClass('book-border');
		$('.pinwheel_wrap').hide();
		$("#btnbooksure").removeClass("btn-disblue").addClass("btn-blue");
	});
	//确定
	$('#btnbooksure').live('click',function(){
		$(this).addClass('capture-loading').html('');
		var bookid=$('input[name=bookid]').val();
		if($.trim($('#searchbook').val()).length==0||bookid==''){
			$('.bookpreview').nextAll('.error').show();
			$('.bookpreview').prev().focus();
			return false;
		}else{
			$.ajax({
				 type:"POST",
			   	 url:"/books/getBookSimpleInfo",
			     data:{id:bookid},
			     dataType:"json",
			   	 success:function(data){
			   		 if(data.returnStatus=='000'){//返回成功
			   			$('#upload-initbook-container').hide();
			            $('#upload-book-container').show();
			            $('input[name=bookid]').val(data.obj.id);
			            //书籍分享
			            initTitleInfoBySearchBook(data);
			   		 }
			   		 else{
			   			 
			   		 }
			   	  }
			})
		}
	})

   //确认分享
   $('#sharebook').live('click',function(){
	   //推荐理由必填
	   if($.trim($('textarea[name=recommend]').val()).length==0){
			$('textarea[name=recommend]').nextAll().css('display','inline-block');
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
   	  url:"/books/searchBooksByName",
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
	   container_div+="<div class='ask-row' data-bookname='"+item.title+"' data-bookid='"+item.data_id+"'> ";
	   container_div+="   <a style='color:rgb(34,34,34)' href='javascript:void(0)' class='booktitle' data-booktype='1' data-bookid='"+item.data_id+"'>"+item.title+"</a>";
	   container_div+='</div>';
   })
   $('#book-renderer').append(container_div).show();
}

//书籍详细界面加载书籍分享
function initTitleInfoBySearchBook(data){
	$('.zg-form-booktext-input').empty();
	var container_div='';
	
	container_div+="<div class='booksimg'>";
	if(data.obj.bookimg.length==0){
		container_div+="<img class='Avatar-book' src='"+"//image/thief.jpg"+"'>";
	}
	else{
		container_div+="<img class='Avatar-book' src='"+data.obj.bookimg+"'>";
	}
	container_div+="</div>";
	
	container_div+="<div class='booksinfo'>";
	container_div+="  <p><label>书籍:</label>"+data.obj.bookname+"</p>";
    container_div+="  <p><label>出版社:</label>"+data.obj.press+"</p>";
    container_div+="  <p><label>作者:</label>"+data.obj.author+"</p>";
	container_div+="</div>";

	$('.zg-form-booktext-input').append(container_div);
}
