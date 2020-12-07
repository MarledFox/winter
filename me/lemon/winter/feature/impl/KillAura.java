package me.lemon.winter.feature.impl;

import java.awt.Color;
import java.awt.Robot;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.memory.UWPListHandler;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

public class KillAura extends Feature {
    private long lastAttack;
    private int switchIndex;
    private Robot robot;
    private JSlider rangeSlider;
    private JSlider delaySlider;
    private JComboBox modeBox;

    public KillAura() {
        super("KillAura", -1, new Color(200, 0, 0));
        this.setSuffix("Single");
        this.lastAttack = System.currentTimeMillis();

        try {
            this.robot = new Robot();
        } catch (Exception var2) {
            System.exit(0);
        }

        this.rangeSlider = new JSlider(1, 7);
        this.rangeSlider.setValue(5);
        this.rangeSlider.setMajorTickSpacing(1);
        this.rangeSlider.setPaintTicks(true);
        this.rangeSlider.setPaintLabels(true);
        this.rangeSlider.setSnapToTicks(true);
        this.getComponentMap().put("Range", this.rangeSlider);
        this.delaySlider = new JSlider(0, 200);
        this.delaySlider.setValue(100);
        this.delaySlider.setMajorTickSpacing(50);
        this.delaySlider.setMinorTickSpacing(10);
        this.delaySlider.setPaintTicks(true);
        this.delaySlider.setPaintLabels(true);
        this.delaySlider.setSnapToTicks(true);
        this.getComponentMap().put("Delay", this.delaySlider);
        String[] modes = new String[]{"Single", "Switch"};
        this.modeBox = new JComboBox(modes);
        this.getComponentMap().put("Mode", this.modeBox);
        this.switchIndex = 0;
    }

    public void onTick(Player localPlayer) {
    }

    public void onLoop(Player localPlayer) {
        Player target = null;
        Vec3 localPos = localPlayer.getEyePos().read();
        boolean isSwitch = this.modeBox.getSelectedIndex() != 0;
        this.setSuffix((String)this.modeBox.getSelectedItem());
        UWPListHandler playerList = localPlayer.getLevel().getPlayerList();
        long next = playerList.next();
        float closest = (float)this.rangeSlider.getValue();
        int count = 0;
        boolean reachedSwitchTarget = false;
        long addr = 0L;

        while((next = playerList.next()) != -1L) {
            Player player = new Player(next);
            float dist = player.getEyePos().read().distance(localPos);
            if (dist < closest) {
                target = player;
                addr = next;
                if (!isSwitch) {
                    closest = dist;
                } else if (this.switchIndex == count) {
                    reachedSwitchTarget = true;
                    break;
                }

                ++count;
            }
        }

        if (target != null) {
            System.out.println(Long.toHexString(addr));
            target.getAabbMin().write(localPlayer.getAabbMin().read());
            target.getAabbMax().write(localPlayer.getAabbMax().read());
            if (System.currentTimeMillis() - this.lastAttack > (long)this.delaySlider.getValue()) {
                this.robot.mousePress(16);
                this.robot.mouseRelease(16);
                this.lastAttack = System.currentTimeMillis();
                ++this.switchIndex;
                if (!reachedSwitchTarget) {
                    this.switchIndex = 0;
                }
            }
        }

    }
}
