<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          我的下载-个人中心-JobPlus知识库
    </title> 
    <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
  </head>
  <body>
    <div id="doc" class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div id="bd">
		<div class="bd-wrap">
		<div class="body">
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
		      <li class="open">
		         <div class="my-doc link"><p>
			            <a href='/myCenter/getMyHeadTop'>
				            <span class="icon-ken"></span>我的知识库
				        </a>
			     <b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                 <li id="mydocument"><a href="/myCenter/getMyDocsUploaded"  class="current"><span class="icon-doc"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/myCenter/getMyTopicsUploaded"><span class="icon-topic"></span>我的话题</a></li>
	                 <li id="mybook"><a href="/myCenter/getBookDetail"><span class="icon-book"></span>我的书籍</a></li>
	                 <li id="mycourse"><a href="/myCenter/getSharedCourseList"><span class="icon-course"></span>我的课程</a></li>
	                 <li id="myarticle"><a href="/myCenter/getSharedArticleList"><span class="icon-article"></span>我的文章</a></li>
	                 <li id="mysite"><a href="/myCenter/getSharedSiteList"><span class="icon-site"></span>我的站点</a></li>
		          </ul>
		      </li>
		      <li>
		         <div class="my-doc link"><p><b class="iconfont list-icon"></b>我的账户<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/myCenter/getAllSms?islook=0"><span class="icon-info"></span>全部消息</a></li>
	                 <li><a href="/myCenter/getSmsFilterParm"><span class="icon-setinfo"></span>消息设置</a></li>
	                 <li><a href="/myCenter/account/security"><span class="icon-accountsafety"></span>账户安全</a></li>
		          </ul>
		       </li>
		       <li>
		         <div class="my-order link"><p><b class="iconfont list-icon"></b>我的订单<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/user/allorder"><span class="icon-allorder"></span>全部订单</a></li>
	                 <li><a href="/user/cash"><span class="icon-cash"></span>已支出</a></li>
	                 <li><a href="/user/nocash"><span class="icon-nocash"></span>未支出</a></li>
		          </ul>
		      </li>
		       <li>
		         <div class="my-order link"><p><b class="iconfont list-icon"></b>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
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
		   <div class='maincontent'>
		     <div class="uc-head-bottom">
				 
				    <img  src="/image/document.png" width="80" height="80" alt="文档图标" class='lazy'>
				    <p>我的文档</p>
				    <p class="dll"><a>上传()</a><a class="current">下载()</a><a>收藏()</a></p>
				  
			</div>
		     <div class="uc-mydocfocus" style="display:none">
			   <div class="console-box">
		          <a class="current">已下载</a>
	           </div>
	           <div class="focus">
                 <div class="docs-list-box">
                   <div class='delete-box normal-console-box clearfix'>
		             <span class="remove"><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		           </div>
	               <div class="status-box">
					  <div class="w450 ib">文档名称</div>
					  <div class="w140 ib">下载次数</div>
					  <div class="w140 ib">下载时间</div>
					  <div class="w119 ib">操作</div>
				   </div>
				   <div class="docs-list">
				     <ul>
				       <#if (myCollectList)??>
				         <#list myCollectList as list>
				           <#if (list.actionType==0)>
				             <li>
				                <div class='w450 fs14 fc3 ib'>
					                  <div class="checkbox chk" data-docid="${list.id}" data-name="${list.docs.title}"></div>
									  <b class="ic ic-${list.docs.docsuffix} mr14"></b>
									  <a href="/myCenter/getDocsDetailForEdit" target="_blank" title="${list.docs.title}">${list.docs.title}</a>
				                </div>
				                <div class='w140 ib il'>
				                  ${list.docs.downsum}
				                </div>
				                <div class='w140 ib'>
				                  ${list.showcolltime}
				                </div>
				                <div class='w119 ib operate'>
				                   <span data-id='${list.id}' data-name='${list.title}'>
					                  <b class="iconfont pr2"></b>
					                                            删除
								   </span>
				                </div>
				             </li>
				           </#if>
				         </#list>
				       </#if>
				     </ul>
				   </div>
	             </div>
	           </div>
	     	</div>
	     	<div class="uc-mydocreply" style="display:none">
			   <div class="console-box">
					<a class="current">已收藏</a>
	           </div>
	            <div class="focus">
                 <div class="docs-list-box">
                   <div class='delete-box normal-console-box clearfix'>
		                <span class="remove"><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		           </div>
	               <div class="status-box">
					  <div class="w450 ib">文档名称</div>
					  <div class="w140 ib">收藏次数</div>
					  <div class="w140 ib">收藏时间</div>
					  <div class="w119 ib">操作</div>
				   </div>
				   <div class="docs-list">
				     <ul>
				       <#if (myCollectList)??>
				         <#list myCollectList as list>
				           <#if (list.actionType==1)>
				             <li>
				                <div class='w450 fs14 fc3 ib'>
					                  <div class="checkbox chk" data-docid="${list.id}" data-name="${list.docs.title}"></div>
									  <b class="ic ic-${list.docs.docsuffix} mr14"></b>
									  <a href="/myCenter/getDocsDetailForEdit" target="_blank" title="${list.docs.title}">${list.docs.title}</a>
				                </div>
				                <div class='w140 ib il'>
				                  ${list.docs.downsum}
				                </div>
				                <div class='w140 ib'>
				                  ${list.showcolltime}
				                </div>
				                <div class='w119 ib operate'>
				                   <span data-id='${list.id}' data-name='${list.title}'>
					                  <b class="iconfont pr2"></b>
					                                            删除
								   </span>
				                </div>
				             </li>
				           </#if>
				         </#list>
				       </#if>
				     </ul>
				   </div>
	             </div>
	           </div>
	     	</div>
	   
           </div>
      	  </div>
		</div>
		</div>
		</div>
	  </div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
     
  </body>

</html>