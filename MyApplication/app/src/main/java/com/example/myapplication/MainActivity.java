package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private Sensor proximitySensor;
    private Sensor accelerometer;
    private Sensor gyroscope;

    private TextView lightSensorValue;
    private TextView proximitySensorValue;
    private TextView accelerometerValue;
    private TextView gyroscopeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        lightSensorValue = findViewById(R.id.lightSensorValue);
        proximitySensorValue = findViewById(R.id.proximitySensorValue);
        accelerometerValue = findViewById(R.id.accelerometerValue);
        gyroscopeValue = findViewById(R.id.gyroscopeValue);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == lightSensor) {
            lightSensorValue.setText("Light Sensor: " + event.values[0]);
        } else if (event.sensor == proximitySensor) {
            proximitySensorValue.setText("Proximity Sensor: " + event.values[0]);
        } else if (event.sensor == accelerometer) {
            accelerometerValue.setText("Accelerometer: X: " + event.values[0] + ", Y: " + event.values[1] + ", Z: " + event.values[2]);
        } else if (event.sensor == gyroscope) {
            gyroscopeValue.setText("Gyroscope: X: " + event.values[0] + ", Y: " + event.values[1] + ", Z: " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used, but required to implement SensorEventListener
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
