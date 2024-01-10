package com.example.Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class SwitchLayout {
    private Stage stage;
    private Scene scene;
    private String onedayOriginal;
    private String sevendayOriginal;
    @FXML
    private Button oneday;

    @FXML
    private Button sevenday;

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("blog.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTwitter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Twitter.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNFT(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NFT-page.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOpenSea(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NFT-OpenSea.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNifty(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NFT-Nifty.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBinace(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NFT-Binance.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRarible(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NFT-Rarible.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void getStyle() {
//        onedayOriginal = oneday.getStyle();
//        sevendayOriginal = sevenday.getStyle();
//    }
}


