package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class FloatMemoryHandler extends BaseMemoryHandler<Float> {
    public FloatMemoryHandler(long offset, boolean stores) {
        super(offset, stores);
    }

    public boolean write(Object value) {
        if (super.write(value)) {
            Globals.getMinecraft().writeFloat(this.address, (Float)value);
        }

        return true;
    }

    public Float read() {
        if (this.needsRead) {
            this.storedValue = Globals.getMinecraft().readFloat(this.address);
            this.needsRead = false;
        }

        return (Float)super.read();
    }
}
