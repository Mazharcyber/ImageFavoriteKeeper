package com.example.app.addtofav;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.app.addtofav.adapterclass.FavAdapter;
import com.example.app.addtofav.databases.DataBaseHelper;
import com.example.app.addtofav.model.FavoriteModel;

import java.util.ArrayList;

public class FavoriteShowActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataBaseHelper favDB;
    private FavAdapter favAdapter;
    private ArrayList<FavoriteModel> arrayListfav = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_show);
        recyclerView = findViewById(R.id.recyclerviewFav);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favDB = new DataBaseHelper(this);
        getAllFavorite();
    }
    private void getAllFavorite() {
        Cursor cursor = favDB.readAllData3();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                FavoriteModel thememodel = new FavoriteModel();
                thememodel.setKey_id(cursor.getString(0));
                thememodel.setTitle(cursor.getString(1));
                thememodel.setImage(cursor.getInt(3));
                arrayListfav.add(thememodel);
            }
            favAdapter = new FavAdapter(getApplicationContext(), arrayListfav, onClickListenerDelete);
            recyclerView.setAdapter(favAdapter);
            favAdapter.notifyDataSetChanged();
        }
    }
    View.OnClickListener onClickListenerDelete = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            favDB.deleteItem3(arrayListfav.get(pos).getKey_id());
            arrayListfav.remove(arrayListfav.get(pos));
            favAdapter.setItems(arrayListfav);
            favAdapter.notifyDataSetChanged();
        }
    };

}