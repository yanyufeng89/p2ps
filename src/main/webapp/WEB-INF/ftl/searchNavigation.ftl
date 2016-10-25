<!DOCTYPE html>
<html>
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
           导航搜索
    </title>
    <meta name="viewport" content="width=1230"/> 
    <meta name="apple-mobile-web-app-capable" content="yes" /> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/pj_searchres.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_framework.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_index.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_simplePagination.css">
  </head>
  
  <body  id="searchtop">
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
		 <form id="searchresForm" class="searchForm" name="searchForm" action="/search/" method="get">
		 -->
		   <div class="searchForm">
			<span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
			<#-- 
			<input type="submit" id="searchres_button" class="search_icon" value="知识搜索">
			-->
			<input type="button" id="searchres_button" class="search_icon" onclick="javascript:reloadPage();" value="知识搜索" style='width:140px'>
			<input type="text" id="searchres_input" class="search_input ui-autocomplete-input" name="Condition"  style='width:552px' tabindex="1" maxlength="64" autocomplete="off" 
			placeholder="JobPlus海量知识库" style="color: rgb(153, 153, 153);" value="${preCondition}" onkeypress="if(event.keyCode==13){reloadPage();};" >
			<input type="hidden" name="fromSearch" id="fromSearches" value="true">
			<input type="botton" name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索" onclick="javascript:reloadPage();" style='width:140px'>
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
					<span data-objtype="1" <#if preProtoType?? && preProtoType == "1">class="active"</#if>>文档</span>
	                <span data-objtype="5" <#if preProtoType?? && preProtoType == "5">class="active"</#if>>话题</span>
	                <span data-objtype="6" <#if preProtoType?? && preProtoType == "6">class="active"</#if>>书籍</span>
	                <span data-objtype="3" <#if preProtoType?? && preProtoType == "3">class="active"</#if>>课程</span>
	                <span data-objtype="2" <#if preProtoType?? && preProtoType == "2">class="active"</#if>>文章</span>
	                <span data-objtype="4" <#if preProtoType?? && preProtoType == "4">class="active"</#if>>站点</span>
              </li>
            </div>
          </ul>
        </div>
      </div>
      <div class="all-news-list" id="all-search-list">
          <div class="alllist" id="allsearch_all">
		    <#if (reCount) gt 0>
             <div class="items_area">
              
             </div>
            
		  <#else>
		  <div class='resultList'>
			     <div class='emptyList'>
				   <div class='empty-tip'>
				       <div class="empty-img">
					      <img src="/image/angry.png" alt="">
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
	            <a class="share-icon bg-index" target="_self" href="/sharein/searchuploadFile"></a>
	            <a target="_self" href="javascript:void(0)" class="text">
				 文档---话题---书籍---课程---文章---站点
				</a>
	   </div>
	   <div class='pj_jsonp ad_exposure'>
	      <img src='/image/ad_exposure_1.jpg' alt='' width='310' height='278'>
	   </div>
	   <div class='pj_jsonp ad_exposure'>
	      <img src='/image/ad_exposure_2.jpg' alt='' width='310' height='278'>
	   </div>
   </div>
  </div>
    <div class='pagetemplate'></div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
    <a id="searchbacktop" title="回到顶部" href="#searchtop" class='back-to-top' style="bottom: 300px; display: none;"></a>
	<#include "/mydocs/commonTemplate/pmorsmgjs/pmorsmg.ftl"/> 
	<script type='text/javascript'>
		      var res=eval(${result});
			  var datamodel={
				result:res,
			  }
				//加载模板
				$('.pagetemplate').setTemplateURL(projectName+'searchTemplate.html');
				$('.pagetemplate').processTemplate(datamodel);
				$('#allsearch_all  .items_area').append($('.pagetemplate').html());
				$('.pagetemplate').empty();
    </script>
	<script type="text/javascript" src="/scripts/jquery.media.js"></script>
	<script type="text/javascript" src="/scripts/pj_searchResult.js"></script>
	<script type='text/javascript' src='/scripts/jquery.simplePagination.js'></script>
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