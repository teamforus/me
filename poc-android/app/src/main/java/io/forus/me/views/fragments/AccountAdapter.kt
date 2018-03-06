package io.forus.me.views.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.forus.me.R
import io.forus.me.entities.Account

class AccountAdapter(val accountListener: MeFragment.AccountListener, var accounts: List<Account> = ArrayList()) : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return this.accounts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = accounts[position]
        holder.mLabelView.text = accounts[position].name

        holder.mView.setOnClickListener {
            accountListener.accountSwitchedTo(accounts[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_record_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mLabelView: TextView = mView.findViewById(R.id.record_name)
        var mItem: Account? = null

        override fun toString(): String {
            return super.toString() + " '" + mLabelView.text + "'"
        }
    }
}