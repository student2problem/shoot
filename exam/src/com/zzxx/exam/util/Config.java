package com.zzxx.exam.util;

import com.zzxx.exam.controller.ClientContext;
import com.zzxx.exam.service.ExamService;

import java.io.IOException;
import java.util.Properties;

/**
 * Config 读取系统的配置文件
 */
public class Config {
    private Properties pro = new Properties();
    private ClientContext controller;
    private ExamService service;

    public ExamService getService() {
        return service;
    }

    public void setService(ExamService service) {
        this.service = service;
    }

    public void setController(ClientContext controller) {
        this.controller = controller;
    }

    public Properties getPro() {
        return pro;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }

    public Config(String file) {
        try {
            pro.load(Config.class.getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getInt(String key) {
        return Integer.parseInt(pro.getProperty(key));
    }

    public String getString(String key) {
        return pro.getProperty(key);
    }

}