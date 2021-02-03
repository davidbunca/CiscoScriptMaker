package com.bunca.objects;

public class Interface {
    private String intefaceId = "";
    private Address address = new Address();
    private String description = "";
    private boolean enabled = false;

    private String clockRate = "";

    private String vlan = "";
    private boolean trunk = false;
    private String portChannelGroup = "";
    private String portChannelMode = "on";

    private String natType = "";

    private String configuration;

    public Interface(String intefaceId, Address address, String description, boolean enabled, String clockRate) {
        setIntefaceId(intefaceId);
        setAddress(address);
        setDescription(description);
        setEnabled(enabled);
        setClockRate(clockRate);
    }

    public Interface(String intefaceId, String description, boolean enabled, String clockRate, String vlan, boolean trunk) {
        setIntefaceId(intefaceId);
        setDescription(description);
        setEnabled(enabled);
        setClockRate(clockRate);
        setVlan(vlan);
        setTrunk(trunk);
    }

    public Interface(String intefaceId) {
        setIntefaceId(intefaceId);
    }

    public Interface(String intefaceId, String portChannelGroup, String portChannelMode) {
        setIntefaceId(intefaceId);
        setPortChannelGroup(portChannelGroup);
        setEnabled(true);
        setPortChannelMode(portChannelMode);
    }

    public Interface(String intefaceId, String natType) {
        this.intefaceId = intefaceId;
        this.natType = natType;
    }

    public String getConfiguration() {
        if (address.getPrefix().isIpv6()) {
            configuration = "ipv6 unicast-routing\nint " + intefaceId + "\n";
        } else {
            configuration = "int " + intefaceId + "\n";
        }
        if (!trunk && !vlan.equals("")) {
            configuration += "switchport access vlan " + vlan + "\n";
        }
        if (trunk) {
            configuration += "switchport mode trunk\n";
        }
        if (!description.equals("")) {
            configuration += "desc " + description + "\n";
        }
        if (address.getPrefix().getSubnetMask().contains("/")) {
            configuration += "ipv6 enable\n";
        }
        if (!address.getAddress().equals("")) {
            if (address.getPrefix().isIpv6()) {
                configuration += "ipv6 add " + address.getAddress() + "" + address.getPrefix().getSubnetMask() + "\n";
            } else {
                configuration += "ip add " + address.getAddress() + " " + address.getPrefix().getSubnetMask() + "\n";
            }
        }
        if (intefaceId.contains("Serial")) {
            if (clockRate.equals("") || (Double.parseDouble(clockRate) < 1)) {
                configuration += "clock rate 128000\n";
            } else {
                configuration += "clock rate " + clockRate + "\n";
            }
        }
        if (!portChannelGroup.equals("")) {
            configuration += "channel-group " + portChannelGroup + " mode " + portChannelMode + "\n";
        }
        if (enabled) {
            configuration += "no sh\n";
        } else {
            configuration += "sh\n";
        }
        configuration += "exit";
        return configuration;
    }

    public String getIpv6RipConf(String process) {
        return "\nint " + intefaceId + "\nipv6 rip " + process + " enable";
    }

    public String getIpv6EigrpConf(String process) {
        return "\nint " + intefaceId + "\nipv6 eigrp " + process;
    }

    public String getNatConf() {
        String conf = "int " + intefaceId + "\n";
        conf += "ip nat " + natType + "\n";
        conf += "exit\n";
        return conf;
    }

    public String getIntefaceId() {
        return intefaceId;
    }

    public void setIntefaceId(String intefaceId) {
        this.intefaceId = intefaceId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClockRate() {
        return clockRate;
    }

    public void setClockRate(String clockRate) {
        this.clockRate = clockRate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public boolean isTrunk() {
        return trunk;
    }

    public void setTrunk(boolean trunk) {
        this.trunk = trunk;
    }

    public String getPortChannelGroup() {
        return portChannelGroup;
    }

    public void setPortChannelGroup(String portChannelGroup) {
        this.portChannelGroup = portChannelGroup;
    }

    public String getPortChannelMode() {
        return portChannelMode;
    }

    public void setPortChannelMode(String portChannelMode) {
        this.portChannelMode = portChannelMode;
    }
}
