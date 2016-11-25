$(function(){
	$('#docfollow').live('click',function(){
		docDownLoadInit($(this));
	});
	//点击 立即下载
	$('.btn-diaolog-downdoc').live('click',function(){
		docDownLoad($(this));
	});
})
//文档详情 文档下载
function docDownLoadInit(obj){
	var downvalue=obj.attr('data-downvalue');
	var docCreatePerson=$('input[name=docCreatePerson]').val();
	var sumvalue=obj.attr('data-sumvalue');
	var docid=$('input[name=docid]').val();
	var isdown=obj.attr('data-isdown');
	if(String(userInfo.userid)==docCreatePerson||isdown==1){
		downvalue=0;	
	}
	var datamodel={
			downvalue:downvalue,
			sumvalue:sumvalue,
			docid:docid,
			docCreatePerson:docCreatePerson,
	}
	//加载模板
	$('.pagetemplate').setTemplateURL('/docDownLoad.html');
	$('.pagetemplate').processTemplate(datamodel);
	$('body').append($('.pagetemplate').html());
	$('.pagetemplate').empty();
}
//点击 立即下载
function docDownLoad(obj){
	//下载所需要的财富值
	var downvalue=obj.attr('data-downvalue');
	//自己所拥有的财富值
	var sumvalue=obj.attr('data-sumvalue');
	var docCreatePerson=obj.attr('data-docCreatePerson');
	//当前自己拥有的财富值大于或等于下载所需的财富值  才能下载
	if(Number(downvalue)>Number(sumvalue)){
		$('.zh-pm-warnmsg').show();
		return false;
	}   
	var docsuffix=$('input[name=docsuffix]').val();
    var docurl=$('input[name=docurl]').val();
    var title=$('input[name=docname]').val();
    docurl=docurl.substring(0,docurl.indexOf('.swf'))+'.'+docsuffix;
	var docid=$('input[name=docid]').val();
	//文档下载路径
	var filePath = docurl+'?filename='+title+'.'+docsuffix;
	 //关掉下载框
	 $('.modal-dialog-bg').remove();
     $('.modal-wrapper').remove();
	 $.ajax({
	    	type:"POST",
	      	url:"/docs/downloadDocs",
	      	data:{downvalue:downvalue,id:docid,userid:docCreatePerson,filePath:filePath,title:title},
	    	dataType:"json",
	    	 async:false, 
	    	success:function(data){
	    		if(data.returnStatus=='000'){//返回成功
	    			window.location.href=encodeURI(docurl+'?filename='+title+'.'+docsuffix);
	    			$('#docfollow').attr('data-downvalue','0');
	    			//下载人数加1
	    			$('.zg-gray-normal strong').html(Number($('.zg-gray-normal strong').html())+1);
	    			showHeadIcon();
	    		}else if(data.returnStatus=='-999'){
	    			//文档资源不存在 或者url不合法
	    			/*console.log("文档资源不存在 或者url不合法");*/
	    			window.location.href='404.html'
	    			
	    		}
	       }
	 })
}