<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          忘记密码
    </title>
    <meta name="viewport" content="width=1230"/> 
    <meta name="apple-mobile-web-app-capable" content="yes" /> 
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_framework.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" href="/scripts/layer.css" id="layui_layer_skinlayercss"> 
    <link rel="stylesheet" type="text/css" href="/css/pj_passretake.css" charset="UTF-8">
    <#include "/mydocs/commonTemplate/pwretake/pwretake.ftl"/> 
  </head>
  <body>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
      <div id="bd">
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              <div  class="upload-widget ">
                <div id="password_content_cont"  class='password_content_cont' style="display: block;">
                     <div class="password_content_h1">
                        <i class="password_content_h1_i"></i>
                        <span class="password_content_h1_span">找回密码</span>
                     </div>
	                 <div class="upload-steps clearfix">
						<ul>
							<li class="step-num active">1<span class="tips"></span>
							</li>
							<li class="step-bar"></li>
							<li class="step-num">2<span class="tips"></span>
							</li>
							<li class="step-bar"></li>
							<li class="step-num">3<span class="tips"></span>
							</li>
						</ul>
						<div class="clear"></div>
						<!--step1-->
						<div class="password_cont" id="step1">
						  <input type='hidden' name='emailortel' value=''>
				          <div class="password_list m15 fl">
				            <div class="password_list_left">请输入邮箱/手机：</div>
				            <input type="text" id="email" class="password_list_text">
				            <div class="password_list_r"></div>
				          </div>
				          <div class="password_list fl m15">
				            <div class="password_list_left">验证码：</div>
				            <input type="text" id="CheckCode" class="password_list_text password_list_textw110">
				            <div id="vcode_imgs1"  class="logoin_text_yz" style="margin-left:10px;"> 
	                
	                        </div>
				            <div class="password_list_r">看不清？<a href="javascript:check_codes(1);" class="registe_a">换一张</a></div>
				          </div>
				          <div class="password_list fl m15">
				            <div class="password_list_left">&nbsp;</div>
				            <input type="submit" value="下一步"  data-messagetype='0' class="password_list_bth" onclick="forgetpw();">
				          </div>
				          <div class="clear"></div>
				        </div>
				        <!--step2--发送邮件-------------------------------------->
				         <div class="password_cont " style="display:none;" id="step3_email">
					          <div class="password_list fl m10">
					            <div class="password_list_s"> 密码重置邮件已发送到你的邮箱：<span id="step3_email_halt"></span>请在20分钟内查收，并在下方填写您收到的验证码</div>
					          </div>
					          <div class="password_list fl m10">
					            <div class="password_list_left">验证码：</div>
					            <input type="text" value="" placeholder="请输入邮箱验证码" class="password_list_text password_list_textw110" id="sendcode_email">
					            <div class="password_list_r step3_email_timer">
					               <input type="button" value="点击免费获取" class="input_btn" data-messagetype='1' id="emailcode" onclick="sendmsg(this)" style="width:160px;">
					            </div>
					          </div>
					          <div class="password_list fl m15">
					            <div class="password_list_left">&nbsp;</div>
					            <input type="submit" value="下一步" class="password_list_bth" onclick="checksendcode('email');">
					          </div>
					      </div>
					      <div class="password_cont " style="display:none;" id="step3_moblie">
					          <div class="password_list fl m10">
					            <div class="password_list_s"> 密码重置短信已发送到你的手机：<span id="step3_email_halt"></span>请在20分钟内查收，并在下方填写您收到的验证码</div>
					          </div>
					          <div class="password_list fl m10">
					            <div class="password_list_left">验证码：</div>
					            <input type="text" value="" placeholder="请输入手机验证码" class="password_list_text password_list_textw110" id="sendcode_moblie">
					            <div class="password_list_r step3_email_timer">
					              <input type="button" value="点击免费获取" class="input_btn" data-messagetype='0' id="phonecode" onclick="sendmsg(this)" style="width:160px;">
					            </div>
					          </div>
					          <div class="password_list fl m15">
					            <div class="password_list_left">&nbsp;</div>
					            <input type="submit" value="下一步" class="password_list_bth" onclick="checksendcode('moblie');">
					          </div>
					       </div>
					       <!--step3--重置密码------------------------------------>
					       <div class="password_cont " style="display:none;" id="step4">
					          <div class="password_list fl m15">
					            <div class="password_list_left">输入新的密码：</div>
					            <input type="password" value="" class="password_list_text" name="password" id="password">
					          </div>
					          <div class="password_list fl m15">
					            <div class="password_list_left">确认新的密码：</div>
					            <input type="password" value="" class="password_list_text" name="passwordconfirm" id="passwordconfirm">
					          </div>
					          <div class="password_list fl m15">
					            <div class="password_list_left">&nbsp;</div>
					            <input type='hidden' name='verification_code' value=''>
					            <input type="submit" value="提交修改" class="password_list_bth" onclick="editpw();">
					          </div>
					        </div>
					        <div class="clear"></div>
					        <!--step4--完成------------------------------------>
					        <div class="password_cont  " style="display:none;" id="step5">
					          <div class="password_cont_success ">
					            <div class="password_cont_success_p">恭喜您！密码重置成功！</div>
					            <div class="password_contm-row"> <a href="javascript:toLogin();" class="password_input_btn">立即登录</a> </div>
					          </div>
					        </div>
					 </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class='pagetemplate'></div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
   
  </body>

</html>