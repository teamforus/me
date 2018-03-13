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
import io.forus.me.R
import io.forus.me.entities.Account
import io.forus.me.services.AccountService

class MeFragment : Fragment(), View.OnClickListener {

    private lateinit var mAccountList: RecyclerView
    private lateinit var mAddButton: FloatingActionButton
    private lateinit var mListener: AccountListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AccountListener) {
            this.mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement AccountListener")
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == this.mAddButton.id) {
            this.mListener.onNewAccountRequested()
            //AccountService.newAccount("TestAddress", "TestName")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_accounts, container, false)

        this.mAccountList = view.findViewById(R.id.account_list)
        this.mAccountList.layoutManager = LinearLayoutManager(this.context)
        this.mAccountList.adapter = AccountAdapter(this.mListener)

        AccountService.getAccounts()?.observe(this, Observer<List<Account>> {
            accounts: List<Account>? ->
            kotlin.run {
                if (accounts != null) {
                    (this.mAccountList.adapter as AccountAdapter).accounts = accounts
                    this.mAccountList.adapter.notifyDataSetChanged()
                }
            }
        })

        this.mAddButton = view.findViewById(R.id.account_add_button)
        this.mAddButton.setOnClickListener(this)

        return view
    }

    interface AccountListener {
        fun accountSwitchedTo(newContext: Account)
        fun onNewAccountRequested()
    }
}