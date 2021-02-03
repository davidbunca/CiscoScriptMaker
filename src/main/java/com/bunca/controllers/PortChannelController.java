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
import com.bunca.objects.PortChannel;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PortChannelController implements Initializable {


    PortChannel portChannel = new PortChannel("1", "", false);
    String[] interfaces = {"FastEthernet", "GigabitEthernet", "TengigabitEthernet", "Serial"};
    String[] interfaceNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    String[] portChannelModes = {"active - Enable LACP unconditionally", "auto - Enable PAgP only if a PAgP device is detected", "desirable - Enable PAgP unconditionally", "on - Enable Etherchannel only", "passive - Enable LACP only if a LACP device is detected"};

    @FXML
    AnchorPane rootPane;
    @FXML
    ChoiceBox interfaceBox, portNumberBox, portNumberBox2, portNumberBox3, portChannelMode;
    @FXML
    Label serialSlash;
    @FXML
    TextField portChannelField;
    @FXML
    CheckBox enableBox;
    @FXML
    TextArea descriptionArea, configurationArea;
    @FXML
    Button copyButton, backButton, clearButton, addInterfaceButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        interfaceBox.setItems(FXCollections.observableArrayList(interfaces));
        portNumberBox.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox2.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox3.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portChannelMode.setItems(FXCollections.observableArrayList(portChannelModes));

        interfaceBox.getSelectionModel().select(0);
        portNumberBox.getSelectionModel().select(0);
        portNumberBox2.getSelectionModel().select(0);
        portNumberBox3.getSelectionModel().select(0);
        portChannelMode.getSelectionModel().select(0);


        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                showConfiguration();
            }
        };

        ChangeListener<String> changeInterfaceListener = new ChangeListener<String>() {
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
            }
        };


        interfaceBox.getSelectionModel().selectedItemProperty().addListener(changeInterfaceListener);
        portNumberBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
        portNumberBox2.getSelectionModel().selectedItemProperty().addListener(changeListener);
        portNumberBox3.getSelectionModel().selectedItemProperty().addListener(changeListener);
        descriptionArea.textProperty().addListener(changeListener);
        portChannelField.textProperty().addListener(changeListener);

        enableBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showConfiguration();
            }
        });

        addInterfaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String interfaceString = interfaceBox.getValue().toString() + " ";
                if (!interfaceString.equals("Serial ")) {
                    interfaceString += portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString();
                    portNumberBox2.getSelectionModel().select(portNumberBox2.getSelectionModel().getSelectedIndex() + 1);
                } else {
                    interfaceString += portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString() + "/" + portNumberBox3.getValue().toString();
                    portNumberBox3.getSelectionModel().select(portNumberBox3.getSelectionModel().getSelectedIndex() + 1);

                }
                String portChannelModeString = "on";
                if (portChannelMode.getValue().toString().equals(portChannelModes[0])) {
                    portChannelModeString = "active";
                } else if (portChannelMode.getValue().toString().equals(portChannelModes[1])) {
                    portChannelModeString = "auto";
                } else if (portChannelMode.getValue().toString().equals(portChannelModes[2])) {
                    portChannelModeString = "desirable";
                } else if (portChannelMode.getValue().toString().equals(portChannelModes[3])) {
                    portChannelModeString = "on";
                } else if (portChannelMode.getValue().toString().equals(portChannelModes[4])) {
                    portChannelModeString = "passive";
                }

                portChannel.addInterfaceToPortChannel(interfaceString, portChannelModeString);
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/portChannel.fxml"));
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/switch.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });

        showConfiguration();


    }


    private void showConfiguration() {
        if (!portChannelField.getText().equals("")) {
            portChannel.setPortChanelNumber(portChannelField.getText());
        } else {
            portChannel.setPortChanelNumber("1");
        }
        portChannel.setDescription(descriptionArea.getText());
        portChannel.setEnabled(enableBox.isSelected());
        configurationArea.setText(portChannel.getConfiguration());
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/switches/datacenter/nexus5000/sw/configuration/guide/cli/CLIConfigurationGuide/EtherChannel.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
