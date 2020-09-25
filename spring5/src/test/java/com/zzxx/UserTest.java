package com.zzxx;

import com.zzxx.domain.User;
import com.zzxx.service.AccountService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void test01(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

        User user =  ac.getBean( User.class);

        System.out.println(user);
    }

}
