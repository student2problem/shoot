package day02;

import java.util.Arrays;

/**
 * 将字符串123,456,789,012根据","拆分，并输出拆分后的每一项
 * @author Bonnie
 *
 */
public class Test02 {
    public static void main(String[] args) {
        String str = "123,456,789,012";
        String regex = ",";
        String[] num = str.split(regex);
        for(int i=0;i<num.length;i++){
            int a = Integer.parseInt(num[i]);
            System.out.println(a);
        }
//        System.out.println(Arrays.toString(num));
    }

}
