package com.jobplus.testjava8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import com.sun.star.io.IOException;

public class Game9 {
	public static void main(String args[]) throws IOException, java.io.IOException {
        File file = new File("D:\\jobplus\\workspace\\51jobplusCore\\src\\test\\java\\com\\jobplus\\testjava8\\code.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] codes=line.split(" ");
            for(String code:codes){
                char c=(char) Integer.parseInt(code, 2);
                sb.append(c);
            }
        }
        System.out.println(sb);
        //BASE64Decoder decoder = new BASE64Decoder();
        java.util.Base64.Decoder dncoder = java.util.Base64.getDecoder();
        byte[] decodeBuffer = dncoder.decode(sb.toString());
        
        File decodeFile = new File("D:\\jobplus\\workspace\\51jobplusCore\\src\\test\\java\\com\\jobplus\\testjava8\\decode.zip");
        FileOutputStream fileOutputStream = new FileOutputStream(decodeFile);
        fileOutputStream.write(decodeBuffer);
        fileOutputStream.close();
        br.close();
    }
}
