<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
             分享书籍
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/jquery.pinwheel-0.1.0.css">
    <link rel="stylesheet" type="text/css" href="/51jobplusCore/css/pj_book.css">
  </head>
  <body>
    
    <div  class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
        <div class='bookdetail'>JobPlus知识库>分享书籍</div>
        <div class="upload-book"></div>
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              <div id="upload-widget" class="upload-widget ">
                <div id="upload-initbook-container" style="display: block;">
                <div class="upload-steps clearfix">
					<ul>
						<li class="step-num active">1<span class="tips">输入书名</span>
						</li>
						<li class="step-bar"></li>
						<li class="step-num">2<span class="tips">补充信息</span>
						</li>
						<li class="step-bar"></li>
						<li class="step-num">3<span class="tips">分享成功</span>
						</li>
					</ul>
				 </div>
                 <div class="mod mod-upload">
                   <div class="bdbook" style="">
	                     <a class="titleinfo">【书籍】</a>
	                     <span class="titlename">请输入分享的书籍名称</span>
	                     <div class="link after">
				            <input type="text" id="searchbook" class='bookdocument' placeholder="请选择书名...">
				            <input type="button" class="bookpreview" value="确定">
				            <span class="capture-loading" style="display: none;"></span>
				            <p class="error" style="display:none;">
                              <i class="tips_icon"></i>请输入书名
                            </p>
				            <div class='ask-renderer' id='book-renderer'  role='listbox'>
				              
				            </div>
	                     </div>
	                    
	                    </div>
                  </div>
                  <div class="upload-intro-query clearfix">
                    <h4>
                            书籍推荐
                    </h4>
                    <span class='pj-encourage'>
                      每成功分享一本书籍即可获得一定的财富奖励哦!
                     </span>
                    <ul>
                      <li class="">
                        名人传记
                      </li>
                      <li class="query-box">
                     政治历史
                      </li>
                      <li class="">
                       科技创新
                      </li>
                      <li class="query-box">
                       教材教辅
                      </li>
                      <li class="">
                     经济管理
                      </li>
                      <li class="query-box">
                        社会科学
                      </li>
                      <li class="">
                    家庭教育
                      </li>
                      <li class="query-box">
                      旅游地图
                      </li>
                      <li class="">
                       杂志新阅
                      </li>
                      <li class="query-box">
                        烹饪美食
                      </li>
                    </ul>
                  </div>
                  <div class="upload-notice">
                    <h4>
                      温馨提示
                    </h4>
                    <ol>
                      <li>
                        1.书籍分享可直接搜索书籍名称;
                      </li>
                      <li>
                        2.分享和已分享的书籍重复将会被移除,为避免重复,分享前可先进行搜索;
                      </li>
                      <li>
                        3.书籍分享有问题需要帮助？查看
                        <a href="#" target="_blank">
                      知识库帮助
                        </a>
                      </li>
                    </ol>
                  </div>
                </div>
                  <form method="post" action="/51jobplusCore/books/shareBook" enctype="multipart/form-data" onkeydown="if(event.keyCode==13)return false;" >
                  <div id="upload-book-container" style="display:none">
                    <div class="upload-steps clearfix">
                      <ul>
                        <li class="step-num active">1
                          <span class="tips">输入书名</span></li>
                        <li class="step-bar active"></li>
                        <li class="step-num active">2
                          <span class="tips">补充信息</span></li>
                        <li class="step-bar"></li>
                        <li class="step-num">3
                          <span class="tips">分享成功</span></li>
                      </ul>
                    </div>
                    
                    <div class="nhd nedit-all-head">
                      <span class="act btn-submit-all-wrap logSend">
                        <a id="sharebook" class="submit-all ml20  submit-all-able">
                          <i class="iconfont"></i>&nbsp;确认分享
                        </a>
                       </span>
                       <h3  class="item-message-all item-message-all-ok">
                        <span>请补充书籍信息，完成分享
                        <br>
                        <span class="item-message-all-subtitle">审核通过后，您将获得
                          <span class="item-reward-wealth">1财富值</span>的奖励
                        </span>
                        </span>
                       </h3>
                    </div>
                    
                    <div id="uploadbook-list" class="uploadbook-list">
                      <input type='hidden' name='bookid' value=''>
                      <div class="editlayoutbook" id="upload-item-0">
                        <dl class="editupload">
                          <dd>
                            <div class="booksedit  booksedit-bdr">
                              <table class="edit-table">
                                <tbody>
                                  <tr>
                                    <td class="name">
                                                                                                        详情：
                                    </td>
                                    <td>
                                      <div class="zg-form-text-input add-question-title-form">
                                        
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name"><i>*</i>推荐：</td>
                                    <td>
                                        <textarea name="recommend" class="content" style="resize: none" placeholder='填写推荐理由'></textarea>
                                        <b class="ic ic-msg" style="background-position: -47px -144px; display:none"></b>
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
                                      <div id="pj-modal-dialog-bookclassify" style="display:none">
                                         <b class="ic ic-msg" style="background-position: -47px -144px;"></b>
                                         <span class="item-msg-content" style="">分类左右必选</span>
                                      </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="name">标签:</td>
                                    <td>
                                      <input type="hidden" value="" name="shareclass">
                                      <div class="zm-tag-editor-editor zg-clear">
                                        <div class="zg-inline" id="bookinputtags"></div>
                                        <div class="zm-tag-editor-command-buttons-wrap zg-left">
                                          <label for="docs" class="zg-icon icon-magnify"></label>
                                          <input type='hidden' name='currenttagval' value=''>
                                          <input class="zu-question-suggest-topic-input label-input-label" type="text" role="combobox" aria-autocomplete="list" aria-label="搜索标签" placeholder="搜索标签" id="searchBook" oninput="getTagsByCondition(this,'book')">
                                          <label class="err-tip" style="display:none;">最多添加五个话题</label></div>
                                      </div>
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
                        1.书籍分享一定要选择分类及标签,方便记录、查询以及搜索;
                      </li>
                      <li>
                        2.多个标签之间要用逗号隔开,便于区分;
                      </li>
                      <li>
                        3.匿名分享的书籍将不能查找分享者是谁,系统自动添加前缀为匿名用户;
                      </li>
                    </ol>
                  </div>
                  </div>
                </form>
                </div>
             </div>
            </div>
          </div>
      </div>
     <#include "/mydocs/commonTemplate/topandtail/tail.ftl"/> 
     <div class='headiconintotem' style='display:none'></div>
     <#include "/mydocs/commonTemplate/sharejs/sharejs.ftl"/> 
     <script type="text/javascript" src="/51jobplusCore/scripts/pj_booksharein.js"></script>
  </body>

</html>