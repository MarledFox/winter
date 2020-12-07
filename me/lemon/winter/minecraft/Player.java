package me.lemon.winter.minecraft;

import me.lemon.winter.memory.*;

public class Player {
	private long address;

	private Vec2MemoryHandler rotation;
	private Vec2MemoryHandler prevRotation;
	private BooleanMemoryHandler onGround;
	private BooleanMemoryHandler prevOnGround;
	private BooleanMemoryHandler collidedHorizontally;
	private BooleanMemoryHandler collidedVertically;
	private FloatMemoryHandler stepHeight;
	private IntMemoryHandler ticksExisted;
	private IntMemoryHandler hurtTime;
	private LongMemoryHandler level;
	//TODO: dedicated aabb handler - this is super easy but i am super lazy :)
	private Vec3MemoryHandler aabbMin;
	private Vec3MemoryHandler aabbMax;
	private FloatMemoryHandler aabbWidth;
	private FloatMemoryHandler aabbHeight;
	private Vec3MemoryHandler eyePos;
	private Vec3MemoryHandler prevEyePos;
	private Vec3MemoryHandler motion;

	public Player(long address) {
		this.address = address;

		rotation = new Vec2MemoryHandler(address + 0x120, true);
		prevRotation = new Vec2MemoryHandler(address + 0x128, true);
		onGround = new BooleanMemoryHandler(address + 0x1C0, true);
		prevOnGround = new BooleanMemoryHandler(address + 0x1C1, true);
		collidedHorizontally = new BooleanMemoryHandler(address + 0x1C2, true);
		collidedVertically = new BooleanMemoryHandler(address + 0x1C3, true);
		stepHeight = new FloatMemoryHandler(address + 0x220, true);
		ticksExisted = new IntMemoryHandler(address + 0x294, true);
		hurtTime = new IntMemoryHandler(address + 0x298, true);
		level = new LongMemoryHandler(address + 0x358, true);
		//TODO: dedicated aabb handler - this is super easy but i am super lazy :)
		aabbMin = new Vec3MemoryHandler(address + 0x480, true);
		aabbMax = new Vec3MemoryHandler(address + 0x48C, true);
		aabbWidth = new FloatMemoryHandler(address + 0x49C, true);
		aabbHeight = new FloatMemoryHandler(address + 0x4A0, true);
		eyePos = new Vec3MemoryHandler(address + 0x4A4, true);
		prevEyePos = new Vec3MemoryHandler(address + 0x4B0, true);
		motion = new Vec3MemoryHandler(address + 0x4BC, true);
	}

	public Vec2MemoryHandler getRotation() {
		return rotation;
	}

	public Vec2MemoryHandler getPrevRotation() {
		return prevRotation;
	}

	public BooleanMemoryHandler getOnGround() {
		return onGround;
	}

	public BooleanMemoryHandler getPrevOnGround() {
		return prevOnGround;
	}

	public BooleanMemoryHandler getCollidedHorizontally() {
		return collidedHorizontally;
	}

	public BooleanMemoryHandler getCollidedVertically() {
		return collidedVertically;
	}

	public FloatMemoryHandler getStepHeight() {
		return stepHeight;
	}

	public IntMemoryHandler getTicksExisted() {
		return ticksExisted;
	}

	public IntMemoryHandler getHurtTime() {
		return hurtTime;
	}

	public MultiPlayerLevel getLevel() {
		return new MultiPlayerLevel(level.read());
	}

	public Vec3MemoryHandler getAabbMin() {
		return aabbMin;
	}

	public Vec3MemoryHandler getAabbMax() {
		return aabbMax;
	}

	public FloatMemoryHandler getAabbWidth() {
		return aabbWidth;
	}

	public FloatMemoryHandler getAabbHeight() {
		return aabbHeight;
	}

	public Vec3MemoryHandler getEyePos() {
		return eyePos;
	}

	public Vec3MemoryHandler getPrevEyePos() {
		return prevEyePos;
	}

	public Vec3MemoryHandler getMotion() {
		return motion;
	}
}
