package app.maqson.notika;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class OpenPdfActivity extends AppCompatActivity {

    Toolbar toolbar;

    String sub_topic_name, topic_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pdf);

        toolbar = findViewById(R.id.toolBar);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if ( getIntent().hasExtra("sub_topic_name") && getIntent().hasExtra("ID")) {
            sub_topic_name = getIntent().getStringExtra("sub_topic_name");
            topic_id = getIntent().getStringExtra("ID");
            toolbar.setTitle(sub_topic_name);
        }
    }
}
