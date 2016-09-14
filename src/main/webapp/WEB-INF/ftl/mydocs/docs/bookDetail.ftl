<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          书籍详情
    </title> 
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    <link rel='stylesheet' type='text/css' href='/51jobplusCore/css/pj_bookdetail.css'>
  </head>
  <body id='booktop'>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='bookdescrition'>
		<span>JobPlus知识库 >书籍<#list record.booktype?split(",") as item><#assign types=item?split(":")/>>${types[1]}</#list></span>
      </div>
      <div id="content">
        <input type='hidden' name='bookid' value='${record.id}'>
        <input type='hidden' name='bookname' value='${record.bookname}'>
        <input type='hidden' name='bookCreatePerson' value='${record.objCreator.userid}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner">
               <div class='basic book-basic'>
                   <div class='infor after'>
                      <#if (record)??>
	                       <div class='infor-left'>
	                           <#if (record.bookimg)??>
	                             <img src="${record.bookimg}" class="book-logo" alt="">
	                            <#else>
	                              <img src="/51jobplusCore/image/thief.jpg" class="book-logo" alt="">
	                           </#if>
	                       </div>
	                       <div class='infor-right'>
                             <div class='bookname'>
                                <span class='tip'>[书籍]</span>
                                <input class="noborder" type="text" readonly="" name="bookname" value="${record.bookname}">
                             </div>
                             <div class='author'>
                                <span class='tip'>作者</span>
                                <input class="noborder" type="text" readonly="" name="author" value="${record.author}">
                             </div>
                             <div class='press'>
                                <span class="tip">出版社：</span>
                                <input class="noborder" type="text" readonly="" name="press" value="${record.press}">
                             </div>
                             <div class='brief book-height course-height tool-height'>
                               <#if (record.intro)??>
                               <div class='showbrief bookbrief' data-brief='${record.intro}'>
                                <span class="tip">简介：</span>
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
						   分享到
						</div>
				        <div class="shareto-menu bdsharebuttonbox bdshare-button-style1-16">
						    <a href="javascript:void(0);" onclick="toShare(1,'${record.bookname}');" class="log_sina png" title="分享到新浪微博"></a>
						    <a href="javascript:void(0);" onclick="toShare(2,'${record.bookname}');" class="log_qq png"  title="分享到QQ空间"></a>
						    <a href="javascript:void(0);" onclick="toShare(3,'${record.bookname}');" class="log_wx png" title="分享到微信"></a>
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
		           <a class='uhead' href='/51jobplusCore/myHome/getHomePage?userid=${booklist.userid}' data-userid='${booklist.userid}' target='_blank'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname' src="${booklist.userHeadIcon}" alt=""  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname' src='/51jobplusCore/image/1b48b5a75c71597_100x100.jpg' alt="" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		            </a>
		             <#if (booklist.commentbyName)??>
		                <a href='/51jobplusCore/myHome/getHomePage?userid=${booklist.userid}' class="uname" data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
		                  <span class="desc">回复</span>
		                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
		                <a href='/51jobplusCore/myHome/getHomePage?userid=${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
		                </span>
		             <#else>
		             <a class='uname' href='/51jobplusCore/myHome/getHomePage?userid=${booklist.userid}' data-userid='${booklist.userid}' target='_blank'>
			             ${booklist.userName}
			         </a>
		             </#if>
		           
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
			
				   <#if (booklist.likedIds)??&&booklist.likedIds?length gt 0>
				      <#if booklist.likedIds?index_of(",")==-1>
						  <#if (booklist.likedIds?string==(Session.user.userid?string)!)>
							<a href="javascript:;" class="meta-item zu-autohide js-thank" data-reasonid='${booklist.id}' data-islike='1' data-likecount='${booklist.likesum}' data-recommend='${booklist.userid}'>
							   <i class="z-icon-thank"></i>取消赞(${booklist.likesum})
							</a>
						  <#else>
							<a href="javascript:;" class="meta-item zu-autohide js-thank" data-reasonid='${booklist.id}' data-islike='0' data-likecount='${booklist.likesum}' data-recommend='${booklist.userid}'>
							   <i class="z-icon-thank"></i>${booklist.likesum}赞
							 </a>
						  </#if>
					   <#else>
						 <#if booklist.likedIds?split(",")?seq_contains((Session.user.userid?string)!)>
							 <a href="javascript:;" class="meta-item zu-autohide js-thank" data-reasonid='${booklist.id}' data-islike='1' data-likecount='${booklist.likesum}' data-recommend='${booklist.userid}'>
							   <i class="z-icon-thank"></i>取消赞(${booklist.likesum})
							</a>
						 <#else>
							<a href="javascript:;" class="meta-item zu-autohide js-thank" data-reasonid='${booklist.id}' data-islike='0' data-likecount='${booklist.likesum}' data-recommend='${booklist.userid}'>
							   <i class="z-icon-thank"></i>${booklist.likesum}赞
							 </a>
						 </#if>
						</#if>
					  <#else>
					     <a href="javascript:;" class="meta-item zu-autohide js-thank" data-reasonid='${booklist.id}' data-islike='0' data-likecount='${booklist.likesum}' data-recommend='${booklist.userid}'>
							   <i class="z-icon-thank"></i>${booklist.likesum}赞
						</a>
				    </#if>
					
					<a href="javascript:;" class="meta-item zu-autohide js-report" data-commentbyid="${booklist.userid}" data-topiccommentid="${booklist.id}" data-reporttype='2'>
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
			  <a href='/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}' data-userid='${Session.user.userid}' target='_blank'>
				 <#if (Session.user.headicon)??>
				   <img src="${Session.user.headicon}" alt="" class='zm-list-avatar' data-userid='${Session.user.userid}' data-moduletype='1'>
				 <#else>
				   <img src='/51jobplusCore/image/1b48b5a75c71597_100x100.jpg' alt="" class='zm-list-avatar' data-userid='${Session.user.userid}' data-moduletype='1'>
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
					  <#if (record.collectIds)??&&record.collectIds?length gt 0>
						    <#if record.collectIds?index_of(",")==-1>
							  <#if (record.collectIds?string==(Session.user.userid?string)!)>
							     <button  class="follow-button zg-follow zg-btn-white" id="bookfollowanswer" data-actiontype="1">
								  取消收藏
							 </button>
							  <#else>
							    <button  class="follow-button zg-follow zg-btn-green" id="bookfollowanswer" data-actiontype="0">
								 收藏书籍
						       </button>
							  </#if>
							<#else>
							 <#if record.collectIds?split(",")?seq_contains((Session.user.userid?string)!)>
							    <button  class="follow-button zg-follow zg-btn-white" id="bookfollowanswer" data-actiontype="1">
								  取消收藏
							    </button>
							 <#else>
							    <button  class="follow-button zg-follow zg-btn-green" id="bookfollowanswer" data-actiontype="0">
								 收藏书籍
						       </button>
							 </#if>
							</#if>
					      <#else>
						       <button  class="follow-button zg-follow zg-btn-green" id="bookfollowanswer" data-actiontype="0">
								 收藏书籍
						       </button>
	                      </#if>
					 
					<span class="zg-gray-normal">
					<a href="">
						 <strong>
							 ${record.collectsum}
						 </strong>
					</a>
					人收藏了这本书籍
					</span>
					<div class="zh-books-followers-sidebar">
					<#if (record.collectUsers)??>

					<div class="list zu-small-avatar-list zg-clear">
					<#list record.collectUsers as colllist>
						<a title="${colllist.username}"  class="zm-item-link-avatar" target='_blank' href='/51jobplusCore/myHome/getHomePage?userid=${colllist.userid}' data-userid="${colllist.userid}" data-moduletype='1'>
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
					  <h3>相关书籍</h3>
					  
					  <#if (record.relatedList)??>
					    <ul>
						 <#list record.relatedList as samelist>
						  <li>
						     <div class='related-book-left'>
							   <#if (samelist.url)??>
							      <a href='/51jobplusCore/books/getBookDetail?id=${samelist.data_id}' target="_self"><img src="${samelist.url}" class="zm-item-img-avatar"></a>
							   <#else>
							      <a href='/51jobplusCore/books/getBookDetail?id=${samelist.data_id}' target="_self"><img src="/51jobplusCore/image/thief.jpg" class="zm-item-img-avatar"></a>
							   </#if>
							 </div>
							 <div class='related-book-right'>
							   <p>[书籍]&nbsp;<span>《${samelist.title}》</span></p>
							   <p>作者:&nbsp;<span>${samelist.author}</span></p>
							   <p>出版社:&nbsp;<span>${samelist.press}</span></p>
							   <p><span>${samelist.replySum}人收藏</span></p>
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
     <a id="bookbacktop" title="回到顶部" href="#booktop" style="bottom: 300px; display: none;"></a>
     <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>
     <script type="text/javascript" src="/51jobplusCore/scripts/pj_mycenterbook.js"></script>
  </body>

</html>
