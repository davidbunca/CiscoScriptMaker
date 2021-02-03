package com.bunca.objects;

public class Network {
    Address address;
    private String ospfArea = "0";

    public Network() {

    }

    public Network(Address address, String ospfArea) {
        setAddress(address);
        setOspfArea(ospfArea);
    }

    public Network(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOspfArea() {
        return ospfArea;
    }

    public void setOspfArea(String ospfArea) {
        this.ospfArea = ospfArea;
    }
}
