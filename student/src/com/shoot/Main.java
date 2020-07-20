package com.shoot;


import com.day8.Mouse;
import com.day8.body.MyInterfaceA;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class Main extends JPanel{
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage bee1;
    public static BufferedImage airplane1;
    public static BufferedImage bigplane1;
    public static BufferedImage bullet1;
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    Hero hero = new Hero();
    Bee bee = new Bee();
    Airplane airplane = new Airplane();
    BigPlane bigPlane =new BigPlane();
    Bullet bullet = new Bullet(hero.getX()+(hero.width)/2-2,hero.getY()-40);


    static {
        //一次读取图片
        try {
            hero0 = ImageIO.read(Main.class.getResourceAsStream("pic/hero0.png"));
            hero1 = ImageIO.read(Main.class.getResourceAsStream("pic/hero1.png"));
            bee1 = ImageIO.read(Main.class.getResourceAsStream("pic/bee.png"));
            airplane1 = ImageIO.read(Main.class.getResourceAsStream("pic/airplane.png"));
            bigplane1 = ImageIO.read(Main.class.getResourceAsStream("pic/bigplane.png"));
            bullet1 = ImageIO.read(Main.class.getResourceAsStream("pic/bullet.png"));
            background = ImageIO.read(Main.class.getResourceAsStream("pic/background.png"));
            start = ImageIO.read(Main.class.getResourceAsStream("pic/start.png"));
            pause = ImageIO.read(Main.class.getResourceAsStream("pic/pause.png"));
            gameover = ImageIO.read(Main.class.getResourceAsStream("pic/gameover.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public final int START = 0;
    public final int RUNNING = 1;
    public final int PAUSE = 2;
    public final int GAME_OVER = 3;

    private  int state = START;

    public void action(){
        java.util.Timer timer = new java.util.Timer();
        //TimerTask ->重复执行的代码
        //long->定时器什么时候开始(ms)
        //long->时间间隔
        timer.schedule(new TimerTask() {
            public void run() {
                //游戏状态的判断

                if(state == RUNNING) {
                    //1.产生新的飞行物
                    creatFlyingObject();
                    //2.飞行物移动
                    flyingObjectMove();
                    //3.判断是否越界
                    outOfBoundsActions();
                    //4.发射子弹
                    shootAction();
                    //子弹移动
                    bulletMove();
                    //5.英雄机移动
                    hero.move();
                    //6.子弹和飞行物碰撞
                    bangAction();
                    //灰烬移动
                    emberAction();
                }

                    repaint();
            }
        }, 1000, 50);
        //监听器-接口-适配器MouseAdapter
        MouseAdapter myAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == START) {
                    state = RUNNING;
                } else if (state == GAME_OVER) {
                    state = START;
                    hero = new Hero();

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(state == PAUSE){
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(state == RUNNING){
                    state = PAUSE;
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == RUNNING) {
                    int mouse_x = e.getX();
                    int mouse_y = e.getY();
                    hero.setX(mouse_x - (hero.width) / 2);
                    hero.setY(mouse_y - (hero.height) / 2);
                    repaint();
                }
            }
        };
            this.addMouseMotionListener(myAdapter);
            this.addMouseListener(myAdapter);
    }
    public static ArrayList<Ember> embers = new ArrayList<>();
    public void emberAction(){
            for(int i=0;i<embers.size();i++){
                if(embers.get(i).burnDown()==true){
                    embers.remove(i);
                }
            }
    }

    //碰撞
    public void bangAction() {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < flyings.size(); j++) {
                if (bullets.get(i).x >= flyings.get(j).x && bullets.get(i).x <= (flyings.get(j).x + flyings.get(j).width)
                        && bullets.get(i).y >= flyings.get(j).y && bullets.get(i).y <= (flyings.get(j).y + flyings.get(j).height)) {
                    flyings.get(j).setLife(flyings.get(j).getLife()-1);
                    bullets.remove(i);
                    if (flyings.get(j).getLife() == 0) {
                        if(flyings.get(j) instanceof Enemy){
                           Enemy enemy =(Enemy) flyings.get(j);
                           hero.addScore(enemy.getScore());
                        }
                        if(flyings.get(j) instanceof Award){
                            Award award = (Award)flyings.get(j);
                            if(award.getAwardType()== Award.ADD_LIFE)
                                hero.addLife();
                            else {
                                hero.addDoubleFire();
                            }
                        }
                        flyings.remove(j);
                    }
                    i--;
                    break;
                }
            }
        }
          int n = hero.life;
        for (int i = 0; i < flyings.size(); i++) {
            if (hero.x <= (flyings.get(i).x + flyings.get(i).width) && hero.y <= (flyings.get(i).getY() + flyings.get(i).height)
                    && (hero.x + hero.width) >= flyings.get(i).x) {
                n--;
                hero.setLife(n);
                flyings.remove(i);
                i--;
                if(hero.life==0){
                    state = GAME_OVER;
                }
            }
        }
    }

    //判断是否越界
    public void outOfBoundsActions(){
        for(int i=0;i<flyings.size();i++){
            if(flyings.get(i).getY()>HEIGHT){
                flyings.remove(i);
                i--;
            }
        }
        for(int i=0;i<bullets.size();i++){
            if(bullets.get(i).getY()<0){
                bullets.remove(i);
                i--;
            }
        }

    }

    //发射子弹的方法
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    private int shootIndex = 0;
    public void  shootAction() {
        shootIndex++;
        if (shootIndex % 5 == 0) {
            Bullet[] bs = hero.shoot();
            for (int i = 0; i < bs.length; i++) {
                bullets.add(bs[i]);
            }
        }
    }

    public void bulletMove(){
//        if(hero.getDoubleFire()==0) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).move();
            }
//        }
//        else {
//
//        }
    }


    public void flyingObjectMove(){
        for(int i=0;i<flyings.size();i++){
            flyings.get(i).move();
        }
    }

    public static ArrayList<FlyingObject> flyings = new ArrayList<>();
    private int flyingIndex = 0;
    private void creatFlyingObject(){
        flyingIndex++;
        if(flyingIndex%10==0){
            int ran = (int)(Math.random()*20);
            FlyingObject flys;
            if(ran ==0){
                flys = new Bee();
            }else if(ran==1||ran==2){
                flys = new BigPlane();
            }else {
                flys = new Airplane();
            }
            flyings.add(flys);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background,0,0,this);
        g.drawImage(hero.getImg(),hero.getX(),hero.getY(),this);
        paintFlyingObject(g);
        paintBullet(g);
        paintWindow(g);
        paintScore(g);
        paintEmber(g);
    }
    private void paintEmber(Graphics g){
        for(int i=0;i<embers.size();i++){


            }
        }


    private void paintScore(Graphics g){
        g.drawString("分数:"+hero.getScore(),5,20);
        g.drawString("生命值:"+hero.getLife(),5,40);
    }
    public void paintWindow(Graphics g){
        switch (state){
            case START : g.drawImage(start,0,0,this);break;
            case PAUSE : g.drawImage(pause,0,0,this);break;
            case GAME_OVER : g.drawImage(gameover,0,0,this);break;
        }
    }
    public void paintBullet(Graphics g){
        for(int i=0;i<bullets.size();i++){
            Bullet bs = bullets.get(i);
            g.drawImage(bs.getImg(),bs.getX(),bs.getY(),this);
        }
    }
    public void paintFlyingObject(Graphics g){
        for(int i=0;i<flyings.size();i++){
            FlyingObject fly = flyings.get(i);
            g.drawImage(fly.getImg(),fly.getX(),fly.getY(),this);
        }
    }
    public static final int WIDTH = 400;
    public static final int HEIGHT = 700;
    public static void main(String[] args) {
        JFrame window = new JFrame();
        //空参构造方法,创建一个原始的很小的窗口
        window.setTitle("飞机大战");
        //窗口标题
        //JFrame window =new Frame(飞机大战);
        window.setSize(WIDTH,HEIGHT);
        //设置窗口大小
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口的默认关闭选项0~3

        window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);
        Main p = new Main();
        window.add(p);
        p.action();
        window.setVisible(true);


    }
}
