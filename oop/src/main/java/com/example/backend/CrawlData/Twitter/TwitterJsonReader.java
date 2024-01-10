package com.example.backend.CrawlData.Twitter;

import com.example.backend.Model.Twitter.Tweet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TwitterJsonReader {
    public static List<Tweet> readTweetJson() {
        List<Tweet> tweetList = new ArrayList<>();
        try {
            // Đường dẫn đến file JSON
            String filePath = "Data//Twitter.json";

            // Sử dụng Gson để đọc file JSON
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(new FileReader(filePath), JsonArray.class);

            // Tạo danh sách các đối tượng Tweet từ mảng JSON

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                Tweet tweet = gson.fromJson(jsonObject, Tweet.class);
                tweetList.add(tweet);
            }

            // In thông tin của các đối tượng Tweet
//            for (Tweet tweet : tweetList) {
//                System.out.println("Date: " + tweet.getDate());
//                System.out.println("User: " + tweet.getUser());
//                System.out.println("Content: " + tweet.getContent());
//                System.out.println("Hashtags: " + tweet.getHashtags());
//                System.out.println("----------------------");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweetList;
    }
}
