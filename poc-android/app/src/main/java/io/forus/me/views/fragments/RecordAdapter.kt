package io.forus.me.views.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.forus.me.R
import io.forus.me.entities.Record

class RecordAdapter(var records: List<Record> = ArrayList(), val recordListener: RecordsFragment.RecordsListener) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return this.records.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = records[position]
        holder.mLabelView.text = records[position].name

        holder.mView.setOnClickListener {
            recordListener.onItemSelect(records[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_record_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mLabelView: TextView = mView.findViewById(R.id.record_name)
        var mItem: Record? = null

        override fun toString(): String {
            return super.toString() + " '" + mLabelView.text + "'"
        }
    }
}