package me.lemon.winter.gui;

import javax.swing.JButton;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.util.KeyHelper;

public class KeybindButton extends JButton {
    private Feature feature;
    private boolean waiting;

    public KeybindButton(Feature feature) {
        super(KeyHelper.getKeyName(feature.getKeybind()));
        this.feature = feature;
        this.addActionListener((e) -> {
            this.setWaiting(!this.waiting);
        });
    }

    private void setWaiting(boolean waiting) {
        this.waiting = waiting;
        this.setText(waiting ? "..." : KeyHelper.getKeyName(this.feature.getKeybind()));
    }

    public void keyPressed(int key) {
        if (this.waiting) {
            this.feature.setKeybind(key == 46 ? -1 : key);
            this.setWaiting(false);
        }

    }
}
