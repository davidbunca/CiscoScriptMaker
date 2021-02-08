package com.bunca.controllers;

import com.bunca.objects.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DhcpController implements Initializable {

    DHCP dhcp = new DHCP("POOL1", "cisco.com");

    String[] ipv4Prefixes = {"/0", "/1", "/2", "/3", "/4", "/5", "/6", "/7", "/8", "/9", "/10", "/11", "/12", "/13", "/14", "/15", "/16", "/17", "/18", "/18", "/20", "/21", "/22", "/23", "/24", "/25", "/26", "/27", "/28", "/29", "/30", "/31", "/32"};

    @FXML
    AnchorPane rootPane;
    @FXML
    ChoiceBox prefixBox;
    @FXML
    TextField networkField, dnsIpField, poolNameField, domainNameField, startExcludedField, endExcludedField;
    @FXML
    TextArea configurationArea;
    @FXML
    Button copyButton, backButton, clearButton, addDnsButton, addExcludedButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        prefixBox.setItems(FXCollections.observableArrayList(ipv4Prefixes));
        prefixBox.getSelectionModel().select(24);


        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                showConfiguration();
            }
        };


        prefixBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
        poolNameField.textProperty().addListener(changeListener);
        domainNameField.textProperty().addListener(changeListener);
        networkField.textProperty().addListener(changeListener);


        addDnsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!dnsIpField.getText().equals("")) dhcp.getDnsServers().add(dnsIpField.getText());
                dnsIpField.setText("");
                showConfiguration();
            }
        });

        addExcludedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!startExcludedField.getText().equals("") && !endExcludedField.getText().equals("")) {
                    dhcp.getExcludedAddresses().add(startExcludedField.getText() + " " + endExcludedField.getText());
                    startExcludedField.setText("");
                    endExcludedField.setText("");
                    showConfiguration();
                }
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/dhcp.fxml"));
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

    private void showConfiguration() {
        if (!poolNameField.getText().equals("")) {
            dhcp.setPoolName(poolNameField.getText());
        } else dhcp.setPoolName("POOL1");
        if (!domainNameField.getText().equals("")) {
            dhcp.setDomainName(domainNameField.getText());
        } else dhcp.setDomainName("cisco.com");
        dhcp.setNetwork(new Network(new Address(networkField.getText(), new Prefix(prefixBox.getValue().toString()))));
        configurationArea.setText(dhcp.getConfiguration());
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/ios-xml/ios/ipaddr_dhcp/configuration/xe-3se/3850/dhcp-xe-3se-3850-book/config-dhcp-server.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
