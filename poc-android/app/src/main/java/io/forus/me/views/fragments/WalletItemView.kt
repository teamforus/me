package io.forus.me.views.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.forus.me.R

import io.forus.me.views.fragments.WalletFragment.WalletListener
import io.forus.me.entities.base.WalletItem

class WalletItemView(private val mListener: WalletListener?, var walletItems: List<WalletItem> = ArrayList()) : RecyclerView.Adapter<WalletItemView.ViewHolder>() {

    override fun getItemCount(): Int {
        return walletItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = walletItems[position]
        holder.mQrView.setImageBitmap(walletItems[position].qrCode)
        holder.mLabelView.text = walletItems[position].label

        holder.mView.setOnClickListener {
            mListener?.onItemSelect(holder.mItem as WalletItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_wallet, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mQrView: ImageView = mView.findViewById(R.id.qr)
        val mLabelView: TextView = mView.findViewById(R.id.label)
        var mItem: WalletItem? = null

        override fun toString(): String {
            return super.toString() + " '" + mLabelView.text + "'"
        }
    }
}
