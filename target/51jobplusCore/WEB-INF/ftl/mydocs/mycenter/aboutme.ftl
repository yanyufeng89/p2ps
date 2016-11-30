<!DOCTYPE html>
<html class="expanded">
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="description" content="JobPlus网是国内首创的企业知识库公共平台,结构化的知识体系,包含文档,书籍,文章,网站,问答,在线课程的分享学习社区。一站式知识库平台,为7亿职场人员提供优质学习分享社区,JobPlus是你最值得信赖的终身学习伙伴。">
        <meta name="keywords" content="JobPlus,知识分享,知识库,文档,书籍,问答,课程,文章,网站,在线培训,企业课堂,员工培训,在线教育,职业技能,视频课程,培训网站,职场培训,网络课堂,人才培训,内容管理">
        <title>${userInfo.username}-个人主页-JobPlus</title>
        <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
        <link rel="stylesheet" type="text/css" href="/css/pj_mycenter.css">
        <link rel="stylesheet" type="text/css" href="/css/jquery.pinwheel-0.1.0.css">
 </head>
    <body id='aboutmetop'>
        <div class="page">
            <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
            <div id="bd" style="margin:0 auto; min-height:480px; width:1200px">
                <div id="bd-1">
                    <div class='top-card'>
                        <div class="profile-card vcard entity">
                            <div class="profile-picture" style='height:160px;line-height:160px'>
                               <#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
                                 <img src="${userInfo.headicon}" alt="个人头像" width="150" height="160" class='lazy'>
                               <#else>
                                 <img src="/image/myphoto.jpg" alt="个人头像" width="150" height="160" class='lazy'>
                               </#if>
                            </div>
                            
							<div class="profile-overview">
                                <div class="profile-overview-content">
                                    
                                        <div id="name-container">
                                            <div id="name" class="editable-item">
                                                <div class="n fn field-text">
                                                    <span class="full-name">${userInfo.username?html}</span>
                                                    <span class="pj-level" style='vertical-align:0;'><em>LV.${userInfo.userlevel?html}</em></span>
												</div>
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
												   <label class='position-split'>|</label>
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
												<label class='area-split'>|</label>
												<#if (userInfo.industry)?? && userInfo.industry?length gt 0>
												   <span class='industry'>${userInfo.industry?html}</span>
												<#else>
												   <span class='industry'>行业</span>
												</#if> 
											  </p>
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
										  <span>这家伙很懒，还没写工作职位</span>
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
										<span>这家伙很懒，还没写教育背景</span>
									    </#if>
                                    </div>
                                    
                                </div>
								
                            </div>
							
                        </div>
					    <div class='profile-func'>
                                 <#if (userInfo.userid?string==(Session.user.userid?string)!)>
                                       <a href="/myHome/getHomePage/${Session.user.userid}?isReview=0" class="preview-profile button-primary">返回编辑界面</a>
								 <#else>
								  <#if (userInfo.fansIds)??>
										   <#if (userInfo.fansIds)?? && userInfo.fansIds?length gt 0>										     
											 <#if userInfo.fansIds?index_of(",")==-1>
												  <#if (userInfo.fansIds?string==(Session.user.userid?string)!)>											 
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
												  <#else>
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>+&nbsp;关注</button>
												  </#if>
											<#else>
												 <#if userInfo.fansIds?split(",")?seq_contains((Session.user.userid?string)!)>
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
												 <#else>
													<button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>+&nbsp;关注</button>
												 </#if>
											</#if>
										  <#else>
											      <button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>+&nbsp;关注</button>
										  </#if>
								    <#else>
									              <button  data-userid="${userInfo.userid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>+&nbsp;关注</button>
									</#if>
								</#if>	    
							     
						<span class='personal-data current' id='personal-data'>个人资料</span>	
						<span class="split">|</span>
						<span class='personal-share' id='personal-share'>个人分享</span>	
						<span class="split">|</span>	
						<span class='personal-attention' id='personal-attention' data-userid='${userInfo.userid}'>个人关注</span>	
						<#if (userInfo.userid?string!=(Session.user.userid?string)!)>
					       <div class="site-mail pm-img img" id='site-mail' data-issitemail='1' data-receivedid='${userInfo.userid}' data-name='${userInfo.username}'></div>	
					    </#if>
						</div> 
						<div class="profile-card-extras">
                        <div id="contact-info-section" class="more-info entity"  style="display:none">
                           <h3>联系方式<em></em></h3>
                           <div class="contact-info">
                             <div class="email">
                               <span>邮箱:&nbsp;&nbsp;</span>
                              
							   <#if (userInfo.contactEmail)?? && userInfo.contactEmail?length gt 0>
                                 <span>${userInfo.contactEmail}</span>
                               <#else>
                                                                                      未知
                               </#if>
                              
                             </div>
                              <div class="phone">
                                <span>电话:&nbsp;&nbsp;</span>
                                
								<#if (userInfo.contactTel)?? && userInfo.contactTel?length gt 0>
                                  <span>${userInfo.contactTel}</span>
								<#else>
                                                                                      未知
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
                              <div class="show-more-info">
							     <a href="javascript:void(0)">
								    <span class="contact"><b class='t-down'></b>联系方式</span>
                                    <span class="edit-contact">编辑联系方式</span>
								 </a>
                              </div>
                         </div>
                    </div>
                    </div>
                    
                    
					
                     <#if ((userInfo.ismarry)?? && userInfo.ismarry?length gt 0 && userInfo.ismarry!=2) || ((userInfo.birthdayone)?? && userInfo.birthdayone?length gt 0) || ((userInfo.birthdaytwo)?? &&  userInfo.birthdaytwo?length gt 0) || ((userInfo.description)?? &&  userInfo.description?length gt 0)>
							<div class="background-selfinfo-container section-container zg-empty">
								<div class="header">
									<span class='selfinfo-icon'>个人信息</span>
								</div>
								<div class="birthday">
								  <#if (userInfo.birthdayone)?? && userInfo.birthdayone?length gt 0>
									<span class='birth'><label>生日:</label>${userInfo.birthdayone}月${userInfo.birthdaytwo}日</span>
								  <#else>
								    <span class='birth'><label>生日:</label></span>
								  </#if>
									
									<#if (userInfo.ismarry)?? && userInfo.ismarry!=2 && userInfo.ismarry?length gt 0>
									  <span class="marriage"><label>婚姻状况:</label>	<#if userInfo.ismarry==1>已婚<#elseif userInfo.ismarry==0>未婚</#if></span>
									 <#else>
									   <span class="marriage">婚姻状况:&nbsp;&nbsp;</span>
									 </#if>
									
								</div>
								<div class="header">
									<span style='font-size:14px'>个人简介</span>
								</div>
								 <div class="intro">
								 <#if (userInfo.description)?? && userInfo.description?length gt 0>
									<span class="introinfo">${userInfo.description}</span>
							     </#if>
								</div>
						   </div>
					  </#if>
					   <#if (eduList)?? && eduList?size gt 0>
					    <#list eduList as edulist>
						 <#if edulist_index==0>
						 <#assign edittype=1 />
					     <div class="background-education-container preview-education  section-container zg-empty" id='education${edulist_index}'>
						<#else>
						 <#assign edittype=0 />
						 <#if eduList?size-1=edulist_index>
						    <div class="background-education-container section-container education-container zg-empty" id='education${edulist_index}'>
						 <#else>
						    <div class="background-education-container  preview-education section-container education-container zg-empty" id='education${edulist_index}'>
						 </#if>
						</#if>
							
                             <#if edulist_index==0>
							  <div class="header">
								<span class='education-icon'>教育背景</span> 
							 </div>
							 </#if>
							  <div class="education e-bold">
								<label>学校名称:</label>
							    <#if (edulist.school)?? && edulist.school?length gt 0>
								  <span class='schoolname'>${edulist.school?html}</span>
							    </#if>
							  </div>
							   <div class='degree'>
								<span class='topDegree'>
								  <label>最高学历:</label>
								  <#if (edulist.degree)?? && edulist.degree?length gt 0>
									 <span class='profession'>${edulist.degree?html}</span>
							      </#if>
								</span>
								
							  </div>
							   <div class='major'>
								<span class='professioninfo'>
								  <label>所学专业:</label>
								  <#if (edulist.major)?? && edulist.major?length gt 0>
									 <span class='profession'>${edulist.major?html}</span>
							      </#if>
								</span>
								
							  </div>
							  <div class='graduate'>
								<span class='schoolinfo'>
								   <span class="schooltime">
									  <label>在校时间:</label>
									<#if (edulist.starttime)??>
									  <#-- ${edulist.starttime?date}--${edulist.endtime?date} -->
									  ${edulist.starttime?string("yyyy年MM月dd日")}&nbsp; – &nbsp;${edulist.endtime?string("yyyy年MM月dd日")}&nbsp;&nbsp;(${edulist.period})
									</#if>
								  </span>
								</span>
								<h5 class="section-logo">
							       <strong>
							        <#if (edulist.schoolLogo)??>
							         <img src="${edulist.schoolLogo}" alt="${edulist.school?html}" width="50" height="50" class='lazy'>
							        </#if>
							       </strong>
							    </h5>
							  </div>
							 
							</div>
					   </#list>
					  </#if>
					   <#if (workExList)?? && workExList?size gt 0>
					    <#list workExList as worklist>
						   <#if worklist_index==0>
							 <#assign edittype=1 />
							 <div class="background-workexperience-container preview-workexperience  section-container zg-empty" id='work${worklist_index}'>
							<#else>
							 <#assign edittype=0 />
							 <#if workExList?size-1=worklist_index>
							    <div class="background-workexperience-container  section-container workexperience-container zg-empty" id='work${worklist_index}'>
						     <#else>
							    <div class="background-workexperience-container preview-workexperience section-container workexperience-container zg-empty" id='work${worklist_index}'>
							 </#if>
						   </#if>
						      
							   <#if worklist_index==0>
								  <div class="header">
									<span class='work-icon'>工作经历</span> 									
								 </div>
							   </#if>
								  <div class="company e-bold">
									  <label> 公司名称:</label>
									 <#if (worklist.company)?? && worklist.company?length gt 0>
									   <span class='companyname'>${worklist.company?html}</span>
									 </#if>
								  </div>
								  <div class='workposition'>
										<span class='positioninfo'>
											<label>工作职位:</label>
										  <#if (worklist.jobtitle)?? && worklist.jobtitle?length gt 0>
											 <span class='position'>${worklist.jobtitle?html}</span>											
										   </#if>
										</span>
								  </div>
								  <div class='onjob'>
									<span class='onjobinfo'>
									   <span class="onjobtime">
										<label> 在职时间:</label>
											<#if (worklist.starttime)?? && worklist.starttime?length gt 0>
												  <#-- ${worklist.starttime?date}--${worklist.endtime?date} --> 
												  ${worklist.starttime?string("yyyy年MM月dd日")}&nbsp; – &nbsp;<#if (worklist.endtime)??>${worklist.endtime?string("yyyy年MM月dd日")}<#else>至今</#if>&nbsp;&nbsp;(${worklist.period})									 
										    </#if>
									  </span>
									</span>
								  </div>
								 
								  <div class='workdescription clearfix'>
									<span class='description'>
											<label>工作内容:</label>
									 <#if (worklist.description)?? && worklist.description?length gt 0>
										 <span class='workcontent'>${worklist.description}</span>										
									 </#if>
									</span>
								  </div>
							  </div>
						</#list>
					  </#if>
					  <#if (personalSkill)?? && personalSkill?size gt 0>
					  <#list personalSkill as skill>
					      <div class="background-skills-container section-container zg-empty" id='skill0'>
						    <div class="header">
								<span class='skill-icon'>技能专长</span> 						
						    </div>
						    <div class="skills-detail zg-empty">
							  <#list skill.skillitem?split(',') as items>
							     <#assign item=items?split(':')/>
								 <span class='skillzone' data-skillid='${item[0]}'>${item[1]}</span>
							  </#list>
							</div>
						  </div>
					  </#list>
					</#if>
					<#--个人资料未维护-->
					<div class="no-material" id='no-material'style="display:none">					   
						<div class="zm-profile-section-list">
						   <ul>
							   <span class="nosharelist">这家伙很懒，还没填写个人资料</span>
						   </ul>
						</div>
				    </div>
                    <div class="profile-navbar clearfix" id='profile-navbar' style='display:none'>
					    <a class="pjitem current"  href="javascript:void(0)" data-userid='${userInfo.userid}' data-tablename='' data-tablecolumn='' data-tablecolumn2=''>
						   最新分享
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
					  <div class='profile-navbar clearfix' id='fans-navbar' style='display:none'>
					     <a class="pjitem current" href="javascript:void(0)" data-userid='${userInfo.userid}'>
							<#if userInfo.userid==(Session.user.userid)!>我的关注<#else>他的关注</#if><span class="num">
									   <#if (atdAndFans)??>
									     ${atdAndFans.attenManSum}
									   <#else>
									     0
									   </#if>
								       </span>
					     </a>
						 <a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}'>
							<#if userInfo.userid==(Session.user.userid)!>我的粉丝<#else>他的粉丝</#if><span class="num">
							         <#if (atdAndFans)??>
								       ${atdAndFans.fansSum}
								     <#else>
								      0
								     </#if>
									</span>
					     </a>
					     <a class="pjitem" href="javascript:void(0)" data-userid='${userInfo.userid}'>
					              最近访问<span class="num">
							         <#if (visitors!)>
								       ${visitors.count}
								     <#else>
								      0
								     </#if>
									</span>
					     </a>
					  </div>
                      <div class='zm-profile-section-wrap' style='display:none'>					   
						<div class='zm-profile-section-list' id='zh-profile-answers-inner-list'>
						   <ul>
						    <#if (recentShare)?? &&recentShare.list?size gt 0>
							   <#list recentShare.list as slist>
								 <li>
								   <div class="allnewscontent">	
								    <#if slist.type='tbl_docs'>[文档]<#elseif slist.type='tbl_topics'>[话题]<#elseif slist.type='tbl_books'>[书籍]<#elseif slist.type='tbl_article'>[文章]<#elseif slist.type='tbl_courses'>[课程]<#else>[站点]</#if><a href="${slist.objurl}"  target="_blank" title='${slist.title}'><span class='allnewsname'>${slist.title}</span></a>
								    <span class="smsdate zg-right">${slist.createtime?string("yyyy-MM-dd")}</span>
								   </div>
								 </li>
							  </#list>
							<#else>
							   <span class='nosharelist'>这家伙很懒，还没有分享</span>
							</#if>
						   </ul>
						</div>
						 <#if (recentShare.last gt 1)>
					         <a href="javascript:;"  data-pageno="1" data-tablename=''  data-tablecolumn2='' data-tablecolumn='' data-userid='${userInfo.userid}' data-sumpage='${recentShare.last}' class="zg-btn-white zg-r3px zu-button-more pj-load-more">更多</a>
					     </#if>
					  </div>	
                      <div id='zh-profile-follows-list' class='zh-profile-follows-list' style='display:none'>
					      <div class="zh-general-list clearfix">
						  </div>
					  </div>					  
                </div>
                <div id="bd-2">
				 <div class="news" style='border-color:#cce1ef'>
                       <div class="top-border">
                          <p>
						   <#if userInfo.userid==(Session.user.userid)!>
                            <a href="javascript:void(0)" self="_self" id='otheratten' data-userid='${userInfo.userid}'>
                               <span class="count">
							     <#list Session.myHeadTop!?keys as itemKey>
									 <#if itemKey="attenManSum">
										${Session.myHeadTop[itemKey]}
									 </#if>
								 </#list>
							   </span>
                               <span class="count-concern">我的关注</span>
                            </a>
						   <#else>
						    <a href="javascript:void(0)" self="_self" id='otheratten' data-userid='${userInfo.userid}'>
                               <span class="count">
							     <#if (atdAndFans)??>
								  ${atdAndFans.attenManSum}
								 <#else>
								   0
								 </#if>
							   </span>
                               <span class="count-concern">他的关注</span>
                             </a>
                           </#if>							
                          </p>
                          
						  
                          <p>
						   <#if userInfo.userid==(Session.user.userid)!>
								<a href="javascript:void(0)"  target="_self" id='otherfans' data-userid='${userInfo.userid}'>
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
							  <a href="javascript:void(0)"  target="_self" id='otherfans' data-userid='${userInfo.userid}'>
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
							<#if visitors.last gt 1>
                               <span class='more' id='moreVisitor'>更多>></span>
							</#if>
                       </div>
                       <div class='detail-list zg-clear'>
						  <#if (visitors)??>
							<#list visitors.list as list>
								<a title="${list.userName}"  class="zm-item-link-avatar uname" target='_blank' href='/myHome/getHomePage/${list.visitorid}' data-userid="${list.visitorid}" data-moduletype='1'>
									   <#if (list.headIcon)??>
										 <img src="${list.headIcon}" alt="个人头像" class="zm-item-img-avatar lazy">
									   <#else>
										  <img src="/image/1b48b5a75c71597_100x100.jpg" alt="个人头像" class="zm-item-img-avatar lazy">
									   </#if>
							    </a>
							</#list>
					   </#if> 
					  </div>
						
                    </div>
					<div class='sharelist'>
					  <div class="recent-share">
                            <span>最新分享</span>
							<#if visitors.last gt 1>
                               <span class='more' id='moreShare'>更多>></span>
							</#if>
                       </div>
					   <div class='detail-list zg-clear'>
						  <#if (recentShare)??>
						   <ul>
								<#list recentShare.list as list>
									<li>
									  <#if list.type='tbl_docs'>
									  [文档]
									  <#elseif list.type='tbl_topics'>
									  [话题]
									  <#elseif list.type='tbl_books'>
									  [书籍]
									  <#elseif list.type='tbl_article'>
									  [文章]
									  <#elseif list.type='tbl_courses'>
									  [课程]
									  <#else>
									 [站点]
									  </#if>
									  <a href='${list.objurl}' target='_blank' title='${list.title}'><span>${list.title}</span></a>
									  <span class="smsdate zg-right">${list.createtime?string("yyyy-MM-dd")}</span>
									</li>
								</#list>
							</ul>
					      </#if> 
					  </div>
					</div>
                </div>
            </div>
            <div class='pagetemplate'></div>
            <a id="backaboutmetop" title="回到顶部" href="#aboutmetop" class='back-to-top' style="display: none; bottom:300px;"></a>
            <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
            <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
            <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script> 
            <script type="text/javascript" src="/scripts/pj_constant.js"></script>
            <script type="text/javascript" src="/scripts/jquery.pinwheel-0.1.0.js"></script>
            <script type="text/javascript" src="/scripts/pj_publicMethod.js"></script>
            <script type="text/javascript" src="/scripts/pj_loadPmAndsmg.js"></script>
            <script type="text/javascript" src="/scripts/pj_myhomepage.js" charset="UTF-8"></script>
    </body>
</html>