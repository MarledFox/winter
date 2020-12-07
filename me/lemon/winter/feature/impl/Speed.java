package me.lemon.winter.feature.impl;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec2;
import me.lemon.winter.minecraft.Vec3;
import me.lemon.winter.util.Utils;

public class Speed extends Feature {
    private boolean speedSwitch = false;
    private JSlider speedSlider;
    private JComboBox modeBox;
    private JCheckBox jump;

    public Speed() {
        super("Speed", -1, new Color(0, 200, 100));
        this.setSuffix("Alternate");
        this.speedSlider = new JSlider(0, 20);
        this.speedSlider.setValue(5);
        this.speedSlider.setMajorTickSpacing(5);
        this.speedSlider.setMinorTickSpacing(1);
        this.speedSlider.setPaintTicks(true);
        this.speedSlider.setPaintLabels(true);
        this.speedSlider.setSnapToTicks(true);
        this.getComponentMap().put("Speed", this.speedSlider);
        String[] modes = new String[]{"Alternate", "Basic"};
        this.modeBox = new JComboBox(modes);
        this.getComponentMap().put("Mode", this.modeBox);
        this.jump = new JCheckBox();
        this.getComponentMap().put("Hop", this.jump);
    }

    public void onTick(Player localPlayer) {
        this.setSuffix((String)this.modeBox.getSelectedItem());
        boolean alternate = this.modeBox.getSelectedIndex() == 0;
        float moveSpeed = 0.2873F;
        if (this.speedSwitch) {
            moveSpeed *= (float)this.speedSlider.getValue();
        }

        Vec2 moveVec = Utils.getMoveVec(moveSpeed, localPlayer.getRotation().read().getY());
        Vec3 motion = localPlayer.getMotion().read();
        motion.setX(moveVec.getX());
        if (this.jump.isSelected() && localPlayer.getOnGround().read()) {
            motion.setY(0.4F);
        }

        motion.setZ(moveVec.getY());
        localPlayer.getMotion().write(motion);
        if (alternate) {
            this.speedSwitch = !this.speedSwitch;
        } else {
            this.speedSwitch = true;
        }

    }

    public void onLoop(Player localPlayer) {
    }
}
