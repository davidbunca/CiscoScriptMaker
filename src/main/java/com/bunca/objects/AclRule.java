package com.bunca.objects;

public class AclRule {
    private String action = "deny";
    private String sourceIp = "";
    private String sourceWildcard = "";

    private String configuration;

    public AclRule(String action, String sourceIp, String wildcard) {
        setAction(action);
        setSourceIp(sourceIp);
        setWildcard(wildcard);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getWildcard() {
        return sourceWildcard;
    }

    public void setWildcard(String wildcard) {
        this.sourceWildcard = wildcard;
    }

    public String getConfiguration() {
        configuration = action + " " + getSourceIpRule() + "\n";
        return configuration;
    }

    protected String getSourceIpRule() {
        String ipRule = "";
        if (sourceWildcard.equals("") || sourceWildcard.equals("0.0.0.0") || sourceWildcard.equals("255.255.255.255")) {
            if (sourceIp.equals("") || sourceWildcard.equals("255.255.255.255")) {
                ipRule += "any";
            } else {
                ipRule += "host " + sourceIp;
            }
        } else {
            if (sourceIp.equals("")) {
                ipRule += "any";
            } else {
                ipRule += sourceIp + " " + sourceWildcard;
            }
        }
        return ipRule;
    }
}
