package com.supriadi.diki.codelabs_sharefre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.supriadi.diki.codelabs_sharefre.adapter.MahasiswaAdapter;
import com.supriadi.diki.codelabs_sharefre.database.MahasiswaHelper;
import com.supriadi.diki.codelabs_sharefre.model.UserModel;

import java.util.ArrayList;

import static com.supriadi.diki.codelabs_sharefre.R.id.recyclerView;

public class MahasiswaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MahasiswaAdapter mahasiswaAdapter;
    MahasiswaHelper mahasiswaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);
        recyclerView = findViewById(R.id.recyclerView);


        mahasiswaHelper = new MahasiswaHelper(this);
        mahasiswaAdapter = new MahasiswaAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mahasiswaAdapter);

        mahasiswaHelper.open();
        ArrayList<UserModel> userModels = mahasiswaHelper.getAllData();
        mahasiswaHelper.close();

        mahasiswaAdapter.setData(userModels);
    }
}
