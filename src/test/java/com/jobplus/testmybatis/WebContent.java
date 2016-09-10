package com.jobplus.testmybatis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jobplus.utils.HtmlUtils;

public class WebContent {
	/**
	 * 读取一个网页全部内容
	 */
	public String getOneHtml(final String htmlurl) throws IOException {
		URL url;
		String temp;
		final StringBuffer sb = new StringBuffer();
		try {
			url = new URL(htmlurl);
			final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));// 读取网页全部内容
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (final MalformedURLException me) {
			System.out.println("你输入的URL格式有问题！请仔细输入");
			me.getMessage();
			throw me;
		} catch (final IOException e) {
			e.printStackTrace();
			throw e;
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param s
	 * @return 获得网页标题
	 */
	public static String getTitle(final String s) {
		String regex;
		String title = "";
		final List<String> list = new ArrayList<String>();
		regex = "<title>.*?</title>";
		final Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
		final Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++) {
			title = title + list.get(i);
		}
		return outTag(title);
	}

	/**
	 * 
	 * @param s
	 * @return 获得链接
	 */
	public List<String> getLink(final String s) {
		String regex;
		final List<String> list = new ArrayList<String>();
		regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
		final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		final Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	/**
	 * 
	 * @param s
	 * @return 获得脚本代码
	 */
	public List<String> getScript(final String s) {
		String regex;
		final List<String> list = new ArrayList<String>();
		regex = "<script.*?</script>";
		final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		final Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	/**
	 * 
	 * @param s
	 * @return 获得CSS
	 */
	public List<String> getCSS(final String s) {
		String regex;
		final List<String> list = new ArrayList<String>();
		regex = "<style.*?</style>";
		final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		final Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	/**
	 * 
	 * @param s
	 * @return 去掉标记
	 */
	public static String outTag(final String s) {
		return s.replaceAll("<.*?>", "");
	}

	/**
	 * 
	 * @param s
	 * @return 获取雅虎知识堂文章标题及内容
	 */
	public HashMap<String, String> getFromYahoo(final String s) {
		final HashMap<String, String> hm = new HashMap<String, String>();
		final StringBuffer sb = new StringBuffer();
		String html = "";
		System.out.println("\n------------------开始读取网页(" + s + ")--------------------");
		try {
			html = getOneHtml(s);
		} catch (final Exception e) {
			e.getMessage();
		}
		// System.out.println(html);
		System.out.println("------------------读取网页(" + s + ")结束--------------------\n");
		System.out.println("------------------分析(" + s + ")结果如下--------------------\n");
		String title = outTag(getTitle(html));
		title = title.replaceAll("_雅虎知识堂", "");
		// Pattern pa=Pattern.compile("<div
		// class=\"original\">(.*?)((\r\n)*)(.*?)((\r\n)*)(.*?)</div>",Pattern.DOTALL);
		final Pattern pa = Pattern.compile("<div class=\"original\">(.*?)</p></div>", Pattern.DOTALL);
		final Matcher ma = pa.matcher(html);
		while (ma.find()) {
			sb.append(ma.group());
		}
		String temp = sb.toString();
		temp = temp.replaceAll("(<br>)+?", "\n");// 转化换行
		temp = temp.replaceAll("<p><em>.*?</em></p>", "");// 去图片注释
		hm.put("title", title);
		hm.put("original", outTag(temp));
		return hm;

	}

	/**
	 * 
	 * @param args
	 *            测试一组网页，针对雅虎知识堂
	 */
	public static void main1(final String args[]) {
		String url = "";
		final List<String> list = new ArrayList<String>();
		System.out.print("输入URL，一行一个，输入结束后输入 go 程序开始运行:   \n");
		/*
		 * http://ks.cn.yahoo.com/question/1307121201133.html
		 * http://ks.cn.yahoo.com/question/1307121101907.html
		 * http://ks.cn.yahoo.com/question/1307121101907_2.html
		 * http://ks.cn.yahoo.com/question/1307121101907_3.html
		 * http://ks.cn.yahoo.com/question/1307121101907_4.html
		 * http://ks.cn.yahoo.com/question/1307121101907_5.html
		 * http://ks.cn.yahoo.com/question/1307121101907_6.html
		 * http://ks.cn.yahoo.com/question/1307121101907_7.html
		 * http://ks.cn.yahoo.com/question/1307121101907_8.html
		 */
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (!(url = br.readLine()).equals("go")) {
				list.add(url);
			}
		} catch (final Exception e) {
			e.getMessage();
		}
		final WebContent wc = new WebContent();
		HashMap<String, String> hm = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			hm = wc.getFromYahoo(list.get(i));
			System.out.println("标题： " + hm.get("title"));
			System.out.println("内容： \n" + hm.get("original"));
		}
		/*
		 * String htmlurl[] = {
		 * "http://ks.cn.yahoo.com/question/1307121201133.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907_2.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907_3.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907_4.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907_5.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907_6.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907_7.html",
		 * "http://ks.cn.yahoo.com/question/1307121101907_8.html" }; WebContent
		 * wc = new WebContent(); HashMap<String, String> hm = new
		 * HashMap<String, String>(); for (int i = 0; i < htmlurl.length; i++) {
		 * hm = wc.getFromYahoo(htmlurl[i]); System.out.println("标题： " +
		 * hm.get("title")); System.out.println("内容： \n" + hm.get("original"));
		 * }
		 */
		/*
		 * String html=""; String link=""; String sscript=""; String content="";
		 * System.out.println(htmlurl+" 开始读取网页内容：");
		 * html=wc.getOneHtml(htmlurl); System.out.println(htmlurl+" 读取完毕开始分析……
		 * "); html=html.replaceAll("
		 * (<script.*?)((\r\n)*)(.*?)((\r\n)*)(.*?)(</script>)","
		 * ");//去除脚本 html=html.replaceAll("
		 * (<style.*?)((\r\n)*)(.*?)((\r\n)*)(.*?)(</style>)","
		 * ");//去掉CSS html=html.replaceAll("<title>.*?</title>"," ");//除去页面标题
		 * html=html.replaceAll(
		 * "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>","
		 * ");//去掉链接 html=html.replaceAll("(\\s){2,}?"," ");//除去多余空格
		 * html=wc.outTag(html);//多余标记 System.out.println(html);
		 */

		/*
		 * String s[]=html.split(" +"); for(int i=0;i<s.length;i++){
		 * content=(content.length()>s[i].length())?content:s[i]; }
		 * System.out.println(content);
		 */

		// System.out.println(htmlurl+"网页内容结束");
		/*
		 * System.out.println(htmlurl+"网页脚本开始："); List
		 * script=wc.getScript(html); for(int i=0;i<script.size();i++){
		 * System.out.println(script.get(i)); }
		 * System.out.println(htmlurl+"网页脚本结束：");
		 * 
		 * System.out.println(htmlurl+"CSS开始："); List css=wc.getCSS(html);
		 * for(int i=0;i<css.size();i++){ System.out.println(css.get(i)); }
		 * System.out.println(htmlurl+"CSS结束：");
		 * 
		 * System.out.println(htmlurl+"全部链接内容开始："); List list=wc.getLink(html);
		 * for(int i=0;i<list.size();i++){ link=list.get(i).toString(); }
		 * System.out.println(htmlurl+"全部链接内容结束：");
		 * 
		 * System.out.println("内容"); System.out.println(wc.outTag(html));
		 */
	}

	public static String getMetaTag(Document document, String attr) {
		Elements elements = document.select("meta[name=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		elements = document.select("meta[property=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		return null;
	}

	public static void main(String args[]) {

		URL url;
		try {
			// url = new URL("http://kanglecjr.iteye.com/blog/2095152");
			// StringBuffer html = new StringBuffer();
			// HttpURLConnection conn = (HttpURLConnection)
			// url.openConnection();
			// conn.setRequestProperty("User-Agent", "Mozilla/31.0 (compatible;
			// MSIE 10.0; Windows NT; DigExt)");
			// InputStreamReader isr = new
			// InputStreamReader(conn.getInputStream(), "utf-8");
			//
			// final StringBuffer sb = new StringBuffer();
			// String temp;
			// final BufferedReader in = new BufferedReader(isr);// 读取网页全部内容
			// while ((temp = in.readLine()) != null) {
			// sb.append(temp);
			// }
			// in.close();
			// System.out.println(getTitle(sb.toString()));
			//

			Document doc = Jsoup.connect("https://www.zhihu.com/question/49636322#answer-42768888")
					.header("User-Agent", "Mozilla/31.0 (compatible; MSIE 10.0; Windows NT; DigExt)").timeout(3000)
					.get();
			String title = doc.title();
			String descStr = "";
			Elements Edesc = doc.select("[name=description]");
			Elements Ekey = doc.select("[name=keywords]");
			if (null != Edesc && Edesc.size() > 0) {
				descStr = Edesc.get(0).attr("content");
			} else {
				if (null != Ekey && Edesc.size() > 0) {
					descStr = Ekey.get(0).attr("content");
				}
			}
			Element image = doc.select("body").select("img").first();
			String igmUrl = image.absUrl("src");
			String text = HtmlUtils.text(doc.body().text());
			System.out.println("Jsoup_*******************:title" + title);
			System.out.println("Jsoup_*******************text:" + text);
			System.out.println("Jsoup_*******************descStr:" + descStr);
			System.out.println("Jsoup_*******************igmUrl:" + igmUrl);
			// System.out.println("Jsoup_*******************:"+doc.getElementById("content").text());
			Blog();
			article();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 /**
     * 获取博客上的文章标题和链接
     */
    public static void article() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postTitle");
            for (Element element :ListDiv) {
                Elements links = element.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text().trim();
                    System.out.println(linkHref);
                    System.out.println(linkText);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * 获取指定博客文章的内容
     */
    public static void Blog() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postBody");
            for (Element element :ListDiv) {
                System.out.println(element.html());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
}
