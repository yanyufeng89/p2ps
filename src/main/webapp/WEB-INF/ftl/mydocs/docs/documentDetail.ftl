<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
               文档详情
    </title> 
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    
    <link rel='stylesheet' type='text/css' href='/51jobplusCore/css/pj_bookdetail.css'>
  </head>
  <body id='doctop'>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='bookdescrition'>
        <span>聘加知识库>文档<#list record.doctype?split(",") as doctype><#assign types=doctype?split(":")/>>${types[1]}</#list></span>
      </div>
      <div id="content">
        <input type='hidden' name='docid' value='${record.id}'>
        <input type='hidden' name='docname' value='${record.title}'>
        <input type='hidden' name='docCreatePerson' value='${record.userid}'>
        <input type='hidden' name='docsuffix' value='${record.docsuffix}'>
        <input type='hidden' name='docurl' value='${record.readurl}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner" style='border-bottom:none'>
               <div class='basic doc-basic'>
                   <div class='infor after'>
                      <#if (record)??>
	                       <div class='infor-right'>
                             <div class='bookname'>
                                <b class="ic ic-${record.docsuffix} mr14"></b>
                               <#if record.title?index_of(record.docsuffix)!=-1>
                                 <#assign title=record.title?substring(0,record.title?index_of(record.docsuffix)?number-1) />
                                 <span class='courseinfo'>${title}</span>
                               <#else>
                                 <span class='courseinfo'>${record.title}</span>
                               </#if>
                             </div>
                             <div class='brief book-height course-height tool-height'>
                               <#if (record.description)??>
                               <div class='showbrief bookbrief' data-brief='${record.description}'>
                                <span class="tip">简介：</span>
                                ${record.description}
                                <#if record.description?length gt 200>
                                <span class="slidedown slide" style="display: block;">展示全部</span>
                                <div class="slideup slide" style="display: none;"><span>收起</span></div>
                                </#if>
                               <div>
                               </#if>
                             </div>
	                       </div>
                      </#if>
                   </div>
               </div>
           </div>
        </div>
        </div>
		<div class='authorinfo'>
		  <#if (record)??>
		   <a href='/51jobplusCore/myHome/getHomePage?userid=${record.objCreator.userid}'>
		   <#if (record.objCreator.headicon)??>
		    <img class="uname" src="${record.objCreator.headicon}" alt="" data-userid="${record.objCreator.userid}" data-moduletype="1">
		   <#else>
		    <img class="uname" src="/51jobplusCore/image/1b48b5a75c71597_100x100.jpg" alt="" data-userid="${record.objCreator.userid}" data-moduletype="1">
		   </#if>
		  </a>
			<span class='author-link' data-userid="${record.objCreator.userid}">
			   <a href='/51jobplusCore/myHome/getHomePage?userid=${record.objCreator.userid}'>${record.objCreator.username}</a>
			</span>
			<span class='createtime'>${record.createtime?string("yyyy-MM-dd")}</span>
			<span class='likesum'>${record.likesum}人赞</span>
			<span class='readsum'>${record.readsum}人浏览</span>
			<span class='collectsum'>${record.collectsum}人收藏</span>
		  </#if>
		</div>
		<div class='detail'>
		  <div class='detail-list' style='padding: 15px 15px 33px 15px;'>
		    <#if record.readurl?index_of('.swf')!=-1>
		      <#assign  url=record.readurl?substring(0,record.readurl?index_of('.swf'))/>
		      <input type='hidden' name='readurl' value='${url}'>
		    </#if>
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
			 <div class="shareto" style='float:right'>
				<div class="shareto-share">
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
							   <i class="z-icon-report-o"></i>
							   举报
				   </span>
				  分享到
				</div>
				<div class="shareto-menu bdsharebuttonbox bdshare-button-style1-16" style='padding-top:3px;'>
                    <a href="javascript:void(0);" onclick="toShare(1,'${record.title}');" class="log_sina png" title="分享到新浪微博"></a>
                    <a href="javascript:void(0);" onclick="toShare(2,'${record.title}');" class="log_qq png"  title="分享到QQ空间"></a>
                    <a href="javascript:void(0);" onclick="toShare(3,'${record.title}');" class="log_wx png" title="分享到微信"></a>
				</div>
			</div>
		  </div>
		</div>
		<div class="userrecommend">
		   <#if (record.commentList)??>
		          用户评价(${record.commentList.count})
		   </#if>
		</div>
		<div class="detail">
		
		 <div class='detailcomment-list'>
		  <#if (record.commentList)??>
		     <#list  record.commentList.list as booklist>
		       <div class='item'>
		         <div class='media-left'>
		           <a class='uhead' href='/51jobplusCore/myHome/getHomePage?userid=${booklist.userid}' data-userid='${booklist.userid}' target='_blank'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname' src="${booklist.userHeadIcon}" alt=""  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname' src='/51jobplusCore/image/1b48b5a75c71597_100x100.jpg' alt="" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		            </a>
		             <#if (booklist.commentbyName)??>
		                 <span class="uname" data-userid='${booklist.userid}'>
			                <a href='/51jobplusCore/myHome/getHomePage?userid=${booklist.userid}' target='_blank'>${booklist.userName}</a>
			                  <span class="desc">回复</span>
			                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
			                <a href='/51jobplusCore/myHome/getHomePage?userid=${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
			                </span>
			             </span>
		             <#else>
		             <a class='uhead' href='/51jobplusCore/myHome/getHomePage?userid=${booklist.userid}' data-userid='${booklist.userid}' target='_blank'>
			             <span class="uname" data-userid='${booklist.userid}' data-moduletype='1'>
			                ${booklist.userName}
			             </span>
			         </a>
		             </#if>
		         </div>
				 
		         <div class='media-right'>
		           <div class="reason"><#if (booklist.comments)??>${booklist.comments?html}</#if></div>
		           <div class="operate">
		             <a itemprop="url" class="answer-date-link meta-item" target="_blank" href="javascript:;">
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
		    <div class='mycomment'>
			  <a href='/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}' data-userid='<#if (Session.user)??> ${Session.user.userid}</#if>'>
			   <#if (Session.user)??>
				 <#if (Session.user.headicon)??>
				   <img src="${Session.user.headicon}" alt="" class='zm-list-avatar' data-userid='${Session.user.userid}' data-moduletype='1'>
				 <#else>
				   <img src='/51jobplusCore/image/1b48b5a75c71597_100x100.jpg' alt="" class='zm-list-avatar' data-userid='${Session.user.userid}' data-moduletype='1'>
				 </#if>
			  </#if>
				 <span class="mycommentinfo">评价语</span>
			  </a>
			</div>
			<div class='publishbook'>
	             <textarea title="在这里输入回答" class='commentcontent' placeholder='在这里输入评价语...'  rows="3" cols="20"></textarea>	
	        </div>
	        <div class="zm-command clearfix">
			   <span class="zg-right">
				  <a class="submit-button zg-btn-blue" name="savedocs" href="javascript:">发布评价</a>
			   </span>
			</div>	
		<#else>
		     <div class="col-md-6 col-md-offset-3 login-after-comments" style="text-align: center;">
				<span class="hidden-xs" style="line-height:50px;font-size: 16px;color: #090909;">登录后才能发布评价</span><br>
				<span class="comments-login-register hidden-xs loginprompt-null" style="margin-left: 20px;margin-right:10px;"><a  target='_blank' href='javascript:toLogin();'>登录</a> |  <a  target='_blank' href='/51jobplusCore/registration.html'>立即注册</a> </span>
			</div>
		</#if>	
	    </div>
        <div class="plus-main-sidebar">
		     <div class="searchresright">
	            <a class="upload-btn bg-index" href="/51jobplusCore/sharein/searchuploadFile"></a>
	         </div>
			 <div class='zm-relate-book'>
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
									 ${record.collectsum}
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
					<#list record.downloadUsers as colllist>

				          <a title="${colllist.username}"  class="zm-item-link-avatar uname" href='/51jobplusCore/myHome/getHomePage?userid=${colllist.userid}' data-userid="${colllist.userid}" data-moduletype='1'>
			
						   <#if (colllist.headicon)??>
							 <img src="${colllist.headicon}" class="zm-item-img-avatar">
						   <#else>
							  <img src="/51jobplusCore/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar">
						   </#if>
						</a>
				   </#list>
					</div>
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
							 <div class='related-book-right'>
							   <span>
							        <b class="ic ic-${samelist.docSuffix} mr14"></b>
							        <#if samelist.title?index_of(samelist.docSuffix)!=-1>
							          <#assign sametilte=samelist.title?substring(0,samelist.title?index_of(samelist.docSuffix)?number-1) />
							          <a href='/51jobplusCore/docs/getDocsDetail?id=${samelist.data_id}'>${sametilte}</a>
							        <#else>
							          <a href='/51jobplusCore/docs/getDocsDetail?id=${samelist.data_id}'>${samelist.title}</a>
							        </#if>
							   </span>
							   <span>
									   <#if (samelist.replySum)??>
									    ${samelist.replySum}
									   <#else>
									    0
									   </#if>
									      人评价
							   </span>
							 </div>
						  </li>
						 </#list>
						</ul>
					  </#if>
					</div>
				  </div>
			   </div>
			 </div>
        </div>
      </div> 
	</div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
    <a id="#docbacktop" title="回到顶部" href="#doctop" style="bottom: 300px; display: none;"></a>
    <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>    
    <script type="text/javascript" src="/51jobplusCore/scripts/jquery.media.js"></script>
    <script type="text/javascript" src="/51jobplusCore/scripts/pj_mycenterdocument.js"></script>
    

  </body>

</html>
