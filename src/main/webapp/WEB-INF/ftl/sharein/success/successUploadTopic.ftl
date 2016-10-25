<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
         话题分享
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
  </head>
  <body>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
		 <div class='topicdetail'>JobPlus知识库>话题分享</div>
         <div class="upload-topic"></div>
		  <div class="bd-wrap">
			<div class="body">
			  <div class="main">
				<div id="upload-widget" class="upload-widget ">
				  <div id="upload-success-container" class="upload-success-container" style="display: block;">
					<div class="upload-steps clearfix">
					  <ul>
						<li class="step-num active">1<span class="tips">输入话题</span></li>
						<li class="step-bar active"></li>
						<li class="step-num active">2<span class="tips">补充信息</span></li>
						<li class="step-bar active"></li>
						<li class="step-num active">3<span class="tips">发布成功</span></li>
					  </ul>
					</div>
					<div class="hd">
					  <div class="hd-wrap">
						<div class='congrats'>
						     恭喜！话题分享成功!
						</div>
						<div class='addtreasure'>
						              系统自动给您加上<span class="success-upload-tips">1财富值</span>的奖励
						      <a href="/myCenter/getMyTopicsUploaded" class='checkshare'>&nbsp;&nbsp;查看已分享的话题>></a>
						 </div>
						 <a class="goon-upload log-xsend"  href="/sharein/searchuploadFile?type=1">继续分享</a>
					  </div>
					</div>
					
					<div class="successupload-notice">
                    <h4>
                      温馨提示
                    </h4>
                    <ol>
                      <li>
                        1.当你的话题分享成功后,系统会为你添加1财富的奖励,累计到一定的财富值即可兑换;
                      </li>
                      <li>
                        2.当您分享的话题评论达到一定的数量后将不能被编辑和删除;
                      </li>
                    </ol>
                  </div>
				  
				  </div>
				</div>
			  </div>
			</div>
		  </div>
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
     <#include "/mydocs/commonTemplate/successuploadjs/successuploadjs.ftl"/> 
  </body>

</html>