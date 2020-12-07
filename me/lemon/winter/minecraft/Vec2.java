package me.lemon.winter.minecraft;

public class Vec2 {
	private float x, y;

	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2() {
		this.x = 0;
		this.y = 0;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
