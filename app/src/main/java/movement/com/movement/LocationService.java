package movement.com.movement;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by isma-ilou on 20.06.2018.
 */

public class LocationService extends Service
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final long INTERVAL = 1000*2;
    private static final long FASTEST_INTERVAL = 1000;
    static double distance = 0;
    LocationRequest mRequest;
    GoogleApiClient mClient;
    Location mCurrent, mStart, mEnd;


    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        createLocationRequest();
        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mClient.connect();
        return mBinder;
    }

    private void createLocationRequest() {
        mRequest = LocationRequest.create();
        mRequest.setInterval(INTERVAL);
        mRequest.setFastestInterval(FASTEST_INTERVAL);
        mRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mClient, mRequest, this);
        } catch (SecurityException e){

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        CountdownActivity.progressDialog.dismiss();
        mCurrent = location;
        if (mStart == null) {
            mStart = mEnd = mCurrent;
        } else {
            mEnd = mCurrent;
        }

        updateUI();
    }

    private void updateUI() {
        if (CountdownActivity.p == 0){
            distance = distance + (mStart.distanceTo(mEnd) / 1000.00);
            CountdownActivity.endTime = System.currentTimeMillis();
            long diff = CountdownActivity.endTime - CountdownActivity.startTime;
            diff = TimeUnit.MILLISECONDS.toSeconds(diff);

            CountdownActivity.mTextDuration.setText(diff + " seconds");
            CountdownActivity.mTextDistance.setText(new DecimalFormat("#.###").format(distance) + " km");

            mStart = mEnd;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        stopLocationUpdates();
        if (mClient.isConnected()) {
            mClient.disconnect();
        }
        mStart = mEnd = null;
        distance = 0;
        return super.onUnbind(intent);
    }

    private void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mClient, this);
        distance = 0;
    }

    public class LocalBinder extends Binder {
        public LocationService getService(){
            return LocationService.this;
        }
    }
}
