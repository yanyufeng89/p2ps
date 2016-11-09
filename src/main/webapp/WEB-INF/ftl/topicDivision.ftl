<!DOCTYPE html>
<html>
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
            话题专区-JobPlus
    </title>
    <meta name="description" content="JobPlus网是国内领先的企业知识库公共平台,分享知识、经验和见解,话题分类包括热门话题,最新话题,等待回答,精彩回答,JobPlus为7亿职场人员提供优质知识交流分享社区,JobPlus是你最值得信赖的终身学习伙伴。">
    <meta name="keywords" content="Jobplus,知识分享,创新问答,创业问答,IT问答,互联网问答,咨询问答,财务问答,工业技术问答,工程问答,市场管理问答,销售管理问答,供应链问答,生产管理问答,设计问答,创意问答,翻译问答,商标专利问答,新媒体问答,影视问答,知识问答,热门话题,最新话题,等待回答,精彩回答">
    
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"> 
    <link rel="stylesheet" type="text/css" href="/css/pj_searchres.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_framework.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/pj_index.css">
    <link rel="stylesheet" type="text/css" href="/css/pj_simplePagination.css">
  </head>
  
  <body id="topdivisiontop">
    <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
    <div class='division-banner'>
	  <span>JobPlus知识库>话题专区</span>
	</div>
    <input type="hidden" id="preSharedType" value="${preSharedType}"/>
	<div class='pj-topics-content' id='pj-topics-content'> 
	    <div class='pj-topics-left'>
		    <div id="topics" class="searches">
			   <div class="searchForm">
				<span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
				<input type="button" id="topics_button" class="search_icon"  value="话题搜索" onclick="javascript:reloadPage();" style="width:140px">
				<input type="text" id="topics_input" class="search_input ui-autocomplete-input" name="Condition" style="width:552px" tabindex="1" maxlength="64" autocomplete="off" placeholder="JobPlus海量知识库"  value="${preCondition}" onkeypress="if(event.keyCode==13){reloadPage();};"  >
				<input type="hidden" name="fromSearch" id="fromtopics" value="true">
				<input type="botton" name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="topics-form-submit" value="分类搜索" onclick="javascript:reloadPage();" style="width:140px">
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
									话题分类:
								</dl>
								<span <#if sortType!=2 && sortType!=3 && sortType!=4>class="active"</#if> data-index="1">热门话题</span>
								<span <#if sortType==2>class="active"</#if> data-index="2">最新话题</span>
								<span <#if sortType==3>class="active"</#if> data-index="3">等待回答</span>
								<span <#if sortType==4>class="active"</#if> data-index="4">精彩话题</span>
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
						 
							
				       </div>
				  <#else>
				     <div class='resultList'>
						 <div class='emptyList'>
						   <div class='empty-tip'>
							   <div class="empty-img">
								  <img src="/image/angry.png" alt="提示"class="lazy">
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
									<div id="sharetopicpaging" class="page"></div>
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
	            <a class="share-icon bg-index" target="_self" href="/sharein/searchuploadFile?type=1"></a>
	            <a target="_self" href="javascript:void(0)" class="text">
				   文档---话题---书籍---课程---文章---站点
				</a>
	      </div>
	      <div class='pj_jsonp ad_exposure'>
	        <img src='/image/ad_exposure_3.jpg' alt='广告' width='308' height='246' class="lazy">
	        <div class='advertising-direction'>广告</div>
	      </div>
	     <div class='pj_jsonp ad_exposure'>
	        <img src='/image/ad_exposure_4.jpg' alt='广告' width='308' height='246' class="lazy">
	        <div class='advertising-direction'>广告</div>
	     </div>
		</div>
    </div>
  
   
    <div class='pagetemplate'></div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
    <a id="topdivisionbacktop" title="回到顶部" href="#topdivisiontop" class='back-to-top' style="bottom: 300px; display: none;"></a>
	<#include "/mydocs/commonTemplate/pmorsmgjs/pmorsmg.ftl"/>
	<script type='text/javascript'>
		    var res=eval(${result});
			var datamodel={
				result:res,
				reCount:${reCount}
			}
			//加载模板
			$('.pagetemplate').setTemplateURL(projectName+'topicSearchTemplate.html');
			$('.pagetemplate').processTemplate(datamodel);
			$('.items_area').append($('.pagetemplate').html());
			$('.pagetemplate').empty();
			$('.newsinfo a').each(function(){
			    $(this).text(autoAddEllipsis($(this).text(),200));
			})
    </script>
    <script type='text/javascript' src='/scripts/jquery.simplePagination.js'></script>
	<script type="text/javascript" src="/scripts/pj_topicdivision.js"></script>
    <script type="text/javascript">
        <#if (reCount) gt rows>
            $(function () {
                $("#sharetopicpaging").pagination({
                     items: ${reCount},
                    itemsOnPage:${rows},
                    cssStyle: 'light-theme',
                    moduleType: 'topicsearch'
                });
            });
        </#if>
    </script>
  </body>

</html>