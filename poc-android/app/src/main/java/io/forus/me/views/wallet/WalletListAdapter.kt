package io.forus.me.views.wallet

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.forus.me.R
import io.forus.me.WalletItemActivity
import io.forus.me.entities.base.WalletItem
import io.forus.me.helpers.LiveDataAdapter

/**
 * Created by martijn.doornik on 22/03/2018.
 */
class WalletListAdapter<T : WalletItem>(lifecycleOwner: LifecycleOwner, private val resource: Int, private val listener:ItemSelectionListener<T>, data: LiveData<List<T>>) : LiveDataAdapter<T, WalletListAdapter.WalletViewHolder>(lifecycleOwner, data) {

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        if (holder.nameView != null) holder.nameView.text = items[position].name
        if (holder.qrView != null) holder.qrView.setImageBitmap(items[position].qrCode)
        if (holder.valueView != null) holder.valueView.text = items[position].amount
        holder.itemView.setOnClickListener({
            listener.onItemSelect(items[position])
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(resource, parent,false)
        return WalletViewHolder(view)
    }

    class WalletViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val qrView: ImageView? = view.findViewById(R.id.qr_view)
        val nameView: TextView? = view.findViewById(R.id.name_view)
        val valueView: TextView? = view.findViewById(R.id.value_view)
    }

    interface ItemSelectionListener<in T> {
        fun onItemSelect(selected:T)
    }
}