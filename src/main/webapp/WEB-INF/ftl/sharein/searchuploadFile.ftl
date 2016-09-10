<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
           选择分享
    </title> 
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_framework.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_wkcommon_base.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_upload.css">
  </head>
  <body>
    <div id="doc" class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
      <div id="bd">
        <div class='bookdetail'>聘加知识库><span id='loveshare'>上传文档</span></div> 
        <div class="upload-share"></div>
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              <div id="upload-ext-info-container" class="task-info upload-ext-info-container mb10"
              style="display:none;">
              </div>
              <div id="upload-widget" class="upload-widget ">
 
                <div id="upload-init-container" style="display: block;">
                  <div class="block-hack">
	                   <div class="block-cnt">
	                        <a href="#" class="nav-item mr25 mingjia <#if type==0>tb-selected</#if>" data-sharename='上传文档' data-target="/51jobplusCore/sharein/shareDocument">
		                         <div class="img"></div>
		                                                                文档<#if type==0><i></i></#if>
	                        </a>
	                        <a href="#" class="nav-item mr25 economy <#if type==1>tb-selected</#if>" data-sharename='话题分享' data-target="/51jobplusCore/sharein/shareTopic">
		                         <div class="img"></div>                                      
		                                                                话题<#if type==1><i></i></#if>
	                        </a>
							<a href="#" class="nav-item mr25 internet <#if type==2>tb-selected</#if>" data-sharename='分享书籍'  data-target="/51jobplusCore/sharein/shareBook">
								  <div class="img"></div>
								     书籍<#if type==2><i></i></#if>
							</a>
							<a href="#" class="nav-item mr25 tech <#if type==3>tb-selected</#if>" data-sharename='课程分享' data-target="/51jobplusCore/sharein/shareCourse">
								 <div class="img"></div>  
								   课程<#if type==3><i></i></#if>
							 </a>
							<a href="#" class="nav-item mr25 fashion <#if type==4>tb-selected</#if>" data-sharename='文章分享' data-target="/51jobplusCore/sharein/shareArticle">
								 <div class="img"></div> 
								   文章<#if type==4><i></i></#if>
							</a>
							<a href="#" class="nav-item authors <#if type==5>tb-selected</#if>" data-sharename='站点分享' data-target="/51jobplusCore/sharein/shareSite">
								 <div class="img"></div>
								   站点<#if type==5><i></i></#if>
							</a>
						</div>
                  </div>
                  <div class="mod mod-upload">
                    <div class="bd" style="">
                      <div class="flash-upload-wrap" id="uploadFile">
                        <a class="upload-btn bg-index" href="searchuploadFile.html"></a>
                      </div>
                      <p class="drag-tips">
                        每成功分享一篇公开文档，可获取
                        <span class="strongTips">
                         1财富值
                        </span>
                        奖励
                      </p>
                    </div>
                    <p class="up-tips">
                      
                    </p>
                  </div>
                  <div class="upload-intro-query clearfix">
                    <h4>
                      分享推荐：
                     
                    </h4>
                     <span class='pj-encourage'>
                       成功分享即可获得一定的财富值奖励!
                     </span>
                     <ul>
                      <li class="">
                        精品专辑
                      </li>
                      <li class="query-box">
                      名家推荐
                      </li>
                      <li class="">
                     热门分享
                      </li>
                      <li class="query-box">
                      使用专栏
                      </li>
                      <li class="">
                     精彩回答
                      </li>
                      <li class="query-box">
                        热搜榜单
                      </li>
                      <li class="">
                    认证推荐
                      </li>
                      <li class="query-box">
                     教育专区
                      </li>
                      <li class="">
                      热门下载
                      </li>
                        <li class="">
                      经典收藏
                      </li>
                    </ul>
                  </div>
                  <div class="upload-notice">
                    <h4>
                     温馨提示
                    </h4>
                    <ol>
                      <li>
                         1.聘加是一个新型的知识分享平台,目前主要聚集在互联网从业者与创业者最关注的知识领域;
                      </li>
                      <li>
                        2.如果您不想上传文档给他人看，只想自己阅读使用，可以将文档上传为私有文档，当然，文档的显示设置可以随时修改，公开文档和私有文档可以随时切换，满足您的阅读需求；
                      </li>
                      <li>
                        3.上传涉及侵权内容的文档将会被移除，严禁上传含有淫秽色情及低俗信息等文档，详情请看
                        <a href="#" target="_blank">
                          文档协议
                        </a>
                        和
                        <a href="#" target="_blank">
                       用户规则
                        </a>
                        让我们一起携手共同打造健康的知识库；
                      </li>
                      <li>
                        4.上传有问题需要帮助？查看
                                         <a href="#" target="_blank">
                          文档帮助
                        </a>
                        和
                        <a href="#" target="_blank">
                       意见反馈
                        </a>
                        ；
                      </li>
                      <li>
                        5.友情提示：您上传私有文档将不涉及财富收支及兑换；分享文档的大小限制在20M以内。
                      </li>
                    </ol>
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
    <#include "/mydocs/commonTemplate/successuploadjs/successuploadjs.ftl"/> 

  </body>

</html>