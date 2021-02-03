package com.bunca.controllers;

import com.bunca.objects.DynamicNAT;
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

public class DynamicNatController implements Initializable {

    DynamicNAT dynamicNAT = new DynamicNAT();

    String[] ipv4Prefixes = {"/0", "/1", "/2", "/3", "/4", "/5", "/6", "/7", "/8", "/9", "/10", "/11", "/12", "/13", "/14", "/15", "/16", "/17", "/18", "/18", "/20", "/21", "/22", "/23", "/24", "/25", "/26", "/27", "/28", "/29", "/30", "/31", "/32"};

    @FXML
    AnchorPane rootPane;
    @FXML
    ChoiceBox prefixBox;
    @FXML
    TextField startField, endField, poolNameField, aclNameField;
    @FXML
    TextArea configurationArea;
    @FXML
    Button copyButton, backButton, clearButton;

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
        aclNameField.textProperty().addListener(changeListener);
        endField.textProperty().addListener(changeListener);
        startField.textProperty().addListener(changeListener);

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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/dynamicNat.fxml"));
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
        dynamicNAT.setAclName(aclNameField.getText());
        dynamicNAT.setEndIP(endField.getText());
        dynamicNAT.setStartIP(startField.getText());
        dynamicNAT.setPoolName(poolNameField.getText());
        dynamicNAT.setPrefix(prefixBox.getValue().toString());
        configurationArea.setText(dynamicNAT.getConfiguration());
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
