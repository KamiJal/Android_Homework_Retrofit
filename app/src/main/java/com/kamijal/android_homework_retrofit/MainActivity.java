package com.kamijal.android_homework_retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kamijal.android_homework_retrofit.adapters.NewsItemViewAdapter;
import com.kamijal.android_homework_retrofit.newsapi.models.Article;
import com.kamijal.android_homework_retrofit.newsapi.NewsApiManager;
import com.kamijal.android_homework_retrofit.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, com.kamijal.android_homework_retrofit.base.Observer {

    private RecyclerView recyclerView;
    private EditText searchInput;
    private NewsItemViewAdapter adapter;
    private NewsApiManager newsApiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.search_button).setOnClickListener(this);

        searchInput = findViewById(R.id.search_input);
        searchInput.setOnClickListener(this);
        searchInput.setOnEditorActionListener(this);

        adapter = new NewsItemViewAdapter();

        recyclerView = findViewById(R.id.news_item_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsApiManager = new NewsApiManager("https://newsapi.org/v2/");
        newsApiManager.subscribe(this);
    }

    private void search() {
        String query = ((EditText) findViewById(R.id.search_input)).getText().toString();

        if (query.isEmpty())
            return;

        newsApiManager.getNewsByQuery(query);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();

        if (view == null)
            view = new View(this);

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                search();
                break;
            case R.id.search_input:
                searchInput.setCursorVisible(true);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            search();
            return true;
        }

        return false;
    }

    @Override
    public void update() {
        MainActivityViewModel viewModel = MainActivityViewModel.Create(this);
        viewModel.setRepository(newsApiManager);
        viewModel.getArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter.setData(articles);
                recyclerView.setAdapter(adapter);
            }
        });

        hideKeyboard();
        searchInput.setCursorVisible(false);
    }
}
