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
import com.bunca.objects.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaticRoutinController implements Initializable {

    StaticRouting staticRouting = new StaticRouting();
    String[] interfaces = {"FastEthernet", "GigabitEthernet", "TengigabitEthernet", "Serial"};
    String[] ipv4Prefixes = {"/0", "/1", "/2", "/3", "/4", "/5", "/6", "/7", "/8", "/9", "/10", "/11", "/12", "/13", "/14", "/15", "/16", "/17", "/18", "/18", "/20", "/21", "/22", "/23", "/24", "/25", "/26", "/27", "/28", "/29", "/30", "/31", "/32"};
    String[] ipv6Prefixes = {"/4", "/8", "/12", "/16", "/20", "/24", "/28", "/32", "/36", "/40", "/44", "/48", "/52", "/56", "/60", "/64", "/68", "/72", "/76", "/80", "/84", "/88", "/92", "/96", "/100", "/104", "/108", "/112", "/116", "/120", "/124", "/128"};
    String[] interfaceNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};


    @FXML
    AnchorPane rootPane;
    @FXML
    ChoiceBox interfaceBox;
    @FXML
    ChoiceBox portNumberBox;
    @FXML
    ChoiceBox portNumberBox2;
    @FXML
    ChoiceBox portNumberBox3;
    @FXML
    Label serialSlash;
    @FXML
    ToggleButton ipv6Button;
    @FXML
    TextField networkField;
    @FXML
    ChoiceBox prefixBox;
    @FXML
    Button copyButton, backButton, clearButton;
    @FXML
    TextArea configurationArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        interfaceBox.setItems(FXCollections.observableArrayList(interfaces));
        prefixBox.setItems(FXCollections.observableArrayList(ipv4Prefixes));
        portNumberBox.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox2.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox3.setItems(FXCollections.observableArrayList(interfaceNumbers));

        interfaceBox.getSelectionModel().select(0);
        prefixBox.getSelectionModel().select(24);
        portNumberBox.getSelectionModel().select(0);
        portNumberBox2.getSelectionModel().select(0);
        portNumberBox3.getSelectionModel().select(0);


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
                showConfiguration();
            }
        };


        interfaceBox.getSelectionModel().selectedItemProperty().addListener(changeInterfaceListener);
        portNumberBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
        portNumberBox2.getSelectionModel().selectedItemProperty().addListener(changeListener);
        portNumberBox3.getSelectionModel().selectedItemProperty().addListener(changeListener);

        prefixBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                staticRouting.getNetwork().getAddress().getPrefix().setPrefix(newValue.toString());
                showConfiguration();
            }
        });

        networkField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    staticRouting.getNetwork().getAddress().setAddress(newValue);
                } else {
                    staticRouting.getNetwork().getAddress().setAddress("network");
                }
                showConfiguration();
            }
        });

        ipv6Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ipv6Button.isSelected()) {
                    ipv6Button.setText("IPv6");
                    staticRouting.getNetwork().getAddress().getPrefix().setIpv6(true);
                    prefixBox.setItems(FXCollections.observableArrayList(ipv6Prefixes));
                    prefixBox.getSelectionModel().select(15);
                    staticRouting.getNetwork().getAddress().getPrefix().setPrefix(prefixBox.getValue().toString());
                } else {
                    ipv6Button.setText("IPv4");
                    staticRouting.getNetwork().getAddress().getPrefix().setIpv6(false);
                    prefixBox.setItems(FXCollections.observableArrayList(ipv4Prefixes));
                    prefixBox.getSelectionModel().select(24);
                    staticRouting.getNetwork().getAddress().getPrefix().setPrefix(prefixBox.getValue().toString());
                }
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/staticRouting.fxml"));
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

        showConfiguration();


    }

    public void showConfiguration() {
        if (interfaceBox.getValue().toString().equals("Serial")) {
            staticRouting.setAnInterface(new Interface(interfaceBox.getValue().toString() + " " + portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString() + "/" + portNumberBox3.getValue().toString()));
        } else {
            staticRouting.setAnInterface(new Interface(interfaceBox.getValue().toString() + " " + portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString()));
        }
        configurationArea.setText(staticRouting.getConfiguration() + "\n");
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/switches/datacenter/sw/5_x/nx-os/unicast/configuration/guide/l3_cli_nxos/l3_route.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
