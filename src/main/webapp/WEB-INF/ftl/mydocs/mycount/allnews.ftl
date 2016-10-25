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
		      <li class="open">
		         <div class="my-doc link"><p><span class="icon-account"></span>我的账户<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                 <li><a href="/myCenter/getAllSms?islook=0" class="current"><span class="icon-info"></span>全部消息</a></li>
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
		       <li>
		         <div class="my-order link"><p><span class="icon-wealth"></span>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
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
		   <div id="allnews" class="maincontent">
			   <div class="uc-head-bottom">
					<span><a class="icon-myallnews"></a></span>
					<div style="float:left">
						<p>全部消息</p>
					</div>
		       </div>
	           <div class="notice-count">
	               <a style='padding-left:12px;' class='notice-sms <#if (pageFlag)??> <#if pageFlag==0>current</#if></#if>' href='/myCenter/getAllSms?islook=0'>未读通知：
	                  <b class="unread_count">
	                   <#list Session.myHeadTop?keys as itemKey>
					     <#if itemKey="privateSmsSum">
					        <#assign privateSmsSum=Session.myHeadTop[itemKey] />
					     </#if>
					     <#if itemKey="newSmsSum">
					        <#assign newSmsSum=Session.myHeadTop[itemKey] />
					     </#if>
			 		   </#list> 
			 		   <#assign sum=privateSmsSum?number+newSmsSum?number />
			 		     ${sum}
	                  </b>
	               </a>
	               <a class='notice-sms <#if (pageFlag)??><#if pageFlag==1>current</#if></#if>' href='/myCenter/getAllSms'>全部消息：
	                 <b class="read_count"> 
	                    <#list Session.myHeadTop?keys as itemKey>
					     <#if itemKey="smsSum">
					        ${Session.myHeadTop[itemKey]}
					     </#if>
			 		   </#list> 
                     </b>
                   </a>
                   <a class="mark_read">全部标记为已读</a>
				   <a class="empty-allnews">清空所有通知</a>
	           </div>
	           <div class='myAllMessage'>
	             <#if (pageFlag)??>
	               <input type='hidden' value='${pageFlag}' name='currenttype'>
	             </#if>
	             <#if (allSmsPage.list)??>
	              <ul>
	                <#list allSmsPage.list as list>
	                  <#if list.islook==1>
	                    <li data-id='${list.id}'  data-islook='1'>
	                  <#else>
	                    <li data-id='${list.id}'  data-islook='0'>
	                  </#if>
	                     <div class='author-list'>
	                       <#if list.smstype==11 || list.smstype==12 || list.smstype==13 || list.smstype==14 || list.smstype==21 || list.smstype==22 || list.smstype==31 || list.smstype==32 ||list.smstype==33 || list.smstype==41 ||list.smstype==42 ||list.smstype==43 || list.smstype==51 || list.smstype==52 || list.smstype==53 || list.smstype==61 || list.smstype==62 ||list.smstype==63>
	                         <span class='sms-icon-1'></span>
	                       <#elseif list.smstype==1>
	                         <span class='sms-icon-3'></span>
	                       <#elseif list.smstype==0> 
	                         <span class='sms-icon-0'></span>
	                       <#else>
	                         <span class='sms-icon-2'></span>
	                       </#if>
	                        <a href='/myHome/getHomePage?userid=${list.senderid}' target='_blank'>${list.sendUserName}:</a>
	                        <#if list.islook==1>
	                          <span class='allnewscontent' style='color:#999'>${list.smscontent}</span>
	                        <#else>
	                          <span class='allnewscontent'>${list.smscontent}</span>
	                        </#if>
	                      </div>
	                      
	                      <div class='cancelorreply pj-pm-item-meta'>
	                            <span class='smsdate zg-gray zg-left'>${list.sendtime?string("yyyy-MM-dd HH:mm:ss")}</span>
	                            <span class='zg-cancelnews zg-right' data-id='${list.id}' data-type='${pageFlag}'><b class="iconfont pr2"></b>&nbsp;删除</span>
	                          <#if list.smstype==1>
	                            <span class="zg-bull zg-right">|</span>
	                            <span class='zg-link-replypm zg-right' data-receivedid='${list.senderid}' data-name='${list.sendUserName}'>回复</span>
	                          </#if>
	                      </div>
	                   </li>
	                </#list>
	              </ul>
	             <#else>
	                <ul id="listMsg"><p class="noMsg">目前您还没有任何消息提醒，看来还不够活跃哦。</p></ul>
	             </#if>
	           </div>
            
            <#if (allSmsPage.last)??>
				 <#if (allSmsPage.last gt 1)>
				    <div class='page-inner'>
					  <div class="ui-pager pager-center">
					    <div class='pager'>
						  <div class="pager-inner">
						   <#if pageFlag==0>
						     <div id="unreadnewspaging" class="page"></div>
						   <#else>
						     <div id="readnewspaging" class="page"></div>
						   </#if>
							
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
      <script type='text/javascript' src='/scripts/pj_mycenterallnews.js'></script>
      <script type="text/javascript">
         <#if (allSmsPage.list)??>
         <#if (pageFlag)??>
          <#if pageFlag==0>
		   $(function(){
				$("#unreadnewspaging").pagination({
					items: ${allSmsPage.count},
					itemsOnPage:${allSmsPage.pageSize},
					cssStyle: 'light-theme',
					moduleType:'unreadnews'
				});
			});
		 <#else>
			  $(function(){
					$("#readnewspaging").pagination({
						items: ${allSmsPage.count},
						itemsOnPage:${allSmsPage.pageSize},
						cssStyle: 'light-theme',
						moduleType:'readnews'
					});
				});
		 </#if>
		</#if>
	  </#if>
      </script>
  </body>

</html>