package me.lemon.winter.feature.impl;

import java.awt.Color;
import me.lemon.winter.Globals;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.minecraft.Vec3;

public class AirJump extends Feature {
    private boolean lastJump = false;

    public AirJump() {
        super("AirJump", -1, new Color(18, 108, 170));
    }

    public void onTick(Player localPlayer) {
        Vec3 motion = localPlayer.getMotion().read();
        boolean jump = Globals.getMoveInputHandler().getJump().read();
        if (jump != this.lastJump && jump && motion.getY() <= 0.0F) {
            motion.setY(0.4F);
            localPlayer.getMotion().write(motion);
        }

        this.lastJump = jump;
    }

    public void onLoop(Player localPlayer) {
    }
}
