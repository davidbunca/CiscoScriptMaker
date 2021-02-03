package com.bunca.objects;

public class DynamicNAT {
    private String startIP = "";
    private String endIP = "";
    private String poolName = "";
    private String aclName = "";
    private String prefix = "";

    private String configuration = "";

    public DynamicNAT() {
    }

    public DynamicNAT(String startIP, String endIP, String poolName, String aclName, String prefix) {
        setStartIP(startIP);
        setEndIP(endIP);
        setPoolName(poolName);
        setAclName(aclName);
        setPrefix(prefix);
    }

    public String getStartIP() {
        return startIP;
    }

    public void setStartIP(String startIP) {
        this.startIP = startIP;
    }

    public String getEndIP() {
        return endIP;
    }

    public void setEndIP(String endIP) {
        this.endIP = endIP;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getAclName() {
        return aclName;
    }

    public void setAclName(String aclName) {
        this.aclName = aclName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getConfiguration() {
        configuration = "";
        if (!startIP.equals("") && !endIP.equals("")) {
            if (poolName.equals("")){
                poolName = "NAT1";
            }
            configuration += "ip nat pool " + poolName + " " + startIP + " " + endIP + " netmask " + getNetMask() + "\n";
        }
        if (!aclName.equals("") && !poolName.equals("")) {
            configuration += "ip nat inside source list " + aclName + " pool " + poolName + "\n";
        }
        return configuration;
    }

    private String getNetMask(){
        String subnetMask = "";
        switch (prefix) {
            case "/0":
                subnetMask = "0.0.0.0";
                break;
            case "/1":
                subnetMask = "128.0.0.0";
                break;
            case "/2":
                subnetMask = "192.0.0.0";
                break;
            case "/3":
                subnetMask = "224.0.0.0";
                break;
            case "/4":
                subnetMask = "240.0.0.0";
                break;
            case "/5":
                subnetMask = "248.0.0.0";
                break;
            case "/6":
                subnetMask = "252.0.0.0";
                break;
            case "/7":
                subnetMask = "254.0.0.0";
                break;
            case "/8":
                subnetMask = "255.0.0.0";
                break;
            case "/9":
                subnetMask = "255.128.0.0";
                break;
            case "/10":
                subnetMask = "255.192.0.0";
                break;
            case "/11":
                subnetMask = "255.224.0.0";
                break;
            case "/12":
                subnetMask = "255.240.0.0";
                break;
            case "/13":
                subnetMask = "255.248.0.0";
                break;
            case "/14":
                subnetMask = "255.252.0.0";
                break;
            case "/15":
                subnetMask = "255.254.0.0";
                break;
            case "/16":
                subnetMask = "255.255.0.0";
                break;
            case "/17":
                subnetMask = "255.255.128.0";
                break;
            case "/18":
                subnetMask = "255.255.192.0";
                break;
            case "/19":
                subnetMask = "255.255.224.0";
                break;
            case "/20":
                subnetMask = "255.255.240.0";
                break;
            case "/21":
                subnetMask = "255.255.248.0";
                break;
            case "/22":
                subnetMask = "255.255.252.0";
                break;
            case "/23":
                subnetMask = "255.255.254.0";
                break;
            case "/24":
                subnetMask = "255.255.255.0";
                break;
            case "/25":
                subnetMask = "255.255.255.128";
                break;
            case "/26":
                subnetMask = "255.255.255.192";
                break;
            case "/27":
                subnetMask = "255.255.255.224";
                break;
            case "/28":
                subnetMask = "255.255.255.240";
                break;
            case "/29":
                subnetMask = "255.255.255.248";
                break;
            case "/30":
                subnetMask = "255.255.255.252";
                break;
            case "/31":
                subnetMask = "255.255.255.254";
                break;
            case "/32":
                subnetMask = "255.255.255.255";
                break;
        }
        return subnetMask;
    }
}
