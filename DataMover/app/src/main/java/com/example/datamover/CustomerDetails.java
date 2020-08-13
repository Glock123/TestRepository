package com.example.datamover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class CustomerDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final int REQUESTCODE = 1;
    private HashSet<String> toppings = new HashSet<>();
    private TextView sweetName=null, showToppings=null;
    private EditText name=null, address=null, phoneNo=null, note=null;
    public View viewForDeliveryOption; // Can be used to reference later which radio button was clicked.
    private RadioButton mSameDay, mNextDay, mPickup;
    private Spinner spinner;
    private String whichDeliveryOptionSelected="Next Day"; // Default delivery selection
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phoneNo = findViewById(R.id.phoneNo);
        note = findViewById(R.id.note);
        sweetName = findViewById(R.id.sweet_name);
        mNextDay = findViewById(R.id.next_day_delivery);
        mSameDay = findViewById(R.id.same_day_delivery);
        mPickup = findViewById(R.id.pickup);
        spinner = findViewById(R.id.type_of_phone);
        showToppings = findViewById(R.id.show_toppings);

        //Restricting the screen orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        //Setting the type of sweet Ordered
        Intent in = getIntent();
        String message = in.getStringExtra(DesertActivity.KEY_FOR_SWEET);
        sweetName.setText(message);

        // Wiring up the spinner with the array of items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_of_phone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner != null) spinner.setAdapter(adapter);

        // Making the next of phone number edittext to dial the number, we set imeOption = actionSend,
        // and this code gets called.
        phoneNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND) {
                    dialNumber();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void dialNumber() {
        String phoneNumber = null;
        if(phoneNo != null) {
            phoneNumber = "tel:" + phoneNo.getText().toString();
        }
        Intent in = new Intent(Intent.ACTION_DIAL);

        in.setData(Uri.parse(phoneNumber));

        if(in.resolveActivity(getPackageManager()) != null) startActivity(in);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        name.setText(bundle.getString("name"));
        address.setText(bundle.getString("address"));
        phoneNo.setText(bundle.getString("phone"));
        note.setText(bundle.getString("note"));
        spinner.setSelection(bundle.getInt("PhoneType"));

        String deliveryType = bundle.getString("DeliveryOption");
        if(deliveryType != null) {
            switch(deliveryType) {
                case "Same Day":
                    mSameDay.setChecked(true);
                    break;
                case "Next Day":
                    mNextDay.setChecked(true);
                    break;
                case "Pickup":
                    mPickup.setChecked(true);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("name", name.getText().toString());
        bundle.putString("address", address.getText().toString());
        bundle.putString("phone", phoneNo.getText().toString());
        bundle.putString("note", note.getText().toString());
        bundle.putString("DeliveryOption", whichDeliveryOptionSelected);
        bundle.putInt("PhoneType", spinner.getSelectedItemPosition());
    }

    public void onSelectDeliveryOption(View view) {
        viewForDeliveryOption = view;

        // TO check if the clicking action checks or un-checks the radio button
        boolean checked = ((RadioButton)view).isChecked();
        if(checked)
            switch(view.getId()) {
                case R.id.same_day_delivery:
                    Toast.makeText(this, "Will be delivered today", Toast.LENGTH_SHORT).show();
                    whichDeliveryOptionSelected = "Same Day";
                    break;
                case R.id.next_day_delivery:
                    Toast.makeText(this, "Will be delivered in next Working day", Toast.LENGTH_SHORT).show();
                    whichDeliveryOptionSelected = "Next Day";
                    break;
                case R.id.pickup:
                    Toast.makeText(this, "Get out of your house and collect your parcel", Toast.LENGTH_SHORT).show();
                    whichDeliveryOptionSelected = "Pickup";
                    break;
            }
        else whichDeliveryOptionSelected = "";

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String phoneType =  parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected Phone Type: " + phoneType, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onToppingButton(View view) {
        Intent in = new Intent(this, ToppingsActivity.class);
        in.putExtra("prevToppings", toppings);
        startActivityForResult(in, REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent in) {
        super.onActivityResult(requestCode, resultCode, in);
        if(requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            if (in != null) {
                showToppings.setText("");
                toppings = (HashSet<String>) in.getSerializableExtra("toppings");
                StringBuilder toppingText = new StringBuilder();
                if(toppings != null)
                    for(String top : toppings) {
                        toppingText.append(top);
                        toppingText.append("\n");
                    }
                if(toppingText.length() > 0) showToppings.setText(toppingText);
            }


        }
    }
}
