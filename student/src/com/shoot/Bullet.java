package com.shoot;

public class Bullet extends FlyingObject{
    private int speed;
    public Bullet(int x,int y){
        this.x = x;
        this.y = y;
        life = 1;
        img = Main.bullet1;
        width = img.getWidth();
        height = img.getHeight();
        speed = 8;
    }

    @Override
    public void move() {
        this.setY(getY()-speed);
    }
}
