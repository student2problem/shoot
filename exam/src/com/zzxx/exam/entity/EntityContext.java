package com.zzxx.exam.entity;

import java.io.*;
//1000:刘增宝:1234:13810381038:liuzb@zzxx.com.cn
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体数据管理, 用来读取数据文件放到内存集合当中
 */
public class EntityContext {
    private Map<String,User> users = new HashMap<>();
    private Map<Integer, List<Question>> questions = new HashMap<>();

    public EntityContext(){
        loadUser("src/com/zzxx/exam/util/user.txt");
        loadQuestions("src/com/zzxx/exam/util/corejava.txt");
    }

    public Map<String, User> getUsers() {
        return users;
    }

    //根据ID查用户
    public User findUserById(String id){
        return users.get(id);
    }

    public Map<Integer, List<Question>> getQuestions() {
        return questions;
    }


    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public void setQuestions(Map<Integer, List<Question>> questions) {
        this.questions = questions;
    }

    //根据难度查题目
    public List<Question>  findQuestionsByLevel(int level){
            return questions.get(level);
    }



    /*
    *读取user.txt文件,将其中的数据,封装为用户对象,然后保存
    */
    public void  loadUser(String filename)  {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename)));//"src/com/zzxx/exam/util/user.txt"
            String line = null;

            while ((line=br.readLine())!=null){
                if(line.startsWith("#")||line.equals("")){
                        continue;
                }
                String[] str = line.split(":");
                String id = str[0];
                String name = str[1];
                String password = str[2];
                String phone = str[3];
                String emil = str[4];
                User user = new User(name,id,password);
                user.setPhone(phone);
                user.setEmail(emil);
                users.put(id,user);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//@answer=2/3,score=5,level=5
    //Map<Integer, List<Question>> getQuestions

    public void loadQuestions(String fileName){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName)));//"src/com/zzxx/exam/util/corejava.txt"
            String line;

            int id = 0;
            while ((line=br.readLine())!=null){

                String[] str = line.split(",");
                Question question = new Question();
                question.setScore(Integer.parseInt(str[1].split("=")[1]));
                question.setLevel(Integer.parseInt(str[2].split("=")[1]));
                if((str[0].split("=").length)==1){
                    question.setType(Question.SINGLE_SELECTION);
                }else {
                    question.setType(Question.MULTI_SELECTION);
                }
                question.setTitle(br.readLine());
                List<String> list1 = new ArrayList<>();
                list1.add(br.readLine());
                list1.add(br.readLine());
                list1.add(br.readLine());
                list1.add(br.readLine());
                question.setOptions(list1);
                question.setId(id);
                int level = Integer.parseInt(str[2].split("=")[1]);
                if(questions.get(level)==null){
                    List<Question > list2 =  new ArrayList<>();
                    list2.add(question);
                    questions.put(level,list2);
                }else {
                    questions.get(level).add(question);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
