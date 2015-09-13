package com.pixion.wificar;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Activity;

public class ActivityCar extends Activity {
	
	WebView fw;
	WebView vf;
	Button forward;
	Button backward;
	Button left;
	Button right;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		//make app fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
        
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		
		fw = (WebView)findViewById(R.id.webView2);
		fw.setBackgroundColor(0x00000000);
		
		vf = (WebView)findViewById(R.id.webView1);
		vf.setBackgroundColor(0x00000000);
		
		forward = (Button)findViewById(R.id.fd);
		backward = (Button)findViewById(R.id.bd);
		left = (Button)findViewById(R.id.lt);
		right = (Button)findViewById(R.id.rt);
		
		forward.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{
					fw.loadUrl("http://192.168.1.4/?FORWARD");			//CALLS FORWARD FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				else if(event.getAction() == MotionEvent.ACTION_UP)
				{
					fw.loadUrl("http://192.168.1.4/?STOP");				//CALLS STOP FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				
				return false;
			}
		});
		
		backward.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{
					fw.loadUrl("http://192.168.1.4/?BACKWARD");			//CALLS BACKWARD FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				else if(event.getAction() == MotionEvent.ACTION_UP)
				{
					fw.loadUrl("http://192.168.1.4/?STOP");			//CALLS STOP FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				
				return false;
			}
		});
		
		left.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{
					fw.loadUrl("http://192.168.1.4/?LEFT");			//CALLS LEFT FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				else if(event.getAction() == MotionEvent.ACTION_UP)
				{
					fw.loadUrl("http://192.168.1.4/?CENTER");			//CALLS CENTER FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				
				return false;
			}
		});
		
		right.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{
					fw.loadUrl("http://192.168.1.4/?RIGHT");			//CALLS RIGHT FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				else if(event.getAction() == MotionEvent.ACTION_UP)
				{
					fw.loadUrl("http://192.168.1.4/?CENTER");			//CALLS CENTER FUNCTION
					fw.setWebViewClient(new WebViewClient());
				}
				
				return false;
			}
		});
	}
	
	
	public void onToggleClicked(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	 
	    if (on) {
	    	final EditText feed = (EditText)findViewById(R.id.editText1);
	    	String s = feed.getText().toString();
	    	vf.loadUrl(s);
	    	vf.setWebViewClient(new WebViewClient());
	    	fw.loadUrl("http://192.168.1.4/?INITIALIZE");			//CALLS INITIALIZE FUNCTION
	    	fw.setWebViewClient(new WebViewClient());
	    	Toast.makeText(getApplicationContext(), "WifiCar Initialized. Ready To Go!", Toast.LENGTH_LONG).show();
	    }
	    else
	    {
	    	fw.loadUrl("http://192.168.1.4/?HALT");					//CALLS HALT FUNCTION
	    	Toast.makeText(getApplicationContext(), "WifiCar Stopped.", Toast.LENGTH_LONG).show();
	    	finish();
	    }
	}
	
	public void onLedClicked(View view2) {
	    // Is the toggle on?
	    	boolean led = ((ToggleButton) view2).isChecked();
	    	if (led) {
	    		fw.loadUrl("http://192.168.1.4/?LEDSON");				//CALLS LED-ON FUNCTION
	    		fw.setWebViewClient(new WebViewClient());
	    		Toast.makeText(getApplicationContext(), "Led's turned on.", Toast.LENGTH_SHORT).show();
	    	} else if(!led){
		    	fw.loadUrl("http://192.168.1.4/?LEDSOFF");				//CALLS LED-OF FUNCTION
		    	fw.setWebViewClient(new WebViewClient());
		    	Toast.makeText(getApplicationContext(), "Led's turned off.", Toast.LENGTH_SHORT).show();
	    	}
	}

	@Override
	protected void onPause()
	{
	super.onPause();
	    //Save state here
		fw.loadUrl("http://192.168.1.4/?HALT");					//CALLS HALT FUNCTION
		Toast.makeText(getApplicationContext(), "WifiCar Stopped.", Toast.LENGTH_LONG).show();
		finish();
	}
}
