package io.forus.me.entities

import io.forus.me.entities.base.WalletItem

/**
 * Created by martijn.doornik on 16/02/2018.
 */
class Service(address:String, name: String, val value: Int) : WalletItem(address, name) {
    override val amount: String
        get() = value.toString() + 'x'
}