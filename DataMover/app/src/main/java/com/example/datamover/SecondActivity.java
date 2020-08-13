package com.example.datamover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    EditText textForDetailedActivity = null;
    TextView textFromDetailedActivity = null;
    public final int REQUESTCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textForDetailedActivity = (EditText) findViewById(R.id.textToDetailedActivity);
        textFromDetailedActivity = (TextView) findViewById(R.id.textFromDetailedActivity);
        //textFromDetailedActivity.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_background, 0, 0, 0);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

    }

    public void onFirstActivity(View view) {
        this.finish();
    }

    public void onDetailedActivity(View view) {
        Intent in = new Intent(this, DetailedActivity.class);
        in.putExtra("Message for Detailed Activity", textForDetailedActivity.getText().toString());
        textForDetailedActivity.setText("");
        startActivityForResult(in, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        super.onActivityResult(requestCode, resultCode, in);
        if(requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            String text = in.getStringExtra("Message from Detailed Activity");
            textFromDetailedActivity.setText(text);
        }
        else textFromDetailedActivity.setText(R.string.no_message);     // this checks if resultCode == RESULT_CANCELLED
    }
}
