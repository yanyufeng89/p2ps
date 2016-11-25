<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>JobPlus官方网站- 构建企业知识服务体系</title>
    <meta name="description" content="JobPlus网是国内首创的企业知识库公共平台,结构化的知识体系,包含文档,书籍,文章,网站,问答,在线课程的分享学习社区。一站式知识库平台,为7亿职场人员提供优质学习分享社区,JobPlus是你最值得信赖的终身学习伙伴。">
    <meta name="keywords" content="JobPlus,知识分享,知识库,文档,书籍,问答,课程,文章,网站,在线培训,企业课堂,员工培训,在线教育,职业技能,视频课程,培训网站,职场培训,网络课堂,人才培训,内容管理">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta property="wb:webmaster" content="a07e8efa7bc99422" />
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_framework.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_index.css" charset="UTF-8">
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
                  <a href="${request.getContextPath()}/search/${cMap['rootID']}" target="_self">
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
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID1']}" target="_self" class="log-xsend">
                    ${cMap['firstViewName1']}
                  </a>
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID2']}" target="_self" class="log-xsend">
                   ${cMap['firstViewName2']}
                  </a>
                </dd>
                <dd>
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID3']}" target="_self" class="log-xsend">
                    ${cMap['firstViewName3']}
                  </a>
                  <a href="${request.getContextPath()}/search/${cMap['firstViewID4']}" target="_self" class="log-xsend">
                    ${cMap['firstViewName4']}
                  </a>
                </dd>
              </dl>
			  <div class='menu_sub dn'> 
			    <dl>
				  <dt><a href="${request.getContextPath()}/search/${cMap['childID1']}" target="_self">${cMap['childName1']}</a></dt>
				  <dd>
				  	<#list cMap['childListData1']?split(',') as ctName>
				  			<a href="${request.getContextPath()}/search/${ctName?split(':')[0]}"  target='_self'>${ctName?split(':')[1]}</a>
				  	</#list>
				  </dd>
				   <dt><a href="${request.getContextPath()}/search/${cMap['childID2']}" target="_self">${cMap['childName2']}</a></dt>
				  <dd>
				    <#list cMap['childListData2']?split(',') as ctName>
				  			<a href="${request.getContextPath()}/search/${ctName?split(':')[0]}"  target='_self'>${ctName?split(':')[1]}</a>
				  	</#list>
				  </dd>
				</dl>
			  </div>
  </#list>
  
  
