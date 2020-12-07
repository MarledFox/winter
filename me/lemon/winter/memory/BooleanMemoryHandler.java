package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class BooleanMemoryHandler extends BaseMemoryHandler<Boolean> {

	public BooleanMemoryHandler(long offset, boolean stores) {
		super(offset, stores);
	}

	@Override
	public boolean write(Object value) {
		if(super.write(value)) {
			Globals.getMinecraft().writeBoolean(address, (Boolean) value);
		}
		return true;
	}

	@Override
	public Boolean read() {
		if(needsRead) {
			storedValue = Globals.getMinecraft().readBoolean(address);
			needsRead = false;
		}
		return super.read();
	}
}
