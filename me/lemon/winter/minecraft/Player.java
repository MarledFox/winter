package me.lemon.winter.minecraft;

import me.lemon.winter.memory.BooleanMemoryHandler;
import me.lemon.winter.memory.FloatMemoryHandler;
import me.lemon.winter.memory.IntMemoryHandler;
import me.lemon.winter.memory.LongMemoryHandler;
import me.lemon.winter.memory.Vec2MemoryHandler;
import me.lemon.winter.memory.Vec3MemoryHandler;

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
    private Vec3MemoryHandler aabbMin;
    private Vec3MemoryHandler aabbMax;
    private FloatMemoryHandler aabbWidth;
    private FloatMemoryHandler aabbHeight;
    private Vec3MemoryHandler eyePos;
    private Vec3MemoryHandler prevEyePos;
    private Vec3MemoryHandler motion;

    public Player(long address) {
        this.address = address;
        this.rotation = new Vec2MemoryHandler(address + 288L, true);
        this.prevRotation = new Vec2MemoryHandler(address + 296L, true);
        this.onGround = new BooleanMemoryHandler(address + 448L, true);
        this.prevOnGround = new BooleanMemoryHandler(address + 449L, true);
        this.collidedHorizontally = new BooleanMemoryHandler(address + 450L, true);
        this.collidedVertically = new BooleanMemoryHandler(address + 451L, true);
        this.stepHeight = new FloatMemoryHandler(address + 544L, true);
        this.ticksExisted = new IntMemoryHandler(address + 660L, true);
        this.hurtTime = new IntMemoryHandler(address + 664L, true);
        this.level = new LongMemoryHandler(address + 856L, true);
        this.aabbMin = new Vec3MemoryHandler(address + 1152L, true);
        this.aabbMax = new Vec3MemoryHandler(address + 1164L, true);
        this.aabbWidth = new FloatMemoryHandler(address + 1180L, true);
        this.aabbHeight = new FloatMemoryHandler(address + 1184L, true);
        this.eyePos = new Vec3MemoryHandler(address + 1188L, true);
        this.prevEyePos = new Vec3MemoryHandler(address + 1200L, true);
        this.motion = new Vec3MemoryHandler(address + 1212L, true);
    }

    public Vec2MemoryHandler getRotation() {
        return this.rotation;
    }

    public Vec2MemoryHandler getPrevRotation() {
        return this.prevRotation;
    }

    public BooleanMemoryHandler getOnGround() {
        return this.onGround;
    }

    public BooleanMemoryHandler getPrevOnGround() {
        return this.prevOnGround;
    }

    public BooleanMemoryHandler getCollidedHorizontally() {
        return this.collidedHorizontally;
    }

    public BooleanMemoryHandler getCollidedVertically() {
        return this.collidedVertically;
    }

    public FloatMemoryHandler getStepHeight() {
        return this.stepHeight;
    }

    public IntMemoryHandler getTicksExisted() {
        return this.ticksExisted;
    }

    public IntMemoryHandler getHurtTime() {
        return this.hurtTime;
    }

    public MultiPlayerLevel getLevel() {
        return new MultiPlayerLevel(this.level.read());
    }

    public Vec3MemoryHandler getAabbMin() {
        return this.aabbMin;
    }

    public Vec3MemoryHandler getAabbMax() {
        return this.aabbMax;
    }

    public FloatMemoryHandler getAabbWidth() {
        return this.aabbWidth;
    }

    public FloatMemoryHandler getAabbHeight() {
        return this.aabbHeight;
    }

    public Vec3MemoryHandler getEyePos() {
        return this.eyePos;
    }

    public Vec3MemoryHandler getPrevEyePos() {
        return this.prevEyePos;
    }

    public Vec3MemoryHandler getMotion() {
        return this.motion;
    }
}
