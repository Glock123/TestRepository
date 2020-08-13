package com.example.datamover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class DesertActivity extends AppCompatActivity {
    public static final String KEY_FOR_SWEET = "key for sweet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desert);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

    }

    public void onSweet(View view) {
        String message = "Nothing ordered";
        switch(view.getId()) {
            case R.id.donut:
                message = "Ordered DONUT";
                break;
            case R.id.ice_cream:
                message = "Ordered: ICE CREAM";
                break;
            case R.id.froyo:
                message = "Ordered: FROYO";
                break;
            case R.id.gulab_jamun:
                message = "Ordered: GULAB JAMUN";
                break;
            case R.id.jalebi:
                message = "Ordered: JALEBI";
                break;
            case R.id.boondi_laddu:
                message = "Ordered: BOONDI LADDU";
                break;
            default: message = "Nothing Ordered";
        }
        Intent in = new Intent(this, CustomerDetails.class);
        in.putExtra(KEY_FOR_SWEET, message);
        startActivity(in);
    }


    public void toMainActivity(View view){
        this.finish();
    }

}