</#if>
              
            </div>
            <div id="search_box" class="search_box">
              <form id="searchForm" class="searchForm" name="searchForm" action="/search/0"
              method="get">
                <span role="status" aria-live="polite" class="ui-helper-hidden-accessible">
                </span>
                
                <input type="submit" id="search_button" class="search_button" value="知识搜索">
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
                
                <input type="submit" id="search_button" class="search_button" value="知识搜索">
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
                    <a href="javascript:void(0)"  target="_self" class="log-xsend">
                      <img alt="图片1"  src="/image/1.jpg" class="lazy" >
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="javascript:void(0)" target="_self" class="log-xsend">
                      <img alt="图片2" src="/image/2.jpg" class="lazy" >
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="javascript:void(0)"  target="_self" class="log-xsend">
                      <img alt="图片3"  src="/image/3.jpg" class="lazy" >
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="javascript:void(0)" target="_self" class="log-xsend">
                      <img alt="图片4"  src="/image/4.jpg" class="lazy" >
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="javascript:void(0)" target="_self" class="log-xsend">
                      <img alt="图片5" src="/image/5.jpg" class="lazy" >
                    </a>
                  </li>
                  <li class="bg-loading " style="opacity: 0; z-index: 1;">
                    <a href="javascript:void(0)" target="_self" class="log-xsend">
                      <img alt="图片6"  src="/image/6.jpg" class="lazy">
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
			  <div class='knowledge-division'>
			      <ul class='clearfix'>
			         <li class='clearfix first'>
			           <a class='topic-division' href='/topics/fore/search/0' target='_self'>
			             <div class='tpc-img'>
			              <img src='/image/topic-division.png' class="lazy" alt='话题专区' height='72' width='136'>
			             </div>
			             <div class='tpc-brief'>
			              <span class='topic-brief'>
			                  <h5>话题专区</h5>
			                  <span>查看详情</span>
			              </span>
			             </div>
			           </a>
			         </li>
			         <li class='clearfix second'>
			            <a class='book-division'  href='/books/fore/area/0' target='_self'>
			              <div class='tpc-img'>
			                <img src='/image/book-division.png' class="lazy" alt='书籍专区' height='72' width='136'>
			              </div>
			              <div class='tpc-brief'>
			                <span class='book-brief'>
			                  <h5>书籍专区</h5>
			                  <span>查看详情</span>
			                </span>
			               </div>
			             </a>
			         </li>
			      </ul>
			  </div>
            </div>
          </div>
          <div class="user-info">
             <div class="logined-wrap" id="logininfo">
				
				<div class="logined-user-info">
				<p class="num-tip">JobPlus海量知识体系</p>
				<p class="num" id="total-docnum" data-docnum="153,377,154">
				     <span class="num1" data-num="1"></span>
				     <span class="num0" data-num="0"></span>
				     <span class="num0" data-num="0"></span>
				     <span class="spr"></span>
				   
				     <span class="num0" data-num="0"></span>
				     <span class="num0" data-num="0"></span>
				     <span class="num0" data-num="0"></span>
				     <span class="spr"></span>
				 
				     <span class="num0" data-num="0"></span>
				     <span class="num0" data-num="0"></span>
				     <span class="num0" data-num="0"></span>
				 </p>
				<div class="org-user-tip">
				<p>点击头像,访问我的机构主页</p>
				<span class="arrow-icon"></span><span class="close-icon close-pic"></span>
				</div>
				<div class="user-brief bg-index clearfix">
				<div class="avatar">
				   <div>
				      <a href="/myCenter/getMyHeadTop" target="_self" class="bg-avatar bg-index">
				         <#if (Session.user.headicon)??&& Session.user.headicon?length gt 0>
		                   <img src="${Session.user.headicon}"  class="lazy <#if Session.user.usertype==2>img-company<#else>img-person</#if>" width='60' height='60' alt="个人头像">
		                 <#else>
		                   <img src="/image/myphoto.jpg"  class="lazy <#if Session.user.usertype==2>img-company<#else>img-person</#if>" width='60' height='60' alt="个人头像">
		                 </#if>
		              </a>
				   </div>
				   
				</div>
				<div class="name-task">
				<p class="name">
			    <#if (Session.user)??>
				<a href='/myCenter/getMyHeadTop' class="name-link" title="<#if (Session.user)??> ${Session.user.username} </#if>" target="_self">${Session.user.username}</a>
				</#if>
				</p>
				<p class="task">
				    <a href="javascript:void(0)" target="_self" class="daily-task">分享知识,领财富值</a>
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
                    <a href="/myCenter/getMyDocsUploaded" target="_self" class="nav-item mr25 mingjia">
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
                    <a href="/myCenter/getMyTopicsUploaded" target="_self" class="nav-item mr25 economy">
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
                    <a href="/myCenter/getSharedBookList" target="_self" class="nav-item mr25 internet">
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
                    <a href="/myCenter/getSharedCourseList" target="_self" class="nav-item mr25 tech">
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
                    <a href="/myCenter/getSharedArticleList" target="_self" class="nav-item mr25 fashion">
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
                    <a href="/myCenter/getSharedSiteList" target="_self" class="nav-item mr25 authors">
                      <div class="img"></div>  站点
                    </a>
				</li>
				</ul>
				</div>
				
				</div>
				</div>
				<div class="my-wk">
					<a class="upload-btn bg-index" style='left:0;top:0' target='_self'  href="/sharein/searchuploadFile"></a>
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
                           <span class='title-label'>${data.title}</span>
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
                           <span class='title-label'>${data.title}</span>
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
      <span>精彩分享</span>
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
                                   <img src="/image/wonderful _share_1.jpg"
                                        alt="创新/创业" title="创新/创业"  class="lazy">
                             </a>
                         </div>
                         <div class="zone-recom">
                             <#list items as item>
                                 <#if item_index gt -1 && item_index lt 2>
                                     <p>
                                         <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                         ${item.title}
                                         </a>
                                     </p>
                                 </#if>
                             </#list>
                         </div>
                     </div>
                 <div class="related-doc">
                 <ul>
                   <#list items as item>
                      <#if item_index gt 1 && item_index lt 9>
                          <li>
                              [${item.extendinfo5}]
                              <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                              <span class='title-label'>${item.title}</span>
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
                                  <img src="/image/wonderful _share_2.jpg"
                                       alt="${items[0].title}" title="${items[0].title}" class="lazy">
                            </a>
                        </div>
                        <div class="zone-recom">
                            <#list items as item>
                                <#if item_index gt -1 && item_index lt 2>
                                    <p>
                                        <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                        ${item.title}
                                        </a>
                                    </p>
                                </#if>
                            </#list>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 1 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    <span class='title-label'>${item.title}</span>
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
                                  <img src="/image/wonderful _share_3.jpg"
                                       alt="${items[0].title}" title="${items[0].title}" class="lazy">
                            </a>
                        </div>
                        <div class="zone-recom">
                            <#list items as item>
                                <#if item_index gt -1 && item_index lt 2>
                                    <p>
                                        <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                        ${item.title}
                                        </a>
                                    </p>
                                </#if>
                            </#list>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 1 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    <span class='title-label'>${item.title}</span>
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
                                  <img src="/image/wonderful _share_4.jpg"
                                       alt="${items[0].title}" title="${items[0].title}" class="lazy">
                            </a>
                        </div>
                        <div class="zone-recom">
                            <#list items as item>
                                <#if item_index gt -1 && item_index lt 2>
                                    <p>
                                        <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                        ${item.title}
                                        </a>
                                    </p>
                                </#if>
                            </#list>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 1 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    <span class='title-label'>${item.title}</span>
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
                                  <img src="/image/wonderful _share_5.jpg"
                                       alt="${items[0].title}" title="${items[0].title}" class="lazy">
                            </a>
                        </div>
                        <div class="zone-recom">
                            <#list items as item>
                                <#if item_index gt -1 && item_index lt 2>
                                    <p>
                                        <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                        ${item.title}
                                        </a>
                                    </p>
                                </#if>
                            </#list>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 1 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    <span class='title-label'>${item.title}</span>
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
                                <img src="/image/wonderful _share_6.jpg"
                                     alt="${items[0].title}" title="${items[0].title}" class="lazy">
                          </a>
                      </div>
                      <div class="zone-recom">
                          <#list items as item>
                              <#if item_index gt -1 && item_index lt 2>
                                  <p>
                                      <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                      ${item.title}
                                      </a>
                                  </p>
                              </#if>
                          </#list>
                      </div>
                  </div>
                  <div class="related-doc">
                      <ul>
                        <#list items as item>
                          <#if item_index gt 1 && item_index lt 9>
                              <li>
                                  [${item.extendinfo5}]
                                  <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                  <span class='title-label'>${item.title}</span>
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
                                <img src="/image/wonderful _share_7.jpg"
                                     alt="${items[0].title}" title="${items[0].title}" class="lazy">
                          </a>
                      </div>
                      <div class="zone-recom">
                          <#list items as item>
                              <#if item_index gt -1 && item_index lt 2>
                                  <p>
                                      <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                      ${item.title}
                                      </a>
                                  </p>
                              </#if>
                          </#list>
                      </div>
                  </div>
                  <div class="related-doc">
                      <ul>
                        <#list items as item>
                          <#if item_index gt 1 && item_index lt 9>
                              <li>
                                  [${item.extendinfo5}]
                                  <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                  <span class='title-label'>${item.title}</span>
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
                                  <img src="/image/wonderful _share_8.jpg"
                                       alt="${items[0].title}" title="${items[0].title}" class="lazy">
                            </a>
                        </div>
                        <div class="zone-recom">
                            <#list items as item>
                                <#if item_index gt -1 && item_index lt 2>
                                    <p>
                                        <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                        ${item.title}
                                        </a>
                                    </p>
                                </#if>
                            </#list>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 1 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    <span class='title-label'>${item.title}</span>
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
                                  <img src="/image/wonderful _share_10.jpg"
                                       alt="${items[0].title}" title="${items[0].title}" class="lazy">
                            </a>
                        </div>
                        <div class="zone-recom">
                            <#list items as item>
                                <#if item_index gt -1 && item_index lt 2>
                                    <p>
                                        <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                        ${item.title}
                                        </a>
                                    </p>
                                </#if>
                            </#list>
                        </div>
                    </div>
                    <div class="related-doc">
                        <ul>
                          <#list items as item>
                            <#if item_index gt 1 && item_index lt 9>
                                <li>
                                    [${item.extendinfo5}]
                                    <a href='javascript:void(0)' onclick="toHref('${item.datatype}',${item.dataid})" title="${item.title}">
                                    <span class='title-label'>${item.title}</span>
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
              <#assign resultList=recommDatas?eval />
              <#list resultList as data>
                  <li class="clearfix">
                      <div class="book-img">
                          <a href='javascript:void(0)' onclick="toHref('${data.protoType}',${data.data_id})">
                            <#if (data.imgUrl)?? && (data.imgUrl)!=''>
                                <img  src="${data.imgUrl}"
                                     title="${data.title}" alt="${data.title}" class="lazy">
                              <#else>
                                  <img  src="/image/default/166.jpg"
                                       title="${data.title}" alt="${data.title}" class="lazy">
                            </#if>
                          </a>
                      </div>
                      <div class="book-info">
                          <h6 class='textoverflow'>
                              <a href='javascript:void(0)' onclick="toHref('${data.protoType}',${data.data_id})"
                                 title="${data.title}">
                                ${data.title}
                              </a>
                          </h6>
                          <p class="author">
                              作者:${data.author}
                          </p>
                          <p class="press">
                              出版社:${data.press}
                          </p>
                          <p class="read-num">
                          <span>
                          ${data.replySum}
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
      <a href="javascript:void(0)" target="_self" class="log-xsend">
        <img class="lazybg lazy" src="/image/knowledgebase-1.jpg" alt='广告'>
      </a>
    </div>
    <a id="backtop" title="回到顶部" href="#top" class='back-to-top' style="display: none; bottom:300px;"></a>
    </div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
    <div class='pagetemplate'></div>
    <#include "/mydocs/commonTemplate/pmorsmgjs/pmorsmg.ftl"/> 
    <script type="text/javascript" src="/scripts/jquery.pause.min.js"></script>
    <script type="text/javascript" src="/scripts/jcarousellite_1.0.1.pack.js"></script>
    <script type="text/javascript" src="/scripts/jcarousellite_1.0.1c4.js"></script>
    <script type="text/javascript" src="/scripts/pj_content.js"></script>

    
</html>