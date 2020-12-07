package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class IntMemoryHandler extends BaseMemoryHandler<Integer> {

	public IntMemoryHandler(long offset, boolean stores) {
		super(offset, stores);
	}

	@Override
	public boolean write(Object value) {
		if(super.write(value)) {
			Globals.getMinecraft().writeInt(address, (Integer) value);
		}
		return true;
	}

	@Override
	public Integer read() {
		if(needsRead) {
			storedValue = Globals.getMinecraft().readInt(address);
			needsRead = false;
		}
		return super.read();
	}
}
