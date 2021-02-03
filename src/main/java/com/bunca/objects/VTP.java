package com.bunca.objects;

public class VTP {
    private String mode = "server";
    private String domain = "DOMAIN";
    private String version = "2";
    private String password = "ciscovtp";

    private String configuration;

    public VTP() {
    }

    public VTP(String mode, String domain, String version) {
        setMode(mode);
        setDomain(domain);
        setVersion(version);
    }

    public VTP(String mode, String domain) {
        setMode(mode);
        setDomain(domain);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConfiguration() {
        configuration = "vtp version " + version;
        configuration += "\nvtp domain " + domain;
        configuration += "\nvtp password " + password;
        configuration += "\nvtp mode " + mode;
        configuration += "\n";
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
