package com.kamijal.android_homework_retrofit.tasks;

import android.graphics.Bitmap;

import java.util.concurrent.ExecutionException;

public class AsyncTaskManager {

    public static Bitmap getBitmapByPath(String path){

        DownloadImageAsyncTask task = new DownloadImageAsyncTask();
        task.execute(path);

        Bitmap result = null;

        try {
            result = task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}
