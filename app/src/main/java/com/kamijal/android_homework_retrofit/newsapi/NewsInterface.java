package com.kamijal.android_homework_retrofit.newsapi;

import com.kamijal.android_homework_retrofit.newsapi.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface NewsInterface {
    @GET("everything?apiKey=84f701a5ef264ba29bbf288971ee0129")
    Call<News> getNews(@Query("q") String query);
}
