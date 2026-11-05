package zeev.fraiman.manystartactivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import android.widget.Button;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import java.util.Locale;

import zeev.fraiman.manystartactivities.adapter.VariantAdapter;
import zeev.fraiman.manystartactivities.model.SplashVariant;
import zeev.fraiman.manystartactivities.variants.AssembleSplashActivity;
import zeev.fraiman.manystartactivities.variants.BottomSheetSplashActivity;
import zeev.fraiman.manystartactivities.variants.BreathingSplashActivity;
import zeev.fraiman.manystartactivities.variants.CalendarSplashActivity;
import zeev.fraiman.manystartactivities.variants.CameraSplashActivity;
import zeev.fraiman.manystartactivities.variants.ContextualSplashActivity;
import zeev.fraiman.manystartactivities.variants.FlashlightSplashActivity;
import zeev.fraiman.manystartactivities.variants.InteractiveSplashActivity;
import zeev.fraiman.manystartactivities.variants.MapSplashActivity;
import zeev.fraiman.manystartactivities.variants.MoodSplashActivity;
import zeev.fraiman.manystartactivities.variants.MotionSplashActivity;
import zeev.fraiman.manystartactivities.variants.OnboardingSplashActivity;
import zeev.fraiman.manystartactivities.variants.ParticleSplashActivity;
import zeev.fraiman.manystartactivities.variants.PendulumSplashActivity;
import zeev.fraiman.manystartactivities.variants.PortalSplashActivity;
import zeev.fraiman.manystartactivities.variants.PreloadSplashActivity;
import zeev.fraiman.manystartactivities.variants.QuoteSplashActivity;
import zeev.fraiman.manystartactivities.variants.RitualSplashActivity;
import zeev.fraiman.manystartactivities.variants.SlideSplashActivity;
import zeev.fraiman.manystartactivities.variants.StandardSplashActivity;
import zeev.fraiman.manystartactivities.variants.SystemSplashActivity;
import zeev.fraiman.manystartactivities.variants.TypewriterSplashActivity;
import zeev.fraiman.manystartactivities.variants.UnboxingSplashActivity;
import zeev.fraiman.manystartactivities.variants.VideoSplashActivity;
import zeev.fraiman.manystartactivities.variants.WebViewSplashActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnLanguageSwitch = findViewById(R.id.btnLanguageSwitch);
        if (btnLanguageSwitch != null) {
            btnLanguageSwitch.setOnClickListener(v -> {
                Locale currentLocale = getResources().getConfiguration().getLocales().get(0);
                String lang = currentLocale.getLanguage();
                if ("he".equalsIgnoreCase(lang) || "iw".equalsIgnoreCase(lang)) {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"));
                } else {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("he"));
                }
            });
        }

        RecyclerView rvVariants = findViewById(R.id.rvVariants);
        rvVariants.setLayoutManager(new LinearLayoutManager(this));

        List<SplashVariant> variants = new ArrayList<>();
        variants.add(new SplashVariant(
                "v1",
                getString(R.string.v0_title),
                getString(R.string.v0_desc),
                getString(R.string.v1_exp),
                StandardSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v2",
                getString(R.string.v1_title),
                getString(R.string.v1_desc),
                getString(R.string.v2_exp),
                PreloadSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v3",
                getString(R.string.v2_title),
                getString(R.string.v2_desc),
                getString(R.string.v3_exp),
                OnboardingSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v4",
                getString(R.string.v3_title),
                getString(R.string.v3_desc),
                getString(R.string.v4_exp),
                InteractiveSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v5",
                getString(R.string.v4_title),
                getString(R.string.v4_desc),
                getString(R.string.v5_exp),
                MotionSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v6",
                getString(R.string.v5_title),
                getString(R.string.v5_desc),
                getString(R.string.v6_exp),
                SystemSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v7",
                getString(R.string.v7_title),
                getString(R.string.v7_desc),
                getString(R.string.v7_exp),
                FlashlightSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v8",
                getString(R.string.v8_title),
                getString(R.string.v8_desc),
                getString(R.string.v8_exp),
                AssembleSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v9",
                getString(R.string.v9_title),
                getString(R.string.v9_desc),
                getString(R.string.v9_exp),
                PendulumSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v10",
                getString(R.string.v10_title),
                getString(R.string.v10_desc),
                getString(R.string.v10_exp),
                UnboxingSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v11",
                getString(R.string.v11_title),
                getString(R.string.v11_desc),
                getString(R.string.v11_exp),
                TypewriterSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v12",
                getString(R.string.v12_title),
                getString(R.string.v12_desc),
                getString(R.string.v12_exp),
                PortalSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v13",
                getString(R.string.v13_title),
                getString(R.string.v13_desc),
                getString(R.string.v13_exp),
                BreathingSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v14",
                getString(R.string.v14_title),
                getString(R.string.v14_desc),
                getString(R.string.v14_exp),
                RitualSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v15",
                getString(R.string.v15_title),
                getString(R.string.v15_desc),
                getString(R.string.v15_exp),
                ContextualSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v16",
                getString(R.string.v16_title),
                getString(R.string.v16_desc),
                getString(R.string.v16_exp),
                QuoteSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v17",
                getString(R.string.v17_title),
                getString(R.string.v17_desc),
                getString(R.string.v17_exp),
                SlideSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v18",
                getString(R.string.v18_title),
                getString(R.string.v18_desc),
                getString(R.string.v18_exp),
                VideoSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v19",
                getString(R.string.v19_title),
                getString(R.string.v19_desc),
                getString(R.string.v19_exp),
                MapSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v20",
                getString(R.string.v20_title),
                getString(R.string.v20_desc),
                getString(R.string.v20_exp),
                WebViewSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v21",
                getString(R.string.v21_title),
                getString(R.string.v21_desc),
                getString(R.string.v21_exp),
                ParticleSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v22",
                getString(R.string.v22_title),
                getString(R.string.v22_desc),
                getString(R.string.v22_exp),
                MoodSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v23",
                getString(R.string.v23_title),
                getString(R.string.v23_desc),
                getString(R.string.v23_exp),
                BottomSheetSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v24",
                getString(R.string.v24_title),
                getString(R.string.v24_desc),
                getString(R.string.v24_exp),
                CameraSplashActivity.class
        ));
        variants.add(new SplashVariant(
                "v25",
                getString(R.string.v25_title),
                getString(R.string.v25_desc),
                getString(R.string.v25_exp),
                CalendarSplashActivity.class
        ));

        VariantAdapter adapter = new VariantAdapter(variants, new VariantAdapter.OnVariantActionListener() {
            @Override
            public void onVariantClick(SplashVariant variant) {
                Intent intent = new Intent(MainActivity.this, variant.getTargetActivityClass());
                startActivity(intent);
            }

            @Override
            public void onInfoClick(SplashVariant variant) {
                showVariantInfoDialog(variant);
            }
        });

        rvVariants.setAdapter(adapter);
    }

    private void showVariantInfoDialog(SplashVariant variant) {
        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle(variant.getTitle())
                .setMessage(variant.getExplanation())
                .setPositiveButton(R.string.btn_close_dialog, (dialog, which) -> dialog.dismiss())
                .setNeutralButton(R.string.btn_launch_demo, (dialog, which) -> {
                    Intent intent = new Intent(MainActivity.this, variant.getTargetActivityClass());
                    startActivity(intent);
                })
                .show();
    }
}
