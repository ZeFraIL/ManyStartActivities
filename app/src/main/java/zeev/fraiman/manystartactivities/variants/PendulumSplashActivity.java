package zeev.fraiman.manystartactivities.variants;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class PendulumSplashActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ImageView ivLogo;
    private TextView tvPrompt;
    private ObjectAnimator simAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_pendulum);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivLogo = findViewById(R.id.ivPendulumLogo);
        tvPrompt = findViewById(R.id.tvPendulumPrompt);
        Button btnEnter = findViewById(R.id.btnEnterPendulum);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        if (accelerometer == null) {
            tvPrompt.setText(R.string.v9_sensor_unavailable);
            startSimulationMode();
        }

        btnEnter.setOnClickListener(v -> {
            TargetActivity.start(this, getString(R.string.v9_title), getString(R.string.trigger_v9));
            finish();
        });
    }

    private void startSimulationMode() {
        simAnim = ObjectAnimator.ofFloat(ivLogo, "rotation", -25f, 25f);
        simAnim.setDuration(1500);
        simAnim.setRepeatCount(ValueAnimator.INFINITE);
        simAnim.setRepeatMode(ValueAnimator.REVERSE);
        simAnim.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null && accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float accelX = event.values[0]; // tilt left/right
            float targetRotation = -accelX * 5.0f; // degrees
            ivLogo.animate()
                    .rotation(targetRotation)
                    .setDuration(120)
                    .start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No-op
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (simAnim != null) {
            simAnim.cancel();
        }
    }
}
