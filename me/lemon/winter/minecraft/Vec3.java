package me.lemon.winter.minecraft;

public class Vec3 {
	private float x, y, z;

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
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

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float magnitude() {
		return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}

	public float distance(Vec3 other) {
		return (float)Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2) + Math.pow(this.z - other.getZ(), 2));
	}
}
