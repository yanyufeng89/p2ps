var myreg = /^([a-zA-Z0-9\-]+[_|\_|\.]?)*[a-zA-Z0-9\-]+@([a-zA-Z0-9\-]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var reg= /^[1][34578]\d{9}$/; //验证手机号码  
var istrue=false;
function reg_checkAjax(id){
	var obj = $.trim($("#"+id).val());
	var msg="";
	var type;
	//邮箱或手机验证
	if(id=="email"){
		if(myreg.test(obj))
		{
			type=3;
		}
		else{
			type=2;
		}
		if(obj==""){
			msg='请输入邮箱/手机！';
			update_html(id,"0",msg); 
			return false;
		}else if(!myreg.test(obj) && !reg.test(obj)){
			msg='请输入正确的邮箱或手机！';
			update_html(id,"0",msg);
			return false;
		}else{
			//1 是用户名  2是手机号  3是邮箱
			$.ajax({
					url:"/user/check/"+obj+"/"+type,
					type : "POST", 
					data :{id:obj},
					dataType:"json",
					async:false,
					contentType: "application/text",
                    success:function(data){                       
                       if(data==0){	
         					msg='填写正确！';
         					update_html(id,"1",msg);
         					if(myreg.test(obj)){
         						//如果是邮箱  用户类型是0  手机号码 用户类型是1
         						$("#regtype").val(0);
         						$("#regcode").show();
         						$('#mobliecode').attr('placeholder','邮箱验证码');
         						$('#phonecode').val('获取邮箱验证码').attr('data-messagetype','1');//0是获取手机验证码 1是获取邮箱验证码
         					}else{
         						$("#regtype").val(1);
         						$("#regcode").show();
         						$('#mobliecode').attr('placeholder','短信验证码');
         						$('#phonecode').val('获取手机验证码').attr('data-messagetype','0');
         					}
         					istrue=true;
         					return true;
         				}else{
         					if(myreg.test(obj)){
         						var msg='该邮箱已存在！';
         					}else{
         						var msg='该手机已存在！';
         					}
         					update_html(id,"0",msg);
         					istrue=false;
         					return false;
         				} 
                     }
			})
		}
	}
	//用户名验证
	if(id=="username")
	{
	  type=1;
      if(obj==""){
	     msg='昵称不能为空！';
		 update_html(id,"0",msg);
		 return false;
    	}
       else if(/^\d.*$/.test(obj)){
    	 msg='用户名不能以数字开头';
		 update_html(id,"0",msg);
		 return false;
    	}
       else if(obj.replace(/[^x00-xFF]/g,'**').length<4 || obj.replace(/[^x00-xFF]/g,'**').length>18 ){
    	 msg='合法长度为4-18个字符';
		 update_html(id,"0",msg);
		 return false;
    	}
       else if(!(/^[_,.!\n\w\u4e00-\u9fa5]*$/.test(obj))){
         msg='用户名只能包含_,英文字母，数字或者中文';
		 update_html(id,"0",msg);
		 return false;
    	}
    	else{
    		$.ajax({
    			url:"user/check/"+obj+"/"+type,
				type : "POST", 
				data :{id:obj},
				contentType: "application/text",
                success:function(data){
                	 if(data==0){	
     					msg='填写正确！';
     					update_html(id,"1",msg);
     					return true;
     				}else{
     					msg='用户名已存在！';
     					update_html(id,"0",msg);
     					return false;
     				} 
                 }
    		})
			 return true;
    	}
      
	}
	if(id=="email1"||id=="email3"){
		if(obj==""){
			 msg='邮箱不能为空！';
			 update_html(id,"0",msg);
		 }else if(!myreg.test(obj)){
			msg="邮箱格式错误！";
			update_html(id,"0",msg);
	     }else{ 
	    	 msg='输入正确！';
			 update_html(id,"1",msg);
			 return true;
	   }
	}
	//密码验证
	if(id=="password1"){
		$("#ajax_password1").hide();
		if(obj==""){
			 msg='密码不能为空！';
			 update_html(id,"0",msg);
			 return false;
		 }else if(obj.length<6 || obj.length>20 ){
			 msg='只能输入6至20位密码！';
			update_html(id,"0",msg);
			return false;
		 }else{
			 msg='输入正确！';
			 update_html(id,"1",msg);
			 return true;
		 }
	}
	//手机验证码
	if(id=="moblie_code"){
		 if(obj==""){
			msg="短信验证码不能为空！";
			 update_html(id,"0",msg);
			 return false;
		 }else{
			msg="输入成功！";
			update_html(id,"1",msg);
			return true;
		 }
	}
}

function update_html(id,type,msg){
	
	if(type=="0"){  
		$("#ajax_"+id).show();
		$("#ajax_"+id).html('<i class="reg_tips_icon">*</i>'+msg); 
		$("#ajax_"+id).attr("class","reg_tips reg_tips_red");
		$("#"+id).addClass("logoin_text_focus");
		$("#"+id).attr('date','0');
	}else{ 
		$("#ajax_"+id).hide();
		$("#ajax_"+id).attr("class","reg_tips reg_tips_blue");
		$("#"+id).removeClass("logoin_text_focus");
		$("#"+id).attr('date','1');
	}
}


function uppassword(id){
	var password = $("#password"+id).val();
	S_level=checkStrong(password);
	switch(S_level) { 
	case 0:
		$(".psw_span").removeClass("psw_span_cur");
	break; 
	case 1: //弱
		$("#pass1_"+id).addClass("psw_span_cur");
		$("#pass2_"+id).removeClass("psw_span_cur");
		$("#pass3_"+id).removeClass("psw_span_cur");
	break; 
	case 2: //中
		$("#pass1_"+id).addClass("psw_span_cur");
		$("#pass2_"+id).addClass("psw_span_cur");
		$("#pass3_"+id).removeClass("psw_span_cur");
	break; 
	default: //强
		$("#pass1_"+id).addClass("psw_span_cur");
		$("#pass2_"+id).addClass("psw_span_cur");
		$("#pass3_"+id).addClass("psw_span_cur");
	} 
}
//返回密码的强度级别 
function checkStrong(sPW){
	if (sPW.length<=4) 
	return 0; //密码太短 
	Modes=0; 
	for (i=0;i<sPW.length;i++){
	//测试每一个字符的类别并统计一共有多少种模式. 
	Modes|=CharMode(sPW.charCodeAt(i)); 
	}
	return bitTotal(Modes); 
} 
//下一步
function forgetpw(){
	alert();
}
function CharMode(iN){ 
	if (iN>=48 && iN <=57) //数字 
	return 1; 
	if (iN>=65 && iN <=90) //大写字母 
	return 2; 
	if (iN>=97 && iN <=122) //小写 
	return 4; 
	else 
	return 8; //特殊字符 
} 

//计算出当前密码当中一共有多少种模式 
function bitTotal(num){ 
	modes=0; 
	for (i=0;i<4;i++){ 
	if (num & 1) modes++; 
	num>>>=1; 
	} 
	return modes; 
}
function showpw(id){
	if($("#showpw"+id).html()=="显示密码"){
		PasswordToText("password"+id);
		$("#showpw"+id).html('隐藏密码');
	}else{
		TextToPassword("password"+id);
		$("#showpw"+id).html('显示密码');
	}
}

function TextToPassword(name){
	var control=document.getElementById(name);
	var newpassword = document.createElement("input");
	newpassword.type="password";
	newpassword.name=control.name;
	newpassword.setAttribute("class",control.getAttribute("class"));
	newpassword.setAttribute("className",control.getAttribute("className"));
	newpassword.id=control.id;
	newpassword.value=control.value;
	newpassword.onblur=control.onblur;
	setTimeout('document.getElementById("'+control.id+'").focus()',200);
	$(control).parent()[0].replaceChild(newpassword,control);
}
function PasswordToText(name){
	var control=document.getElementById(name);
		var newpassword = document.createElement("input");
		newpassword.type="text";
		newpassword.name=control.name;
		newpassword.setAttribute("class",control.getAttribute("class"));
		newpassword.setAttribute("className",control.getAttribute("className"));
		newpassword.id=control.id;
		newpassword.value=control.value;
		newpassword.onblur=control.onblur;
		$(control).parent()[0].replaceChild(newpassword,control);
}
//注册
function check_user(){
	var flag =true;
	var authcode=$("#CheckCode").val();//验证码
	var usertype=$("#usertype").val();
	var email=$.trim($("#email").val());
	var password = $.trim($("#password1").val());
	var moblie_code=$.trim($("#mobliecode").val());
	var arrayObj = new Array();
	var verificationcode=$('input[name=verification_code]').val();

	arrayObj.push('email');
	reg_checkAjax("username");
	arrayObj.push('username');
	reg_checkAjax("email");
	reg_checkAjax("password1");
	if($.trim(verificationcode)==''&&$.trim(email)!=''&&$.trim($('#username').val())!=''){
		layer.msg('请获取手机或邮箱验证码！', {offset:'36%',shade:0.3,shadeClose:true});
		return false;  
	}
	if($.trim($('#mobliecode').val())==''){
		layer.msg('请输入短信或邮箱验证码！', {offset:'36%',shade:0.3,shadeClose:true});
		return false;  
	}
	arrayObj.push('password1');
	for(i=0;i<arrayObj.length;i++){
		if(!exitsdate(arrayObj[i])){
			return false;
		}
	}
	if(authcode==""){
		layer.msg('请输入验证码！', {offset:'36%',shade:0.3,shadeClose:true});return false;  
	}
	//验证输入的验证码和手机或者邮箱获取的验证码是否一致
	$.ajax({
		type:"POST",
      	url:"/mobilesms/checkSms",
      	data:{smsId:verificationcode,validateCode:moblie_code},
    	dataType:"json",
    	async:false,
    	success:function(data){
    		if(Number(data)==0){
    			layer.msg('短信或邮箱验证码不正确！', {offset:'36%',shade:0.3,shadeClose:true});
    			  flag = false;
    		}else{
    			
    		}
    	}
	})
	if(flag){
		var loadi = layer.msg('正在注册……',{icon:16, offset:'36%', shade:0.3,shadeClose:true});
		
		if($("#xieyi").attr("checked")!='checked'){  
			layer.msg('您必须同意注册协议才能成为本站会员！', {offset:'36%',shade:0.3,shadeClose:true});return false;  
		}else{
			$("#regtype1").submit();
		}
	}
}
	


function exitsdate(id){
	if(document.getElementById(id))
	{
		if($('#'+id).attr('date')!='1'){
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
$("#usertype1").submit();

//发送验证码
function sendmsg(obj,mtype){
	//1代码获取邮箱验证码  0是手机验证码
	var messagetype=$(obj).attr('data-messagetype');
	var moblie=$("#email").val();
	var authcode=$("#CheckCode").val();
	if(mtype!='forgetpw'){
		reg_checkAjax("email");
		if(!istrue){
			return false;
		};
		if(!reg.test(moblie)&&!myreg.test(moblie)){
			layer.msg('请输入正确的手机号码！', {offset:'36%',shade:0.3,shadeClose:true});
			return false;
		}
		if(authcode==""){
			layer.msg('请输入验证码！', {offset:'36%',shade:0.3,shadeClose:true});
			return false;
		}
		var checkCode="";
		$("#vcode_imgs1 div span").each(function(){
			checkCode+=$(this).text();
		});
		if($.trim($("#CheckCode").val().toLowerCase())!= $.trim(checkCode.toLowerCase())){
		  layer.msg('请输入正确的验证码！', {offset:'36%',shade:0.3,shadeClose:true});
		  return false;  
		}
	}

	//mtype 不同表示两个界面 一个是找回密码界面获取验证码  一个是注册界面获取验证码
	if(mtype=='forgetpw'){
		if(messagetype=='1'){
			$('#emailcode').addClass('btn_disabled');
		}else{
			$('#phonecode').addClass('btn_disabled');
		}
	}else{
		$(obj).addClass('btn_disabled');
	}
	
	
	showtime(60,messagetype,mtype);
	$.ajax({
		type:"POST",
      	url:"/mobilesms/sendSms",
      	data:{mobileNo:moblie},
    	dataType:"json",
    	success:function(data){
    		if(data.returnStatus=='000'){
    			$('input[name=verification_code]').val(data.returnData);
    		}else{
    			layer.msg('发送异常,请稍后再试！', {offset:'36%',shade:0.3,shadeClose:true});
    		}
    	}
	})
}

function check_name(){
	$("#ajax_password1").hide();
	var username=$("#username").val();
	
	if(!myreg.test(username) && !reg.test(username)){
		msg='请输入正确的邮箱或手机！';
		update_html("username","0",msg);
		return false;
	}
	else{
	   	msg="输入成功！";
		update_html("username","1",msg);
		return true;
	}
}

function showtip(id){
	$("#tip"+id).show();
}
function hidetip(id){
	$("#tip"+id).hide();
}