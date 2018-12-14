package br.com.appdog.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import javax.inject.Inject;

import br.com.appdog.R;
import br.com.appdog.databinding.MainActivityBinding;
import br.com.appdog.view.fragments.DogFragment;
import br.com.appdog.viewmodel.MainViewModel;



public class MainActivity extends BaseActivity{

    //MainActivityBinding binding;
    MainActivityBinding binding;

    @Inject
    public MainViewModel mainViewModel;

  //  @Inject
    public DogFragment dogFragment;
    public FragmentManager fragmentManager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setViewModel(mainViewModel);
        fragmentManager = this.getSupportFragmentManager();
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter
                (fragmentManager);
        final ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabLayout = findViewById(R.id.tabs);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(final FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(final int position) {
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:

                    fragment = new DogFragment();
                    bundle.putString("category", "husky");
                    fragment.setArguments(bundle);
                    break;
               case 1:

                   fragment = new DogFragment();
                   bundle.putString("category", "hound");
                   fragment.setArguments(bundle);
                   break;
                case 2:

                    fragment = new DogFragment();
                    bundle.putString("category", "pug");
                    fragment.setArguments(bundle);
                    break;

                case 3:

                    fragment = new DogFragment();
                    bundle.putString("category", "labrador");
                    fragment.setArguments(bundle);
                    break;
                default:

                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }


    }
}
