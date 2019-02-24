package com.example.basicframework.base.bus;

public class OpenDrawerBus {
    private boolean isOpen = false;

    public OpenDrawerBus(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
