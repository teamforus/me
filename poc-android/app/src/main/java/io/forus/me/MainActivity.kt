package io.forus.me

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.forus.me.entities.Record
import io.forus.me.entities.Account
import io.forus.me.entities.RecordCategory
import io.forus.me.views.fragments.WalletFragment
import io.forus.me.entities.base.WalletItem
import io.forus.me.services.DatabaseService
import io.forus.me.services.RecordService
import io.forus.me.services.ValidationService
import io.forus.me.views.fragments.RecordsFragment
import io.forus.me.views.MainPagerAdapter
import io.forus.me.views.fragments.MeFragment
import io.forus.me.views.transformers.MainTransformer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
        AppCompatActivity(),
        WalletFragment.WalletListener,
        RecordsFragment.RecordsListener,
        MeFragment.AccountListener,
        ViewPager.OnPageChangeListener {

    private lateinit var dataThread: DatabaseService.DataThread
    private lateinit var mPager: ViewPager
    private lateinit var mPagerAdapter: PagerAdapter

    /**
     * Shows a toast on the screen for debugging reasons. Remove
     * on release.
     *
     * @param text The text to display on screen.
      */
    private fun debug(text: String) {
        Toast.makeText(this.baseContext, text, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dataThread = DatabaseService.DataThread("DATA_MAIN")
        this.dataThread.start()

        this.dataThread.postTask(Runnable { DatabaseService.inject(this) })

        // Hold the main process until database is loaded.
        while (!DatabaseService.ready) {}

        this.mPager = findViewById(R.id.main_pager)
        this.mPager.setPageTransformer(false, MainTransformer())
        this.mPager.addOnPageChangeListener(this)

        val fragments = ArrayList<Fragment>()
        fragments.add(0, WalletFragment())
        fragments.add(1, MeFragment())
        fragments.add(2, RecordsFragment())
        this.mPagerAdapter = MainPagerAdapter(supportFragmentManager, fragments)
        this.mPager.adapter = this.mPagerAdapter
        this.showMe()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_wallet -> {
                this.mPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_qr -> {
                this.mPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_records -> {
                this.mPager.currentItem = 2
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    override fun onItemSelect(item: WalletItem) {
        // Todo show wallet item info shenanigans
        debug("Selected: $item")
    }

    override fun onItemSelect(record: Record) {
        // TODO show record stuff
        debug("Selected: $record")
    }

    /**
     * What to do when tapping a category label.
     *
     * @param category The clicked category
     */
    override fun onCategorySelect(category: RecordCategory) {
        val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        alertBuilder.setTitle(R.string.choose_name)
        val nameText = EditText(this)
        nameText.inputType = InputType.TYPE_CLASS_TEXT
        val addressText = EditText(this)
        addressText.inputType = InputType.TYPE_CLASS_TEXT
        alertBuilder.setView(nameText)
        alertBuilder.setPositiveButton(R.string.create, {
            dialog: DialogInterface?, which: Int ->
            val address: String = addressText.text.toString()
            val name: String = nameText.text.toString()

            if (ValidationService.isValidAddress(address)) {
                RecordService.newRecord(address, name, category)
            }
        })
        alertBuilder.setNegativeButton(R.string.cancel, {
            dialog: DialogInterface?, which: Int ->
            dialog!!.cancel()
        })
        alertBuilder.show()
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        this.setSelectedMenuItem(position)
    }

    private fun setSelectedMenuItem(index: Int) {
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        val view: MenuItem = navigation.menu.getItem(index)
        navigation.selectedItemId = view.itemId
    }

    private fun showMe() {
        this.mPager.currentItem = 1
        this.setSelectedMenuItem(1)
    }

    private fun showRecord() {
        this.mPager.currentItem = 2
        this.setSelectedMenuItem(2)
    }

    private fun showWallet() {
        this.mPager.currentItem = 0
        this.setSelectedMenuItem(0)
    }
    override fun accountSwitchedTo(newContext: Account) {

    }

}