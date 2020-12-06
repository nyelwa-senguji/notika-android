package com.example.notika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    ImageView img;
    TextView heading, heading_desc;

    String topic_name, subject;
//    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        img = findViewById(R.id.img);
        heading = findViewById(R.id.heading);
        heading_desc = findViewById(R.id.heading_desc);
//
        getData();
        setData();
    }

    private void getData(){
        if (getIntent().hasExtra("topic_name") && getIntent().hasExtra("subject")){
            topic_name = getIntent().getStringExtra("topic_name");
            subject = getIntent().getStringExtra("subject");
//            myImage = getIntent().getIntExtra("myImage", 1);
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
//
    private void setData(){
//        img.setImageResource(myImage);
        heading_desc.setText(subject);
        heading.setText(topic_name);
    }
}
