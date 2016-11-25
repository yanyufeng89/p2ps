<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>
       ${record.coursesname}-JobPlus
    </title> 
	<link rel='stylesheet' type='text/css' href='/css/pj_wapdetail.css'>
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
     <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
	 <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap_course.js"></script>
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
		    <input type="text" id="searchres_input" class="search_input ui-autocomplete-input" placeholder="JobPlus海量知识库" name="Condition"  tabindex="1" maxlength="64" autocomplete="off"  value="">
            <botton name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索"></botton>
		</div>
        <div class="main bookMain">
           <input type='hidden' name='courseid' value='${record.id}'>
           <input type='hidden' name='coursecontent' value='${record.intro?trim}'>
           <input type='hidden' name='coursename' value='${record.coursesname}'>
           <input type='hidden' name='courseCreatePerson' value='${record.userid}'>
           <div class="plus-main-content-inner course-content">
				  <#if (record)??>
						<div class='main-left'>
	                           <#if (record.coursesimg)??>
	                             <img src="${record.coursesimg}" class="book-logo lazy" alt="课程图标" height='220' width='166'>
	                            <#else>
	                              <img src="/image/d-course.jpg" class="book-logo lazy" alt="课程图标" height='220' width='166'>
	                           </#if>
	                     </div>
	                     
	                     <div class='main-right'>
                             <div class='headline control-display'>
	                             [课程]${record.coursesname?html}
                             </div>
                             <div class='author control-display'>
                                <span class='tip'>链接:</span>
                                <a href='${record.coursesurl}' target='_blank' id='courseurl' rel='nofollow' style='color:#0867c5;'>${record.coursesurl} </a>
                             </div>
                             <div class='coursedes course-height' id='coursebrief'>
                                 <span class='tip'>简介:</span>
                                 <span class='brief-content'>
                                    ${record.intro?trim}
                                 </span>
                                 <div class="slideup slide" style="display: none;font-size: 2.2rem;"><span><i class="z-icon-up"></i>收起</span></div>
                             </div>
	                     </div>
				  </#if>
           </div>
		   <div class='related-data'>
		      <span class='cousername'><a href='/myHome/getHomePage/${record.userid}' target='_blank'>${record.objCreator.username}</a></span>
			  <span>${record.createtime?string("yyyy-MM-dd")}</span>
			  <span><i class="z-icon-preview"></i>${record.readsum}人学习</span>
			  <span><i class="z-icon-thank"></i>${record.likesum}人点赞</span>
		   </div>
		</div>
		<div class='relatedcontent'>
		<div class='userrecommend' id='course-commcount' data-num='${record.recommendsum}'> 用户推荐(${record.recommendsum})</div>
		<div class='detail'>
		  <div class='detail-list'>
		  <#if record.commentList.list?size lte 0>
		     <p class='uncomment'>暂无推荐，你也可以发布推荐哦:) </p>
		  </#if>
		  <#if (record.commentList)??>
		     <#list  record.commentList.list as booklist>
		       <div class='item'>
		         <div class='media-left'>
		           <a class='uhead' href='/myHome/getHomePage/${booklist.userid}' target='_blank' data-userid='${booklist.userid}'>
		             <#if (booklist.userHeadIcon)??>
		               <img class='uname lazy' src="${booklist.userHeadIcon}" alt="个人头像"  data-userid='${booklist.userid}' data-moduletype='1'>
		             <#else>
		               <img class='uname lazy' src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" data-userid='${booklist.userid}' data-moduletype='1'>
		             </#if>
		             <#if (booklist.commentbyName)??>
			                <a href='/myHome/getHomePage/${booklist.userid}' class="uname" data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
			                  <span class="desc">&nbsp;回复&nbsp;</span>
			                <span class="uname"  data-userid='${booklist.commentby}' data-moduletype='1'>
			                <a href='/myHome/getHomePage/${booklist.commentby}' target='_blank'>${booklist.commentbyName}</a>
			                </span>
		             <#else>
			                <a href='/myHome/getHomePage/${booklist.userid}' class='uname' data-userid='${booklist.userid}' target='_blank'>${booklist.userName}</a>
		             </#if>
		           </a>
		         </div>
		         <div class='media-right'>
		           <div class="reason">${booklist.recommend?html}</div>
		           <div class="operate">
		             <a  class="answer-date-link meta-item"  href="javascript:;">
		               <#if (booklist.createtime?string("yyyy-MM-dd"))??>
		                  ${booklist.createtime?string("yyyy-MM-dd")}
		               </#if>
					 </a>
					 <a href="javascript:;" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox" data-relationid='${booklist.id}' data-recommend='${booklist.userid}' data-recommendname='${booklist.userName}'> 
						<i class="z-icon-comment"></i>评论
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
        <#--判断是否登录-->
	    <#if (Session.user)??>
		<div class="mycomment halving-line">
			  <a href="/myHome/getHomePage/${Session.user.userid}" data-userid="2" target="_blank">
				 <#if (Session.user.headicon)??>
				   <img src="${Session.user.headicon}" alt="个人头像" class='zm-list-avatar lazy' data-userid='${Session.user.userid}' data-moduletype='1'>
				 <#else>
				   <img src='/image/1b48b5a75c71597_100x100.jpg' alt="个人头像" class='zm-list-avatar lazy' data-userid='${Session.user.userid}' data-moduletype='1'>
				 </#if>
				 <span class="mycommentinfo">推荐语</span>
			  </a>
        </div>
        <div class="publishbook">
	       <textarea title="在这里输入回答" class="commentcontent" placeholder="在这里输入推荐语..." ></textarea>	
	    </div>
	    <div class="pj-command clearfix">
			   <span class="zg-right">
			      <b class="ic ic-msg" style="background-position: -47px -144px;display:none"></b>
				  <span class="item-msg-content" style='display:none'>文字超出最大限制</span>
				  <a class="submit-button zg-btn-blue" name="savebook" href="javascript:void(0)">发布</a>
			   </span>
	    </div>
	    <#else>
	    <div class="col-md-6 col-md-offset-3 login-after-comments">
	            <span class="hidden-xs">登录后才能发布评论</span><br>
	            <span class="comments-login-register hidden-xs loginprompt-null"><a href='javascript:void(0);' onclick="toLogin();">登录</a> | <a  target='_blank' href='/registration.html'>立即注册</a> </span>
	    </div>
	    </#if>
	</div>
	 <div class='pagetemplate'></div>
	
	 
  </body>

</html>
