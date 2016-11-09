<!DOCTYPE html>
<html class="expanded">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <title>最近访问-个人中心-JobPlus知识库</title>
        <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
    </head>
    <body id='currentvisittop'>
        <div  class="page">
            <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
            <div id="bd" style="margin:0 auto; width:1200px">
                <div id="bd-1">
                    <div id="top-card" class='top-card'>
                        <div class="profile-card vcard entity">
                            <div class="profile-picture" id="preview">
                               <#if (cutUser.headicon)?? && cutUser.headicon?length gt 0>
                                 <img src="${cutUser.headicon}" width="120" height="120" id="imghead" class='lazy' alt="个人头像">
                               <#else>
                                 <img src="/image/myphoto.jpg" width="120" height="120" id="imghead" class='lazy' alt="个人头像">
                               </#if>
                               
                              </div>
                            <div class="profile-overview">
                                <div class="profile-overview-content">
                                    <div id="member-498233445" class="masthead">
                                        <div id="name-container">
                                            <div id="titlename" class="editable-item">
                                                <span class="n fn field-text">
                                                    <span class="full-name">${cutUser.username}</span>
                                                </span>
                                            </div>
                                        </div>
                                        <div id="location-container">
                                            <div  class="editable-item">
                                                <p class="title field-text">
												  <#if (cutUser.position)?? && cutUser.position?length gt 0>
												    <span class='position'>${cutUser.position}</span>
												  <#else>
												   <span class='position'> 填写职位</span>
												  </#if>
												 											 
												   --												  
												  <#if (cutUser.compname)?? && cutUser.compname?length gt 0>
												     <span class='compname'>${cutUser.compname}</span>
												  <#else>
												     <span class='compname'> 填写公司名称</span>
												  </#if>
												  
												</p>
                                            </div>
                                        </div>
                                        <div id="position">
                                            <div  class="editable-item">
                                                <p class="title field-text " dir="ltr">
												  <#if (cutUser.province)?? && cutUser.province?length gt 0>
												      <span class='province'>${cutUser.province}</span>
												  <#else>
												      <span class='province'> 填写省份</span>
												  </#if>
												  &nbsp;&nbsp;
												  <#if (cutUser.city)?? && cutUser.city?length gt 0>
												      <span class='city'>${cutUser.city}</span>
												  <#else>
												      <span class='city'> 填写城市</span>
												  </#if>
												  
												  &nbsp;&nbsp;|&nbsp;&nbsp;
												  <#if (cutUser.industry)?? && cutUser.industry?length gt 0>
												     <span class='industry'>${cutUser.industry}</span>
												  <#else>
												     <span class='industry'> 填写行业</span>
												  </#if> 
												  
												</p>
                                            </div>
                                        </div>
                                    </div>
									
                                    <div class="field" data-add-entity="WORK" data-trk="prof-edit-topcard-edu-inline_cta">
									 <#if (workExList)?? && workExList?size gt 0>
                                       <#list workExList as wlist>
										  <#if wlist_index==0>
										     <#assign company=wlist.company />
											 <#assign cid=wlist.id />
										  </#if>
										</#list>
									    <span>目前就职&nbsp;&nbsp;<#if (company)??>${company}</#if></span>
									  
								    <#--<#else>
									    <button type="button" class="cta" id="control_gen_3">添加工作经历</button>
                                        <button type="button" class="desc-action prevent-edit-action" aria-describedby="-desc" data-tooltip-template-id="-desc"  tabindex="0"></button>-->
								     </#if>
                                    </div>
                                    <div class="field" data-add-entity="EDU" data-trk="prof-edit-topcard-edu-inline_cta">
									 <#if (eduList)?? && eduList?size gt 0>
                                       <#list eduList as elist>
										  <#if elist_index==0>
										     <#assign school=elist.school />
											 <#assign sid=elist.id />
										  </#if>
										</#list>
									    <span>教育背景&nbsp;&nbsp;<#if (school)??>${school}</#if></span>
									    
								     <#--<#else>
									    <button type="button" class="cta" id="control_gen_4">添加教育背景</button>
                                        <button type="button" class="desc-action prevent-edit-action" aria-describedby="-desc" data-tooltip-template-id="-desc" tabindex="0"></button>-->
								     </#if>
                                    </div>
                                   
                                </div>
								<div class="profile-aux">
                                      <div class="profile-actions">
                                        <#if cutUser.userid==Session.userid>
                                           <a href="/myHome/getAnotherHomePage/${cutUser.userid}" class="preview-profile button-primary" >个人资料预览</a>
                                        <#else>
                                            <a href="/myHome/getAnotherHomePage/${cutUser.userid}" class="preview-profile button-primary" >返回他的个人主页</a>
									    </#if>
									  </div>
                               </div>
                            </div>
                        </div>
			    </div>
				<div class='zm-profile-section-wrap'>
				     <div class="zm-profile-section-head">
					    <h2 class="zm-profile-section-name">最近访问</h2>
					 </div>
			         <div class='zm-profile-section-list'>
					  <div id='zh-profile-current-visit-list'>
						<div class='zh-general-list clearfix'>
						  <#if (visitors)??>
						   <#list visitors.list as list>
							 <div class='zm-profile-card zm-profile-section-item zg-clear no-hovercard'>
							   <div class="zg-right">
								  <#if (list.fansIds)??&&list.fansIds?length gt 0>
									 <#if list.fansIds?index_of(",")==-1>
										  <#if (list.fansIds?string==Session.user.userid?string)>
											<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
										  <#else>
											<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>+&nbsp;关注</button>
										  </#if>
									  <#else>
										 <#if list.fansIds?split(",")?seq_contains(Session.user.userid?string)>
											<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
										 <#else>
											<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>+&nbsp;关注</button>
										 </#if>
									</#if>
								  <#else>
									  <button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>+&nbsp;关注</button>
								  </#if>
							   </div>
								 <a title="${list.userName}"  target="_blank" class="zm-item-link-avatar"  data-userid='${list.visitorid}' href='/myHome/getHomePage/${list.visitorid}'>
								 <#if (list.headIcon)??>
								   <img src="${list.headIcon}" class="zm-item-img-avatar lazy" alt="个人头像">
								 <#else>
								   <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-item-img-avatar lazy" alt="个人头像">
								 </#if>
							   </a>
							   <div class="zm-list-content-medium">
									<h2 class="zm-list-content-title">
										<a  href='/myHome/getHomePage/${list.visitorid}' target="_blank" data-userid='${list.visitorid}' class="zg-link author-link" title="${list.userName}">${list.userName}</a> 
									</h2>
									<div class="ellipsis">
										<span class="bio">
										<#if (list.description)??>
										 ${list.description}
										</#if>
										</span>
									</div>
									<div class="details zg-gray">
									<a target="_self"  class="zg-link-gray-normal"  href='javascript:void(0)'>
									  <#if (list.attentionsum)??>${list.attentionsum}</#if>关注
									</a>
									<a class="zg-link-gray-normal">/</a>
									<a target="_self"  class="zg-link-gray-normal" href='javascript:void(0)'>
									  <#if (list.fanssum)??> ${list.fanssum}</#if>粉丝
									</a>
									<a target="_self"  class="zg-link-gray-normal" href='javascript:void(0)'>
									  <#if (list.visittime)??>&nbsp;&nbsp;${list.visittime?string("yyyy-MM-dd HH:mm:ss")}</#if>
									</a>
									</div>
							  </div>
							 </div>
					   </#list>
					  </#if>
					</div>
					
				  </div>
				 
			   </div>
			       <#if (visitors.last gt 1)>
					  <a href="javascript:;" data-loadtype="2"  data-pageno="1"  data-userid='${cutUser.userid}' data-sumpage='${visitors.last}' class="zg-btn-white zg-r3px zu-button-more pj-load-more">更多</a>
			      </#if>
			   </div>
                </div>
                <div id="bd-2">
                    <div class="cvadvertisement">
      
                    </div>
                </div>                    			
            </div>
            <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
            <a id="visitbacktop" title="回到顶部" href="#currentvisittop" class='back-to-top' style="bottom: 300px; display: none;"></a>	
            <div class='homepageTemplate'></div>
            <div class='pagetemplate'></div>
            <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
    </body>

</html>