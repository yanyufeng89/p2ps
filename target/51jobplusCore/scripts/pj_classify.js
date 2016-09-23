var parentConfigList=[];
var childConfigList=[];
var clatype='';
$(function(){
	//分类选中(左侧)
	$('.topic-sort li').live('click',function(){
		   if(clatype==''){
			   clatype=$('input[name=doctypemark]').val();
		   }
		   $(this).addClass('cur').siblings().removeClass('cur');
		   //根据父节点id获取对应的子节点数据
		   var nid=$(this).data('id');
		   var parenode=nid+":"+$(this).html();
		   getChildTypeConfigsByParent(nid);
		   //文档和其他五类不一样  当左侧变化的时候  只有对应的右侧变化
		   if(clatype=='doctype'){
			   $(this).parents('.topic-sort').next().empty().append(rightsort());
		   }else{
			   $('.edit-class-sec ol').empty().append(rightsort());
		   }
		   var node= $(this).parents('.topic-sort').next().find('.cur').val()+":"+
		   $(this).parents('.topic-sort').next().find('.cur').html();
		   $(this).parents('.topic-sort').next().find('input[name='+clatype+']').val(parenode+","+node);
	})

	//获取分类后选中的值(右侧)
	$('.edit-class-sec li').live('click',function(){
		   var parentid=$(this).attr('data-parentid');
		   $(this).addClass('cur').siblings().removeClass('cur');
		   $(this).parents('.edit-class-sec').prev().find('li').each(function(){
			  if($(this).val()==parentid&&!$(this).hasClass('cur')){
				  $(this).addClass('cur');
			  }
		   })
		   //父节点id:父节点name+','+子节点id+':'子节点name
		   var parentNode=$(this).parents('.edit-class-sec').prev().find('.cur').val()+":"+
		   $(this).parents('.edit-class-sec').prev().find('.cur').html();
		   $(this).parent().find('input[name='+clatype+']').val(parentNode+","+$(this).val()+":"+$(this).html());
	})
})
//根据父级节点ID获取所有子节点   100是父节点ID
function getChildTypeConfigsByParent(id){
	   $.ajax({
    	type:"POST",
    	url:"/51jobplusCore/tags/getChildTypeConfigsByParent",
    	data:{parentTypeId:id},
    	dataType:"json",
    	async:false,
    	success:function(data){
    		if(data.returnStatus=='000'){//返回成功
        		childConfigList=data.typeConfigList;
        		//加载分类
        		
    		}else{//返回失败
    			
    		}
    		
    	}
  });
}
//获取所有父节点
function getAllParentTypeConfigs(type){
	   clatype=type;
	   $.ajax({
     	type:"POST",
     	url:"/51jobplusCore/tags/getAllParentTypeConfigs",
     	//data:{parentTypeId:100},
     	dataType:"json",
     	success:function(data){
     		if(data.returnStatus=='000'){//返回成功
     			parentConfigList=data.typeConfigList;	
     			getChildTypeConfigsByParent(parentConfigList[0].typeid);
     			initClass();
    		}else{//返回失败
    			
    		}
    		
    	}
 });
}
function initClass(){
	$('.topic-sort ol').html(leftsort());
	$('.edit-class-sec ol').html(rightsort(''));
}
var pnode,cnode;
//文档上传分类左侧部分
var leftsort=function(){
	  var html="";
	  $.each(parentConfigList,function(a,b){
		  /*if(a==0){
			  pnode=b.typeid+":"+b.typename;
			  html+="                                <li value="+b.typeid+" data-id="+b.typeid+" class='cur'>"+b.typename+"</li>";	
		  }
		  else{*/
			  html+="                                <li value="+b.typeid+" data-id="+b.typeid+">"+b.typename+"</li>";	
		  /*}*/
      });
	  return html;
}
//文档上传分类右侧部分
var rightsort=function(){
	 var html="";
	 $.each(childConfigList,function(c,d){
		/*if(c==0){
			cnode=d.typeid+":"+d.typename;
			html+="                                <li value="+d.typeid+" data-id="+d.typeid+" class='cur'>"+d.typename+"</li>";	
		}*/
		/*else{*/
			html+="                                <li value="+d.typeid+" data-id="+d.typeid+" data-parentid="+d.parentid+">"+d.typename+"</li>";	
		/*}*/
     });
	 html+="                                  <input type='hidden' name="+clatype+" value='' />"
	 return html;
}
