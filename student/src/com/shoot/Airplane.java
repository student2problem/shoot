package com.shoot;

public class Airplane extends FlyingObject implements Enemy{
    private final int speed;

//    public void setScore(int score) {
//        this.score = score;
//    }

    private  int score;

    public int getScore() {
        return score;
    }

    public Airplane(){
        life=1;
        score = 5;
        img = Main.airplane1;
        width = img.getWidth();
        height = img.getHeight();
        x = (int)(Math.random()*(Main.WIDTH-getWidth()));
        y = height;
        speed = 5;
    }
    @Override
    public void move() {
        y+=speed;
    }
}
