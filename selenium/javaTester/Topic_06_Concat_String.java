package javaTester;

public class Topic_06_Concat_String {

	public static void main(String[] args) {
		String userID = "mngr303746";
		
		String xpath =  "//td[text()='Manger Id : " + userID + "']";
		System.out.println(xpath);

	}

}
