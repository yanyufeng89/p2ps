<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        JobPlus-登录
    </title>

    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link rel="stylesheet" href="/css/css.css" type="text/css" charset="UTF-8">
    <link rel="stylesheet" href="/css/login.css" type="text/css" charset="UTF-8">
<#--<link rel="stylesheet" href="/css/layer.css" type="text/css" charset="UTF-8">-->
    <link rel="stylesheet" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
    <script src="/scripts/js.KinerCode.js" type="text/javascript"></script>
    <script type="text/javascript" src="/scripts/pj_authcode.js"></script>
    <script language="javascript" src="/scripts/layer.js"></script>
    <script type="text/javascript" src="/scripts/pj_reg_ajax.js"></script>
    <script type="text/javascript" src="/scripts/pj_constant.js"></script>
    <script type="text/javascript" src="/scripts/jquery.cookie.js"></script>
    <script src="/scripts/sockjs-0.3.min.js"></script>
    <script>
        setBackUrlCookie();
    </script>
</head>

<body class="login" style="background:#fff;">
<div class="top-header">
    <div class='header-row'>
        <div class="header-alignleft">
            <div class="header-contact-info">
                <img src="/image/website-banners.png" class="img-responsive lazy" alt="网站横幅">
            </div>
        </div>
    </div>
</div>
<div class="login-head">
    <a class="navbar-brand" href="/index">
        <img src="image/pluslogo.png" class="img-responsive lazy" alt="logo">
    </a>
</div>
<div class="logoin_cont_box">
    <input type="hidden" value="${backurl}" id="backurl"/>
    <form id="usertype1" method="post" action="login<#if backurl??>?backurl=${backurl}</#if>">
        <div class="login_left">
            <div class="login_box_cont">
                <div class="login_box_h1_d">
                    用户登录
                </div>
                <div class="clear">
                </div>
                <div class="lgoin_box_cot" id="login_cur">
                    <div class="login_box_list logoin_re">
                        <input type="text" class="login_box_bth placeholder loginname" id="username" name="username"
                               placeholder="请输入手机/邮箱" onblur="check_name();">

                        <span class="reg_tips reg_tips_red" id="ajax_username" style="display:none;">
                </span>
                    </div>
                </div>
                <div class="login_box_list logoin_re_m">
              <span id="pass1">
                <input type="password" placeholder="" onkeyup="uppassword(1)" id="password1" name="passwd"
                       onblur="reg_checkAjax(&#39;password1&#39;);"
                       class="login_box_bth placeholder loginname loginpwd">
              </span>
                    <span class="reg_tips reg_tips_red" id="ajax_password1">
                    <#if (message)??>
                        <span>
                        ${message}
                   </span>
                    </#if>
              </span>

                </div>
                <div class="clear">
                </div>
                <div class="login_box_list logoin_re_m" style="display:none;">
                    <input id="txt_CheckCode" type="text" class="login_box_bth_yz loginname  loginpyz"
                           value="验证码" maxlength="4">
                </div>
                <div class="login_box_list_yzm" id="vcode_imgs1" style="display:none;">

                </div>
                <div class="clear">
                </div>
                <div class="login_box_cz">
              <span class="login_box_cz_l">
                <input type="checkbox" name="loginname" value="1" class="index_logoin_check">
                <em class="fl">
                  记住登录状态
                </em>
              </span>
                    <a href="/index/fore/retakepwd" style='color:#0867c5'>
                        忘记密码？
                    </a>
                </div>
                <div class="clear">
                </div>
                <div class="login_box_cz">
                    <input type="submit" value="登 录" class="login_box_bth2">
                    <a class="go_register" href="registration.html">
                        立即注册
                    </a>
                </div>
                <div class="login_other">
                    <div class="login_other_left">
                        其他方式登录：
                    </div>
                    <div class="pjlogin_qq">
                        <a href="/authorize/weibo/login" target="_self" class="pjlog_sina png" title="新浪微博">
                        </a>
                        <a href="/authorize/qq/login" target="_self" class="png" title="QQ">
                        </a>
                        <a href="/authorize/wechat/login" target="_self" class="pjlog_wx png" title="微信">
                        </a>
                    </div>
                </div>
                <div class="clear">
                </div>
            </div>
        </div>
    </form>
</div>

<#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
<script>
    jQuery(document).ready(function ($) {
        var actionUrl = $("#backurl").val();
        if (actionUrl == null || actionUrl == '') {
            var referrer = document.referrer.replace("http://", "");
            if (referrer.indexOf("registration") == -1 && referrer.indexOf("login") == -1) {
                var backurl = encodeURI(referrer.substring(referrer.indexOf(projectName) + projectName.length, referrer.length));
                if (backurl.startWith("?"))
                    backurl = "";
                else if (backurl.indexOf("?message=") > -1) {
                    backurl = backurl.substring(0, backurl.indexOf("?message="));
                }
                $.cookie("backurl", backurl);
                $("#usertype1").attr("action", "login?backurl=" + backurl);
            }
        }
    });
</script>
</body>
</html>