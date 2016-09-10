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
		         <ul class="submenu" style="display:block">
	                 <li><a href="/51jobplusCore/myCenter/getAllSms?islook=0"><span class="icon-info"></span>全部消息</a></li>
	                 <li><a href="/51jobplusCore/myCenter/getSmsFilterParm" class="current"><span class="icon-setinfo"></span>消息设置</a></li>
	                 <li><a href="/51jobplusCore/myCenter/account/security"><span class="icon-accountsafety"></span>账户安全</a></li>
		          </ul>
		       </li>
		       <li class="open">
		         <div class="my-order link"><p><span class="icon-order"></span>我的订单<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/51jobplusCore/user/allorder"><span class="icon-allorder"></span>全部订单</a></li>
	                 <li><a href="/51jobplusCore/user/cash"><span class="icon-cash"></span>已经支出</a></li>
	                 <li><a href="/51jobplusCore/user/nocash"><span class="icon-nocash"></span>尚未支付</a></li>
		          </ul>
		      </li>
		       <li>
		         <div class="my-order link"><p><span class="icon-wealth"></span>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	               <li><a href="/51jobplusCore/account/getDetailListByRecord?changetype=1"><span class="icon-incomewealth"></span>财富收益</a></li>
	               <li><a href="/51jobplusCore/account/getDetailListByRecord?changetype=2"><span class="icon-wealthspending"></span>财富支出</a></li>
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
		  <#include "/mydocs/commonTemplate/confansinfo/confansinfo.ftl"/>
		
		   <div class="maincontent" id="allorders">
			<div class="uc-news">
			   <div class="console-box">
			      <p>消息设置</p>
	           </div>
	           <div id="settingscontent">
	             <#if (smsFilterMap)??>
	               <#list smsFilterMap?keys as itemKey>
	                  <#if itemKey="ntsSmsF">
	                    <#assign ntsSmsF=smsFilterMap[itemKey]/>
	                  </#if>
	                  <#if itemKey="prvtSmsF">
	                     <#assign prvSmsF=smsFilterMap[itemKey]/>
	                 </#if>
	               </#list>

	             </#if>
		         <div data-type="infosettings_voteup" class="settings-item settings-item-solidline clearfix" >
					  <h3 class="zg-left settings-item-left">私信设置</h3>
					  <div class="zg-left settings-item-right">
					    <div class="row settings-receive">
					      <label  <#if (prvSmsF)??><#if prvSmsF==1>data-ischecked='1'<#else> data-ischecked='0'</#if></#if>>
					        <input type="radio" name="answer-voteup-receive" value="all" <#if (prvSmsF)??><#if prvSmsF==1>checked=""</#if></#if> data-filteritem='1' data-filterlevel='1'>允许任何人给我发私信</label>
					      <label <#if (prvSmsF)??><#if prvSmsF==0>data-ischecked='1'<#else> data-ischecked='0'</#if></#if>>
					        <input type="radio" name="answer-voteup-receive" value="follow" <#if (prvSmsF)??><#if prvSmsF==0>checked=""</#if></#if> data-filteritem='1' data-filterlevel='0'>只允许我关注的人给我发私信</label>
					      <label <#if (prvSmsF)??><#if prvSmsF==-1>data-ischecked='1'<#else> data-ischecked='0'</#if></#if>>
					        <input type="radio" name="answer-voteup-receive" value="no" <#if (prvSmsF)??><#if prvSmsF==-1>checked=""</#if></#if> data-filteritem='1' data-filterlevel='-1'>禁止任何人给我发私信</label>
					     </div>
					  </div>
				 </div>
				 <div data-type="infotips_voteup" class="settings-item settings-item-solidline clearfix">
					  <h3 class="zg-left settings-item-left">消息提醒</h3>
					  <div class="zg-left settings-item-right">
					    <div class="row settings-receive">
					      <label <#if (ntsSmsF)??><#if ntsSmsF==1>data-ischecked='1'<#else> data-ischecked='0'</#if></#if>>
					        <input type="radio" name="nfotips-voteup-receive" value="all" <#if (ntsSmsF)??><#if ntsSmsF==1>checked=""</#if></#if>  data-filteritem='0' data-filterlevel='1'>接收所有消息提醒</label>
					      <label <#if (ntsSmsF)??><#if ntsSmsF==0>data-ischecked='1'<#else> data-ischecked='0'</#if></#if>>
					        <input type="radio" name="nfotips-voteup-receive" value="follow" <#if (ntsSmsF)??><#if ntsSmsF==0>checked=""</#if></#if>  data-filteritem='0' data-filterlevel='0'>只接收我关注人的消息提醒</label>
					      <label <#if (ntsSmsF)??><#if ntsSmsF==-1>data-ischecked='1'<#else> data-ischecked='0'</#if></#if>>
					        <input type="radio" name="nfotips-voteup-receive" value="no"  <#if (ntsSmsF)??><#if ntsSmsF==-1>checked=""</#if></#if> data-filteritem='0' data-filterlevel='-1'>不接收任何消息提醒</label>
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
	  </div>
	  <div class='pagetemplate'></div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
      <script type='text/javascript' src='/51jobplusCore/scripts/pj_mycenterinfosettings.js'></script>
  </body>

</html>