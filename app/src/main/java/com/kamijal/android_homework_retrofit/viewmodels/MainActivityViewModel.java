package com.kamijal.android_homework_retrofit.viewmodels;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.kamijal.android_homework_retrofit.newsapi.models.Article;
import com.kamijal.android_homework_retrofit.repositories.RepositoryInterface;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private RepositoryInterface repository;
    private final MutableLiveData<List<Article>> mArticles = new MutableLiveData<>();

    public void setRepository (RepositoryInterface repository) {
        this.repository = repository;
    }

    public LiveData<List<Article>> getArticles() {
        mArticles.setValue(repository.getArticlesList());
        return this.mArticles;
    }

    public static MainActivityViewModel Create(final FragmentActivity activity){
       return ViewModelProviders.of(activity).get(MainActivityViewModel.class);
    }
}
