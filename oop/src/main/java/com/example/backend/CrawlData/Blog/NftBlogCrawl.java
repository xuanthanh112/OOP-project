package com.example.backend.CrawlData.Blog;

import com.example.backend.Model.Blog.Article;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NftBlogCrawl {
    public  void call() {
        int numpage = 5;
        List<Article> blogList = new ArrayList<>();
        for (int page = 1; page <= numpage; page++) {
            String url = "https://www.nftically.com/blog/page/" + page + "/";
            Document doc;

            try {
                doc = Jsoup
                        .connect(url)
                        .userAgent("Jsoup client")
                        .timeout(10000)
                        .get();

                List<Article> articlesList = WebPageScraper.scrapeArticles(doc);
                for (Article blog: articlesList){
                    blogList.add(blog);
                }


                // In ra danh sách bài viết
//                for (Article article : articlesList) {
//                    System.out.println("Title: " + article.getTitle());
//                    System.out.println("Link: " + article.getLink());
//                    System.out.println("Tag : " + article.getTag());
//                    System.out.println("Datetime : " + article.getDate());
//                    System.out.println("---------------------");
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String fileName = "Data//Blog.json";
        try {
            FileWriter writer = new FileWriter(fileName);

            // Ghi chuỗi JSON vào file
            Gson gson = new Gson();
            String jsonString = gson.toJson(blogList);
            writer.write(jsonString);

            // Đóng file
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


