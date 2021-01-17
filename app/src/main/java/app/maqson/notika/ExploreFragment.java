package app.maqson.notika;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;


public class ExploreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView settings = (ImageView) view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SettingsActivity.class);
                startActivity(i);
            }
        });


        //Attach SectionPageAdapter to ViewPager
        SectionPageAdapter pageAdapter = new SectionPageAdapter(getChildFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
        pager.setAdapter(pageAdapter);

        //Attach ViewPager to TabLayout
        final TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
    }

    public class SectionPageAdapter extends FragmentPagerAdapter {

        public SectionPageAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new BrowseFragment();
                case 1:
                    return new CatalogueFragment();
            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return getResources().getText(R.string.explore_tab);
                case 1:
                    return getResources().getText(R.string.catalogue_tab);
            }
            return null;
        }
    }
}
