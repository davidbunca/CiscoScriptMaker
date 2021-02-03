package com.bunca.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.bunca.objects.VLAN;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VlanConfigurationController implements Initializable {

    VLAN vlan = new VLAN();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField vlanNameField;

    @FXML
    private TextArea configurationArea;

    @FXML
    private Button copyButton;

    @FXML
    private Button backButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField vlanField;

    @FXML
    private CheckBox enableBox;
    @FXML
    private TextField vlanAddressField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vlanField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    vlan.setId(newValue);
                } else {
                    vlan.setId("1");
                }
                setVlanConf(vlan);
            }
        });
        vlanNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                vlan.setName(newValue);
                setVlanConf(vlan);
            }
        });
        vlanAddressField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                vlan.setAddress(newValue);
                setVlanConf(vlan);
            }
        });
        enableBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vlan.setEnabled(enableBox.isSelected());
                setVlanConf(vlan);
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/vlanConfig.fxml"));
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
        setVlanConf(vlan);

    }

    public void setVlanConf(VLAN vlan) {
        configurationArea.setText(vlan.getConfiguration());
    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/switches/lan/catalyst4500/12-2/25ew/configuration/guide/conf/vlans.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
