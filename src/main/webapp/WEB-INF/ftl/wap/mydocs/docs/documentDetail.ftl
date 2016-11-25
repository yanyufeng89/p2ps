<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>
        ${record.title}-JobPlus
    </title> 
	<link rel='stylesheet' type='text/css' href='/css/pj_wapdetail.css'>
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
     <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
	 <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap_doc.js"></script>
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
  <body  style='background:#fff'>
    <div class="wrap bc">
      <#include "/mydocs/commonTemplate/topandtail/waptop.ftl"/>
        <div class='search-box' style='display:none'>
		    <input type="text" id="searchres_input" class="search_input ui-autocomplete-input" name="Condition"  placeholder="JobPlus海量知识库"  tabindex="1" maxlength="64" autocomplete="off"  value="">
            <botton name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索"></botton>
		</div>
        <div class="docMain">
           <input type='hidden' name='docid' value='${record.id}'>
           <input type='hidden' name='docname' value='${record.title}'>
           <input type='hidden' name='docCreatePerson' value='${record.userid}'>
           <input type='hidden' name='docsuffix' value='${record.docsuffix?lower_case}'>
           <input type='hidden' name='docurl' value='${record.readurl}'>
           <div class="plus-main-content-inner doc-content">
			  <#if (record)??>
			   <div class='infor-right'>
					<span class='headline'>${record.title}</span>
			   </div>
			  </#if>
			  <div class='authorInfo doc-value'>
		      <#-- 匿名用户  -->
		     <#if record.ispublic==2>
			   <a target='_blank'>
			    <img class="uname lazy" src="/image/1b48b5a75c71597_100x100.jpg" alt="个人头像"  data-moduletype="1">
			   </a>
			   <span class='author-link'>
				 <a  target='_blank'>匿名用户</a>
			   </span>
		      <#else>
			 <#if (record)??>
			   <a href='/myHome/getHomePage/${record.objCreator.userid}' target='_blank'>
			   <#--<#if (record.objCreator.headicon)??>
			    <img class="uname lazy" src="${record.objCreator.headicon}" alt=""个人头像 data-userid="${record.objCreator.userid}" data-moduletype="1">
			   <#else>
			    <img class="uname lazy" src="/image/1b48b5a75c71597_100x100.jpg" alt="个人头像" data-userid="${record.objCreator.userid}" data-moduletype="1">
			   </#if>-->
			   </a>
			   <span class='author-link' data-userid="${record.objCreator.userid}">
				   <a href='/myHome/getHomePage/${record.objCreator.userid}' target='_blank'>${record.objCreator.username}</a>
			   </span>
		    </#if>
			<span class='createtime'>${record.createtime?string("yyyy-MM-dd")}</span>
			<span class='likesum'>${record.likesum}人赞</span>
			<span class='readsum'>${record.readsum}人浏览</span>
			<span class='collectsum'>${record.collectsum}人收藏</span>

		  </#if>
		</div>
        </div>
        <div class='doc-description'>
		  ${record.firstPageText}
        </div>
        <div class='doc-downvalue'>
           <#if (Session.account)??>
				  <button  class="follow-button zg-follow zg-btn-green docfollow" id="docfollow" data-downvalue="${record.downvalue}" data-sumvalue='${Session.account.points}' data-isdown='<#if (isdown)??>${isdown}</#if>'>
					<i class="z-icon-downvalue"></i>下载      
				  </button>
				  <#else>
                  <button  class="follow-button zg-follow zg-btn-green docfollow" onclick="toLogin();" data-downvalue="${record.downvalue}" data-sumvalue='${Session.account.points}' data-isdown='<#if (isdown)??>${isdown}</#if>'>
                    <i class="z-icon-downvalue"></i>下载      
                  </button>
           </#if>
        </div>	  
		</div>		
       
	</div>
	 <div class='pagetemplate'></div>
	
	
  </body>

</html>
