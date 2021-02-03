package com.bunca.controllers;

import com.bunca.objects.Interface;
import com.bunca.objects.StaticNAT;
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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaticNatController implements Initializable {

    StaticNAT staticNAT = new StaticNAT();
    String[] interfaces = {"FastEthernet", "GigabitEthernet", "TengigabitEthernet", "Serial"};
    String[] interfaceNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    String[] natTypes = {"inside", "outside"};
    @FXML
    AnchorPane rootPane;
    @FXML
    ChoiceBox interfaceBox, portNumberBox, portNumberBox2, portNumberBox3, natTypeBox;
    @FXML
    Label serialSlash;
    @FXML
    TextField localField, globalField;
    @FXML
    TextArea configurationArea;
    @FXML
    Button copyButton, backButton, clearButton, addIntButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        interfaceBox.setItems(FXCollections.observableArrayList(interfaces));
        portNumberBox.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox2.setItems(FXCollections.observableArrayList(interfaceNumbers));
        portNumberBox3.setItems(FXCollections.observableArrayList(interfaceNumbers));
        natTypeBox.setItems(FXCollections.observableArrayList(natTypes));

        interfaceBox.getSelectionModel().select(0);
        portNumberBox.getSelectionModel().select(0);
        portNumberBox2.getSelectionModel().select(0);
        portNumberBox3.getSelectionModel().select(0);
        natTypeBox.getSelectionModel().select(0);


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
        localField.textProperty().addListener(changeListener);
        globalField.textProperty().addListener(changeListener);

        addIntButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String interfaceString = interfaceBox.getValue().toString() + " ";
                if (!interfaceString.equals("Serial ")) {
                    interfaceString += portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString();
                } else {
                    interfaceString += portNumberBox.getValue().toString() + "/" + portNumberBox2.getValue().toString() + "/" + portNumberBox3.getValue().toString();
                }
                staticNAT.getInterfaces().add(new Interface(interfaceString, natTypeBox.getValue().toString()));
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/staticNat.fxml"));
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
        staticNAT.setLocalIP(localField.getText());
        staticNAT.setGlobalIP(globalField.getText());
        configurationArea.setText(staticNAT.getConfiguration());
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/ios-xml/ios/ipaddr_nat/configuration/15-mt/nat-15-mt-book/iadnat-addr-consv.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
