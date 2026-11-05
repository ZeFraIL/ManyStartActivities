package zeev.fraiman.manystartactivities.variants;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class VideoSplashActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView tvStatus;
    private ImageView ivLogo;
    private ObjectAnimator fallbackAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_video);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView = findViewById(R.id.videoViewBackground);
        tvStatus = findViewById(R.id.tvVideoStatus);
        ivLogo = findViewById(R.id.ivVideoLogo);
        Button btnEnter = findViewById(R.id.btnEnterVideo);

        setupVideoPlayback();

        btnEnter.setOnClickListener(v -> {
            TargetActivity.start(this, getString(R.string.v18_title), getString(R.string.trigger_v18));
            finish();
        });
    }

    private void setupVideoPlayback() {
        int resId = getResources().getIdentifier("splash_video", "raw", getPackageName());
        if (resId != 0) {
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + resId);
            videoView.setVideoURI(videoUri);
            videoView.setOnPreparedListener(mp -> {
                mp.setLooping(true);
                mp.setVolume(0f, 0f); // Silent playback
                videoView.start();
            });
        } else {
            // Fallback if video file is not present in res/raw/
            tvStatus.setText(R.string.v18_no_video);

            fallbackAnim = ObjectAnimator.ofFloat(ivLogo, "alpha", 0.4f, 1.0f);
            fallbackAnim.setDuration(1200);
            fallbackAnim.setRepeatCount(ValueAnimator.INFINITE);
            fallbackAnim.setRepeatMode(ValueAnimator.REVERSE);
            fallbackAnim.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null && !videoView.isPlaying()) {
            videoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.stopPlayback();
        }
        if (fallbackAnim != null) {
            fallbackAnim.cancel();
        }
    }
}
