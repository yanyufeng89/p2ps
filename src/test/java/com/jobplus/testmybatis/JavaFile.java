package com.jobplus.testmybatis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.jobplus.pojo.CompanyNews;

public class JavaFile {

	public static void main(String[] args) {

		CompanyNews record = new CompanyNews();
		
		System.out.println("******record.getLikesum(): " + record.getLikesum());
		
		
		System.out.println("+++++++++++++++++++++++" );
		
		System.out.println("record.getLikesum() != null " + record.getLikesum() != null);
		
		System.out.println("record.getLikesum() == null " + record.getLikesum() == null);
		
	}

	public static void main4(String[] args) {

		String str1 = "a";
		String str2 = "b";
		String str3 = str1 + str2;
		String str4 = str1 + "b";
		String str5 = "ab";
		String str6 = new String("ab");
		String str7 = new String("ab");
		System.out.println("str3.hashCode： " + str3.hashCode());
		System.out.println("str4.hashCode： " + str4.hashCode());
		System.out.println("str5.hashCode： " + str5.hashCode());
		System.out.println("str6.hashCode： " + str6.hashCode());
		System.out.println("str7.hashCode： " + str7.hashCode());
		System.out.println("str3==str4: " + str3 == str4);
		System.out.println("str3==str5: " + str3 == str4);
		System.out.println("str4==str5: " + str3 == str4);
		System.out.println("str3.equals(str4): " + str3.equals(str4));
		System.out.println("str3.equals(str5): " + str3.equals(str5));
		System.out.println("str4.equals(str5): " + str4.equals(str5));
	}

	public static void main1(String[] args) {
		String str = "<option value=\"urn:li:fs_industry:121\">安保和调查</option>";
		String str1 = str.substring(str.indexOf("<option value=\"") + "<option value=\"".length(), str.indexOf("\">"));
		String str2 = str.substring(str.indexOf("\">") + "\">".length(), str.indexOf("</option>"));
		System.out.println(str.indexOf("<option value=\"") + "   " + str.indexOf("\">"));
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str.replace(str1, str2));
	}

	public static void main2(String[] args) {
		try {
			// read file content from file
			StringBuffer sb = new StringBuffer("");

			FileReader reader = new FileReader("c://websiteTemplate.html");
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			while ((str = br.readLine()) != null) {
				// System.out.println(str);
				if (str.indexOf("<option") != -1) {
					String str1 = str.substring(str.indexOf("<option value=\"") + "<option value=\"".length(),
							str.indexOf("\">"));
					String str2 = str.substring(str.indexOf("\">") + "\">".length(), str.indexOf("</option>"));
					if (str1.length() == 0) {
						continue;
					}
					str = str.replaceFirst(str1, str2);

				}
				System.out.println(str);
				sb.append(str + "\n");

			}

			br.close();
			reader.close();

			// write string to file
			FileWriter writer = new FileWriter("c://test2.html");
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(sb.toString());

			bw.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}