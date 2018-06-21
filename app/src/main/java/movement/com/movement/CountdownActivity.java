package movement.com.movement;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.SettingInjectorService;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import movement.com.movement.model.Sponsor;

public class CountdownActivity extends AppCompatActivity {

    static TextView mTextDuration, mTextDistance;
    Button mButtonStart, mButtonStop, mButtonPause;

    LocationManager mLocationManager;
    static boolean status;
    static long startTime, endTime;
    static ProgressDialog progressDialog;
    static int p = 0;

    LocationService mService;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        mTextDuration = findViewById(R.id.text_test_time);
        mTextDistance = findViewById(R.id.text_test_distance);
        mButtonStart = findViewById(R.id.button_test_start);
        mButtonPause = findViewById(R.id.button_test_pause);
        mButtonStop = findViewById(R.id.button_test_stop);

        requestForPermissions();
        addOnClickListener();
    }

    private void addOnClickListener() {
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGPS();
                mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                    return;
                if (status == false)
                    bindService();

                progressDialog = new ProgressDialog(CountdownActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Getting location");
                progressDialog.show();

                mButtonStart.setVisibility(View.GONE);
                mButtonPause.setVisibility(View.VISIBLE);
                mButtonPause.setText("Pause");
                mButtonStop.setVisibility(View.VISIBLE);
            }
        });

        mButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonPause.getText().toString().equalsIgnoreCase("pause")){
                    mButtonPause.setText("Resume");
                    p = 1;
                } else if (mButtonPause.getText().toString().equalsIgnoreCase("resume")){
                    checkGPS();
                    mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        return;
                    mButtonPause.setText("Pause");
                    p = 0;
                }
            }
        });

        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == true)
                    unbindService();

                mButtonStart.setVisibility(View.VISIBLE);
                mButtonPause.setVisibility(View.GONE);
                mButtonPause.setText("Pause");
                mButtonStop.setVisibility(View.GONE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestForPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1000);
        }
    }

    private void checkGPS() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            showGPSDisableAlert();
        }
    }

    private void showGPSDisableAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Enable GPS to use this function")
                .setCancelable(false)
                .setPositiveButton("Enable GPS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void bindService() {
        if (status == true) return;

        Intent intent = new Intent(getApplicationContext(), LocationService.class);
        bindService(intent, sc, BIND_AUTO_CREATE);
        status = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1000:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
            mService = binder.getService();
            status = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            status = false;
        }
    };

    @Override
    protected void onDestroy() {
        if (status == true) {
            unbindService();
        }
        super.onDestroy();
    }

    private void unbindService() {
        if (status == false) return;
        Intent intent = new Intent(getApplicationContext(), LocationService.class);
        unbindService(sc);
        status = false;
    }

    @Override
    public void onBackPressed() {
        if (status == false) {
            super.onBackPressed();
        } else {
            moveTaskToBack(true);
        }
    }
}
