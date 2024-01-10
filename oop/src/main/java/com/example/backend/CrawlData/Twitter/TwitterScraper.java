package com.example.backend.CrawlData.Twitter;


import com.example.backend.exceptionhand.Scraper;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TwitterScraper implements Scraper {
    public Map<String, JSONObject> scrape() {
        Map<String, JSONObject> sex = new LinkedHashMap<>();
        int id = 1;
        try {
            FirefoxOptions options = new FirefoxOptions();
            System.setProperty("webdriver.gecko.driver"
                    ,"browserDrivers/geckodriver.exe");

            WebDriver driver = new FirefoxDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://twitter.com/i/flow/login");

            WebElement usernameInput = driver.findElement(  // Tìm đến phần tử có thẻ input và có 2 thuộc tính autocomplete và name
                    By.cssSelector("input[autocomplete='username'][name='text']"));
            usernameInput.sendKeys("tranhuonggiang6122003@gmail.com"); //Set cho phần tử đó có giá trị là tài khoản đăng nhập
            usernameInput.sendKeys(Keys.ENTER);//Ấn Enter để chuyển trang tùy vào trang có thể là ấn Enter có thể là button ấn nhưng 90% là enter

            WebElement verify = driver.findElement(By.xpath("//input[@data-testid='ocfEnterTextTextInput']"));
            verify.sendKeys("RangRang0612");
            verify.sendKeys(Keys.ENTER);

            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("giang6122003");
            driver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']")).click();

            driver.findElement(By.xpath("//a[@data-testid='AppTabBar_Explore_Link']")).click();
            WebElement search = driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']"));
            search.sendKeys("NFTs");
            search.sendKeys(Keys.ENTER);


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@data-testid='cellInnerDiv']//article[@data-testid='tweet']")));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            long pageHeight = (long) jsExecutor.executeScript("return Math.max( document.body.scrollHeight"
                    + ", document.body.offsetHeight, document.documentElement.clientHeight,"
                    + " document.documentElement.scrollHeight,"
                    + " document.documentElement.offsetHeight )");
            int steps = 1;
            long delayBetweenStepsInMillis = 5000;
            long scrollStep = pageHeight / steps;
            for (int i = 0; i < 5; i++) {
                String html = (String) jsExecutor.executeScript("return document.documentElement.outerHTML");
                Document document = Jsoup.parse(html);
                Elements elements = document.select("div[class='css-175oi2r r-1iusvr4 r-16y2uox r-1777fci r-kzbkwu']");
                for (Element element : elements) {
                    sex.put(String.valueOf(id++), TweetHandler.handle(element));
                    JSONObject tweetData = TweetHandler.handle(element);
                    sex.put(String.valueOf(id++), tweetData);

                    long yOffset = i * scrollStep;
                    jsExecutor.executeScript("window.scrollTo(0, " + yOffset + ")");
                    Thread.sleep(delayBetweenStepsInMillis);
                }
            }
            TweetHandler.writeToFile(sex.toString());
            driver.quit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return sex;
    }
    public void call() {
        TwitterScraper ts = new TwitterScraper();
        ts.scrape();
    }
}
