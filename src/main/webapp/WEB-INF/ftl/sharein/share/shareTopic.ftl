<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>话题分享</title>
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_topic.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/jquery.autocomplete.css" charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/uedit/themes/default/css/umeditor.css" charset="UTF-8">
   </head>
  
  <body>
    <div class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/>
      <div id="bd">
        <div class='topicdetail'>聘加知识库>话题分享</div>
        <div class="upload-topic"></div>
        <div class="bd-wrap">
          <div class="body">
            <div class="main">
              <div id="upload-widget" class="upload-widget ">
                <div id="upload-inittopic-container">
                  <div class="upload-steps clearfix">
                    <ul>
                      <li class="step-num active">1
                        <span class="tips">输入话题</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">2
                        <span class="tips">补充信息</span></li>
                      <li class="step-bar"></li>
                      <li class="step-num">3
                        <span class="tips">分享成功</span></li>
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
                        <input type="text" id="js-before-ask" class='txtdocument' placeholder="请输入你的问题" value='' autocomplete="off" data-searchtype='0' />
                        <input type='button' class='topicpreview iwanttoask' value='确定'>
                        <span class="capture-loading" style="display: none;"></span>
                        <p class="error" style="display: none;">
                          <i class="tips_icon"></i>请输入话题</p>
                        <div class="ask-renderer" role='listbox' id='ask-renderer'>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="upload-intro-query clearfix">
                    <h4>
                    话题推荐
                     </h4>
                     <span class='pj-encourage'>每成功分享一个话题即可获得一定的财富奖励哦!</span>
                     <ul>
                      <li class="">
                        搜索头条
                      </li>
                      <li class="query-box">
                       新闻热点
                      </li>
                      <li class="">
                      科技创新
                      </li>
                      <li class="query-box">
                        投资理财
                      </li>
                      <li class="">
                       军事历史
                      </li>
                      <li class="query-box">
         IT互联网
                      </li>
                      <li class="">
                       社会与法
                      </li>
                      <li class="query-box">
                       体育运动
                      </li>
                      <li class="">
                       娱乐媒体
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
                        1.话题分享应遵循客观、真实、简洁、明确、规范的原则,提问尽可能简洁明了,尽量避开太广泛的问题;
                      </li>
                      <li>
                        2.分享和已有问题完全重复的问题将会被移除,为避免重复,提问前可想进行搜索;
                      </li>
                      <li>
                        3.严禁分享含有辱骂他人,淫秽色情以及低俗信息等话题;
                      </li>
                      <li>
                        4.提问有问题需要帮助？查看
                        <a href="#" target="_blank">
                                                                 知识库帮助
                        </a>
                      </li>
                    </ol>
                  </div>
                </div>
                <form method='post' action='/51jobplusCore/topics/add' enctype="multipart/form-data" id='topicaddForm' onkeydown="if(event.keyCode==13)return false;" >
                  <input type='hidden' name='content' value=''>
                  <input type='hidden' name='topicsclass' value=''>
                  <input type='hidden' name='ispublic' value=''>
                  <div id="upload-topicfiles-container" style="display:none;">
                    <div class="upload-steps clearfix">
                      <ul>
                        <li class="step-num active">1
                          <span class="tips">分享话题</span></li>
                        <li class="step-bar active"></li>
                        <li class="step-num active">2
                          <span class="tips">完善信息</span></li>
                        <li class="step-bar"></li>
                        <li class="step-num">3
                          <span class="tips">完成分享</span></li>
                      </ul>
                    </div>
                    
                    <div class="nhd nedit-all-head">
                      <span class="act btn-submit-all-wrap logSend">
                        <a href="" id="btn-cancel-all" class="add-all ml20  add-all-able">取消</a>
                        <a id="btn-public-all" class="submit-all ml20  submit-all-able">
                          <i class="iconfont"></i>&nbsp;发布</a></span>
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
                                    <td class="name"><i>*</i>简介：</td>
                                    <td>
                                      <i class="tr-inline-icon z-ico-textedit"></i>
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
                                      <input type="hidden" name="downvalue" value="0">
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
                                         <label for="public0">匿名</label>
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
                                          <ol>
                                            <li class="tltle display-n">一级分类</li></ol>
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
                                          <label for="docs" class="zg-icon icon-magnify"></label>
                                          <input type='hidden' name='currenttagval' value=''>
                                          <input class="zu-question-suggest-topic-input label-input-label" type="text" role="combobox" aria-autocomplete="list" aria-label="搜索标签" placeholder="搜索标签" id="searchTopic" oninput="getTagsByCondition(this,'topic')">
                                          <label class="err-tip" style="display:none;">最多添加五个话题</label></div>
                                      </div>
                                      <span class="topic-error">
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
                        <li>1.上传涉及侵权内容的话题将会被移除。如何判断话题是否侵权？查看
                          <a href="#" target="_blank">文库协议</a>和
                          <a href="#" target="_blank">用户规则</a></li>
                        <li>2.上传有问题需要帮助？查看
                          <a href="#" target="_blank">文库帮助</a>和
                          <a href="#" target="_blank">意见反馈</a></li>
                        <li>3.为营造绿色网络环境，严禁上传含有淫秽色情及低俗信息等话题，让我们一起携手共同打造健康文库</li></ol>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/>
      <#include "/mydocs/commonTemplate/sharejs/sharejs.ftl"/> 
      <script type="text/javascript" src="/51jobplusCore/scripts/pj_topicsharein.js"></script>
      <script type="text/javascript" src="/51jobplusCore/scripts/jquery.autocomplete.js"></script>
  </body>

</html>