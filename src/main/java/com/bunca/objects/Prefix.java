package com.bunca.objects;

public class Prefix {
    private String prefix = "";

    private String subnetMask = "";
    private String ipv6SubnetMask = "";
    private String wildCard = "";

    private boolean ipv6 = false;

    public Prefix(String prefix, boolean ipv6) {
        setIpv6(ipv6);
        setPrefix(prefix);
    }

    public Prefix(String prefix) {
        setPrefix(prefix);
    }

    public Prefix() {

    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSubnetMask() {
        if (ipv6) {
            subnetMask = prefix;
        } else {
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
        return subnetMask;
    }

    public String getWildCard() {
        switch (prefix) {
            case "/0":
                wildCard = "255.255.255.255";
                break;
            case "/1":
                wildCard = "127.255.255.255";
                break;
            case "/2":
                wildCard = "63.255.255.255";
                break;
            case "/3":
                wildCard = "31.255.255.255";
                break;
            case "/4":
                wildCard = "15.255.255.255";
                break;
            case "/5":
                wildCard = "7.255.255.255";
                break;
            case "/6":
                wildCard = "3.255.255.255";
                break;
            case "/7":
                wildCard = "1.255.255.255";
                break;
            case "/8":
                wildCard = "0.255.255.255";
                break;
            case "/9":
                wildCard = "0.127.255.255";
                break;
            case "/10":
                wildCard = "0.63.255.255";
                break;
            case "/11":
                wildCard = "0.31.255.255";
                break;
            case "/12":
                wildCard = "0.15.255.255";
                break;
            case "/13":
                wildCard = "0.7.255.255";
                break;
            case "/14":
                wildCard = "0.3.255.255";
                break;
            case "/15":
                wildCard = "0.1.255.255";
                break;
            case "/16":
                wildCard = "0.0.255.255";
                break;
            case "/17":
                wildCard = "0.0.127.255";
                break;
            case "/18":
                wildCard = "0.0.63.255";
                break;
            case "/19":
                wildCard = "0.0.31.255";
                break;
            case "/20":
                wildCard = "0.0.15.255";
                break;
            case "/21":
                wildCard = "0.0.7.255";
                break;
            case "/22":
                wildCard = "0.0.3.255";
                break;
            case "/23":
                wildCard = "0.0.1.255";
                break;
            case "/24":
                wildCard = "0.0.0.255";
                break;
            case "/25":
                wildCard = "0.0.0.127";
                break;
            case "/26":
                wildCard = "0.0.0.63";
                break;
            case "/27":
                wildCard = "0.0.0.31";
                break;
            case "/28":
                wildCard = "0.0.0.15";
                break;
            case "/29":
                wildCard = "0.0.0.7";
                break;
            case "/30":
                wildCard = "0.0.0.3";
                break;
            case "/31":
                wildCard = "0.0.0.1";
                break;
            case "/32":
                wildCard = "0.0.0.0";
                break;
        }
        return wildCard;
    }

    public boolean isIpv6() {
        return ipv6;
    }

    public void setIpv6(boolean ipv6) {
        this.ipv6 = ipv6;
    }
}
