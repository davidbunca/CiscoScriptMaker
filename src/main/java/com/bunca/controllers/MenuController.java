package com.bunca.controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    AnchorPane rootPane;
    @FXML
    Pane creditsPane;
    @FXML
    Button webButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URL("https://bunca2.wixsite.com/website").toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void openSwitch(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmls/switch.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void openRouter(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmls/router.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    private boolean creditsOpen = false;

    public void openCredits(ActionEvent event) throws IOException {
        if (creditsOpen) {
            creditsPane.setVisible(false);
            creditsOpen = false;
        } else {
            creditsPane.setVisible(true);
            creditsOpen = true;
        }
    }


}
