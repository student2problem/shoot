package com.zzxx.exam.controller;

import com.zzxx.exam.entity.ExamInfo;
import com.zzxx.exam.entity.Question;
import com.zzxx.exam.entity.QuestionInfo;
import com.zzxx.exam.entity.User;
import com.zzxx.exam.service.ExamService;
import com.zzxx.exam.service.IdorPwdException;
import com.zzxx.exam.ui.*;
import com.zzxx.exam.util.Config;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ClientContext {
    private ExamFrame examFrame;
    private LoginFrame loginFrame;
    private MenuFrame menuFrame;
    private MsgFrame msgFrame;
    private WelcomeWindow welcomeWindow;
    private ExamService service;
    private User user;
    public void startShow(){
        loginFrame.setVisible(true);
    }
    private ResultFrame resultFrame;

    public void setResultFrame(ResultFrame resultFrame) {
        this.resultFrame = resultFrame;
    }

    public void login(){
        //获得账号和密码
        String id = loginFrame.getIdField().getText();
        String pwd = loginFrame.getPwdField().getText();
        try {
            user = service.getEntityContext().getUsers().get(id);
            service.login(id,pwd);
            loginFrame.setVisible(false);
            menuFrame.getInfo().setText(user.getName()+ " 同学您好!");
            menuFrame.setVisible(true);
        } catch (IdorPwdException e) {
            loginFrame.updateMessage(e.getMessage());
            
        }
    }


    public void result(){

        resultFrame.showResult("你的分数是: "+ service.getScore());
        resultFrame.setVisible(true);

    }


    public void resultClose(){
        resultFrame.setVisible(false);
    }


    public void msg(){
        msgFrame.setVisible(true);
        msgFrame.showMsg(service.msgShow("src/com/zzxx/exam/util/rule.txt"));
    }


    public void msgClose(){
        msgFrame.setVisible(false);
    }

    public void menuExit(){
        menuFrame.setVisible(false);
        loginFrame.setVisible(true);
    }


    //定义一套试卷
    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }


    private Question currentQuestion;
    private int questionIndex = 0;

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public void startMenu()  {
        menuFrame.setVisible(false);
        ExamInfo info = service.startExam(user);
        examFrame.getExamInfo().setText("姓名:"+info.getUser().getName()+
                    " 考试:"+info.getTitle()+" 考试时间:"+info.getTimeLimit()+"小时");
        currentQuestion = service.getQuestionFormPaper(questionIndex);
        examFrame.getQuestionArea().setText(currentQuestion.toString());
        examFrame.getQuestionCount().setText("题目:20 的 "+(questionIndex+1)+"题");
        examFrame.setVisible(true);
    }



    public void next() {
        // 2.记录当前这道题的用户答案

        service.setUserAnswers(questionIndex,examFrame.getAnswers());

        questionIndex ++;
        // 1.更新界面
        currentQuestion = service.getQuestionFormPaper(questionIndex);
        examFrame.getQuestionArea().setText(currentQuestion.toString());
        examFrame.getQuestionCount().setText("题目:20 的 "+(questionIndex+1)+"题");
        examFrame.setAnswer(service.loadAnswers(questionIndex));

    }

    public void prev() {
        // 2.记录当前这道题的用户答案

        service.setUserAnswers(questionIndex,examFrame.getAnswers());

        questionIndex --;

        // 1.更新界面
        examFrame.getQuestionArea().setText(currentQuestion.toString());
        examFrame.getQuestionCount().setText("题目:20 的 "+(questionIndex+1)+"题");
        currentQuestion = service.getQuestionFormPaper(questionIndex);
        examFrame.setAnswer(service.loadAnswers(questionIndex));

    }

    public void send() {

        // 1.更新界面
        service.setUserAnswers(questionIndex,examFrame.getAnswers());
        questionIndex = 0;
        examFrame.setVisible(false);
        menuFrame.setVisible(true);

    }



    public void setExamFrame(ExamFrame examFrame) {
        this.examFrame = examFrame;
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public void setMenuFrame(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    public void setMsgFrame(MsgFrame msgFrame) {
        this.msgFrame = msgFrame;
    }

    public void setWelcomeWindow(WelcomeWindow welcomeWindow) {
        this.welcomeWindow = welcomeWindow;
    }

    public void setService(ExamService service) {
        this.service = service;
    }


}
