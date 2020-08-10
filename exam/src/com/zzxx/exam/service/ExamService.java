package com.zzxx.exam.service;

//所有的业务模型

import com.zzxx.exam.entity.*;
import com.zzxx.exam.util.Config;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ExamService {

    private EntityContext entityContext;

    public void setEntityContext(EntityContext entityContext) {
        this.entityContext = entityContext;
    }

    public EntityContext getEntityContext() {
        return entityContext;
    }

    public User login(String id , String password) throws IdorPwdException {
        //loginFrame中获得账号输入框和密码输入框
        //在模拟数据库中的users 查找有没有对应的User对象
//        Map<String,User> users = entityContext.getUsers();
/*        User user = users.get(id);*/

        User user = entityContext.findUserById(id);
        if(user!=null){
            //判断密码
            if(password.equals(user.getPassword())){
                return  user;
            }
        }
        throw new IdorPwdException("账号/密码错误");
    }

    public String msgShow(String fileName){
        BufferedReader br = null;
        try {
             br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName)));
             StringBuilder sb = new StringBuilder();
            String line;
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            return sb.toString();
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
        return null;
    }

    private Config config;

    public void setConfig(Config config) {
        this.config = config;
    }

    private ExamInfo examInfo;

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public ExamInfo startExam(User user) {
        examInfo = new ExamInfo();
        examInfo.setTimeLimit(config.getInt("TimeLimit"));
        examInfo.setQuestionCount(config.getInt("QuestionNumber"));
        try {
            String paperTitle = config.getString("PaperTitle");
            byte[] arr = new byte[0];
            arr = paperTitle.getBytes("iso8859-1");
            String title = new String(arr,"utf-8");
            examInfo.setTitle(title);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        examInfo.setUser(user);
        // 生成一套试卷
        createExamPaper();
        return examInfo;
    }

    private List<QuestionInfo> paper = new ArrayList<>();

    public void createExamPaper(){

        for (int i = Question.LEVEL1; i <=Question.LEVEL10; i++) {
            //获取难度级别对应的所有试题
            List<Question> questions = entityContext.findQuestionsByLevel(i);
            List<Question> randomQuestion = getRandomQuestions(questions,2);
            List<QuestionInfo> questionInfos = new ArrayList<>();
            for(int j=0;j<2;j++) {
                QuestionInfo questionInfo = new QuestionInfo();
                questionInfo.setQuestion(randomQuestion.get(j));
                questionInfos.add(questionInfo);
            }
            paper.addAll(questionInfos);
        }
    }

    private List<Question> getRandomQuestions(List<Question> sourceQuestions, int fetchNum) {
        Collections.shuffle(sourceQuestions);
        return sourceQuestions.subList(0, fetchNum);
    }

    //从试卷中获取题目
    public Question getQuestionFormPaper(int i){
            return paper.get(i).getQuestion();

    }

    //将答案放入questionInfo中
    public void setUserAnswers(int index,List<Integer> userAnswers){
            paper.get(index).setUserAnswers(userAnswers);

    }
    //将答案从questionInfo读取
    public List<Integer> loadAnswers(int index){
        return paper.get(index).getUserAnswers();

    }

    //获得分数
    public Integer getScore(){
        int score = 0;
        for(int i=0;i<paper.size();i++){
            if (paper.get(i).getUserAnswers()!=null &&
                    paper.get(i).getQuestion().getAnswers().equals(paper.get(i).getUserAnswers())){
                score += paper.get(i).getQuestion().getScore();
                continue;
            }
        }
        return score;
    }
}
