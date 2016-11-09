<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
            站点分享-JobPlus
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
  </head>
  <body>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
       <div class='bookdetail'>JobPlus知识库>站点分享</div>
        <div class="upload-site"></div>
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              <div id="upload-widget" class="upload-widget ">
                <div id="upload-initsite-container" style="display: block;">
                <div class="upload-steps clearfix">
					<ul>
						<li class="step-num active">1<span class="tips">输入链接</span></li>
						<li class="step-bar"></li>
						<li class="step-num">2<span class="tips">补充信息</span></li>
						<li class="step-bar"></li>
						<li class="step-num">3<span class="tips">推荐成功</span></li>
					</ul>
				 </div>
                 <div class="mod mod-upload">
                   <div class="bdarticle" style="">
				         <div class="titleinfo">
	                       <span>【站点】</span>
	                       <span style='font-weight:500'>请输入分享的站点链接</span>
						 </div>
	                     <div class="link after">
				            <input type="text" id="searchsite" class='coursedocument' placeholder="请输入链接...">
				            <#--<input type="button" class="sitepreview" value="确定">-->
				            <span class="tocapture active sitepreview"></span>
				            <p class="error" style="display: none;">
				              <i class="tips_icon"></i>
				            </p>
							<div class="bar after">
								<a class="btn-disblue btnsure" id="btnsitesure" href="javascript:void(0)">确定</a>
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
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关的站点;
                      </li>
                      <li>
                        2.输入框里粘贴你要分享的网站链接, 点击"√"进行校对无误之后确定分享;
                      </li>
                      <li>
                        3.禁止分享非法，含有淫秽色情及低俗信息的站点链接;
                      </li>
                      <li>
                        4.站点分享有问题需要帮助？详情请查看知识库
                        <a href="/about/terms_service" target="_blank">服务条款</a>和
                        <a href="/about/newbie_guide" target="_blank">帮助中心。</a>
                      </li>
                    </ol>
                  </div>
                </div>
			   <form method="post" action="/myCenter/shareSite" enctype="multipart/form-data" id='siteaddForm'>
                <div id="upload-site-container"  style="display:none;">
                  <div class="upload-steps clearfix">
                    <ul>
                      <li class="step-num active">1<span class="tips">输入链接</span></li>
                      <li class="step-bar active"></li>
                      <li class="step-num active">2<span class="tips">补充信息</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">3<span class="tips">推荐成功</span></li>
                    </ul>
                  </div>
				  
                 <div class="nhd nedit-all-head">
                      <span class="act btn-submit-all-wrap logSend">
                        <a href="javascript:location.reload();"  class="add-all ml20  add-all-able" style="width:60px;">取消</a>
                        <a id="sharesite" class="submit-all ml20  submit-all-able">
                          <i class="iconfont"></i>&nbsp;确认推荐
                        </a>
                       </span>
                       <h3  class="item-message-all item-message-all-ok">
                                                                     请补充站点信息，完成推荐
                       </h3>
                   </div>
				   
                  <div id="uploadsite-list" class="uploadcourse-list">
                      <input type='hidden' name='siteurl' value=''>
                      <input type='hidden' name='title' value=''>
                      <div class="editlayoutbook" id="upload-item-0">
                        <dl class="editupload">
                          <dd>
                            <div class="booksedit  booksedit-bdr">
                            
                              <table class="edit-table">
                                <tbody>
                                 <tr>
                                    <td class="name"><i>*</i>站点：</td> 
                                    <td> 
                                      <div class="add-title-form">
                                          
                                      </div>
                                    </td>
                                   </tr>
                                   <tr>
                                    <td class="name"><i>*</i>简介：</td> 
                                    <td> 
	                                    <textarea name="intro" class="add-brief-form"></textarea>
	                                    <b class="ic ic-msg" style="background-position: -47px -144px;display:none"></b>
                                        <span class="item-msg-content" style='display:none'>简介必填</span>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name"><i>*</i>推荐：</td>
                                    <td>
                                        <textarea name="recommend" class="content"  placeholder='填写推荐理由'></textarea>
                                         <b class="ic ic-msg" style="background-position: -47px -144px;display:none"></b>
                                         <span class="item-msg-content" style='display:none'>推荐理由必填</span>
                                    </td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                          </dd>
                          <dd>
                            <div class="booksedit">
                              <table class="edit-table">
                                <tbody>
                                  <tr>
                                    <td class="name">
                                         <i>*</i>分类:
                                    </td>
                                    <td>
                                      <ul>
                                        <li class="edit-class-sub topic-sort">
                                          <ol>
                                            <li class="tltle display-n">一级分类</li></ol>
                                        </li>
                                        <li class="edit-class-sub edit-class-sec">
                                          <ol></ol>
                                        </li>
                                      </ul>
                                      <div id="pj-modal-dialog-siteclassify" style="display:none">
                                         <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                         <span class="item-msg-content" style="">分类左右必选</span>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name">标签:</td>
                                    <td>
                                      <input type="hidden" value="" name="siteclass">
                                      <div class="zm-tag-editor-editor zg-clear">
                                        <div class="zg-inline" id="siteinputtags"></div>
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
                        1.站点分享时必须对该站点填写推荐理由，可以让别人更加清晰的了解该站点的亮点以及相关内容;
                      </li>
                      <li>
                        2.分享站点一定要选择分类,方便记录、查询以及搜索;
                      </li>
                      <li>
                        3.多个标签之间要用逗号隔开，便于更多的好友发现查看;
                      </li>
                       <li>
                        4.站点分享有问题需要帮助？详情请查看知识库
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
    <script type="text/javascript" src="/scripts/pj_sitesharein.js"></script>
  </body>

</html>