<!DOCTYPE html>
<html>
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
            书籍专区
    </title>
    <meta name="viewport" content="width=1230"/> 
    <meta name="apple-mobile-web-app-capable" content="yes" /> 
    <link rel="stylesheet" type="text/css" href="/css/pj_searchres.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_framework.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_index.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_simplePagination.css">
  </head>
  
  <body id="topdivisiontop">
    <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
    <div class='division-banner'>
	  <span>JobPlus知识库>书籍专区</span>
	</div>
    <input type="hidden" id="preSharedType" value="${preSharedType}"/>
	<div class='pj-topics-content' id='pj-topics-content'> 
	    <div class='pj-topics-left'>
	        <div id="books" class="searches">
			   <div class="searchForm">
				<span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
				<input type="button" id="books_button" class="search_icon"  value="书籍搜索" onclick="javascript:reloadPage();" style="width:140px">
				<input type="text" id="books_input" class="search_input ui-autocomplete-input" name="Condition" style="width:552px" tabindex="1" maxlength="64" autocomplete="off" placeholder="JobPlus海量知识库"  value="${preCondition}" onkeypress="if(event.keyCode==13){reloadPage();};"  >
				<input type="hidden" name="fromSearch" id="frombooks" value="true">
				<input type="botton" name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="books-form-submit" value="分类搜索" onclick="javascript:reloadPage();" style="width:140px">
			  </div>
		    </div>
		    <div id="topic-division-container" class='topic-division-container'>
			  <div class="content">
				<div class="positionHead">
					<ul id="filterBox" class="filter-wrapper">
						<div class="details" id="filterCollapse">
						    <li class="first">
								<dl class="title">知识分类:</dl>
								<span <#if preSharedType==''>class="active"</#if> data-index="0">所有</span>
								<dl class='select-1'>
									<#list typeConfigs as type>
									 <#if  (type_index <=11)>
										<#if preSharedType==type.typeid?c>
											<span data-index="${type.typeid}" class="active">${type.typename}</span>
										<#else>
											<span data-index="${type.typeid}">${type.typename}</span>
										</#if>
									  </#if>
									</#list>
									<div class="clear"></div>
								</dl>
								<dl class='select-1  pt5'>
									<#list typeConfigs as type>
									 <#if  (type_index >11)>
										<#if preSharedType==type.typeid?c>
											<span data-index="${type.typeid}" class="active">${type.typename}</span>
										<#else>
											<span data-index="${type.typeid}">${type.typename}</span>
										</#if>
									  </#if>
									</#list>
									<div class="clear"></div>
								</dl>
							</li>
							
							
							<li class="last">
							<dl class='topic-division-title'>
								<dl class="title">
									书籍分类:
								</dl>
								<span <#if sortType!=2>class="active"</#if> data-index="1">热门书籍</span>
								<span <#if sortType==2>class="active"</#if> data-index="2">最新书籍</span>				
							 </dl>
							</li>
							
						</div>
					</ul>
				</div>
			  </div>
			
			  <div class="all-news-list" id="all-news-list">
				  <div class="alllist" id="allnews_all">
				    <#if reCount gt 0>
					   <div class="items_area">
						 <#--查询结果集-->
  						<#assign resultList=result?eval />
							 <#list resultList as data>
								 <div class="item">
									 <div class='bookdivision-content'>
										 <div class='bookdivision-left'>
											 <div class="title">
												 <a target="_blank" href="javascript:void(0);" onclick="toHref('tbl_books',${data.data_id})" title="${data.title}">${data.title}</a>
											 </div>
											 <div class="newsinfo" <#if data.description??>title="${data.description}"</#if>>
												 <a target="_blank" href="javascript:void(0);"  onclick="toHref('tbl_books',${data.data_id})"><#if data.description??>${data.description}</#if></a>
											 </div>
											 <div class="tool">
												 <div class="pj-meta-panel">
													 <a  class="answer-date-link pj-meta-item" target="_self" href="javascript:void(0);">发布于&nbsp;${data.updateTime}</a>

													 <a href="javascript:void(0);" class="pj-meta-item zu-autohide js-recommend">
														 <i class="z-icon-comment"></i><#if data.replySum??>${data.replySum}<#else>0</#if>人推荐
													 </a>

													 <a href="javascript:void(0);" class="pj-meta-item zu-autohide js-collect">
														 <i class="z-icon-collect"></i><#if data.collectSum??>${data.collectSum}<#else>0</#if>人收藏
													 </a>
												 </div>
											 </div>
										 </div>
										 <div class='bookdivision-right'>
											 <div class="picno">
												 <div class="pic">
													 <div class="picx">
														 <#if data.imgUrl?length gt 5>
															 <div class="pict">
															   <img src='${data.imgUrl}' alt=''>
															 </div>
														 <#else>
															 <div class="pict">
																<img src='/image/default/65.jpg' alt=''>
															 </div>
														 </#if>
													 </div>
												 </div>
											 </div>
										 </div>
									 </div>
								 </div>
							 </#list>
						 
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
			<#if (reCount)??>
				<#if (reCount gt rows)>
					<div class='page-inner'>
						<div class="ui-pager pager-center">
							<div class='pager'>
								<div class="pager-inner">
									<div id="bookdivisionpaging" class="page"></div>
								</div>
							</div>
						</div>
					</div>
				</#if>
			</#if>
		   </div>
		</div>
        <div class='pj-topics-right'>
		  <div class="share-plaza">
	            <a class="share-icon bg-index" target="_self" href="/sharein/searchuploadFile?type=2"></a>
	            <a target="_self" href="javascript:void(0)" class="text">
				   文档---话题---书籍---课程---文章---站点
				</a>
	      </div>
	      <div class='pj_jsonp ad_exposure'>
	        <img src='/image/ad_exposure_11.jpg' alt='' width='310' height='278'>
	      </div>
	     <div class='pj_jsonp ad_exposure'>
	        <img src='/image/ad_exposure_12.jpg' alt='' width='310' height='278'>
	     </div>
		</div>
    </div>
  
   
    <div class='pagetemplate'></div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
    <a id="topdivisionbacktop" title="回到顶部" href="#topdivisiontop" class='back-to-top' style="bottom: 300px; display: none;"></a>
	<#include "/mydocs/commonTemplate/pmorsmgjs/pmorsmg.ftl"/>
    <script type='text/javascript' src='/scripts/jquery.simplePagination.js'></script>
	<script type="text/javascript" src="/scripts/pj_bookdivision.js"></script>
    <script type="text/javascript">
        <#if (reCount) gt rows>
            $(function () {
                $("#bookdivisionpaging").pagination({
                    items: ${reCount},
                    itemsOnPage:${rows},
                    cssStyle: 'light-theme',
                    moduleType: 'booksearch'
                });
            });
        </#if>
    </script>
  </body>

</html>