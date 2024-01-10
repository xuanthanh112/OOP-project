package com.example.Application;//package com.example.forntend;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class NftFloorController extends SwitchLayout implements Initializable {

    @FXML
    private Button Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private Button backButton;

    @FXML
    private Button binance;

    @FXML
    private TableColumn<?, ?> floorPricecol;

    @FXML
    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private Button nifty;

    @FXML
    private TableColumn<?, ?> numOwnerscol;

    @FXML
    private Button oneday;

    @FXML
    private Button opensea;

    @FXML
    private Button rarible;

    @FXML
    private TextField searchText;

    @FXML
    private Pane slider;

    @FXML
    private TableColumn<?, ?> totalSupplycol;

    @FXML
    private TableColumn<?, ?> url;

    @FXML
    private TableView<?> view;

    @FXML
    private TableColumn<?, ?> volumeChangecol;

    @FXML
    private TableColumn<?, ?> volumecol;

//    private Stack<Scene> sceneStack = new Stack<>();
//    NFTJsonReader op = new NFTJsonReader();
    //List<NFT> nftList = new ArrayList<>();
    //ObservableList<NFT> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
//        binance.setOnMouseClicked(event -> {
//            BinanceSelect1D();
////            initializeTableView(list);
//            oneday.setOnMouseClicked(event ->{
//                BinanceSelect7D();
//            });
//        });




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

        //SearchbyName("Az");

    }

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


    // SetTable
//    public void initializeTableView(ObservableList<NFT> list) {
//        namecol.setCellValueFactory(new PropertyValueFactory<NFT, String>("name"));
//        idcol.setCellValueFactory(new PropertyValueFactory<NFT, String>("id"));
//        //urlcol.setCellValueFactory(new PropertyValueFactory<NFT, String>("url"));
//        numOwnerscol.setCellValueFactory(new PropertyValueFactory<NFT, Integer>("numOwners"));
//        totalSupplycol.setCellValueFactory(new PropertyValueFactory<NFT, Integer>("totalSupply"));
//        volumecol.setCellValueFactory(new PropertyValueFactory<NFT, Double>("volume"));
//        floorPricecol.setCellValueFactory(new PropertyValueFactory<NFT, Double>("floorPrice"));
//        //numOfSalescol.setCellValueFactory(new PropertyValueFactory<NFT, Integer>("numOfSales"));
//        volumeChangecol.setCellValueFactory(new PropertyValueFactory<NFT, Double>("volumeChange"));
//        view.setItems(list);
//    }
//    //Tìm kiếm theo tên NFT
//    public void SearchbyName(String s){
//        ObservableList<NFT> sName = FXCollections.observableArrayList();
//        for (NFT nft: list){
//            if (nft.getName().toLowerCase().contains(s.toLowerCase())) {
//                sName.add(nft);
//                System.out.println(nft);
//            }
//        }
//        initializeTableView(sName);
//    }




}

