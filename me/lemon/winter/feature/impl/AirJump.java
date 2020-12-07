package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.Globals;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

import java.awt.*;

public class AirJump extends Feature {

	private boolean lastJump = false;

	public AirJump() {
		super("AirJump", -1, new Color(18, 108, 170));
	}

	@Override
	public void onTick(Player localPlayer) {
		Vec3 motion = localPlayer.getMotion().read();
		boolean jump = Globals.getMoveInputHandler().getJump().read();
		if(jump != lastJump && jump && motion.getY() <= 0.f) {
			motion.setY(0.4f);
			localPlayer.getMotion().write(motion);
		}
		lastJump = jump;
	}

	@Override public void onLoop(Player localPlayer) { }
}
