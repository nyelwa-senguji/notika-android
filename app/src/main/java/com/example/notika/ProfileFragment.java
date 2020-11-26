package com.example.notika;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // XML objects
        TextView username = (TextView) view.findViewById(R.id.username);
        StorageReference mStorageRef;
        TextView email = (TextView) view.findViewById(R.id.email);
        CircleImageView profile = (CircleImageView) view.findViewById(R.id.profile_image);
        final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) view.findViewById(R.id.collapsingToolBar);
        AppBarLayout abl = (AppBarLayout) view.findViewById(R.id.appBarLayout);

        // Firebase objects
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();


        for (UserInfo user: FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {
            if (user.getProviderId().equals("google.com")) {
                //user info
                final String name = user.getDisplayName();
                String myemail = user.getEmail();
                Uri photoUrl = user.getPhotoUrl();

                username.setText(name);
                email.setText(myemail);
                //profile.setImageURI(photoUrl);
                RequestOptions placeholderRequest = new RequestOptions();
                placeholderRequest.placeholder(R.drawable.default_profile);
                Glide.with(getActivity()).setDefaultRequestOptions(placeholderRequest).load(photoUrl).into(profile);

                abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    boolean isShow = false;
                    int scrollRange = -1;

                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (scrollRange == -1) {
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }
                        if (scrollRange + verticalOffset == 0) {
                            ctl.setTitle(name);
                            isShow = true;
                        } else if (isShow) {
                            ctl.setTitle("");
                            isShow = false;
                        }
                    }
                });
            }

            if (user.getProviderId().equals("password")){
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

                                        username.setText(user_name);
                                        email.setText(myemail);
                                        RequestOptions placeholderRequest = new RequestOptions();
                                        placeholderRequest.placeholder(R.drawable.default_profile);
                                        Glide.with(getActivity()).setDefaultRequestOptions(placeholderRequest).load(uri).into(profile);

                                        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                                            boolean isShow = false;
                                            int scrollRange = -1;
                                            @Override
                                            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                                                if (scrollRange == -1) {
                                                    scrollRange = appBarLayout.getTotalScrollRange();
                                                }
                                                if (scrollRange + verticalOffset == 0) {
                                                    ctl.setTitle(user_name);
                                                    isShow = true;
                                                } else if (isShow) {
                                                    ctl.setTitle("");
                                                    isShow = false;
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                });

            }
        }
    }
}
