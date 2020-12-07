package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.Globals;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

import java.awt.*;

public class Phase extends Feature {

	public Phase() {
		super("Phase", -1, new Color(98, 108, 118));
	}

	@Override
	public void onTick(Player localPlayer) {
		Vec3 max = localPlayer.getAabbMax().read();
		Vec3 min = localPlayer.getAabbMin().read();
		max.setY(isEnabled() ? min.getY() : min.getY() + 1.8f);
		localPlayer.getAabbMax().write(max);
	}

	@Override public void onLoop(Player localPlayer) { }
}
