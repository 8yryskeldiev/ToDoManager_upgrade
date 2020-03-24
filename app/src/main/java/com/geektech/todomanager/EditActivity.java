package com.geektech.todomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditActivity extends AppCompatActivity {
    EditText title, description;
    Button deadline, start, save, cancel;
    TextView deadlineText,startText;
    CheckBox checkBox;
Task task;
Date taskStartDate;
    Date taskDeadline;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.edit_title);
        start=findViewById(R.id.edit_start_button);

        description = findViewById(R.id.edit_description);
        deadline = findViewById(R.id.edit_deadline_button);
        save = findViewById(R.id.edit_save);
        startText=findViewById(R.id.edit_start_text);
        cancel = findViewById(R.id.edit_cancel);
        deadlineText = findViewById(R.id.edit_deadline_text);
        checkBox = findViewById(R.id.details_checkbox);

        Intent intent=getIntent();
        if(intent==null){
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            task = (Task) intent.getSerializableExtra("hhh");
            title.setText(task.title);
            description.setText(task.description);
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            startText.setText(format.format(task.startDate));
            deadlineText.setText(format.format(task.deadline));
        }


    }

    public void onChooseDeadline(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadlineText.setText(dayOfMonth + "." + (month + 1) + "." + year);
                taskDeadline = new GregorianCalendar(year, month, dayOfMonth).getTime();
            }
        }, year, month, day);
        picker.show();
    }

    public void onCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onChooseStartDate(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startText.setText(dayOfMonth + "." + (month + 1) + "." + year);
                taskStartDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
            }
        }, year, month, day);
        picker.show();
    }


    public void onSave(View view) {
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

        task.deadline = taskDeadline;
        task.title = taskTitle;
        task.description = taskDescription;
        task.startDate =taskStartDate ;
        task.isDone = true;

        Intent intent = new Intent();
        intent.putExtra("edit", task);
        setResult(RESULT_OK, intent);
        finish();
    }


}
