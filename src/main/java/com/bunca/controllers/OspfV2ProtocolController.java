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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.bunca.objects.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OspfV2ProtocolController implements Initializable {


    OSPFV2 ospfv2Eigrp = new OSPFV2();
    String[] ipv4Prefixes = {"/0", "/1", "/2", "/3", "/4", "/5", "/6", "/7", "/8", "/9", "/10", "/11", "/12", "/13", "/14", "/15", "/16", "/17", "/18", "/18", "/20", "/21", "/22", "/23", "/24", "/25", "/26", "/27", "/28", "/29", "/30", "/31", "/32"};

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField networkField, processField, areaField;

    @FXML
    private ChoiceBox prefixBox;

    @FXML
    private TextArea configurationArea;

    @FXML
    private Button copyButton, addNetworkButton, backButton, clearButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prefixBox.setItems(FXCollections.observableArrayList(ipv4Prefixes));

        prefixBox.getSelectionModel().select(24);

        addNetworkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!networkField.getText().equals("") && !areaField.getText().equals("")) {
                    ospfv2Eigrp.getNetworks().add(new Network(new Address(networkField.getText(), new Prefix(prefixBox.getValue().toString())), areaField.getText()));
                    networkField.setText("");
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/ospfv2Protocol.fxml"));
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

        ChangeListener<String> changePortNumberListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                //showConfiguration();
            }
        };

        processField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    ospfv2Eigrp.setOspfProcess(newValue);
                } else {
                    ospfv2Eigrp.setOspfProcess("1");
                }
                showConfiguration();
            }
        });


        showConfiguration();
    }

    private void showConfiguration() {
        configurationArea.setText(ospfv2Eigrp.getConfiguration());
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/ios-xml/ios/iproute_ospf/configuration/xe-16/iro-xe-16-book/iro-cfg.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}