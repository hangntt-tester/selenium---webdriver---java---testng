package javaTester;
// Kiểu dữ liệu

import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class Topic_01_Data_Type {
	
	// Chứa những kí tự (duy nhất 1 kí tự)
	// char
	char a = 'c';
	
	// Chứa số nguyên (ko có dấu)
	// byte
	byte byteNumber = 127;
	// short
	short shortNumber = 6300;
	// int
	int intNumber = 500;
	// long
	long longNumber = 500000000;
	
	// Chứa số thực (có dấu)
	// float
	float floatNumber = 59.635F;
	// double
	double doubleNumber = 5656.325D;
	
	// Luân lí (đúng/sai)
	// boolean
	boolean status = true;
	
	// Chứa những chuỗi kí tư (số/ chữ/ đặc biệt)
	//string
	String name = "Nguyen Van Tam";
	String address = "129/15 Le Lai";
	String password = "P@ss**@#$";
	
	// Kiểu Class
	// Class
	Topic_01_Data_Type topic01;
	
	// Kiểu đối tượng
	//Object
	
	//Kiểu mảng 
	//Array
	String [] students = {name, address, password};
	int[] studentSalary = {1600, 1500, 1000};
	
	// Kiểu Interface
	// Interface
	WebDriver driver;
	
	// Kiểu tập hợp (List/ Set/ Queue)
	// List: ArrayList/ LinkedList/....
	// Collection
	ArrayList<String> StudentName = new ArrayList<String>();
}
