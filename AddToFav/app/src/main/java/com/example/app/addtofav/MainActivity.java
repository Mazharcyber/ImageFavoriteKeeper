package com.example.app.addtofav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.addtofav.adapterclass.ThemesAdapter;
import com.example.app.addtofav.model.ThemesModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button favButton;
    private final ArrayList<ThemesModel> themesModels = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favButton = findViewById(R.id.goFav);
         recyclerView=findViewById(R.id.recyclerviewThemes);
        recyclerView.setHasFixedSize(true);

        addToFavorite();

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FavoriteShowActivity.class));
            }
        });
    }

    public void addToFavorite(){
        recyclerView.setAdapter(new ThemesAdapter(themesModels,MainActivity.this));
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        themesModels.add(new ThemesModel(R.drawable.pic,"1","theme1"));
        themesModels.add(new ThemesModel(R.drawable.pic2,"2","theme2"));
        themesModels.add(new ThemesModel(R.drawable.pic3,"3","theme3"));
        themesModels.add(new ThemesModel(R.drawable.pic4,"4","theme4"));
        themesModels.add(new ThemesModel(R.drawable.pic5,"5","theme5"));
        themesModels.add(new ThemesModel(R.drawable.pic6,"6","theme6"));
    }
    @Override
    protected void onResume() {
        super.onResume();
        addToFavorite();
    }


}