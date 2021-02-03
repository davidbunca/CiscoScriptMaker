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
import com.bunca.objects.AclRule;
import com.bunca.objects.StandradACL;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccessListController implements Initializable {


    StandradACL standradACL = new StandradACL("1");
    String[] actions = {"deny", "permit"};

    @FXML
    AnchorPane rootPane;
    @FXML
    TextArea configurationArea;
    @FXML
    Button backButton, copyButton, clearButton, addRuleButton;
    @FXML
    TextField aclNumberField, sourceIpField, wildcardField;
    @FXML
    ChoiceBox actionBox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        actionBox.setItems(FXCollections.observableArrayList(actions));
        actionBox.getSelectionModel().select(0);

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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/accessList.fxml"));
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
                standradACL.getAclRules().add(new AclRule(actionBox.getValue().toString(), sourceIpField.getText(), wildcardField.getText()));
                showConfiguration();
                sourceIpField.setText("");
                wildcardField.setText("");
            }
        });

        aclNumberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    standradACL.setNumberOrName(newValue);
                } else {
                    standradACL.setNumberOrName("1");
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
        showConfiguration();
    }

    private void showConfiguration() {
        configurationArea.setText(standradACL.getConfiguration());
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
