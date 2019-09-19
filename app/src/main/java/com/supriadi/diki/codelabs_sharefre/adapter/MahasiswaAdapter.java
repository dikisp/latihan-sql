package com.supriadi.diki.codelabs_sharefre.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.supriadi.diki.codelabs_sharefre.R;
import com.supriadi.diki.codelabs_sharefre.model.UserModel;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaHolder> {
    private ArrayList<UserModel> lisMahasiswa = new ArrayList<>();

    public MahasiswaAdapter(){

    }

    public  void setData(ArrayList<UserModel> lisMahasiswa){
        if(lisMahasiswa.size() > 0){
            this.lisMahasiswa.clear();
        }
        this.lisMahasiswa.addAll(lisMahasiswa);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MahasiswaHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa_row, parent, false);
        return new MahasiswaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaHolder holder, int position) {
        holder.textViewNama.setText(lisMahasiswa.get(position).getName());
        holder.textViewNim.setText(lisMahasiswa.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return lisMahasiswa.size();
    }

    class MahasiswaHolder extends RecyclerView.ViewHolder {
        private TextView textViewNim;
        private TextView textViewNama;
        MahasiswaHolder(@NonNull View itemView){
            super(itemView);
            textViewNama = (TextView) itemView.findViewById(R.id.txt_nim);
            textViewNama = (TextView) itemView.findViewById(R.id.txt_nama);
        }
    }
}
