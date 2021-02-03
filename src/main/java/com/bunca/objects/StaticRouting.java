package com.bunca.objects;

public class StaticRouting {
    private Network network = new Network(new Address("network", new Prefix("/24", false)));
    private Interface anInterface;

    private String configuration;

    public StaticRouting(Network network, Interface anInterface) {
        setNetwork(network);
        setAnInterface(anInterface);
    }

    public StaticRouting() {

    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Interface getAnInterface() {
        return anInterface;
    }

    public void setAnInterface(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String getConfiguration() {

        if (network.getAddress().getPrefix().isIpv6()) {
            configuration = "ipv6 route " + network.getAddress().getAddress() + " " + network.getAddress().getPrefix().getSubnetMask() + " " + anInterface.getIntefaceId();
        } else {
            configuration = "ip route " + network.getAddress().getAddress() + " " + network.getAddress().getPrefix().getSubnetMask() + " " + anInterface.getIntefaceId();
        }
        return configuration;
    }
}
