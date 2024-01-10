package com.example.Application;

import com.example.backend.JsonRead.NFTJsonReader;
import com.example.backend.Model.Blog.Article;
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

public class BlogController extends SwitchLayout implements Initializable  {
    @FXML
    private Button Exit;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private Pane slider;
    @FXML
    private TableView<Article> view;
    @FXML
    private TableColumn<Article, String> date;

    @FXML
    private TableColumn<Article, String> link;

    @FXML
    private TextField keywordTextFeild;

    @FXML
    private TableColumn<Article, String> title;

    @FXML
    private TableColumn<Article, String> tag;

    @FXML
    private Button tagButton;

    @FXML
    private Button titleButton;

    @FXML
    private Button backButton;



    private Stack<Scene> sceneStack = new Stack<>();
    NFTJsonReader op = new NFTJsonReader();
    List<Article> BlogList = op.readBlogJson("data//Blog.json");
    ObservableList<Article> list = FXCollections.observableList(BlogList);

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
        tagButton.setOnMouseClicked(event -> TextFieldTagSearch());
        titleButton.setOnMouseClicked(event -> TextFieldKeywordSearch());

        initializeTableView(list);
    }
    // SetTable
    public void initializeTableView(ObservableList<Article> list) {
        title.setCellValueFactory(new PropertyValueFactory<Article, String>("title"));
        link.setCellValueFactory(new PropertyValueFactory<Article, String>("link"));
        date.setCellValueFactory(new PropertyValueFactory<Article, String>("date"));
        tag.setCellValueFactory(new PropertyValueFactory<Article, String>("tag"));
        view.setItems(list);
    }

    public void TextFieldKeywordSearch(){
        String searchTerm = keywordTextFeild.getText();
        SearchByKeyword(searchTerm, list);
    }
    public void TextFieldTagSearch(){
        String searchTerm = keywordTextFeild.getText();
        SearchByTag(searchTerm, list);
    }

    public void SearchByKeyword(String s, List<Article> blog){
        ObservableList<Article>  blogList= FXCollections.observableList(blog);
        ObservableList<Article> searchByKeywordList = FXCollections.observableArrayList();
        for (Article blogs: blog){
            if(blogs.getTitle().toLowerCase().contains(s.toLowerCase())){
                searchByKeywordList.add(blogs);
            }
        }
        initializeTableView(searchByKeywordList);
    }
    public void SearchByTag(String s, List<Article> blog){
        ObservableList<Article>  blogList= FXCollections.observableList(blog);
        ObservableList<Article> searchByTagList = FXCollections.observableArrayList();
        for (Article blogs: blogList){
            String tag = blogs.getTag();
            String result = String.join(", ", tag);
            if(result.toLowerCase().contains(s.toLowerCase())){
                searchByTagList.add(blogs);
            }
        }
        initializeTableView(searchByTagList);
    }

}

