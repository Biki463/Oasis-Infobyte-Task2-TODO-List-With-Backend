package com.example.to_do_list_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.to_do_list_app.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
  ActivityDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      binding.button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent=new Intent();
            intent.putExtra("title",binding.title.getText().toString());
            intent.putExtra("dis",binding.dis.getText().toString());
            setResult(RESULT_OK,intent);
            finish();

          }
      });


    }
}