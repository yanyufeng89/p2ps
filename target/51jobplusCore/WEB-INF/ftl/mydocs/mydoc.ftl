<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          ${Session.user.username}-个人中心-JobPlus
    </title> 
   <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
  </head>
  <body>
    <div id="doc" class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div id="bd">
		<div class="bd-wrap">
		<div class="uc-aside">
		<div class="uc-user-box">
		<div class="<#if Session.user.usertype==2>img-business-center<#else>img-center</#if>">
		<a  href="<#if Session.user.usertype==2>/comp/getHomePage/${Session.user.userid}?isReview=0<#else>/myCenter/getMyHeadTop</#if>" target="_self">
		 <#if (Session.user.headicon)?? && Session.user.headicon?length gt 0>
		    <img src="${Session.user.headicon}" width='100' height='100' alt="个人头像" class='lazy'>
		  <#else>
		    <img src="/image/myphoto.jpg" width='100' height='100' alt="个人头像" class='lazy'>
		 </#if>
		</a>
		</div>
		<p class="user-name">
			<a class="name-link" href="<#if Session.user.usertype==2>/comp/getHomePage/${Session.user.userid}?isReview=0<#else>/myCenter/getMyHeadTop</#if>" target="_self">
			   <#if (Session.user)??>
		           ${Session.user.username}
		        </#if>
			</a>
			<#if Session.user.usertype==2>
		   <#else>
             <span class="pj-level"><em>LV.${Session.user.userlevel}</em></span>
		   </#if> 
		</p>
		<p class="user-level">
		  <#if Session.user.usertype==2>
		    <a href="/comp/getHomePage/${Session.user.userid}?isReview=0" target="_blank">&nbsp;进入公司主页</a> 
		  <#else>
		    <a href="/myHome/getHomePage/${Session.user.userid}?isReview=0" target="_blank">&nbsp;进入个人主页</a>
		  </#if>
		</p>
		<div class="mydoc-list">
		<ul>
		    <ul id="accordion">
		      <li>
		         <div class="my-doc link">
			         <p>
			           <a class="current"><span class="icon-ken"></span>我的知识库</a>
			           <b class="iconfont up-arrow doc-arrow-btn"></b>
			         </p>
		         </div>
		         <ul class="submenu">
	                 <li id="mydocument"><a href="/myCenter/getMyDocsUploaded"><span class="icon-doc"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/myCenter/getMyTopicsUploaded"><span class="icon-topic"></span>我的话题</a></li>
	                 <li id="mybook"><a href="/myCenter/getSharedBookList"><span class="icon-book"></span>我的书籍</a></li>
	                 <li id="mycourse"><a href="/myCenter/getSharedCourseList"><span class="icon-course"></span>我的课程</a></li>
	                 <li id="myarticle"><a href="/myCenter/getSharedArticleList"><span class="icon-article"></span>我的文章</a></li>
	                 <li id="mysite"><a href="/myCenter/getSharedSiteList"><span class="icon-site"></span>我的站点</a></li>
		          </ul>
		      </li>
		      <li>
		         <div class="my-doc link">
		            <p>
		               <span class="icon-account"></span>
		                                       我的账户
		               <b class="iconfont up-arrow doc-arrow-btn"></b>
		             </p>
		          </div>
		         <ul class="submenu">
	                 <li><a href="/myCenter/getAllSms?islook=0"><span class="icon-info"></span>全部消息</a></li>
	                 <li><a href="/myCenter/getSmsFilterParm"><span class="icon-setinfo"></span>消息设置</a></li>
	                 <li><a href="/myCenter/account/security"><span class="icon-accountsafety"></span>账户安全</a></li>
		          </ul>
		       </li>
		       <li>
		         <div class="my-order link">
		            <p>
		               <span class="icon-order"></span>
		                                              我的订单
		               <b class="iconfont up-arrow doc-arrow-btn"></b>
		            </p>
		         </div>
		         <ul class="submenu">
	                 <li><a href="/user/allorder"><span class="icon-allorder"></span>全部订单</a></li>
	                 <li><a href="/user/cash"><span class="icon-cash"></span>已经支出</a></li>
	                 <li><a href="/user/nocash"><span class="icon-nocash"></span>尚未支付</a></li>
		          </ul>
		      </li>
		       <li>
		         <div class="my-order link">
		            <p>
		               <span class="icon-wealth"></span>
		                                            我的财富
		               <b class="iconfont up-arrow doc-arrow-btn"></b>
		             </p>
		         </div>
		         <ul class="submenu">
	                 <li><a href="/account/getDetailListByRecord?changetype=1"><span class="icon-incomewealth"></span>财富收益</a></li>
	                 <li><a href="/account/getDetailListByRecord?changetype=2"><span class="icon-wealthspending"></span>财富支出</a></li>
		          </ul>
		      </li>
		    </ul>
		</ul>
		</div>
		</div>
		<div class="side-nav-bar" id="side-nav-bar" style="display: none; left: 1461.5px; position: fixed; top: 650px;">
		<ul>
		<li><a href="#" target="_self"><span class="s-ic guide"></span>反馈</a></li>
		<li class="gotop"><a href="javascript:void(0);"><span class="s-ic top"></span></a></li>
		</ul>
		</div>
		</div>
		<div class="uc-main">
			<div class="uc-head" style="padding:0;width:950px;">
				<#include "/mydocs/commonTemplate/confansinfo/confansinfoshare.ftl"/>
			</div>
			<div class="uc-mydoc  maincontent">
			  <div class="mylibrary">
			          我的知识库
			    <a href='/sharein/searchuploadFile' class='head-sharein' style='float:right;margin-right:4px;'></a>
			  </div>

			  <div class='datacount'>
			    <p>
			       <a href='javascript:void(0)'>财富&nbsp;${Session.account.points}</a>
			       <#assign sum=operationSum.docsharesum?number+operationSum.topicssharesum?number+operationSum.booksharesum?number+operationSum.coursessharesum?number+operationSum.articlesharesum?number+operationSum.sitessharesum?number>
			       <a href='javascript:void(0)'>分享&nbsp;${sum}</a>
			    </p>
			  </div>
			  <div class="mydoccontent" style='margin-top:20px;'>
			      <div class="category">
			          <ul> 
			             <li class="first">
			              <a href='/myCenter/getMyDocsUploaded'>
			               <span class='title'>文档 </span>
						   <p>
							 <span class="count"><#if (operationSum.docsharesum)??>${operationSum.docsharesum}<#else>0</#if></span>
							 <span class="count-desp">上传</span>
						   </p>
						   <p>
							 <span class="count"><#if (operationSum.docdownsum)??>${operationSum.docdownsum}<#else>0</#if></span>
							 <span class="count-desp">下载</span>
						   </p>
						   <p>
							 <span class="count"><#if (operationSum.doccollsum)??>${operationSum.doccollsum}<#else>0</#if></span>
							 <span class="count-desp">收藏</span>
						   </p>
			              </a>
	                     </li>
			             <li>
			              <a href='/myCenter/getMyTopicsUploaded'>
							   <span class='title'>话题</span>
							   <p>
									 <span class="count"><#if (operationSum.topicssharesum)??>${operationSum.topicssharesum}<#else>0</#if></span>
									 <span class="count-desp">分享</span>
							   </p>
							   <p>
									 <span class="count"><#if (operationSum.topicsattesum)??>${operationSum.topicsattesum}<#else>0</#if></span>
									 <span class="count-desp">关注</span>
							   </p>
							   <p>
									 <span class="count"><#if (operationSum.topicscomsum)??>${operationSum.topicscomsum}<#else>0</#if></span>
									 <span class="count-desp">回复</span>
							   </p>
			               </a>
                     </li>
		             <li>
		              <a href='/myCenter/getSharedBookList'>
		               <span class='title'>书籍</span>
		                 <p class="count-p">
		                     <span class="count"><#if (operationSum.booksharesum)??>${operationSum.booksharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		                 </p>
		                  <p class="count-p">
			                  <span class="count"><#if (operationSum.bookcollsum)??>${operationSum.bookcollsum}<#else>0</#if></span>
			                  <span class="count-desp">收藏</span>
			              </p>
		               </a>
                     </li>
		             <li>
		             <a href='/myCenter/getSharedCourseList'>
		                 <span class='title'>课程</span>
		                 <p class="count-p">
		                     <span class="count"><#if (operationSum.coursessharesum)??>${operationSum.coursessharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		                 </p>
		                 <p class="count-p">
							 <span class="count"><#if (operationSum.coursescollsum)??>${operationSum.coursescollsum}<#else>0</#if></span>
							 <span class="count-desp">收藏</span>
			              </p>
		               </a>
		             </li>
		             <li>
		              <a href='/myCenter/getSharedArticleList'>
		               <span class='title'>文章 </span>
		               <p class="count-p">
		                     <span class="count"><#if (operationSum.articlesharesum)??>${operationSum.articlesharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		               </p>
		               <p class="count-p">
							 <span class="count"><#if (operationSum.articlecollsum)??>${operationSum.articlecollsum}<#else>0</#if></span>
							 <span class="count-desp">收藏</span>
			           </p>
		               </a>
                     </li>
		             <li>
		              <a href='/myCenter/getSharedSiteList'>
		               <span class='title'>站点 </span>
		               <div>
		                 <p class="count-p">
		                     <span class="count"><#if (operationSum.sitessharesum)??>${operationSum.sitessharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		                 </p>
		                  <p class="count-p">
							 <span class="count"><#if (operationSum.sitescollsum)??>${operationSum.sitescollsum}<#else>0</#if></span>
							 <span class="count-desp">收藏</span>
			              </p>
		               </div>
		               </a>
		              </li>
		          </ul>
		      </div>
		  </div>
		  
		</div>
		</div>
		</div>
	  </div>
	  <div class='pagetemplate'></div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
  </body>

</html>