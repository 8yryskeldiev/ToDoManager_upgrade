package com.geektech.todomanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements TaskClickListener {

    RecyclerView recyclerView;
    MainAdapter adapter;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MainAdapter();
        adapter.listener = this;
        recyclerView.setAdapter(adapter);


        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("to_main");
        if(task!=null){
    adapter.addTask(task);
    Log.d("ololo", "в ресайклер");
}


    }

    public void onCreateTask(View view) {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivityForResult(intent, 42);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42) {
            if (resultCode == RESULT_OK) {
             Task task = (Task) data.getSerializableExtra("task");
               adapter.addTask(task);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Task creation canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onTaskClick(Task task) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        intent.putExtra("to_details", task);
        startActivity(intent);
        Log.d("ololo", "от мейн к деталям");

    }
}
