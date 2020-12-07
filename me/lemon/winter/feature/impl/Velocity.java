package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

import java.awt.*;

public class Velocity extends Feature {

	public Velocity() {
		super("AntiVelocity", -1, new Color(200, 155, 189));
	}

	@Override
	public void onTick(Player localPlayer) { }

	@Override
	public void onLoop(Player localPlayer) {
		if (localPlayer.getHurtTime().read() > 9) {
			localPlayer.getMotion().write(new Vec3(0, 0, 0));
		}
	}
}
