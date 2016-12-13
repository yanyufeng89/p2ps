var projectName = '/';
var url_rules = {
    "tbl_topics": "topics/getTopicsDetail/",
    "TOPICS": "topics/getTopicsDetail/",
    "tbl_books": "books/getBookDetail/",
    "BOOK": "books/getBookDetail/",
    "6": "books/getBookDetail/",
    "tbl_courses": "courses/getCourseDetail/",
    "COURSES": "courses/getCourseDetail/",
    "tbl_sites": "sites/getSiteDetail/",
    "SITES": "sites/getSiteDetail/",
    "tbl_article": "article/getArticleDetail/",
    "ARTICLE": "article/getArticleDetail/",
    "tbl_docs": "docs/getDocsDetail/",
    "DOC": "docs/getDocsDetail/"
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
//根据点击表名转成对应中文
function convertCh(tablename) {
    var name;
    switch (tablename) {
        case 'tbl_docs':
            name = '文档';
            break;
        case 'tbl_topics':
            name = '话题';
            break;
        case 'tbl_books':
            name = '书籍';
            break;
        case 'tbl_article':
            name = '文章';
            break;
        case 'tbl_courses':
            name = '课程';
            break;
        case '':
            name = '';
            break;
        default:
            name = '站点';
            break;
    }
    $('.zm-profile-section-name').html(name);
    return name;
}

//时间日期转换
function formatDate(str) {
    var now = new Date(str);
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hours = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    // year+"年"+fixZero(month,2)+"月"+fixZero(date,2)+"日    "+fixZero(hour,2)+":"+fixZero(minute,2)+":"+fixZero(second,2)
    return year + "-" + fixZero(month, 2) + "-" + fixZero(date, 2);
}
//时间日期转换
function formatDate_hhmmss(str){
	var now=new Date(str);
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var date=now.getDate();
	var hours=now.getHours();
	var minute=now.getMinutes();
	var second=now.getSeconds();
	// year+"年"+fixZero(month,2)+"月"+fixZero(date,2)+"日    "+fixZero(hour,2)+":"+fixZero(minute,2)+":"+fixZero(second,2)
	return  year+"-"+fixZero(month,2)+"-"+fixZero(date,2)+" "+fixZero(hours,2)+":"+fixZero(minute,2)+":"+fixZero(second,2); 
}
function fixZero(num, length) {
    var str = "" + num;
    var len = str.length;
    var s = "";
    for (var i = length; i-- > len;) {
        s += "0";
    }
    return s + str;
}
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
            //alert("服务器忙，请稍后再试！");
        }
    }
});

String.prototype.startWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substr(0, str.length) == str)
        return true;
    else
        return false;
    return true;
}

function getBackUrl() {
    var currentUrl = window.location.href.replace("http://", "");
    var backurl = encodeURI(currentUrl.substring(currentUrl.indexOf(projectName) + projectName.length, currentUrl.length));
    if (backurl.startWith("?"))
        backurl = "";
    else if (backurl.indexOf("?message=") > -1) {
        backurl = backurl.substring(0, backurl.indexOf("?message="));
    }
    return backurl;
}

function getLoginUrl() {
    return projectName + "login?backurl=" + getBackUrl();
}

function toLogin() {
    window.location.href = projectName + "login?backurl=" + getBackUrl();
}

function toLogout() {
    window.location.href = projectName + "logout?backurl=" + getBackUrl();
}

var addEvent = (function () {
    if (document.addEventListener) {
        return function (el, type, fn) {
            if (el.length) {
                for (var i = 0; i < el.length; i++) {
                    addEvent(el[i], type, fn);
                }
            } else {
                el.addEventListener(type, fn, false);
            }
        };
    } else {
        return function (el, type, fn) {
            if (el.length) {
                for (var i = 0; i < el.length; i++) {
                    addEvent(el[i], type, fn);
                }
            } else {
                el.attachEvent('on' + type,
                    function () {
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

function setCurrentLocationForBackUrlCookie() {
    var currentUrl = window.location.href.replace("http://", "");
    if (currentUrl.indexOf("registration") == -1 && currentUrl.indexOf("login") == -1) {
        var backurl = encodeURI(currentUrl.substring(currentUrl.indexOf(projectName) + projectName.length, currentUrl.length));
        if (backurl.startWith("?"))
            backurl = "";
        else if (backurl.indexOf("?message=") > -1) {
            backurl = backurl.substring(0, backurl.indexOf("?message="));
        }
        $.cookie("backurl", backurl);
    }
}

function setBackUrlCookie() {
    var paramName = "backurl=";
    var currentUrl = window.location.href.replace("http://", "");
    var backurl = "";
    if (currentUrl.indexOf(paramName) > -1)
        backurl = decodeURI(currentUrl.substring(currentUrl.indexOf(paramName) + paramName.length, currentUrl.length));
    $.cookie("backurl", backurl);
}

function deleteBackUrlCookie() {
    $.cookie('backurl', '', {expires: -1});
}

function getBackUrlCookie() {
    return decodeURI($.cookie("backurl"));
}

function isEmail(str) {
    if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(str))
        return true;
    return false;
}

function isTel(str) {
    if (/^1[34578]\d{9}$/.test(str))
        return true;
    return false;
}
//判断是否是合格的URL
function isURL (str_url) {// 验证url  
    /*var strRegex="^((https|http|rtsp|mms)?://)"  
    + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@  
    + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
    + "|" // 允许IP和DOMAIN（域名）  
    + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.  
    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名  
    + "[a-z]{2,6})" // first level domain- .com or .museum  
    + "(:[0-9]{1,4})?" // 端口- :80  
    + "((/?)|" // a slash isn't required if there is no file name  
    + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; */
	var strRegex=/((https|http|ftp|rtsp|mms):\/\/)?(([0-9a-z_!~*'().&=+$%-]+:)?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)/g;
    var re=new RegExp(strRegex); 
    return re.test(str_url); 
} 