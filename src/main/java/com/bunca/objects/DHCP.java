package com.bunca.objects;

import java.util.ArrayList;

public class DHCP {
    private String poolName = "POOL1";
    private String domainName = "cisco.com";
    private ArrayList<String> dnsServers = new ArrayList<>();
    private ArrayList<String> excludedAddresses = new ArrayList<>();
    private Network network = new Network();

    private String configuration;

    public DHCP(String poolName, String domainName) {
        setPoolName(poolName);
        setDomainName(domainName);
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public ArrayList<String> getDnsServers() {
        return dnsServers;
    }

    public ArrayList<String> getExcludedAddresses() {
        return excludedAddresses;
    }

    public String getConfiguration() {
        configuration = "ip dhcp pool " + poolName + "\n";
        if (!network.getAddress().getAddress().equals("")) {
            configuration += "network " + network.getAddress().getAddress() + " " + network.getAddress().getPrefix().getSubnetMask() + "\n";
        }
        configuration += "domain-name " + domainName + "\n";
        if (dnsServers.size() != 0) {
            configuration += "dns server ";
            for (String serverIp : dnsServers) {
                configuration += serverIp + " ";
            }
            configuration += "\n";
        }
        configuration += "exit\n";
        if (excludedAddresses.size() != 0) {
            for (String range : excludedAddresses) {
                configuration += "ip dhcp excluded-address " + range + "\n";
            }
        }
        return configuration;
    }
}
