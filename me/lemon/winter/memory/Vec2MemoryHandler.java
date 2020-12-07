package me.lemon.winter.memory;

import me.lemon.winter.Globals;
import me.lemon.winter.minecraft.Vec2;

public class Vec2MemoryHandler extends BaseMemoryHandler<Vec2> {
    public Vec2MemoryHandler(long offset, boolean stores) {
        super(offset, stores);
    }

    public boolean write(Object value) {
        if (super.write(value)) {
            Globals.getMinecraft().writeFloat(this.address, ((Vec2)value).getX());
            Globals.getMinecraft().writeFloat(this.address + 4L, ((Vec2)value).getY());
        }

        return true;
    }

    public Vec2 read() {
        if (this.needsRead) {
            this.storedValue = new Vec2(Globals.getMinecraft().readFloat(this.address), Globals.getMinecraft().readFloat(this.address + 4L));
            this.needsRead = false;
        }

        return (Vec2)super.read();
    }
}
