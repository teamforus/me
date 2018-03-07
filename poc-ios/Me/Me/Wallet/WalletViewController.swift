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
//        sendEther(to)
    }
    
    @IBAction func request(_ sender: Any) {
        print("recieve")
    }
    
    @IBOutlet weak var walletLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        walletVC = self
    }
    
    func sendEther(to address: String) {
        let alertController = UIAlertController(title: "Send ether", message: "Destination:\n\(address)", preferredStyle: .alert)
        
        let confirmAction = UIAlertAction(title: "Send", style: .default) { (_) in
            let amount = alertController.textFields?[0].text
            
            self.model.performEtherTransaction(destination: address, amount: amount!)
        }
        
        //the cancel action doing nothing
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel) { (_) in }
        
        //adding textfields to our dialog box
        alertController.addTextField { (textField) in
            textField.placeholder = "Enter amount"
        }
        
        // set temporary fields
        alertController.textFields?[0].text = model.hardcodedAmount
        
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
