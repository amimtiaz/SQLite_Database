package com.imtiaz_acedamy.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //Cursor cursor = dbHelper.getAllData();



        Cursor cursor = dbHelper.searchDataByName("");
        binding.tvDisplay.setText("Total row: " + cursor.getCount());

        if (cursor!= null && cursor.getCount()>0){

            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String mobile = cursor.getString(2);

                binding.tvDisplay.append("\nID: "+id+ " Name: " + name + " Mobile: " + mobile);
            }

        }else {
            binding.tvDisplay.setText("Data nai");
        }


        binding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchBarText = binding.searchBar.getText().toString().toLowerCase();

                Toast.makeText(ShowResult.this, searchBarText, Toast.LENGTH_SHORT).show();

                Cursor cursor = dbHelper.searchDataByName(""+searchBarText);
                binding.tvDisplay.setText("Total row: " + cursor.getCount());
                
                if (cursor!= null && cursor.getCount()>0){ 
                    
                    while (cursor.moveToNext()){
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String mobile = cursor.getString(2);

                        binding.tvDisplay.append("\nID: "+id+ " Name: " + name + " Mobile: " + mobile);
                    }
                    
                }else {
                    binding.tvDisplay.setText("Data nai");
                }

               
            }
        });









    }
}