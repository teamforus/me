package io.forus.me.entities

import android.arch.persistence.room.Entity
import io.forus.me.entities.base.WalletItem

/**
 * Created by martijn.doornik on 16/02/2018.
 */
//@Entity
class Service(address:String, name: String, val value: Int = 0) : WalletItem(address, name) {
    override val amount: String
        get() = value.toString() + 'x'
}