package com.example.appnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appnotes.db.Task;
import com.example.appnotes.db.TaskService;

import java.util.ArrayList;
import java.util.List;

public class TasksActiviy extends AppCompatActivity {
    private TaskService taskService;
    private int userId;
    private ListView taskList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        taskService = new TaskService(this);
        userId = getIntent().getIntExtra("user_id", -1);
        taskList = findViewById(R.id.taskList);
        Button addBtn = findViewById(R.id.addTaskBtn);
        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TasksActiviy.this , CreateTaskActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }
    public void loadTasks(){
        List<Task> list = taskService.getTasks(userId);
        List<String> titles = new ArrayList<>();
        for (Task t : list ) titles.add(t.getTask());
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        taskList.setAdapter(adapter);
        taskList.setOnItemClickListener(((parent, view, position, id) -> {
            Task clickedTask = list.get(position);

            Intent i = new Intent(TasksActiviy.this, TaskDetailsActivity.class);
            i.putExtra("task_id", clickedTask.getId());
            startActivity(i);
        }));

    }
}
