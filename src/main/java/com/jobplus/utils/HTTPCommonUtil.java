package com.jobplus.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by eric on 2016/11/4.
 */
public class HTTPCommonUtil {
    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document getHttpsDocument(String address, int timeout) {
        try {
            URL url = new URL("https", address.replace("https://", ""), -1, "");
            trustEveryone();
            Connection conn = HttpConnection.connect(url);
            conn.header("Accept", "text/html, application/xhtml+xml, */*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN")
                    .header("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)").timeout(timeout)
                    .get();
            return conn.response().parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document getHtmlByUrl(String url) throws Exception {
        Document document = getHtmlByJsoup(url);
        if (document == null && url.indexOf("https://") > -1) {
            document = getHttpsDocument(url, 5000);
        }
        if (document == null)
            throw new Exception("抓取" + url + "页面数据失败");
        return document;
    }

    public static Document getHtmlByJsoup(String url) {
        try {
            return Jsoup.connect(url)
                    .header("Accept", "text/html, application/xhtml+xml, */*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN")
                    .header("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)").timeout(3000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
