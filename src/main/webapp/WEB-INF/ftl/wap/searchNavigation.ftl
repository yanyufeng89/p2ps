<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>
       ${title}搜索结果-JobPlus
    </title> 
	<link rel='stylesheet' type='text/css' href='/css/pj_wapsearchres.css'>
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
    <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
	 <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap_searchResult.js"></script>
     <script type="text/javascript" src="/scripts/pj_constant.js"></script>
      <script type="text/javascript">
	         var phoneWidth =  parseInt(window.screen.width);
	         var phoneScale = phoneWidth/640;
	         var ua = navigator.userAgent;
	         if (/Android (\d+\.\d+)/.test(ua)){
	                   var version = parseFloat(RegExp.$1);
	                   if(version>2.3){
	                            document.write('<meta name="viewport" content="width=640, height=device-height minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
	                   }else{
	                            document.write('<meta name="viewport" content="width=640, height=device-height target-densitydpi=device-dpi">');
	                   }
	         } else {
	                   document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
	         }
	 </script>
  </head>
  <body>
     <div class="wrap bc">
		<#include "/mydocs/commonTemplate/topandtail/waptop.ftl"/>
		<div class='search-box' style='display:none'>
		    <input type="text" id="searchres_input" class="search_input ui-autocomplete-input" name="Condition" placeholder="JobPlus海量知识库" value="${preCondition}"  onkeypress="if(event.keyCode==13){reloadPage();};"  style="color: rgb(153, 153, 153);"  tabindex="1" maxlength="64" autocomplete="off"  value="">
            <botton  name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索"></botton>
		</div>
		<div class='search-classify clearfix' id='search-classify'>
		   <ul>
		     <li data-objtype="" <#if (preProtoType == 0)||(preProtoType == "")>class="active"</#if>>全部</li>
		     <li data-objtype="1" <#if preProtoType?? && preProtoType == "1">class="active"</#if>>文档</li>
		     <li data-objtype="5" <#if preProtoType?? && preProtoType == "5">class="active"</#if>>话题</li>
		     <li data-objtype="6" <#if preProtoType?? && preProtoType == "6">class="active"</#if>>书籍</li>
		     <li data-objtype="3" <#if preProtoType?? && preProtoType == "3">class="active"</#if>>课程</li>
		     <li data-objtype="2" <#if preProtoType?? && preProtoType == "2">class="active"</#if>>文章</li>
		     <li data-objtype="4" <#if preProtoType?? && preProtoType == "4">class="active"</#if>>站点</li>
		   </ul>
		</div>
		<div class='search-main'>
		   <#if (reCount) gt 0>
		   
		   <#else>
		   <div class='resultList'>
			     <div class='emptyList'>
				   <div class='empty-tip'>
				       <div class="empty-img">
					      <img src="/image/wap-angry.png" alt="提示" class="lazy">
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
	   <#--加载更多-->
	   <#if (reCount?number/pageSize?number)?ceiling gt 1>
		  <a data-pageno='1' data-sumpage='${(reCount?number/pageSize?number)?ceiling}' class="zg-btn-white zu-button-more search-loadmore" data-type='1'>更多</a>
       </#if>
     </div>
	 <div class='pagetemplate'></div>
	 
     <script type='text/javascript'>
			    var res=eval(${result});
				var datamodel={
					result:res,
				}
				//加载模板
				$('.pagetemplate').setTemplateURL('/searchWapTemplate.html');
				$('.pagetemplate').processTemplate(datamodel);
				$('.search-main').append($('.pagetemplate').html());
				$('.pagetemplate').empty();
				$('.item-content a').each(function(){
			      $(this).text(autoAddEllipsis($(this).text(),80));
			    })
     </script>
	
  </body>

</html>
