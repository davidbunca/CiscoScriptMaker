package com.bunca.objects;

import java.util.ArrayList;

public class EIGRP {
    private ArrayList<Network> networks = new ArrayList<>();
    private String eigrpProcess = "1";
    private boolean summarysation = false;

    private boolean ipv6 = false;
    private ArrayList<Interface> ipv6Interfaces = new ArrayList<>();

    private String configuration;

    public EIGRP() {
    }

    public EIGRP(String eigrpProcess, boolean summarysation) {
        setEigrpProcess(eigrpProcess);
        setSummarysation(summarysation);
    }

    public ArrayList<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<Network> networks) {
        this.networks = networks;
    }

    public String getEigrpProcess() {
        return eigrpProcess;
    }

    public void setEigrpProcess(String eigrpProcess) {
        this.eigrpProcess = eigrpProcess;
    }

    public boolean isSummarysation() {
        return summarysation;
    }

    public void setSummarysation(boolean summarysation) {
        this.summarysation = summarysation;
    }

    public String getConfiguration() {
        if (!ipv6) {
            configuration = "router eigrp " + eigrpProcess + "\n";
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
                configuration += interfac.getIpv6EigrpConf(eigrpProcess) + "\nexit";
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

    public ArrayList<Interface> getIpv6Interfaces() {
        return ipv6Interfaces;
    }
}
