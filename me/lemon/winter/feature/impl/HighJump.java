package me.lemon.winter.feature.impl;

import java.awt.Color;
import javax.swing.JSlider;
import me.lemon.winter.Globals;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

public class HighJump extends Feature {
    private float prevMotion;
    private JSlider heightSlider = new JSlider(0, 20);

    public HighJump() {
        super("HighJump", -1, new Color(101, 92, 170));
        this.heightSlider.setValue(5);
        this.heightSlider.setMajorTickSpacing(5);
        this.heightSlider.setMinorTickSpacing(1);
        this.heightSlider.setPaintTicks(true);
        this.heightSlider.setPaintLabels(true);
        this.heightSlider.setSnapToTicks(true);
        this.getComponentMap().put("Height", this.heightSlider);
    }

    public void onTick(Player localPlayer) {
        Vec3 motion = localPlayer.getMotion().read();
        if (Globals.getMoveInputHandler().getJump().read() && motion.getY() > 0.0F && this.prevMotion < 0.0F) {
            motion.setY((float)this.heightSlider.getValue() / 10.0F);
            localPlayer.getMotion().write(motion);
        }

        this.prevMotion = motion.getY();
    }

    public void onLoop(Player localPlayer) {
    }
}
