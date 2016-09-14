<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>
           导航搜索
    </title>
    <meta name="viewport" content="width=1230"/> 
    <meta name="apple-mobile-web-app-capable" content="yes" /> 
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_searchres.css">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_framework.css">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_index.css">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_simplePagination.css">
  </head>
  
  <body>
    <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 

    <div class='division-banner'>
	  <#-- <span>JobPlus知识库>创新/创新方法</span> -->
	  
	  <#if (preSharedType == 0)|| (preSharedType == "") >
		    <span>JobPlus知识库><span id="span_type1">所有</span></span>
	  <#else>
		  <#list searchTypeList as clist>
		  	<#if preSharedType == clist.parTagId?c>
			   <span>JobPlus知识库><span id="span_type1">${clist.parTagName}</span></span>
			</#if>
		  
			<#list clist.chlTagList as ctagList>
				<#if preSharedType == ctagList.typeid?c>
					<#-- <a data-id="${ctagList.typeid}" data-pid="${clist.parTagId}" href="#" class="active"><dt>${ctagList.typename}</dt></a>  -->
					<span>JobPlus知识库><span id="span_type1">${clist.parTagName}</span>/<span id="span_type1">${ctagList.typename}</span></span>
				</#if>
			</#list>
		  </#list>
	  </#if>
	  
	  
	</div>
	<div id="searches" class="searches">
	<#-- 
     <form id="searchresForm" class="searchForm" name="searchForm" action="/51jobplusCore/search/" method="get">
     -->
       <div class="searchForm">
        <span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
        <#-- 
        <input type="submit" id="searchres_button" class="search_button" value="搜索知识">
        -->
        <input type="button" id="searchres_button" class="search_button" onclick="javascript:reloadPage();" value="搜索知识">
        <input type="text" id="searchres_input" class="search_input ui-autocomplete-input" name="Condition" tabindex="1" maxlength="64" autocomplete="off" 
        placeholder="JobPlus海量知识库" style="color: rgb(153, 153, 153);" value="${preCondition}" onkeypress="if(event.keyCode==13){reloadPage();};">
        <input type="hidden" name="fromSearch" id="fromSearches" value="true">
        <input type="botton" name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索" onclick="javascript:reloadPage();">
      </div>
      <#--  
      </form>
      -->
      
      
      <a href="/51jobplusCore/sharein/searchuploadFile" target="_blank" class="topic-sharein" style='float:right'></a>
    </div>
    <div id="search-navigation-container" class='search-navigation-container'>
      <div class="content">
        <div class="positionHead">
          <ul id="searchBox" class="filter-wrapper">
            <div class="details" id="searchCollapse">
              <li class="">
               <div class="clear"></div>
			   <dl class='select-1'>
                <dl class="title">
                                        分类: 
                </dl>
                <#if (preSharedType == 0)|| (preSharedType == "") >
                 <span data-id="0" class="active">所有</span>
                <#else>
                 <span data-id="0" >所有</span>
                </#if>
               
					<#list searchTypeList as clist>
						<#if preSharedType == clist.parTagId?c>
		                 <span data-id=${clist.parTagId} data-pid="0" class="active">${clist.parTagName}</span>
		                <#else>
		                 <span data-id=${clist.parTagId} data-pid="0" >${clist.parTagName}</span>
		                </#if>
						<#-- 
						<span data-id=${clist.parTagId}>${clist.parTagName}</span>
						-->
					</#list>
			   </dl>
			   <div class="clear"></div>
			   <dl class='select-2'>
			     <div class="select-2-arrow" style="display: none;"></div>
			     <#list searchTypeList as clist>
		                 <div class="2-list" style="display: none;">
			                 <#list clist.chlTagList as ctagList>
				     			<#if preSharedType == ctagList.typeid?c>
									<a data-id="${ctagList.typeid}" data-pid="${clist.parTagId}" href="#" class="active"><dt>${ctagList.typename}</dt></a>
								<#else>
									<a data-id="${ctagList.typeid}" data-pid="${clist.parTagId}" href="#"><dt>${ctagList.typename}</dt></a>
								</#if>
							</#list>
						</div>

				</#list>
			   </dl>
              </li>
              <li class="last">
                <dl class="title">知识类型:</dl>
               
                <#if preProtoType?? && preProtoType == "doc">
	                <span data-objtype="">所有</span>
					<span data-objtype="doc" class="active">文档</span>
	                <span data-objtype="topics">话题</span>
	                <span data-objtype="book">书籍</span>
	                <span data-objtype="courses">课程</span>
	                <span data-objtype="article">文章</span>
	                <span data-objtype="site">站点</span>
                <#elseif preProtoType?? && preProtoType == "topics">
	                <span data-objtype="">所有</span>
					<span data-objtype="doc">文档</span>
	                <span data-objtype="topics" class="active">话题</span>
	                <span data-objtype="book">书籍</span>
	                <span data-objtype="courses">课程</span>
	                <span data-objtype="article">文章</span>
	                <span data-objtype="site">站点</span>
                <#elseif preProtoType?? && preProtoType == "book">
	                <span data-objtype="">所有</span>
					<span data-objtype="doc">文档</span>
	                <span data-objtype="topics">话题</span>
	                <span data-objtype="book" class="active">书籍</span>
	                <span data-objtype="courses">课程</span>
	                <span data-objtype="article">文章</span>
	                <span data-objtype="site">站点</span>
                <#elseif preProtoType?? && preProtoType == "courses">
	                <span data-objtype="">所有</span>
					<span data-objtype="doc">文档</span>
	                <span data-objtype="topics">话题</span>
	                <span data-objtype="book">书籍</span>
	                <span data-objtype="courses" class="active">课程</span>
	                <span data-objtype="article">文章</span>
	                <span data-objtype="site">站点</span>
                <#elseif preProtoType?? && preProtoType == "article">
                	<span data-objtype="">所有</span>
					<span data-objtype="doc">文档</span>
	                <span data-objtype="topics">话题</span>
	                <span data-objtype="book">书籍</span>
	                <span data-objtype="courses">课程</span>
	                <span data-objtype="article" class="active">文章</span>
	                <span data-objtype="site">站点</span>
                <#elseif preProtoType?? &&  preProtoType == "site">
                	<span data-objtype="">所有</span>
					<span data-objtype="doc">文档</span>
	                <span data-objtype="topics">话题</span>
	                <span data-objtype="book">书籍</span>
	                <span data-objtype="courses">课程</span>
	                <span data-objtype="article">文章</span>
	                <span data-objtype="site" class="active">站点</span>
	            <#-- <#if preProtoType == "" || preProtoType == 0 || preProtoType??> -->
	            <#else>
	                <span data-objtype="" class="active">所有</span>
					<span data-objtype="doc">文档</span>
	                <span data-objtype="topics">话题</span>
	                <span data-objtype="book">书籍</span>
	                <span data-objtype="courses">课程</span>
	                <span data-objtype="article">文章</span>
	                <span data-objtype="site">站点</span>
                </#if>
                
              </li>
            </div>
          </ul>
        </div>
      </div>
      <div class="all-news-list" id="all-search-list">
          <div class="alllist" id="allsearch_all">
             <div class="items_area">
  <#--查询结果集-->
  <#assign resultList=result?eval />
  <#list resultList as item>
  <#if item.protoType == "doc">
  		<div class="item" id="item-1">
                  <div class="topicdivision-content">
					<div class="topicdivision-left">
						 <div class="title">
							 <a target="_blank" href="${request.getContextPath()}/docs/getDocsDetail?id=${item.data_id}"  title="邪恶力量？" style='margin-left: -6px;' >【文档】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="5646">
							 <p>
									<#if item.description??>${item.description}<#else>0</#if>
							 </p>
						 </div>
						 <div class="tools">
							 <div class="pj-meta-panel">
								 <a class="answer-date-link pj-meta-item" target="_blank" href="javascript:void(0);">发布于&nbsp;${item.createTime}</a>

                                 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-thank">
									 <i class="z-icon-preview"></i>
									 <#if item.readSum??>${item.readSum}<#else>0</#if>人浏览
								 </a>
								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-thank">
									 <i class="z-icon-thank"></i>
									 <#if item.likeSum??>${item.likeSum}<#else>0</#if>人赞
								 </a>
                                <a href="javascript:void(0);;" name="addcomment" class="pj-meta-item toggle-comment js-comment">
									 <i class="z-icon-download"></i><#if item.downSum??>${item.downSum}<#else>0</#if>人下载
								 </a>
								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-share  goog-menu-button">
									 <i class="z-icon-collect"></i><#if item.collectSum??>${item.collectSum}<#else>0</#if>人收藏
								 </a>
							 </div>
						 </div>
						 </div>
						 <div class="topicdivision-right">
							 <div class="picno">
								 <div class="pic">
									 <div class="picx">
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''></div>
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
                </div>
  <#elseif item.protoType == "book">
  			<div class="item" id="item-1">
                  <div class="topicdivision-content">
					<div class="topicdivision-left">
						 <div class="title">
							 <a target="_blank" href="${request.getContextPath()}/books/getBookDetail?id=${item.data_id}"  title="邪恶力量？" style='margin-left: -6px;'>【书籍】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="5646">
							 <p>
									<#if item.description??>${item.description}<#else>0</#if>
							 </p>
						 </div>
						 <div class="tools">
							 <div class="pj-meta-panel">
								 <a class="answer-date-link pj-meta-item" target="_blank" href="javascript:void(0);">发布于&nbsp;${item.createTime}</a>
							<#--
							<a href="javascript:void(0);;" name="addcomment" class="pj-meta-item toggle-comment js-comment">
									 <i class="z-icon-preview"></i><#if item.readSum??><#if item.readSum??>${item.readSum}<#else>0</#if><#else>0</#if>人浏览
								 </a>							
							 -->
								 
								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-thank">
									 <i class="z-icon-comment"></i>
									 <#if item.likeSum??>${item.replySum}<#else>0</#if>人推荐
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-share  goog-menu-button">
									 <i class="z-icon-collect"></i><#if item.collectSum??>${item.collectSum}<#else>0</#if>人收藏
								 </a>
							 </div>
						 </div>
						 </div>
						 <div class="topicdivision-right">
							 <div class="picno">
								 <div class="pic">
									 <div class="picx">
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''></div>
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
                </div>
  <#elseif item.protoType == "article">
  				<div class="item" id="item-1">
                  <div class="topicdivision-content">
					<div class="topicdivision-left">
						 <div class="title">
							 <a target="_blank" href="${request.getContextPath()}/article/getArticleDetail?id=${item.data_id}"  title="邪恶力量？" style='margin-left: -6px;'>【文章】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="5646">
							 <p>
									<#if item.description??>${item.description}<#else>0</#if>
							 </p>
						 </div>
						 <div class="tools">
							 <div class="pj-meta-panel">
                                 <a class="answer-date-link pj-meta-item" target="_blank" href="javascript:void(0);">发布于&nbsp;${item.createTime}</a>
								 <a href="javascript:void(0);;" name="addcomment" class="meta-item toggle-comment js-comment">
									 <i class="z-icon-preview"></i><#if item.readSum??>${item.readSum}<#else>0</#if>人浏览
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-thank">
									 <i class="z-icon-thank"></i>
									 <#if item.likeSum??>${item.likeSum}<#else>0</#if>人赞
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-share  goog-menu-button">
									 <i class="z-icon-collect"></i><#if item.collectSum??>${item.collectSum}<#else>0</#if>人收藏
								 </a>
							 </div>
						 </div>
						 </div>
						 <div class="topicdivision-right">
							 <div class="picno">
								 <div class="pic">
									 <div class="picx">
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''></div>
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
                </div>
  <#elseif item.protoType == "courses">
  				<div class="item" id="item-1">
                  <div class="topicdivision-content">
					<div class="topicdivision-left">
						 <div class="title">
							 <a target="_blank" href="${request.getContextPath()}/courses/getCourseDetail?id=${item.data_id}"  title="邪恶力量？" style='margin-left: -6px;'>【课程】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="5646">
							 <p>
									<#if item.description??>${item.description}<#else>0</#if>
							 </p>
						 </div>
						 <div class="tools">
							 <div class="pj-meta-panel">
								<a class="answer-date-link pj-meta-item" target="_blank" href="javascript:void(0);">发布于&nbsp;${item.createTime}</a>

								 <a href="javascript:void(0);;" name="addcomment" class="pj-meta-item toggle-comment js-comment">
									 <i class="z-icon-preview"></i><#if item.readSum??>${item.readSum}<#else>0</#if>人浏览
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-thank">
									 <i class="z-icon-thank"></i>
									 <#if item.likeSum??>${item.likeSum}<#else>0</#if>人赞
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-share  goog-menu-button">
									 <i class="z-icon-collect"></i><#if item.collectSum??>${item.collectSum}<#else>0</#if>人收藏
								 </a>
							 </div>
						 </div>
						 </div>
						 <div class="topicdivision-right">
							 <div class="picno">
								 <div class="pic">
									 <div class="picx">
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''></div>
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
                </div>
  <#elseif item.protoType == "site">
  			<div class="item" id="item-1">
                  <div class="topicdivision-content">
					<div class="topicdivision-left">
						 <div class="title">
							 <a target="_blank" href="${request.getContextPath()}/sites/getSiteDetail?id=${item.data_id}" title="邪恶力量？" style='margin-left: -6px;'>【站点】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="5646">
							 <p>
									<#if item.description??>${item.description}<#else>0</#if>
							 </p>
						 </div>
						 <div class="tools">
							 <div class="pj-meta-panel">
							     <a class="answer-date-link pj-meta-item" target="_blank" href="javascript:void(0);">发布于&nbsp;${item.createTime}</a>
								 <a href="javascript:void(0);;" name="addcomment" class="pj-meta-item toggle-comment js-comment">
									 <i class="z-icon-preview"></i><#if item.readSum??>${item.readSum}<#else>0</#if>人浏览
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-thank">
									 <i class="z-icon-thank"></i>
									 <#if item.likeSum??>${item.likeSum}<#else>0</#if>人赞
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-share  goog-menu-button">
									 <i class="z-icon-collect"></i><#if item.collectSum??>${item.collectSum}<#else>0</#if>人收藏
								 </a>
							 </div>
						 </div>
						 </div>
						 <div class="topicdivision-right">
							 <div class="picno">
								 <div class="pic">
									 <div class="picx">
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''></div>
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
                </div>
                
  
  <#else>
  		<div class="item" id="item-1">
                  <div class="topicdivision-content">
					<div class="topicdivision-left">
						 <div class="title">
							 <a target="_blank" href="${request.getContextPath()}/topics/getTopicsDetail?topicId=${item.data_id}"  title="邪恶力量？" style='margin-left: -6px;'>【话题】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="5646">
							 <p>
									<#if item.title??>${item.title}<#else>0</#if>
							 </p>
						 </div>
						 <div class="tools">
							 <div class="pj-meta-panel">
								 <a class="answer-date-link pj-meta-item" target="_blank" href="javascript:void(0);">编辑于&nbsp;${item.createTime}</a>
								<a href="javascript:void(0);;" name="addcomment" class="meta-item toggle-comment js-comment">
									 <i class="z-icon-comment"></i><#if item.readSum??>${item.readSum}<#else>0</#if>人评论
								 </a>
								 

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-thank">
									 <i class="z-icon-thank"></i>
									 <#if item.likeSum??>${item.collectSum}<#else>0</#if>人关注
								 </a>

								 <a href="javascript:void(0);;" class="pj-meta-item zu-autohide js-share goog-menu-button">
									 <i class="z-icon-comment"></i><#if item.replySum??>${item.replySum}<#else>0</#if>人回答
								 </a>
							 </div>
						 </div>
						 </div>
						 <div class="topicdivision-right">
							 <div class="picno">
								 <div class="pic">
									 <div class="picx">
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''></div>
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
                </div>
  </#if>          
             
