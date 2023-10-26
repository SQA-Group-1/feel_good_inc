package com.example.feelgoodinc.adapters;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;

public class TutorialPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragments;

    public TutorialPagerAdapter (FragmentActivity fa, List<Fragment> fragments) {
        super(fa);
        this.fragments = fragments;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}

