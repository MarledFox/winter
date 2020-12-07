package me.lemon.winter.minecraft;

public class Vec3 {
    private float x;
    private float y;
    private float z;

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3() {
        this.x = 0.0F;
        this.y = 0.0F;
        this.z = 0.0F;
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

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float magnitude() {
        return (float)Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
    }

    public float distance(Vec3 other) {
        return (float)Math.sqrt(Math.pow((double)(this.x - other.getX()), 2.0D) + Math.pow((double)(this.y - other.getY()), 2.0D) + Math.pow((double)(this.z - other.getZ()), 2.0D));
    }
}
