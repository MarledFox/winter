package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class UWPListHandler {
    private long address;
    private long current;

    public UWPListHandler(long address) {
        this.address = address;
        this.current = Globals.getMinecraft().readLong(address);
    }

    public long next() {
        long stop = Globals.getMinecraft().readLong(this.address + 8L);
        long _return = -1L;
        if (this.current < stop) {
            _return = Globals.getMinecraft().readLong(this.current);
            this.current += 8L;
        }

        return _return;
    }
}
