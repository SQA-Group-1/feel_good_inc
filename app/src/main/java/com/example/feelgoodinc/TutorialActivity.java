package com.example.feelgoodinc;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.example.feelgoodinc.adapters.TutorialPagerAdapter;
import com.example.feelgoodinc.fragments.tutorialFragments.TutorialFragment1;
import com.example.feelgoodinc.fragments.tutorialFragments.TutorialFragment2;
import com.example.feelgoodinc.fragments.tutorialFragments.TutorialFragment3;
import com.example.feelgoodinc.fragments.tutorialFragments.TutorialFragment4;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // create the fragment pages and add to an arraylist
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TutorialFragment1());
        fragments.add(new TutorialFragment2());
        fragments.add(new TutorialFragment3());
        fragments.add(new TutorialFragment4());

        ViewPager2 viewPager = findViewById(R.id.pager);

        // instantiate new pagerAdapter using the fragments list and assign it to the viewPager
        FragmentStateAdapter pagerAdapter = new TutorialPagerAdapter(this, fragments);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // attach tab layout to the view pager
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true,
                (tab, position) -> {});
        tabLayoutMediator.attach();
    }

}