package me.lemon.winter.feature.impl;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.memory.UWPListHandler;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

import javax.swing.*;
import java.awt.*;

public class KillAura extends Feature {

	private long lastAttack;
	private int switchIndex;
	private Robot robot;
	private JSlider rangeSlider, delaySlider;
	private JComboBox modeBox;

	public KillAura() {
		super("KillAura", -1, new Color(200, 0, 0));
		this.setSuffix("Single");
		lastAttack = System.currentTimeMillis();
		try {
			this.robot = new Robot();
		} catch(Exception e) {
			System.exit(0); // lolololloololololololol
		}

		rangeSlider = new JSlider(1, 7);
		rangeSlider.setValue(5);
		rangeSlider.setMajorTickSpacing(1);
		rangeSlider.setPaintTicks(true);
		rangeSlider.setPaintLabels(true);
		rangeSlider.setSnapToTicks(true);
		this.getComponentMap().put("Range", rangeSlider);

		delaySlider = new JSlider(0, 200);
		delaySlider.setValue(100);
		delaySlider.setMajorTickSpacing(50);
		delaySlider.setMinorTickSpacing(10);
		delaySlider.setPaintTicks(true);
		delaySlider.setPaintLabels(true);
		delaySlider.setSnapToTicks(true);
		this.getComponentMap().put("Delay", delaySlider);

		String[] modes = new String[] { "Single", "Switch" };
		modeBox = new JComboBox(modes);
		this.getComponentMap().put("Mode", modeBox);

		switchIndex = 0;
	}

	@Override public void onTick(Player localPlayer) {}

	@Override
	public void onLoop(Player localPlayer) {
		Player target = null;
		Vec3 localPos = localPlayer.getEyePos().read(); //save performance by only reading once
		boolean isSwitch = modeBox.getSelectedIndex() != 0;
		this.setSuffix((String)modeBox.getSelectedItem());

		UWPListHandler playerList = localPlayer.getLevel().getPlayerList();
		long next = playerList.next(); //skip local. Based?
		float closest = rangeSlider.getValue();
		int count = 0;
		boolean reachedSwitchTarget = false;
		long addr = 0;
		while((next = playerList.next()) != -1) {
			Player player = new Player(next);

			float dist = player.getEyePos().read().distance(localPos);
			if(dist < closest) {
				target = player;
				addr = next;
				if(!isSwitch) {
					closest = dist;
				} else {
					if(switchIndex == count) {
						reachedSwitchTarget = true;
						break;
					}
				}
				count++;
			}
		}
		if(target != null) {
			System.out.println(Long.toHexString(addr));
			target.getAabbMin().write(localPlayer.getAabbMin().read());
			target.getAabbMax().write(localPlayer.getAabbMax().read());
			if(System.currentTimeMillis() - lastAttack > delaySlider.getValue()) {
				robot.mousePress(16);
				robot.mouseRelease(16);
				lastAttack = System.currentTimeMillis();
				switchIndex++;
				if(!reachedSwitchTarget) {
					switchIndex = 0;
				}
			}
		}
	}
}
