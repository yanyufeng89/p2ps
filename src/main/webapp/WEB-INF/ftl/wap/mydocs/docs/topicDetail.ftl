<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height, user-scalable=no,initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>
       ${topicsDetail.title}-JobPlus
    </title> 
	<link rel='stylesheet' type='text/css' href='/css/pj_wapdetail.css'>
    <link rel='stylesheet' type='text/css' href='/css/pj_wapbase.css'>
    <div id='wx_pic' style='margin:0 auto;display:none;'>
          <img src='/image/logo_1.jpg' />
    </div>
     <script type="text/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
	 <script type="text/javascript" src="/scripts/jquery-jtemplates.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap.js"></script>
     <script type="text/javascript" src="/scripts/pj_wap_topic.js"></script>
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
		    <input type="text" id="searchres_input" class="search_input ui-autocomplete-input" name="Condition" placeholder="JobPlus海量知识库" tabindex="1" maxlength="64" autocomplete="off"  value="">
            <botton name="search-submit" class="bdcs-search-form-submit bdcs-search-form-submit-magnifier" id="searches-form-submit" value="分类搜索"></botton>
		</div>
        <div class="main articleMain">
           <input type='hidden' name='topicid' value='${topicsDetail.id}'>
           <input type='hidden' name='titlename' value='${topicsDetail.title}'>
           <input type='hidden' name='createperson' value='${topicsDetail.createperson}'>
           <div class="plus-main-content-inner">
				 <div class='titlename' style='color:#0867c5'>${topicsDetail.title}</div>
				 <#if (topicsDetail.topicsclass)?? &&topicsDetail.topicsclass?length gt 2>
					 <div class="zm-tag-editor zg-section">
						<div class="zm-tag-editor-labels zg-clear">
							<#list topicsDetail.topicsclass?split(',') as detaillist>
							   <#assign p=detaillist?split(':')/>
							   <a href="javascript:void(0);" class='zm-item-tag' href='' data-topicid='${p[0]}'>${p[1]}</a>
							</#list>
						</div>
					  </div>
				 </#if>
			     <#if (topicsDetail.content)??&&topicsDetail.content?length gt 0>
			        <div class='mainContent'>
				       <#assign topiccontent=topicsDetail.content?substring(0,topicsDetail.content?length-4)/>
	                   ${topiccontent}
			        </div>                            
			      </#if>
                 <div class="authorInfo">
					   <#if (topicsDetail.ispublic==1)>
						<a href='/myHome/getHomePage/${topicsDetail.createperson}' class='zm-item-link-avatar avatar-link' target='_blank' data-userid='${topicsDetail.createperson}'>${topicsDetail.objCreator.username}</a>
					   <#else>
						 <a  class=''  data-userid=''>匿名用户</a>
					   </#if>
                         <span class='meta-top'>${topicsDetail.createtime?string("yyyy-MM-dd")}</span>
		         </div>
           </div>
		</div>
		<div class='relatedcontent'>
		<div class='userrecommend' id='topic-commcount' data-num='${topicsDetail.replysum}'>用户回答 (${topicsDetail.replysum})</div>
		<div class='detail'>
		  <div class='detail-list'>
		  <#if topicsDetail.commentList.list?size lte 0>
		     <p class='uncomment'>暂无回答，你也可以发布回答哦:) </p>
		  </#if>
		  <#if (topicsDetail.commentList)??>
		     <#list  topicsDetail.commentList.list as list>
		       <div class='item'>
		         <div class='media-left'>
		             <#if (list.tmpHeadIcon)??&&list.tmpHeadIcon?length gt 0>
							     <#if (list.isPublic==1)>
							      <a class="zm-item-link-avatar avatar-link" href='/myHome/getHomePage/${list.userid}' target="_blank">
							        <img src="${list.tmpHeadIcon}" class="zm-list-avatar avatar lazy"  alt="个人头像" data-userid='${list.userid}' data-moduletype="0">
							      </a>
							     <#else>
							        <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-avatar avatar lazy" alt="个人头像" data-moduletype="0">
							     </#if>
							    <#else>
							      <#if (list.isPublic==1)>
							        <a class="zm-item-link-avatar avatar-link" href='/myHome/getHomePage/${list.userid}' target="_blank">
							           <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-avatar avatar lazy" alt="个人头像" data-userid='${list.userid}' data-moduletype='0'>
							        </a>
							      <#else>
							         <img src="/image/1b48b5a75c71597_100x100.jpg" class="zm-list-avatar avatar lazy" alt="个人头像" data-userid='${list.userid}' data-moduletype='0'>
							      </#if>
							   </#if>
							   
		             <#if (list.isPublic==1)>
			                <a href='/myHome/getHomePage/${list.userid}' class='uname' data-userid='${list.userid}' target='_blank'>${list.tmpUserName}</a>
		             <#else>
			                <a  class='uname' data-userid='' target='_blank'>匿名用户</a>
		             </#if>
		         </div>
		         <div class='media-right'>
		           <div class="reason">
					   <#if (list.commcontent)??&&list.commcontent?length gt 0>
							${list.commcontent}
					   </#if>
				   </div>
		           <div class="operate">
		             <a  class="answer-date-link meta-item"  href="javascript:;">
		               <#if (list.updatetime?string("yyyy-MM-dd"))??>
		                  ${list.updatetime?string("yyyy-MM-dd")}
		               </#if>
					 </a>
					 <a href="javascript:;" name="addcomment" class="meta-item toggle-comment topic-com" data-answer='${list.id}'  data-commentcount='${list.replysum}' data-createperson='${list.userid}'> 
						<i class="z-icon-comment"></i>${list.replysum}条评论
				     </a>
		           </div>
		         </div>
		       </div>
		     </#list>
		  </#if>
		 </div>
		  
		</div>
		<#if (topicsDetail.commentList.last gt 1)>
			<a data-pageno='1' data-sumpage='${topicsDetail.commentList.last}' class="zg-btn-white zu-button-more loadmore">更多</a>
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
				 <span class="mycommentinfo">填写话题经验，提升回答可信度</span>
			  </a>
        </div>
        <div class="publishbook">
	       <textarea title="在这里输入回答" class="commentcontent" placeholder="在这里输入回答..." ></textarea>	
	    </div>
	    <div class="pj-command clearfix">
	           <label class="zg-left anno-box">
				<input name="anno-checkbox" type="checkbox" > 匿名
			   </label>
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
