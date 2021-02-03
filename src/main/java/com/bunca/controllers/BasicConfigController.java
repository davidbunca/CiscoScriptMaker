package com.bunca.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasicConfigController implements Initializable {


    @FXML
    AnchorPane rootPane;
    @FXML
    PasswordField enablePassword, linkPassword, telnetPassword;
    @FXML
    TextField hostnameField, banerField;
    @FXML
    TextArea configuration;
    @FXML
    Button backButton, copyButton, clearButton;

    String enablePass = "ciscoe";
    String linkPass = "ciscoc";
    String telnetPass = "ciscot";
    String hostname = "Device";
    String baner = "Authorized users only.";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBasicConfig(enablePass, linkPass, telnetPass, hostname, baner);
        enablePassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    enablePass = newValue;
                } else {
                    enablePass = "ciscoe";
                }

                setBasicConfig(enablePass, linkPass, telnetPass, hostname, baner);
            }
        });

        linkPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    linkPass = newValue;
                } else {
                    linkPass = "ciscoc";
                }
                setBasicConfig(enablePass, linkPass, telnetPass, hostname, baner);
            }
        });

        telnetPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    telnetPass = newValue;
                } else {
                    telnetPass = "ciscoe";
                }
                setBasicConfig(enablePass, linkPass, telnetPass, hostname, baner);
            }
        });

        hostnameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    hostname = newValue;
                } else {
                    hostname = "Device";
                }
                setBasicConfig(enablePass, linkPass, telnetPass, hostname, baner);
            }
        });

        banerField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    baner = newValue;
                } else {
                    baner = "Authorized users only";
                }
                setBasicConfig(enablePass, linkPass, telnetPass, hostname, baner);
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


        copyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Toolkit.getDefaultToolkit()
                        .getSystemClipboard()
                        .setContents(
                                new StringSelection(configuration.getText()),
                                null
                        );

            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/basicConfig.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });


    }

    void setBasicConfig(String enablePass, String linkPass, String telnetPass, String hostname, String baner) {
        String conf = "enable\n" +
                "configure terminal\n" +
                "hostname " + hostname + "\n" +
                "enable password " + enablePass + "\n" +
                "enable secret " + enablePass + "\n" +
                "banner motd #" + baner + "#\n" +
                "service password-encryption\n" +
                "line con 0\n" +
                "password " + linkPass + "\n" +
                "login\n" +
                "logging synchronous\n" +
                "line vty 0 15\n" +
                "password " + telnetPass + "\n" +
                "login\n" +
                "logging synchronous\n" +
                "transport input all\n" +
                "exit\n";
        configuration.setText(conf);
    }


}
