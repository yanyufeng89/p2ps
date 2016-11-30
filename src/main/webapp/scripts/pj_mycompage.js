$(function(){
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
	
	//修改业务领域
	$('a[name=editsarea]').live('click',function(){
		$(this).hide();
		//获取值
		var tb='[';
		$('.areazone').each(function(){
			tb+='{"id":'+$(this).data('skillid')+',"name":"'+$(this).text()+'"},';
		})
		tb=tb.substring(0,tb.length-1);
    	tb+=']';
    	var data={
    			skillitem:jQuery.parseJSON(tb),
    	}
    	$('.homepageTemplate').setTemplateURL('/myareaTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
    	var $child=$('.business-area').children('a');
    	$('.business-area').empty().append($('.homepageTemplate').html()).append($child).append(saveOrCancelBtn());
    	$('.homepageTemplate').empty();
	});
	//修改公司地址
	$('a[name=editsaddress]').live('click',function(){
		$(this).hide();
		var $child=$('.com-address').children('a');
		$('.homepageTemplate').setTemplateURL('/comAddressTemplate.html');
    	$('.homepageTemplate').processTemplate();
    	$('.com-address').empty().append($('.homepageTemplate').html()).append($child).append(saveOrCancelBtn());
    	$.getScript('/scripts/pj_country.js',function(){
    		setup();
		});
    	$('.homepageTemplate').empty();
	});
	
	//修改公司介绍
	$('a[name=editscombrief]').live('click',function(){
		$(this).hide();
		var listorder_now_text=$.trim($('.profile-brief span').text());
		var list_form = '<textarea name="combrief" rows="6" cols="20" class="listorder_textarea listorder"></textarea>';
		list_form+='<input type="hidden" name="oldvalue" id="output" value="'+listorder_now_text+'">';
		$('.profile-brief span').html(list_form);
		$('.profile-brief').append(saveOrCancelBtn());
		$('.listorder_textarea').val(listorder_now_text).focus();
	})
	//修改公司网站以及相关信息
	$('.com-websit .zu-edit-button').live('click',function(){
		$(this).hide();
		var $child=$('.com-websit').children('a');
		$('.homepageTemplate').setTemplateURL('/websiteTemplate.html');
    	$('.homepageTemplate').processTemplate();
    	$('.com-websit').empty().append($('.homepageTemplate').html()).append($child).append(saveOrCancelBtn());
    	$('.homepageTemplate').empty();
	});
	$('a[name=edittitle],a[name=editsenterprise],a[name=editslogan]').live('click',function(){
		$(this).hide();
		editUserInfo($(this));
	});
	//编辑界面评论
	$('.numComments').live('click',function(){
		$(this).toggle(function(){
			var $child=$(this).children();
	    	$(this).empty().append($child).append('收起评论');
	    	var data={
	    		flashcommentList:'',
	    	}
	    	$('.homepageTemplate').setTemplateURL('/flashCommentTemplate.html');
	    	$('.homepageTemplate').processTemplate(data);
	    	$(this).parents('.operate-container').append($('.homepageTemplate').html());
	    	$('.homepageTemplate').empty();
            
		},function(){
			var $children=$(this).children();
	    	$(this).empty().append($children).append('评论 1');
	    	$(this).parents('.operate-container').find('.panel-container').hide();
		})
		$(this).trigger('click');
	})
})
//保存与取消按钮
function saveOrCancelBtn(type){
	//type=1表示是个人主页头部内容修改
	var shtm='';
	    if(type==1){
	    	shtm+="<div class='hm-command'>";
	    }else{
	    	shtm+="<div class='cm-command'>";
	    }
	    shtm+='<a class="zm-command-cancel" name="cancel" href="javascript:;" style="font-size:14px;font-weight:500;">取消</a>'
	    shtm+='<a class="zg-r3px zg-btn-blue" name="save" href="javascript:;">保存</a>';	
	    shtm+="</div>";
	return shtm;
}
//修改用户信息
function editUserInfo(obj){
	var type=obj.data('type');
	var comName=$('.'+type).html();
	var list_form = '<textarea name="combrief" rows="6" cols="3" class="listorder_'+type+' listorder"></textarea>';
	if(type=='enterprise'){
		list_form+='<span class="editor-tags-i">多个企业服务可以用空格隔开</span>'
	}
	$('.'+type).html(list_form);
	$('#'+type+'-container').append(saveOrCancelBtn(1));
	$('.listorder_'+type).val(comName).focus();
}
