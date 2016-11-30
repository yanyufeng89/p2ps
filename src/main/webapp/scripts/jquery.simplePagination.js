/**
* simplePagination.js v1.5
* A simple jQuery pagination plugin.
* http://flaviusmatis.github.com/simplePagination.js/
*
* Copyright 2012, Flavius Matis
* Released under the MIT license.
* http://flaviusmatis.github.com/license.html
*/
var userInfo;
(function($){

	var methods = {
		init: function(options) {
			var o = $.extend({
				items: 1,
				itemsOnPage: 1,
				pages: 0,
				displayedPages:10,
				edges: 2,
				currentPage: 1,
				hrefTextPrefix: '#page-',
				hrefTextSuffix: '',
				prevText: '上一页',
				nextText: '下一页',
				ellipseText: '&hellip;',
				cssStyle: 'light-theme',
				moduleType:'bookcol',
				selectOnClick: true,
				onPageClick: function(pageNumber, event) {

				},
				onInit: function() {

				}
			}, options || {});

			var self = this;

			o.pages = o.pages ? o.pages : Math.ceil(o.items / o.itemsOnPage) ? Math.ceil(o.items / o.itemsOnPage) : 1;
			o.currentPage = o.currentPage - 1;
			o.halfDisplayed = o.displayedPages / 2;

			this.each(function() {
				self.addClass(o.cssStyle + ' simple-pagination').data('pagination', o);
				methods._draw.call(self);
			});

			o.onInit();

			return this;
		},

		selectPage: function(page) {
			methods._selectPage.call(this, page - 1);
			return this;
		},

		prevPage: function() {
			var o = this.data('pagination');
			if (o.currentPage > 0) {
				methods._selectPage.call(this, o.currentPage - 1);
			}
			return this;
		},

		nextPage: function() {
			var o = this.data('pagination');
			if (o.currentPage < o.pages - 1) {
				methods._selectPage.call(this, o.currentPage + 1);
			}
			return this;
		},

		getPagesCount: function() {
			return this.data('pagination').pages;
		},

		getCurrentPage: function () {
			return this.data('pagination').currentPage + 1;
		},

		destroy: function(){
			this.empty();
			return this;
		},

		redraw: function(){
			methods._draw.call(this);
			return this;
		},

		disable: function(){
			var o = this.data('pagination');
			o.disabled = true;
			this.data('pagination', o);
			methods._draw.call(this);
			return this;
		},

		enable: function(){
			var o = this.data('pagination');
			o.disabled = false;
			this.data('pagination', o);
			methods._draw.call(this);
			return this;
		},

		_draw: function() {
			var	o = this.data('pagination'),
				interval = methods._getInterval(o),
				i;

			methods.destroy.call(this);

			var $panel = this.prop("tagName") === "UL" ? this : $('<ul></ul>').appendTo(this);

			// Generate Prev link
			if (o.prevText&&o.currentPage!=0) {
				methods._appendItem.call(this, o.currentPage - 1, {text: o.prevText, classes: 'prev'});
			}

			// Generate start edges
			/*if (interval.start > 0 && o.edges > 0) {
				var end = Math.min(o.edges, interval.start);
				for (i = 0; i < end; i++) {
					methods._appendItem.call(this, i);
				}
				if (o.edges < interval.start && (interval.start - o.edges != 1)) {
					$panel.append('<li class="disabled"><span class="ellipse">' + o.ellipseText + '</span></li>');
				} else if (interval.start - o.edges == 1) {
					methods._appendItem.call(this, o.edges);
				}
			}*/
			/* if(o.pages>10){
				 for (i = interval.start; i < 10; i++) {
						methods._appendItem.call(this, i);
				}	
	        }else{*/
	        	// Generate interval links
				for (i = interval.start; i < interval.end; i++) {
					methods._appendItem.call(this, i);
				}
				// Generate end edges
				/*if (interval.end < o.pages && o.edges > 0) {
					if (o.pages - o.edges > interval.end && (o.pages - o.edges - interval.end != 1)) {
						$panel.append('<li class="disabled"><span class="ellipse">' + o.ellipseText + '</span></li>');
					} else if (o.pages - o.edges - interval.end == 1) {
						methods._appendItem.call(this, interval.end++);
					}
					var begin = Math.max(o.pages - o.edges, interval.end);
					for (i = begin; i < o.pages; i++) {
						methods._appendItem.call(this, i);
					}
				}*/
	       /* }*/
				
			// Generate Next link
			if (o.nextText&&o.currentPage+1!=o.pages) {
				methods._appendItem.call(this, o.currentPage + 1, {text: o.nextText, classes: 'next'});
			}
		},

		_getInterval: function(o) {
			return {
				start: Math.ceil(o.currentPage > o.halfDisplayed ? Math.max(Math.min(o.currentPage - o.halfDisplayed, (o.pages - o.displayedPages)), 0) : 0),
				end: Math.ceil(o.currentPage > o.halfDisplayed ? Math.min(o.currentPage + o.halfDisplayed, o.pages) : Math.min(o.displayedPages, o.pages))
			};
		},

		_appendItem: function(pageIndex, opts) {
			var self = this, options, $link, o = self.data('pagination'), $linkWrapper = $('<li></li>'), $ul = self.find('ul');

			pageIndex = pageIndex < 0 ? 0 : (pageIndex < o.pages ? pageIndex : o.pages - 1);

			options = $.extend({
				text: pageIndex + 1,
				classes: ''
			}, opts || {});

			if (pageIndex == o.currentPage || o.disabled) {
				if (o.disabled) {
					$linkWrapper.addClass('disabled');
				} else {
					$linkWrapper.addClass('active');
				}
				$link = $('<span class="current">' + (options.text) + '</span>');
			} else {
				$link = $('<a class="page-link">' + (options.text) + '</a>');

				$link.on('click',function(event){
					return methods._selectPage.call(self, pageIndex, event);
				});
			}

			if (options.classes) {
				$link.addClass(options.classes);
			}

			$linkWrapper.append($link);

			if ($ul.length) {
				$ul.append($linkWrapper);
			} else {
				self.append($linkWrapper);
			}
		},

		_selectPage: function(pageIndex, event) {
			var o = this.data('pagination');
			o.currentPage = pageIndex;
			if (o.selectOnClick) {
				methods._draw.call(this);
			}
			//实现界面的数据重新加载
			initPageData(pageIndex + 1,o.moduleType);
			return o.onPageClick(pageIndex + 1, event);
		}

	};
	
	$.fn.pagination = function(method) {
             
		// Method calling logic
		if (methods[method] && method.charAt(0) != '_') {
			return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' +  method + ' does not exist on jQuery.pagination');
		}

	};

})(jQuery);

