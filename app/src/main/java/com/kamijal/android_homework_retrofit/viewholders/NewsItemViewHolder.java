package com.kamijal.android_homework_retrofit.viewholders;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kamijal.android_homework_retrofit.R;
import com.kamijal.android_homework_retrofit.tasks.AsyncTaskManager;

public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView author;
    private final TextView url;
    private final TextView content;

    private final ImageView image;

    public NewsItemViewHolder(@NonNull View itemView) {
        super(itemView);

        this.title = itemView.findViewById(R.id.item_news_title);
        this.author = itemView.findViewById(R.id.item_news_author);
        this.url = itemView.findViewById(R.id.item_news_url);
        this.content = itemView.findViewById(R.id.item_news_content);
        this.image = itemView.findViewById(R.id.item_news_image);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setAuthor(String author) {
        if (author == null)
            author = "n/d";

        this.author.setText(String.format("Author: %s", author));
    }

    public void setUrl(String url) {
        if (url == null) {
            this.url.getLayoutParams().height = 0;
            return;
        }

        this.url.setText(url);
    }

    public void setContent(String content) {
        if (content == null) {
            this.content.getLayoutParams().height = 0;
            return;
        }

        content = content.substring(0, content.lastIndexOf('['));
        this.content.setText(content);
    }


    public void setImage(String imagePath) {

        if (imagePath != null) {
            Bitmap downloaded = AsyncTaskManager.getBitmapByPath(imagePath);

            if (downloaded != null) {
                this.image.setImageBitmap(downloaded);
                return;
            }
        }

        this.image.getLayoutParams().height = 0;
    }
}
