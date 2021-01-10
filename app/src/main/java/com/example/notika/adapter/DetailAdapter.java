package com.example.notika.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notika.DetailActivity;
import com.example.notika.R;
import com.example.notika.model.Sub_Topic;
import com.example.notika.model.Topic;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class DetailAdapter extends FirestoreRecyclerAdapter<Sub_Topic, DetailAdapter.DetailViewHolder> {

    public DetailAdapter(@NonNull FirestoreRecyclerOptions<Sub_Topic> options){
        super(options);
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View browseItem = layoutInflater.inflate(R.layout.detail_list, parent, false);
        DetailViewHolder detailViewHolder = new DetailViewHolder(browseItem);
        return detailViewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i, @NonNull Sub_Topic sub_topic) {
        detailViewHolder.sub_topic.setText(sub_topic.getSub_topic_name());


    }

    static class DetailViewHolder extends RecyclerView.ViewHolder{


        TextView sub_topic;

        DetailViewHolder(@NonNull View itemView) {
            super(itemView);

            sub_topic = itemView.findViewById(R.id.sub_topic);

        }
    }
}
