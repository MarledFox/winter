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
        this.storedValue = null;
    }

    public boolean write(Object value) {
        if (this.storedValue != null && !value.getClass().equals(this.storedValue.getClass())) {
            return false;
        } else {
            this.needsRead = true;
            return true;
        }
    }

    public T read() {
        if (!this.stores) {
            this.needsRead = true;
        }

        return this.storedValue;
    }
}
