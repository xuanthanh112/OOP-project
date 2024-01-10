//package com.example.forntend;
//
//import javafx.animation.TranslateTransition;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class NftController implements Initializable {
//    @FXML
//    private Button Exit;
//
////    @FXML
////    private Button Exit1;
//
//    @FXML
//    private Label Menu;
//
//    @FXML
//    private Label MenuBack;
//
//    @FXML
//    private Pane slider;
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        Exit.setOnMouseClicked(event -> {
//            System.exit(0);
//        });
//
////        Exit1.setOnMouseClicked(event -> {
////            System.exit(0);
////        });
//
//        slider.setTranslateX(-176);
//        Menu.setOnMouseClicked(event -> {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(slider);
//
//            slide.setToX(0);
//            slide.play();
//
//            slider.setTranslateX(-176);
//
//            slide.setOnFinished((ActionEvent e)-> {
//                Menu.setVisible(false);
//                MenuBack.setVisible(true);
//            });
//        });
//
//        MenuBack.setOnMouseClicked(event -> {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(slider);
//
//            slide.setToX(-176);
//            slide.play();
//
//            slider.setTranslateX(0);
//
//            slide.setOnFinished((ActionEvent e)-> {
//                Menu.setVisible(true);
//                MenuBack.setVisible(false);
//            });
//        });
//    }
//    private Stage stage;
//    private Scene scene;
//
//    public void switchToHome(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    public void switchToBlog(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("blog.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void switchToTwitter(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("Twitter.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void switchToNFT(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("NFT-page.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//}
package com.example.Application;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class NftController implements Initializable {


    @FXML
    private Button Exit;


    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private Button backButton;

    @FXML
    private Pane slider;

    private Stack<Scene> sceneStack = new Stack<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });


        // Initialize the stack with the first scene
//        sceneStack.push(Menu.getScene());
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });
        backButton.setOnMouseClicked(event -> {
            navigateBack();
        });

    }


//Chuyển giữa các trang

    public void switchToHome(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        switchScene("home.fxml", sourceNode.getScene());
    }

    public void switchToBlog(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        switchScene("blog.fxml", sourceNode.getScene());
    }

    public void switchToTwitter(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        switchScene("Twitter.fxml", sourceNode.getScene());
    }

    public void switchToNFT(ActionEvent event) throws IOException {
        Node sourceNode = (Node) event.getSource();
        switchScene("NFT-page.fxml", sourceNode.getScene());
    }
//Quay lại các trang trước đó
private void switchScene(String fxmlFileName, Scene currentScene) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) currentScene.getWindow();
        sceneStack.add(currentScene); // Đẩy scene hiện tại vào stack
        stage.setScene(newScene);
        stage.show();
}

    private void navigateBack() {
        System.out.println("Scene Stack Size: " + sceneStack.size());
        if (sceneStack.size() > 1) {
            sceneStack.pop();
            Scene previousScene = sceneStack.peek();
            Stage stage = (Stage) previousScene.getWindow();
            stage.setScene(previousScene);
            stage.show();
        }
    }
}
