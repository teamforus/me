//
//  WalletModel.swift
//  Me
//
//  Created by Jamal on 01/03/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import Foundation
import Starscream
import SwiftyJSON

class WalletModel: WebSocketDelegate {
    var myAccount = "0x7ab7fdd0d2f4fdcf869e28a94b5beceadc680a65"
    
    var labelText = "Loaded Wallet VC"
    
    let socket = WebSocket(url: URL(string: "ws://136.144.185.48:8484/")!)
    
    func websocketDidConnect(socket: WebSocketClient) {
        print("websocket is connected!")
        
        getBalance()
        
    }
    
    func websocketDidDisconnect(socket: WebSocketClient, error: Error?) {
        print("websocket is disconnected: \(String(describing: error?.localizedDescription))")
    }
    
    func websocketDidReceiveMessage(socket: WebSocketClient, text: String) {
        processResponse(response: text)
    }
    
    func processResponse(response: String) {
        var responseJSON = JSON.init(parseJSON: response)
        
        if responseJSON["eventName"] == "balance" {
            updateBalance(json: responseJSON)
        }
    }
    
    func updateBalance(json: JSON) {
        let balance = json["eventData"]["balance"]
        let balanceInEther = Double(balance.string!)! / 100000000000000000
        let roundedBalance = Double(round(1000*balanceInEther)/1000)
        walletVC.etherBalance.text = String(roundedBalance)
    }
    
    func websocketDidReceiveData(socket: WebSocketClient, data: Data) {
        print("got some data: \(data.count)")
    }
    
    func createAccount() {
        let message = """
                {"eventName":"createAccount"}
            """
        
        self.socket.write(string: message)
    }
    
    func getBalance() {
        let message = """
                {"eventName":"getBalance","eventData":{"address":"0x7ab7fdd0d2f4fdcf869e28a94b5beceadc680a65"}}
            """
        self.socket.write(string: message)
    }
    
    init() {
        socket.delegate = self
        socket.connect()
    }
    
    deinit {
        socket.disconnect(forceTimeout: 0)
        socket.delegate = nil
    }
}

