package com.kamijal.android_homework_retrofit.repositories;

import com.kamijal.android_homework_retrofit.newsapi.models.Article;

import java.util.List;

public interface RepositoryInterface {
    List<Article> getArticlesList();
}
