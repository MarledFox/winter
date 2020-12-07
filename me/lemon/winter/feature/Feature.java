package me.lemon.winter.feature;

import me.lemon.winter.Winter;
import me.lemon.winter.minecraft.Player;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Feature {
	private String name;
	private int keybind;
	private Color color;
	private boolean enabled;
	private String suffix;

	private Map<String, JComponent> componentMap = new HashMap<>();

	public Feature(String name, int keybind, Color color) {
		this.name = name;
		this.keybind = keybind;
		this.color = color;
		this.enabled = false;
		this.suffix = "";
	}

	public abstract void onTick(Player localPlayer);
	public abstract void onLoop(Player localPlayer);

	public String getName() {
		return name;
	}

	public int getKeybind() {
		return keybind;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Color getColor() {
		return color;
	}

	public String getSuffix() {
		return suffix.isEmpty() ? "" : "[" + suffix + "]";
	}

	public Map<String, JComponent> getComponentMap() {
		return componentMap;
	}

	public void setKeybind(int keybind) {
		this.keybind = keybind;
		Winter.getInstance().getFileHandler().write();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
