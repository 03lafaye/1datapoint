package com.onedatapoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CuringDepressionActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button textButton = (Button) findViewById(R.id.TextActivityButton);
        textButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent myintent = new Intent(CuringDepressionActivity.this, TextActivity.class);
            	CuringDepressionActivity.this.startActivity(myintent);	
            }
        });
        
        final Button sliderButton = (Button) findViewById(R.id.SliderActivityButton);
        sliderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                // Perform action on click
            	Intent myintent2 = new Intent(CuringDepressionActivity.this, SliderActivity.class);
            	CuringDepressionActivity.this.startActivity(myintent2);	
            }
        });
    }
    
}
