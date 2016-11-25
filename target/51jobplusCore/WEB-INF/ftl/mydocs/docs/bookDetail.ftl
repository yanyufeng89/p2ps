<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          ${record.bookname}-JobPlus
    </title>
    <meta name="description" content="JobPlus网是国内领先的企业知识库公共平台,书籍推荐,分享阅读,JobPlus为7亿职场人员提供优质学习分享社区,JobPlus是你最值得信赖的终身学习伙伴。">
    <meta name="keywords" content="JobPlus,知识分享,创新书籍,创业书籍,IT书籍,互联网书籍,咨询书籍,财务书籍,工业技术书籍,工程书籍,市场管理书籍,销售管理书籍,供应链书籍,生产管理书籍,设计书籍,创意书籍,翻译书籍,文案策划,新媒体书籍,影视书籍">
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    <link rel='stylesheet' type='text/css' href='/css/pj_bookdetail.css'>
    <div id='wx_pic' style='margin:0 auto;display:none;'>
          <img src='/image/logo_1.jpg' />
    </div>
  </head>
  <body id='booktop'>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='bookdescrition'>
		<span>JobPlus知识库 >书籍</span>
      </div>
      <div id="content">
        <input type='hidden' name='bookid' value='${record.id}'>
        <input type='hidden' name='bookname' value='${record.bookname}'>
        <input type='hidden' name='bookcontent' value='${record.intro?trim}'>
        <input type='hidden' name='bookCreatePerson' value='${record.objCreator.userid}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner book-main">
               <div class='basic book-basic'>
                   <div class='infor after'>
                      <#if (record)??>
	                       <div class='infor-left'>
	                           <#if (record.bookimg)??>
	                             <img src="${record.bookimg}" class="book-logo lazy" alt="书籍图片">
	                            <#else>
	                              <img src="/image/thief.jpg" class="book-logo lazy" alt="书籍图片">
	                           </#if>
	                       </div>
						   
	                       <div class='infor-right'>
                             <div class='bookname'>
                                <span class='noborder'>[书籍]&nbsp;${record.bookname}</span>
                             </div>
                             <div class='author'>
                                <span class='tip'>作者:</span>
                                <span class='noborder'>${record.author}</span>
                             </div>
                             <div class='press'>
                                <span class="tip">出版社:</span>
                                <span class='noborder'>${record.press}</span>
                             </div>
                             <div class='brief book-height course-height tool-height'>
                               <#if (record.intro)??>
                               <div class='showbrief bookbrief' id='bookbrief' data-brief='${record.intro?trim}'>
                                <span class='tip'>简介:</span>
                                <span class='brief-content'>${record.intro?trim}</span>
                                <#--<span class="slidedown slide" style="display: none;">展示全部</span>-->
                                <div class="slideup slide" style="display: none;"><span>收起</span></div>
                               </div>
                               </#if>
                             </div>
	                       </div>
						   
						   
                      </#if>
					   <div class='additional after resCollectionButton'>
					   　　  <div class='purchase-book'><a href='${record.supplierUrl}' target='_blank' class='purchaseurl' rel='nofollow'>购买本书</a></div>
						 <div class="shareto" style='margin-top:10px;'>
							<div class='shareto-share'>
							   分享到
							</div>
							<div class="shareto-menu bdsharebuttonbox bdshare-button-style1-16">
                                <a href="javascript:void(0);" class="log_sina png" title="分享到新浪微博"></a>
                                <a href="javascript:void(0);" class="log_qq png"  title="分享到QQ空间"></a>
                                <a href="javascript:void(0);" class="log_wx png" title="分享到微信"></a>
							</div>
						</div>
                      </div>
                   </div>
                   
               </div>
           </div>
        
        
		<div class='userrecommend' id='book-commcount' data-num='${record.recommendsum}'>用户推荐(${record.recommendsum})</div>
		<div class='detail'>
		 <div class='detail-list'>
		  <#if record.commentList.list?size lte 0>
		     <p class='uncomment'>暂无推荐，你也可以发布推荐哦:) </p>
		  </#if>
		  <#if (record.commentList)??>
		     <#list  record.commentList.list as booklist>
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
		                <a href='/myHome/getHomePage/${booklist.userid}' class="uname" data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
		                  <span class="desc">回复</span>
		                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
		                <a href='/myHome/getHomePage/${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
		                </span>
		             <#else>
		             <a class='uname' href='/myHome/getHomePage/${booklist.userid}' data-userid='${booklist.userid}' target='_blank'>
			             ${booklist.userName}
			         </a>
		             </#if>
		           
		         </div>
		         <div class='media-right'>
		           <div class="reason">${booklist.recommend?html}</div>
		           <div class="operate">
		             <a itemprop="url" class="answer-date-link meta-item" target="_self" href="javascript:;">
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
			 
		  </#if>
		  </div>
		  <#if (record.commentList.last gt 1)>
			   <a data-pageno='1' data-sumpage='${record.commentList.last}' class="zg-btn-white zu-button-more loadmore">更多</a>
		  </#if>
		</div>
		
		<#--判断是否登录-->
		<#if (Session.user)??>
		    <div class='mycomment'>
			  <a href='/myHome/getHomePage/${Session.user.userid}' data-userid='${Session.user.userid}' target='_blank'>
				 <#if (Session.user.headicon)??>
				   <img src="${Session.user.headicon}" alt="个人头像" class='zm-list-avatar lazy' data-userid='${Session.user.userid}' data-moduletype='1'>
				 <#else>
				   <img src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" class='zm-list-avatar lazy' data-userid='${Session.user.userid}' data-moduletype='1'>
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
	            <a class="share-icon bg-index" target="_self" href="/sharein/searchuploadFile?type=2"></a>
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
					  <#if record.collectUsers?size gt 6>
						<#list record.collectUsers[0..5] as colllist>
							<a title="${colllist.username}"  class="zm-item-link-avatar" target='_blank' href='/myHome/getHomePage/${colllist.userid}'  data-moduletype='1'>
							   <#if (colllist.headicon)??>
								 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}" alt='个人头像'>
							   <#else>
								  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}" alt='个人头像'>
							   </#if>
							</a>
					   </#list>
					    <a class='fa-ellipsis-h'>...</a>
					  <#else>
					    <#list record.collectUsers as colllist>
							<a title="${colllist.username}"  class="zm-item-link-avatar" target='_blank' href='/myHome/getHomePage/${colllist.userid}' data-moduletype='1'>
							   <#if (colllist.headicon)??>
								 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy"  data-userid="${colllist.userid}" alt='个人头像'>
							   <#else>
								  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}" alt='个人头像'>
							   </#if>
							</a>
					   </#list>
					  </#if>
					</div>
					<#if record.collectUsers?size gt 6>
						<div class="list zu-small-avatar-list zg-clear" style='display:none'>
							<#list record.collectUsers as colllist>
								<a title="${colllist.username}"  class="zm-item-link-avatar" target='_blank' href='/myHome/getHomePage/${colllist.userid}'  data-moduletype='1'>
								   <#if (colllist.headicon)??>
									 <img src="${colllist.headicon}" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}" alt='个人头像'>
								   <#else>
									  <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" data-userid="${colllist.userid}" alt='个人头像'>
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
					  <h3>相关书籍</h3>
					  <#if (record.relatedList)??>
					    <ul>
						 <#list record.relatedList as samelist>
						  <li>
							 <div class='related-book-right'>
							   <a  href='/books/getBookDetail/${samelist.data_id}'  target='_blank'  title='${samelist.title}'><span class='SidebarListNav-label'>${samelist.title}</span></a>
							   <span class='evaluate-label'>(${samelist.author})</span>
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
	            <img src='/image/ad_exposure_7.jpg' alt='广告' width='308' height='246' class='lazy'>
	            <div class='advertising-direction'>广告</div>
	         </div>
        </div>
      </div> 
	</div>
	 
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
     <a id="bookbacktop" title="回到顶部" href="#booktop" class='back-to-top' style="bottom: 300px; display: none;"></a>
     <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>
     <script type="text/javascript" src="/scripts/pj_mycenterbook.js"></script>
     <script type="text/javascript" src="/scripts/share.js"></script>
  </body>

</html>
