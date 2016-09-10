<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          个人中心_聘十知识库
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
		<div class="img-center">
		<div>
		    <#if (Session.user.headicon)??>
		    <img src="${Session.user.headicon}">
		  <#else>
		    <img src="/51jobplusCore/image/myphoto.jpg">
		  </#if>
		</div>
		</div>
		<p class="user-name">
			<a class="name-link" href="/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}" target="_blank"> 
	          <#if (user.username)??>
	           ${user.username}
	         </#if>
	        </a>
		</p>
		<p class="user-level"><a href="/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}" target="_blank">&nbsp;进入个人主页</a></p>
		<div class="mydoc-list">
		<ul>
		    <ul id="accordion">
		      <li>
		         <div class="my-doc link"><p>
		           <a href='/51jobplusCore/myCenter/getMyHeadTop'>
		             <span class="icon-ken"></span>我的知识库
		           </a>
		             <b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li id="mydocument"><a href="/51jobplusCore/myCenter/getMyDocsUploaded"><span class="icon-doc"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/51jobplusCore/myCenter/getMyTopicsUploaded"><span class="icon-topic"></span>我的话题</a></li>
	                 <li id="mybook"><a href="/51jobplusCore/myCenter/getSharedBookList"><span class="icon-book"></span>我的书籍</a></li>
	                 <li id="mycourse"><a href="/51jobplusCore/myCenter/getSharedCourseList"><span class="icon-course"></span>我的课程</a></li>
	                 <li id="myarticle"><a href="/51jobplusCore/myCenter/getSharedArticleList"><span class="icon-article"></span>我的文章</a></li>
	                 <li id="mysite"><a href="/51jobplusCore/myCenter/getSharedSiteList"><span class="icon-site"></span>我的站点</a></li>
		          </ul>
		      </li>
		      <li>
		         <div class="my-doc link"><p><span class="icon-account"></span>我的账户<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/51jobplusCore/myCenter/getAllSms?islook=0"><span class="icon-info"></span>全部消息</a></li>
	                 <li><a href="/51jobplusCore/myCenter/getSmsFilterParm"><span class="icon-setinfo"></span>消息设置</a></li>
	                 <li><a href="/51jobplusCore/myCenter/account/security"><span class="icon-accountsafety"></span>账户安全</a></li>
		          </ul>
		       </li>
		       <li>
		         <div class="my-order link"><p><span class="icon-order"></span>我的订单<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/51jobplusCore/user/allorder"><span class="icon-allorder"></span>全部订单</a></li>
	                 <li><a href="/51jobplusCore/user/cash"><span class="icon-cash"></span>已经支出</a></li>
	                 <li><a href="/51jobplusCore/user/nocash"><span class="icon-nocash"></span>尚未支付</a></li>
		          </ul>
		      </li>
		       <li class="open">
		         <div class="my-order link"><p><span class="icon-wealth"></span>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                <li><a href="/51jobplusCore/account/getDetailListByRecord?changetype=1" class="current"><span class="icon-incomewealth"></span>财富收益</a></li>
	                <li><a href="/51jobplusCore/account/getDetailListByRecord?changetype=2"><span class="icon-wealthspending"></span>财富支出</a></li>
		          </ul>
		      </li>
		    </ul>
		</ul>
		</div>
		</div>
		
		</div>
		<div class="uc-main">
		  <div class="uc-head" style="padding:0;width:950px;">
		    <#include "/mydocs/commonTemplate/confansinfo/confansinfo.ftl"/>
		 </div>
		 <div class='fortunecontent maincontent'>
		    <div class="uc-head-bottom">
			  <div style="float:left">
				<span><a class="icon-myfortunecome"></a></span>
				<div style="float:left">
					<p>我的财富值:${Session.account.points}</p>
				</div>
			   </div>
		   </div>
		   <div class='docs-list-box'>
		     <div class='status-box'>
			   <div class='w335 ib'>财富收益</div>
			   <div class='w335 ib'>收益数</div>
			   <div class='w204 ib'>时间</div>
			 </div>
			 <div class='docs-list'>
			   <ul>
			     <#if (actDPage)??>
				   <#list actDPage.list as list>
					 <li>
					   <div class='w335 ib ilfortune'>${list.changecause}</div>
					   <div class='w335 ib ilfortune'>${list.changevalue}财富值</div>
					   <div class='w204 ib ilfortune'>${list.createtime?string("yyyy-MM-dd HH:mm:ss")}</div>
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
							<div id="fortunecome" class="page"></div>
							
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
	  </div>
	  <div class='pagetemplate'></div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
      <script type='text/javascript' src='/51jobplusCore/scripts/pj_mycenterinfosettings.js'></script>
      <script type="text/javascript">
	  <#if (actDPage.list)??>
		$(function(){
			$("#fortunecome").pagination({
				items: ${actDPage.count},
				itemsOnPage:${actDPage.pageSize},
				cssStyle: 'light-theme',
				moduleType:'fortunecome'
			});
		});
	  </#if>
	</script>
  </body>

</html>