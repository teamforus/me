package io.forus.me.views.wallet

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.forus.me.R
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.forus.me.views.base.TitledFragment

class WalletFragment : TitledFragment() {

    private lateinit var navigation:TabLayout
    private lateinit var pager:WalletPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.wallet_fragment, container, false)
        val pages = listOf(
                TokenFragment().also { it.title = resources.getString(R.string.tab_tokens) },
                AssetFragment().also { it.title = resources.getString(R.string.tab_assets) },
                ServiceFragment().also { it.title = resources.getString(R.string.tab_services) }
        )
        navigation = view.findViewById(R.id.navigation_wallet)
        pager = view.findViewById(R.id.wallet_pager)
        pager.adapter = WalletPagerAdapter(childFragmentManager, pages)
        navigation.setupWithViewPager(pager)
        return view
    }

    fun showAssets() {
        this.pager.currentItem = 1
    }

    fun showServices() {
        this.pager.currentItem = 2
    }

    fun showTokens() {
        this.pager.currentItem = 0
    }

    class WalletPagerAdapter(fragmentManager: FragmentManager, private val fragments: List<TitledFragment>): FragmentPagerAdapter(fragmentManager) {

        override fun getCount(): Int {
            return fragments.size
        }
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragments[position].title
        }

    }
}