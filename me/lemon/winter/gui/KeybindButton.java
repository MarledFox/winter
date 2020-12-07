package me.lemon.winter.gui;

import lc.kra.system.keyboard.event.GlobalKeyEvent;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.util.KeyHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeybindButton extends JButton {
	private Feature feature;
	private boolean waiting;

	public KeybindButton(Feature feature) {
		super(KeyHelper.getKeyName(feature.getKeybind()));
		this.feature = feature;

		this.addActionListener((e) -> this.setWaiting(!this.waiting));
	}

	private void setWaiting(boolean waiting) {
		this.waiting = waiting;

		this.setText(waiting ? "..." : KeyHelper.getKeyName(feature.getKeybind()));
	}

	public void keyPressed(int key) {
		if(this.waiting) {
			feature.setKeybind(key == GlobalKeyEvent.VK_DELETE ? -1 : key);
			this.setWaiting(false);
		}
	}
}
