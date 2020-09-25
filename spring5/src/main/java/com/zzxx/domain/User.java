package com.zzxx.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component("user")
@Scope("singleton")
public class User implements Serializable {
    @Value("10")
    private int age;
    @Value("小黑")
    private String name;
    @Resource(name = "date1970")
    private Date date;


    public User() {
        System.out.println("调用空参构造");
    }

    @PostConstruct
    public void init(){
        System.out.println("user init");
    }

    @PreDestroy   //销毁之前
    public void destroy(){
        System.out.println("user destroy");
    }

    public User(int age, String name, Date date) {
        this.age = age;
        this.name = name;
        this.date = date;
    }



    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public int getAge() {
        return age;
    }

    @Value("20")
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

/*    @Autowired
    @Qualifier("date1970")*/
//    @Resource(name = "date1970")
    public void setDate(Date date) {
        this.date = date;
    }
}