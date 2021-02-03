package com.bunca.objects;

import java.util.ArrayList;

public class OSPFV2 {
    private ArrayList<Network> networks = new ArrayList<>();
    private String ospfProcess = "1";
    private boolean summarysation = false;

    private String configuration;

    public OSPFV2() {
    }


    public ArrayList<Network> getNetworks() {
        return networks;
    }

    public String getOspfProcess() {
        return ospfProcess;
    }

    public void setOspfProcess(String ospfProcess) {
        this.ospfProcess = ospfProcess;
    }

    public void setNetworks(ArrayList<Network> networks) {
        this.networks = networks;
    }


    public boolean isSummarysation() {
        return summarysation;
    }

    public void setSummarysation(boolean summarysation) {
        this.summarysation = summarysation;
    }

    public String getConfiguration() {

        configuration = "router ospf " + ospfProcess + "\n";
        for (Network network : networks) {
            configuration += "network " + network.getAddress().getAddress() + " " + network.getAddress().getPrefix().getWildCard() + " area " + network.getOspfArea() + "\n";
        }
        configuration += "exit";
        configuration += "\n";

        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

}
