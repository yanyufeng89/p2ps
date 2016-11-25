<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          我的文档-个人中心-JobPlus知识库
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
		 <a  href="/myCenter/getMyHeadTop" target="_self">
		  <#if (Session.user.headicon)?? && Session.user.headicon?length gt 0>
		    <img src="${Session.user.headicon}" width='100' height='100' alt="个人头像"  class='lazy'>
		  <#else>
		    <img src="/image/myphoto.jpg" width='100' height='100' alt="个人头像" class='lazy'>
		  </#if>
		 </a>
		</div>
		<p class="user-name">
			<a class="name-link" href="/myCenter/getMyHeadTop" target="_self">
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
		    <a href="javascript:void(0)" target="_blank">&nbsp;进入公司主页</a> 
		  <#else>
		    <a href="/myHome/getHomePage/${Session.user.userid}?isReview=0" target="_blank">&nbsp;进入个人主页</a>
		  </#if>
        </p>    
		<div class="mydoc-list">
             <ul id="accordion">
		      <li class="open">
		         <div class="my-doc link"><p>
		            <a href='/myCenter/getMyHeadTop'>
			         <span class="icon-ken"></span>我的知识库
			        </a>
			         <b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                 <li id="mydocument"><a href="/myCenter/getMyDocsUploaded" class='current'><span class="icon-doc"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/myCenter/getMyTopicsUploaded" ><span class="icon-topic"></span>我的话题</a></li>
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
		       <li>
		         <div class="my-order link"><p><span class="icon-wealth"></span>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	              	  <li><a href="/account/getDetailListByRecord?changetype=1"><span class="icon-incomewealth"></span>财富收益</a></li>
	                  <li><a href="/account/getDetailListByRecord?changetype=2"><span class="icon-wealthspending"></span>财富支出</a></li>
		          </ul>
		      </li>
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
				 
				    <span><a class='icon-mydocument'></a></span>
				    <div style="float:left">
					    <p>我的文档</p>
					    <p class="dll">
					       <a href='/myCenter/getMyDocsUploaded' <#if (docsPage)??>class="current"</#if> id='docupload'>上传&nbsp;<#if (Session.operationSum.docsharesum)??>${Session.operationSum.docsharesum}</#if></a>
	                       <a href='/myCenter/getMyDocsDowned' <#if (myDownloadPage)??>class="current"</#if> id='docdownload'>下载&nbsp;<#if (Session.operationSum.docdownsum)??>${Session.operationSum.docdownsum}</#if></a>
	                       <a href='/myCenter/getMyDocsCollected' <#if (myCollectPage)??>class="current"</#if> id='doccollect'>收藏&nbsp;<#if (Session.operationSum.doccollsum)??>${Session.operationSum.doccollsum}</#if></a>
	                    </p>
                    </div>
                    <a href='javascript:void(0);' onclick="share('doc');" class='head-sharein' style='float:right;margin:23px 28px 0 0'></a>
				  
			</div>
		    <#if (docsPage)??>
			<div class="uc-mydocshare">
			<#else>
			<div class="uc-mydocshare" style='display:none'>
			</#if>
			   <div class="console-box">
					<a <#if (ispublic)??><#if (ispublic==1)>class="current"</#if></#if> href='/myCenter/getMyDocsUploaded'>已分享</a>
					<a <#if (ispublic)??><#if (ispublic==0)>class="current"</#if></#if> href='/myCenter/getMyDocsUploaded?ispublic=0'>私有</a>
					<#-- <a <#if (ispublic)??><#if (ispublic==2)>class="current"</#if></#if> href='/myCenter/getMyDocsUploaded?ispublic=2'>匿名分享</a> -->	
	           </div>
	           <!-- 首先判断是否有上传的内容 没有的话 就显示为隐藏 -->
	           <div id="sharecontent">
	             <#if (ispublic)??>
	               <#if (ispublic==1)>
	               <div class="successshare">
	               <#-- 公开的文档删除  需要扣除财富值 -->
				   <input type='hidden' name='alMn' value='${Session.account.points}'>
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
		                   <div class="w322 ib"><div class="checkbox select-all"></div>文档名称</div>	
		                   <div class="w84 ib">文档状态</div>					   
						   <div class="w84 ib">是否匿名</div>
						   <div class="w84 ib">下载次数</div>
						   <div class="w84 ib">收藏次数</div>
						   <div class="w94 ib">上传时间</div>
						   <div class="w119 ib">操作</div>
					   </div>
					   <div class="docs-list">
					     <ul>
					       <#if (docsPage)??>
							      <#list docsPage.list as list>
								         <li>
								          <div class='w300 fs14 fc3 ib dochidden'>
								             <div class="checkbox chk" data-docid="${list.id}" data-ispublic='${list.ispublic}' data-name="${list.title}" ></div>
								             <b class="ic ic-${list.docsuffix?lower_case} mr14"></b>
								           
								           <#if list.isconverter!=1 && list.isconverter!=3>	
								           <#-- 状态为提交中： 先查询是否转换成功  是跳转 否则 return false  href="/docs/getDocsDetail/${list.id}" -->							           
								             <a data-docid='${list.id}' data-ispublic='${list.ispublic}' class='doctitle notCvt' target="_blank" title="${list.title}">${list.title}</a>
								             <#else>
								              	 <a href="/docs/getDocsDetail/${list.id}" data-docid='${list.id}' data-ispublic='${list.ispublic}' class='doctitle <#if list.isconverter!=1>disabled</#if>' target="_blank" title="${list.title}">${list.title}</a>
								             </#if>

								          </div>
								          <div class='w84 ib'>
								          <#-- 1:成功  0:未转换 3:转换失败 2：转换中 -->

								             <#if list.isconverter==1>
								             	上传成功
								             <#elseif list.isconverter==0 || list.isconverter==2>
								                <b class='ib tijiao'></b>审核中
								             <#elseif list.isconverter==3>
								                                                     审核未通过
								              <#else>
								              	<b class='ib tijiao'></b>审核中
								             </#if>

								          </div>
								          <div class='w84 ib doc-ispublic'>
								           <#if list.ispublic==1>
								             	公开
								            <#elseif list.ispublic==2>
								            	匿名
								            <#else>
								            	公开
								           </#if>
								          </div>
								          <div class='w84 ib il'>
								           <#if (list.downsum)??>
								             ${list.downsum}
								           </#if>
								          </div>
								          <div class='w84 ib il'>
								           <#if (list.collectsum)??>
								            ${list.collectsum}
								           </#if>
								          </div>
								          <div class="w94 ib ilb">
								             ${list.createtime?string("yyyy-MM-dd")}
								          </div>
								          <div class='w119 ib operate ilb'>
								              <a href='/myCenter/getDocsDetailForEdit?docId=${list.id}'  class='pr10 modify-doc __HIDE__' target='_blank'>
								                <b class="iconfont pr2"></b>修改
								              </a>
								              <span data-id='${list.id}' data-name='${list.title}' data-ispublic='${list.ispublic}' data-type='1'>
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
							   <div class="w425 ib"><div class="checkbox select-all"></div>文档名称</div>
							   <div class="w165 ib">文档状态</div>
							   <div class="w165 ib">上传时间</div>
							   <div class="w125 ib">操作</div>
						   </div>
						   <div class="docs-list">
						      <ul>
					           <#if (docsPage)??>
							      <#list docsPage.list as list>
								         <li>
								          <div class='w405 fs14 fc3 ib dochidden'>
								             <div class="checkbox chk" data-docid="${list.id}" data-name="${list.title}"></div>
								             <b class="ic ic-${list.docsuffix?lower_case} mr14"></b>
								             <#--<#if list.title?index_of(list.docsuffix)!=-1>
								               <#assign ctitle=list.title?substring(0,list.title?index_of(list.docsuffix)?number-1) />
								               <a href="/docs/getDocsDetail/${list.id}"  data-docid='${list.id}'  data-isconverter='${list.isconverter}' class='doctitle' target="_blank" title="${ctitle}">${ctitle}</a>
								             <#else>
								               <a href="/docs/getDocsDetail/${list.id}" data-docid='${list.id}'  data-isconverter='${list.isconverter}' class='doctitle' target="_blank" title="${list.title}">${list.title}</a>
								             </#if>-->
								              <#if list.isconverter!=1 && list.isconverter!=3>	
								           <#-- 状态为提交中： 先查询是否转换成功  是跳转 否则 return false  href="/docs/getDocsDetail/${list.id}" -->							           
								             <a data-docid='${list.id}' data-ispublic='${list.ispublic}' class='doctitle notCvt' target="_blank" title="${list.title}">${list.title}</a>
								             <#else>
								              	 <a href="/docs/getDocsDetail/${list.id}" data-docid='${list.id}' data-ispublic='${list.ispublic}' class='doctitle <#if list.isconverter!=1>disabled</#if>' target="_blank" title="${list.title}">${list.title}</a>
								             </#if>
								          </div>
								          <div class='w165 ib'>
								             <#-- 1:成功  0:未转换 3:转换失败 2：转换中 -->
								             <#if list.isconverter==1>
								             	上传成功
								             <#elseif list.isconverter==0 || list.isconverter==2>
								             <b class='ib tijiao'></b>审核中
								             <#elseif list.isconverter==3>
								                                                     审核未通过
								             <#else>
								              	<b class='ib tijiao'></b>审核中
								             </#if>
								          </div>
								          <div class="w165 ib">
								              ${list.createtime?string("yyyy-MM-dd")}
								          </div>
								          <div class='w125 ib operate'>
								              <a href='/myCenter/getDocsDetailForEdit?docId=${list.id}'  class='pr10 modify-doc __HIDE__' target='_blank'>
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
							   <div class="w560 ib"><div class="checkbox select-all"></div>文档名称</div>
							   <div class="w204 ib">上传时间</div>
							   <div class="w119 ib">操作</div>
						   </div>
						   <div class="docs-list">
						    <ul>
						      <#if (docsPage)??>
							      <#list docsPage.list as list>
							        
								       <li>
								          <div class='w560 fs14 fc3 ib dochidden'>
								             <div class="checkbox chk" data-docid="${list.id}" data-name="${list.title}"></div>
								             <b class="ic ic-${list.docsuffix?lower_case} mr14"></b>
								             
                                            <#--<#if list.title?index_of(list.docsuffix)!=-1>
								               <#assign ltitle=list.title?substring(0,list.title?index_of(list.docsuffix)?number-1) />
								               <a href="/docs/getDocsDetail/${list.id}" target="_blank" title="${ltitle}">${ltitle}</a>
								            <#else>
								                <a href="/docs/getDocsDetail/${list.id}" target="_blank" title="${list.title}">${list.title}</a>
								            </#if>-->
								            <a href="/docs/getDocsDetail/${list.id}" target="_blank"  title="${list.title}">${list.title}</a>
								          </div>
								          <div class="w204 ib">
								             ${list.createtime?string("yyyy-MM-dd")}
								          </div>
								          <div class='w119 ib operate'>
								              <a href='/myCenter/getDocsDetailForEdit?docId=${list.id}'  class='pr10 modify-doc __HIDE__' target='_blank'>
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
					  <div class="w525 ib"><div class="checkbox select-all"></div>文档名称</div>
					  <div class="w140 ib">下载次数</div>
					  <div class="w140 ib">下载时间</div>
					  <div class="w92 ib">操作</div>
				   </div>
				   <div class="docs-list">
				   <ul>
				     <#if (myDownloadPage)??>
				         <#list myDownloadPage.list as list>
				             <li>
				                <div class='w525 fs14 fc3 ib dochidden'>
					                  <div class="checkbox chk" data-docid="${list.id}" data-name="${list.docs.title}"></div>
									  <b class="ic ic-${list.docs.docsuffix?lower_case} mr14"></b>
									<#--<#if list.docs.title?index_of(list.docs.docsuffix)!=-1> 
									    <#assign atitle=list.docs.title?substring(0,list.docs.title?index_of(list.docs.docsuffix)?number-1) />
									    <a href="/docs/getDocsDetail/${list.objectid}" target="_blank" title="${atitle}">${atitle}</a>
				                    <#else>				                       
				                    </#if>-->
				                     <a href="/docs/getDocsDetail/${list.objectid}" data-ispublic='${list.ispublic}' target="_blank" title="${list.docs.title}">${list.docs.title}</a>
				                </div>
				                <div class='w140 ib il'>
				                  ${list.docs.downsum}
				                </div>
				                <div class='w140 ib'>
				                  ${list.colltime?string("yyyy-MM-dd")}
				                </div>
				                <div class='w92 ib operate'>
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
					  <div class="w525 ib"><div class="checkbox select-all"></div>文档名称</div>
					  <div class="w140 ib">收藏次数</div>
					  <div class="w140 ib">收藏时间</div>
					  <div class="w92 ib">操作</div>
				   </div>
				   <div class="docs-list">
				     <ul>
				       <#if (myCollectPage)??>
				         <#list myCollectPage.list as list>
				             <li>
				                <div class='w525 fs14 fc3 ib dochidden'>
				                  <div class="checkbox chk" data-docid="${list.id}" data-name="${list.docs.title}"></div>
								  <b class="ic ic-${list.docs.docsuffix?lower_case} mr14"></b>
								  <#--<#if list.docs.title?index_of(list.docs.docsuffix)!=-1>
								    <#assign btitle=list.docs.title?substring(0,list.docs.title?index_of(list.docs.docsuffix)?number-1) />
								    <a href="/docs/getDocsDetail/${list.objectid}" target="_blank" title="${btitle}">${btitle}</a>
								  <#else>
								   
				                  </#if>-->
				                    <a href="/docs/getDocsDetail/${list.objectid}" target="_blank" title="${list.docs.title}">${list.docs.title}</a>
				                </div>
				                <div class='w140 ib il'>
				                  ${list.docs.collectsum}
				                </div>
				                <div class='w140 ib'>
				                 ${list.colltime?string("yyyy-MM-dd")}
				                </div>
				                <div class='w92 ib operate'>
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
      <script type="text/javascript" src="/scripts/jquery.media.js"></script>
      <script type='text/javascript' src='/scripts/pj_mycenterdocument.js'></script>
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