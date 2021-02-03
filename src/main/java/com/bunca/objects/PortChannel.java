package com.bunca.objects;

import java.util.ArrayList;

public class PortChannel {
    private ArrayList<Interface> interfaces = new ArrayList<>(8);
    private String portChanelNumber = "1";
    private String description = "";
    private boolean enabled = false;

    private String configuration = "";

    public PortChannel(String portChanelNumber, String description, boolean enabled) {
        setPortChanelNumber(portChanelNumber);
        setDescription(description);
        setEnabled(enabled);
    }

    public ArrayList<Interface> getInterfaces() {
        return interfaces;
    }

    public void addInterfaceToPortChannel(String interfaceId, String portChanelMode) {
        if (interfaces.size() != 8) {
            interfaces.add(new Interface(interfaceId, portChanelNumber, portChanelMode));
        }
    }

    public String getPortChanelNumber() {
        return portChanelNumber;
    }

    public void setPortChanelNumber(String portChanelNumber) {
        this.portChanelNumber = portChanelNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfiguration() {
        configuration = "int port-channel " + portChanelNumber + "\n";
        if (!description.equals("")) {
            configuration += "desc " + description + "\n";
        }
        if (enabled) {
            configuration += "no sh\n";
        } else {
            configuration += "sh\n";
        }
        configuration += "exit\n";
        for (Interface inteface : interfaces) {
            inteface.setPortChannelGroup(portChanelNumber);
            configuration += inteface.getConfiguration() + "\n";
        }
        return configuration;
    }

}
