package com.bunca.objects;

import java.util.ArrayList;

public class StaticNAT {
    private String localIP = "";
    private String globalIP = "";
    private ArrayList<Interface> interfaces = new ArrayList<>();

    private String configuration = "";

    public StaticNAT() {
    }

    public StaticNAT(String localIP, String globalIP) {
        setLocalIP(localIP);
        setGlobalIP(globalIP);
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP(String localIP) {
        this.localIP = localIP;
    }

    public String getGlobalIP() {
        return globalIP;
    }

    public void setGlobalIP(String globalIP) {
        this.globalIP = globalIP;
    }

    public ArrayList<Interface> getInterfaces() {
        return interfaces;
    }

    public String getConfiguration() {
        configuration = "";
        if (!localIP.equals("") && !globalIP.equals("")){
            configuration = "ip nat inside source static " + localIP + " " + globalIP + "\n";
        }
        for (Interface interfac : interfaces) {
            configuration += interfac.getNatConf();
        }
        return configuration;
    }
}
