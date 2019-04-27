package com.kamijal.android_homework_retrofit.newsapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article{

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("author")
    @Expose
    public String author;

    @SerializedName("content")
    @Expose
    public String content;

    @SerializedName("source")
    @Expose
    public Source source;

    @SerializedName("publishedAt")
    @Expose
    public String publishedAt;

    @SerializedName("urlToImage")
    @Expose
    public String urlToImage;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("description")
    @Expose
    public String description;
}
