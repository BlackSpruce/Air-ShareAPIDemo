package com.blackspruce.airshareapidemo;

import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	private static final String TAG = "AirShareAPIDemo";
	private static final int REQUEST_PICK_PAIRED_DEVICE = 99;
	private static final int REQUEST_VIEW_URL_ON_REMOTE_DEVICE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 Intent pickPairedDeviceIntent = new Intent(Intent.ACTION_PICK,null);
		 pickPairedDeviceIntent.setComponent(new ComponentName("com.blackspruce.gloo","com.blackspruce.gloo.PickPairedDevice"));
		 startActivityForResult(pickPairedDeviceIntent, REQUEST_PICK_PAIRED_DEVICE);
   
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch (requestCode) {
	    case REQUEST_PICK_PAIRED_DEVICE:
	        if (resultCode == RESULT_OK && data != null) {
	            String deviceName = data.getStringExtra("deviceName");
	            String shareToken = data.getStringExtra("shareToken");
	            Log.d(TAG, " Pick result : devicename = "+deviceName+", shareToken="+shareToken);
	            if ( shareToken != null ) {
	            	 Intent viewIntent = new Intent(Intent.ACTION_VIEW,
	            			 				Uri.parse("https://play.google.com/store/apps/details?id=com.blackspruce.gloo&referrer=utm_source%3Dapidemo"));
	        		 viewIntent.setComponent(new ComponentName("com.blackspruce.gloo","com.blackspruce.gloo.ReceiveLocalIntent"));
	        		 viewIntent.putExtra("shareToken", shareToken);
	        		 startActivityForResult(viewIntent, REQUEST_VIEW_URL_ON_REMOTE_DEVICE);
	            }
	        }
	        break;
	    case REQUEST_VIEW_URL_ON_REMOTE_DEVICE:
	    	if ( resultCode == RESULT_OK )
	    		Log.d(TAG, " Share result OK");
	    	else
	    		Log.d(TAG, " Share result FAILED");
	    	break;
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
