<!DOCTYPE html>
<html class="expanded">   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <title>${userInfo.username}-个人主页-JobPlus</title>
        <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
        <link rel="stylesheet" type="text/css" href="/css/pj_mycom.css">
        <link rel="stylesheet" type="text/css" href="/css/dateSelect.css">
        <link rel="stylesheet" type="text/css" href="/css/jquery.confirm.css">
        <link rel="stylesheet" type="text/css" href="/css/jquery.pinwheel-0.1.0.css">
    </head>
    <body id='comtop'>
      <div  class="page">
        <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
        <div class='com-main'>
          <div class='top-card container clearfix'>
              <div class='top-card-company-logo logo' id='comlogo'>
                  <#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
                     <img src="${userInfo.headicon}"  class='lazy' alt="个人头像" width="180" height='180' id="imghead">
                  <#else>
                     <img src="/image/cm-defaultIcon.jpg" class='lazy' alt="个人头像" width="180" height='180' id="imghead">
                  </#if>
                    <span class="ProfileAvatarEditor-tip">更换logo</span>
                    <form method="POST"  id="previewImage" enctype="multipart/form-data">
                      <input name='headIconFile' class="file-3" type="file" accept="image/jpg,image/jpeg,image/png,image/pns" size="30" onchange="previewImage(this,1)" />
                   </form>
              </div>
              <div class='top-card-data'>
                <div class='profile-overview-content'>
                    <div id="title-container" class='title-container editable-item clearfix'>
                        <input type='hidden' name='username' value='${userInfo.username?html}'>
                        <span class="title"> ${userInfo.username?html}</span>
						<a href="javascript:;" class="zu-edit-button" name="edittitle" data-type='title'><i class="zu-edit-button-icon"></i>修改</a>
                    </div> 
                    <div id="slogan-container" class='slogan-container editable-item clearfix'>
                       <input type='hidden' name='slogan' value='${userInfo.description}'>
                       <span class="slogan"><#if ((userInfo.description)?? && userInfo.description?length gt 0)>${userInfo.description}<#else>企业标语</#if></span>
                       <a href="javascript:;" class="zu-edit-button" name="editslogan" data-type='slogan'><i class="zu-edit-button-icon"></i>修改</a>
                    </div>
                    <div id='enterprise-container' class='enterprise-container editable-item clearfix'>
                       <input type='hidden' name='enterprise' value='${userInfo.specialty}'>
                       <span class="enterprise">
                       <#if ((userInfo.specialty)?? && userInfo.specialty?length gt 0)>
                       <#list userInfo.specialty?split(" ") as item>
                         <#if item_index=0>
                          ${item}
                         <#else>
                          &nbsp;${item}
                         </#if>
                       </#list>
                       <#else>
                                                          企业产品(服务)
                       </#if>
                       </span>
                       <a href="javascript:;" class="zu-edit-button" name="editsenterprise" data-type='enterprise'><i class="zu-edit-button-icon"></i>修改</a>
                    </div>
                 </div>
                 <div id='preview-container'>
                   <div class="profile-actions">
                       <a href="/comp/getHomePage/${Session.user.userid}" class="preview-profile button-primary">企业主页预览</a>
				   </div>
                 </div>
              </div>
			  <div class='profile-func clearfix' id='company-tab'>
			    <span class="personal-data current" id="latest-update">最新快讯</span>
				<label class="split">|</label>
			    <span class="personal-data" id="company-profile">公司简介</span>
			  </div>
          </div>
		  <div class='middle-card container clearfix'>
		     <div class='middle-card-left tab-flash'>
			    <div class='public-flash'>
				  <h4 class="recent-updates-heading com-heading">发布快讯</h4>
				  <div  class="share-view fixed-full">    
				     <div class="share-view-content">
				        <div class="identity-header">
				            <div class='post-meta ember-view'>
				            	<#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
			                      <img class="avatar loaded" alt="JobPlus China" width='40' height='40' src="${userInfo.headicon}">
			                  <#else>
			                     <img class="avatar loaded" alt="JobPlus China" width='40' height='40' src="/image/cm-defaultIcon180.jpg">
			                  </#if>
				               <a class='com-name' href='javascript:void(0)'>
				                 <span class="name semibold">${userInfo.username}</span>
				               </a>
				            </div>
				        </div>
				        
				        <form method="POST" action="/comp/addCpNews" id="adNewsForm" enctype="multipart/form-data">
					        <div class='content expanded' id='addNewsContent'>
					           <textarea name='news' class='mentions-input' placeholder='发文章、照片和动态' rows="6" cols="20"></textarea>
					        </div>
					        <div class='share-flash-bar ember-view clearfix'>
			                    <form method="POST"  id="previewImage" enctype="multipart/form-data">
			                      <input name='newsImgFiles' class="file-3"  id="newsImgFiles"  type="file" accept="image/jpg,image/jpeg,image/png,image/pns" size="30"/>
			                   </form>
			                    <input type='hidden' name='sitetitle' value=''>
			                    <input type='hidden' name='siteurl' value=''>
					            <a class="photo-button com-icon" href='javascript:void(0)'></a>
					            <a class="link-button com-icon" id="link-button" href='javascript:void(0)'></a>
					            <a class="zg-r3px zg-btn-blue" id='public-flash'>发布</a>
					        </div>
				        </form>
				        
				     </div>
				  </div>
				</div>
				<div class='entity-all separate-entities ember-view'>
				   <h4 class="recent-updates-heading com-heading">最新快讯</h4>
				   <input type='hidden' name='companyId' value='${userInfo.userid}'>
				   <input type='hidden' name='companyName' value='${userInfo.username}'>
				   <#if (cpNewes.list)?? && cpNewes.list?size gt 0>
				   <ul class='entity-list row' id='entity-list'>
				   	<#list cpNewes.list as oneNews>
				   	<li class='entity-list-item' id="entity-list-item-${oneNews.id}">
				   		<div class='post-meta ember-view'>
				   			<a href='javascript:void(0)'>
				   				<#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
			                      <img class="lazy-image avatar loaded" alt="JobPlus China" width='50' height='50' src="${userInfo.headicon}">
			                  <#else>
			                      <img class="lazy-image avatar loaded" alt="JobPlus China" width='50' height='50' src="/image/cm-defaultIcon180.jpg">
			                  </#if>
				   			</a>
				   			<a href='javascript:void(0)'>
				   				<h3 class="actor">
				   					<span class="name semibold">${userInfo.username}</span>
				   					<time class="timestamp">${oneNews.createtime?string("yyyy-MM-dd HH:mm:ss")}</time>
				   				</h3>
				   			</a>
				   		</div>
				   		<#if (oneNews.news)?? && oneNews.news?length gt 0>
				   		<div  class="inline-show-more-text feed-s-main-content ember-view" data-flash='${oneNews.id}'>  
					   		<#if oneNews_index==0 && (oneNews.istop==1)>     
					            <i class="com-top com-icon"></i>
					        </#if>
					        ${oneNews.news}
				   		</div>
				   		</#if>     
				   		<#if (oneNews.siteurl)?? && oneNews.siteurl?length gt 0>
				   		<div class='flash-url'>
			               <a href='${oneNews.siteurl}' target='_blank'>${oneNews.sitetitle}<span class='siteurl'>${oneNews.siteurl}</span></a>
				   		</div>
				   		</#if>
				   		<#if (oneNews.imgurl)?? && oneNews.imgurl?length gt 0>
				   		<div class='flash-img'>
			              <img src='${oneNews.imgurl}' alt=''>
				   		</div>
				   		</#if>
				   		<div class='operate-container'>
				   		    <#if (oneNews.likedIds)??&&oneNews.likedIds?length gt 0>
	                           <#if oneNews.likedIds?index_of(",")==-1>
	                            <#if (oneNews.likedIds?string==(Session.user.userid?string)!)>
	                              <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='1' data-flashid='${oneNews.id}' data-likenum='${oneNews.likesum}'>取消赞  (${oneNews.likesum})</a>
	                            <#else> 
	                              <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='0' data-flashid='${oneNews.id}' data-likenum='${oneNews.likesum}'>赞  ${oneNews.likesum}</a>                                   
	                            </#if>                                             
	                           <#else>
	                              <#if oneNews.likedIds?split(",")?seq_contains((Session.user.userid?string)!)>
	                                 <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='1' data-flashid='${oneNews.id}' data-likenum='${oneNews.likesum}'>取消赞  (${oneNews.likesum})</a>
	                              <#else>
	                                 <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='0' data-flashid='${oneNews.id}' data-likenum='${oneNews.likesum}'>赞  ${oneNews.likesum}</a> 
	                              </#if>
	                           </#if>    
	                          <#else> 
	                                 <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='0' data-flashid='${oneNews.id}' data-likenum='${oneNews.likesum}'>赞  ${oneNews.likesum}</a>                                
	                          </#if>
				   			<a class="numComments operate" href='javascript:void(0)' data-flashid='${oneNews.id}' data-commsum='${oneNews.commentsum}'>评论 ${oneNews.commentsum}</a>
				   			<a href='javascript:void(0)'   class='update-btn operate'>
				   				<span class='delete-falsh' data-newsid='${oneNews.id}'><b class="iconfont pr2"></b>删除</span>
				   				<span class='stick-botton' data-newsid='${oneNews.id}'><i class='com-stick com-icon'></i>置顶</span>
				   			</a>
				   		</div>
				   	</li>
				   	</#list>
				   	 <#if (cpNewes.last gt 1)>
					 <a href="javascript:;" data-pageno="1"  data-sumpage="${cpNewes.last}" data-comtype='1' class="zg-btn-white  zu-button-more flash-load-more">更多</a>
					 </#if>
					</ul>
				   <#else>
				    <ul class='entity-list row' id='entity-list'>
				    <li class='no-flashlist'>暂无最新快讯</li>
				    </ul>
				   </#if>
				   
				</div>
			 </div>
			 <div class='middle-card-left tab-profile' style='display:none'>
			      <input type='hidden' name='nation' value='<#if (cpnInfo.nation)??>${cpnInfo.nation}</#if>'>
			      <input type='hidden' name='province' value='<#if (cpnInfo.province)??>${cpnInfo.province}</#if>'>
			      <input type='hidden' name='city' value='<#if (cpnInfo.city)??>${cpnInfo.city}</#if>'>
			      <input type='hidden' name='postcode' value='<#if (cpnInfo.postcode)??>${cpnInfo.postcode}</#if>'>
			      <input type='hidden' name='address' value='<#if (cpnInfo.address)??>${cpnInfo.address}</#if>'>
			      <input type='hidden' name='url' value='<#if (cpnInfo.url)??>${cpnInfo.url}</#if>'>
			      <input type='hidden' name='scale' value='<#if (cpnInfo.scale)??>${cpnInfo.scale}</#if>'>
			      <input type='hidden' name='industry' value='<#if (cpnInfo.industry)??>${cpnInfo.industry}</#if>'>
			      <input type='hidden' name='establishtime' value='<#if (cpnInfo.establishtime)??>${cpnInfo.establishtime?string("yyyy")}</#if>'>
			      <input type='hidden' name='type' value='<#if (cpnInfo.type)??>${cpnInfo.type}</#if>'>
			    <div class='public-profile'>
				   <h4 class="com-heading">图片</h4>
				   <div class='profile-logo' id='briefimg'>
				   <#if (cpnInfo.imgurl)?? && cpnInfo.imgurl?length gt 0>
			         <img src="${cpnInfo.imgurl}" class='lazy' alt="简介图片" width="834" height='220' id='imgbrief'>
			       <#else>
			         <img src="/image/bg-computer.jpg" class='lazy' alt="简介图片" width="834" height='220' id='imgbrief'>
			       </#if>
                    <span class="update-comimg-tip">更换图片</span>
                    <form method="POST"  id="briefImage" enctype="multipart/form-data">
                      <input name='imgFiles' class="file-3" id="brieffile" type="file" accept="image/jpg,image/jpeg,image/png,image/pns" size="30" />
                   </form>
				   </div>
				   <h4 class="com-heading">公司介绍</h4>
				   <div class='profile-brief clearfix'>
				    <form id='profile-brief' onsubmit="return postBriefData()"> 
				     <span class='intro'><#if (cpnInfo.intro)?? && cpnInfo.intro?length gt 0>${cpnInfo.intro}</#if></span>
				     <a href="javascript:;" class="zu-edit-button" name="editscombrief"><i class="zu-edit-button-icon"></i>修改</a>
				    </form> 
				   </div>
				    <h4 class="com-heading">业务领域</h4>
				    <div class='business-area clearfix'>
				    <form id='business-area' onsubmit="return postAreaData()">
				    <input type='hidden' name='busiarea' value='${cpnInfo.busiarea}'>
				    <div class='area'>
				     <#if (cpnInfo.busiarea)?? && cpnInfo.busiarea?length gt 0>
				      <#list cpnInfo.busiarea?split(',') as items>
						 <#assign item=items?split(':')/>
							<span class="areazone" data-areaid="${item[0]}">${item[1]}</span>
					  </#list>
					 </#if>
					 <a href="javascript:;" class="zu-edit-button" name="editsarea"  data-areaitem='${cpnInfo.busiarea}' style='vertical-align:middle;'><i class="zu-edit-button-icon"></i>修改</a>
					 </div>
				     
				     </form>
				    </div>
				    <div class='com-address clearfix'>
				      <form id='com-address' onsubmit="return postAddressData()">
				        <ul>
				         <li>
				           <#--<#if (cpnInfo.nation)?? && cpnInfo.nation?length gt 0>
				             <span>国家/地区</span>
				             <p>${cpnInfo.nation}<a href="javascript:;" class="zu-edit-button" name="editsnation"><i class="zu-edit-button-icon"></i>修改</a></p>
				           <#else>
				             <span>国家/地区<a href="javascript:;" class="zu-edit-button" name="editsnation"><i class="zu-edit-button-icon"></i>修改</a></span>
				           </#if>-->
				           <span>国家/地区</span>
				           <p>
				             <#if (cpnInfo.nation)?? && cpnInfo.nation?length gt 0>
				             ${cpnInfo.nation}
				             </#if>
				             <a href="javascript:;" class="zu-edit-button" name="editsnation"><i class="zu-edit-button-icon"></i>修改</a>
				           </p>
				         </li>
				         <li>
				            <#--<#if (cpnInfo.province)?? && cpnInfo.province?length gt 0>
				             <span>省/自治区/直辖市</span>
				             <p>${cpnInfo.province}<a href="javascript:;" class="zu-edit-button" name="editsprovince"><i class="zu-edit-button-icon"></i>修改</a></p>
				            <#else>
				             <span>省/自治区/直辖市<a href="javascript:;" class="zu-edit-button" name="editsprovince"><i class="zu-edit-button-icon"></i>修改</a></span>
				           </#if>-->
				            <span>省/自治区/直辖市</span>
				            <p>
				            <#if (cpnInfo.province)?? && cpnInfo.province?length gt 0>
				            ${cpnInfo.province}
				            </#if>
				            <a href="javascript:;" class="zu-edit-button" name="editsprovince"><i class="zu-edit-button-icon"></i>修改</a>
				            </p>
				         </li>
				         <li <#if (cpnInfo.city)??><#else>style='margin-top:20px;'</#if>>
				           <#--<#if (cpnInfo.city)?? && cpnInfo.city?length gt 0>
				             <span>市/区</span>
				             <p>${cpnInfo.city}<a href="javascript:;" class="zu-edit-button" name="editscity"><i class="zu-edit-button-icon"></i>修改</a></p>
				           <#else>
				             <span>市/区<a href="javascript:;" class="zu-edit-button" name="editscity"><i class="zu-edit-button-icon"></i>修改</a></span>
				           </#if>-->
				           <span>市/区</span>
				           <p>
				           <#if (cpnInfo.city)?? && cpnInfo.city?length gt 0>
				             ${cpnInfo.city}
				           </#if>
				           <a href="javascript:;" class="zu-edit-button" name="editscity"><i class="zu-edit-button-icon"></i>修改</a>
				           </p>
				         </li>
				         <li <#if (cpnInfo.postcode)??><#else>style='margin-top:20px;'</#if>>
				           <#--<#if (cpnInfo.postcode)?? && cpnInfo.postcode?length gt 0>
				             <span>邮编</span>
				             <p>${cpnInfo.postcode}<a href="javascript:;" class="zu-edit-button" name="editspostcode"><i class="zu-edit-button-icon"></i>修改</a></p>
				           <#else>
				             <span>邮编<a href="javascript:;" class="zu-edit-button" name="editspostcode"><i class="zu-edit-button-icon"></i>修改</a></span>
				           </#if>-->
				           <span>邮编</span>
				           <p>
				           <#if (cpnInfo.postcode)?? && cpnInfo.postcode?length gt 0>
					           ${cpnInfo.postcode}
					       </#if>
					           <a href="javascript:;" class="zu-edit-button" name="editspostcode"><i class="zu-edit-button-icon"></i>修改</a>
				           </p>
				         </li>
				         <li <#if (cpnInfo.address)??><#else>style='margin-top:20px;'</#if>>
				           <#--<#if (cpnInfo.address)?? && cpnInfo.address?length gt 0>
				             <span>详细地址</span>
				             <p>${cpnInfo.address}<a href="javascript:;" class="zu-edit-button" name="editsaddress"><i class="zu-edit-button-icon"></i>修改</a></p>
				           <#else>
				             <span>详细地址<a href="javascript:;" class="zu-edit-button" name="editsaddress"><i class="zu-edit-button-icon"></i>修改</a></span>
				           </#if>-->
				            <span>详细地址</span>
				            <p>
				            <#if (cpnInfo.address)?? && cpnInfo.address?length gt 0>
				            ${cpnInfo.address}
				            </#if>
				            <a href="javascript:;" class="zu-edit-button" name="editsaddress"><i class="zu-edit-button-icon"></i>修改</a>
				            </p>
				         </li>
				        </ul>
					 </form>
				    </div>
				    <div class='com-websit clearfix'>
					  <form id='com-websit' onsubmit="return postWebsitData()">
				       <ul>
				         <li>
				          <#--<#if (cpnInfo.url)?? && cpnInfo.url?length gt 0>
				           <span>公司网站</span>
				           <p><a href='${cpnInfo.url}' target='_blank' class='websiturl' title='${cpnInfo.url}'>${cpnInfo.url}</a> <a href="javascript:;" class="zu-edit-button" name="editsurl"><i class="zu-edit-button-icon"></i>修改</a></p>
				          <#else>
				          <span>公司网站<a href="javascript:;" class="zu-edit-button" name="editsurl"><i class="zu-edit-button-icon"></i>修改</a></span>
				          </#if>-->
				         
				           <span>公司网站</span>
				           <p>
				            <#if (cpnInfo.url)?? && cpnInfo.url?length gt 0>
				             <a href='${cpnInfo.url}' target='_blank' class='websiturl' title='${cpnInfo.url}'>${cpnInfo.url}</a> 
				            </#if>
				           <a href="javascript:;" class="zu-edit-button" name="editsurl"><i class="zu-edit-button-icon"></i>修改</a>
				           </p>
				         
				         </li>
				         <li>
				         <#--<#if (cpnInfo.scale)?? && cpnInfo.scale?length gt 0>
				           <span>公司规模</span>
				           <p>${cpnInfo.scale}<a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				         <#else>
				           <span>公司规模<a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></span>
				         </#if>-->
				          <span>公司规模</span>
				           <p>
				           <#if (cpnInfo.scale)?? && cpnInfo.scale?length gt 0>
				           ${cpnInfo.scale}
				           </#if>
				           <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a>
				           </p>
				         </li>
				         <li>
				          <#--<#if (cpnInfo.industry)?? && cpnInfo.industry?length gt 0>
				           <span>所属行业</span>
				           <p>${cpnInfo.industry} <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				          <#else>
				           <span>所属行业<a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></span>
				          </#if>-->
				          <span>所属行业</span>
				          <p>
				          <#if (cpnInfo.industry)?? && cpnInfo.industry?length gt 0>
				           ${cpnInfo.industry}
				          </#if>
				           <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a>
				          </p>
				         </li>
				         <li>
				          <#--<#if (cpnInfo.establishtime)??>
				           <span>创立年份</span>
				           <p>${cpnInfo.establishtime?string("yyyy")}<a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				          <#else>
				           <span>创立年份<a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></span>
				          </#if>-->
				           <span>创立年份</span>
				           <p>
				           <#if (cpnInfo.establishtime)??>
				           ${cpnInfo.establishtime?string("yyyy")}
				           </#if>
				           <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a>
				           </p>
				         </li>
				         <li>
				           <#--<#if (cpnInfo.type)?? && cpnInfo.type?length gt 0>
				            <span>公司类型</span>
				            <p>${cpnInfo.type} <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				           <#else>
				           <span>公司类型<a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></span>
				          </#if>-->
				          <span>公司类型</span>
				          <p>
				          <#if (cpnInfo.type)?? && cpnInfo.type?length gt 0>
				          ${cpnInfo.type} 
				          </#if>
				          <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				         </li>
				       </ul>
					   </form>
				    </div>
				</div>
			 </div>
			 <div class='middle-card-right'>
			   <div class="strength com-strength" id="com-strength">
                      <span>企业资料完善度</span>
                      <div class="strength-meter v2">
                         <div class="strength-data">
                            <div class="level-indicator"></div>
                           	<em class="fill-marker" style="visibility:visible; <#if completion==100>width:180px;bottom:84px;left:68px<#elseif completion==75>width:160px;bottom:64px;left:88px<#elseif completion==50>width:160px;bottom:44px;left:88px<#elseif completion==25>width:160px;bottom:24px;left:88px</#if>"></em>
                            <p class="current-level-description" style="visibility: visible; bottom: <#if completion==100>86px;<#elseif completion==75>64px;<#elseif completion==50>44px;<#else>24px;</#if>; opacity: 1;"><#if completion==100>高级<#elseif completion==75>中级<#elseif completion==50>初级<#elseif completion==25>初级</#if></p>
                          </div>
                          <canvas id="waterbubble" class="mask" width="82" height="82"></canvas>
                      </div>
                </div>
                <div class="seeme">
					  <div class="myattention">
					   <div class="top-border">
					    <p>
						    <a href="/comp/getHomePage/${Session.user.userid}?requesType=0" target="_self"  data-userid='${userInfo.userid}'>
                               <span class="count">
								 <#list Session.myHeadTop!?keys as itemKey>
									 <#if itemKey="attenManSum">
										${Session.myHeadTop[itemKey]}
									 </#if>
								 </#list> 
							   </span>
                               <span class="count-concern">我的关注</span>
                             </a>
                          </p>
						  <p>
							  <a href="/comp/getHomePage/${Session.user.userid}?requesType=1" target="_self"   data-userid="${userInfo.userid}">
                               <span class="count">
									 <#list Session.myHeadTop!?keys as itemKey>
										 <#if itemKey="fansSum">
											${Session.myHeadTop[itemKey]}
										 </#if>
									</#list> 
							   </span>
                               <span class="count-concern">我的粉丝</span>
                              </a> 
                          </p>
						</div>
					  </div>
					  
                      <div class="recent-seeme" style="border-top:1px solid #e5e5e5; padding-top:10px;">
                            <span>最近访问</span>
                            <#if visitors.last gt 1>
                            <span class='more'><a href='/comp/getHomePage/${Session.user.userid}?requesType=3' target="_self">更多>></a></span>
                            </#if>
                       </div>
                       <div class='detail-list zg-clear'>
						  <#if (visitors.list)?? && visitors.list?size gt 0>
							<#list visitors.list as list>
								<a title="${list.userName}"  class="zm-item-link-avatar uname" target='_blank' href='/myHome/getHomePage/${list.visitorid}'  data-moduletype='1'>
									   <#if (list.headIcon)??>
										 <img src="${list.headIcon}" alt="个人头像" class="zm-item-img-avatar lazy <#if list.usertype==2>company-img</#if>" data-userid="${list.visitorid}">
									   <#else>
										  <img src="/image/1b48b5a75c71597_100x100.jpg" alt="个人头像" class="zm-item-img-avatar lazy <#if list.usertype==2>company-img</#if>" data-userid="${list.visitorid}">
									   </#if>
							    </a>
							</#list>
							<#else>
							<span class="no-flashlist">暂无最近访问</span>
					      </#if> 
					   </div>
					  
                    </div>
			 </div>
		  </div>
        </div>
      </div>
      <div class='homepageTemplate pagetemplate'></div>
      <a id="backcomtop" title="回到顶部"  class='back-to-top' href="#comtop" style="display:none; bottom:300px;"></a>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
      <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script> 
      <script type="text/javascript" src="/scripts/pj_changeHeadIcon.js" charset="UTF-8"></script>
      <script type="text/javascript" src="/scripts/jquery.pinwheel-0.1.0.js"></script>
      <script type="text/javascript" src="/scripts/waterbubble.js"></script>
      <script type="text/javascript" src="/scripts/pj_mycompage.js"></script>
      <script type="text/javascript" src="/scripts/pj_randomid.js"></script>
      <script type="text/javascript" src="/scripts/pj_chosen.js"></script>
      <script type="text/javascript" src="/scripts/pj_constant.js"></script>
      <script type="text/javascript" src='/scripts/pj_centerCommon.js'></script>
      <script type="text/javascript" src="/scripts/pj_loadPmAndsmg.js"></script>
      <script type="text/javascript" src="/scripts/jquery.confirm.js"></script>
      <script type="text/javascript" src="/scripts/pj_msgbox.js"></script>
      <script type="text/javascript" src="/scripts/pj_constant.js"></script>
      <script type="text/javascript" src="/scripts/pj_publicMethod.js"></script>
      <script type="text/javascript" src="/scripts/pj_country.js"></script>
      <script type="text/javascript" src="/scripts/jquery.simplePagination.js"></script>
      <script type="text/javascript">
        $('#waterbubble').waterbubble({
			    radius:40,
			    lineWidth:2,
			    data:${completion/100},
			    waterColor: '#7cbb2f',
			    textColor: '',
			    outsideColor:'#cdcdcd',
			    txt: '',
			    font: 'bold 10px "Microsoft YaHei"',
			    wave: false,
			    animation: false
		});
	</script>
    </body>
</html>