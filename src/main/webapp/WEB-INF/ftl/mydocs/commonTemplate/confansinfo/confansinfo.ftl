<div class="uc-head" style="padding:0;width:950px;">
    <div class='infotiptemplate'></div>
	<div class="uc-head-top">
	     <a href="javascript:void(0)" id='myselfattenman' data-userid='${Session.user.userid}'>我关注的人
		    <#list Session.myHeadTop?keys as itemKey>
			     <#if itemKey="attenManSum">
			        (${Session.myHeadTop[itemKey]})
			     </#if>
		     </#list>        
		   </a>
		   <a href="javascript:void(0)"  id='myselffans' data-userid='${Session.user.userid}'>  我的粉丝
		     <#list Session.myHeadTop?keys as itemKey>
			     <#if itemKey="fansSum">
			        (${Session.myHeadTop[itemKey]})
			     </#if>
		      </#list>   
		   </a>

	</div>
</div>