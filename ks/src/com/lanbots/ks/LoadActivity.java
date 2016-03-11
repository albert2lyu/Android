package com.lanbots.ks;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class LoadActivity extends Activity {
	
	private static final int LOAD_DISPLAY_INME = 1500;
	
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
          
        getWindow().setFormat(PixelFormat.RGBA_8888);  
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);  
        setContentView(R.layout.load);  
          
        new Handler().postDelayed(new Runnable() {  
            public void run() {  
                //Go to main activity, and finish load activity  
                Intent mainIntent = new Intent(LoadActivity.this, LoginActivity.class);  
                LoadActivity.this.startActivity(mainIntent);  
                LoadActivity.this.finish();  
            }  
        }, LOAD_DISPLAY_INME);   
    }  

}
