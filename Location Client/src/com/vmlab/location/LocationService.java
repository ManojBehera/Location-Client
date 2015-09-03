package com.vmlab.location;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service {

	private Timer timer;
	private long UPDATE_INTERVAL;
	public static final String Stub = null;
	LocationManager mlocmag;
	LocationListener mlocList;
	private double lat, longn;
	static int i = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		mlocmag = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Location loc = mlocmag
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (loc == null) {
			loc = mlocmag
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		timer = new Timer(); // location.
		UpdateWithNewLocation(loc); // This method is used to get updated
//		mlocmag.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
//				mlocList);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
		mlocmag.removeUpdates(mlocList);
	}

	private void UpdateWithNewLocation(final Location loc) {

		UPDATE_INTERVAL = 1000; // one minute duration
		

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (loc != null) {
					final double latitude = loc.getLatitude(); // Updated lat
					final double longitude = loc.getLongitude(); // Updated long

					String response = null;
//					if (lat != latitude || longn != longitude) {

						lat = latitude;
						longn = longitude;
						//send receiver
						Log.i("Manoj Behera", ""+latitude+" : "+longitude);

//					}
				}

				else {
					String latLongStr = "No lat and longitude found" + i++;
					Log.i("Manoj Behera", latLongStr);
				}

			}
		}, 0, UPDATE_INTERVAL);
	}

}