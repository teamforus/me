package io.forus.me.views.wallet

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.forus.me.R
import io.forus.me.WalletItemActivity
import io.forus.me.entities.base.EthereumItem
import io.forus.me.entities.base.WalletItem
import io.forus.me.helpers.ThreadHelper
import io.forus.me.services.IdentityService
import io.forus.me.services.TokenService
import io.forus.me.views.base.TitledFragment
import java.util.concurrent.Callable

/**
 * Created by martijn.doornik on 22/03/2018.
 */
class TokenFragment : TitledFragment(), WalletListAdapter.ItemSelectionListener<WalletItem> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.wallet_items_fragment, container, false)

        val tokenData = ThreadHelper.await(Callable {
            TokenService.getTokensByIdentity(IdentityService.currentAddress)
        })
        val listView: RecyclerView = view.findViewById(R.id.wallet_list)
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = WalletListAdapter(this, R.layout.token_list_item_view, this, tokenData!!)

        return view
    }

    override fun onItemSelect(selected: WalletItem) {
        val intent = Intent(this.context, WalletItemActivity::class.java)
        intent.putExtra(WalletItemActivity.WALLET_ITEM_KEY, selected.toJson().toString())
        startActivity(intent)
    }



}