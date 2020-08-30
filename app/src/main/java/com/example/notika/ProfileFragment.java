package com.example.notika;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        TextView username = (TextView) view.findViewById(R.id.username);
        TextView email = (TextView) view.findViewById(R.id.email);
        CircleImageView profile = (CircleImageView) view.findViewById(R.id.profile_image);
        final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) view.findViewById(R.id.collapsingToolBar);
        AppBarLayout abl = (AppBarLayout) view.findViewById(R.id.appBarLayout);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            final String name = user.getDisplayName();
            String myemail = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            username.setText(name);
            email.setText(myemail);
            //profile.setImageURI(photoUrl);
            Glide.with(getActivity()).load(photoUrl).into(profile);

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
    }
}
