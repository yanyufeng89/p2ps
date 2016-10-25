<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
             课程分享
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
    <link rel="stylesheet" type="text/css" href="/css/pj_course.css">
  </head>
  <body>
    
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
   
        <div class='bookdetail'>JobPlus知识库>课程分享</div>
        <div class="upload-course"></div>
        <div class="body">
            <div class="main">
    
              <div id="upload-widget" class="upload-widget ">
               
                <div id="upload-initcourse-container" style="display: block;">
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
                   <div class="bdcourse" style="">
					     <div class="titleinfo">
							 <span>【课程】</span>
							 <span style='font-weight:500'>请输入分享的课程链接</span>
						 </div>
	                     <div class="link after">
	                        <input type='hidden' name='isverify' value='0'>
				            <input type="text" id="searchcourse" class='coursedocument' placeholder="请输入链接...">
				            <#--<input type="button" class="coursepreview" value="确定">-->
				            <span class="tocapture active coursepreview"></span>
				            <p class="error" style="display: none;">
				              <i class="tips_icon"></i>
				            </p>
							<div class="bar after">
								<a class="btn-disblue btnsure" id="btncoursesure" href="javascript:void(0)">确定</a>
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
                        <a href="#" target="_self">
                                                              服务条款
                        </a>和
                        <a href="#" target="_self">
                                                              帮助中心
                        </a>
                      </li>
                    </ol>
                  </div>
                </div>
			  <form method="post" action="/myCenter/shareCourse" enctype="multipart/form-data" id='courseaddForm'>
                <div id="upload-course-container" style="display:none;">
                  <div class="upload-steps clearfix">
                    <ul>
                      <li class="step-num active">1 <span class="tips">输入链接</span></li>
                      <li class="step-bar active"></li>
                      <li class="step-num active">2<span class="tips">补充信息</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">3<span class="tips">推荐成功</span></li>
                    </ul>
                  </div>
				  
                  <div class="nhd nedit-all-head">
                      <span class="act btn-submit-all-wrap logSend">
                        <a href="javascript:location.reload();"  class="add-all ml20  add-all-able" style="width:60px;">取消</a>
                        <a id="sharecourse" class="submit-all ml20  submit-all-able">
                          <i class="iconfont"></i>&nbsp;确认推荐
                        </a>
                       </span>
                       <h3  class="item-message-all item-message-all-ok">
                                                                         请补充课程信息，完成推荐
                       </h3>
                   </div>
				   <div id="uploadcourse-list" class="uploadcourse-list">
                      <input type='hidden' name='coursesurl' value=''>
                      <div class="editlayoutbook" id="upload-item-0">
                        <dl class="editupload">
                          <dd>
                            <div class="booksedit  booksedit-bdr">
                            
                              <!--<div class="item-index">1</div>
                              <div class="ui-imgBtn-delete" title="删除"  data-name="工作吧.xlsx" data-index="0"> 
                                  <b class="iconfont pr2"></b><span>删除</span>          
                              </div> -->
                              
                              <table class="edit-table">
                                <tbody>
                                  <tr>
                                    <td class="name">
                                                                                                      详情：
                                    </td>
                                    <td>
                                      <div class="zg-form-text-input add-course-title-form">
                                        
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name"><i>*</i>推荐：</td>
                                    <td>
                                        <textarea name="recommend" class="content" style="resize: none" placeholder='填写推荐理由'></textarea>
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
                                      <div id="pj-modal-dialog-courseclassify" style="display:none">
                                         <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                         <span class="item-msg-content" style="">分类左右必选</span>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name">标签:</td>
                                    <td>
                                      <input type="hidden" value="" name="coursesclass">
                                      <div class="zm-tag-editor-editor zg-clear">
                                        <div class="zg-inline" id="courseinputtags"></div>
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
                        1.分享课程一定要选择分类;
                      </li>
                      <li>
                        2.点击继续添加按钮,您还可以继续添加多个链接同时上传,操作快速、便捷、满足您的需求;
                      </li>
                      <li>
                        3.您还可以添加推荐以及多个标签,便于更多的好友发现查看;
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
     <script type="text/javascript" src="/scripts/pj_coursesharein.js"> </script>            
  </body>

</html>