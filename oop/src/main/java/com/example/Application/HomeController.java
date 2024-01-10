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
//public class HomeController implements Initializable {
//
//    @FXML
//    private Button Exit;
//
//    @FXML
//    private Button Exit1;
//
//    @FXML
//    private Label Menu;
//
//    @FXML
//    private Label MenuBack;
//
//    @FXML
//    private Pane slider;
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        Exit.setOnMouseClicked(event -> {
//            System.exit(0);
//        });
//
//        Exit1.setOnMouseClicked(event -> {
//            System.exit(0);
//        });
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
//
//
//        private Stage stage;
//        private Scene scene;
//
//        public void switchToHome(ActionEvent event) throws IOException {
//            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        public void switchToBlog(ActionEvent event) throws IOException {
//            Parent root = FXMLLoader.load(getClass().getResource("blog.fxml"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
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
//
//
//}
//
package com.example.Application;

import com.example.backend.JsonRead.FileWriting;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Stack;

public class HomeController implements Initializable {

    @FXML
    private Button Exit;

    @FXML
    private Button Exit1;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private Pane slider;

    @FXML
    private Button backButton;
    private Stack<Scene> sceneStack = new Stack<>();
    FileWriting write = new FileWriting();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
                Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        Exit1.setOnMouseClicked(event -> {
            System.exit(0);
        });
        write.WriteData();

        // Initialize the stack with the first scene
        sceneStack.push(Menu.getScene());
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


    // ... existing methods

    public void switchToHome(ActionEvent event) throws IOException {
        switchScene("hello-view.fxml");
    }

    public void switchToBlog(ActionEvent event) throws IOException {
        switchScene("blog.fxml");
    }

    public void switchToTwitter(ActionEvent event) throws IOException {
        switchScene("Twitter.fxml");
    }

    public void switchToNFT(ActionEvent event) throws IOException {
        switchScene("NFT-page.fxml");
    }

    private void switchScene(String fxmlFileName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);

        Stage stage = (Stage) Menu.getScene().getWindow();
        sceneStack.push(newScene);

        // Set up the back navigation
        newScene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ESCAPE) {
                navigateBack();
            }
        });

        stage.setScene(newScene);
        stage.show();


    }

    private void navigateBack() {
        if (sceneStack.size() > 1) {
            sceneStack.pop(); // Remove the current scene
            Scene previousScene = sceneStack.peek();
            Stage stage = (Stage) previousScene.getWindow();
            stage.setScene(previousScene);
        }
    }

}
