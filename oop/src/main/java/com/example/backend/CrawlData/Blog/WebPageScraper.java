package com.example.backend.CrawlData.Blog;

import com.example.backend.Model.Blog.Article;
import com.example.backend.exceptionhand.DateConverter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class WebPageScraper {

    public static List<Article> scrapeArticles(Document doc) {
        List<Article> articlesList = new ArrayList<>();

        Elements lstArticles = doc.select("div.blog-info");
        for (Element element : lstArticles) {
            String tag = element.select(".saspot-label").first().text();
            Element titleElem = element.select(".blog-title > a").first();
            Element time = element.select(".blog-date > ul > li").first();
            String link = titleElem.attr("href");
            String title = titleElem.text();
            String inputDate = time.text();
            String formattedDate = DateConverter.convertDate(inputDate, "MMMM d, yyyy", "dd/MM/yyyy");
            Article article = new Article(link, title, tag, formattedDate);
            articlesList.add(article);
        }

        return articlesList;
    }
}