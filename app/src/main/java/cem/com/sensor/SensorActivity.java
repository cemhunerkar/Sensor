package cem.com.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Sensor lightSensor;
    TextView iv, iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_screen);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        iv = (TextView) findViewById(R.id.jtext);
        iv2 = (TextView) findViewById(R.id.jtext2);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            iv2.setTextSize(50);
            float f = event.values[0];
            iv2.setText("LIGHT: " + ((int) f));
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] == 0) {
                iv.setTextSize(50);
                iv.setText("NEAR " + event.values[0]);
                //System.out.println("near");
            } else {
                iv.setTextSize(50);
                iv.setText("FAR " + event.values[0]);
                //System.out.println("far");
            }
        }
    }
}