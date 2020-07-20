package com.shoot;

import java.awt.image.BufferedImage;

public class Ember {
    private BufferedImage[] images;
    private int index;
    private BufferedImage img;
    private int interval = 10, i;//控制图片切换频率
    private int x,y;

    public Ember(FlyingObject object){
        images = object.ember;
        img = object.getImg();
        x = object.getX();
        y = object.getY();
        index = 0;
        i = 0;
    }
    public boolean burnDown(){
         i++;
         if(i%interval==0){
            if(index == images.length){
                return true;
            }
            img = images[index++];
         }
         return false;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public BufferedImage getImg(){
        return img;
    }
}
