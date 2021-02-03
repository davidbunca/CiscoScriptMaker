package com.bunca.objects;

import java.util.ArrayList;

public class Rip {
    private ArrayList<Network> networks = new ArrayList<>();
    private String ripVersion = "2";
    private boolean summarysation = false;

    private boolean ipv6 = false;
    private String procesName = "process1";
    private ArrayList<Interface> ipv6Interfaces = new ArrayList<>();

    private String configuration;

    public Rip() {
    }

    public Rip(String ripVersion, boolean summarysation) {
        setRipVersion(ripVersion);
        setSummarysation(summarysation);
    }

    public ArrayList<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<Network> networks) {
        this.networks = networks;
    }

    public String getRipVersion() {
        return ripVersion;
    }

    public void setRipVersion(String ripVersion) {
        this.ripVersion = ripVersion;
    }

    public boolean isSummarysation() {
        return summarysation;
    }

    public void setSummarysation(boolean summarysation) {
        this.summarysation = summarysation;
    }

    public String getConfiguration() {
        if (!ipv6) {
            configuration = "router rip" + "\nversion " + ripVersion + "\n";
            for (Network network : networks) {
                configuration += "network " + network.getAddress().getAddress() + "\n";
            }
            if (!summarysation) {
                configuration += "no auto-summary";
            } else {
                configuration += "auto-summary";
            }
            configuration += "\nexit";
        } else {
            configuration = "ipv6 unicast-routing";
            for (Interface interfac : ipv6Interfaces) {
                configuration += interfac.getIpv6RipConf(procesName) + "\nexit";
            }
        }
        configuration += "\n";
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public boolean isIpv6() {
        return ipv6;
    }

    public void setIpv6(boolean ipv6) {
        this.ipv6 = ipv6;
    }

    public String getProcesName() {
        return procesName;
    }

    public void setProcesName(String procesName) {
        this.procesName = procesName;
    }

    public ArrayList<Interface> getIpv6Interfaces() {
        return ipv6Interfaces;
    }
}
