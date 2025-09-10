package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    LinearLayout inputBar;
    EditText inputLine;
    Button confirmButton;
    Button addButton;
    Button deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {"Edmonton", "Vancouver"};
        //Views
        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);

        inputBar = findViewById(R.id.input_bar);
        inputLine = findViewById(R.id.input_line);
        confirmButton = findViewById(R.id.confirm_button);


        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);

        //Enable single choice mode
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Add button -> show input bar
        addButton.setOnClickListener(v -> inputBar.setVisibility(View.VISIBLE));

        //Confirm button -> add city, clear box, hide bar
        confirmButton.setOnClickListener(v -> {
            String newCity = inputLine.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                inputLine.setText("");            // clear text
                inputBar.setVisibility(View.GONE); // hide bar
            }
        });

        //Delete button -> remove selected city
        deleteButton.setOnClickListener(v -> {
            int position = cityList.getCheckedItemPosition();
            if (position != ListView.INVALID_POSITION) {
                dataList.remove(position);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
            }
        });
    }
}