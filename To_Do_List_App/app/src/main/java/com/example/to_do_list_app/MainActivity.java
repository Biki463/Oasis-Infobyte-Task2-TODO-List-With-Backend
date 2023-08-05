package com.example.to_do_list_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.to_do_list_app.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
                startActivityForResult(intent,1);
            }
        });
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setHasFixedSize(true);
        RVAdapter adapter=new RVAdapter();
        binding.rv.setAdapter(adapter);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> note) {
              adapter.submitList(note);
            }
        });
  new ItemTouchHelper(new ItemTouchHelper.Callback() {
      @Override
      public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
          return 0;
      }

      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
          return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
          noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));

      }
  }) .attachToRecyclerView(binding.rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            String title=data.getStringExtra("title");
            String dis=data.getStringExtra("dis");
            Note note=new Note(title,dis);
            noteViewModel.insert(note);
            Toast.makeText(this,"note added",Toast.LENGTH_SHORT).show();
        }
    }
}