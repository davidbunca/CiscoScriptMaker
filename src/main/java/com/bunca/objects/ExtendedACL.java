package com.bunca.objects;

import java.util.ArrayList;

public class ExtendedACL {
    private String numberOrName = "100";
    private ArrayList<ExtAclRule> extAclRules = new ArrayList<>();

    private String configuration;

    public ExtendedACL(String numberOrName) {
        setNumberOrName(numberOrName);
    }

    public String getNumberOrName() {
        return numberOrName;
    }

    public void setNumberOrName(String numberOrName) {
        this.numberOrName = numberOrName;
    }

    public ArrayList<ExtAclRule> getExtAclRules() {
        return extAclRules;
    }

    public String getConfiguration() {
        configuration = "ip access-list extended " + numberOrName + "\n";
        for (ExtAclRule extAclRule : extAclRules) {
            configuration += extAclRule.getConfiguration();
        }
        configuration += "exit\n";
        return configuration;
    }
}
