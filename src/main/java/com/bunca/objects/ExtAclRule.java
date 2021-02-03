package com.bunca.objects;

public class ExtAclRule extends AclRule {
    private String protocol = "ahp";
    private String destinationIp = "";
    private String destinationWildcard = "";
    private String portRule = "";
    private String port = "";

    private String configuration;

    public ExtAclRule(String action, String sourceIp, String wildcard, String protocol, String destinationIp, String destinationWildcard, String portRule, String port) {
        super(action, sourceIp, wildcard);
        setProtocol(protocol);
        setDestinationIp(destinationIp);
        setDestinationWildcard(destinationWildcard);
        setPortRule(portRule);
        setPort(port);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public String getDestinationWildcard() {
        return destinationWildcard;
    }

    public void setDestinationWildcard(String destinationWildcard) {
        this.destinationWildcard = destinationWildcard;
    }

    public String getPortRule() {
        return portRule;
    }

    public void setPortRule(String portRule) {
        this.portRule = portRule;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


    public String getConfiguration() {
        configuration = getAction() + " " + protocol + " " + super.getSourceIpRule() + " " + getDestinationIpRule() + " " + portRule + " " + port + "\n";
        return configuration;
    }

    private String getDestinationIpRule() {
        String ipRule = "";
        if (destinationWildcard.equals("") || destinationWildcard.equals("0.0.0.0") || destinationWildcard.equals("255.255.255.255")) {
            if (destinationIp.equals("") || destinationWildcard.equals("255.255.255.255")) {
                ipRule += "any";
            } else {
                ipRule += "host " + destinationIp;
            }
        } else {
            if (destinationIp.equals("")) {
                ipRule += "any";
            } else {
                ipRule += destinationIp + " " + destinationWildcard;
            }
        }
        return ipRule;
    }
}
