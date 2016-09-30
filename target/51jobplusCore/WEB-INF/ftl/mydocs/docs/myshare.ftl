<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
               我的分享
    </title> 
   <#include "/mydocs/commonTemplate/headstyle/headstyle.ftl"/>
   <link rel="stylesheet" type="text/css" href="/css/pj_mycenter.css">
  </head>
  <body>
     <div id="doc" class="page">
        <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
        <div id="sharecontent">
          <div class="sharecontent-wrap">
	           <div class="fansleft">
	             <span>
	                 <#if (Session.user)??>
	                    ${Session.user.username}
	                 </#if>的分享(60)
	             </span>
	            
	           </div>
	           <div class="fansright">
	               <a href="javascript:history.go(-1)">返回>></a>
	           </div>
          </div>
          
          <div class="people" id="share">
             <div class="doc-info-wrap">
                <ul class="clearfix">
                    <li>
                       <div class="num">10</div>
                       <a class="nav-item mr25 mingjia tb-selected">
                          <div class="img"></div> 文档
                          <i></i>
                       </a>
                    </li>
                    
                    <li>
                       <div class="num">10</div>
                       <a class="nav-item mr25 economy">
                          <div class="img"></div> 话题
                       </a>
                    </li>
                    
                    <li>
                       <div class="num">10</div>
                       <a class="nav-item mr25 internet">
                          <div class="img"></div> 书籍
                       </a>
                    </li>
                    
                    <li>
                       <div class="num">10</div>
                       <a class="nav-item mr25 tech">
                          <div class="img"></div> 课程
                       </a>
                    </li>
                    
                    <li>
                       <div class="num">10</div>
                       <a class="nav-item mr25 fashion">
                          <div class="img"></div> 文章
                       </a>
                    </li>
                    
                    <li>
                       <div class="num">10</div>
                       <a class="nav-item mr25 authors">
                          <div class="img"></div> 站点
                       </a>
                    </li>
                </ul>
             </div> 
          </div>
          
          <div class="content">
             <div class="doc">
                <ul>
                  <li class="selected-item">
                    <div class="w200 ib">
                        <a>      成功分享文档......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                  
                  <li class="selected-item">
                    <div class="w200 ib">
                                                              <a>      成功分享文档......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                </ul>
             </div>
             
             <div class="topic" style="display:none">
               <ul>
                  <li class="selected-item">
                    <div class="w200 ib">
                                                           <a>成功分享话题......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                  
                  <li class="selected-item">
                    <div class="w200 ib">
                                                           <a>   成功分享话题......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                </ul>
             </div>
             
             <div class="book" style="display:none">
                <ul>
                  <li class="selected-item">
                    <div class="w200 ib">
                                                          <a> 成功分享书籍......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                  
                  <li class="selected-item">
                    <div class="w200 ib">
                                                               <a> 成功分享书籍......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                </ul>
             </div>
             
             <div class="article" style="display:none">
                <ul>
                  <li class="selected-item">
                    <div class="w200 ib">
                                                                <a>成功分享文章......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                  
                  <li class="selected-item">
                    <div class="w200 ib">
                                                               <a> 成功分享文章......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                </ul>
             </div>
             
             <div class="course" style="display:none">
                 <ul>
                  <li class="selected-item">
                    <div class="w200 ib">
                                                                <a>成功分享课程......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                  
                  <li class="selected-item">
                    <div class="w200 ib">
                                                                <a>成功分享课程......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                </ul>
             </div>
             
             <div class="site" style="display:none">
                 <ul>
                  <li class="selected-item">
                    <div class="w200 ib">
                                                              <a>  成功分享站点......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                  
                  <li class="selected-item">
                    <div class="w200 ib">
                                                                <a>成功分享站点......</a>
                    </div>
                    <div class="date">
                       2016-5-10
                    </div>
                  <li>
                </ul>
             </div>
          </div>
         </div>
        <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
     </div>
       <#include "/mydocs/commonTemplate/bottomjs/bottomjs.ftl"/>
  </body>

</html>