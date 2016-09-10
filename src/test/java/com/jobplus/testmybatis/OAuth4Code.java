package com.jobplus.testmybatis;


import com.jobplus.thirdparty.weibo4j.Oauth;
import com.jobplus.thirdparty.weibo4j.model.WeiboException;
import com.jobplus.thirdparty.weibo4j.util.BareBonesBrowserLaunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OAuth4Code {
	public static void main(String [] args) throws WeiboException, IOException{
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		System.out.println(oauth.authorize("code"));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		try{
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
			}else{
				e.printStackTrace();
			}
		}
	}
}
