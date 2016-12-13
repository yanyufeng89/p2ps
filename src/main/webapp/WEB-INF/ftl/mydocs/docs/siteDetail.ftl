<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
       ${record.title}-JobPlus
    </title>
    <meta name="description" content="JobPlus网是国内领先的企业知识库公共平台,为创业者、大学生、职场人士提供各个实用网站的链接,JobPlus为7亿职场人员提供优质学习分享社区,JobPlus是你最值得信赖的终身学习伙伴。">
    <meta name="keywords" content="jobplus,知识分享,创新网站,创业网站,IT网站,互联网,咨询网站,财务网站,工业技术网站,工程网站,市场管理网站,销售管理网站,供应链网站,生产管理网站,设计网站,创意网站,翻译网站,互联网+,新媒体网站,影视网站">
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    <link rel='stylesheet' type='text/css' href='/css/pj_bookdetail.css'>
    <div id='wx_pic' style='margin:0 auto;display:none;'>
          <img src='/image/logo_1.jpg' />
    </div>
  </head>
  <body id='sitetop'>
    <div  class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='bookdescrition'>
		<span>JobPlus知识库>站点<#list record.sitetype?split(",") as item><#assign types=item?split(":")/>>${types[1]}</#list></span>
      </div>
      <div id="content">
        <input type='hidden' name='siteid' value='${record.id}'>
        <input type='hidden' name='sitesname' value='${record.title}'>
        <input type='hidden' name='sitescontent' value='${record.intro?html}'>
        <input type='hidden' name='siteCreatePerson' value='${record.userid}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner">
               <div class='basic book-basic'>
                   <div class='infor after'>
                      <#if (record)??>
	                       <div class='infor-right'>
                             <div class='bookname' style='margin-top:0'>
                                <span class='courseinfo'>[站点]&nbsp;${record.title?html}</span>
                             </div>
                             <div class='press'>
                                <span class="tip">链接:</span>
                                <a href='${record.siteurl}' target='_blank'  id='siteurl' rel='nofollow'>${record.siteurl}</a>
                             </div>
                             <div class='brief book-height course-height tool-height'>
                               <#if (record.intro)??>
                               <div class='showbrief bookbrief' id='sitebrief' data-brief='${record.intro?html}'>
                                <span class='tip'>简介:</span>
                                <span class='brief-content'>${record.intro?trim}</span>
                                <#--<span class="slidedown slide" style="display: none;">展示全部</span>-->
                                <div class="slideup slide" style="display: none;"><span>收起</span></div>
                               </div>
                               </#if>
                             </div>
	                       </div>
                      </#if>
                   </div>
                   <div class='additional after resCollectionButton'>
                     <div class="topic-shareto">
                        <div class='shareto-share'>
                          <span><a href='/myHome/getHomePage/${record.userid}'  target='_blank' data-userid='${record.userid}'>${record.objCreator.username}</a></span>
                          <span>${record.createtime?string("yyyy-MM-dd")}</span>
						  <span><i class="z-icon-preview"></i>${record.readsum}人浏览</span>
                          
                          <#if (record.likedIds)??&&record.likedIds?length gt 0>
						    <#if record.likedIds?index_of(",")==-1>
							  <#if (record.likedIds?string==(Session.user.userid?string)!)>
							    <span id='sitelike' data-islike='1' data-likecount='${record.likesum}'>
	                                 <i class="z-icon-thank"></i>
		                             取消赞(${record.likesum})
		                        </span>
							  <#else>
							   <span id='sitelike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}人赞
		                       </span>
							  </#if>
							<#else>
							 <#if record.likedIds?split(",")?seq_contains((Session.user.userid?string)!)>
							    <span id='sitelike' data-islike='1' data-likecount='${record.likesum}'>
	                                 <i class="z-icon-thank"></i>
		                             取消赞(${record.likesum})
		                        </span>
							 <#else>
							    <span id='sitelike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}人赞
		                       </span>
							 </#if>
							</#if>
					      <#else>
						   <span id='sitelike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}人赞
		                   </span>
	                      </#if>
                                                                  
							
						</div>
				       <div class="shareto-menu bdsharebuttonbox bdshare-button-style1-16">
					     <span> 分享到</span>
                           <a href="javascript:void(0);" class="log_sina png" title="分享到新浪微博"></a>
                           <a href="javascript:void(0);" class="log_qq png"  title="分享到QQ空间"></a>
                           <a href="javascript:void(0);" class="log_wx png" title="分享到微信"></a>
					   </div>
				    </div>
                   </div>
               </div>
           </div>
        
		<div class='userrecommend' id='site-commcount' data-num='${record.recommendsum}'>用户推荐(${record.recommendsum})</div>
		<div class='detail'>
		  <div class='detail-list'>
		  <#if (record.commentList)??>
		     <#list  record.commentList.list as booklist>
		       <div class='item'>
		         <div class='media-left'>
		           <a class='uhead' href='/myHome/getHomePage/${booklist.userid}' target='_blank'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname lazy <#if booklist.usertype==2>company-img</#if>' src="${booklist.userHeadIcon}" alt="个人头像"  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname lazy <#if booklist.usertype==2>company-img</#if>' src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		             <#if (booklist.commentbyName)??>
			               <a href='/myHome/getHomePage/${booklist.userid}' target='_blank' class='uname' data-userid='${booklist.userid}'> 
						     ${booklist.userName}
						   </a>
			                <span class="desc">&nbsp;回复&nbsp;</span>
			                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
			                   <a href='/myHome/getHomePage/${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
			                </span>
		             <#else>
			                <a href='/myHome/getHomePage/${booklist.userid}' class='uname' data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
		             </#if>
		           </a>
		         </div>
		         <div class='media-right'>
		           <div class="reason">${booklist.recommend?html}</div>
		           <div class="operate">
		             <a itemprop="url" class="answer-date-link meta-item" target="_self" href="javascript:;">
		               <#if (booklist.createtime?string("yyyy-MM-dd"))??>
		                  ${booklist.createtime?string("yyyy-MM-dd")}
		               </#if>
		             </a>
		             <a href="javascript:;" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox" data-recommend='${booklist.userid}' data-recommendname='${booklist.userName}' data-relationid='${booklist.id}'> 
						<i class="z-icon-comment"></i>评论
				     </a>
					 <a href="javascript:;" class="meta-item zu-autohide js-report" data-commentbyid="${booklist.userid}" data-topiccommentid="${booklist.id}" data-reporttype='6'>
					    <i class="z-icon-report-o"></i>举报
					 </a>
		           </div>
		         </div>
		       </div>
		     </#list>
		     
		  </#if>
		  </div>
		   <#if (record.commentList.last gt 1)>
			   <a data-pageno='1' data-sumpage='${record.commentList.last}' class="zg-btn-white zu-button-more loadmore">更多</a>
		   </#if>
		</div>
		
		<#--判断是否登录-->
		<#if (Session.user)??>
		    <div class='mycomment'>
			  <a href='/myHome/getHomePage/${Session.user.userid}' target='_blank'>
				 <#if (Session.user.headicon)??>
				   <img src="${Session.user.headicon}" alt="个人头像" class='zm-list-avatar lazy <#if Session.user.usertype==2>company-img</#if>' data-userid='${Session.user.userid}' data-moduletype='1'>
				 <#else>
				   <img src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" class='zm-list-avatar lazy <#if Session.user.usertype==2>company-img</#if>' data-userid='${Session.user.userid}' data-moduletype='1'>
				 </#if>
				 <span class="mycommentinfo">推荐语</span>
			  </a>
			</div>
			<div class='publishbook'>
	             <textarea title="在这里输入回答" class='commentcontent' placeholder='在这里输入推荐语...'  rows="3" cols="20"></textarea>	
	        </div>
	        <div class="zm-command clearfix">
			   <span class="zg-right">
			      <b class="ic ic-msg" style="background-position: -47px -144px;display:none"></b>
				  <span class="item-msg-content" style='display:none'>文字超出最大限制</span>
				  <a class="submit-button zg-btn-blue" name="savebook" href="javascript:">发布</a>
			   </span>
			</div>
		<#else>
		   <div class="col-md-6 col-md-offset-3 login-after-comments" style="text-align: center;">
	           <span class="hidden-xs" style="line-height:50px;font-size: 16px;color: #333;">登录后才能发布评论</span><br>
	           <span class="comments-login-register hidden-xs loginprompt-null" style="margin-left: 20px;margin-right:10px;"><a href='javascript:void(0);' onclick="toLogin();">登录</a> |  <a  target='_blank' href='/registration.html'>立即注册</a> </span>
		   </div>
		</#if>		
	    </div>
        <div class="plus-main-sidebar">
		     <div class="searchresright share-plaza">
	            <a class="share-icon bg-index" target="_self" href="/sharein/searchuploadFile?type=5"></a>
	            <a target="_self" href="javascript:void(0)" class="text">
				 文档---话题---书籍---课程---文章---站点
				</a>
	         </div>
			 <div class='zm-relate-content'>
			   <div class='zm-side-section'>
					 <div class="zm-side-section-inner">
					 <#if (record.collectIds)??&&record.collectIds?length gt 0>
						    <#if record.collectIds?index_of(",")==-1>
							  <#if (record.collectIds?string==(Session.user.userid?string)!)>
							     <button data-follow="q:m:button" class="follow-button zg-follow zg-btn-white" id="sitefollowanswer" data-actiontype="1">
								  取消收藏
							     </button>
							  <#else>
							    <button data-follow="q:m:button" class="follow-button zg-follow zg-btn-green" id="sitefollowanswer" data-actiontype="0">
						  		  收藏站点
						        </button>
							  </#if>
							<#else>
							 <#if record.collectIds?split(",")?seq_contains((Session.user.userid?string)!)>
							    <button data-follow="q:m:button" class="follow-button zg-follow zg-btn-white" id="sitefollowanswer" data-actiontype="1">
								  取消收藏
							    </button>
							 <#else>
							    <button data-follow="q:m:button" class="follow-button zg-follow zg-btn-green" id="sitefollowanswer" data-actiontype="0">
						  		  收藏站点
						        </button>
							 </#if>
							</#if>
					      <#else>
						     <button data-follow="q:m:button" class="follow-button zg-follow zg-btn-green" id="sitefollowanswer" data-actiontype="0">
						  		  收藏站点
						     </button>
	                      </#if>
					<span class="zg-gray-normal">
					<a href="">
						 <strong>
							 ${record.collectsum}
						 </strong>
					</a>
					人收藏了这个站点
					</span>
					<div class="zh-books-followers-sidebar">
					<#if (record.collectUsers)??>
					<div class="list zu-small-avatar-list zg-clear">
					<#if record.collectUsers?size gt 6>
						<#list record.collectUsers[0..5] as colllist>
							<a title="${colllist.username}"  class="zm-item-link-avatar" href='/myHome/getHomePage/${colllist.userid}' target='_blank' data-moduletype='1'>
							   <#if (colllist.headicon)??>
								 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy <#if colllist.usertype==2>company-img</#if>"  data-userid="${colllist.userid}" alt="个人头像">
							   <#else>
								  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy <#if colllist.usertype==2>company-img</#if>"  data-userid="${colllist.userid}" alt="个人头像">
							   </#if>
							</a>
					   </#list>
					   <a class='fa-ellipsis-h'>...</a>
					 <#else>
					   <#list record.collectUsers as colllist>
							<a title="${colllist.username}"  class="zm-item-link-avatar" href='/myHome/getHomePage/${colllist.userid}' target='_blank'  data-moduletype='1'>
							   <#if (colllist.headicon)??>
								 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy <#if colllist.usertype==2>company-img</#if>" data-userid="${colllist.userid}"  alt="个人头像">
							   <#else>
								  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy <#if colllist.usertype==2>company-img</#if>" data-userid="${colllist.userid}"  alt="个人头像">
							   </#if>
							</a>
					   </#list>
					 </#if>
					</div>
					<#if record.collectUsers?size gt 6>
						<div class="list zu-small-avatar-list zg-clear" style='display:none'>
							<#list record.collectUsers as colllist>
								<a title="${colllist.username}"  class="zm-item-link-avatar" href='/myHome/getHomePage/${colllist.userid}' target='_blank' data-moduletype='1'>
								   <#if (colllist.headicon)??>
									 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy <#if colllist.usertype==2>company-img</#if>" data-userid="${colllist.userid}"  alt="个人头像">
								   <#else>
									  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy <#if colllist.usertype==2>company-img</#if>" data-userid="${colllist.userid}"  alt="个人头像">
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
					  <h3>相关站点</h3>
					  <#if (record.relatedList)??>
					    <ul>
						 <#list record.relatedList as samelist>
						  <li>
							 <div class='related-book-right'>
							        <a href='/sites/getSiteDetail/${samelist.data_id}' target='_blank' title='${samelist.title}'><span class='SidebarListNav-label'>${samelist.title}</span></a>
									<span class='evaluate-label'><#if (samelist.replySum)??> ${samelist.replySum}<#else>0</#if>人推荐</span>
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
	            <img src='/image/ad_exposure_10.jpg' class='lazy' alt="广告" width='308' height='246'>
	            <div class='advertising-direction'>广告</div>
	         </div>
        </div>
      </div> 
	</div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
    <a id="sitebacktop" title="回到顶部" href="#sitetop" class='back-to-top' style="bottom: 300px; display: none;"></a>
    <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>
    <script type="text/javascript" src="/scripts/pj_mycentersite.js"></script>
    <script type="text/javascript" src="/scripts/share.js"></script>
  </body>

</html>
