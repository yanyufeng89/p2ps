<!DOCTYPE html>
<html class="expanded">   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <title>个人主页</title>
        <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
        <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_mycenter.css">
        <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/dateSelect.css">
        <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/jquery.pinwheel-0.1.0.css">
    </head>
    <body id='centertop'>
        <div  class="page">
            <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
            <div id="bd" style="margin:0 auto; width:1200px">
                <div id="bd-1">
                    <div id="top-card">
                        <div class="profile-card vcard entity">
                            <div class="profile-picture" id="preview">
                               <#if (userInfo.headicon)??>
                                 <img src="${userInfo.headicon}" width="120" height="120" id="imghead">
                               <#else>
                                 <img src="/51jobplusCore/image/myphoto.jpg" width="120" height="120" id="imghead">
                               </#if>
                                <span class="ProfileAvatarEditor-tip">更换头像</span>
                                <form method="POST"  id="previewImage" enctype="multipart/form-data">
                                  <input name='headIconFile' class="file-3" type="file" accept="image/*" size="30" onchange="previewImage(this)" />
                                </form>
                              </div>
                            <div class="profile-overview">
                                <div class="profile-overview-content">
                                    <div id="member-498233445" class="masthead">
                                        <div id="name-container">
                                            <div id="titlename" class="editable-item">
                                                <span class="n fn field-text">
                                                    <span class="full-name">
													  ${userInfo.username?html}
											        </span>
													<a href="javascript:;" class="zu-edit-button" name="edittitle"><i class="zu-edit-button-icon"></i>修改</a>
                                                </span>
                                            </div>
                                        </div>
                                        <div id="location-container">
                                            <div  class="editable-item">
                                                <p class="title field-text">
												  <#if (userInfo.position)?? && userInfo.position?length gt 0>
												    <span class='position'>${userInfo.position?html}</span>
												  <#else>
												   <span class='position'> 填写职位</span>
												  </#if>
												  <a href="javascript:;" class="zu-edit-button" name="editposition"><i class="zu-edit-button-icon"></i>修改</a>												 
												   --												  
												  <#if (userInfo.compname)?? && userInfo.compname?length gt 0>
												     <span class='compname'>${userInfo.compname?html}</span>
												  <#else>
												     <span class='compname'> 填写公司名称</span>
												  </#if>
												  <a href="javascript:;" class="zu-edit-button" name="editcompname"><i class="zu-edit-button-icon"></i>修改</a>
												</p>
                                            </div>
                                        </div>
                                        <div id="position">
                                            <div  class="editable-item">
                                                <p class="title field-text " dir="ltr">
												  <#if (userInfo.province)?? && userInfo.province?length gt 0>
												      <span class='province'>${userInfo.province?html}</span>
												  <#else>
												      <span class='province'> 填写省份</span>
												  </#if>
												  &nbsp;&nbsp;
												  <#if (userInfo.city)?? && userInfo.city?length gt 0>
												      <span class='city'>${userInfo.city?html}</span>
												  <#else>
												      <span class='city'> 填写城市</span>
												  </#if>
												  <a href="javascript:;" class="zu-edit-button" name="editcity"><i class="zu-edit-button-icon"></i>修改</a>
												  &nbsp;&nbsp;|&nbsp;&nbsp;
												  <#if (userInfo.industry)?? && userInfo.industry?length gt 0>
												     <span class='industry'>${userInfo.industry?html}</span>
												  <#else>
												     <span class='industry'> 填写行业</span>
												  </#if> 
												  <a href="javascript:;" class="zu-edit-button" name="editindustry"><i class="zu-edit-button-icon"></i>修改</a>
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
									    <a href="#work0" class="zu-edit-button" name="editoncompany" data-currentid='${cid}' ><i class="zu-edit-button-icon"></i>修改</a>
								     <#else>
								     <#if (workExList)?? && workExList?size gt 0>
                                      <button type="button" class="cta" id="control_gen_3">添加工作经历</button>
									<#else>
									 <button type="button" class="cta" id="control_gen_3"  data-addtype='1'>添加工作经历</button>
									</#if>
									    
                                        <button type="button" class="desc-action prevent-edit-action" aria-describedby="-desc" data-tooltip-template-id="-desc"  tabindex="0"></button>
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
									    <a href="#education0" class="zu-edit-button" name="editonschool" data-currentid='${sid}'><i class="zu-edit-button-icon"></i>修改</a>
								     <#else>
								        <#if (eduList)?? && eduList?size gt 0>
	                                    <button type="button" class="cta" id="control_gen_4">添加教育背景</button>
									   <#else>
									    <button type="button" class="cta" id="control_gen_4" data-addtype='1'>添加教育背景</button>
									   </#if>
									    
                                        <button type="button" class="desc-action prevent-edit-action" aria-describedby="-desc" data-tooltip-template-id="-desc" tabindex="0"></button>
								     </#if>
                                    </div>
                                   
                                </div>
								<div class="profile-aux">
                                      <div class="profile-actions">
                                           <a href="/51jobplusCore/myHome/getHomePage?userid=${Session.user.userid}&isReview=1" class="preview-profile button-primary" target='_blank'>个人资料预览</a>
									  </div>
                               </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="profile-card-extras">
                        <div id="contact-info-section" class="more-info entity"  style="display:none">
                            
                           <h3>我的联系方式<em></em></h3>
            
                             <div class="email">
                               <span>邮箱:&nbsp;&nbsp;</span>
                              
							   <#if (userInfo.email)?? && userInfo.email?length gt 0>
							     <#assign email=userInfo.email />
                                 <span>${userInfo.email?html}</span>
                               </#if>
                               <input type='hidden' name='email' value='<#if (email) ??>${email}</#if>'>
							  
                             </div>
                              <div class="phone">
                                <span>电话:&nbsp;&nbsp;</span>
                                
								<#if (userInfo.mobile)?? && userInfo.mobile?length gt 0>
								  <#assign phone=userInfo.mobile />
                                  <span>${userInfo.mobile?html}</span>
								</#if>
								<input type='hidden' name='phone' value='<#if (phone)??>${phone}</#if>'>
								
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
                              <div class="show-more-info" data-ispackup='0'>
							    <a href="javascript:void(0)">
								   <span class="contact"><b class='t-down'></b>联系方式</span>
                                   <span class="edit-contact">编辑联系方式</span>
							    </a>
                             </div>
                         </div>
                    </div>
                    <div id="recommended-sections">
                        <div class="recommended-sections-container">
                            <h3 class="recommended-sections-headline">添加新版块到个人资料 — 彰显个人优势，提升事业高度！</h3></div>
                        <div id="rec-sctn-view-toggle" class="recommended-sections-list-container viewmore-container state-viewmore view-1">
                            <ul class="recommended-sections-all">
                                <li class="recommended-section-selfinfo do-default">
                                        <div class="recommended-section-content">
                                            <a class='introinfo recommendedimg'></a>
                                            <h5 class="recommended-section-title">基本信息</h5>
                                            <p class="recommended-section-value-statement">完善信息,让更多人了解我</p>
                                         </div>
                                         <button class="recommended-section-add">添加信息</button>  
                                </li>
                                <li class="recommended-section-education">
                                    <div class="recommended-section-content">
                                        <a class='educationinfo recommendedimg'></a>
                                        <h5 class="recommended-section-title">教育背景</h5>
                                        <p class="recommended-section-value-statement">列出学历,提升7倍人气</p>
                                    </div>
									<#if (eduList)?? && eduList?size gt 0>
                                    <button class="recommended-section-add">添加学校</button>
								   <#else>
								    <button class="recommended-section-add" data-addtype='1'>添加学校</button>
								   </#if>
                                </li>
                                <li class="recommended-section-work">
                                    <div class="recommended-section-content">
                                        <a class='workinfo recommendedimg'></a>
                                        <h5 class="recommended-section-title">工作经历</h5>
                                        <p class="recommended-section-value-statement">添加工作经历,提高个人能力</p>
                                     </div>
									<#if (workExList)?? && workExList?size gt 0>
                                      <button class="recommended-section-add">添加经历</button>
									<#else>
									  <button class="recommended-section-add" data-addtype='1'>添加经历</button>
									</#if>
                                </li>
								<li class="recommended-section-skill">
                                    <div class="recommended-section-content">
									    <a class='skill recommendedimg'></a>
                                        <h5 class="recommended-section-title">技能专长</h5>
                                        <p class="recommended-section-value-statement">列出技能,提高8倍人气</p>
								    </div>
                                    <button class="recommended-section-add">添加技能</button>
                                </li>
								<li class="recommended-section-langue">
								</li>
								<li class="recommended-section-langue">
								</li>
                                <li class="recommended-section-hobby" style='display:none'>
                                    <div class="recommended-section-content">
                                        <h5 class="recommended-section-title">兴趣爱好</h5>
                                        <p class="recommended-section-value-statement">列出信息,提高3倍人气</p></div>
                                    <button class="recommended-section-add">添加信息</button>
                                </li>
								
                                <li class="recommended-section-langue" style='display:none'>
                                    <div class="recommended-section-content">
                                        <h5 class="recommended-section-title">兴趣爱好</h5>
                                        <p class="recommended-section-value-statement">列出信息,提高3倍人气</p></div>
                                    <button class="recommended-section-add">添加学校</button>
                                </li>
                                <li class="recommended-section-honor" style='display:none'>
                                    <div class="recommended-section-content">
                                        <h5 class="recommended-section-title">荣誉奖项</h5>
                                        <p class="recommended-section-value-statement">列出信息,提高3倍人气</p></div>
                                    <button class="recommended-section-add">添加经历</button>
                                </li>
                               
                                <li class="recommended-section-ccpm" style='display:none'>
                                    <div class="recommended-section-content">
                                        <h5 class="recommended-section-title">资格认证</h5>
                                        <p class="recommended-section-value-statement">列出信息,提高3倍人气</p></div>
                                    <button class="recommended-section-add">添加学校</button>
                                </li>
                                <li class="recommended-section-program" style='display:none'>
                                    <div class="recommended-section-content">
                                        <h5 class="recommended-section-title">所做项目</h5>
                                        <p class="recommended-section-value-statement">列出信息,提高3倍人气</p></div>
                                    <button class="recommended-section-add">添加经历</button>
                                </li>
                            </ul>
                            <div class="view-bar view-more-bar" style='display:none'>
                                <span>展开</span>
                            </div>
                        </div>
                       <#if ((userInfo.ismarry)?? && userInfo.ismarry?length gt 0 && userInfo.ismarry!=2) || ((userInfo.birthdayone)?? && userInfo.birthdayone?length gt 0) || ((userInfo.birthdaytwo)?? &&  userInfo.birthdaytwo?length gt 0) || ((userInfo.description)?? &&  userInfo.description?length gt 0)>
							<div class="background-selfinfo-container section-container" id='self-info-detail'>
							    <input type='hidden' name='ismarry' value='<#if (userInfo.ismarry)??>${userInfo.ismarry}</#if>'>
							    <input type='hidden' name='birthdayone' value='<#if (userInfo.birthdayone)??>${userInfo.birthdayone} </#if>'>
							    <input type='hidden' name='birthdaytwo' value='<#if (userInfo.birthdaytwo)??>${userInfo.birthdaytwo} </#if>'>
								<div class="header">
									<span>个人信息</span>
								</div>
								<div class="birthday">
								  <#if (userInfo.birthdayone)?? && userInfo.birthdayone?length gt 0>
									<span class='birth'>生日:&nbsp;&nbsp;${userInfo.birthdayone}月${userInfo.birthdaytwo}日</span>
									<a href="javascript:;" class="zu-edit-button" name="editbirthday" ><i class="zu-edit-button-icon"></i>修改</a>
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
									  <a href="javascript:;" class="zu-edit-button" name="editmarriage" ><i class="zu-edit-button-icon"></i>修改</a>
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
									<a href="javascript:;" class="zu-edit-button" name="editintroinfo" ><i class="zu-edit-button-icon"></i>修改</a>
							     </#if>
								</div>
						   </div>
					  </#if>
                      
					  <#if (eduList)?? && eduList?size gt 0>
					    <#list eduList as edulist>
						 <#if edulist_index==0>
						 <#assign edittype=1 />
					     <div class="background-education-container section-container" id='education${edulist_index}'>
						<#else>
						 <#assign edittype=0 />
						 <div class="background-education-container section-container education-container" id='education${edulist_index}'>
						</#if>
							   <input type='hidden' name='school' value='${edulist.school?html}'>
							   <input type='hidden' name='major' value='${edulist.major?html}'>
							   <input type='hidden' name='starttime' value='${edulist.starttime?html}'>
							   <input type='hidden' name='endtime' value='${edulist.endtime?html}'>
							   <input type='hidden' name='currentid' value='${edulist.id?html}'>
                             <#if edulist_index==0>
							  <div class="header">
								<span>教育背景</span> 
							    <a href="javascript:void(0)" id="addcontinuous" class="add-all ml20  add-all-able" data-addtype='0'>
								  <span>+</span>&nbsp;继续添加
							    </a>
							 </div>
							 </#if>
							  <div class="education">
									   学校名称:&nbsp;&nbsp;
								<#if (edulist.school)?? && edulist.school?length gt 0>
								 <span class='schoolname'>
                                     ${edulist.school?html}
								</span>
								<a href="javascript:;" class="zu-edit-button" data-currentid='${edulist.id}' name="editschoolname"><i class="zu-edit-button-icon"></i>修改</a>
							   </#if>
								
							  </div>
							  <div class='graduate'>
								<span class='schoolinfo'>
								   <span class="schooltime">
									  在校时间:&nbsp;&nbsp;
									<#if (edulist.starttime)??>
									  ${edulist.starttime?date}--${edulist.endtime?date}
									  <a href="javascript:;" class="zu-edit-button" data-currentid='${edulist.id}' data-edittype='${edittype}' name="editschooltime"><i class="zu-edit-button-icon"></i>修改</a>
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
									<a href="javascript:;" class="zu-edit-button" data-currentid='${edulist.id}' data-edittype='${edittype}' name="editsmajor"><i class="zu-edit-button-icon"></i>修改</a>
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
							 <div class="background-workexperience-container section-container" id='work${worklist_index}'>
							<#else>
							 <#assign edittype=0 />
							 <div class="background-workexperience-container section-container workexperience-container" id='work${worklist_index}'>
						   </#if>
						       <input type='hidden' name='companyname' value=' <#if (worklist.company)?? && worklist.company?length gt 0>${worklist.company?html}'</#if>>
							   <input type='hidden' name='description' value=' <#if (worklist.description)?? && worklist.description?length gt 0>${worklist.description?html}'</#if>>
							   <input type='hidden' name='jobtitle' value=' <#if (worklist.jobtitle)?? && worklist.jobtitle?length gt 0>${worklist.jobtitle?html}'</#if>>
							   <input type='hidden' name='onjobstartTime' value=' <#if (worklist.starttime)?? && worklist.starttime?length gt 0>${worklist.starttime}'</#if>>
							   <input type='hidden' name='onjobendTime' value=' <#if (worklist.endtime)?? && worklist.endtime?length gt 0>${worklist.endtime}'</#if>>
							   <input type='hidden' name='currentid' value='${worklist.id}'>
							   <#if worklist_index==0>
								  <div class="header">
									<span>工作经历</span> 
									<a href="javascript:void(0)" id="addcontinuouswork" class="add-all ml20  add-all-able" data-addtype='0'>
									  <span>+</span>&nbsp;继续添加
									</a>
								 </div>
							   </#if>
								  <div class="company">
									   公司名称:&nbsp;&nbsp;
									 <#if (worklist.company)?? && worklist.company?length gt 0>
									   <span class='companyname'>
									      ${worklist.company?html}
									   </span>
									   <a href="javascript:;" class="zu-edit-button" name="editscompanyname" data-currentid='${worklist.id}'><i class="zu-edit-button-icon"></i>修改</a>
									 </#if>
								  </div>
								  <div class='onjob'>
									<span class='onjobinfo'>
									   <span class="onjobtime">
										 在职时间:&nbsp;&nbsp;
											<#if (worklist.starttime)?? && worklist.starttime?length gt 0>
												  ${worklist.starttime?date}--${worklist.endtime?date}
												 <a href="javascript:;" class="zu-edit-button" name="editsonjobtime"  data-currentid='${worklist.id}'><i class="zu-edit-button-icon"></i>修改</a>
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
											<a href="javascript:;" class="zu-edit-button" name="editsposition" data-currentid='${worklist.id}' ><i class="zu-edit-button-icon"></i>修改</a>
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
										<a href="javascript:;" class="zu-edit-button" name="editsdescription" data-currentid='${worklist.id}'><i class="zu-edit-button-icon"></i>修改</a>
									 </#if>
									</span>
								  </div>
							  </div>
						</#list>
					  </#if>
                    </div>
					<#if (personalSkill)?? && personalSkill?size gt 0>
					  <#list personalSkill as skill>
					      <div class="background-skills-container section-container zg-empty" id='skill0'>
						    <div class="header">
								<span>技能专长</span> 
								<a href="javascript:void(0)" id="addcontinuousskill" class="add-all ml20  add-all-able" data-skillid='${skill.id}' data-skillitem='${skill.skillitem}'>
								  <span>+</span>&nbsp;继续添加
								</a>
						    </div>
						    <div class="skills-detail zg-empty">
							  <#list skill.skillitem?split(',') as items>
							     <#assign item=items?split(':')/>
								 <span class='skillzone' data-skillid='${item[0]}'>${item[1]}</span>
							  </#list>
							  <a href="javascript:;" class="zu-edit-button" name="editskill" data-skillid='${skill.id}' data-skillitem='${skill.skillitem}'>
							      <i class="zu-edit-button-icon"></i>修改
							  </a>
							</div>
						  </div>
					  </#list>
					</#if>
                </div>
                <div id="bd-2">
                    <div class="advertisement">
      
                    </div>
                    <div class="seeme">
                        <div class="recent-seeme">
                            <span>最近访问</span>
                            <span class='more'><a href='/51jobplusCore/myHome/moreRecentVistors?userid=${Session.user.userid}' self="_blank">更多>></a></span>
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
											  <#if (list.fansIds?string==Session.user.userid?string)>
												<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-unfollow"  data-actiontype='0'>取消关注</button>
											  <#else>
												<button  data-userid="${list.visitorid}" class="zg-btn zm-rich-follow-btn small nth-0 zg-btn-follow"  data-actiontype='1'>关注</button>
											  </#if>
										<#else>
											 <#if list.fansIds?split(",")?seq_contains(Session.user.userid?string)>
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
            <div class='homepageTemplate'></div>
            <div class='pagetemplate'></div>
            
            <a id="backcentertop" title="回到顶部" href="#centertop" style="display:none; bottom:300px;"></a>
            <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
            <script type="text/javascript" src="/51jobplusCore/scripts/jquery-1.8.0.min.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/jquery-jtemplates.js"></script> 
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_randomid.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_constant.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_chosen.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_loadPmAndsmg.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_publicMethod.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/jquery.pinwheel-0.1.0.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_area.js"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/pj_myhomepage.js" charset="UTF-8"></script>
            <script type="text/javascript" src="/51jobplusCore/scripts/dateSelect.js"></script>
    </body>

</html>