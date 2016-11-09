<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
           JobPlus-建议反馈
    </title>
    <meta name="description" content="JobPlus网是国内首创的企业知识库公共平台,为中国7亿上班族打造的一站式学习平台,践行着“我为人人,人人为我”的互联网分享精神,着力于弥补低效率,高成本的学习遗憾,让高效学习成果真正与工作成果连接起来,“既学+既用”,“提升岗位胜任能力”。">
    <meta name="keywords" content="JobPlus,知识分享,知识库,文档,书籍,问答,课程,文章,网站,在线培训,企业课堂,员工培训,在线教育,职业技能,视频课程,培训网站,职场培训,网络课堂,人才培训,知识管理">
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
             <img alt='建议反馈'  src='/image/feedback.jpg' class='lazy' width='100%' height='120'>
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