package javaTester;

import org.openqa.selenium.support.Color;

public class Topic_09_RGB {
	
	public static void main(String[] args) {
		System.out.println(hex2Rgb("#03a9f4"));
	}

	public static String hex2Rgb(String colorStr) {
	    return Color.fromString(colorStr).asRgb();
	}
}

