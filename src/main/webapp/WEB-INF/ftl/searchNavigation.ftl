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
  
  <body  id="top">
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
	
	<div class='pj-searches-content' id='pj-searches-content'> 
	 <div class='pj-searches-left'>
		<div id="searches" class="searches">
		<#-- 
		 <form id="searchresForm" class="searchForm" name="searchForm" action="/51jobplusCore/search/" method="get">
		 -->
		   <div class="searchForm">
			<span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
			<#-- 
			<input type="submit" id="searchres_button" class="search_icon" value="知识搜索">
			-->
			<input type="button" id="searchres_button" class="search_icon" onclick="javascript:reloadPage();" value="知识搜索">
			<input type="text" id="searchres_input" class="search_input ui-autocomplete-input" name="Condition" tabindex="1" maxlength="64" autocomplete="off" 
			placeholder="JobPlus海量知识库" style="color: rgb(153, 153, 153);" value="${preCondition}" onkeypress="if(event.keyCode==13){reloadPage();};">
			<input type="hidden" name="fromSearch" id="fromSearches" value="true">
			<input type="botton" name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索" onclick="javascript:reloadPage();">
		  </div>
		  <#--  
		  </form>
		  -->
		</div>
	    <div id="search-navigation-container" class='search-navigation-container'>
      <div class="content">
        <div class="positionHead">
          <ul id="searchBox" class="filter-wrapper">
            <div class="details" id="searchCollapse">
              <li class="">
               <div class="clear"></div>
			   <dl class='select-1'>
                <dl class="title">知识分类:</dl>
                <#if (preSharedType == 0)|| (preSharedType == "") >
                 <span data-id="0" class="active">所有</span>
                <#else>
                 <span data-id="0" >所有</span>
                </#if>
				<#list searchTypeList as clist>
				  <#if  (clist_index <=11)>
					<#if preSharedType == clist.parTagId?c>
					 <span data-id=${clist.parTagId} data-pid="0" data-index='${clist_index}' class="active">${clist.parTagName}</span>
					<#else>
					 <span data-id=${clist.parTagId} data-pid="0" data-index='${clist_index}'>${clist.parTagName}</span>
					</#if>
					<#-- 
					<span data-id=${clist.parTagId}>${clist.parTagName}</span>
					-->
				  </#if>
				</#list>
				<div class="clear"></div>
			   </dl>

			   <dl class='select-2'>
			     <div class="select-2-arrow" style="display: none;"></div>
			     <#list searchTypeList as clist>
				   <#if (clist_index <=11)>
		                 <div class="2-list" data-index='${clist_index}' data-pid="${clist.parTagId}" style="display: none;"> 
			                 <#list clist.chlTagList as ctagList>
				     			<#if preSharedType == ctagList.typeid?c>
									<a data-id="${ctagList.typeid}"   href="#" class="active"><dt>${ctagList.typename}</dt></a>
								<#else>
									<a data-id="${ctagList.typeid}"   href="#"><dt>${ctagList.typename}</dt></a>
								</#if>
							</#list>
						</div>
				   </#if>
				</#list>
			   </dl>
			   <dl class='select-1  pt5'>
				<#list searchTypeList as clist>
				  <#if  (clist_index >11)>
					<#if preSharedType == clist.parTagId?c>
					 <span data-id=${clist.parTagId} data-pid="0"  data-index='${clist_index}'class="active">${clist.parTagName}</span>
					<#else>
					 <span data-id=${clist.parTagId} data-pid="0"  data-index='${clist_index}'>${clist.parTagName}</span>
					</#if>
					<#-- 
					<span data-id=${clist.parTagId}>${clist.parTagName}</span>
					-->
				  </#if>
				</#list>
				<div class="clear"></div>
			   </dl>
			   <dl class='select-2'>
			     <div class="select-2-arrow" style="display: none;"></div>
			     <#list searchTypeList as clist>
				   <#if (clist_index >11)>
		                 <div class="2-list"  data-index='${clist_index}' data-pid="${clist.parTagId}" style="display: none;">
			                 <#list clist.chlTagList as ctagList>
				     			<#if preSharedType == ctagList.typeid?c>
									<a data-id="${ctagList.typeid}"  href="#" class="active"><dt>${ctagList.typename}</dt></a>
								<#else>
									<a data-id="${ctagList.typeid}"  href="#"><dt>${ctagList.typename}</dt></a>
								</#if>
							</#list>
						</div>
				   </#if>
				</#list>
			   </dl>
              </li>
              <li class="last">
                    <dl class="title">知识类型:</dl>
	                <span data-objtype="" <#if (preProtoType == 0)||(preProtoType == "")>class="active"</#if>>所有</span>
					<span data-objtype="doc" <#if preProtoType?? && preProtoType == "doc">class="active"</#if>>文档</span>
	                <span data-objtype="topics" <#if preProtoType?? && preProtoType == "topics">class="active"</#if>>话题</span>
	                <span data-objtype="book" <#if preProtoType?? && preProtoType == "book">class="active"</#if>>书籍</span>
	                <span data-objtype="courses" <#if preProtoType?? && preProtoType == "courses">class="active"</#if>>课程</span>
	                <span data-objtype="article" <#if preProtoType?? && preProtoType == "article">class="active"</#if>>文章</span>
	                <span data-objtype="site" <#if preProtoType?? && preProtoType == "site">class="active"</#if>>站点</span>
              </li>
            </div>
          </ul>
        </div>
      </div>
      <div class="all-news-list" id="all-search-list">
          <div class="alllist" id="allsearch_all">
		    <#if (result?eval)?? && result?eval ?size gt 0>
             <div class="items_area">
  <#--查询结果集-->
  <#assign resultList=result?eval />
  <#list resultList as item>
  <#if item.protoType == "doc">
  		<div class="item" id="item-1">
                  <div class="topicdivision-content">
					<div class="topicdivision-left">
						 <div class="title">
							 <a target="_blank" href="${request.getContextPath()}/docs/getDocsDetail?id=${item.data_id}"  title="${item.title}" style='margin-left: -6px;' >【文档】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="${item.description}">
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
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt='' width='52' height='65'></div>
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
							 <a target="_blank" href="${request.getContextPath()}/books/getBookDetail?id=${item.data_id}"  title="${item.title}" style='margin-left: -6px;'>【书籍】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="${item.description}">
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
									 <#if item.replySum??>${item.replySum}<#else>0</#if>人推荐
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
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt='' width='52' height='65'></div>
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
							 <a target="_blank" href="${request.getContextPath()}/article/getArticleDetail?id=${item.data_id}"  title="${item.title}" style='margin-left: -6px;'>【文章】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="${item.description}">
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
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''  width='52' height='65'></div>
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
							 <a target="_blank" href="${request.getContextPath()}/courses/getCourseDetail?id=${item.data_id}"  title="${item.title}" style='margin-left: -6px;'>【课程】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="${item.description}">
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
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''  width='52' height='65'></div>
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
							 <a target="_blank" href="${request.getContextPath()}/sites/getSiteDetail?id=${item.data_id}" title="${item.title}" style='margin-left: -6px;'>【站点】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="${item.description}">
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
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''  width='52' height='65'></div>
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
							 <a target="_blank" href="${request.getContextPath()}/topics/getTopicsDetail?topicId=${item.data_id}"  title="${item.title}" style='margin-left: -6px;'>【话题】<#if item.title??>${item.title}<#else>0</#if></a>
						 </div>
						 <div class="newsinfo" title="${item.title}">
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
											 <div class="pict"><img src='<#if item.imgUrl?? && item.imgUrl?length gt 5>${item.imgUrl}<#else>${request.getContextPath()}/image/search_default.jpg</#if>' alt=''  width='52' height='65'></div>
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
                </div>
  </#if>          
             
</#list>

              
          </div>
		  <#else>
		  <div class='resultList'>
			     <div class='emptyList'>
				   <div class='empty-tip'>
				       <div class="empty-img">
					      <img src="/51jobplusCore/image/angry.png" alt="">
					   </div>
					   <div class="empty-info">
							对不起，没有找到满足搜索条件的信息<br>
							你可以修改你的搜索条件
						</div>
				   </div>
				   <div class="cline"></div>
				 </div>
			  </div>
		  </#if>
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
                            <div id="searchpaging" class="page" onclick="href=#top"></div>
                        </div>
                    </div>
                </div>
            </div>
      </#if>
        
   </div>
   </div>
   <div class='pj-searches-right'>
	   <div class="share-plaza">
	            <a class="share-icon bg-index" href="/51jobplusCore/sharein/searchuploadFile"></a>
	            <a target="_blank" href="javascript:void(0)" class="text">
				 文档---话题---书籍---课程---文章---站点
				</a>
	   </div>
   </div>
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