package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class IntMemoryHandler extends BaseMemoryHandler<Integer> {
    public IntMemoryHandler(long offset, boolean stores) {
        super(offset, stores);
    }

    public boolean write(Object value) {
        if (super.write(value)) {
            Globals.getMinecraft().writeInt(this.address, (Integer)value);
        }

        return true;
    }

    public Integer read() {
        if (this.needsRead) {
            this.storedValue = Globals.getMinecraft().readInt(this.address);
            this.needsRead = false;
        }

        return (Integer)super.read();
    }
}
