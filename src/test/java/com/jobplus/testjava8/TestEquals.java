package com.jobplus.testjava8;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestEquals {
	public static void main1(String args[]) {
		Student s1 = new Student("张一", 6);
		Student s2 = new Student("张一", 6);
		if (s1.equals(s2)) {
			System.out.println("相同  s1的代码:" + s1.hashCode() + "  s2的代码:" + s2.hashCode());
		} else {
			System.out.println("不相同");
		}
	}
	
	public static void main(String args[]) {

		String s1 = new String("anan.wang"); // 此语句创建了两个对象，一个是字符串对象“zhaoxudong”（存放于栈中的字面量），另一个是new后在堆中产生的对象。详细见下面的四.4

		String s2 = new String("anan.wang");
		
		String s3="anan.wang";
		String s4="anan.wang";

		// 上述两条语句一共是产生了三个对象，因为栈中只有产生了一个对象。

		System.out.println(s1 == s2);// false
		
		System.out.println(s2 == s3);// false
		
		System.out.println(s1 == s3);// false
		
		System.out.println(s3 == s4);// true

		System.out.println(s1.equals(s2));// true
		
		System.out.println(s2.equals(s3));// true
		
		System.out.println(s1.equals(s3));// true

		System.out.println(s1.hashCode());// s1.hashcode()等于s2.hashcode()// ，指向同一内存的引用
		
		System.out.println(s2.hashCode()); // equals和hashCode方法只用于两个对象的比较和容器中，与对象的创建没有关系
		
		System.out.println(s3.hashCode());
		
		Set hashset = new HashSet();

		hashset.add(s1);

		hashset.add(s2); /* 在添加s2时， hashset认为s1和s2是相等的，所以让s2覆盖了s1; */
		
		hashset.add(s3); /* 在添加s3时， hashset认为s1和s2是相等的，所以让s3覆盖了s2; */
		
		hashset.add(s4); /* 在添加s3时， hashset认为s1和s2是相等的，所以让s3覆盖了s2; */

		Iterator it = hashset.iterator();

		while (it.hasNext()) {

			System.out.println(it.next());

		} // 最后在while循环的时候只打印出了一个”zhaoxudong”。
	}
	
}

class Student {
	private int age;
	private String name;

	public Student() {
	}

	public Student(String name, int age) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		return (this.name.hashCode() + this.age) * 31;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj == null) {
			result = false;
		}
		if (this == obj) {
			result = true;
		}
		if (obj instanceof Student) {
			Student stu = (Student) obj;
			if (stu.getName().equals(this.name) && stu.getAge() == (this.age)) {
				result = true;
			}
		} else {
			result = false;
		}
		return result;
	}
}
