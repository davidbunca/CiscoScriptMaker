package com.bunca.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RouterController implements Initializable {

    @FXML
    AnchorPane rootPane;

    @FXML
    Button interfaceButton, basicConfigButton, ripButton, eigrpButton, ospfv2Button, backButton, staticRoutingButton, standAclButton, extAclButton, dhcpButton, staticNatButton, dynamicNatButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        interfaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/interfaceRouter.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });
        basicConfigButton.setOnAction(new EventHandler<ActionEvent>() {
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
        staticNatButton.setOnAction(new EventHandler<ActionEvent>() {
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
        dynamicNatButton.setOnAction(new EventHandler<ActionEvent>() {
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

        dhcpButton.setOnAction(new EventHandler<ActionEvent>() {
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

        ripButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/ripProtocol.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });
        standAclButton.setOnAction(new EventHandler<ActionEvent>() {
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
        extAclButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/extAccessList.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });
        eigrpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/eigrpProtocol.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });
        ospfv2Button.setOnAction(new EventHandler<ActionEvent>() {
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
                    pane = FXMLLoader.load(getClass().getResource("/fxmls/menu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rootPane.getChildren().setAll(pane);
            }
        });

        staticRoutingButton.setOnAction(new EventHandler<ActionEvent>() {
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

    }
}
