package com.kamijal.android_homework_retrofit.newsapi;

import androidx.annotation.NonNull;

import com.kamijal.android_homework_retrofit.base.Observable;
import com.kamijal.android_homework_retrofit.base.Observer;
import com.kamijal.android_homework_retrofit.newsapi.NewsInterface;
import com.kamijal.android_homework_retrofit.newsapi.models.Article;
import com.kamijal.android_homework_retrofit.newsapi.models.News;
import com.kamijal.android_homework_retrofit.repositories.RepositoryInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiManager extends Observable<Observer> implements RepositoryInterface, Callback<News> {

    private final NewsInterface proxy;
    private List<Article> articles = null;

    public NewsApiManager(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        proxy = retrofit.create(NewsInterface.class);
    }

    @Override
    public List<Article> getArticlesList() {
        return this.articles;
    }

    public void getNewsByQuery(String query){
        proxy.getNews(query).enqueue(this);
    }

    @Override
    public void notifyDataChanged() {
        for (Observer observer: this.observers) {
            observer.update();
        }
    }

    @Override
    public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
        News body = response.body();

        if (body == null || !body.status.equals("ok"))
            return;

        this.articles = body.articles;
        this.notifyDataChanged();
    }

    @Override
    public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {

    }
}
