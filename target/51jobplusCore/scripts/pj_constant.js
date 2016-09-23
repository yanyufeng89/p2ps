var projectName = '/51jobplusCore/';
var url_rules = {
    "tbl_topics": "/topics/getTopicsDetail?topicId=",
    "TOPICS": "/topics/getTopicsDetail?topicId=",
    "tbl_books": "/books/getBookDetail?id=",
    "BOOK": "/books/getBookDetail?id=",
    "tbl_courses": "/courses/getCourseDetail?id=",
    "COURSES": "/courses/getCourseDetail?id=",
    "tbl_sites": "/sites/getSiteDetail?id=",
    "SITES": "/sites/getSiteDetail?id=",
    "tbl_article": "/article/getArticleDetail?id=",
    "ARTICLE": "/article/getArticleDetail?id=",
    "tbl_docs": "/docs/getDocsDetail?id=",
    "DOC": "/docs/getDocsDetail?id="
};


var name_rules = {
    "tbl_topics": "话题",
    "TOPICS": "话题",
    "tbl_books": "书籍",
    "BOOK": "书籍",
    "tbl_courses": "课程",
    "COURSES": "课程",
    "tbl_sites": "站点",
    "SITES": "站点",
    "tbl_article": "文章",
    "ARTICLE": "文章",
    "tbl_docs": "文档",
    "DOC": "文档"
};

/**
 * 组装超链接url
 * @param type
 * @param id
 * @returns {string}
 */
function getLinkUrl(type, id) {
    return projectName + url_rules[type] + id;
}

/**
 * 获取类型名称
 * @param type
 * @param id
 * @returns {string}
 */
function getTypeName(type) {
    return name_rules[type];
}

/**
 * 打开新窗口跳转
 * @param type
 * @param id
 */
function toHref(type, id) {
    window.open(getLinkUrl(type, id));
}

/**
 * 跳转
 * @param type
 * @param id
 * @param isSelf ture:当前页面跳转 false:打开新窗口
 */
function toHref(type, id, isSelf) {
    if (isSelf)
        window.location.href = getLinkUrl(type, id);
    else
        window.open(getLinkUrl(type, id));
}

/**
 * 配置全局ajax配置
 * @returns {boolean}
 */
$.ajaxSetup({
    complete: function (XMLHttpRequest, textStatus) {
        if (textStatus == "parsererror") {
            toLogin();
        } else if (textStatus == "error") {
            alert("服务器忙，请稍后再试！");
        }
    }
});

function getLoginUrl() {
    var currentUrl = window.location.href;
    return projectName + "login?backurl=" + encodeURI(currentUrl.substring(currentUrl.indexOf(projectName) + projectName.length, currentUrl.length));
}


function toLogin() {
    var currentUrl = window.location.href;
    window.location.href = projectName + "login?backurl=" + encodeURI(currentUrl.substring(currentUrl.indexOf(projectName) + projectName.length, currentUrl.length));
}


function toLogout() {
    var currentUrl = window.location.href;
    window.location.href = projectName + "logout?backurl=" + encodeURI(currentUrl.substring(currentUrl.indexOf(projectName) + projectName.length, currentUrl.length));
}

/**
 * 分享
 * @param site
 * @param title
 */
function toShare(site, title) {
    var webid = null;
    if (site == 1)
        webid = "tsina";
    else if (site == 2)
        webid = "qzone";
    else if (site == 3)
        webid = "weixin";
    else
        alert("分享失败！");
    window.open("http://www.jiathis.com/send/?webid=" + webid + "&url=" + encodeURI(window.location.href) + "&title=" + title + "&uid=1693856");
}

var addEvent = (function() {
    if (document.addEventListener) {
        return function(el, type, fn) {
            if (el.length) {
                for (var i = 0; i < el.length; i++) {
                    addEvent(el[i], type, fn);
                }
            } else {
                el.addEventListener(type, fn, false);
            }
        };
    } else {
        return function(el, type, fn) {
            if (el.length) {
                for (var i = 0; i < el.length; i++) {
                    addEvent(el[i], type, fn);
                }
            } else {
                el.attachEvent('on' + type,
                    function() {
                        return fn.call(el, window.event);
                    });
            }
        };
    }
})();

addEvent(window, 'storage', function (event) {
    if (event.key == 'pmCount') {
       // getpmCount();
        $('#menu-item-4 .zg-noti-number').html(localStorage.pmCount);
    }
    if (event.key == 'smsCount') {
       // getSmsCount();
        $('#menu-item-5 .zg-noti-number').html(localStorage.smsCount);
    }
});
