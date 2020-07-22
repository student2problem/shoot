package day02;

import java.util.Scanner;

/**
 * 输入一个IP地址，然后将4段数字分别输出
 * @author Bonnie
 *
 */
public class Test03 {
    public static void main(String[] args) {
        System.out.println("请输入一个ip地址");
//        String ip = "192.168.3.100";
        Scanner console = new Scanner(System.in);
        String ip = console.next();
        String regex = "\\.";
        String[] num = ip.split(regex);
        for(int i=0;i<num.length;i++){
            int a = Integer.parseInt(num[i]);
            System.out.println(a);
        }
    }
}
