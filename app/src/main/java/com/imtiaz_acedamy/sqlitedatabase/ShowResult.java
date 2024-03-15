package com.imtiaz_acedamy.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.imtiaz_acedamy.sqlitedatabase.databinding.ActivityShowResultBinding;


public class ShowResult extends AppCompatActivity {

    private ActivityShowResultBinding binding;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(ShowResult.this);

        Cursor cursor = dbHelper.getAllData();

        binding.tvDisplay.setText("Total row: " + cursor.getCount());

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String mobile = cursor.getString(2);

            binding.tvDisplay.append("\nID: "+id+ " Name: " + name + " Mobile: " + mobile);
        }

    }
}