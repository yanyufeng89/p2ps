<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
           话题详细
    </title> 
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    <link rel="stylesheet" type="text/css" href="/css/pj_simplePagination.css">
    <link rel='stylesheet' type='text/css' href='/css/pj_topicdetail.css'>
  </head>
  <body id='topictop'>
    <div  class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='topicdescrition'>
		<span>聘加知识库>话题<#list topicsDetail.topicstype?split(",") as item><#assign types=item?split(":")/>>${types[1]}</#list></span>
      </div>
      <div id="content">
        <input type='hidden' name='titleid' value='${topicsDetail.id}'>
        <input type='hidden' name='titlename' value='${topicsDetail.title}'>
        <input type='hidden' name='createperson' value='${topicsDetail.createperson}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner">
                <div id="zh-question-title" data-editable="true">
                    <h2 class='zm-item-title'>
                       <span class="zm-editable-content">
                          <span class='title' data-id='${topicsDetail.id}'>${topicsDetail.title}</span>
                          <#if topicsDetail.createperson==(Session.user.userid)!>
                          		<a href="javascript:;" class="zu-edit-button" name="edit" data-id='${topicsDetail.id}'>
                                  <i class="zu-edit-button-icon"></i>修改
                                </a>
                          </#if>
                         
                       </span>
                       <div id='titletemplate' style="display: none;"></div>
                    </h2>
			     </div>
			     <#if (topicsDetail.topicsclass)?? &&topicsDetail.topicsclass?length gt 2>
				     <div class="zm-tag-editor zg-section">
						<div class="zm-tag-editor-labels zg-clear">
							<#list topicsDetail.topicsclass?split(',') as detaillist>
							   <#assign p=detaillist?split(':')/>
							   <a href="javascript:;" class='zm-item-tag' href='' data-topicid='${p[0]}'>${p[1]}</a>
							</#list>
							<#if topicsDetail.createperson==(Session.user.userid)!>
							<a href="javascript:;" class="zu-edit-button" name="edit" data-topicsid='${topicsDetail.id}' data-topicsclass='${topicsDetail.topicsclass}'>
							   <i class="zu-edit-button-icon"></i>修改
							</a>
							</#if>
						</div>
						<div id='tagtemplate' style="display: none;"></div>
					  </div>
				  </#if>
				  <#if (topicsDetail.content)??&&topicsDetail.content?length gt 0>
					  <div id="zh-question-detail">
	                     <div class="zm-editable-content">
	                         <#assign topiccontent=topicsDetail.content?substring(0,topicsDetail.content?length-4)/>
	                         ${topiccontent}
							 <#if topicsDetail.createperson==(Session.user.userid)!>
	                            <a href="javascript:;" class="zu-edit-button" name="edit"><i class="zu-edit-button-icon"></i>修改</a>
                             </#if>
	                     </div>
	                      <textarea title="在这里输入问题简介" id='topiceditor' class='topiceditor' placeholder='在这里输入问题简介' style='display:none'>${topicsDetail.content}</textarea>
	                      <div class="zm-command zg-r3px" style='display:none'>
							  <a class="zm-command-cancel" name="cancel" href="javascript:;">取消</a>
							  <a class="zg-btn-blue" name="save" href="javascript:;">保存</a>
						  </div>
	                  </div>
                  </#if>
                  <div class="zm-item-meta zm-item-comment-el clearfix" id="zh-question-meta-wrap">
                      <div class="zm-meta-panel">
							<a href="javascript:;" name="addcomment" class="toggle-comment meta-item" id='topiccomment' data-commentcount='${topicsDetail.commentsum}'>
							 <i class="z-icon-comment"></i>${topicsDetail.commentsum}条评论
							</a>
                            <a href="javascript:;" name="report-question" class="report meta-item" data-commentbyid='${topicsDetail.createperson}' data-topiccommentid='${topicsDetail.id}' data-reporttype='5'>
							  <i class="z-icon-report-o"></i>举报
					        </a>
					        <span  class='meta-item'>分享到</span>
                          <a href="javascript:void(0);" class="jiathis_button_tsina log_sina png" title="分享到新浪微博"></a>
                          <a href="javascript:void(0);" class="jiathis_button_qzone log_qq png"  title="分享到QQ空间"></a>
                          <a href="javascript:void(0);" class="jiathis_button_weixin log_wx png" title="分享到微信"></a>
                          <script type="text/javascript">
                              var jiathis_config = {data_track_clickback:'true'};
                          </script>
                          <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1345538646577576" charset="utf-8"></script>
                      </div>
                      <div class='topiccommtemplate'></div>
                  </div>
                  <div class="zh-answers-title clearfix">
						<div id="zh-answers-filter" class="Sorter">
						
                        <#if (sortType=='1')>
                          <input type='hidden' name='sorttype' value='2'>
						  <span class="lbl">按时间排序</span>
						  <a class="lbl" href="/topics/getTopicsDetail/${topicsDetail.id}?sortType=2">按点赞排序</a>
                        <#else>
                         <input type='hidden' name='sorttype' value='1'>
                          <span class="lbl">按点赞排序</span>
						  <a class="lbl" href="/topics/getTopicsDetail/${topicsDetail.id}?sortType=1">按时间排序</a>
                        </#if>  
						<i class="zg-icon zg-icon-double-arrow"></i>
						</div>
						<h3 data-num="60" id="zh-question-answer-num">
						 ${topicsDetail.replysum}
						个回答</h3>
				  </div>
				  <div class="zh-question-answer-wrapper navigable" id="zh-question-answer-wrap">
				   <#if (topicsDetail.commentList)??>
				     <#list topicsDetail.commentList.list as list>
					     <div tabindex="-1" class="zm-item-answer  zm-item-expanded">
							<div class="answer-head">
							<div class="zm-item-answer-author-info">

							   <a class="zm-item-link-avatar avatar-link" href='/myHome/getHomePage/${list.userid}' target="_blank">

							   <#if (list.tmpHeadIcon)??&&list.tmpHeadIcon?length gt 0>
							     <#if (list.isPublic==1)>
							        <img src="${list.tmpHeadIcon}" class="zm-list-avatar avatar" data-userid='${list.userid}' data-moduletype="0">
							     <#else>
							        <img src="${list.tmpHeadIcon}" class="zm-list-anonymous-avatar avatar" data-userid='${list.userid}' data-moduletype="0">
							     </#if>
							    <#else>
							      <#if (list.isPublic==1)>
							         <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-avatar avatar" data-userid='${list.userid}' data-moduletype='0'>
							      <#else>
							         <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-anonymous-avatar avatar" data-userid='${list.userid}' data-moduletype='0'>
							      </#if>
							   </#if>
							   <#if (list.isPublic==1)>
							    <span class="author-link" data-tip="" target="_self"  data-userid='${list.userid}' data-moduletype='0'>${list.tmpUserName}</span>
							   <#else>
							     <span class="anonymous-user" data-tip="" target="_self"  data-userid='${list.userid}' data-moduletype='0'>匿名用户</span>
							   </#if>
						    </a>
							
							
							</div>
			
							</div>
							<div class="zm-item-rich-text expandable js-collapse-body">

							<div class="zm-editable-content clearfix">
							 <#if (list.commcontent)??&&list.commcontent?length gt 0>
							   ${list.commcontent}
							 </#if>
							</div>
							
							
							</div>
							
							<div class="zm-item-meta answer-actions clearfix js-contentActions">
								<div class="zm-meta-panel">
								
								<a itemprop="url" class="answer-date-link meta-item"  target="_self" href="javascript:;">编辑&nbsp;${list.updatetime?string("yyyy-MM-dd")}</a>
								
								<a href="javascript:;" name="addcomment" class="meta-item toggle-comment js-comment" data-answer='${list.id}'  data-commentcount='${list.replysum}' data-createperson='${list.userid}'> 
								<i class="z-icon-comment"></i>${list.replysum}条评论</a>
								
								 <#if (list.likedIds)??&&list.likedIds?length gt 0>
								   <#if list.likedIds?index_of(",")==-1>
								   
								      <#if (list.likedIds?string==(Session.user.userid?string)!)>
										<a href="javascript:;" class="meta-item zu-autohide js-thank" data-topiccommentid='${list.id}' data-like='0' data-likecount='${list.likesum}' data-createperson='${list.userid}'>
											<i class="z-icon-thank"></i>
											取消赞(${list.likesum})
										</a>
									  <#else>
									    <a href="javascript:;" class="meta-item zu-autohide js-thank" data-topiccommentid='${list.id}' data-like='1' data-likecount='${list.likesum}' data-createperson='${list.userid}'>
											<i class="z-icon-thank"></i>
											${list.likesum}赞
									    </a>
									  </#if>
									  
									<#else>
									  <#if list.likedIds?split(",")?seq_contains((Session.user.userid?string)!)>
									    <a href="javascript:;" class="meta-item zu-autohide js-thank" data-topiccommentid='${list.id}' data-like='0' data-likecount='${list.likesum}' data-createperson='${list.userid}'>
											<i class="z-icon-thank"></i>
											取消赞(${list.likesum})
										</a>
									  <#else>
									    <a href="javascript:;" class="meta-item zu-autohide js-thank" data-topiccommentid='${list.id}' data-like='1' data-likecount='${list.likesum}' data-createperson='${list.userid}'>
											<i class="z-icon-thank"></i>
											${list.likesum}赞
									    </a>
									  </#if>
									</#if>
									  <a href="javascript:void(0)" class="del zm-comment-op-link needsfocus" name="delcomment" data-reply='${list.id}' data-tableName='8'>
								          <i class="zg-icon zg-icon-comment-del"></i>删除
								      </a>
							     <#else>
									<a href="javascript:;" class="meta-item zu-autohide js-thank" data-topiccommentid='${list.id}' data-like='1' data-likecount='${list.likesum}' data-createperson='${list.userid}'>
										<i class="z-icon-thank"></i>
										${list.likesum}赞
									</a>
									 <a href="javascript:void(0)" class="del zm-comment-op-link needsfocus" name="delcomment" data-reply='${list.id}' data-tableName='8'>
								          <i class="zg-icon zg-icon-comment-del"></i>删除
								      </a>
								 </#if>
								 
								<a href="javascript:;" class="meta-item zu-autohide js-report" data-commentbyid='${list.userid}' data-topiccommentid='${list.id}' data-reporttype='5'><i class="z-icon-report-o"></i>举报</a>
                                    <span  class='meta-item'>分享到</span>
                                    <a href="javascript:void(0);" class="jiathis_button_tsina log_sina png" title="分享到新浪微博"></a>
                                    <a href="javascript:void(0);" class="jiathis_button_qzone log_qq png"  title="分享到QQ空间"></a>
                                    <a href="javascript:void(0);" class="jiathis_button_weixin log_wx png" title="分享到微信"></a>
                                    <script type="text/javascript">
                                        var jiathis_config = {data_track_clickback:'true'};
                                    </script>
                                    <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1345538646577576" charset="utf-8"></script>
								</div>
							    
							</div>
						</div>
               	     </#list>
					  <#if (topicsDetail.commentList.last gt 1)>
			            <a data-pageno='1' data-sumpage='${topicsDetail.commentList.last}' class="zg-btn-white zu-button-more loadmore">更多</a>
		        	 </#if>
               	   </#if>
               	   
               	  </div>
                 
					<#--判断是否登录-->
					<#if (Session.user)??>
					<div id="zh-question-answer-form-wrap" class="zh-question-answer-form-wrap">
						<div class="zm-editable-tip zu-answer-form-disabled-wrap" style="display: none;"></div>
						<div class="zm-editable-content" style="display: none;"><a href="javascript:;" class="zu-edit-button" name="edit"><i class="zu-edit-button-icon"></i>修改</a></div>
						<div class="pj-editable-editor-wrap" style="">
							<div class="zh-answer-form clearfix">
							   <a href='/myHome/getHomePage/${Session.user.userid}' class="zm-item-link-avatar" target='_blank'>
								 <#if (Session.user.headicon)??&&Session.user.headicon?length gt 0>
								   <img src="${Session.user.headicon}" class="zm-list-avatar" data-userid='${Session.user.userid}' data-moduletype='0'>
								  <#else>
								   <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-avatar" data-userid='${Session.user.userid}' data-moduletype='0'>
								 </#if>
							   </a>
							    <div class="zu-answer-form-title">
									<a href='/myHome/getHomePage/${Session.user.userid}' target='_blank' title="<#if (Session.user)??>${Session.user.username}</#if>" data-userid='${Session.user.userid}' class='author-link' data-moduletype='0'>
									  <#if (Session.user)??>
									   ${Session.user.username}
									  </#if>
									</a>
									<span>
										<a name="edit_bio" class="zu-edit-button" href="#">填写话题经验，提升回答可信度</a>
									</span>
								 </div>
							 </div> 
						     <div class='publishanswer'>
								<textarea title="在这里输入回答" id='uEditorCustom'  class='uEditorCustom' placeholder='在这里输入回答' style='display:none'></textarea>	
							 </div>		
							 <div class="zm-command clearfix">
							    <label class="zg-left anno-box" style="-webkit-user-select: none;">
								   <input name="anno-checkbox" type="checkbox" style="-webkit-user-select: none;"> 匿名
								</label> 
							    <span class="zg-right">
								  <a class="submit-button zg-btn-blue" name="save" href="javascript:" data-id='${topicsDetail.id}'>发布回答</a>
							    </span>
							</div>
						 </div>
					 </div>
					  
					 <#else>
					    <div class="col-md-6 col-md-offset-3 login-after-comments" style="text-align: center;">
				               <span class="hidden-xs" style="line-height:50px;font-size: 16px;color: #090909;">登录后才能发布回答</span><br>
				               <span class="comments-login-register hidden-xs loginprompt-null" style="margin-left: 20px;margin-right:10px;"><a href='javascript:void(0);' onclick="toLogin();">登录</a> |  <a  target='_blank' href='/registration.html'>立即注册</a> </span>
			            </div>
					 </#if>
					</div>
				  </div>
        

        <div class="plus-main-sidebar">
		     <div class="searchresright">
	            <a class="upload-btn bg-index" target="_self" href="/sharein/searchuploadFile"></a>
	         </div>
			 <div class='zm-relate-answer'>
			 <div class="zm-side-section">
				<div class="zm-side-section-inner" id="zh-question-side-header-wrap">

				<#if (topicsDetail.fansIds)??&&topicsDetail.fansIds?length gt 0>
				  <#if topicsDetail.fansIds?index_of(",")==-1>
				    <#if (topicsDetail.fansIds?string==(Session.user.userid?string)!)>
				      <button  class="follow-button zg-follow zg-btn-white" data-id="${topicsDetail.id}"  data-actiontype='0' id='topicfollowanswer'>
				                      取消关注
				     </button>
				    <#else>
				     <button  class="follow-button zg-follow zg-btn-green" id='topicfollowanswer'  data-id="${topicsDetail.id}"  data-actiontype='1'>
				                   关注话题
				     </button>
				    </#if>
				  <#else>
				    <#if topicsDetail.fansIds?split(",")?seq_contains((Session.user.userid?string)!)>
				     <button  class="follow-button zg-follow zg-btn-white" data-id="${topicsDetail.id}"  data-actiontype='0' id='topicfollowanswer'>
				                      取消关注
				     </button>
				    <#else>
				    <button  class="follow-button zg-follow zg-btn-green" data-id="${topicsDetail.id}"  data-actiontype='1' id='topicfollowanswer'>
				                   关注话题
				     </button>
				    </#if>
				</#if>
				<#else>
				<button  class="follow-button zg-follow zg-btn-green" data-id="${topicsDetail.id}"  data-actiontype='1' id='topicfollowanswer'>
				        关注话题
				</button>
				</#if>
				<span class='zg-gray-normal'>
				<a href="">
                     <strong>
                         ${topicsDetail.followssum}
                     </strong>
                </a>
				人关注该问题
				</span>
				<div class="zh-question-followers-sidebar">

				
				<div class="list zu-small-avatar-list zg-clear">
				  <#if (topicsDetail.fansList)??>
				   <#list topicsDetail.fansList as fanlist>

				     <a title="${fanlist.username}"  class="zm-item-link-avatar" target='_blank' href='/myHome/getHomePage/${fanlist.userid}'  data-userid='${fanlist.userid}' data-moduletype='0'>
				 
					   <#if (fanlist.headicon)?? && fanlist.headicon?length gt 0>
					     <img src="${fanlist.headicon}" class="zm-item-img-avatar" data-moduletype='0'>
					   <#else>
					     <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar" data-moduletype='0'>
					   </#if>
					</a>
				  </#list>
				 </#if>
				</div>
				
				</div>
				</div>
			 </div>
			 <div class="zm-side-section">
					<div class="zm-side-section-inner">
					<div id="zh-question-related-questions" class="zh-question-related-questions clearfix" data-za-module="RelatedQuestions">
					
					<h3>相关问题</h3>
					<ul itemprop="relatedQuestion">
					<#if (topicsDetail.relatedList)??>
					   <#list topicsDetail.relatedList as relatelist>
						<li>
							 <a class="question_link" href="/topics/getTopicsDetail/${relatelist.data_id}"  target='_blank' data-id="${relatelist.id}">${relatelist.title}</a> 
							 <span class="num">${relatelist.replySum}个回答</span>
						</li>
					  </#list>
                   </#if>
					</ul>
					</div>
					</div>
		    </div>
           </div>

        </div>
		   </div>
        </div>
      </div> 
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
     <a id="topicbacktop" title="回到顶部" href="#topictop" style="bottom: 300px; display: none;"></a>
     <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>
     <script type="text/javascript" src="/manage/js/mycenter/pj_mycentertopic.js"></script>

  </body>

</html>
