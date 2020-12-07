package me.lemon.winter.minecraft;

import me.lemon.winter.memory.BooleanMemoryHandler;
import me.lemon.winter.memory.FloatMemoryHandler;

public class MoveInputHandler {
    private FloatMemoryHandler moveStrafe;
    private FloatMemoryHandler moveForward;
    private BooleanMemoryHandler sneak;
    private BooleanMemoryHandler jump;
    private BooleanMemoryHandler sprint;
    private BooleanMemoryHandler forward;
    private BooleanMemoryHandler back;
    private BooleanMemoryHandler left;
    private BooleanMemoryHandler right;

    public MoveInputHandler(long address) {
        this.moveStrafe = new FloatMemoryHandler(address + 8L, false);
        this.moveForward = new FloatMemoryHandler(address + 12L, false);
        this.sneak = new BooleanMemoryHandler(address + 73L, false);
        this.jump = new BooleanMemoryHandler(address + 91L, false);
        this.sprint = new BooleanMemoryHandler(address + 92L, false);
        this.forward = new BooleanMemoryHandler(address + 95L, false);
        this.back = new BooleanMemoryHandler(address + 96L, false);
        this.left = new BooleanMemoryHandler(address + 97L, false);
        this.right = new BooleanMemoryHandler(address + 98L, false);
    }

    public FloatMemoryHandler getMoveStrafe() {
        return this.moveStrafe;
    }

    public FloatMemoryHandler getMoveForward() {
        return this.moveForward;
    }

    public BooleanMemoryHandler getSneak() {
        return this.sneak;
    }

    public BooleanMemoryHandler getJump() {
        return this.jump;
    }

    public BooleanMemoryHandler getSprint() {
        return this.sprint;
    }

    public BooleanMemoryHandler getForward() {
        return this.forward;
    }

    public BooleanMemoryHandler getBack() {
        return this.back;
    }

    public BooleanMemoryHandler getLeft() {
        return this.left;
    }

    public BooleanMemoryHandler getRight() {
        return this.right;
    }
}
