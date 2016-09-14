<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
           首页
    </title>
    <meta name="viewport" content="width=1230,initial-scale=0.9"/>
    <meta name="apple-mobile-web-app-capable" content="yes" /> 
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_framework.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_index.css" charset="UTF-8">
  </head>

  <body id="top">
    <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
    <div id="bd">
     <div class="nav-wrap mb10">
        <div class="ui-nav">
          <div class="inner clearfix">
            <div class="cate hide wk-all-cate" id="wk-all-cate">
            
            <#if (homeTypeList)?? &&homeTypeList?size gt 0>
               <#list homeTypeList as cMap>
            
              <dl>
                <dt>
                <#-- 奇偶  样式--> 
                 <#if cMap_index % 2 gt 0>
                	<b class="t-tag dd"> </b>
                <#else>
                	<b class="t-tag cc"> </b>
                </#if>
                 <#--
                  <a href="${request.getContextPath()}/search/${cMap['rootID']}" target="_blank">
                   -->
                   <span>
                    ${cMap['rootName']}
                    <b class="li-aw n-ic">
                    </b>
                    <b class="li-aw n-ic">
                    </b>
                  </span>
                </dt>
                <dd>
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID1']}" target="_blank" class="log-xsend">
                    ${cMap['firstViewName1']}
                  </a>
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID2']}" target="_blank" class="log-xsend">
                   ${cMap['firstViewName2']}
                  </a>
                </dd>
                <dd>
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID3']}" target="_blank" class="log-xsend">
                    ${cMap['firstViewName3']}
                  </a>
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID4']}" target="_blank" class="log-xsend">
                    ${cMap['firstViewName4']}
                  </a>
                </dd>
              </dl>
			  <div class='menu_sub dn'> 
			    <dl>
				  <dt><a href="${request.getContextPath()}/search/${cMap['childID1']}" target="_blank">${cMap['childName1']}</a></dt>
				  <dd>
				  	<#list cMap['childListData1']?split(',') as ctName>
				  			<a href="${request.getContextPath()}/search/${ctName?split(':')[0]}"  target='_blank'>${ctName?split(':')[1]}</a>
				  	</#list>
				  </dd>
				   <dt><a href="${request.getContextPath()}/search/${cMap['childID2']}" target="_blank">${cMap['childName2']}</a></dt>
				  <dd>
				    <#list cMap['childListData2']?split(',') as ctName>
				  			<a href="${request.getContextPath()}/search/${ctName?split(':')[0]}"  target='_blank'>${ctName?split(':')[1]}</a>
				  	</#list>
				  </dd>
				</dl>
			  </div>
  </#list>
  
  
