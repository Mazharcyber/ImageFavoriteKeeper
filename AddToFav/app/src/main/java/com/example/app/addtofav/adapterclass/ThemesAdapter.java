package com.example.app.addtofav.adapterclass;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.addtofav.R;
import com.example.app.addtofav.databases.DataBaseHelper;
import com.example.app.addtofav.model.ThemesModel;

import java.util.ArrayList;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ViewHolder> {

    private final ArrayList<ThemesModel> themesModels;
    private final Context context;
    private DataBaseHelper favDB;

    public ThemesAdapter(ArrayList<ThemesModel> themesModels, Context context) {
        this.themesModels = themesModels;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        favDB = new DataBaseHelper(context);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ThemesModel themesModelfav = themesModels.get(position);
        Cursor cursor = favDB.readAllData3();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                ArrayList<String> ids = new ArrayList<>();
                ids.add(cursor.getString(0));
                if (ids.contains(themesModelfav.getKey_id())) {
                    holder.favBtn.setImageResource(R.drawable.ic_favorite);
                }
            }
        }

        String title = themesModelfav.getTitle();
        int image = themesModelfav.getImage();
        String id = themesModelfav.getKey_id();
        holder.imageView.setImageResource(themesModelfav.getImage());
        holder.titleTextView.setText(themesModelfav.getTitle());
        holder.imageView.setTag(position);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFavourite(title, image, holder.favBtn, id);
            }
        });
    }


    @Override
    public int getItemCount() {
        return themesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        ImageView favBtn;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.id_ImageView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            favBtn = itemView.findViewById(R.id.fav_Btn);
            cardView = itemView.findViewById(R.id.cardViewThemes);
        }
    }

    private void checkFavourite(String title, int image, ImageView
            favorite, String unique_id) {
        Cursor cursor = favDB.readAllData3();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equals(unique_id)) {
                    favDB.deleteItem3(unique_id);
                    favorite.setImageResource(R.drawable.ic_unfavorite_24);
                    return;
                }
            }
        }
        favDB.addScanRecord3(unique_id, title, image);
        favorite.setImageResource(R.drawable.ic_favorite);
    }

}

