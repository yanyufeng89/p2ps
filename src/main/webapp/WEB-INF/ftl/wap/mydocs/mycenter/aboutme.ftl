<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>
       ${userInfo.username}-个人主页-JobPlus
    </title> 
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
	<link rel='stylesheet' type='text/css' href='/css/pj_wapmycenter.css'>
	<script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
	 <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap.js"></script>
     <script type="text/javascript" src="/scripts/pj_constant.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap_myhomepage.js"></script>
     <script type="text/javascript">
	         var phoneWidth =  parseInt(window.screen.width);
	         var phoneScale = phoneWidth/640;
	         var ua = navigator.userAgent;
	         if (/Android (\d+\.\d+)/.test(ua)){
	                   var version = parseFloat(RegExp.$1);
	                   if(version>2.3){
	                            document.write('<meta name="viewport" content="width=640, height=device-height minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
	                   }else{
	                            document.write('<meta name="viewport" content="width=640, height=device-height target-densitydpi=device-dpi">');
	                   }
	         } else {
	                   document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
	         }
	 </script>
  </head>
  <body>
    <div class="wrap bc">
      <#include "/mydocs/commonTemplate/topandtail/waptop.ftl"/>
      <div class='search-box' style='display:none'>
		    <input type="text" id="searchres_input" class="search_input ui-autocomplete-input" name="Condition"  tabindex="1" maxlength="64" autocomplete="off"  value="">
            <botton name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索"></botton>
	  </div>
	  <div class="top-ceng">
      <div class="top-card">
        <div class='profile-card clearfix'>
           <div class='profile-picture'>
             <#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
                <img src="${userInfo.headicon}" alt="个人头像" width="200" height="270" class='lazy'>
             <#else>
                <img src="/image/myphoto.jpg" alt="个人头像" width="200" height="270" class='lazy'>
             </#if>
           </div>
           <div class='profile-overview'>
              <div class="profile-overview-content">
                 <p id="name">
                     <span class="full-name">${userInfo.username?html}</span>
                     <span class="pj-level"><em>LV.${Session.user.userlevel}</em></span>
                 </p>
                 <p class='editable-item control-display'>
					<#if (userInfo.position)?? && userInfo.position?length gt 0>
					    ${userInfo.position?html}
					  <#else>
					    职位
					</#if>
                 </p>
                 <p  class='editable-item control-display'>
					 <#if (userInfo.compname)?? && userInfo.compname?length gt 0>
					    ${userInfo.compname?html}
					<#else>
					      公司名称
					</#if>
                 </p>
                 <p class='editable-item control-display'>
					<#if (userInfo.province)?? && userInfo.province?length gt 0>
					   <span class='province'>${userInfo.province?html}</span>
					<#else>
					   <span class='province'>所在省份</span>
					</#if>
					<#if (userInfo.city)?? && userInfo.city?length gt 0>
					    <span class='city'>${userInfo.city?html}</span>
					<#else>
					    <span class='city'>所在城市</span>
				    </#if>
                 </p>
                 <p class='editable-item control-display'>
                   <#if (userInfo.industry)?? && userInfo.industry?length gt 0>
				       <span class='industry'>${userInfo.industry?html}</span>
				   <#else>
				      <span class='industry'>行业</span>
			       </#if>
                 </p> 
                 <p class='editable-item control-display'>
                   <#if (eduList)?? && eduList?size gt 0>
				   <#list eduList as elist>
					  <#if elist_index==0>
						 <#assign school=elist.school />
					  </#if>
					</#list>
				    <span><#if (school)??>${school?html}</#if></span>
					<#else>
					<span>这家伙很懒，还没写教育背景</span>
				    </#if>
                 </p> 
              </div>
           </div>
        </div>
		<div class='profile-func'>
		   <#if (userInfo.userid?string!=(Session.user.userid?string)!)>
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
		</div>
		<div class='profile-card-extras'>
		   <div id="contact-info-section" class="more-info entity" style='display:none'>
		   <h3>联系方式<em></em></h3>
		   <div class="contact-info clearfix">
			 <div class="email">
			   <span>邮箱:&nbsp;</span>
			  
			   <#if (userInfo.contactEmail)?? && userInfo.contactEmail?length gt 0>
				 <span>${userInfo.contactEmail}</span>
			   <#else>
																	  未知
			   </#if>
			  
			 </div>
			  <div class="phone">
				<span>电话:&nbsp;</span>
				
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
								<span class="public-profile-url control-display">${homePageUrl}</span>
								<a class="public-profile-settings-link" href="" title="更新公开资料徽章的设置"></a>
							 </dd>
					   </dl>
				  </li>
			  </ul>
			  <div class="show-more-info" id='show-more-info'>
				 <a href="javascript:void(0)">
					<span class="contact" ><b class='t-down'></b>&nbsp;联系方式</span>
				 </a>
			  </div>
		 </div>
		</div>
	  </div>
	  </div>
	  <div class='bottom-ceng'>
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
			<span>个人简介</span>
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
							         <img src="${edulist.schoolLogo}" alt="${edulist.school?html}" width="80" height="80" class='lazy'>
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
					
                    <div class='profile-navbar clearfix' id='fans-navbar' style='display:none'>
					     <a class="pjitem current" href="javascript:void(0)" data-userid='${userInfo.userid}'>
							<#if userInfo.userid==(Session.user.userid)!>我关注的人<#else>他关注的人</#if><span class="num">
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
								    <#if slist.type='tbl_docs'>[文档]<#elseif slist.type='tbl_topics'>[话题]<#elseif slist.type='tbl_books'>[书籍]<#elseif slist.type='tbl_article'>[文章]<#elseif slist.type='tbl_courses'>[课程]<#else>[站点]</#if><a href="${slist.objurl}"  target="_blank" title='${slist.title}'><span class='allnewsname ellipsis'>${slist.title}</span></a>
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
					<div class='empty-div'></div>
	</div>
	</div>
	 <div class='pagetemplate'></div>
	 
	 
  </body>

</html>
