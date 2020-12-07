package me.lemon.winter.feature.impl;

import java.awt.Color;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;

public class Step extends Feature {
    public Step() {
        super("Step", -1, new Color(42, 79, 34));
    }

    public void onTick(Player localPlayer) {
        localPlayer.getStepHeight().write(this.isEnabled() ? 1.0F : 0.562F);
    }

    public void onLoop(Player localPlayer) {
    }
}
