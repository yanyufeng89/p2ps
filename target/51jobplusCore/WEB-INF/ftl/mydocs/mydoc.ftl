<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          个人中心
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
		<div class="img-center">
		 <#if (Session.user.headicon)??>
		    <img src="${Session.user.headicon}" width='80' height='80'>
		  <#else>
		    <img src="/image/myphoto.jpg" width='80' height='80'>
		 </#if>
		</div>
		<p class="user-name">
			<a class="name-link" href="/myCenter/getMyHeadTop" target="_self">
			   <#if (Session.user)??>
		           ${Session.user.username}
		        </#if>
			</a>
		</p>
		<p class="user-level"><a href="/myHome/getHomePage?userid=${Session.user.userid}" target="_blank">&nbsp;进入个人主页</a></p>
		<div class="mydoc-list">
		<ul>
		    <ul id="accordion">
		      <li>
		         <div class="my-doc link">
			         <p>
			           <a class="current">
			             <span class="icon-ken"></span>
			                                 我的知识库
			            </a>
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
		<li><a href="#" target="_blank"><span class="s-ic guide"></span>反馈</a></li>
		<li class="gotop"><a href="javascript:void(0);"><span class="s-ic top"></span></a></li>
		</ul>
		</div>
		</div>
		<div class="uc-main">
			<div class="uc-head" style="padding:0;width:950px;">
				<#include "/mydocs/commonTemplate/confansinfo/confansinfoshare.ftl"/>
			</div>
			<div class="uc-mydoc  maincontent">
			  <div class="mydoccontent">
			      <div class="mylibrary">我的知识库</div>
			     
			      <div class="category">
			          <ul> 
			             <li class="first">
			               <span>文档 </span>
			               <div>
			                 <p>
			                   <a>
			                     <span class="count"><#if (operationSum.docsharesum)??>${operationSum.docsharesum}<#else>0</#if></span>
			                     <span class="count-desp">上传</span>
			                   </a>
			                 </p>
			                 <p>
			                  <a>
			                     <span class="count"><#if (operationSum.docdownsum)??>${operationSum.docdownsum}<#else>0</#if></span>
			                     <span class="count-desp">下载</span>
			                   </a>
			                 </p>
			                 <p>
			                   <a>
			                     <span class="count"><#if (operationSum.doccollsum)??>${operationSum.doccollsum}<#else>0</#if></span>
			                     <span class="count-desp">收藏</span>
			                   </a>
			                 </p>
			               </div>
	                     </li>
			             <li>
			               <span>话题</span>
			                <div>
			                 <p>
			                   <a>
			                     <span class="count"><#if (operationSum.topicssharesum)??>${operationSum.topicssharesum}<#else>0</#if></span>
			                     <span class="count-desp">分享</span>
			                   </a>
			                 </p>
			                 <p>
			                  <a>
			                     <span class="count"><#if (operationSum.topicsattesum)??>${operationSum.topicsattesum}<#else>0</#if></span>
			                     <span class="count-desp">关注</span>
			                   </a>
			                 </p>
			                 <p>
			                   <a>
			                     <span class="count"><#if (operationSum.topicscomsum)??>${operationSum.topicscomsum}<#else>0</#if></span>
			                     <span class="count-desp">回复</span>
			                   </a>
			                 </p>
			               </div>
                     </li>
		             <li>
		               <span>书籍</span>
		               <div>
		                 <p class="count-p">
		                   <a>
		                     <span class="count"><#if (operationSum.booksharesum)??>${operationSum.booksharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		                   </a>
		                 </p>
		                  <p class="count-p">
			                   <a>
			                     <span class="count"><#if (operationSum.bookcollsum)??>${operationSum.bookcollsum}<#else>0</#if></span>
			                     <span class="count-desp">收藏</span>
			                   </a>
			              </p>
		               </div>
                     </li>
		             <li>
		              <span>课程</span>
		               <div>
		                 <p class="count-p">
		                   <a>
		                     <span class="count"><#if (operationSum.coursessharesum)??>${operationSum.coursessharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		                   </a>
		                 </p>
		                  <p class="count-p">
			                   <a>
			                     <span class="count"><#if (operationSum.coursescollsum)??>${operationSum.coursescollsum}<#else>0</#if></span>
			                     <span class="count-desp">收藏</span>
			                   </a>
			               </p>
		               </div>
		             </li>
		             <li>
		               <span>文章 </span>
		               <div>
		                 <p class="count-p">
		                   <a>
		                     <span class="count"><#if (operationSum.articlesharesum)??>${operationSum.articlesharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		                   </a>
		                 </p>
		                  <p class="count-p">
			                   <a>
			                     <span class="count"><#if (operationSum.articlecollsum)??>${operationSum.articlecollsum}<#else>0</#if></span>
			                     <span class="count-desp">收藏</span>
			                   </a>
			               </p>
		               </div>
                     </li>
		             <li>
		               <span>站点 </span>
		               <div>
		                 <p class="count-p">
		                   <a>
		                     <span class="count"><#if (operationSum.sitessharesum)??>${operationSum.sitessharesum}<#else>0</#if></span>
		                     <span class="count-desp">分享</span>
		                   </a>
		                 </p>
		                  <p class="count-p">
			                   <a>
			                     <span class="count"><#if (operationSum.sitescollsum)??>${operationSum.sitescollsum}<#else>0</#if></span>
			                     <span class="count-desp">收藏</span>
			                   </a>
			              </p>
		               </div>
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