</#if>
              
            </div>
            <div id="search_box" class="search_box">
              <form id="searchForm" class="searchForm" name="searchForm" action="/51jobplusCore/search/0"
              method="get">
                <span role="status" aria-live="polite" class="ui-helper-hidden-accessible">
                </span>
                
                <input type="submit" id="search_button" class="search_button" value="搜索知识">
                <input type="text" id="search_input" class="search_input ui-autocomplete-input" name="Condition"
                tabindex="1" maxlength="64" autocomplete="off" value="" placeholder="JobPlus海量知识库"  style="color: rgb(153, 153, 153);">
                <input type="hidden" name="labelWords" id="labelWords" value="">
                <input type="hidden" name="fromSearch" id="fromSearch" value="true">
                <input type="hidden" name="suginput" id="suginput">
                <input type="submit" name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier"
                id="search-form-submit" style='height:45px;' value="分类搜索">
              </form>
              
              <#--
              <form id="searchForm" class="searchForm" name="searchForm" action="${request.getContextPath()}/search/"
              method="get">
                <span role="status" aria-live="polite" class="ui-helper-hidden-accessible">
                </span>
                
                <input type="submit" id="search_button" class="search_button" value="搜索知识">
                <input type="text" id="search_input" class="search_input ui-autocomplete-input" name="Condition"
                tabindex="1" maxlength="64" autocomplete="off" value=""  style="color: rgb(153, 153, 153);">
                <input type="hidden" name="labelWords" id="labelWords" value="">
                <input type="hidden" name="fromSearch" id="fromSearch" value="true">
                <input type="hidden" name="suginput" id="suginput">
                <input type="botton" name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier"
                id="search-form-submit" value="分类搜索">
              </form>
               -->
              <input type="hidden" id="search_py" value="">
              <input type="hidden" id="isIndex" value="true">
            </div>
            <div class="search" id="wk-all-search">
                <#include "hotSearch.ftl"/>
            </div>
          </div>
        </div>
      </div>
     <div id="screen-hd" class="clearfix">
        <div class="screen-hd-wrap clearfix">
          <div class="banner">
            <div class="banner-con">
              <div id="slide-bner" class="slide-bner clearfix">
                <ul class="img-list">
                  <li class="bg-loading cur" style="opacity: 1; z-index: 9;">
                    <a href="#"
                    target="_blank" class="log-xsend" data-logxsend="[1,100004,{&#39;index&#39;:&#39;1&#39;}]">
                      <img alt="百度高考题库" title="百度高考题库" src="/51jobplusCore/image/1.jpg" />
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="#"
                    target="_blank" class="log-xsend" data-logxsend="[1,100004,{&#39;index&#39;:&#39;2&#39;}]">
                      <img alt="高考第一弹" title="高考第一弹" src="/51jobplusCore/image/2.jpg" />
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="#"
                    target="_blank" class="log-xsend" data-logxsend="[1,100004,{&#39;index&#39;:&#39;3&#39;}]">
                      <img alt="跨境电商税改" title="跨境电商税改" src="/51jobplusCore/image/3.jpg" />
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="#"
                    target="_blank" class="log-xsend" data-logxsend="[1,100004,{&#39;index&#39;:&#39;4&#39;}]">
                      <img alt="中瑞财富" title="中瑞财富" src="/51jobplusCore/image/4.jpg" />
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="#"
                    target="_blank" class="log-xsend" data-logxsend="[1,100004,{&#39;index&#39;:&#39;5&#39;}]">
                      <img alt="营改增" title="营改增" src="/51jobplusCore/image/5.jpg" />
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="#"
                    target="_blank" class="log-xsend" data-logxsend="[1,100004,{&#39;index&#39;:&#39;6&#39;}]">
                      <img alt="热播剧角色原型" title="热播剧角色原型" src="/51jobplusCore/image/6.jpg" />
                    </a>
                  </li>
                </ul>
                <div class="nav-btn">
                  <a href="javascript:void(0);" class="pre" style="display: none;">
                    <b class="bg-index">
                      上一张
                    </b>
                  </a>
                  <a href="javascript:void(0);" class="next" style="display: none;">
                    <b class="bg-index">
                      下一张
                    </b>
                  </a>
                </div>
                <ul class="play-nav">
                  <li>
                    <a href="javascript:void(0);">
                      1
                    </a>
                  </li>
                  <li>
                    <a href="javascript:void(0);">
                      2
                    </a>
                  </li>
                  <li>
                    <a href="javascript:void(0);">
                      3
                    </a>
                  </li>
                  <li>
                    <a href="javascript:void(0);">
                      4
                    </a>
                  </li>
                  <li>
                    <a href="javascript:void(0);">
                      5
                    </a>
                  </li>
                  <li>
                    <a href="javascript:void(0);">
                      6
                    </a>
                  </li>
                </ul>
              </div>
			  
               <a class="topic-division" href='/51jobplusCore/topics/fore/search/1' target='_blank'>
    
				
              </a>
			  
            </div>
          </div>
          <div class="user-info">
             <div class="logined-wrap" id="logininfo">
				<div class="logined-doc-num">
				<p class="num-tip">JobPlus海量知识体系</p>
				  <p class="num" id="total-docnum" data-docnum="153,377,154">
				     <span class="num1" data-num="1"></span>
				     <span class="num0" data-num="2"></span>
				     <span class="num3" data-num="3"></span>
				     <span class="spr"></span>
				   
				     <span class="num4" data-num="4"></span>
				     <span class="num5" data-num="5"></span>
				     <span class="num6" data-num="6"></span>
				     <span class="spr"></span>
				 
				     <span class="num7" data-num="7"></span>
				     <span class="num8" data-num="8"></span>
				     <span class="num9" data-num="9"></span>
				  </p>
				</div>
				<div class="logined-user-info">
				<div class="org-user-tip">
				<p>点击头像，访问我的机构主页</p>
				<span class="arrow-icon"></span><span class="close-icon close-pic"></span>
				</div>
				<div class="user-brief bg-index clearfix">
				<div class="avatar">
				   <div>
				      <a href="/51jobplusCore/myCenter/getMyHeadTop" target="_blank" class="bg-avatar bg-index">
				         <#if (Session.user.headicon)??>
		                   <img src="${Session.user.headicon}" width='60' height='60'>
		                 <#else>
		                 <img src="/51jobplusCore/image/myphoto.jpg" width='60' height='60'>
		              </#if>
		               </a>
				   </div>
				   
				</div>
				<div class="name-task">
				<p class="name">
			   <#if (Session.user)??>
				<a href='/51jobplusCore/myCenter/getMyHeadTop' class="name-link" title="<#if (Session.user)??> ${Session.user.username} </#if>" target="_blank">
	                   <#if (Session.user.username?length gt 7)>
	                     ${Session.user.username?substring(0,7)}...
	                   <#else>
	                     ${Session.user.username}
	                   </#if>
				</a>
				 </#if>
				<a href="#" target="_blank"><span class="iconfont ic-eduVip hide"></span></a>
				</p>
				<p class="task">
				    <a href="javascript:void(0)" target="_self" class="daily-task">分享知识，领财富值</a>
			    </p>
				</div>
				</div>
				<div class="doc-info-wrap">
				<div class="doc-num-info">
				<ul class="clearfix pj-doccount" id="doccount">
				<li>
					<div class="num">
					<#if (Session.operationSum.docsharesum)??>
					   ${Session.operationSum.docsharesum}
					  <#else>
					    0
					 </#if>
					</div>
                    <a href="/51jobplusCore/myCenter/getMyDocsUploaded" target="_blank" class="nav-item mr25 mingjia">
                      <div class="img"></div> 文档
                    </a>
				</li>
				<li>
					<div class="num">
					<#if (Session.operationSum.topicssharesum)??>
					   ${Session.operationSum.topicssharesum}
					  <#else>
					    0
					 </#if>
					</div>
                    <a href="/51jobplusCore/myCenter/getMyTopicsUploaded" target="_blank" class="nav-item mr25 economy">
                      <div class="img"></div> 话题
                    </a>
				</li>
				<li style="margin-right:0px">
					<div class="num">
					<#if (Session.operationSum.booksharesum)??>
					   ${Session.operationSum.booksharesum}
					  <#else>
					    0
					 </#if>
					</div>
                    <a href="/51jobplusCore/myCenter/getSharedBookList" target="_blank" class="nav-item mr25 internet">
                      <div class="img"></div> 书籍
                    </a>
				</li>
				<li>
					<div class="num">
					<#if (Session.operationSum.coursessharesum)??>
					   ${Session.operationSum.coursessharesum}
					  <#else>
					    0
					 </#if>
					</div>
                    <a href="/51jobplusCore/myCenter/getSharedCourseList" target="_blank" class="nav-item mr25 tech">
                      <div class="img"></div> 课程
                    </a>
				</li>
				<li>
					<div class="num">
					<#if (Session.operationSum.articlesharesum)??>
					   ${Session.operationSum.articlesharesum}
					  <#else>
					   0
					 </#if>
					</div>
                    <a href="/51jobplusCore/myCenter/getSharedArticleList" target="_blank" class="nav-item mr25 fashion">
                      <div class="img"></div> 文章
                    </a>
				</li>
				<li style="margin-right:0px">
					<div class="num">
					<#if (Session.operationSum.sitessharesum)??>
					   ${Session.operationSum.sitessharesum}
					  <#else>
					    0
					 </#if>
					</div>
                    <a href="/51jobplusCore/myCenter/getSharedSiteList" target="_blank" class="nav-item mr25 authors">
                      <div class="img"></div>  站点
                    </a>
				</li>
				</ul>
				</div>
				<div class="my-wk">
				
				<p class="upload-wrap">
				<a class="upload-btn bg-index" style='left:0;top:0' target='_blank' href="/51jobplusCore/sharein/searchuploadFile"></a>
				<span class="upload-btn-wealth-tip" style="display: inline;">
				<i class="ic icon-upload-wealth bg-index"></i>
				送下载券<i class="arrow">
				<i class="arrow-out"></i>
				<i class="arrow-inner"></i>
				</i>
				</span>
				</p>
				</div>
				</div>
				</div>
           </div>
          </div>
          <div class="news-shareinfo">
             <div class='newstitle'>
			  <span>最新分享</span>
			 </div>
			
			   <div class='newscontent-left'>
			     <ul>
                 <#list latestDatas as data>
                   <#if data_index<9>
                       <li>
                       ${data_index+1}.[${data.extendinfo5}]
                           <a href='javascript:void(0);' onclick="toHref('${data.datatype}',${data.dataid})" title="${data.title}">
                           ${data.title}
                           </a>
                       </li>
                   </#if>
                 </#list>
			     </ul>
			   </div>
			   <div class='newscontent-right'>
			     <ul>
                 <#list latestDatas as data>
                   <#if data_index gt 8 && data_index<18>
                       <li>
                       ${data_index+1}.[${data.extendinfo5}]
                           <a href='javascript:void(0);' onclick="toHref('${data.datatype}',${data.dataid})" title="${data.title}">
                           ${data.title}
                           </a>
                       </li>
                   </#if>
                 </#list>
			     </ul>
               </div>
		
          </div>
        </div>
      </div>
    </div>
    <div id="index-main-body" class='index-main-body'>

    <div id="wonderful-share" class="clearfix wonderful-share">
      <span>
                 精彩分享
      </span>
    </div>
    <div  class="row-2 zone clearfix wonderful-sharecontent">
      <div class="row-main">
        <div class="main-con clearfix">
          <dl class="clearfix" title="创新/创业">
            <dt>
              <span>创新/创业</span>
            </dt>
              <dd>
                <#list hotShareDataMap?keys as mKey>
                 <#if mKey=='创新/创业'>
                   <#assign items = hotShareDataMap[mKey]>
                     <div class="zone-info clearfix">
                         <div class="zone-img">
                             <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                               <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                   <img src="${items[0].imgurl}"
                                        alt="${items[0].title}" title="${items[0].title}">
                               <#else>
                                   <img src="/51jobplusCore/image/index_default.jpg"
                                        alt="${items[0].title}" title="${items[0].title}">
                               </#if>
                             </a>
                         </div>
                         <div class="zone-recom">
                             <p>
                                 <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                    title="${items[0].title}">
                                     ${items[0].title}
                                 </a>
                             </p>
                         </div>
                     </div>
                 <div class="related-doc">
                 <ul>
                   <#list items as item>
                      <#if item_index gt 0 && item_index lt 9>
                          <li>
                              [${item.extendinfo5}]
                              <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                              ${item.title}
                              </a>
                          </li>
                      </#if>
                   </#list>
                 </ul>
                 </div>
                 </#if>
                </#list>
              </dd>
          </dl>
          <dl class="clearfix" title="咨询/财务">
            <dt>
              <span>咨询/财务</span>
            </dt>
              <dd>
              <#list hotShareDataMap?keys as mKey>
                <#if mKey=='咨询/财务'>
                  <#assign items = hotShareDataMap[mKey]>
                    <div class="zone-info clearfix">
                        <div class="zone-img">
                            <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                              <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                  <img src="${items[0].imgurl}"
                                       alt="${items[0].title}" title="${items[0].title}">
                              <#else>
                                  <img src="/51jobplusCore/image/index_default.jpg"
                                       alt="${items[0].title}" title="${items[0].title}">
                              </#if>
                            </a>
                        </div>
                        <div class="zone-recom">
                            <p>
                                <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                   title="${items[0].title}">
                                ${items[0].title}
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 0 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    ${item.title}
                                    </a>
                                </li>
                            </#if>
                          </#list>
                        </ul>
                    </div>
                </#if>
              </#list>
              </dd>
          </dl>
          <dl class="clearfix" title="IT/互联网">
            <dt>
              <span>IT/互联网</span>
            </dt>
              <dd>
              <#list hotShareDataMap?keys as mKey>
                <#if mKey=='IT/互联网'>
                  <#assign items = hotShareDataMap[mKey]>
                    <div class="zone-info clearfix">
                        <div class="zone-img">
                            <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                              <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                  <img src="${items[0].imgurl}"
                                       alt="${items[0].title}" title="${items[0].title}">
                              <#else>
                                  <img src="/51jobplusCore/image/index_default.jpg"
                                       alt="${items[0].title}" title="${items[0].title}">
                              </#if>
                            </a>
                        </div>
                        <div class="zone-recom">
                            <p>
                                <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                   title="${items[0].title}">
                                ${items[0].title}
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 0 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    ${item.title}
                                    </a>
                                </li>
                            </#if>
                          </#list>
                        </ul>
                    </div>
                </#if>
              </#list>
              </dd>
          </dl>
          <dl class="clearfix" title="工程/技术">
            <dt>
               <span>工程/技术</span>
            </dt>
              <dd>
              <#list hotShareDataMap?keys as mKey>
                <#if mKey=='工程/技术'>
                  <#assign items = hotShareDataMap[mKey]>
                    <div class="zone-info clearfix">
                        <div class="zone-img">
                            <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                              <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                  <img src="${items[0].imgurl}"
                                       alt="${items[0].title}" title="${items[0].title}">
                              <#else>
                                  <img src="/51jobplusCore/image/index_default.jpg"
                                       alt="${items[0].title}" title="${items[0].title}">
                              </#if>
                            </a>
                        </div>
                        <div class="zone-recom">
                            <p>
                                <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                   title="${items[0].title}">
                                ${items[0].title}
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 0 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    ${item.title}
                                    </a>
                                </li>
                            </#if>
                          </#list>
                        </ul>
                    </div>
                </#if>
              </#list>
              </dd>
          </dl>
          <dl class="clearfix" title="市场/销售">
            <dt>
              <span>市场/销售</span>
            </dt>
              <dd>
              <#list hotShareDataMap?keys as mKey>
                <#if mKey=='市场/销售'>
                  <#assign items = hotShareDataMap[mKey]>
                    <div class="zone-info clearfix">
                        <div class="zone-img">
                            <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                              <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                  <img src="${items[0].imgurl}"
                                       alt="${items[0].title}" title="${items[0].title}">
                              <#else>
                                  <img src="/51jobplusCore/image/index_default.jpg"
                                       alt="${items[0].title}" title="${items[0].title}">
                              </#if>
                            </a>
                        </div>
                        <div class="zone-recom">
                            <p>
                                <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                   title="${items[0].title}">
                                ${items[0].title}
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 0 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    ${item.title}
                                    </a>
                                </li>
                            </#if>
                          </#list>
                        </ul>
                    </div>
                </#if>
              </#list>
              </dd>
          </dl>
          <dl class="clearfix" title="供应/制造">
            <dt>
               <span>供应/制造</span>
            </dt>
            <dd>
            <#list hotShareDataMap?keys as mKey>
              <#if mKey=='供应/制造'>
                <#assign items = hotShareDataMap[mKey]>
                  <div class="zone-info clearfix">
                      <div class="zone-img">
                          <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                            <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                <img src="${items[0].imgurl}"
                                     alt="${items[0].title}" title="${items[0].title}">
                            <#else>
                                <img src="/51jobplusCore/image/index_default.jpg"
                                     alt="${items[0].title}" title="${items[0].title}">
                            </#if>
                          </a>
                      </div>
                      <div class="zone-recom">
                          <p>
                              <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                 title="${items[0].title}">
                              ${items[0].title}
                              </a>
                          </p>
                      </div>
                  </div>
                  <div class="related-doc">
                      <ul>
                        <#list items as item>
                          <#if item_index gt 0 && item_index lt 9>
                              <li>
                                  [${item.extendinfo5}]
                                  <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                  ${item.title}
                                  </a>
                              </li>
                          </#if>
                        </#list>
                      </ul>
                  </div>
              </#if>
            </#list>
            </dd>
          </dl>
          <dl class="clearfix" title="创意/设计">
            <dt>
               <span>创意/设计</span>
            </dt>
            <dd>
            <#list hotShareDataMap?keys as mKey>
              <#if mKey=='创意/设计'>
                <#assign items = hotShareDataMap[mKey]>
                  <div class="zone-info clearfix">
                      <div class="zone-img">
                          <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                            <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                <img src="${items[0].imgurl}"
                                     alt="${items[0].title}" title="${items[0].title}">
                            <#else>
                                <img src="/51jobplusCore/image/index_default.jpg"
                                     alt="${items[0].title}" title="${items[0].title}">
                            </#if>
                          </a>
                      </div>
                      <div class="zone-recom">
                          <p>
                              <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                 title="${items[0].title}">
                              ${items[0].title}
                              </a>
                          </p>
                      </div>
                  </div>
                  <div class="related-doc">
                      <ul>
                        <#list items as item>
                          <#if item_index gt 0 && item_index lt 9>
                              <li>
                                  [${item.extendinfo5}]
                                  <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                  ${item.title}
                                  </a>
                              </li>
                          </#if>
                        </#list>
                      </ul>
                  </div>
              </#if>
            </#list>
            </dd>
          </dl>
          <dl class="clearfix" title="媒体/影视">
            <dt>
             <span>媒体/影视</span>
            </dt>
              <dd>
              <#list hotShareDataMap?keys as mKey>
                <#if mKey=='媒体/影视'>
                  <#assign items = hotShareDataMap[mKey]>
                    <div class="zone-info clearfix">
                        <div class="zone-img">
                            <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                              <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                  <img src="${items[0].imgurl}"
                                       alt="${items[0].title}" title="${items[0].title}">
                              <#else>
                                  <img src="/51jobplusCore/image/index_default.jpg"
                                       alt="${items[0].title}" title="${items[0].title}">
                              </#if>
                            </a>
                        </div>
                        <div class="zone-recom">
                            <p>
                                <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                   title="${items[0].title}">
                                ${items[0].title}
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 0 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    ${item.title}
                                    </a>
                                </li>
                            </#if>
                          </#list>
                        </ul>
                    </div>
                </#if>
              </#list>
              </dd>
          </dl>
          <dl class="clearfix" title="翻译/出版">
             <dt>
                <span>翻译/出版</span>
             </dt>
              <dd>
              <#list hotShareDataMap?keys as mKey>
                <#if mKey=='翻译/出版'>
                  <#assign items = hotShareDataMap[mKey]>
                    <div class="zone-info clearfix">
                        <div class="zone-img">
                            <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})">
                              <#if (items[0].imgurl)?? && (items[0].imgurl)!=''>
                                  <img src="${items[0].imgurl}"
                                       alt="${items[0].title}" title="${items[0].title}">
                              <#else>
                                  <img src="/51jobplusCore/image/index_default.jpg"
                                       alt="${items[0].title}" title="${items[0].title}">
                              </#if>
                            </a>
                        </div>
                        <div class="zone-recom">
                            <p>
                                <a href='javascript:void(0)' onclick="toHref('${items[0].datatype}',${items[0].dataid})"
                                   title="${items[0].title}">
                                ${items[0].title}
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 0 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    ${item.title}
                                    </a>
                                </li>
                            </#if>
                          </#list>
                        </ul>
                    </div>
                </#if>
              </#list>
              </dd>
          </dl>
        </div>
      </div>
      <div class="row-side">
        <div class="ui-rank" >
          <div class='hot-recommend'>
            <span>热门推荐</span>
          </div>
         <div id='hot-recommend' class='hot-recommend-book'>
          <ul>
              <#list recommDatas as data>
                  <li class="clearfix">
                      <div class="book-img">
                          <a href='javascript:void(0)' onclick="toHref('${data.datatype}',${data.dataid})">
                            <#if (data.imgurl)?? && (data.imgurl)!=''>
                                <img  src="${data.imgurl}"
                                     title="${data.title}" alt="${data.title}">
                              <#else>
                                  <img  src="/51jobplusCore/image/book_default.jpg"
                                       title="${data.title}" alt="${data.title}">
                            </#if>
                          </a>
                      </div>
                      <div class="book-info">
                          <h6>
                              <a href='javascript:void(0)' onclick="toHref('${data.datatype}',${data.dataid})"
                                 title="${data.title}">
                                ${data.title}
                              </a>
                          </h6>
                          <p class="author">
                              作者:${data.extendinfo}
                          </p>
                          <p class="press">
                              出版社:${data.extendinfo2}
                          </p>
                          <p class="read-num">
                          <span>
                          ${data.kpi2}
                          </span>
                              人推荐
                          </p>
                      </div>
                  </li>
              </#list>
          </ul>
		 </div>
        </div>
      </div>
    </div>
    <div id="advertisingb" class="clearfix">
      <a href="#" target="_blank" class="log-xsend">
        <img class="lazybg" src="/51jobplusCore/image/jd文教-1200-110.jpg" alt="医药健康专区" title="医药健康专区">
      </a>
    </div>
    <a id="backtop" title="回到顶部" href="#top" style="display: none; bottom:300px;"></a>
    </div>
    
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
    <div class='pagetemplate'></div>
    <#include "/mydocs/commonTemplate/pmorsmgjs/pmorsmg.ftl"/> 
    <script type="text/javascript" src="/51jobplusCore/scripts/jquery.pause.min.js"></script>
    <script type="text/javascript" src="/51jobplusCore/scripts/jcarousellite_1.0.1.pack.js"></script>
    <script type="text/javascript" src="/51jobplusCore/scripts/jcarousellite_1.0.1c4.js"></script>
    <script type="text/javascript" src="/51jobplusCore/scripts/pj_content.js"></script>

    
</html>