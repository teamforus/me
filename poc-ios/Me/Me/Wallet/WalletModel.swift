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
    
    let privateKey = "0x3897473832ebdbbd3f29f2c9b1e88a7d2fb40c0071dcbc073a3f17889bbac121"
    
    // destination: 0xDC6AFa0bc952FD8b60e89aA765Dd70EE0C559dc8
    var address = String()
    
    let hardcodedAmount = "200000000000000000"
    
    var labelText = "Loaded Wallet VC"
    
    let socket = WebSocket(url: URL(string: "ws://136.144.185.48:8484/")!)
    
    func websocketDidConnect(socket: WebSocketClient) {
        print("websocket is connected!")
        
        loadAccount()
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
        
        if responseJSON["eventName"] == "newAccount" {
            address = String(describing: responseJSON["eventData"]["address"])
            print("address = \(address)")
            getBalance(forAccount: address)
        }
        
        if responseJSON["eventName"] == "balance" {
            updateBalance(json: responseJSON)
        }
        
        if responseJSON["eventName"] == "ipfsAttributeChanged" {
            displayName(name: "\(responseJSON["eventData"]["attributeValue"])")
        }
    }
    
    func displayName(name: String) {
        print("ipfs name: \(name)")
    }
    
    func loadAccount() {
        let transaction = """
        {"eventName":"addAccount","eventData":{"privateKey":"\(privateKey)"}}
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
        if balance != JSON.null {
            let balanceInEther = Double(balance.string!)! / 1000000000000000000
            let roundedBalance = Double(round(1000*balanceInEther)/1000)
            //            walletVC.etherBalance.text = String(describing: roundedBalance)
        }
    }
    
    func performEtherTransaction(destination: String, amount: String) {
        print("amount: \(amount)")
        
        let transaction = """
        {"eventName":"sendEther","eventData":{"from":"\(address)","to":"\(destination)","amount":"\(amount)"}}
        """
        
        print("performing ether transaction: \(transaction)")
        
        self.socket.write(string: transaction)
    }
    
    func updateName(name: String) {
        let transaction = """
        {"eventName":"setIPFSAttribute","eventData":{"ownerAddress":"0x7B2aFE6d5E16944084eaA292Ecaa9c3B6469B445","identityAddress":"0x6E5D5fda754a18eE47357470D8F553BBd528a3b0","attributeName":"Name","attributeValue":"\(name)"}}
        """
        
        print("performing updateName transaction: \(transaction)")
        
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

