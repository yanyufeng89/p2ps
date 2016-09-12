<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>
            话题专区
    </title>
    <meta name="viewport" content="width=1200"/> 
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_searchres.css">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_framework.css">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_index.css">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_simplePagination.css">
  </head>
  
  <body>
    <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
    <div class='division-banner'>
	  <span>聘加知识库>话题</span>
	</div>
    <input type="hidden" id="topicstype" value="${topicstype}"/>
    <div id="topic-division-container" class='topic-division-container'>
      <div class="content">
        <div class="positionHead">
            <ul id="filterBox" class="filter-wrapper">
                <div class="details" id="filterCollapse">
                    <li class="first">
                    <dl class='topic-division-title'>
                        <dl class="title">
                            话题专区:
                        </dl>
                        <span <#if theme==1>class="active"</#if> data-index="1">热门话题</span>
                        <span <#if theme==2>class="active"</#if> data-index="2">最新话题</span>
                        <span <#if theme==3>class="active"</#if> data-index="3">等待回答</span>
                        <span <#if theme==4>class="active"</#if> data-index="4">精彩问答</span>
                     </dl>
                     <a href="/51jobplusCore/sharein/searchuploadFile" target="_blank" class="topic-sharein" style='margin:-6px 0 0 514px;'></a>
                    </li>
                    <li class="last">
                        <dl class="title">分类:</dl>
                        <span <#if topicstype==''>class="active"</#if> data-index="">所有</span>
                        <#list typeConfigs as type>
                            <#if topicstype==type.typeid?c>
                                <span data-index="${type.typeid}" class="active">${type.typename}</span>
                            <#else>
                                <span data-index="${type.typeid}">${type.typename}</span>
                            </#if>
                        </#list>
                    </li>
                </div>
            </ul>
        </div>
      </div>
      <div class="all-news-list" id="all-news-list">
          <div class="alllist" id="allnews_all">
             <div class="items_area">
                 <#if (topicsPage)??>
                     <#list topicsPage.list as data>
                         <div class="item">
                             <div class='topicdivision-content'>
                                 <div class='topicdivision-left'>
                                     <div class="title">
                                         <a target="_blank" href="javascript:void(0);" onclick="toHref('tbl_topics',${data.id})" title="${data.title}">${data.title}</a>
                                     </div>
                                     <div class="newsinfo" <#if data.intro??>title="${data.intro}"</#if>>
                                         <p>
                                            <#if data.intro??>
                                                ${data.intro}
                                            </#if>
                                         </p>
                                     </div>
                                     <div class="tools">
                                         <div class="zm-meta-panel">
                                             <a  class="answer-date-link meta-item" target="_blank" href="javascript:void(0);">编辑于&nbsp;${data.showcreatetime}</a>

                                             <a href="javascript:void(0);" name="addcomment" class="meta-item toggle-comment js-comment">
                                                 <i class="z-icon-concern"></i><#if data.followssum??>${data.followssum}<#else>0</#if>人关注
                                             </a>

                                             <a href="javascript:void(0);" class="meta-item zu-autohide js-thank">
                                                 <i class="z-icon-thank"></i>
                                                 <#if data.likesum??>${data.likesum}<#else>0</#if>人赞
                                             </a>

                                             <a href="javascript:void(0);" class="meta-item zu-autohide js-share goog-inline-block goog-menu-button">
                                                 <i class="z-icon-comment"></i><#if data.replysum??>${data.replysum}<#else>0</#if>人回答
                                             </a>
                                         </div>
                                     </div>
                                 </div>
                                 <div class='topicdivision-right'>
                                     <div class="picno">
                                         <div class="pic">
                                             <div class="picx">
                                                 <#if data.topicimg??>
                                                     <div class="pict">
                                                       <img src='${data.topicimg}' alt=''>
                                                     </div>
                                                 <#else>
                                                     <div class="pict">
                                                        <img src='/51jobplusCore/image/search_default.jpg' alt=''>
                                                     </div>
                                                 </#if>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </#list>
                 </#if>
          </div>
      </div>
    </div>
    <#if (topicsPage.last)??>
        <#if (topicsPage.last gt 1)>
            <div class='page-inner'>
                <div class="ui-pager pager-center">
                    <div class='pager'>
                        <div class="pager-inner">
                            <div id="sharetopicpaging" class="page"></div>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    </#if>
   </div>
    <div class='pagetemplate'></div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
	<#include "/mydocs/commonTemplate/pmorsmgjs/pmorsmg.ftl"/>
    <script type='text/javascript' src='/51jobplusCore/scripts/jquery.simplePagination.js'></script>
	<script type="text/javascript" src="/51jobplusCore/scripts/pj_topicdivision.js"></script>
    <script type="text/javascript">
        <#if (topicsPage.list)??>
            $(function () {
                $("#sharetopicpaging").pagination({
                    items: ${topicsPage.count},
                    itemsOnPage:${topicsPage.pageSize},
                    cssStyle: 'light-theme',
                    moduleType: 'topicsearch'
                });
            });
        </#if>
    </script>
  </body>

</html>