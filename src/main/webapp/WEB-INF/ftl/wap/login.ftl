<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>
             登录-JobPlus
    </title> 
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
	<link rel='stylesheet' type='text/css' href='/css/pj_waplogin.css'>
	 <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
     <script type="text/javascript" src="/scripts/pj_reg_wap_ajax.js"></script>
     <script type="text/javascript" src="/scripts/js.KinerCode.js"></script>
     <script type="text/javascript" src="/scripts/pj_authcode.js"></script>
     <script type='text/javascript' src='/scripts/pj_publicMethod.js'></script>
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
	     <div id='register-layer'>
	       <div class='index-header'>
	          <img src='/image/login-logo.png' alt='登录-logo' width='280' height='90' >
	       </div>
	       <div class='logoin_cont_box clearfix'>
	          <div class="index-tab-navs">
				<div class="pj-navs-slider" data-active-index="0" id='pj-navs-slider'>
					<a href="javascript:void(0)" class='active'>登 录</a>
					<a href="javascript:void(0)">注册</a>
					<span class="pj-navs-slider-bar"></span>
				</div>
			 </div>
			   <div class="pj-view pj-view-signin"  style="display: block;">
			    <form method="post" id='usertype1' action="login<#if backurl??>?backurl=${backurl}</#if>">
			       <div class='pj-group-inputs'>
			           <div class="pj-email pj-input-wrapper">
						   <input type="text" name="username" class='email' id='email1' aria-label="手机号或邮箱" placeholder="手机号或邮箱" required="">
					   </div>
					   <div class="pj-input-wrapper">
						   <input type="password" name="passwd" id='password1' class='passwd' aria-label="密码" placeholder="密码" required="">
						   <#if (message)??>
						      <label class="error is-visible">${message}</label>
						   </#if>
					   </div>
			       </div>
			       <div class="login_box_cz clearfix">
						<label class="remember-me">
						  <input type="checkbox" name="remember_me" checked="" value="true">记住登录状态
						</label>
						<a class="unable-login" href="/index/fore/retakepwd">忘记密码?</a>
			       </div>
			       <div class="pj-button-login command">
					    <button class="pj-sign-button submit" type="button" onclick="login_check_user(this)">登录</button>
			       </div>
			    </form>
			 </div>
			 
			   <div class="pj-view pj-view-signup"  style="display: none;">
			
			    <form method="post" id='usertype2' action="user/reg">
			        <div class='pj-group-inputs'>
			           <div class="pj-fullname pj-input-wrapper">
						   <input type="text" name="username" id='username' aria-label="昵称" placeholder="昵称" >
					   </div>
					   <div class="pj-phone pj-input-wrapper">
						   <input type="text" name="email" id='email' aria-label="请输入手机号或邮箱" placeholder="请输入手机号或邮箱">
					   </div>
					   <div class="pj-passwd pj-input-wrapper">
						   <input type="password" name="passwd" id='password1' aria-label="密码(不少于 6 位)" placeholder="密码(不少于 6 位)">
					   </div>
					   <div class="pj-vcode pj-input-wrapper">
						   <input type="text" name="vcode" id='moblie_code' aria-label="验证码" placeholder="验证码">
						   <div id="vcode_imgs1"  class="logoin_text_yz" style="margin-left:10px;"> 
						   
	                       </div>
					   </div>
			       </div>
			       <div class="pj-button-register command">
					    <button class="pj-sign-button submit" type="button"  onclick="register_check_user(this)">注册</button>
			       </div>
			    </form>
			    <p class="agreement-tip">点击「注册」按钮，即代表你同意<a href="javascript:void(0)" target="_self" class="reg_yhxy">用户协议</a></p>
			 </div>
			 </div>
			 </div>
			 <div id='validation-layer' class='pj-view' style='display:none'>
			    <div class='verification'>
			      <input type='hidden' value='' name='verification_code'>
			      <h1>验证手机</h1>
			      <h2>请输入你收到的 6 位数短信验证码</h2>
			       <div class='pj-group-inputs'>
			           <div class="pj-fullname pj-input-wrapper">
						   <input type="text" name="vemail" id='vemail'>
					   </div>
					   <div class="pj-phone pj-input-wrapper">
						   <input type="text" name="v-code" id='v-code'>
						   <button type="button" class="pj-send-code" id='pj-send-code' onclick="sendmsg($('#email').val())">点击免费获取</button>
					   </div>
			       </div>
			        <div class="pj-button-register command">
					    <button class="pj-sure-button submit" type="button" onclick="check_code(this)">确认</button>
			       </div>
			    </div>
			    
			 </div>
	       </div>
		   
	  </div>
     </div>
	
  </body>

</html>
