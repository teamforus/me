//
//  RecordsModel.swift
//  Me
//
//  Created by Jamal on 01/03/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import Foundation
import Starscream
import SwiftyJSON

class RecordsModel: WebSocketDelegate {
    var labelText = "Loaded Records VC"
    
    let socket = WebSocket(url: URL(string: "ws://136.144.185.48:8484/")!)
    
    func websocketDidConnect(socket: WebSocketClient) {
        print("websocket is connected!")
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
        
        if responseJSON["eventName"] == "ipfsAttributeChanged" {
            displayName(name: "\(responseJSON["eventData"]["attributeValue"])")
        }
    }
    
    func displayName(name: String) {
        print("ipfs name: \(name)")
        
        recordsVC.setLabel(name: name)
//        print()
//        recordsVC.nameLabel.text = "somename"
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
    
    init() {
        socket.delegate = self
        socket.connect()
    }
    
    deinit {
        socket.disconnect(forceTimeout: 0)
        socket.delegate = nil
    }
}
