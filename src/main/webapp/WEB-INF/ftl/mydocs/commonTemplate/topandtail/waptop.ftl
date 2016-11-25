<div class='title tc pr top'>
   <a class="back-btn" href='/index'></a>
   <a class='search-icon' id='search-icon'></a>
    <div class="nav-btn">
   <#if (Session.user)??>
      <span class='username' style='display:block'  id='username'>我<i class="z-icon-selfdown"></i></span>
      <ul class="top-nav-dropdown" id="top-nav-profile-dropdown">
		<li>
			<a href='/myHome/getHomePage/${Session.user.userid}'  target="_blank">我的主页</a>
		</li>
		<li>
			 <a href="javascript:void(0);" onclick="toLogout();">退出</a>
		</li>
		<li>
			 <a href="javascript:void(0);" class='pj-fewer' id='pj-fewer'><i class="z-icon-fewer"></i></a>
	    </li>
	  </ul>
   <#else>
      <a href="javascript:void(0);" onclick="toLogin();" style='color:#fff'>登录</a>
      <label>|</label>
      <a href="/login?isregister=1" style='color:#fff'>注册</a>
   </#if> 
  </div>
</div>