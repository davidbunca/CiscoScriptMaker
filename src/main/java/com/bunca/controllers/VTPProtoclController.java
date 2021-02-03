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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.bunca.objects.VTP;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VTPProtoclController implements Initializable {

    VTP vtp = new VTP();
    String[] modes = {"server", "client", "transparent"};

    @FXML
    AnchorPane rootPane;
    @FXML
    TextArea configurationArea;
    @FXML
    Button backButton, copyButton, clearButton;
    @FXML
    TextField domainField;
    @FXML
    ChoiceBox modeBox;
    @FXML
    PasswordField vtpPassField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVTPConfig(vtp);
        modeBox.setItems(FXCollections.observableArrayList(modes));
        modeBox.getSelectionModel().select(0);
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/vtpProtocol.fxml"));
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

        domainField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    vtp.setDomain(newValue);
                } else {
                    vtp.setDomain("DOMAIN");
                }
                setVTPConfig(vtp);
            }
        });

        vtpPassField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.equals("")) {
                    vtp.setPassword(newValue);
                } else {
                    vtp.setPassword("ciscovtp");
                }
                setVTPConfig(vtp);
            }
        });

        modeBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                vtp.setMode(newValue);
                setVTPConfig(vtp);
            }
        });

    }

    private void setVTPConfig(VTP vtp) {
        configurationArea.setText(vtp.getConfiguration());

    }

    @FXML
    public void info() {
        try {
            Desktop.getDesktop().browse(new URL("https://www.cisco.com/c/en/us/td/docs/switches/lan/catalyst4500/12-2/31sg/configuration/guide/vtp.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
