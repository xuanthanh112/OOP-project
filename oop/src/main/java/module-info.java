module java {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    requires com.fasterxml.jackson.databind;

    requires org.json;
    requires org.jsoup;
    requires com.google.gson;

    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.firefox_driver;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires dev.failsafe.core;
    requires org.seleniumhq.selenium.support;


    opens com.example.backend.CrawlData.Twitter to  net.synedra.validatorfx,
            org.kordamp.ikonli.javafx, org.kordamp.bootstrapfx.core,
            eu.hansolo.tilesfx, com.almasb.fxgl.all, dev.failsafe.core;
    opens com.example.backend.Model.NftFloor to com.google.gson, javafx.base;
    opens com.example.backend.Model.Blog to com.google.gson, javafx.base;
    opens com.example.backend.CrawlData.NftFloor.OpenSea to com.google.gson;
    opens com.example.backend.CrawlData.NftFloor.Binance to com.google.gson;
    opens com.example.backend.CrawlData.NftFloor.NiftyGateway to com.google.gson;
    opens com.example.backend.CrawlData.NftFloor.Rarible to com.google.gson;
    opens com.example.backend.Model.Twitter to com.google.gson, javafx.base;

    opens com.example.Application to javafx.fxml;
    exports com.example.Application;
    opens com.example.backend.exceptionhand to com.google.gson, javafx.base;

}
