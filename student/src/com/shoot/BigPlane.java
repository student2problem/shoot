package com.shoot;

public class BigPlane extends FlyingObject implements Enemy,Award{
    private int blood;
    private int speed;
    private int score;
    private int awardType;

    public int getScore() {
        return score;
    }

//    public void setScore(int score) {
//        this.score = score;
//    }

    public BigPlane(){
        life = 4;
        score = 20;
        img = Main.bigplane1;
        width = img.getWidth();
        height = img.getHeight();
        x = (int)(Math.random()*(Main.WIDTH-getWidth()));
        y = height;
        speed = 3;
        awardType = (int)(Math.random()*2);
    }
    public int getAwardType() {
        return awardType;
    }

    public void setAwardType(int awardType) {
        this.awardType = awardType;
    }

    @Override
    public void move() {
        y+=speed;
    }
}
