package com.example.notika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button signUp;
    Button explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageView image = (ImageView) findViewById(R.id.imageView);

        signUp = (Button) findViewById(R.id.signup);
        explore = (Button) findViewById(R.id.explore);

        signUp.setTranslationX(-1000f);
        explore.setTranslationX(1000f);

        signUp.animate().translationXBy(1000f).setDuration(2000);
        explore.animate().translationXBy(-1000f).setDuration(2000);

        /*image.animate()
             .translationYBy(-500f)
             .setDuration(2000)
             .withEndAction(new Runnable() {
                 @Override
                 public void run() {
                     signUp.animate().translationXBy(1000f).setDuration(2000);
                     explore.animate().translationXBy(-1000f).setDuration(2000);
                 }
             });*/
    }

    public void onClickExplore(View view) {
        Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSignUp(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
