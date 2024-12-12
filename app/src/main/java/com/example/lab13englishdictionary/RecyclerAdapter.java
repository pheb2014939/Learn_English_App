package com.example.lab13englishdictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Animation tranlate_anim;

    private Context context;
    private ArrayList<Vocabulary> vocabularyArrayList;

    public RecyclerAdapter(Context context, ArrayList<Vocabulary> vocabularyArrayList) {
        this.context = context;
        this.vocabularyArrayList = vocabularyArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vocabulary vocabulary = vocabularyArrayList.get(position);
        holder.vocab.setText(vocabulary.getVocab());
        holder.ex.setText(vocabulary.getEx());
        holder.def.setText(vocabulary.getDef());

        Glide.with(context)
                .load(vocabulary.getImageUrl()) // Assuming getImageUrl() returns the URL of the image
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return vocabularyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ex, def, vocab;

        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            ex = itemView.findViewById(R.id.ex);
            def = itemView.findViewById(R.id.def);
            vocab = itemView.findViewById(R.id.vocab);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            tranlate_anim = AnimationUtils.loadAnimation(context, R.anim.tranlate_anim);
            mainLayout.setAnimation(tranlate_anim);
        }
    }
}

