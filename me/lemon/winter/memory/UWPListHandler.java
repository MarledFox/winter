package me.lemon.winter.memory;

import me.lemon.winter.Globals;

public class UWPListHandler {
	private long address;
	private long current;

	public UWPListHandler(long address) {
		this.address = address;
		this.current = Globals.getMinecraft().readLong(address);
	}

	public long next() {
		long stop = Globals.getMinecraft().readLong(address + 0x8);
		long _return = -1;
		if(current < stop) {
			_return = Globals.getMinecraft().readLong(current);
			current += 0x8;
		}
		return _return;
	}
}
