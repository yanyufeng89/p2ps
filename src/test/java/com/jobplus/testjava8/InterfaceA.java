package com.jobplus.testjava8;

public interface InterfaceA {

	String getName();

	default void testDefaultMethode() {
		System.out.println("this is Interface default methode");
	}

}
