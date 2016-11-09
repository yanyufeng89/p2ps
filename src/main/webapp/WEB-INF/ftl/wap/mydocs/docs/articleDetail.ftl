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
  </head>
  <body>
    <div class="wrap bc">
      <#include "/mydocs/commonTemplate/topandtail/waptop.ftl"/>
        <div class="main articleMain">
           <input type='hidden' name='articleid' value='${record.id}'>
           <div class="plus-main-content-inner">
				  <#if (record)??>
						 <div class='titlename'>${record.title}</div>
						 <div class='authorInfo'>
							<a href="javascript:void(0)"  class='author-link' target="_blank"  data-userid="${record.objCreator.userid}">
							   <#if (record.objCreator.headicon)??>
									<img class="uname lazy" src="${record.objCreator.headicon}" alt="个人头像" data-userid="${record.objCreator.userid}" >
								<#else>
									<img class="uname lazy" src="/image/1b48b5a75c71597_100x100.jpg" alt="个人头像" data-userid="${record.objCreator.userid}">
								</#if>${record.objCreator.username}
							</a>
							<span class='meta-top'>${record.updatetime?string("yyyy-MM-dd")}</span>
						 </div>
					     <#if (record.intro)??>
					        <div class='mainContent'>
						       ${record.intro}
					        </div>                            
					      </#if>
				  </#if>
                  <div class="statistical-data">
					   <span><i class="z-icon-thank"></i>${record.likesum}人赞</span>
                       <span><i class="z-icon-preview"></i>${record.readsum}人阅读</span>
                       <span><i class="z-icon-playtour"></i>${record.supportCount}人打赏</span> 						   
				  </div>
           </div>
		</div>
		<div class='relatedcontent'>
		<div class='userrecommend'> 用户推荐${record.recommendsum}</div>
		<div class='detail'>
		  <div class='detail-list'>
		  <#if record.commentList.list?size lte 0>
		     <p class='uncomment'>暂无推荐，你也可以发布推荐哦:) </p>
		  </#if>
		  <#if (record.commentList)??>
		     <#list  record.commentList.list as booklist>
		       <div class='item'>
		         <div class='media-left'>
		           <a class='uhead' href='javascript:void(0)' target='_blank' data-userid='${booklist.userid}'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname lazy' src="${booklist.userHeadIcon}" alt="个人头像"  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname lazy' src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		             <#if (booklist.commentbyName)??>
			                <a href='javascript:void(0)' class="uname" data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
			                  <span class="desc">&nbsp;回复&nbsp;</span>
			                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
			                <a href='javascript:void(0)' target='_blank'>${booklist.commentbyName}</a>
			                </span>
		             <#else>
			                <a href='javascript:void(0)' class='uname' data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
		             </#if>
		           </a>
		         </div>
		         <div class='media-right'>
		           <div class="reason">${booklist.recommend?html}</div>
		           <div class="operate">
		             <a itemprop="url" class="answer-date-link meta-item" target="_self" href="javascript:;">
		               <#if (booklist.createtime?string("yyyy-MM-dd"))??>
		                  ${booklist.createtime?string("yyyy-MM-dd")}
		               </#if>
					 </a>
		           </div>
		         </div>
		       </div>
		     </#list>
		  </#if>
		 </div>
		  
		</div>
		<#if (record.commentList.last gt 1)>
			   <a data-pageno='1' data-sumpage='${record.commentList.last}' class="zg-btn-white zu-button-more loadmore">更多</a>
	    </#if>
	    </div>
      
	</div>
	 <div class='pagetemplate'></div>
	 <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
	 <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap.js"></script>


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
  </body>

</html>
