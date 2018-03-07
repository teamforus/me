//
//  WalletViewController.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

var walletVC = WalletTableViewController()

class WalletTableViewController: UITableViewController {
    
    let model = WalletModel()
    @IBOutlet weak var etherBalance: UILabel!
    
    @IBAction func Send(_ sender: Any) {
        print("send")
        sendEther()
    }
    
    @IBAction func request(_ sender: Any) {
        print("recieve")
    }
    
    @IBOutlet weak var walletLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        walletVC = self
    }
    
    func sendEther() {
        let alertController = UIAlertController(title: "Send ether", message: "Fill amount and destination", preferredStyle: .alert)
        
        
        let confirmAction = UIAlertAction(title: "Enter", style: .default) { (_) in
            let amount = alertController.textFields?[0].text
            let address = alertController.textFields?[1].text
            
            self.model.performEtherTransaction(destination: address!, amount: amount!)
        }
        
        //the cancel action doing nothing
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel) { (_) in }
        
        //adding textfields to our dialog box
        alertController.addTextField { (textField) in
            textField.placeholder = "Enter amount"
        }
        alertController.addTextField { (textField) in
            textField.placeholder = "Enter destination address"
        }
        
        // set temporary fields
        alertController.textFields?[0].text = model.hardcodedAmount
        alertController.textFields?[1].text = model.account2Address
        
        //adding the action to dialogbox
        alertController.addAction(confirmAction)
        alertController.addAction(cancelAction)
        
        //finally presenting the dialog box
        self.present(alertController, animated: true, completion: nil)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
