package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.Globals;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

import java.awt.*;

public class Sprint extends Feature {

	public Sprint() {
		super("Sprint", -1, new Color(170, 130, 0));
	}

	@Override
	public void onTick(Player localPlayer) {
		Globals.getMoveInputHandler().getSprint().write(true);
	}

	@Override public void onLoop(Player localPlayer) { }
}
