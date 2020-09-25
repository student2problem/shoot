package com.zzxx;

import com.zzxx.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    public void Test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) ac.getBean("user");
        User user1 = ac.getBean("user", User.class);
        //User类必须唯一
        User user2 = ac.getBean( User.class);
        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
    }

    @Test
    public void Test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) ac.getBean("user2");
        System.out.println(user);
    }

    @Test
    public void Test3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) ac.getBean("user3");
        System.out.println(user);
    }
}
