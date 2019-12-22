package com.example.tranthihoan_day6;

public class Contact {
    String name;
    String xuat;
    boolean icon;

    public Contact(String name, String xuat, boolean icon) {
        this.name = name;
        this.xuat = xuat;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXuat() {
        return xuat;
    }

    public void setXuat(String xuat) {
        this.xuat = xuat;
    }

    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }
}
