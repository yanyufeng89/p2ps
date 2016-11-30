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
                    <#--<#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
                     <img src="${userInfo.headicon}"  class='lazy' alt="个人头像" width="180"  id="imghead">
                    <#else>
                     <img src="/image/cm-defaultIcon.jpg" class='lazy' alt="个人头像" width="180"  id="imghead">
                    </#if>-->
                    <img src="/image/cm-defaultIcon180.jpg" class='lazy' alt="个人头像" width="180"  id="imghead">
              </div>
              <div class='top-card-data'>
                <div class='profile-overview-content'>
                    <div id="name-container">
                        <div id="titlename" class="editable-item">
                            <span class="full-name">微软亚太研发集团</span>
                        </div>
                    </div>
                    <div id="slogan-container" class='slogan-container'>
                       <span class="slogan">微软一直坚持技术导向的宗旨,让员工可以接触到世界最先进的产品和开发交流,通过大家的技术探索和技术实现</span>
                    </div>
                    <div id='enterprise-container' class='enterprise-container'>
                       <span class="enterprise">企业级应用产品</span>
                    </div>
                 </div>
              </div>
			  <div class='profile-func clearfix' id="aboutCompany-tab">
			    <a href="/comp/getHomePage/${Session.user.userid}?isReview=0" class="preview-profile button-primary">返回编辑界面</a>
			    <span class="personal-data current" id="latest-update">最新快讯</span>
				<label class="split">|</label>
			    <span class="personal-data" id="company-profile">公司简介</span>
			    <label class="split">|</label>
			    <span class="personal-data" id="company-share">公司分享</span>
			    <label class="split">|</label>
			    <span class="personal-data" id="company-attention">公司关注</span>
			  </div>
          </div>
		  <div class='middle-card container clearfix'>
		     <div class='middle-card-left tab-flash'>
				<div class='entity-all separate-entities ember-view' style='margin-top:0'>
				   <h4 class="recent-updates-heading com-heading">
					      最新快讯
				   </h4>
				   <ul class='entity-list row'>
				     <li class='entity-list-item'>
				        <div class='post-meta ember-view'>
				            <a href='javascript:void(0)'>
				              <img class="lazy-image avatar  loaded" width='50' height='50' alt="JobPlus China" src="http://m.c.lnkd.licdn.com/mpr/mpr/shrinknp_100_100/AAEAAQAAAAAAAAg9AAAAJGRiNDAyM2E2LWYwMjUtNGVkOS1iMzU2LWEzMjIyOGRmNmQzYg.png">
				            </a>
				            <a href='javascript:void(0)'>
				              <h3 class="actor">
							    <span class="name semibold">JobPlus China</span>
							    <time class="timestamp">2016-09-26 10:20:13</time>
							  </h3>
				            </a>
				        </div>
				        <div  class="inline-show-more-text feed-s-main-content ember-view">       
				            2015年1月，在第66届艾美奖[2]  上，国家电视艺术和科学学院为微软颁发了“电视功能强化设备奖”。2015年7月9日，微软宣布对手机业务进行根本性重组，同时表示将削减至7800多个职位。[3]  2015年10月据美国CNBC报道，微软将在新一轮裁员仲裁撤1000个岗位。新裁员是在之前宣布的裁员基础上额外追加的。
                        </div>
                        <div class='flash-img'>
                            <img src='http://m.c.lnkd.licdn.com/media-proxy/ext?w=800&h=800&f=none&hash=2aZPixKVB6zgDUANI8y04HW%2BwPM%3D&ora=1%2CaFBCTXdkRmpGL2lvQUFBPQ%2CxAVta9Er0Vinkhwfjw8177yE41y87UNCVordEGXyD3u0qYrdf3K7eceMKrCjuVoSfysclA1hdvL6EzS3D5G7LYrsdNsijpfiIo24ZxUBbFI8lWxI' alt=''>
                        </div>
                        <div class='operate-container'>
                           <a class="numLikes" href='javascript:void(0)'>赞 2</a>
                           <a class="numComments" href='javascript:void(0)'>评论 1</a>
                         <a href='javascript:void(0)'   class='update-btn'>
                            <span class='delete-falsh'><b class="iconfont pr2"></b>删除</span>
                            <span class='stick-botton'><i class='com-stick com-icon'></i>置顶</span>
                         </a>
                        </div>
				     </li>
				     <li class='entity-list-item'>
				        <div class='post-meta ember-view'>
				            <a href='javascript:void(0)'>
				              <img class="lazy-image avatar  loaded" width='50' height='50' alt="JobPlus China" src="http://m.c.lnkd.licdn.com/mpr/mpr/shrinknp_100_100/AAEAAQAAAAAAAAg9AAAAJGRiNDAyM2E2LWYwMjUtNGVkOS1iMzU2LWEzMjIyOGRmNmQzYg.png">
				            </a>
				            <a href='javascript:void(0)'>
				              <h3 class="actor">
							    <span class="name semibold">JobPlus China</span>
							    <time class="timestamp">2016-09-26 10:20:13</time>
							  </h3>
				            </a>
				        </div>
				        <div  class="inline-show-more-text feed-s-main-content ember-view">       
				            2015年1月，在第66届艾美奖[2]  上，国家电视艺术和科学学院为微软颁发了“电视功能强化设备奖”。2015年7月9日，微软宣布对手机业务进行根本性重组，同时表示将削减至7800多个职位。[3]  2015年10月据美国CNBC报道，微软将在新一轮裁员仲裁撤1000个岗位。新裁员是在之前宣布的裁员基础上额外追加的。
                        </div>
                        <div class='flash-img'>
                            <img src='http://m.c.lnkd.licdn.com/media-proxy/ext?w=800&h=800&f=none&hash=2aZPixKVB6zgDUANI8y04HW%2BwPM%3D&ora=1%2CaFBCTXdkRmpGL2lvQUFBPQ%2CxAVta9Er0Vinkhwfjw8177yE41y87UNCVordEGXyD3u0qYrdf3K7eceMKrCjuVoSfysclA1hdvL6EzS3D5G7LYrsdNsijpfiIo24ZxUBbFI8lWxI' alt=''>
                        </div>
                        <div class='operate-container'>
                           <a class="numLikes" href='javascript:void(0)'>赞 2</a>
                           <a class="numComments" href='javascript:void(0)'>评论 1</a>
                         <a href='javascript:void(0)'   class='update-btn'>
                            <span class='delete-falsh'><b class="iconfont pr2"></b>删除</span>
                            <span class='stick-botton'><i class='com-stick com-icon'></i>置顶</span>
                         </a>
                        </div>
				     </li>
				   </ul>
				</div>
			 </div>
			 <div class='middle-card-left tab-profile' style='display:none'>
			    <div class='public-profile'>
				   <h4 class="com-heading">
					  图片
				   </h4>
				   <div class='profile-logo'>
				    <img src="http://m.c.lnkd.licdn.com/media-proxy/ext?w=800&h=800&f=none&hash=2aZPixKVB6zgDUANI8y04HW%2BwPM%3D&ora=1%2CaFBCTXdkRmpGL2lvQUFBPQ%2CxAVta9Er0Vinkhwfjw8177yE41y87UNCVordEGXyD3u0qYrdf3K7eceMKrCjuVoSfysclA1hdvL6EzS3D5G7LYrsdNsijpfiIo24ZxUBbFI8lWxI" class='lazy' alt="公司图片" width="834" height='220' id="imghead">
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
				   </div>
				    <h4 class="com-heading">
					 业务领域
				    </h4>
				    <div class='business-area clearfix'>
				      <span class="areazone" data-skillid="8">软件服务</span>
				    </div>
				    <div class='com-address clearfix'>
				         详情地址: 中国江苏省苏州市工业园区星湖街创意产业园18号    221700
				    </div>
				    <div class='com-websit clearfix'>
				       <ul>
				         <li>
				           <span>公司网站</span>
				           <p>http://www.jobplus.com.cn</p>
				         </li>
				         <li>
				           <span>公司规模</span>
				           <p>10000+ </p>
				         </li>
				         <li>
				           <span>所属行业</span>
				           <p>互联网</p>
				         </li>
				         <li>
				           <span>创立年份</span>
				           <p>1985年 </p>
				         </li>
				         <li>
				            <span>公司类型</span>
				            <p>外商独资 </p>
				         </li>
				       </ul>
				    </div>
				</div>
			 </div>
			 <div class='middle-card-right'>
                <div class="news" style="border-color:#cce1ef">
                       <div class="top-border">
                          <p>
                            <a href="javascript:void(0)" self="_self" id="otheratten" data-userid="2">
                               <span class="count">
										9
							   </span>
                               <span class="count-concern">我的关注</span>
                            </a>
                          </p>
                          <p>
								<a href="javascript:void(0)" target="_self" id="otherfans" data-userid="2">
								   <span class="count">
											13
								   </span>
								   <span class="count-concern">我的粉丝</span>
								</a> 
                          </p>
                       </div>
                  </div>
				  <div class="seeme">
                       <div class="recent-seeme">
                            <span>最近访问</span>
                               <span class="more" id="moreVisitor">更多&gt;&gt;</span>
                       </div>
                       <div class="detail-list zg-clear">
								<a title="小丑" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/31" data-userid="31" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/10/31/31_4d3a0cc861ef406e9c2ce1e2d53e86a1.png" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="Amy" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/479" data-userid="479" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/29/479_38b3a5f56e42417ebb21337a70e011e4.jpg" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="admin" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/1" data-userid="1" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/16/1_65e135354a1d48e8bc00f4280f9f49d3.jpg" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="LEO .Messi" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/30" data-userid="30" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/22/30_01894ca8585b4ad380a241bd9c66a2b1.png" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="LEO .Messi" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/30" data-userid="30" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/22/30_01894ca8585b4ad380a241bd9c66a2b1.png" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="陌上花开" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/471" data-userid="471" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/16/471_24723608295a400994098a73de7bffd2.jpg" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="LEO .Messi" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/30" data-userid="30" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/22/30_01894ca8585b4ad380a241bd9c66a2b1.png" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="Amy" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/479" data-userid="479" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/29/479_38b3a5f56e42417ebb21337a70e011e4.jpg" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="LEO .Messi" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/30" data-userid="30" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/22/30_01894ca8585b4ad380a241bd9c66a2b1.png" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
								<a title="LEO .Messi" class="zm-item-link-avatar uname" target="_blank" href="/myHome/getHomePage/30" data-userid="30" data-moduletype="1">
										 <img src="http://192.168.0.39:8199/headIcon/2016/11/22/30_01894ca8585b4ad380a241bd9c66a2b1.png" alt="个人头像" class="zm-item-img-avatar lazy">
							    </a>
					  </div>
						
                    </div>
					<div class="sharelist">
					  <div class="recent-share">
                            <span>最新分享</span>
                               <span class="more" id="moreShare">更多&gt;&gt;</span>
                       </div>
					   <div class="detail-list zg-clear">
						   <ul>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113969" target="_blank" title="20161114会议纪要"><span>20161114会议纪要</span></a>
									  <span class="smsdate zg-right">2016-11-28</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113905" target="_blank" title="30段超实用CSS代码"><span>30段超实用CSS代码</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113906" target="_blank" title="Css垂直居中"><span>Css垂直居中</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113907" target="_blank" title="html5实现本地存储登陆用户信息保存"><span>html5实现本地存储登陆用户信息保存</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113908" target="_blank" title="html-javascript前端页面刷新重载的方法汇总"><span>html-javascript前端页面刷新重载的方法汇总</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113909" target="_blank" title="Jquery实例库"><span>Jquery实例库</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113911" target="_blank" title="高性能滚动 scroll 及页面渲染优化"><span>高性能滚动 scroll 及页面渲染优化</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113903" target="_blank" title="java ajax"><span>java ajax</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113899" target="_blank" title="备份"><span>备份</span></a>
									  <span class="smsdate zg-right">2016-11-24</span>
									</li>
									<li>
									  [文档]
									  <a href="/docs/getDocsDetail/113873" target="_blank" title="Jquery实例库"><span>Jquery实例库</span></a>
									  <span class="smsdate zg-right">2016-11-23</span>
									</li>
							</ul>
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
    </body>
</html>