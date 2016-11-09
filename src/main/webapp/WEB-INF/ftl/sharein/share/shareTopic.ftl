<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>话题分享-JobPlus</title>
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
    <link rel="stylesheet" type="text/css" href="/css/pj_topic.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/jquery.autocomplete.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/uedit/themes/default/css/umeditor.css" charset="UTF-8">
   </head>
  
  <body>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
        <div class='topicdetail'>JobPlus知识库>话题分享</div>
        <div class="upload-topic"></div>
        <div class="bd-wrap">
          <div class="body">
            <div class="main">
              <div id="upload-widget" class="upload-widget ">
                <div id="upload-inittopic-container">
                  <div class="upload-steps clearfix">
                    <ul>
                      <li class="step-num active">1<span class="tips">输入话题</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">2<span class="tips">补充信息</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">3<span class="tips">发布成功</span></li>
                    </ul>
                  </div>
                  <div class="mod mod-upload">
                    <div class="bdtopic" style="">
                      <div class="titleinfo">
                        <span>【话题】</span>
                        <span style='font-weight:500'>请输入话题</span>
                      </div>
                      <div class="link after">
                        <input type='hidden' name='pj-autocomplete' value=''>
                        <input type="text" id="js-before-ask" class='txttopic' placeholder="请输入你的问题" value='' autocomplete="off" data-searchtype='0' />
                        <#--<input type='button' class='topicpreview iwanttoask' value='确定'>-->
                        <span class="capture-loading" style="display: none;"></span>
                        <p class="error" style="display: none;">
                          <i class="tips_icon"></i>请输入话题</p>
                        <div class="ask-renderer" role='listbox' id='ask-renderer'>
                        </div>
                        <div class="bar after">
						    <a class="btnsure btn-blue" id='btntopicsure' href='javascript:void(0)'>确定</a>
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
                        1.JobPlus专注企业知识服务平台，请不要分享与之无关的话题;
                      </li>
                      <li>
                        2.话题分享应遵循客观、真实、简洁、明确、规范的原则，提问尽可能简洁明了，尽量避免太宽泛的问题;
                      </li>
                      <li>
                        3.输入你的话题名称,  话题名称尾部要加"?" 禁止提问辱骂他人、含有淫秽色情及低俗信息等话题;
                      </li>
                      <li>
                        4.提问有问题需要帮助？详情请查看知识库
                        <a href="/about/terms_service" target="_blank">服务条款</a>和
                        <a href="/about/newbie_guide" target="_blank">帮助中心。</a>
                      </li>
                    </ol>
                  </div>
                </div>
                <form method='post' action='/topics/add' enctype="multipart/form-data" id='topicaddForm'>
                  <input type='hidden' name='content' value=''>
                  <input type='hidden' name='topicsclass' value=''>
                  <input type='hidden' name='ispublic' value=''>
                  <div id="upload-topicfiles-container" style="display:none;">
                    <div class="upload-steps clearfix">
                      <ul>
                        <li class="step-num active">1<span class="tips">分享话题</span></li>
                        <li class="step-bar active"></li>
                        <li class="step-num active">2<span class="tips">完善信息</span></li>
                        <li class="step-bar"></li>
                        <li class="step-num">3<span class="tips">发布成功</span></li>
                      </ul>
                    </div>
                    
                    <div class="nhd nedit-all-head">
                      <span class="act btn-submit-all-wrap logSend">
                        <a href="javascript:location.reload();" id="btn-cancel-all" class="add-all ml20  add-all-able" style='width:60px;'>取消</a>
                        <a id="btn-public-all" class="submit-all ml20  submit-all-able">
                          <i class="iconfont"></i>&nbsp; 确认发布
                        </a>
                       </span>
                       <h3 id="item-message-all" class="item-message-all item-message-all-ok">
                                                               请补充话题信息，完成上传
                       </h3>
                    </div>
                    
                    <div id="uploadtopic-list" class="uploadtopic-list">
                      <div class="editlayoutbook" id="upload-item-0">
                        <dl class="editupload">
                          <dd>
                            <div class="booksedit  booksedit-bdr">
                              <table class="edit-table">
                                <tbody>
                                  <tr>
                                    <td class="name">
                                      <i>*</i>问题：</td>
                                    <td>
                                      <textarea rows="1" class="zg-editor-input zu-seamless-input-origin-element" 
								           title="在这里输入问题" id="pj-question-suggest-title-content"  placeholder="写下你的问题" name="title" data-searchtype='1'>
								      </textarea>
                                      <div class="question-suggest-ac-wrap" id="pj-question-suggest-ac-wrap" style="display: none;">
                                        <div class="ac-renderer">
                                          
                                          
                                        </div>
                                      </div>
                                      <div id='pj-modal-dialog-warnmsg-wrapper' style="display:none">
                                        <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                        <span class="item-msg-content" >问题字数太少了吧</span>
                                      </div>
                                    </td>
                                  </tr>
              
                                  <tr>
                                    <td class="name"><i>*</i>说明：</td>
                                    <td>
                                      <div class='topic-edit'>
                                         <i class="tr-inline-icon"></i>
                                      </div>
                                      <div id='pj-question-suggest-detail-container'>
                                        <textarea title="在这里输入问题简介" id='summernote' class='summernote' placeholder='在这里输入问题简介'></textarea>
                                      </div>
                                      <div id="pj-modal-dialog-detail-wrapper" style="display:none">
                                         <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                         <span class="item-msg-content" style="">问题简介必填</span>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name">悬赏：</td>
                                    <td>
                                      <input type="hidden" name="rewardValue" value="0">
                                      <select>
                                        <option value="0">免财富值</option>
                                        <option value="1">1财富值</option>
                                        <option value="2">2财富值</option>
                                        <option value="5">5财富值</option>
                                        <option value="8">8财富值</option>
                                        <option value="10">10财富值</option>
                                        <option value="20">20财富值</option>
                                       </select>
                                       <span class='ispublic'>
                                         <input type="checkbox" value="1" class="zg-addq-isanon" name="ispublic" id="public0">
                                         <label for="public0" style="vertical-align: middle;">匿名</label>
                                       </span>
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
                                      <i>*</i>分类:</td>
                                    <td>
                                      <ul>
                                        <li class="edit-class-sub topic-sort">
                                          <ol><li class="tltle display-n">一级分类</li></ol>
                                        </li>
                                        <li class="edit-class-sub edit-class-sec">
                                          <ol></ol>
                                        </li>
                                      </ul>
                                      <div id="pj-modal-dialog-classify" style="display:none">
                                         <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                         <span class="item-msg-content" style="">分类左右必选</span>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name"><i>*</i>标签:</td>
                                    <td>
                                      <input type="hidden" value="" name="docclass">
                                      <div class="zm-tag-editor-editor zg-clear">
                                        <div class="zg-inline" id='inputtags'></div>
                                        <div class="zm-tag-editor-command-buttons-wrap zg-left">
                                          <input style='display:none'/>
                                          <label for="docs" class="zg-icon icon-magnify"></label>
                                          <input type='hidden' name='currenttagval' value=''>
                                          <input class="zu-question-suggest-topic-input label-input-label" type="text" role="combobox" aria-autocomplete="list" aria-label="搜索标签" placeholder="搜索标签" id="searchTopic" oninput="getTagsByCondition(this,'topic')">
                                          <label class="err-tip" style="display:none;">最多添加五个话题</label></div>
                                      </div>
                                      <span class="pj-warmprompt">
                                           <b class="ic ic-msg" style="background-position: -47px -144px;"></b>至少添加一个标签
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
                    <div class="uploadinfobook">
                      <ol>
                        <li class="doc-type"></li>
                        <li>1.提问时必须对该话题进行说明，可以让别人更加清晰的了解该话题的相关内容,根据内容说明可以随时更改话题名称，话题名称尾部要加"?";</li>
                        <li>2.您可以免财富值提问话题，也可以悬赏选择1到20点财富值提问；同时可以对话题进行设置是否"匿名"提问;</li>
                        <li>3.话题提问时必须添加分类，详细的分类，方便更好的查找话题内容;</li>
                        <li>4.添加话题标签，更精准的搜索目标话题,多个标签之间要用逗号隔开，便于区分;</li>
                        <li>
                        5.提问有问题需要帮助？详情请查看知识库
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
      <script type="text/javascript" src="/scripts/pj_topicsharein.js"></script>
      <script type="text/javascript" src="/scripts/jquery.autocomplete.js"></script>
  </body>

</html>