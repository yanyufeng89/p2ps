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
		      <li class="open">
		         <div class="my-doc link"><p>
		            <a href='/myCenter/getMyHeadTop'>
		               <span class="icon-ken"></span>我的知识库
		            </a>
		            <b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                 <li id="mydocument"><a href="/myCenter/getMyDocsUploaded"><span class="icon-doc"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/myCenter/getMyTopicsUploaded"><span class="icon-topic"></span>我的话题</a></li>
	                 <li id="mybook"><a href="/myCenter/getSharedBookList" class="current"><span class="icon-book"></span>我的书籍</a></li>
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
		<li><a href="#" ><span class="s-ic guide"></span>反馈</a></li>
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
				   <span><a class='icon-mybook'></a></span>
				   <div style="float:left">
				    <p>我的书籍</p>
				    <p class="dll">
					    <a id='bookshare' href='/myCenter/getSharedBookList' <#if (sharedBookPage)??>class="current"</#if>>
					    分享&nbsp;<#if (Session.operationSum.booksharesum)??>${Session.operationSum.booksharesum}</#if>
					    </a>
					    <a id='bookcollect'  href='/myCenter/getCollectedBookList' <#if (collectedBookPage)??>class="current"</#if>>
					    收藏&nbsp;<#if (Session.operationSum.bookcollsum)??>${Session.operationSum.bookcollsum}</#if>
					    </a>
				    </p>
				    </div>
				   </div>
		    </div>
		   <#if (sharedBookPage)??>
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
		                   <div class="w356 ib"><div class="checkbox select-all"></div>书籍名称</div>
		                   <div class="w140 ib">推荐语</div>
						   <div class="w92 ib">作者</div>
						   <div class="w92 ib">收藏次数</div>
						   <div class="w119 ib">分享时间</div>
						   <div class="w92 ib">操作</div>
					   </div>
					   
					   <div class="docs-list">
					     <ul>
					       <#if (sharedBookPage)??>
					          <#list sharedBookPage.list as list>
					            <li>
					              <div class='w336 fs14 fc3 ib titlehidden'>
                                   	  <div class="checkbox chk" data-commentid="${list.bookShare.id}" data-bookid='${list.id}' data-name="${list.bookname}"></div>
                                      <a href="/books/getBookDetail?id=${list.id}" target="_blank" title="${list.bookname}">${list.bookname}</a>  
                                   </div>
                                   <div class='w121 ib recommendhidden'>
                                       <#if (list.bookShare.recommend)??>
                                         <p title='${list.bookShare.recommend}'>
                                            ${list.bookShare.recommend}
                                         </p>
                                       </#if>
                                   </div>
                                   <div class='w92 ib ilbook recommendhidden'>
                                       <#if (list.author)??>
                                         <p title='${list.author}'>${list.author}</p>
                                       </#if>
                                   </div>
                                   <div class='w92 ib ilbookc'>
                                       <#if (list.collectsum)??>
                                         ${list.collectsum}
                                       </#if>
                                   </div>
                                   <div class='w119 ib ilbook'>
                                       <#if (list.bookShare.createtime?string("yyyy-MM-dd"))??>
                                         ${list.bookShare.createtime?string("yyyy-MM-dd")}
                                       </#if>
                                   </div>
                                   <div class='w92 ib operate'>
								       <span data-commentid='${list.bookShare.id}' data-bookid='${list.id}' data-name='${list.bookname}' class='cancelshare'>
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
					 <#if (sharedBookPage.last)??>
						 <#if (sharedBookPage.last gt 1)>
							<div class='page-inner'>
							  <div class="ui-pager pager-center">
								<div class='pager'>
								 
								  <div class="pager-inner">
									<div id="paging1" class="page"></div>
									
								  </div>
								
								</div>
							  </div>
							</div>
						 </#if>
					  </#if>
			   
		           </div>
	           </div>
	          </div>
	          
	          <#if (collectedBookPage.list)??>
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
		                   <div class="w366 ib"><div class="checkbox select-all"></div>书籍名称</div>
						   <div class="w92 ib">作者</div>
						   <div class="w125 ib">出版社</div>
						   <div class="w92 ib">收藏次数</div>
						   <div class="w119 ib">分享时间</div>
						   <div class="w92 ib">操作</div>
				   </div>
				  
				   <div class="docs-list">
				     <ul>
			          <#if (collectedBookPage.list)??>
			             <#list collectedBookPage.list as sharelist>
			                   <li>
					              <div class='w346 fs14 fc3 ib titlehidden'>
                                   	  <div class="checkbox chk" data-bookid="${sharelist.id}" data-commentid='${sharelist.myCollect.id}' data-name="${sharelist.bookname}"></div>
                                      <a href="/books/getBookDetail?id=${sharelist.id}" target="_blank" title="${sharelist.bookname}">${sharelist.bookname}</a>  
                                   </div>
                                   <div class='w73 ib ilbook recommendhidden'>
                                       <p title='${sharelist.author}'><#if (sharelist.author)??>${sharelist.author}</#if></p>
                                   </div>
                                   <div class='w125 ib recommendhidden'>
                                      <p title='${sharelist.press}'> <#if (sharelist.press)??>${sharelist.press}</#if></p>
                                   </div>
                                   <div class='w92 ib ilbookc'>
                                       <#if (sharelist.collectsum)??>
                                         ${sharelist.collectsum}
                                       </#if>
                                   </div>
                                   <div class='w119 ib ilbook'>
                                       <#if (sharelist.myCollect.colltime?string("yyyy-MM-dd"))??>
                                          ${sharelist.myCollect.colltime?string("yyyy-MM-dd")}
                                       </#if>
                                   </div>
                                   <div class='w92 ib operate'>
								       <span data-bookid="${sharelist.id}" data-commentid='${sharelist.myCollect.id}' data-name='${sharelist.bookname}'>
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
	             
	           <#if (collectedBookPage.last)??>
				 <#if (collectedBookPage.last gt 1)>
				    <div class='page-inner'>
					  <div class="ui-pager pager-center">
					    <div class='pager'>
						 
						  <div class="pager-inner">
						    <div id="paging2" class="page"></div>
							
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
     <script type='text/javascript' src='/scripts/pj_mycenterbook.js'></script>
	 <script type="text/javascript">
	  <#if (collectedBookPage.list)??>
		$(function(){
			$("#paging2").pagination({
				items: ${collectedBookPage.count},
				itemsOnPage:${collectedBookPage.pageSize},
				cssStyle: 'light-theme',
				moduleType:'bookcol'
			});
		});
	  </#if>
	  
	  <#if (sharedBookPage.list)??>
		  $(function(){
				$("#paging1").pagination({
					items: ${sharedBookPage.count},
					itemsOnPage:${sharedBookPage.pageSize},
					cssStyle: 'light-theme',
					moduleType:'bookshare'
				});
			});
	  </#if>
	</script>
  </body>

</html>