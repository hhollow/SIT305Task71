package com.example.lostfoundapptask71;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class add_Item extends AppCompatActivity {

    EditText  input_Name,  input_Phone,  input_Description,  input_Date,  input_Location;
    Button buttonSaveNewItem, buttonHome;
    TextView itemID;
    RadioGroup postTypeRadioGroup;
    RadioButton rbtn_lost, rbtn_found;
    DatabaseHelper db;

    public String postType = "NotSet";
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        postTypeRadioGroup = (RadioGroup) findViewById(R.id.postTypeRadioGroup);
        rbtn_lost = (RadioButton) findViewById(R.id.rbtn_lost);
        rbtn_found = (RadioButton) findViewById(R.id.rbtn_found);
        input_Name = (EditText)  findViewById(R.id.input_Name);
        input_Phone = (EditText)  findViewById(R.id.input_Phone);
        input_Description = (EditText)  findViewById(R.id.input_Description);
        input_Date = (EditText)  findViewById(R.id.input_Date);
        input_Location = (EditText)  findViewById(R.id.input_Location);
        buttonSaveNewItem = (Button) findViewById(R.id.buttonSaveNewItem);
        buttonHome = (Button) findViewById(R.id.buttonHome);
        db = new DatabaseHelper(this);

        // sets the item to either be Lost or Found
        postTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup postTypeRadioGroup, int checkedID) {
                switch(checkedID) {
                    case R.id.rbtn_lost:
                        postType = "Lost";
                        break;
                    case R.id.rbtn_found:
                        postType = "Found";
                        break;
                }
            }
        });


        // This method saves the user input to the database, it also calls the validation method to check for missing fields
        buttonSaveNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputValidation()== true){
                    //Commit new entry to DB
                    String item_TypeTEXT = postType;
                    String item_NameTEXT = input_Name.getText().toString();
                    String item_PhoneTEXT = input_Phone.getText().toString();
                    String item_DescriptionTEXT = input_Description.getText().toString();
                    String item_DateTEXT = input_Date.getText().toString();
                    String item_LocationTEXT = input_Location.getText().toString();

                    Boolean checkInsertSuccess = db.insertData(item_TypeTEXT,item_NameTEXT, item_PhoneTEXT, item_DescriptionTEXT, item_DateTEXT, item_LocationTEXT);
                    if(checkInsertSuccess){
                        Toast.makeText(add_Item.this, "Item saved!", Toast.LENGTH_LONG).show();
                        backToHome();
                    }
                    else {
                        Toast.makeText(add_Item.this, "FAILED", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    inputValidation();
                }

            }
        });


        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send user back to homepage
                backToHome();
            }
        });

    }


    // Takes user back to main page
    public void backToHome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    // checks for empty fields before saving new database entry
    public Boolean inputValidation(){
        //Check each input field for values and warn if empty
        Boolean formComplete = false;
        if (postType.length() <1 ){
            Toast.makeText(add_Item.this, "Pick a post type", Toast.LENGTH_SHORT).show();
            formComplete = false;
        }
        if (input_Name.length() <1 ){
            Toast.makeText(add_Item.this, "Enter Name", Toast.LENGTH_SHORT).show();
            formComplete = false;
        }
        if (input_Phone.length() <1 ) {
            Toast.makeText(add_Item.this, "Enter Phone", Toast.LENGTH_SHORT).show();
            formComplete = false;
        }
        if (input_Description.length() <1 ) {
            Toast.makeText(add_Item.this, "Enter Description", Toast.LENGTH_SHORT).show();
            formComplete = false;
        }
        if (input_Date.length() <1 ) {
            Toast.makeText(add_Item.this, "Enter Date", Toast.LENGTH_SHORT).show();
            formComplete = false;
        }
        if (input_Location.length() <1 ) {
            Toast.makeText(add_Item.this, "Enter Location", Toast.LENGTH_SHORT).show();
            formComplete = false;
        }
        else if (postType != null && input_Name != null && input_Phone !=null && input_Description !=null && input_Date !=null && input_Location !=null ) {
            // Set number of sets to run through
            formComplete = true;
        }
        return formComplete;
    }
}