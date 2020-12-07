package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.util.Utils;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec2;
import me.lemon.winter.minecraft.Vec3;

import javax.swing.*;
import java.awt.*;

public class Speed extends Feature {

	private boolean speedSwitch = false;
	private JSlider speedSlider;
	private JComboBox modeBox;
	private JCheckBox jump;

	public Speed() {
		super("Speed", -1, new Color(0, 200, 100));
		this.setSuffix("Alternate");

		speedSlider = new JSlider(0, 20);
		speedSlider.setValue(5);
		speedSlider.setMajorTickSpacing(5);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setSnapToTicks(true);
		this.getComponentMap().put("Speed", speedSlider);

		String[] modes = new String[] { "Alternate", "Basic" };
		modeBox = new JComboBox(modes);
		this.getComponentMap().put("Mode", modeBox);

		jump = new JCheckBox();
		this.getComponentMap().put("Hop", jump);
	}

	@Override
	public void onTick(Player localPlayer) {
		this.setSuffix((String)modeBox.getSelectedItem());
		boolean alternate = modeBox.getSelectedIndex() == 0;
		float moveSpeed = 0.2873f;
		if(speedSwitch) moveSpeed *= speedSlider.getValue();
		Vec2 moveVec = Utils.getMoveVec(moveSpeed, localPlayer.getRotation().read().getY());
		Vec3 motion = localPlayer.getMotion().read();
		motion.setX(moveVec.getX());
		if(jump.isSelected() && localPlayer.getOnGround().read()) {
			motion.setY(0.4f);
		}
		motion.setZ(moveVec.getY());
		localPlayer.getMotion().write(motion);
		if(alternate) {
			speedSwitch = !speedSwitch;
		} else {
			speedSwitch = true;
		}
	}

	@Override public void onLoop(Player localPlayer) { }
}
