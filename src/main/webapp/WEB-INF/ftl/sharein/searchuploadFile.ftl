<!DOCTYPE html>
<html class="expanded">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
           选择分享
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
  </head>
  <body>
    <div id="doc" class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
        <div class='bookdetail'>JobPlus知识库>
           <#if type==0>
            <span id='loveshare'>上传文档</span>
           <#elseif type==1>
            <span id='loveshare'>话题分享</span>
           <#elseif type==2>
            <span id='loveshare'>分享书籍</span>
           <#elseif type==3>
            <span id='loveshare'>课程分享</span>
           <#elseif type==4>
            <span id='loveshare'>文章分享</span>
           <#elseif type==5>
            <span id='loveshare'>站点分享</span>
           </#if>
        </div> 
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
	                        <a href="#" class="nav-item mr25 mingjia <#if type==0>tb-selected</#if>" data-sharename='上传文档' data-text='文档' data-target="/sharein/shareDocument">
		                         <div class="img"></div>
		                                                                文档<#if type==0><i></i></#if>
	                        </a>
	                        <a href="#" class="nav-item mr25 economy <#if type==1>tb-selected</#if>" data-sharename='话题分享' data-text='话题' data-target="/sharein/shareTopic">
		                         <div class="img"></div>                                      
		                                                                话题<#if type==1><i></i></#if>
	                        </a>
							<a href="#" class="nav-item mr25 internet <#if type==2>tb-selected</#if>" data-sharename='分享书籍'  data-text='书籍' data-target="/sharein/shareBook">
								  <div class="img"></div>
								     书籍<#if type==2><i></i></#if>
							</a>
							<a href="#" class="nav-item mr25 tech <#if type==3>tb-selected</#if>" data-sharename='课程分享' data-text='课程' data-target="/sharein/shareCourse">
								 <div class="img"></div>  
								   课程<#if type==3><i></i></#if>
							 </a>
							<a href="#" class="nav-item mr25 fashion <#if type==4>tb-selected</#if>" data-sharename='文章分享' data-text='文章' data-target="/sharein/shareArticle">
								 <div class="img"></div> 
								   文章<#if type==4><i></i></#if>
							</a>
							<a href="#" class="nav-item authors <#if type==5>tb-selected</#if>" data-sharename='站点分享' data-text='站点' data-target="/sharein/shareSite">
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
                                                                     每成功分享一篇公开<span id='sharetext'><#if type==0>文档<#elseif type==1>话题<#elseif type==2>书籍<#elseif type==3>课程<#elseif type==4>文章<#elseif type==5>站点</#if></span>，可获取
                        <span class="strongTips">
                         <#if type==0>
                            2财富值
                         <#else>
                            1财富值
                         </#if>
                        </span>
                        奖励
                      </p>
                    </div>
                    <p class="up-tips">
                      
                    </p>
                  </div>
                  <div class="upload-intro-query clearfix">
                     <h4>推荐分享</h4>
                    <ul>
                      <li class="">
                       创新创业
                      </li>
                      <li class="query-box">
                      工业4.0
                      </li>
                      <li class="">
                       新型材料
                      </li>
                      <li class="query-box">
                      互联网+
                      </li>
                      <li class="">
                      企业管理 
                      </li>
                      <li class="query-box">
                         互联网运营
                      </li>
                      <li class="">
                       供应链金融
                      </li>
                      <li class="query-box">
                       新媒体
                      </li>
                      <li class="">
                      融资股权
                      </li>
                      <li class="query-box">
                       生物医药
                      </li>
                       <li class="">
                    财务管理
                      </li>
                      <li class="query-box">
                       大数据
                      </li>
                    </ul>
                  </div>
                  <div class="upload-notice">
                    <h4>
                     温馨提示
                    </h4>
                    <#if type==0>
                     <ol>
                    <#else>
                     <ol style='display:none'>
                    </#if>
                      <li>
                         1.JobPlus专注企业知识服务平台，请不要分享与之无关内容;
                      </li>
                      <li>
                        2.您可以分享日常积累和撰写的文档资料，支持多种文档类型：doc，docx，ppt，pptx，xls，xlsx，vsd，pot，pps，pdf，txt，wps，etc;
                      </li>
                      <li>
                        3.从我的电脑选择要分享的文档：按住CTRL可以分享多份文档, 分享的文件大小限制在20M以内;
                      </li>
                      <li>
                        4.分享涉及侵权内容的文档将会被移除，严禁分享含有淫秽色情及低俗信息等文档;
                      </li>
                      <li>
                        5.分享有问题需要帮助？详情请查看知识库<a href="#" target="_blank">服务条款</a>和<a href="#" target="_blank">帮助中心</a>
                      </li>
                    </ol>
                     <#if type==1>
                     <ol>
                     <#else>
                     <ol style='display:none'>
                     </#if>
                      <li>
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关内容;
                      </li>
                      <li>
                        2.话题分享应遵循客观、真实、简洁、明确、规范的原则，提问尽可能简洁明了，尽量避免太宽泛的问题;
                      </li>
                      <li>
                        3.禁止提问辱骂他人、含有淫秽色情及低俗信息等话题;
                      </li>
                      <li>
                        4.提问有问题需要帮助？详情请查看知识库
                        <a href="#" target="_blank">
                                                                 服务条款
                        </a>和
                        <a href="#" target="_blank">
                                                                帮助中心
                        </a>
                      </li>
                    </ol>
                     <#if type==2>
                        <ol>
                     <#else>
                        <ol style='display:none'>
                     </#if>
                      <li>
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关内容;
                      </li>
                      <li>
                        2.如果您发现一本好书，可以分享给更多的人并给出你的见解，让他们可以找这本书;
                      </li>
                      <li>
                       3.禁止分享低俗、含有淫秽色情及低俗信息等书籍;
                      </li>
                      <li>
                        4.书籍分享有问题需要帮助？详情请查看知识库
                        <a href="#" target="_blank">
                     服务条款
                        </a>
                        和
                        <a href="#" target="_blank">
                     帮助中心
                        </a>
                      </li>
                    </ol>
                     <#if type==3>
                        <ol>
                     <#else>
                        <ol style='display:none'>
                     </#if>
                      <li>
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关内容;
                      </li>
                      <li>
                        2.如果您发现一节好的在线语音或视频课程，可以分享出来，让更多的人一起学习;
                      </li>
                      <li>
                        3.严禁分享含有辱骂他人、淫秽色情及低俗信息等视频和音频链接;
                      </li>
                      <li>
                        4.课程分享有问题需要帮助？详情请查看知识库
                        <a href="#" target="_blank">
                                                              服务条款
                        </a>和
                        <a href="#" target="_blank">
                                                              帮助中心
                        </a>
                      </li>
                    </ol>
                     <#if type==4>
                        <ol>
                     <#else>
                        <ol style='display:none'>
                     </#if>
                      <li>
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关内容;
                      </li>
                      <li>
                        2.如果您发现一篇好的文章，可以分享出来，让大家一起学习成长;
                      </li>
                      <li>
                        3.禁止分享辱骂他人，含有淫秽色情及低俗信息的文章链接;
                      </li>
                      <li>
                        4.文章分享有问题需要帮助？详情请查看知识库
                        <a href="#" target="_blank">
                                                                服务条款
                        </a>和
                        <a href="#" target="_blank">
                                                              帮助中心
                        </a>
                      </li>
                    </ol>
                     <#if type==5>
                        <ol>
                     <#else>
                        <ol style='display:none'>
                     </#if>
                      <li>
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关内容;
                      </li>
                      <li>
                        2.如果您发现一个好的站点，可以分享给更多的人，直接分享网站链接即可;
                      </li>
                      <li>
                        3.禁止分享非法，含有淫秽色情及低俗信息的站点链接;
                      </li>
                      <li>
                        4.站点分享有问题需要帮助？详情请查看知识库
                        <a href="#" target="_blank">
                                                                 服务条款
                        </a>和
                        <a href="#" target="_blank">
                                                                帮助中心
                        </a>
                      </li>
                    </ol>
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