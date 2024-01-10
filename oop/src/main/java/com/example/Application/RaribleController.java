package com.example.Application;

import com.example.backend.JsonRead.NFTJsonReader;
import com.example.backend.Model.NftFloor.NFT;
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

public class RaribleController extends SwitchLayout implements Initializable {
    @FXML
    private Button Exit;

//    @FXML
//    private Button Exit1;
    @FXML
    private Button oneday;
    @FXML
    private Button sevenday;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private Button backButton;

    @FXML
    private Button binance;
    @FXML
    private Button nifty;

    @FXML
    private Button rarible;

    @FXML
    private TextField searchText;

    @FXML
    private Pane slider;
    @FXML
    private TableView<NFT> view;
    @FXML
    private TableColumn<NFT, Double> floorPricecol;

    @FXML
    private TableColumn<NFT, String> idcol;

    @FXML
    private TableColumn<NFT, String> namecol;

    @FXML
    private TableColumn<NFT, Integer> numOwnerscol;
    @FXML
    private TableColumn<NFT, String> urlcol;
    @FXML
    private TableColumn<NFT, Integer> totalSupplycol;
    @FXML
    private TableColumn<NFT, Double> volumecol;
    @FXML
    private TableColumn<NFT, Double> volumeChangecol;

    @FXML
    private Button collectionButton;
    @FXML
    private Button priceButton;
    @FXML
    private TextField maxPrice;

    @FXML
    private TextField minPrice;
    private  String onedayOriginal;
    private String sevendayOriginal;

    private Stack<Scene> sceneStack = new Stack<>();
    NFTJsonReader op = new NFTJsonReader();
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

        sevenday.setOnMouseClicked(event ->{
            sevenday.setOnAction(e -> {
                // Đổi màu của button khi sự kiện xảy ra
                sevenday.setStyle("-fx-background-color: #EC326DE0;");
                oneday.setStyle("-fx-background-color: #cc4b4c;");
            });
            Rarible7D();
        });
        oneday.setOnMouseClicked(event ->{
            oneday.setOnAction(e -> {
                // Đổi màu của button khi sự kiện xảy ra
                oneday.setStyle("-fx-background-color: #EC326DE0;");
                sevenday.setStyle("-fx-background-color: #cc4b4c;");
            });
            Rarible1D();
        });
        collectionButton.setOnMouseClicked(event -> TextFieldKeywordSearch());
        priceButton.setOnMouseClicked(event -> FloorPriceSearch());
        Rarible1D();
        //SearchbyName("Az");

    }
    public void TextFieldKeywordSearch(){
        String searchTerm = searchText.getText();
        SearchByName(searchTerm);
    }
    public void FloorPriceSearch(){
        String min = minPrice.getText();
        String max = maxPrice.getText();
        SearchByPriceFloor(Double.parseDouble(min), Double.parseDouble(max));
    }
    // SetTable
    public void initializeTableView(ObservableList<NFT> list) {
        namecol.setCellValueFactory(new PropertyValueFactory<NFT, String>("name"));
        idcol.setCellValueFactory(new PropertyValueFactory<NFT, String>("id"));
        //urlcol.setCellValueFactory(new PropertyValueFactory<NFT, String>("url"));
        numOwnerscol.setCellValueFactory(new PropertyValueFactory<NFT, Integer>("numOwners"));
        totalSupplycol.setCellValueFactory(new PropertyValueFactory<NFT, Integer>("totalSupply"));
        volumecol.setCellValueFactory(new PropertyValueFactory<NFT, Double>("volume"));
        floorPricecol.setCellValueFactory(new PropertyValueFactory<NFT, Double>("floorPrice"));
        //numOfSalescol.setCellValueFactory(new PropertyValueFactory<NFT, Integer>("numOfSales"));
        volumeChangecol.setCellValueFactory(new PropertyValueFactory<NFT, Double>("volumeChange"));
        view.setItems(list);
    }
    public void Rarible7D(){
        List<NFT> nftList = op.readNFTJson("data//rarible7D.json");
        ObservableList<NFT> list = FXCollections.observableList(nftList);
        initializeTableView(list);
    }
    public void Rarible1D(){
        List<NFT> nftList = op.readNFTJson("data//rarible1D.json");
        ObservableList<NFT> list = FXCollections.observableList(nftList);
        initializeTableView(list);
    }
    public void SearchByName(String s){
        List<NFT> NFTlist = op.readNFTJson("data//rarible1D.json");
        ObservableList<NFT> list = FXCollections.observableList(NFTlist);
        ObservableList<NFT> sName = FXCollections.observableArrayList();
        for (NFT nft: list){
            if (nft.getName().toLowerCase().contains(s.toLowerCase())) {
                sName.add(nft);
                //System.out.println(nft);
            }
        }
        initializeTableView(sName);
    }
    public void SearchByPriceFloor(double min, double max){
        List<NFT> NFTlist = op.readNFTJson("data//rarible1D.json");

        ObservableList<NFT> list = FXCollections.observableList(NFTlist);
        ObservableList<NFT> sPrice = FXCollections.observableArrayList();
        for (NFT nft: list){
            if (nft.getFloorPrice() >= min) {
                if (nft.getFloorPrice() <= max){
                    sPrice.add(nft);
                }
            }
        }
        initializeTableView(sPrice);
    }



}
