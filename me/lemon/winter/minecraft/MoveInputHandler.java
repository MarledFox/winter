package me.lemon.winter.minecraft;

import me.lemon.winter.memory.BooleanMemoryHandler;
import me.lemon.winter.memory.FloatMemoryHandler;

public class MoveInputHandler {
	private FloatMemoryHandler moveStrafe, moveForward;
	private BooleanMemoryHandler sneak, jump, sprint, forward, back, left, right;

	public MoveInputHandler(long address) {
		moveStrafe = new FloatMemoryHandler(address + 0x8, false);
		moveForward = new FloatMemoryHandler(address + 0xC, false);
		sneak = new BooleanMemoryHandler(address + 0x49, false);
		jump = new BooleanMemoryHandler(address + 0x5B, false);
		sprint = new BooleanMemoryHandler(address + 0x5C, false);
		forward = new BooleanMemoryHandler(address + 0x5F, false);
		back = new BooleanMemoryHandler(address + 0x60, false);
		left = new BooleanMemoryHandler(address + 0x61, false);
		right = new BooleanMemoryHandler(address + 0x62, false);
	}

	public FloatMemoryHandler getMoveStrafe() {
		return moveStrafe;
	}

	public FloatMemoryHandler getMoveForward() {
		return moveForward;
	}

	public BooleanMemoryHandler getSneak() {
		return sneak;
	}

	public BooleanMemoryHandler getJump() {
		return jump;
	}

	public BooleanMemoryHandler getSprint() {
		return sprint;
	}

	public BooleanMemoryHandler getForward() {
		return forward;
	}

	public BooleanMemoryHandler getBack() {
		return back;
	}

	public BooleanMemoryHandler getLeft() {
		return left;
	}

	public BooleanMemoryHandler getRight() {
		return right;
	}
}
