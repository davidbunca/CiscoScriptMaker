package com.bunca.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.bunca.objects.ExtAclRule;
import com.bunca.objects.ExtendedACL;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExtAccessListController implements Initializable {


    ExtendedACL extendedACL = new ExtendedACL("100");
    String[] actions = {"deny", "permit"};
    String[] protocols = {"ahp - Authentication Header Protocol",
            "eigrp - Cisco's EIGRP routing protocol",
            "esp - Encapsulation Security Payload",
            "gre - Cisco's GRE tunneling",
            "icmp - Internet Control Message Protocol",
            "ip - Any Internet Protocol",
            "ospf - OSPF routing protocol",
            "tcp - Transmission Control Protocol",
            "udp - User Datagram Protocol"};
    String[] portRules = {"eq - Match only packets on a given port number",
            "established - established",
            "gt - Match only packets with a greater port number",
            "lt - Match only packets with a lower port number",
            "neq - Match only packets not on a given port number",
            "range - Match only packets in the range of port numbers"};
    String[] icmpRules = {"echo - Echo (ping)",
            "echo-reply - Echo reply",
            "host-unreachable - Host unreachable",
            "net-unreachable - Net unreachable",
            "port-unreachable - Port unreachable",
            "protocol-unreachable - Protocol unreachable",
            "ttl-exceeded - TTL exceeded",
            "unreachable - All unreachables"};
    String[] ipRules = {"dscp - Match packets with given dscp value",
            "precedence - Match packets with given precedence value"};

    @FXML
    AnchorPane rootPane;
    @FXML
    TextArea configurationArea;
    @FXML
    Button backButton, copyButton, clearButton, addRuleButton;
    @FXML
    TextField aclNumberField, sourceIpField, sourceWildcardField, destinationIpField, destinationWildcardField, portField;
    @FXML
    ChoiceBox actionBox, protocolBox, portRuleBox;
    @FXML
    Label portRuleLabel, portLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        actionBox.setItems(FXCollections.observableArrayList(actions));
        actionBox.getSelectionModel().select(0);
        protocolBox.setItems(FXCollections.observableArrayList(protocols));
        protocolBox.getSelectionModel().select(0);
        portRuleLabel.setText("Port rule:");
        portRuleLabel.setVisible(false);
        portRuleBox.setItems(FXCollections.observableArrayList(portRules));
        portRuleBox.getSelectionModel().select(0);
        portRuleBox.setVisible(false);
        portLabel.setText("Port:");
        portLabel.setVisible(false);
        portField.setVisible(false);
        portField.setText("");


        copyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Toolkit.getDefaultToolkit()
                        .getSystemClipboard()
                        .setContents(
                                new StringSelection(configurationArea.getText()),
                                null
                        );

            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/extAccessList.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/menu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });

        addRuleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String protocol = "ahp";
                String portRule = "eq";
                if (protocolBox.getValue().toString().equals(protocols[0])) {
                    protocol = "ahp";
                    portRule = "";
                } else if (protocolBox.getValue().toString().equals(protocols[1])) {
                    protocol = "eigrp";
                    portRule = "";
                } else if (protocolBox.getValue().toString().equals(protocols[2])) {
                    protocol = "esp";
                    portRule = "";
                } else if (protocolBox.getValue().toString().equals(protocols[3])) {
                    protocol = "gre";
                    portRule = "";
                } else if (protocolBox.getValue().toString().equals(protocols[4])) {
                    protocol = "icmp";
                    if (portRuleBox.getValue().toString().equals(icmpRules[0])) {
                        portRule = "echo";
                    } else if (portRuleBox.getValue().toString().equals(icmpRules[1])) {
                        portRule = "echo-reply";
                    } else if (portRuleBox.getValue().toString().equals(icmpRules[2])) {
                        portRule = "host-unreachable";
                    } else if (portRuleBox.getValue().toString().equals(icmpRules[3])) {
                        portRule = "net-unreachable";
                    } else if (portRuleBox.getValue().toString().equals(icmpRules[4])) {
                        portRule = "port-unreachable";
                    } else if (portRuleBox.getValue().toString().equals(icmpRules[5])) {
                        portRule = "protocol-unreachable";
                    } else if (portRuleBox.getValue().toString().equals(icmpRules[6])) {
                        portRule = "ttl-exceeded";
                    } else if (portRuleBox.getValue().toString().equals(icmpRules[7])) {
                        portRule = "unreachable";
                    }
                } else if (protocolBox.getValue().toString().equals(protocols[5])) {
                    protocol = "ip";
                    if (portRuleBox.getValue().toString().equals(ipRules[0])) {
                        portRule = "dscp";
                    } else if (portRuleBox.getValue().toString().equals(ipRules[1])) {
                        portRule = "precedence";
                    }
                } else if (protocolBox.getValue().toString().equals(protocols[6])) {
                    protocol = "ospf";
                    portRule = "";
                } else if (protocolBox.getValue().toString().equals(protocols[7])) {
                    protocol = "tcp";
                    if (portRuleBox.getValue().toString().equals(portRules[0])) {
                        portRule = "eq";
                    } else if (portRuleBox.getValue().toString().equals(portRules[1])) {
                        portRule = "established";
                    } else if (portRuleBox.getValue().toString().equals(portRules[2])) {
                        portRule = "gt";
                    } else if (portRuleBox.getValue().toString().equals(portRules[3])) {
                        portRule = "lt";
                    } else if (portRuleBox.getValue().toString().equals(portRules[4])) {
                        portRule = "neq";
                    } else if (portRuleBox.getValue().toString().equals(portRules[5])) {
                        portRule = "range";
                    }
                } else if (protocolBox.getValue().toString().equals(protocols[8])) {
                    protocol = "udp";
                    if (portRuleBox.getValue().toString().equals(portRules[0])) {
                        portRule = "eq";
                    } else if (portRuleBox.getValue().toString().equals(portRules[1])) {
                        portRule = "established";
                    } else if (portRuleBox.getValue().toString().equals(portRules[2])) {
                        portRule = "gt";
                    } else if (portRuleBox.getValue().toString().equals(portRules[3])) {
                        portRule = "lt";
                    } else if (portRuleBox.getValue().toString().equals(portRules[4])) {
                        portRule = "neq";
                    } else if (portRuleBox.getValue().toString().equals(portRules[5])) {
                        portRule = "range";
                    }
                }

                extendedACL.getExtAclRules().add(new ExtAclRule(actionBox.getValue().toString(), sourceIpField.getText(), sourceWildcardField.getText(), protocol, destinationIpField.getText(), destinationWildcardField.getText(), portRule, portField.getText()));
                showConfiguration();
                sourceIpField.setText("");
                sourceWildcardField.setText("");
                destinationIpField.setText("");
                destinationWildcardField.setText("");
            }
        });

        aclNumberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    extendedACL.setNumberOrName(newValue);
                } else {
                    extendedACL.setNumberOrName("100");
                }
                showConfiguration();
            }
        });

        sourceIpField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {

                } else {

                }
            }
        });

        actionBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {

            }
        });
        protocolBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                if (newValue.equals(protocols[0]) || newValue.equals(protocols[1]) || newValue.equals(protocols[2]) || newValue.equals(protocols[3]) || newValue.equals(protocols[6])) {
                    portRuleLabel.setText("Port rule:");
                    portRuleLabel.setVisible(false);
                    try {
                        portRuleBox.setItems(FXCollections.observableArrayList(""));
                        portRuleBox.getSelectionModel().select(0);
                    } catch (Exception e) {

                    }
                    portRuleBox.setVisible(false);

                    portLabel.setText("Port:");
                    portLabel.setVisible(false);
                    portField.setVisible(false);
                    portField.setText("");
                } else if (newValue.equals(protocols[4])) {
                    portRuleLabel.setText("ICMP rule:");
                    portRuleLabel.setVisible(true);
                    try {
                        portRuleBox.setItems(FXCollections.observableArrayList(icmpRules));
                        portRuleBox.getSelectionModel().select(0);
                    } catch (Exception e) {

                    }
                    portRuleBox.setVisible(true);

                    portLabel.setText("Port:");
                    portLabel.setVisible(false);
                    portField.setVisible(false);
                    portField.setText("");
                } else if (newValue.equals(protocols[5])) {
                    portRuleLabel.setText("IP rule:");
                    portRuleLabel.setVisible(true);
                    try {
                        portRuleBox.setItems(FXCollections.observableArrayList(ipRules));
                        portRuleBox.getSelectionModel().select(0);
                    } catch (Exception e) {

                    }
                    portRuleBox.setVisible(true);

                    portLabel.setText("IP value:");
                    portLabel.setVisible(true);
                    portField.setVisible(true);
                    portField.setText("");
                } else if (newValue.equals(protocols[7]) || newValue.equals(protocols[8])) {
                    portRuleLabel.setText("Port rule:");
                    portRuleLabel.setVisible(true);
                    try {
                        portRuleBox.setItems(FXCollections.observableArrayList(portRules));
                        portRuleBox.getSelectionModel().select(0);
                    } catch (Exception e) {

                    }
                    portRuleBox.setVisible(true);

                    portLabel.setText("Port:");
                    portLabel.setVisible(true);
                    portField.setVisible(true);
                    portField.setText("");
                }
            }
        });
        showConfiguration();
    }

    private void showConfiguration() {
        configurationArea.setText(extendedACL.getConfiguration());
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/support/docs/security/ios-firewall/23602-confaccesslists.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
