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
              <input type='hidden' name='userid' value='${userInfo.userid}'>
              <div class='top-card-company-logo logo' id='comlogo'>
                    <#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
                     <img src="${userInfo.headicon}"  class='lazy' alt="个人头像" width="180" height='180' id="imghead">
                    <#else>
                     <img src="/image/cm-defaultIcon180.jpg" class='lazy' alt="个人头像" width="180" height='180' id="imghead">
                    </#if>
              </div>
              <div class='top-card-data'>
                <div class='profile-overview-content'>
                    <div id="name-container">
                        <div id="titlename" class="editable-item">
                            <span class="full-name">${userInfo.username?html}</span>
                        </div>
                    </div>
                    <div id="slogan-container" class='slogan-container'>
                       <span class="slogan">
                       <#if (userInfo.description)?? && userInfo.description?length gt 0>
                         ${userInfo.description?html}
                       <#else>
                                                           企业标语
                       </#if>
                       </span>
                    </div>
                    <div id='enterprise-container' class='enterprise-container'>
                       <span class="enterprise">
                       <#if (userInfo.specialty)?? && userInfo.specialty?length gt 0>
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
                    </div>
                 </div>
              </div>
			  <div class='profile-func clearfix' id="aboutCompany-tab">
			    
			    <#if (userInfo.userid?string==(Session.user.userid?string)!)>
                                    <a href="/comp/getHomePage/${Session.user.userid}?isReview=0" class="preview-profile button-primary">返回编辑界面</a>
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
								
			    <span class="personal-data current" id="latest-update">最新快讯</span>
				<label class="split">|</label>
			    <span class="personal-data" id="company-profile">公司简介</span>
			    <label class="split">|</label>
			    <span class="personal-data" id="company-share">公司分享</span>
			    <label class="split">|</label>
			    <span class="personal-data" id="company-attention" data-userid="${userInfo.userid}">公司关注</span>
			    <div class='shareThe'>
			             分享到
			     <a href="javascript:void(0);" class="log_sina png" title="分享到新浪微博"></a>
			     <a href="javascript:void(0);" class="log_qq png" title="分享到QQ空间"></a>
			     <a href="javascript:void(0);" class="log_wx png" title="分享到微信"></a>
			    </div>
			    <#if (userInfo.userid?string!=(Session.user.userid?string))>
					<div class="site-mail pm-img img" id='site-mail' data-issitemail='1' data-receivedid='${userInfo.userid}' data-name='${userInfo.username}'></div>	
				 </#if>
			  </div>
          </div>
		  <div class='middle-card container clearfix'>
		     <div class='middle-card-left tab-flash'>
				<div class='entity-all separate-entities ember-view' style='margin-top:0'>
				   <h4 class="recent-updates-heading com-heading">最新快讯</h4>
				   <input type='hidden' name='companyId' value='${userInfo.userid}'>
				   <input type='hidden' name='companyName' value='${userInfo.username}'>
				   <#if (cpNewes.list)?? && cpNewes.list?size gt 0>
				   <ul class='entity-list row' id='entity-list'>
				    <#list cpNewes.list as news>
				     <li class='entity-list-item'>
				        <div class='post-meta ember-view'>
	
							 <#if (userInfo.headicon)?? && userInfo.headicon?length gt 0>
				               <img class="lazy-image avatar loaded" width='50' height='50' alt="${userInfo.username}" src="${userInfo.headicon}">
				             <#else>
							  <img class="lazy-image avatar loaded" width='50' height='50' alt="${userInfo.username}" src="/image/cm-defaultIcon180.jpg">
							 </#if>	
							
				              <h3 class="actor">
							    <span class="name semibold">${userInfo.username?html}</span>
							    <time class="timestamp">${news.createtime?string("yyyy-MM-dd HH:mm:ss")}</time>
							  </h3>
				            
				        </div>
				       <#if (news.news)?? && news.news?length gt 0>
				        <div  class="inline-show-more-text feed-s-main-content ember-view" data-flash='${news.id}'>  
				          <#if news_index==0 && (news.istop==1)>     
				            <i class="com-top com-icon"></i>
				          </#if>
				          ${news.news}
                        </div>
                        </#if>
                        <#if (oneNews.siteurl)?? && oneNews.siteurl?length gt 0>
				   		<div class='flash-url'>
			               <a href='${oneNews.siteurl}' target='_blank'>${oneNews.sitetitle}<span class='siteurl'>${oneNews.siteurl}</span></a>
				   		</div>
				   		</#if>
                        <#if (news.imgurl)?? && news.imgurl?length gt 0>
                        <div class='flash-img'>
                            <img src='${news.imgurl}' alt=''>
                        </div>
                        </#if>
                        <div class='operate-container'>
	                         <#if (news.likedIds)??&&news.likedIds?length gt 0>
	                           <#if news.likedIds?index_of(",")==-1>
	                            <#if (news.likedIds?string==(Session.user.userid?string)!)>
	                              <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='1' data-flashid='${news.id}' data-likenum='${news.likesum}'>取消赞  (${news.likesum})</a>
	                            <#else> 
	                              <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='0' data-flashid='${news.id}' data-likenum='${news.likesum}'>赞  ${news.likesum}</a>                                   
	                            </#if>                                             
	                           <#else>
	                              <#if news.likedIds?split(",")?seq_contains((Session.user.userid?string)!)>
	                                 <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='1' data-flashid='${news.id}' data-likenum='${news.likesum}'>取消赞  (${news.likesum})</a>
	                              <#else>
	                                 <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='0' data-flashid='${news.id}' data-likenum='${news.likesum}'>赞  ${news.likesum}</a> 
	                              </#if>
	                           </#if>    
	                          <#else> 
	                                 <a class="numLikes operate" href='javascript:void(0)'  data-likeOperate='0' data-flashid='${news.id}' data-likenum='${news.likesum}'>赞  ${news.likesum}</a>                                
	                          </#if>
                           
                           <a class="numComments operate" href='javascript:void(0)' data-flashid='${news.id}' data-commsum='${news.commentsum}'>评论  ${news.commentsum}</a>
                        </div>
				     </li>
					 </#list>
					 <#if (cpNewes.last gt 1)>
					 <a href="javascript:;" data-pageno="1"  data-sumpage="${cpNewes.last}" data-comtype='0' class="zg-btn-white  zu-button-more flash-load-more">更多</a>
					 </#if>
					 </ul>
				   <#else>
		
					  <li class='no-flashlist'>暂无最新快讯</li>
				
				   </#if>
				</div>
			 </div>
			 <div class='middle-card-left tab-profile' style='display:none'>
			    <div class='public-profile'>

				   <div class='profile-logo'>
				   <#if (cpnInfo.imgurl)??>
				    <img src="${cpnInfo.imgurl}" class='lazy' alt="公司图片" width="834" height='220' style='margin-top:20px'>
				   <#else>
				   <img src="/image/bg-computer.jpg" class='lazy' alt="公司图片" width="834" height='220' style='margin-top:20px'>
				   </#if>
				   </div>
				   <h4 class="com-heading">公司介绍</h4>
				   <div class='profile-brief clearfix'>
				    <#if (cpnInfo.intro)?? && cpnInfo.intro?length gt 0>
				     <span class='intro'>${cpnInfo.intro}</span>
				    <#else>
				     <span>暂无公司介绍</span>
				    </#if>
				   </div>
				  
				    <h4 class="com-heading">业务领域</h4>
				    <div class='business-area clearfix' style='border-bottom:none;padding-bottom:0'>
				      <#if (cpnInfo.busiarea)?? && cpnInfo.busiarea?length gt 0>
				      <#list cpnInfo.busiarea?split(',') as items>
						 <#assign item=items?split(':')/>
							<span class="areazone" data-skillid="${item[0]}">${item[1]}</span>
					  </#list>
					  <#else>
					   <span class="no-areazone">暂无业务领域</span>
					 </#if>
				    </div>
				    <h4 class="com-heading">公司地址</h4>
				    <div class='com-address clearfix' style='border-bottom:none;padding:0 20px 0 20px'>
				         <#if (cpnInfo.address)?? && (cpnInfo.nation)?? && (cpnInfo.province)?? && (cpnInfo.city)??>
				         ${cpnInfo.nation}${cpnInfo.province}${cpnInfo.city} ${cpnInfo.address} ${cpnInfo.postcode}
				         <#else>
				                           暂无公司地址
				         </#if>    
				    </div>
				    <div class='com-websit clearfix'>
				       <ul>
				         <li>
				           <span>公司网站</span>
				           <p><#if (cpnInfo.url)??><a href='${cpnInfo.url}' target='_blank' class='websiturl' title='${cpnInfo.url}'>${cpnInfo.url}</a><#else>暂无公司网站</#if></p>
				         </li>
				         <li>
				           <span>公司规模</span>
				           <p><#if (cpnInfo.scale)??>${cpnInfo.scale}<#else>暂无数据</#if></p>
				         </li>
				         <li>
				           <span>所属行业</span>
				           <p><#if (cpnInfo.industry)??>${cpnInfo.industry}<#else>暂无数据</#if></p>
				         </li>
				         <li>
				           <span>创立年份</span>
				           <p><#if (cpnInfo.establishtime)??>${cpnInfo.establishtime?string("yyyy")}<#else>暂无数据</#if></p>
				         </li>
				         <li>
				            <span>公司类型</span>
				            <p><#if (cpnInfo.type)??>${cpnInfo.type}<#else>暂无数据</#if> </p>
				         </li>
				       </ul>
				    </div>
				</div>
			 </div>
			 <div class="middle-card-left tab-profile profile-navbar" id='cmprofile-navbar' style='display:none'>
			 
			  <div>
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
			   <div class='cm-profile-section-wrap'>					   
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
							 <#if (userInfo.userid==Session.user.userid)>
							    <span class='nosharelist'>这没有分享，开始<a href="/sharein/searchuploadFile?type=0" target="_blank">分享</a></span>
							 <#else>
							    <span class='nosharelist'>这家伙很懒，还没有分享</span>
							 </#if>
							</#if>
						   </ul>
						</div>
						 <#if (recentShare.last gt 1)>
					         <a href="javascript:;"  data-pageno="1" data-tablename=''  data-tablecolumn2='' data-tablecolumn='' data-userid='${userInfo.userid}' data-sumpage='${recentShare.last}' class="zg-btn-white zg-r3px zu-button-more pj-load-more">更多</a>
					     </#if>
				</div>
             </div>
             <div class='middle-card-left profile-navbar clearfix' id='cmfans-navbar' style='display:none'>
				 <div>
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
				 <div id='cm-profile-follows-list' class='cm-profile-follows-list'>
				      <div class="zh-general-list clearfix">
					  </div>
				 </div>	
			  </div>
			 <div class='middle-card-right'>
                <div class="news" style="border-color:#cce1ef">
                       <div class="top-border">
                          <p>
                            <#if userInfo.userid==(Session.user.userid)!>
                            <a href="javascript:void(0)" target="_self" id='cmotheratten' data-userid='${userInfo.userid}'>
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
						    <a href="javascript:void(0)" target="_self" id='cmotheratten' data-userid='${userInfo.userid}'>
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
								<a href="javascript:void(0)"  target="_self" id='cmotherfans' data-userid='${userInfo.userid}'>
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
							  <a href="javascript:void(0)"  target="_self" id='cmotherfans' data-userid='${userInfo.userid}'>
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
                               <span class='more' id='cmmoreVisitor'>更多>></span>
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
					<div class="sharelist">
					  <div class="recent-share">
                            <span>最新分享</span>
                            <#if visitors.last gt 1>
                               <span class='more' id='cmmoreShare'>更多>></span>
							</#if>
                       </div>
					  <div class='detail-list zg-clear'>
						  <#if (recentShare.list)?? && recentShare.list?size gt 0>
						   <ul>
							  <#list recentShare.list as list>
									<li>
									  <a href='${list.objurl}' target='_blank' title='${list.title}'>
										  <#if list.type='tbl_docs'>
										  <span>[文档]
										  <#elseif list.type='tbl_topics'>
										  <span>[话题]
										  <#elseif list.type='tbl_books'>
										  <span>[书籍]
										  <#elseif list.type='tbl_article'>
										  <span>[文章]
										  <#elseif list.type='tbl_courses'>
										  <span>[课程]
										  <#else>
										  <span>[站点]
										  </#if>
									     ${list.title}</span>
									     <span class="smsdate zg-right">${list.createtime?string("yyyy-MM-dd")}</span>
									  </a>
									</li>
								</#list>
							</ul>
						  <#else>
							<span class="no-flashlist">暂无最新分享</span>
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
      <script type="text/javascript" src="/scripts/pj_publicMethod.js"></script>
      <script type="text/javascript" src="/scripts/pj_loadPmAndsmg.js"></script>
      <script type="text/javascript" src='/scripts/pj_centerCommon.js'></script>
      <script type="text/javascript" src="/scripts/share.js"></script>
      <script type="text/javascript" src="/scripts/jquery.simplePagination.js"></script>
      <script type="text/javascript" src="/scripts/pj_constant.js"></script>
    </body>
</html>