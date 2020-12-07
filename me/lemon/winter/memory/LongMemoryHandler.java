package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class LongMemoryHandler extends BaseMemoryHandler<Long> {
    public LongMemoryHandler(long offset, boolean stores) {
        super(offset, stores);
    }

    public boolean write(Object value) {
        if (super.write(value)) {
            Globals.getMinecraft().writeLong(this.address, (Long)value);
        }

        return true;
    }

    public Long read() {
        if (this.needsRead) {
            this.storedValue = Globals.getMinecraft().readLong(this.address);
            this.needsRead = false;
        }

        return (Long)super.read();
    }
}
