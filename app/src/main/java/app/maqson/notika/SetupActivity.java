package app.maqson.notika;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    CircleImageView accountProfile;

    private Uri mainImageUri=null;

    private StorageReference mStorageRef;

    private FirebaseAuth mAuth;

    private  String user_id;

    FirebaseFirestore db;

    EditText userNameTxt;

    Button saveBtn;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //Setting up the setupActivity toolbar
        getSupportActionBar().setTitle("Account Setup");     // toolbar title

        progressBar = (ProgressBar) findViewById(R.id.progressbar); // Initializing progress bar

        //Firebase Objects
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user_id = mAuth.getCurrentUser().getUid();

        //Objects from XML file
        accountProfile = (CircleImageView) findViewById(R.id.profile_image);
        userNameTxt = (EditText) findViewById(R.id.userName);
        saveBtn = (Button) findViewById(R.id.saveSetupDataBtn);

        //Onclick event when the image is clicked
        accountProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ContextCompat.checkSelfPermission(SetupActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                        //if permission is not granted, request for permission
                        ActivityCompat.requestPermissions(SetupActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    }else{
                        // start picker to get image for cropping and then use the image in cropping activity
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(1,1)
                                .start(SetupActivity.this);
                    }
                }else{
                    // start picker to get image for cropping and then use the image in cropping activity
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(1,1)
                            .start(SetupActivity.this);
                }
            }
        });


        //Saving data into Firestore
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                //Getting text from the Username textField
                String user_name = userNameTxt.getText().toString();

                //Checking if image and textField are not empty
                if (!TextUtils.isEmpty(user_name) && mainImageUri != null){
                    //Get user_id
                    user_id = mAuth.getCurrentUser().getUid();

                    //Store the image into fireStore
                    StorageReference image_path = mStorageRef.child("profile_images")
                                                              .child(user_id + ".jpg");
                    image_path.putFile(mainImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                Task<Uri> downloadUri = task.getResult().getMetadata().getReference().getDownloadUrl();

                                Map<String, String> userMap = new HashMap<>();
                                userMap.put("name", user_name);
                                userMap.put("image", downloadUri.toString());

                                db.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        if (task.isSuccessful()){
                                            startActivity(new Intent(SetupActivity.this, SignInActivity.class));
                                        }else{
                                            String error = task.getException().getMessage();
                                            Toast.makeText(SetupActivity.this, "Error:"+ error, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(SetupActivity.this, "Error:"+ error, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mainImageUri = result.getUri();
                accountProfile.setImageURI(mainImageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
