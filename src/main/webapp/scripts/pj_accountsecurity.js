var myreg = /^([a-zA-Z0-9\-]+[_|\_|\.]?)*[a-zA-Z0-9\-]+@([a-zA-Z0-9\-]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var reg= /^[1][34578]\d{9}$/; //验证手机号码 
var flag=true;
$(function(){
	//绑定手机或者修改手机号码
	$('.boundmobile').live('click',function(){
		$('.phone-form').removeClass('hidden');
	})
	//绑定邮箱或者修改邮箱
	$('.boundemail').live('click',function(){
		$('.email-form').removeClass('hidden');
	})
	//修改密码
	$('.editpawssord').live('click',function(){
		$('.password-form').removeClass('hidden');
	});
	//获取邮箱验证码
	$('.email-form .getcode').live('click',function(){
		checkAjax('email',$(this))
	})
	//获取手机验证码
	$('.phone-form .getcode').live('click',function(){
		checkAjax('phone',$(this))
	})
	//发送邮箱或手机验证码
	$('#emailcode,#phonecode').live('click',function(){
		var type=$(this).attr('data-type');
		var email=$('#'+type).val();
		$(this).addClass('btn_disabled');
		showtime(60,type);
		$.ajax({
			type:"POST",
	      	url:"/mobilesms/sendSms",
	      	data:{mobileNo:email},
	    	dataType:"json",
	    	success:function(data){
	    		if(data.returnStatus=='000'){
	    			if(type=='email')
	    			    $('input[name=email_verification_code]').val(data.returnData);
	    			else
	    				$('input[name=mobile_verification_code]').val(data.returnData);
	    			
	    		}else{
	    			
	    		}
	    	}
		})
	})
	//手机或者邮箱确定
	$('.email-form .btnsure,.phone-form .btnsure').live('click',function(){
		 var flag=true;
		 var $this=$(this);
		 var email_verification_code,mobile_verification_code,datacol;
		 var type=$(this).attr('data-type');
		 var email=$('#'+type).val();
		 if(type=='email'){
			 email_verification_code=$('input[name=email_verification_code]').val();
			 if($.trim(email_verification_code)==''){
				 $this.prev().prev().find('.verification').html('请获取新的验证码').show();
				 return false;
			 }
		 }else{
			 mobile_verification_code=$('input[name=mobile_verification_code]').val();
			 if($.trim(mobile_verification_code)==''){
				 $this.prev().prev().find('.verification').html('请获取新的验证码').show();
				 return false;
			 }
		 }
		 var digits=$('.'+type+'-form input[name=digits]').val();
		 if($.trim(digits)==''){
			 $this.prev().prev().find('.verification').html('请输入验证码').show();
			 return false;
		 }
		 if(type=='email'){
			 datacol="smsId="+email_verification_code+"&&validateCode="+digits;
		 }else{
			 datacol="smsId="+mobile_verification_code+"&&validateCode="+digits;
	     }
		 $.ajax({
				type:"POST",
		      	url:"/mobilesms/checkSms",
		      	data:datacol,
		    	dataType:"json",
		    	async:false,
		    	success:function(data){
		    		if(Number(data)==0){
		    			 $this.prev().prev().find('.verification').html('输入的验证码不正确').show();
		    			 flag = false;
		    		}else{
		    			
		    		}
		    	}
			})
		if(flag){
  		    var eindex1=Math.ceil((email.substring(0,email.lastIndexOf('@'))).length*2/3);
            var eindex2=email.lastIndexOf('@');
			if(type=='email'){
     			$('.email-form,.email-form .group:last-child').addClass('hidden');			
				$('.email').html(email.substring(0,eindex1-1)+'****'+email.substring(eindex2-1,email.length));
				$('.boundemail').html('修改');
				updateUserInfo('email=',email);
				$('input[name=email]').val(email);
				//清空验证码
				$('input[name=email_verification_code]').val('');
				
			}else{
				$('.phone-form,.phone-form .group:last-child').addClass('hidden');
				$('.phone').html(email.substring(0,3)+'****'+email.substring(7,email.length));
				$('.boundmobile').html('修改');
				updateUserInfo('mobile=',email);
				$('input[name=mobile]').val(email);
				//清空验证码
				$('input[name=mobile_verification_code]').val('');
			}
			
			
			
		}else{
			return flag;
		}
	});
	//修改密码点击确定
	$('.password-form .btnsure').live('click',function(){
		var password=$('input[name=password]').val();
		var password_repeat=$('input[name=password_repeat]').val();
		//手机号码
		var mobile=$('input[name=mobile]').val();
		//邮箱
		var email=$('input[name=email]').val();
		$('.password_error').html('').hide();
		$('.password_repeat_error').html('').hide();
		if($.trim(password)==''&&$.trim(password_repeat)==''){
			$('.password_error').html('填写密码').show();
			$('.password_repeat_error').html('填写密码').show();
			return false;
		}
		if ($.trim(password)==''&&$.trim(password_repeat)!=''){
			$('.password_error').html('填写密码').show();
			return false;
		}
		if($.trim(password)!=''&&$.trim(password_repeat)==''){
			$('.password_repeat_error').html('填写密码').show();
			return false;
		}
		if($.trim(password)!=$.trim(password_repeat)){
			$('.password_repeat_error').html('请再次输入相同的值').show();
			return false;
		}
		if($.trim(password).length<6){
			$('.password_error').html('密码不能少于6位').show();
			return false;
		}
		updatePasswd(mobile,email,password);
	})
	
})
//定时刷新
function startrequest() { 
	$('.successupdate').hide();
} 
//更改密码
function updatePasswd(mobile,email,password){
	var flag=true;
	var col;
	if($.trim(mobile)!=''){
		col="mobile="+mobile+"&&passwd="+password;
	}else{
		col="email="+email+"&&passwd="+password;
	}
	$.ajax({
		type:"POST",
      	url:"/user/changePassword",
      	data:col,
    	dataType:"json",
    	async:false,
    	success:function(data){
    		if(data==1){
    		
    		}else{
    			$('.password_error').html('不能使用之前设置过的密码').show();
    			flag=false;
    		}
    	}
	})
	if(flag){
		$('input[name=password]').val('');
		$('input[name=password_repeat]').val('');
		$('.password-form').addClass('hidden');
		$('.successupdate').html('修改密码成功!').show();
		//定时刷新
	    setInterval("startrequest()",3000); 
	}else{
		return flag;
	}
}
//更新用户信息
function updateUserInfo(column,val){
	$.ajax({
		type:"POST",
      	url:"/user/update",
      	data:column+val,
    	dataType:"json",
    	async:false,
    	success:function(data){
    		if(data==0){
    			
    		}else{
    			
    		}
    	}
	})
}
//鼠标移开校验手机号码或邮箱(1 是用户名  2是手机号  3是邮箱)
function checkAjax(id,obj){
	var type;
	var tel=$.trim($("#"+id).val());
	if($.trim(tel)==''){
		if(id=='email'){
			$('.email-form').find('.error').html('请填写邮箱').show();
			flag=false;
		}else{
			$('.phone-form').find('.error').html('请填写手机号码').show();
			flag=false;
		}
	}else{
		if(id=='email'){
			var email=$('#email').val();
			if(!myreg.test(tel)){
				$('.email-form').find('.error').html('请输入正确的邮箱').show();
				flag=false;
			}else{
				type=3;
				if(!Isexist(tel,3)){
					flag=false;
				}else{
					//发送邮箱验证码
					obj.addClass('hidden').prev().removeClass('hidden');
					obj.next().removeClass('hidden');
					$('#emailcode').addClass('btn_disabled');
					showtime(60,'email');
					$.ajax({
						type:"POST",
				      	url:"/mobilesms/sendSms",
				      	data:{mobileNo:email},
				    	dataType:"json",
				    	success:function(data){
				    		if(data.returnStatus=='000'){
				    			$('input[name=email_verification_code]').val(data.returnData);
				    			
				    		}else{
				    			
				    		}
				    	}
					})
				}
			}
		}else{
			var mobile=$('#phone').val();
            if(!reg.test(tel)){
            	$('.phone-form').find('.error').html('请输入正确的手机号').show();
            	flag=false;
			}else{
				type=2;
				if(!Isexist(tel,2)){
					flag=false;
				}else{
					//发送手机验证码
					obj.addClass('hidden').prev().removeClass('hidden');
					obj.next().removeClass('hidden');
					$('#phonecode').addClass('btn_disabled');
					showtime(60,'mobile');
					$.ajax({
						type:"POST",
				      	url:"/mobilesms/sendSms",
				      	data:{mobileNo:mobile},
				    	dataType:"json",
				    	success:function(data){
				    		if(data.returnStatus=='000'){
				    			$('input[name=mobile_verification_code]').val(data.returnData);
				    			
				    		}else{
				    			
				    		}
				    	}
					})
				}
			}
		}
	}
	return flag;
}
//判断新输入的手机或者邮箱是否存在
function Isexist(email,type){
	var isflag=true;
	//1 是用户名  2是手机号  3是邮箱
	$.ajax({
			url:"/user/check/"+email+"/"+type,
			type : "POST", 
			data :{id:email},
			dataType:"json",
			contentType: "application/text",
			async:false,
            success:function(data){
               if(data==1){	
 					if(type==2){
 						$('.phone-form').find('.error').html('手机号码已注册').show();
 						isflag=false;
 					}else{
 						$('.email-form').find('.error').html('邮箱已注册').show();
 						isflag=false;
 					}
 				}else{
 					$('.phone-form,.email-form').find('.error').hide();
 				} 
             }
	})
	return  isflag;
}

function showtime(t,types){
	if(types=='email'){
		document.getElementById('emailcode').disabled=true;
		for(i=1;i<=t;i++) {
			window.setTimeout("update_e(" + i + ","+t+")", i * 1000);
		}
	}else{
		document.getElementById('phonecode').disabled=true;
		for(i=1;i<=t;i++) {
			window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
		}
	}
}
function update_e(num,t) {
	if(num == t) {
			document.getElementById('emailcode').value='重新发送';
			document.getElementById('emailcode').disabled=false;
	}
	else {
		    printnr = t-num;
			document.getElementById('emailcode').value=" (" + printnr +")秒后重新发送";

	}
}
function update_p(num,t){
	if(num == t) {
			document.getElementById('phonecode').value='重新发送';
			document.getElementById('phonecode').disabled=false;
	}
	else {
		    printnr = t-num;
	        document.getElementById('phonecode').value=" (" + printnr +")秒后重新发送";		
	}
}
