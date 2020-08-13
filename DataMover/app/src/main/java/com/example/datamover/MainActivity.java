package com.example.datamover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mHeading;
    Button changeColorButton;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeading = (TextView) findViewById(R.id.heading);
        changeColorButton = (Button) findViewById(R.id.changeColor);
        //if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) changeColorButton.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Device's API level is: " + String.valueOf(Build.VERSION.SDK_INT), Toast.LENGTH_SHORT).show();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int color = -1;
        color = bundle.getInt("ColorForHeading");
        if(color != -1) mHeading.setTextColor(color);

    }

    public void onSecondActivity(View view) {
        Intent in = new Intent(this, SecondActivity.class);
        startActivity(in);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ColorForHeading", mHeading.getCurrentTextColor());
    }

    public void onChangeColor(View view) {
        int random = (int) (Math.random() * mColorArray.length);
        String colorName = mColorArray[random];
        /*
        Other way to get random integer between a range is :
        Random rand = new Random();
        rand.nextInt(20); -> Gives number between 0-19
         */
        int colorResourceName = getResources().getIdentifier(colorName, "color", this.getPackageName());

        //More safe for all api levels, never crashes the app.
        int colorNumber = ContextCompat.getColor(this, colorResourceName);

        mHeading.setTextColor(colorNumber);
    }

    public void onOrderDesert(View view) {
        if(view.getId() == R.id.orderDesert) {
            Toast.makeText(this, "Directing you to desert's page...", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(this, DesertActivity.class);
            startActivity(in);
        }
    }
}
