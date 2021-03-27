package javaTester;

import java.util.Random;

public class Topic_05_Random_Number {
	public static void main(String[] args) {
		// Java Class
		Random rand = new Random();
		System.out.println(rand.nextInt(999999));
		System.out.println(rand.nextInt(999999));
		
		// Random email (ngẫu nhiên)
		// Format: prefix + random + postfix (web mail server: github/ gmail/ hotmail/..)
		System.out.println("auto" + rand.nextInt(999999) + "@tiki.vn");
		System.out.println("auto" + rand.nextInt(999999) + "@tiki.vn");
		System.out.println("auto" + rand.nextInt(999999) + "@tiki.vn");
		System.out.println("auto" + rand.nextInt(999999) + "@tiki.vn");
		System.out.println("auto" + rand.nextInt(999999) + "@tiki.vn");
	}
}
