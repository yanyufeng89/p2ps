<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>
         找回密码-JobPlus
    </title> 
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
	<link rel='stylesheet' type='text/css' href='/css/pj_waplogin.css'>
	 <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
     <script type="text/javascript" src="/scripts/pj_reg_wap_ajax.js"></script>
     <script type="text/javascript" src="/scripts/js.KinerCode.js"></script>
     <script type="text/javascript" src="/scripts/pj_authcode.js"></script>
     <script type='text/javascript' src='/scripts/pj_publicMethod.js'></script>
     <script type="text/javascript" src="/scripts/pj_constant.js"></script>
     <script type="text/javascript">
		 var phoneWidth =  parseInt(window.screen.width);
		 var phoneScale = phoneWidth/640;
		 var ua = navigator.userAgent;
		 if (/Android (\d+\.\d+)/.test(ua)){
				   var version = parseFloat(RegExp.$1);
				   if(version>2.3){
							document.write('<meta name="viewport" content="width=640, height=device-height minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
				   }else{
							document.write('<meta name="viewport" content="width=640, height=device-height target-densitydpi=device-dpi">');
				   }
		 } else {
				   document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
		 }
	 </script>
  </head>
  <body style='background:#f5fbfe'>
     <div class="wrap bc">
      <div class='index-main-body'>
	      <div id='wap-step1' class='pj-view'>
	            <h1>找回密码</h1>
			    <div class='verification'>
			      <input type='hidden' value='' name='verification_code'>
			      
			      <div class='pj-group-inputs'>
			           <div class="pj-fullname pj-input-wrapper">
						   <input type="text" name="email" id='email' aria-label="请输入邮箱/手机" placeholder="请输入邮箱/手机" required="">
					   </div>
					   <div class="pj-vcode pj-input-wrapper">
						   <input type="text" name="vcode" id='moblie_code' aria-label="验证码" placeholder="验证码">
						   <div id="vcode_imgs1"  class="logoin_text_yz" style="margin-left:10px;"> 
						   
	                       </div>
					   </div>
			       </div>
			        <div class="pj-button-register command">
					    <button class="pj-sure-button submit" type="button" onclick="wap_forgetpw();">下一步</button>
			       </div>
			    </div>
		  </div>
		  <div id='wap-step2' class='pj-view' style='display:none'>
		       <h2>验证码已发送,请注意查收</h2>
			   <div class='verification'>
			      <input type='hidden' value='' name='verification_code'>
			      <div class='pj-group-inputs'>
			          <div class="pj-phone pj-input-wrapper">
						   <input type="text" name="v-code" id='v-code'>
						   <button type="button" class="pj-send-code" id='pj-send-code' onclick="sendmsg($('#email').val())">点击免费获取</button>
					  </div>
			       </div>
			        <div class="pj-button-register command">
					    <button class="pj-sure-button submit" type="button" onclick="wap_checksendcode();">下一步</button>
			       </div>
			   </div>
		  </div>
		  <div id='wap-step3' class='pj-view' style='display:none'>
		       <h2>重置密码</h2>
			   <div class='verification'>
			      <div class='pj-group-inputs'>
			          <div class="pj-phone pj-input-wrapper">
						   <input type="password" name="password"  aria-label="输入新的密码" placeholder="输入新的密码">
					  </div>
					  <div class="pj-phone pj-input-wrapper">
						   <input type="password" name="passwordconfirm"  aria-label="确认新的密码" placeholder="确认新的密码">
					  </div>
			       </div>
			        <div class="pj-button-register command">
					    <button class="pj-sure-button submit" type="button" onclick="wap_editpw();">提交修改</button>
			       </div>
			   </div>
		  </div>
		  <div class="password_cont  " style="display:none;" id="wap-step4">
	          <div class="password_cont_success ">
	            <h1>恭喜您！密码重置成功！</h1>
	            <button class="pj-sure-button submit"  style='width:60%' type="button" onclick="toLogin();">立即登录</button>
	          </div>
		</div>
	  </div>
     </div>
	
	 
  </body>

</html>
