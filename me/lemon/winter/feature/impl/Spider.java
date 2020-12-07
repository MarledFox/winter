package me.lemon.winter.feature.impl;

import java.awt.Color;
import javax.swing.JSlider;
import me.lemon.winter.Globals;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

public class Spider extends Feature {
    private JSlider speedSlider = new JSlider(0, 20);

    public Spider() {
        super("Spider", -1, new Color(170, 69, 95));
        this.speedSlider.setValue(5);
        this.speedSlider.setMajorTickSpacing(5);
        this.speedSlider.setMinorTickSpacing(1);
        this.speedSlider.setPaintTicks(true);
        this.speedSlider.setPaintLabels(true);
        this.speedSlider.setSnapToTicks(true);
        this.getComponentMap().put("Climb Speed", this.speedSlider);
    }

    public void onTick(Player localPlayer) {
        if (localPlayer.getCollidedHorizontally().read()) {
            Vec3 motion = localPlayer.getMotion().read();
            motion.setY(Globals.getMoveInputHandler().getSneak().read() ? 0.0F : (float)this.speedSlider.getValue() / 10.0F);
            localPlayer.getMotion().write(motion);
        }

    }

    public void onLoop(Player localPlayer) {
    }
}
