<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
         分享书籍-JobPlus
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
  </head>
  <body>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
		 <div class='bookdetail'>JobPlus知识库>分享书籍</div>
         <div class="upload-book"></div>
		  <div class="bd-wrap">
			<div class="body">
			  <div class="main">
				<div id="upload-widget" class="upload-widget ">
				  <div id="upload-success-container" class="upload-success-container" style="display: block;">
					<div class="upload-steps clearfix">
					  <ul>
						<li class="step-num active">1<span class="tips">输入书名</span></li>
						<li class="step-bar active"></li>
						<li class="step-num active">2<span class="tips">补充信息</span></li>
						<li class="step-bar active"></li>
						<li class="step-num active">3<span class="tips">推荐成功</span></li>
					  </ul>
					</div>
					<div class="hd">
					  <div class="hd-wrap">
				<#if num gt 0>
						<div class='congrats'>
							恭喜！书籍分享成功!
						</div>
						<div class='addtreasure'>
						              系统自动给您加上<span class="success-upload-tips">${num}财富值</span>的奖励
						      <a href="/myCenter/getSharedBookList" class='checkshare'>&nbsp;&nbsp;查看已分享的书籍>></a>
						 </div>
				<#else>
						<div class='congrats'>
							这本书您已经分享过啦！
						</div>
						<div class='addtreasure'>
						      <a href="/myCenter/getSharedBookList" class='checkshare'>&nbsp;&nbsp;查看已分享的书籍>></a>
						 </div>
				</#if>						
						 <a class="goon-upload log-xsend"  href="/sharein/searchuploadFile?type=2">继续分享</a>
					  </div>
					</div>
					
					<div class="successupload-notice">
                    <h4>
                      温馨提示
                    </h4>
                    <ol>
                      <li>
                        1. 当您的书籍分享成功后，系统会为你添加1财富的奖励，累计到一定的财富值即可兑换；
                      </li>
                      <li>
                        2. 内容优秀的书籍，将为您带来更多的关注与收益；
                      </li>
                      <li>
                        3. 书籍推荐有问题需要帮助？详情请查看知识库
                        <a href="/about/terms_service" target="_self">服务条款</a>
                        和
                        <a href="/about/newbie_guide" target="_self">帮助中心。</a>
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