package com.bunca.objects;

import java.util.ArrayList;

public class StandradACL {
    private String numberOrName = "1";
    private ArrayList<AclRule> aclRules = new ArrayList<>();

    private String configuration;

    public StandradACL(String numberOrName) {
        setNumberOrName(numberOrName);
    }

    public String getNumberOrName() {
        return numberOrName;
    }

    public void setNumberOrName(String numberOrName) {
        this.numberOrName = numberOrName;
    }

    public ArrayList<AclRule> getAclRules() {
        return aclRules;
    }

    public String getConfiguration() {
        configuration = "ip access-list standard " + numberOrName + "\n";
        for (AclRule aclRule : aclRules) {
            configuration += aclRule.getConfiguration();
        }
        configuration += "exit\n";
        return configuration;
    }

}
