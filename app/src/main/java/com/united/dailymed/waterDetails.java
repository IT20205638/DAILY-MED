package com.united.dailymed;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class waterDetails extends AppCompatActivity {

    Button  btnwaterDetails;
    EditText finalTot;
    RadioGroup rgActivities;
    RadioButton rbActivities;
    RadioGroup rgGender;
    RadioButton rbGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_water_details);


        //getting the id's of required components

        finalTot = findViewById(R.id.etTotal);
        btnwaterDetails = findViewById(R.id.btnStart);
        rgActivities = findViewById(R.id.rgactivities);
        rgGender = findViewById(R.id.rggender);

    }


    /*on click method of button to insert user details to db and move to next activity */
    public void waterdetailsDone(View view){

        //getting the id of checked radio button
        int id = rgActivities.getCheckedRadioButtonId();
        rbActivities = (RadioButton)findViewById(id);
        //getting the id of checked radio button
        int id2 = rgGender.getCheckedRadioButtonId();
        rbGender = (RadioButton)findViewById(id2);
        //instance of the db helper class
        WaterDBHandler waterdbhandler=new WaterDBHandler(this);
        //convert water target amount obtained to string
        String total = finalTot.getText().toString();
        //validation for null values
        if (!total.isEmpty()){

            //call to insert method
            long val = waterdbhandler.addWater(rbGender.getText().toString(),rbActivities.getText().toString(),
                    (double)Integer.parseInt(finalTot.getText().toString()));

            //redirecting on success and remaining on failure
            if (val > 0) {
                Intent i = new Intent(waterDetails.this, Waterdashboard.class);
                Toast.makeText(this, "Changes Applied!", Toast.LENGTH_SHORT).show();
                startActivity(i);
            } else {
                Intent i = new Intent(waterDetails.this, waterDetails.class);
                Toast.makeText(this, "Failed to calculate water", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            //requesting input if field is null
            Toast.makeText(waterDetails.this,"Please enter your details!",Toast.LENGTH_SHORT).show();
        }
    }//end of method
}














