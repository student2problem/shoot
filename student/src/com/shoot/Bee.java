package com.shoot;

import java.awt.*;
import java.util.Random;

public class Bee extends FlyingObject implements Award {
    private int score;
    private int x_speed;
    private int y_speed;
    private int awardType;


    public Bee(){
        life = 1;
        score = 10;
        img = Main.bee1;
        width = img.getWidth();
        height = img.getHeight();
        x = (int)(Math.random()*(Main.WIDTH-getWidth()));
        y = height;
        x_speed = 3;
        y_speed = 2;
        awardType = (int)(Math.random()*2);
    }
    public int getAwardType() {
        return awardType;
    }

    public void setAwardType(int awardType) {
        this.awardType = awardType;
    }


    public Bee(int awardType) {
        this.awardType = awardType;
    }

    @Override
    public void move() {
        y+=y_speed;
        x+=x_speed;
        if(x>=Main.WIDTH-getWidth()-14){
            x_speed = -3;
        } else if(x==0){
            x_speed = 3;
        }
    }

}
