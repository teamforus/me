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
    var account1PrivateKey = "0x3897473832ebdbbd3f29f2c9b1e88a7d2fb40c0071dcbc073a3f17889bbac121"
    var account1Address = "0x7B2aFE6d5E16944084eaA292Ecaa9c3B6469B445"
    
    let hardcodedAmount = "2200000000000000"
    
    var account2Address = "0xE2028Dbcb6F5EA9Cfba40a777e983A81469D0034"
    
    var labelText = "Loaded Wallet VC"
    
    let socket = WebSocket(url: URL(string: "ws://136.144.185.48:8484/")!)
    
    func websocketDidConnect(socket: WebSocketClient) {
        print("websocket is connected!")
        
        loadAccount()
        getBalance(forAccount: account1Address)
    }
    
    func websocketDidDisconnect(socket: WebSocketClient, error: Error?) {
        socket.connect()
        print("websocket is disconnected: \(String(describing: error?.localizedDescription))")
    }
    
    func websocketDidReceiveMessage(socket: WebSocketClient, text: String) {
        processResponse(response: text)
    }
    
    func processResponse(response: String) {
        print("got response: \(response)")
        
        var responseJSON = JSON.init(parseJSON: response)
        
        if responseJSON["eventName"] == "balance" {
            updateBalance(json: responseJSON)
        }
    }
    
    func loadAccount() {
        let transaction = """
        {"eventName":"addAccount","eventData":{"privateKey":"\(account1PrivateKey)"}}
        """
        
        print("loading account: \(transaction)")
        
        self.socket.write(string: transaction)
    }
    
    func getBalance(forAccount account: String) {
        let transaction = """
            {"eventName":"getBalance","eventData":{"address":"\(account)"}}
            """
        
        print("getting balance: \(transaction)")
        
        self.socket.write(string: transaction)
    }
    
    func updateBalance(json: JSON) {
        let balance = json["eventData"]["balance"]
        let balanceInEther = Double(balance.string!)! / 100000000000000000
        let roundedBalance = Double(round(1000*balanceInEther)/1000)
        walletVC.etherBalance.text = String(roundedBalance)
    }
    
    func performEtherTransaction(destination: String, amount: String) {
        let transaction = """
            {"eventName":"sendEther","eventData":{"from":"0x7B2aFE6d5E16944084eaA292Ecaa9c3B6469B445","to":"\(destination)","amount":"\(amount)"}}
            """
        
        print("performing ether transaction: \(transaction)")
        
        self.socket.write(string: transaction)
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
    
    init() {
        socket.delegate = self
        socket.connect()
    }
    
    deinit {
        socket.disconnect(forceTimeout: 0)
        socket.delegate = nil
    }
}

