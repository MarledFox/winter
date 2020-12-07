package me.lemon.winter.feature.impl;

import java.awt.Color;
import me.lemon.winter.Globals;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;

public class Sprint extends Feature {
    public Sprint() {
        super("Sprint", -1, new Color(170, 130, 0));
    }

    public void onTick(Player localPlayer) {
        Globals.getMoveInputHandler().getSprint().write(true);
    }

    public void onLoop(Player localPlayer) {
    }
}
