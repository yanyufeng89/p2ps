<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          个人中心_JobPlus知识库
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
		 <a class="name-link" href="/myCenter/getMyHeadTop" target="_self">
		  <#if (Session.user.headicon)?? && Session.user.headicon?length gt 0>
		    <img src="${Session.user.headicon}" width='80' height='80'>
		  <#else>
		    <img src="/image/myphoto.jpg" width='80' height='80'>
		  </#if>
		  </a>
		</div>
		<p class="user-name">
			<a class="name-link" href="/myCenter/getMyHeadTop" target="_self">
	          <#if (user.username)??>
	           ${user.username}
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
		           <a href='/myCenter/getMyHeadTop'>
		             <span class="icon-ken"></span>我的知识库
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
		         <div class="my-doc link"><p><span class="icon-account"></span>我的账户<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/myCenter/getAllSms?islook=0"><span class="icon-info"></span>全部消息</a></li>
	                 <li><a href="/myCenter/getSmsFilterParm"><span class="icon-setinfo"></span>消息设置</a></li>
	                 <li><a href="/myCenter/account/security"><span class="icon-accountsafety"></span>账户安全</a></li>
		          </ul>
		       </li>
		       <li>
		         <div class="my-order link"><p><span class="icon-order"></span>我的订单<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/user/allorder"><span class="icon-allorder"></span>全部订单</a></li>
	                 <li><a href="/user/cash"><span class="icon-cash"></span>已经支出</a></li>
	                 <li><a href="/user/nocash"><span class="icon-nocash"></span>尚未支付</a></li>
		          </ul>
		      </li>
		       <li class="open">
		         <div class="my-order link"><p><span class="icon-wealth"></span>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                <li><a href="/account/getDetailListByRecord?changetype=1"><span class="icon-incomewealth"></span>财富收益</a></li>
	                <li><a href="/account/getDetailListByRecord?changetype=2" class="current"><span class="icon-wealthspending"></span>财富支出</a></li>
		          </ul>
		      </li>
		    </ul>
		</ul>
		</div>
		</div>
		
		</div>
		<div class="uc-main">
		  <div class="uc-head" style="padding:0;width:950px;">
		    <#include "/mydocs/commonTemplate/confansinfo/confansinfoshare.ftl"/>
		 </div>
		 <div class='fortunecontent maincontent'>
		   <div class="uc-head-bottom">
			  <div style="float:left">
				<span><a class="icon-myfortuneexpend"></a></span>
				<div style="float:left">
					<p>我的财富值:&nbsp;${Session.account.points}</p>
				</div>
			   </div>
		     </div>
		   <div class='docs-list-box'>
		     <div class='status-box'>
			   <div class='w366 ib'>财富支出</div>
			   <div class='w366 ib'>消耗量</div>
			   <div class='w140 ib'>时间</div>
			 </div>
			 <div class='docs-list'>
			   <ul>
			     <#if (actDPage)??>
				   <#list actDPage.list as list>
					 <li>
					   <div class='w366 ib ilfortune'>${list.changecause}</div>
					   <div class='w366 ib ilfortune'>${list.changevalue}财富值</div>
					   <div class='w140 ib ilfortune'>${list.createtime?string("yyyy-MM-dd HH:mm:ss")}</div>
					 </li>
				  </#list>
				 </#if>
			   </ul>
			 </div>
		   </div>
		   <#if (actDPage.last)??>
				 <#if (actDPage.last gt 1)>
					<div class='page-inner'>
					  <div class="ui-pager pager-center">
						<div class='pager'>
						 
						  <div class="pager-inner">
							<div id="fortuneexpend" class="page"></div>
							
						  </div>
						
						</div>
					  </div>
					</div>
				 </#if>
			</#if>
		 </div>
      	 </div>

		</div>
		</div>
	  </div>
	  <div class='pagetemplate'></div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
      <script type='text/javascript' src='/scripts/pj_mycenterinfosettings.js'></script>
      <script type="text/javascript">
	  <#if (actDPage.list)??>
		$(function(){
			$("#fortuneexpend").pagination({
				items: ${actDPage.count},
				itemsOnPage:${actDPage.pageSize},
				cssStyle: 'light-theme',
				moduleType:'fortuneexpand'
			});
		});
	  </#if>
	</script>
  </body>

</html>