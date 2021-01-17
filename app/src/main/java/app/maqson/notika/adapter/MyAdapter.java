package app.maqson.notika.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.maqson.notika.R;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String[] data1;
    private int[] images;
    private Context context;

    public  MyAdapter(Context ct, String[] s1, int[] img){
        context = ct;
        data1 = s1;
        images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View listItem = inflater.inflate(R.layout.my_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(listItem);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText(data1[position]);
        holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            myImage = itemView.findViewById(R.id.imageView);
        }
    }
}
