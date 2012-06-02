package com.onedatapoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textlayout);
        
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent myintent = new Intent(TextActivity.this, CuringDepressionActivity.class);
            	TextActivity.this.startActivity(myintent);
            	
            }
        });
        
        
        final Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	final EditText hotThoughtInputText = (EditText) findViewById(R.id.HotThoughtInputText);
            	hotThoughtInputText.setEnabled(false);
            	
            	Intent myintent = new Intent(TextActivity.this, CuringDepressionActivity.class);
            	TextActivity.this.startActivity(myintent);
            	
            }
        });
    }
}
