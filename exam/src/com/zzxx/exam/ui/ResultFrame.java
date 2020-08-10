package com.zzxx.exam.ui;

import com.zzxx.exam.controller.ClientContext;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ResultFrame extends JFrame{

    private JTextArea ta;

    private ClientContext controller;

    public void setController(ClientContext controller) {
        this.controller = controller;
    }

    public void showResult(String score) {
        ta.setText(score);
        ta.setLineWrap(true);// 允许折行显示
        ta.setEditable(false);// 不能够编辑内容
    }
    public ResultFrame() {
        init();
    }


    private void init() {
        setSize(200, 300);
        setContentPane(createContentPane());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {//关闭
                controller.resultClose();
            }
        });
    }

    private JScrollPane createContentPane() {
        JScrollPane jsp = new JScrollPane();
        ta = new JTextArea();
        jsp.add(ta);
        jsp.getViewport().add(ta);
        return jsp;
    }
}
