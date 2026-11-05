package zeev.fraiman.manystartactivities.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.model.SplashVariant;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.VariantViewHolder> {

    public interface OnVariantActionListener {
        void onVariantClick(SplashVariant variant);
        void onInfoClick(SplashVariant variant);
    }

    private final List<SplashVariant> variants;
    private final OnVariantActionListener actionListener;

    public VariantAdapter(List<SplashVariant> variants, OnVariantActionListener actionListener) {
        this.variants = variants;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public VariantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_variant, parent, false);
        return new VariantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VariantViewHolder holder, int position) {
        SplashVariant variant = variants.get(position);
        holder.tvTitle.setText(variant.getTitle());
        holder.tvDescription.setText(variant.getDescription());

        holder.btnInfo.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onInfoClick(variant);
            }
        });

        holder.btnLaunch.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onVariantClick(variant);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onVariantClick(variant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return variants.size();
    }

    static class VariantViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final Button btnInfo;
        final Button btnLaunch;

        public VariantViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvVariantTitle);
            tvDescription = itemView.findViewById(R.id.tvVariantDescription);
            btnInfo = itemView.findViewById(R.id.btnInfo);
            btnLaunch = itemView.findViewById(R.id.btnLaunch);
        }
    }
}
