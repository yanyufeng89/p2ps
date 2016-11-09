<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>
               ${record.title}-JobPlus
    </title> 
    <meta name="viewport" content="width=1230">
    
	<link rel='stylesheet' type='text/css' href='/css/pj_wapdetail.css'>
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
  </head>
  <body>
    <div class="wrap bc">
      <#include "/mydocs/commonTemplate/topandtail/waptop.ftl"/>
        <div class="main siteMain">
           <input type='hidden' name='siteid' value='${record.id}'>
           <input type='hidden' name='sitescontent' value='${record.intro?trim}'>
           <div class="plus-main-content-inner">
				  <#if (record)??>
						 <div class='titlename'>[站点]${record.title?html}</div>
						 <div class='link'>
							<span class="tip">链接:</span>
							<a href='${record.siteurl}' target='_blank'  id='siteurl' style='color:#0867c5;'>${record.siteurl}</a>
						 </div>
					     <#if (record.intro)??>
					        <div class='description tool-height'>
					           <span class="tip">简介:</span>
						       ${record.intro?html}
                                <span class="slidedown slide" style="display: block;"><label>...&nbsp;&nbsp;&nbsp;</label>展示全部</span>
                                <div class="slideup slide" style="display: none;"><span>收起</span></div>
					        </div>                            
					     </#if>
				  </#if>
                  <div class="statistical-data">
                       <span><i class="z-icon-preview"></i>${record.readsum}人浏览</span>		
					   <span><i class="z-icon-thank"></i>${record.likesum}人赞</span>		   
				  </div>
           </div>
		  </div>
		<div class='relatedcontent'>
		<div class='userrecommend'>用户推荐${record.recommendsum}</div>
		<div class='detail'>
		  <div class='detail-list'>
		  <#if record.commentList.list?size lte 0>
		     <p class='uncomment'>暂无推荐，你也可以发布推荐哦:) </p>
		  </#if>
		  <#if (record.commentList)??>
		     <#list  record.commentList.list as booklist>
		       <div class='item'>
		         <div class='media-left'>
		           <a class='uhead' href='/myHome/getHomePage?userid=${booklist.userid}' target='_blank' data-userid='${booklist.userid}'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname lazy' src="${booklist.userHeadIcon}" alt="个人头像"  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname lazy' src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		             <#if (booklist.commentbyName)??>
			                <a href='/myHome/getHomePage?userid=${booklist.userid}' class="uname" data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
			                  <span class="desc">&nbsp;回复&nbsp;</span>
			                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
			                  <a href='/myHome/getHomePage?userid=${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
			                </span>
		             <#else>
			                <a href='/myHome/getHomePage?userid=${booklist.userid}' class='uname' data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
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
