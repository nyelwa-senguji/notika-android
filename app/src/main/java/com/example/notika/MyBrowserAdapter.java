package com.example.notika;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyBrowserAdapter extends RecyclerView.Adapter<MyBrowserAdapter.BrowserViewHolder> {

    String data1[];
    String data2[];
    int images[];
    Context context;

    public  MyBrowserAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public BrowserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View browseItem = layoutInflater.inflate(R.layout.browse_list, parent, false);
        BrowserViewHolder browserViewHolder = new BrowserViewHolder(browseItem);
        return browserViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrowserViewHolder holder, int position) {
        holder.title_tv.setText(data1[position]);
        holder.description_tv.setText(data2[position]);
        holder.horizontalImage.setImageResource(images[position]);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("myImage", images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class BrowserViewHolder extends RecyclerView.ViewHolder{

        TextView title_tv;
        TextView description_tv;
        ImageView horizontalImage;
        LinearLayout mainLayout;

        public BrowserViewHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = itemView.findViewById(R.id.title_tv);
            description_tv = itemView.findViewById(R.id.description_tv);
            horizontalImage = itemView.findViewById(R.id.horizontalImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
