package com.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
    private int doubleFire;
    private int score;

    public Hero() {

        x = 150;
        y = 400;
        life = 5;
        doubleFire = 0;
        img = Main.hero0;
        width = img.getWidth();
        height = img.getHeight();
    }

    private int count = 0;
    private BufferedImage[] heroImg = {Main.hero0, Main.hero1};

    @Override
    public void move() {
        count++;
        setImg(heroImg[count % 2]);
//        if(count%2==0){
//            setImg(Main.hero0);
//        }else {
//            setImg(Main.hero1);
//        }
    }

    public int getDoubleFire() {
        return doubleFire;
    }

    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    public void addDoubleFire(){
        doubleFire += 20;
    }

    public Bullet[] shoot() {
       Bullet[] bullets ;
        if (doubleFire == 0) {
            bullets =new Bullet[1];
           bullets[0] = new Bullet(this.getX() + (this.width) / 2 - 2, this.getY() - 10);
        } else
            {
            bullets =new Bullet[2];
            bullets[0] = new Bullet(this.getX() + (this.width)/4, this.getY() - 10);
            bullets[1] = new Bullet(this.getX() + this.width - (this.width)/4, this.getY() - 10);
            doubleFire--;
       }
          return bullets;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void addScore(int score){
        this.setScore(this.getScore()+score);
    }
    public void addLife(){
        this.setLife(this.getLife()+1);
    }

}
