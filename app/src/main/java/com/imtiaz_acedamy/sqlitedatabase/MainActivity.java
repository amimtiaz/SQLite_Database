package com.imtiaz_acedamy.sqlitedatabase;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.imtiaz_acedamy.sqlitedatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(MainActivity.this);

        binding.insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = binding.edName.getText().toString().trim();
                String userMobile = binding.edMobile.getText().toString().trim();

                if (userName.isEmpty() && userMobile.isEmpty()){
                    binding.edName.setError("Fill the filed");
                } else {

                    dbHelper.insertData(binding.edName.getText().toString(), binding.edMobile.getText().toString());
                    Toast.makeText(MainActivity.this, "Data has been inserted", Toast.LENGTH_SHORT).show();
                    showDialog();

                }



            }
        });

        binding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ShowResult.class));

            }
        });

       
    }

//    public void alertDialog() {
//
//        Button okayBtn;
//
//        AlertDialog.Builder builder;
//
//        builder = new AlertDialog.Builder(this);
//        builder.setTitle("Data Inserted");
//        builder.setMessage("Data successfully Inserted");
//        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        builder.show();
//    }


    public void showDialog() {

        Button okayBtn;
        Dialog dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        okayBtn = dialog.findViewById(R.id.okayBtn);
        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
        dialog.show();
    }

}