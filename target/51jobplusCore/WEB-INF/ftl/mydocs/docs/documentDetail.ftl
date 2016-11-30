<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
       ${record.title}-JobPlus
    </title>
    <meta name="description" content="JobPlus网是国内领先的企业知识库公共平台,你可以在线分享文档,上传文档,论文,研究报告,行业标准,设计方案,电子书等电子文档, JobPlus为7亿职场人员提供优质学习分享社区,JobPlus是你最值得信赖的终身学习伙伴。">
    <meta name="keywords" content="Jobplus,知识分享,创新文档,创业文档,IT文档,互联网文档,财务文档,工业技术文档,工程文档,咨询管理文档,工程文档,市场管理文档,销售管理文档,供应链文档,生产管理文档,设计文档,创意文档,翻译文档,新媒体文档,影视文档,免费文档,word文档,考试资料,教学课件,参考资料,论文,学术论文,研究报告,工作范文">
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    
    <link rel='stylesheet' type='text/css' href='/css/pj_bookdetail.css'>
    <div id='wx_pic' style='margin:0 auto;display:none;'>
          <img src='/image/logo_1.jpg' />
    </div>
  </head>
  <body id='doctop'>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='bookdescrition'>
        <span>JobPlus知识库>文档<#list record.doctype?split(",") as doctype><#assign types=doctype?split(":")/>>${types[1]}</#list></span>
      </div>
      <div id="content">
        <input type='hidden' name='docid' value='${record.id}'>
        <input type='hidden' name='docname' value='${record.title}'>
        <input type='hidden' name='docCreatePerson' value='${record.userid}'>
        <input type='hidden' name='docsuffix' value='${record.docsuffix?lower_case}'>
        <input type='hidden' name='docurl' value='${record.readurl}'>
        <input type='hidden' name='sumpage' value='${record.docpages?number}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner doc-title">
               <div class='basic doc-basic' style='padding-bottom:12px;margin-bottom:0'>
                   <div class='infor after'>
                      <#if (record)??>
	                       <div class='infor-right'>
                             <div class='bookname'>
                                <b class="ic ic-${record.docsuffix?lower_case} mr14"></b>
                               
                                <span class='courseinfo'>${record.title}</span>
                             </div>
                            
	                       </div>
                      </#if>
                   </div>
               </div>
          </div>
		<div class='authorinfo doc-value'>
		<#-- 匿名用户  -->
		 <#if record.ispublic==2>
			   <a target='_blank'>
			    <img class="uname lazy" src="/image/1b48b5a75c71597_100x100.jpg" alt="个人头像"  data-moduletype="1">
			   </a>
			   <span class='author-link'>
				   <a  target='_blank'>匿名用户</a>
			   </span>
		 <#else>
			 <#if (record)??>
			   <a href='/myHome/getHomePage/${record.objCreator.userid}' target='_blank'>
			   <#if (record.objCreator.headicon)??>
			    <img class="uname lazy" src="${record.objCreator.headicon}" alt=""个人头像 data-userid="${record.objCreator.userid}" data-moduletype="1">
			   <#else>
			    <img class="uname lazy" src="/image/1b48b5a75c71597_100x100.jpg" alt="个人头像" data-userid="${record.objCreator.userid}" data-moduletype="1">
			   </#if>
			   </a>
			   <span class='author-link' data-userid="${record.objCreator.userid}">
				   <a href='/myHome/getHomePage/${record.objCreator.userid}' target='_blank'>${record.objCreator.username}</a>
			   </span>
		    </#if>
			<span class='createtime'>${record.createtime?string("yyyy-MM-dd")}</span>
			<span class='likesum'>${record.likesum}人赞</span>
			<span class='readsum'>${record.readsum}人浏览</span>
			<span class='collectsum'>${record.collectsum}人收藏</span>
			
			<span class='docbrief'>
			    <#if record.description?length gt 0>
				  简介<span class='ic-arrow ic-arrow-down' id='docbrief'></span>
				<#else>
				 暂无简介
				</#if>
		    </span>
		  </#if>
		</div>
		<#if record.description?length gt 0>
		    <div class='doc-desc-wrap'>
			   <p class="doc-desc  overflow" id="docDescWrap-3" style='display:none'>
			       <span id="docDesc-3" class="doc-desc-all">
				      ${record.description}
				   </span>
			       <span class="ic-pointer"></span>
			   </p>
			</div>
		</#if>
		<div class='detail' style='background:#f9f9f9;'>
		  <div class='detail-list document-list'>
		    <#if record.readurl?index_of('.swf')!=-1>
		      <#assign  url=record.readurl?substring(0,record.readurl?index_of('.swf'))/>
		      <input type='hidden' name='readurl' value='${url}'>
		    </#if>
		    <#if record.docsuffix?lower_case=='ppt' || record.docsuffix?lower_case=='pptx' || record.docsuffix?lower_case=='pps' || record.docsuffix?lower_case=='pot'>
		        <div class='doc-ppt'>
		          <a class="media" href="${url}1.swf"></a>
		        </div>
		       <div id='pj-kong' class='pj-kong'>
				      <img src="/image/kong.gif" alt="空图片" height='800' width='885' class='lazy'>
				</div>
		        <#if (record.docpages?number gt 1)>
		        <div class="ui-toolbar-middle clearfix">
				        <span class="arrow-btn left-btn iconfont disable-btn" id='left-btn'></span>
				        <div class="ipt-box">
				            <input type='hidden' name='currvalue' value=''>
				            <input type="text" value="1" onkeydown='onlyNum()' onblur='onblus()'>
				            <span class="total-page">/&nbsp;${record.docpages?number}</span>
				        </div>
				        <span  class="arrow-btn right-btn iconfont enable-btn" id='right-btn'></span>
				   </div>
		        </#if>
		    <#else>
		     <#if (record.docpages?number gte 3)>
               <a class="media" href="${url}1.swf"></a>
               <div class='secant_line'></div> 
               <a class="media" href="${url}2.swf"></a>
               <div class='secant_line'></div>
               <a class="media" href="${url}3.swf"></a>
             <#else>
               <#list 1..record.docpages?number as t>
                   <a class="media" href="${url}${t}.swf"></a>
               </#list>
             </#if>
              <#if (record.docpages)??>
				 <#if (record.docpages?number gt 3)>
					<div class="banner-more-btn">
						<span class="moreBtn goBtn" style='padding-left:40px;' data-pagesize='${record.docpages?number-3}' data-currentpage='1'>
						<span>还剩${record.docpages?number-3}页未读，</span><span class="fc2e">继续阅读</span>
						</span>
				    </div>
				    <p class="down-arrow goBtn" data-pagesize='${record.docpages?number-3}' data-currentpage='1'></p>
				 </#if>
			   </#if>
		    </#if>
		   
			 <div class="doc-shareto">
				<div class="shareto-share" style='padding-top:5px;'>
				  <#if (Session.user)??>
				   <#if (record.likedIds)??&&record.likedIds?length gt 0>
						    <#if record.likedIds?index_of(",")==-1>
							  <#if (record.likedIds?string==Session.user.userid?string)>
							    <span id='doclike' data-islike='1' data-likecount='${record.likesum}'>
	                                 <i class="z-icon-thank"></i>
		                             取消赞&nbsp;(${record.likesum})
		                        </span>
							  <#else>
							   <span id="doclike"  data-islike="0" data-likecount='${record.likesum}'>
							     <i class="z-icon-thank"></i>
							     ${record.likesum}&nbsp;人赞
				               </span>
							  </#if>
							<#else>
							 <#if record.likedIds?split(",")?seq_contains(Session.user.userid?string)>
							    <span id='doclike' data-islike='1' data-likecount='${record.likesum}'>
	                                 <i class="z-icon-thank"></i>
		                             取消赞&nbsp;(${record.likesum})
		                        </span>
							 <#else>
							    <span id='doclike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}&nbsp;人赞
		                       </span>
							 </#if>
							</#if>
					      <#else>
						   <span id='doclike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}&nbsp;人赞
		                   </span>
	                </#if>
					<#else>
					       <span id='doclike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}&nbsp;人赞
		                   </span>
					</#if>
					
					<#if (Session.user)??>
					<#if (record.collectIds)??&&record.collectIds?length gt 0>
						    <#if record.collectIds?index_of(",")==-1>
							  <#if (record.collectIds?string==Session.user.userid?string)>
							    <span id='doccoll' data-iscollect='1' data-collectcount='${record.collectsum}'>
	                                 <i class="z-icon-collect"></i>
		                             已收藏&nbsp;(${record.collectsum})
		                        </span>
							  <#else>
							   <span id="doccoll"  data-iscollect="0" data-collectcount='${record.collectsum}'>
							     <i class="z-icon-collect"></i>
							      ${record.collectsum}&nbsp;人收藏
				               </span>
							  </#if>
							<#else>
							 <#if record.collectIds?split(",")?seq_contains(Session.user.userid?string)>
							    <span id='doccoll' data-iscollect='1' data-collectcount='${record.collectsum}'>
	                                 <i class="z-icon-collect"></i>
		                                 已收藏&nbsp;(${record.collectsum})
		                        </span>
							 <#else>
							    <span id='doccoll'  data-iscollect='0' data-collectcount='${record.collectsum}'>
		                              <i class="z-icon-collect"></i>
		                              ${record.collectsum}&nbsp;人收藏
		                       </span>
							 </#if>
							</#if>
					      <#else>
						   <span id='doccoll'  data-iscollect='0' data-collectcount='${record.collectsum}'>
		                              <i class="z-icon-collect"></i>
		                              ${record.collectsum}&nbsp;人收藏
		                   </span>
	                </#if>
					<#else>
					       <span id='doccoll'  data-iscollect='0' data-collectcount='${record.collectsum}'>
		                              <i class="z-icon-collect"></i>
		                              ${record.collectsum}&nbsp;人收藏
		                   </span>
					</#if>

                   <span id="docreport" data-commentbyid="${record.userid}" data-topiccommentid="${record.id}" data-reporttype="4">
							   <i class="z-icon-report-o"></i>举报
				   </span>
				  分享到
				</div>
				<div class="shareto-menu bdsharebuttonbox bdshare-button-style1-16" style='padding-top:3px;'>
                    <a href="javascript:void(0);" class="log_sina png" title="分享到新浪微博"></a>
                    <a href="javascript:void(0);" class="log_qq png"  title="分享到QQ空间"></a>
                    <a href="javascript:void(0);" class="log_wx png" title="分享到微信"></a>
				</div>
			</div>
		  </div>
		</div>
		<div class="doc-wrap" id='doc-commcount' data-num='${record.commentList.count}'><#if (record.commentList)??>用户评价(${record.commentList.count})</#if></div>
		<div class="detail doc-detail">
		
		 <div class='detailcomment-list' style='padding-bottom:14px;'>
		 <#if record.commentList.list?size lte 0>
		     <p class='uncomment'>暂无点评，你也可以点评哦:) </p>
		 </#if>
		 <#if (record.commentList.list)?? &&record.commentList.list?size gt 0>
		     <#list  record.commentList.list  as booklist>
		       <div class='item'>
		         <div class='media-left'>
		           <a class='uhead' href='/myHome/getHomePage/${booklist.userid}' data-userid='${booklist.userid}' target='_blank'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname lazy' src="${booklist.userHeadIcon}" alt="个人头像"  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname lazy' src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		            </a>
		             <#if (booklist.commentbyName)??>
		                 <span class="uname" data-userid='${booklist.userid}'>
			                <a href='/myHome/getHomePage/${booklist.userid}' target='_blank'>${booklist.userName}</a>
			                  <span class="desc">回复</span>
			                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
			                <a href='/myHome/getHomePage/${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
			                </span>
			             </span>
		             <#else>
		             <a class='uhead' href='/myHome/getHomePage/${booklist.userid}' data-userid='${booklist.userid}' target='_blank'>
			             <span class="uname" data-userid='${booklist.userid}' data-moduletype='1'>
			                ${booklist.userName}
			             </span>
			         </a>
		             </#if>
		         </div>
				 
		         <div class='media-right'>
		           <div class="reason"><#if (booklist.comments)??>${booklist.comments?html}</#if></div>
		           <div class="operate">
		             <a itemprop="url" class="answer-date-link meta-item" target="_self" href="javascript:;">
		               <#if (booklist.createtime?string("yyyy-MM-dd"))??>
		                  ${booklist.createtime?string("yyyy-MM-dd")}
		               </#if>
		             </a>
		             <a href="javascript:;" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox" data-recommend='${booklist.userid}' data-recommendname='${booklist.userName}' data-relationid='${booklist.id}'> 
						<i class="z-icon-comment"></i>评论
				     </a>
		           </div>
		         </div>
		       </div>
		     </#list>
		     <#if (record.commentList.last gt 1)>
			   <a data-pageno='1' data-sumpage='${record.commentList.last}' class="zg-btn-white zu-button-more loadmore">更多</a>
			 </#if>
			 
		  </#if>
		   </div>
		  </div>
		  
		
		
		<#--判断是否登录-->
		<#if (Session.user)??>
		    <div class='doc-evaluation'>
			  <a href='/myHome/getHomePage/${Session.user.userid}'  target='_blank' data-userid='<#if (Session.user)??> ${Session.user.userid}</#if>'>
			   <#if (Session.user)??>
				 <#if (Session.user.headicon)??>
				   <img src="${Session.user.headicon}" alt="个人头像" class='zm-list-avatar lazy' data-userid='${Session.user.userid}' data-moduletype='1'>
				 <#else>
				   <img src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" class='zm-list-avatar lazy' data-userid='${Session.user.userid}' data-moduletype='1'>
				 </#if>
			  </#if>
				 <span class="mycommentinfo">评价语</span>
			  </a>
			</div>
			<div class='publishbook doc-public'>
	             <textarea title="在这里输入回答" class='commentcontent' placeholder='在这里输入评价语...'  rows="3" cols="20"></textarea>	
	        </div>
	        <div class="zm-command clearfix doc-btn">
			   <span class="zg-right">
			      <b class="ic ic-msg" style="background-position: -47px -144px;display:none"></b>
				  <span class="item-msg-content" style='display:none'>文字超出最大限制</span>
				  <a class="submit-button zg-btn-blue" name="savedocs" href="javascript:">发布</a>
			   </span>
			</div>	
		<#else>
		     <div class="col-md-6 col-md-offset-3 login-after-comments" style="text-align: center;">
				<span class="hidden-xs" style="line-height:50px;font-size: 16px;color: #333;">登录后才能发布评价</span><br>
				<span class="comments-login-register hidden-xs loginprompt-null" style="margin-left: 20px;margin-right:10px;"><a href='javascript:void(0);' onclick="toLogin();">登录</a> |  <a  target='_blank' href='/registration.html'>立即注册</a> </span>
			</div>
		</#if>	
	    </div>
        <div class="plus-main-sidebar">
		     <div class="searchresright share-plaza">
	            <a class="share-icon bg-index" target="_self" href="/sharein/searchuploadFile?type=0"></a>
				<a target="_self" href="javascript:void(0)" class="text">
				  文档---话题---书籍---课程---文章---站点
				</a>
	         </div>
			 <div class='zm-relate-content'>
			   <div class='zm-side-section'>
					 <div class="zm-side-section-inner">
					      <#if (record.downloadUsers)??>
					       <#list record.downloadUsers as downlist>
						     <#if (Session.user)??>
					          <#if downlist.userid?string == Session.user.userid?string>
					            <#assign isdown=1 />
					          <#else>
					            <#assign isdown=0 />
					          </#if> 
							 </#if>
					       </#list>
					      </#if>
						  <#if (Session.account)??>
							  <button  class="follow-button zg-follow zg-btn-green" id="docfollow" data-downvalue="${record.downvalue}" data-sumvalue='${Session.account.points}' data-isdown='<#if (isdown)??>${isdown}</#if>'>
								下载文档
							  </button>
						  <#else>
                              <button  class="follow-button zg-follow zg-btn-green docfollow" onclick="toLogin();" data-downvalue="${record.downvalue}" data-sumvalue='${Session.account.points}' data-isdown='<#if (isdown)??>${isdown}</#if>'>
                                                                             下载文档
                              </button>
                          </#if>
						  <div style='float:left'>
						  <span class="zg-gray-normal" style='display:inherit;'>
							<a href="">
								 <strong>
									 ${record.downsum}
								 </strong>
							</a>
							人下载了这篇文档
						 </span>
						   <span style='font-size:14px;color:#666;'>
						    <#if record.downvalue!=0>
							     <a href="javascript:void(0)">
									 <strong>
										 ${record.downvalue}
									 </strong>
								</a>
							         财富值
							<#else>
							  免费
						    </#if>
						   </span>
						 </div>
					<div class="zh-books-followers-sidebar" style='clear:both'>
						<#if (record.downloadUsers)??>
						<div class="list zu-small-avatar-list zg-clear">
						 <#if record.downloadUsers?size gt 6>
							<#list record.downloadUsers[0..5] as colllist>
							   <a title="${colllist.username}"  class="zm-item-link-avatar uname" target='_blank' href='/myHome/getHomePage/${colllist.userid}'  data-moduletype='1'>
								   <#if (colllist.headicon)??>
									 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy"  data-userid="${colllist.userid}" alt="个人头像">
								   <#else>
									  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}"  alt="个人头像">
								   </#if>
								</a>
						    </#list>
							<a class='fa-ellipsis-h'>...</a>
						  <#else>
						     <#list record.downloadUsers as colllist>
							   <a title="${colllist.username}"  class="zm-item-link-avatar uname" target='_blank' href='/myHome/getHomePage/${colllist.userid}' data-moduletype='1'>
								   <#if (colllist.headicon)??>
									 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}"  alt="个人头像">
								   <#else>
									  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}"  alt="个人头像">
								   </#if>
								</a>
						    </#list> 
					      </#if>
						</div>
						<#if record.downloadUsers?size gt 6>
							<div class="list zu-small-avatar-list zg-clear" style='display:none'>
								<#list record.downloadUsers as colllist>
								   <a title="${colllist.username}"  class="zm-item-link-avatar uname" target='_blank' href='/myHome/getHomePage/${colllist.userid}' data-moduletype='1'>
									   <#if (colllist.headicon)??>
										 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}"  alt="个人头像">
									   <#else>
										  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}"  alt="个人头像">
									   </#if>
									</a>
							   </#list>
						     </div>
					     </#if>
						</#if>
					</div>
					</div>
			   </div>
			   <div class='zm-side-section'>
			      <div class='zm-side-section-inner'>
				    <div class='zh-question-related-books clearfix'>
					  <h3>相关文档</h3>
					  <#if (record.relatedList)??>
					    <ul>
						 <#list record.relatedList as samelist>
						  <li>
							 <div class='related-doc-right'>
							        <b class="ic ic-${samelist.docSuffix?lower_case} mr14"></b>
							        <a href='/docs/getDocsDetail/${samelist.data_id}' target='_blank' title='${samelist.title}'><span class='SidebarListNav-label'  style='max-width:190px'>${samelist.title}</span></a>
							        <span class='evaluate-label'><#if (samelist.replySum)??>${samelist.replySum}<#else>0</#if>人评价</span>
							 </div>
						  </li>
						 </#list>
						</ul>
					  </#if>
					</div>
					
				  </div>
			   </div>
			 </div>
			 <div class='pj_jsonp ad_exposure'>
	            <img src='/image/ad_exposure_5.jpg' alt="广告" width='308' height='246' class='lazy'>
	            <div class='advertising-direction'>广告</div>
	         </div>
        </div>
      </div> 
	</div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
    <a id="docbacktop" title="回到顶部" href="#doctop" class='back-to-top' style="bottom: 300px; display: none;"></a>
    <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>    
    <script type="text/javascript" src="/scripts/jquery.media.js"></script>
    <script type="text/javascript" src="/scripts/pj_mycenterdocument.js" charset="utf-8"></script>
    <script type="text/javascript" src="/scripts/share.js"></script>

  </body>

</html>
