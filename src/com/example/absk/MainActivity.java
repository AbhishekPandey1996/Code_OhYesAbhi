package com.example.absk;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements android.view.View.OnClickListener{

Button newtransaction, givenlist;   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        givenlist = (Button) findViewById(R.id.blendamount);
       newtransaction = (Button) findViewById(R.id.bnewtrans); 
       newtransaction.setOnClickListener(this);
       givenlist.setOnClickListener(this);
}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch(v.getId()){
	
	case R.id.bnewtrans:
		
		Intent i = new Intent(MainActivity.this, newtrans.class);
		startActivity(i);
		break;
		
	case R.id.blendamount:
		Intent j = new Intent(MainActivity.this, Givenlist.class);
		break;
	
	}
	}


   
}