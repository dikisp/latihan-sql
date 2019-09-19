package com.supriadi.diki.codelabs_sharefre;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.supriadi.diki.codelabs_sharefre.database.MahasiswaHelper;
import com.supriadi.diki.codelabs_sharefre.model.UserModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LoadDataAsync extends AsyncTask<Void, Integer, Boolean> {
    private final String TAG = LoadDataAsync.class.getSimpleName();
    private MahasiswaHelper mahasiswaHelper;
    private UserPreference appPreference;
    private WeakReference<LoadDataCallback> weakCallback;
    private WeakReference<Resources> weakResources;
    double progress;
    double maxprogress = 100;


    private ArrayList<UserModel> preLoadRaw() {
        ArrayList<UserModel> mahasiswaModels = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = weakResources.get();
            InputStream raw_dict = res.openRawResource(R.raw.data_mahasiswa);
//            InputStream raw_dict = res.openRawResource(R.raw.data_mahasiswa);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                UserModel mahasiswaModel;
                mahasiswaModel = new UserModel(splitstr[0], splitstr[1]);
                mahasiswaModels.add(mahasiswaModel);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mahasiswaModels;
    }

    LoadDataAsync(MahasiswaHelper mahasiswaHelper, UserPreference preference, LoadDataCallback callback, Resources resources) {
        this.mahasiswaHelper = mahasiswaHelper;
        this.appPreference = preference;
        this.weakCallback = new WeakReference<>(callback);
        this.weakResources = new WeakReference<>(resources);
    }

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute");
        weakCallback.get().onPreLoad();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        weakCallback.get().onProgressUpdate(values[0]);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Boolean firstRun = appPreference.getFirstRun();
        if (firstRun) {
            ArrayList<UserModel> mahasiswaModels = preLoadRaw();
            mahasiswaHelper.open();
            progress = 30;
            publishProgress((int) progress);
            Double progressMaxInsert = 80.0;
            Double progressDiff = (progressMaxInsert - progress) / mahasiswaModels.size();
            boolean isInsertSuccess;
            try {
                for (UserModel model : mahasiswaModels) {
                    mahasiswaHelper.insert(model);
                    progress += progressDiff;
                    publishProgress((int) progress);
                }
                isInsertSuccess = true;
                appPreference.setFirstRun(false);
            } catch (Exception e) {
                Log.e(TAG, "doInBackground: Exception");
                isInsertSuccess = false;
            }
            mahasiswaHelper.close();
            publishProgress((int) maxprogress);
            return isInsertSuccess;
        } else {
            try {
                synchronized (this) {
                    this.wait(2000);
                    publishProgress(50);
                    this.wait(2000);
                    publishProgress((int) maxprogress);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            weakCallback.get().onLoadSuccess();
        } else {
            weakCallback.get().onLoadFailed();
        }
    }
}
