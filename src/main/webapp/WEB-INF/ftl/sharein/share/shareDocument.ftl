<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
             上传文档
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_doc.css">
  </head>
  <body>
   
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
      <div id="bd">
        <div class='docdetail'>聘加知识库>上传文档</div>
        <div class="upload-doc"></div>
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              <div id="upload-ext-info-container" class="task-info upload-ext-info-container mb10"
              style="display:none;">
              </div>
              <div id="upload-widget" class="upload-widget ">
               <form method="post"  enctype="multipart/form-data" id ='test11form' action='/51jobplusCore/docs/upload' onkeydown="if(event.keyCode==13)return false;" >
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
	                     <a class="titleinfo">【文档】</a>
	                     <span class="titlename">请选择上传文档</span>
	                     <div class="link after file-box">
				            <input type='text' name='textfield' style='color:darkgrey;' id='textfield' class='txtdocument' placeholder='请选择文档...'/>
				            <input type='button' class='docpreview' value='浏览' />
				            <input type="file" name="fileField" class="file" id="docfileField" size="28" multiple="multiple"/>
                               <p class="error"><i class="tips_icon"></i>包含不支持的文件类型,具体文件类型参考温馨提示</p>
				           	<div class="bar after">
						        <input type="botton" class="btn-disblue" value="确定" id='btnsure' style='float:right;' disabled="disabled">
						        <a href='/51jobplusCore/sharein/searchuploadFile' class='btn-cancleupload'>取消</a>
						        <div class="btn-loading push-loading"></div>
		                    </div>
	                     </div>
	                    
	                    </div>
                  </div>
                  <div class="upload-intro-query clearfix">
                    <h4>
                      文档推荐
                     
                    </h4>
                     <span class='pj-encourage'>
                        每成功上传一篇文档即可获得一定的财富奖励哦!
                      </span>
                    <ul>
                      <li class="">
                        考试资料
                      </li>
                      <li class="query-box">
                        教学课件
                      </li>
                      <li class="">
                       学术论文
                      </li>
                      <li class="query-box">
                        金融财经
                      </li>
                      <li class="">
                        研究报告
                      </li>
                      <li class="query-box">
                        教材教辅
                      </li>
                      <li class="">
                       法律文献
                      </li>
                      <li class="query-box">
                        管理文献
                      </li>
                      <li class="">
                        社会科学
                      </li>
                      <li class="query-box">
                       生活休闲
                      </li>
                    </ul>
                  </div>
                  <div class="upload-notice">
                    <h4>
                      温馨提示
                    </h4>
                    <ol>
                      <li>
                        1.您可以上传日常积累和撰写的课程资料，如模板、总结，每次可上传≤20份，每份≤200MB，支持多种文档类型：
                      </li>
                      <li class="doc-type">
                        <b class="ic ic-doc mr5">
                        </b>
                        doc,docx
                        <b class="ic ic-ppt ml10 mr5">
                        </b>
                        ppt,pptx
                        <b class="ic ic-xls ml10 mr5">
                        </b>
                        xls,xlsx
                        <b class="ic ic-vsd ml10 mr5">
                        </b>
                        vsd
                        <b class="ic ic-pot ml10 mr5">
                        </b>
                        pot
                        <b class="ic ic-pps ml10 mr5">
                        </b>
                        pps
						<b class="ic ic-pdf ml10 mr5">
                        </b>
                        pdf
                        <b class="ic ic-txt ml10 mr5">
                        </b>
                        txt
                        <b class="ic ic-wps ml10 mr5">
                        </b>
                        wps
						 <b class="ic ic-et ml10 mr5">
                        </b>
                        et
						
                      </li>
                      <li>
                        2.从我的电脑上选择要上传的文档,按CTRL可以上传多份文档;
                      </li>
                      <li>
                        3.上传涉及侵权内容的文档将会被移除,严禁上传含有淫秽色情及低俗信息等文档;
                      </li>
                      <li>
                        4.上传的文件大小限制在20M以内;
                      </li>
					  <li>
                        5.上传有问题需要帮助？查看<a>知识库帮助</a>
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
                      <span>请补充文档信息，完成上传
                        <br>
                        <span class="item-message-all-subtitle">审核通过后，您将获得
                          <span class="item-reward-wealth">1财富值</span>的奖励
                        </span>
                    </h3>
                    <p class="error" style='margin:0'><i class="tips_icon"></i>上传的文档包含不支持的类型</p>
                  </div>
                  <div id='addtemp' style='display:none'></div>
                  <div id="upload-list" class="upload-list">

                  </div>
                 
                  <div class="uploadinfobook">
                    <ol>
                      <li class="doc-type"></li>
                      <li>1.上传涉及侵权内容的文档将会被移除。如何判断文档是否侵权？查看
                        <a href="#" target="_blank">文库协议</a>和
                        <a href="#" target="_blank">用户规则</a></li>
                      <li>2.上传有问题需要帮助？查看
                        <a href="#" target="_blank">文库帮助</a>和
                        <a href="#" target="_blank">意见反馈</a></li>
                      <li>3.为营造绿色网络环境，严禁上传含有淫秽色情及低俗信息等文档，让我们一起携手共同打造健康文库</li></ol>
                   
                </div>
              </div>
               </form>
                <div id="upload-success-container" class="upload-success-container" style="">
                  <div class="upload-steps clearfix">
                    <ul>
                      <li class="step-num active">
                        1
                        <span class="tips">
                          选择文档
                        </span>
                      </li>
                      <li class="step-bar active">
                      </li>
                      <li class="step-num active">
                        2
                        <span class="tips">
                          补充信息
                        </span>
                      </li>
                      <li class="step-bar active">
                      </li>
                      <li class="step-num active">
                        3
                        <span class="tips">
                          完成上传
                        </span>
                      </li>
                    </ul>
                  </div>
                  <div class="hd">
                    <div class="hd-wrap">
                      <h1>
                        <span class="ic ic-success">
                        </span>
                        恭喜！文档上传成功
                      </h1>
                      <p>
                        <span class="success-upload-tips">
                          文档通过审核后，即可收到财富值奖励
                        </span>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#">
                          查看已上传文档
                        </a>
                        &gt;
                      </p>
                      <a class="goon-upload log-xsend"
                      href="javascript:void function(){location.reload();}()">
                        继续上传
                      </a>
                    </div>
                  </div>
                  <div class="bd">
                    <div class="bd-wrap clearfix">
                      <h2>
                        <span class="title-emphasize">
                          高赏金
                        </span>
                        悬赏任务
                        <span class="title-tips">
                          上传悬赏话题，获取下载券奖励
                        </span>
                        <a class="title-more" target="_blank" href="#">
                          更多&gt;
                        </a>
                      </h2>
                      <div class="award-cards clearfix">
                        <ul class="award-ul-wrap clearfix">
                        </ul>
                        <div class="award-nav">
                          <span class="active">
                          </span>
                          <span>
                          </span>
                          <span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div id="task-upload-success-container" class="task-upload-success-container">
                  <h1>
                    <i class="iconfont">
                      
                    </i>
                    上传成功，话题被采纳后即可获得赏金
                  </h1>
                  <p>
                    话题被审核公开后，悬赏者将选择是否采纳，您在
                    <a href="#" target="_blank">
                      悬赏任务
                    </a>
                    中可以看到文档状态
                  </p>
                  <p class="nav">
                    <a href="#">
                      返回当前悬赏任务
                    </a>
                    <a href="#" target="_blank">
                      查看已上传的文档
                    </a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
     <#include "/mydocs/commonTemplate/sharejs/sharejs.ftl"/> 
     <script type="text/javascript" src="/51jobplusCore/scripts/pj_docsharein.js"></script>
     <script type="text/javascript" src="/51jobplusCore/scripts/pj_docClassInit.js"></script>
  </body>

</html>