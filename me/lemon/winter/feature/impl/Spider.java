package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.Globals;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

import javax.swing.*;
import java.awt.*;

public class Spider extends Feature {

	private JSlider speedSlider;

	public Spider() {
		super("Spider", -1, new Color(170, 69, 95));

		speedSlider = new JSlider(0, 20);
		speedSlider.setValue(5);
		speedSlider.setMajorTickSpacing(5);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setSnapToTicks(true);
		this.getComponentMap().put("Climb Speed", speedSlider);
	}

	@Override
	public void onTick(Player localPlayer) {
		if(localPlayer.getCollidedHorizontally().read()) {
			Vec3 motion = localPlayer.getMotion().read();
			motion.setY(Globals.getMoveInputHandler().getSneak().read() ? 0.f : speedSlider.getValue() / 10.f);
			localPlayer.getMotion().write(motion);
		}
	}

	@Override public void onLoop(Player localPlayer) { }
}
