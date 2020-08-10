package com.zzxx.exam.ui;

import com.zzxx.exam.controller.ClientContext;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 主菜单界面
 */
public class MsgFrame extends JFrame {
    private JTextArea ta;

    public MsgFrame() {
        init();
    }
    private ClientContext controller;

    public void setController(ClientContext controller) {
        this.controller = controller;
    }

    private void init() {
        setSize(600, 400);
        setContentPane(createContentPane());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {//关闭
                controller.msgClose();
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

    public void showMsg(String file) {
        ta.setText(file);
        ta.setLineWrap(true);// 允许折行显示
        ta.setEditable(false);// 不能够编辑内容
    }
}
