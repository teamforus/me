package io.forus.me.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import io.forus.me.entities.base.WalletItem

/**
 * Created by martijn.doornik on 16/02/2018.
 */
@Entity
class Token(address: String = "", name: String = "", @Ignore val value: Double = 0.0) : WalletItem(address, name) {
    override val amount: String
        get() = value.toString()
}