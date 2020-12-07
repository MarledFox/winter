package me.lemon.winter.minecraft;

import me.lemon.winter.memory.UWPListHandler;

public class MultiPlayerLevel {
    private long at;

    public MultiPlayerLevel(long at) {
        this.at = at;
    }

    public UWPListHandler getPlayerList() {
        return new UWPListHandler(this.at + 72L);
    }
}
