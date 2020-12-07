package me.lemon.winter.memory;

public class BaseMemoryHandler<T> {
	protected long address;
	protected T storedValue;
	protected boolean needsRead;
	protected boolean stores;

	public BaseMemoryHandler(long address, boolean stores) {
		this.address = address;
		this.stores = stores;

		this.needsRead = true;
		storedValue = null;
	}

	public boolean write(Object value) {
		if(storedValue != null && !value.getClass().equals(storedValue.getClass()))
			return false;
		needsRead = true;
		return true;
	}

	public T read() {
		if(!stores) {
			this.needsRead = true;
		}
		return this.storedValue;
	}
}
