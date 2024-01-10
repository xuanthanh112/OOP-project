package com.example.backend.JsonRead;


import com.example.backend.Model.Blog.Article;
import com.example.backend.Model.NftFloor.NFT;
import com.example.backend.Model.Twitter.Tweet;
import com.google.gson.Gson;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class NFTJsonReader {
    public  List<NFT> readNFTJson(String filePath) {
        List<NFT> NftList = new ArrayList<>();

        try {
            // Sử dụng Gson để đọc file JSON
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(new FileReader(filePath), JsonArray.class);

            // Duyệt qua mảng JSON và tạo đối tượng từ mỗi phần tử
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                NFT Nft = gson.fromJson(jsonObject, NFT.class);

                 NftList.add(Nft);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Trả về danh sách NFT
        return NftList;
    }
    public  List<Tweet> readTweetJson(String filePath) {
        List<Tweet> NftList = new ArrayList<>();

        try {
            // Sử dụng Gson để đọc file JSON
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(new FileReader(filePath), JsonArray.class);

            // Duyệt qua mảng JSON và tạo đối tượng từ mỗi phần tử
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                Tweet Tweer = gson.fromJson(jsonObject, Tweet.class);

                NftList.add(Tweer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Trả về danh sách Tweet
        return NftList;
    }
    public  List<Article> readBlogJson(String filePath) {
        List<Article> NftList = new ArrayList<>();

        try {
            // Sử dụng Gson để đọc file JSON
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(new FileReader(filePath), JsonArray.class);

            // Duyệt qua mảng JSON và tạo đối tượng từ mỗi phần tử
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                Article Tweer = gson.fromJson(jsonObject, Article.class);

                NftList.add(Tweer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Trả về danh sách Article
        return NftList;
    }
}