</#list>

              
          </div>
      </div>
    </div>
    <#-- 
    <div class="csdn-pagination page1">
	    <span class="page-nav">
	        <a href="#md" data-page="1" class="btn btn-xs btn-default">首页</a>
	        <a href="#md" data-page="1" class="btn btn-xs btn-default btn-prev">&lt;</a>
	        
	            <a href="#md" data-page="1" class="btn btn-xs btn-default ">1</a>
	        
	            <a href="#md" data-page="2" class="btn btn-xs btn-default active cblue">2</a>
	        
	            <a href="#md" data-page="3" class="btn btn-xs btn-default ">3</a>
	        
	        <a href="#md" data-page="3" class="btn btn-xs btn-default btn-next">&gt;</a>
	        <a href="#md" data-page="3" class="btn btn-xs btn-default">末页</a>
	    </span>
    </div>
    -->
    
    <#if (reCount) gt 0>
            <div class='page-inner'>
                <div class="ui-pager pager-center">
                    <div class='pager'>
                        <div class="pager-inner">
                            <div id="searchpaging" class="page"></div>
                        </div>
                    </div>
                </div>
            </div>
      </#if>
        
   </div>
    <div class='pagetemplate'></div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
	<#include "/mydocs/commonTemplate/pmorsmgjs/pmorsmg.ftl"/> 
	<script type="text/javascript" src="/51jobplusCore/scripts/jquery.media.js"></script>
	<script type="text/javascript" src="/51jobplusCore/scripts/pj_searchResult.js"></script>
	<script type='text/javascript' src='/51jobplusCore/scripts/jquery.simplePagination.js'></script>
	<script type="text/javascript">
        <#if (reCount) gt 0>
            $(function () {
                $("#searchpaging").pagination({
                    items: ${reCount},
                    itemsOnPage:${pageSize},
                    cssStyle: 'light-theme',
                    moduleType: 'search'
                });
            });
        </#if>
    </script>
  </body>

</html>