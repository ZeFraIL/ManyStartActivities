package zeev.fraiman.manystartactivities.variants;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;
import zeev.fraiman.manystartactivities.view.ParticleSurfaceView;

public class ParticleSplashActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private boolean isNavigated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_particle);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivLogo = findViewById(R.id.ivParticleLogo);
        ParticleSurfaceView surfaceView = findViewById(R.id.surfaceViewParticle);

        surfaceView.setOnParticlesAssembledListener(this::onParticlesConverged);
    }

    private synchronized void onParticlesConverged() {
        if (isNavigated) return;
        isNavigated = true;

        // Fade in logo in center
        ivLogo.animate()
                .alpha(1.0f)
                .scaleX(1.15f)
                .scaleY(1.15f)
                .setDuration(400)
                .withEndAction(() -> ivLogo.postDelayed(() -> {
                    TargetActivity.start(ParticleSplashActivity.this, getString(R.string.v21_title), getString(R.string.trigger_v21));
                    finish();
                }, 600));
    }
}
