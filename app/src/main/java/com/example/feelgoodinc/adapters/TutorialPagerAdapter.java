package com.example.feelgoodinc.adapters;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;

/***
 * adapts the viewpager to display the tutorial fragments in a carousel style
 * Usage:
 * <pre>
 *      // in activity with a viewPager
 *      FragmentStateAdapter pagerAdapter = new TutorialPagerAdapter(this, fragments);
 *      viewPager.setAdapter(pagerAdapter);
 * </pre>
 */
public class TutorialPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragments;

    /***
     * assigns list of fragments
     * @param fa activity
     * @param fragments list of fragments
     */
    public TutorialPagerAdapter (FragmentActivity fa, List<Fragment> fragments) {
        super(fa);
        this.fragments = fragments;
    }

    /***
     * fetches a fragment from the list
     * @param position position of fragment to get
     * @return  fragment
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    /***
     * gets size of fragment list
     * @return int list size
     */
    @Override
    public int getItemCount() {
        return fragments.size();
    }
}

