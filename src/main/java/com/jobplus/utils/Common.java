package com.jobplus.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eric on 2016/8/23.
 */
public class Common {

    /**
     * 首页9大分类
     *
     * @param sharedType
     * @return
     */
    public static String setIndexSharedType(String sharedType) {
        if (StringUtils.isNotBlank(sharedType)) {
            if (sharedType.indexOf("创新") > -1 || sharedType.indexOf("创业") > -1) {
                return "创新/创业";
            } else if (sharedType.indexOf("咨询") > -1 || sharedType.indexOf("财务") > -1) {
                return "咨询/财务";
            } else if (sharedType.indexOf("IT") > -1 || sharedType.indexOf("互联网") > -1) {
                return "IT/互联网";
            } else if (sharedType.indexOf("工程") > -1 || sharedType.indexOf("技术") > -1) {
                return "工程/技术";
            } else if (sharedType.indexOf("市场") > -1 || sharedType.indexOf("销售") > -1) {
                return "市场/销售";
            } else if (sharedType.indexOf("供应") > -1 || sharedType.indexOf("制造") > -1) {
                return "供应/制造";
            } else if (sharedType.indexOf("创意") > -1 || sharedType.indexOf("设计") > -1) {
                return "创意/设计";
            } else if (sharedType.indexOf("媒体") > -1 || sharedType.indexOf("影视") > -1) {
                return "媒体/影视";
            } else if (sharedType.indexOf("翻译") > -1 || sharedType.indexOf("出版") > -1) {
                return "翻译/出版";
            }
        }
        return null;
    }

    /**
     * 设置资源类型名称
     *
     * @param dataType
     * @return
     */
    public static String setDataTypeName(String dataType) {
        switch (dataType) {
            case "TOPICS":
                return "话题";
            case "BOOK":
                return "书籍";
            case "COURSES":
                return "课程";
            case "SITES":
                return "站点";
            case "ARTICLE":
                return "文章";
            case "DOC":
                return "文档";
            default:
                return "";
        }
    }

    /**
     * 过滤所有以"<"开头以">"结尾的标签
     * <p>
     *
     * @param str
     * @return String
     */
    public static String filterHtml(String str) {
        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile("<([^>]*)>");
            Matcher matcher = pattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, "");
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return str;
    }


    /**
     * 获取第一个图片地址
     * <p>
     *
     * @param str
     * @return String
     */
    public static String getFirstImg(String str) {
        String img = null;
        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile("src=\\\"http([^\\\"]+)\\\"");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                img = str.substring(matcher.start(), matcher.end());
                img = img.replace("src=", "").replace("\"", "").replaceAll(" ", "");
            }
        }
        return img;
    }

    /**
     * 生成6位随机验证码，且首位不为0
     *
     * @return
     */
    public static String random() {
        int count = 6;
        char start = '0';
        char end = '9';

        Random rnd = new Random();

        char[] result = new char[count];
        int len = end - start + 1;

        while (count-- > 0) {
            result[count] = (char) (rnd.nextInt(len) + start);
        }
        result[0] = (char) (rnd.nextInt(9) + '1');
        return new String(result);
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * url编码
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoderUrl(String url) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(url)) {
            return java.net.URLEncoder.encode(url, "UTF-8").replaceAll("%3F", "?").replaceAll("%2F", "/").replaceAll("%3D", "=");
        }
        return url;
    }

    /**
     * 将emoji表情替换成*
     *
     * @param source
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }
    
    /**
	 * 是否wap请求
	 *
	 * @param request
	 * @param url
	 * @return
	 */
	public static  boolean isWapReq(HttpServletRequest request, String url) {
		if (Common.judgeIsMoblie(request) && wapReqUrlList.contains(url))
			return true;
		return false;
	}

	// 需要进行wap处理的视图名称
	public static List<String> wapReqUrlList = new ArrayList(){
	{
		add("index");
		add("login");
		add("passwordRetake");
		add("searchNavigation");
		add("mydocs/mycenter/mycenter");
		add("mydocs/mycenter/aboutme");
		add("mydocs/docs/bookDetail");
		add("mydocs/docs/courseDetail");
		add("mydocs/docs/articleDetail");
		add("mydocs/docs/documentDetail");
		add("mydocs/docs/siteDetail");
		add("mydocs/docs/topicDetail");
	}};

    /**
     * 是否是手机浏览器访问
     *
     * @param request
     * @return
     */
    public static boolean judgeIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"iphone", "android", "phone", "mobile", "wap", "netfront", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile", "ipod", "ipad", "windows phone", "mqqbrowser"};
        String agent = request.getHeader("User-Agent");
        if (agent != null) {
            for (String mobileAgent : mobileAgents) {
                if (agent.toLowerCase().indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }
}
