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
import com.bunca.objects.Address;
import com.bunca.objects.Interface;
import com.bunca.objects.Network;
import com.bunca.objects.Rip;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RipProtocolController implements Initializable {

    Rip routerRip = new Rip();
    String[] interfaces = {"FastEthernet", "GigabitEthernet", "TengigabitEthernet", "Serial"};
    String[] interfaceNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    String[] versions = {"2"};
    @FXML
    private AnchorPane rootPane;

    @FXML
    private CheckBox summarisationBox;

    @FXML
    private TextField networkField, processField;

    @FXML
    private ChoiceBox interfaceBox, portNumberBox, portNumberBox2, portNumberBox3;

    @FXML
    private ToggleButton ipv6Button;

    @FXML
    private ChoiceBox versionBox;

    @FXML
    private TextArea configurationArea;

    @FXML
    private Button copyButton, addNetworkButton, backButton, addInterfaceButton, clearButton;

    @FXML
    private Label serialSlash, slash, interfaceLabel, processLabel, networkLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        interfaceBox.setItems(FXCollections.observableArrayList(interfaces));
        portNumberBox.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox2.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox3.setItems(FXCollections.observableArrayList(interfaceNumbers));
        versionBox.setItems(FXCollections.observableArrayList(versions));


        versionBox.getSelectionModel().select(0);
        interfaceBox.getSelectionModel().select(0);
        portNumberBox.getSelectionModel().select(0);
        portNumberBox2.getSelectionModel().select(0);
        portNumberBox3.getSelectionModel().select(0);


        addNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!networkField.getText().equals("")) {
                    routerRip.getNetworks().add(new Network(new Address(networkField.getText())));
                    networkField.setText("");
                }
                showConfiguration();
            }
        });

        addInterfaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!interfaceBox.getValue().toString().equals("Serial")) {
                    routerRip.getIpv6Interfaces().add(new Interface(interfaceBox.getValue().toString() + " " + portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString()));
                } else {
                    routerRip.getIpv6Interfaces().add(new Interface(interfaceBox.getValue().toString() + " " + portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString() + "/" + portNumberBox3.getValue().toString()));
                }
                showConfiguration();
            }
        });

        summarisationBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                routerRip.setSummarysation(summarisationBox.isSelected());
                showConfiguration();
            }
        });

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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/ripProtocol.fxml"));
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/router.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });

        ipv6Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ipv6Button.isSelected()) {
                    ipv6Button.setText("IPv6");
                    interfaceBox.setVisible(true);
                    portNumberBox.setVisible(true);
                    portNumberBox2.setVisible(true);
                    portNumberBox3.setVisible(false);
                    slash.setVisible(true);
                    serialSlash.setVisible(false);
                    processField.setVisible(true);
                    addInterfaceButton.setVisible(true);
                    interfaceLabel.setVisible(true);
                    processLabel.setVisible(true);

                    networkLabel.setVisible(false);
                    networkField.setVisible(false);
                    addNetworkButton.setVisible(false);
                    summarisationBox.setVisible(false);

                    routerRip.setIpv6(true);


                } else {
                    ipv6Button.setText("IPv4");
                    interfaceBox.setVisible(false);
                    portNumberBox.setVisible(false);
                    portNumberBox2.setVisible(false);
                    portNumberBox3.setVisible(false);
                    slash.setVisible(false);
                    serialSlash.setVisible(false);
                    processField.setVisible(false);
                    addInterfaceButton.setVisible(false);
                    interfaceLabel.setVisible(false);
                    processLabel.setVisible(false);

                    networkLabel.setVisible(true);
                    networkField.setVisible(true);
                    addNetworkButton.setVisible(true);
                    summarisationBox.setVisible(true);

                    routerRip.setIpv6(false);


                }
                showConfiguration();
            }
        });

        ChangeListener<String> changePortNumberListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                //showConfiguration();
            }
        };

        interfaceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                if (newValue != "Serial") {
                    portNumberBox3.setVisible(false);
                    serialSlash.setVisible(false);

                } else {
                    portNumberBox3.setVisible(true);
                    serialSlash.setVisible(true);

                }
                //showConfiguration();
            }
        });
        portNumberBox.getSelectionModel().selectedItemProperty().addListener(changePortNumberListener);
        portNumberBox2.getSelectionModel().selectedItemProperty().addListener(changePortNumberListener);
        portNumberBox3.getSelectionModel().selectedItemProperty().addListener(changePortNumberListener);
        processField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    routerRip.setProcesName(newValue);
                } else {
                    routerRip.setProcesName("process1");
                }
                showConfiguration();
            }
        });

        versionBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                routerRip.setRipVersion(newValue.toString());
            }
        });


        routerRip.setRipVersion(versionBox.getValue().toString());
        routerRip.setSummarysation(summarisationBox.isSelected());

        showConfiguration();
    }

    private void showConfiguration() {
        configurationArea.setText(routerRip.getConfiguration());
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/ios-xml/ios/iproute_rip/configuration/15-mt/irr-15-mt-book/irr-cfg-info-prot.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
