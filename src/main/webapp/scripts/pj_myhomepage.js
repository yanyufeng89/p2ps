var randomid=1;
var userInfo;
$(function(){

	 //个人中心  点击联系方式
    $(".show-more-info").on('click',function(){
    	//只有在看别人的主页的时候 才会有收起
    	var ispackup=$(this).attr('data-ispackup');
    	$("#contact-info-section").toggle();
    	if($(this).find('.t-down').length==0){
    		$(this).find('.t-up').removeClass('t-up').addClass('t-down');
    	}else{
    		$(this).find('.t-down').removeClass('t-down').addClass('t-up');
    	}
    	if(ispackup=='1'){
    		if($('.section-container').is(':hidden')){
        	    $('.section-container').show();
        	}else{
        		$('.section-container').hide();
            }
    		 if($(this).find('.contact').text()=='个人简介'){
    			 $(this).find('.contact').html('<b class="t-up"></b>收起');
    		     $('.zm-profile-section-wrap,#zh-profile-follows-list').hide();
    		     $('.section-container').show();
    		 }
    		 else{
    			 $(this).find('.contact').html('<b class="t-down"></b>个人简介')
    			 $('.zm-profile-section-wrap').show();
    		     $('.section-container,#zh-profile-follows-list').hide();
    		 }
    	}
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
	var li =$("#recommended-sections ul li:gt(2)");
    li.hide();
    $("#recommended-sections ul").css('height','176px');
    $("#recommended-sections .view-bar").click(function(){
    	if(li.is(':visible')){
			 li.slideUp('fast');
			 $("#recommended-sections ul").css('height','176px');
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
    	$('#titlename .full-name').text(username);
    	//同时更改数据库中的字段值
    	updateColumn(username,"username=");
    	hiddenUserNameEdit();
    });
    //公司名字修改
    $('a[name=editcompname]').live('click',function(){
    	var compname=$(this).prev().text();
    	
    	var data={
				title:$.trim(compname),
				canclename:'canclecompname',
				savename:'savecompname'
		}
    	$('.homepageTemplate').setTemplateURL(projectName+'myHomePageTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $(this).after($('.homepageTemplate').html());
  	    $(this).hide().prev().hide();
  	    $('.homepageTemplate').empty();
  	    $('.pj-editable-editor-outer').css('margin-bottom','6px');
    });
    //修改公司名点击取消
    $('a[name=canclecompname]').live('click',function(){
    	hiddenEditArea('compname');
    });
    //修改公司名点击保存
    $('a[name=savecompname]').live('click',function(){
    	var compname=$('#nameedit').val();
    	$('.compname').text(compname);
    	if($.trim(compname).length==0){
    		$('.editcompname').hide();
    	}
    	//同时更改数据库中的字段值
    	updateColumn(compname,'compname=');
    	hiddenEditArea('compname');
    });
    //关注人
    $('.operation .zm-rich-follow-btn').live('click',function(){
    	topicFollow($(this),0)
    })
    //点击修改职位
    $('a[name=editposition]').live('click',function(){
    	var position=$(this).prev().text();
    	if($.trim(position).length==0){
    		$('.editposition').hide();
    	}
    	var data={
				title:$.trim(position),
				canclename:'cancleposition',
				savename:'saveposition'
		}
    	$('.homepageTemplate').setTemplateURL(projectName+'myHomePageTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $(this).after($('.homepageTemplate').html());
  	    $(this).hide().prev().hide();
  	    $('.homepageTemplate').empty();
  	    $('.pj-editable-editor-outer').css('margin-bottom','6px');
    });
    //修改职位点击取消
    $('a[name=cancleposition]').live('click',function(){
    	hiddenEditArea('position');
    });
    //修改职位点击保存
    $('a[name=saveposition]').live('click',function(){
    	var position=$('#nameedit').val();
    	if($.trim(position).length==0){
    		$('.editposition').hide();
    	}
    	$('.position').text(position);
    	//同时更改数据库中的字段值
    	updateColumn(position,'position=');
    	hiddenEditArea('position');
    });
    //修改行业
    $('a[name=editindustry]').live('click',function(){
    	var industry=$(this).prev().text();
    	var data={
				title:$.trim(industry),
				canclename:'cancleindustry',
				savename:'saveindustry'
		}
    	$('.homepageTemplate').setTemplateURL(projectName+'myHomePageTemplate.html');
    	$('.homepageTemplate').processTemplate(data);
  	    $(this).after($('.homepageTemplate').html());
  	    $(this).hide().prev().hide();
  	    $('.homepageTemplate').empty();
  	    $('.pj-editable-editor-outer').css('margin-bottom','6px');
    })
    //修改行业点击取消
    $('a[name=cancleindustry]').live('click',function(){
    	hiddenEditArea('industry');
    });
    //修改行业点击保存
    $('a[name=saveindustry]').live('click',function(){
    	var industry=$('#nameedit').val();
    	if($.trim(industry).length==0){
    		$('.editindustry').hide();
    	}
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
  	    $(this).hide()
  	    $('.province,.city').hide();
  	    $('.homepageTemplate').empty();
  	    
    	var opt0 = [province,city];
    	_init_area(opt0);
    })
    //取消编辑城市文本框
    $('a[name=canclecity]').live('click',function(){
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
    	if((birthdayday!=''&&birthdaymonth=='')||(birthdayday==''&&birthdaymonth!='')){
    		$('.birthday b').show();
    		$('.birthday .item-msg-content').show();
    		return false;
    	}
    	var marriage=$.trim($('input[name=ismarry]').val());
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
    		window.location.href='#self-info-detail';
    		$('.background-selfinfo-container').css('border-color','#0867c5');
    	}
    	
       //$(this).addClass('disabled');
    })
    //修改基本信息的生日
    $('a[name=editbirthday],a[name=editintroinfo],a[name=editmarriage]').live('click',function(){
    	editMyIntroInfo($(this));
    })
    //添加教育背景
    $('.recommended-section-education .recommended-section-add,#addcontinuous,#control_gen_4').live('click',function(){
    	//1表示继续添加  
    	var addtype=$(this).attr('data-addtype'); 
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
        			anchor:anchorlen
        	}
        	$('.homepageTemplate').processTemplate(data);
        	if($lastDiv.last().length==0){
        		$('#recommended-sections').append($('.homepageTemplate').html());
        	}else{
        		$lastDiv.last().after($('.homepageTemplate').html());
        	}
        	$('.homepageTemplate').empty();
        	window.location.href='#education'+anchorlen;
        	$('#schoolstartTime'+randomid).dateSelect();
        	$('#schoolendTime'+randomid).dateSelect();
    	}else{
    			window.location.href='#education0';
    			$('#education0').css('border-color','#0867c5');
    	}
    	
    	
    });
    //点击添加工作经历
    $('.recommended-section-work .recommended-section-add,#addcontinuouswork,#control_gen_3').live('click',function(){
    	var addtype=$(this).attr('data-addtype');
    	if($('.background-workexperience-container').length==0||addtype==0){
    		$('.homepageTemplate').setTemplateURL(projectName+'myWorkExperienceTemplate.html');
        	var isContinueAdd='';
        	if(addtype=='1'){
        		$(this).addClass('disabled');
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
        	}else{
        		$lastDiv.last().after($('.homepageTemplate').html());
        	}
        	$('.homepageTemplate').empty();
        	window.location.href='#work'+anchorlen;
        	$('#jobstartTime'+randomid).dateSelect();
        	$('#jobendTime'+randomid).dateSelect();
    	}else{
    			window.location.href='#work0';
    			$('#work0').css('border-color','#0867c5');
    	}
    	
    })
    
    //工作经验点击保存
    $('a[name=myworksave]').live('click',function(){
    	//公司名字
    	var companyname=$.trim($(this).parents('.background-workexperience-container').find('.companyname .content').val());
    	var onjobstartTime1=$.trim($(this).parents('.background-workexperience-container').find('.onjobstartTime').val());
    	var onjobendTime1=$.trim($(this).parents('.background-workexperience-container').find('.onjobendTime').val());
    	//在校开始时间
    	var onjobstartTime=$.trim($(this).parents('.background-workexperience-container').find('.onjobstartTime').val()+' 00:00:00');
    	//在校结束时间
    	var onjobendTime=$.trim($(this).parents('.background-workexperience-container').find('.onjobendTime').val()+' 00:00:00');
    	//工作描述
    	var description=$(this).parents('.background-workexperience-container').find('.workcontent .content').val();
    	
    	if((onjobstartTime!=''&&onjobendTime=='')||(onjobstartTime==''&&onjobendTime!='')){
    		$(this).parents('.background-workexperience-container').find('.onjobtime b').show();
    		$(this).parents('.background-workexperience-container').find('.onjobtime .item-msg-content').show();
    		return false;
    	}
	    var start=new Date(onjobstartTime1.replace("-", "/").replace("-", "/"));  
	    var end=new Date(onjobstartTime1.replace("-", "/").replace("-", "/"));  
	    if(end<start){
	    	$(this).parents('.background-workexperience-container').find('.onjobtime b').show();
	    	$(this).parents('.background-workexperience-container').find('.onjobtime .item-msg-content').html('开始时间应小于结束时间').show();
	        return false;  
	    }  
    	//工作职位
    	var position=$.trim($(this).parents('.background-workexperience-container').find('.position .content').val());
    	if(companyname!=''){
    		if(onjobendTime1==''){
    			$(this).parents('.background-workexperience-container').find('.onjobtime b').show();
        		$(this).parents('.background-workexperience-container').find('.onjobtime .item-msg-content').show();
        		return false;
    		}
    	}
    	if(companyname==''){
    		$(this).parents('.background-workexperience-container').find('.companyname b').show();
    		$(this).parents('.background-workexperience-container').find('.companyname .item-msg-content').show();
    		return false;
    	}else{
        	saveWorkExperience(companyname,onjobstartTime,onjobendTime,position,description,$(this))
    	}
    })
    
    //教育背景点击保存
    $('a[name=myeducationsave]').live('click',function(){
    	//学校名字
    	var schoolname=$.trim($(this).parents('.background-education-container').find('.schoolname .content').val());
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
    	//所学专业
    	var profession=$.trim($(this).parents('.background-education-container').find('.profession .content').val());
    	if(schoolname!=''){
    		if(schoolendTime1==''){
    			$(this).parents('.background-education-container').find('.schooltime b').show();
        		$(this).parents('.background-education-container').find('.schooltime .item-msg-content').show();
        		return false;
    		}
    	}
    	//学校名称必填
    	if(schoolname==''){
    		$(this).parents('.background-education-container').find('.schoolname b').show();
    		$(this).parents('.background-education-container').find('.schoolname .item-msg-content').show();
    		return false;
    	}else{
        	saveEducationBg(schoolname,schoolstartTime,schoolendTime,profession,$(this))
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
    $('a[name=editschooltime],a[name=editschoolname],a[name=editsmajor]').live('click',function(){
    	var schoolname=$.trim($(this).parents('.background-education-container').find('input[name=school]').val());
    	var schoolstartTime=$.trim($(this).parents('.background-education-container').find('input[name=starttime]').val());
    	if(schoolstartTime.indexOf(' ')!=-1){
    		schoolstartTime=schoolstartTime.substring(0,schoolstartTime.indexOf(' '));
    	}
    	var schoolendTime=$.trim($(this).parents('.background-education-container').find('input[name=endtime]').val());
    	if(schoolendTime.indexOf(' ')!=-1){
    		schoolendTime=schoolendTime.substring(0,schoolendTime.indexOf(' '));
    	}
    	
    	var profession=$.trim($(this).parents('.background-education-container').find('input[name=major]').val());
    	var currentid=$(this).attr('data-currentid');
    	editEducationData(schoolname,schoolstartTime,schoolendTime,profession,currentid,$(this));
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
       var email=$('.emailinfo textarea[name=email]').val();
  	   var phone=$('.phoneinfo textarea[name=phone]').val();
  	   saveMyContanct(email,phone);
    })
    //取消我的联系方式
    $('a[name=mycontanctcancel]').live('click',function(){
    	 var email=$(this).attr('data-email');
    	 var phone=$(this).attr('data-phone');
    	 updateMyContanctData(email,phone)
    })
    
     //关注与取消关注人
    $('.detail-list .zg-btn,.profile-actions .zg-btn,#zh-profile-follows-list .zg-btn').live('click',function(){
    	addFollows($(this));
    })
    
    //回到顶部
    $("#backcentertop,#backaboutmetop").mousemove(function(){
     	$("#backcentertop,#backaboutmetop").css("background-position-x", "-28px");
     }).mouseleave(function(){
     	$("#backcentertop,#backaboutmetop").css("background-position-x", "0");
    })
         
     /*首页当界面下拉到一定位置出现向上的箭头 start*/
     $(window).scroll(function(){  
         if ($(window).scrollTop()>100){  
             $("#backcentertop,#backaboutmetop").fadeIn("fast");  
         }  
         else  
         {  
             $("#backcentertop,#backaboutmetop").fadeOut("fast");  
         }  
     });
    /*首页当界面下拉到一定位置出现向上的箭头 end*/
    
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
    $('.pjitem').live('click',function(){
    	loadShareData($(this));
    });
    //他的关注
    $('#otheratten').live('click',function(){
    	otherAtten($(this));
    	
    })
    //他的粉丝
    $('#otherfans').live('click',function(){
    	otherFans($(this));
    	
    })
})
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
		url:projectName+"myCenter/getMyAttenMan",
		data:{userid:userid},
		dataType:"json",
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
			 $('#zh-profile-follows-list .zh-general-list').empty().append($('.pagetemplate').html());
			 $('.pagetemplate').empty();
			 $('#zh-profile-follows-list .zm-profile-fansandatten-name').html('他关注的人');
			 if(data.user.userid==(userInfo==undefined?'':userInfo.userid)){
				 $('#zh-profile-follows-list .zm-profile-fansandatten-name').html('我关注的人'); 
			 }else{
				 $('#zh-profile-follows-list .zm-profile-fansandatten-name').html('他关注的人');
			 }
			 //判断当前是否有加载更多
			 if(data.attenManPage.last>1){
				 var mhtml='<a href="javascript:;" data-pageno="1"  data-isfans="0" data-userid='+data.user.userid+' data-sumpage='+data.attenManPage.last+' class="zg-btn-white zg-r3px zu-button-more pjatten-load-more">更多</a>'  
			 }
			 $('#zh-profile-follows-list').append(mhtml);
			 
			 $('#zh-profile-follows-list').show();
			 //加载更多去掉
			 $('#pjfans-load-more').remove();
			 //显示当前层 隐藏其他层
			 //隐藏个人信息
			 $('.section-container,#contact-info-section,.zm-profile-section-wrap').hide();
			 $('.contact').html('<b class="t-down"></b>个人简介');
			 intoUserInfo();
			
		}
	})
}

//他的粉丝
function otherFans(obj){
	var userid=obj.attr('data-userid');
	$.ajax({
		type:"POST",
		url:projectName+"myCenter/getMyFans",
		data:{userid:userid},
		dataType:"json",
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
			 $('#zh-profile-follows-list .zh-general-list').empty().append($('.pagetemplate').html());
			 $('.pagetemplate').empty();
			 if(data.user.userid==(userInfo==undefined?'':userInfo.userid)){
				 $('#zh-profile-follows-list .zm-profile-fansandatten-name').html('我的粉丝'); 
			 }else{
				 $('#zh-profile-follows-list .zm-profile-fansandatten-name').html('他的粉丝');
			 }
			 $('#zh-profile-follows-list').show();
			 //判断当前是否有加载更多
			 if(data.myFansPage.last>1){
				 var mhtml='<a href="javascript:;" data-pageno="1" data-isfans="1" data-userid='+data.user.userid+' data-sumpage='+data.myFansPage.last+' class="zg-btn-white zg-r3px zu-button-more pjfans-load-more">更多</a>'  
			 }
			 $('#zh-profile-follows-list').append(mhtml);
			 //加载更多去掉
			 $('#pjatten-load-more').remove();
			 //显示当前层 隐藏其他层
			 //隐藏个人信息
			 $('.section-container,#contact-info-section,.zm-profile-section-wrap').hide();
			 $('.contact').html('<b class="t-down"></b>个人简介');
			 intoUserInfo();
			 
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
		url:projectName+"myHome/getOneShares",
		data:{userid:userid,tableName:tablename,tableColumn:tablecolumn,tableColumn2:tablecolumn2},
		dataType:"json",
        success:function(data){
        	   var name=convertCh(tablename);
        	   $('#zh-profile-answers-inner-list ul').empty().append(loadMoreHtml(data.shares.list,name,tablename));
        	   if($('.pj-load-more').length>0)
        	   $('.pj-load-more').remove();
        	   if(Number(data.shares.last)>1){
        		   var htm='<a href="javascript:void(0)"  data-pageno="1" data-tablecolumn2='+tablecolumn2+' data-tablename='+tablename+'  data-tablecolumn='+tablecolumn+' data-userid='+userid+' data-sumpage='+data.shares.last+' class="zg-btn-white zg-r3px zu-button-more pj-load-more">更多</a>'
        		   $('#zh-profile-answers-inner-list').append(htm);
        	   }
        }
	})
	//显示当前层  隐藏其他层
	$('#zh-profile-follows-list,.section-container,#contact-info-section').hide();
	$('.zm-profile-section-wrap').show();
	$('.contact').html('<b class="t-down"></b>个人简介')
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
		url:projectName+"myHome/getOneShares",
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
			url:projectName+"myCenter/getMyFans",
			data:{userid:userid,pageNo:Number(pageNo)+1},
			dataType:"json",
			ansyc:false,
	        success:function(data){
	        	$.each(data.myFansPage.list,function(index,item){
	    			if(item.fansIds!=undefined){
	    				if(item.fansIds.indexOf(',')!=-1){
	                        $.each(item.fansIds.split(','),function(index1,item1){
	                        	if(item1==(userInfo==undefined?'':serInfo.userid)){
	                        		item.fansIds=1;
	                        	}else{
	                        		item.fansIds=0;
	                        	}
	                        })
	    				}else{
	    					if(item.fansIds==(userInfo==undefined?'':serInfo.userid)){
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
	        }
		})
	}else{
		$.ajax({
			type:"POST",
			url:projectName+"myCenter/getMyAttenMan",
			data:{userid:userid,pageNo:Number(pageNo)+1},
			dataType:"json",
			ansyc:false,
	        success:function(data){
	        	$.each(data.attenManPage.list,function(index,item){
					item.fansIds=1;
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
	        }
		})
	}
	intoUserInfo();
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
	$.each(shares,function(index,item){
		shtml+='<li>'
	    shtml+='  <span class="allnewscontent">分享了'+name
	    shtml+='     <a href='+getLinkUrl(tablename,item.id)+' target="_blank">'+item.objName+'</a>'
	    shtml+=' </span>'	
	    shtml+=' <span class="smsdate zg-right">'+formatDate(item.createtime)+'</span>'
	    shtml+='</li>'	
	})
	return shtml;
}

//我的关注列表 
 function addFollows(obj){
	 var userid=obj.attr('data-userid');
	 //actionType 1关注，0取消关注
	 var actiontype=obj.attr('data-actionType');
	 $.ajax({
			type:"POST",
			url:projectName+"myCenter/addFollows",
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
		          			obj.empty().html('关注');
		          		} 
	        	  }
	        }
		})
   }
//保存我的联系方式
function saveMyContanct(email,phone){
	$.ajax({
		type:'POST',
	    url:projectName+"myHome/updUserInfo",
	    data:{email:email,mobile:phone},
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

	editWorkData(companyname,schoolstartTime,schoolendTime,jobtitle,description,currentid,obj);
}
//保存教育背景
function saveEducationBg(schoolname,schoolstartTime,schoolendTime,profession,obj){
	    var currentid=obj.parents('.background-education-container').find('input[name=currentid]').val();
		$.ajax({
			type:'POST',
		    url:projectName+"myHome/updEduInfo",
		    data:{school:schoolname,major:profession,starttime:schoolstartTime,endtime:schoolendTime,id:currentid},
		    dataType:"json",
		    success:function(data){
		    	if(data.returnStatus=='000'){//返回成功
		    		updateMyEducationData(schoolname,schoolstartTime,schoolendTime,profession,obj,data.obj)
		    	}
		    	else{
		    		
		    	}
		    }
	})
}
//保存工作经验
function saveWorkExperience(companyname,onjobstartTime,onjobendTime,position,description,obj){
	var currentid=obj.parents('.background-workexperience-container').find('input[name=currentid]').val();
	$.ajax({
		type:'POST',
	    url:projectName+"myHome/updWorkExpInfo",
	    data:{company:companyname,jobtitle:position,starttime:onjobstartTime,endtime:onjobendTime,id:currentid,description:description},
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
		var starttime=obj.attr('data-starttime');
		var endtime=obj.attr('data-endtime');
		var currentid=obj.attr('data-currentid');
		updateMyEducationData(schoolname,starttime,endtime,major,obj,currentid);

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
function editEducationData(schoolname,schoolstartTime,schoolendTime,profession,currentid,obj){
	randomid++;
	var data={
			schoolname:schoolname,
			schoolstartTime:schoolstartTime,
			schoolendTime:schoolendTime,
			profession:profession,
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
			onjobendTime:onjobendTime,
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
	if(description!='')
		obj.parents('.background-workexperience-container').next().find('.workcontent textarea[name=description]').val(description);
	if(jobtitle!='')
		obj.parents('.background-workexperience-container').next().find('.position textarea[name=position]').val(jobtitle);
	$('#jobstartTime'+randomid).dateSelect();
	$('#jobendTime'+randomid).dateSelect();
	obj.parents('.background-workexperience-container').remove();

}
//创建教育背景html
function createEducationBgHtml(text){
	var html='';
	html+='<div class="header">';
	html+='  <span>'+text+'</span>';
	if(text=='工作经历'){
		html+='  <a href="javascript:void(0)" id="addcontinuouswork" class="add-all ml20  add-all-able" data-addtype="0">';
	}else{
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
	    url:projectName+"myHome/updUserInfo",
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
		$('a[name=editbirthday]').show();
	}
	
	if(marriage!=''&&marriage!='2'){
		if(marriage=='1'){
			marriage='已婚'
		}else{
			marriage='未婚'
		}
		$('.birthday .marriage').html("婚姻状况:&nbsp;&nbsp;"+marriage);
		$('a[name=editmarriage]').show();
	}
	
	if(description!=''){
		$('.intro .introinfo').text(description);
		$('a[name=editintroinfo]').show();
	}

	$('#birthdaymonth,#birthdayday,#marriage,textarea[name=recommend],.background-selfinfo-container .zm-command').hide();
}
//教育背景界面赋值
function updateMyEducationData(schoolname,schoolstartTime,schoolendTime,profession,obj,id){
	if(schoolname==''&&schoolstartTime==''&&schoolendTime==''&&profession==''){
		hiddenEducationBg();
	}
	if(schoolname!=''){
		obj.parents('.background-education-container').find('.education .schoolname').text(schoolname);
		obj.parents('.background-education-container').find('input[name=school]').val(schoolname);
		obj.parents('.background-education-container').find('a[name=editschoolname]').show();
	}
	if(schoolstartTime!=''){
		var starttime=obj.parents('.background-education-container').find('.schoolstartTime').val();
		var endtime=obj.parents('.background-education-container').find('.schoolendTime').val()
		obj.parents('.background-education-container').find('.graduate .schooltime').html("在校时间:&nbsp;&nbsp;"+starttime+'--'+endtime);
		obj.parents('.background-education-container').find('input[name=starttime]').val(starttime);
		obj.parents('.background-education-container').find('input[name=endtime]').val(endtime);
		obj.parents('.background-education-container').find('a[name=editschooltime]').show();
	}
	if(profession!=''){
		obj.parents('.background-education-container').find('.major .profession').text(profession);
		obj.parents('.background-education-container').find('input[name=major]').val(profession);
		obj.parents('.background-education-container').find('a[name=editsmajor]').show();
	}
	var edittype=obj.parents('.background-education-container').find('input[name=edittype]').val();

	//获取背景教育的第一个div层
	var $firstDiv=$('#recommended-sections').find('.background-education-container').first();
	if($firstDiv.find('.header').length==0){
		$firstDiv.find('.education').before(createEducationBgHtml('教育背景'));
		$firstDiv.removeClass('education-container')
	};
	//赋值当前的id 为了之后修改
	obj.parents('.background-education-container').find('a[name=editschoolname],a[name=editschooltime],a[name=editsmajor]').attr('data-currentid',id)
	obj.parents('.background-education-container').find('.schoolname textarea[name=recommend],.schoolstartTime,.schoolendTime,.profession textarea[name=major],.zm-command').hide();
}
//工作经验界面赋值
function updateMyWorkData(companyname,onjobstartTime,onjobendTime,position,description,obj,id){
	if(companyname==''&&onjobstartTime==''&&onjobendTime==''&&position==''&&description==''){
		hiddenWorkExperience();
	}
	if(companyname!=''){
		obj.parents('.background-workexperience-container').find('.company .companyname').text(companyname);
		obj.parents('.background-workexperience-container').find('input[name=companyname]').val(companyname);
		obj.parents('.background-workexperience-container').find('a[name=editscompanyname]').show();
	}
	if(onjobstartTime!=''){
		var onjobstartTime=obj.parents('.background-workexperience-container').find('.onjobstartTime').val();
		var onjobendTime=obj.parents('.background-workexperience-container').find('.onjobendTime').val();
		obj.parents('.background-workexperience-container').find('.onjob .onjobtime').html("在职时间:&nbsp;&nbsp;"+onjobstartTime+'--'+onjobendTime);
		obj.parents('.background-workexperience-container').find('input[name=onjobstartTime]').val(onjobstartTime);
		obj.parents('.background-workexperience-container').find('input[name=onjobendTime]').val(onjobendTime);
		obj.parents('.background-workexperience-container').find('a[name=editsonjobtime]').show();
	}
	if(position!=''){
		obj.parents('.background-workexperience-container').find('.position').text(position);
		obj.parents('.background-workexperience-container').find('input[name=jobtitle]').val(position);
		obj.parents('.background-workexperience-container').find('a[name=editsposition]').show();
	}
	if(description!=''){
		obj.parents('.background-workexperience-container').find('.workcontent').text(description);
		obj.parents('.background-workexperience-container').find('input[name=description]').val(description);
		obj.parents('.background-workexperience-container').find('a[name=editsdescription]').show();
	}
	var edittype=obj.parents('.background-workexperience-container').find('input[name=edittype]').val();

	//获取工作经验的第一个div层
	var $firstDiv=$('#recommended-sections').find('.background-workexperience-container').first();
	if($firstDiv.find('.header').length==0){
		$firstDiv.find('.company').before(createEducationBgHtml('工作经历'));
		$firstDiv.removeClass('workexperience-container')
	};
	//赋值当前的id 为了之后修改
	obj.parents('.background-workexperience-container').find('a[name=editscompanyname],a[name=editsonjobtime],a[name=editsposition]').attr('data-currentid',id)
	obj.parents('.background-workexperience-container').find('.companyname textarea[name=recommend],.onjobstartTime,.onjobendTime,.position textarea[name=position],.workcontent textarea[name=description],.zm-command').hide();
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
	    url:projectName+"myHome/updUserInfo",
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
	    url:projectName+"myHome/updUserInfo",
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

//个人中心更换头像
function previewImage(file){
		 var MAXWIDTH  = 260; 
         var MAXHEIGHT = 180;
         var imgpath=$('input[type=file]').val();
         var div = document.getElementById('preview');
         var filetype=['jpg','png','jpeg','pns'];
         var imgtype=file.files[0].name.substring(file.files[0].name.length-3,file.files[0].name.length)
         if($.inArray(imgtype,filetype)!=-1){
        	 updUserInfo();
        	 if (file.files && file.files[0])
             {
                 div.innerHTML =
                	 //'<form method="POST"  id="previewImage" enctype="multipart/form-data"><img id=imghead> <span class="ProfileAvatarEditor-tip">修改头像</span> <input id="previewImage" class="file-3" type="file" size="30" onchange="previewImage(this)"></form>';
                '<img id=imghead width="120" height="120"><span class="ProfileAvatarEditor-tip">更换头像</span><form method="POST"  id="previewImage" enctype="multipart/form-data"><input name="headIconFile" class="file-3" type="file" accept="image/*" size="30" onchange="previewImage(this)" /> </form>';
                 
                 
                 var img = document.getElementById('imghead');
                 img.onload = function(){
                   var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                   img.width  =  rect.width;
                   img.height =  rect.height;
                   /*img.style.marginTop = rect.top+'px';*/
                 }

                 var reader = new FileReader();
                 reader.onload = function(evt){img.src = evt.target.result;}
                 reader.readAsDataURL(file.files[0]);
             }
             else //兼容IE
             {
               var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
               file.select();
               var src = document.selection.createRange().text;
               div.innerHTML = '<img id=imghead>';
               var img = document.getElementById('imghead');
               img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
               var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
               status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
               div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
             }
         }
         
   }
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
 }


//更新用户表tbl_user
function updUserInfo() {  
    var formData = new FormData($("#previewImage")[0]);  
    $.ajax({  
    	 url:projectName+"myHome/updUserInfo",
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (data) {  
             /*alert(data);  */
         },  
         error: function (data) {  
             alert(data);  
         }  
    });  
}  
//加载用户信息
function intoUserInfo(){
	$('.uhead,.zm-item-link-avatar,.author-link').pinwheel();
	
}