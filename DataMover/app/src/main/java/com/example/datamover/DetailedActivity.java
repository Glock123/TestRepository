package com.example.datamover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailedActivity extends AppCompatActivity {
    Spinner mSpinner = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        // Safety measures
        if(extras != null) {
            String message = extras.getString("Message for Detailed Activity");
            // Extra safety lel
            if(message != null && message.compareTo("") != 0)
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        mSpinner = (Spinner) findViewById(R.id.spinner);
    }

    public void returnText(View view) {
        String text = mSpinner.getSelectedItem().toString();
        Intent in = new Intent();
        in.putExtra("Message from Detailed Activity", text);
        if(text.compareTo("RESULT NOT OK") == 0) setResult(RESULT_CANCELED, in);
        else setResult(RESULT_OK, in);
        this.finish();
    }
}
