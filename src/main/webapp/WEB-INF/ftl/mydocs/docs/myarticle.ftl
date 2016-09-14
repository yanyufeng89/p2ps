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
		   <#if (Session.user.headicon)??>
		    <img src="${Session.user.headicon}" width='80' height='80'>
		  <#else>
		    <img src="/51jobplusCore/image/myphoto.jpg" width='80' height='80'>
		  </#if>
		</div>
		<p class="user-name">
			<a class="name-link" href="/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}" target="_blank"> 
	           <#if (Session.user)??>
		           ${Session.user.username}
		        </#if>
		    </a>
		</p>
		<p class="user-level"><a href="/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}" target="_blank">&nbsp;进入个人主页</a></p>

		<div class="mydoc-list">
		    <ul id="accordion">
		      <li class="open">
		         <div class="my-doc link"><p>
			         <a href='/51jobplusCore/myCenter/getMyHeadTop'>
				         <span class="icon-ken"></span>我的知识库
				     </a>
			         <b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                 <li id="mydocument"><a href="/51jobplusCore/myCenter/getMyDocsUploaded"><span class="icon-doc"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/51jobplusCore/myCenter/getMyTopicsUploaded"><span class="icon-topic"></span>我的话题</a></li>
	                 <li id="mybook"><a href="/51jobplusCore/myCenter/getSharedBookList"><span class="icon-book"></span>我的书籍</a></li>
	                 <li id="mycourse"><a href="/51jobplusCore/myCenter/getSharedCourseList"><span class="icon-course"></span>我的课程</a></li>
	                 <li id="myarticle"><a href="/51jobplusCore/myCenter/getSharedArticleList" class="current"><span class="icon-article"></span>我的文章</a></li>
	                 <li id="mysite"><a href="/51jobplusCore/myCenter/getSharedSiteList"><span class="icon-site"></span>我的站点</a></li>
		          </ul>
		      </li>
		      <li>
		         <div class="my-doc link"><p><span class="icon-account"></span>我的账户<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/51jobplusCore/myCenter/getAllSms?islook=0"><span class="icon-info"></span>全部消息</a></li>
	                 <li><a href="/51jobplusCore/myCenter/getSmsFilterParm">消息设置<span class="icon-setinfo"></span></a></li>
	                 <li><a href="/51jobplusCore/myCenter/account/security">账户安全<span class="icon-accountsafety"></span></a></li>
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
		       <li>
		         <div class="my-order link"><p><span class="icon-wealth"></span>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                <li><a href="/51jobplusCore/account/getDetailListByRecord?changetype=1"><span class="icon-incomewealth"></span>财富收益</a></li>
	                <li><a href="/51jobplusCore/account/getDetailListByRecord?changetype=2"><span class="icon-wealthspending"></span>财富支出</a></li>
		          </ul>
		      </li>
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
		
		   <div class='maincontent'>
		     <div class="uc-head-bottom">
				  <div style="float:left" id="mytopiccontent">
				     <span><a class='icon-myarticle'></a></span>
				     <div style="float:left">
					    <p>我的文章</p>
					    <p class="dll">
						    <a id='articleshare' href='/51jobplusCore/myCenter/getSharedArticleList'  <#if (shaArticlePage)??>class="current"</#if>>
						                 分享(<#if (Session.operationSum.articlesharesum)??>${Session.operationSum.articlesharesum}</#if>)
						    </a>
						    <a id='articlecollect' href='/51jobplusCore/myCenter/getCollectedArticleList' <#if (colArticlePage)??>class="current"</#if>>
						                收藏(<#if (Session.operationSum.articlecollsum)??>${Session.operationSum.articlecollsum}</#if>)
						    </a>
					    </p>
				    </div>
				   </div>
				</div>
		    <#if (shaArticlePage)??>
			    <div class="uc-mydocshare">
			<#else>
			    <div class="uc-mydocshare" style='display:none'>
			</#if>
			   <div class="console-box">
					<a class="current">已分享</a>
	           </div>
	           <!-- 首先判断是否有上传的内容 没有的话 就显示为隐藏 -->
	           <div id="sharecontent">
	              <div class="successshare">
		             <div class="docs-list-box">
		               <div class='delete-box normal-console-box clearfix'>
		                 <span class="remove" data-type='0'><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		                </div>
		              <div class="status-box">
		                   <div class="w356 ib"><div class="checkbox select-all"></div>文章名称</div>
						   <div class="w165 ib">推荐语</div>
						   <div class="w125 ib">收藏次数</div>
						   <div class="w125 ib">分享时间</div>
						   <div class="w76 ib">操作</div>
					   </div>
					   <div class="docs-list">
					     <ul>
					       <#if (shaArticlePage)??>
					          <#list shaArticlePage.list as list>
					            <li>
					               <div class='w356 fs14 fc3 ib titlehidden'>
                                   	  <div class="checkbox chk" data-courseid="${list.id}" data-name="${list.title}"></div>
                                      <a href="/51jobplusCore/article/getArticleDetail?id=${list.id}" target="_blank" title="${list.title}">${list.title}</a>  
                                   </div>
                                   <div class='w165 ib replayhidden'>
                                       <#if (list.recommend)??>
                                         <p title='${list.recommend}'>
                                            ${list.recommend}
                                         </p>
                                       </#if>
                                   </div>
                                   <div class='w125 ib ilbookc'>
                                       <#if (list.collectsum)??>
                                         ${list.collectsum}
                                       </#if>
	                                </div>
	                                <div class='w125 ib ilbook'>
	                                       <#if (list.createtime?string("yyyy-MM-dd"))??>
	                                         ${list.createtime?string("yyyy-MM-dd")}
	                                       </#if>
	                                 </div>
	                                 <div class='w92 ib operate'>
									       <span data-courseid='${list.id}' data-name='${list.title}' class='cancelshare'>
							                  <b class="iconfont pr2"></b>
							                                            删除
									       </span>
	                                  </div>
					            </li>
					          </#list>
					       </#if>
					     </ul>
					   </div>
		             </div>
		             <#if (shaArticlePage.last)??>
						 <#if (shaArticlePage.last gt 1)>
							<div class='page-inner'>
							  <div class="ui-pager pager-center">
								<div class='pager'>
								 
								  <div class="pager-inner">
									<div id="articleSharePaging" class="page"></div>
									
								  </div>
								
								</div>
							  </div>
							</div>
						 </#if>
					  </#if>
		           </div>
	           </div>
           </div>
           <#if (colArticlePage)??>
             <div class="uc-mydocreply">
           <#else>
             <div class="uc-mydocreply" style="display:none">
           </#if>
          
			   <div class="console-box">
					<a class="current">已收藏</a>
	           </div>
	            <div class="focus">
                 <div class="docs-list-box">
                   <div class='delete-box normal-console-box clearfix'>
		                 <span class="remove" data-type='1'><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		           </div>
	               <div class="status-box">
					  <div class="w446 ib"><div class="checkbox select-all"></div>文章名称</div>
					  <div class="w165 ib">收藏次数</div>
					  <div class="w165 ib">收藏时间</div>
					  <div class="w92 ib">操作</div>
				   </div>
				   <div class="docs-list">
				     <ul>
				       <#if (colArticlePage)??>
				        <#list colArticlePage.list as collist>
				          <li>
				            <div class='w446 fs14 fc3 ib titlehidden'>
                               	  <div class="checkbox chk" data-courseid="${collist.myCollect.id}" data-name="${collist.title}"></div>
                                  <a href="/51jobplusCore/article/getArticleDetail?id=${collist.myCollect.objectid}" target="_blank" title="${collist.title}">${collist.title}</a>  
                            </div>
                            <div class='w165 ib ilbookc'>
                               <#if (collist.collectsum)??>
                                 ${collist.collectsum}
                               </#if>
                            </div>
                            <div class='w165 ib ilbook'>
                               <#if (collist.myCollect.colltime?string("yyyy-MM-dd"))??>
                                 ${collist.myCollect.colltime?string("yyyy-MM-dd")}
                               </#if>
                            </div>
                            <div class='w92 ib operate'>
						       <span data-courseid='${collist.myCollect.id}' data-name='${collist.title}'>
				                  <b class="iconfont pr2"></b>
				                                            删除
						       </span>
                           </div>
				          </li>
				        </#list>
				      </#if>
				     </ul>
				   </div>
	             </div>
	              <#if (colArticlePage.last)??>
					 <#if (colArticlePage.last gt 1)>
						<div class='page-inner'>
						  <div class="ui-pager pager-center">
							<div class='pager'>
							 
							  <div class="pager-inner">
								<div id="articleColPaging" class="page"></div>
								
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
		
	  </div>
	  <div class='pagetemplate'></div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
      <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
      <script type='text/javascript' src='/51jobplusCore/scripts/pj_mycenterarticle.js'></script>
      <script type="text/javascript">
		  <#if (colArticlePage.list)??>
			$(function(){
				$("#articleColPaging").pagination({
					items: ${colArticlePage.count},
					itemsOnPage:${colArticlePage.pageSize},
					cssStyle: 'light-theme',
					moduleType:'articlecol'
				});
			});
		  </#if>
		  
		  <#if (shaArticlePage.list)??>
			  $(function(){
					$("#articleSharePaging").pagination({
						items: ${shaArticlePage.count},
						itemsOnPage:${shaArticlePage.pageSize},
						cssStyle: 'light-theme',
						moduleType:'articleshare'
					});
				});
		  </#if>
	  </script>
  </body>

</html>