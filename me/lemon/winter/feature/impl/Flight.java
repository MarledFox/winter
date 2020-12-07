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

public class Flight extends Feature {

	private JSlider glideAmount;
	private JSlider bounceAmount;
	private JCheckBox bounce;
	private JSlider bounceDelay;

	public Flight() {
		super("Flight", -1, new Color(87, 158, 170));
		this.setSuffix("Glide");

		glideAmount = new JSlider(-10, 10);
		glideAmount.setValue(-7);
		glideAmount.setMajorTickSpacing(5);
		glideAmount.setMinorTickSpacing(1);
		glideAmount.setPaintTicks(true);
		glideAmount.setPaintLabels(true);
		glideAmount.setSnapToTicks(true);
		this.getComponentMap().put("Glide", glideAmount);

		bounceAmount = new JSlider(-10, 10);
		bounceAmount.setValue(5);
		bounceAmount.setMajorTickSpacing(5);
		bounceAmount.setMinorTickSpacing(1);
		bounceAmount.setPaintTicks(true);
		bounceAmount.setPaintLabels(true);
		bounceAmount.setSnapToTicks(true);
		this.getComponentMap().put("Bounce Amount", bounceAmount);

		bounceDelay = new JSlider(2, 20);
		bounceDelay.setValue(5);
		bounceDelay.setMajorTickSpacing(2);
		bounceDelay.setMinorTickSpacing(1);
		bounceDelay.setPaintTicks(true);
		bounceDelay.setPaintLabels(true);
		bounceDelay.setSnapToTicks(true);
		this.getComponentMap().put("Bounce Delay", bounceDelay);

		bounce = new JCheckBox();
		this.getComponentMap().put("Bounce", bounce);
	}

	@Override
	public void onTick(Player localPlayer) {
		Vec3 motion = localPlayer.getMotion().read();
		boolean doBounce = localPlayer.getTicksExisted().read() % bounceDelay.getValue() == 0;
		float glide = glideAmount.getValue() / 100.f;
		float bouncy = bounceAmount.getValue() / 50.f;
		motion.setY((Globals.getMoveInputHandler().getJump().read() ? 0.4f : (Globals.getMoveInputHandler().getSneak().read() ? -0.4f : doBounce ? bouncy : glide)));
		localPlayer.getMotion().write(motion);
	}

	@Override public void onLoop(Player localPlayer) { }
}
