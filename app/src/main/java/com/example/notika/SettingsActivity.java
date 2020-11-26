package com.example.notika;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

//    private static final String PREFS_NAME = "prefs";
//
//    private static final String PREF_DARK_THEME = "dark theme";

    TextView ifNotSignedIn, userName, userEmail, signOut;

    CircleImageView profile;

    RelativeLayout ifSignedIn;

    Switch toggleSwitch;

    FirebaseAuth mAuth;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        // Use chosen theme
//        SharedPreferences preferences  = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//
//        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
//
//        if (useDarkTheme){
//            setTheme(R.style.DarkTheme);
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Setting up the settingActivity toolbar
        getSupportActionBar().setTitle("Settings");     // toolbar title

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      // Back button enabled

        getSupportActionBar().setDisplayShowHomeEnabled(true);      // Back button enabled

        //Objects from XML file
        ifNotSignedIn = (TextView) findViewById(R.id.ifNotSignedIn);        //Text requiring user to sign in

        signOut = (TextView) findViewById(R.id.signOut);        //Sign out text

        ifSignedIn = (RelativeLayout) findViewById(R.id.ifSignedIn);        // Relative layout displaying user information

        userName = (TextView) findViewById(R.id.username);      // Text displaying name of the user

        profile = (CircleImageView) findViewById(R.id.profile_image);       // User profile image display

        userEmail = (TextView) findViewById(R.id.userEmail);        // Text displaying email of the user

        toggleSwitch = (Switch) findViewById(R.id.themeSwitch);

        mAuth = FirebaseAuth.getInstance();     // Authentication instance


        firebaseUser = mAuth.getCurrentUser();


        if(firebaseUser!=null) {
            getUserInfo();
        }else{
            // If user is not signed in
            ifSignedIn.setVisibility(View.GONE);        // Remove the relativeLayout containing user information

            // Assign a listener to send user to the signIn Activity
            ifNotSignedIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SettingsActivity.this, SignInActivity.class));
                }
            });
        }

        //When sign out text is pressed, start a dialogue box
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle(R.string.signout)
                        .setMessage("Are you sure you want to Sign Out")
                        .setPositiveButton("SIGN OUT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.signOut();
                                startActivity(new Intent(SettingsActivity.this, SignInActivity.class));
                                ifSignedIn.setVisibility(View.GONE);
                                ifNotSignedIn.setVisibility(View.VISIBLE);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                buttonPositive.setTextColor(ContextCompat.getColor(SettingsActivity.this, R.color.colorPrimary));
                Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonNegative.setTextColor(ContextCompat.getColor(SettingsActivity.this,R.color.colorPrimary));
            }
        });

//        //toggle switch for changing the theme
//        toggleSwitch.setChecked(useDarkTheme);
//        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                toggleTheme(b);
//            }
//        });
    }

    private void getUserInfo() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        StorageReference mStorageRef;

        for (UserInfo user: FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {
            // Checking if user is signed in
            if (user.getProviderId().equals("google.com")) {

                ifNotSignedIn.setVisibility(View.GONE);     // Remove the text requesting user to sign in

                // Obtain data from firebase using firebase instance created
                final String name = user.getDisplayName();      // Get user name

                String myemail = user.getEmail();       // Get user email

                Uri photoUrl = user.getPhotoUrl();      // Get user profile

                //Setting the obtained data into the required fields
                userName.setText(name);     // Set user name

                userEmail.setText(myemail);     // Set user email

                Glide.with(SettingsActivity.this).load(photoUrl).into(profile);     // Set user profile

            }
            if (user.getProviderId().equals("password")) {
                ifNotSignedIn.setVisibility(View.GONE);     // Remove the text requesting user to sign in
                String user_id = mAuth.getCurrentUser().getUid();
                mStorageRef = FirebaseStorage.getInstance().getReference();
                mStorageRef = FirebaseStorage.getInstance().getReference();
                StorageReference profileRef = mStorageRef.child("profile_images")
                        .child(user_id + ".jpg");
                profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Get user data from firestore
                        db.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()){
                                    if (task.getResult().exists()){
                                        String myemail = user.getEmail();
                                        String user_name = task.getResult().getString("name");

                                        userName.setText(user_name);
                                        userEmail.setText(myemail);
                                        RequestOptions placeholderRequest = new RequestOptions();
                                        placeholderRequest.placeholder(R.drawable.default_profile);
                                        Glide.with(SettingsActivity.this).setDefaultRequestOptions(placeholderRequest).load(uri).into(profile);
                                    }
                                }
                            }
                        });
                    }
                });
            }
        }
    }

//    // Method to store user's preference for theme and restart the current activity
//    private void toggleTheme(boolean darkTheme){
//        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putBoolean(PREF_DARK_THEME, darkTheme);
//        editor.apply();
//
//        Intent i = getIntent();
//        finish();
//        startActivity(i);
//    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
