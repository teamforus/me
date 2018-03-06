package io.forus.me.views.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.forus.me.R
import io.forus.me.entities.RecordCategory
import io.forus.me.services.RecordService

class RecordCategoryAdapter(val recordListener: RecordsFragment.RecordsListener, var recordCategories: List<RecordCategory> = ArrayList()   ) : RecyclerView.Adapter<RecordCategoryAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return this.recordCategories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = recordCategories[position]
        holder.mLabelView.text = recordCategories[position].label

        holder.mView.setOnClickListener {
            recordListener.onCategorySelect(recordCategories[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_record_category, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mLabelView: TextView = mView.findViewById(R.id.record_category_name)
        var mItem: RecordCategory? = null

        override fun toString(): String {
            return super.toString() + " '" + mLabelView.text + "'"
        }
    }
}