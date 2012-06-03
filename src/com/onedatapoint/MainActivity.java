package com.onedatapoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parentlayout);
        
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent myintent = new Intent(MainActivity.this, CuringDepressionActivity.class);
            	MainActivity.this.finish();
            	
            }
        });
        
        
        final Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent myintent = new Intent(MainActivity.this, CuringDepressionActivity.class);
            	MainActivity.this.finish();
            	
            }
        });
    }
}
