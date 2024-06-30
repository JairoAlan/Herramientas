package com.example.practicaherramientas;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_nivel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_nivel extends Fragment implements SensorEventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    View vista;
    TextView tv_z, tv_y, tv_x;
    SensorManager sensorManager;
    Sensor accelerometer;
    ImageButton nivel1, nivel2;

    public Fragment_nivel() {
        // Required empty public constructor
    }

    public static Fragment_nivel newInstance(String param1, String param2) {
        Fragment_nivel fragment = new Fragment_nivel();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_nivel, container, false);

        tv_x = vista.findViewById(R.id.tv_x);
        tv_y = vista.findViewById(R.id.tv_y);
        tv_z = vista.findViewById(R.id.tv_z);
        nivel1 = vista.findViewById(R.id.nivel1);
        nivel2 = vista.findViewById(R.id.nivel2);

        sensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        nivel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSensors();
            }
        });

        nivel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSensors();
            }
        });

        return vista;
    }

    private void startSensors() {
        nivel2.setVisibility(View.VISIBLE);
        nivel1.setVisibility(View.GONE);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void stopSensors() {
        nivel2.setVisibility(View.GONE);
        nivel1.setVisibility(View.VISIBLE);
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            double pitch = Math.atan2(-x, Math.sqrt(y * y + z * z)) * 180 / Math.PI;
            double roll = Math.atan2(y, z) * 180 / Math.PI;

            tv_x.setText(String.format("Y : %.2f°", pitch));
            tv_y.setText(String.format("X : %.2f°", roll));
            tv_z.setText(String.format("X: %.2f, Y: %.2f, Z: %.2f", x, y, z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPause() {
        super.onPause();
        stopSensors();
    }

    @Override
    public void onResume() {
        super.onResume();
        startSensors();
    }
}
