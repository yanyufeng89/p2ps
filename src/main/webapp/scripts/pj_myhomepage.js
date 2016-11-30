var randomid=1;
var userInfo;
$(function(){
	//当个人资料还未维护
	if($('.section-container').length==0){
		$('#no-material').show();
	}
	 //个人中心  点击联系方式
    $(".show-more-info").on('click',function(){
    	$("#contact-info-section").toggle();
    	if($(this).find('.t-down').length==0){
    		$(this).find('.t-up').removeClass('t-up').addClass('t-down');
    	}else{
    		$(this).find('.t-down').removeClass('t-down').addClass('t-up');
    	}
    	$('.background-education-container').last().addClass('section-border');
    })
    //查看他人主页 --当工作经历只有一个时  加底边
    if($('.background-workexperience-container').length==1){
    	$('.preview-workexperience').css('border-bottom','1px solid #d6d6d6');
    }
    if($('.background-education-container ').length==1){
    	$('.preview-education').css('border-bottom','1px solid #d6d6d6');
    };
    //查看他人主页  点击个人资料
    $('#personal-data').live('click',function(){
    	addClassFun($(this));
    	if($('.section-container').length==0){
    		$('#no-material').show();
    	}else{
    		$('.section-container').show();
    	}
    	$('.zm-profile-section-wrap,#zh-profile-follows-list,#profile-navbar,#fans-navbar').hide();
    });
   //查看他人主页  点击个人分享
    $('#personal-share').live('click',function(){
    	addClassFun($(this));
    	$('.section-container,#fans-navbar,#zh-profile-follows-list,#no-material').hide();
    	$('#profile-navbar,.zm-profile-section-wrap').show();
    	/*//当最新分享的数量是0是 列表不显示
    	if($('#zh-profile-answers-inner-list ul li').length==0){
    		$('.zm-profile-section-wrap').hide();
    	}*/
    })
    //查看他人主页  点击个人关注
    $('#personal-attention').live('click',function(){
    	addClassFun($(this));
    	$('#fans-navbar').show();
    	$('#profile-navbar,.zm-profile-section-wrap,.section-container,#no-material').hide();
    	otherAtten($(this));
    })
    //定时刷新界面上选中的边框颜色
    setInterval("startrequest()",3000); 
    //鼠标移到头像上显示详细信息
    intoUserInfo();
    //获取用户信息
	getCurrentUser();
    //他的分享
    $('#othershare').live('click',function(){
    	if($('.profile-navbar').is(':hidden')){
    		$('.profile-navbar,.zm-profile-section-wrap').show();
    		$('#contact-info-section').hide();
    		$(this).find('.contact').html('详细信息');
    	}
    })
    //个人中心版块展开与收起
	var li =$("#recommended-sections ul li:gt(5)");
    li.hide();
    $("#recommended-sections ul").css('height','326px');
    $("#recommended-sections .view-bar").click(function(){
    	if(li.is(':visible')){
			 li.slideUp('fast');
			 $("#recommended-sections ul").css('height','326px');
			 $(this).removeClass('view-less-bar').addClass('view-more-bar');
			 $(this).find('span').html('展开');
		}else{
			li.slideDown('fast').show();
			$("#recommended-sections ul").css('height','480px');
			$(this).removeClass('view-more-bar').addClass('view-less-bar');
			$(this).find('span').html('收起');
		}	
    });
    
    //点击修改用户名
    $('a[name=edittitle]').live('click',function(){
    	var titletext=$(this).prev().text();
    	var data={
				title:$.trim(titletext),
				canclename:'cancleusername',
				savename:'saveusername'
		}
    	$('.homepageTemplate').setTemplateURL(projectName+'myHomePageTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $('#titlename').append($('.homepageTemplate').html());
  	    $('#titlename .field-text').hide();
  	    $('.homepageTemplate').empty();
    });
    //修改用户名点击取消
    $('a[name=cancleusername]').live('click',function(){
    	hiddenUserNameEdit();
    });
    //修该用户名点击保存
    $('a[name=saveusername]').live('click',function(){
    	var username=$('#nameedit').val();
    	if($.trim(username).length==0){
    		return false;
    	}
    	if($.trim(username).replace(/[^x00-xFF]/g,'**').length<4 || $.trim(username).replace(/[^x00-xFF]/g,'**').length>18 ){
    		$(this).parent().find('b').show();
    		$(this).parent().find('.item-msg-content').html('合法长度为4-18个字符').show();
    		return false;
    	}
    	$('#titlename .full-name').text(username);
    	//同时更改数据库中的字段值
    	updateColumn(username,"username=");
    	hiddenUserNameEdit();
    });
    //公司名字修改
    $('a[name=editcompname]').live('click',function(){
		var compname = $(this).prev().text();
		if ('填写公司名称' == $.trim(compname))
			compname = '';
    	var data={
				title:$.trim(compname),
			    placeholder:'填写公司名称',
				canclename:'canclecompname',
				savename:'savecompname'
		}
    	$('.homepageTemplate').setTemplateURL(projectName+'myHomePageTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $(this).after($('.homepageTemplate').html());
  	    $(this).hide().prev().hide();
  	    $('.position-split').hide();
  	    $('.homepageTemplate').empty();
  	    $('.pj-editable-editor-outer').css('margin-bottom','6px');
    });
    //修改公司名点击取消
    $('a[name=canclecompname]').live('click',function(){
    	$('.position-split').show();
    	hiddenEditArea('compname');
    });
    //修改公司名点击保存
    $('a[name=savecompname]').live('click',function(){
    	var compname=$('#nameedit').val();
    	$('.compname').text(compname);
    	if($.trim(compname).length==0){
    		$('.editcompname').hide();
    	}
    	if($.trim(compname).replace(/[^x00-xFF]/g,'**').length>120){
    		$(this).parent().find('b').show();
    		$(this).parent().find('.item-msg-content').html('公司名称超出最大限制').show();
    		return false;
    	}
    	$('.position-split').show();
    	//同时更改数据库中的字段值
    	updateColumn(compname,'compname=');
    	hiddenEditArea('compname');
    });
    //关注人
    $('.operation .zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0);
    })
    //点击修改职位
    $('a[name=editposition]').live('click',function(){
    	var position=$(this).prev().text();
    	if($.trim(position).length==0){
    		$('.editposition').hide();
    	}
		if ('填写职位' == $.trim(position))
			position = '';
    	var data={
				title:$.trim(position),
			    placeholder:'填写职位',
				canclename:'cancleposition',
				savename:'saveposition'
		}
    	$('.homepageTemplate').setTemplateURL(projectName+'myHomePageTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $(this).after($('.homepageTemplate').html());
  	    $('.position-split').hide();
  	    $(this).hide().prev().hide();
  	    $('.homepageTemplate').empty();
  	    $('.pj-editable-editor-outer').css('margin-bottom','6px');
    });
    //修改职位点击取消
    $('a[name=cancleposition]').live('click',function(){
    	$('.position-split').show();
    	hiddenEditArea('position');
    });
    //修改职位点击保存
    $('a[name=saveposition]').live('click',function(){
    	var position=$('#nameedit').val();
    	if($.trim(position).length==0){
    		$('.editposition').hide();
    	}
    	if($.trim(position).replace(/[^x00-xFF]/g,'**').length>120){
    		$(this).parent().find('b').show();
    		$(this).parent().find('.item-msg-content').html('职位超出最大限制').show();
    		return false;
    	}
    	$('.position-split').show();
    	$('.position').text(position);
    	//同时更改数据库中的字段值
    	updateColumn(position,'position=');
    	hiddenEditArea('position');
    });
    //修改行业
    $('a[name=editindustry]').live('click',function(){
    	var industry=$(this).prev().text();
		if ('填写行业' == $.trim(industry))
			industry = '';
    	var data={
				title:$.trim(industry),
			    placeholder:'填写行业',
				canclename:'cancleindustry',
				savename:'saveindustry'
		}
    	$('.homepageTemplate').setTemplateURL(projectName+'myHomePageTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $(this).after($('.homepageTemplate').html());
  	    $(this).hide().prev().hide();
  	    $('.area-split').hide();
  	    $('.homepageTemplate').empty();
  	    $('.pj-editable-editor-outer').css('margin-bottom','6px');
    })
    //修改行业点击取消
    $('a[name=cancleindustry]').live('click',function(){
    	$('.area-split').show();
    	hiddenEditArea('industry');
    });
    //修改行业点击保存
    $('a[name=saveindustry]').live('click',function(){
    	var industry=$('#nameedit').val();
    	if($.trim(industry).length==0){
    		$('.editindustry').hide();
    	}
    	if($.trim(industry).replace(/[^x00-xFF]/g,'**').length>120){
    		$(this).parent().find('b').show();
    		$(this).parent().find('.item-msg-content').html('行业超出最大限制').show();
    		return false;
    	}
    	$('.area-split').show();
    	$('.industry').text(industry);
    	//同时更改数据库中的字段值
    	updateColumn(industry,'industry=');
    	hiddenEditArea('industry');
    });
    //修改城市或者省份
    $('a[name=editcity]').live('click',function(){
    	var province=$.trim($('.province').html());
    	var city=$.trim($('.city').html());
    	if(province.length==0){
    		province="省份"
    	}
    	if(city.length==0){
    		city="市"
    	}
    	var showval=province+city;
    	var data={
    			province:province,
    			city:city,
    			canclename:'canclecity',
    			savename:'savecity'
    	};
    	$('.homepageTemplate').setTemplateURL(projectName+'initAreaTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $(this).after($('.homepageTemplate').html());
  	    /*$('.area-split').hide();*/
  	    $(this).hide()
  	    $('.province,.city').hide();
  	    $('.homepageTemplate').empty();
  	    
    	var opt0 = [province,city];
    	_init_area(opt0);
    })
    //取消编辑城市文本框
    $('a[name=canclecity]').live('click',function(){
    	/*$('.area-split').show();*/
    	hiddenCiteArea();
    })
    //修改城市保存
    $('a[name=savecity]').live('click',function(){
    	var province=$.trim($('input[name=province]').val());
    	var city=$.trim($('input[name=city]').val());
    	if(city=='省份'){
    		city='';
    	}
    	if(province=='地级市'){
    		province='';
    	}
    	hiddenCiteArea();
    	/*$('.area-split').show();*/
    	$('.province').html(province);
    	$('.city').html(city);
    	updateCityOrProvince(city,province);
    });
    //选择省份
    $('#s_province').live('change',function(){
    	$('input[name=province]').val($(this).find('option:selected').val());
    });
    //选择市
    $('#s_city').live('change',function(){
    	$('input[name=city]').val($(this).find('option:selected').val());
    });
    //选择生日日期
    $('#birthdayday').live('click',function(){
    	$('input[name=birthdaytwo]').val($(this).find('option:selected').val());
    })
    //选择生日月份
    $('#birthdaymonth').live('click',function(){
    	$('input[name=birthdayone]').val($(this).find('option:selected').val());
    })
    //选择婚姻状况
    $('#marriage').live('click',function(){
    	$('input[name=ismarry]').val($(this).find('option:selected').val());
    })
    //个人信息点击取消
    $('a[name=myintrocancel]').live('click',function(){
    	hiddenSelfInfo($(this));
    });
    //个人信息点击保存
    $('a[name=myintrosave]').live('click',function(){
    	var birthdayday=$.trim($('input[name=birthdaytwo]').val());
    	var birthdaymonth=$.trim($('input[name=birthdayone]').val());
		if (birthdaymonth == '0' || birthdayday == '0') {
			$('.birthday b').show();
			$('.birthday .item-msg-content').show();
			return false;
		}
		var marriage = $.trim($('input[name=ismarry]').val());
		if (marriage == '' || marriage == '2') {
			$('.marriage b').show();
			$('.marriage .item-msg-content').show();
			return false;
		}
    	var description=$('textarea[name=recommend]').val();
    	//同时界面上赋值
    	//更改数据库中对应个人信息字段
    	updateSelfInfo(birthdayday,birthdaymonth,marriage,description);
    	
    });
    //点击添加基本信息
    $('.recommended-section-selfinfo .recommended-section-add').live('click',function(){
    	if($('.background-selfinfo-container').length==0){
    		$('.homepageTemplate').setTemplateURL(projectName+'myintroTemplate.html');
        	var data={
        			birthdayone:0,
        			birthdaytwo:0,
        			ismarry:'',
        			isEdit:0,
        	}
        	$('.homepageTemplate').processTemplate(data);
        	$('#recommended-sections').append($('.homepageTemplate').html());
        	$('.homepageTemplate').empty();
    	}else{
            $('a[name=editbirthday]').trigger('click');
    		$('.background-selfinfo-container').css('border-color','#0867c5');
    	}
    	window.location.href='#self-info-detail';
       //$(this).addClass('disabled');
    })
    
    //添加技能
    $('.recommended-section-skill .recommended-section-add').live('click',function(){
	   if($('.background-skills-container').length==0){
	   		$('.homepageTemplate').setTemplateURL(projectName+'mySkillTemplate.html');
	       	var data={
	       			birthdayone:0,
	       			birthdaytwo:0,
	       			ismarry:'',
	       			isEdit:0,
	       	}
	       	$('.homepageTemplate').processTemplate(data);
	       	$('#recommended-sections').append($('.homepageTemplate').html());
	       	$('.homepageTemplate').empty();
	   	}else{
	   		$('a[name=editskill]').trigger('click');
	   		$('#skill0').css('border-color','#0867c5');
	   	}
	    window.location.href='#skill0';
    })
    //修改技能
    $('a[name=editskill],#addcontinuousskill,button[name=addskill]').live('click',function(){
    	var skillid=$(this).attr('data-skillid');
    	var skillitem=$(this).attr('data-skillitem');
    	var tb='[';
    	for(var i=0;i<skillitem.split(',').length;i++){
    		tb+='{"id":'+skillitem.split(",")[i].split(":")[0]+',"name":"'+skillitem.split(",")[i].split(":")[1]+'"},';
    	}
    	tb=tb.substring(0,tb.length-1);
    	tb+=']';
    	var data={
    			skillid:skillid,
    			skillitem:jQuery.parseJSON(tb),
    			skillitems:skillitem
    	}
    	$('.homepageTemplate').setTemplateURL(projectName+'mySkillTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
    	$('#skill0').after($('.homepageTemplate').html());
       	$('.homepageTemplate').empty();
       	$('#skill0').remove();
    })
    //取消修改技能
    $('a[name=myskillcancel]').live('click',function(){
    	var skillitem=$(this).attr('data-skillitem');
    	var id=$(this).attr('data-skillid');
    	if(skillitem.length==0){
    		$('.background-skills-container').remove();
    	}else{
    		cancelSkills(skillitem,id,$(this));
    	}
    })
    //删除技能
    $('a[name=removeskill]').live('click',function(){
    	$(this).parent().remove();
    })
    //修改基本信息的生日
    $('a[name=editbirthday],a[name=editintroinfo],a[name=editmarriage]').live('click',function(){
    	editMyIntroInfo($(this));
    })
    //添加教育背景
    $('.recommended-section-education .recommended-section-add,#addcontinuous,#control_gen_4,button[name=addschool]').live('click',function(){
    	//0表示继续添加  
    	var addtype=$(this).attr('data-addtype'); 
    	//如果存在教育背景新增框    就提示先编辑未保存的 内容
    	if(addtype==0){
    		if($('.background-education-container').last().find('input[name=school]').val().length==0){
        		var cutid=$('.background-education-container').last().attr('data-num');
        		window.location.href='#education'+(Number(cutid)-1);
        		//获取焦点
        		$('#education'+cutid+' textarea[name=school]').focus();
        		return false;
        	}
    	}
        if($('.background-education-container').length==0||addtype==0){
        		$('.homepageTemplate').setTemplateURL(projectName+'myEducationBgTemplate.html');
            	var isContinueAdd='';
            	if(addtype=='1'){
            		isContinueAdd=1;
            	}else{
            		isContinueAdd=0;
            		randomid++;
            	}
            	var $lastDiv=$('#recommended-sections').find('.background-education-container');
            	var anchorlen=$lastDiv.length+1;
            	var data={
            			isContinueAdd:isContinueAdd,
            			isEdit:0,
            			randomid:randomid,
            			anchor:anchorlen,
            			topDegree:''
            	}
            	$('.homepageTemplate').processTemplate(data);
            	if($lastDiv.last().length==0){
            		$('#recommended-sections').append($('.homepageTemplate').html());
            		window.location.href='#education'+anchorlen;
            	}else{
            		$lastDiv.last().after($('.homepageTemplate').html());
            		window.location.href='#education'+(anchorlen-1);
            	}
            	$('.homepageTemplate').empty();
            	
            	$('#education'+anchorlen).css('background','#f9f9f9');
            	$('#schoolstartTime'+randomid).dateSelect();
            	$('#schoolendTime'+randomid).dateSelect();
        	}else{
        		    $('#addcontinuous').trigger('click');
        			/*window.location.href='#education1';
        			$('#education1').css('border-color','#0867c5');*/
        	}
   
    });
    //点击添加工作经历
    $('.recommended-section-work .recommended-section-add,#addcontinuouswork,#control_gen_3,button[name=addjob]').live('click',function(){
    	var addtype=$(this).attr('data-addtype');
    	if(addtype==0){
    		//如果存在工作经历新增框    就提示先编辑未保存的 内容
    		if($('.background-workexperience-container').last().find('input[name=jobtitle]').val().length==0){
        		var cutid=$('.background-workexperience-container').last().attr('data-num');
        		window.location.href='#work'+(Number(cutid)-1);
        		//获取焦点
        		$('#work'+cutid+' textarea[name=position]').focus();
        		return false;
        	}
    	}
        if($('.background-workexperience-container').length==0||addtype==0){
        		$('.homepageTemplate').setTemplateURL(projectName+'myWorkExperienceTemplate.html');
            	var isContinueAdd='';
            	if(addtype=='1'){
            		/*$(this).addClass('disabled');*/
            		isContinueAdd=1;
            	}else{
            		isContinueAdd=0;
            		randomid++;
            	}
            	var $lastDiv=$('#recommended-sections').find('.background-workexperience-container');
            	var anchorlen=$lastDiv.length+1;
            	var data={
            			isContinueAdd:isContinueAdd,
            			isEdit:0,
            			randomid:randomid,
            			anchor:anchorlen
            	}
            	$('.homepageTemplate').processTemplate(data);
            	if($lastDiv.last().length==0){
            		$('#recommended-sections').append($('.homepageTemplate').html());
            		window.location.href='#work'+anchorlen;
            	}else{
            		$lastDiv.last().after($('.homepageTemplate').html());
            		window.location.href='#work'+(anchorlen-1);
            	}
            	$('.homepageTemplate').empty();
            	
            	$('#work'+anchorlen).css('background','#f9f9f9');
            	$('#jobstartTime'+randomid).dateSelect();
            	$('#jobendTime'+randomid).dateSelect();
        	}else{
        		    $('#addcontinuouswork').trigger('click');
        			/*window.location.href='#work1';
        			$('#work1').css('border-color','#0867c5');*/
        	}
    })
    
    //工作经验点击保存
    $('a[name=myworksave]').live('click',function(){
    	//公司名字
    	var companyname=$.trim($(this).parents('.background-workexperience-container').find('.companyname .content').val());
    	var onjobstartTime1=$.trim($(this).parents('.background-workexperience-container').find('.onjobstartTime').val());
    	var onjobendTime1='';
    	var onjobendTime='';
    	var end='';
    	if(!$(this).parents('.background-workexperience-container').find('.onjobendTime').is(":visible")){
    		onjobendTime1='至今';
    		onjobendTime='至今';
    	}else{
    	    onjobendTime1=$.trim($(this).parents('.background-workexperience-container').find('.onjobendTime').val());
    	    onjobendTime=$.trim($(this).parents('.background-workexperience-container').find('.onjobendTime').val()+' 00:00:00');
    	}
    	
    	//在校开始时间
    	var onjobstartTime=$.trim($(this).parents('.background-workexperience-container').find('.onjobstartTime').val()+' 00:00:00');

    	
    	//工作描述
    	var description=$(this).parents('.background-workexperience-container').find('.workcontent .content').val();
    	description=description.replace(/\n/g,'<br/>');
    	if(description.replace(/[^x00-xFF]/g,'**').length>512){
    		$(this).parents('.background-workexperience-container').find('.workcontent b').show();
    		$(this).parents('.background-workexperience-container').find('.workcontent .item-msg-content').show();
    		return false;
    	}
    	
    	if((onjobstartTime!=''&&onjobendTime=='')||(onjobstartTime==''&&onjobendTime!='')){
    		$(this).parents('.background-workexperience-container').find('.onjobtime b').show();
    		$(this).parents('.background-workexperience-container').find('.onjobtime .item-msg-content').show();
    		return false;
    	}
	    var start=new Date(onjobstartTime1.replace("-", "/").replace("-", "/"));
	    if(onjobendTime1!='至今'){
	    	end=new Date(onjobstartTime1.replace("-", "/").replace("-", "/")); 
	    }else{
	    	end=new Date();
	    }
 	    if(end<start){
 	    	$(this).parents('.background-workexperience-container').find('.onjobtime b').show();
 	    	$(this).parents('.background-workexperience-container').find('.onjobtime .item-msg-content').html('开始时间应小于结束时间').show();
 	        return false;  
 	    }  
	    
    	//工作职位
    	var position=$.trim($(this).parents('.background-workexperience-container').find('.position .content').val());
    	if(position.replace(/[^x00-xFF]/g,'**').length>128){
    		$(this).parents('.background-workexperience-container').find('.position b').show();
    		$(this).parents('.background-workexperience-container').find('.position .item-msg-content').show();
    		return false;
    	}
    	if(companyname!=''){
    		if(onjobendTime1==''){
    			$(this).parents('.background-workexperience-container').find('.onjobtime b').show();
        		$(this).parents('.background-workexperience-container').find('.onjobtime .item-msg-content').show();
        		return false;
    		}
    		if(onjobstartTime=='00:00:00'||onjobendTime=='00:00:00'){
    			$(this).parents('.background-workexperience-container').find('.onjobtime b').show();
        		$(this).parents('.background-workexperience-container').find('.onjobtime .item-msg-content').html('请选择在职时间').show();
        		return false;
    		}
    	}
    	
    	if(companyname==''){
    		$(this).parents('.background-workexperience-container').find('.companyname b').show();
    		$(this).parents('.background-workexperience-container').find('.companyname .item-msg-content').html('请填写公司名称').show();
    		return false;
    	}else if(companyname.replace(/[^x00-xFF]/g,'**').length>256){
    		$(this).parents('.background-workexperience-container').find('.companyname b').show();
    		$(this).parents('.background-workexperience-container').find('.companyname .item-msg-content').html('公司名称超出最大限制').show();
    		return false;
    	}
    	else{
        	saveWorkExperience(companyname,onjobstartTime,onjobendTime,position,description,$(this))
    	}
    })
    
    //教育背景点击保存
    $('a[name=myeducationsave]').live('click',function(){
    	//学校名字
    	var schoolname=$.trim($(this).parents('.background-education-container').find('.schoolname .content').val());
    	var schoollogo=$.trim($(this).parents('.background-education-container').find('input[name=schoollogo]').val());
    	var schoolstartTime1=$.trim($(this).parents('.background-education-container').find('.schoolstartTime').val());
    	var schoolendTime1=$.trim($(this).parents('.background-education-container').find('.schoolendTime').val());
    	//在校开始时间
    	var schoolstartTime=$.trim($(this).parents('.background-education-container').find('.schoolstartTime').val()+' 00:00:00');
    	//在校结束时间
    	var schoolendTime=$.trim($(this).parents('.background-education-container').find('.schoolendTime').val()+' 00:00:00');
    	if((schoolstartTime!=''&&schoolendTime=='')||(schoolstartTime==''&&schoolendTime!='')){
    		$(this).parents('.background-education-container').find('.schooltime b').show();
    		$(this).parents('.background-education-container').find('.schooltime .item-msg-content').show();
    		return false;
    	}
	    var start=new Date(schoolstartTime1.replace("-", "/").replace("-", "/"));  
	    var end=new Date(schoolendTime1.replace("-", "/").replace("-", "/"));  
	    if(end<start){
	    	$(this).parents('.background-education-container').find('.schooltime b').show();
	    	$(this).parents('.background-education-container').find('.schooltime .item-msg-content').html('开始时间应小于结束时间').show();
	        return false;  
	    }  
    	//学历
    	var topDegree=$.trim($(this).parents('.background-education-container').find('.topDegree .degree-select').val());
    	//所学专业
    	var profession=$.trim($(this).parents('.background-education-container').find('.profession .content').val());
    	if(profession.replace(/[^x00-xFF]/g,'**').length>512){
    		$(this).parents('.background-education-container').find('.profession b').show();
	    	$(this).parents('.background-education-container').find('.profession .item-msg-content').show();
	        return false;  
    	}
    	if(schoolname!=''){
    		if(schoolendTime1==''){
    			$(this).parents('.background-education-container').find('.schooltime b').show();
    			$(this).parents('.background-education-container').find('.schooltime .item-msg-content').show();
    			return false;
    		}
    		if(schoolstartTime=='00:00:00'||schoolendTime=='00:00:00'){
    			$(this).parents('.background-education-container').find('.schooltime b').show();
    	    	$(this).parents('.background-education-container').find('.schooltime .item-msg-content').html('请选择在校时间').show();
    	        return false;  
    		}
    	}
    	//学校名称必填
    	if(schoolname==''){
    		$(this).parents('.background-education-container').find('.schoolname b').show();
    		$(this).parents('.background-education-container').find('.schoolname .item-msg-content').html('请填写学校名称').show();
    		return false;
    	}else if(schoolname.replace(/[^x00-xFF]/g,'**').length>256){
    		$(this).parents('.background-education-container').find('.schoolname b').show();
    		$(this).parents('.background-education-container').find('.schoolname .item-msg-content').html('学校名称超出最大限制').show();
    		return false;
    	}else{
        	saveEducationBg(topDegree=="--请选择--"?'':topDegree,schoolname,schoolstartTime,schoolendTime,profession,schoollogo,$(this))
    	}
    })
    //教育背景点击取消
    $('a[name=myeducationcancel]').live('click',function(){
    	hiddenEducationBg($(this));
    })
    //工作经验点击取消
    $('a[name=myworkcancel]').live('click',function(){
    	hiddenWorkExperience($(this));
    })
    //修改教育背景
    $('a[name=editschooltime],a[name=editschoolname],a[name=editsmajor],a[name=editsdegree]').live('click',function(){
    	var schoolname=$.trim($(this).parents('.background-education-container').find('input[name=school]').val());
    	var schoolLogo=$.trim($(this).parents('.background-education-container').find('input[name=schoollogo]').val());
    	var schoolstartTime=$.trim($(this).parents('.background-education-container').find('input[name=starttime]').val());
    	if(schoolstartTime.indexOf(' ')!=-1){
    		schoolstartTime=schoolstartTime.substring(0,schoolstartTime.indexOf(' '));
    	}
    	var schoolendTime=$.trim($(this).parents('.background-education-container').find('input[name=endtime]').val());
    	if(schoolendTime.indexOf(' ')!=-1){
    		schoolendTime=schoolendTime.substring(0,schoolendTime.indexOf(' '));
    	}
    	
    	var profession=$.trim($(this).parents('.background-education-container').find('input[name=major]').val());
    	var topDegree=$.trim($(this).parents('.background-education-container').find('input[name=degree]').val());
    	var currentid=$(this).attr('data-currentid');
    	editEducationData(schoolname,schoolstartTime,schoolendTime,topDegree,profession,currentid,schoolLogo,$(this));
    })
    //修改工作经历
    $('a[name=editscompanyname],a[name=editsonjobtime],a[name=editsposition],a[name=editsdescription]').live('click',function(){
    	var currentid=$(this).attr('data-currentid');
    	getWorkData($(this).parents('.background-workexperience-container'),currentid,$(this));
    })
   //目前职位,教育背景修改 调到对应的数据层 加颜色
   $('a[name=editoncompany],a[name=editonschool]').live('click',function(){
	   $('#'+$(this).attr('href').substring(1)).css('border-color','#0867c5')
   })

   //修改我的联系方式
   $('a[name=editemail],a[name=editphone]').live('click',function(){
	   var email=$('input[name=email]').val();
	   var phone=$('input[name=phone]').val();
	   var data={
			   email:email,
			   phone:phone,
	   }
	   $('.homepageTemplate').setTemplateURL(projectName+'myContanctTemplate.html');
	   $('.homepageTemplate').processTemplate(data);
	   $('#contact-info-section .email,#contact-info-section .phone').remove();
	   $('#contact-info-section').append($('.homepageTemplate').html());
	   $('.emailinfo textarea[name=email]').val(email);
	   $('.phoneinfo textarea[name=phone]').val(phone);
	   $('a[name=editemail],a[name=editphone]').hide();
	   $('.homepageTemplate').empty();
   });
   //保存我的联系方式
    $('a[name=mycontanctsave]').live('click',function(){
       $('.email b,.phone b,.email .item-msg-content,.phone .item-msg-content').hide();
       var email=$('.emailinfo textarea[name=email]').val();
  	   var phone=$('.phoneinfo textarea[name=phone]').val();
		if (email != null && email != '' && !isEmail(email)) {
			$('.email b').show();
			$('.email .item-msg-content').html('邮箱格式不正确').show();
			return false;
		}
        if(email.length>32){
        	$('.email b').show();
			$('.email .item-msg-content').html('邮箱超出最大限制').show();
			return false;
        }
		if (phone != null && phone != '' && !isTel(phone)) {
			$('.phone b').show();
			$('.phone .item-msg-content').show();
			return false;
		}
  	   saveMyContanct(email,phone);
    })
    //取消我的联系方式
    $('a[name=mycontanctcancel]').live('click',function(){
    	 var email=$(this).attr('data-email');
    	 var phone=$(this).attr('data-phone');
    	 $('.email b,.phone b,.email .item-msg-content,.phone .item-msg-content').hide();
    	 updateMyContanctData(email,phone)
    })
    
     //关注与取消关注人
    $('.detail-list .zg-btn,.profile-func .zg-btn,#zh-profile-follows-list .zg-btn').live('click',function(){
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
    //技能点击保存
    $('a[name=myskillsave]').live('click',function(){
    	var id=$(this).attr('data-skillid');
    	SaveSkills(id,$(this));
    })
    
    //个人主页文档加载更多
    /*tbl_docs  tbl_article   tbl_sites tbl_topics                  title  
    tbl_books  bookname
    tbl_courses  coursesName*/
    $('.pj-load-more').live('click',function(){
    	$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
    	loadMoreData($(this));
    })
    //粉丝和他的关注加载更多
	$('.pjfans-load-more,.pjatten-load-more').live('click',function(){
		$(this).addClass('loading').empty().append("<span class='capture-loading'></span>加载中");
		loadMoreFansAndAttenData($(this));
	});
    //选择6大类获取分享的数据
    $('#profile-navbar .pjitem').live('click',function(){
    	addClassFun($(this));
    	loadShareData($(this));
    });
    //他的关注
    $('#otheratten,#fans-navbar .pjitem:eq(0)').live('click',function(){
    	addClassFun($(this));
    	otherAtten($(this));
    })
    //他的粉丝
    $('#otherfans,#fans-navbar .pjitem:eq(1)').live('click',function(){
    	addClassFun($(this));
    	otherFans($(this));
    })
    //最近访问
    $('#fans-navbar .pjitem:eq(2)').live('click',function(){
    	addClassFun($(this));
    	currVisitor($(this));
    })
    //右侧最近分享加载更多
    $('#moreShare').live('click',function(){
    	$('#personal-share').addClass('current').siblings().removeClass('current');
    	$('.section-container,#fans-navbar,#zh-profile-follows-list').hide();
    	$('#profile-navbar,.zm-profile-section-wrap').show();
        $('#profile-navbar .pjitem:eq(0)').trigger("click");
    })
    //右侧最近访问加载更多
    $('#moreVisitor').live('click',function(){
    	$('#personal-attention').addClass('current').siblings().removeClass('current');
    	$('#fans-navbar').show();
    	$('#profile-navbar,.zm-profile-section-wrap,.section-container').hide();
    	$('#fans-navbar .pjitem:eq(2)').trigger("click");
    });
    //最近访问加载更多
    $('.pjvisitor-load-more').live('click',function(){
    	addClassFun($(this));
    	moreVisitor($(this));
    });
    //输入学校 搜索
    $('textarea[name=school]').live('input propertychange',function(){
    	$(this).parents('.background-education-container').find('input[name=schoollogo]').val('');
    	var oldval=$(this).prev().val();
    	var newval=$(this).val();
    	if(newval !== null &&newval !== undefined&&$.trim(newval).length!=0&&$.trim(oldval).length!=$.trim(newval).length){
			findMoreSchool($(this));
		}else if($.trim(newval).length==0){
			$(this).parents('.schoolname').next().hide();
		}
    	$(this).parents('.schoolname').find('input[name=pj-autocomplete]').val(newval);
    })
    //点击搜索出来的学校名称
    $('.typeahead-results-container li').live('click',function(){
    	var schoolname=$(this).attr('data-schoolname');
    	var schoollogo=$(this).attr('data-schoollogo');
    	$(this).parents('.typeahead-results-container').prev().find('textarea[name=school]').val(schoolname);
    	$(this).parents('.background-education-container').find('input[name=schoollogo]').val(schoollogo);
    	$(this).parents('.typeahead-results-container').hide();
    });
    // 在页面任意位置点击而触发此事件(隐藏学校搜索下拉框)
    $(document).click(function(e) {
    	if($('.typeahead-results-container').length>0)
    		$('.typeahead-results-container').hide();
    })
    //获取请求的url参数
	var strHref = this.location.href;
	//0表示点击用户图像上面的关注   1代表粉丝   2代表分享  3代表最近访问更多
    var requesType = strHref.getQuery("requesType");
    if(requesType!=null){
    	if(requesType==0){
    		$('#otheratten').trigger('click');
    	}else if(requesType==1){
    		$('#otherfans').trigger('click');
    	}else if(requesType==2){
            $('#personal-share').trigger('click');
    	}else{
    		$('#moreVisitor').trigger('click');
    	}
    }
    //删除学校信息
    $('a[name=deleteschool]').live('click',function(){
    	var schoolid=$(this).attr('data-id');
    	var $this=$(this);
    	$.confirm({
			'title': '删除学校',
			'message': "确定要删除学校吗？",
			'buttons': {
				'确认': {
					'class': 'blue',
					'action': function () {
						deleteSchool($this,schoolid);
					}
				},
				'取消': {
					'class': 'gray',
					'action': function () {
					}
				}
			}
		});
    });
    //删除工作职位
    $('a[name=deletework]').live('click',function(){
    	var workid=$(this).attr('data-id');
    	var $this=$(this);
    	$.confirm({
			'title': '删除职位',
			'message': "确定要删除此职位吗？",
			'buttons': {
				'确认': {
					'class': 'blue',
					'action': function () {
						deleteWork($this,workid);
					}
				},
				'取消': {
					'class': 'gray',
					'action': function () {
					}
				}
			}
		});
    })
    
    //工作经历  选择至今
    $('.still-here').live('click',function(){
    	if($(this).hasClass('select-icon')){
    		$(this).removeClass('select-icon');
    		$(this).prev().find('.onjobendTime').show();
    		$(this).prev().find('.current-position').hide();
    	}else{
    		$(this).addClass('select-icon');
    		$(this).prev().find('.onjobendTime').hide();
    		$(this).prev().find('.current-position').show();
    	}
    })
    //仍然在职
    $('.isCurrent').live('click',function(){
    	$(this).prev().trigger('click');
    })
    
})
//删除职位
function deleteWork(obj,workid){
	$.ajax({
		type:"POST",
     	url:"/myHome/delWorkExpInfo",
     	data:{id:workid},
     	dataType:"json",
     	async:false,
     	success:function(data){
     		if(data.returnStatus=='000'){
     			obj.parents('.background-workexperience-container').remove();
     		}else{
     			
     		}
     	}
	})
}
//删除学校信息
function deleteSchool(obj,schoolid){
	$.ajax({
		type:"POST",
     	url:"/myHome/delEduInfo",
     	data:{id:schoolid},
     	dataType:"json",
     	async:false,
     	success:function(data){
     		if(data.returnStatus=='000'){
     			obj.parents('.background-education-container').remove();
     		}else{
     			
     		}
     	}
	})
}
//搜索学校
function findMoreSchool(obj){
	var conds=$(obj).val();
  	//过滤掉空格 "" null 等
    if($.trim(conds).length>0){
		 $.ajax({
         	type:"POST",
         	url:"/myHome/searchSchool/"+conds,
         	dataType:"json",
         	async:false,
         	success:function(data){
         		if(data.returnStatus=='000'&&data.schoolList.length>0){//返回成功
         		   initTitleContentBySearchSchool(data.schoolList,obj);
         		   
        		}else{//返回失败
        			obj.parents('.background-education-container').find('input[name=schoollogo]').val('');
        		}
        	}
          });
     }else{

	 }
}
//追加学校信息
function initTitleContentBySearchSchool(data,obj){
	obj.parents('.schoolname').next().empty();
	var container_div="";
        container_div+="<ul class='hasImage'>";
	$.each(data,function(index,item){
		container_div+="<li data-id='"+item.id+"' data-schoollogo='"+(item.schoollogo==undefined?'/image/default/default_schoollogo.jpg':item.schoollogo)+"' data-schoolname='"+item.schoolname+"'>";
		container_div+="<a href='javascript:void(0)' target='_blank'><img src="+(item.schoollogo==undefined?'/image/default/default_schoollogo.jpg':item.schoollogo)+"  alt=''/></a>"
		container_div+="  <h4>"+item.schoolname+"</h4>";
		container_div+="  <div>"+(item.schoolcity==undefined?'':item.schoolcity)+"</div>"
		container_div+="</li>";
	})
	container_div+="</ul>";
	obj.parents('.schoolname').next().append(container_div).show();
}
function addClassFun(obj){
	obj.addClass('current').siblings().removeClass('current');
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
				 $('#zh-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无访问信息</span>").show();
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
				$('.pagetemplate').setTemplateURL(projectName+'visitLoadMoreTemplate.html');
		        $('.pagetemplate').processTemplate(datamodel);
		        $('#zh-profile-follows-list .zh-general-list').empty().append($('.pagetemplate').html());
				$('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
				$('.pagetemplate').empty();
				 
				 //判断当前是否有加载更多
				 if(data.visitors.last>1){
					 var mhtml='<a href="javascript:;" data-pageno="1"   data-userid='+userid+' data-sumpage='+data.visitors.last+' class="zg-btn-white zg-r3px zu-button-more pjvisitor-load-more">更多</a>'  
				 }
				 $('#zh-profile-follows-list').append(mhtml);
				 $('#zh-profile-follows-list').show();
				 $('#personal-attention').addClass('current').siblings().removeClass('current');
				 $('#fans-navbar .pjitem:eq(2)').addClass('current').siblings().removeClass('current');
				 intoUserInfo();
			}
			
		 }
		})
}
//定时刷新界面上边框的颜色
function startrequest() { 
  $('.section-container').each(function(){
	  $(this).css('border-color','#cccfd3')
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
				 $('#zh-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无信息</span>").show();
				 
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
				 $('.pagetemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
				 $('.pagetemplate').processTemplate(model);
				 $('#zh-profile-follows-list .zh-general-list').empty().append($('.pagetemplate').html());
				 $('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
				 $('.pagetemplate').empty();
				 
				 //判断当前是否有加载更多
				 if(data.attenManPage.last>1){
					 var mhtml='<a href="javascript:;" data-pageno="1"  data-isfans="0" data-userid='+data.user.userid+' data-sumpage='+data.attenManPage.last+' class="zg-btn-white zg-r3px zu-button-more pjatten-load-more">更多</a>'  
				 }
				 $('#zh-profile-follows-list').append(mhtml);
				 
				 $('#zh-profile-follows-list').show();
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
				$('#zh-profile-follows-list .zh-general-list').empty().append("<span class='nosharelist'>暂无信息</span>").show();
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
				 $('.pagetemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
				 $('.pagetemplate').processTemplate(model);
				 $('#zh-profile-follows-list .zh-general-list').empty().append($('.pagetemplate').html());
				 $('.pjfans-load-more,.pjatten-load-more,.pjvisitor-load-more').remove();
				 $('.pagetemplate').empty();
				 
				 $('#zh-profile-follows-list').show();
				 //判断当前是否有加载更多
				 if(data.myFansPage.last>1){
					 var mhtml='<a href="javascript:void(0);" data-pageno="1" data-isfans="1" data-userid='+data.user.userid+' data-sumpage='+data.myFansPage.last+' class="zg-btn-white zg-r3px zu-button-more pjfans-load-more">更多</a>'  
				 }
				 $('#zh-profile-follows-list').append(mhtml);
				 /*//加载更多去掉
				 $('#pjatten-load-more').remove();*/
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
				 $('#zh-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
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
				 $('#zh-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
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
    		$('#zh-profile-follows-list .zh-general-list').append($('.pagetemplate').html());
			$('.pagetemplate').empty();				
   	        $('.pjvisitor-load-more').attr('data-pageno',Number(pageNo)+1);
     	       obj.removeClass('loading').empty().append('更多');
     	     if(Number(sumpage)==Number(pageNo)+1)
     		   $('.pjvisitor-load-more').hide();
     	    intoUserInfo();
    	}
      	
    })
}
//根据点击表名转成对应中文
function convertCh(tablename){
  var name;
  switch(tablename){
    case 'tbl_docs':
    	name='文档';
    	break;
    case 'tbl_topics':
    	name='话题';
    	break;
    case 'tbl_books':
    	name='书籍';
    	break;
    case 'tbl_article':
    	name='文章';
    	break;
    case 'tbl_courses':
    	name='课程';
    	break;
    default:
    	name='站点';  
    	break;
  }
  $('.zm-profile-section-name').html(name);
  return name;
}
//他的分享6大类切换--加载html   新增tablename add byJerry
function loadMoreHtml(shares,name,tablename){
	var shtml='';
	if(shares.length==0){
		shtml='<span class="nosharelist">这家伙很懒，还没有分享'+name+'</span>';
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
//保存我的联系方式
function saveMyContanct(email,phone){
	$.ajax({
		type:'POST',
	    url:"/myHome/updUserInfo",
	    data:{contactEmail:email,contactTel:phone},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		updateMyContanctData(email,phone)
	    	}
	    	else{
	    		
	    	}
	    }
	})
}
//修改的联系方式 界面赋值
function updateMyContanctData(email,phone){
	$('.emailinfo textarea[name=email]').hide();
	$('.emailinfo').text(email);
	
	$('.phoneinfo textarea[name=phone]').hide();
	$('.phoneinfo').text(phone);
	//同时更改界面中input的值
	$('input[name=email]').val(email);
	$('input[name=phone]').val(phone);
	
	$('a[name=mycontanctsave]').parent().hide();
	$('a[name=editemail],a[name=editphone]').show();
}
//获取界面中工作经验的数据 为了修改
function getWorkData($layer,currentid,obj){
	var companyname=$.trim($layer.find('input[name=companyname]').val());
	var schoolstartTime=$.trim($layer.find('input[name=onjobstartTime]').val());
	if(schoolstartTime.indexOf(' ')!=-1){
		schoolstartTime=schoolstartTime.substring(0,schoolstartTime.indexOf(' '));
	}
	var schoolendTime=$.trim($layer.find('input[name=onjobendTime]').val());
	if(schoolendTime.indexOf(' ')!=-1){
		schoolendTime=schoolendTime.substring(0,schoolendTime.indexOf(' '));
	}
	var jobtitle=$.trim($layer.find('input[name=jobtitle]').val());
	var description=$.trim($layer.find('input[name=description]').val());
	/*description=description.replace('<br/>',/\n/g);*/
	editWorkData(companyname,schoolstartTime,schoolendTime,jobtitle,description,currentid,obj);
}
//保存教育背景
function saveEducationBg(topDegree,schoolname,schoolstartTime,schoolendTime,profession,schoollogo,obj){
	    var currentid=obj.parents('.background-education-container').find('input[name=currentid]').val();
		$.ajax({
			type:'POST',
		    url:"/myHome/updEduInfo",
		    data:{degree:topDegree,school:schoolname,major:profession,starttime:schoolstartTime,endtime:schoolendTime,id:currentid},
		    dataType:"json",
		    success:function(data){
		    	if(data.returnStatus=='000'){//返回成功
		    		updateMyEducationData(topDegree,schoolname,schoolstartTime,schoolendTime,profession,obj,data.obj,schoollogo)
		    	}
		    	else{
		    		console.log("updEdu  failed");
		    	}
		    }
	})
}
//保存工作经验
function saveWorkExperience(companyname,onjobstartTime,onjobendTime,position,description,obj){
	var currentid=obj.parents('.background-workexperience-container').find('input[name=currentid]').val();
	$.ajax({
		type:'POST',
	    url:"/myHome/updWorkExpInfo",
	    data:{company:companyname,jobtitle:position,starttime:onjobstartTime,strEndtime:onjobendTime=="至今"?'':onjobendTime,id:currentid,description:description},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		updateMyWorkData(companyname,onjobstartTime,onjobendTime,position,description,obj,data.obj)
	    	}
	    	else{
	    		
	    	}
	    }
})
}
//编辑基本信息
function editMyIntroInfo(obj){
	var ismarry=$('input[name=ismarry]').val();
    if(ismarry=='1'){
		ismarry='1';
	}else{
		ismarry='0';
	}

	var birthdayone=$('input[name=birthdayone]').val();
	var birthdaytwo=$('input[name=birthdaytwo]').val();
	var description=$('.intro .introinfo').html();
	var data={
			ismarry:ismarry,
			birthdayone:Number(birthdayone==""?0:birthdayone),
			birthdaytwo:Number(birthdaytwo==""?0:birthdaytwo),
			isEdit:1,
			description:description
	}
	$('.homepageTemplate').setTemplateURL(projectName+'myintroTemplate.html');
	$('.homepageTemplate').processTemplate(data);
	obj.parents('.background-selfinfo-container').after($('.homepageTemplate').html());
	obj.parents('.background-selfinfo-container').remove();
	$('.homepageTemplate').empty();
	if(description!=''){
		$('.intro textarea[name=recommend]').val(description);
	}
}
//教育背景点击取消
function hiddenEducationBg(obj){
	var isedit=obj.parents('.background-education-container').find('input[name=isedit]').val();
	if(isedit=='0')
      obj.parents('.background-education-container').remove();
	else{
		var schoolname=obj.attr('data-school');
		var major=obj.attr('data-major');
		var degree=obj.attr('data-degree');
		var starttime=obj.attr('data-starttime');
		var endtime=obj.attr('data-endtime');
		var currentid=obj.attr('data-currentid');
		var schoollogo=obj.attr('data-schoollogo');
		updateMyEducationData(degree,schoolname,starttime,endtime,major,obj,currentid,schoollogo);

	}
}
//教育背景点击取消
function hiddenWorkExperience(obj){
	var isedit=obj.parents('.background-workexperience-container').find('input[name=isedit]').val();
	if(isedit=='0')
      obj.parents('.background-workexperience-container').remove();
	else{
		var companyname=obj.attr('data-company');
		var description=obj.attr('data-description');
		var onjobstartTime=obj.attr('data-onjobstartTime');
		var onjobendTime=obj.attr('data-onjobendTime');
		var jobtitle=obj.attr('data-jobtitle');
		var currentid=obj.attr('data-currentid');
		updateMyWorkData(companyname,onjobstartTime,onjobendTime,jobtitle,description,obj,currentid);
	}
}
//修改教育背景 加载模板
function editEducationData(schoolname,schoolstartTime,schoolendTime,topDegree,profession,currentid,schoollogo,obj){
	randomid++;
	var data={
			schoolname:schoolname,
			schoolstartTime:schoolstartTime,
			schoolendTime:schoolendTime,
			profession:profession,
			topDegree:topDegree,
			schoollogo:schoollogo,
			isEdit:1,
			currentid:currentid,
			isContinueAdd:0,
			randomid:randomid
	}
	$('.homepageTemplate').setTemplateURL(projectName+'myEducationBgTemplate.html');
	$('.homepageTemplate').processTemplate(data);
	obj.parents('.background-education-container').after($('.homepageTemplate').html());
	$('.homepageTemplate').empty();
	if(schoolname!='')
		obj.parents('.background-education-container').next().find('.schoolname textarea[name=school]').val(schoolname);
	if(topDegree!='')
		obj.parents('.background-education-container').next().find('.topDegree textarea[name=degree]').val(topDegree);
	if(profession!='')
		obj.parents('.background-education-container').next().find('.profession textarea[name=major]').val(profession);
	$('#schoolstartTime'+randomid).dateSelect();
	$('#schoolendTime'+randomid).dateSelect();
	obj.parents('.background-education-container').remove();
}

//修改工作经历
function editWorkData(companyname,onjobstartTime,onjobendTime,jobtitle,description,currentid,obj){	
	randomid++;
	var data={
			companyname:companyname,
			onjobstartTime:onjobstartTime,
			onjobendTime:onjobendTime=='至今'?'':onjobendTime,
			description:description,
			jobtitle:jobtitle,
			isEdit:1,
			currentid:currentid,
			isContinueAdd:0,
			randomid:randomid
	}
	$('.homepageTemplate').setTemplateURL(projectName+'myWorkExperienceTemplate.html');
	$('.homepageTemplate').processTemplate(data);
	obj.parents('.background-workexperience-container').after($('.homepageTemplate').html());
	$('.homepageTemplate').empty();
	if(companyname!='')
		obj.parents('.background-workexperience-container').next().find('.companyname textarea[name=company]').val(companyname);
		obj.parents('.background-workexperience-container').next().find('textarea[name=description]').val(description.replace(/<br\s*\/?\s*>/ig, '\n'));
	if(jobtitle!='')
		obj.parents('.background-workexperience-container').next().find('textarea[name=position]').val(jobtitle);
	$('#jobstartTime'+randomid).dateSelect();
	$('#jobendTime'+randomid).dateSelect();
	obj.parents('.background-workexperience-container').remove();

}
//创建教育背景html
function createEducationBgHtml(text){
	var html='';
	html+='<div class="header">';
	
	if(text=='工作经历'){
		html+='  <span class="education-icon">'+text+'</span>';
		html+='  <a href="javascript:void(0)" id="addcontinuouswork" class="add-all ml20  add-all-able" data-addtype="0">';
	}else{
		html+='  <span class="work-icon">'+text+'</span>';
		html+='  <a href="javascript:void(0)" id="addcontinuous" class="add-all ml20  add-all-able" data-addtype="0">';
	}
	html+='    <span>+</span>&nbsp;继续添加'
	html+='  </a>'
	html+='</div>';
	return html;
}
//个人信息点击取消
function hiddenSelfInfo(obj){
	var isedit=$('input[name=isedit]').val();
	if(isedit=='0')
	  $('.background-selfinfo-container').remove();
	else{
		var birthdaymonth=obj.attr('data-birthdayone');
		var birthdayday=obj.attr('data-birthdaytwo');
		var marriage=obj.attr('data-ismarry');
		var description=obj.attr('data-description');
		updateSelfInfo(birthdayday,birthdaymonth,marriage,description);
	}
      
}
//更改数据库中对应个人信息字段
function updateSelfInfo(birthdayday,birthdaymonth,marriage,description){
	$.ajax({
		type:'POST',
	    url:"/myHome/updUserInfo",
	    data:{birthdayone:birthdaymonth,birthdaytwo:birthdayday,ismarry:marriage,description:description},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		updateMyIntroData(birthdayday,birthdaymonth,marriage,description)
	    	}
	    	else{
	    		
	    	}
	    }
	})
}
//当点击完保存之后 个人信息界面更新数据
function updateMyIntroData(birthdayday,birthdaymonth,marriage,description){
	if(birthdayday=='' && birthdaymonth=='' && marriage=='' && description==''){
		hiddenSelfInfo();
	}
	var birthday='';
	if(birthdaymonth!=''){
		birthday+=birthdaymonth+'月'
		birthday+=birthdayday+'日'
		$('.birthday .birth').html(birthday);
	}
	$('a[name=editbirthday]').show();
	if(marriage!=''&&marriage!='2'){
		if(marriage=='1'){
			marriage='已婚'
		}else{
			marriage='未婚'
		}
		$('.birthday .marriage').html("婚姻状况:&nbsp;&nbsp;"+marriage);
	}
	$('a[name=editmarriage]').show();
	if(description!=''){
		$('.intro .introinfo').html(description);
	}
	$('a[name=editintroinfo]').show();
	$('#birthdaymonth,#birthdayday,#marriage,textarea[name=recommend],.background-selfinfo-container .zm-command').hide();
}
//教育背景界面赋值
function updateMyEducationData(topDegree,schoolname,schoolstartTime,schoolendTime,profession,obj,id,schoollogo){
	if(schoolname==''&&schoolstartTime==''&&schoolendTime==''&&profession==''){
		hiddenEducationBg();
	}
	if(schoolname!=''){
		obj.parents('.background-education-container').find('.education .schoolname').text(schoolname);
		obj.parents('.background-education-container').find('input[name=school]').val(schoolname);
	}else{
		obj.parents('.background-education-container').find('input[name=school]').val('');
	}
	obj.parents('.background-education-container').find('a[name=editschoolname]').show();
	if(schoolstartTime!=''){
		var starttime=obj.parents('.background-education-container').find('.schoolstartTime').val();
		obj.parents('.background-education-container').find('input[name=starttime]').val(starttime);
		starttime=new Date(Date.parse(starttime.replace(/-/g, '/')));
		var endtime=obj.parents('.background-education-container').find('.schoolendTime').val();
		obj.parents('.background-education-container').find('input[name=endtime]').val(endtime);
		endtime=new Date(Date.parse(endtime.replace(/-/g, '/')));
		//计算两个日期之间相差多少
		var eduyears=better_time(starttime,endtime);
		starttime=starttime.getFullYear()+'年'+fixZero(starttime.getMonth()+1,2)+'月'+starttime.getDate()+'日';
		endtime=endtime.getFullYear()+'年'+fixZero(endtime.getMonth()+1,2)+'月'+endtime.getDate()+'日';
		obj.parents('.background-education-container').find('.graduate .schooltime').html("在校时间:&nbsp;&nbsp;"+starttime+'&nbsp; – &nbsp;'+endtime+"&nbsp;&nbsp;"+"("+eduyears+")");
		obj.parents('.background-education-container').find('a[name=editschooltime]').show();
	}
	if(topDegree!=''){
		obj.parents('.background-education-container').find('.degree .topDegree').text(topDegree);
		obj.parents('.background-education-container').find('input[name=degree]').val(topDegree);
	}else{
		obj.parents('.background-education-container').find('input[name=degree]').val('');
	}
	obj.parents('.background-education-container').find('a[name=editsdegree]').show();
	if(profession!=''){
		obj.parents('.background-education-container').find('.major .profession').text(profession);
		obj.parents('.background-education-container').find('input[name=major]').val(profession);
	}else{
		obj.parents('.background-education-container').find('input[name=major]').val('');
	}
	obj.parents('.background-education-container').find('a[name=editsmajor]').show();
	var edittype=obj.parents('.background-education-container').find('input[name=edittype]').val();
	obj.parents('.background-education-container').find('.section-logo img').remove();
	if(schoollogo!=''){
	    obj.parents('.background-education-container').find('.section-logo').append("<img class='lazy' src='"+schoollogo+"' alt='"+schoolname+"' width='50' height='50'>");
	}
	//获取背景教育的第一个div层
	var $firstDiv=$('#recommended-sections').find('.background-education-container').first();
	if($firstDiv.find('.header').length==0){
		$firstDiv.find('.education').before(createEducationBgHtml('教育背景'));
		$firstDiv.removeClass('education-container')
	};
	
	//赋值当前的id 为了之后修改
	obj.parents('.background-education-container').find('a[name=editschoolname],a[name=editschooltime],a[name=editsmajor],a[name=editsdegree]').attr('data-currentid',id)
	obj.parents('.background-education-container').find('.schoolname textarea[name=recommend],.schoolstartTime,.schoolendTime,.topDegree select[name=degree],.profession textarea[name=major],.zm-command').hide();
}
//工作经验界面赋值
function updateMyWorkData(companyname,onjobstartTime,onjobendTime,position,description,obj,id){
	if(companyname==''&&onjobstartTime==''&&onjobendTime==''&&position==''&&description==''){
		hiddenWorkExperience();
	}
	if(companyname!=''){
		obj.parents('.background-workexperience-container').find('.companyname').text(companyname);
		obj.parents('.background-workexperience-container').find('input[name=companyname]').val(companyname);
	}else{
		obj.parents('.background-workexperience-container').find('input[name=companyname]').val('');
	}
	obj.parents('.background-workexperience-container').find('a[name=editscompanyname]').show();
	if(onjobstartTime!=''){
		var onjobstartTime=obj.parents('.background-workexperience-container').find('.onjobstartTime').val();
		obj.parents('.background-workexperience-container').find('input[name=onjobstartTime]').val(onjobstartTime);
		onjobstartTime=new Date(Date.parse(onjobstartTime.replace(/-/g, '/')));
		var onjobendTime='';
		if(!obj.parents('.background-workexperience-container').find('.onjobendTime').is(':visible')){
			onjobendTime='至今';
		}else{
			onjobendTime=obj.parents('.background-workexperience-container').find('.onjobendTime').val();
		}
		obj.parents('.background-workexperience-container').find('input[name=onjobendTime]').val(onjobendTime);
		if(onjobendTime!='至今')
		onjobendTime=new Date(Date.parse(onjobendTime.replace(/-/g, '/')));
		//计算两个日期之间相差多少
		var workyears=better_time(onjobstartTime,onjobendTime);
		onjobstartTime=onjobstartTime.getFullYear()+'年'+fixZero(onjobstartTime.getMonth()+1,2)+'月'+onjobstartTime.getDate()+'日';
		if(onjobendTime!='至今')
		onjobendTime=onjobendTime.getFullYear()+'年'+fixZero(onjobendTime.getMonth()+1,2)+'月'+onjobendTime.getDate()+'日';
		
		obj.parents('.background-workexperience-container').find('.onjob .onjobtime').html("在职时间:&nbsp;&nbsp;"+onjobstartTime+'&nbsp; – &nbsp;'+onjobendTime+"&nbsp;&nbsp;"+"("+workyears+")");
		
		obj.parents('.background-workexperience-container').find('a[name=editsonjobtime]').show();
	}
	if(position!=''){
		obj.parents('.background-workexperience-container').find('.position').text(position);
		obj.parents('.background-workexperience-container').find('input[name=jobtitle]').val(position);
	}else{
		obj.parents('.background-workexperience-container').find('input[name=jobtitle]').val('');
	}
	obj.parents('.background-workexperience-container').find('a[name=editsposition]').show();
	if(description!=''){
		obj.parents('.background-workexperience-container').find('.workcontent').html(description+"<a href='javascript:;' class='zu-edit-button' name='editsdescription' data-currentid='"+id+"' style='display:none'><i class='zu-edit-button-icon'></i>修改</a>");
		obj.parents('.background-workexperience-container').find('input[name=description]').val(description);
	}else{
		obj.parents('.background-workexperience-container').find('input[name=description]').val('');
	}
	obj.parents('.background-workexperience-container').find('a[name=editsdescription]').show();
	var edittype=obj.parents('.background-workexperience-container').find('input[name=edittype]').val();

	//获取工作经验的第一个div层
	var $firstDiv=$('#recommended-sections').find('.background-workexperience-container').first();
	if($firstDiv.find('.header').length==0){
		$firstDiv.find('.workposition').before(createEducationBgHtml('工作经历'));
		$firstDiv.removeClass('workexperience-container')
	};
	//赋值当前的id 为了之后修改
	obj.parents('.background-workexperience-container').find('a[name=editscompanyname],a[name=editsonjobtime],a[name=editsposition]').attr('data-currentid',id)
	obj.parents('.background-workexperience-container').find('.companyname textarea[name=recommend],.topDegree select[name=degree],.onjobstartTime,.onjobendTime,.position textarea[name=position],.workcontent textarea[name=description],.zm-command,.still-here,.isCurrent').hide();
}
//取消编辑城市文本框
function hiddenCiteArea(){
	$('.province,.city,a[name=editcity]').show();
	$('.areaInfo').remove();
}
//更改城市
function updateCityOrProvince(city,province){
	$.ajax({
		type:'POST',
	    url:"/myHome/updUserInfo",
	    data:{city:city,province:province},
	    dataType:"json",
	    success:function(data){
	    	if(data.returnStatus=='000'){//返回成功
	    		
	    	}
	    	else{
	    		
	    	}
	    }
	})
}
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
//隐藏用户名修改的文本框
function hiddenUserNameEdit(){
	$('#titlename  .field-text').show().next().remove();
}
//隐藏对应的编辑文本框
function hiddenEditArea(column){
	$('.'+column).show().next().show();
	
	$('a[name=cancle'+column+']').parents('.zm-editable-editor-wrap').remove();
}



 
//加载用户信息
function intoUserInfo(){
	$('.uhead,.zm-item-link-avatar,.author-link').pinwheel();
}

//创建技能(根据输入的条件查询  有就直接筛选  没有就创建)
function  getSkillsByCondition(obj){
	var oldval,newval;
	oldval=$('input[name=currentskillval]').val();
	newval=$(obj).val();
	if(newval !== null &&newval !== undefined&&$.trim(newval).length!=0&&$.trim(oldval).length!=$.trim(newval).length){
		findSkills(newval,$(obj));
	}else if($.trim(newval).length==0){
		   $(obj).parent().find('div:last-child').remove();
		   $('input[name=currentskillval]').val('');
           $('#skill0').height($('#skillinputtags').height()+140+'px');
	}
	
}
//查找技能
function findSkills(conds,obj){
	$.ajax({
		type:'POST',
		url:"/skills/findSkill/ "+conds,
		dataType:"JSON",
     	async:false,
     	success:function(data){
     		if(data.returnStatus=='000'){
     			$('input[name=currentskillval]').val(conds);
     			obj.chosen(data,obj,'skill',conds);
     		}else{
     			
     		}
     	}		
	})
}
//保存技能
function SaveSkills(id,obj){
	var skills='';
	var skillIds='';
	$('#skillinputtags a').each(function(){
		skills+=$(this).attr('id')+":"+$(this).data('name')+",";
		//技能ids
		skillIds+= $(this).attr('id')+",";
	})
	skills=skills.substring(0,skills.length-1);
	skillIds=skillIds.substring(0,skillIds.length-1);
	if(skills.length==0){
		$('.background-skills-container,button[name=addskill]').remove();
		if(obj.attr('data-skillid')=='')
		return false;
	}
	$.ajax({
		type:'POST',
		url:"/myHome/updSkill",
		data:{skillitem:skills,id:id,skillIds:skillIds},
		dataType:"JSON",
     	async:false,
     	success:function(data){
     		if(data.returnStatus=='000'){  
     			cancelSkills(skills,$.trim(id)==''?data.obj.id:id,obj);	
     		}else{
     			
     		}
     	}
	})
}
function cancelSkills(skills,id,obj){
	var tb='[';
 	for(var i=0;i<skills.split(',').length;i++){
 		tb+='{"id":'+skills.split(",")[i].split(":")[0]+',"name":"'+skills.split(",")[i].split(":")[1]+'"},';
 	}
 	tb=tb.substring(0,tb.length-1);
 	tb+=']';
		var data={
 			skillid:id,
 			skillitem:jQuery.parseJSON(tb),
 			skillitems:skills
 	}
 	$('.homepageTemplate').setTemplateURL(projectName+'saveSkill.html');
 	$('.homepageTemplate').processTemplate(data);
 	obj.parents('.background-skills-container').after($('.homepageTemplate').html());
    	$('.homepageTemplate').empty();
     obj.parents('.background-skills-container').remove();
}
//判断两个日期相差多少年月
function better_time(starttime,endtime){
	if(endtime=='至今')
		endtime=new Date();
	var month='';
	var day=endtime.getDate()-starttime.getDate();
	if(endtime.getFullYear()==starttime.getFullYear()){
		 month=endtime.getMonth()-starttime.getMonth();
		if(parseInt(day)>=15||parseInt(month)==0){
			month=month+1;
		}
		return month+'个月';
	}else{
		var year=endtime.getFullYear()-starttime.getFullYear()-1;
		if(endtime.getMonth()!=starttime.getMonth()&&year!=0){
			month=endtime.getMonth()+12-starttime.getMonth();
		}else{
			year=year+1;
		}
		if(parseInt(day)>=15){
			month=month+1;
		}
		if(parseInt(month)>12){
			year=year+1;
			month=month-12;
		}
		return String(parseInt(year)==0?'':(year+'年'))+String(month==''?'':(month+'个月'));
	}
	
}
