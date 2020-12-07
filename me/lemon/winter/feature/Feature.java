package me.lemon.winter.feature;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import me.lemon.winter.Winter;
import me.lemon.winter.minecraft.Player;

public abstract class Feature {
    private String name;
    private int keybind;
    private Color color;
    private boolean enabled;
    private String suffix;
    private Map<String, JComponent> componentMap = new HashMap();

    public Feature(String name, int keybind, Color color) {
        this.name = name;
        this.keybind = keybind;
        this.color = color;
        this.enabled = false;
        this.suffix = "";
    }

    public abstract void onTick(Player var1);

    public abstract void onLoop(Player var1);

    public String getName() {
        return this.name;
    }

    public int getKeybind() {
        return this.keybind;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public Color getColor() {
        return this.color;
    }

    public String getSuffix() {
        return this.suffix.isEmpty() ? "" : "[" + this.suffix + "]";
    }

    public Map<String, JComponent> getComponentMap() {
        return this.componentMap;
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
