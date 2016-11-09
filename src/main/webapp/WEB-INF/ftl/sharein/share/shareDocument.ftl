<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
             上传文档-JobPlus
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
  </head>
  <body>
   
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
        <div class='docdetail'>JobPlus知识库>上传文档</div>
        <div class="upload-doc"></div>
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              <div id="upload-ext-info-container" class="task-info upload-ext-info-container mb10"
              style="display:none;">
              </div>
              <div id="upload-widget" class="upload-widget ">
               <form method="post"  enctype="multipart/form-data" id ='test11form' action='/docs/upload'>
                <input type='hidden' name='docId' value=''>
                <div id="upload-init-container" style="display: block;">
	                 <div class="upload-steps clearfix">
						<ul>
							<li class="step-num active">1<span class="tips">选择文档</span>
							</li>
							<li class="step-bar"></li>
							<li class="step-num">2<span class="tips">补充信息</span>
							</li>
							<li class="step-bar"></li>
							<li class="step-num">3<span class="tips">上传成功</span>
							</li>
						</ul>
					 </div>
                 <div class="mod mod-upload">
                   <div class="bddoc" style="">
				         <div class="titleinfo">
	                       <span>【文档】</span>
	                       <span style='font-weight:500'>请选择上传文档</span>
						 </div>
	                     <div class="link after file-box">
	                        <input style='display:none'/>
				            <input type='text' name='textfield' style='color:darkgrey;visible:hidden' id='textfield' class='txtdocument' placeholder='请选择文档...'/>
				            <#--<input type='button' class='docpreview' value='浏览' />-->
				            <span class="tocapture active docpreview"></span>
				            <input type="file" name="fileField" class="file" id="docfileField"  multiple="multiple"/>
                               <p class="error"><i class="tips_icon"></i>包含不支持的文件类型,具体文件类型参考温馨提示</p>
				           	<div class="bar after">
						        <a class="btn-disblue btnsure"  id='btndocsure' href='javascript:void(0)'>确定</a>
						        <a href='/sharein/searchuploadFile' class='btn-cancleupload'>取消</a>
						        <div class="btn-loading push-loading"></div>
		                    </div>
	                     </div>
	                    
	                    </div>
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
                    <ol>
                      <li>
                        1.JobPlus专注企业知识服务平台，请不要上传与之无关文档， 请勿上传已设置加密或只读的文档，不支持此类文档;
                      </li>
                      <li class="doc-type">
                        2.您可以分享日常积累和撰写的文档资料，支持多种文档类型：doc，docx，ppt，pptx，xls，xlsx，vsd，pot，pps，pdf，txt，wps，etc;
                      </li>
                      <li>
                        3.从我的电脑选择要分享的文档：按住CTRL可以点击多份文档分享, 分享的文件大小限制在20M以内;
                      </li>
                      <li>
                        4.分享涉及侵权内容的文档将会被移除，严禁分享含有淫秽色情及低俗信息等文档; 
                      </li>
                      <li>
                        5.分享有问题需要帮助？详情请查看知识库
                        <a href="/about/terms_service" target="_blank">服务条款</a>和
                        <a href="/about/newbie_guide" target="_blank">帮助中心。</a>
                      </li>
                    </ol>
                  </div>
                </div>
                <div id="upload-files-container" style="display:none;">
                  <div class="upload-steps clearfix">
                    <ul>
                      <li class="step-num active">1<span class="tips">选择文档</span></li>
                      <li class="step-bar active"></li>
                      <li class="step-num active">2<span class="tips">完善信息</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">3<span class="tips">上传成功</span></li>
                    </ul>
                  </div>
                  <div class="nhd nedit-all-head">
                    <span class="act btn-submit-all-wrap logSend">
                      <a href="###" id="btn-add-all" class="add-all ml20   add-all-able">
                        <span>+</span>&nbsp;继续添加
                        <input type="file" name="fileField" class="file"  size="28" multiple="multiple">
                      </a>
                      <a  id="btn-submit-all" class="submit-all ml20  submit-all-able">
                        <i class="iconfont"></i>&nbsp;确认上传
                      </a>
                      </span>
                    <h3 id="item-message-all" class="item-message-all item-message-all-ok">
                                                          请补充文档信息，完成上传
                    </h3>
                    <p class="error" style='margin:0'><i class="tips_icon"></i>上传的文档包含不支持的类型</p>
                  </div>
                  <div id='addtemp' style='display:none'></div>
                  <div id="upload-list" class="upload-list">

                  </div>
                 
                  <div class="uploadinfobook">
                    <ol>
                      <li class="doc-type"></li>
                      <li>1.完善上传的文档简介，可以让别人更加清晰的了解分享文档的相关内容;</li>
                      <li>2.将您上传的文档可以免财富值免费分享，也可以1到20点财富值进行售价赚取财富值；同时可以对文档进行设置"公开"和"私有"属性;</li>
                      <li>3.文档上传时必须添加分类，详细的分类，方便更准确找到你的文档;</li>
                      <li>4.添加文档标签，更精准的搜索目标文档，多个标签之间要用逗号隔开，便于区分;</li>
                      <li>
                        5.文档分享有问题需要帮助？详情请查看知识库
                        <a href="/about/terms_service" target="_blank">服务条款</a>和
                        <a href="/about/newbie_guide" target="_blank">帮助中心。</a>
                      </li>
                    </ol>
                   
                </div>
              </div>
               </form>
                
              </div>
            </div>
          </div>
        </div>
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
     <#include "/mydocs/commonTemplate/sharejs/sharejs.ftl"/> 
     <script type="text/javascript" src="/scripts/pj_docsharein.js"></script>
     <script type="text/javascript" src="/scripts/pj_docClassInit.js"></script>
  </body>

</html>