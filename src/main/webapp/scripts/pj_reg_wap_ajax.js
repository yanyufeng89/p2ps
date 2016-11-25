var myreg = /^([a-zA-Z0-9\-]+[_|\_|\.]?)*[a-zA-Z0-9\-]+@([a-zA-Z0-9\-]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var reg= /^[1][34578]\d{9}$/; //验证手机号码  
var ispass=true;
$(function(){
	var qurl=window.location.href;
	var requesType = qurl.getQuery("isregister");
	if(requesType==1){
		$('#pj-navs-slider').attr('data-active-index','1');
		$('#pj-navs-slider a').last().addClass('active').siblings().removeClass('active');
		$('.pj-view-signup').show();
		$('.pj-view-signin').hide();
	}
	//注册是0  登录是1 (data-active-index)
	$('#pj-navs-slider a').live('click',function(){
		var index=$(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$(this).parent().attr('data-active-index',index);
		$('.pj-view').eq(index).show().siblings(':not(.index-tab-navs)').hide();
	});
	//输入文本框获取焦点
	$('#username,#email,#password1,#moblie_code,#email1').live('focus',function(){
		$(this).next(':not(#vcode_imgs1)').remove();
	});
	$('#v-code').live('focus',function(){
		$(this).next(':not(#pj-send-code)').remove();
	});
})
//登录
function login_check_user(){
	ispass=true;
	var login_email=$('input[name=username]').first().val();//手机号或者邮箱
	var login_passwd=$('input[name=passwd]').first().val();//密码
	reg_checkAjax("email1",login_email);
	reg_checkAjax("password1",login_passwd);
	if(ispass){
		$("#usertype1").submit();
	}
} 
//找回密码
function wap_forgetpw(){
	var aucode = $.trim($("#moblie_code").val());
	var usertel =  $.trim($("#email").val());
	if(usertel==""){
		$('#email').after('<label class="error is-visible" >请填写你绑定的邮箱或手机</label>');
		return false;
	}
	if(aucode==""){
		$('#moblie_code').after('<label class="error is-visible" style="right:9rem;">验证码不能为空</label>');
		return false;
	}
	if(!myreg.test(usertel) && !reg.test(usertel)){
		$('#email').after('<label class="error is-visible">邮箱或手机格式错误</label>');
		return false;
	}
	var type='';
	if(myreg.test(usertel)){
		type=3; 
	}
	else{
		type=2;
	}
	var checkCode="";
	$("#vcode_imgs1 div span").each(function(){
		checkCode+=$(this).text();
	});
	if($.trim(aucode.toLowerCase())!= $.trim(checkCode.toLowerCase())){
	  $('#moblie_code').after('<label class="error is-visible" style="right:9rem;">请输入正确的验证码</label>');
	  return false;
	}
	//判断手机或邮箱是否存在
	if(!wIsexistsmoble(usertel,type)){
		return false;  
	}
	//进入下一步
	$('#wap-step1').hide();
	$('#wap-step2').show();
	//发送短信或者邮箱验证码
	sendmsg(usertel);
}
//判断手机或邮箱是否存在
function wIsexistsmoble(usertel,type){
	ispass=true;
	$.ajax({
		url:"/user/check/"+usertel+"/"+type,
		type : "POST", 
		data :{id:usertel},
		dataType:"json",
		async:false,
		contentType: "application/text",
        success:function(data){
           if(data==0){	
        	      $('#email').after('<label class="error is-visible">手机号码或邮箱不存在</label>');
        	      ispass=false;
				}else{
					
				} 
         }
       })
       return ispass;
}
//找回密码---校验手机或邮箱验证码
function wap_checksendcode(){
	ispass=true;
	var usertel=$.trim($("#email").val());
	var verification_code=$('input[name=verification_code]').val();
	var code=$("#v-code").val();
	 if($.trim(verification_code)==''){
		 if(myreg.test(usertel)){
			 $('#v-code').after('<label class="error is-visible" style="right:17rem;">请输入邮箱验证码</label>');
		 }else{
			 $('#v-code').after('<label class="error is-visible" style="right:17rem;">请输入手机验证码</label>');	
		 }
		 ispass=false;
	 }
	 $.ajax({
			type:"POST",
	      	url:"/mobilesms/checkSms",
	      	data:{smsId:verification_code,validateCode:code},
	    	dataType:"json",
	    	async:false,
	    	success:function(data){
	    		if(Number(data)==0){
	    			 if(myreg.test(usertel)){
	    				 $('#v-code').after('<label class="error is-visible" style="right:17rem;">邮箱验证码不正确</label>');
	    			 }else{
	    				 $('#v-code').after('<label class="error is-visible" style="right:17rem;">手机验证码不正确</label>');	
	    			 }
	    			 ispass = false;
	    		}else{
	    			
	    		}
	    	}
		})
		if(ispass){
			$('#wap-step2').hide();
			$('#wap-step3').show();
		}
}
//注册
function register_check_user(){
	ispass=true;
	var authcode=$("input[name=vcode]").val();//验证码
	var fullname=$.trim($("input[name=username]").last().val());//昵称
	var email=$('input[name=email]').val();//手机号或者邮箱
	var passwd=$('input[name=passwd]').last().val();//密码
	reg_checkAjax("username",fullname);
	reg_checkAjax("email",email);
	reg_checkAjax("password1",passwd);
	if(authcode==""){
		$('#moblie_code').after('<label class="error is-visible" style="right:10.6rem;">请输入验证码</label>');
		return false;
	}
	var checkCode="";
	$("#vcode_imgs1 div span").each(function(){
		checkCode+=$(this).text();
	});
	if($.trim(authcode.toLowerCase())!= $.trim(checkCode.toLowerCase())){
	   $('#moblie_code').after('<label class="error is-visible" style="right:10.6rem;">请输入正确的验证码</label>');
	   return false;
	}
	if(ispass){
		//发送手机或邮箱验证码
		sendmsg(email);
		$('#register-layer').hide();
		$('#validation-layer').show();
		$('#vemail').val(email);
	}
}
//修改密码
function wap_editpw(){
	 var email=$('input[name=email]').val();//手机号或者邮箱
	 var password=$.trim($("input[name=password]").val());
	 var passwordconfirm=$.trim($("input[name=passwordconfirm]").val());
	 if(password!=passwordconfirm){
			$('input[name=passwordconfirm]').after('<label class="error is-visible">两次输入密码不一致</label>');
			return false;
		 }else if(password.length<6){
			$('input[name=passwordconfirm]').after('<label class="error is-visible">密码长度必须大于等于6</label>');
			return false;
		 }else{
			 if(myreg.test(email)){
				 col="email="+email+'&&passwd='+password;
			 }else{
				 col="mobile="+email+'&&passwd='+password;
			 }
			 $.ajax({
					type:"POST",
			      	url:"/user/changePassword",
			      	data:col,
			    	dataType:"json",
			    	async:false,
			    	success:function(data){
						if(data==1){
			    			$('#wap-step3').hide();
			    			$('#wap-step4').show();
			    		}else{
			    			$('input[name=passwordconfirm]').after('<label class="error is-visible">不能使用之前设置过的密码</label>');
			    		}
			    	}
				})
		 }
}
//发送手机或邮箱验证码
function sendmsg(email){
	var msg1,msg2;
	//判断是手机号码还是邮箱号码
	if(myreg.test(email)){//邮箱号码
		msg1='验证邮箱';
		msg2='请输入你收到的 6 位数邮箱验证码';
		
	}else{//手机号码
		msg1='验证手机';
		msg2='请输入你收到的 6 位数短信验证码';
	}
	$('#validation-layer h1').html(msg1);
	$('#validation-layer h2').html(msg2);
	showtime(60,'1','wap-forgetpw');
	$.ajax({
		type:"POST",
      	url:"/mobilesms/sendSms",
      	data:{mobileNo:email},
    	dataType:"json",
    	success:function(data){
    		if(data.returnStatus=='000'){
    			$('input[name=verification_code]').val(data.returnData);
    		}else{
    			$('#v-code').after('<label class="error is-visible" style="right: 18rem;">发送异常</label>');
    		}
    	}
	})
}
//确定进入JobPlus
function check_code(){
	$('#v-code').next(':not(#pj-send-code)').remove();
	checksendcode();
	if(!ispass){
		return false;
	}
	$("#usertype2").submit();
}
//校验验证码
function checksendcode(){
	ispass=true;
	//验证码
	var code=$('#v-code').val();
	if($.trim(code).length==0){
		$('#v-code').after('<label class="error is-visible" style="right: 17rem;">请输入验证码</label>');
		ispass=false;
	}
	var verification_code=$('input[name=verification_code]').val();
	var emal=$('input[name=email]').last().val();//手机号或者邮箱
	if(ispass){
		$.ajax({
			type:"POST",
	      	url:"/mobilesms/checkSms",
	      	data:{smsId:verification_code,validateCode:code},
	    	dataType:"json",
	    	async:false,
	    	success:function(data){
	    		if(Number(data)==0){
	    			if(myreg.test(emal)){//邮箱号码
	    				$('#v-code').after('<label class="error is-visible" style="right: 17rem;">邮箱验证码不正确</label>');
	    			}else{//手机号码
	    				$('#v-code').after('<label class="error is-visible" style="right: 17rem;">手机验证码不正确</label>');
	    			}
	    			ispass = false;
	    		}else{
	    			
	    		}
	    	}
		})
	}
}
//验证信息
function reg_checkAjax(id,val){
	/*var val = $.trim($("#"+id).val());*/
	var msg="";
	var type;
	//邮箱或手机验证
	if(id=="email"){
		if(myreg.test(val)){
			type=3;
		}else{
			type=2;
		}
		if(val==""){			
			$('#email').after('<label class="error is-visible">请输入邮箱/手机</label>');
			ispass=false;  
		}else if(!myreg.test(val) && !reg.test(val)){
			$('#email').after('<label class="error is-visible">请输入正确的邮箱或手机</label>');
			ispass=false;  
		}else{
			//1 是用户名  2是手机号  3是邮箱
			$.ajax({
					url:"/user/check/"+val+"/"+type,
					type : "POST", 
					data :{id:val},
					dataType:"json",
					async:false,
					contentType: "application/text",
                    success:function(data){                       
                       if(data==0){	
         					
         				}else{
         					if(myreg.test(val)){
         						$('#email').after('<label class="error is-visible">该邮箱已存在</label>');
         					}else{
         						$('#email').after('<label class="error is-visible">该手机已存在</label>');
         					}
         					ispass=false;
         				} 
                     }
			})
			
		}
	}
	//用户名验证
	if(id=="username"){
	  type=1;
      if(val==""){
		 $('#username').after('<label class="error is-visible">昵称不能为空</label>');
		 ispass=false;  
    	}
       else if(/^\d.*$/.test(val)){
		 $('#username').after('<label class="error is-visible">用户名不能以数字开头</label>');
		 ispass=false;  
    	}
       else if(val.replace(/[^x00-xFF]/g,'**').length<4 || val.replace(/[^x00-xFF]/g,'**').length>18 ){
		 $('#username').after('<label class="error is-visible">合法长度为4-18个字符</label>');
		 ispass=false;  
    	}
       else if(!(/^[_,.!\n\w\u4e00-\u9fa5]*$/.test(val))){
		 $('#username').after('<label class="error is-visible">用户名只能包含_,英文字母，数字或者中文</label>');
		 ispass=false;  
    	}
    	else{
    		$.ajax({
    			url:"user/check/"+val+"/"+type,
				type : "POST", 
				data :{id:val},
				contentType: "application/text",
                success:function(data){
                	 if(data==0){	
                		 
     				 }else{
     					$('#username').after('<label class="error is-visible">用户名已存在</label>');
     					ispass=false;  
     				} 
                 }
    		})
			
    	}
      
	}
	//密码验证
	if(id=="password1"){
		$("#ajax_password1").hide();
		if(val==""){
			 $('#password1').after('<label class="error is-visible">密码不能为空</label>');
			 ispass=false;  
		 }else if(val.length<6 || val.length>20 ){
			$('#password1').after('<label class="error is-visible">只能输入6至20位密码</label>');
			ispass=false;  
		 }
	}
	if(id=="email1"){
		if(val==""){
			 $('#email1').after('<label class="error is-visible">邮箱不能为空</label>');
			 ispass=false;  
		 }else if(!myreg.test(val)&&!reg.test(val)){
			 $('#email1').after('<label class="error is-visible">邮箱或手机格式错误</label>');
			 ispass=false;  
	     }
	}
}
