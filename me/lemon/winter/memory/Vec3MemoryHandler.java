package me.lemon.winter.memory;

import me.lemon.winter.Globals;
import me.lemon.winter.minecraft.Vec3;

public class Vec3MemoryHandler extends BaseMemoryHandler<Vec3> {
	public Vec3MemoryHandler(long offset, boolean stores) {
		super(offset, stores);
	}

	@Override
	public boolean write(Object value) {
		if(super.write(value)) {
			Globals.getMinecraft().writeFloat(address, ((Vec3)value).getX());
			Globals.getMinecraft().writeFloat(address + 0x4, ((Vec3)value).getY());
			Globals.getMinecraft().writeFloat(address + 0x8, ((Vec3)value).getZ());
		}
		return true;
	}

	@Override
	public Vec3 read() {
		if(needsRead) {
			storedValue = new Vec3(
					Globals.getMinecraft().readFloat(address),
					Globals.getMinecraft().readFloat(address + 0x4),
					Globals.getMinecraft().readFloat(address + 0x8)
			);
			needsRead = false;
		}
		return super.read();
	}
}
