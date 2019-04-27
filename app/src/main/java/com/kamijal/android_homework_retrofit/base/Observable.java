package com.kamijal.android_homework_retrofit.base;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T> {
    protected final List<T> observers = new ArrayList<>();

    public void subscribe(T observer){
        observers.add(observer);
    }

    public void unsubscribe(T observer){
        observers.remove(observer);
    }

    public abstract void notifyDataChanged();
}
