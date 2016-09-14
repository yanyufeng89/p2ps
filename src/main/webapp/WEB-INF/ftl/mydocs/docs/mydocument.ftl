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
	                 <li id="mydocument"><a href="/51jobplusCore/myCenter/getMyDocsUploaded" class='current'><span class="icon-doc"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/51jobplusCore/myCenter/getMyTopicsUploaded" ><span class="icon-topic"></span>我的话题</a></li>
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
				    <span><a class='icon-mydocument'></a></span>
				    <div style="float:left">
					    <p>我的文档</p>
					    <p class="dll">
					       <a href='/51jobplusCore/myCenter/getMyDocsUploaded' <#if (docsPage)??>class="current"</#if> id='docupload'>上传(<#if (Session.operationSum.docsharesum)??>${Session.operationSum.docsharesum}</#if>)</a>
	                       <a href='/51jobplusCore/myCenter/getMyDocsDowned' <#if (myDownloadPage)??>class="current"</#if> id='docdownload'>下载(<#if (Session.operationSum.docdownsum)??>${Session.operationSum.docdownsum}</#if>)</a>
	                       <a href='/51jobplusCore/myCenter/getMyDocsCollected' <#if (myCollectPage)??>class="current"</#if> id='doccollect'>收藏(<#if (Session.operationSum.doccollsum)??>${Session.operationSum.doccollsum}</#if>)</a>
	                    </p>
                    </div>
				   </div>
			</div>
		    <#if (docsPage)??>
			<div class="uc-mydocshare">
			<#else>
			<div class="uc-mydocshare" style='display:none'>
			</#if>
			   <div class="console-box">
					<a <#if (ispublic)??><#if (ispublic==1)>class="current"</#if></#if> href='/51jobplusCore/myCenter/getMyDocsUploaded?ispublic=1'>已分享</a>
					<a <#if (ispublic)??><#if (ispublic==0)>class="current"</#if></#if> href='/51jobplusCore/myCenter/getMyDocsUploaded?ispublic=0'>私有</a>
					<a <#if (ispublic)??><#if (ispublic==2)>class="current"</#if></#if> href='/51jobplusCore/myCenter/getMyDocsUploaded?ispublic=2'>草稿</a>
	           </div>
	           <!-- 首先判断是否有上传的内容 没有的话 就显示为隐藏 -->
	           <div id="sharecontent">
	             <#if (ispublic)??>
	               <#if (ispublic==1)>
	               <div class="successshare">
	               <#else>
	                <div class="successshare" style='display:none'>
	               </#if>
	             <#else>
	                <div class="successshare" style='display:none'>
	             </#if>
		             <div class="docs-list-box">
		               <div class='delete-box normal-console-box clearfix'>
		                 <span class="remove" data-type='1'><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		               </div>
		               <div class="status-box">
		                   <div class="w335 ib"><div class="checkbox select-all"></div>文档名称</div>						   
						   <div class="w140 ib">下载次数</div>
						   <div class="w140 ib">收藏次数</div>
						   <div class="w140 ib">上传时间</div>
						   <div class="w119 ib">操作</div>
					   </div>
					   <div class="docs-list">
					     <ul>
					       <#if (docsPage)??>
							      <#list docsPage.list as list>
								         <li>
								          <div class='w335 fs14 fc3 ib dochidden'>
								             <div class="checkbox chk" data-docid="${list.id}" data-name="${list.title}" ></div>
								             <b class="ic ic-${list.docsuffix?lower_case} mr14"></b>
								             <#if list.title?index_of(list.docsuffix)!=-1>
								               <#assign dtitle=list.title?substring(0,list.title?index_of(list.docsuffix)?number-1) />
								                 <a href="/51jobplusCore/docs/getDocsDetail?id=${list.id}" data-isconverter='${list.isconverter}'  class='doctitle' target="_blank" title="${dtitle}">${dtitle}</a>
								             <#else>
								                 <a href="/51jobplusCore/docs/getDocsDetail?id=${list.id}" data-isconverter='${list.isconverter}'  class='doctitle' target="_blank" title="${list.title}">${list.title}</a>
								             </#if>
								             
								          </div>
								          <div class='w140 ib il'>
								           <#if (list.downsum)??>
								             ${list.downsum}
								           </#if>
								          </div>
								          <div class='w140 ib il'>
								           <#if (list.collectsum)??>
								            ${list.collectsum}
								           </#if>
								          </div>
								          <div class="w140 ib">
								             ${list.createtime?string("yyyy-MM-dd")}
								          </div>
								          <div class='w119 ib operate'>
								              <a href='/51jobplusCore/myCenter/getDocsDetailForEdit?docId=${list.id}'  class='pr10 modify-doc __HIDE__' target='_blank'>
								                <b class="iconfont pr2"></b>修改
								              </a>
								              <span data-id='${list.id}' data-name='${list.title}' data-type='1'>
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
		             <#if (docsPage.last)??>
						 <#if (docsPage.last gt 1)>
							<div class='page-inner'>
							  <div class="ui-pager pager-center">
								<div class='pager'>
								 
								  <div class="pager-inner">
									<div id="docsharepaging" class="page"></div>
									
								  </div>
								
								</div>
							  </div>
							</div>
						 </#if>
					  </#if>
		           </div>
		           <#if (ispublic)??>
		             <#if (ispublic==0)>
		                   <div class="private">
		             <#else>
		                   <div class="private" style='display:none'>
		             </#if>
		           <#else>
		                   <div class="private" style='display:none'>
		           </#if>
		              <div class="docs-list-box">
		                  <div class='delete-box normal-console-box clearfix'>
		                     <span class="remove" data-type='1'><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		                  </div>
			               <div class="status-box">
							   <div class="w525 ib"><div class="checkbox select-all"></div>文档名称</div>
							   <div class="w204 ib">上传时间</div>
							   <div class="w165 ib">操作</div>
						   </div>
						   <div class="docs-list">
						      <ul>
					           <#if (docsPage)??>
							      <#list docsPage.list as list>
								         <li>
								          <div class='w525 fs14 fc3 ib dochidden'>
								             <div class="checkbox chk" data-docid="${list.id}" data-name="${list.title}"></div>
								             <b class="ic ic-${list.docsuffix?lower_case} mr14"></b>
								             <#if list.title?index_of(list.docsuffix)!=-1>
								               <#assign ctitle=list.title?substring(0,list.title?index_of(list.docsuffix)?number-1) />
								               <a href="/51jobplusCore/docs/getDocsDetail?id=${list.id}" data-isconverter='${list.isconverter}' class='doctitle' target="_blank" title="${ctitle}">${ctitle}</a>
								             <#else>
								               <a href="/51jobplusCore/docs/getDocsDetail?id=${list.id}" data-isconverter='${list.isconverter}' class='doctitle' target="_blank" title="${list.title}">${list.title}</a>
								             </#if>
								          </div>
								          <div class="w204 ib">
								              ${list.createtime?string("yyyy-MM-dd")}
								          </div>
								          <div class='w165 ib operate'>
								              <a href='/51jobplusCore/myCenter/getDocsDetailForEdit?docId=${list.id}'  class='pr10 modify-doc __HIDE__' target='_blank'>
								                <b class="iconfont pr2"></b>修改
								              </a>
								              <span data-id='${list.id}' data-name='${list.title}' data-type='1'>
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
			            <#if (docsPage.last)??>
						 <#if (docsPage.last gt 1)>
							<div class='page-inner'>
							  <div class="ui-pager pager-center">
								<div class='pager'>
								 
								  <div class="pager-inner">
									<div id="docprivatepaging" class="page"></div>
									
								  </div>
								
								</div>
							  </div>
							</div>
						 </#if>
					  </#if>
		           </div>
		           <#if (ispublic)??>
		             <#if (ispublic==2)>
		               <div class="draft">
		             <#else>
		               <div class="draft" style="display:none">
		             </#if>
		            <#else>
		               <div class="draft" style="display:none">
		           </#if>
			            <div class="docs-list-box">
			               <div class='delete-box normal-console-box clearfix'>
		                     <span class="remove" data-type='1' ><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		                   </div>
			               <div class="status-box">
							   <div class="w525 ib"><div class="checkbox select-all"></div>文档名称</div>
							   <div class="w204 ib">上传时间</div>
							   <div class="w165 ib">操作</div>
						   </div>
						   <div class="docs-list">
						    <ul>
						      <#if (docsPage)??>
							      <#list docsPage.list as list>
							        
								       <li>
								          <div class='w525 fs14 fc3 ib dochidden'>
								             <div class="checkbox chk" data-docid="${list.id}" data-name="${list.title}"></div>
								             <b class="ic ic-${list.docsuffix?lower_case} mr14"></b>
								             
                                            <#if list.title?index_of(list.docsuffix)!=-1>
								               <#assign ltitle=list.title?substring(0,list.title?index_of(list.docsuffix)?number-1) />
								               <a href="/51jobplusCore/docs/getDocsDetail?id=${list.id}" target="_blank" title="${ltitle}">${ltitle}</a>
								            <#else>
								                <a href="/51jobplusCore/docs/getDocsDetail?id=${list.id}" target="_blank" title="${list.title}">${list.title}</a>
								            </#if> 
								          </div>
								          <div class="w204 ib">
								             ${list.createtime?string("yyyy-MM-dd")}
								          </div>
								          <div class='w165 ib operate'>
								              <a href='/51jobplusCore/myCenter/getDocsDetailForEdit?docId=${list.id}'  class='pr10 modify-doc __HIDE__' target='_blank'>
								                <b class="iconfont pr2"></b>修改
								              </a>
								              <span data-id='${list.id}' data-name='${list.title}' data-type='1'>
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
			             <#if (docsPage.last)??>
							 <#if (docsPage.last gt 1)>
							    <div class='page-inner'>
								  <div class="ui-pager pager-center">
								    <div class='pager'>
									 
									  <div class="pager-inner">
									    <div id="docdrafpaging" class="page"></div>
										
									  </div>
									
									</div>
								  </div>
								</div>
							 </#if>
			           </#if>
	                </div>
	           </div>
	           </div>
	         <#if (myDownloadPage)??>
	        <div class="uc-mydocfocus">
	        <#else>
	        <div class="uc-mydocfocus" style="display:none">
	        </#if>
			   <div class="console-box">
		          <a class="current">已下载</a>
	           </div>
	           <div class="focus">
                 <div class="docs-list-box">
                   <div class='delete-box normal-console-box clearfix'>
		              <span class="remove" data-type='0' data-actiontype='0'><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		           </div>
	               <div class="status-box">
					  <div class="w450 ib"><div class="checkbox select-all"></div>文档名称</div>
					  <div class="w165 ib">下载次数</div>
					  <div class="w165 ib">下载时间</div>
					  <div class="w119 ib">操作</div>
				   </div>
				   <div class="docs-list">
				   <ul>
				     <#if (myDownloadPage)??>
				         <#list myDownloadPage.list as list>
				             <li>
				                <div class='w450 fs14 fc3 ib dochidden'>
					                  <div class="checkbox chk" data-docid="${list.id}" data-name="${list.docs.title}"></div>
									  <b class="ic ic-${list.docs.docsuffix?lower_case} mr14"></b>
									<#if list.docs.title?index_of(list.docs.docsuffix)!=-1> 
									    <#assign atitle=list.docs.title?substring(0,list.docs.title?index_of(list.docs.docsuffix)?number-1) />
									    <a href="/51jobplusCore/docs/getDocsDetail?id=${list.objectid}" target="_blank" title="${atitle}">${atitle}</a>
				                    <#else>
				                        <a href="/51jobplusCore/docs/getDocsDetail?id=${list.objectid}" target="_blank" title="${list.docs.title}">${list.docs.title}</a>
				                    </#if>
				                </div>
				                <div class='w165 ib il'>
				                  ${list.docs.downsum}
				                </div>
				                <div class='w165 ib'>
				                  ${list.colltime?string("yyyy-MM-dd")}
				                </div>
				                <div class='w119 ib operate'>
				                   <span data-id='${list.id}' data-name='${list.docs.title}' data-type='0' data-actiontype='0'>
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
	           </div>
	           
	           <#if (myDownloadPage.last)??>
				 <#if (myDownloadPage.last gt 1)>
				    <div class='page-inner'>
					  <div class="ui-pager pager-center">
					    <div class='pager'>
						 
						  <div class="pager-inner">
						    <div id="docdownpaging" class="page"></div>
							
						  </div>
						
						</div>
					  </div>
					</div>
				 </#if>
			   </#if>
			   
	     	</div>
	     	 <#if (myCollectPage)??>
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
		              <span class="remove" data-type='0' data-actiontype='1'><b class="iconfont"></b>&nbsp;&nbsp;删除</span>
		           </div>
	               <div class="status-box">
					  <div class="w450 ib"><div class="checkbox select-all"></div>文档名称</div>
					  <div class="w165 ib">收藏次数</div>
					  <div class="w165 ib">收藏时间</div>
					  <div class="w119 ib">操作</div>
				   </div>
				   <div class="docs-list">
				     <ul>
				       <#if (myCollectPage)??>
				         <#list myCollectPage.list as list>
				             <li>
				                <div class='w450 fs14 fc3 ib dochidden'>
				                  <div class="checkbox chk" data-docid="${list.id}" data-name="${list.docs.title}"></div>
								  <b class="ic ic-${list.docs.docsuffix?lower_case} mr14"></b>
								  <#if list.docs.title?index_of(list.docs.docsuffix)!=-1>
								    <#assign btitle=list.docs.title?substring(0,list.docs.title?index_of(list.docs.docsuffix)?number-1) />
								    <a href="/51jobplusCore/docs/getDocsDetail?id=${list.objectid}" target="_blank" title="${btitle}">${btitle}</a>
								  <#else>
								     <a href="/51jobplusCore/docs/getDocsDetail?id=${list.objectid}" target="_blank" title="${list.docs.title}">${list.docs.title}</a>
				                  </#if>
				                </div>
				                <div class='w165 ib il'>
				                  ${list.docs.collectsum}
				                </div>
				                <div class='w165 ib'>
				                 ${list.colltime?string("yyyy-MM-dd")}
				                </div>
				                <div class='w119 ib operate'>
				                   <span data-id='${list.id}' data-name='${list.docs.title}' data-type='0' data-actiontype='1'>
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
	               <#if (myCollectPage.last)??>
					 <#if (myCollectPage.last gt 1)>
					    <div class='page-inner'>
						  <div class="ui-pager pager-center">
						    <div class='pager'>
							 
							  <div class="pager-inner">
							    <div id="doccollpaging" class="page"></div>
								
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
      <script type="text/javascript" src="/51jobplusCore/scripts/jquery.media.js"></script>
      <script type='text/javascript' src='/51jobplusCore/scripts/pj_mycenterdocument.js'></script>
      <script type="text/javascript">
       <#if (docsPage.list)??>
		$(function(){
			$("#docsharepaging").pagination({
				items: ${docsPage.count},
				itemsOnPage:${docsPage.pageSize},
				cssStyle: 'light-theme',
				moduleType:'docshare'
			});
		});
	  </#if>
	  
	  <#if (docsPage.list)??>
		  $(function(){
				$("#docprivatepaging").pagination({
					items: ${docsPage.count},
					itemsOnPage:${docsPage.pageSize},
					cssStyle: 'light-theme',
					moduleType:'docprivate'
				});
			});
	  </#if>
	  
	  <#if (docsPage.list)??>
		  $(function(){
				$("#docprivatepaging").pagination({
					items: ${docsPage.count},
					itemsOnPage:${docsPage.pageSize},
					cssStyle: 'light-theme',
					moduleType:'docprivate'
				});
			});
	  </#if>
	  
	  <#if (myDownloadPage.list)??>
		  $(function(){
				$("#docdownpaging").pagination({
					items: ${myDownloadPage.count},
					itemsOnPage:${myDownloadPage.pageSize},
					cssStyle: 'light-theme',
					moduleType:'docdown'
				});
			});
	  </#if>
	  
	  <#if (myCollectPage.list)??>
		  $(function(){
				$("#doccollpaging").pagination({
					items: ${myCollectPage.count},
					itemsOnPage:${myCollectPage.pageSize},
					cssStyle: 'light-theme',
					moduleType:'doccollect'
				});
			});
	  </#if>
	 </script>
  </body>

</html>