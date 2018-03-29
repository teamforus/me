package io.forus.me.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import io.forus.me.entities.base.WalletItem
import io.forus.me.services.IdentityService

/**
 * Created by martijn.doornik on 16/02/2018.
 */
@Entity
class Token(
        address: String = "",
        name: String = "",
        identity:String = IdentityService.currentAddress,
        @Ignore val value: Double = 0.0
) : WalletItem(address, name, identity) {
    override val amount: String
        get() = value.toString()
}