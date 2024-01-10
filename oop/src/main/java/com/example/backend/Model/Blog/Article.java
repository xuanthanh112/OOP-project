package com.example.backend.Model.Blog;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("tag")
    private String tag;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private String title;
    @SerializedName("date")
    private String date;

    public Article(String link, String title, String tag, String date) {
        this.link = link;
        this.title = title;
        this.tag = tag;
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return
                "tag='" + tag + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
