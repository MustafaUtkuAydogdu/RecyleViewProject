package com.example.recyleviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ExampleItemList myList;


        Intent intent = getIntent();

        myList = intent.getParcelableExtra("linker");

        ExampleItem item1 = myList.myList.get(1);

        int image = item1.getImageResource();
        String lectureName = item1.getText1();
        String buildingName = item1.getText2();

        ImageView myImage = findViewById(R.id.resim);
        TextView lectureName1 = findViewById(R.id.lectureName);
        TextView buildingName1 = findViewById(R.id.buildingName);

        myImage.setImageResource(image);
        lectureName1.setText(lectureName);
        buildingName1.setText(buildingName);

    }


}
