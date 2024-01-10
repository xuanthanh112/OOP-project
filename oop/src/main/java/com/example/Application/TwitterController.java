package com.example.Application;
import com.example.backend.JsonRead.NFTJsonReader;
import com.example.backend.Model.Twitter.Tweet;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class TwitterController extends SwitchLayout implements Initializable  {
    @FXML
    private Button Exit;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private Pane slider;
    @FXML
    private TableView<Tweet> view;
    @FXML
    private TableColumn<Tweet, String> user;
    @FXML
    private TableColumn<Tweet, String> content;
    @FXML
    private TableColumn<Tweet, List<String>> hagtags;
    @FXML
    private TableColumn<Tweet, String> date;
    @FXML
    private TextField keyword;
    @FXML
    private Button KeywordButton;
    @FXML
    private Button hashtagButton;
    @FXML
    private TextField hashtagField;

    private Stack<Scene> sceneStack = new Stack<>();
    NFTJsonReader op = new NFTJsonReader();
    List<Tweet> tweetList = op.readTweetJson("data//Twitter.json");
    ObservableList<Tweet> list = FXCollections.observableList(tweetList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
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

        KeywordButton.setOnMouseClicked(event ->TextFieldKeywordSearch());
        hashtagButton.setOnMouseClicked(event ->TextFieldHashtagSearch());

        initializeTableView(list);
    }
    // SetTable
    public void initializeTableView(ObservableList<Tweet> list) {
        user.setCellValueFactory(new PropertyValueFactory<Tweet, String>("user"));
        content.setCellValueFactory(new PropertyValueFactory<Tweet, String>("content"));
        date.setCellValueFactory(new PropertyValueFactory<Tweet, String>("date"));
        hagtags.setCellValueFactory(new PropertyValueFactory<Tweet, List<String>>("hashtags"));
        view.setItems(list);
    }

    public void TextFieldKeywordSearch(){
        String searchTerm = keyword.getText();
        SearchByKeyword(searchTerm, list);
    }
    public void TextFieldHashtagSearch(){
        String searchTerm = hashtagField.getText();
        SearchByHashtag(searchTerm, list);
    }

    public void SearchByKeyword(String s, List<Tweet> tweet){
        ObservableList<Tweet>  tweetsList= FXCollections.observableList(tweet);
        ObservableList<Tweet> searchByKeywordList = FXCollections.observableArrayList();
        for (Tweet tweets: tweet){
            if(tweets.getContent().toLowerCase().contains(s.toLowerCase())){
                searchByKeywordList.add(tweets);
            }
        }
        initializeTableView(searchByKeywordList);
    }
    public void SearchByHashtag(String s, List<Tweet> tweet){
        ObservableList<Tweet>  tweetsList= FXCollections.observableList(tweet);
        ObservableList<Tweet> searchByHashtagList = FXCollections.observableArrayList();
        for (Tweet tweets: tweetsList){
            List<String> hashtag = tweets.getHashtags();
            String result = String.join(", ", hashtag);
            if(result.toLowerCase().contains(s.toLowerCase())){
                searchByHashtagList.add(tweets);
            }
        }
        initializeTableView(searchByHashtagList);
    }



}

