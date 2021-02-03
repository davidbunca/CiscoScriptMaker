package com.bunca.objects;

public class VLAN {
    private String name = "";
    private String id = "1";
    private String address = "";
    private boolean enabled = false;

    private String configuration;

    public VLAN(String name, String id, String address, boolean enabled) {
        setName(name);
        setId(id);
        setAddress(address);
        setEnabled(enabled);
    }

    public VLAN() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfiguration() {
        configuration = "vlan " + id;
        if (!name.equals("")) {
            configuration += "\nname " + name;
        }
        configuration += "\nexit";
        if (!address.equals("")) {
            configuration += "\nint vlan " + id;
            configuration += "\nip add " + address;
            if (enabled) {
                configuration += "\nno sh";
            } else {
                configuration += "\nsh";
            }
            configuration += "\nexit";
        }
        configuration += "\n";
        return configuration;
    }
}
