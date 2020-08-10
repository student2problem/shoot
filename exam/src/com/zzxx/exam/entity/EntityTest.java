package com.zzxx.exam.entity;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class EntityTest {
    public static void main(String[] args) throws IOException {
        /*EntityContext ec = new EntityContext();
        ec.loadUser("src/com/zzxx/exam/util/user.txt");

        Set<String> set = ec.getUsers().keySet();
        for(String key:set){
            System.out.println(key+":"+ec.getUsers().get(key).getEmail());
        }*/


        EntityContext ec = new EntityContext();
//        ec.loadQuestions("src/com/zzxx/exam/util/corejava.txt");
//        Set<Integer> set = ec.getQuestions().keySet();
        System.out.println( "难度1:" + ec.findQuestionsByLevel(1));



    }
}
