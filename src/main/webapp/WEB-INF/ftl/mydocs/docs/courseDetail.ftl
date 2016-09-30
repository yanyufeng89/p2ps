<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
               ${record.coursesname}_JobPlus
    </title> 
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    <link rel='stylesheet' type='text/css' href='/css/pj_bookdetail.css'>
  </head>
  <body id='coursetop'>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='bookdescrition'>
		<span>JobPlus知识库>课程<#list record.coursestype?split(",") as item><#assign types=item?split(":")/>>${types[1]}</#list></span>
      </div>
      <div id="content">
        <input type='hidden' name='courseid' value='${record.id}'>
        <input type='hidden' name='coursename' value='${record.coursesname}'>
        <input type='hidden' name='courseCreatePerson' value='${record.userid}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner">
               <div class='basic book-basic'>
                   <div class='infor after'>
                      <#if (record)??>
	                       <div class='infor-right'>
                             <div class='bookname'>
                                <span class='tip'>[课程]</span>
                                <span class='courseinfo'>${record.coursesname}</span>
                             </div>
                             <div class='press'>
                                <span class="tip">链接:</span>
                                <a href='${record.coursesurl}' target='_blank' id='courseurl'>${record.coursesurl}</a>
                             </div>
                             <div class='brief book-height course-height tool-height'>
                               <#if (record.intro)??>
                               <div class='showbrief bookbrief' data-brief='${record.intro}'>
                                <span class="tip">简介:</span>
                                ${record.intro}
                                <#if record.intro?length gt 300>
                                <span class="slidedown slide" style="display: block;">展示全部</span>
                                <div class="slideup slide" style="display: none;"><span>收起</span></div>
                                </#if>
                               <div>
                               </#if>
                             </div>
	                       </div>
                      </#if>
                   </div>
                   <div class='additional after resCollectionButton'>
                     <div class="shareto">
                        <div class='shareto-share'>
                          <span><i class="z-icon-preview"></i>${record.readsum}人浏览</span>
                          
                          <#if (record.likedIds)??&&record.likedIds?length gt 0>
						    <#if record.likedIds?index_of(",")==-1>
							  <#if (record.likedIds?string==(Session.user.userid?string)!)>
							    <span id='courselike' data-islike='1' data-likecount='${record.likesum}'>
	                                 <i class="z-icon-thank"></i>
		                             取消赞(${record.likesum})
		                        </span>
							  <#else>
							   <span id='courselike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}人赞
		                       </span>
							  </#if>
							<#else>
							 <#if record.likedIds?split(",")?seq_contains((Session.user.userid?string)!)>
							    <span id='courselike' data-islike='1' data-likecount='${record.likesum}'>
	                                 <i class="z-icon-thank"></i>
		                             取消赞(${record.likesum})
		                        </span>
							 <#else>
							    <span id='courselike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}人赞
		                       </span>
							 </#if>
							</#if>
					      <#else>
						   <span id='courselike'  data-islike='0' data-likecount='${record.likesum}'>
		                               <i class="z-icon-thank"></i>
		                               ${record.likesum}人赞
		                   </span>
	                      </#if>
                                                                分享到
						</div>
				        <div class="shareto-menu bdsharebuttonbox bdshare-button-style1-16">
                            <a href="javascript:void(0);" onclick="toShare(1,'${record.coursesname}');" class="log_sina png" title="分享到新浪微博"></a>
                            <a href="javascript:void(0);" onclick="toShare(2,'${record.coursesname}');" class="log_qq png"  title="分享到QQ空间"></a>
                            <a href="javascript:void(0);" onclick="toShare(3,'${record.coursesname}');" class="log_wx png" title="分享到微信"></a>
					    </div>
				    </div>
                   </div>
               </div>
           </div>
        </div>
        </div>
		<div class='userrecommend'>
		      用户推荐(${record.recommendsum})
		</div>
		<div class='detail'>
		  <div class='detail-list'>
		  <#if (record.commentList)??>
		     <#list  record.commentList.list as booklist>
		       <div class='item'>
		         <div class='media-left'>
		           <a class='uhead' href='/myHome/getHomePage?userid=${booklist.userid}' target='_blank' data-userid='${booklist.userid}'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname' src="${booklist.userHeadIcon}" alt=""  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname' src='/image/1b48b5a75c71597_100x100.jpg' alt="" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		             <#if (booklist.commentbyName)??>
		               <a href='/myHome/getHomePage?userid=${booklist.userid}' class="uname" data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
		                  <span class="desc">&nbsp;回复&nbsp;</span>
		                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
		                  <a href='/myHome/getHomePage?userid=${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
		               </span>
		             <#else>
			                <a href='/myHome/getHomePage?userid=${booklist.userid}' class='uname' data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>   
		             </#if>
		           </a>
		         </div>
		         <div class='media-right'>
		           <div class="reason">${booklist.recommend?html}</div>
		           <div class="operate">
		             <a itemprop="url" class="answer-date-link meta-item" target="_blank" href="javascript:;">
		               <#if (booklist.createtime?string("yyyy-MM-dd"))??>
		                  ${booklist.createtime?string("yyyy-MM-dd")}
		               </#if>
		             </a>
		             <a href="javascript:;" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox" data-relationid='${booklist.id}' data-recommend='${booklist.userid}' data-recommendname='${booklist.userName}'> 
						<i class="z-icon-comment"></i>评论
				     </a>
					 <a href="javascript:;" class="meta-item zu-autohide js-report" data-commentbyid="${booklist.userid}" data-topiccommentid="${booklist.id}" data-reporttype='3'>
					    <i class="z-icon-report-o"></i>举报
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
		  <a href='/myHome/getHomePage?userid=${Session.user.userid}' target='_blank' data-userid='${Session.user.userid}'>
		     <#if (Session.user.headicon)??>
		       <img src="${Session.user.headicon}" alt="" class='zm-list-avatar' data-userid='${Session.user.userid}' data-moduletype='1'>
			 <#else>
			   <img src='/image/1b48b5a75c71597_100x100.jpg' alt="" class='zm-list-avatar' data-userid='${Session.user.userid}' data-moduletype='1'>
			 </#if>
			 <span class="mycommentinfo">推荐语</span>
		  </a>
		</div>
		<div class='publishbook'>
             <textarea title="在这里输入回答" class='commentcontent' placeholder='在这里输入推荐语...'  rows="3" cols="20"></textarea>	
        </div>
        <div class="zm-command clearfix">
		   <span class="zg-right">
			  <a class="submit-button zg-btn-blue" name="savebook" href="javascript:">发布</a>
		   </span>
		</div>
		<#else>
		     <div class="col-md-6 col-md-offset-3 login-after-comments" style="text-align: center;">
	               <span class="hidden-xs" style="line-height:50px;font-size: 16px;color: #090909;">登录后才能发布评论</span><br>
	               <span class="comments-login-register hidden-xs loginprompt-null" style="margin-left: 20px;margin-right:10px;"><a href='javascript:void(0);' onclick="toLogin();">登录</a> |  <a  target='_blank' href='/registration.html'>立即注册</a> </span>
			 </div>	
		</#if>
	    </div>
        <div class="plus-main-sidebar">
		     <div class="searchresright share-plaza">
	           <a class="share-icon bg-index" href="/sharein/searchuploadFile?type=3"></a>
	             <a target="_blank" href="javascript:void(0)" class="text">
				     文档---话题---书籍---课程---文章---站点
				 </a>
	         </div>
			 <div class='zm-relate-content'>
			   <div class='zm-side-section'>
					 <div class="zm-side-section-inner">
					     <#if (record.collectIds)??&&record.collectIds?length gt 0>
						    <#if record.collectIds?index_of(",")==-1>
							  <#if (record.collectIds?string==(Session.user.userid?string)!)>
							     <button  class="follow-button zg-follow zg-btn-white" id="coursefollowanswer" data-actiontype="1">
								  取消收藏
							     </button>
							  <#else>
							    <button  class="follow-button zg-follow zg-btn-green" id="coursefollowanswer" data-actiontype="0">
						  		  收藏课程
						        </button>
							  </#if>
							<#else>
							 <#if record.collectIds?split(",")?seq_contains((Session.user.userid?string)!)>
							    <button  class="follow-button zg-follow zg-btn-white" id="coursefollowanswer" data-actiontype="1">
								  取消收藏
							    </button>
							 <#else>
							    <button  class="follow-button zg-follow zg-btn-green" id="coursefollowanswer" data-actiontype="0">
						  		  收藏课程
						        </button>
							 </#if>
							</#if>
					      <#else>
						     <button class="follow-button zg-follow zg-btn-green" id="coursefollowanswer" data-actiontype="0">
						  		  收藏课程
						     </button>
	                      </#if>
				
					 
					<span class="zg-gray-normal">
					<a href="">
						 <strong>
							 ${record.collectsum}
						 </strong>
					</a>
					人收藏了这节课程
					</span>
					<div class="zh-books-followers-sidebar">
					<#if (record.collectUsers)??>

					<div class="list zu-small-avatar-list zg-clear">
					<#list record.collectUsers as colllist>
						<a title="${colllist.username}"  class="zm-item-link-avatar" href='/myHome/getHomePage?userid=${colllist.userid}' target='_blank' data-userid="${colllist.userid}" data-moduletype='1'>
						   <#if (colllist.headicon)??>
							 <img src="${colllist.headicon}" class="zm-item-img-avatar">
						   <#else>
							  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar">
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
					  <h3>相关课程</h3>
					  <#if (record.relatedList)??>
					    <ul>
						 <#list record.relatedList as samelist>
						  <li>
							 <div class='related-book-right'>
							   <a href='/courses/getCourseDetail?id=${samelist.data_id}'>${samelist.title}</a>
							   <span class='num'>
									   <#if (samelist.replySum)??>${samelist.replySum}<#else>0</#if>人浏览
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
			 <div class='pj_jsonp ad_exposure'>
	            <img src='/image/ad_exposure_8.jpg' alt='' width='310' height='278'>
	         </div>
        </div>
      </div> 
	</div>
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
     <a id="coursebacktop" title="回到顶部" href="#coursetop" class='back-to-top' style="bottom: 300px; display: none;"></a>
     <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>
     <script type="text/javascript" src="/scripts/pj_mycentercourse.js"></script>
   
  </body>

</html>
