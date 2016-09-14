<!DOCTYPE html>
<html class="expanded">
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <title>个人主页</title>
        <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
        <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_mycenter.css">
        <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/jquery.pinwheel-0.1.0.css">
 </head>
    <body id='aboutmetop'>
        <div class="page">
            <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
            <div id="bd" style="margin:0 auto; min-height:480px; width:1200px">
                <div id="bd-1">
                    <div id="top-card">
                        <div class="profile-card vcard entity">
                            <div class="profile-picture">
                               <#if (userInfo.headicon)??>
                                 <img src="${userInfo.headicon}" width="120" height="120">
                               <#else>
                                 <img src="/51jobplusCore/image/myphoto.jpg" width="120" height="120">
                               </#if>
                            </div>
                            
							<div class="profile-overview">
                                <div class="profile-overview-content">
                                    <div id="member-498233445" class="masthead">
                                        <div id="name-container">
                                            <div id="name" class="editable-item">
                                                <span class="n fn field-text">
                                                    <span class="full-name">
													  ${userInfo.username?html}
											        </span>
												</span>
                                            </div>
                                        </div>
                                        <div id="location-container">
                                            <div  class="editable-item">
                                                <p class="title field-text " dir="ltr">
												   <span class='position'> 
												      <#if (userInfo.position)?? && userInfo.position?length gt 0>
													    ${userInfo.position?html}
													  <#else>
													    职位
													  </#if>
												   </span>
												   --
												   <span class='compname'> 
												    <#if (userInfo.compname)?? && userInfo.compname?length gt 0>
													  ${userInfo.compname?html}
													<#else>
													公司名称
													</#if>
												  </span>
												</p>
                                            </div>
                                        </div>
                                        <div id="position">
                                            <div  class="editable-item">
                                              <p class="title field-text " dir="ltr">
                                                <#if (userInfo.province)?? && userInfo.province?length gt 0>
												   <span class='province'>${userInfo.province?html}</span>
												<#else>
												   <span class='province'>所在省份</span>
												</#if>&nbsp;&nbsp;
												<#if (userInfo.city)?? && userInfo.city?length gt 0>
												    <span class='city'>${userInfo.city?html}</span>
												<#else>
												    <span class='city'>所在城市</span>
											    </#if>
												&nbsp;&nbsp;|&nbsp;&nbsp;
												<#if (userInfo.industry)?? && userInfo.industry?length gt 0>
												   <span class='industry'>${userInfo.industry?html}</span>
												<#else>
												   <span class='industry'>行业</span>
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
										  </#if>
										</#list>
									      <span>目前就职&nbsp;&nbsp;<#if (company)??>${company?html}</#if></span>
										<#else>
										  <span>目前就职</span>
										</#if>
                                    </div>
                                    <div class="field" data-add-entity="EDU" data-trk="prof-edit-topcard-edu-inline_cta">
                                        <#if (eduList)?? && eduList?size gt 0>
										   <#list eduList as elist>
											  <#if elist_index==0>
												 <#assign school=elist.school />
											  </#if>
											</#list>
									    <span>教育背景&nbsp;&nbsp;<#if (school)??>${school?html}</#if></span>
										<#else>
										<span>教育背景</span>
									    </#if>
                                    </div>
                                    
                                </div>
								<div class="profile-aux">
                                 <div class="profile-actions">
                                 <#if (userInfo.userid?string==(Session.user.userid?string)!)>
									   <div class="profile-actions">
                                           <a href="/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}" class="preview-profile button-primary" target="_self">返回编辑界面</a>
									  </div>
								 <#else>
								  <#if (userInfo.fansIds)??>
										   <#if (userInfo.fansIds)?? && userInfo.fansIds?length gt 0>										     
											 <#if userInfo.fansIds?index_of(",")==-1>
												  <#if (userInfo.fansIds?string==(Session.user.userid?string)!)>											 
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
												  <#else>
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
												  </#if>
											<#else>
												 <#if userInfo.fansIds?split(",")?seq_contains((Session.user.userid?string)!)>
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
												 <#else>
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
												 </#if>
											</#if>
										  <#else>
											      <button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
										  </#if>
								    <#else>
									              <button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
									</#if>
								</#if>	    
							     </div>
                               </div>
                            </div>
							
                        </div>
                    </div>
                    
                    <div class="profile-card-extras">
                        <div id="contact-info-section" class="more-info entity"  style="display:none">
                           <h3>我的联系方式<em></em></h3>
                           <div class="contact-info">
                             <div class="email">
                               <span>邮箱:&nbsp;&nbsp;</span>
                              
							   <#if (userInfo.email)?? && userInfo.email?length gt 0>
							     <#assign email=userInfo.email />
							     <#--邮箱不同  区显示3分之2的位数，向上取整-->
							     <#assign email_index1=((userInfo.email?substring(0,userInfo.email?last_index_of('@'))?length)*2/3)?ceiling />
							     <#assign email_index2=userInfo.email?last_index_of('@') />
                                 <span>${userInfo.email?substring(0,email_index1?number-1)+'****'+userInfo.email?substring(email_index2?number-1,userInfo.email?length)}</span>
                               </#if>
                              
                             </div>
                              <div class="phone">
                                <span>电话:&nbsp;&nbsp;</span>
                                
								<#if (userInfo.mobile)?? && userInfo.mobile?length gt 0>
								  <#assign phone=userInfo.mobile />
                                  <span>${userInfo.mobile?substring(0,3)+'****'+userInfo.mobile?substring(7,userInfo.mobile?length)}</span>
								</#if>
								
                             </div>
                           </div>

                        </div>
                        <div class="profile-actions entity">
                             <ul class="public-profile-section is-focusable" tabindex="0">
                                 <li>
                                      <dl class="public-profile">
                                            <dd>
											    <span class="public-profile-url">${homePageUrl}</span>
                                                <a class="public-profile-settings-link" href="" title="更新公开资料徽章的设置"></a>
                                             </dd>
                                       </dl>
                                  </li>
                              </ul>
                              <div class="show-more-info" data-ispackup='1'>
							     <a href="javascript:void(0)">
								    <span class="contact"><b class='t-down'></b>个人简介</span>
                                    <span class="edit-contact">编辑联系方式</span>
								 </a>
                              </div>
                         </div>
                    </div>
					
                     <#if ((userInfo.ismarry)?? && userInfo.ismarry?length gt 0 && userInfo.ismarry!=2) || ((userInfo.birthdayone)?? && userInfo.birthdayone?length gt 0) || ((userInfo.birthdaytwo)?? &&  userInfo.birthdaytwo?length gt 0) || ((userInfo.description)?? &&  userInfo.description?length gt 0)>
							<div class="background-selfinfo-container section-container" style='display:none'>
								<div class="header">
									<span>个人信息</span>
								</div>
								<div class="birthday">
								  <#if (userInfo.birthdayone)?? && userInfo.birthdayone?length gt 0>
									<span class='birth'>生日:&nbsp;&nbsp;${userInfo.birthdayone}月${userInfo.birthdaytwo}日</span>
								  <#else>
								    <span class='birth'>生日:&nbsp;&nbsp;</span>
								  </#if>
									<span class='marr'>
									<#if (userInfo.ismarry)?? && userInfo.ismarry!=2 && userInfo.ismarry?length gt 0>
									  <span class="marriage">婚姻状况:&nbsp;&nbsp;
									  
									    <#if userInfo.ismarry==1>
									    已婚
									    <#elseif userInfo.ismarry==0>
									    未婚
									    </#if>
								     
									  </span>
									 
									 <#else>
									   <span class="marriage">婚姻状况:&nbsp;&nbsp;</span>
									 </#if>
									</span>
								</div>
								<div class="header">
									<span>自我简介</span>
								</div>
								 <div class="intro">
								 <#if (userInfo.description)?? && userInfo.description?length gt 0>
									<span class="introinfo">${userInfo.description?html}</span>
							     </#if>
								</div>
						   </div>
					  </#if>
					   <#if (eduList)?? && eduList?size gt 0>
					    <#list eduList as edulist>
						 <#if edulist_index==0>
						 <#assign edittype=1 />
					     <div class="background-education-container section-container" id='education${edulist_index}' style='display:none'>
						<#else>
						 <#assign edittype=0 />
						 <div class="background-education-container section-container education-container" id='education${edulist_index}' style='display:none'>
						</#if>
							
                             <#if edulist_index==0>
							  <div class="header">
								<span>教育背景</span> 
							 </div>
							 </#if>
							  <div class="education">
									   学校名称:&nbsp;&nbsp;
								<#if (edulist.school)?? && edulist.school?length gt 0>
								 <span class='schoolname'>
                                     ${edulist.school?html}
								</span>
								
							   </#if>
								
							  </div>
							  <div class='graduate'>
								<span class='schoolinfo'>
								   <span class="schooltime">
									  在校时间:&nbsp;&nbsp;
									<#if (edulist.starttime)??>
									  ${edulist.starttime?date}--${edulist.endtime?date}
									 
									</#if>
								  </span>
								 
								</span>
							  </div>
							  <div class='major'>
								<span class='professioninfo'>
										 所学专业:&nbsp;&nbsp;
								  <#if (edulist.major)?? && edulist.major?length gt 0>
									 <span class='profession'>
									   ${edulist.major?html}
									 </span>
									
							      </#if>
								</span>
							  </div>
							</div>
					   </#list>
					  </#if>
					   <#if (workExList)?? && workExList?size gt 0>
					    <#list workExList as worklist>
						   <#if worklist_index==0>
							 <#assign edittype=1 />
							 <div class="background-workexperience-container section-container" id='work${worklist_index}' style='display:none'>
							<#else>
							 <#assign edittype=0 />
							 <div class="background-workexperience-container section-container workexperience-container" id='work${worklist_index}' style='display:none'>
						   </#if>
						      
							   <#if worklist_index==0>
								  <div class="header">
									<span>工作经历</span> 									
								 </div>
							   </#if>
								  <div class="company">
									   公司名称:&nbsp;&nbsp;
									 <#if (worklist.company)?? && worklist.company?length gt 0>
									   <span class='companyname'>
									      ${worklist.company?html}
									   </span>
									 </#if>
								  </div>
								  <div class='onjob'>
									<span class='onjobinfo'>
									   <span class="onjobtime">
										 在职时间:&nbsp;&nbsp;
											<#if (worklist.starttime)?? && worklist.starttime?length gt 0>
												  ${worklist.starttime?date}--${worklist.endtime?date}												 
										    </#if>
									  </span>
									</span>
								  </div>
								  <div class='workposition'>
										<span class='positioninfo'>
											工作职位:&nbsp;&nbsp;
										  <#if (worklist.jobtitle)?? && worklist.jobtitle?length gt 0>
											 <span class='position'>
											    ${worklist.jobtitle?html}
											 </span>											
										   </#if>
										</span>
								  </div>
								  <div class='workdescription'>
									<span class='description'>
											工作内容:&nbsp;&nbsp;
									 <#if (worklist.description)?? && worklist.description?length gt 0>
										 <span class='workcontent'>
										   ${worklist.description?html}
										 </span>										
									 </#if>
									</span>
								  </div>
							  </div>
						</#list>
					  </#if>
                      <div class="profile-navbar clearfix">
					    <a class="pjallshare" href="javascript:void(0)">
						   所有分享
						   <span class="num">
							  <#if (atdAndFans.operationSum.allshresum)??>
							    ${atdAndFans.operationSum.allshresum}
							  <#else>
							   0
							  </#if>
							</span>
						</a>
					    <a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}' data-tablename='tbl_docs'  data-tablecolumn='title' data-tablecolumn2='userid'>
							文档
							<span class="num">
							  <#if (atdAndFans.operationSum.docsharesum)??>
							    ${atdAndFans.operationSum.docsharesum}
							  <#else>
							   0
							  </#if>
							</span>
					    </a>
						 <a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}' data-tablename='tbl_topics'  data-tablecolumn='title' data-tablecolumn2='createPerson'>
							话题
							<span class="num">
							   <#if (atdAndFans.operationSum.topicssharesum)??>
							     ${atdAndFans.operationSum.topicssharesum}
							  <#else>
							     0
							  </#if>
                            </span>
					    </a>
						 <a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}' data-tablename='tbl_books'  data-tablecolumn='bookname' data-tablecolumn2='userid'>
							书籍
							<span class="num">
							   <#if (atdAndFans.operationSum.booksharesum)??>
							     ${atdAndFans.operationSum.booksharesum}
							  <#else>
							     0
							  </#if>
                            </span>
					    </a>
						 <a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}' data-tablename='tbl_article'  data-tablecolumn='title' data-tablecolumn2='userid'>
							文章
							<span class="num">
							   <#if (atdAndFans.operationSum.articlesharesum)??>
							     ${atdAndFans.operationSum.articlesharesum}
							  <#else>
							     0
							  </#if>
                            </span>
					    </a>
						 <a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}' data-tablename='tbl_courses'  data-tablecolumn='coursesName' data-tablecolumn2='userid'>
							课程
							<span class="num">
							   <#if (atdAndFans.operationSum.coursessharesum)??>
							     ${atdAndFans.operationSum.coursessharesum}
							  <#else>
							     0
							  </#if>
                            </span>
					    </a>
						<a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}' data-tablename='tbl_sites'  data-tablecolumn='title' data-tablecolumn2='userid'>
							站点
							<span class="num">
							   <#if (atdAndFans.operationSum.sitessharesum)??>
							     ${atdAndFans.operationSum.sitessharesum}
							  <#else>
							     0
							  </#if>
                            </span>
					    </a>
						
                      </div>
                      <div class='zm-profile-section-wrap'>
					    <div class="zm-profile-section-head">
							<h2 class="zm-profile-section-name">文档</h2>
						</div>
						<div class='zm-profile-section-list' id='zh-profile-answers-inner-list'>
						   <ul>
						    <#if (shares)?? &&shares?size gt 0>
							  <#list shares.list as slist>
								 <li>
								   <span class="allnewscontent">
								      分享了文档										
									<a href="${request.getContextPath()}/docs/getDocsDetail?id=${slist.id}"  target="_blank">${slist.objName}</a>
								   </span>
								   <span class="smsdate zg-right">${slist.createtime?string("yyyy-MM-dd")}</span>
								 </li>
							  </#list>
							</#if>
						   </ul>
						   <#if (shares.last gt 1)>
					         <a href="javascript:;"  data-pageno="1" data-tablename='tbl_docs'  data-tablecolumn2='userid' data-tablecolumn='title' data-userid='${userInfo.userid}' data-sumpage='${shares.last}' class="zg-btn-white zg-r3px zu-button-more pj-load-more">更多</a>
					       </#if>
						</div>
					  </div>	
                      <div id='zh-profile-follows-list' class='zh-profile-follows-list' style='display:none'>
                          <div class="zm-profile-section-head">
							<h2 class="zm-profile-fansandatten-name">他关注的人</h2>
						  </div>
					      <div class="zh-general-list clearfix">
						  </div>
					  </div>					  
                </div>
                <div id="bd-2">
				 <div class="news">
                       <div class="top-border">
                          <p>
						   <#if userInfo.userid==(Session.user.userid)!>
                            <a href="javascript:void(0)" self="_blank" id='otheratten' data-userid='${userInfo.userid}'>
                               <span class="count">
							     <#list Session.myHeadTop!?keys as itemKey>
									 <#if itemKey="attenManSum">
										${Session.myHeadTop[itemKey]}
									 </#if>
								 </#list>
							   </span>
                               <span class="count-concern">我关注的人</span>
                            </a>
						   <#else>
						    <a href="javascript:void(0)" self="_blank" id='otheratten' data-userid='${userInfo.userid}'>
                               <span class="count">
							     <#if (atdAndFans)??>
								  ${atdAndFans.attenManSum}
								 <#else>
								   0
								 </#if>
							   </span>
                               <span class="count-concern">他关注的人</span>
                             </a>
                           </#if>							
                          </p>
                          
						  
                          <p>
						   <#if userInfo.userid==(Session.user.userid)!>
								<a href="javascript:void(0)"  self="_blank" id='otherfans' data-userid='${userInfo.userid}'>
								   <span class="count">
									  <#list Session.myHeadTop!?keys as itemKey>
										 <#if itemKey="fansSum">
											${Session.myHeadTop[itemKey]}
										 </#if>
									  </#list>   
								   </span>
								   <span class="count-concern">我的粉丝</span>
								</a> 
							<#else>
							  <a href="javascript:void(0)"  self="_blank" id='otherfans' data-userid='${userInfo.userid}'>
                               <span class="count">
							      <#if (atdAndFans)??>
								    ${atdAndFans.fansSum}
								 <#else>
								   0
								 </#if>
							   </span>
                               <span class="count-concern">他的粉丝</span>
                              </a> 
							</#if>
                          </p>
						  
                       </div>
                    </div>
					
                    <div class="seeme">
                       <div class="recent-seeme">
                            <span>最近访问</span>
							<#if userInfo.userid==(Session.user.userid)!>
                               <span class='more'><a href='/51jobplusCore/myHome/moreRecentVistors?userid=${userInfo.userid}' self="_blank">更多>></a></span>
							</#if>
                       </div>
						
                       <div class='detail-list'>
						  <#if (visitors)??>
							<#list visitors.list as list>
								 <div class='iteminfo'>
									<a class="uhead" href='/51jobplusCore/myHome/getHomePage?userid=${list.visitorid}' data-userid="${list.visitorid}">
									 <#if (list.headIcon)??>
									   <img class="uname" src="${list.headIcon}" alt="" data-userid="${list.visitorid}">
									 <#else>
									   <img class='unname' src='/51jobplusCore/image/1b48b5a75c71597_100x100.jpg' alt="" data-userid="${list.visitorid}">
									 </#if>
								   </a>
								   
								   <div class='visitorcontent'>
									 <a href='/51jobplusCore/myHome/getHomePage?userid=${list.visitorid}' target="_blank" class="uhead" data-userid="${list.visitorid}">${list.userName}</a>
									 <div class="meta"><span>${list.visittime?string("yyyy-MM-dd HH:mm:ss")}</span></div>
								   </div>
								   <span class='zg-right'> 
								     
									    <#if (list.fansIds)??&&list.fansIds?length gt 0>
										 <#if list.fansIds?index_of(",")==-1>
											  <#if (list.fansIds?string==(Session.user.userid?string)!)>
												<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
											  <#else>
												<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
											  </#if>
										<#else>
											 <#if list.fansIds?split(",")?seq_contains((Session.user.userid?string)!)>
												<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
											 <#else>
												<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
											 </#if>
										</#if>
									  <#else>
										  <button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
									   </#if>
									
								   </span>
								 </div>
							</#list>
					   </#if> 
					  </div>
						
                    </div>
                </div>
            </div>
            <div class='pagetemplate'></div>
            <a id="backaboutmetop" title="回到顶部" href="#aboutmetop" style="display: none; bottom:300px;"></a>
            <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
            <script type="text/javascript" src="/51jobplusCore/scripts/jquery-1.8.0.min.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/jquery-jtemplates.js"></script> 
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_constant.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/jquery.pinwheel-0.1.0.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_publicMethod.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_loadPmAndsmg.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_myhomepage.js" charset="UTF-8"></script>
    </body>
</html>