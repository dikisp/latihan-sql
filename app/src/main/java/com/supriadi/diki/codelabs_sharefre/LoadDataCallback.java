package com.supriadi.diki.codelabs_sharefre;

public interface LoadDataCallback {
    void onPreLoad();
    void onProgressUpdate(long progress);
    void onLoadSuccess();
    void onLoadFailed();
}
