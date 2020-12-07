package me.lemon.winter.feature.impl;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import me.lemon.winter.Globals;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

public class Flight extends Feature {
    private JSlider glideAmount;
    private JSlider bounceAmount;
    private JCheckBox bounce;
    private JSlider bounceDelay;

    public Flight() {
        super("Flight", -1, new Color(87, 158, 170));
        this.setSuffix("Glide");
        this.glideAmount = new JSlider(-10, 10);
        this.glideAmount.setValue(-7);
        this.glideAmount.setMajorTickSpacing(5);
        this.glideAmount.setMinorTickSpacing(1);
        this.glideAmount.setPaintTicks(true);
        this.glideAmount.setPaintLabels(true);
        this.glideAmount.setSnapToTicks(true);
        this.getComponentMap().put("Glide", this.glideAmount);
        this.bounceAmount = new JSlider(-10, 10);
        this.bounceAmount.setValue(5);
        this.bounceAmount.setMajorTickSpacing(5);
        this.bounceAmount.setMinorTickSpacing(1);
        this.bounceAmount.setPaintTicks(true);
        this.bounceAmount.setPaintLabels(true);
        this.bounceAmount.setSnapToTicks(true);
        this.getComponentMap().put("Bounce Amount", this.bounceAmount);
        this.bounceDelay = new JSlider(2, 20);
        this.bounceDelay.setValue(5);
        this.bounceDelay.setMajorTickSpacing(2);
        this.bounceDelay.setMinorTickSpacing(1);
        this.bounceDelay.setPaintTicks(true);
        this.bounceDelay.setPaintLabels(true);
        this.bounceDelay.setSnapToTicks(true);
        this.getComponentMap().put("Bounce Delay", this.bounceDelay);
        this.bounce = new JCheckBox();
        this.getComponentMap().put("Bounce", this.bounce);
    }

    public void onTick(Player localPlayer) {
        Vec3 motion = localPlayer.getMotion().read();
        boolean doBounce = localPlayer.getTicksExisted().read() % this.bounceDelay.getValue() == 0;
        float glide = (float)this.glideAmount.getValue() / 100.0F;
        float bouncy = (float)this.bounceAmount.getValue() / 50.0F;
        motion.setY(Globals.getMoveInputHandler().getJump().read() ? 0.4F : (Globals.getMoveInputHandler().getSneak().read() ? -0.4F : (doBounce ? bouncy : glide)));
        localPlayer.getMotion().write(motion);
    }

    public void onLoop(Player localPlayer) {
    }
}
