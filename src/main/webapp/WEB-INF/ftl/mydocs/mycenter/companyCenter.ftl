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
                     <img src="${userInfo.headicon}"  class='lazy' alt="个人头像" width="180"  id="imghead">
                  <#else>
                     <img src="/image/cm-defaultIcon.jpg" class='lazy' alt="个人头像" width="180"  id="imghead">
                  </#if>
                    <#--<img src="/image/cm-defaultIcon180.jpg" class='lazy' alt="个人头像" width="180"  id="imghead">-->
                    <span class="ProfileAvatarEditor-tip">更换头像</span>
                    <form method="POST"  id="previewImage" enctype="multipart/form-data">
                      <input name='headIconFile' class="file-3" type="file" accept="image/*" size="30" onchange="previewImage(this,1)" />
                   </form>
              </div>
              <div class='top-card-data'>
                <div class='profile-overview-content'>
                    <div id="title-container" class='clearfix'>
                        <span class="title"> ${userInfo.username?html}</span>
						<a href="javascript:;" class="zu-edit-button" name="edittitle" data-type='title'><i class="zu-edit-button-icon"></i>修改</a>
                    </div> 
                    <div id="slogan-container" class='slogan-container clearfix'>
                       <span class="slogan"><#if ((userInfo.description)?? &&  userInfo.description?length gt 0)>${userInfo.description}<#else></#if></span>
                       <a href="javascript:;" class="zu-edit-button" name="editslogan" data-type='slogan'><i class="zu-edit-button-icon"></i>修改</a>
                    </div>
                    <div id='enterprise-container' class='enterprise-container clearfix'>
                       <span class="enterprise"><#if ((userInfo.specialty)?? &&  userInfo.specialty?length gt 0)>${userInfo.specialty}<#else></#if></span>
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
				  <h4 class="recent-updates-heading com-heading">
					  发布快讯
				  </h4>
				  <div  class="share-view fixed-full">    
				     <div class="share-view-content">
				        <div class="identity-header">
				            <div class='post-meta ember-view'>
				            	<#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
			                      <img class="avatar loaded" alt="JobPlus China" width='50' height='50' src="${userInfo.headicon}">
			                  <#else>
			                     <img class="avatar loaded" alt="JobPlus China" width='50' height='50' src="http://m.c.lnkd.licdn.com/mpr/mpr/shrinknp_100_100/AAEAAQAAAAAAAAg9AAAAJGRiNDAyM2E2LWYwMjUtNGVkOS1iMzU2LWEzMjIyOGRmNmQzYg.png">
			                  </#if>
				               <a class='com-name' href='javascript:void(0)'>
				                 <span class="name semibold">${userInfo.username}</span>
				               </a>
				            </div>
				        </div>
				        <div class='content expanded'>
				           <textarea name='flash' class='mentions-input' placeholder='发文章、照片和动态' rows="6" cols="20"></textarea>
				        </div>
				        <div class='share-flash-bar ember-view clearfix'>
				            <a class="photo-button com-icon" href='javascript:void(0)'></a>
				            <a class="link-button com-icon" href='javascript:void(0)'></a>
				            <a disabled=""  class="zg-r3px zg-btn-blue" id='public-flash'>发布</a>
				        </div>
				     </div>
				  </div>
				</div>
				<div class='entity-all separate-entities ember-view'>
				   <h4 class="recent-updates-heading com-heading">
					      最新快讯
				   </h4>
				   <ul class='entity-list row'>
				   <#if (cpNewes)?? && cpNewes?size gt 0>
				   	<#list cpNewes.list as oneNews>
				   	<li class='entity-list-item'>
				   		<div class='post-meta ember-view'>
				   			<a href='javascript:void(0)'>
				   				<#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
			                      <img class="avatar loaded" alt="JobPlus China" width='50' height='50' src="${userInfo.headicon}">
			                  <#else>
			                     <img class="avatar loaded" alt="JobPlus China" width='50' height='50' src="http://m.c.lnkd.licdn.com/mpr/mpr/shrinknp_100_100/AAEAAQAAAAAAAAg9AAAAJGRiNDAyM2E2LWYwMjUtNGVkOS1iMzU2LWEzMjIyOGRmNmQzYg.png">
			                  </#if>
				   			</a>
				   			<a href='javascript:void(0)'>
				   				<h3 class="actor">
				   					<span class="name semibold">${userInfo.username}</span>
				   					<time class="timestamp">${oneNews.updatetime?string("yyyy-MM-dd HH:mm:ss")}</time>
				   				</h3>
				   			</a>
				   		</div>
				   		<div  class="inline-show-more-text feed-s-main-content ember-view">       
				   			${oneNews.news}
				   		</div>
				   		<div class='flash-img'>
				   			<#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
			                      <img src='${oneNews.imgurl}' alt=''>
			                  <#else>
			                     <img src='http://m.c.lnkd.licdn.com/media-proxy/ext?w=800&h=800&f=none&hash=2aZPixKVB6zgDUANI8y04HW%2BwPM%3D&ora=1%2CaFBCTXdkRmpGL2lvQUFBPQ%2CxAVta9Er0Vinkhwfjw8177yE41y87UNCVordEGXyD3u0qYrdf3K7eceMKrCjuVoSfysclA1hdvL6EzS3D5G7LYrsdNsijpfiIo24ZxUBbFI8lWxI' alt=''>
			                  </#if>
				   		</div>
				   		<div class='operate-container'>
				   		<#if oneNews.likeIds>
				   			<a class="numLikes" href='javascript:void(0)'>赞 ${oneNews.likesum}</a>
				   		<#else>
				   			<a class="numLikes" href='javascript:void(0)'>取消赞 ${oneNews.likesum}</a>
				   		</#if>
				   			<a class="numComments" href='javascript:void(0)'>评论 ${oneNews.commentsum}</a>
				   			<a href='javascript:void(0)'   class='update-btn'>
				   				<span class='delete-falsh'><b class="iconfont pr2"></b>删除</span>
				   				<span class='stick-botton'><i class='com-stick com-icon'></i>置顶</span>
				   			</a>
				   		</div>
				   	</li>

				   	</#list>
				   </#if>
				   </ul>
				</div>
			 </div>
			 <div class='middle-card-left tab-profile' style='display:none'>
			    <div class='public-profile'>
				   <h4 class="com-heading">
					  图片
				   </h4>
				   <div class='profile-logo'>
				    <img src="/image/bg-computer.jpg" class='lazy' alt="公司图片" width="834" height='220' id="imghead">
                    <span class="update-comimg-tip">更换图片</span>
                    <form method="POST"  id="profileImage" enctype="multipart/form-data">
                      <input name='headIconFile' class="file-3" type="file" accept="image/*" size="30" onchange="previewImage(this,2)" />
                   </form>
				   </div>
				   <h4 class="com-heading">
					  公司介绍
				   </h4>
				   <div class='profile-brief clearfix'>
				     <span>微软，是一家总部位于美国的跨国科技公司，也是世界PC（Personal Computer，个人计算机）机软件开发的先导，由比尔·盖茨与保罗·艾伦创办于1975年，公司总部设立在华盛顿州的雷德蒙德（Redmond，邻近西雅图）。以研发、制造、授权和提供广泛的电脑软件服务业务为主。</span>
				     <a href="javascript:;" class="zu-edit-button" name="editscombrief"><i class="zu-edit-button-icon"></i>修改</a>
				   </div>
				    <h4 class="com-heading">
					 业务领域
				    </h4>
				    <div class='business-area clearfix'>
				      <span class="areazone" data-skillid="8">软件服务</span>
				      <a href="javascript:;" class="zu-edit-button" name="editsarea"><i class="zu-edit-button-icon"></i>修改</a>
				    </div>
				    <div class='com-address clearfix'>
				         详情地址: 中国江苏省苏州市工业园区星湖街创意产业园18号    221700<a href="javascript:;" class="zu-edit-button" name="editsaddress"><i class="zu-edit-button-icon"></i>修改</a>
				    </div>
				    <div class='com-websit clearfix'>
				       <ul>
				         <li>
				           <span>公司网站</span>
				           <p>http://www.jobplus.com.cn <a href="javascript:;" class="zu-edit-button" name="editsurl"><i class="zu-edit-button-icon"></i>修改</a></p>
				         </li>
				         <li>
				           <span>公司规模</span>
				           <p>10000+ <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				         </li>
				         <li>
				           <span>所属行业</span>
				           <p>互联网 <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				         </li>
				         <li>
				           <span>创立年份</span>
				           <p>1985年 <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				         </li>
				         <li>
				            <span>公司类型</span>
				            <p>外商独资 <a href="javascript:;" class="zu-edit-button" name="editsmodule"><i class="zu-edit-button-icon"></i>修改</a></p>
				         </li>
				       </ul>
				    </div>
				</div>
			 </div>
			 <div class='middle-card-right'>
			   <div class="strength com-strength" id="com-strength">
                      <span>企业资料完善度</span>
                      <div class="strength-meter v2">
                         <div class="strength-data">
                            <div class="level-indicator"></div>
                            <em class="fill-marker" style="visibility:visible; width:180px;bottom:84px;left:68px"></em>
                            <p class="current-level-description" style="visibility: visible; bottom: 86px;; opacity: 1;">高级</p>
                          </div>
                          <canvas id="waterbubble" class="mask" width="82" height="82"></canvas>
                      </div>
                </div>
                <div class="seeme">
					  <div class="myattention">
					   <div class="top-border">
					    <p>
						    <a href="/myHome/getHomePage/2?requesType=0" target="_self" data-userid="29">
                               <span class="count">
										9
							   </span>
                               <span class="count-concern">我的关注</span>
                             </a>
                          </p>
						  <p>
							  <a href="/myHome/getHomePage/2?requesType=1" target="_self" data-userid="29">
                               <span class="count">
											13
							   </span>
                               <span class="count-concern">我的粉丝</span>
                              </a> 
                          </p>
						</div>
					  </div>
					  
                      <div class="recent-seeme" style="border-top:1px solid #e5e5e5; padding-top:10px;">
                            <span>最近访问</span>
                            <span class="more"><a href="/myHome/getHomePage/2?requesType=3" target="_self">更多&gt;&gt;</a></span>
                       </div>
                       <div class="detail-list zg-clear">
								 <a title="Amy" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/479" data-userid="479" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/29/479_38b3a5f56e42417ebb21337a70e011e4.jpg" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								 <a title="admin" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/1" data-userid="1" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/16/1_65e135354a1d48e8bc00f4280f9f49d3.jpg" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								 <a title="LEO .Messi" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/30" data-userid="30" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/22/30_01894ca8585b4ad380a241bd9c66a2b1.png" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
					  </div>
					  
                    </div>
			 </div>
		  </div>
        </div>
      </div>
      <div class='homepageTemplate'></div>
      <a id="backcomtop" title="回到顶部"  class='back-to-top' href="#comtop" style="display:none; bottom:300px;"></a>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
      <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script> 
      <script type="text/javascript" src="/scripts/pj_changeHeadIcon.js" charset="UTF-8"></script>
      <script type="text/javascript" src="/scripts/waterbubble.js"></script>
      <script type="text/javascript" src="/scripts/pj_mycompage.js"></script>
      <script type="text/javascript" src="/scripts/pj_constant.js"></script>
      <#--<script type="text/javascript" src="/scripts/pj_country.js"></script>-->
      <script type="text/javascript">
        $('#waterbubble').waterbubble({
			    radius:40,
			    lineWidth:2,
			    data:1,
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