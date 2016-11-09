<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
             文章分享-JobPlus
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
    <link rel="stylesheet" type="text/css" href="/uedit/themes/default/css/umeditor.css" charset="UTF-8">
  </head>
  <body>

    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
        <div class='bookdetail'>JobPlus知识库>文章分享</div> 
        <div class="upload-article"></div>
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              
              <div id="upload-widget" class="upload-widget ">
                
                <div id="upload-initarticle-container" style="display:block;">
                <div class="upload-steps clearfix">
					<ul>
						<li class="step-num active">1<span class="tips">输入标题</span>
						</li>
						<li class="step-bar"></li>
						<li class="step-num">2<span class="tips">编辑内容</span></li>
						<li class="step-bar"></li>
						<li class="step-num">3<span class="tips">发布成功</span></li>
					</ul>
				 </div>
                 <div class="mod mod-upload">
                   <div class="bdarticle" style="">
				         <div class="titleinfo">
	                       <span>【文章】</span>
	                       <span style='font-weight:500'>请输入文章标题</span>
						 </div>
	                     <div class="link after">
				            <input type="text" id="searcharticle" class='articledocument' placeholder="请输入标题...">
				            <p class="error" style="display: none;"><i class="tips_icon"></i></p>
							<div class="bar after">
								<a class="btnsure btn-blue" id="btnarticlesure" href="javascript:void(0)">确定</a>
								<a href="/sharein/searchuploadFile" class="btn-cancleupload">取消</a>
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
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关的文章;
                      </li>
                      <li>
                        2.您可以先拟好文章标题，点击确定进入文章编辑内容;
                      </li>
                      <li>
                        3.分享涉及侵权内容的文章将会被移除，严禁分享含有淫秽色情及低俗信息的文章;
                      </li>
                      <li>
                        4.文章分享有问题需要帮助？详情请查看知识库
                        <a href="/about/terms_service" target="_blank">服务条款
                        </a>和
                        <a href="/about/newbie_guide" target="_blank"> 帮助中心。</a>
                      </li>
                    </ol>
                  </div>
                </div>
				<form method="post" action="/myCenter/shareArticle" enctype="multipart/form-data" id='articleaddForm'>
                <div id="upload-article-container"  style="display:none;">
                  <div class="upload-steps clearfix">
                    <ul>
                      <li class="step-num active">1<span class="tips">输入标题</span></li>
                      <li class="step-bar active"></li>
                      <li class="step-num active">2<span class="tips">编辑内容</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">3<span class="tips">发布成功</span></li>
                    </ul>
                  </div>
                 <div class="nhd nedit-all-head">
                      <span class="act btn-submit-all-wrap logSend">
                        <a href="javascript:location.reload();"  class="add-all ml20  add-all-able" style="width:60px;">取消</a>
                        <a id="sharearticle" class="submit-all ml20  submit-all-able">
                          <i class="iconfont"></i>&nbsp;确认发布
                        </a>
                       </span>
                       <h3  class="item-message-all item-message-all-ok">
                                                                   请编辑文章内容，完成发布
                       </h3>
                  </div>
                  	  <div id="uploadcourse-list" class="uploadcourse-list">
                      <div class="editlayoutarticle" id="upload-item-0">
                        <dl>
                          <dd>
                            <div class="articlesedit  booksedit-bdr">
 
                              <table class="edit-table">
                                <tbody>
                                  <tr>
                                    <td style='height:25px;'>
                                      <textarea rows="1" class="zg-editor-input zu-seamless-input-origin-element"   title="在这里输入标题" id="pj-article-suggest-title-content"  placeholder="写下你的标题" name="title" data-searchtype='1'></textarea>
                                    </td>
                                    
                                  </tr>
                                  <tr>
                                    <td>
                                      <input type='hidden' name='intro' value=''>
                                      <div id='pj-article-suggest-detail-container'>
                                        <textarea title="在这里输入内容" id='arsummernote' class='arsummernote' placeholder='在这里输入内容'></textarea>
                                      </div>
                                      <div id='pj-article-warnmsg' style="display:none">
                                        <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                        <span class="item-msg-content" >问题字数太少了吧</span>
                                      </div>
                                    </td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                          </dd>
                          <dd>
                            <div class="articlesedit">
                              <table class="edit-table">
                                <tbody>
                                  <tr><td class="name"><i>*</i>分类:</td></tr>
                                  <tr> 
                                    <td>
                                      <ul>
                                        <li class="edit-class-sub topic-sort" style='margin-left:13px;height:424px;'>
                                          <ol>
                                            <li class="tltle display-n">一级分类</li></ol>
                                        </li>
                                        <li class="edit-class-sub edit-class-sec" style='height:263px;'>
                                          <ol></ol>
                                        </li>
                                      </ul>
                                      <div id="pj-modal-dialog-articleclassify" style="display:none">
                                         <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                         <span class="item-msg-content" style="">分类左右必选</span>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                  <td>
                                    <span class="article-ispublic">      
							           <input type='hidden' value='1' name='ispublic'>
							           <input type="radio" class="zg-addq-isanon" name='artIspublic' value='1' checked id='artIspublic' />
							           <label  for='artIspublic' style="margin-right:20px;">公开</label> 
							           <input type="radio" class="zg-addq-isanon" name='artIspublic' value='0' id='artIsprivate'/>
							           <label for='artIsprivate'>匿名</label>
							         </span>
								  </td>
								  </tr>
                                  <tr><td class="name articleicon">标签:</td></tr>
                                  <tr>
                                    <td>
                                      <input type="hidden" value="" name="articleclass">
                                      <div class="zm-tag-editor-editor zg-clear" style='margin-left:13px;width:301px;'>
                                        <div class="zg-inline" id="articleinputtags"></div>
                                        <div class="zm-tag-editor-command-buttons-wrap zg-left">
                                          <input style='display:none'/>
                                          <label for="docs" class="zg-icon icon-magnify"></label>
                                          <input type='hidden' name='currenttagval' value=''>
                                          <input class="zu-question-suggest-topic-input label-input-label" type="text" role="combobox" 
                                          aria-autocomplete="list" placeholder="搜索标签"  oninput="getTagsByCondition(this,'doc')">
                                          <label class="err-tip" style="display:none;">最多添加五个话题</label></div>
                                      </div>
                                      <span class="pj-warmprompt">
                                           <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                      </span>
                                     </td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                          </dd>
                          <div class="clearfix"></div>
                        </dl>
                      </div>
                    </div>
					
					<div class="upload-notice">
                    <h4>
                      温馨提示
                    </h4>
                    <ol>
                      <li>
                        1.文章编辑内容时，您可以随时更改文章标题，点击“电脑”标志按钮时可以全屏编辑，营造沉浸式的写作氛围;
                      </li>
                      <li>
                        2.文章分享一定要选择分类,方便记录、查询以及搜索;
                      </li>
                      <li>
                        3.多个标签之间要用逗号隔开,便于更多的好友发现查看;
                      </li>
                      <li>
                        4.文章分享有问题需要帮助？详情请查看知识库
                        <a href="/about/terms_service" target="_blank">服务条款</a>和
                        <a href="/about/newbie_guide" target="_blank">帮助中心。</a>
                      </li>
                    </ol>
                  </div>
				  </form>
				  
                </div>
              </div>
            </div>
          </div>
        </div>

     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
     <#include "/mydocs/commonTemplate/sharejs/sharejs.ftl"/> 
     <script type="text/javascript" src="/scripts/pj_articlesharein.js"></script>
     
  </body>

</html>