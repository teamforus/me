package io.forus.me.views.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import io.forus.me.R
import io.forus.me.entities.Token
import io.forus.me.entities.base.WalletItem
import io.forus.me.services.AccountService
import io.forus.me.services.TokenService
import io.forus.me.services.Web3Service

class WalletFragment : Fragment(), View.OnClickListener {
    private var mListener: WalletListener? = null
    private lateinit var addButton: FloatingActionButton

    override fun onClick(view: View?) {
        if (this.addButton == view) {
            this.mListener?.onRequestNewWalletItem()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet_list, container, false)

        val list:View = view.findViewById(R.id.wallet_items_list)
        // Set the adapter
        if (list is RecyclerView) {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = WalletItemView(mListener)
            TokenService.getTokensByAccount(AccountService.currentAddress)!!.observe(this, Observer<List<Token>> {
                tokens: List<Token>? ->
                run {
                    if (tokens != null) {
                        (list.adapter as WalletItemView).walletItems = tokens
                    }
                }
            })
        }
        addButton = view.findViewById(R.id.add_wallet_item_button)
        addButton.setOnClickListener(this)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is WalletListener) {
            this.mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement WalletListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface WalletListener {
        fun onItemSelect(item: WalletItem)
        fun onRequestNewWalletItem()
    }
}
