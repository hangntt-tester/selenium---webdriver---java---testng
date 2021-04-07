package javaTester;

public class Topic_08_Split_String {

	public static void main(String[] args) {
		String url = "http://the-internet.herokuapp.com/";
		
		String[] newUrl = url.split("//");
		// http:
		// the-internet.herokuapp.com/
		
		url = newUrl[0] + "//" + "admin" + ":" + "admin" + "@" + newUrl[1];
		
		System.out.println(url);
	}

}
