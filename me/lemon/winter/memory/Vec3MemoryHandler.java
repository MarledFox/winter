package me.lemon.winter.memory;

import me.lemon.winter.Globals;
import me.lemon.winter.minecraft.Vec3;

public class Vec3MemoryHandler extends BaseMemoryHandler<Vec3> {
    public Vec3MemoryHandler(long offset, boolean stores) {
        super(offset, stores);
    }

    public boolean write(Object value) {
        if (super.write(value)) {
            Globals.getMinecraft().writeFloat(this.address, ((Vec3)value).getX());
            Globals.getMinecraft().writeFloat(this.address + 4L, ((Vec3)value).getY());
            Globals.getMinecraft().writeFloat(this.address + 8L, ((Vec3)value).getZ());
        }

        return true;
    }

    public Vec3 read() {
        if (this.needsRead) {
            this.storedValue = new Vec3(Globals.getMinecraft().readFloat(this.address), Globals.getMinecraft().readFloat(this.address + 4L), Globals.getMinecraft().readFloat(this.address + 8L));
            this.needsRead = false;
        }

        return (Vec3)super.read();
    }
}
