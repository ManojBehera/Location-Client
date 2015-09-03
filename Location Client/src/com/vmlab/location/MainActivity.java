package com.vmlab.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		checkLocationService();
	}

	/**
	 * Check location service.
	 */
	private void checkLocationService()
	{

	    Intent mServiceIntent = new Intent(this, LocationService.class);
	    startService(mServiceIntent);
	}

	
}