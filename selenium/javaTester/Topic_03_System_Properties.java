package javaTester;

public class Topic_03_System_Properties {

	public static void main(String[] args) {
		String projectLocation = System.getProperty("user.dir");
		String osName = System.getProperty("os.name");
		System.out.println(projectLocation);
		System.out.println(osName);
		System.out.println(projectLocation + "\\BrowserDrivers\\chromedriver.exe");
	}

}
