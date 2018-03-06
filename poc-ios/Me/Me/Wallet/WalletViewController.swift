//
//  WalletViewController.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

class WalletTableViewController: UITableViewController {
    
    let model = WalletModel()

    @IBAction func Send(_ sender: Any) {
        print("send")
    }
    
    @IBAction func request(_ sender: Any) {
        print("recieve")
    }
    
    @IBOutlet weak var walletLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        walletLabel.text = model.labelText
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
