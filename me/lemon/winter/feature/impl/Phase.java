package me.lemon.winter.feature.impl;

import java.awt.Color;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

public class Phase extends Feature {
    public Phase() {
        super("Phase", -1, new Color(98, 108, 118));
    }

    public void onTick(Player localPlayer) {
        Vec3 max = localPlayer.getAabbMax().read();
        Vec3 min = localPlayer.getAabbMin().read();
        max.setY(this.isEnabled() ? min.getY() : min.getY() + 1.8F);
        localPlayer.getAabbMax().write(max);
    }

    public void onLoop(Player localPlayer) {
    }
}
