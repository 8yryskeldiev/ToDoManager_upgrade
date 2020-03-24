package com.geektech.todomanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TaskDetailsActivity extends AppCompatActivity {

    TextView title, description, startDate, deadline;
    CheckBox checkBox;
Button edit ,save;
Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
       edit=findViewById(R.id.deatails_button_edit);
       save=findViewById(R.id.deatails_button_save);
       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(TaskDetailsActivity.this, EditActivity.class);
             intent.putExtra("hhh",task);
               startActivityForResult(intent, 44);
           }
       });

        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            startDate = findViewById(R.id.details_start_date);
            deadline = findViewById(R.id.details_deadline);
            checkBox = findViewById(R.id.details_checkbox);

            task = (Task) intent.getSerializableExtra("task");

            title.setText(task.title);
            description.setText(task.description);
            checkBox.setChecked(task.isDone);
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            startDate.setText(format.format(task.startDate));
            deadline.setText(format.format(task.deadline));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 44) {
            if (resultCode == RESULT_OK) {
                task = (Task) data.getSerializableExtra("edit");
                title.setText(task.title);
                description.setText(task.description);
                checkBox.setChecked(task.isDone);
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                startDate.setText(format.format(task.startDate));
                deadline.setText(format.format(task.deadline));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Task creation canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSaveClick(View view) {
        String taskTitle = title.getText().toString();
        if (taskTitle.equals("")) {
            Toast.makeText(this, "Enter task title", Toast.LENGTH_SHORT).show();
            return;
        }

        String taskDescription = description.getText().toString();
        if (taskDescription.equals("")) {
            Toast.makeText(this, "Enter task description", Toast.LENGTH_SHORT).show();
            return;
        }
        

        title.setText(task.title);
        description.setText(task.description);
        checkBox.setChecked(task.isDone);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        startDate.setText(format.format(task.startDate));
        deadline.setText(format.format(task.deadline));


        Intent intent = new Intent(TaskDetailsActivity.this, MainActivity.class);
        intent.putExtra("details", task);
        startActivity(intent);
    }
}
