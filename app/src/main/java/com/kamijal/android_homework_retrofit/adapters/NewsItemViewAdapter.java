package com.kamijal.android_homework_retrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kamijal.android_homework_retrofit.R;
import com.kamijal.android_homework_retrofit.newsapi.models.Article;
import com.kamijal.android_homework_retrofit.viewholders.NewsItemViewHolder;

import java.util.List;

public class NewsItemViewAdapter extends RecyclerView.Adapter<NewsItemViewHolder> {

    private List<Article> articles;

    public void setData(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        holder.setTitle(articles.get(position).title);
        holder.setAuthor(articles.get(position).author);
        holder.setUrl(articles.get(position).url);
        holder.setContent(articles.get(position).content);
        holder.setImage(articles.get(position).urlToImage);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
