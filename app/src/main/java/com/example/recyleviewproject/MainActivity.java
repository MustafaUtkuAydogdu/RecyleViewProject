package com.example.recyleviewproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //properties
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private com.example.recyleviewproject.ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonInsert;
    private EditText editTextInsert;
    private EditText editTextRemove;
    private EditText lectureNameText;
    private EditText buildingNameText;

    private Button saveButton;


    ArrayList<ExampleItem> exportedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();



        //createExampleList();

        buildRecyclerView();
        setButtons();
    }
    public void insertItem(int position,String lectureName, String buildingName ) {
        mExampleList.add(position, new ExampleItem(R.drawable.bilkent, "Lecture : " + lectureName, "Building Name "+ buildingName));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }


    public void createExampleList() {
        mExampleList = new ArrayList<>();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new com.example.recyleviewproject.ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new com.example.recyleviewproject.ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //empty
            }
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }
    public void setButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        saveButton = findViewById(R.id.button_save);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);
        lectureNameText = findViewById(R.id.lectureName);
        buildingNameText = findViewById(R.id.buildingName);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                String lectureName = lectureNameText.getText().toString();
                String buildingName = buildingNameText.getText().toString();

                insertItem(position,lectureName,buildingName);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

                Intent intent;
                intent = new Intent(MainActivity.this,Activity2.class);
                intent.putExtra("linker", createList(mExampleList));
                startActivity(intent);

            }
        });
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list",json);
        editor.apply();
        Toast.makeText(this,"Dersleriniz kaydedildi",Toast.LENGTH_LONG).show();

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    public ExampleItemList createList(ArrayList<ExampleItem> k)
    {
        ExampleItemList exportList = new ExampleItemList();

        for (int i = 0; i < k.size(); i++)
        {
            exportList.myList.add(k.get(i));
        }

        return exportList;

    }


}