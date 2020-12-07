package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.Globals;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

import java.awt.*;

public class Step extends Feature {

	public Step() {
		super("Step", -1, new Color(42, 79, 34));
	}

	@Override
	public void onTick(Player localPlayer) {
		localPlayer.getStepHeight().write(isEnabled() ? 1.f : 0.562f);
	}

	@Override public void onLoop(Player localPlayer) { }
}
