package com.jobplus.testjava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BeanAImpl implements InterfaceA {

	@Override
	public String getName() {
		
		return null;
	}

	public static void main(String args[]) {
		BeanAImpl b = new BeanAImpl();
		// 接口有实现方法，子类可不用实现
		// 如多个父接口存在同名的默认方法，则子类必须实现此方法，可以在实现的方法中指定调用那一个接口的方法
		b.testDefaultMethode();
		testListLambda();
		testStream();
		runThreadUseLambda();
		runThreadUseInnerClass();
	}

	/**
	 * 测试stream
	 */
	public static void testListLambda() {

		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("F");
		// lambda表达式
		list.forEach(o -> {
			System.out.print(o + " ");
		});
		System.out.println();
		Runnable runnable2 = () -> {
			System.out.println("RunningfromLambda");
		};
		runnable2.run();
	}

	public static void testStream() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Stream<Integer> stream = numbers.stream();
		stream.filter((x) -> {
			return x % 2 == 0;
		}).map((x) -> {
			return x * x + " ";
		}).forEach(System.out::print);
		System.out.println();
		Stream<Integer> stream1 = numbers.stream();
		System.out.println((stream1.findFirst()).hashCode());
		// .forEach(o->System.out.print(o+" "));

	}

	public static void runThreadUseLambda() {
		// Runnable是一个函数接口，只包含了有个无参数的，返回void的run方法；
		// 所以lambda表达式左边没有参数，右边也没有return，只是单纯的打印一句话
		new Thread(() -> System.out.println("lambda实现的线程")).start();
	}

	public static void runThreadUseInnerClass() {
		// 这种方式就不多讲了，以前旧版本比较常见的做法
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("内部类实现的线程");
			}
		}).start();
	}
}
