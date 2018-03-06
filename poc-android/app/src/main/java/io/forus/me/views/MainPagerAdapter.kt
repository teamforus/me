package io.forus.me.views

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.widget.Toast

class MainPagerAdapter(fragmentManager: FragmentManager, val fragments: List<Fragment>) : android.support.v4.app.FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}