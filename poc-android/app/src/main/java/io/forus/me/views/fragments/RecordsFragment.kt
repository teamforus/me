package io.forus.me.views.fragments

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import io.forus.me.R

import io.forus.me.entities.Record
import io.forus.me.entities.RecordCategory
import io.forus.me.services.AccountService
import io.forus.me.services.DatabaseService
import io.forus.me.services.RecordService

class RecordsFragment : Fragment(), View.OnClickListener {

    private lateinit var mAddButton: FloatingActionButton
    private lateinit var mListener: RecordsListener
    private lateinit var mCategoryList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onClick(view: View?) {
        if (view!!.id == mAddButton.id) {
            this.mListener.onRequestNewRecordCategory()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_records, container, false)
        this.mCategoryList = view.findViewById(R.id.record_category_list)
        this.mCategoryList.layoutManager = LinearLayoutManager(context)
        this.mCategoryList.adapter = RecordCategoryAdapter(this.mListener)

        RecordService.inject.getRecordCategoriesByAccount(AccountService.currentAddress)!!.observe(this, Observer<List<RecordCategory>> {
                recordCategories: List<RecordCategory>? ->
            run {
                if (recordCategories != null) {
                    (this.mCategoryList.adapter as RecordCategoryAdapter).recordCategories = recordCategories
                    this.mCategoryList.adapter.notifyDataSetChanged()
                } else {
                    System.err.println("No categories found!")
                }
            }
        })

        this.mAddButton = view.findViewById(R.id.record_category_add_button)
        this.mAddButton.setOnClickListener(this)

        return view
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is RecordsListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement RecordsListener")
        }
    }

    interface RecordsListener {
        fun onItemSelect(record: Record)
        fun onCategorySelect(category: RecordCategory)
        fun onRequestNewRecord(category: RecordCategory)
        fun onRequestNewRecordCategory()
    }

}
