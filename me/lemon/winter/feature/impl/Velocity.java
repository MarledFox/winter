package me.lemon.winter.feature.impl;

import java.awt.Color;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

public class Velocity extends Feature {
    public Velocity() {
        super("AntiVelocity", -1, new Color(200, 155, 189));
    }

    public void onTick(Player localPlayer) {
    }

    public void onLoop(Player localPlayer) {
        if (localPlayer.getHurtTime().read() > 9) {
            localPlayer.getMotion().write(new Vec3(0.0F, 0.0F, 0.0F));
        }

    }
}
