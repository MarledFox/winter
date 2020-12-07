package me.lemon.winter.minecraft;

public class Vec2 {
    private float x;
    private float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2() {
        this.x = 0.0F;
        this.y = 0.0F;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
