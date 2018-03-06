package io.forus.me.entities

import io.forus.me.entities.base.WalletItem

/**
 * Created by martijn.doornik on 16/02/2018.
 */
class Token(address: String, name: String, val value: Double) : WalletItem(address, name) {
    override val amount: String
        get() = value.toString()
}