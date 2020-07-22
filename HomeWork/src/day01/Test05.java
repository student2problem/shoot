package day01;

import java.util.Random;
import java.util.Scanner;

/**
 * 随机生成一个5位的英文字母验证码(大小写混搭)
 * 然后将该验证码输出给用户，然后要求用户输入该验证码，大小写不限制。
 * 然后判定用户输入的验证码是否有效(无论用户输入大小写，只要字母都正确即可)。
 * @author Bonnie
 *
 */
public class Test05 {
	public static void main(String[] args) {
		String str1 ="";
		int num = 0;
		char ch = 0;
		Random ran  = new Random();
		for(int i=0;i<5;i++){
			num = ran.nextInt(58)+65;
			ch = (char)num;
			if(num>=65&num<=90||num>=97&&num<=112){
				str1 = ch+str1;
			}else {
				i--;
			}
		}
		System.out.println("验证码是: "+str1.toString());
		System.out.println("请输入验证码: ");
		Scanner console = new Scanner(System.in);
		String str2 = console.next();
		boolean b = str2.equalsIgnoreCase(str1);
		if(b==true){
			System.out.println("验证码正确");
		}else{
			System.out.println("验证码错误");
		}
	}
	
}
