package com.example.app.addtofav.adapterclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.addtofav.R;
import com.example.app.addtofav.databases.DataBaseHelper;
import com.example.app.addtofav.model.FavoriteModel;


import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FavoriteModel> favArraylist;
    private DataBaseHelper favDB;
    View.OnClickListener onClickListener;
    View.OnClickListener onClickListenerDelete;

    public FavAdapter(Context context, ArrayList<FavoriteModel> favArraylist,View.OnClickListener onClickListenerDelete) {
        this.context = context;
        this.favArraylist = favArraylist;
        this.onClickListenerDelete = onClickListenerDelete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,
                parent, false);
        favDB = new DataBaseHelper(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        FavoriteModel themesModel = favArraylist.get(position);
        holder.favImageView.setImageResource(themesModel.getImage());
        holder.title_textView.setText(themesModel.getTitle());
        holder.favImageView.setTag(position);
        holder.favBtn.setImageResource(R.drawable.ic_unfavorite_24);
        holder.favBtn.setTag(position);
        holder.favImageView.setOnClickListener(onClickListener);
        holder.favBtn.setOnClickListener(onClickListenerDelete);

    }

    @Override
    public int getItemCount() {
        return favArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favBtn;
        ImageView favImageView;
        TextView title_textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favBtn = itemView.findViewById(R.id.fav_Btn);
            favImageView = itemView.findViewById(R.id.id_ImageView);
            title_textView = itemView.findViewById(R.id.title_textView);

        }
    }

    public void setItems(ArrayList<FavoriteModel> favArraylist) {
        this.favArraylist = favArraylist;
    }


}