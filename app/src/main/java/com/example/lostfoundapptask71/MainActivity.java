package com.example.lostfoundapptask71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonCreateNewItem, buttonViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreateNewItem = (Button) findViewById(R.id.buttonCreateNewItem);
        buttonViewList = (Button) findViewById(R.id.buttonViewList);


        buttonCreateNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddItem();
            }
        });
        buttonViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemList();
            }
        });
    }

    // Opens the add item screen
    public void openAddItem(){
        Intent intent = new Intent(this,add_Item.class);
        startActivity(intent);
    }

    // Opens the List of items
    public void openItemList(){
        Intent intent = new Intent(this,ItemList.class);
        startActivity(intent);
    }
}