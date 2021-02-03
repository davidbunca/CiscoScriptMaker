package com.bunca.objects;

public class Address {
    private String address = "";
    private Prefix prefix = new Prefix();


    public Address(String address, Prefix prefix) {
        setAddress(address);
        setPrefix(prefix);
    }

    public Address(String address) {
        setAddress(address);
    }

    public Address() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }
}
