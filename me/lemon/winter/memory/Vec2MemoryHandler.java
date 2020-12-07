package me.lemon.winter.memory;

import me.lemon.winter.Globals;
import me.lemon.winter.minecraft.Vec2;
import me.lemon.winter.minecraft.Vec3;

public class Vec2MemoryHandler extends BaseMemoryHandler<Vec2> {
	public Vec2MemoryHandler(long offset, boolean stores) {
		super(offset, stores);
	}

	@Override
	public boolean write(Object value) {
		if(super.write(value)) {
			Globals.getMinecraft().writeFloat(address, ((Vec2)value).getX());
			Globals.getMinecraft().writeFloat(address + 0x4, ((Vec2)value).getY());
		}
		return true;
	}

	@Override
	public Vec2 read() {
		if(needsRead) {
			storedValue = new Vec2(
					Globals.getMinecraft().readFloat(address),
					Globals.getMinecraft().readFloat(address + 0x4)
			);
			needsRead = false;
		}
		return super.read();
	}
}
