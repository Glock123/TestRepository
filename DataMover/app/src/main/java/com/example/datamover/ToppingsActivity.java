package com.example.datamover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import java.util.HashSet;

public class ToppingsActivity extends AppCompatActivity {

    private HashSet<String> toppingsSelected = new HashSet<>();
    private CheckBox chocolateSyrup, sprinkles, crushedNuts, cherries, orioCookieCrumbles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);

        chocolateSyrup = findViewById(R.id.chocolate_syrup);
        sprinkles = findViewById(R.id.sprinkles);
        crushedNuts = findViewById((R.id.crushed_nuts));
        cherries = findViewById(R.id.cherries);
        orioCookieCrumbles = findViewById(R.id.orio_cookie_crumbles);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        toppingsSelected = (HashSet<String>)(getIntent().getSerializableExtra("prevToppings"));

        if(toppingsSelected != null)
            for(String top : toppingsSelected)
                switch(top) {
                    case "Chocolate Syrup" :
                        chocolateSyrup.setChecked(true);
                        break;
                    case "Sprinkles":
                        sprinkles.setChecked(true);
                        break;
                    case "Crushed Nuts":
                        crushedNuts.setChecked(true);
                        break;
                    case "Cherries":
                        cherries.setChecked(true);
                        break;
                    case "Orio Cookie Crumbles":
                        orioCookieCrumbles.setChecked(true);
                        break;
                }
    }

    public void onSelection(View view) {
        CheckBox checkBox = (CheckBox)view;
        String messageInside = checkBox.getText().toString();

        if(checkBox.isChecked()) toppingsSelected.add(messageInside);
        else {
            try {
                toppingsSelected.remove(messageInside);
            }
            catch(Exception e){}
        }
    }


    public void onCustomer(View view) {
        Intent in = new Intent();
        in.putExtra("toppings", toppingsSelected);
        setResult(RESULT_OK, in);
        this.finish();
    }
}
