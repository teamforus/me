package io.forus.me.views.record

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.forus.me.R
import io.forus.me.views.base.TitledFragment

/**
 * Created by martijn.doornik on 15/03/2018.
 */
class RecordsFragment : TitledFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.records_fragment, container, false)

        return view
    }

}