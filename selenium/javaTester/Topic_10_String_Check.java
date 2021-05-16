package javaTester;

import org.testng.Assert;


public class Topic_10_String_Check {

	public static void main(String[] args) {
		//Java
		//java
		//JAVA
		//JaVa
		// String articleTitle = "Lập trình Java trong 4 tuần";
		String articleTitle = "Java";
		
		Assert.assertTrue(articleTitle.equalsIgnoreCase("Java"));
		Assert.assertTrue(articleTitle.equalsIgnoreCase("java"));
		Assert.assertTrue(articleTitle.equalsIgnoreCase("JAVA"));
		Assert.assertTrue(articleTitle.equalsIgnoreCase("JaVa"));

		
		// Testing Framework
		// JUnit/ TestNG/ AsertJ/ Hamcrest/ ...
	}

}
