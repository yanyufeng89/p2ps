<div class="uc-head-top">
       <div class='infotiptemplate'></div>
	   <div style="float:left">
		   <a href="javascript:void(0)"  id='myselfattenman' data-userid='${Session.user.userid}'>我关注的人
		    <#if (Session.myHeadTop)??>
			     <#list Session.myHeadTop?keys as itemKey>
				     <#if itemKey="attenManSum">
				        (${Session.myHeadTop[itemKey]})
				     </#if>
			     </#list>  
		     </#if>
		   </a>
		   <a href="javascript:void(0)"  id='myselffans' data-userid='${Session.user.userid}'>  我的粉丝
		    <#if (Session.myHeadTop)??>
		      <#list Session.myHeadTop?keys as itemKey>
			     <#if itemKey="fansSum">
			        (${Session.myHeadTop[itemKey]})
			     </#if>
		      </#list>   
		     </#if>
		   </a>
	  </div>
	  
	     <a href='javascript:void(0);' onclick="share();" target='_blank' class='head-sharein' style='float:right'></a>
	  
</div>
<script type="text/javascript">
    function share() {
        var currentUrl = window.location.href;
        var type = 0;
        if (currentUrl.indexOf("getMyTopicsUploaded") > -1)
            type = 1;
        else if (currentUrl.indexOf("getSharedBookList") > -1)
            type = 2;
        else if (currentUrl.indexOf("getSharedCourseList") > -1)
            type = 3;
        else if (currentUrl.indexOf("getSharedArticleList") > -1)
            type = 4;
        else if (currentUrl.indexOf("getSharedSiteList") > -1)
            type = 5;
        window.open("/51jobplusCore/sharein/searchuploadFile?type=" + type);
    }
</script>