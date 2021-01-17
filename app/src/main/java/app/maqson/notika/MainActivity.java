package app.maqson.notika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    Button signUp;
    Button explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = (Button) findViewById(R.id.signup);
        explore = (Button) findViewById(R.id.explore);

        // Animation of the buttons
        signUp.setTranslationX(-1000f);
        explore.setTranslationX(1000f);

        signUp.animate().translationXBy(1000f).setDuration(2000);
        explore.animate().translationXBy(-1000f).setDuration(2000);

    }

    // onCLick Start Explore Activity
    public void onClickExplore(View view) {
        Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
        startActivity(intent);
        finish();
    }

    // onCLick Start Sign up Activity
    public void onClickSignUp(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
