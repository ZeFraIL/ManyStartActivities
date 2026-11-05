package zeev.fraiman.manystartactivities.variants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class OnboardingSplashActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button btnNext;
    private Button btnSkip;
    private List<OnboardingPage> pages;

    private static class OnboardingPage {
        final String title;
        final String description;

        OnboardingPage(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_onboarding);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager = findViewById(R.id.viewPager);
        TabLayout tabIndicator = findViewById(R.id.tabIndicator);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        pages = new ArrayList<>();
        pages.add(new OnboardingPage(getString(R.string.v2_slide1_title), getString(R.string.v2_slide1_desc)));
        pages.add(new OnboardingPage(getString(R.string.v2_slide2_title), getString(R.string.v2_slide2_desc)));
        pages.add(new OnboardingPage(getString(R.string.v2_slide3_title), getString(R.string.v2_slide3_desc)));

        viewPager.setAdapter(new OnboardingAdapter(pages));

        new TabLayoutMediator(tabIndicator, viewPager, (tab, position) -> {
            // Dot indicators automatically managed
        }).attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == pages.size() - 1) {
                    btnNext.setText(R.string.v2_btn_start);
                    btnSkip.setVisibility(View.INVISIBLE);
                } else {
                    btnNext.setText(R.string.v2_btn_next);
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }
        });

        btnNext.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();
            if (current < pages.size() - 1) {
                viewPager.setCurrentItem(current + 1);
            } else {
                TargetActivity.start(this, getString(R.string.v2_title), getString(R.string.trigger_v2_complete));
                finish();
            }
        });

        btnSkip.setOnClickListener(v -> {
            TargetActivity.start(this, getString(R.string.v2_title), getString(R.string.trigger_v2_skip));
            finish();
        });
    }

    private static class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.PageViewHolder> {
        private final List<OnboardingPage> pageList;

        OnboardingAdapter(List<OnboardingPage> pageList) {
            this.pageList = pageList;
        }

        @NonNull
        @Override
        public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_onboarding_page, parent, false);
            return new PageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
            OnboardingPage page = pageList.get(position);
            holder.tvTitle.setText(page.title);
            holder.tvDescription.setText(page.description);
        }

        @Override
        public int getItemCount() {
            return pageList.size();
        }

        static class PageViewHolder extends RecyclerView.ViewHolder {
            final TextView tvTitle;
            final TextView tvDescription;

            PageViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvOnboardingTitle);
                tvDescription = itemView.findViewById(R.id.tvOnboardingDescription);
            }
        }
    }
}
