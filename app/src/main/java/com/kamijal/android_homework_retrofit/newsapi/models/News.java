package com.kamijal.android_homework_retrofit.newsapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("articles")
    @Expose
    public List<Article> articles;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("totalResults")
    @Expose
    public int totalResults;
}
