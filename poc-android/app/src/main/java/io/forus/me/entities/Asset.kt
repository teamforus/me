package io.forus.me.entities

import io.forus.me.entities.base.WalletItem

/**
 * Created by martijn.doornik on 16/02/2018.
 */
class Asset(address: String, name: String) : WalletItem(address, name) {
    override val amount: String
        get() {
            return ""
        }

    override val label: String
            get() = this.name
}