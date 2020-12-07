package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class LongMemoryHandler extends BaseMemoryHandler<Long> {

	public LongMemoryHandler(long offset, boolean stores) {
		super(offset, stores);
	}

	@Override
	public boolean write(Object value) {
		if(super.write(value)) {
			Globals.getMinecraft().writeLong(address, (Long) value);
		}
		return true;
	}

	@Override
	public Long read() {
		if(needsRead) {
			storedValue = Globals.getMinecraft().readLong(address);
			needsRead = false;
		}
		return super.read();
	}
}
