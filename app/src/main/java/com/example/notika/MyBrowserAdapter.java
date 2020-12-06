package com.example.notika;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MyBrowserAdapter extends FirestoreRecyclerAdapter<Topic, MyBrowserAdapter.BrowserViewHolder> {

//    private String[] data1;
//    private String[] data2;
//    private int[] images;
    private Context context;

    MyBrowserAdapter(@NonNull FirestoreRecyclerOptions<Topic> options){
       super(options);
    }

    @NonNull
    @Override
    public BrowserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View browseItem = layoutInflater.inflate(R.layout.browse_list, parent, false);
        BrowserViewHolder browserViewHolder = new BrowserViewHolder(browseItem);
        return browserViewHolder;
    }

//    @Override
//    public void onBindViewHolder(@NonNull BrowserViewHolder holder, int position) {
//        holder.title_tv.setText(data1[position]);
//        holder.description_tv.setText(data2[position]);
//        holder.horizontalImage.setImageResource(images[position]);
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("data1", data1[position]);
//                intent.putExtra("data2", data2[position]);
//                intent.putExtra("myImage", images[position]);
//                context.startActivity(intent);
//            }
//        });
//    }

    @Override
    protected void onBindViewHolder(@NonNull BrowserViewHolder browserViewHolder, int i, @NonNull Topic topic) {
        browserViewHolder.title_tv.setText(topic.getTopic_name());
        browserViewHolder.description_tv.setText(topic.getSubject());
        //Glide.with(context).load(topic.getImage()).into(browserViewHolder.horizontalImage);
        Picasso.get().load(topic.getImage()).into(browserViewHolder.horizontalImage);
    }

//    @Override
//    public int getItemCount() {
//        return data1.length;
//    }

    static class BrowserViewHolder extends RecyclerView.ViewHolder{

        TextView title_tv;
        TextView description_tv;
        ImageView horizontalImage;
        LinearLayout mainLayout;

        BrowserViewHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = itemView.findViewById(R.id.title_tv);
            description_tv = itemView.findViewById(R.id.description_tv);
            horizontalImage = itemView.findViewById(R.id.horizontalImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), DetailActivity.class);
                    i.putExtra("topic_name", title_tv.getText());
                    i.putExtra("subject", description_tv.getText());
                    itemView.getContext().startActivity(i);
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(), DetailActivity.class));
                }
            });
        }
    }
}
