<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
           建议反馈
    </title> 
    <meta name="viewport" content="width=1230,initial-scale=0.9"/>
    <meta name="apple-mobile-web-app-capable" content="yes" /> 
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_framework.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_about.css">
    <link rel="stylesheet" type="text/css" href="/uedit/themes/default/css/umeditor.css" charset="UTF-8">
  </head>
  <body>
    <div class="page" id="feedback">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='pj-container'>
         <div class='left'>
           <#include "/mydocs/commonTemplate/aboutnav/aboutnav.ftl"/>
         </div>
         <div class='right'>
           <div class='aboutme-img'>
             <img alt='' src='/image/feedback.jpg' width='100%' height='120'>
           </div>
           <div class='feedback'>
             <div class='slogan'>您的建议/问题<span>(500字以内)<span></div>
             <textarea  id='uEditorAbout' class='uEditorAbout' placeholder='在这里输入建议/问题' style='display:none'></textarea>	
             <div class='email'>
                                               邮箱地址:&nbsp;<input placeholder='建议留下常用的邮箱,便于我们第一时间回复您' id='sugEmail'/>                        
             </div>
             <div class='mobile'>
                                             手机号码:&nbsp;<input placeholder='或者留下手机号码,产品经理在处理反馈时,可能与您联系' id='sugMobile'/>
               <a  class="btn-send" onclick="javascript:sugSubmit();">提&nbsp;&nbsp;交</a>
             </div>
           </div>
         </div>
      </div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/about/communaljs.ftl"/>
      <script type="text/javascript" src="/uedit/umeditor.config.js"></script>
      <script type="text/javascript" src="/uedit/umeditor.js"></script>
      <script type="text/javascript" src="/scripts/pj_about.js"></script>
      <script type="text/javascript" src="/scripts/pj_msgbox.js"></script>
  </body>

</html>