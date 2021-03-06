<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
          账户安全-个人中心-JobPlus知识库
    </title> 
    <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
  </head>
  <body>
    <div id="doc" class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
        <div id="bd">
		<div class="bd-wrap">
		<div class="uc-aside <#if Session.user.usertype==2>com-aside</#if>">
		<div class="uc-user-box">
		<div class="<#if Session.user.usertype==2>img-business-center<#else>img-center</#if>">
		<a  href="<#if Session.user.usertype==2>/comp/getHomePage/${Session.user.userid}?isReview=0<#else>/myCenter/getMyHeadTop</#if>" target="_self">
		    <#if (Session.user.headicon)?? && Session.user.headicon?length gt 0>
		    <img src="${Session.user.headicon}" alt="个人头像" width='100' height='100' class='lazy'>
		  <#else>
		    <img src="/image/<#if Session.user.usertype==2>cm-defaultIcon100.jpg<#else>myphoto.jpg</#if>" alt="个人头像" width='100' height='100' class='lazy'>
		  </#if>
		 </a>
		</div>
		<p class="user-name">
			<a class="name-link" href="<#if Session.user.usertype==2>/comp/getHomePage/${Session.user.userid}?isReview=0<#else>/myCenter/getMyHeadTop</#if>" target="_self">
	            <#if (Session.user)??>
		           ${Session.user.username}
		        </#if>
		    </a>
		    <#if Session.user.usertype==2>
		   <#else>
             <span class="pj-level"><em>LV.${Session.user.userlevel}</em></span>
		   </#if> 
		</p>
        <p class="user-level">
         <#if Session.user.usertype==2>
		    <a href="/comp/getHomePage/${Session.user.userid}?isReview=0" target="_blank">&nbsp;进入公司主页</a> 
		  <#else>
		    <a href="/myHome/getHomePage/${Session.user.userid}?isReview=0" target="_blank">&nbsp;进入个人主页</a>
		  </#if>
        </p> 
		<div class="mydoc-list">
		<ul>
		    <ul id="accordion">
		      <li>
		         <div class="my-doc link">
					  <p>
						  <a href='/myCenter/getMyHeadTop'>
							<span class="icon-ken <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的知识库
						  </a>
							<b class="iconfont up-arrow doc-arrow-btn"></b>
					 </p>
				 </div>
		         <ul class="submenu">
	                 <li id="mydocument"><a href="/myCenter/getMyDocsUploaded"><span class="icon-doc <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的文档</a></li>
	                 <li id="mytopic"><a href="/myCenter/getMyTopicsUploaded"><span class="icon-topic <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的话题</a></li>
	                 <li id="mybook"><a href="/myCenter/getSharedBookList"><span class="icon-book <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的书籍</a></li>
	                 <li id="mycourse"><a href="/myCenter/getSharedCourseList"><span class="icon-course <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的课程</a></li>
	                 <li id="myarticle"><a href="/myCenter/getSharedArticleList"><span class="icon-article <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的文章</a></li>
	                 <li id="mysite"><a href="/myCenter/getSharedSiteList"><span class="icon-site <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的站点</a></li>
		          </ul>
		      </li>
		      <li class="open">
		         <div class="my-doc link"><p><span class="icon-account <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的账户<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu" style="display:block">
	                 <li><a href="/myCenter/getAllSms?islook=0"><span class="icon-info <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>全部消息</a></li>
	                 <li><a href="/myCenter/getSmsFilterParm"><span class="icon-setinfo <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>消息设置</a></li>
	                 <li><a href="/myCenter/account/security" class="current"><span class="icon-accountsafety <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>账户安全</a></li>
		          </ul>
		       </li>
		       <li>
		        <div class="my-order link"><p><span class="icon-order <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的订单<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	                 <li><a href="/user/allorder"><span class="icon-allorder <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>全部订单</a></li>
	                 <li><a href="/user/cash"><span class="icon-cash <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>已经支出</a></li>
	                 <li><a href="/user/nocash"><span class="icon-nocash <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>尚未支付</a></li>
		          </ul>
		      </li>
		       <li>
		         <div class="my-order link"><p><span class="icon-wealth <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>我的财富<b class="iconfont up-arrow doc-arrow-btn"></b></p></div>
		         <ul class="submenu">
	               <li><a href="/account/getDetailListByRecord?changetype=1"><span class="icon-incomewealth <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>财富收益</a></li>
	               <li><a href="/account/getDetailListByRecord?changetype=2"><span class="icon-wealthspending <#if (Session.user.usertype==2)>c-icon<#else>u-icon</#if>"></span>财富支出</a></li>
		          </ul>
		      </li>
		    </ul>
		</ul>
		</div>
		</div>
		<div class="side-nav-bar" id="side-nav-bar" style="display: none; left: 1461.5px; position: fixed; top: 650px;">
		<ul>
		<li><a href="#" target="_self"><span class="s-ic guide"></span>反馈</a></li>
		<li class="gotop"><a href="javascript:void(0);"><span class="s-ic top"></span></a></li>
		</ul>
		</div>
		</div>
		<div class="uc-main">
		    <div class="uc-head" style="padding:0;width:950px;">
				<#include "/mydocs/commonTemplate/confansinfo/confansinfoshare.ftl"/>
			</div>	
		   <div id="allnews" class="maincontent">
			<div class="uc-news">
			   <div class="console-box">
					<p>账号安全</p>
	           </div>
	           <div class="settings-acount-main">
					<div class="settings-intro">
					绑定手机和邮箱，并设置密码，帐号更安全。
					</div>
					<input type='hidden' name='email_verification_code' value=''>
					<input type='hidden' name='mobile_verification_code' value=''>
					<div class="settings-section">
					<div class="settings-item clearfix">
					<label class="settings-item-title">邮箱</label>
					<div class="settings-item-content form-container">
					
					<div class="group group-text hidden-expanded">
					   <#if (Session.user.email)?? && Session.user.email?length gt 0>
					      <input type='hidden' name='email' value='${Session.user.email}'>
					      <#--邮箱不同  区显示3分之2的位数，向上取整-->
					      <#assign email_index1=((Session.user.email?substring(0,Session.user.email?last_index_of('@'))?length)*2/3)?ceiling />
					      <#assign email_index2=Session.user.email?last_index_of('@') />
					      <span class="text email">
					          ${Session.user.email?substring(0,email_index1?number-1)+'****'+Session.user.email?substring(email_index2?number-1,Session.user.email?length)}
					      </span>
				          <a href="#" class="js-expand boundemail">修改</a>
					   <#else>
					      <span class="text email"></span>
					      <a href="#" class="js-expand boundemail">绑定邮箱</a>
					   </#if>
					</div>
					
					<div class="form-basic panel hidden email-form">
						<div class="group">
						   <input required="" class="text" name="email" id='email' type="email" placeholder="输入邮箱">
						   <label for="email" class="error"></label>
						</div>
						<div class="group hidden">
							<input required="" class="text digits" name="digits" placeholder="6 位数验证码">
							<label for="email_no" class="error verification"></label>
							<input type="button" value="获取验证码" class="register_submit" data-type='email' id="emailcode" style="width:180px;">
						</div>
						<button class="getcode zg-btn-blue" data-type='email'>获取验证码</button>
						<button class="btnsure zg-btn-blue hidden" data-type='email'>确定</button>
						<span class="hint-label-container z-text-muted"></span>
					</div>
					</div>
					</div>
					<div class="settings-item clearfix">
					<label class="settings-item-title">手机</label>
					<div class="settings-item-content form-container">
					
					<div class="group group-text">
					<#if (Session.user.mobile)?? && Session.user.mobile?length gt 0>
					    <input type='hidden' name='mobile' value='${Session.user.mobile}'>
						<span class="text phone">
						  ${Session.user.mobile?substring(0,3)+'****'+Session.user.mobile?substring(7,Session.user.mobile?length)}
						</span>
						<a href="#" class="js-expand boundmobile">修改</a>
				    <#else>
					    <span class="text phone"></span>
					    <a href="#" class="js-expand boundmobile">绑定手机</a>
					</#if>
					</div>
					
					<div class="form-basic panel hidden phone-form">
						<div class="group">
						   <input required="" class="text" name="phone_no" id='phone' type="tel" placeholder="输入新手机号" >
						   <label for="phone_no" class="error"></label>
						</div>
						<div class="group hidden">
						   <input required="" class="text digits" name="digits" placeholder="6 位数验证码">
						   <label for="phone_no" class="error verification"></label>
						   <input type="button" value="获取验证码" class="register_submit"  data-type='phone' id="phonecode" style="width:180px;">
						</div>
						<button class="getcode zg-btn-blue" data-type='phone'>获取验证码</button>
						<button class="btnsure zg-btn-blue hidden" data-type='phone'>确定</button>
						<span class="hint-label-container z-text-muted"></span>
					</div>
					</div>
					</div>
					<div class="settings-item clearfix">
					<label class="settings-item-title">帐号密码</label>
					<div class="settings-item-content form-container">
					<div class="group group-text hidden-expanded">					
					<a href="#" class="js-expand editpawssord">修改密码</a>
					<label for="phone_no" class="error successupdate"></label>
					</div>
					<div class="form-basic panel hidden password-form">
						<div class="group">
						 <input required="" class="text" type="password" placeholder="输入新密码" name="password" id="password">
						 <label for="phone_no" class="error password_error"></label>
						</div>
						<div class="group">
						 <input required="" class="text" type="password" placeholder="再次输入" name="password_repeat" data-rule-equalto="#password">
						 <label for="phone_no" class="error password_repeat_error"></label>
						</div>
						<button class="btnsure zg-btn-blue">确定</button>
					</div>
					</div>
					</div>
					
					</div>
				</div>
            </div>
           </div>
      	  </div>
		</div>
		</div>
	  </div>
	  <div class='pagetemplate'></div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
      <script type="text/javascript" src="/scripts/pj_accountsecurity.js"></script>
  </body>

</html>