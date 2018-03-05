package io.forus.me.services

import io.forus.me.entities.Asset
import io.forus.me.entities.Service
import io.forus.me.entities.Token
import java.util.ArrayList

import io.forus.me.entities.base.WalletItem

/**
 * Created by martijn.doornik on 15/02/2018.
 */

internal class Web3Service private constructor() {

    val walletItems: List<WalletItem>
        get() {
            val ret = ArrayList<WalletItem>()
            ret.add(Token("0x45678901234567890123", "BAT", 200.85))
            ret.add(Service("0x12345678901234567890", "World Expo Entrance", 2))
            ret.add(Asset("0x23456789012345678901", "Tesla Model 3 - KH-355-X"))
            ret.add(Service("0x34567890123456789012", "Paradigm NYE Groningen Entrance", 4))
            return ret

        }

    companion object {
        val instance = Web3Service()
    }
}
