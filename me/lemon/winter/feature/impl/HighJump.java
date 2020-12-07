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

public class HighJump extends Feature {

	private float prevMotion;
	private JSlider heightSlider;

	public HighJump() {
		super("HighJump", -1, new Color(101, 92, 170));

		heightSlider = new JSlider(0, 20);
		heightSlider.setValue(5);
		heightSlider.setMajorTickSpacing(5);
		heightSlider.setMinorTickSpacing(1);
		heightSlider.setPaintTicks(true);
		heightSlider.setPaintLabels(true);
		heightSlider.setSnapToTicks(true);
		this.getComponentMap().put("Height", heightSlider);
	}

	@Override
	public void onTick(Player localPlayer) {
		Vec3 motion = localPlayer.getMotion().read();
		if(Globals.getMoveInputHandler().getJump().read() && motion.getY() > 0.f && prevMotion < 0.f) {
			motion.setY(heightSlider.getValue() / 10.f);
			localPlayer.getMotion().write(motion);
		}
		prevMotion = motion.getY();
	}

	@Override public void onLoop(Player localPlayer) { }
}
