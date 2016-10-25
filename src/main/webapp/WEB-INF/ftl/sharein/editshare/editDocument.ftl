<!DOCTYPE html>
<html class="expanded">
  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>
             上传文档
    </title> 
    <#include "/mydocs/commonTemplate/shareknowledge/shareknowledge.ftl"/>
    <link rel="stylesheet" type="text/css" href="/css/pj_doc.css">
  </head>
  <body>
   
    <div id="doc" class="page">
      <#include "/mydocs/commonTemplate/topandtail/top.ftl"/> 
        <div class='docdetail'>JobPlus知识库>上传文档</div>
        <div class="upload-doc"></div>
        <div class="bd-wrap">
        <div class="body">
            <div class="main">
              <div id="upload-widget" class="upload-widget ">
               <form method="post"  enctype="multipart/form-data" id ='test11form' action='/docs/upload'>
                  <input type='hidden' name='docId' value='${record.id}'>
                  <div id="upload-files-container">
	                  <div class="upload-steps clearfix">
	                    <ul>
	                      <li class="step-num active">1<span class="tips">上传文档</span></li>
	                      <li class="step-bar active"></li>
	                      <li class="step-num active">2<span class="tips">完善信息</span></li>
	                      <li class="step-bar"></li>
	                      <li class="step-num">3<span class="tips">完成分享</span></li>
	                    </ul>
	                  </div>
	                  <div class="nhd nedit-all-head">
	                    <span class="act btn-submit-all-wrap logSend">
	                      
	                      <a  id="btn-submit-all" class="submit-all ml20  submit-all-able">
	                        <i class="iconfont"></i>&nbsp;确认上传
	                      </a>
	                      </span>
	                    <h3 id="item-message-all" class="item-message-all item-message-all-ok">
	                      <span>
	                                                                    文档更新                       
	                      </span>
	                    </h3>
	                  </div>
	                  
	                  <div id="upload-list" class="upload-list">
	                     <div class="editlayoutbook" id="upload-item-0">
						  <dl class="editupload">
						    <dd>
						      <div class="booksedit  booksedit-bdr">
						        <table class="edit-table">
						          <tbody>
						            <tr>
						              <td class="name">
						                <i>*</i>标题：</td>
						              <td>	
						                <input type="text" name="title" class="titles" value="${record.title}">
										<b class="ic ic-msg"></b>
										<span class="item-msg-content">建议您结合文档正文完善文档标题信息</span>
								      </td>
						            </tr>
						            <tr>
						              <td class="name">简介：</td>
						              <td>
						                <textarea name="description" class="bookcontent" style="resize: none">${record.description}</textarea></td>
						            </tr>
						            <tr>
						              <td class="name">售价：</td>
						              <td>
						                <input type="hidden" name="downvalue" value="${record.downvalue}">
						                <select>
							               <option value="0"  <#if (record.downvalue==0)>selected="selected"</#if>>免财富值</option>
										   <option value="1"  <#if (record.downvalue==1)>selected="selected"</#if>>1财富值</option>
										   <option value="2"  <#if (record.downvalue==2)>selected="selected"</#if>>2财富值</option>
										   <option value="5"  <#if (record.downvalue==5)>selected="selected"</#if>>5财富值</option>
										   <option value="8"  <#if (record.downvalue==8)>selected="selected"</#if>>8财富值</option>
										   <option value="10" <#if (record.downvalue==10)>selected="selected"</#if>>10财富值</option>
										   <option value="20" <#if (record.downvalue==20)>selected="selected"</#if>>20财富值</option>
						                </select>
						                
										  <span class='ispublic'>
											<input type="hidden" name='ispublic' value='${record.ispublic}'>
											<input type="radio" class="zg-addq-isanon" name="public0" id="gpublic0" value='1'  <#if (record.ispublic==1)>checked</#if>>
											<label for="gpublic0" style="margin-right:20px;">公开</label>
											<input type="radio" class="zg-addq-isanon" name="public0" id="spublic1" value='0' <#if (record.ispublic==0)>checked</#if>>
											<label for="spublic1">私有</label>
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
						                  <#if (record.doctype)??>
						                     <#assign doctype=record.doctype?split(',')/>
							                  <li class="edit-class-sub topic-sort">
							                    <ol>
							                      <#if (parentTagsList)??>
							                        <#assign p=doctype[0]?split(':')/>
							                        <#assign pp=p[0]/>
							                        <#list parentTagsList as plist>
								                      <li  value="${plist.typeid}" data-id="${plist.typeid}" <#if (plist.typeid?string==pp)> class="cur"</#if>>${plist.typename}</li>
								                    </#list>
							                      </#if>
							                     </ol>
							                  </li>
							                  <li class="edit-class-sub edit-class-sec">
							                    <ol>
							                      <#if (childTagsList)??>
							                        <#assign c=doctype[1]?split(':')/>
							                        <#assign cc=c[0]/>
							                        <#list childTagsList as clist>
								                      <li  value="${clist.typeid}" data-id="${clist.typeid}" <#if (clist.typeid?string==cc)> class="cur"</#if>>${clist.typename}</li>
								                    </#list>
							                      </#if>
							                      <input type="hidden" name="doctype" value="${record.doctype}">
							                    </ol>
							                  </li>
						                  </#if>
						                </ul>
						                <div class="docclassify" style="display:none">             
						                   <b class="ic ic-msg" style="background-position: -47px -144px;"></b>               
						                    <span class="item-msg-content" style="">分类左右必选</span>         
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td class="name">标签：</td>
						              <td>
						                <input type="hidden"  value='doctype' name='doctypemark'>
						                <input type="hidden" value="" name="docclass">
						                <div class="zm-tag-editor-editor zg-clear">
						                  <div class="zg-inline">
							                  <#if (record.docclass)??&&(record.docclass?length!=0)>
							                      <#list record.docclass?split(',') as doclist>
							                         <div class="zm-tag-editor-edit-item">
							                          <#assign ss=doclist?split(':')/>
							                              <span>${ss[1]}</span>
                                                          <a id="${ss[0]}" data-name="${ss[1]}" class="zg-r3px zm-tag-editor-remove-button" name="remove"></a>
							                         </div>
							                      </#list>
							                  </#if>
						                  </div>
						                  <div class="zm-tag-editor-command-buttons-wrap zg-left">
						                   <#if (record.docclass)??&&(record.docclass?length!=0)>
						                    <#assign len=record.docclass?split(',')/>
						                      <#if (len?size == 5)>
							                    <label for="docs" class="zg-icon icon-magnify" style="display:none"></label>
							                    <input style="display:none" class="zu-question-suggest-topic-input label-input-label" type="text" role="combobox" aria-autocomplete="list" aria-label="搜索标签" placeholder="搜索标签" oninput="getTagsByCondition(this,'doc')">
							                  <#else>
							                    <label for="docs" class="zg-icon icon-magnify"></label>
							                    <input class="zu-question-suggest-topic-input label-input-label" type="text" role="combobox" aria-autocomplete="list" aria-label="搜索标签" placeholder="搜索标签" oninput="getTagsByCondition(this,'doc')">
						                      </#if>
										   <#else>
										      <label for="docs" class="zg-icon icon-magnify"></label>
						                      <input class="zu-question-suggest-topic-input label-input-label" type="text" role="combobox" aria-autocomplete="list" aria-label="搜索标签" placeholder="搜索标签" oninput="getTagsByCondition(this,'doc')">
						                   </#if>
										   <label class="err-tip" style="display:none;">最多添加五个话题</label>
										  </div>
						                </div>
						                <span class="pj-warmprompt">至少添加一个标签</span></td>
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
                      <li>1.上传涉及侵权内容的文档将会被移除。如何判断文档是否侵权？查看
                        <a href="#" target="_self">文库协议</a>和
                        <a href="#" target="_self">用户规则</a></li>
                      <li>2.上传有问题需要帮助？查看
                        <a href="#" target="_self">文库帮助</a>和
                        <a href="#" target="_self">意见反馈</a></li>
                      <li>3.为营造绿色网络环境，严禁上传含有淫秽色情及低俗信息等文档，让我们一起携手共同打造健康文库</li></ol>
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
  </body>

</html>