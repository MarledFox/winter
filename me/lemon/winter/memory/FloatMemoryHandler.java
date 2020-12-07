package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class FloatMemoryHandler extends BaseMemoryHandler<Float> {

	public FloatMemoryHandler(long offset, boolean stores) {
		super(offset, stores);
	}

	@Override
	public boolean write(Object value) {
		if(super.write(value)) {
			Globals.getMinecraft().writeFloat(address, (Float) value);
		}
		return true;
	}

	@Override
	public Float read() {
		if(needsRead) {
			storedValue = Globals.getMinecraft().readFloat(address);
			needsRead = false;
		}
		return super.read();
	}
}
