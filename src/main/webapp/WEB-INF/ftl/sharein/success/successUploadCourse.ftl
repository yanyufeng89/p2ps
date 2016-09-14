<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
            课程分享
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
  </head>
  <body>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
		  <div class='bookdetail'>JobPlus知识库>课程分享</div>
		  <div class="upload-course"></div>
		  <div class="bd-wrap">
			<div class="body">
			  <div class="main">
				<div id="upload-widget" class="upload-widget ">
				  <div id="upload-success-container" class="upload-success-container" style="display: block;">
					<div class="upload-steps clearfix">
					  <ul>
						<li class="step-num active">1
						  <span class="tips">输入链接</span></li>
						<li class="step-bar active"></li>
						<li class="step-num active">2
						  <span class="tips">补充信息</span></li>
						<li class="step-bar active"></li>
						<li class="step-num active">3
						  <span class="tips">分享成功</span></li>
					  </ul>
					</div>
					<div class="hd">
					  <div class="hd-wrap">
						<div class='congrats'>
						     恭喜！课程分享成功!
						</div>
						<div class='addtreasure'>
						              系统自动给您加上<span class="success-upload-tips">1财富值</span>的奖励
						 </div>
						 <a href="/51jobplusCore/myCenter/getSharedCourseList" class='checkshare'>查看已分享的课程>></a>
						<a class="goon-upload log-xsend"  href="/51jobplusCore/sharein/searchuploadFile?type=3">继续分享</a>
					  </div>
					</div>
					
					<div class="successupload-notice">
                    <h4>
                      温馨提示
                    </h4>
                    <ol>
                      <li>
                        1.如果你发现一节好的教程,可以推荐给更多的朋友,让更多的人一起学习;
                      </li>
                      <li>
                        2.分享和已分享的课程重复将会被移除,为避免重复,分享前可想进行搜索;
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