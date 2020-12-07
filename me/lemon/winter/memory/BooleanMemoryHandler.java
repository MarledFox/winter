package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class BooleanMemoryHandler extends BaseMemoryHandler<Boolean> {
    public BooleanMemoryHandler(long offset, boolean stores) {
        super(offset, stores);
    }

    public boolean write(Object value) {
        if (super.write(value)) {
            Globals.getMinecraft().writeBoolean(this.address, (Boolean)value);
        }

        return true;
    }

    public Boolean read() {
        if (this.needsRead) {
            this.storedValue = Globals.getMinecraft().readBoolean(this.address);
            this.needsRead = false;
        }

        return (Boolean)super.read();
    }
}
