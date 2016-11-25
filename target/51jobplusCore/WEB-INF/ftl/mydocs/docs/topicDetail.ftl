<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
           ${topicsDetail.title}-JobPlus
    </title> 
    <meta name="description" content="JobPlus网是国内领先的企业知识库公共平台，分享知识、经验和见解，知识问答，话题交流社区，JobPlus为7亿职场人员提供优质学习分享社区，JobPlus是你最值得信赖的终身学习伙伴。">
    <meta name="keywords" content="JobPlus,知识分享,创新话题,创业问答,IT问答,互联网话题,问题咨询,财务问答,工业技术问答,工程问答,市场管理问答,销售管理问答,供应链问答,生产管理问答,设计类话题,创意问答,翻译问答,文案话题,新媒体话题,影视问答,写作话题">
    <#include "/mydocs/commonTemplate/detailjs/detailcss.ftl"/>
    <link rel="stylesheet" type="text/css" href="/css/pj_simplePagination.css">
    <link rel='stylesheet' type='text/css' href='/css/pj_topicdetail.css'>
    <div id='wx_pic' style='margin:0 auto;display:none;'>
          <img src='/image/logo_1.jpg' />
    </div>
  </head>
  <body id='topictop'>
    <div  class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div class='topicdescrition'>
		<span>JobPlus知识库>话题<#list topicsDetail.topicstype?split(",") as item><#assign types=item?split(":")/>>${types[1]}</#list></span>
      </div>
      <div id="content">
        <input type='hidden' name='titleid' value='${topicsDetail.id}'>
        <input type='hidden' name='titlename' value='${topicsDetail.title}'>
        <input type='hidden' name='createperson' value='${topicsDetail.createperson}'>
        <input type='hidden' name='points' value='${Session.account.points}'>
        <input type='hidden' name='rewardValue' value='${topicsDetail.rewardValue}'>
        <input type='hidden' name='isreward' value='${topicsDetail.acceptStatus}'>
        <div class="plus-main-content">
           <div class="plus-main-content-inner" style='border-bottom:none'>
                <div id="zh-question-title" class='zh-question-title'>
                  <div class='zm-editable-content'>
                      <span class='topic-title' data-id='${topicsDetail.id}'>${topicsDetail.title}</span>
                      <#if topicsDetail.createperson==(Session.user.userid)!>
                      		<a href="javascript:;" class="zu-edit-button" name="edit">
                              <i class="zu-edit-button-icon"></i>修改
                            </a>
                      </#if>
                   </div>
			     </div>
			     <#if (topicsDetail.topicsclass)?? &&topicsDetail.topicsclass?length gt 2>
				     <div class="zm-tag-editor zg-section">
						<div class="zm-tag-editor-labels zg-clear">
							<#list topicsDetail.topicsclass?split(',') as detaillist>
							   <#assign p=detaillist?split(':')/>
							   <a href="javascript:void(0);" class='zm-item-tag' href='' data-topicid='${p[0]}'>${p[1]}</a>
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
							  <a class="zg-btn-blue" name="save"  href="javascript:;" style="width:60px">保存</a>
						  </div>
	                  </div>
                  </#if>
                  <div class="zm-item-meta zh-question-meta-wrap clearfix" id="zh-question-meta-wrap">
                  <#-- 话题还没有最佳回复   回答采纳状态  1:已采纳   0:不悬赏  -1：取消悬赏 2:等待悬赏-->
                   
                      <div class='reward'>
                       <#if (topicsDetail.ispublic==1)>
						<a href='/myHome/getHomePage/${topicsDetail.createperson}' class='zm-item-link-avatar avatar-link' target='_blank' data-userid='${topicsDetail.createperson}'>${topicsDetail.objCreator.username}</a>
					   <#else>
						 <a  class=''  data-userid=''>匿名用户</a>
					   </#if>
                         <span class='meta-item'>${topicsDetail.createtime?string("yyyy-MM-dd")}</span>
                       <#--  <#if topicsDetail.acceptStatus != 0 && topicsDetail.createperson==(Session.user.userid)!>
	                         <a class='meta-item <#if topicsDetail.acceptStatus != 2>disabled</#if>' id='rewardval'>悬赏:&nbsp;<i class="<#if topicsDetail.acceptStatus == 2>z-icon-reward-o<#else>z-icon-accept-reward-o</#if>"></i><label class="lable-rewardValue" data-rewardValue="${topicsDetail.rewardValue}">${topicsDetail.rewardValue}</label></a>
	                         <a class='meta-item <#if topicsDetail.acceptStatus != 2>disabled</#if>' href='javascript:void(0)' id='advancereward'><i class="<#if topicsDetail.acceptStatus == 2>z-icon-advancereward-o<#else>z-icon-accept-advancereward-o</#if>"></i><span class='text'>提高悬赏</span></a>
	                         <a class='meta-item <#if topicsDetail.acceptStatus != 2>disabled</#if>' href='javascript:void(0)' id='cancelreward'><i class="<#if topicsDetail.acceptStatus == 2>z-icon-cancelreward-o<#else>z-icon-accept-cancelreward-o</#if>"></i><span class='text'>取消悬赏</span></a>
                         <#elseif topicsDetail.acceptStatus != 0&&topicsDetail.createperson!=(Session.user.userid)!>
                             <a class='meta-item <#if topicsDetail.acceptStatus != 2>disabled</#if>'>悬赏:&nbsp;&nbsp;<i class="<#if topicsDetail.acceptStatus == 2>z-icon-reward-o<#else>z-icon-accept-reward-o</#if>"></i><label class="lable-rewardValue" data-rewardValue="${topicsDetail.rewardValue}">${topicsDetail.rewardValue}</label></a> 
                         </#if>
                         ACT:${topicsDetail.acceptStatus}
						-->
						
						 <#if  topicsDetail.createperson==(Session.user.userid)!>
	                        <a class='meta-item <#if topicsDetail.acceptStatus==-1 || topicsDetail.acceptStatus==1>disabled</#if>' id='rewardval'>悬赏:&nbsp;<i class="<#if topicsDetail.acceptStatus==2 || topicsDetail.acceptStatus==0>z-icon-reward-o<#else>z-icon-accept-reward-o</#if>"></i><label class="lable-rewardValue" data-rewardValue="${topicsDetail.rewardValue}">${topicsDetail.rewardValue}</label></a>
		                    <a class='meta-item <#if topicsDetail.acceptStatus==-1 || topicsDetail.acceptStatus==1>disabled</#if>' href='javascript:void(0)' id='advancereward'><i class="<#if topicsDetail.acceptStatus==2 || topicsDetail.acceptStatus==0>z-icon-advancereward-o<#else>z-icon-accept-advancereward-o</#if>"></i><span class='text'>提高悬赏</span></a>
		                    <a class='meta-item <#if topicsDetail.acceptStatus==-1 || topicsDetail.acceptStatus==1>disabled</#if>' href='javascript:void(0)' id='cancelreward'><i class="<#if topicsDetail.acceptStatus==2 || topicsDetail.acceptStatus==0>z-icon-cancelreward-o<#else>z-icon-accept-cancelreward-o</#if>"></i><span class='text'>取消悬赏</span></a>
						<#elseif topicsDetail.createperson!=(Session.user.userid)!>
                             <a class='meta-item <#if topicsDetail.acceptStatus != 2>disabled</#if>'>悬赏:&nbsp;&nbsp;<i class="<#if topicsDetail.acceptStatus == 2>z-icon-reward-o<#else>z-icon-accept-reward-o</#if>"></i><label class="lable-rewardValue" data-rewardValue="${topicsDetail.rewardValue}">${topicsDetail.rewardValue}</label></a> 
                         </#if>
						
						
                      </div>
                      <div class="zm-meta-panel topic-operate">
							 <a href="javascript:;" name="addcomment" class="toggle-comment meta-item" id='topiccomment' data-commentcount='${topicsDetail.commentsum}'>
							 <i class="z-icon-comment"></i>${topicsDetail.commentsum}条评论
							</a>
                            <a href="javascript:;" name="report-question" class="report meta-item" data-commentbyid='${topicsDetail.createperson}' data-topiccommentid='${topicsDetail.id}' data-reporttype='11'>
							  <i class="z-icon-report-o"></i>举报
					        </a>
					        <#if (Session.user)??>
					          <span class="zg-bull">•</span>
                              <a href="javascript:void(0)" name="invite" class="meta-item invite" id='invite'>邀请回答
                              </a>
                            </#if>
					        <span  class='meta-item'>分享到</span>
                          <a href="javascript:void(0);" class="log_sina png" title="分享到新浪微博"></a>
                          <a href="javascript:void(0);" class="log_qq png"  title="分享到QQ空间"></a>
                          <a href="javascript:void(0);" class="log_wx png" title="分享到微信"></a>
                      </div>
                  </div>
                  <div class="zh-answers-title clearfix" id="zh-answers-count">
						<div id="zh-answers-filter" class="Sorter">
						<#--sortType 1代表按照时间排序  2代表点赞排序-->
                        <#if (sortType=='1')>
	                          <input type='hidden' name='sorttype' value='1'>
							  <span class="lbl">按时间排序</span>
							  <a class="lbl" href="/topics/getTopicsDetail/${topicsDetail.id}?sortType=2">按点赞排序</a>
                        <#else>
                              <input type='hidden' name='sorttype' value='2'>
	                          <span class="lbl">按点赞排序</span>
							  <a class="lbl" href="/topics/getTopicsDetail/${topicsDetail.id}?sortType=1">按时间排序</a>
                        </#if>  
						<i class="zg-icon zg-icon-double-arrow"></i>
						</div>
						<h3  id="zh-question-answer-num" data-num='${topicsDetail.replysum}'>${topicsDetail.replysum}个回答</h3>
				  </div>
				  <div class="zh-question-answer-wrapper navigable" id="zh-question-answer-wrap">
				   <#if (topicsDetail.commentList)??>
				     <#list topicsDetail.commentList.list as list>
					     <#if list_index==0 && list.isAccept==1 &&topicsDetail.createperson==(Session.user.userid)!>
					       <i class='z-icon-answer-adopt'></i>
						   <span class="answer-title h2 grid">已采纳</span>
						   <#assign isAccept=1>
						 <#elseif list_index==0 && list.isAccept==1 &&topicsDetail.createperson!=(Session.user.userid)!>
						   <i class='z-icon-answer-best'></i>
						   <span class="answer-title h2 grid">最佳答案</span>
						   <#assign isAccept=1>
						 <#else>
						   <#assign isAccept=0>
						 </#if>
						 
					     <div tabindex="-1" class="zm-item-answer  zm-item-expanded" <#if isAccept==1>style='background:#f3f9ff'</#if>>
							<div class="answer-head">
							<div class="zm-item-answer-author-info">

							   <#if (list.tmpHeadIcon)??&&list.tmpHeadIcon?length gt 0>
							     <#if (list.isPublic==1)>
							      <a class="zm-item-link-avatar avatar-link" href='/myHome/getHomePage/${list.userid}' target="_blank">
							        <img src="${list.tmpHeadIcon}" class="zm-list-avatar avatar lazy"  alt="个人头像" data-userid='${list.userid}' data-moduletype="0">
							      </a>
							     <#else>
							        <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-anonymous-avatar avatar lazy" alt="个人头像" data-moduletype="0">
							     </#if>
							    <#else>
							      <#if (list.isPublic==1)>
							        <a class="zm-item-link-avatar avatar-link" href='/myHome/getHomePage/${list.userid}' target="_blank">
							           <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-avatar avatar lazy" alt="个人头像" data-userid='${list.userid}' data-moduletype='0'>
							        </a>
							      <#else>
							         <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-anonymous-avatar avatar lazy" alt="个人头像" data-userid='${list.userid}' data-moduletype='0'>
							      </#if>
							   </#if>
							   <#if (list.isPublic==1)>
							   <a class="zm-item-link-avatar avatar-link" href='/myHome/getHomePage/${list.userid}' target="_blank">
							      <span class="author-link" data-tip="" target="_self"  data-userid='${list.userid}' data-moduletype='0'>${list.tmpUserName}</span>
							   </a>
							   <#else>
							     <span class="anonymous-user" data-tip="" target="_self"  data-moduletype='0'>匿名用户</span>
							   </#if>
						   
							
							
							</div>
			
							</div>
							<div class="zm-item-rich-text expandable js-collapse-body">

							<div class="zm-editable-content clearfix">
							 <#if (list.commcontent)??&&list.commcontent?length gt 0>
							   ${list.commcontent}
							 </#if>
							</div>
							
							</div>
							
							<div class="answer-actions clearfix js-contentActions">
							  
								<div class="zm-meta-panel">
								
								<a itemprop="url" class="answer-date-link meta-item"  target="_self" href="javascript:;">${list.updatetime?string("yyyy-MM-dd")}</a>
								
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
							     <#else>
									<a href="javascript:;" class="meta-item zu-autohide js-thank" data-topiccommentid='${list.id}' data-like='1' data-likecount='${list.likesum}' data-createperson='${list.userid}'>
										<i class="z-icon-thank"></i>
										${list.likesum}赞
									</a>
								 </#if>
								 
								<a href="javascript:;" class="meta-item zu-autohide js-report" data-commentbyid='${list.userid}' data-topiccommentid='${list.id}' data-reporttype='5'><i class="z-icon-report-o"></i>举报</a>
                                <span  class='meta-item'>分享到</span>
                                    <a href="javascript:void(0);" class="log_sina png" title="分享到新浪微博"></a>
                                    <a href="javascript:void(0);" class="log_qq png"  title="分享到QQ空间"></a>
                                    <a href="javascript:void(0);" class="log_wx png" title="分享到微信"></a>
                                <#if topicsDetail.acceptStatus==2&& topicsDetail.createperson==(Session.user.userid)! && list.userid!=(Session.user.userid)!>
							     	<a href="javascript:;" class='adopt-answer' data-adopt="${list.id}" data-receiveuid="${list.userid}"><i class="z-icon-adopt-o"></i>采纳答案</a>
								</#if>
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
								   <img src="${Session.user.headicon}" class="zm-list-avatar lazy" alt="个人头像" data-userid='${Session.user.userid}' data-moduletype='0'>
								  <#else>
								   <img src="/image/1b48b5a75c71597_100x100.jpg"  alt="个人头像" class="zm-list-avatar lazy" data-userid='${Session.user.userid}' data-moduletype='0'>
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
							       <b class="ic ic-msg" style="background-position: -47px -144px;display:none"></b>
				                   <span class="item-msg-content" style='display:none'>文字超出最大限制</span>
								  <a class="submit-button zg-btn-blue" name="save" href="javascript:" data-id='${topicsDetail.id}'>发布</a>
							    </span>
							</div>
						 </div>
					 </div>
					  
					 <#else>
					    <div class="col-md-6 col-md-offset-3 login-after-comments" style="text-align: center;">
				               <span class="hidden-xs" style="line-height:50px;font-size: 16px;color: #333;">登录后才能发布回答</span><br>
				               <span class="comments-login-register hidden-xs loginprompt-null" style="margin-left: 20px;margin-right:10px;"><a href='javascript:void(0);' onclick="toLogin();">登录</a> |  <a  target='_blank' href='/registration.html'>立即注册</a> </span>
			            </div>
					 </#if>
					</div>	 
           </div>
     
        <div class="plus-main-sidebar">
		     <div class="searchresright share-plaza">
	            <a class="share-icon bg-index" target="_self" href="/sharein/searchuploadFile?type=1"></a>
	            <a target="_self" href="javascript:void(0)" class="text">
				  文档---话题---书籍---课程---文章---站点
				</a>
	         </div>
			 <div class='zm-relate-content'>
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
				   <#if topicsDetail.fansList?size gt 6>
				   <#list topicsDetail.fansList[0..5] as fanlist>
				       <a title="${fanlist.username}" target='_blank' class='zm-item-link-avatar' href='/myHome/getHomePage/${fanlist.userid}' data-moduletype='0'>
					     <#if (fanlist.headicon)?? && fanlist.headicon?length gt 0>
					       <img src="${fanlist.headicon}" class="zm-item-img-avatar" data-moduletype='0' alt="个人头像" data-userid='${fanlist.userid}'>
					     <#else>
					        <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar" alt="个人头像" data-moduletype='0'  data-userid='${fanlist.userid}'>
					     </#if>
					  </a> 
				   </#list>
				   <a class='fa-ellipsis-h'>...</a>
				  <#else>
				     <#list topicsDetail.fansList as fanlist>
				       <a title="${fanlist.username}" target='_blank' class='zm-item-link-avatar' href='/myHome/getHomePage/${fanlist.userid}' data-moduletype='0'>
					     <#if (fanlist.headicon)?? && fanlist.headicon?length gt 0>
					       <img src="${fanlist.headicon}" class="zm-item-img-avatar lazy" data-moduletype='0' alt="个人头像" data-userid='${fanlist.userid}'>
					     <#else>
					        <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" alt="个人头像" data-moduletype='0'  data-userid='${fanlist.userid}'>
					     </#if>
					  </a> 
				     </#list>
				  </#if>
				 </div>
				 <#if topicsDetail.fansList?size gt 6>
				 <div class='list zu-small-avatar-list zg-clear' style='display:none'>
				     <#if (topicsDetail.fansList)??>
				         <#list topicsDetail.fansList as fanlist>
				           <a title="${fanlist.username}" target='_blank' class='zm-item-link-avatar' href='/myHome/getHomePage/${fanlist.userid}' data-moduletype='0'>
					        <#if (fanlist.headicon)?? && fanlist.headicon?length gt 0>
					          <img src="${fanlist.headicon}" class="zm-item-img-avatar lazy" alt="个人头像" data-moduletype='0'  data-userid='${fanlist.userid}'>
					        <#else>
					           <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" alt="个人头像" data-moduletype='0'  data-userid='${fanlist.userid}'>
					       </#if>
					      </a> 
				         </#list>
				    </#if>
				 </div>
				 </#if>
				 </#if>
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
						 <div class='related-topic-right'>
							 <a class="question_link SidebarListNav-label" href="/topics/getTopicsDetail/${relatelist.data_id}" data-id="${relatelist.id}"  target='_blank' title='${relatelist.title}'>${relatelist.title}</a>
							 <span class='evaluate-label'>${relatelist.replySum}个回答</span>
						 </div>
						</li>
					  </#list>
                   </#if>
					</ul>
					</div>
			   </div>
		    </div>
           </div>
           <div class='pj_jsonp ad_exposure'>
	            <img src='/image/ad_exposure_6.jpg' alt="广告" width='308' height='246' class='lazy'>
	            <div class='advertising-direction'>广告</div>
	       </div>  
        </div>
		</div>
        </div>
      </div> 
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
     <a id="topicbacktop" title="回到顶部" href="#topictop"  class='back-to-top' style="bottom: 300px; display: none;"></a>
     <#include "/mydocs/commonTemplate/detailjs/detailjs.ftl"/>
     <script type="text/javascript" src="/scripts/pj_mycentertopic.js"></script>
     <script type="text/javascript" src="/scripts/pj_msgbox.js"></script>
     <script type="text/javascript" src="/scripts/share.js"></script>
  </body>

</html>