function initPageData(page,moduleType){
	switch(moduleType){
	  case 'docshare':
		  initDocSharePageData(page);
			  break;
	  case 'docprivate':
		  initDocPrivatePageData(page);
			  break;
	  case 'docgarbage':
		  initDocGarbagePageData(page);
		  break;
	  case 'docdrafpaging':
	      initDocDrafPageData(page);
			  break;
	  case 'docdown':
		  initDocDownPageData(page);
		      break;
	  case 'doccollect':
		  initDocColPageData(page);
		      break;	      
	  case 'bookshare':
		  initBookSharePageData(page);
		  break;
	  case 'bookcol':
		  initBookColPageData(page);
		  break;
	  case 'coursecol':
		  initCourseColPageData(page);
		  break;
	  case 'courseshare':
		  initCourseSharePageData(page);
		  break;
	  case 'topicshare':
		  initTopicSharePageData(page);
		  break;
	  case 'topicattention':
		  initTopicAttentionPageData(page);
		  break;
	  case 'topiccomment':
		  initTopicCommentPageData(page);
		  break;
	  case 'articleshare':
		  initArticleSharePageData(page);
		  break;
	  case 'articlecol':
		  initArticleColPageData(page);
		  break;
	  case 'siteshare':
		  initSiteSharePageData(page);
		  break;
	  case 'sitecol':
		  initSiteColPageData(page);
		  break;  
	  case 'topiccommentlist':
		  initCommListPageData(page);
		  break;
	  case 'unreadnews':
		  initAllUnReadNewsPageData(page,'0');
	      break;
	  case 'readnews':
		  initAllUnReadNewsPageData(page,'1');
	      break;
	  case 'fortunecome':
		  initFortuneComeOrExpendPageData(page,1);
		  break;
	  case 'fortuneexpand':
		  initFortuneComeOrExpendPageData(page,2);
		  break;
	  case 'myattenlist' :
		  initMyAttenPageData(page);
		  break;
	  case 'myfanslist' :
		  initMyFansPageData(page);
		  break;
	  case 'topicsearch' :
		  initTopicPageData(page);
		  break;
	  case 'booksearch' :
		  initBookPageData(page);
		  break;
	  case 'search' :
		  initSearch(page);
		  break;
	}
}
//我的粉丝
function initMyFansPageData(page){
	$.ajax({
		type:"POST",
		url:"/myCenter/getMyFans",
		data:{userid:userInfo==undefined?'':userInfo.userid,pageNo:page},
		dataType:"json",
		success:function(data){
			$.each(data.myFansPage.list,function(index,item){
    			if(item.fansIds!=undefined){
    				if(item.fansIds.indexOf(',')!=-1){
    					if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.fansIds.split(','))!=-1){
    						item.fansIds=1;
    					}else{
    						item.fansIds=0;
    					}
    				}else{
    					if(item.fansIds==(userInfo==undefined?'':userInfo.userid)){
    						item.fansIds=1;
    					}else{
    						item.fansIds=0;
    					}
    					
    				}
	    		}else{
	    			item.fansIds=0;
	    		}
             }) 
             
			 var model={
					 attenManPage:data.myFansPage.list,
			 }
			 //加载他的粉丝数据模板
			 $('.pagetemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
			 $('.pagetemplate').processTemplate(model);
			 $('.maincontent').empty().append($('.pagetemplate').html());
			 $('.pagetemplate').empty(); 
			 intoUserInfo();
		  }
		})
		window.scrollTo(0,0);
}
//我的关注
function initMyAttenPageData(page){
	$.ajax({
		type:"POST",
		url:"/myCenter/getMyAttenMan",
		data:{userid:userInfo==undefined?'':userInfo.userid,pageNo:page},
		dataType:"json",
		success:function(data){
			$.each(data.attenManPage.list,function(index,item){
				item.fansIds=1;
			})
			var model={
					 attenManPage:data.attenManPage.list,
			}
			 //加载他关注的人的数据模板
			 $('.pagetemplate').setTemplateURL(projectName+'otherAttenAndFansTemplate.html');
			 $('.pagetemplate').processTemplate(model);
			 $('.maincontent').empty().append($('.pagetemplate').html());
			 $('.pagetemplate').empty();
			 intoUserInfo();
		}
	})
	window.scrollTo(0,0);
}
//加载文档已分享 (包括公开和匿名)(ispublic 1代表已分享  0是私有  2是匿名)
function initDocSharePageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyDocsUploaded",
//     	data:{pageNo:page,ispublic:1},
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
				$.each(data.docsPage.list,function(index,item){
	     			/*if(item.title.indexOf(item.docsuffix)!=-1)*/
					/*item.title=item.title.substring(0,item.title.indexOf(item.docsuffix)-1)*/
					item.docsuffix=item.docsuffix.toLowerCase()
				})
     			var datamodel={
     					docsPage:data.docsPage.list
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'docShareTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.successshare .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//加载文档私有
function initDocPrivatePageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyDocsUploaded",
     	data:{pageNo:page,ispublic:0},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
	     		$.each(data.docsPage.list,function(index,item){
	     			/*if(item.title.indexOf(item.docsuffix)!=-1)
					item.title=item.title.substring(0,item.title.indexOf(item.docsuffix)-1)*/	
					item.docsuffix=item.docsuffix.toLowerCase()
				})
     			var datamodel={
     					docsPage:data.docsPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'docPrivateTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.private .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//加载文档垃圾箱
function initDocGarbagePageData(page){
	$.ajax({
		type:"POST",
		url:"/myCenter/getMyDocsUploaded",
		data:{pageNo:page,isvalid:2},
		dataType:"json",
		ansync:false,
		success:function(data){
			$.each(data.docsPage.list,function(index,item){
				/*if(item.title.indexOf(item.docsuffix)!=-1)
					item.title=item.title.substring(0,item.title.indexOf(item.docsuffix)-1)*/	
				item.docsuffix=item.docsuffix.toLowerCase()
			})
			var datamodel={
				docsPage:data.docsPage.list,
			}
			//加载模板
			$('.pagetemplate').setTemplateURL(projectName+'docGarbageTemplate.html');
			$('.pagetemplate').processTemplate(datamodel);
			$('.garbage .docs-list ul').empty().append($('.pagetemplate').html());
			$('.pagetemplate').empty();
		}
	})
	window.scrollTo(0,0);
}
//加载文档草稿
function initDocDrafPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyDocsUploaded",
     	data:{pageNo:page,ispublic:2},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		$.each(data.docsPage.list,function(index,item){
     			/*if(item.title.indexOf(item.docsuffix)!=-1)
				item.title=item.title.substring(0,item.title.indexOf(item.docsuffix)-1)	*/
				item.docsuffix=item.docsuffix.toLowerCase()
			})
 			var datamodel={
 					docsPage:data.docsPage.list,
 			}
 			//加载模板
 		   $('.pagetemplate').setTemplateURL(projectName+'docPrivateTemplate.html');
      	   $('.pagetemplate').processTemplate(datamodel);
      	   $('.draft .docs-list ul').empty().append($('.pagetemplate').html());
      	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//加载文档下载
function initDocDownPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyDocsDowned",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
	     		$.each(data.myDownloadPage.list,function(index,item){
	     			/*if(item.docs.title.indexOf(item.docs.docsuffix)!=-1)
					item.docs.title=item.docs.title.substring(0,item.docs.title.indexOf(item.docs.docsuffix)-1)*/	
					item.docs.docsuffix=item.docs.docsuffix.toLowerCase()
				})
     			var datamodel={
     					myDownloadPage:data.myDownloadPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'docDownTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocfocus .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//加载文档收藏
function initDocColPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyDocsCollected",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		    $.each(data.myCollectPage.list,function(index,item){
     		    	item.downsum=item.collectsum;
     		    	/*if(item.docs.title.indexOf(item.docs.docsuffix)!=-1)
     		    	item.docs.title=item.docs.title.substring(0,item.docs.title.indexOf(item.docs.docsuffix)-1)*/
     		    	item.docs.docsuffix=item.docs.docsuffix.toLowerCase()
     		    })
     			var datamodel={
     		    	myDownloadPage:data.myCollectPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'docDownTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocreply .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}

//加载书籍收藏
function initBookColPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getCollectedBookList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     			var datamodel={
     					collectedBookPage:data.collectedBookPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'bookColTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocreply .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}

//加载书籍分享
function initBookSharePageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getSharedBookList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     			var datamodel={
     					sharedBookPage:data.sharedBookPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'bookShareTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.successshare .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();

     	}
	})
	window.scrollTo(0,0);
}
//加载课程收藏
function initCourseColPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getCollectedCourseList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     			var datamodel={
     					colCoursePage:data.colCoursePage.list,
     					detail:'courses/getCourseDetail'
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'courseColTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocreply .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//加载课程分享
function initCourseSharePageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getSharedCourseList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     			var datamodel={
     					shaCoursePage:data.shaCoursePage.list,
     					detail:'courses/getCourseDetail'
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'courseShareTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.successshare .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();

     	}
	})
	window.scrollTo(0,0);
}
//加载话题分享
function initTopicSharePageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyTopicsUploaded",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     			var datamodel={
     					topicsPage:data.topicsPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'topicShareTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.successshare .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();

     	}
	})
	window.scrollTo(0,0);
}
//加载话题关注
function initTopicAttentionPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyTopicsAttention",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		    $.each(data.attentionPage.list,function(index,item){
     		    	item.attentiontime=formatDate(item.attentiontime);
     		    })
     			var datamodel={
     					attentionPage:data.attentionPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'topicAttentionTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocfocus .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();

     	}
	})
	window.scrollTo(0,0);
}
//加载话题回复
function initTopicCommentPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getMyTopicsComment",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		    $.each(data.topicsCommentPage.list,function(index,item){
     		    	item.commcontent.replace('white-space: normal;', '');
     		    	item.createtime=formatDate(item.createtime);
     		    })
     			var datamodel={
     					topicsCommentPage:data.topicsCommentPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'topicCommentListTemplate.html',null,{filter_data: false});
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocreply .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();

     	}
	})
	window.scrollTo(0,0);
}
//加载文章分享
function initArticleSharePageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getSharedArticleList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		   $.each(data.shaArticlePage.list,function(index,item){
     			   item.coursesname=item.title
     		   })
     			var datamodel={
     					shaCoursePage:data.shaArticlePage.list,
     					detail:'article/getArticleDetail'
     			}
     			
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'articleShareTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.successshare .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();

     	}
	})
	window.scrollTo(0,0);
}
//加载文章收藏
function initArticleColPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getCollectedArticleList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		$.each(data.colArticlePage.list,function(index,item){
  			      item.coursesname=item.title
  		        })
     			var datamodel={
     					colCoursePage:data.colArticlePage.list,
     					detail:'article/getArticleDetail'
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'courseColTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocreply .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//加载站点分享
function initSiteSharePageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getSharedSiteList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		   $.each(data.shaSitePage.list,function(index,item){
     			   item.coursesname=item.title
     		   })
     			var datamodel={
     					shaCoursePage:data.shaSitePage.list,
     					detail:'sites/getSiteDetail'
     			}
     			
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'courseShareTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.successshare .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();

     	}
	})
	window.scrollTo(0,0);
}
//加载站点收藏
function initSiteColPageData(page){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getCollectedSiteList",
     	data:{pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		$.each(data.colSitePage.list,function(index,item){
  			      item.coursesname=item.title
  		        })
     			var datamodel={
     					colCoursePage:data.colSitePage.list,
     					detail:'sites/getSiteDetail'
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'courseColTemplate.html');
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.uc-mydocreply .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//话题评论分页
function initCommListPageData(page){
	var topicid=$('#content input[name=titleid]').val();
	$.ajax({
		type:"POST",
     	url:"/topics/getPartTopicsComment",
     	data:{topicsid:topicid,type:'3',pageNo:page},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		$.each(data.obj.list,function(index,item){
	    		if(item.likedIds!=undefined){
	    			if(item.likedIds.indexOf(',')!=-1){
	    				if($.inArray(String(userInfo==undefined?'':userInfo.userid), item.likedIds.split(','))!=-1){
    						item.likedIds=1;
    					}else{
    						item.likedIds=0;
    					}
	    			}
	    			else{
	    				if(item.likedIds==(userInfo==undefined?'':userInfo.userid)){
	    					item.likedIds=1;
	    				}
	    				else{
	    					item.likedIds=0;
	    				}
	    			}
	    		}
	    		else{
	    			item.likedIds=0;
	    		}
	    	  	
	         	})
     			var datamodel={
     					topicscommentList:data.obj.list,
     					userid:userInfo==undefined?'':userInfo.userid,
     			}
     			//加载模板
     			$('.pagetemplate').setTemplateURL(projectName+'topicCommentAppendTemplate.html');
    	    	$('.pagetemplate').processTemplate(datamodel);
    	    	$('.zm-comment-list').find('.zm-item-comment').remove();
   	    		if($('.page-inner').length>0){
   	    			$('.page-inner').before($('.pagetemplate').html());
   	    		}else{
   	    			$('#appendreply').before($('.pagetemplate').html());
   	    		}
   	    		$('.pagetemplate').empty();
   	    		$('.zm-item-link-avatar').pinwheel();
   	            $('.zg-link').pinwheel();
   	            window.location.href='#topiccomment';
     	}
	})
}

//加载未读消息
function initAllUnReadNewsPageData(page,type){
	$.ajax({
		type:"POST",
     	url:"/myCenter/getAllSms",
     	data:{pageNo:page,islook:type},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		   $.each(data.allSmsPage.list,function(index,item){
     			  if(item.smstype=='11'||item.smstype=='12'||item.smstype=='13'||item.smstype=='14'||item.smstype=='21'||item.smstype=='22'||
 						 item.smstype=='31'||item.smstype=='32'||item.smstype=='33'||item.smstype=='41'||item.smstype=='42'||item.smstype=='43'||
 						 item.smstype=='51'||item.smstype=='52'||item.smstype=='53'||item.smstype=='61'||item.smstype=='62'||item.smstype=='63'){
 							item.smstype='1'//消息
 						}
 						else if(item.smstype=='0'){
 							item.smstype='0'//系统消息
 						}
 						else if(item.smstype=='1'){
 							item.smstype='3'//私信
 						}
 						else{
 							item.smstype='2'//关注成为粉丝  
 				}
  		        })
     			var datamodel={
     			    allSmsPage:data.allSmsPage.list,
     			    currentnum:type,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'allNewsTemplate.html',null,{filter_data: false});
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.myAllMessage ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	}
	})
	window.scrollTo(0,0);
}
//财富收益或者支出
function initFortuneComeOrExpendPageData(page,type){
	$.ajax({
		type:"POST",
     	url:"/account/getDetailListByRecord",
     	data:{pageNo:page,changetype:type},
     	dataType:"json",
     	ansync:false,
     	success:function(data){
     		var datamodel={
     				actDPage:data.actDPage.list,
     			}
     			//加载模板
     		   $('.pagetemplate').setTemplateURL(projectName+'fortuneComeOrExpend.html',null,{filter_data: false});
          	   $('.pagetemplate').processTemplate(datamodel);
          	   $('.fortunecontent .docs-list ul').empty().append($('.pagetemplate').html());
          	   $('.pagetemplate').empty();
     	 }
     	})
    //跳到顶部
	window.scrollTo(0,0);
}
//话题专区--加载话题列表
function initTopicPageData(page){

	var Condition = $("#topics_input").val();
	var sortType = $("#filterCollapse li.last span.active").attr("data-index");
	var sharedType = $("#filterCollapse li.first span.active").attr("data-index");
	var tags = "";
	$.ajax({
		type:"POST",
		url:projectName + "topics/fore/search/" + sharedType,
		data:{Condition:Condition,sortType:sortType,tags:tags,pages:page-1},
		dataType:"json",
		ansync:false,
		success:function(data){
			var array = eval(data.result);
			var datamodel={
				result:array,
				reCount:data.reCount
			}
			//跳到顶部
			window.scrollTo(0,0);
			//加载模板
			$('.pagetemplate').setTemplateURL(projectName+'topicSearchTemplate.html',null,{filter_data: false});
			$('.pagetemplate').processTemplate(datamodel);
			$('.items_area').empty().append($('.pagetemplate').html());
			$('.pagetemplate').empty();
			$('.newsinfo a').each(function(){
			      $(this).text(autoAddEllipsis($(this).text(),200));
			})
		}
	})

/*	var theme = $("#filterCollapse li.last span.active").attr("data-index");
	var topicstype = $("#filterCollapse li.first span.active").attr("data-index");
	$.ajax({
		type:"POST",
		url:projectName + "topics/fore/search/" + theme,
		data:{pageNo:page,topicstype:topicstype},
		dataType:"json",
		ansync:false,
		success:function(data){
			var datamodel={
				topicsPage:data.topicsPage.list,
				topiclen:data.topicsPage.count
			}
			//跳到顶部
			window.scrollTo(0,0);
			//加载模板
			$('.pagetemplate').setTemplateURL(projectName+'topicSearchTemplate.html');
			$('.pagetemplate').processTemplate(datamodel);
			$('.items_area').empty().append($('.pagetemplate').html());
			$('.pagetemplate').empty();
		}
	})*/
}
//书籍专区专区--加载书籍列表
function initBookPageData(page){
	var Condition = $("#books_input").val();
	var sortType = $("#filterCollapse li.last span.active").attr("data-index");
	var sharedType = $("#filterCollapse li.first span.active").attr("data-index");
	var tags = "";
	$.ajax({
		type:"POST",
		url:projectName + "books/fore/area/" + sharedType,
		data:{Condition:Condition,sortType:sortType,tags:tags,pages:page-1},
		dataType:"json",
		ansync:false,
		success:function(data){
			var array = eval(data.result);
			var datamodel={
				result:array,
				reCount:data.reCount
			}
			//跳到顶部
			window.scrollTo(0,0);
			//加载模板
			$('.pagetemplate').setTemplateURL(projectName+'bookSearchTemplate.html');
			$('.pagetemplate').processTemplate(datamodel);
			$('.items_area').empty().append($('.pagetemplate').html());
			$('.pagetemplate').empty();
			$('.newsinfo a').each(function(){
			      $(this).text(autoAddEllipsis($(this).text(),200));
			})
		}
	})
}
//search 搜索
function initSearch(page){
	var Condition = $("#searchres_input").val();
	var protoType = $("#searchCollapse li:last-child span.active").attr("data-objtype");
	var sharedType= 0;
	if($("#searchCollapse li:first-child span.active").length==1){
		sharedType= $("#searchCollapse li:first-child span.active").attr("data-id");
	}else{
		sharedType= $(".select-2 a.active").attr("data-id");
	}
	var tags = "";
	var pages = "";
	$.ajax({
		type:"POST",
		url:projectName + "search/" + sharedType,
		//搜索页标从0开始
		data: {Condition:Condition, sharedType:sharedType, protoType:protoType, tags:tags, pages:page-1},
		dataType:"json",
		ansync:false,
		success:function(data){	
			var array = eval(data.result);
			var datamodel={
				result:array,
			}
			//跳到顶部
			window.scrollTo(0,0);
			
			//加载模板
			$('.pagetemplate').setTemplateURL(projectName+'searchTemplate.html');
			$('.pagetemplate').processTemplate(datamodel);
			$('.items_area').empty().append($('.pagetemplate').html());
			$('.pagetemplate').empty();
			$('.newsinfo a').each(function(){
			      $(this).text(autoAddEllipsis($(this).text(),200));
			})
		}
	})
}